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
from concurrent.futures import ThreadPoolExecutor, as_completed
from datetime import datetime
from typing import Optional

# Add repo root to path
REPO_ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
sys.path.insert(0, REPO_ROOT)

from dotenv import load_dotenv
load_dotenv(os.path.join(REPO_ROOT, ".env"))

from fasih_auth import perform_login, refresh_token_if_needed, get_headers
from fasih_api import (
    fetch_surveys, fetch_all_assignments, fetch_assignments,
    fetch_template_mapping, fetch_regions,
    request_photo_presign_put, upload_photo_to_s3, request_photo_presign_get,
    confirm_submit, request_presign_url, upload_to_s3,
    map_answers_to_data_slots,
    check_idpln, check_nikpln,
)
from fasih_crypto import compute_md5, compute_md5_base64
from fasih_archive import create_7z_archive
from submit_fasih import (
    build_dynamic_answers, stage_and_encrypt, clean_pln_name,
    build_new_assignment_target, resolve_coordinate, build_paradata,
    STATIC_LEGACY_KEY,
)
from region import get_region, DEFAULT_REGION

import requests as req_lib
import base64
import hashlib

# --- Logging ---

logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(levelname)s - %(message)s"
)
logger = logging.getLogger("petugas")

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

def apply_region_config() -> str:
    """Tarik config dari server wilayah (lihat .region) lalu terapkan ke env proses.

    Token Mapbox SENGAJA diambil dari server dan MENANG atas .env lokal: HP petugas
    yang sudah terlanjur di-setup dengan token wilayah lain (mis. Bontang) otomatis
    pindah ke token wilayahnya cukup dengan `fasih-update` — tanpa edit .env per HP.
    Kalau server tak punya token / tak bisa dihubungi, .env lokal tetap dipakai.
    Return: nama akun token yang akhirnya dipakai (buat log), atau "" bila tak ada."""
    if not PLN_API_URL:
        return ""
    headers = {"X-API-Key": PLN_API_KEY} if PLN_API_KEY else {}
    # 2x percobaan, timeout longgar: ini request PERTAMA ke tunnel (DNS+TLS+routing
    # dingin) dan di jaringan HP sering >8s — kalau gagal, token wilayah tak terpakai.
    last = ""
    for attempt in (1, 2):
        try:
            resp = req_lib.get(f"{PLN_API_URL}/api/config", headers=headers, timeout=25)
            if resp.status_code == 200:
                tok = ((resp.json() or {}).get("mapbox_token") or "").strip()
                if tok:
                    os.environ["MAPBOX_ACCESS_TOKEN"] = tok  # server menang atas .env lokal
                    return _mapbox_account(tok)
                break                       # server jawab tapi tak punya token → fallback
            last = f"HTTP {resp.status_code}"
            if resp.status_code in (401, 403):
                break                       # key salah — retry tak menolong
        except Exception as e:
            last = f"{type(e).__name__}: {str(e)[:120]}"
    if last:
        logger.warning(f"Config wilayah tak terambil ({last}) — pakai .env lokal")
    return _mapbox_account(os.getenv("MAPBOX_ACCESS_TOKEN", ""))


def _mapbox_account(token: str) -> str:
    """Nama akun di dalam token Mapbox (buat log — jangan pernah cetak tokennya)."""
    try:
        p = token.split(".")[1]
        p += "=" * (4 - len(p) % 4)
        return json.loads(base64.urlsafe_b64decode(p.encode())).get("u", "?")
    except Exception:
        return "" if not token else "?"


def pln_lookup(idpel: str = "", nometer: str = "") -> Optional[dict]:
    """Fetch PLN data from server API."""
    if not PLN_API_URL:
        logger.warning("PLN_API_URL not set — skipping PLN enrichment")
        return None

    params = {}
    if idpel:
        params["idpel"] = idpel
    if nometer:
        params["nometer"] = nometer

    headers = {}
    if PLN_API_KEY:
        headers["X-API-Key"] = PLN_API_KEY

    try:
        resp = req_lib.get(f"{PLN_API_URL}/api/lookup", params=params, headers=headers, timeout=30)
        if resp.status_code == 200:
            return resp.json()
        elif resp.status_code == 404:
            logger.warning(f"PLN data not found: {idpel or nometer}")
            return None
        else:
            logger.error(f"PLN API error {resp.status_code}: {resp.text[:200]}")
            return None
    except Exception as e:
        logger.error(f"PLN API connection error: {e}")
        return None


def download_photo(photo_url: str, dest_dir: str) -> Optional[str]:
    """Download photo from server API."""
    if not PLN_API_URL or not photo_url:
        return None

    url = f"{PLN_API_URL}{photo_url}" if photo_url.startswith("/") else photo_url
    headers = {}
    if PLN_API_KEY:
        headers["X-API-Key"] = PLN_API_KEY

    try:
        resp = req_lib.get(url, headers=headers, timeout=30)
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
    except Exception as e:
        logger.error(f"Photo download error: {e}")
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

def _is_prabayar(direct_args: dict) -> bool:
    """Determine product type from PLN API explicit 'produk' field, tarif suffix fallback."""
    produk = (direct_args.get("produk") or "").strip().upper()
    if produk:
        return produk == "PRABAYAR"
    return (direct_args.get("tarif") or "").strip().upper().endswith("M")


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
_cek_state = {"enabled": True}
# Serialize token refreshes so parallel workers never write TOKEN_FILE concurrently.
_token_lock = threading.Lock()

def _cek(fn, *args) -> dict:
    if not _cek_state["enabled"]:
        return {}
    try:
        return fn(*args).get("data") or {}
    except Exception as e:
        msg = str(e).lower()
        # Disable CEK for the rest of the run on a persistent failure (429 quota,
        # or read/connect timeout when BPS is slow) — it would only waste ~30s per
        # item. The submit itself still registers the record via paradata.
        if any(t in msg for t in ("429", "timed out", "timeout", "max retries", "connection")):
            _cek_state["enabled"] = False
            logger.warning("⏭️  CEK dinonaktifkan (BPS 429/timeout) — submit tetap jalan & TERDATA lewat paradata")
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

def _load_survey_cache(email: str):
    try:
        with open(_SURVEY_CACHE_FILE) as f:
            c = json.load(f)
        if c.get("email") == email and (time.time() - c.get("ts", 0)) < _SURVEY_CACHE_TTL:
            return c.get("survey_caches")
    except Exception:
        pass
    return None

def _save_survey_cache(email: str, survey_caches: dict):
    try:
        trimmed = {}
        for k, sc in survey_caches.items():
            trimmed[k] = {
                "periode": sc["periode"],
                "template_mapping": sc["template_mapping"],
                "assignments": sc["assignments"][:20],  # a few templates is enough
                "regions": sc["regions"],
            }
        with open(_SURVEY_CACHE_FILE, "w") as f:
            json.dump({"email": email, "ts": time.time(), "survey_caches": trimmed}, f)
    except Exception as e:
        logger.warning(f"Gagal simpan cache survei: {e}")


def submit_single(
    token_data: dict,
    val: str,
    survey_caches: dict,
    dry_run: bool = False,
    temp_dir: str = "",
    force: bool = False,
    resubmit_all: bool = False,
    resubmit_reject: bool = False,
) -> tuple[bool, str]:
    """Submit single item — picks correct survey (Prabayar/Pascabayar) automatically."""
    try:
        with _token_lock:
            token_data = refresh_token_if_needed(token_data, token_file=TOKEN_FILE, exit_on_failure=False)
        headers = get_headers(token_data)

        # Determine idpel vs nometer
        is_idpel = len(val) == 12
        idpel_val = val if is_idpel else ""
        nometer_val = "" if is_idpel else val

        # Search ALL surveys' assignments for existing match
        target = None
        matched_key = None
        create_new = False
        template_assignment_id = None

        for skey, sc in survey_caches.items():
            tm = sc["template_mapping"]
            idpel_slot = next((s for s, v in tm.items() if v == "r101a"), "data3")
            nometer_slot = next((s for s, v in tm.items() if v == "r101b"), "data1")
            for a in sc["assignments"]:
                v_idpel = (a.get(idpel_slot) or "").strip()
                v_nometer = (a.get(nometer_slot) or "").strip()
                if (is_idpel and v_idpel == val) or (not is_idpel and v_nometer == val):
                    target = a
                    matched_key = skey
                    break
            if target:
                break

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
            tm = sc["template_mapping"]
            i_slot = next((s for s, v in tm.items() if v == "r101a"), "data3")
            n_slot = next((s for s, v in tm.items() if v == "r101b"), "data1")
            status_alias = target.get("assignmentStatusAlias") or ""
            if "SUBMITTED" in status_alias or "DONE" in status_alias or "APPROVED" in status_alias:
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
            if not target or "REJECT" not in status_alias.upper():
                return True, "Bukan data REJECT (status berubah / tak ketemu) — dilewati."

        # Anti-dupe FIRST (before PLN lookup): for a known idpel a single check-idpln
        # says if it's already registered — skip tercatat without wasting a PLN lookup
        # (also lowers CEK/PLN load, which feeds the 429 that would disable CEK).
        # Nometer-only items resolve idpel via PLN below and are CEK'd there.
        import uuid
        aid = target.get("id") if target else str(uuid.uuid4())
        d_idpln = _cek(check_idpln, headers, aid, idpel_val) if idpel_val else {}
        if d_idpln.get("fasih_exists") and not resubmit_all and not resubmit_reject:
            return True, "Sudah TERCATAT di FASIH — skip (anti-dupe)."

        # Step 5: PLN lookup via server API
        lat, lon = None, None
        pln_data = pln_lookup(idpel=idpel_val, nometer=nometer_val)
        photo_path = None

        # GUARD: never submit without valid PLN data. Without it the survey
        # (Prabayar/Pascabayar), nama, alamat & coords all fall back to
        # placeholders → junk record in the wrong survey. Abort instead.
        if not pln_data:
            return False, "❌ Data PLN tidak ditemukan / server PLN tak terjangkau (cek fasih-status). Item dilewati agar tidak kirim data placeholder."

        # BLOK III (r301) region cascade cocoknya lewat fullcode dari kd_kel. Kalau
        # --workers tinggi, tunnel PLN bisa balikin baris PARSIAL (nama ada, kd_kel
        # kosong) → fallback nama salah ambil kabupaten (6404 vs 6408) → r301 blank
        # di app padahal r102 keisi. Retry sekali; kalau tetap kosong, skip (bisa
        # diulang) — jangan submit region ngawur.
        def _kel_ok(d):
            k = str((d or {}).get("kd_kel") or "").strip()
            return len(k) == 10 and k.isdigit()
        if not _kel_ok(pln_data):
            pln_data = pln_lookup(idpel=idpel_val, nometer=nometer_val) or pln_data
        if not _kel_ok(pln_data):
            return False, "❌ Region PLN tak lengkap (kd_kel kosong — BLOK III bakal blank) — dilewati, coba lagi. Kalau sering: turunkan --workers (tunnel PLN overload)."

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
            try:
                pln_lat = pln_data.get("latitude")
                pln_lon = pln_data.get("longitude")
                if pln_lat and float(pln_lat) != 0.0:
                    lat = float(pln_lat)
                if pln_lon and float(pln_lon) != 0.0:
                    lon = float(pln_lon)
            except (ValueError, TypeError):
                pass

            # Download photo
            if pln_data.get("photo_url"):
                photo_path = download_photo(pln_data["photo_url"], temp_dir)

        # Step 5b: CEK IDPel — reuse the early result; only CEK now if the idpel was
        # just resolved via PLN (nometer input). prelist_source (BPS's authoritative
        # Prabayar/Pascabayar) routes create_new; PLN produk is the fallback.
        if not d_idpln and idpel_val:
            d_idpln = _cek(check_idpln, headers, aid, idpel_val)
        prelist = (d_idpln.get("prelist_source") or "").strip().upper()
        if d_idpln and not d_idpln.get("exists"):
            logger.warning(f"CEK IDPel {idpel_val}: exists=false di BPS")

        # DEDUP GUARD (global, anti-dupe) — the single check-idpln above is the source
        # of truth for "already registered in FASIH". Skip anything tercatat unless the
        # user explicitly forces a rebuild (--resubmit-all, e.g. to fix region/BLOK III).
        fasih_exists = d_idpln.get("fasih_exists")
        if fasih_exists and not resubmit_all and not resubmit_reject:
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
                return False, f"Tidak ada assignment di survey {matched_key}."
            template_assignment = _find_template_for_region(template_pool, pln_data)
            target = build_new_assignment_target(
                template_assignment, idpel_val, nometer_val, sc["template_mapping"])
            target["id"] = aid  # same id used for CEK, mirrors the app
            target["data2"] = direct_args.get("nama") or ""
            target["data4"] = direct_args.get("alamat") or ""
            target["data5"] = direct_args.get("alamat") or ""

        # Resolve active survey cache
        sc = survey_caches[matched_key]
        cached_template_mapping = sc["template_mapping"]
        cached_regions = sc["regions"]
        pid = sc["periode"]["id"]

        # CEK NIK (pemadanan) — companion verification, best-effort (see _cek)
        nik_val = direct_args.get("nik") or ""
        nikpln_data = _cek(check_nikpln, headers, aid, nik_val) if nik_val else {}
        if nik_val and nikpln_data and not nikpln_data.get("exists"):
            logger.warning(f"CEK NIK {nik_val}: exists=false (tidak padan) di BPS")

        # Step 6: Build answers
        answers = build_dynamic_answers(target, direct_args, cached_template_mapping)
        # Feed the real NIK pemadanan result into the archive so BPS shows the NIK
        # as matched (hasilPemadananNIK/no_kk/result_callnik) instead of "tidak ditemukan"
        answers["_nikpln"] = nikpln_data

        # Step 7: Photo upload
        if photo_path and os.path.exists(photo_path):
            tid = target.get("id")
            filename = f"{tid}_r106.png"
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
                logger.warning(f"Photo upload failed: {e}")

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

        # Step 8: Encrypt
        region_id = (target.get("region") or {}).get("id", "")
        wrapped_key = None
        for r in cached_regions:
            if r.get("region_id") == region_id or (r.get("region") or {}).get("id") == region_id:
                wrapped_key = r.get("wrappedDatakey")
                break
        if not wrapped_key:
            wrapped_key = STATIC_LEGACY_KEY
        try:
            key_bytes = base64.b64decode(wrapped_key.encode("utf-8"))
        except Exception:
            key_bytes = STATIC_LEGACY_KEY.encode("utf-8")

        user_name = "Petugas"
        try:
            payload_b64 = token_data["access_token"].split(".")[1]
            payload_b64 += "=" * (4 - len(payload_b64) % 4)
            jwt_payload = json.loads(base64.urlsafe_b64decode(payload_b64.encode("utf-8")))
            user_name = jwt_payload.get("name") or jwt_payload.get("email") or "Petugas"
        except Exception:
            pass

        encrypted = stage_and_encrypt(answers, key_bytes, target, user_name)

        # Step 9: Archive + upload
        archive_path = create_7z_archive(encrypted, target["id"], temp_dir)

        # ponytail: reject-resubmit uses the plain submit path (presign-url + s3/submit),
        # matching the real app's HAR of a reject fix — NOT s3/edit. createStatus stays
        # "false" (target.isNew unset) so the EXISTING id is reused, no duplicate.
        # Upgrade path: if BPS ever 4xxs a reject here, revisit whether it wants s3/edit.
        is_edit = (target.get("assignmentStatusAlias") != "OPEN") and not resubmit_reject
        copy_from_id = target.get("copyFromId")
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
        data_slots = map_answers_to_data_slots(answers, cached_template_mapping)
        for i in range(1, 11):
            key = f"data{i}"
            if key not in data_slots:
                data_slots[key] = ""
            else:
                data_slots[key] = str(data_slots[key]) if data_slots[key] is not None else ""

        params = {
            "surveyPeriodeId": str(target.get("surveyPeriodId") or ""),
            "assignmentId": str(target.get("id") or ""),
            "filename": arc_filename,
            "md5": str(archive_md5),
            "createStatus": "true" if target.get("isNew", False) else "false",
            "draftStatus": "false",
            "regionId": str(region_id),
            **data_slots,
            "latitude": str(lat) if lat is not None else "0.0",
            "longitude": str(lon) if lon is not None else "0.0",
            "copyFromId": str(target.get("copyFromId") or ""),
            "statusApproval": "false",
            "sourceFrom": "CAPI",
            # Real paradata (interview action-log + device telemetry) like the app;
            # empty paradata => record stored but not registered into the FASIH frame
            # (check-idpln fasih_exists stays false).
            "paradata": build_paradata(lat, lon, target.get("currentUserId") or "", user_name),
            "comment": '{"dataKey":"","notes":[]}', "note": ""
        }

        if not dry_run:
            submit_resp = confirm_submit(headers, params, is_edit=is_edit)
            return True, "Sukses: Data berhasil dikirimkan ke BPS!"
        else:
            return True, "Sukses (DRY RUN)"

    except Exception as e:
        msg = str(e)
        # Network hiccups (BPS slow/overloaded) are transient — log a clean line,
        # not a full stack trace. Reserve the traceback for real/unexpected errors.
        if any(t in msg.lower() for t in ("timed out", "timeout", "max retries", "connection")):
            logger.error(f"Submit error for {val}: BPS lambat/timeout — {msg[:120]}")
            return False, "BPS lambat/timeout — coba lagi (cek fasih-status). Item dilewati."
        logger.error(f"Submit error for {val}: {e}", exc_info=True)
        return False, f"Error: {str(e)}"


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
                if idp and idp not in seen:
                    seen.add(idp)
                    out.append(idp)
    return out


def main():
    parser = argparse.ArgumentParser(description="Batch Submit Petugas")
    parser.add_argument("input", nargs="?", help="File .txt berisi daftar IDPel/NoMeter (satu per baris)")
    parser.add_argument("--list", "-l", help="Daftar IDPel/NoMeter dipisah koma")
    parser.add_argument("--dry-run", action="store_true", help="Test tanpa submit ke BPS")
    parser.add_argument("--force", action="store_true", help="Re-register: paksa submit ulang record lama yang BELUM tercatat di FASIH (fasih_exists=false); yang sudah tercatat dilewati")
    parser.add_argument("--no-cek", action="store_true", help="Skip CEK IDPel/NIK dari awal (hindari 429 rate-limit). Data tetap TERDATA di FASIH via paradata; kehilangan routing prelist + tampilan pemadanan NIK")
    parser.add_argument("--resubmit-all", action="store_true", help="Submit ULANG semua ID walau sudah TERCATAT (buat betulin region/BLOK III record lama). Bikin record baru; yang lama jadi dobel")
    parser.add_argument("--resubmit-reject", action="store_true", help="Perbaiki data REJECT: buka ulang assignment yang statusnya REJECTED lalu resubmit ke ID yang SAMA (createStatus=false) — TIDAK bikin record baru, tidak dobel. Tanpa input = auto-cari semua reject; dengan input = hanya reject di daftar itu")
    parser.add_argument("--fast", action="store_true", help="Setup survei dari cache disk (run pertama ambil 1 halaman lalu di-cache; run berikutnya ZERO fetch, gak bisa timeout). Buat create_new/add-sample")
    parser.add_argument("--delay", type=float, default=0.5, help="Stagger acak per item (detik) untuk hindari thundering-herd; 0 = tanpa stagger")
    parser.add_argument("--workers", type=int, default=4, help="Jumlah submit paralel (default 4). Item nunggu latency BPS ~8-10 dtk, jadi paralel = jauh lebih cepat. 1 = serial")
    args = parser.parse_args()

    if args.no_cek:
        _cek_state["enabled"] = False

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
    elif args.resubmit_reject:
        pass  # no input needed — reject idpels auto-derived after survey setup
    else:
        parser.print_help()
        sys.exit(1)

    if not items and not args.resubmit_reject:
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
    if items or not args.resubmit_reject:
        print(f"\n📋 Total item: {len(items)}")
    if args.dry_run:
        print("🧪 Mode: DRY RUN (tidak submit ke BPS)")
    if args.force:
        print("🔁 Mode: FORCE RE-REGISTER (submit ulang record yang belum tercatat di FASIH)")
    if args.resubmit_all:
        print("♻️  Mode: RESUBMIT-ALL (submit ulang semua walau sudah tercatat — betulin region)")
    if args.resubmit_reject:
        print("🩹 Mode: RESUBMIT-REJECT (perbaiki data REJECT ke assignment yang SAMA — tidak dobel)")
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
    survey_caches = _load_survey_cache(email) if (args.fast and not args.resubmit_reject) else None
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
                if args.resubmit_reject:
                    # Reject records live anywhere in the full list (never on page-0
                    # templates) — must page through everything to find them.
                    print(f"📋 Mengambil SEMUA tugas {skey} (cari reject)...")
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

            survey_caches[skey] = {
                "survey": survey,
                "periode": active_periode,
                "template_mapping": template_mapping,
                "assignments": assignments,
                "regions": regions,
            }
            print(f"   {skey}: {len(assignments)} tugas")

        if not survey_caches:
            print("❌ Tidak ada survei dengan periode aktif.")
            sys.exit(1)
        if args.fast and not args.resubmit_reject:
            _save_survey_cache(email, survey_caches)  # never persist reject full-list as the fast cache

    # RESUBMIT-REJECT: the item list IS the set of REJECTED records discovered in the
    # freshly-fetched assignments. If the user also passed IDs, keep only those that are
    # actually reject (never accidentally resubmit a non-reject in this mode).
    if args.resubmit_reject:
        rejects = _reject_idpels(survey_caches)
        items = [x for x in items if x in set(rejects)] if items else rejects
        if not items:
            print("✅ Tidak ada data REJECT untuk diperbaiki. Selesai.")
            sys.exit(0)
        print(f"\n🩹 {len(items)} data REJECT ditemukan → resubmit ke assignment yang SAMA (tidak dobel)")

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
        if args.delay > 0:
            time.sleep(random.uniform(0, args.delay))  # stagger, avoid thundering-herd
        wdir = tempfile.mkdtemp(prefix="fasih_")
        try:
            ok, message = submit_single(
                token_data, val, survey_caches,
                dry_run=args.dry_run, temp_dir=wdir, force=args.force,
                resubmit_all=args.resubmit_all,
                resubmit_reject=args.resubmit_reject,
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
    # Dedup guard degrades if CEK got disabled mid-run (429/timeout) — warn loudly so
    # silent duplicates for already-tercatat items don't go unnoticed (finding #1).
    if not _cek_state["enabled"] and not args.no_cek:
        print("⚠️  CEK sempat mati (429/timeout) di tengah batch — guard anti-dupe bocor.")
        print("    Item TERCATAT setelah itu bisa jadi dobel. Ganti akun / cek ulang ID-nya.")

    failed = [r for r in report_rows if r["status"] == "FAILED"]
    if failed:
        print(f"\n❌ {len(failed)} ID GAGAL (+ alasan):")
        for r in failed:
            print(f"   • {r['val']} — {r['message'][:90]}")
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
