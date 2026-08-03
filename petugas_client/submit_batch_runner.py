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
    _reject_idpels,
    _open_idpels,
    _reopen_idpels,
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


PETUGAS_ACCOUNTS_FILE = os.path.join(REPO_ROOT, "petugas_accounts.json")


def load_saved_accounts(is_local: bool = False) -> list[dict]:
    """
    Load saved BPS accounts.
    - HP Petugas (is_local=False): ONLY loads local setoran accounts on this HP (petugas_accounts.json) & fasih_token.json.
    - Local Machine (is_local=True): Loads master users.json, .fasih_accounts.txt, and local accounts.
    """
    accounts = []
    seen = set()

    # 1. Local petugas_accounts.json (Setoran Akun khusus HP ini)
    if os.path.exists(PETUGAS_ACCOUNTS_FILE):
        try:
            with open(PETUGAS_ACCOUNTS_FILE, "r") as f:
                data = json.load(f)
                for acc in data:
                    em = (acc.get("email") or "").strip()
                    if em and em.lower() not in seen:
                        seen.add(em.lower())
                        accounts.append(acc)
        except Exception:
            pass

    # 2. Currently logged in account on device (fasih_token.json)
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

    # 3. ONLY if running on LOCAL Computer (MacBook / Admin), load master users.json & .fasih_accounts.txt
    if is_local:
        if os.path.exists(USERS_FILE):
            try:
                with open(USERS_FILE, "r") as f:
                    data = json.load(f)
                    for acc in data:
                        em = (acc.get("email") or "").strip()
                        if em and em.lower() not in seen:
                            seen.add(em.lower())
                            acc["_src"] = "users"   # milik users.json — boleh ditulis balik
                            accounts.append(acc)
            except Exception:
                pass

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
                            # Sumber khusus fasih-submit-batch. JANGAN ditulis ke
                            # users.json — itu daftar kerja fasih-auto-runner
                            # (ULP Sulawesi & Berau) yang punya field `group`.
                            accounts.append({"email": em, "password": pw, "_src": "fasih_txt"})
            except Exception:
                pass

    return accounts


def save_accounts_to_disk(accounts: list[dict], is_local: bool = False):
    """Simpan akun. HP Petugas -> petugas_accounts.json; Local -> users.json.

    MERGE, bukan timpa. Versi lama menulis ulang seluruh daftar in-memory dengan
    set field tetap, sehingga dua kerusakan terjadi sekaligus di mesin local:
      1. `group` dan `hits_429` milik users.json terhapus (pengelompokan ULP
         fasih-auto-runner hilang — 233 akun jadi tanpa group);
      2. akun yang cuma ada di .fasih_accounts.txt (khusus fasih-submit-batch)
         ikut tertulis ke users.json, jadi daftar kerja runner tercemar.
    Sekarang: field lama dipertahankan, dan hanya akun milik users.json atau yang
    baru disetor lewat wizard yang ditulis balik."""
    try:
        target_file = USERS_FILE if is_local else PETUGAS_ACCOUNTS_FILE
        existing = []
        try:
            with open(target_file) as f:
                existing = json.load(f) or []
        except Exception:
            existing = []
        by_email = {str(a.get("email", "")).strip().lower(): a for a in existing}

        for a in accounts:
            em = str(a.get("email", "")).strip()
            if not em:
                continue
            key = em.lower()
            # Akun dari sumber lain (.fasih_accounts.txt / token login) tidak boleh
            # menambah baris baru di sini — kecuali memang sudah tercatat.
            if a.get("_src") in ("fasih_txt",) and key not in by_email:
                continue
            row = by_email.get(key)
            if row is None:
                row = {
                    "email": em,
                    "daily_quota": 400,
                    "used_today": 0,
                    "last_date": datetime.date.today().isoformat(),
                    "is_disabled": False,
                }
                by_email[key] = row
                existing.append(row)
            # perbarui hanya yang memang berubah; field lain (group, hits_429, dst) utuh
            for k in ("password", "token_data"):
                if a.get(k):
                    row[k] = a[k]

        with open(target_file + ".tmp", "w") as f:
            json.dump([{k: v for k, v in r.items() if k != "_src"} for r in existing], f, indent=2)
        os.replace(target_file + ".tmp", target_file)
    except Exception as e:
        print(f"⚠️ Gagal menyimpan file akun: {e}")


def get_or_fetch_survey_caches(account_info: dict, fast_mode: bool = True, full_fetch: bool = False) -> dict:
    """Load or fetch BPS survey caches for a specific BPS account.

    full_fetch=True pages through EVERY assignment (needed by discovery modes —
    reject/open/reopen live beyond page-0) and bypasses the page-0 disk cache."""
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

    # The disk "fast" cache only holds page-0 templates — useless for discovery, skip it.
    survey_caches = _load_survey_cache(email) if (fast_mode and not full_fetch) else None
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

        if full_fetch:
            assignments = fetch_all_assignments(headers, pid)
        else:
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

    # Never persist the full-list fetch as the page-0 "fast" cache.
    if email and survey_caches and not full_fetch:
        _save_survey_cache(email, survey_caches)

    return survey_caches


# --- SIMULATION WIZARD ---

def step1_setoran_creds(accounts: list[dict], is_local: bool = False) -> list[dict]:
    """Simulasi 1: Setoran Creds akun BPS yang akan dijalankan di HP Petugas."""
    print("\n" + "=" * 65)
    print("🔑 SIMULASI 1: SETORAN CREDS AKUN BPS (HP PETUGAS)")
    print("=" * 65)

    if accounts:
        print(f"📋 Ditemukan {len(accounts)} Akun Creds BPS tersimpan di HP ini:")
        for idx, acc in enumerate(accounts, 1):
            em = acc.get("email", "Unknown")
            print(f"   {idx:2d}. {em}")
        print("-" * 65)
    else:
        print("📋 Belum ada Creds Akun BPS tersimpan di HP ini.")
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
                save_accounts_to_disk(accounts, is_local)
            else:
                print(f"❌ Login SSO Gagal untuk {email}. Silakan cek email & password.")

            again = input("\nTambah Creds akun BPS lagi? (y/N): ").strip().lower()
            if again not in ("y", "yes"):
                break

    if not accounts:
        print("\n❌ Belum ada Creds akun BPS tersimpan di HP ini. Wajib setoran minimal 1 Creds akun BPS!")
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
                    save_accounts_to_disk(accounts, is_local)
                else:
                    print("❌ Login SSO Gagal. Coba lagi.")

    return accounts


def step2_select_account_count(accounts: list[dict]) -> list[dict]:
    """Simulasi 2: Ditanyakan mau jalankan berapa/yang mana akun berdasarkan Creds yang diinput."""
    print("\n" + "=" * 65)
    print("📊 SIMULASI 2: PENENTUAN AKUN BPS YANG AKAN DIJALANKAN")
    print("=" * 65)
    print(f"Daftar Creds Akun BPS yang tersedia ({len(accounts)} akun):")
    for idx, acc in enumerate(accounts, 1):
        print(f"   [{idx:3d}] {acc.get('email')}")

    print("-" * 65)
    print("📌 OPSI PILIHAN AKUN:")
    print("  • Masukkan 1 nomor (misal: 1 untuk HANYA akun ke-1)")
    print("  • Masukkan rentang nomor (misal: 1-5 untuk akun ke-1 s.d. ke-5)")
    print("  • Masukkan nomor terpisah (misal: 1,3,7)")
    print("  • Paste Email BPS (misal: heriplnt90@gmail.com)")
    print(f"  • Tekan ENTER / ketik 'ALL' untuk menggunakan SEMUA akun (1 - {len(accounts)})")
    print("-" * 65)

    selected_accounts = []
    while True:
        inp = input(f"👉 Pilih Akun BPS yang akan dijalankan: ").strip()

        if not inp or inp.upper() in ("ALL", "SEMUA"):
            selected_accounts = list(accounts)
            break

        # Check if email is entered
        if "@" in inp:
            matched = [a for a in accounts if inp.lower() in a.get("email", "").lower()]
            if matched:
                selected_accounts = matched
                break
            else:
                print(f"❌ Akun dengan email '{inp}' tidak ditemukan.")
                continue

        # Check range e.g. 1-5
        if "-" in inp and inp.replace("-", "").isdigit():
            parts = inp.split("-")
            s_idx = max(1, min(len(accounts), int(parts[0])))
            e_idx = max(s_idx, min(len(accounts), int(parts[1])))
            selected_accounts = accounts[s_idx - 1 : e_idx]
            break

        # Check comma separated e.g. 1, 3, 5
        if "," in inp:
            try:
                indices = [int(x.strip()) for x in inp.split(",") if x.strip().isdigit()]
                valid = [accounts[i - 1] for i in indices if 1 <= i <= len(accounts)]
                if valid:
                    selected_accounts = valid
                    break
            except Exception:
                pass

        # Check single number e.g. 1
        if inp.isdigit():
            val = int(inp)
            if 1 <= val <= len(accounts):
                selected_accounts = [accounts[val - 1]]
                break
            else:
                print(f"❌ Masukkan nomor antara 1 dan {len(accounts)}.")

    print(f"\n✅ {len(selected_accounts)} Akun BPS dipilih untuk pengerjaan kali ini:")
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


def step3_discover_tasks_per_account(selected_accounts: list[dict], discover_mode: str) -> tuple[dict, dict]:
    """Step 3 for discovery modes (reject/open/reopen): instead of pasting IDPel lists,
    auto-derive each selected account's work-list straight from BPS. Accounts with zero
    matching records are kept but simply contribute nothing (user does not have to know
    upfront which accounts have rejects). Returns (account_tasks, caches_by_email) — the
    full-list caches are reused by Step 4 so the resubmit engine can bind each record."""
    label = {
        "reject": "REJECT",
        "open": "OPEN (belum dibuka)",
        "reopen": "OPEN (pernah dibuka)",
    }[discover_mode]
    discover_fn = {
        "reject": _reject_idpels,
        "open": _open_idpels,
        "reopen": _reopen_idpels,
    }[discover_mode]

    print("\n" + "=" * 65)
    print(f"📋 SIMULASI 3: DETEKSI OTOMATIS DATA {label} PER AKUN BPS")
    print("=" * 65)
    print(f"   Tidak perlu paste IDPel — sistem ambil daftar {label} langsung dari BPS")
    print(f"   untuk tiap akun terpilih (login pakai creds tersimpan, tanpa login manual).")

    account_tasks = {}
    caches_by_email = {}
    n = len(selected_accounts)
    for idx, acc in enumerate(selected_accounts, 1):
        email = acc.get("email") or _account_email(acc.get("token_data"))
        try:
            caches = get_or_fetch_survey_caches(acc, full_fetch=True)
        except Exception as e:
            print(f"   [{idx}/{n}] {email}: ❌ gagal ambil survei — {str(e)[:80]} (dilewati)")
            continue
        ids = discover_fn(caches)
        caches_by_email[email] = caches
        account_tasks[email] = {"account": acc, "tasks": ids}
        tail = "" if ids else " — tidak ada, dilewati"
        print(f"   [{idx}/{n}] {email}: {len(ids)} data {label}{tail}")

    total = sum(len(v["tasks"]) for v in account_tasks.values())
    print("-" * 65)
    print(f"📊 Total {total} data {label} ditemukan di {len(account_tasks)} akun.")

    # Optional per-account cap (mirrors single-account --resubmit-reject "mau berapa?").
    if total > 0 and sys.stdin.isatty():
        try:
            ans = input(f"👉 Berapa data {label} per akun di-resubmit? [ENTER=SEMUA, atau angka misal 10]: ").strip()
            if ans.isdigit() and int(ans) > 0:
                cap = int(ans)
                for v in account_tasks.values():
                    v["tasks"] = v["tasks"][:cap]
                print(f"🎯 Dibatasi maksimal {cap} data {label} per akun.")
        except (KeyboardInterrupt, EOFError):
            print("\n❌ Dibatalkan oleh pengguna.")
            sys.exit(0)

    return account_tasks, caches_by_email


def step4_execute_parallel_batch(account_tasks: dict, is_local: bool, mode_flags: dict, caches_by_email: dict = None):
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

    # Fetch survey caches for all involved accounts upfront — unless the discovery step
    # already built the full-list caches (reject/open/reopen), in which case reuse them.
    if caches_by_email is None:
        caches_by_email = {}
        print("\n📊 Mempersiapkan cache survei BPS untuk setiap akun...")
        for email, info in account_tasks.items():
            try:
                print(f"   • Memuat survei untuk {email}...")
                caches_by_email[email] = get_or_fetch_survey_caches(info["account"], fast_mode=True)
                print(f"     ✅ Survei {email} siap.")
            except Exception as e:
                print(f"     ❌ Gagal memuat survei {email}: {e}")

    # Determine worker count (from --workers parameter if specified, else min 4 or len(account_tasks))
    req_workers = mode_flags.get("workers") or 0
    if req_workers > 0:
        workers_count = req_workers
    else:
        workers_count = max(4, len(account_tasks))
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

        # Reject resubmit: 30-60s/data to spread BPS load + human-like pacing (matches
        # single-account --resubmit-reject). Other modes run at full speed.
        if mode_flags.get("resubmit_reject"):
            time.sleep(random.uniform(30, 60))

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

    _succ = [r for r in report_rows if r["status"] == "SUCCESS"]
    _sent = [r for r in _succ if r["message"].startswith("Sukses") or "berhasil" in r["message"].lower()]
    _terc = [r for r in _succ if r["message"].startswith("Sudah") or "TERCATAT" in r["message"]]

    if _sent:
        print(f"   📤 Baru dikirim ke BPS  : {len(_sent)}")
        sample_sent = [r['idpel'] for r in _sent[:10]]
        print(f"      IDPel: {', '.join(sample_sent)}" + (f" ... (+{len(_sent)-10} lainnya)" if len(_sent) > 10 else ""))
    if _terc:
        print(f"   🟢 Sudah tercatat (skip): {len(_terc)}")
        sample_terc = [r['idpel'] for r in _terc[:10]]
        print(f"      IDPel: {', '.join(sample_terc)}" + (f" ... (+{len(_terc)-10} lainnya)" if len(_terc) > 10 else ""))

    print(f"❌ Total Gagal : {failed_cnt}")

    failed_rows = [r for r in report_rows if r["status"] == "FAILED"]
    if failed_rows:
        print(f"\n❌ Rincian {len(failed_rows)} IDPel Gagal:")
        for r in failed_rows:
            print(f"   • {r['idpel']} ({r['email']}) — {r['message']}")

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
    # Delegate to the single-account batch_submit.py engine ONLY for explicit-list modes:
    # a direct .txt/.csv file, --list/-l, or --resubmit-all (all need a caller-supplied
    # list). Discovery modes (--resubmit-reject/open/reopen) now run through the SAME
    # multi-account wizard below — auto-deriving each account's list from BPS with the
    # saved creds, no single login/logout — UNLESS an explicit file/list is also given.
    has_direct_input = any(arg.endswith(('.txt', '.csv')) for arg in sys.argv[1:])
    has_list = any(arg in ('--list', '-l') or arg.startswith(('--list=', '-l=')) for arg in sys.argv[1:])
    has_resubmit_all = any(arg == '--resubmit-all' for arg in sys.argv[1:])

    if len(sys.argv) > 1 and (has_direct_input or has_list or has_resubmit_all):
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

    # 1. Load accounts (HP Petugas loads local setoran accounts, Local machine loads master repo accounts)
    accounts = load_saved_accounts(is_local)

    # 2. Step 1: Setoran Creds Akun BPS
    accounts = step1_setoran_creds(accounts, is_local)

    # 3. Step 2: Mau jalankan berapa akun?
    selected_accounts = step2_select_account_count(accounts)

    # 4. Step 3: discovery modes auto-derive the per-account list from BPS; otherwise
    # the user pastes an IDPel list per account.
    discover_mode = (
        "reject" if args.resubmit_reject else
        "open" if args.resubmit_open else
        "reopen" if args.resubmit_reopen else None
    )
    caches_by_email = None
    if discover_mode:
        account_tasks, caches_by_email = step3_discover_tasks_per_account(selected_accounts, discover_mode)
    else:
        account_tasks = step3_input_dil_tasks_per_account(selected_accounts)

    if not account_tasks or all(not v.get("tasks") for v in account_tasks.values()):
        print("\n✅ Tidak ada data untuk diproses pada akun terpilih. Selesai.")
        sys.exit(0)

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

    step4_execute_parallel_batch(account_tasks, is_local, mode_flags, caches_by_email=caches_by_email)


if __name__ == "__main__":
    try:
        main()
    except (KeyboardInterrupt, EOFError):
        print("\n\n👋 Dibatalkan oleh pengguna (Ctrl+C). Selesai.")
        sys.exit(0)
