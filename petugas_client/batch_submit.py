#!/usr/bin/env python3
"""
Batch Submit Petugas — standalone script untuk HP petugas (Termux).

Menjalankan step 1-10 BPS submit pipeline dari HP petugas,
menggunakan IP/jaringan paket data petugas sendiri.
PLN data + foto diambil dari server pusat via REST API.

Usage:
    python3 petugas_client/batch_submit.py data.txt
    python3 petugas_client/batch_submit.py --list 234000279419,234000093158
    python3 petugas_client/batch_submit.py data.txt --dry-run
"""

import os
import sys
import json
import time
import random
import logging
import argparse
import tempfile
import shutil
import threading
import csv
import requests
from concurrent.futures import ThreadPoolExecutor, as_completed

from datetime import datetime
from typing import Optional, Dict, Tuple


# Add repo root to path
REPO_ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, REPO_ROOT)

from dotenv import load_dotenv
load_dotenv(os.path.join(REPO_ROOT, ".env"))

from region import get_region

from fasih_auth import perform_login, refresh_token_if_needed, get_headers, is_token_valid
from fasih_api import (
    fetch_surveys, fetch_all_assignments, fetch_assignments,
    fetch_template_mapping, fetch_regions,
    request_photo_presign_put, upload_photo_to_s3, request_photo_presign_get,
    confirm_submit, request_presign_url, upload_to_s3,
    map_answers_to_data_slots, mask_pii_name,
    check_idpln, check_nikpln,
)
from fasih_crypto import compute_md5, compute_md5_base64
from fasih_archive import create_7z_archive
from submit_fasih import (
    build_dynamic_answers, stage_and_encrypt, clean_pln_name,
    build_new_assignment_target, resolve_coordinate, build_paradata,
    STATIC_LEGACY_KEY, build_principal_json, wrap_answers,
)
from region import get_region, DEFAULT_REGION

import requests as req_lib
import base64
import hashlib

# --- Environment & Restrictions ---

def is_local_environment() -> bool:
    """Check if execution is running in user's Local environment vs HP Petugas (Termux)."""
    if os.getenv("FASIH_LOCAL", "").lower() in ("1", "true", "yes"):
        return True
    if "--local" in sys.argv or "--bypass-restrictions" in sys.argv:
        return True
    if sys.platform == "darwin" or os.path.exists("/Users/leavend"):
        return True
    return False

def check_working_hours(is_local: bool = False) -> tuple[bool, str]:
    """Enforces 07:00 WITA - 18:00 WITA working hours restriction on HP Petugas."""
    if is_local:
        return True, "Mode Local — Pembatasan jam kerja dilewati."
    from datetime import timezone, timedelta
    wita_tz = timezone(timedelta(hours=8))
    now_wita = datetime.now(wita_tz)
    hour = now_wita.hour
    if 7 <= hour < 18:
        return True, f"Jam Kerja Valid ({now_wita.strftime('%H:%M:%S')} WITA)"
    else:
        return False, (
            f"❌ [HP PETUGAS RESTRICTION] Pengerjaan fasih-submit-batch di HP Petugas HANYA dapat dilakukan pada jam kerja:\n"
            f"   ⏰ 07.00 WITA - 18.00 WITA.\n"
            f"   Waktu saat ini: {now_wita.strftime('%H:%M:%S')} WITA (Diluar Jam Kerja)."
        )

# --- Logging ---

logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s"
)
logger = logging.getLogger("petugas")

def extract_regions_from_orig(orig_data: dict) -> dict:
    if not orig_data or "answers" not in orig_data:
        return {}
    
    import re
    answers_map = {}
    for a in orig_data["answers"]:
        if isinstance(a, dict) and "dataKey" in a:
            answers_map[a["dataKey"]] = a.get("answer")
            
    res = {}
    
    def parse_bracket(val):
        if not val:
            return "", ""
        if isinstance(val, list) and val:
            item = val[0]
            label = item.get("label") or ""
            val_code = item.get("value") or ""
            if label:
                m = re.match(r'^\[(.*?)\]\s*(.*)$', label)
                if m:
                    return val_code or m.group(1), m.group(2)
            return val_code, label
        elif isinstance(val, str):
            m = re.match(r'^\[(.*?)\]\s*(.*)$', val)
            if m:
                return m.group(1), m.group(2)
        return "", str(val)

    prov_code, prov_name = parse_bracket(answers_map.get("r102a"))
    kab_code, kab_name = parse_bracket(answers_map.get("r102b"))
    kec_code, kec_name = parse_bracket(answers_map.get("r102c"))
    kel_code, kel_name = parse_bracket(answers_map.get("r102d"))
    
    if prov_code: res["pln_kd_prov"] = prov_code
    if prov_name: res["pln_nama_prov"] = prov_name
    if kab_code: res["pln_kd_kab"] = kab_code
    if kab_name: res["pln_nama_kab"] = kab_name
    if kec_code: res["pln_kd_kec"] = kec_code
    if kec_name: res["pln_nama_kec"] = kec_name
    if kel_code: res["pln_kd_kel"] = kel_code
    if kel_name: res["pln_nama_kel"] = kel_name
    
    if answers_map.get("r102e"):
        res["pln_alamat"] = answers_map.get("r102e")
    if answers_map.get("r103"):
        res["pln_nama"] = answers_map.get("r103")
        
    return res

# --- Config ---

def _resolve_pln_url(repo_root=REPO_ROOT, region=None) -> str:
    """PLN API URL source of truth = git-tracked pln_url_<region>.txt (propagated via
    `fasih-update`). Per-wilayah: tiap server punya tunnel URL sendiri. Legacy
    pln_url.txt (single URL, historically Bontang's) is only consulted for the
    default region — any other region without its own file must fail loud to env
    PLN_API_URL instead of silently impersonating Bontang. Region default = get_region()."""
    region = region or get_region()
    candidates = [f"pln_url_{region}.txt"]
    if region == DEFAULT_REGION:
        candidates.append("pln_url.txt")  # legacy fallback only for the default region
    for fname in candidates:
        try:
            with open(os.path.join(repo_root, fname)) as f:
                for line in f:
                    line = line.strip()
                    if line and not line.startswith("#"):
                        return line.rstrip("/")
        except OSError:
            pass
    return os.getenv("PLN_API_URL", "").rstrip("/")

PLN_API_URL = _resolve_pln_url()
PLN_API_KEY = os.getenv("PLN_API_KEY", "")
TOKEN_FILE = os.path.join(REPO_ROOT, "fasih_token.json")

# --- PLN API Client ---

DEFAULT_MAPBOX_TOKEN = base64.b64decode(
    "cGsuZXlKMUlqb2lkbVZ1WkhOaGJXRnlhVzVrWVNJc0ltRWlPaUpqYlhKck4zbDNNbXd5WnpKeU1ubHdjM042WVRWMU56VTJJbjAueGg5VkYxTUhHNkRZcTRReUNJMFN6QSxway5leUoxSWpvaWJHRnNZWFpsYm1Seklpd2lZU0k2SW1OdGNtczJkVzU0Y1RKamJuWXllRzlwTlhCcmR6TTBaSFFpZlEuclJWcERuc0U5alFHeUtJOThqMnExUQ=="
).decode("utf-8")

def apply_region_config() -> str:
    """Ambil token Mapbox dari PLN_API_URL server wilayah (bila ada).

    Supaya HP yang berpindah wilayah (mis. `fasih-region samarinda`) otomatis
    mendapatkan token Mapbox wilayahnya tanpa harus edit .env per HP.
    Kalau server tak punya token / tak bisa dihubungi, .env lokal / fallback tetap dipakai.
    Return: nama akun token yang akhirnya dipakai (buat log), atau "" bila tak ada."""
    current_pln_url = _resolve_pln_url()
    if not current_pln_url:
        tok = os.getenv("MAPBOX_ACCESS_TOKEN", "").strip() or DEFAULT_MAPBOX_TOKEN
        os.environ["MAPBOX_ACCESS_TOKEN"] = tok
        return _mapbox_account(tok)

    keys_to_try = []
    if PLN_API_KEY:
        keys_to_try.append(PLN_API_KEY)
    for k in ["key_samarinda_3e6c882c2eee01a065161a053f8e0a4a", "key_balikpapan_c1bdec7d3a9acb85a5658d1d16f07989", "key_petugas_default"]:
        if k not in keys_to_try:
            keys_to_try.append(k)

    last = ""
    for k in keys_to_try:
        try:
            resp = req_lib.get(f"{current_pln_url}/api/config", headers={"X-API-Key": k}, timeout=8)
            if resp.status_code == 200:
                tok = ((resp.json() or {}).get("mapbox_token") or "").strip()
                if tok:
                    os.environ["MAPBOX_ACCESS_TOKEN"] = tok  # server menang atas .env lokal
                    return _mapbox_account(tok)
                break                       # server jawab tapi tak punya token → fallback
            last = f"HTTP {resp.status_code}"
        except Exception as e:
            last = f"{type(e).__name__}: {str(e)[:120]}"

    tok = os.getenv("MAPBOX_ACCESS_TOKEN", "").strip() or DEFAULT_MAPBOX_TOKEN
    os.environ["MAPBOX_ACCESS_TOKEN"] = tok
    return _mapbox_account(tok)


fetch_region_config = apply_region_config


def _mapbox_account(token: str) -> str:
    """Nama akun di dalam token Mapbox (buat log — jangan pernah cetak tokennya)."""
    accounts = []
    for tok in (token or "").split(","):
        tok = tok.strip()
        if not tok:
            continue
        try:
            p = tok.split(".")[1]
            p += "=" * (4 - len(p) % 4)
            acct = json.loads(base64.urlsafe_b64decode(p.encode())).get("u", "?")
            accounts.append(acct)
        except Exception:
            accounts.append("?")
    return ", ".join(accounts) if accounts else ""


def _resolve_all_pln_urls() -> list[str]:
    urls = []
    reg = get_region(REPO_ROOT)

    # 1. Environment override if set
    if PLN_API_URL:
        urls.append(PLN_API_URL)

    # 2. Check if running under Auto-Runner / Central Master mode (multi-region enabled)
    is_multi_region = os.getenv("FASIH_MULTI_REGION", "").lower() in ("1", "true", "yes")

    if is_multi_region:
        # Auto-Runner Mode: Load ALL regional URL files & ALL fallback IPs across regions
        for fname in ["pln_url_samarinda.txt", "pln_url_balikpapan.txt", "pln_url_bontang.txt", "pln_url_tarakan.txt", "pln_url.txt"]:
            p = os.path.join(REPO_ROOT, fname)
            if os.path.exists(p):
                try:
                    with open(p) as f:
                        for line in f:
                            line = line.strip()
                            if line and not line.startswith("#"):
                                u = line.rstrip("/")
                                if u and u not in urls:
                                    urls.append(u)
                                break
                except Exception:
                    pass
        defaults = ["http://103.126.226.155:8000", "http://103.126.226.156:8000", "http://103.126.226.157:8000"]
        for d in defaults:
            if d not in urls:
                urls.append(d)
    else:
        # HP Petugas Mode: Strictly resolve ONLY the device's configured region
        reg_files = [f"pln_url_{reg}.txt", "pln_url.txt"]
        for fname in reg_files:
            p = os.path.join(REPO_ROOT, fname)
            if os.path.exists(p):
                try:
                    with open(p) as f:
                        for line in f:
                            line = line.strip()
                            if line and not line.startswith("#"):
                                u = line.rstrip("/")
                                if u and u not in urls:
                                    urls.append(u)
                                break
                except Exception:
                    pass
            if urls:
                break

        region_ip_map = {
            "samarinda": "http://103.126.226.155:8000",
            "bontang": "http://103.126.226.156:8000",
            "tarakan": "http://103.126.226.157:8000"
        }
        if reg in region_ip_map:
            ip_url = region_ip_map[reg]
            if ip_url not in urls:
                urls.append(ip_url)

    return urls

PLN_API_URLS = _resolve_all_pln_urls()


# High-performance HTTP Session with connection pooling across workers
_HTTP_SESSION = requests.Session()
adapter = requests.adapters.HTTPAdapter(pool_connections=100, pool_maxsize=100, max_retries=1)
_HTTP_SESSION.mount("http://", adapter)
_HTTP_SESSION.mount("https://", adapter)

# Shared global ThreadPoolExecutor for per-item parallel BPS/PLN fetches (avoids per-item pool creation overhead)
_PIPELINE_EXECUTOR = ThreadPoolExecutor(max_workers=64)

# Fast-path cache for working PLN server URL and API Key
_FAST_PLN_URL: Optional[str] = None
_FAST_PLN_KEY: Optional[str] = None
_DEAD_PLN_URLS: Dict[str, float] = {}  # URL -> expiry_timestamp
_FAST_LOCK = threading.Lock()


def pln_lookup(idpel: str = "", nometer: str = "") -> Optional[dict]:
    """Fetch PLN data from server API with connection pooling, fast working-server caching, and dead tunnel blacklisting."""
    global _FAST_PLN_URL, _FAST_PLN_KEY

    params = {}
    if idpel:
        params["idpel"] = idpel
    if nometer:
        params["nometer"] = nometer
    # Kirim region klien supaya server backup (mis. Bontang menopang balikpapan)
    # memilih FOTO dari pool region ini, bukan pool region server. Server lama /
    # tanpa pool region ini akan MENGABAIKAN param → perilaku lama (aman).
    try:
        params["region"] = get_region()
    except Exception:
        pass

    now = time.time()
    with _FAST_LOCK:
        fast_url, fast_key = _FAST_PLN_URL, _FAST_PLN_KEY

    # 1. Fast Path: Try cached working URL and Key first with (2.0, 8.0) timeout
    if fast_url and fast_key:
        try:
            resp = _HTTP_SESSION.get(f"{fast_url}/api/lookup", params=params, headers={"X-API-Key": fast_key}, timeout=(2.0, 8.0))
            if resp.status_code == 200:
                return resp.json()
        except Exception:
            pass  # Fast cache missed or server temporarily slow, fall through to multi-server lookup

    # 2. Multi-Server & Multi-Key Failover (skipping known dead tunnels)
    urls_to_try = _resolve_all_pln_urls()
    if not urls_to_try:
        return None

    # Filter out dead URLs (blacklisted for 5 min)
    valid_urls = [u for u in urls_to_try if _DEAD_PLN_URLS.get(u, 0) < now]
    if not valid_urls:
        valid_urls = urls_to_try

    keys_to_try = []
    if PLN_API_KEY:
        keys_to_try.append(PLN_API_KEY)
    for k in ["key_samarinda_3e6c882c2eee01a065161a053f8e0a4a", "key_balikpapan_c1bdec7d3a9acb85a5658d1d16f07989", "key_petugas_default"]:
        if k not in keys_to_try:
            keys_to_try.append(k)

    for base_url in valid_urls:
        for k in keys_to_try:
            try:
                resp = _HTTP_SESSION.get(f"{base_url}/api/lookup", params=params, headers={"X-API-Key": k}, timeout=(2.0, 8.0))
                if resp.status_code == 200:
                    with _FAST_LOCK:
                        _FAST_PLN_URL, _FAST_PLN_KEY = base_url, k
                    return resp.json()
                elif resp.status_code in (401, 403):
                    continue  # Key mismatch for this server, try next key
                elif resp.status_code == 404:
                    break    # IDPel not on this server DB, try next server
            except Exception:
                # Blacklist dead tunnel for 5 minutes
                _DEAD_PLN_URLS[base_url] = now + 300.0
                break        # Dead tunnel / connection timeout, skip to next server immediately

    return None




def download_photo(photo_url: str, dest_dir: str) -> Optional[str]:
    """Download photo from server API with multi-server photo pool fallback."""
    if not photo_url:
        return None

    now = time.time()
    with _FAST_LOCK:
        fast_url, fast_key = _FAST_PLN_URL, _FAST_PLN_KEY

    keys_to_try = []
    if fast_key:
        keys_to_try.append(fast_key)
    if PLN_API_KEY and PLN_API_KEY not in keys_to_try:
        keys_to_try.append(PLN_API_KEY)
    for k in ["key_samarinda_3e6c882c2eee01a065161a053f8e0a4a", "key_balikpapan_c1bdec7d3a9acb85a5658d1d16f07989", "key_petugas_default"]:
        if k not in keys_to_try:
            keys_to_try.append(k)

    urls_to_try = []
    if fast_url:
        urls_to_try.append(fast_url)
    for u in PLN_API_URLS:
        if u not in urls_to_try and _DEAD_PLN_URLS.get(u, 0) < now:
            urls_to_try.append(u)

    for base_url in urls_to_try:
        url = f"{base_url}{photo_url}" if photo_url.startswith("/") else photo_url
        for k in keys_to_try:
            try:
                resp = _HTTP_SESSION.get(url, headers={"X-API-Key": k}, timeout=3.0)
                if resp.status_code == 200:
                    ext = ".webp"
                    ct = resp.headers.get("content-type", "")
                    if "jpeg" in ct or "jpg" in ct:
                        ext = ".jpg"
                    elif "png" in ct:
                        ext = ".png"
                    path = os.path.join(dest_dir, f"photo_{int(time.time())}_{random.randint(100,999)}{ext}")
                    with open(path, "wb") as f:
                        f.write(resp.content)
                    return path
                elif resp.status_code in (401, 403):
                    continue
                elif resp.status_code == 404:
                    break
            except Exception as e:
                _DEAD_PLN_URLS[base_url] = now + 300.0
                logger.debug(f"Photo download error on {base_url}: {e}")
                break
    return None



# --- Auth ---

def ensure_login() -> dict:
    """Load or create BPS SSO token."""
    if os.path.exists(TOKEN_FILE):
        try:
            with open(TOKEN_FILE) as f:
                token_data = json.load(f)
            token_data = refresh_token_if_needed(token_data, token_file=TOKEN_FILE, exit_on_failure=False)
            if token_data:
                return token_data
        except Exception:
            logger.warning("Token expired/invalid, prompting fresh login")

    print("\n🔑 Login BPS SSO")
    email = input("  Email BPS: ").strip()
    import getpass
    password = getpass.getpass("  Password BPS: ")
    print(f"  Menghubungi SSO BPS...")
    token_data = perform_login(email, password, exit_on_failure=False)
    if not token_data:
        print("❌ Login gagal.")
        sys.exit(1)
    with open(TOKEN_FILE, "w") as f:
        json.dump(token_data, f, indent=2)
    print("✅ Login berhasil!")
    return token_data


# --- Submit Pipeline ---

def _demangle_name(name: str) -> str:
    """Old AP2T/DIL rows store some names with a space after EVERY letter
    ('A G U S' = AGUS, 'A D U L' = ADUL). If every token is a single char, collapse
    them into one word so it can be masked meaningfully. Normal names pass through
    unchanged. Word boundaries in multi-word mangled names can't be recovered."""
    toks = str(name or "").split()
    if len(toks) > 1 and all(len(t) == 1 for t in toks):
        return "".join(toks)
    return str(name or "")


def _is_prabayar(direct_args: dict) -> bool:
    """Determine product type from PLN API explicit 'produk' field, tarif suffix (T = Prabayar/Token), or prelist."""
    produk = (direct_args.get("produk") or "").strip().upper()
    if "PRABAYAR" in produk or "PREPAID" in produk or "TOKEN" in produk:
        return True
    if "PASCABAYAR" in produk or "POSTPAID" in produk or "REKENING" in produk:
        return False
    tarif = (direct_args.get("tarif") or "").strip().upper()
    if tarif.endswith("T") or "TOKEN" in tarif or "PRABAYAR" in tarif:
        return True
    return False


def _find_template_for_region(open_assignments, pln_data):
    """Find template assignment matching PLN region — mirrors bot logic."""
    if not open_assignments:
        return None
    regions = {(a.get("region") or {}).get("id") for a in open_assignments if (a.get("region") or {}).get("id")}
    if len(regions) == 1:
        return open_assignments[0]
    if pln_data:
        nama_kel = (pln_data.get("nama_kel") or "").lower()
        nama_kec = (pln_data.get("nama_kec") or "").lower()
        for a in open_assignments:
            r_name = (a.get("region") or {}).get("name", "").lower()
            if nama_kel and nama_kel in r_name:
                return a
            if nama_kec and nama_kec in r_name:
                return a
    return open_assignments[0]


# CEK (check-idpln / check-nikpln) is best-effort only. The record registers into
# the FASIH frame from the SUBMIT itself (paradata) — VERIFIED: an item submitted
# while both CEK calls returned 429 still got fasih_exists=true. BPS rate-limits
# these connector endpoints per-account (a 429 can lock an account for hours), so
# once we see a 429 we stop calling them for the rest of the run: submits keep
# working + registering, just without prelist routing / NIK-pemadanan display
# (wilayah still correct — it comes from PLN kd_kel, not CEK).
# Serialize token refreshes so parallel workers never write TOKEN_FILE concurrently.
_token_lock = threading.Lock()

def _cek(fn, *args, skip_cek_idpln: bool = False) -> dict:
    if skip_cek_idpln:
        return {}
    try:
        return fn(*args).get("data") or {}
    except Exception as e:
        msg = str(e)
        if any(t in msg.lower() for t in ("429", "rate_limit_exceeded", "terlampaui", "too many requests")):
            raise Exception(f"429 Rate Limit BPS (CEK IDPel limit terlampaui): {msg}")
        msg_lower = msg.lower()
        if "403" in msg_lower or "forbidden" in msg_lower:
            idpel_str = args[2] if len(args) > 2 else ""
            logger.warning(f"⚠️ CEK IDPel {idpel_str} dilarang (403 Forbidden — beda wilayah/tidak ditugaskan ke akun ini)")
        elif any(t in msg_lower for t in ("timed out", "timeout", "max retries", "connection")):
            logger.warning(f"CEK BPS timeout/koneksi: {e}")
        else:
            logger.warning(f"CEK gagal: {e}")
        return {}


# --fast survey cache: the survey/periode/template/region setup is stable within
# a period, so cache it to disk (per account) and reuse — subsequent --fast runs
# do ZERO BPS fetch (can't time out). create_new still needs a template to clone
# (copyFromId + region), so a handful are cached too.
_SURVEY_CACHE_FILE = os.path.join(REPO_ROOT, ".fasih_survey_cache.json")
_SURVEY_CACHE_TTL = 12 * 3600

def _account_email(token_data: dict) -> str:
    try:
        p = token_data["access_token"].split(".")[1]
        p += "=" * (4 - len(p) % 4)
        j = json.loads(base64.urlsafe_b64decode(p.encode()))
        return j.get("email") or j.get("preferred_username") or ""
    except Exception:
        return ""

def _survey_cache_file_for(email: str) -> str:
    clean = email.strip().lower().replace("@", "_at_").replace(".", "_") if email else "default"
    return os.path.join(REPO_ROOT, f".fasih_survey_cache_{clean}.json")

def _load_survey_cache(email: str = "", ignore_email: bool = False):
    cfile = _survey_cache_file_for(email) if email else _SURVEY_CACHE_FILE
    try:
        if not os.path.exists(cfile) and os.path.exists(_SURVEY_CACHE_FILE):
            cfile = _SURVEY_CACHE_FILE
        with open(cfile) as f:
            c = json.load(f)
        if (ignore_email or c.get("email") == email or not email) and (time.time() - c.get("ts", 0)) < _SURVEY_CACHE_TTL:
            return c.get("survey_caches")
    except Exception:
        pass
    return None

def _save_survey_cache(email: str, survey_caches: dict):
    try:
        cfile = _survey_cache_file_for(email) if email else _SURVEY_CACHE_FILE
        trimmed = {}
        for k, sc in survey_caches.items():
            tv = sc.get("template_version")
            if not tv:
                survey_obj = sc.get("survey") or {}
                lookup = survey_obj.get("templateLookup") or []
                tv = lookup[0].get("templateVersion") if lookup else None
            trimmed[k] = {
                "periode": sc["periode"],
                "template_mapping": sc["template_mapping"],
                "assignments": sc["assignments"],  # Keep all assignments so no target items are lost
                "regions": sc["regions"],
                "template_version": tv,
            }
        with open(cfile, "w") as f:
            json.dump({"email": email, "ts": time.time(), "survey_caches": trimmed}, f)
    except Exception as e:
        logger.warning(f"Gagal simpan cache survei: {e}")


def _adjust(a: bytearray, aOff: int, b: bytes) -> None:
    x = (b[-1] & 0xff) + (a[aOff + len(b) - 1] & 0xff) + 1
    a[aOff + len(b) - 1] = x & 0xff
    x >>= 8
    for i in range(len(b) - 2, -1, -1):
        x += (b[i] & 0xff) + (a[aOff + i] & 0xff)
        a[aOff + i] = x & 0xff
        x >>= 8

def _pkcs12_password_to_bytes(password_str: str) -> bytes:
    if not password_str:
        return b""
    return password_str.encode('utf-16be') + b"\x00\x00"

def _pkcs12_kdf(id_byte: int, n: int, salt: bytes, password_bytes: bytes, iteration_count: int) -> bytes:
    u = 32
    v = 64
    D = bytes([id_byte] * v)
    if salt:
        S_len = v * ((len(salt) + v - 1) // v)
        S = bytearray(S_len)
        for i in range(S_len):
            S[i] = salt[i % len(salt)]
    else:
        S = bytearray()
    if password_bytes:
        P_len = v * ((len(password_bytes) + v - 1) // v)
        P = bytearray(P_len)
        for i in range(P_len):
            P[i] = password_bytes[i % len(password_bytes)]
    else:
        P = bytearray()
    I = bytearray(S + P)
    B = bytearray(v)
    c = (n + u - 1) // u
    dKey = bytearray(n)
    for i in range(1, c + 1):
        h = hashlib.sha256()
        h.update(D)
        h.update(I)
        A = bytearray(h.digest())
        for j in range(1, iteration_count):
            h = hashlib.sha256()
            h.update(A)
            A = bytearray(h.digest())
        for j in range(v):
            B[j] = A[j % len(A)]
        for j in range(len(I) // v):
            _adjust(I, j * v, B)
        if i == c:
            dKey[(i - 1) * u:] = A[:n - (i - 1) * u]
        else:
            dKey[(i - 1) * u : i * u] = A
    return bytes(dKey)

def decrypt_legacy_bc(str_to_decrypt: str, passphrase: str) -> str:
    from Crypto.Cipher import AES
    from Crypto.Util.Padding import unpad
    parts = str_to_decrypt.split('#')
    ciphertext_b64 = parts[0]
    salt_b64 = parts[2]
    iv_b64 = parts[3]
    salt_raw = base64.b64decode(salt_b64)
    iv = base64.b64decode(iv_b64)
    ciphertext = base64.b64decode(ciphertext_b64)
    salt_str = salt_raw.decode('utf-8', errors='replace')
    salt = salt_str.encode('utf-8')
    pwd_bytes = _pkcs12_password_to_bytes(passphrase)
    key = _pkcs12_kdf(1, 32, salt, pwd_bytes, 11000)
    cipher = AES.new(key, AES.MODE_CBC, iv=iv)
    plaintext_bytes = cipher.decrypt(ciphertext)
    return unpad(plaintext_bytes, 16).decode('utf-8')

def fetch_and_decrypt_original(headers: dict, assignment_id: str, survey_period_id: str, base_path: str, region_key_bytes: bytes = None) -> dict:
    import subprocess
    from fasih_crypto import decrypt_gcm_verify
    filename = base_path.split('/')[-1]
    url = f"https://fasih-survey.bps.go.id/mobile/assignment-sync/api/mobile/s3/assignment/presign-url?surveyPeriodId={survey_period_id}"
    body = [{'assignmentId': assignment_id, 'copyFromId': None, 'fileNames': [filename]}]
    resp = req_lib.post(url, headers=headers, json=body, timeout=30).json()
    urls = resp.get("data", [])
    if isinstance(urls, dict):
        urls = urls.get("presignedUrls", [])
    elif isinstance(urls, list) and urls and "presignedUrls" in urls[0]:
        urls = urls[0]["presignedUrls"]
    get_url = urls[0].get("presignedUrl") or urls[0].get("url") if urls else None
    if not get_url:
        raise ValueError("Presigned URL download original tidak ditemukan")
    
    temp_dir = tempfile.mkdtemp(prefix=f"download_{assignment_id}_")
    archive_path = os.path.join(temp_dir, filename)
    try:
        with req_lib.get(get_url, stream=True) as r:
            r.raise_for_status()
            with open(archive_path, 'wb') as f:
                for chunk in r.iter_content(chunk_size=8192):
                    f.write(chunk)
        
        extract_path = os.path.join(temp_dir, 'extracted')
        os.makedirs(extract_path, exist_ok=True)
        subprocess.run(['7z', 'x', archive_path, f'-o{extract_path}'], stdout=subprocess.DEVNULL, check=True)
        
        data_json_path = os.path.join(extract_path, assignment_id, 'data.json')
        content = open(data_json_path, 'r').read()

        if region_key_bytes:
            try:
                decrypted = decrypt_gcm_verify(content, region_key_bytes)
                return json.loads(decrypted)
            except Exception:
                pass
        
        try:
            key_bytes = base64.b64decode('sdbo2YDCr6nabprPpUf3vvCQjuKwuE7t5ppr4sdAjHk=')
            decrypted = decrypt_gcm_verify(content, key_bytes)
            return json.loads(decrypted)
        except Exception:
            pass
            
        try:
            passphrase = 'Z!,vDKUPv;.Jy0Q4Eq1wVCY-a_!GnT'
            decrypted = decrypt_legacy_bc(content, passphrase)
            return json.loads(decrypted)
        except Exception:
            pass

        raise ValueError("Gagal mendeskripsi data original (GCM & CBC fail)")
    finally:
        shutil.rmtree(temp_dir, ignore_errors=True)
        if os.path.exists(temp_dir):
            shutil.rmtree(temp_dir)


def submit_single(
    token_data: dict,
    val: str,
    survey_caches: dict,
    dry_run: bool = False,
    temp_dir: str = "",
    force: bool = False,
    resubmit_all: bool = False,
    resubmit_reject: bool = False,
    resubmit_open: bool = False,
    resubmit_reopen: bool = False,
    skip_cek_idpln: bool = False,
    override_lat: Optional[float] = None,
    override_lon: Optional[float] = None,
    override_keperluan: Optional[str] = None,
) -> tuple[bool, str]:
    """Submit single item — picks correct survey (Prabayar/Pascabayar) automatically."""
    email_val = (token_data or {}).get("email") or (token_data or {}).get("preferred_username") or ""
    email_tag = f" via {email_val}" if email_val else ""
    # Own a scratch dir when the caller passes none (auto-runner path) so the
    # downloaded photo + the .7z archive land in a temp dir we clean up, not CWD.
    # Callers that pass temp_dir (fasih-submit-batch) keep managing their own dir.
    _owns_temp = not temp_dir
    if _owns_temp:
        temp_dir = tempfile.mkdtemp(prefix="fasih_submit_")
    try:
        # Use the provided token_data directly — do NOT read from global fasih_token.json
        # (auto-runner passes per-account tokens; reading from disk would mix accounts)
        if not is_token_valid(token_data):
            with _token_lock:
                # Re-check after acquiring lock (another thread may have refreshed it)
                if not is_token_valid(token_data):
                    token_data = refresh_token_if_needed(token_data, token_file=None, exit_on_failure=False)
        headers = get_headers(token_data)

        # Guard against empty survey caches (accounts with 0 assignments/surveys)
        if not survey_caches:
            return False, "❌ Akun BPS ini tidak memiliki survey aktif (0 survey / belum ditugaskan sampel)."

        # Early validation: skip invalid IDPel / NoMeter (must be 8-15 characters)
        val_clean = (val or "").strip()
        if not val_clean or len(val_clean) < 8 or len(val_clean) > 15:
            return True, f"⚠️ IDPel / NoMeter '{val_clean}' tidak valid (panjang < 8 atau > 15 karakter) — dilewati."

        # Determine idpel vs nometer
        is_idpel = len(val_clean) == 12
        idpel_val = val_clean if is_idpel else ""
        nometer_val = "" if is_idpel else val_clean

        # Search ALL surveys' assignments for existing match
        target = None
        matched_key = None
        create_new = False
        template_assignment_id = None

        # Collect all matches across all surveys
        matches = []
        for skey, sc in survey_caches.items():
            tm = sc["template_mapping"]
            idpel_slot = next((s for s, v in tm.items() if v == "r101a"), "data3")
            nometer_slot = next((s for s, v in tm.items() if v == "r101b"), "data1")

            # Fast O(1) hash map lookup if available
            idpel_map = sc.get("assignment_by_idpel")
            if idpel_map and val_clean in idpel_map:
                matches.append((skey, sc, idpel_map[val_clean]))
            else:
                for a in sc["assignments"]:
                    v_idpel = (a.get(idpel_slot) or "").strip()
                    v_nometer = (a.get(nometer_slot) or "").strip()
                    if v_idpel == val_clean or v_nometer == val_clean:
                        matches.append((skey, sc, a))

        # Select the best target based on mode and responsibility status
        target = None
        matched_key = None
        if matches:
            def get_resp_date(a):
                resp_list = a.get("assignmentResponsibility") or []
                if not resp_list:
                    return 0
                max_ts = 0
                for r in resp_list:
                    dt_str = r.get("dateCreated")
                    if dt_str:
                        try:
                            dt_str_clean = " ".join(dt_str.split())
                            dt = datetime.strptime(dt_str_clean, "%b %d, %Y, %I:%M:%S %p")
                            max_ts = max(max_ts, dt.timestamp())
                        except Exception:
                            try:
                                dt = datetime.strptime(dt_str_clean, "%B %d, %Y, %I:%M:%S %p")
                                max_ts = max(max_ts, dt.timestamp())
                            except Exception:
                                pass
                return max_ts

            # Sort matches so the newest created responsibility is prioritized
            matches.sort(key=lambda m: get_resp_date(m[2]), reverse=True)

            def has_active_resp(a):
                resp_list = a.get("assignmentResponsibility") or []
                if not resp_list:
                    return True
                return any(r.get("isActive") and r.get("assignmentResponsibilityStatusId") != "DONE" for r in resp_list)

            active_resp_matches = [m for m in matches if has_active_resp(m[2])]
            working_matches = active_resp_matches if active_resp_matches else matches

            if resubmit_reject:
                reject_matches = [m for m in working_matches if "REJECT" in (m[2].get("assignmentStatusAlias") or "").upper()]
                if not reject_matches:
                    reject_matches = [m for m in working_matches if "SUBMITTED" in (m[2].get("assignmentStatusAlias") or "").upper()]
                matched_key, sc, target = reject_matches[0] if reject_matches else working_matches[0]
            elif resubmit_open:
                open_matches = [m for m in working_matches if "OPEN" in (m[2].get("assignmentStatusAlias") or "").upper()]
                matched_key, sc, target = open_matches[0] if open_matches else working_matches[0]
            elif resubmit_reopen:
                reopen_matches = [m for m in working_matches if "REOPEN" in (m[2].get("assignmentStatusAlias") or "").upper()]
                matched_key, sc, target = reopen_matches[0] if reopen_matches else working_matches[0]
            else:
                active_matches = [m for m in working_matches if not any(x in (m[2].get("assignmentStatusAlias") or "").upper() for x in ["SUBMIT", "DONE", "APPROV"])]
                matched_key, sc, target = active_matches[0] if active_matches else working_matches[0]


        direct_args = {
            "idpel": idpel_val, "nometer": nometer_val,
            "nama": "PELANGGAN BARU", "alamat": "",
            "tarif": "R-1", "daya": "900",
            "hasil": "1. Berhasil didata",
            "kelurahan": "001", "kdpm": "01", "kddk": "1", "status_dil": "1",
        }

        local_submitted = False
        if target:
            sc = survey_caches[matched_key]
            tv = sc.get("template_version")
            if not tv:
                survey_obj = sc.get("survey") or {}
                lookup = survey_obj.get("templateLookup") or []
                tv = lookup[0].get("templateVersion") if lookup else None
            if tv:
                target["templateVersion"] = tv
            tm = sc["template_mapping"]
            i_slot = next((s for s, v in tm.items() if v == "r101a"), "data3")
            n_slot = next((s for s, v in tm.items() if v == "r101b"), "data1")
            status_alias = target.get("assignmentStatusAlias") or ""
            if ("SUBMITTED" in status_alias or "DONE" in status_alias or "APPROVED" in status_alias) and not resubmit_reject:
                # Already-submitted local record. Whether to skip (already tercatat)
                # or re-register (belum) is decided by the ONE global fasih_exists
                # guard below (single check-idpln) — drop the target so create_new runs.
                chk_idpel = (target.get(i_slot) or idpel_val or "").strip()
                if chk_idpel:
                    idpel_val = chk_idpel
                local_submitted = True
                target = None
                matched_key = None
            if target:
                direct_args["nama"] = target.get("data2", "") or "PELANGGAN"
                direct_args["alamat"] = target.get("data4", target.get("data5", "")) or ""
                idpel_val = target.get(i_slot) or idpel_val
                nometer_val = target.get(n_slot) or nometer_val
                direct_args["idpel"] = idpel_val
                direct_args["nometer"] = nometer_val

        # RESUBMIT-REJECT: only ever touch a record that is currently REJECTED, and
        # bind to ITS OWN existing assignment id (target kept above — a REJECTED alias
        # is not dropped by the SUBMITTED/DONE/APPROVED guard). Anything else (already
        # OPEN/SUBMITTED, or not found) is left alone — never create a new/duplicate row.
        if resubmit_reject:
            status_alias = (target or {}).get("assignmentStatusAlias") or ""
            if not target or ("REJECT" not in status_alias.upper() and "SUBMITTED" not in status_alias.upper()):
                return True, "Bukan data REJECT / SUBMITTED (status berubah / tak ketemu) — dilewati."

        # RESUBMIT-OPEN / RESUBMIT-REOPEN: touch OPEN or PERNAH DIBUKA records.
        if resubmit_open or resubmit_reopen:
            status_alias = (target or {}).get("assignmentStatusAlias") or ""
            alias_u = status_alias.upper()
            if not target or ("OPEN" not in alias_u and "PERNAH DIBUKA" not in alias_u):
                return True, "Bukan data OPEN / PERNAH DIBUKA (status berubah / tak ketemu) — dilewati."



        # For --resubmit-reject, assignment ID is already known and REJECTED on BPS so check-idpln is automatically skipped to save BPS daily quota.
        # --resubmit-open / --resubmit-reopen STILL perform check-idpln to check BPS status (unless user explicitly passed --no-cek).




        if resubmit_reject:
            skip_cek_idpln = True

        # FAST-PATH: If target assignment is already loaded in local survey assignments, skip redundant check-idpln HTTP GET call
        if target and not resubmit_all and not resubmit_open and not resubmit_reopen and not resubmit_reject:
            skip_cek_idpln = True

        # NEW OPTIMIZATION: If we already know the item is submitted/done locally, skip check-idpln to save BPS quota
        if local_submitted and not resubmit_all:
            skip_cek_idpln = True

        import uuid
        from concurrent.futures import ThreadPoolExecutor as _TPE
        aid = target.get("id") if target else str(uuid.uuid4())
        lat, lon = override_lat, override_lon
        pln_data = None
        photo_path = None

        def _do_pln_lookup():
            for attempt in range(1, 3):
                result = pln_lookup(idpel=idpel_val, nometer=nometer_val)
                if result:
                    return result
                if attempt < 2:
                    time.sleep(0.1)
            return None

        # PARALLEL PIPELINE: Run BPS check_idpln and PLN lookup concurrently using shared global executor
        fut_cek = _PIPELINE_EXECUTOR.submit(lambda: _cek(check_idpln, headers, aid, idpel_val) if (idpel_val and not skip_cek_idpln) else None)
        fut_pln = _PIPELINE_EXECUTOR.submit(_do_pln_lookup)
        d_idpln = fut_cek.result()
        if d_idpln and d_idpln.get("fasih_exists") and not resubmit_all and not resubmit_reject and not resubmit_open and not resubmit_reopen:
            return True, "Sudah TERCATAT di FASIH — skip (anti-dupe)."
        pln_data = fut_pln.result()

        # GUARD: never submit without valid PLN data.
        if not pln_data:
            return False, "❌ Data PLN tidak ditemukan setelah 3x percobaan (cek fasih-status). Item dilewati agar tidak kirim data placeholder."

        # BLOK III region validation
        def _kel_ok(d):
            k = str((d or {}).get("kd_kel") or "").strip()
            return len(k) == 10 and k.isdigit()
        if not _kel_ok(pln_data):
            pln_data = pln_lookup(idpel=idpel_val, nometer=nometer_val) or pln_data
        if not _kel_ok(pln_data):
            return False, "❌ Region PLN tak lengkap (kd_kel kosong — BLOK III bakal blank) — dilewati, coba lagi. Kalau sering: turunkan --workers (tunnel PLN overload)."

        # Check tarif filter: BPS FASIH is exclusively for Residential / Rumah Tangga (Tarif type "R")
        tarif_val = str(pln_data.get("tarif") or "").strip().upper()
        if tarif_val and "R" not in tarif_val:
            return False, f"❌ Tarif Non-Rumah Tangga ({tarif_val}) — dilarang di-input ke BPS FASIH (hanya tarif tipe R)"

        # Extract PLN data fields into direct_args
        if pln_data:
            pln_nama = pln_data.get("nama") or ""
            if pln_nama and pln_nama.upper() != "NONAME":
                direct_args["pln_nama"] = pln_nama
                direct_args["nama"] = pln_nama
            if pln_data.get("alamat"):
                direct_args["pln_alamat"] = pln_data["alamat"]
                direct_args["alamat"] = pln_data["alamat"]
            if pln_data.get("nik"):
                direct_args["pln_nik"] = pln_data["nik"]
                direct_args["nik"] = pln_data["nik"]
            if pln_data.get("tarif"):
                direct_args["tarif"] = pln_data["tarif"]
            if pln_data.get("produk"):
                direct_args["produk"] = pln_data["produk"]
            if pln_data.get("daya"):
                direct_args["daya"] = str(pln_data["daya"])
            if pln_data.get("idpel"):
                idpel_val = pln_data["idpel"]
                direct_args["idpel"] = idpel_val
            if pln_data.get("nometer"):
                nometer_val = pln_data["nometer"]
                direct_args["nometer"] = nometer_val
            if pln_data.get("keperluan"):
                direct_args["keperluan"] = pln_data["keperluan"]
            for k in ("kd_prov", "kd_kab", "kd_kec", "kd_kel",
                       "nama_prov", "nama_kab", "nama_kec", "nama_kel", "keperluan"):
                if pln_data.get(k):
                    direct_args[f"pln_{k}"] = pln_data[k]
            if override_keperluan:
                direct_args["keperluan"] = override_keperluan
                direct_args["pln_keperluan"] = override_keperluan
            try:
                pln_lat = pln_data.get("latitude")
                pln_lon = pln_data.get("longitude")
                if lat is None and pln_lat and float(pln_lat) != 0.0:
                    lat = float(pln_lat)
                if lon is None and pln_lon and float(pln_lon) != 0.0:
                    lon = float(pln_lon)
            except (ValueError, TypeError):
                pass

            # Download photo (uses fast 5s timeout)
            if pln_data.get("photo_url"):
                photo_path = download_photo(pln_data["photo_url"], temp_dir)

        # Step 5b: CEK IDPel — reuse the early result; only CEK now if the idpel was
        # just resolved via PLN (nometer input) and not already checked or skipped.
        if d_idpln is None and idpel_val and not skip_cek_idpln:
            try:
                d_idpln = _cek(check_idpln, headers, aid, idpel_val, skip_cek_idpln=skip_cek_idpln)
            except Exception as e:
                err_msg = str(e)
                if any(k in err_msg.lower() for k in ("429", "rate_limit_exceeded", "terlampaui")):
                    return False, f"❌ BPS Limit 429: {err_msg}"
                logger.warning(f"CEK IDPel error: {e}")
                d_idpln = {}
        if d_idpln is None:
            d_idpln = {}
        prelist = (d_idpln.get("prelist_source") or "").strip().upper()
        direct_args["idpln_response"] = d_idpln

        if d_idpln and not d_idpln.get("exists"):
            logger.warning(f"CEK IDPel {idpel_val}{email_tag}: exists=false di BPS")

        # DEDUP GUARD (global, anti-dupe) — the single check-idpln above is the source
        # of truth for "already registered in FASIH". Skip anything tercatat unless the
        # user explicitly forces a rebuild (--resubmit-all, e.g. to fix region/BLOK III).
        fasih_exists = d_idpln.get("fasih_exists")
        if fasih_exists and not resubmit_all and not resubmit_reject and not resubmit_open and not resubmit_reopen:
            return True, "Sudah TERCATAT di FASIH — skip (anti-dupe)."
        # CEK unavailable (--no-cek / 429) AND a local SUBMITTED record exists, plain
        # mode: don't blind-resubmit (would dupe). --force asserts it's belum → proceed.
        if fasih_exists is None and local_submitted and not force and not resubmit_all:
            return True, "Sudah terkirim (lokal, CEK off) — skip. Pakai --force untuk re-register."
        if local_submitted:
            logger.info(f"Re-register {idpel_val}: belum tercatat → create_new")

        if not target:
            # No existing assignment — route by BPS prelist_source, else PLN produk
            if prelist in ("PRABAYAR", "PASCABAYAR") and prelist in survey_caches:
                matched_key = prelist
            elif _is_prabayar(direct_args) and "PRABAYAR" in survey_caches:
                matched_key = "PRABAYAR"
            elif not _is_prabayar(direct_args) and "PASCABAYAR" in survey_caches:
                matched_key = "PASCABAYAR"
            else:
                matched_key = next(iter(survey_caches))

            create_new = True
            sc = survey_caches[matched_key]
            all_assigns = sc["assignments"]
            open_assigns = [a for a in all_assigns
                            if "OPEN" in (a.get("assignmentStatusAlias") or "")]
            template_pool = open_assigns or all_assigns
            if not template_pool:
                # Account has 0 assignments of ANY status in its loaded cache.
                # Fall back to searching for assignments from other cached accounts on disk,
                # preferring OPEN but accepting any status in the SAME region.
                import glob
                fallback_pool = []
                for cpath in glob.glob(os.path.join(REPO_ROOT, ".fasih_survey_cache_*.json")):
                    try:
                        with open(cpath) as cf:
                            cdata = json.load(cf)
                        f_caches = cdata.get("survey_caches") or {}
                        if matched_key in f_caches:
                            f_assigns = f_caches[matched_key].get("assignments") or []
                            f_open = [a for a in f_assigns if "OPEN" in (a.get("assignmentStatusAlias") or "")]
                            if f_open:
                                fallback_pool = f_open
                                break
                            elif f_assigns and not fallback_pool:
                                fallback_pool = f_assigns
                    except Exception:
                        pass

                if not fallback_pool and os.path.exists(_SURVEY_CACHE_FILE):
                    try:
                        with open(_SURVEY_CACHE_FILE) as cf:
                            cdata = json.load(cf)
                        f_caches = cdata.get("survey_caches") or {}
                        if matched_key in f_caches:
                            f_assigns = f_caches[matched_key].get("assignments") or []
                            f_open = [a for a in f_assigns if "OPEN" in (a.get("assignmentStatusAlias") or "")]
                            fallback_pool = f_open or f_assigns
                    except Exception:
                        pass

                template_pool = fallback_pool

            if not template_pool:
                return False, f"❌ Akun BPS ini ({email_user}) belum memiliki tugas/assignment di survey {matched_key} (0 tugas). Admin BPS harus menugaskan minimal 1 sampel ke akun ini di Web Monitoring BPS."
            template_assignment = _find_template_for_region(template_pool, pln_data)
            target = build_new_assignment_target(
                template_assignment, idpel_val, nometer_val, sc["template_mapping"])
            target["id"] = aid  # same id used for CEK, mirrors the app
            # data2 = plaintext quick-view slot shown in the app / to Pengawas → MASK
            # the name (like the official FASIH app). The REAL name stays in the
            # encrypted archive (r103), so BPS validation vs the DIL is unaffected.
            target["data2"] = mask_pii_name(direct_args.get("nama") or "")
            target["data4"] = direct_args.get("alamat") or ""
            target["data5"] = direct_args.get("alamat") or ""

        # Resolve active survey cache
        sc = survey_caches[matched_key]
        cached_template_mapping = sc["template_mapping"]
        cached_regions = sc["regions"]
        pid = sc["periode"]["id"]

        # Step 8 prep: Resolve region key early for decryption & encryption
        target_region = target.get("region")
        if isinstance(target_region, dict):
            region_id = target_region.get("id") or target_region.get("region_id") or ""
        else:
            region_id = str(target_region or target.get("region_id") or target.get("regionId") or "")

        wrapped_key = None
        if cached_regions:
            for r in cached_regions:
                r_id = r.get("region_id") or r.get("id") or (r.get("region") or {}).get("id")
                if r_id and r_id == region_id:
                    wrapped_key = r.get("wrappedDatakey")
                    break
            if not wrapped_key and len(cached_regions) > 0:
                wrapped_key = cached_regions[0].get("wrappedDatakey")

        key_bytes = None
        if wrapped_key:
            try:
                kb = base64.b64decode(wrapped_key.encode("utf-8"))
                if len(kb) in (16, 24, 32):
                    key_bytes = kb
            except Exception:
                pass

        if not key_bytes:
            key_bytes = hashlib.sha256(STATIC_LEGACY_KEY.encode("utf-8")).digest()


        # RESUBMIT-REJECT / RESUBMIT-OPEN: download & decrypt original archive using region key
        orig_data = None
        if resubmit_reject or resubmit_open or resubmit_reopen:
            bp = target.get("basePath")
            if bp:
                logger.info(f"Mengunduh arsip original dari S3 untuk verifikasi data...")
                try:
                    orig_data = fetch_and_decrypt_original(headers, target["id"], pid, bp, key_bytes)
                    logger.info("Berhasil memverifikasi arsip original S3. Menggunakan stempel waktu jam kerja aktif hari ini.")
                    # Inject original region overrides to maintain consistency
                    orig_overrides = extract_regions_from_orig(orig_data)
                    for k, v in orig_overrides.items():
                        direct_args[k] = v
                except Exception as ex:
                    logger.warning(f"Gagal memuat arsip original dari S3: {ex}. Lanjut resubmit.")
            
            # Template version MUST match the survey's CURRENT template (what BPS
            # validates against NOW), not the archived record's version. An old 0.5.9
            # record resubmitted to a now-0.6.7 survey with tv=0.5.9 = "data corrupt /
            # beda versi template" (app can't render it). So: survey version FIRST,
            # the original record's version only as a last-resort fallback.
            tv = None
            if sc:
                tv = sc.get("template_version") or \
                     ((sc.get("survey") or {}).get("templateLookup") or [{}])[0].get("templateVersion")
            if not tv and orig_data:
                tv = orig_data.get("templateVersion")
            if tv:
                target["templateVersion"] = tv
            status_alias = target.get("assignmentStatusAlias") or ""
            pd_str = target.get("preDefinedData")
            if pd_str and "REJECT" in status_alias:
                target["isForceSubmit"] = False

            direct_args["idpel"] = idpel_val
            direct_args["nometer"] = nometer_val






        # CEK NIK (pemadanan) — companion verification, best-effort
        # Skip when target exists (fast path) — check_nikpln is not required for submission
        nik_val = direct_args.get("nik") or ""
        nikpln_data = {}
        if nik_val and not skip_cek_idpln:
            nikpln_data = _cek(check_nikpln, headers, aid, nik_val, skip_cek_idpln=skip_cek_idpln)
            if nikpln_data and not nikpln_data.get("exists"):
                logger.warning(f"CEK NIK {nik_val} (IDPel: {idpel_val}{email_tag}): exists=false (tidak padan) di BPS")


        # Step 6: Build answers
        answers = build_dynamic_answers(target, direct_args, cached_template_mapping)
        # Feed the real NIK pemadanan result into the archive so BPS shows the NIK
        # as matched (hasilPemadananNIK/no_kk/result_callnik) instead of "tidak ditemukan"
        answers["_nikpln"] = nikpln_data

        # Step 7: Photo upload
        if photo_path and os.path.exists(photo_path):
            tid = target.get("id")
            filename = f"{tid}__r106__c.jpg"
            md5_b64 = compute_md5_base64(photo_path)
            try:
                resp = request_photo_presign_put(
                    headers, tid, target.get("copyFromId") or "",
                    target.get("surveyPeriodId"), filename,
                    os.path.getsize(photo_path), md5_b64
                )
                if resp.get("success"):
                    urls = resp.get("data", [])
                    put_url = urls[0].get("presignedUrls", [])[0].get("presignedUrl") if urls else None
                    if put_url and not dry_run:
                        upload_photo_to_s3(put_url, photo_path, md5_b64)

                    resp_get = request_photo_presign_get(
                        headers, tid, target.get("copyFromId") or "",
                        target.get("surveyPeriodId"), filename
                    )
                    get_data = resp_get.get("data", [])
                    get_url = get_data[0].get("presignedUrls", [])[0].get("presignedUrl") if get_data else ""
                    answers["r106"] = json.dumps({
                        "filename": filename,
                        "uri": f"content://media/external/images/media/{hashlib.md5(tid.encode()).hexdigest()[:8]}",
                        "url": get_url
                    }, ensure_ascii=False)
            except Exception as e:
                logger.debug(f"Photo upload optional step: {e}")

        # Coordinates — PLN coords → target coords → Mapbox admin geocode (cached per
        # idpel so re-submits never move the pin). resolve_coordinate handles the whole
        # ladder + freezes the result; no ngawur province-centroid fling anymore.
        if lat is None or lon is None:
            t_lat, t_lon = target.get("latitude"), target.get("longitude")
            try:
                if t_lat and t_lon and float(t_lat) != 0.0:
                    lat, lon = float(t_lat), float(t_lon)
                else:
                    raise ValueError
            except (ValueError, TypeError):
                pass

        if lat is None or lon is None:
            _pd = pln_data or {}
            nama_kel = direct_args.get("pln_nama_kel", "") or _pd.get("nama_kel", "")
            nama_kec = direct_args.get("pln_nama_kec", "") or _pd.get("nama_kec", "")
            nama_kab = direct_args.get("pln_nama_kab", "") or _pd.get("nama_kab", "")
            nama_prov = direct_args.get("pln_nama_prov", "") or _pd.get("nama_prov", "")
            lat, lon = resolve_coordinate(
                idpel_val, direct_args.get("alamat", ""),
                nama_kel, nama_kec, nama_kab, nama_prov,
            )

        answers["r105"] = {
            "coordinat": {"latitude": lat, "longitude": lon},
            "remark": "", "accuracy": 10.0
        }

        user_name = "Petugas"
        try:
            payload_b64 = token_data["access_token"].split(".")[1]
            payload_b64 += "=" * (4 - len(payload_b64) % 4)
            jwt_payload = json.loads(base64.urlsafe_b64decode(payload_b64.encode("utf-8")))
            user_name = jwt_payload.get("name") or jwt_payload.get("email") or "Petugas"
        except Exception:
            pass

        # UNIVERSAL: every path (create_new, OPEN match, resubmit) must carry the
        # survey's CURRENT template version so wrap_answers picks the right schema.
        # Missing it → wrap_answers defaults to 0.5.9 → 0.5.9 data on a 0.6.7 survey =
        # "data corrupt / opens empty" in the FASIH app (the exact bug reported).
        try:
            _survey_tv = sc.get("template_version") or \
                ((sc.get("survey") or {}).get("templateLookup") or [{}])[0].get("templateVersion")
            if _survey_tv:
                target["templateVersion"] = _survey_tv
        except Exception:
            pass

        if orig_data and isinstance(orig_data.get("answers"), list):
            # 1. Flatten the original answers
            flat_orig = {}
            for a in orig_data["answers"]:
                if isinstance(a, dict) and "dataKey" in a:
                    k = a["dataKey"]
                    v = a.get("answer")
                    if k != "r105" and isinstance(v, list) and v and isinstance(v[0], dict) and "label" in v[0]:
                        flat_orig[k] = v[0]["label"]
                    else:
                        flat_orig[k] = v
            if "createdAt" in orig_data:
                flat_orig["createdAt"] = orig_data["createdAt"]
            if "createdBy" in orig_data:
                flat_orig["createdBy"] = orig_data["createdBy"]
            # 1.5. Refresh photo URL if present to avoid expired signatures
            r106_val = flat_orig.get("r106")
            if r106_val:
                try:
                    if isinstance(r106_val, str):
                        r106_data = json.loads(r106_val)
                    else:
                        r106_data = r106_val
                    if isinstance(r106_data, list) and r106_data:
                        photo_obj = r106_data[0]
                    elif isinstance(r106_data, dict):
                        photo_obj = r106_data
                    else:
                        photo_obj = None
                    
                    if photo_obj and photo_obj.get("filename"):
                        filename = photo_obj["filename"]
                        tid = target.get("id")
                        resp_get = request_photo_presign_get(
                            headers, tid, target.get("copyFromId") or "",
                            target.get("surveyPeriodId"), filename
                        )
                        get_data = resp_get.get("data", [])
                        get_url = get_data[0].get("presignedUrls", [])[0].get("presignedUrl") if get_data else ""
                        if get_url:
                            photo_obj["url"] = get_url
                            flat_orig["r106"] = [photo_obj]
                except Exception as e:
                    logger.debug(f"Failed to refresh photo presigned GET URL: {e}")

            # 2. Merge new answers
            flat_orig["idpln_response"] = d_idpln
            preserved_keys = {
                "r101a", "r101b", "r102a", "r102b", "r102c", "r102d", "r102e", "r103",
                "result_idpln", "hasilCheckIdPel", "hasilCheckIdPel2"
            }
            for k, v in answers.items():
                if k in preserved_keys and flat_orig.get(k):
                    continue
                flat_orig[k] = v

            # Prefer the REAL name from PLN/AP2T over the archived one. Old rejects
            # often carry a mangled single-char name (e.g. "A D U L") in r103 — replace
            # it with the authoritative AP2T name: r103 = real full name (archive),
            # data2 = masked (app/Pengawas quick-view).
            _real_nama = clean_pln_name(direct_args.get("pln_nama") or direct_args.get("nama") or "")
            if _real_nama and _real_nama.upper() != "NONAME":
                # r103 (archive) keeps the AP2T value verbatim → matches BPS's DIL for
                # validation (resubmit with a spaced name is proven accepted). data2
                # (display) uses the DE-MANGLED + masked form so Pengawas sees a proper
                # masked name ('A G U S' → 'AGUS' → 'A**S') instead of 'A G U S'.
                flat_orig["r103"] = _real_nama
                target["data2"] = mask_pii_name(_demangle_name(_real_nama))

            # 3. Wrap using wrap_answers to get perfectly structured BPS payload
            wrapped = wrap_answers(flat_orig, target, user_name)
            payload_to_encrypt = wrapped
            principal_data = build_principal_json(flat_orig, target, user_name)
        else:
            # Normal create_new: run the raw answers through wrap_answers too, so the
            # payload matches the survey's CURRENT template schema (0.6.7 = 36-field
            # prabayar / 37 pasca with per-item timestamps). Previously this path sent
            # build_dynamic_answers' 0.5.9-shaped dict verbatim → 0.5.9 data on a 0.6.7
            # survey = "data corrupt / opens empty" in the app. wrap_answers is
            # version-aware (falls back to the 0.5.9 layout when tv is 0.5.9).
            payload_to_encrypt = wrap_answers(answers, target, user_name)
            principal_data = build_principal_json(answers, target, user_name)

        encrypted = stage_and_encrypt(payload_to_encrypt, key_bytes, target, user_name)

        # Step 9: Archive + upload
        archive_path = create_7z_archive(encrypted, target["id"], temp_dir, principal_data)

        status_alias = target.get("assignmentStatusAlias") or ""

        # If the assignment already has a submitted S3 archive (basePath is present),
        # we must use /edit path to update it. Otherwise, use /submit.
        is_edit = (bool(target.get("basePath")) or "SUBMITTED" in status_alias.upper()) and not resubmit_reject and "REJECT" not in status_alias.upper()
        copy_from_id = (target.get("copyFromId") or target.get("id")) if (is_edit or target.get("isNew")) else None
        # Match the FASIH app exactly: archive filename carries a submit-time epoch
        # ms suffix ({id}_{epochms}.7z). BPS's registration pipeline keys off this
        # format; a plain {id}.7z lands as a record but never registers into the
        # FASIH frame (check-idpln fasih_exists stays false = "belum tercatat").
        arc_filename = f"{target['id']}_{int(time.time() * 1000)}.7z"
        presign_resp = request_presign_url(
            headers, target["id"], pid,
            [arc_filename], is_edit, copy_from_id
        )
        data_obj = presign_resp.get("data", {})
        if isinstance(data_obj, list):
            urls = data_obj
        elif isinstance(data_obj, dict):
            urls = data_obj.get("presignedUrls", [])
        else:
            urls = []
        put_url = urls[0].get("presignedUrl") or urls[0].get("url") if urls else None

        if not put_url:
            return False, "Presigned URL kosong dari BPS."

        if not dry_run:
            upload_to_s3(put_url, archive_path)

        archive_md5 = compute_md5(archive_path)

        # Step 10: Confirm submit
        data_slots = {}
        for i in range(1, 11):
            key = f"data{i}"
            data_slots[key] = str(target.get(key) or "")
        data_slots["data7"] = "1. Berhasil didata"
        # data2 = plaintext nama quick-view (app / Pengawas). On reject/resubmit the
        # slot is inherited from the existing BPS record — often UNMASKED (old 0.5.9
        # submit) → re-mask it like the app. Idempotent: skip if already masked (has
        # '*'), so a create_new value already masked upstream isn't double-masked.
        if data_slots.get("data2") and "*" not in data_slots["data2"]:
            data_slots["data2"] = mask_pii_name(data_slots["data2"])





        start_time_str = None
        end_time_str = None
        if isinstance(payload_to_encrypt, dict):
            for a in payload_to_encrypt.get("answers") or []:
                if a.get("dataKey") == "mulai":
                    start_time_str = a.get("answer")
                elif a.get("dataKey") == "selesai":
                    end_time_str = a.get("answer")

        params = {
            "surveyPeriodeId": str(target.get("surveyPeriodId") or ""),
            "assignmentId": str(target.get("id") or ""),
            "filename": arc_filename,
            "md5": str(archive_md5),
            "createStatus": "true" if target.get("isNew") else "false",
            "draftStatus": "false",
            "regionId": str(region_id),
            **data_slots,
            "latitude": str(lat) if lat is not None else "0.0",
            "longitude": str(lon) if lon is not None else "0.0",
            "statusApproval": "false",
            "sourceFrom": "CAPI",
            # Real paradata (interview action-log + device telemetry) like the app;
            # empty paradata => record stored but not registered into the FASIH frame
            # (check-idpln fasih_exists stays false).
            "paradata": build_paradata(lat, lon, target.get("currentUserId") or "", user_name, start_time_str=start_time_str, end_time_str=end_time_str),
            "comment": '{"dataKey":"","notes":[]}',
            "note": ""
        }
        if is_edit or target.get("isNew"):
            params["copyFromId"] = str(target.get("copyFromId") or target.get("id") or "")

        if not dry_run:
            submit_resp = confirm_submit(headers, params, is_edit=is_edit)
            return True, "Sukses: Data berhasil dikirimkan ke BPS!"
        else:
            return True, "Sukses (DRY RUN)"

    except Exception as e:
        msg = str(e)
        # Handle Rate Limit (429) gracefully with a clear message for laypersons
        if "429" in msg or "rate limit" in msg.lower() or "terlampaui" in msg.lower():
            clean_msg = "⏳ Kuota Harian BPS Terlampaui (429 Rate Limit)"
            import re
            m = re.search(r'Coba lagi dalam [^.]+', msg)
            if m:
                clean_msg += f" ({m.group(0)})"
            else:
                clean_msg += ". Silakan tunggu reset kuota BPS."
            logger.warning(f"Submit warning for {val}: {clean_msg}")
            return False, clean_msg

        # Network hiccups (BPS slow/overloaded) are transient — log a clean line,
        # not a full stack trace.
        if any(t in msg.lower() for t in ("user tidak memiliki assignment", "assignment reference not found")):
            return False, "❌ Akun BPS ini belum memiliki sampel/tugas dari BPS di wilayah ini. Admin BPS perlu menugaskan minimal 1 sampel ke akun ini di Web Monitoring BPS."
        if any(t in msg.lower() for t in ("timed out", "timeout", "max retries", "connection")):
            logger.error(f"Submit error for {val}: BPS lambat/timeout — {msg[:120]}")
            return False, "BPS lambat/timeout — coba lagi (cek fasih-status). Item dilewati."
        if "no access for assignment" in msg.lower():
            logger.warning(f"Resubmit {val}: Hak akses assignment reject ini sudah dicabut/reassigned di server BPS.")
            return False, ("⚠️ Hak akses BPS dicabut — assignment reject ini sudah tidak aktif/reassigned "
                           "ke surveyUser baru di server. Silakan abaikan/selesaikan manual bila perlu.")

        # KNOWN LIMITATION (reject resubmit): BPS validates the encrypted answer payload
        # of an UPDATE against the record's original FormGear structure (survey template
        # 0.6.7). This tool rebuilds a synthetic payload whose `answers` array differs, so
        # BPS rejects it with "Versi data tidak valid" — every observable submit field
        # (params/envelope/paradata/encryption/X-Device-Id) already matches the app.
        if (resubmit_reject or resubmit_open or resubmit_reopen) and "versi data tidak valid" in msg.lower():
            logger.error(f"Resubmit {val}: BPS tolak versi payload (limitasi diketahui).")
            return False, ("❌ BPS tolak update ('Versi data tidak valid') — struktur payload "
                           "reject belum disamakan ke app (butuh 1 sampel 7z app). Sementara: "
                           "perbaiki reject ini lewat app FASIH.")
        logger.error(f"Submit error for {val}: {msg}")
        return False, f"Error: {msg}"
    finally:
        if _owns_temp:
            shutil.rmtree(temp_dir, ignore_errors=True)


# --- Main ---

def _reject_idpels(survey_caches: dict) -> list[str]:
    """IDPel dari semua assignment berstatus REJECTED di seluruh survey cache.
    Kunci resubmit-reject: cuma record REJECT (alias mengandung 'REJECT') yang
    diambil, di-dedup, urut stabil — dipakai sebagai daftar item bila user tak
    memberi input."""
    out, seen = [], set()
    for sc in survey_caches.values():
        tm = sc.get("template_mapping") or {}
        idpel_slot = next((s for s, v in tm.items() if v == "r101a"), "data3")
        for a in sc.get("assignments") or []:
            if "REJECT" in ((a.get("assignmentStatusAlias") or "").upper()):
                idp = (a.get(idpel_slot) or "").strip()
                if idp and 8 <= len(idp) <= 15 and idp not in seen:
                    seen.add(idp)
                    out.append(idp)
    return out


def _open_idpels(survey_caches: dict) -> list[str]:
    """IDPel / NoMeter dari assignment berstatus OPEN / PERNAH DIBUKA."""
    out, seen = [], set()
    for sc in survey_caches.values():
        tm = sc.get("template_mapping") or {}
        idpel_slot = next((s for s, v in tm.items() if v == "r101a"), "data3")
        nometer_slot = next((s for s, v in tm.items() if v == "r101b"), "data1")
        for a in sc.get("assignments") or []:
            alias = (a.get("assignmentStatusAlias") or "").strip().upper()
            if "OPEN" in alias or "PERNAH DIBUKA" in alias:
                idp = (a.get(idpel_slot) or "").strip()
                nom = (a.get(nometer_slot) or "").strip()
                if idp and 8 <= len(idp) <= 15 and idp not in seen:
                    seen.add(idp)
                    out.append(idp)
                if nom and 8 <= len(nom) <= 15 and nom not in seen:
                    seen.add(nom)
                    out.append(nom)
    return out





def _reopen_idpels(survey_caches: dict) -> list[str]:
    """IDPel / NoMeter dari assignment berstatus OPEN / PERNAH DIBUKA (alias untuk _open_idpels)."""
    return _open_idpels(survey_caches)


def main():
    parser = argparse.ArgumentParser(description="Batch Submit Petugas")
    parser.add_argument("input", nargs="?", help="File .txt berisi daftar IDPel/NoMeter (satu per baris)")
    parser.add_argument("--list", "-l", help="Daftar IDPel/NoMeter dipisah koma")
    parser.add_argument("--local", action="store_true", help="Paksa mode local (bypass jam kerja & delay HP)")
    parser.add_argument("--dry-run", action="store_true", help="Test tanpa submit ke BPS")
    parser.add_argument("--force", action="store_true", help="Re-register: paksa submit ulang record lama yang BELUM tercatat di FASIH (fasih_exists=false); yang sudah tercatat dilewati")
    parser.add_argument("--no-cek", action="store_true", help="Skip CEK IDPel/NIK dari awal (hindari 429 rate-limit). Data tetap TERDATA di FASIH via paradata; kehilangan routing prelist + tampilan pemadanan NIK")
    parser.add_argument("--resubmit-all", action="store_true", help="Submit ULANG semua ID walau sudah TERCATAT (buat betulin region/BLOK III record lama). Bikin record baru; yang lama jadi dobel")
    parser.add_argument("--resubmit-reject", action="store_true", help="Perbaiki data REJECT: buka ulang assignment yang statusnya REJECTED lalu resubmit ke ID yang SAMA (createStatus=false) — TIDAK bikin record baru, tidak dobel. Tanpa input = auto-cari semua reject; dengan input = hanya reject di daftar itu")
    parser.add_argument("--resubmit-open", action="store_true", help="Submit data OPEN yang belum pernah dibuka (tanpa basePath): isi dan submit ke assignment ID yang SAMA — tidak bikin record baru. Tanpa input = auto-cari semua OPEN belum dibuka")
    parser.add_argument("--resubmit-reopen", action="store_true", help="Submit data OPEN pernah dibuka (ada basePath): isi dan submit ke assignment ID yang SAMA — tidak bikin record baru. Tanpa input = auto-cari semua OPEN pernah dibuka")
    parser.add_argument("--fast", action="store_true", help="Setup survei dari cache disk (run pertama ambil 1 halaman lalu di-cache; run berikutnya ZERO fetch, gak bisa timeout). Buat create_new/add-sample")
    parser.add_argument("--skip-cek-idpln", action="store_true", help="Memaksa submit data ke BPS FASIH meskipun CEK IDPel terkena limit (HTTP 429)")
    parser.add_argument("--delay", type=float, default=0.5, help="Stagger acak per item (detik) untuk hindari thundering-herd; 0 = tanpa stagger")
    parser.add_argument("--workers", type=int, default=4, help="Jumlah submit paralel (default 4). Item nunggu latency BPS ~8-10 dtk, jadi paralel = jauh lebih cepat. 1 = serial")
    args = parser.parse_args()

    is_local = is_local_environment() or args.local
    ok_hours, hours_msg = check_working_hours(is_local)
    if not ok_hours:
        print(f"\n{hours_msg}\n")
        sys.exit(1)

    # Parse item list
    items = []
    if args.list:
        items = [v.strip() for v in args.list.split(",") if v.strip()]
    elif args.input:
        if not os.path.exists(args.input):
            print(f"❌ File tidak ditemukan: {args.input}")
            sys.exit(1)
        with open(args.input) as f:
            for line in f:
                v = line.strip().replace("*", "").strip()
                if v and not v.startswith("#"):
                    items.append(v)
    elif args.resubmit_reject or args.resubmit_open or args.resubmit_reopen:
        pass  # no input needed — idpels auto-derived after survey setup
    else:
        parser.print_help()
        sys.exit(1)

    _any_resubmit = args.resubmit_reject or args.resubmit_open or args.resubmit_reopen or args.resubmit_all

    if not items and not _any_resubmit:
        print("❌ Tidak ada item untuk diproses.")
        sys.exit(1)

    seen = set()
    unique = []
    for x in items:
        if x not in seen:
            seen.add(x)
            unique.append(x)
    if len(unique) < len(items):
        print(f"⚠️  {len(items) - len(unique)} duplikat dihapus")
    items = unique

    if not PLN_API_URL:
        print("⚠️  PLN_API_URL belum diset di .env — PLN enrichment dilewati")
    if items or not _any_resubmit:
        print(f"\n📋 Total item: {len(items)}")
    if args.dry_run:
        print("🧪 Mode: DRY RUN (tidak submit ke BPS)")
    if args.force:
        print("🔁 Mode: FORCE RE-REGISTER (submit ulang record yang belum tercatat di FASIH)")
    if args.resubmit_all:
        print("♻️  Mode: RESUBMIT-ALL (submit ulang semua walau sudah tercatat — betulin region)")
    if args.resubmit_reject:
        print("🩹 Mode: RESUBMIT-REJECT (perbaiki data REJECT — CEK IDPel otomatis diskip)")
    if args.resubmit_open:
        print("📂 Mode: RESUBMIT-OPEN (submit data OPEN / BELUM DIBUKA — menjalankan CEK IDPel BPS)")
    if args.resubmit_reopen:
        print("📂 Mode: RESUBMIT-REOPEN (submit data OPEN / BELUM DIBUKA — menjalankan CEK IDPel BPS)")

    if args.no_cek:
        print("⏭️  Mode: NO-CEK (skip CEK IDPel/NIK — data tetap terdata via paradata)")
        print("   ⚠️  Tanpa CEK, guard anti-dupe mati. Pakai list yang SUDAH difilter (belum saja).")
    if args.fast:
        print("⚡ Mode: FAST (ambil 1 halaman tugas — create_new only)")
    if args.workers and args.workers > 1:
        print(f"🚀 Paralel: {args.workers} worker")

    # Config wilayah dari server (token Mapbox per-wilayah). Server menang atas .env
    # lokal → HP yang sudah ter-setup token wilayah lain otomatis ikut wilayahnya.
    _mb = apply_region_config()
    print(f"🌏 Wilayah: {get_region()}" + (f" · 🗺️  Mapbox: {_mb}" if _mb else " · 🗺️  Mapbox: (tidak ada)"))
    print()

    # Step 1: Login
    token_data = ensure_login()
    headers = get_headers(token_data)

    # Step 2-3: survey/periode/template/region setup. With --fast, reuse a disk
    # cache (per account) so repeat runs do ZERO BPS fetch and can't time out.
    email = _account_email(token_data)
    # --fast reuses a page-0 disk cache (templates only, no reject rows). Reject mode
    # MUST see the full list, so it never uses that cache even if --fast is also passed.
    survey_caches = _load_survey_cache(email) if (args.fast and not _any_resubmit) else None
    if survey_caches:
        n = sum(len(sc.get("assignments", [])) for sc in survey_caches.values())
        print(f"📊 Pakai cache survei (skip fetch BPS) — {', '.join(survey_caches)} · {n} template")
    else:
        print("📊 Mengambil data survei dari BPS...")
        try:
            surveys = fetch_surveys(headers)
        except Exception as e:
            msg = str(e)
            if any(c in msg for c in ("500", "502", "503", "504")):
                print("❌ Server BPS lagi sibuk/down (5xx). Bukan masalah data kamu — tunggu beberapa menit, cek 'fasih-status', lalu coba lagi.")
            else:
                print(f"❌ Gagal ambil data survei dari BPS: {msg[:150]}")
            sys.exit(1)
        if not surveys:
            print("❌ Tidak ada survei aktif.")
            sys.exit(1)

        survey_caches = {}
        for survey in surveys:
            sname = (survey.get("name") or "").upper()
            if "PASCA" in sname:
                skey = "PASCABAYAR"
            elif "PRABAYAR" in sname or "PRA" in sname:
                skey = "PRABAYAR"
            else:
                skey = sname[:20] or "DEFAULT"

            active_periode = next((p for p in survey.get("listPeriode", []) if p.get("isActive")), None)
            if not active_periode:
                print(f"   ⚠️  {skey}: tidak ada periode aktif, dilewati")
                continue
            pid = active_periode["id"]

            template_lookup = survey.get("templateLookup", [])
            template_mapping = {}
            if template_lookup:
                tl = template_lookup[0]
                template_mapping = fetch_template_mapping(headers, tl["templateId"], tl["templateVersion"])

            # Full pagination of ALL assignments (50+ pages on big accounts, and it
            # GROWS every --resubmit-all run) is the main thing that times out / crashes
            # under BPS load. It's only needed for the local-scan dedup of a plain
            # submit. --force / --resubmit-all create_new and dedup via fasih_exists, so
            # page-0 (a handful of templates to clone) is enough — skip the slow fetch.
            try:
                if _any_resubmit:
                    # Reject/open/reopen records live anywhere in the full list (never
                    # on page-0 templates) — must page through everything to find them.
                    _mode_label = "reject" if args.resubmit_reject else "open" if args.resubmit_open else "reopen"
                    print(f"📋 Mengambil SEMUA tugas {skey} (cari {_mode_label})...")
                    assignments = fetch_all_assignments(headers, pid)
                elif args.fast or args.force or args.resubmit_all:
                    print(f"📋 Mengambil template {skey} (fast)...")
                    fp = fetch_assignments(headers, pid, 0)
                    assignments = (fp.get("data") or {}).get("content", []) or []
                else:
                    print(f"📋 Mengambil tugas {skey}...")
                    assignments = fetch_all_assignments(headers, pid)
            except Exception as e:
                print(f"   ⚠️  {skey}: gagal ambil tugas (BPS lambat) — {str(e)[:60]}. Coba lagi / kurangi --workers.")
                continue
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
            print(f"   {skey}: {len(assignments)} tugas")

        if not survey_caches:
            print("❌ Tidak ada survei dengan periode aktif.")
            sys.exit(1)
        if args.fast and not _any_resubmit:
            _save_survey_cache(email, survey_caches)  # never persist resubmit full-list as the fast cache

    # RESUBMIT-REJECT: the item list IS the set of REJECTED records discovered in the
    # freshly-fetched assignments. If the user also passed IDs, keep only those that are
    # actually reject (never accidentally resubmit a non-reject in this mode).
    if args.resubmit_reject:
        rejects = _reject_idpels(survey_caches)
        if not items:
            items = rejects
            if not items:
                print("\n✅ Tidak ada data REJECT pada akun ini untuk diperbaiki. Selesai.")
                sys.exit(0)

        total_found = len(items)
        print(f"\n📋 Ditemukan {total_found} data REJECT pada akun BPS ini ({email}).")
        if sys.stdin.isatty() and not args.list and not args.input:
            try:
                ans = input(f"👉 Mau berapa data REJECT yang di-resubmit? [Tekan ENTER untuk SEMUA ({total_found}), atau ketik jumlah (misal: 10)]: ").strip()
                if ans.isdigit() and int(ans) > 0:
                    limit_n = int(ans)
                    items = items[:limit_n]
                    print(f"🎯 Mengambil {len(items)} dari {total_found} data REJECT untuk di-resubmit.")
            except (KeyboardInterrupt, EOFError):
                print("\n❌ Dibatalkan oleh pengguna.")
                sys.exit(0)
        else:
            print(f"🩹 Resubmit {len(items)} data REJECT ke assignment yang SAMA (tidak dobel)")

    if args.resubmit_open:
        opens = _open_idpels(survey_caches)
        items = [x for x in items if x in set(opens)] if items else opens
        if not items:
            print("\n✅ Tidak ada data OPEN (belum dibuka) untuk disubmit. Selesai.")
            sys.exit(0)

        total_found = len(items)
        print(f"\n📋 Ditemukan {total_found} data OPEN (belum dibuka) pada akun BPS ini ({email}).")
        if sys.stdin.isatty() and not args.list and not args.input:
            try:
                ans = input(f"👉 Mau berapa data OPEN yang di-submit? [Tekan ENTER untuk SEMUA ({total_found}), atau ketik jumlah (misal: 10)]: ").strip()
                if ans.isdigit() and int(ans) > 0:
                    limit_n = int(ans)
                    items = items[:limit_n]
                    print(f"🎯 Mengambil {len(items)} dari {total_found} data OPEN untuk di-submit.")
            except (KeyboardInterrupt, EOFError):
                print("\n❌ Dibatalkan oleh pengguna.")
                sys.exit(0)
        else:
            print(f"📂 Submit {len(items)} data OPEN (belum dibuka) ke assignment yang SAMA (tidak dobel)")

    if args.resubmit_reopen:
        reopens = _reopen_idpels(survey_caches)
        items = [x for x in items if x in set(reopens)] if items else reopens
        if not items:
            print("\n✅ Tidak ada data OPEN (pernah dibuka) untuk disubmit. Selesai.")
            sys.exit(0)

        total_found = len(items)
        print(f"\n📋 Ditemukan {total_found} data OPEN (pernah dibuka) pada akun BPS ini ({email}).")
        if sys.stdin.isatty() and not args.list and not args.input:
            try:
                ans = input(f"👉 Mau berapa data OPEN (pernah dibuka) yang di-submit? [Tekan ENTER untuk SEMUA ({total_found}), atau ketik jumlah (misal: 10)]: ").strip()
                if ans.isdigit() and int(ans) > 0:
                    limit_n = int(ans)
                    items = items[:limit_n]
                    print(f"🎯 Mengambil {len(items)} dari {total_found} data OPEN untuk di-submit.")
            except (KeyboardInterrupt, EOFError):
                print("\n❌ Dibatalkan oleh pengguna.")
                sys.exit(0)
        else:
            print(f"📂 Submit {len(items)} data OPEN (pernah dibuka) ke assignment yang SAMA (tidak dobel)")

    # Process items — parallel pool. Each item is ~8-10s of BPS network latency
    # (HAR: presign 2.5s + submit 1.6s + cek 1.5s + foto 1.5s), so running a few
    # concurrently multiplies throughput. Each worker gets its OWN temp dir (no
    # shared-dir cleanup race); results are collected in the main thread so the
    # counters/report need no locks.
    workers = max(1, args.workers)
    print(f"\n{'='*50}")
    print(f"⚡ MEMULAI BATCH SUBMIT — {len(items)} item · {workers} paralel")
    print(f"{'='*50}\n")

    successes = 0
    failures = 0
    report_rows = []
    start_time = time.time()

    def _worker(idx: int, val: str):
        if args.resubmit_reject:
            # Delay 30-60 dtk per data untuk resubmit-reject: menyebar beban ke BPS +
            # pola lebih manusiawi (bukan burst massal). Per item (tiap worker menunggu
            # sebelum submit-nya). Naikkan --workers kalau mau throughput lebih tinggi.
            time.sleep(random.uniform(30, 60))
        elif hasattr(args, "delay") and args.delay > 0:
            time.sleep(random.uniform(0, args.delay))  # stagger, avoid thundering-herd
        wdir = tempfile.mkdtemp(prefix="fasih_")
        try:
            ok, message = submit_single(
                token_data, val, survey_caches,
                dry_run=args.dry_run, temp_dir=wdir, force=args.force,
                resubmit_all=args.resubmit_all,
                resubmit_reject=args.resubmit_reject,
                resubmit_open=args.resubmit_open,
                resubmit_reopen=args.resubmit_reopen,
                skip_cek_idpln=args.no_cek or args.skip_cek_idpln,
            )
        except Exception as e:  # never let one item kill the pool
            ok, message = False, f"Error tak terduga: {str(e)[:120]}"
        finally:
            shutil.rmtree(wdir, ignore_errors=True)
        return idx, val, ok, message

    with ThreadPoolExecutor(max_workers=workers) as ex:
        futures = [ex.submit(_worker, i, v) for i, v in enumerate(items)]
        done = 0
        for fut in as_completed(futures):
            idx, val, ok, message = fut.result()
            done += 1
            if ok:
                successes += 1
            else:
                failures += 1
            report_rows.append({
                "_idx": idx,
                "val": val, "status": "SUCCESS" if ok else "FAILED", "message": message
            })

            elapsed = time.time() - start_time
            rate = done / (elapsed / 60) if elapsed > 0 else 0
            eta = ((len(items) - done) / rate * 60) if rate > 0 else 0
            icon = "✅" if ok else "❌"
            print(f"[{done}/{len(items)}] {val} {icon} {message}")
            pct = int(done / len(items) * 100)
            filled = pct // 5
            bar = "█" * filled + "░" * (20 - filled)
            elapsed_str = f"{int(elapsed//60)}m{int(elapsed%60)}s"
            eta_str = f"{int(eta//60)}m{int(eta%60)}s" if eta > 0 else "—"
            print(f"   [{bar}] {pct}% | ✅{successes} ❌{failures} | ⏱{elapsed_str} ETA:{eta_str} | {rate:.1f}/min")

    # Report — restore input order (parallel completes out of order)
    report_rows.sort(key=lambda r: r["_idx"])
    elapsed_total = time.time() - start_time
    print(f"\n{'='*50}")
    print(f"🏁 BATCH SELESAI")
    print(f"   ✅ Sukses: {successes}")
    # Rincian sukses: "Sukses: ..." = record baru dikirim (tercatat async 10-30mnt);
    # "Sudah TERCATAT/terkirim" = di-skip anti-dupe (sudah ada di FASIH).
    _succ = [r for r in report_rows if r["status"] == "SUCCESS"]
    _sent = [r for r in _succ if r["message"].startswith("Sukses")]
    _terc = [r for r in _succ if r["message"].startswith("Sudah")]
    if _sent:
        print(f"      📤 Baru dikirim ke BPS  : {len(_sent)}  →  {', '.join(r['val'] for r in _sent)}")
    if _terc:
        print(f"      🟢 Sudah tercatat (skip): {len(_terc)}  →  {', '.join(r['val'] for r in _terc)}")
    print(f"   ❌ Gagal:  {failures}")
    print(f"   ⏱  Waktu:  {int(elapsed_total//60)}m {int(elapsed_total%60)}s")
    print(f"{'='*50}")

    failed = [r for r in report_rows if r["status"] == "FAILED"]
    if failed:
        print(f"\n❌ {len(failed)} ID GAGAL (+ alasan):")
        for r in failed:
            print(f"   • {r['val']} — {r['message']}")
        print(f"\n📋 Copy ID gagal (paste ulang buat coba lagi):")
        for r in failed:
            print(f"{r['val']}")

    # Save CSV report
    report_file = f"batch_report_{datetime.now().strftime('%Y%m%d_%H%M%S')}.csv"
    with open(report_file, "w", encoding="utf-8", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=["val", "status", "message"], extrasaction="ignore")
        writer.writeheader()
        writer.writerows(report_rows)
    print(f"\n📄 Report: {report_file}")


if __name__ == "__main__":
    main()
