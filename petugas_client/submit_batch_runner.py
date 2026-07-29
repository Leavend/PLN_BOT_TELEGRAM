#!/usr/bin/env python3
"""
submit_batch_runner.py — Multi-Account Parallel Interactive Runner for fasih-submit-batch.

HP Petugas Restrictions (Excluding Local machine):
  1. Operating hours restricted to 07:00 - 18:00 WITA.
  2. Worker delay of 30 to 60 seconds per data item.
  3. Parallel multi-account execution engine.

Simulation Workflow:
  Step 1: Setoran Creds Akun BPS (Email & Password SSO).
  Step 2: Ask User how many accounts to run from saved Creds.
  Step 3: Prompt User 1-by-1 for DIL task lists (IDPel) per selected BPS Creds Account.
  Step 4: Execute Parallel Submits with HP Petugas Restrictions.
"""

import os
import sys
import re
import json
import time
import random
import datetime
import tempfile
import shutil
import argparse
from datetime import datetime as dt, timezone, timedelta
from concurrent.futures import ThreadPoolExecutor, as_completed

# Add repo root to sys.path
REPO_ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, REPO_ROOT)

from dotenv import load_dotenv
load_dotenv(os.path.join(REPO_ROOT, ".env"))

from region import get_region
from fasih_auth import perform_login, refresh_token_if_needed, get_headers, is_token_valid
from fasih_api import fetch_surveys, fetch_assignments, fetch_all_assignments, fetch_template_mapping, fetch_regions
from petugas_client.batch_submit import (
    submit_single,
    _load_survey_cache,
    _save_survey_cache,
    _account_email,
    apply_region_config,
    PLN_API_URL
)

USERS_FILE = os.path.join(REPO_ROOT, "users.json")
FASIH_ACCOUNTS_FILE = os.path.join(REPO_ROOT, ".fasih_accounts.txt")
TOKEN_FILE = os.path.join(REPO_ROOT, "fasih_token.json")


def is_local_environment() -> bool:
    """
    Check if execution is running in user's Local environment vs HP Petugas (Termux).
    Returns True if running locally (Mac OS / explicit Local flag / local user path).
    """
    if os.getenv("FASIH_LOCAL", "").lower() in ("1", "true", "yes"):
        return True
    if "--local" in sys.argv or "--bypass-restrictions" in sys.argv:
        return True
    # Auto-detect local machine (Mac OS / local workspace)
    if sys.platform == "darwin" or os.path.exists("/Users/leavend"):
        return True
    return False


def get_wita_time():
    """Get current time in WITA timezone (UTC+8)."""
    wita_tz = timezone(timedelta(hours=8))
    return dt.now(wita_tz)


def check_working_hours(is_local: bool = False) -> tuple[bool, str]:
    """
    Enforces 07:00 WITA - 18:00 WITA working hours on HP Petugas.
    Local machine is exempt.
    """
    if is_local:
        return True, "Mode Local — Pembatasan jam kerja dilewati."

    now_wita = get_wita_time()
    hour = now_wita.hour

    # Working hours: 07:00:00 - 17:59:59 WITA (hour 7 to 17 inclusive)
    if 7 <= hour < 18:
        return True, f"Jam Kerja Valid ({now_wita.strftime('%H:%M:%S')} WITA)"
    else:
        return False, (
            f"❌ [HP PETUGAS RESTRICTION] Pengerjaan fasih-submit-batch di HP Petugas HANYA dapat dilakukan pada jam kerja:\n"
            f"   ⏰ 07.00 WITA - 18.00 WITA.\n"
            f"   Waktu saat ini: {now_wita.strftime('%H:%M:%S')} WITA (Diluar Jam Kerja).\n"
            f"   Pengerjaan dihentikan untuk mematuhi regulasi jam kerja petugas."
        )


def load_saved_accounts() -> list[dict]:
    """Load all saved BPS accounts from users.json, .fasih_accounts.txt, and fasih_token.json."""
    accounts = []
    seen = set()

    # 1. users.json
    if os.path.exists(USERS_FILE):
        try:
            with open(USERS_FILE, "r") as f:
                data = json.load(f)
                for acc in data:
                    em = (acc.get("email") or "").strip()
                    if em and em.lower() not in seen:
                        seen.add(em.lower())
                        accounts.append(acc)
        except Exception:
            pass

    # 2. .fasih_accounts.txt
    if os.path.exists(FASIH_ACCOUNTS_FILE):
        try:
            with open(FASIH_ACCOUNTS_FILE, "r") as f:
                for line in f:
                    line = line.strip()
                    if not line or line.startswith("#"):
                        continue
                    if ":" in line:
                        em, pw = line.split(":", 1)
                        em, pw = em.strip(), pw.strip()
                    else:
                        em, pw = line, "Pln@1234"
                    if em and em.lower() not in seen:
                        seen.add(em.lower())
                        accounts.append({"email": em, "password": pw})
        except Exception:
            pass

    # 3. fasih_token.json
    if os.path.exists(TOKEN_FILE):
        try:
            with open(TOKEN_FILE, "r") as f:
                td = json.load(f)
                em = _account_email(td)
                if em and em.lower() not in seen:
                    seen.add(em.lower())
                    accounts.append({"email": em, "token_data": td})
        except Exception:
            pass

    return accounts


def save_accounts_to_disk(accounts: list[dict]):
    """Save updated accounts to users.json."""
    try:
        clean_accs = []
        for a in accounts:
            clean_accs.append({
                "email": a.get("email", ""),
                "password": a.get("password", ""),
                "token_data": a.get("token_data"),
                "daily_quota": a.get("daily_quota", 400),
                "used_today": a.get("used_today", 0),
                "last_date": a.get("last_date", datetime.date.today().isoformat()),
                "is_disabled": a.get("is_disabled", False)
            })
        with open(USERS_FILE, "w") as f:
            json.dump(clean_accs, f, indent=2)
    except Exception as e:
        print(f"⚠️ Gagal menyimpan users.json: {e}")


def get_or_fetch_survey_caches(account_info: dict, fast_mode: bool = True) -> dict:
    """Load or fetch BPS survey caches for a specific BPS account."""
    token_data = account_info.get("token_data")
    email = account_info.get("email") or _account_email(token_data)

    if not token_data and account_info.get("password"):
        token_data = perform_login(account_info["email"], account_info["password"], exit_on_failure=False)
        account_info["token_data"] = token_data

    if not token_data:
        raise ValueError(f"Akun {email} belum login / token tidak valid.")

    if not is_token_valid(token_data):
        token_data = refresh_token_if_needed(token_data, token_file=None, exit_on_failure=False)
        account_info["token_data"] = token_data

    survey_caches = _load_survey_cache(email) if fast_mode else None
    if survey_caches:
        return survey_caches

    headers = get_headers(token_data)
    surveys = fetch_surveys(headers)
    survey_caches = {}

    for survey in surveys:
        sname = (survey.get("name") or "").upper()
        skey = "PASCABAYAR" if "PASCA" in sname else "PRABAYAR" if "PRA" in sname else "DEFAULT"
        active_periode = next((p for p in survey.get("listPeriode", []) if p.get("isActive")), None)
        if not active_periode:
            continue
        pid = active_periode["id"]
        template_lookup = survey.get("templateLookup", [])
        template_mapping = {}
        if template_lookup:
            tl = template_lookup[0]
            template_mapping = fetch_template_mapping(headers, tl["templateId"], tl["templateVersion"])

        fp = fetch_assignments(headers, pid, 0)
        assignments = (fp.get("data") or {}).get("content", []) or []
        regions = fetch_regions(headers, pid)
        lookup = survey.get("templateLookup") or []
        tv = lookup[0].get("templateVersion") if lookup else None
        survey_caches[skey] = {
            "survey": survey,
            "periode": active_periode,
            "template_mapping": template_mapping,
            "assignments": assignments,
            "regions": regions,
            "template_version": tv,
        }

    if email and survey_caches:
        _save_survey_cache(email, survey_caches)

    return survey_caches


# --- SIMULATION WIZARD ---

def step1_setoran_creds(accounts: list[dict]) -> list[dict]:
    """Simulasi 1: Setoran Creds akun BPS yang akan dijalankan di HP Petugas."""
    print("\n" + "=" * 65)
    print("🔑 SIMULASI 1: SETORAN CREDS AKUN BPS (HP PETUGAS)")
    print("=" * 65)

    if accounts:
        print(f"📋 Ditemukan {len(accounts)} Akun Creds BPS yang tersimpan di sistem:")
        for idx, acc in enumerate(accounts, 1):
            em = acc.get("email", "Unknown")
            print(f"   {idx:2d}. {em}")
        print("-" * 65)

    ans = input("Apakah ingin menambah / setoran Creds Akun BPS baru? (y/N): ").strip().lower()
    if ans in ("y", "yes"):
        while True:
            print("\n➕ Setoran Creds Akun BPS Baru:")
            email = input("   Email BPS SSO   : ").strip()
            if not email:
                break
            import getpass
            try:
                password = getpass.getpass("   Password BPS SSO: ").strip()
            except Exception:
                password = input("   Password BPS SSO: ").strip()

            if not password:
                print("❌ Password tidak boleh kosong.")
                continue

            print(f"🔄 Verifikasi login SSO BPS untuk {email}...")
            td = perform_login(email, password, exit_on_failure=False)
            if td and "access_token" in td:
                print(f"✅ Login SSO BERHASIL untuk {email}!")
                existing = next((a for a in accounts if a.get("email", "").lower() == email.lower()), None)
                if existing:
                    existing["password"] = password
                    existing["token_data"] = td
                else:
                    accounts.append({
                        "email": email,
                        "password": password,
                        "token_data": td
                    })
                save_accounts_to_disk(accounts)
            else:
                print(f"❌ Login SSO Gagal untuk {email}. Silakan cek email & password.")

            again = input("\nTambah Creds akun BPS lagi? (y/N): ").strip().lower()
            if again not in ("y", "yes"):
                break

    if not accounts:
        print("\n❌ Belum ada Creds akun BPS tersimpan. Wajib setoran minimal 1 Creds akun BPS!")
        while not accounts:
            print("\n➕ Input Creds Akun BPS Pertama:")
            email = input("   Email BPS SSO   : ").strip()
            import getpass
            try:
                password = getpass.getpass("   Password BPS SSO: ").strip()
            except Exception:
                password = input("   Password BPS SSO: ").strip()
            if email and password:
                td = perform_login(email, password, exit_on_failure=False)
                if td and "access_token" in td:
                    print(f"✅ Login SSO BERHASIL untuk {email}!")
                    accounts.append({"email": email, "password": password, "token_data": td})
                    save_accounts_to_disk(accounts)
                else:
                    print("❌ Login SSO Gagal. Coba lagi.")

    return accounts


def step2_select_account_count(accounts: list[dict]) -> list[dict]:
    """Simulasi 2: Ditanyakan mau jalankan berapa akun berdasarkan Creds yang diinput."""
    print("\n" + "=" * 65)
    print("📊 SIMULASI 2: PENENTUAN JUMLAH AKUN BPS YANG AKAN DIJALANKAN")
    print("=" * 65)
    print(f"Daftar Creds Akun BPS yang tersedia ({len(accounts)} akun):")
    for idx, acc in enumerate(accounts, 1):
        print(f"   [{idx}] {acc.get('email')}")

    print("-" * 65)
    while True:
        num_str = input(f"👉 Mau jalankan pengerjaan berapa akun BPS? [1 - {len(accounts)}]: ").strip()
        if num_str.isdigit():
            k = int(num_str)
            if 1 <= k <= len(accounts):
                break
        print(f"❌ Harap masukkan angka valid antara 1 s.d. {len(accounts)}.")

    selected_accounts = accounts[:k]
    print(f"\n✅ {len(selected_accounts)} Akun BPS akan dijalankan pada pengerjaan kali ini:")
    for idx, acc in enumerate(selected_accounts, 1):
        print(f"   {idx}. {acc.get('email')}")

    return selected_accounts


def step3_input_dil_tasks_per_account(selected_accounts: list[dict]) -> dict:
    """
    Simulasi 3: Ditanyain 1 per 1 List Tugas Pengerjaan DIL sesuai berapa Creds akun BPS
    yang ingin dikerjakan di Simulasi 2.
    """
    print("\n" + "=" * 65)
    print("📋 SIMULASI 3: INPUT LIST TUGAS DIL (IDPEL) PER CREDS AKUN BPS")
    print("=" * 65)

    account_tasks = {}

    for idx, acc in enumerate(selected_accounts, 1):
        email = acc.get("email")
        print("\n" + "-" * 65)
        print(f"📋 [Creds Akun BPS #{idx}/{len(selected_accounts)}] {email}")
        print("   Masukkan / Paste List Tugas IDPel yang akan dikerjakan untuk akun ini.")
        print("   Tekan ENTER 2x jika sudah selesai mempaste list IDPel.")
        print("-" * 65)

        lines = []
        empty = 0
        while True:
            try:
                line = input()
            except EOFError:
                break
            stripped = line.strip()
            if not stripped:
                empty += 1
                if empty >= 2:
                    break
                continue
            empty = 0
            ids = re.findall(r'\b\d{12}\b', stripped)
            if ids:
                lines.extend(ids)
            elif stripped.isdigit() and len(stripped) >= 8:
                lines.append(stripped)

        # Dedup, preserve order
        seen = set()
        unique_tasks = []
        for t in lines:
            if t not in seen:
                seen.add(t)
                unique_tasks.append(t)

        print(f"✅ Berhasil menginput {len(unique_tasks)} IDPel tugas untuk Creds akun {email}.")
        account_tasks[email] = {
            "account": acc,
            "tasks": unique_tasks
        }

    return account_tasks


def step4_execute_parallel_batch(account_tasks: dict, is_local: bool, mode_flags: dict):
    """
    Simulasi 4: Running Script fasih-submit-batch secara Paralel dengan Pembatasan:
      1. Waktu: 07.00 - 18.00 WITA (Khusus HP Petugas).
      2. Delay per worker: 30 - 60 Detik / Data (Khusus HP Petugas).
    """
    print("\n" + "=" * 65)
    print("⚡ SIMULASI 4: EKSEKUSI FASIH-SUBMIT-BATCH PARALEL")
    print("=" * 65)

    # 1. Re-verify working hours condition
    ok_hours, hours_msg = check_working_hours(is_local)
    if not ok_hours:
        print(hours_msg)
        sys.exit(1)

    print(f"⏰ Status Jam Kerja : {hours_msg}")
    if is_local:
        print("📍 Mode Execution   : 💻 LOCAL (Bypass pembatasan jam kerja & delay 30-60s HP)")
    else:
        print("📍 Mode Execution   : 📱 HP PETUGAS (Termux / Remote Device)")
        print("🔒 Pembatasan Delay : ⏳ 30 Detik - 60 Detik per Data per Worker")

    print("-" * 65)

    # Flatten all tasks into a queue for parallel execution
    task_queue = []
    for email, info in account_tasks.items():
        acc = info["account"]
        tasks = info["tasks"]
        for idpel in tasks:
            task_queue.append({
                "email": email,
                "account": acc,
                "idpel": idpel
            })

    total_tasks = len(task_queue)
    if total_tasks == 0:
        print("⚠️ Tidak ada IDPel tugas yang diinput untuk diproses.")
        return

    print(f"🚀 Memulai pengerjaan {total_tasks} IDPel pada {len(account_tasks)} Creds Akun BPS...")

    # Fetch survey caches for all involved accounts upfront
    caches_by_email = {}
    print("\n📊 Mempersiapkan cache survei BPS untuk setiap akun...")
    for email, info in account_tasks.items():
        try:
            print(f"   • Memuat survei untuk {email}...")
            caches_by_email[email] = get_or_fetch_survey_caches(info["account"], fast_mode=True)
            print(f"     ✅ Survei {email} siap.")
        except Exception as e:
            print(f"     ❌ Gagal memuat survei {email}: {e}")

    # Determine worker count (1 worker per account or min 4)
    workers_count = max(1, len(account_tasks))
    print(f"\n⚡ Worker Paralel Aktif: {workers_count} Worker Thread")
    print("=" * 65 + "\n")

    report_rows = []
    completed_cnt = 0
    success_cnt = 0
    failed_cnt = 0
    start_time = time.time()
    lock = ThreadPoolExecutor(max_workers=workers_count)

    def _worker_task(item):
        email = item["email"]
        acc = item["account"]
        idpel = item["idpel"]
        survey_caches = caches_by_email.get(email)

        if not survey_caches:
            return idpel, email, False, f"❌ Survey cache tidak tersedia untuk {email}"

        # Pembatasan 2: Delay 30 - 60 Detik per Data di HP Petugas
        if not is_local:
            delay_sec = random.uniform(30.0, 60.0)
            print(f"⏳ [HP Petugas Delay] Worker ({email}) delay {delay_sec:.1f}s sebelum submit {idpel}...")
            time.sleep(delay_sec)
        else:
            time.sleep(random.uniform(0.1, 0.4))

        # Re-check working hours periodically during execution
        ok_h, msg_h = check_working_hours(is_local)
        if not ok_h:
            return idpel, email, False, f"❌ Terhenti: {msg_h}"

        token_data = acc.get("token_data")
        wdir = tempfile.mkdtemp(prefix=f"fasih_batch_{idpel}_")
        try:
            ok, msg = submit_single(
                token_data=token_data,
                val=idpel,
                survey_caches=survey_caches,
                dry_run=mode_flags.get("dry_run", False),
                temp_dir=wdir,
                force=mode_flags.get("force", False),
                resubmit_all=mode_flags.get("resubmit_all", False),
                resubmit_reject=mode_flags.get("resubmit_reject", False),
                resubmit_open=mode_flags.get("resubmit_open", False),
                resubmit_reopen=mode_flags.get("resubmit_reopen", False),
                skip_cek_idpln=mode_flags.get("skip_cek_idpln", False),
            )
        except Exception as e:
            ok, msg = False, f"Error: {str(e)[:120]}"
        finally:
            shutil.rmtree(wdir, ignore_errors=True)

        return idpel, email, ok, msg

    futures = [lock.submit(_worker_task, t) for t in task_queue]
    for fut in as_completed(futures):
        idpel, email, ok, msg = fut.result()
        completed_cnt += 1
        if ok:
            success_cnt += 1
        else:
            failed_cnt += 1

        icon = "✅" if ok else "❌"
        print(f"[{completed_cnt}/{total_tasks}] {idpel} ({email}) {icon} {msg}")

        report_rows.append({
            "idpel": idpel,
            "email": email,
            "status": "SUCCESS" if ok else "FAILED",
            "message": msg,
            "timestamp": dt.now().strftime("%Y-%m-%d %H:%M:%S")
        })

    lock.shutdown(wait=True)

    elapsed = time.time() - start_time
    m, s = divmod(int(elapsed), 60)

    print("\n" + "=" * 65)
    print("🏁 HASIL PENGERJAAN BATCH PARALEL SELESAI")
    print("=" * 65)
    print(f"✅ Total Sukses: {success_cnt}")
    print(f"❌ Total Gagal : {failed_cnt}")
    print(f"⏱  Total Waktu : {m}m {s}s")
    print("=" * 65)

    # Save report
    report_filename = f"batch_report_{dt.now().strftime('%Y%m%d_%H%M%S')}.csv"
    try:
        import csv
        with open(report_filename, "w", encoding="utf-8", newline="") as f:
            writer = csv.DictWriter(f, fieldnames=["idpel", "email", "status", "message", "timestamp"])
            writer.writeheader()
            writer.writerows(report_rows)
        print(f"📄 Report tersimpan di: {report_filename}")
    except Exception as e:
        print(f"⚠️ Gagal menyimpan report CSV: {e}")


def main():
    # Only delegate to batch_submit.py if an explicit input file (.txt/.csv) or --list / --resubmit-* flag is passed
    has_direct_input = any(arg.endswith(('.txt', '.csv')) for arg in sys.argv[1:])
    has_resubmit_or_list = any(arg.startswith(('--list', '-l', '--resubmit-reject', '--resubmit-open', '--resubmit-reopen', '--resubmit-all')) for arg in sys.argv[1:])

    if len(sys.argv) > 1 and (has_direct_input or has_resubmit_or_list):
        import subprocess
        cmd = [sys.executable, os.path.join(REPO_ROOT, "petugas_client", "batch_submit.py")] + sys.argv[1:]
        result = subprocess.run(cmd)
        sys.exit(result.returncode)

    parser = argparse.ArgumentParser(description="fasih-submit-batch Interactive & Parallel Runner")
    parser.add_argument("input", nargs="?", help="File .txt berisi list IDPel (optional)")
    parser.add_argument("--list", "-l", help="List IDPel dipisah koma (optional)")
    parser.add_argument("--local", action="store_true", help="Paksa mode local (bypass jam kerja & delay HP)")
    parser.add_argument("--bypass-restrictions", action="store_true", help="Bypass pembatasan jam kerja & delay HP")
    parser.add_argument("--dry-run", action="store_true", help="Test tanpa submit nyata")
    parser.add_argument("--force", action="store_true", help="Force re-register")
    parser.add_argument("--resubmit-all", action="store_true", help="Submit ulang semua")
    parser.add_argument("--resubmit-reject", action="store_true", help="Perbaiki data REJECT")
    parser.add_argument("--resubmit-open", action="store_true", help="Submit data OPEN")
    parser.add_argument("--resubmit-reopen", action="store_true", help="Submit data REOPEN")
    parser.add_argument("--skip-cek-idpln", "--no-cek", action="store_true", dest="skip_cek_idpln", help="Skip CEK IDPel BPS")
    parser.add_argument("--fast", action="store_true", help="Setup survei dari cache disk")
    parser.add_argument("--workers", type=int, default=0, help="Jumlah submit paralel")
    parser.add_argument("--delay", type=float, default=0.5, help="Stagger delay per item")

    args, unknown = parser.parse_known_args()

    is_local = is_local_environment() or args.local or args.bypass_restrictions
    _mb = apply_region_config()

    print("=" * 65)
    print("📌 FASIH SUBMIT BATCH — PARALEL SYSTEM HP PETUGAS")
    print("=" * 65)
    print(f"🌏 Wilayah    : {get_region()}" + (f" (Mapbox: {_mb})" if _mb else ""))
    print(f"📍 Mode Env   : {'💻 LOCAL (Exempt/Bypass Restrictions)' if is_local else '📱 HP PETUGAS (Restricted 07-18 WITA & 30-60s Delay)'}")

    # Preliminary Working Hours Check for HP Petugas
    ok_hours, hours_msg = check_working_hours(is_local)
    print(f"⏰ Jam Kerja   : {hours_msg}")
    print("=" * 65)

    if not ok_hours:
        print(f"\n{hours_msg}")
        sys.exit(1)

    # 1. Load accounts
    accounts = load_saved_accounts()

    # 2. Step 1: Setoran Creds Akun BPS
    accounts = step1_setoran_creds(accounts)

    # 3. Step 2: Mau jalankan berapa akun?
    selected_accounts = step2_select_account_count(accounts)

    # 4. Step 3: Input list tugas IDPel per akun 1 per 1
    account_tasks = step3_input_dil_tasks_per_account(selected_accounts)

    # 5. Step 4: Execute parallel submit with restrictions
    mode_flags = {
        "dry_run": args.dry_run,
        "force": args.force,
        "resubmit_all": args.resubmit_all,
        "resubmit_reject": args.resubmit_reject,
        "resubmit_open": args.resubmit_open,
        "resubmit_reopen": args.resubmit_reopen,
        "skip_cek_idpln": args.skip_cek_idpln,
        "fast": args.fast,
        "workers": args.workers,
        "delay": args.delay,
    }

    step4_execute_parallel_batch(account_tasks, is_local, mode_flags)


if __name__ == "__main__":
    try:
        main()
    except (KeyboardInterrupt, EOFError):
        print("\n\n👋 Dibatalkan oleh pengguna (Ctrl+C). Selesai.")
        sys.exit(0)
