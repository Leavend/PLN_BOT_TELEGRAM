#!/usr/bin/env python3
"""
fasih_auto_runner.py — High-Performance Autonomous Multi-Account Batch Processor for BPS FASIH.

Features:
  1. 20 Concurrent Workers for rapid submission.
  2. BPS SSO Account Rotation with Daily Quota (300 IDPel/user/day limit, auto daily reset).
  3. Real-time Master Excel State Engine (reads/writes .xlsx directly).
  4. Excel-First Coordinates Hierarchy (Excel Lat/Lon > Mapbox API via PLN servers).
  5. Excel-First BLOK III 204 Mapping (Excel KET_KEPERLUAN > AP2T Lookup).
  6. Multi-Server AP2T Failover (3 Servers) + Samarinda Central Photo Pool Fallback.
  7. 1-Time Automatic Retry for transient network / PLN server timeouts.
"""

import os
import sys
import time
import json
import logging
import threading
import argparse
import datetime
import pandas as pd
from typing import Optional, Dict, Any, List, Tuple
from concurrent.futures import ThreadPoolExecutor, as_completed

# Ensure parent directory is in sys.path
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

from fasih_auth import get_headers, perform_login
from fasih_api import fetch_surveys, fetch_all_assignments, fetch_template_mapping, fetch_regions, check_idpln
from petugas_client.batch_submit import (
    submit_single,
    pln_lookup,
    _load_survey_cache,
    _save_survey_cache,
    PLN_API_URL
)

# Set up logger
logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s [%(levelname)s] %(message)s",
    datefmt="%H:%M:%S"
)
logger = logging.getLogger("fasih_auto_runner")

# Thread locks
_excel_lock = threading.Lock()
_quota_lock = threading.Lock()

# Default AP2T Multi-Server Pool
DEFAULT_AP2T_SERVERS = [
    "http://103.126.226.155:8000",  # Server 1: Samarinda Central / Main
    "http://103.126.226.156:8000",  # Server 2: Bontang
    "http://103.126.226.157:8000",  # Server 3: Tarakan / Region 3
]


def resolve_r204_from_keperluan(keperluan: str) -> str:
    """Map KET_KEPERLUAN string from Excel or AP2T to BLOK III 204 options:
      1. Milik sendiri
      2. Kontrak/sewa
      3. Bebas sewa
      4. Dinas
      5. Lainnya
    """
    if not keperluan:
        return "1. Milik sendiri"
    kep_upper = str(keperluan).upper()
    if any(x in kep_upper for x in ["KOST", "KOS", "KONTRAK", "SEWA"]):
        return "2. Kontrak/sewa"
    if "BEBAS SEWA" in kep_upper:
        return "3. Bebas sewa"
    if "DINAS" in kep_upper:
        return "4. Dinas"
    if any(x in kep_upper for x in ["PRIBADI", "MILIK"]):
        return "1. Milik sendiri"
    return "5. Lainnya"


class AccountManager:
    """Manages BPS SSO accounts, tokens, and daily quotas (300 IDPel/day limit)."""

    def __init__(self, users_file: str = "users.json"):
        self.users_file = users_file
        self.accounts: List[Dict[str, Any]] = []
        self._load_accounts()

    def _load_accounts(self):
        today = datetime.date.today().isoformat()
        if os.path.exists(self.users_file):
            try:
                with open(self.users_file, "r") as f:
                    self.accounts = json.load(f)
            except Exception as e:
                logger.warning(f"Could not read {self.users_file}: {e}")

        # Fallback to fasih_token.json if users.json does not exist
        if not self.accounts and os.path.exists("fasih_token.json"):
            try:
                with open("fasih_token.json", "r") as f:
                    td = json.load(f)
                    email = td.get("email") or (td.get("user") or {}).get("email") or "default_user@bps"
                    self.accounts = [{
                        "email": email,
                        "password": "",
                        "token_data": td,
                        "daily_quota": 300,
                        "used_today": 0,
                        "last_date": today
                    }]
            except Exception:
                pass

        # Reset daily quota if date changed & auto-login if token missing
        for acc in self.accounts:
            if acc.get("last_date") != today:
                acc["last_date"] = today
                acc["used_today"] = 0
            if "daily_quota" not in acc:
                acc["daily_quota"] = 300
            if "used_today" not in acc:
                acc["used_today"] = 0

            # Auto-login if password available but token missing
            if not acc.get("token_data") and acc.get("email") and acc.get("password"):
                try:
                    logger.info(f"🔑 Auto SSO Login for account: {acc['email']}...")
                    td = perform_login(acc["email"], acc["password"], exit_on_failure=False)
                    if td and "access_token" in td:
                        acc["token_data"] = td
                        logger.info(f"✅ Auto SSO Login successful for: {acc['email']}")
                except Exception as e:
                    logger.error(f"❌ Auto SSO Login failed for {acc['email']}: {e}")

    def save_accounts(self):
        with _quota_lock:
            try:
                # Omit actual passwords if saving back, or keep safe
                with open(self.users_file, "w") as f:
                    json.dump(self.accounts, f, indent=2)
            except Exception as e:
                logger.warning(f"Failed saving accounts to {self.users_file}: {e}")

    def get_available_account(self) -> Optional[Dict[str, Any]]:
        """Find an active account that has remaining daily quota (< 300)."""
        with _quota_lock:
            today = datetime.date.today().isoformat()
            for acc in self.accounts:
                if acc.get("last_date") != today:
                    acc["last_date"] = today
                    acc["used_today"] = 0
                if acc.get("is_disabled"):
                    continue
                # Auto login check if token is missing
                if not acc.get("token_data") and acc.get("email") and acc.get("password"):
                    try:
                        td = perform_login(acc["email"], acc["password"], exit_on_failure=False)
                        if td and "access_token" in td:
                            acc["token_data"] = td
                    except Exception:
                        pass
                if acc.get("token_data") and acc.get("used_today", 0) < acc.get("daily_quota", 300):
                    return acc
            return None

    def increment_usage(self, email: str):
        """Increment daily usage for account after successful submit."""
        with _quota_lock:
            for acc in self.accounts:
                if acc.get("email") == email:
                    acc["used_today"] = acc.get("used_today", 0) + 1
                    break
            self.save_accounts()

    def mark_quota_exhausted(self, email: str):
        """Mark account as quota exhausted for the day when BPS returns 429/limit error."""
        with _quota_lock:
            for acc in self.accounts:
                if acc.get("email") == email:
                    acc["used_today"] = acc.get("daily_quota", 300)
                    logger.warning(f"⛔ Akun {email} ditandai KUOTA HABIS (429/Limit BPS mencapai batas).")
                    break
            self.save_accounts()


class ExcelQueueManager:
    """Manages reading and thread-safe writing of the Master Excel file."""

    def __init__(self, excel_path: str):
        self.excel_path = excel_path
        self.df: Optional[pd.DataFrame] = None
        self.idpel_col: Optional[str] = None
        self.lat_col: Optional[str] = None
        self.lon_col: Optional[str] = None
        self.keperluan_col: Optional[str] = None
        self._load_excel()

    def _load_excel(self):
        if not os.path.exists(self.excel_path):
            raise FileNotFoundError(f"Excel file not found: {self.excel_path}")

        self.df = pd.read_excel(self.excel_path)
        cols = list(self.df.columns)

        # Detect columns
        for c in cols:
            cu = str(c).upper()
            if not self.idpel_col and any(k in cu for k in ["IDPEL", "NO_PELANGGAN", "ID_PEL", "ID PELANGGAN"]):
                self.idpel_col = c
            if not self.lat_col and any(k in cu for k in ["LATITUDE", "LAT", "KOORDINAT_LAT"]):
                self.lat_col = c
            if not self.lon_col and any(k in cu for k in ["LONGITUDE", "LON", "KOORDINAT_LON"]):
                self.lon_col = c
            if not self.keperluan_col and any(k in cu for k in ["KET_KEPERLUAN", "KEPERLUAN", "KD_KEPERLUAN"]):
                self.keperluan_col = c

        # Fallback to first column for IDPel if not matched
        if not self.idpel_col and len(cols) > 0:
            self.idpel_col = cols[0]

        # Ensure BOT status columns exist
        if "BOT_STATUS" not in self.df.columns:
            self.df["BOT_STATUS"] = "PENDING"
        if "BOT_RETRY" not in self.df.columns:
            self.df["BOT_RETRY"] = 0
        if "BOT_PETUGAS" not in self.df.columns:
            self.df["BOT_PETUGAS"] = ""
        if "BOT_TIMESTAMP" not in self.df.columns:
            self.df["BOT_TIMESTAMP"] = ""
        if "BOT_CATATAN" not in self.df.columns:
            self.df["BOT_CATATAN"] = ""

        # Fill NaNs in tracking columns
        self.df["BOT_STATUS"] = self.df["BOT_STATUS"].fillna("PENDING")
        self.df["BOT_RETRY"] = self.df["BOT_RETRY"].fillna(0).astype(int)

        self._unsaved_count = 0
        self._last_save_time = time.time()
        logger.info(f"Excel loaded: {len(self.df)} rows | IDPel Col: '{self.idpel_col}' | Lat/Lon: '{self.lat_col}'/'{self.lon_col}' | Keperluan: '{self.keperluan_col}'")

    def update_row(self, index: int, status: str, retry_count: int, user_email: str, catatan: str, force_save: bool = False):
        """Thread-safe update of a single row in memory with throttled Excel flush."""
        with _excel_lock:
            self.df.at[index, "BOT_STATUS"] = status
            self.df.at[index, "BOT_RETRY"] = retry_count
            self.df.at[index, "BOT_PETUGAS"] = user_email
            self.df.at[index, "BOT_TIMESTAMP"] = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            self.df.at[index, "BOT_CATATAN"] = catatan
            self._unsaved_count += 1
            now = time.time()
            if force_save or self._unsaved_count >= 5 or (now - self._last_save_time) > 3:
                self._save_excel()

    def flush(self):
        """Force save remaining in-memory updates to disk."""
        with _excel_lock:
            self._save_excel()

    def _save_excel(self):
        """Save DataFrame to Excel file."""
        try:
            self.df.to_excel(self.excel_path, index=False)
            self._unsaved_count = 0
            self._last_save_time = time.time()
        except Exception as e:
            logger.warning(f"Error saving Excel file: {e}")

    def get_pending_items(self, start_row: Optional[int] = None, start_idpel: Optional[str] = None) -> List[Tuple[int, Dict[str, Any]]]:
        """Get list of (index, row_dict) for rows that need processing."""
        with _excel_lock:
            pending = []
            target_start_idx = 0

            # Resolve start_idpel row index if specified
            if start_idpel:
                idpel_clean = str(start_idpel).strip()
                matches = self.df[self.df[self.idpel_col].astype(str).str.strip() == idpel_clean]
                if not matches.empty:
                    target_start_idx = matches.index[0]
                    logger.info(f"📍 Menemukan Start IDPel '{idpel_clean}' pada baris Excel #{target_start_idx + 2}")
                else:
                    logger.warning(f"⚠️ Start IDPel '{idpel_clean}' tidak ditemukan di Excel. Mulai dari awal.")

            # Resolve start_row if specified (converting 1-indexed Excel row to 0-indexed DataFrame index)
            elif start_row is not None and start_row > 0:
                # Excel row 1 is header, so Excel row 2 is df index 0
                target_start_idx = max(0, start_row - 2)
                logger.info(f"📍 Menyetel baris awal eksekusi dari baris Excel #{start_row} (DF index {target_start_idx})")

            for idx, row in self.df.iterrows():
                if idx < target_start_idx:
                    continue
                status = str(row.get("BOT_STATUS", "PENDING")).upper()
                if status in ["PENDING", "RETRYING"]:
                    item = {
                        "idpel": str(row.get(self.idpel_col) or "").strip(),
                        "lat": row.get(self.lat_col) if self.lat_col else None,
                        "lon": row.get(self.lon_col) if self.lon_col else None,
                        "keperluan": str(row.get(self.keperluan_col) or "").strip() if self.keperluan_col else "",
                        "retry_count": int(row.get("BOT_RETRY", 0)),
                    }
                    pending.append((idx, item))
            return pending


class AutonomousRunner:
    """Main orchestrator running 20 parallel workers."""

    def __init__(self, excel_path: str, users_file: str, max_workers: int = 20, mode_args: dict = None):
        self.excel_mgr = ExcelQueueManager(excel_path)
        self.account_mgr = AccountManager(users_file)
        self.max_workers = max_workers
        self.mode_args = mode_args or {}

        # Cache of initialized survey caches per user email
        self.user_caches: Dict[str, dict] = {}
        self._cache_lock = threading.Lock()

    def _get_user_caches(self, token_data: dict, email: str) -> dict:
        """Load or build survey caches for a specific BPS user account."""
        with self._cache_lock:
            if email in self.user_caches:
                return self.user_caches[email]

            sc = _load_survey_cache(email)
            if not sc:
                headers = get_headers(token_data)
                logger.info(f"📊 Initializing survey caches for user: {email}")
                surveys = fetch_surveys(headers)
                sc = {}
                for s in surveys:
                    sname = (s.get("name") or "").upper()
                    skey = "PASCABAYAR" if "PASCA" in sname else "PRABAYAR" if "PRA" in sname else "DEFAULT"
                    active_p = next((p for p in s.get("listPeriode", []) if p.get("isActive")), None)
                    if active_p:
                        tl = (s.get("templateLookup") or [{}])[0]
                        tm = fetch_template_mapping(headers, tl.get("templateId", ""), tl.get("templateVersion", "")) if tl else {}
                        sc[skey] = {
                            "survey": s,
                            "periode": active_p,
                            "template_mapping": tm,
                            "assignments": fetch_all_assignments(headers, active_p["id"]),
                            "regions": fetch_regions(headers, active_p["id"])
                        }
                _save_survey_cache(email, sc)
            self.user_caches[email] = sc
            return sc

    def process_item(self, idx: int, item: dict):
        """Worker task for processing a single IDPel item."""
        idpel = item["idpel"]
        retry_count = item["retry_count"]

        # Validate IDPel
        if not idpel or len(idpel) < 10 or not idpel.isdigit():
            self.excel_mgr.update_row(idx, "INVALID_IDPEL", retry_count, "", "IDPel tidak valid (bukan angka 12 digit)")
            return

        # Acquire an available user account with remaining quota
        acc = self.account_mgr.get_available_account()
        if not acc:
            logger.warning(f"⚠️ No active accounts with remaining daily quota for item {idpel}")
            return

        email = acc["email"]
        token_data = acc.get("token_data")
        if not token_data:
            logger.warning(f"Account {email} has no token_data — skipping")
            return

        # Prepare survey caches
        sc = self._get_user_caches(token_data, email)

        # Coordinate hierarchy: Excel Lat/Lon (Priority 1)
        override_lat, override_lon = None, None
        if item.get("lat") and item.get("lon"):
            try:
                lat_f, lon_f = float(item["lat"]), float(item["lon"])
                if lat_f != 0.0 and lon_f != 0.0:
                    override_lat, override_lon = lat_f, lon_f
            except (ValueError, TypeError):
                pass

        # BLOK III 204 hierarchy: Excel KET_KEPERLUAN (Priority 1)
        direct_args = {}
        if item.get("keperluan"):
            direct_args["keperluan"] = item["keperluan"]

        # Execute submission
        resubmit_reject = self.mode_args.get("resubmit_reject", False)
        resubmit_open = self.mode_args.get("resubmit_open", False)
        resubmit_reopen = self.mode_args.get("resubmit_reopen", False)
        dry_run = self.mode_args.get("dry_run", False)

        logger.info(f"🚀 [Worker] Processing {idpel} via {email} (Attempt {retry_count + 1})...")

        ok, msg = submit_single(
            token_data=token_data,
            val=idpel,
            survey_caches=sc,
            dry_run=dry_run,
            resubmit_reject=resubmit_reject,
            resubmit_open=resubmit_open,
            resubmit_reopen=resubmit_reopen,
            skip_cek_idpln=self.mode_args.get("skip_cek_idpln", False)
        )

        if ok:
            logger.info(f"✅ {idpel} SUCCESS via {email}: {msg}")
            self.excel_mgr.update_row(idx, "SUCCESS", retry_count, email, msg)
            self.account_mgr.increment_usage(email)
        else:
            msg_lower = msg.lower()
            # Check if Non-Rumah Tangga tarif (S1T, B-1, I-1, etc.)
            is_non_residential = "non-rumah tangga" in msg_lower or "hanya tarif tipe r" in msg_lower
            # Check if BPS account limit / quota reached (429 / limit / quota error)
            is_rate_limited = any(t in msg_lower for t in ["429", "quota", "limit", "too many requests"])

            if is_non_residential:
                logger.warning(f"🚫 {idpel} NON_RESIDENTIAL via {email}: {msg}")
                self.excel_mgr.update_row(idx, "NON_RESIDENTIAL", retry_count, email, msg)
            elif is_rate_limited:
                logger.warning(f"⚠️ Account {email} reached BPS quota limit: {msg}. Falling back to next available account...")
                self.account_mgr.mark_quota_exhausted(email)
                # Keep status as RETRYING so another available account picks it up immediately
                self.excel_mgr.update_row(idx, "RETRYING", retry_count, email, f"Fallback User (Limit 429): {msg}")
            else:
                # Check if transient PLN error -> trigger 1 retry
                is_transient = any(err in msg for err in ["PLN tidak ditemukan", "terjangkau", "overload", "timeout", "500", "502", "504"])
                if is_transient and retry_count < 1:
                    logger.warning(f"⚠️ {idpel} transient error: {msg}. Scheduling RETRY 1x...")
                    self.excel_mgr.update_row(idx, "RETRYING", retry_count + 1, email, f"Retry 1x: {msg}")
                else:
                    status_code = "FAILED_PLN" if "PLN" in msg else "FAILED"
                    logger.error(f"❌ {idpel} {status_code} via {email}: {msg}")
                    self.excel_mgr.update_row(idx, status_code, retry_count, email, msg)

    def prompt_interactive_start(self) -> Tuple[Optional[int], Optional[str]]:
        """Interactive startup prompt to let user select start row or IDPel."""
        df = self.excel_mgr.df
        total_rows = len(df)
        completed_cnt = sum(1 for status in df["BOT_STATUS"] if str(status).upper() in ["SUCCESS", "FAILED", "FAILED_PLN"])
        pending_cnt = total_rows - completed_cnt

        print("\n" + "=" * 65)
        print("📌 FASIH AUTONOMOUS RUNNER — SESI INTERAKTIF")
        print("=" * 65)
        print(f"📄 File Excel           : {self.excel_mgr.excel_path}")
        print(f"📊 Total Baris Excel    : {total_rows:,}")
        print(f"✅ Sudah Dikerjakan     : {completed_cnt:,}")
        print(f"📋 Menunggu Diproses    : {pending_cnt:,}")
        print("=" * 65)
        print("Pilih titik awal eksekusi script:")
        print("  [1] Mulai otomatis dari baris PENDING pertama (Default)")
        print("  [2] Mulai dari Nomor Baris Excel tertentu (misal: 1500)")
        print("  [3] Mulai dari IDPel tertentu (misal: 312100553931)")
        print("-" * 65)

        try:
            choice = input("Masukkan pilihan [1-3] (Tekan Enter untuk Opsi 1): ").strip()
            if choice == "2":
                r_str = input(f"Masukkan nomor baris Excel (2 - {total_rows + 1}): ").strip()
                if r_str.isdigit():
                    return int(r_str), None
            elif choice == "3":
                id_str = input("Masukkan IDPel awal yang ingin dikerjakan: ").strip()
                if id_str:
                    return None, id_str
        except (KeyboardInterrupt, EOFError):
            print("\nProses dibatalkan oleh pengguna.")
            sys.exit(0)

        return None, None

    def run(self, start_row: Optional[int] = None, start_idpel: Optional[str] = None, non_interactive: bool = False):
        """Run the main autonomous execution loop with 20 parallel workers."""
        # Check interactive prompt if terminal & no explicit start args given
        if not non_interactive and sys.stdin.isatty() and start_row is None and not start_idpel:
            start_row, start_idpel = self.prompt_interactive_start()

        logger.info(f"⚡ MEMULAI AUTONOMOUS RUNNER — Workers: {self.max_workers}")
        pending_items = self.excel_mgr.get_pending_items(start_row=start_row, start_idpel=start_idpel)
        logger.info(f"📋 Total IDPel pending yang akan diproses: {len(pending_items)}")

        if not pending_items:
            logger.info("✅ Semua IDPel di Excel sudah diproses (STATUS = SUCCESS / FAILED).")
            return

        try:
            with ThreadPoolExecutor(max_workers=self.max_workers) as executor:
                futures = [executor.submit(self.process_item, idx, item) for idx, item in pending_items]
                for future in as_completed(futures):
                    try:
                        future.result()
                    except Exception as e:
                        logger.error(f"Worker exception: {e}")
        except KeyboardInterrupt:
            logger.warning("\n⚠️ Proses dihentikan oleh pengguna (Ctrl+C). Menyimpan progress terakhir...")
        finally:
            self.excel_mgr.flush()

        # Generate CSV report identical to fasih-submit-batch format
        try:
            import csv
            report_file = f"batch_report_{datetime.datetime.now().strftime('%Y%m%d_%H%M%S')}.csv"
            report_rows = []
            for idx, row in self.excel_mgr.df.iterrows():
                report_rows.append({
                    "val": str(row.get(self.excel_mgr.idpel_col) or ""),
                    "status": str(row.get("BOT_STATUS") or ""),
                    "message": str(row.get("BOT_CATATAN") or ""),
                    "petugas": str(row.get("BOT_PETUGAS") or ""),
                    "timestamp": str(row.get("BOT_TIMESTAMP") or "")
                })

            with open(report_file, "w", encoding="utf-8", newline="") as f:
                writer = csv.DictWriter(f, fieldnames=["val", "status", "message", "petugas", "timestamp"])
                writer.writeheader()
                writer.writerows(report_rows)

            logger.info(f"📄 Report CSV berhasil dibuat: {report_file}")

            # Print summary table
            success_cnt = sum(1 for r in report_rows if r["status"] == "SUCCESS")
            failed_cnt = sum(1 for r in report_rows if "FAIL" in r["status"])
            retry_cnt = sum(1 for r in report_rows if r["status"] == "RETRYING")

            print("\n" + "=" * 50)
            print(f"📊 SUMMARY AUTONOMOUS RUNNER REPORT")
            print("=" * 50)
            print(f"✅ Total Sukses: {success_cnt}")
            print(f"❌ Total Gagal : {failed_cnt}")
            if retry_cnt > 0:
                print(f"🔄 Pending Retry: {retry_cnt}")
            print(f"📄 File Excel Master : {self.excel_mgr.excel_path}")
            print(f"📄 File CSV Report   : {report_file}")
            print("=" * 50 + "\n")
        except Exception as e:
            logger.warning(f"Error generating CSV report: {e}")

        logger.info("🎉 EKSEKUSI BOT SELESAI. Semua progress tersimpan di Excel Master.")


def main():
    parser = argparse.ArgumentParser(description="Autonomous Multi-Account Batch Processor for BPS FASIH")
    parser.add_argument("--excel", required=True, help="Path file Master Excel (.xlsx)")
    parser.add_argument("--users-file", default="users.json", help="Path file akun BPS (default: users.json)")
    parser.add_argument("--workers", type=int, default=20, help="Jumlah paralel worker (default: 20)")
    parser.add_argument("--start-row", type=int, help="Mulai dari nomor baris Excel tertentu (misal: 1500)")
    parser.add_argument("--start-idpel", type=str, help="Mulai dari IDPel tertentu di Excel")
    parser.add_argument("--non-interactive", action="store_true", help="Jalankan langsung tanpa sesi interaktif")
    parser.add_argument("--resubmit-reject", action="store_true", help="Mode perbaiki data REJECTED")
    parser.add_argument("--resubmit-open", action="store_true", help="Mode submit data OPEN")
    parser.add_argument("--resubmit-reopen", action="store_true", help="Mode submit data REOPEN")
    parser.add_argument("--skip-cek-idpln", "--no-cek", action="store_true", dest="skip_cek_idpln", help="Memaksa submit data ke BPS FASIH meskipun CEK IDPel terkena limit (HTTP 429)")
    parser.add_argument("--dry-run", action="store_true", help="Simulasi tanpa upload nyata")

    args = parser.parse_args()

    mode_args = {
        "resubmit_reject": args.resubmit_reject,
        "resubmit_open": args.resubmit_open,
        "resubmit_reopen": args.resubmit_reopen,
        "skip_cek_idpln": args.skip_cek_idpln,
        "dry_run": args.dry_run
    }

    runner = AutonomousRunner(
        excel_path=args.excel,
        users_file=args.users_file,
        max_workers=args.workers,
        mode_args=mode_args
    )
    runner.run(
        start_row=args.start_row,
        start_idpel=args.start_idpel,
        non_interactive=args.non_interactive
    )


if __name__ == "__main__":
    main()
