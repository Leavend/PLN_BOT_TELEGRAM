#!/usr/bin/env python3
"""
PLN Data + Photo API Server

Exposes PLN AP2T lookup and random house photos via REST API.
Runs on the PLN server, consumed by field workers' Termux clients.

Usage:
    python3 server.py                     # default port 8900
    PLN_API_PORT=9000 python3 server.py   # custom port
"""
from __future__ import annotations  # `str | None` annotations lazy → import-safe di Python 3.9

import os
import sys
import json
import random
import hashlib
import hmac
import time
import logging
from datetime import datetime
from functools import wraps

# Add parent dir so we can import pln_lookup
sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from flask import Flask, request, jsonify, send_file, abort
from region import get_region

app = Flask(__name__)

logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s - %(name)s - %(levelname)s - %(message)s"
)
logger = logging.getLogger("pln_api")

# .env harus dimuat SEBELUM baca API_KEYS. Tanpa ini PLN_API_KEYS kosong dan
# require_api_key jatuh ke jalur "tanpa key" (auth terbuka) padahal server
# diekspos ke publik lewat tunnel.
try:
    from dotenv import load_dotenv
    load_dotenv(os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), ".env"))
except ImportError:
    logger.warning("python-dotenv tidak terpasang — .env TIDAK dimuat, PLN_API_KEYS bisa kosong. "
                   "Jalankan: pip install -r requirements.txt")

# --- Config ---

API_KEYS = {k.strip() for k in os.getenv("PLN_API_KEYS", "").split(",") if k.strip()}

REPO = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
REGION = get_region()


def get_photo_dirs(region):
    return [os.path.join(REPO, "house_photos", region)]


PHOTO_DIRS = get_photo_dirs(REGION)
# Database foto server WAJIB webp. Sengaja webp-only: file selain webp yang nyasar ke
# folder wilayah akan DIABAIKAN (tidak disajikan), bukan diam-diam ikut terpakai.
# Konversi dulu ke webp sebelum ditaruh (lihat docs/runbook-foto-wilayah.md).
VALID_EXTENSIONS = (".webp",)

# --- PLN Lookup singleton ---

_pln_tool = None

def get_pln_tool():
    global _pln_tool
    if _pln_tool is None:
        from pln_lookup import PLNLookupTool
        _pln_tool = PLNLookupTool()
        logger.info("PLNLookupTool initialized")
    return _pln_tool

# --- Photo index ---

_photo_list = []          # SEMUA foto (buat resolve /api/photo/<id>)
_pools = {}               # nama_pool -> [path]  (foto langsung di folder wilayah = "default")
_pool_keywords = {}       # nama_pool -> [keyword nama_kec]  (routing, dari pools.json)
DEFAULT_POOL = "default"


def load_photos():
    """Index foto per POOL. Foto langsung di house_photos/<region>/ = pool 'default';
    tiap SUBFOLDER = pool bernama (mis. melak_kotabangun/). pools.json memetakan
    pool -> keyword kecamatan untuk routing (lihat _route_pool)."""
    global _photo_list, _pools, _pool_keywords
    _photo_list = []
    _pools = {DEFAULT_POOL: []}
    _pool_keywords = {}
    for d in PHOTO_DIRS:
        if not os.path.isdir(d):
            continue
        for f in os.listdir(d):
            p = os.path.join(d, f)
            if os.path.isfile(p) and f.lower().endswith(VALID_EXTENSIONS):
                _pools[DEFAULT_POOL].append(p)
                _photo_list.append(p)
            elif os.path.isdir(p):                     # subfolder = pool bernama
                pics = [os.path.join(p, s) for s in os.listdir(p)
                        if s.lower().endswith(VALID_EXTENSIONS)]
                _pools.setdefault(f, []).extend(pics)
                _photo_list.extend(pics)
        cfg = os.path.join(d, "pools.json")
        if os.path.exists(cfg):
            try:
                with open(cfg) as fh:
                    _pool_keywords = {k: [str(x).upper() for x in v]
                                      for k, v in json.load(fh).items()}
            except Exception as e:
                logger.warning(f"pools.json tak terbaca ({e}) — routing pool dilewati")
    summary = ", ".join(f"{k}:{len(v)}" for k, v in _pools.items())
    logger.info(f"Loaded {len(_photo_list)} foto | pool[{summary}] | routing:{list(_pool_keywords)}")


def _route_pool(nama_kec: str) -> str:
    """Pilih pool dari kecamatan pelanggan. Cocok keyword -> pool itu (walau kosong,
    supaya pelanggan Melak/Kota Bangun TIDAK dapat foto Kota Samarinda — hindari foto
    salah-area). Tidak cocok -> pool default."""
    kec = (nama_kec or "").upper()
    for pool, kws in _pool_keywords.items():
        if any(k in kec for k in kws):
            return pool
    return DEFAULT_POOL

# --- Auth middleware ---

def require_api_key(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        if not API_KEYS:
            return f(*args, **kwargs)
        key = request.headers.get("X-API-Key") or request.args.get("api_key")
        if not key or key not in API_KEYS:
            logger.warning(f"Unauthorized request from {request.remote_addr}")
            return jsonify({"error": "Unauthorized"}), 401
        return f(*args, **kwargs)
    return decorated

# --- Helpers ---

def construct_pln_alamat(p: dict) -> str:
    parts = []
    pnj = str(p.get("pnj") or "").strip()
    namapnj = str(p.get("namapnj") or "").strip()
    nobang = str(p.get("nobang") or "").strip()
    rt = str(p.get("rt") or "").strip()
    rw = str(p.get("rw") or "").strip()
    lingkungan = str(p.get("lingkungan") or "").strip()
    alamat_raw = str(p.get("alamat") or p.get("alamat_51") or "").strip()

    if pnj and namapnj:
        parts.append(f"{pnj} {namapnj}")
    elif namapnj:
        parts.append(namapnj)
    elif alamat_raw:
        parts.append(alamat_raw)
    if nobang:
        parts.append(f"NO. {nobang}")
    if rt:
        parts.append(f"RT. {rt}")
    if rw:
        parts.append(f"RW. {rw}")
    if lingkungan:
        parts.append(lingkungan)
    res = " ".join(parts).strip()
    return res if res else alamat_raw


def extract_profile_data(raw_data: dict) -> dict:
    """Extract clean profile from raw PLN response."""
    profiles = raw_data.get("dil_main", raw_data.get("list", raw_data.get("lInfoMasterNedisys", [])))
    if not profiles:
        return {}
    p = profiles[0]

    from submit_fasih import clean_pln_name
    nama = clean_pln_name(str(p.get("nama", "")).strip())
    alamat = construct_pln_alamat(p)

    # Validate nometer: real PLN meter numbers are 10-11 digits
    nometer_raw = ""
    for nk in ("nometer_kwh", "nomor_meter_kwh", "no_meter_kwh",
               "no_meter", "nomor_meter", "nometer", "meter_number"):
        candidate = str(p.get(nk) or "").strip()
        if candidate and len(candidate) >= 8 and candidate.isdigit():
            nometer_raw = candidate
            break

    # Derive explicit product type from AP2T ket_produk field
    ket_produk_raw = str(p.get("ket_produk") or "").strip()
    tarif_raw = str(p.get("tarif") or p.get("gol_tarif") or "").strip()
    if "prabayar" in ket_produk_raw.lower():
        produk = "PRABAYAR"
    elif "pascabayar" in ket_produk_raw.lower() or "postpaid" in ket_produk_raw.lower():
        produk = "PASCABAYAR"
    elif tarif_raw.upper().endswith("M"):
        produk = "PRABAYAR"
    else:
        produk = "PASCABAYAR"

    return {
        "idpel": str(p.get("id_pelanggan") or p.get("idpel") or "").strip(),
        "nometer": nometer_raw,
        "nama": nama,
        "alamat": alamat,
        "nik": str(p.get("noidentitas") or p.get("no_identitas") or "").strip(),
        "no_hp": str(p.get("notelp_hp") or p.get("notelp") or "").strip(),
        "tarif": tarif_raw,
        "daya": str(p.get("daya") or p.get("daya_51") or "").strip(),
        "produk": produk,
        "keperluan": str(p.get("keperluan") or "").strip(),
        "kd_prov": str(p.get("kd_prov") or "").strip(),
        "kd_kab": str(p.get("kd_kab") or "").strip(),
        "kd_kec": str(p.get("kd_kec") or "").strip(),
        "kd_kel": str(p.get("kd_kel") or "").strip(),
        "nama_prov": str(p.get("nama_prov") or "").strip(),
        "nama_kab": str(p.get("nama_kab") or "").strip(),
        "nama_kec": str(p.get("nama_kec") or "").strip(),
        "nama_kel": str(p.get("nama_kel") or "").strip(),
        "latitude": str(p.get("koordinat_y") or p.get("latitude") or "").strip(),
        "longitude": str(p.get("koordinat_x") or p.get("longitude") or "").strip(),
        "raw": p,
    }


# --- Routes ---

@app.route("/health")
def health():
    return jsonify({
        "status": "ok",
        "region": REGION,
        "photos": len(_photo_list),
        "time": datetime.now().isoformat()
    })


@app.route("/api/lookup", methods=["GET"])
@require_api_key
def lookup():
    """
    PLN AP2T lookup by IDPel or NoMeter.
    Query params: idpel=xxx OR nometer=xxx
    Returns: cleaned profile data + random photo URL
    """
    idpel = request.args.get("idpel", "").strip()
    nometer = request.args.get("nometer", "").strip()

    if not idpel and not nometer:
        return jsonify({"error": "Parameter idpel atau nometer wajib diisi"}), 400

    tool = get_pln_tool()
    try:
        if idpel and len(idpel) == 12:
            raw = tool.lookup_by_idpel(idpel)
        elif nometer and len(nometer) == 11:
            raw = tool.lookup_by_nometer(nometer)
        elif idpel:
            raw = tool.lookup_by_idpel(idpel)
            if not raw:
                raw = tool.lookup_by_nometer(idpel)
        else:
            raw = tool.lookup_by_nometer(nometer)
            if not raw:
                raw = tool.lookup_by_idpel(nometer)
    except Exception as e:
        logger.error(f"PLN lookup error: {e}", exc_info=True)
        return jsonify({"error": f"Lookup gagal: {str(e)}"}), 500

    if not raw:
        return jsonify({"error": "Data tidak ditemukan", "query": idpel or nometer}), 404

    profile = extract_profile_data(raw)
    if not profile:
        return jsonify({"error": "Profil kosong dari response PLN", "query": idpel or nometer}), 404

    # Enrich with second lookup if nama missing but idpel available
    if (not profile.get("nama") or profile["nama"] == "NoName") and profile.get("idpel"):
        try:
            second = tool.lookup_by_idpel(profile["idpel"])
            if second:
                second_profile = extract_profile_data(second)
                for fk in ("nama", "nik", "alamat", "tarif", "daya", "nometer",
                           "latitude", "longitude", "keperluan",
                           "kd_prov", "kd_kab", "kd_kec", "kd_kel",
                           "nama_prov", "nama_kab", "nama_kec", "nama_kel"):
                    sv = second_profile.get(fk) or ""
                    if sv and (sv != "NoName") and not profile.get(fk):
                        profile[fk] = sv
        except Exception:
            pass

    # Attach photo URL
    photo_id = _pick_photo_id(profile.get("nama_kec", ""))
    if photo_id:
        profile["photo_url"] = f"/api/photo/{photo_id}"
    else:
        profile["photo_url"] = None

    # Remove raw from response (too big)
    profile.pop("raw", None)
    logger.info(f"Lookup OK: {idpel or nometer} → {profile.get('nama', '?')}")
    return jsonify(profile)


@app.route("/api/photo/random")
@require_api_key
def random_photo():
    """Returns a random house photo file."""
    if not _photo_list:
        abort(404)
    path = random.choice(_photo_list)
    return send_file(path)


@app.route("/api/photo/<photo_id>")
@require_api_key
def get_photo(photo_id):
    """Returns a specific photo by its stable hash ID."""
    for p in _photo_list:
        if _hash_path(p) == photo_id:
            return send_file(p)
    abort(404)


@app.route("/api/config")
@require_api_key
def region_config():
    """Config wilayah untuk klien petugas (auth-gated → hanya petugas wilayah ini).

    Server yang membagikan MAPBOX_ACCESS_TOKEN wilayahnya, supaya HP petugas otomatis
    memakai token wilayahnya sendiri cukup dengan `fasih-region <wilayah>` + fasih-update
    — tanpa menyetel .env di tiap HP, dan token tidak pernah masuk git (repo publik).
    Kosong = klien pakai .env lokalnya sendiri (fallback)."""
    return jsonify({
        "region": REGION,
        "mapbox_token": os.getenv("MAPBOX_ACCESS_TOKEN", ""),
    })


@app.route("/api/photos/count")
@require_api_key
def photo_count():
    return jsonify({"count": len(_photo_list)})


def _hash_path(path: str) -> str:
    return hashlib.md5(os.path.basename(path).encode()).hexdigest()[:12]


def _pick_photo_id(nama_kec: str = "") -> str | None:
    """Foto acak dari pool sesuai area pelanggan. Pool kosong -> None (bukan foto
    area lain)."""
    pics = _pools.get(_route_pool(nama_kec)) or []
    return _hash_path(random.choice(pics)) if pics else None


# --- Main ---

if __name__ == "__main__":
    load_photos()

    port = int(os.getenv("PLN_API_PORT", "8900"))
    host = os.getenv("PLN_API_HOST", "0.0.0.0")

    if not API_KEYS:
        logger.warning("No PLN_API_KEYS set — API is OPEN to anyone! Set PLN_API_KEYS in .env")

    logger.info(f"Starting PLN API server on {host}:{port}")
    logger.info(f"Photos loaded: {len(_photo_list)}")
    logger.info(f"API keys configured: {len(API_KEYS)}")

    app.run(host=host, port=port, debug=False, threaded=True)
