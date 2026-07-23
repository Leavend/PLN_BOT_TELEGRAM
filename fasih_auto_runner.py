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

# Set up logger with unbuffered stdout handler and file logging in Logs-Runner/
LOGS_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), "Logs-Runner")
os.makedirs(LOGS_DIR, exist_ok=True)

session_time = datetime.datetime.now().strftime("%Y%m%d_%H%M%S")
log_filename = os.path.join(LOGS_DIR, f"auto_runner_{session_time}.log")
latest_filename = os.path.join(LOGS_DIR, "latest_runner.log")

stdout_formatter = logging.Formatter("%(asctime)s [%(levelname)s] %(message)s", datefmt="%H:%M:%S")
file_formatter = logging.Formatter("%(asctime)s [%(levelname)s] %(message)s", datefmt="%Y-%m-%d %H:%M:%S")

stdout_handler = logging.StreamHandler(sys.stdout)
stdout_handler.setLevel(logging.INFO)
stdout_handler.setFormatter(stdout_formatter)

file_handler = logging.FileHandler(log_filename, encoding="utf-8")
file_handler.setLevel(logging.INFO)
file_handler.setFormatter(file_formatter)

latest_handler = logging.FileHandler(latest_filename, mode="w", encoding="utf-8")
latest_handler.setLevel(logging.INFO)
latest_handler.setFormatter(file_formatter)

logger = logging.getLogger("fasih_auto_runner")
logger.setLevel(logging.INFO)
logger.handlers.clear()
logger.addHandler(stdout_handler)
logger.addHandler(file_handler)
logger.addHandler(latest_handler)
logger.propagate = False

# Suppress urllib3 connectionpool and requests duplicate warnings
logging.getLogger("urllib3").setLevel(logging.ERROR)
logging.getLogger("urllib3.connectionpool").setLevel(logging.ERROR)
logging.getLogger("requests").setLevel(logging.ERROR)

# Thread locks (Reentrant locks to prevent self-deadlocks)
_excel_lock = threading.RLock()
_quota_lock = threading.RLock()

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
                        "daily_quota": 400,
                        "used_today": 0,
                        "last_date": today
                    }]
            except Exception:
                pass

        # Reset daily quota if date changed
        for acc in self.accounts:
            if acc.get("last_date") != today:
                acc["last_date"] = today
                acc["used_today"] = 0
            if "daily_quota" not in acc:
                acc["daily_quota"] = 400
            if "used_today" not in acc:
                acc["used_today"] = 0

    def save_accounts(self):
        with _quota_lock:
            # Merge with on-disk users.json to ensure no accounts added externally are dropped
            if os.path.exists(self.users_file):
                try:
                    with open(self.users_file, "r") as f:
                        disk_accounts = json.load(f)
                    mem_emails = { (a.get("email") or "").lower() for a in self.accounts }
                    for da in disk_accounts:
                        e_l = (da.get("email") or "").lower()
                        if e_l and e_l not in mem_emails:
                            self.accounts.append(da)
                            mem_emails.add(e_l)
                except Exception:
                    pass

            tmp_path = self.users_file + ".tmp"
            try:
                with open(tmp_path, "w") as f:
                    json.dump(self.accounts, f, indent=2)
                if os.path.exists(tmp_path) and os.path.getsize(tmp_path) > 0:
                    os.replace(tmp_path, self.users_file)
            except Exception as e:
                logger.warning(f"Failed saving accounts to {self.users_file}: {e}")
                if os.path.exists(tmp_path):
                    try:
                        os.remove(tmp_path)
                    except Exception:
                        pass


    def set_cooldown(self, email: str, seconds: int = 60):
        """Set a temporary cooldown on an account for transient 429 rate limit without corrupting used_today."""
        with _quota_lock:
            for acc in self.accounts:
                if (acc.get("email") or "").lower() == email.lower():
                    acc["cooldown_until"] = time.time() + seconds
                    logger.warning(f"⏳ Akun {email} dimasukkan ke cooldown sementara {seconds}s (Rate Limit BPS 429).")
                    break

    def get_available_account(
        self,
        account_start: int = 1,
        account_end: Optional[int] = None,
        selected_emails: Optional[List[str]] = None
    ) -> Any:
        """Find an active account within range or selected emails list with remaining quota."""
        with _quota_lock:
            today = datetime.date.today().isoformat()
            if selected_emails:
                email_set = set(e.lower() for e in selected_emails)
                subset = [acc for acc in self.accounts if (acc.get("email") or "").lower() in email_set]
            else:
                start_idx = max(0, account_start - 1)
                end_idx = account_end if (account_end is not None and account_end > 0) else len(self.accounts)
                subset = self.accounts[start_idx:end_idx]

            now = time.time()
            all_quota_full = True
            for acc in subset:
                if acc.get("last_date") != today:
                    acc["last_date"] = today
                    acc["used_today"] = 0
                if acc.get("is_disabled"):
                    continue

                used = acc.get("used_today", 0)
                quota = acc.get("daily_quota", 400)
                if used < quota:
                    # Auto login check if token is missing
                    if not acc.get("token_data") and acc.get("email") and acc.get("password"):
                        try:
                            td = perform_login(acc["email"], acc["password"], exit_on_failure=False)
                            if td and "access_token" in td:
                                acc["token_data"] = td
                        except Exception:
                            pass

                    if not acc.get("token_data"):
                        continue  # Skip accounts without valid login token

                    if acc.get("cooldown_until") and now < acc["cooldown_until"]:
                        all_quota_full = False
                        continue  # Temporarily in 429 cooldown

                    all_quota_full = False
                    return acc

            if all_quota_full:
                return None  # All selected accounts really reached daily quota
            return "IN_COOLDOWN"  # Accounts are still valid, but temporarily cooling down from 429


    def increment_usage(self, email: str):
        """Increment daily usage for account after successful submit."""
        with _quota_lock:
            for acc in self.accounts:
                if acc.get("email") == email:
                    acc["used_today"] = acc.get("used_today", 0) + 1
                    break
            self.save_accounts()

    def sync_usage_from_excel(self, df: pd.DataFrame):
        """Automatically synchronize used_today counts for all accounts based on Excel Master data for today."""
        with _quota_lock:
            today = datetime.date.today().isoformat()
            counts = {}
            if df is not None and "BOT_STATUS" in df.columns and "BOT_PETUGAS" in df.columns:
                success_mask = df["BOT_STATUS"].astype(str).str.upper() == "SUCCESS"
                if "BOT_CATATAN" in df.columns:
                    cat_series = df["BOT_CATATAN"].fillna("").astype(str)
                    success_mask = success_mask & (~cat_series.str.contains("anti-dupe|skip", case=False))
                if success_mask.any():
                    sub_df = df[success_mask]
                    petugas_series = sub_df["BOT_PETUGAS"].fillna("").astype(str).str.strip().str.lower()
                    if "BOT_TIMESTAMP" in sub_df.columns:
                        ts_series = sub_df["BOT_TIMESTAMP"].fillna("").astype(str)
                        date_mask = ts_series.str.startswith(today) | (ts_series == "")
                        petugas_series = petugas_series[date_mask]
                    counts = petugas_series[petugas_series != ""].value_counts().to_dict()

            synced_any = False
            for acc in self.accounts:
                email = str(acc.get("email", "")).strip().lower()
                if acc.get("last_date") != today:
                    acc["last_date"] = today
                    acc["used_today"] = counts.get(email, 0)
                    synced_any = True
                else:
                    actual = counts.get(email, 0)
                    if actual > acc.get("used_today", 0):
                        acc["used_today"] = actual
                        synced_any = True

            if synced_any:
                self.save_accounts()

    def mark_quota_exhausted(self, email: str):
        """Mark account as quota exhausted ONLY if actual usage reached daily quota or confirmed exhausted after repeated 429 errors."""
        with _quota_lock:
            for acc in self.accounts:
                if (acc.get("email") or "").lower() == email.lower():
                    used = acc.get("used_today", 0)
                    quota = acc.get("daily_quota", 400)
                    acc["hits_429"] = acc.get("hits_429", 0) + 1
                    if used >= quota or acc["hits_429"] >= 5:
                        acc["used_today"] = quota
                        logger.warning(f"⛔ Akun {email} ditandai KUOTA HARIAN HABIS ({used}/{quota}) setelah {acc['hits_429']}x limit 429. Beralih ke akun berikutnya...")
                    else:
                        acc["cooldown_until"] = time.time() + 60
                        logger.warning(f"⏳ Akun {email} terkena 429 Rate Limit (Hits: {acc['hits_429']}/5, Terpakai: {used}/{quota}). Cooldown 60s & beralih ke akun lain...")
                    break
            self.save_accounts()


    def mark_subset_exhausted(
        self,
        account_start: int = 1,
        account_end: Optional[int] = None,
        selected_emails: Optional[List[str]] = None
    ):
        """Mark active subset of accounts as quota exhausted for today (used_today = daily_quota) so they cannot be used for today."""
        with _quota_lock:
            today = datetime.date.today().isoformat()
            if selected_emails:
                email_set = set(e.lower() for e in selected_emails)
                subset = [acc for acc in self.accounts if (acc.get("email") or "").lower() in email_set]
            else:
                start_idx = max(0, account_start - 1)
                end_idx = account_end if (account_end is not None and account_end > 0) else len(self.accounts)
                subset = self.accounts[start_idx:end_idx]

            for acc in subset:
                quota = acc.get("daily_quota", 400)
                acc["used_today"] = quota
                acc["last_date"] = today
                logger.warning(f"⛔ Akun {acc.get('email')} ditandai MENCAPAI LIMIT HARIAN ({quota}/{quota}) di users.json & tidak dapat digunakan lagi hari ini.")
            self.save_accounts()



def parse_combined_coord(val: Any) -> Tuple[Optional[float], Optional[float]]:
    """Parse combined coordinate string e.g. '2.1521711111,117.4891911111' or '117.4891, 2.1521' into (Lat, Lon)."""
    if not val or pd.isna(val):
        return None, None
    val_str = str(val).strip().replace(";", ",").replace("\t", ",").replace(" ", ",")
    parts = [p.strip() for p in val_str.split(",") if p.strip()]
    if len(parts) >= 2:
        try:
            v1 = float(parts[0])
            v2 = float(parts[1])
            # Indonesia Latitude is between -12 and 10, Longitude is between 90 and 145.
            if -12.0 <= v1 <= 10.0 and 90.0 <= v2 <= 145.0:
                return v1, v2
            elif -12.0 <= v2 <= 10.0 and 90.0 <= v1 <= 145.0:
                return v2, v1
            else:
                return v1, v2
        except (ValueError, TypeError):
            pass
    return None, None


class ExcelQueueManager:

    """Manages reading and thread-safe writing of the Master Excel file."""

    def __init__(self, excel_path: str):
        self.excel_path = excel_path
        self.df: Optional[pd.DataFrame] = None
        self.idpel_col: Optional[str] = None
        self.lat_col: Optional[str] = None
        self.lon_col: Optional[str] = None
        self.coord_col: Optional[str] = None
        self.keperluan_col: Optional[str] = None


        self._load_excel()
        self._auto_detect_columns()



    def _load_excel(self):
        if not os.path.exists(self.excel_path):
            raise FileNotFoundError(f"File Excel tidak ditemukan: {self.excel_path}")

        if os.path.getsize(self.excel_path) == 0:
            raise ValueError(f"File Excel '{self.excel_path}' berukuran 0 byte (kosong/corrupt). Harap gunakan file Excel yang valid!")

        cache_path = self.excel_path + ".cache.pkl"
        excel_mtime = os.path.getmtime(self.excel_path)
        loaded_from_cache = False

        # Strategy 1: Check if valid pickle cache exists and is reasonably recent (mtime >= excel_mtime - 3600)
        if os.path.exists(cache_path) and os.path.getmtime(cache_path) >= (excel_mtime - 3600):
            try:
                self.df = pd.read_pickle(cache_path)
                loaded_from_cache = True
                logger.info(f"⚡ Loaded Excel cache instantly ({len(self.df)} rows): {cache_path}")
            except Exception as ex_cache:
                logger.warning(f"Could not read pickle cache {cache_path}: {ex_cache}")

        # Strategy 2: Read Excel (.xlsx / calamine / openpyxl) if cache was not loaded
        if not loaded_from_cache:
            excel_err = None
            try:
                sheet_dict = None
                try:
                    sheet_dict = pd.read_excel(self.excel_path, sheet_name=None, engine="calamine")
                except Exception:
                    sheet_dict = pd.read_excel(self.excel_path, sheet_name=None)

                if isinstance(sheet_dict, dict):
                    valid_dfs = []
                    sheet_names = list(sheet_dict.keys())
                    for s_name, s_df in sheet_dict.items():
                        if isinstance(s_df, pd.DataFrame) and not s_df.empty:
                            if "SHEET_NAME" not in s_df.columns:
                                s_df["SHEET_NAME"] = s_name
                            valid_dfs.append(s_df)
                    if valid_dfs:
                        self.df = pd.concat(valid_dfs, ignore_index=True)
                        if len(sheet_names) > 1:
                            logger.info(f"📊 Menemukan {len(sheet_names)} Sheet ({', '.join(sheet_names)}) — Menggabungkan total {len(self.df)} baris data!")
                    else:
                        self.df = pd.DataFrame()
                elif isinstance(sheet_dict, pd.DataFrame):
                    self.df = sheet_dict

                # Re-save fresh cache
                try:
                    self.df.to_pickle(cache_path)
                except Exception:
                    pass
            except Exception as e:
                excel_err = e


            # Strategy 3: Fallback Recovery — if Excel reading failed (e.g. Bad magic number / zip corrupt), try reading pickle cache regardless of timestamp!
            if excel_err:
                if os.path.exists(cache_path):
                    try:
                        self.df = pd.read_pickle(cache_path)
                        logger.info(f"🛡️ [RECOVERY SUCCESS] File Excel .xlsx bermasalah ({excel_err}), tetapi berhasil dipulihkan secara otomatis dari Cache Pickle ({len(self.df)} baris)!")
                    except Exception as ex_pickle_fallback:
                        raise ValueError(f"Gagal membaca format file Excel '{self.excel_path}': {excel_err} (Cache pickle juga gagal: {ex_pickle_fallback})")
                else:
                    raise ValueError(f"Gagal membaca format file Excel '{self.excel_path}': {excel_err}")


    def _auto_detect_columns(self):
        if self.df is None:
            return

        cols = list(self.df.columns)

        # Detect columns
        for c in cols:
            cu = str(c).upper().strip()
            if not self.idpel_col and any(k in cu for k in ["IDPEL", "NO_PELANGGAN", "ID_PEL", "ID PELANGGAN"]):
                self.idpel_col = c
            if not self.lat_col and any(k in cu for k in ["LATITUDE", "KOORDINAT_Y", "KOORDINAT Y", "KOORD_Y", "KOORD Y", "LAT_Y", "LATITUDE_Y", "LAT", "KOORDINAT_LAT", "Y_KOORDINAT"]):
                self.lat_col = c
            if not self.lon_col and any(k in cu for k in ["LONGITUDE", "KOORDINAT_X", "KOORDINAT X", "KOORD_X", "KOORD X", "LON_X", "LONGITUDE_X", "LON", "LONG", "KOORDINAT_LON", "X_KOORDINAT"]):
                self.lon_col = c
            if not self.coord_col and any(k in cu for k in ["TITIK KOORDINAT", "TITIK_KOORDINAT", "KOORDINAT", "LAT_LON", "LAT_LONG", "COORDINATE", "COORDINATES", "LATITUDE_LONGITUDE"]):
                self.coord_col = c
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

        self._apply_checkpoint_if_exists()

        self._unsaved_count = 0
        self._last_save_time = time.time()
        logger.info(f"Excel loaded: {len(self.df)} rows | IDPel Col: '{self.idpel_col}' | Lat/Lon: '{self.lat_col}'/'{self.lon_col}' | Combined Coord: '{self.coord_col}' | Keperluan: '{self.keperluan_col}'")

    def _apply_checkpoint_if_exists(self):
        """Apply any un-flushed progress from checkpoint CSV files on load (matching by IDPel and index)."""
        ckpt_paths = [self.excel_path + ".checkpoint.csv"]

        # Check for related checkpoint files in same directory (e.g. BOT DTSEN TJR.xlsx.checkpoint.csv)
        dir_name = os.path.dirname(self.excel_path) or "."
        if os.path.exists(dir_name):
            try:
                for f in os.listdir(dir_name):
                    if f.endswith(".checkpoint.csv"):
                        p = os.path.join(dir_name, f)
                        if p not in ckpt_paths:
                            ckpt_paths.append(p)
            except Exception:
                pass

        # Build IDPel -> Row Index mapping for self.df
        idpel_to_idx = {}
        if self.idpel_col and self.idpel_col in self.df.columns:
            for idx, val in enumerate(self.df[self.idpel_col]):
                if pd.notna(val):
                    clean_id = str(val).strip()
                    if clean_id:
                        idpel_to_idx[clean_id] = idx

        applied_cnt = 0
        max_len = len(self.df)

        for cp in ckpt_paths:
            if not os.path.exists(cp):
                continue
            try:
                ckpt_df = pd.read_csv(cp)
                if ckpt_df.empty:
                    continue

                for row in ckpt_df.itertuples(index=False):
                    try:
                        target_i = None

                        # 1. Match by IDPel if available (most reliable across files/sheets)
                        idpel_val = str(getattr(row, "idpel", "")).strip()
                        if idpel_val and idpel_val in idpel_to_idx:
                            target_i = idpel_to_idx[idpel_val]
                        # 2. Match by direct index if cp is the exact same file checkpoint
                        elif cp == (self.excel_path + ".checkpoint.csv"):
                            idx_val = getattr(row, "index", None)
                            if idx_val is not None and not pd.isna(idx_val):
                                i = int(idx_val)
                                if 0 <= i < max_len:
                                    target_i = i

                        if target_i is not None:
                            st = str(getattr(row, "status", ""))
                            curr_st = str(self.df.at[target_i, "BOT_STATUS"]).upper()
                            if curr_st not in ("SUCCESS", "NON_RESIDENTIAL", "INVALID_IDPEL") or st in ("SUCCESS", "NON_RESIDENTIAL"):
                                self.df.at[target_i, "BOT_STATUS"] = st
                                self.df.at[target_i, "BOT_RETRY"] = int(getattr(row, "retry_count", 0))
                                self.df.at[target_i, "BOT_PETUGAS"] = str(getattr(row, "user_email", ""))
                                self.df.at[target_i, "BOT_CATATAN"] = str(getattr(row, "catatan", ""))
                                self.df.at[target_i, "BOT_TIMESTAMP"] = str(getattr(row, "timestamp", ""))
                                applied_cnt += 1
                    except Exception:
                        pass
            except Exception as e:
                logger.warning(f"Failed loading checkpoint file {cp}: {e}")

        if applied_cnt > 0:
            logger.info(f"🔄 Checkpoint recovery: Applied {applied_cnt} recent updates from checkpoint log.")


    def update_row(self, index: int, status: str, retry_count: int, user_email: str, catatan: str, force_save: bool = False):
        """Thread-safe update of a single row in memory with instant non-blocking checkpointing."""
        with _excel_lock:
            self.df.at[index, "BOT_STATUS"] = status
            self.df.at[index, "BOT_RETRY"] = retry_count
            self.df.at[index, "BOT_PETUGAS"] = user_email
            self.df.at[index, "BOT_TIMESTAMP"] = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            self.df.at[index, "BOT_CATATAN"] = catatan
            self._unsaved_count += 1

            # Instant append to lightweight CSV checkpoint (< 1ms, zero blocking)
            self._write_checkpoint(index, status, retry_count, user_email, catatan)

            now = time.time()
            # Save heavy Excel file in background only every 500 items or every 10 minutes (600s)
            if force_save or self._unsaved_count >= 500 or (now - self._last_save_time) > 600:
                self._save_excel_async()

    def _write_checkpoint(self, index: int, status: str, retry_count: int, user_email: str, catatan: str):
        """Write an instant lightweight append-only checkpoint log for instant zero-data-loss persistence."""
        try:
            ckpt_path = self.excel_path + ".checkpoint.csv"
            file_exists = os.path.exists(ckpt_path)
            with open(ckpt_path, "a", encoding="utf-8") as f:
                if not file_exists:
                    f.write("index,idpel,status,retry_count,user_email,catatan,timestamp\n")
                idpel = str(self.df.at[index, self.idpel_col]) if self.idpel_col in self.df.columns else ""
                clean_catatan = str(catatan).replace("\n", " ").replace(",", ";")
                ts = self.df.at[index, "BOT_TIMESTAMP"]
                f.write(f"{index},{idpel},{status},{retry_count},{user_email},{clean_catatan},{ts}\n")
        except Exception:
            pass

    def flush(self):
        """Force save remaining in-memory updates to Excel disk synchronously on exit/finish."""
        logger.info("💾 Saving final progress to Excel Master...")
        with _excel_lock:
            self._save_excel()

    def _save_excel_async(self):
        """Trigger background thread for saving heavy Excel file without blocking worker threads."""
        if getattr(self, "_is_saving", False):
            return
        self._is_saving = True
        t = threading.Thread(target=self._async_save_worker, daemon=True)
        t.start()

    def _async_save_worker(self):
        """Worker thread for background Excel writing."""
        try:
            with _excel_lock:
                df_copy = self.df.copy()
            
            # Save pickle cache first so state is preserved instantly
            cache_path = self.excel_path + ".cache.pkl"
            try:
                df_copy.to_pickle(cache_path)
            except Exception:
                pass

            tmp_path = self.excel_path + ".tmp.xlsx"
            df_copy.to_excel(tmp_path, engine="openpyxl", index=False)
            if os.path.exists(tmp_path) and os.path.getsize(tmp_path) > 0:
                os.replace(tmp_path, self.excel_path)
                with _excel_lock:
                    self._unsaved_count = 0
                    self._last_save_time = time.time()
        except Exception as e:
            logger.warning(f"Background Excel save error: {e}")
            if os.path.exists(tmp_path):
                try:
                    os.remove(tmp_path)
                except Exception:
                    pass
        finally:
            self._is_saving = False

    def _save_excel(self):
        """Save DataFrame safely using atomic temporary file write to prevent corruption on Ctrl+C."""
        cache_path = self.excel_path + ".cache.pkl"
        try:
            self.df.to_pickle(cache_path)
        except Exception:
            pass

        tmp_path = self.excel_path + ".tmp.xlsx"
        try:
            self.df.to_excel(tmp_path, engine="openpyxl", index=False)
            if os.path.exists(tmp_path) and os.path.getsize(tmp_path) > 0:
                os.replace(tmp_path, self.excel_path)
                self._unsaved_count = 0
                self._last_save_time = time.time()
        except Exception as e:
            logger.warning(f"Error saving Excel file: {e}")
            if os.path.exists(tmp_path):
                try:
                    os.remove(tmp_path)
                except Exception:
                    pass


    def get_pending_indices(self, start_row: Optional[int] = None, start_idpel: Optional[str] = None) -> List[int]:
        """Get list of row indices that need processing (instant <1ms)."""
        with _excel_lock:
            target_start_idx = 0
            if start_idpel:
                idpel_clean = str(start_idpel).strip()
                matches = self.df[self.df[self.idpel_col].astype(str).str.strip() == idpel_clean]
                if not matches.empty:
                    target_start_idx = matches.index[0]
                    logger.info(f"📍 Menemukan Start IDPel '{idpel_clean}' pada baris Excel #{target_start_idx + 2}")
                else:
                    logger.warning(f"⚠️ Start IDPel '{idpel_clean}' tidak ditemukan di Excel. Mulai dari awal.")
            elif start_row is not None and start_row > 0:
                target_start_idx = max(0, start_row - 2)
                logger.info(f"📍 Menyetel baris awal eksekusi dari baris Excel #{start_row} (DF index {target_start_idx})")

            status_series = self.df["BOT_STATUS"].astype(str).str.upper()
            terminal_statuses = ["SUCCESS", "NON_RESIDENTIAL", "INVALID_IDPEL"]
            mask = ~status_series.isin(terminal_statuses)
            if target_start_idx > 0:
                mask.iloc[:target_start_idx] = False

            return self.df.index[mask].tolist()

    def get_item(self, idx: int) -> dict:
        """Fetch item dict lazily on demand for a single row index."""
        with _excel_lock:
            row = self.df.iloc[idx]
            lat = row.get(self.lat_col) if self.lat_col else None
            lon = row.get(self.lon_col) if self.lon_col else None

            # Fallback to combined coordinate column if lat/lon empty or single combined string
            coord_raw = row.get(self.coord_col) if self.coord_col else (lat if (isinstance(lat, str) and ("," in lat or " " in lat)) else None)
            if coord_raw and pd.notna(coord_raw) and str(coord_raw).strip():
                parsed_lat, parsed_lon = parse_combined_coord(coord_raw)
                if parsed_lat is not None and parsed_lon is not None:
                    lat, lon = parsed_lat, parsed_lon

            # Ensure correct Latitude (-15 to 15) and Longitude (90 to 145) in Indonesia
            if lat is not None and lon is not None:
                try:
                    lat_f, lon_f = float(lat), float(lon)
                    if (lat_f > 50 or lat_f < -50) and (-15 <= lon_f <= 15):
                        lat, lon = lon_f, lat_f
                except (ValueError, TypeError):
                    pass

            return {

                "idpel": str(row.get(self.idpel_col) or "").strip(),
                "lat": lat,
                "lon": lon,
                "keperluan": str(row.get(self.keperluan_col) or "").strip() if self.keperluan_col else "",
                "retry_count": int(row.get("BOT_RETRY", 0)),
            }



class AutonomousRunner:
    """Main orchestrator running 20 parallel workers."""

    def __init__(
        self,
        excel_path: str,
        users_file: str,
        max_workers: int = 20,
        mode_args: dict = None,
        account_start: int = 1,
        account_end: Optional[int] = None,
        selected_emails: Optional[List[str]] = None
    ):
        self.excel_mgr = ExcelQueueManager(excel_path)
        self.account_mgr = AccountManager(users_file)
        self.account_mgr.sync_usage_from_excel(self.excel_mgr.df)
        self.max_workers = max_workers
        self.mode_args = mode_args or {}
        self.account_start = account_start
        self.account_end = account_end
        self.selected_emails = selected_emails

        # Cache of initialized survey caches per user email
        self.user_caches: Dict[str, dict] = {}
        self._cache_lock = threading.Lock()
        self.stop_event = threading.Event()
        self.processed_indices = set()
        self._processed_lock = threading.Lock()

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

    def process_item(self, idx: int, item: Optional[dict] = None):
        """Worker task for processing a single IDPel item."""
        if self.stop_event.is_set():
            return

        if item is None:
            item = self.excel_mgr.get_item(idx)

        idpel = item["idpel"]
        retry_count = item["retry_count"]

        # Validate IDPel
        if not idpel or len(idpel) < 10 or not idpel.isdigit():
            self.excel_mgr.update_row(idx, "INVALID_IDPEL", retry_count, "", "IDPel tidak valid (bukan angka 12 digit)")
            return

        # Acquire an available user account within specified range or selected email list
        acc = None
        for _ in range(30):
            if self.stop_event.is_set():
                return
            res = self.account_mgr.get_available_account(
                account_start=self.account_start,
                account_end=self.account_end,
                selected_emails=self.selected_emails
            )
            if isinstance(res, dict):
                acc = res
                with self._processed_lock:
                    self._cooldown_log_count = 0
                break
            elif res == "IN_COOLDOWN":
                with self._processed_lock:
                    now = time.time()
                    if now - getattr(self, "_last_cooldown_log", 0) > 15.0:
                        cnt = getattr(self, "_cooldown_log_count", 0) + 1
                        self._cooldown_log_count = cnt
                        self._last_cooldown_log = now
                        logger.info(f"⏳ Akun BPS sedang dalam cooldown 429 rate-limit sementara. Menunggu akun siap...")
                time.sleep(3.0)
            else:
                break


        if not acc:
            if not self.stop_event.is_set():
                self.stop_event.set()
                logger.warning("⛔ SEMUA AKUN BPS YANG DIGUNAKAN TELAH MENCAPAI LIMIT KUOTA HARIAN. Menghentikan eksekusi...")
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

        max_pln_attempts = 3
        for attempt in range(1, max_pln_attempts + 1):
            if attempt > 1:
                logger.info(f"🔄 [Worker] Re-attempting {idpel} via {email} (Attempt {attempt}/{max_pln_attempts})...")
            else:
                logger.info(f"🚀 [Worker] Processing {idpel} via {email} (Attempt 1/{max_pln_attempts})...")

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

            with self._processed_lock:
                self.processed_indices.add(idx)
                self.completed_cnt = getattr(self, "completed_cnt", 0) + 1
                cnt = self.completed_cnt
                tot = getattr(self, "total_pending_cnt", 1)
                now = time.time()
                st = getattr(self, "start_time", now)
                if (now - getattr(self, "_last_progress_log", 0) >= 3.5) or cnt == tot:
                    self._last_progress_log = now
                    elapsed = max(0.1, now - st)
                    speed = cnt / elapsed
                    remaining = max(0, tot - cnt)
                    eta_sec = int(remaining / speed) if speed > 0 else 0
                    m, s = divmod(eta_sec, 60)
                    h, m = divmod(m, 60)
                    eta_str = f"{h}j {m}m {s}d" if h > 0 else (f"{m}m {s}d" if m > 0 else f"{s}d")
                    pct = (cnt / tot * 100) if tot > 0 else 100.0
                    logger.info(f"⚡ [PROGRESS] {cnt}/{tot} ({pct:.1f}%) | Kecepatan: {speed:.1f} data/s | ETA Sisa Waktu: {eta_str}")

            if ok:

                logger.info(f"✅ {idpel} SUCCESS via {email}: {msg}")
                self.excel_mgr.update_row(idx, "SUCCESS", retry_count + attempt - 1, email, msg)
                self.account_mgr.increment_usage(email)
                break
            else:
                msg_lower = msg.lower()
                # Check if Non-Rumah Tangga tarif (S1T, B-1, I-1, etc.)
                is_non_residential = "non-rumah tangga" in msg_lower or "hanya tarif tipe r" in msg_lower
                # Check if BPS account limit / quota reached (429 / limit / quota error)
                is_rate_limited = any(t in msg_lower for t in ["429", "quota", "limit", "too many requests"])
                # Check if account has no assignment / sample in this region
                is_no_assignment = any(t in msg_lower for t in ["belum memiliki sampel", "tidak memiliki assignment", "web monitoring bps"])
                # Check if error is 'Region PLN tak lengkap (kd_kel kosong)' -> DO NOT RETRY
                is_region_incomplete = any(err in msg_lower for err in ["region pln tak lengkap", "kd_kel kosong"])
                # Check if error is 'Data PLN tidak ditemukan / server PLN tak terjangkau' -> RETRY UP TO 3X
                is_pln_not_found = not is_region_incomplete and any(err in msg_lower for err in ["pln tidak ditemukan", "terjangkau", "tidak terjangkau", "timeout", "500", "502", "504", "connection"])

                if is_non_residential:
                    logger.warning(f"🚫 {idpel} Tarif Non-Rumah Tangga via {email}: {msg}")
                    self.excel_mgr.update_row(idx, "NON_RESIDENTIAL", retry_count, email, msg)
                    break
                elif is_rate_limited:
                    logger.warning(f"⏳ Akun {email} terkena 429 Rate Limit. Dimasukkan ke cooldown 60s...")
                    self.account_mgr.mark_quota_exhausted(email)
                    self.excel_mgr.update_row(idx, "RETRYING", retry_count, email, f"Cooldown 429 BPS: {msg}")
                    time.sleep(2.0)
                    break
                elif is_no_assignment:
                    logger.error(f"⛔ Akun {email} tidak memiliki tugas/sampel BPS di wilayah ini! Otomatis beralih ke akun berikutnya...")
                    self.account_mgr.mark_quota_exhausted(email)
                    self.excel_mgr.update_row(idx, "RETRYING", retry_count, email, f"Beralih Akun ({email} tidak ada tugas di wilayah ini)")
                    break
                elif is_region_incomplete:
                    # ABAIKAN RETRY: Langsung FAILED_PLN & move ke task berikutnya
                    logger.error(f"❌ {idpel} FAILED_PLN via {email}: {msg} (Dilewati tanpa retry — kd_kel kosong)")
                    self.excel_mgr.update_row(idx, "FAILED_PLN", retry_count, email, msg)
                    break
                elif is_pln_not_found:
                    if attempt < max_pln_attempts:
                        logger.warning(f"⚠️ {idpel} Gangguan Sementara PLN (Coba {attempt}/{max_pln_attempts}): {msg} — mencoba ulang dalam 1 detik...")
                        time.sleep(1.0)
                        continue
                    else:
                        logger.error(f"❌ {idpel} FAILED_PLN via {email}: {msg} (Gagal setelah {max_pln_attempts}x percobaan — dilewati)")
                        self.excel_mgr.update_row(idx, "FAILED_PLN", retry_count + attempt - 1, email, f"Gagal {max_pln_attempts}x retry: {msg}")
                        break
                else:
                    status_code = "FAILED_PLN" if "PLN" in msg else "FAILED"
                    logger.error(f"❌ {idpel} {status_code} via {email}: {msg}")
                    self.excel_mgr.update_row(idx, status_code, retry_count, email, msg)
                    break

    def prompt_interactive_start(self) -> Tuple[Optional[int], Optional[str]]:
        """Interactive startup prompt to let user select start row or IDPel."""
        df = self.excel_mgr.df
        total_rows = len(df)
        terminal_statuses = ["SUCCESS", "NON_RESIDENTIAL", "INVALID_IDPEL"]
        completed_cnt = sum(1 for status in df["BOT_STATUS"] if str(status).upper() in terminal_statuses)
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
            choice = input("Masukkan pilihan [1-3] atau ketik langsung No. Baris / IDPel: ").strip()
            if choice == "2":
                r_str = input(f"Masukkan nomor baris Excel (2 - {total_rows + 1}): ").strip()
                if r_str.isdigit():
                    return int(r_str), None
            elif choice == "3":
                id_str = input("Masukkan IDPel awal yang ingin dikerjakan: ").strip()
                if id_str:
                    return None, id_str
            elif choice.isdigit():
                if len(choice) >= 10:
                    # Auto-detect 10+ digit number as IDPel
                    print(f"📍 Otomatis mendeteksi IDPel awal: '{choice}'")
                    return None, choice
                elif int(choice) > 1:
                    # Auto-detect number as Excel row number
                    row_num = int(choice)
                    print(f"📍 Otomatis mendeteksi Nomor Baris Excel awal: #{row_num}")
                    return row_num, None
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
        pending_indices = self.excel_mgr.get_pending_indices(start_row=start_row, start_idpel=start_idpel)
        logger.info(f"📋 Total IDPel pending yang akan diproses: {len(pending_indices)}")

        if not pending_indices:
            logger.info("✅ Semua IDPel di Excel sudah diproses (STATUS = SUCCESS / NON_RESIDENTIAL / INVALID_IDPEL / FAILED_PLN / FAILED).")
            return

        try:
            from concurrent.futures import wait, FIRST_COMPLETED
            import concurrent.futures.thread

            class DaemonThreadPoolExecutor(ThreadPoolExecutor):
                def _adjust_thread_count(self):
                    orig_thread = threading.Thread
                    def daemon_thread(*args, **kwargs):
                        kwargs['daemon'] = True
                        return orig_thread(*args, **kwargs)
                    try:
                        threading.Thread = daemon_thread
                        super()._adjust_thread_count()
                    finally:
                        threading.Thread = orig_thread

            executor = DaemonThreadPoolExecutor(max_workers=self.max_workers)
            active_futures = set()
            pending_iter = iter(pending_indices)
            self.total_pending_cnt = len(pending_indices)
            self.completed_cnt = 0
            self.start_time = time.time()
            self._last_progress_log = 0.0

            # Prime pool with up to max_workers active tasks (instant dispatch)
            while len(active_futures) < self.max_workers and not self.stop_event.is_set():
                try:
                    idx = next(pending_iter)
                    active_futures.add(executor.submit(self.process_item, idx))
                except StopIteration:
                    break

            # Continuous bounded worker pipeline
            while active_futures and not self.stop_event.is_set():
                done, active_futures = wait(active_futures, return_when=FIRST_COMPLETED)
                for f in done:
                    try:
                        f.result()
                    except Exception as e:
                        logger.error(f"Worker exception: {e}")

                # Replenish finished worker slots
                while len(active_futures) < self.max_workers and not self.stop_event.is_set():
                    try:
                        idx = next(pending_iter)
                        active_futures.add(executor.submit(self.process_item, idx))
                    except StopIteration:
                        break

        except KeyboardInterrupt:
            logger.warning("\n⚠️ Proses dihentikan oleh pengguna (Ctrl+C). Menyimpan progress terakhir...")
            self.stop_event.set()
            for f in active_futures:
                f.cancel()
            executor.shutdown(wait=False, cancel_futures=True)
            try:
                concurrent.futures.thread._threads_queues.clear()
            except Exception:
                pass
        finally:
            self.excel_mgr.flush()
            if not self.stop_event.is_set():
                executor.shutdown(wait=True)

        # Generate CSV report containing ONLY IDPels processed during this session
        try:
            import csv
            report_dir = os.path.join("Folder-Runner", "report")
            os.makedirs(report_dir, exist_ok=True)
            report_filename = f"batch_report_{datetime.datetime.now().strftime('%Y%m%d_%H%M%S')}.csv"
            report_file = os.path.join(report_dir, report_filename)
            report_rows = []
            
            with _excel_lock:
                target_indices = sorted(self.processed_indices) if getattr(self, "processed_indices", None) else []
                if target_indices:
                    for idx in target_indices:
                        if idx < len(self.excel_mgr.df):
                            row = self.excel_mgr.df.iloc[idx]
                            report_rows.append({
                                "val": str(row.get(self.excel_mgr.idpel_col) or ""),
                                "status": str(row.get("BOT_STATUS") or ""),
                                "message": str(row.get("BOT_CATATAN") or ""),
                                "petugas": str(row.get("BOT_PETUGAS") or ""),
                                "timestamp": str(row.get("BOT_TIMESTAMP") or "")
                            })
                else:
                    # Fallback: include all non-PENDING rows if processed_indices was empty
                    for idx, row in self.excel_mgr.df.iterrows():
                        st = str(row.get("BOT_STATUS") or "").upper()
                        if st and st != "PENDING":
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

            logger.info(f"📄 Report CSV berhasil dibuat ({len(report_rows)} baris): {report_file}")

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


def scan_excel_files(target_dir: str = "Folder-Runner") -> List[str]:
    """Scan target directory (Folder-Runner) and fallback to '.' for valid Excel files (> 1KB)."""
    files = []
    dirs_to_check = [target_dir, "."] if os.path.exists(target_dir) else ["."]
    seen = set()
    for d in dirs_to_check:
        try:
            for f in os.listdir(d):
                if f.endswith((".xlsx", ".xls")) and not f.startswith("~$") and not f.startswith("batch_report_"):
                    filepath = os.path.join(d, f) if d != "." else f
                    if filepath not in seen and os.path.exists(filepath) and os.path.getsize(filepath) > 1024:
                        seen.add(filepath)
                        files.append(filepath)
        except Exception:
            pass
    files.sort()
    return files


def print_grouped_accounts(accounts: List[Dict[str, Any]]) -> Dict[str, List[int]]:
    """Print registered BPS accounts in users.json grouped by region/group sections.
    Returns a dictionary mapping uppercase group names to their list of 1-based account indices.
    """
    groups_map = {}
    for idx, acc in enumerate(accounts, 1):
        grp = str(acc.get("group") or acc.get("region") or "PALUKOTA").strip().upper()
        if grp not in groups_map:
            groups_map[grp] = []
        groups_map[grp].append((idx, acc))

    group_indices = {}
    group_emojis = {

        "PALUKOTA": "🏙️ ",
        "MALINAU": "🏔️ ",
        "TANJUNG REDEB": "📁 ",
        "DONGGALA": "⛵ ",
        "BUNGKU": "🌴 ",
        "SAMARINDA": "🌏 ",
        "BALIKPAPAN": "🌊 ",
        "BONTAG": "🏗️ ",
        "BONTANG": "🏗️ ",
        "BERAU": "🌲 ",
    }

    for grp_name, items in groups_map.items():
        emoji = group_emojis.get(grp_name, "📁 ")
        indices = [it[0] for it in items]
        group_indices[grp_name] = indices
        if grp_name == "TANJUNG REDEB":
            group_indices["TJR"] = indices
            group_indices["TANJUNG"] = indices
            group_indices["TANJUNG_REDEB"] = indices
        elif grp_name == "DONGGALA":
            group_indices["DGL"] = indices
        elif grp_name == "BUNGKU":
            group_indices["BGK"] = indices


        min_idx, max_idx = min(indices), max(indices)

        print("\n" + "=" * 65)
        print(f"{emoji} AKUN WILAYAH / ULP: {grp_name} (Urutan #{min_idx} - #{max_idx} · {len(items)} Akun)")
        print("=" * 65)
        for idx, acc in items:
            if acc.get("is_disabled"):
                status_str = "❌ Disabled"
            elif acc.get("used_today", 0) >= acc.get("daily_quota", 400):
                status_str = "❌ Limit"
            else:
                status_str = "✅ Active"
            email = acc.get("email") or "Unknown"
            used = acc.get("used_today", 0)
            quota = acc.get("daily_quota", 400)
            print(f"  {idx:2d}. {email:<38} [{status_str}] (Terpakai: {used}/{quota})")

    return group_indices


def manage_accounts_interactive(users_file: str = "users.json"):
    """Interactive menu for managing BPS accounts in users.json."""
    mgr = AccountManager(users_file)
    while True:
        print("\n" + "=" * 60)
        print("👤 KELOLA AKUN BPS (users.json)")
        print("=" * 60)
        if not mgr.accounts:
            print("⚠️ Belum ada akun di users.json (menggunakan fallback fasih_token.json jika ada).")
        else:
            print(f"📋 Total {len(mgr.accounts)} akun terdaftar:")
            print_grouped_accounts(mgr.accounts)

        print("-" * 60)

        print("  [1] ➕ Tambah Akun BPS Baru (Email & Password)")
        print("  [2] 🔑 Test Login & Validasi Tugas Semua Akun (Auto-Disable Akun Kosong)")
        print("  [3] 🗑️ Hapus Akun BPS")
        print("  [0] 🔙 Kembali ke Menu Utama")
        print("=" * 60)

        choice = input("Pilih menu (0-3): ").strip()
        if choice == "0":
            break
        elif choice == "1":
            print("\n➕ Tambah Akun BPS Baru:")
            email = input("  Email BPS SSO   : ").strip()
            if not email:
                print("❌ Email tidak boleh kosong!")
                continue
            import getpass
            try:
                password = getpass.getpass("  Password BPS SSO: ").strip()
            except Exception:
                password = input("  Password BPS SSO: ").strip()

            if not password:
                print("❌ Password tidak boleh kosong!")
                continue

            print(f"\n🔄 Menghubungi SSO BPS untuk memverifikasi akun {email}...")
            try:
                td = perform_login(email, password, exit_on_failure=False)
                if td and "access_token" in td:
                    print(f"✅ Login SSO BERHASIL untuk {email}!")
                    existing = next((a for a in mgr.accounts if a.get("email") == email), None)
                    if existing:
                        existing["password"] = password
                        existing["token_data"] = td
                        existing["is_disabled"] = False
                        print(f"ℹ️ Password & Token untuk {email} telah diperbarui (akun diaktifkan).")
                    else:
                        today = datetime.date.today().isoformat()
                        mgr.accounts.append({
                            "email": email,
                            "password": password,
                            "token_data": td,
                            "daily_quota": 400,
                            "used_today": 0,
                            "last_date": today,
                            "is_disabled": False
                        })
                        print(f"✅ Akun {email} berhasil ditambahkan ke {users_file}!")
                    mgr.save_accounts()
                else:
                    print(f"❌ Login SSO gagal untuk {email}. Periksa kembali email & password.")
            except Exception as e:
                print(f"❌ Error saat login SSO: {e}")

        elif choice == "2":
            print("\n🔑 Memeriksa, Test Login, & Validasi Tugas Semua Akun:")
            if not mgr.accounts:
                print("⚠️ Tidak ada akun di users.json untuk di-test.")
                continue
            for idx, acc in enumerate(mgr.accounts, 1):
                email = acc.get("email")
                pwd = acc.get("password")
                if email and pwd:
                    print(f"   [{idx}/{len(mgr.accounts)}] Memeriksa {email}...")
                    try:
                        td = perform_login(email, pwd, exit_on_failure=False)
                        if td and "access_token" in td:
                            acc["token_data"] = td
                            headers = get_headers(td)
                            surveys = fetch_surveys(headers)
                            active_surveys_cnt = 0
                            total_assigns = 0
                            for s in surveys:
                                active_p = next((p for p in s.get("listPeriode", []) if p.get("isActive")), None)
                                if active_p:
                                    active_surveys_cnt += 1
                                    assigns = fetch_all_assignments(headers, active_p["id"])
                                    total_assigns += len(assigns)
                            if active_surveys_cnt == 0 or total_assigns == 0:
                                acc["is_disabled"] = True
                                print(f"       ❌ DISABLED (0 tugas survey aktif)")
                            else:
                                acc["is_disabled"] = False
                                print(f"       ✅ ACTIVE ({active_surveys_cnt} survey aktif, {total_assigns} tugas)")
                        else:
                            acc["is_disabled"] = True
                            print(f"       ❌ DISABLED (Login SSO Gagal)")
                    except Exception as e:
                        acc["is_disabled"] = True
                        print(f"       ❌ ERROR: {e} -> Akun dinonaktifkan sementara")
                else:
                    print(f"   [{idx}/{len(mgr.accounts)}] {email} (tanpa password tersimpan)")
            mgr.save_accounts()
            print("✅ Verifikasi dan pemfilteran selesai. Hasil disimpan ke users.json.")

        elif choice == "3":
            if not mgr.accounts:
                print("❌ Tidak ada akun untuk dihapus.")
                continue
            idx_str = input(f"Masukkan nomor akun yang ingin dihapus (1-{len(mgr.accounts)}): ").strip()
            if idx_str.isdigit():
                idx_val = int(idx_str)
                if 1 <= idx_val <= len(mgr.accounts):
                    removed = mgr.accounts.pop(idx_val - 1)
                    mgr.save_accounts()
                    print(f"✅ Akun {removed.get('email')} berhasil dihapus.")
                else:
                    print("❌ Nomor akun tidak valid.")


def select_excel_interactive() -> Optional[str]:
    """Interactively scan and list Excel files in Folder-Runner/ and current directory."""
    excel_files = scan_excel_files("Folder-Runner")
    print("\n" + "=" * 60)
    print("📂 PILIH FILE MASTER EXCEL")
    print("=" * 60)
    if excel_files:
        print("Ditemukan file Excel di folder ini (diutamakan dari Folder-Runner/):")
        for idx, f in enumerate(excel_files, 1):
            size_mb = os.path.getsize(f) / (1024 * 1024)
            folder_tag = "📁 Folder-Runner" if f.startswith("Folder-Runner") else "📄 Root"
            print(f"   [{idx}] {f} ({size_mb:.2f} MB) [{folder_tag}]")
        print("   [0] Input path file manual")
        print("-" * 60)
        choice = input(f"Pilih file (1-{len(excel_files)} / 0): ").strip()
        if choice.isdigit():
            c_val = int(choice)
            if 1 <= c_val <= len(excel_files):
                return excel_files[c_val - 1]
            elif c_val == 0:
                manual_path = input("Masukkan path file Excel: ").strip().strip('"').strip("'")
                if os.path.exists(manual_path):
                    return manual_path
                else:
                    print(f"❌ File '{manual_path}' tidak ditemukan.")
                    return None
    else:
        print("⚠️ Tidak ditemukan file Excel (.xlsx/.xls) di folder ini.")
        manual_path = input("Masukkan path file Excel: ").strip().strip('"').strip("'")
        if os.path.exists(manual_path):
            return manual_path
        else:
            print(f"❌ File '{manual_path}' tidak ditemukan.")
            return None
    return None


def interactive_main_menu(args):
    """Interactive CLI Main Menu for fasih-auto-runner."""
    while True:
        print("\n" + "=" * 60)
        print("🤖 FASIH AUTONOMOUS RUNNER — INTERACTIVE MENU")
        print("=" * 60)
        print("  [1] 🚀 Jalankan Auto Runner (Pilih File Excel & Akun BPS)")
        print("  [2] 👤 Kelola Akun BPS (Tambah / Lihat / Hapus Akun di users.json)")
        print("  [3] ⚙️ Pengaturan Mode Submit (Reject / Open / Reopen / Skip Cek IDPel)")
        print("  [0] ❌ Keluar")
        print("=" * 60)

        c = input("Pilih menu (0-3): ").strip()
        if c == "0":
            print("👋 Bye!")
            sys.exit(0)
        elif c == "2":
            manage_accounts_interactive(args.users_file)
        elif c == "3":
            while True:
                print("\n" + "-" * 50)
                print("⚙️ PENGATURAN MODE SUBMIT:")
                print(f"  1. Skip Cek IDPel (--no-cek)          : {'✅ ON' if args.skip_cek_idpln else '❌ OFF'}")
                print(f"  2. Resubmit Reject (--resubmit-reject) : {'✅ ON' if args.resubmit_reject else '❌ OFF'}")
                print(f"  3. Resubmit Open (--resubmit-open)     : {'✅ ON' if args.resubmit_open else '❌ OFF'}")
                print(f"  4. Resubmit Reopen (--resubmit-reopen) : {'✅ ON' if args.resubmit_reopen else '❌ OFF'}")
                print("  0. 🔙 Kembali ke Menu Utama")
                print("-" * 50)
                sub_c = input("Pilih nomor mode untuk toggle ON/OFF (0 untuk kembali): ").strip()
                if sub_c == "0":
                    break
                elif sub_c == "1":
                    args.skip_cek_idpln = not args.skip_cek_idpln
                elif sub_c == "2":
                    args.resubmit_reject = not args.resubmit_reject
                elif sub_c == "3":
                    args.resubmit_open = not args.resubmit_open
                elif sub_c == "4":
                    args.resubmit_reopen = not args.resubmit_reopen
        elif c == "1":
            excel_path = select_excel_interactive()
            if not excel_path:
                continue

            mgr = AccountManager(args.users_file)
            try:
                excel_mgr_temp = ExcelQueueManager(excel_path)
                mgr.sync_usage_from_excel(excel_mgr_temp.df)
            except Exception:
                pass
            total_accs = len(mgr.accounts)

            group_indices = print_grouped_accounts(mgr.accounts)

            print("-" * 65)
            print("📌 PILIHAN AKUN BPS YANG AKAN DIGUNAKAN:")
            print("  • Ketik Nama Wilayah (misal: MALINAU atau PALUKOTA)")
            print("  • ATAU Masukkan nomor urutan (misal: 45 atau rentang 45-60)")
            print("  • ATAU Paste/Ketik Email BPS (satu atau banyak per baris)")
            print(f"  • ATAU Tekan ENTER pada baris kosong untuk menggunakan SEMUA AKUN (1 - {total_accs})")
            print("-" * 65)
            print("Pilihan Akun BPS (Ketik Wilayah / Paste Email / Nomor / ENTER):")

            acc_lines = []
            while True:
                try:
                    line = input().strip()
                    if not line:
                        break
                    acc_lines.append(line)
                    first_item = acc_lines[0].strip()
                    if len(acc_lines) == 1 and ("@" not in first_item):
                        break
                except (KeyboardInterrupt, EOFError):
                    break

            acc_inp = " ".join(acc_lines).strip()

            acc_start = 1
            acc_end = None
            selected_emails = None

            acc_inp_upper = acc_inp.upper()
            if acc_inp_upper in group_indices:
                indices = group_indices[acc_inp_upper]
                acc_start = min(indices)
                acc_end = max(indices)
                print(f"\n✅ Dipilih seluruh akun Wilayah {acc_inp_upper} (Urutan #{acc_start} s.d. #{acc_end} — Total {len(indices)} akun).")
            elif "@" in acc_inp:
                # Email(s) input
                emails = [e.strip() for e in acc_inp.replace(",", " ").replace(";", " ").replace("\t", " ").split() if "@" in e]
                if emails:
                    selected_emails = emails
                    print(f"\n✅ Dipilih {len(selected_emails)} akun spesifik berdasarkan Email:")
                    for e_idx, em in enumerate(selected_emails, 1):
                        print(f"   {e_idx:2d}. {em}")
            elif "-" in acc_inp and acc_inp.replace("-", "").isdigit():
                parts = acc_inp.split("-")
                acc_start = max(1, min(total_accs, int(parts[0])))
                acc_end = max(acc_start, min(total_accs, int(parts[1])))
                print(f"\n✅ Dipilih akun rentang urutan #{acc_start} s.d. #{acc_end}.")
            elif acc_inp.isdigit():
                acc_start = max(1, min(total_accs, int(acc_inp)))
                aend_inp = input(f"   Sampai Akun BPS ke- ({acc_start} - {total_accs}, default {total_accs}): ").strip()
                if aend_inp.isdigit():
                    acc_end = max(acc_start, min(total_accs, int(aend_inp)))
                else:
                    acc_end = total_accs
                print(f"\n✅ Dipilih akun rentang urutan #{acc_start} s.d. #{acc_end or total_accs}.")
            else:
                print(f"\n✅ Menggunakan SEMUA akun terdaftar (1 - {total_accs}).")


            workers_input = input(f"\n⚡ Jumlah paralel worker (default {args.workers}): ").strip()
            max_workers = int(workers_input) if workers_input.isdigit() and int(workers_input) > 0 else args.workers

            mode_args = {
                "resubmit_reject": args.resubmit_reject,
                "resubmit_open": args.resubmit_open,
                "resubmit_reopen": args.resubmit_reopen,
                "skip_cek_idpln": args.skip_cek_idpln,
                "dry_run": args.dry_run
            }

            try:
                runner = AutonomousRunner(
                    excel_path=excel_path,
                    users_file=args.users_file,
                    max_workers=max_workers,
                    mode_args=mode_args,
                    account_start=acc_start,
                    account_end=acc_end,
                    selected_emails=selected_emails
                )
            except Exception as e:
                print(f"\n❌ GAGAL MEMBUKA FILE EXCEL: {e}")
                input("\nTekan Enter untuk kembali ke Menu Utama...")
                continue

            runner.run(
                start_row=args.start_row,
                start_idpel=args.start_idpel,
                non_interactive=False
            )
            break


def main():
    parser = argparse.ArgumentParser(description="Autonomous Multi-Account Batch Processor for BPS FASIH")
    parser.add_argument("--excel", help="Path file Master Excel (.xlsx)")
    parser.add_argument("--users-file", default="users.json", help="Path file akun BPS (default: users.json)")
    parser.add_argument("--workers", type=int, default=20, help="Jumlah paralel worker (default: 20)")
    parser.add_argument("--start-row", type=int, help="Mulai dari nomor baris Excel tertentu (misal: 1500)")
    parser.add_argument("--start-idpel", type=str, help="Mulai dari IDPel tertentu di Excel")
    parser.add_argument("--account-start", type=int, default=1, help="Mulai dari urutan nomor akun BPS tertentu di users.json (default: 1)")
    parser.add_argument("--account-end", type=int, help="Sampai urutan nomor akun BPS tertentu di users.json (default: akun terakhir)")
    parser.add_argument("--emails", nargs="+", help="Filter daftar email BPS tertentu yang akan dijalankan")
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

    if not args.excel and not args.non_interactive:
        interactive_main_menu(args)
        return

    if not args.excel:
        parser.error("Argument --excel diperlukan saat menggunakan --non-interactive.")

    runner = AutonomousRunner(
        excel_path=args.excel,
        users_file=args.users_file,
        max_workers=args.workers,
        mode_args=mode_args,
        account_start=args.account_start,
        account_end=args.account_end,
        selected_emails=args.emails
    )
    runner.run(
        start_row=args.start_row,
        start_idpel=args.start_idpel,
        non_interactive=args.non_interactive
    )


if __name__ == "__main__":
    try:
        main()
    except (KeyboardInterrupt, EOFError):
        print("\n\n👋 Auto Runner dihentikan oleh pengguna (Ctrl+C). Selesai.")
        sys.exit(0)
