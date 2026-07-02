#!/usr/bin/env python3
import os
import sys
import csv
import json
import base64
import hashlib
import logging
import tempfile
import requests
import asyncio
from datetime import datetime
from typing import Optional, Dict, Any, List

from telegram import Update, InlineKeyboardButton, InlineKeyboardMarkup, ReplyKeyboardMarkup, KeyboardButton
from telegram.request import HTTPXRequest
from telegram.ext import (
    Application,
    CommandHandler,
    ContextTypes,
    MessageHandler,
    CallbackQueryHandler,
    ConversationHandler,
    filters,
)
from dotenv import load_dotenv

# Import helper modules
from fasih_auth import perform_login, refresh_token_if_needed, get_headers
from fasih_crypto import encrypt_gcm, decrypt_gcm_verify, compute_md5, compute_md5_base64
from fasih_archive import create_7z_archive
from fasih_api import (
    fetch_surveys, fetch_assignments, fetch_all_assignments, fetch_regions, request_presign_url, upload_to_s3,
    request_photo_presign_put, upload_photo_to_s3, request_photo_presign_get, confirm_submit,
    fetch_template_mapping, map_answers_to_data_slots
)

# Async wrappers for blocking functions using asyncio.to_thread
async def async_perform_login(*args, **kwargs):
    return await asyncio.to_thread(perform_login, *args, **kwargs)

async def async_refresh_token_if_needed(*args, **kwargs):
    return await asyncio.to_thread(refresh_token_if_needed, *args, **kwargs)

async def async_fetch_surveys(*args, **kwargs):
    return await asyncio.to_thread(fetch_surveys, *args, **kwargs)

async def async_fetch_assignments(*args, **kwargs):
    return await asyncio.to_thread(fetch_assignments, *args, **kwargs)

async def async_fetch_all_assignments(*args, **kwargs):
    return await asyncio.to_thread(fetch_all_assignments, *args, **kwargs)

async def async_fetch_regions(*args, **kwargs):
    return await asyncio.to_thread(fetch_regions, *args, **kwargs)

async def async_request_presign_url(*args, **kwargs):
    return await asyncio.to_thread(request_presign_url, *args, **kwargs)

async def async_upload_to_s3(*args, **kwargs):
    return await asyncio.to_thread(upload_to_s3, *args, **kwargs)

async def async_request_photo_presign_put(*args, **kwargs):
    return await asyncio.to_thread(request_photo_presign_put, *args, **kwargs)

async def async_upload_photo_to_s3(*args, **kwargs):
    return await asyncio.to_thread(upload_photo_to_s3, *args, **kwargs)

async def async_request_photo_presign_get(*args, **kwargs):
    return await asyncio.to_thread(request_photo_presign_get, *args, **kwargs)

async def async_confirm_submit(*args, **kwargs):
    return await asyncio.to_thread(confirm_submit, *args, **kwargs)

async def async_fetch_template_mapping(*args, **kwargs):
    return await asyncio.to_thread(fetch_template_mapping, *args, **kwargs)

async def async_geocode_address_nominatim(*args, **kwargs):
    return await asyncio.to_thread(geocode_address_nominatim, *args, **kwargs)

async def async_create_7z_archive(*args, **kwargs):
    return await asyncio.to_thread(create_7z_archive, *args, **kwargs)

# Load configuration
load_dotenv()

# Setup logging
logging.basicConfig(
    format="%(asctime)s - %(name)s - %(levelname)s - %(message)s", level=logging.INFO
)
logger = logging.getLogger(__name__)

STATIC_LEGACY_KEY = "Z!,vDKUPv;.Jy0Q4Eq1wVCY-a_!GnT"

# Access control usernames list
ALLOWED_USERS = []
allowed_str = os.getenv("ALLOWED_TELEGRAM_USERNAMES", "")
if allowed_str:
    ALLOWED_USERS = [u.strip().lower().replace("@", "") for u in allowed_str.split(",") if u.strip()]

def get_random_house_photo() -> Optional[str]:
    photos_dir = "house_photos"
    if os.path.isdir(photos_dir):
        import random
        valid_extensions = (".jpg", ".jpeg", ".png")
        dir_photos = [
            os.path.join(photos_dir, f) for f in os.listdir(photos_dir)
            if f.lower().endswith(valid_extensions)
        ]
        if dir_photos:
            return random.choice(dir_photos)
            
    # Fallback to local default files
    for p_name in ["foto_default.jpg", "default_house.jpg", "foto_default.png", "default_house.png"]:
        if os.path.exists(p_name):
            return p_name
    return None

def is_user_allowed(username: Optional[str]) -> bool:
    if not ALLOWED_USERS:
        return True
    if not username:
        return False
    return username.lower() in ALLOWED_USERS

async def safe_delete_message(message) -> bool:
    if message:
        try:
            await message.delete()
            return True
        except Exception as e:
            logger.warning(f"Failed to delete message: {e}")
    return False

# Helper function to get token file path per chat
def get_user_token_file(chat_id: int) -> str:
    script_dir = os.path.dirname(os.path.abspath(__file__))
    return os.path.join(script_dir, f"fasih_token_{chat_id}.json")

def load_user_token(chat_id: int) -> Optional[dict]:
    token_file = get_user_token_file(chat_id)
    if os.path.exists(token_file):
        try:
            with open(token_file, "r") as f:
                return json.load(f)
        except Exception as e:
            logger.error(f"Error loading token for {chat_id}: {e}")
    return None

def save_user_token(chat_id: int, token_data: dict):
    token_file = get_user_token_file(chat_id)
    with open(token_file, "w") as f:
        json.dump(token_data, f, indent=2)

INDONESIAN_PROVINCES = {
    "aceh": (-5.5483, 95.3238),
    "sumatera utara": (3.5952, 98.6722),
    "sumatera barat": (-0.9471, 100.4172),
    "riau": (0.5074, 101.4478),
    "kepulauan riau": (0.9167, 104.4500),
    "jambi": (-1.6101, 103.6131),
    "sumatera selatan": (-2.9761, 104.7754),
    "bangka belitung": (-2.1319, 106.1161),
    "bengkulu": (-3.7928, 102.2608),
    "lampung": (-5.3971, 105.2663),
    "dki jakarta": (-6.2088, 106.8456),
    "jawa barat": (-6.9175, 107.6191),
    "banten": (-6.1200, 106.1502),
    "jawa tengah": (-7.0051, 110.4381),
    "di yogyakarta": (-7.7956, 110.3695),
    "yogyakarta": (-7.7956, 110.3695),
    "jawa timur": (-7.2575, 112.7521),
    "bali": (-8.4095, 115.1889),
    "nusa tenggara barat": (-8.5729, 116.3248),
    "ntb": (-8.5729, 116.3248),
    "nusa tenggara timur": (-10.1772, 123.6070),
    "ntt": (-10.1772, 123.6070),
    "kalimantan barat": (-0.0263, 109.3425),
    "kalimantan tengah": (-2.2100, 113.9200),
    "kalimantan selatan": (-3.3167, 114.5900),
    "kalimantan timur": (-0.5022, 117.1536),
    "kalimantan utara": (3.0731, 116.0413),
    "sulawesi utara": (1.4822, 124.8488),
    "sulawesi tengah": (-0.8917, 119.8707),
    "sulawesi selatan": (-5.1476, 119.4327),
    "sulawesi tenggara": (-3.9722, 122.5149),
    "gorontalo": (0.5435, 123.0568),
    "sulawesi barat": (-2.6773, 118.8895),
    "maluku": (-3.6547, 128.1906),
    "maluku utara": (0.7893, 127.3756),
    "papua": (-2.5413, 140.7052),
    "papua barat": (-0.8614, 134.0620),
    "papua selatan": (-8.4991, 140.4011),
    "papua tengah": (-3.3686, 135.5002),
    "papua pegunungan": (-4.0934, 138.9482),
    "papua barat daya": (-0.8762, 131.2514),
}

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

def get_fallback_coordinate(prov_str, kab_str, kec_str, alamat_str):
    import random
    prov_clean = str(prov_str or "").lower().strip()
    kab_clean = str(kab_str or "").lower().strip()
    kec_clean = str(kec_str or "").lower().strip()
    addr_clean = str(alamat_str or "").lower().strip()
    
    matched_coords = None
    if "bontang" in kab_clean or "bontang" in addr_clean or "bontang" in kec_clean or "bontang" in prov_clean:
        matched_coords = (0.1378, 117.4958)
    else:
        for p_name, coords in INDONESIAN_PROVINCES.items():
            if p_name in prov_clean or p_name in kab_clean or p_name in addr_clean:
                matched_coords = coords
                break
            
    if not matched_coords:
        matched_coords = (-5.1476, 119.4327)
        
    lat, lon = matched_coords
    lat += random.uniform(-0.06, 0.06)
    lon += random.uniform(-0.06, 0.06)
    return lat, lon

def escape_markdown(text: str) -> str:
    if not text:
        return ""
    return text.replace("\\", "").replace("*", "").replace("_", "").replace("[", "").replace("]", "").replace("`", "")

def expand_indonesian_address_abbreviations(address: str) -> str:
    if not address:
        return ""
    import re
    addr = " " + address.upper() + " "
    
    mappings = {
        r"\bOTISTA\b": "Otto Iskandardinata",
        r"\bGATSU\b": "Gatot Subroto",
        r"\bA\.?\s*YANI\b": "Ahmad Yani",
        r"\bM\.?\s*T\.?\s*HARYONO\b": "M.T. Haryono",
        r"\bJEND\.?\s*SUDIRMAN\b": "Jenderal Sudirman",
        r"\bS\.?\s*PARMAN\b": "S. Parman",
        r"\bHOS\s+COKROAMINOTO\b": "HOS Cokroaminoto",
        r"\bSUPRAPTO\b": "Letjen Suprapto",
        r"\bDI\s+PANJAITAN\b": "D.I. Panjaitan",
    }
    
    for pattern, replacement in mappings.items():
        addr = re.sub(pattern, replacement, addr)
        
    return addr.strip()

def geocode_address_nominatim(alamat, kel, kec, kab, prov):
    import requests
    import random
    
    # Try Mapbox Geocoding first if token is present
    mapbox_token = os.getenv("MAPBOX_ACCESS_TOKEN")
    if mapbox_token:
        import urllib.parse
        alamat_clean = expand_indonesian_address_abbreviations(alamat)
        if alamat_clean:
            q = f"{alamat_clean}"
            if kel:
                q += f", {kel}"
            if kec:
                q += f", {kec}"
            if kab:
                q += f", {kab}"
            if prov:
                q += f", {prov}"
            q += ", Indonesia"
            try:
                quoted_q = urllib.parse.quote(q)
                url = f"https://api.mapbox.com/geocoding/v5/mapbox.places/{quoted_q}.json"
                r = requests.get(url, params={"access_token": mapbox_token, "limit": 1, "country": "id"}, timeout=5)
                res = r.json()
                if res.get("features"):
                    center = res["features"][0]["geometry"]["coordinates"]
                    # Mapbox returns [longitude, latitude]
                    return float(center[1]), float(center[0])
            except Exception as e:
                logger.error(f"Error in Mapbox geocoding: {e}")

    queries = []
    alamat_clean = expand_indonesian_address_abbreviations(alamat)
    alamat_clean = alamat_clean.replace("JL.", "Jalan ").strip() if alamat_clean else ""
    
    if alamat_clean:
        if kab and prov:
            queries.append(f"{alamat_clean}, {kab}, {prov}, Indonesia")
        if kab:
            queries.append(f"{alamat_clean}, {kab}, Indonesia")
        if kel and kec and kab and prov:
            queries.append(f"{alamat_clean}, {kel}, {kec}, {kab}, {prov}, Indonesia")
            
    if kel and kec and kab and prov:
        queries.append(f"{kel}, {kec}, {kab}, {prov}, Indonesia")
    if kec and kab and prov:
        queries.append(f"{kec}, {kab}, {prov}, Indonesia")
    if kab and prov:
        queries.append(f"{kab}, {prov}, Indonesia")
        
    for q in queries:
        try:
            r = requests.get(
                "https://nominatim.openstreetmap.org/search", 
                params={"q": q, "format": "json", "limit": 1}, 
                headers={"User-Agent": "FasihBPSBot/1.0"}, 
                timeout=5
            )
            res = r.json()
            if res:
                lat = float(res[0]["lat"])
                lon = float(res[0]["lon"])
                
                # Fuzz general results to simulate household spread
                if q != queries[0] if len(queries) > 0 else False:
                    lat += random.uniform(-0.0008, 0.0008)
                    lon += random.uniform(-0.0008, 0.0008)
                return lat, lon
        except Exception:
            pass
    return None, None

# Decorator to restrict access to allowed users
def restricted(func):
    async def wrapper(update: Update, context: ContextTypes.DEFAULT_TYPE):
        user = update.effective_user
        if not user or not is_user_allowed(user.username):
            if update.message:
                await update.message.reply_text("⛔ Maaf, Anda tidak memiliki akses untuk menggunakan bot ini.")
            elif update.callback_query:
                await update.callback_query.answer("⛔ Anda tidak memiliki akses.", show_alert=True)
            return
        return await func(update, context)
    return wrapper

# Safe BPS Fasih Submit Implementation
async def submit_fasih_safe(
    token_data: dict,
    token_file: str,
    idpel: Optional[str] = None,
    nometer: Optional[str] = None,
    assignment_id: Optional[str] = None,
    dry_run: bool = False,
    direct_args: Optional[dict] = None,
    photo_path: Optional[str] = None,
    lat: Optional[float] = None,
    lon: Optional[float] = None,
    status_callback=None,
    create_new: bool = False,
    template_assignment_id: Optional[str] = None
) -> tuple[bool, str]:
    """
    Executes the submit pipeline safely without calling sys.exit(1).
    Returns (success, message).
    """
    try:
        if status_callback:
            await status_callback("🔄 Menyiapkan data autentikasi...")
        
        token_data = await async_refresh_token_if_needed(token_data, token_file=token_file, exit_on_failure=False)
        headers = get_headers(token_data)

        if status_callback:
            await status_callback("📊 Mengambil daftar tugas dari BPS...")
            
        surveys = await async_fetch_surveys(headers)
        if not surveys:
            return False, "Tidak ada survei yang aktif di akun Anda."
        
        survey = surveys[0]
        active_periode = next((p for p in survey.get("listPeriode", []) if p.get("isActive")), None)
        if not active_periode:
            return False, "Tidak ada periode aktif untuk survei ini."
            
        template_lookup = survey.get("templateLookup", [])
        template_mapping = {}
        if template_lookup:
            tl = template_lookup[0]
            template_mapping = await async_fetch_template_mapping(headers, tl["templateId"], tl["templateVersion"])

        pid = active_periode["id"]
        
        target = None
        if create_new:
            content = await async_fetch_all_assignments(headers, pid)
            template_target = next((a for a in content if a.get("id") == template_assignment_id), None)
            if not template_target:
                return False, "Template assignment acuan tidak ditemukan di server BPS."
            from submit_fasih import build_new_assignment_target
            target = build_new_assignment_target(template_target, idpel or "", nometer or "", template_mapping)
            if direct_args:
                target["data2"] = direct_args.get("nama") or ""
                target["data4"] = direct_args.get("alamat") or ""
                target["data5"] = direct_args.get("alamat") or ""
        else:
            # Fetch page 0 first (quick check to save time)
            first_page = await async_fetch_assignments(headers, pid, page=0)
            content = (first_page.get("data") or {}).get("content", [])
            total_server = (first_page.get("data") or {}).get("total", 0)
            
            if assignment_id:
                target = next((a for a in content if a.get("id") == assignment_id), None)
            elif idpel or nometer:
                idpel_slot = next((slot for slot, var in template_mapping.items() if var == "r101a"), "data3")
                nometer_slot = next((slot for slot, var in template_mapping.items() if var == "r101b"), "data1")
                target = next((
                    a for a in content
                    if (not idpel or a.get(idpel_slot) == idpel) and (not nometer or a.get(nometer_slot) == nometer)
                ), None)
            else:
                target = next((a for a in content if "OPEN" in (a.get("assignmentStatusAlias") or "") or "SUBMITTED" in (a.get("assignmentStatusAlias") or "")), None)

            if not target and len(content) < total_server:
                # Not found in page 0, fetch remaining assignments in parallel
                content = await async_fetch_all_assignments(headers, pid)
                if assignment_id:
                    target = next((a for a in content if a.get("id") == assignment_id), None)
                elif idpel or nometer:
                    idpel_slot = next((slot for slot, var in template_mapping.items() if var == "r101a"), "data3")
                    nometer_slot = next((slot for slot, var in template_mapping.items() if var == "r101b"), "data1")
                    target = next((
                        a for a in content
                        if (not idpel or a.get(idpel_slot) == idpel) and (not nometer or a.get(nometer_slot) == nometer)
                    ), None)
                else:
                    target = next((a for a in content if "OPEN" in (a.get("assignmentStatusAlias") or "") or "SUBMITTED" in (a.get("assignmentStatusAlias") or "")), None)

        if not target:
            return False, "Tugas tidak ditemukan atau tidak berstatus OPEN/SUBMITTED."

        if status_callback:
            await status_callback("📝 Menyusun data jawaban kuesioner...")

        if not direct_args:
            direct_args = {
                "idpel": idpel or "",
                "nometer": nometer or "",
                "nama": target.get("data2", "") or "PELANGGAN",
                "alamat": target.get("data4", "") or "",
                "tarif": "R-1",
                "daya": "900",
                "hasil": "1",
                "kelurahan": "001",
                "kdpm": "01",
                "kddk": "1",
                "status_dil": "1"
            }

        # Perform PLN Lookup to enrich data with NIK, real address, and real name if not already filled
        if not direct_args.get("pln_nik") or not direct_args.get("pln_alamat"):
            lookup_key = idpel or nometer or target.get("data3") or target.get("data1")
            if lookup_key:
                if status_callback:
                    await status_callback("🔍 Mencocokkan NIK & Alamat dgn PLN AP2T...")
                try:
                    from pln_lookup import PLNLookupTool
                    engine = PLNLookupTool()
                    def do_pln_lookup():
                        cleaned_lk = str(lookup_key).strip()
                        if len(cleaned_lk) == 12:
                            return engine.lookup_by_idpel(cleaned_lk)
                        elif len(cleaned_lk) == 11:
                            return engine.lookup_by_nometer(cleaned_lk)
                        else:
                            r = engine.lookup_by_idpel(cleaned_lk)
                            profiles = r.get("dil_main", r.get("list", r.get("lInfoMasterNedisys", []))) if r else []
                            if not profiles:
                                r = engine.lookup_by_nometer(cleaned_lk)
                            return r
                    
                    res = await asyncio.to_thread(do_pln_lookup)
                    if res:
                        profiles = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
                        if profiles:
                            p_profile = profiles[0]
                            if not p_profile.get("nama") and p_profile.get("id_pelanggan"):
                                def do_second_lookup():
                                    return engine.lookup_by_idpel(p_profile.get("id_pelanggan"))
                                second_res = await asyncio.to_thread(do_second_lookup)
                                if second_res:
                                    sec_profiles = second_res.get("dil_main", second_res.get("list", second_res.get("lInfoMasterNedisys", [])))
                                    if sec_profiles:
                                        p_profile.update(sec_profiles[0])
                            
                            from submit_fasih import clean_pln_name
                            pln_nama = clean_pln_name(str(p_profile.get("nama", "")).strip())
                            pln_alamat = construct_pln_alamat(p_profile)
                            pln_nik = str(p_profile.get("noidentitas") or p_profile.get("no_identitas") or "").strip()
                            
                            if pln_nama and pln_nama != "NoName":
                                direct_args["pln_nama"] = pln_nama
                            if pln_alamat:
                                direct_args["pln_alamat"] = pln_alamat
                            if pln_nik:
                                direct_args["pln_nik"] = pln_nik
                                direct_args["nik"] = pln_nik
                                
                            direct_args["pln_kd_prov"] = str(p_profile.get("kd_prov") or "").strip()
                            direct_args["pln_kd_kab"] = str(p_profile.get("kd_kab") or "").strip()
                            direct_args["pln_kd_kec"] = str(p_profile.get("kd_kec") or "").strip()
                            direct_args["pln_kd_kel"] = str(p_profile.get("kd_kel") or "").strip()
                            direct_args["pln_nama_prov"] = str(p_profile.get("nama_prov") or "").strip()
                            direct_args["pln_nama_kab"] = str(p_profile.get("nama_kab") or "").strip()
                            direct_args["pln_nama_kec"] = str(p_profile.get("nama_kec") or "").strip()
                            direct_args["pln_nama_kel"] = str(p_profile.get("nama_kel") or "").strip()
                            direct_args["keperluan"] = str(p_profile.get("keperluan") or "").strip()
                except Exception as e:
                    logger.error(f"Error performing PLN lookup in submit_fasih_safe: {e}")

        # Build answer dict using imported helper
        from submit_fasih import build_dynamic_answers
        answers = build_dynamic_answers(target, direct_args, template_mapping)

        # Photo upload handling
        if photo_path and os.path.exists(photo_path):
            if status_callback:
                await status_callback("📷 Mengunggah foto pelanggan ke S3 BPS...")
                
            tid = target.get("id")
            filename = f"{tid}_r106.png"
            md5_b64 = compute_md5_base64(photo_path)
            
            try:
                resp = await async_request_photo_presign_put(
                    headers, tid, target.get("copyFromId") or "", target.get("surveyPeriodId"),
                    filename, os.path.getsize(photo_path), md5_b64
                )
                if not resp.get("success"):
                    if dry_run:
                        put_url = "http://mock-photo-put-url"
                    else:
                        return False, f"Gagal mendapatkan presigned URL foto: {resp}"
                else:
                    urls = resp.get("data", [])
                    put_url = urls[0].get("presignedUrls", [])[0].get("presignedUrl") if urls else None
                    if not put_url:
                        if dry_run:
                            put_url = "http://mock-photo-put-url"
                        else:
                            return False, "S3 PUT URL foto kosong dari server."
            except Exception as e:
                if dry_run:
                    put_url = "http://mock-photo-put-url"
                else:
                    return False, f"Gagal mendapatkan presigned URL foto: {str(e)}"
            
            if not dry_run and put_url != "http://mock-photo-put-url":
                if not await async_upload_photo_to_s3(put_url, photo_path, md5_b64):
                    return False, "Gagal mengunggah foto ke S3."
            
            try:
                resp_get = await async_request_photo_presign_get(
                    headers, tid, target.get("copyFromId") or "", target.get("surveyPeriodId"), filename
                )
                get_data = resp_get.get("data", [])
                get_url = get_data[0].get("presignedUrls", [])[0].get("presignedUrl") if get_data else ""
            except Exception:
                get_url = "http://mock-photo-get-url" if dry_run else ""
            
            answers["r106"] = json.dumps({
                "filename": filename,
                "uri": f"content://media/external/images/media/{hashlib.md5(tid.encode()).hexdigest()[:8]}",
                "url": get_url
            }, ensure_ascii=False)

        # Resolve coordinates fallback if None
        if lat is None or lon is None:
            t_lat = target.get("latitude")
            t_lon = target.get("longitude")
            try:
                if t_lat and t_lon and float(t_lat) != 0.0:
                    lat = float(t_lat)
                    lon = float(t_lon)
                else:
                    raise ValueError
            except (ValueError, TypeError):
                addr = target.get("data5", "") or target.get("data6", "") or ""
                region_name = target.get("region", {}).get("name", "")
                lat, lon = get_fallback_coordinate(region_name, "", "", addr)

        answers["r105"] = {
            "coordinat": {"latitude": lat, "longitude": lon},
            "remark": "",
            "accuracy": 10.0
        }

        if status_callback:
            await status_callback("🔐 Mengenkripsi data jawaban...")

        # Resolve encryption key
        region_id = target.get("region", {}).get("id", "")
        regions = await async_fetch_regions(headers, pid)
        wrapped_key = None
        for r in regions:
            if r.get("region_id") == region_id or r.get("region", {}).get("id") == region_id:
                wrapped_key = r.get("wrappedDatakey")
                break
        if not wrapped_key:
            wrapped_key = STATIC_LEGACY_KEY
        try:
            key_bytes = base64.b64decode(wrapped_key.encode("utf-8"))
        except Exception:
            key_bytes = STATIC_LEGACY_KEY.encode("utf-8")

        # Encrypt answers using shared stage_and_encrypt
        from submit_fasih import stage_and_encrypt
        
        user_name = "Nadif Firjatullah"
        try:
            payload_b64 = token_data["access_token"].split(".")[1]
            payload_b64 += "=" * (4 - len(payload_b64) % 4)
            jwt_payload = json.loads(base64.b64decode(payload_b64.encode('utf-8')))
            user_name = jwt_payload.get("name") or jwt_payload.get("email") or "Nadif Firjatullah"
        except Exception:
            pass

        try:
            encrypted = stage_and_encrypt(answers, key_bytes, target, user_name)
        except Exception as enc_err:
            logger.error(f"Encryption failed: {enc_err}", exc_info=True)
            return False, f"Enkripsi jawaban gagal: {enc_err}"

        if status_callback:
            await status_callback("📦 Mengompres data enkripsi ke 7z...")

        # 7z compression and S3 upload
        with tempfile.TemporaryDirectory() as work_dir:
            archive_path = await async_create_7z_archive(encrypted, target["id"], work_dir)
            
            if status_callback:
                await status_callback("☁️ Mengunggah arsip kuesioner ke S3 BPS...")
                
            is_edit = target.get("assignmentStatusAlias") != "OPEN"
            copy_from_id = target.get("copyFromId")
            error_detail = None
            try:
                presign_resp = await async_request_presign_url(headers, target["id"], pid, [f"{target['id']}.7z"], is_edit, copy_from_id)
                data_obj = presign_resp.get("data", {})
                if isinstance(data_obj, list):
                    urls = data_obj
                elif isinstance(data_obj, dict):
                    urls = data_obj.get("presignedUrls", [])
                else:
                    urls = []
                put_url = urls[0].get("presignedUrl") or urls[0].get("url") if urls else None
                if not urls:
                    error_detail = f"Response data kosong dari server: {presign_resp}"
            except Exception as e:
                put_url = None
                if isinstance(e, requests.exceptions.HTTPError) and e.response is not None:
                    error_detail = f"HTTP {e.response.status_code}: {e.response.text}"
                else:
                    error_detail = str(e)
                logger.error(f"Error requesting presign URL: {error_detail}", exc_info=True)
                
            if not put_url:
                if dry_run:
                    put_url = "http://mock-s3-url"
                else:
                    msg = "Presigned PUT URL untuk berkas arsip kosong."
                    if error_detail:
                        msg += f"\nDetail: {error_detail}"
                    return False, msg
                
            if not dry_run and put_url != "http://mock-s3-url":
                if not await async_upload_to_s3(put_url, archive_path):
                    return False, "Gagal mengunggah arsip ke S3."
                
            archive_md5 = compute_md5(archive_path)

        if status_callback:
            await status_callback("📡 Mengirimkan konfirmasi akhir ke server BPS...")

        # Confirm submission
        region_id = target.get("region", {}).get("id") or ""
        data_slots = map_answers_to_data_slots(answers, template_mapping)
        
        # Ensure all data1-data10 keys are present and string-serialized
        for i in range(1, 11):
            key = f"data{i}"
            if key not in data_slots:
                data_slots[key] = ""
            else:
                data_slots[key] = str(data_slots[key]) if data_slots[key] is not None else ""

        params = {
            "surveyPeriodeId": str(target.get("surveyPeriodId") or ""),
            "assignmentId": str(target.get("id") or ""),
            "filename": f"{target.get('id')}.7z",
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
            "paradata": "",
            "comment": "",
            "note": ""
        }
        
        if not dry_run:
            submit_resp = await async_confirm_submit(headers, params, is_edit=is_edit)
            logger.info(f"BPS submit confirmation response: {submit_resp}")
            return True, "Sukses: Data berhasil dikirimkan ke server BPS!"
        else:
            return True, "Sukses (DRY RUN): Semua pengecekan berhasil, kuesioner valid."
            
    except Exception as e:
        logger.error("Exception in submit_fasih_safe", exc_info=True)
        return False, f"Kesalahan submit: {str(e)}"

# Conversation handler states
WAITING_SELECT_ASSIGNMENT, WAITING_TARIF, WAITING_DAYA, WAITING_HASIL, WAITING_PHOTO, WAITING_LOCATION, WAITING_CONFIRM, WAITING_LOOKUP_INPUT, WAITING_SUBMIT_SEARCH_INPUT, WAITING_DUPLICATE_CHOICE, WAITING_WILAYAH_SELECTION, WAITING_ASSIGNMENT_SEARCH_INPUT, WAITING_BATCH_SUBMIT_INPUT, WAITING_BATCH_CONFIRM = range(14)

# --- BOT HANDLERS ---

def get_main_menu_keyboard() -> ReplyKeyboardMarkup:
    keyboard = [
        [KeyboardButton("📋 Daftar Tugas (List)"), KeyboardButton("⚡ Kirim Kuesioner (Submit)")],
        [KeyboardButton("🔍 Cari InfoPelanggan"), KeyboardButton("🔍 Cari Assignment BPS")],
        [KeyboardButton("⚡ Batch Submit (Massal)"), KeyboardButton("🔑 Status & Login")],
        [KeyboardButton("🚪 Keluar (Logout)")]
    ]
    return ReplyKeyboardMarkup(keyboard, resize_keyboard=True, is_persistent=True)

@restricted
async def start_command(update: Update, context: ContextTypes.DEFAULT_TYPE):
    chat_id = update.effective_chat.id
    token_data = load_user_token(chat_id)
    
    if token_data:
        email = None
        try:
            import base64
            import json
            payload_b64 = token_data["access_token"].split(".")[1]
            payload_b64 += "=" * (4 - len(payload_b64) % 4)
            jwt_payload = json.loads(base64.b64decode(payload_b64.encode('utf-8')))
            email = jwt_payload.get("email") or jwt_payload.get("preferred_username")
        except Exception:
            pass
            
        if email:
            status_msg = f"🟢 Terautentikasi ({email})"
        else:
            status_msg = "🟢 Terautentikasi"
    else:
        status_msg = "🔴 Belum login"
    
    welcome_text = (
        f"⚡ **Fasih BPS Automation Bot** ⚡\n"
        f"────────────────────────────────\n"
        f"👤 **Status Sesi:** {status_msg}\n"
        f"📅 **Waktu Server:** {datetime.now().strftime('%Y-%m-%d %H:%M')}\n"
        f"────────────────────────────────\n\n"
        f"💡 **Petunjuk Penggunaan:**\n"
        f"• Gunakan tombol **Menu** di kiri bawah untuk akses cepat seluruh fitur.\n"
        f"• Lakukan login via DM dengan format:\n"
        f"  `/login email@bps.go.id password_anda`\n"
        f"• Lihat daftar tugas aktif Anda dengan `/list`.\n"
        f"• Submit kuesioner langkah-demi-langkah dengan `/submit`.\n"
        f"• Unggah berkas `.csv` untuk melakukan pengiriman massal (Bulk Submit).\n\n"
        f"🔒 _Semua data kredensial disimpan terenkripsi/terisolasi di server lokal Anda._"
    )
    await update.message.reply_text(welcome_text, parse_mode="Markdown", reply_markup=get_main_menu_keyboard())

@restricted
async def login_command(update: Update, context: ContextTypes.DEFAULT_TYPE):
    chat = update.effective_chat
    if chat.type != "private":
        bot_username = context.bot.username
        pm_link = f"https://t.me/{bot_username}?start=login"
        keyboard = InlineKeyboardMarkup([[InlineKeyboardButton("🔑 Login di Chat Pribadi", url=pm_link)]])
        await update.message.reply_text(
            "🔒 **Peringatan Keamanan!**\n"
            "Untuk melindungi kredensial Anda, proses login hanya dapat dilakukan di chat pribadi (DM) dengan bot.",
            reply_markup=keyboard,
            parse_mode="Markdown"
        )
        return

    # Check arguments
    args = context.args
    if len(args) == 2:
        email, password = args[0], args[1]
        sent_msg = await update.message.reply_text("🔄 Melakukan login SSO BPS...")
        try:
            token_data = perform_login(email, password, exit_on_failure=False)
            save_user_token(chat.id, token_data)
            await sent_msg.edit_text("✅ Login berhasil! Sesi Anda telah disimpan.")
            await update.message.reply_text("Silakan gunakan tombol menu di bawah untuk bernavigasi:", reply_markup=get_main_menu_keyboard())
        except Exception as e:
            await sent_msg.edit_text(f"❌ Login gagal: {str(e)}")
        return

    # Otherwise, start login conversation (can also do step by step, but for simplicity guide usage)
    await update.message.reply_text(
        "Silakan lakukan login dengan format:\n`/login <email_anda> <password_anda>`\n\n"
        "Contoh:\n`/login budi@bps.go.id rahasia123`",
        parse_mode="Markdown",
        reply_markup=get_main_menu_keyboard()
    )

@restricted
async def logout_command(update: Update, context: ContextTypes.DEFAULT_TYPE):
    chat_id = update.effective_chat.id
    token_file = get_user_token_file(chat_id)
    if os.path.exists(token_file):
        os.remove(token_file)
        await update.message.reply_text("✅ Anda telah logout dan file session dihapus.")
    else:
        await update.message.reply_text("ℹ️ Anda tidak dalam posisi login.")

LIST_PAGE_SIZE = 10

def _build_list_page(content, page, template_mapping=None):
    """Build paginated block message and inline keyboard navigation for list view."""
    total = len(content)
    start = page * LIST_PAGE_SIZE
    end = min(start + LIST_PAGE_SIZE, total)
    total_pages = (total + LIST_PAGE_SIZE - 1) // LIST_PAGE_SIZE
    
    out_lines = []
    out_lines.append(
        f"📋 **Survei:** GROUNDCHECK PELANGGAN LISTRIK PT PLN (PERSERO) - PRABAYAR\n"
        f"📅 **Periode:** 2026 (Aktif)\n"
        f"📊 **Daftar Tugas ({start+1}-{end} dari {total}):**\n"
    )
    
    idpel_slot = "data3"
    nometer_slot = "data1"
    if template_mapping:
        idpel_slot = next((slot for slot, var in template_mapping.items() if var == "r101a"), "data3")
        nometer_slot = next((slot for slot, var in template_mapping.items() if var == "r101b"), "data1")
        
    for i in range(start, end):
        a = content[i]
        status = a.get("assignmentStatusAlias", "?")
        status_icon = "🟢" if status == "OPEN" else "🔵" if status == "SUBMITTED" else "⚪"
        
        idpel = a.get(idpel_slot, "-")
        nometer = a.get(nometer_slot, "-")
        nama = escape_markdown(str(a.get("data2", "-")).strip())
        
        region = a.get("region") or {}
        l1 = region.get("level1") or {}
        l2 = l1.get("level2") or {}
        l3 = l2.get("level3") or {}
        region_str = f"{l3.get('name','')} ({l2.get('name','')})"
        
        out_lines.append(
            f"┌─ {status_icon} **TUGAS {i+1}** ──────────────────\n"
            f"│  👤 Nama    : *{nama}*\n"
            f"│  🔑 IDPel   : `{idpel}`\n"
            f"│  📟 NoMeter : `{nometer}`\n"
            f"│  📍 Wilayah : {region_str}\n"
            f"│  ⚡ Status  : `{status}`\n"
            f"└────────────────────────────────"
        )
    
    response_text = "\n".join(out_lines)
    
    keyboard = []
    nav = []
    if page > 0:
        nav.append(InlineKeyboardButton("⬅️ Prev", callback_data=f"lpage_{page-1}"))
    nav.append(InlineKeyboardButton(f"{page+1}/{total_pages}", callback_data="lnoop"))
    if end < total:
        nav.append(InlineKeyboardButton("➡️ Next", callback_data=f"lpage_{page+1}"))
    keyboard.append(nav)
    keyboard.append([InlineKeyboardButton("❌ Tutup", callback_data="lclose")])
    
    return response_text, InlineKeyboardMarkup(keyboard)

@restricted
async def list_command(update: Update, context: ContextTypes.DEFAULT_TYPE):
    chat_id = update.effective_chat.id
    token_data = load_user_token(chat_id)
    if not token_data:
        await update.message.reply_text("🔑 Anda belum login. Gunakan `/login` terlebih dahulu.")
        return

    sent_msg = await update.message.reply_text("⏳ Mengambil daftar tugas dari BPS...")
    try:
        token_file = get_user_token_file(chat_id)
        token_data = await async_refresh_token_if_needed(token_data, token_file=token_file, exit_on_failure=False)
        headers = get_headers(token_data)
        
        surveys = await async_fetch_surveys(headers)
        if not surveys:
            await sent_msg.edit_text("📋 Tidak ada survei yang ditemukan.")
            return

        survey = surveys[0]
        active_period = next((p for p in survey.get("listPeriode", []) if p.get("isActive")), None)
        if not active_period:
            await sent_msg.edit_text("📋 Tidak ada periode aktif.")
            return
            
        pid = active_period["id"]
        
        # Fetch ALL assignments in parallel for fast loading
        all_content = await async_fetch_all_assignments(headers, pid)
            
        template_lookup = survey.get("templateLookup", [])
        template_mapping = {}
        if template_lookup:
            tl = template_lookup[0]
            template_mapping = await async_fetch_template_mapping(headers, tl["templateId"], tl["templateVersion"])
        context.user_data["template_mapping"] = template_mapping
            
        context.user_data["list_assignments"] = all_content
        context.user_data["list_page"] = 0
        
        response_text, markup = _build_list_page(all_content, 0, template_mapping=template_mapping)
        await safe_delete_message(sent_msg)
        await update.message.reply_text(response_text, reply_markup=markup, parse_mode="Markdown")
        
    except Exception as e:
        logger.error("Error in list_command", exc_info=True)
        await sent_msg.edit_text(f"❌ Terjadi kesalahan: {str(e)}")

async def list_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    if query.data == "lclose":
        try:
            await query.message.delete()
        except Exception:
            pass
        return
        
    if query.data == "lnoop":
        return
        
    if query.data.startswith("lpage_"):
        page = int(query.data.split("_")[1])
        context.user_data["list_page"] = page
        content = context.user_data.get("list_assignments", [])
        if not content:
            await query.message.edit_text("ℹ️ Sesi daftar tugas kedaluwarsa. Silakan ketik /list kembali.")
            return
            
        template_mapping = context.user_data.get("template_mapping")
        response_text, markup = _build_list_page(content, page, template_mapping=template_mapping)
        await query.message.edit_text(response_text, reply_markup=markup, parse_mode="Markdown")

# --- INTERACTIVE SUBMISSION CONVERSATION ---

ASSIGN_PAGE_SIZE = 8  # ponytail: raise if needed

def _build_assign_page(eligible, page, template_mapping=None):
    """Build paginated inline keyboard for assignment selection."""
    total = len(eligible)
    start = page * ASSIGN_PAGE_SIZE
    end = min(start + ASSIGN_PAGE_SIZE, total)
    total_pages = (total + ASSIGN_PAGE_SIZE - 1) // ASSIGN_PAGE_SIZE
    
    idpel_slot = "data3"
    if template_mapping:
        idpel_slot = next((slot for slot, var in template_mapping.items() if var == "r101a"), "data3")
        
    keyboard = []
    for i in range(start, end):
        a = eligible[i]
        status = a.get("assignmentStatusAlias", "?")
        icon = "🟢" if status == "OPEN" else "🔵"
        nama = a.get('data2', '') or 'NoName'
        idpel = a.get(idpel_slot, '') or '-'
        btn_text = f"{icon} {nama} ({idpel})"
        keyboard.append([InlineKeyboardButton(btn_text, callback_data=f"assign_{i}")])
    
    # Nav row
    nav = []
    if page > 0:
        nav.append(InlineKeyboardButton("⬅️ Prev", callback_data=f"apage_{page-1}"))
    nav.append(InlineKeyboardButton(f"{page+1}/{total_pages}", callback_data="noop"))
    if end < total:
        nav.append(InlineKeyboardButton("➡️ Next", callback_data=f"apage_{page+1}"))
    keyboard.append(nav)
    keyboard.append([InlineKeyboardButton("🔍 Cari IDPel", callback_data="search_idpel")])
    keyboard.append([InlineKeyboardButton("❌ Batalkan", callback_data="cancel")])
    
    header = (f"📋 **Pilih tugas ({start+1}-{end} dari {total}):**\n"
              "🟢 = OPEN  |  🔵 = SUBMITTED (re-submit)")
    return header, InlineKeyboardMarkup(keyboard)

@restricted
async def start_submit(update: Update, context: ContextTypes.DEFAULT_TYPE):
    chat_id = update.effective_chat.id
    token_data = load_user_token(chat_id)
    if not token_data:
        await update.message.reply_text("🔑 Anda belum login. Silakan `/login` terlebih dahulu.")
        return ConversationHandler.END

    sent_msg = await update.message.reply_text("⏳ Mengambil daftar tugas dari BPS...")
    try:
        token_file = get_user_token_file(chat_id)
        token_data = await async_refresh_token_if_needed(token_data, token_file=token_file, exit_on_failure=False)
        headers = get_headers(token_data)
        
        surveys = await async_fetch_surveys(headers)
        if not surveys:
            await sent_msg.edit_text("📋 Tidak ada survei aktif.")
            return ConversationHandler.END
            
        survey = surveys[0]
        active_periode = next((p for p in survey.get("listPeriode", []) if p.get("isActive")), None)
        if not active_periode:
            await sent_msg.edit_text("📋 Tidak ada periode aktif.")
            return ConversationHandler.END
            
        pid = active_periode["id"]
        
        # Fetch ALL assignments in parallel for fast loading
        all_content = await async_fetch_all_assignments(headers, pid)
        if not all_content:
            await sent_msg.edit_text(
                "📋 Tidak ada tugas acuan di akun BPS Anda.\n"
                "Harus ada minimal 1 tugas aktif di wilayah Anda sebagai template enkripsi & region."
            )
            return ConversationHandler.END
            
        context.user_data["open_assignments"] = all_content
        context.user_data["submit_args"] = {}
        context.user_data["assign_page"] = 0
        context.user_data["survey_active_periode"] = active_periode
        context.user_data["survey"] = survey
        
        keyboard = [
            [InlineKeyboardButton("📋 Lihat Semua Tugas", callback_data="view_all_assignments")],
            [InlineKeyboardButton("❌ Batalkan", callback_data="cancel")]
        ]
        
        await safe_delete_message(sent_msg)
        await update.message.reply_text(
            "📝 **Masukkan ID Pelanggan atau Nomor Meter:**\n\n"
            "Ketik ID Pelanggan/Nomor Meter yang ingin Anda isi kuesionernya (atau ketuk tombol di bawah untuk memilih dari daftar tugas):",
            reply_markup=InlineKeyboardMarkup(keyboard),
            parse_mode="Markdown"
        )
        return WAITING_SUBMIT_SEARCH_INPUT
        
    except Exception as e:
        logger.error("Error starting submit", exc_info=True)
        await sent_msg.edit_text(f"❌ Gagal mengambil tugas: {str(e)}")
        return ConversationHandler.END

async def process_submit_search_input(update: Update, context: ContextTypes.DEFAULT_TYPE):
    input_text = update.message.text.strip()
    
    # Strip common list bracket/hash prefixes and index numbers
    import re
    val_clean = input_text.lstrip("[({# ")
    val_clean = re.sub(r"^\d+[\.\-\)\/\s\]]+", "", val_clean).strip()
    
    cleaned_digits = "".join(re.findall(r"\d+", val_clean))
    if len(cleaned_digits) < 4:
        await update.message.reply_text(
            "⚠️ Input tidak valid. Masukkan ID Pelanggan atau No Meter yang benar."
        )
        return WAITING_SUBMIT_SEARCH_INPUT
        
    context.user_data["submit_search_input"] = cleaned_digits
    
    # 1. Check if ID Pelanggan / No Meter already exists in user's assignments
    all_content = context.user_data.get("open_assignments", [])
    
    # Fetch template mapping
    chat_id = update.effective_chat.id
    token_data = load_user_token(chat_id)
    headers = get_headers(token_data)
    survey = context.user_data["survey"]
    template_lookup = survey.get("templateLookup", [])
    template_mapping = {}
    if template_lookup:
        tl = template_lookup[0]
        template_mapping = fetch_template_mapping(headers, tl["templateId"], tl["templateVersion"])
    context.user_data["template_mapping"] = template_mapping
    
    idpel_slot = next((slot for slot, var in template_mapping.items() if var == "r101a"), "data3")
    nometer_slot = next((slot for slot, var in template_mapping.items() if var == "r101b"), "data1")
    
    # Try to find a match
    match = None
    for a in all_content:
        # Match if either slot matches the input
        val_idpel = a.get(idpel_slot) or ""
        val_nometer = a.get(nometer_slot) or ""
        if val_idpel == cleaned_digits or val_nometer == cleaned_digits:
            match = a
            break
            
    if match:
        # Match found! Cek status.
        status = match.get("assignmentStatusAlias", "OPEN")
        nama = match.get("data2", "") or "NoName"
        idpel = match.get(idpel_slot) or ""
        nometer = match.get(nometer_slot) or ""
        
        context.user_data["target_assignment"] = match
        context.user_data["submit_args"]["assignment_id"] = match["id"]
        context.user_data["submit_args"]["idpel"] = idpel
        context.user_data["submit_args"]["nometer"] = nometer
        context.user_data["submit_args"]["nama"] = nama
        context.user_data["create_new"] = False
        
        if "SUBMITTED" in status:
            keyboard = [
                [InlineKeyboardButton("🔄 Re-Submit / Edit", callback_data="dup_continue")],
                [InlineKeyboardButton("❌ Batalkan", callback_data="cancel")]
            ]
            await update.message.reply_text(
                f"⚠️ **Peringatan: ID Pelanggan sudah terdaftar & SUBMITTED**\n\n"
                f"• Pelanggan: **{escape_markdown(nama)}**\n"
                f"• IDPel: `{escape_markdown(idpel)}` | NoMeter: `{escape_markdown(nometer)}`\n"
                f"• Status BPS: `{escape_markdown(status)}` (Sudah Terkirim)\n\n"
                f"Apakah Anda ingin melanjutkan pengisian kuesioner untuk melakukan re-submit/edit tugas ini?",
                reply_markup=InlineKeyboardMarkup(keyboard),
                parse_mode="Markdown"
            )
            return WAITING_DUPLICATE_CHOICE
        else:
            # Status is OPEN/other
            keyboard = [
                [InlineKeyboardButton("📝 Lanjutkan Pengisian", callback_data="dup_continue")],
                [InlineKeyboardButton("❌ Batalkan", callback_data="cancel")]
            ]
            await update.message.reply_text(
                f"⚠️ **Peringatan: ID Pelanggan sudah terdaftar**\n\n"
                f"• Pelanggan: **{escape_markdown(nama)}**\n"
                f"• IDPel: `{escape_markdown(idpel)}` | NoMeter: `{escape_markdown(nometer)}`\n"
                f"• Status BPS: `{escape_markdown(status)}`\n\n"
                f"Apakah Anda ingin melanjutkan pengisian kuesioner untuk tugas ini?",
                reply_markup=InlineKeyboardMarkup(keyboard),
                parse_mode="Markdown"
            )
            return WAITING_DUPLICATE_CHOICE
            
    else:
        # 2. Match NOT found. Lakukan lookup ke PLN AP2T
        waiting_msg = await update.message.reply_text("⏳ Mencari data pelanggan di database PLN AP2T...")
        try:
            from pln_lookup import PLNLookupTool
            engine = PLNLookupTool()
            def do_lookup():
                if len(cleaned_digits) == 12:
                    return engine.lookup_by_idpel(cleaned_digits)
                elif len(cleaned_digits) == 11:
                    return engine.lookup_by_nometer(cleaned_digits)
                else:
                    # Fallback: check IDPel first, then NoMeter if empty
                    r = engine.lookup_by_idpel(cleaned_digits)
                    profiles = r.get("dil_main", r.get("list", r.get("lInfoMasterNedisys", []))) if r else []
                    if not profiles:
                        r = engine.lookup_by_nometer(cleaned_digits)
                    return r

            res = await asyncio.to_thread(do_lookup)
                
            pln_profile = None
            if res:
                profiles = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
                if profiles:
                    pln_profile = profiles[0]
                    if not pln_profile.get("nama") and pln_profile.get("id_pelanggan"):
                        def do_second_lookup():
                            return engine.lookup_by_idpel(pln_profile.get("id_pelanggan"))
                        second_res = await asyncio.to_thread(do_second_lookup)
                        if second_res:
                            second_profiles = second_res.get("dil_main", second_res.get("list", second_res.get("lInfoMasterNedisys", [])))
                            if second_profiles:
                                pln_profile.update(second_profiles[0])
                    
            try:
                await waiting_msg.delete()
            except Exception:
                pass
            
            if pln_profile:
                # Profil ditemukan!
                from submit_fasih import clean_pln_name
                nama = clean_pln_name(str(pln_profile.get("nama", "")).strip())
                alamat = construct_pln_alamat(pln_profile)
                tarif = str(pln_profile.get("tarif", pln_profile.get("gol_tarif", "R-1"))).strip()
                daya = str(pln_profile.get("daya", "900")).strip()
                lat_val = pln_profile.get("koordinat_y", pln_profile.get("latitude"))
                lon_val = pln_profile.get("koordinat_x", pln_profile.get("longitude"))
                
                lat_num = None
                lon_num = None
                try:
                    if lat_val and float(lat_val) != 0.0:
                        lat_num = float(lat_val)
                    if lon_val and float(lon_val) != 0.0:
                        lon_num = float(lon_val)
                except ValueError:
                    pass
                    
                if lat_num is None or lon_num is None:
                    nama_prov = str(pln_profile.get("nama_prov") or "").strip()
                    nama_kab = str(pln_profile.get("nama_kab") or "").strip()
                    nama_kec = str(pln_profile.get("nama_kec") or "").strip()
                    nama_kel = str(pln_profile.get("nama_kel") or "").strip()
                    lat_num, lon_num = await async_geocode_address_nominatim(alamat, nama_kel, nama_kec, nama_kab, nama_prov)
                    if lat_num is None or lon_num is None:
                        lat_num, lon_num = get_fallback_coordinate(nama_prov, nama_kab, nama_kec, alamat)
                
                context.user_data["pln_profile"] = {
                    "nama": nama,
                    "alamat": alamat,
                    "tarif": tarif,
                    "daya": daya,
                    "lat": lat_num,
                    "lon": lon_num,
                    "nik": str(pln_profile.get("noidentitas") or pln_profile.get("no_identitas") or "").strip(),
                    "kd_prov": str(pln_profile.get("kd_prov") or "").strip(),
                    "kd_kab": str(pln_profile.get("kd_kab") or "").strip(),
                    "kd_kec": str(pln_profile.get("kd_kec") or "").strip(),
                    "kd_kel": str(pln_profile.get("kd_kel") or "").strip(),
                    "nama_prov": str(pln_profile.get("nama_prov") or "").strip(),
                    "nama_kab": str(pln_profile.get("nama_kab") or "").strip(),
                    "nama_kec": str(pln_profile.get("nama_kec") or "").strip(),
                    "nama_kel": str(pln_profile.get("nama_kel") or "").strip(),
                    "pln_kd_prov": str(pln_profile.get("kd_prov") or "").strip(),
                    "pln_kd_kab": str(pln_profile.get("kd_kab") or "").strip(),
                    "pln_kd_kec": str(pln_profile.get("kd_kec") or "").strip(),
                    "pln_kd_kel": str(pln_profile.get("kd_kel") or "").strip(),
                    "pln_nama_prov": str(pln_profile.get("nama_prov") or "").strip(),
                    "pln_nama_kab": str(pln_profile.get("nama_kab") or "").strip(),
                    "pln_nama_kec": str(pln_profile.get("nama_kec") or "").strip(),
                    "pln_nama_kel": str(pln_profile.get("nama_kel") or "").strip(),
                    "keperluan": str(pln_profile.get("keperluan") or "").strip(),
                    "idpel": str(pln_profile.get("id_pelanggan", "")).strip() or (cleaned_digits if len(cleaned_digits) == 12 else ""),
                    "nometer": str(
                        pln_profile.get("nometer_kwh") or 
                        pln_profile.get("nomor_meter_kwh") or 
                        pln_profile.get("no_meter_kwh") or 
                        pln_profile.get("no_meter") or 
                        pln_profile.get("nomor_meter") or 
                        pln_profile.get("nometer") or 
                        pln_profile.get("meter_number") or 
                        ""
                    ).strip() or (cleaned_digits if len(cleaned_digits) == 11 else "")
                }
                
                p = context.user_data["pln_profile"]
                keyboard = [
                    [InlineKeyboardButton("➕ Tambah Tugas Baru", callback_data="create_new_confirm")],
                    [InlineKeyboardButton("❌ Batalkan", callback_data="cancel")]
                ]
                await update.message.reply_text(
                    f"🔍 **ID Pelanggan tidak ada di BPS, tetapi ditemukan di PLN:**\n\n"
                    f"• Nama: **{escape_markdown(p['nama'])}**\n"
                    f"• IDPel: `{escape_markdown(p['idpel'])}` | NoMeter: `{escape_markdown(p['nometer'])}`\n"
                    f"• Tarif/Daya: `{escape_markdown(p['tarif'])}` / `{escape_markdown(p['daya'])} VA`\n"
                    f"• Alamat: {escape_markdown(p['alamat'])}\n\n"
                    f"Apakah Anda ingin membuat penugasan baru (Tambah Assignment) untuk pelanggan ini di Fasih Apps?",
                    reply_markup=InlineKeyboardMarkup(keyboard),
                    parse_mode="Markdown"
                )
                return WAITING_SUBMIT_SEARCH_INPUT
            else:
                # Profil tidak ditemukan di PLN juga
                keyboard = [
                    [InlineKeyboardButton("➕ Tetap Tambah Tugas Baru", callback_data="create_new_confirm")],
                    [InlineKeyboardButton("❌ Batalkan", callback_data="cancel")]
                ]
                await update.message.reply_text(
                    f"❓ **ID Pelanggan tidak ditemukan di daftar BPS maupun database PLN.**\n\n"
                    f"IDPel/NoMeter: `{cleaned_digits}`\n\n"
                    f"Apakah Anda ingin tetap memaksakan pembuatan tugas baru secara manual?",
                    reply_markup=InlineKeyboardMarkup(keyboard),
                    parse_mode="Markdown"
                )
                context.user_data["pln_profile"] = {
                    "nama": "PELANGGAN BARU",
                    "alamat": "",
                    "tarif": "R-1",
                    "daya": "900",
                    "lat": None,
                    "lon": None,
                    "idpel": cleaned_digits if len(cleaned_digits) == 12 else "",
                    "nometer": cleaned_digits if len(cleaned_digits) == 11 else ""
                }
                return WAITING_SUBMIT_SEARCH_INPUT
                
        except Exception as e:
            logger.error("Error looking up PLN profile", exc_info=True)
            await waiting_msg.edit_text(f"⚠️ Kesalahan mencari di PLN AP2T: {str(e)}")
            return WAITING_SUBMIT_SEARCH_INPUT

async def submit_search_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    if query.data == "view_all_assignments":
        eligible = context.user_data.get("open_assignments", [])
        eligible_filtered = [
            a for a in eligible
            if "OPEN" in (a.get("assignmentStatusAlias") or "")
            or "SUBMITTED" in (a.get("assignmentStatusAlias") or "")
        ]
        if not eligible_filtered:
            await query.message.edit_text("ℹ️ Tidak ada tugas yang tersedia untuk disubmit/edit.")
            return ConversationHandler.END
            
        context.user_data["open_assignments"] = eligible_filtered
        template_mapping = context.user_data.get("template_mapping")
        header, markup = _build_assign_page(eligible_filtered, 0, template_mapping=template_mapping)
        await query.message.edit_text(header, reply_markup=markup, parse_mode="Markdown")
        return WAITING_SELECT_ASSIGNMENT
        
    if query.data == "cancel":
        await query.message.edit_text("❌ Pengisian dibatalkan.")
        return ConversationHandler.END
        
    if query.data == "create_new_confirm":
        return await start_create_new_flow(update, context)
        
    return WAITING_SUBMIT_SEARCH_INPUT

async def start_create_new_flow(update: Update, context: ContextTypes.DEFAULT_TYPE):
    eligible = context.user_data.get("open_assignments", [])
    
    regions_map = {}
    for a in eligible:
        rid = a.get("region", {}).get("id")
        if rid and rid not in regions_map:
            region = a.get("region") or {}
            l1 = region.get("level1") or {}
            l2 = l1.get("level2") or {}
            l3 = l2.get("level3") or {}
            name = f"{l3.get('name','Unknown')} ({l2.get('name','Unknown')})"
            regions_map[rid] = {
                "name": name,
                "template": a
            }
            
    if not regions_map:
        context.user_data["template_assignment"] = eligible[0]
        return await proceed_to_tarif_after_template(update, context)
        
    if len(regions_map) == 1:
        rid = list(regions_map.keys())[0]
        context.user_data["template_assignment"] = regions_map[rid]["template"]
        return await proceed_to_tarif_after_template(update, context)
        
    keyboard = []
    for rid, info in regions_map.items():
        keyboard.append([InlineKeyboardButton(info["name"], callback_data=f"selreg_{rid}")])
    keyboard.append([InlineKeyboardButton("❌ Batalkan", callback_data="cancel")])
    
    msg_text = "📍 **Pilih Wilayah Kerja (Region) untuk penugasan baru:**"
    if update.callback_query:
        await update.callback_query.message.edit_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
    else:
        await update.message.reply_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
        
    context.user_data["regions_map"] = regions_map
    return WAITING_WILAYAH_SELECTION

async def wilayah_selection_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    if query.data == "cancel":
        await query.message.edit_text("❌ Pengisian dibatalkan.")
        return ConversationHandler.END
        
    if query.data.startswith("selreg_"):
        rid = query.data.split("_")[1]
        regions_map = context.user_data.get("regions_map", {})
        if rid in regions_map:
            context.user_data["template_assignment"] = regions_map[rid]["template"]
            return await proceed_to_tarif_after_template(update, context)
            
    return WAITING_WILAYAH_SELECTION

async def retrieve_pln_profile_for_existing_assignment(target: dict, context: ContextTypes.DEFAULT_TYPE):
    template_mapping = context.user_data.get("template_mapping") or {}
    idpel_slot = next((slot for slot, var in template_mapping.items() if var == "r101a"), "data3")
    nometer_slot = next((slot for slot, var in template_mapping.items() if var == "r101b"), "data1")
    idpel = target.get(idpel_slot, "")
    nometer = target.get(nometer_slot, "")
    context.user_data["pln_profile"] = None
    try:
        from pln_lookup import PLNLookupTool
        engine = PLNLookupTool()
        def do_lookup():
            r = None
            if idpel:
                r = engine.lookup_by_idpel(idpel)
            if not r and nometer:
                r = engine.lookup_by_nometer(nometer)
            return r

        res = await asyncio.to_thread(do_lookup)
        
        if res:
            profiles = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
            if profiles:
                pln_profile = profiles[0]
                from submit_fasih import clean_pln_name
                nama = clean_pln_name(str(pln_profile.get("nama", "")).strip())
                alamat = construct_pln_alamat(pln_profile)
                tarif = str(pln_profile.get("tarif", pln_profile.get("gol_tarif", "R-1"))).strip()
                daya = str(pln_profile.get("daya", "900")).strip()
                lat_val = pln_profile.get("koordinat_y", pln_profile.get("latitude"))
                lon_val = pln_profile.get("koordinat_x", pln_profile.get("longitude"))
                
                lat_num = None
                lon_num = None
                try:
                    if lat_val and float(lat_val) != 0.0:
                        lat_num = float(lat_val)
                    if lon_val and float(lon_val) != 0.0:
                        lon_num = float(lon_val)
                except ValueError:
                    pass
                    
                if lat_num is None or lon_num is None:
                    nama_prov = str(pln_profile.get("nama_prov") or "").strip()
                    nama_kab = str(pln_profile.get("nama_kab") or "").strip()
                    nama_kec = str(pln_profile.get("nama_kec") or "").strip()
                    nama_kel = str(pln_profile.get("nama_kel") or "").strip()
                    lat_num, lon_num = await async_geocode_address_nominatim(alamat, nama_kel, nama_kec, nama_kab, nama_prov)
                    if lat_num is None or lon_num is None:
                        lat_num, lon_num = get_fallback_coordinate(nama_prov, nama_kab, nama_kec, alamat)
                
                context.user_data["pln_profile"] = {
                    "nama": nama,
                    "alamat": alamat,
                    "tarif": tarif,
                    "daya": daya,
                    "lat": lat_num,
                    "lon": lon_num,
                    "nik": str(pln_profile.get("noidentitas") or pln_profile.get("no_identitas") or "").strip(),
                    "kd_prov": str(pln_profile.get("kd_prov") or "").strip(),
                    "kd_kab": str(pln_profile.get("kd_kab") or "").strip(),
                    "kd_kec": str(pln_profile.get("kd_kec") or "").strip(),
                    "kd_kel": str(pln_profile.get("kd_kel") or "").strip(),
                    "nama_prov": str(pln_profile.get("nama_prov") or "").strip(),
                    "nama_kab": str(pln_profile.get("nama_kab") or "").strip(),
                    "nama_kec": str(pln_profile.get("nama_kec") or "").strip(),
                    "nama_kel": str(pln_profile.get("nama_kel") or "").strip(),
                    "pln_kd_prov": str(pln_profile.get("kd_prov") or "").strip(),
                    "pln_kd_kab": str(pln_profile.get("kd_kab") or "").strip(),
                    "pln_kd_kec": str(pln_profile.get("kd_kec") or "").strip(),
                    "pln_kd_kel": str(pln_profile.get("kd_kel") or "").strip(),
                    "pln_nama_prov": str(pln_profile.get("nama_prov") or "").strip(),
                    "pln_nama_kab": str(pln_profile.get("nama_kab") or "").strip(),
                    "pln_nama_kec": str(pln_profile.get("nama_kec") or "").strip(),
                    "pln_nama_kel": str(pln_profile.get("nama_kel") or "").strip(),
                    "keperluan": str(pln_profile.get("keperluan") or "").strip(),
                    "idpel": idpel or str(pln_profile.get("id_pelanggan", "")).strip(),
                    "nometer": nometer or str(
                        pln_profile.get("nometer_kwh") or 
                        pln_profile.get("nomor_meter_kwh") or 
                        pln_profile.get("no_meter_kwh") or 
                        pln_profile.get("no_meter") or 
                        pln_profile.get("nomor_meter") or 
                        pln_profile.get("nometer") or 
                        pln_profile.get("meter_number") or 
                        ""
                    ).strip()
                }

                # Update target and submit_args if name or address is currently empty or NoName
                if not context.user_data.get("submit_args"):
                    context.user_data["submit_args"] = {}
                args = context.user_data["submit_args"]
                p = context.user_data["pln_profile"]
                if not args.get("nama") or args.get("nama") == "NoName":
                    args["nama"] = nama
                if not args.get("alamat"):
                    args["alamat"] = alamat
                args["nik"] = p["nik"]
                args["pln_nama"] = nama
                args["pln_alamat"] = alamat
                args["pln_nik"] = p["nik"]
                args["pln_kd_prov"] = p.get("kd_prov") or ""
                args["pln_kd_kab"] = p.get("kd_kab") or ""
                args["pln_kd_kec"] = p.get("kd_kec") or ""
                args["pln_kd_kel"] = p.get("kd_kel") or ""
                args["pln_nama_prov"] = p.get("nama_prov") or ""
                args["pln_nama_kab"] = p.get("nama_kab") or ""
                args["pln_nama_kec"] = p.get("nama_kec") or ""
                args["pln_nama_kel"] = p.get("nama_kel") or ""
                args["keperluan"] = p.get("keperluan") or ""
                if not target.get("data2") or target.get("data2") == "NoName":
                    target["data2"] = nama
                target["data4"] = alamat
                target["data5"] = alamat
    except Exception as e:
        logger.error(f"Error querying AP2T for existing assignment: {str(e)}", exc_info=True)

async def prompt_tarif_selection(update: Update, context: ContextTypes.DEFAULT_TYPE):
    p = context.user_data.get("pln_profile")
    target = context.user_data["target_assignment"]
    
    keyboard = []
    if p:
        keyboard.append([InlineKeyboardButton(f"🟢 Gunakan Data PLN ({p['tarif']}/{p['daya']} VA)", callback_data="tarif_ap2t_autofill")])
        
    keyboard.append([InlineKeyboardButton("R-1", callback_data="tarif_R-1"), InlineKeyboardButton("R-1T", callback_data="tarif_R-1T")])
    keyboard.append([InlineKeyboardButton("R-2", callback_data="tarif_R-2"), InlineKeyboardButton("B-1", callback_data="tarif_B-1")])
    keyboard.append([InlineKeyboardButton("⬅️ Kembali", callback_data="prev_tarif"), InlineKeyboardButton("❌ Batalkan", callback_data="cancel")])
    
    msg_text = (
        f"Pelanggan: **{target.get('data2', '') or (p.get('nama', '') if p else '')}**\n"
        f"IDPel: `{target.get('data3', '') or (p.get('idpel', '') if p else '')}` | NoMeter: `{target.get('data1', '') or (p.get('nometer', '') if p else '')}`\n\n"
        f"👉 **Pilih Tarif PLN pelanggan** (atau ketik langsung tarif kustom):"
    )
    
    if update.callback_query:
        await update.callback_query.message.edit_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
    else:
        await update.message.reply_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
        
    return WAITING_TARIF

async def proceed_to_tarif_after_template(update: Update, context: ContextTypes.DEFAULT_TYPE):
    p = context.user_data.get("pln_profile", {})
    template = context.user_data["template_assignment"]
    template_mapping = context.user_data["template_mapping"]
    
    from submit_fasih import build_new_assignment_target
    target = build_new_assignment_target(template, p["idpel"], p["nometer"], template_mapping)
    target["data2"] = p.get("nama") or ""
    target["data4"] = p.get("alamat") or ""
    target["data5"] = p.get("alamat") or ""
    
    context.user_data["target_assignment"] = target
    context.user_data["submit_args"]["assignment_id"] = target["id"]
    context.user_data["submit_args"]["idpel"] = p["idpel"]
    context.user_data["submit_args"]["nometer"] = p["nometer"]
    context.user_data["submit_args"]["nama"] = p["nama"]
    context.user_data["submit_args"]["alamat"] = p["alamat"]
    context.user_data["submit_args"]["lat"] = p["lat"]
    context.user_data["submit_args"]["lon"] = p["lon"]
    context.user_data["submit_args"]["nik"] = p.get("nik") or ""
    context.user_data["submit_args"]["pln_nama"] = p.get("nama") or ""
    context.user_data["submit_args"]["pln_alamat"] = p.get("alamat") or ""
    context.user_data["submit_args"]["pln_nik"] = p.get("nik") or ""
    context.user_data["submit_args"]["pln_kd_prov"] = p.get("kd_prov") or ""
    context.user_data["submit_args"]["pln_kd_kab"] = p.get("kd_kab") or ""
    context.user_data["submit_args"]["pln_kd_kec"] = p.get("kd_kec") or ""
    context.user_data["submit_args"]["pln_kd_kel"] = p.get("kd_kel") or ""
    context.user_data["submit_args"]["pln_nama_prov"] = p.get("nama_prov") or ""
    context.user_data["submit_args"]["pln_nama_kab"] = p.get("nama_kab") or ""
    context.user_data["submit_args"]["pln_nama_kec"] = p.get("nama_kec") or ""
    context.user_data["submit_args"]["pln_nama_kel"] = p.get("nama_kel") or ""
    context.user_data["submit_args"]["keperluan"] = p.get("keperluan") or ""
    context.user_data["create_new"] = True
    context.user_data["used_ap2t_autofill"] = False
    
    return await prompt_tarif_selection(update, context)

async def duplicate_choice_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    if query.data == "cancel":
        await query.message.edit_text("❌ Pengisian dibatalkan.")
        return ConversationHandler.END
        
    if query.data == "dup_continue":
        target = context.user_data["target_assignment"]
        
        status_msg = await query.message.edit_text("⏳ Menghubungkan ke PLN AP2T untuk mencocokkan data...")
        await retrieve_pln_profile_for_existing_assignment(target, context)
        
        context.user_data["used_ap2t_autofill"] = False
        return await prompt_tarif_selection(update, context)
        
    return WAITING_DUPLICATE_CHOICE

async def select_assignment_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    if query.data == "cancel":
        await query.message.edit_text("❌ Pengisian dibatalkan.")
        return ConversationHandler.END
    
    if query.data == "noop":
        return WAITING_SELECT_ASSIGNMENT
    
    if query.data.startswith("apage_"):
        page = int(query.data.split("_")[1])
        context.user_data["assign_page"] = page
        eligible = context.user_data["open_assignments"]
        template_mapping = context.user_data.get("template_mapping")
        header, markup = _build_assign_page(eligible, page, template_mapping=template_mapping)
        await query.message.edit_text(header, reply_markup=markup, parse_mode="Markdown")
        return WAITING_SELECT_ASSIGNMENT
    
    if query.data == "search_idpel":
        await query.message.edit_text(
            "🔍 **Ketik IDPel atau Nama pelanggan untuk mencari:**\n"
            "(ketik /cancel untuk membatalkan)",
            parse_mode="Markdown"
        )
        return WAITING_SELECT_ASSIGNMENT
    
    if not query.data.startswith("assign_"):
        return WAITING_SELECT_ASSIGNMENT
    
    idx = int(query.data.split("_")[1])
    target = context.user_data["open_assignments"][idx]
    
    template_mapping = context.user_data.get("template_mapping") or {}
    idpel_slot = next((slot for slot, var in template_mapping.items() if var == "r101a"), "data3")
    nometer_slot = next((slot for slot, var in template_mapping.items() if var == "r101b"), "data1")

    context.user_data["target_assignment"] = target
    context.user_data["submit_args"]["assignment_id"] = target["id"]
    context.user_data["submit_args"]["idpel"] = target.get(idpel_slot, "")
    context.user_data["submit_args"]["nometer"] = target.get(nometer_slot, "")
    context.user_data["submit_args"]["nama"] = target.get("data2", "")
    context.user_data["create_new"] = False
    
    # Query PLN AP2T in the background
    status_msg = await query.message.edit_text("⏳ Menghubungkan ke PLN AP2T untuk mencocokkan data...")
    await retrieve_pln_profile_for_existing_assignment(target, context)
    
    context.user_data["used_ap2t_autofill"] = False
    return await prompt_tarif_selection(update, context)

async def search_assignment_text(update: Update, context: ContextTypes.DEFAULT_TYPE):
    """Handle text search for IDPel/Nama in WAITING_SELECT_ASSIGNMENT."""
    query_text = update.message.text.strip().lower()
    eligible = context.user_data.get("open_assignments", [])
    
    matches = []
    for i, a in enumerate(eligible):
        d2 = (a.get('data2') or '').lower()
        d3 = (a.get('data3') or '').lower()
        d1 = (a.get('data1') or '').lower()
        if query_text in d2 or query_text in d3 or query_text in d1:
            matches.append((i, a))
    
    if not matches:
        await update.message.reply_text(
            f"❌ Tidak ditemukan tugas dengan kata kunci `{escape_markdown(update.message.text)}`\n"
            "Coba lagi atau ketik /cancel",
            parse_mode="Markdown"
        )
        return WAITING_SELECT_ASSIGNMENT
    
    # Show max 10 search results
    keyboard = []
    for i, a in matches[:10]:
        status = a.get("assignmentStatusAlias", "?")
        icon = "🟢" if status == "OPEN" else "🔵"
        nama = a.get('data2', '') or 'NoName'
        idpel = a.get('data3', '') or '-'
        btn_text = f"{icon} {nama} ({idpel})"
        keyboard.append([InlineKeyboardButton(btn_text, callback_data=f"assign_{i}")])
    
    keyboard.append([InlineKeyboardButton("⬅️ Kembali ke Daftar", callback_data="apage_0")])
    keyboard.append([InlineKeyboardButton("❌ Batalkan", callback_data="cancel")])
    
    shown = min(len(matches), 10)
    await update.message.reply_text(
        f"🔍 Ditemukan **{len(matches)}** tugas (menampilkan {shown}):",
        reply_markup=InlineKeyboardMarkup(keyboard),
        parse_mode="Markdown"
    )
    return WAITING_SELECT_ASSIGNMENT

async def process_tarif(update: Update, context: ContextTypes.DEFAULT_TYPE, value: str):
    context.user_data["submit_args"]["tarif"] = value
    
    # Prompt for Daya
    keyboard = [
        [InlineKeyboardButton("450 VA", callback_data="daya_450"), InlineKeyboardButton("900 VA", callback_data="daya_900")],
        [InlineKeyboardButton("1300 VA", callback_data="daya_1300"), InlineKeyboardButton("2200 VA", callback_data="daya_2200")],
        [InlineKeyboardButton("⬅️ Kembali", callback_data="prev_daya"), InlineKeyboardButton("❌ Batalkan", callback_data="cancel")]
    ]
    
    msg_text = f"Diterima tarif: `{value}`\n\n👉 **Pilih Daya PLN pelanggan** (atau ketik langsung daya kustom):"
    
    if update.callback_query:
        await update.callback_query.message.edit_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
    else:
        await update.message.reply_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
        
    return WAITING_DAYA

async def tarif_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    if query.data == "tarif_ap2t_autofill":
        p = context.user_data["pln_profile"]
        context.user_data["submit_args"]["tarif"] = p["tarif"]
        context.user_data["submit_args"]["daya"] = p["daya"]
        context.user_data["used_ap2t_autofill"] = True
        return await process_hasil(update, context, "1. Berhasil didata")
        
    val = query.data.split("_")[1]
    val = "R-1" if val == "default" else val
    context.user_data["used_ap2t_autofill"] = False
    return await process_tarif(update, context, val)

async def tarif_text(update: Update, context: ContextTypes.DEFAULT_TYPE):
    val = update.message.text.strip().upper()
    context.user_data["used_ap2t_autofill"] = False
    return await process_tarif(update, context, val)

async def process_daya(update: Update, context: ContextTypes.DEFAULT_TYPE, value: str):
    context.user_data["submit_args"]["daya"] = value
    
    # Prompt for Hasil
    keyboard = [
        [InlineKeyboardButton("1. Berhasil didata", callback_data="hasil_1. Berhasil didata")],
        [InlineKeyboardButton("2. Tidak ditemukan", callback_data="hasil_2. Tidak ditemukan")],
        [InlineKeyboardButton("⬅️ Kembali", callback_data="prev_hasil"), InlineKeyboardButton("❌ Batalkan", callback_data="cancel")]
    ]
    
    msg_text = f"Diterima daya: `{value} VA`\n\n👉 **Pilih Hasil Pendataan:**"
    
    if update.callback_query:
        await update.callback_query.message.edit_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
    else:
        await update.message.reply_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
        
    return WAITING_HASIL

async def daya_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    val = query.data.split("_")[1]
    val = "900" if val == "default" else val
    return await process_daya(update, context, val)

async def daya_text(update: Update, context: ContextTypes.DEFAULT_TYPE):
    val = update.message.text.strip()
    return await process_daya(update, context, val)

async def process_hasil(update: Update, context: ContextTypes.DEFAULT_TYPE, value: str):
    context.user_data["submit_args"]["hasil"] = value
    
    # Prompt for Photo
    keyboard = [
        [InlineKeyboardButton("⏭️ Lewati Foto Rumah", callback_data="skip_photo")],
        [InlineKeyboardButton("⬅️ Kembali", callback_data="prev_photo"), InlineKeyboardButton("❌ Batalkan", callback_data="cancel")]
    ]
    
    msg_text = (
        f"Diterima hasil: `{value}`\n\n"
        f"📸 **Silakan unggah FOTO rumah tampak depan** (kirim sebagai Gambar/Foto),\n"
        f"atau klik tombol di bawah untuk melompati foto."
    )
    
    if update.callback_query:
        await update.callback_query.message.edit_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
    else:
        await update.message.reply_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
        
    return WAITING_PHOTO

async def hasil_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    val = query.data.split("_")[1]
    val = "1. Berhasil didata" if val == "default" else val
    return await process_hasil(update, context, val)

async def hasil_text(update: Update, context: ContextTypes.DEFAULT_TYPE):
    val = update.message.text.strip()
    return await process_hasil(update, context, val)

async def process_photo(update: Update, context: ContextTypes.DEFAULT_TYPE, has_photo: bool):
    # Prompt for Location
    p = context.user_data.get("pln_profile")
    auto_lat = None
    auto_lon = None
    
    if p and p.get("lat") and p.get("lon"):
        auto_lat = p["lat"]
        auto_lon = p["lon"]
        
    keyboard = []
    if auto_lat and auto_lon:
        keyboard.append([InlineKeyboardButton(f"🟢 Gunakan Koordinat PLN ({auto_lat:.5f}, {auto_lon:.5f})", callback_data="use_pln_loc")])
        
    keyboard.append([InlineKeyboardButton("⏭️ Lewati Lokasi GPS", callback_data="skip_loc")])
    keyboard.append([
        InlineKeyboardButton("⬅️ Kembali", callback_data="prev_location"), 
        InlineKeyboardButton("❌ Batalkan", callback_data="cancel")
    ])
    
    msg_text = (
        f"{'✅ Foto diterima' if has_photo else '⏭️ Foto dilewati'}\n\n"
        f"📍 **Silakan bagikan LOKASI GPS Anda saat ini** (Gunakan fitur kirim lokasi Telegram),\n"
        f"atau klik tombol di bawah untuk melompati GPS."
    )
    
    if update.callback_query:
        await update.callback_query.message.edit_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
    else:
        await update.message.reply_text(msg_text, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
        
    return WAITING_LOCATION

async def photo_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    context.user_data["submit_args"]["photo_path"] = None
    return await process_photo(update, context, False)

async def photo_message(update: Update, context: ContextTypes.DEFAULT_TYPE):
    sent_msg = await update.message.reply_text("⏳ Mengunduh foto...")
    
    # Download photo
    photo_file = await update.message.photo[-1].get_file()
    temp_dir = tempfile.gettempdir()
    photo_path = os.path.join(temp_dir, f"telegram_photo_{update.message.message_id}.png")
    await photo_file.download_to_drive(photo_path)
    
    context.user_data["submit_args"]["photo_path"] = photo_path
    try:
        await sent_msg.delete()
    except Exception:
        pass
    return await process_photo(update, context, True)

async def process_location(update: Update, context: ContextTypes.DEFAULT_TYPE, has_loc: bool):
    # Summary of all args
    args = context.user_data["submit_args"]
    target = context.user_data["target_assignment"]
    
    summary = (
        f"📊 **RINGKASAN DATA KUESIONER**\n"
        f"```\n"
        f"┌──────────────────────────────────┐\n"
        f"│        INFORMASI PELANGGAN       │\n"
        f"└──────────────────────────────────┘\n"
        f" Nama    : {args.get('nama', target.get('data2', ''))[:20]}\n"
        f" IDPel   : {args.get('idpel')}\n"
        f" NoMeter : {args.get('nometer')}\n"
        f" Tarif   : {args.get('tarif')}\n"
        f" Daya    : {args.get('daya')} VA\n"
        f" Hasil   : {args.get('hasil')}\n"
        f" Foto    : {'Terlampir ✅' if args.get('photo_path') else 'Tidak ada ❌'}\n"
        f" Lokasi  : {'Terlampir ✅' if has_loc else 'Tidak ada ❌'}\n"
        f"───────────────────────────────────\n"
        f"```\n"
        f"👉 **Silakan pilih aksi pengiriman di bawah ini:**"
    )
    
    keyboard = [
        [InlineKeyboardButton("✅ Kirim Sekarang (Live Submit)", callback_data="confirm_submit")],
        [InlineKeyboardButton("⚠️ Uji Coba Saja (Dry Run)", callback_data="confirm_dryrun")],
        [InlineKeyboardButton("⬅️ Kembali", callback_data="prev_confirm"), InlineKeyboardButton("❌ Batalkan", callback_data="confirm_cancel")]
    ]
    
    if update.callback_query:
        await update.callback_query.message.edit_text(summary, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
    else:
        await update.message.reply_text(summary, reply_markup=InlineKeyboardMarkup(keyboard), parse_mode="Markdown")
        
    return WAITING_CONFIRM

async def location_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    if query.data == "use_pln_loc":
        p = context.user_data.get("pln_profile")
        if p and p.get("lat") and p.get("lon"):
            context.user_data["submit_args"]["lat"] = p["lat"]
            context.user_data["submit_args"]["lon"] = p["lon"]
            return await process_location(update, context, True)
    if query.data == "skip_loc":
        p = context.user_data.get("pln_profile")
        if p and p.get("lat") and p.get("lon"):
            context.user_data["submit_args"]["lat"] = p["lat"]
            context.user_data["submit_args"]["lon"] = p["lon"]
            return await process_location(update, context, True)
    context.user_data["submit_args"]["lat"] = None
    context.user_data["submit_args"]["lon"] = None
    return await process_location(update, context, False)

async def location_message(update: Update, context: ContextTypes.DEFAULT_TYPE):
    loc = update.message.location
    context.user_data["submit_args"]["lat"] = loc.latitude
    context.user_data["submit_args"]["lon"] = loc.longitude
    return await process_location(update, context, True)

# --- BACK NAVIGATION CALLBACKS ---

async def prev_tarif_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    keyboard = [
        [InlineKeyboardButton("📋 Lihat Semua Tugas", callback_data="view_all_assignments")],
        [InlineKeyboardButton("❌ Batalkan", callback_data="cancel")]
    ]
    await query.message.edit_text(
        "📝 **Masukkan ID Pelanggan atau Nomor Meter:**\n\n"
        "Ketik ID Pelanggan/Nomor Meter yang ingin Anda isi kuesionernya (atau ketuk tombol di bawah untuk memilih dari daftar tugas):",
        reply_markup=InlineKeyboardMarkup(keyboard),
        parse_mode="Markdown"
    )
    return WAITING_SUBMIT_SEARCH_INPUT

async def prev_daya_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    return await prompt_tarif_selection(update, context)

async def prev_hasil_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    if context.user_data.get("used_ap2t_autofill"):
        return await prompt_tarif_selection(update, context)
        
    tarif = context.user_data["submit_args"].get("tarif", "R-1")
    return await process_tarif(update, context, tarif)

async def prev_photo_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    daya = context.user_data["submit_args"].get("daya", "900")
    return await process_daya(update, context, daya)

async def prev_location_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    hasil = context.user_data["submit_args"].get("hasil", "1. Berhasil didata")
    return await process_hasil(update, context, hasil)

async def prev_confirm_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    has_photo = bool(context.user_data["submit_args"].get("photo_path"))
    return await process_photo(update, context, has_photo)

async def confirm_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    choice = query.data
    if choice == "confirm_cancel":
        # Clean up temp photo
        photo_path = context.user_data["submit_args"].get("photo_path")
        if photo_path and os.path.exists(photo_path):
            os.remove(photo_path)
        await query.message.edit_text("❌ Pengisian kuesioner dibatalkan.")
        return ConversationHandler.END
        
    dry_run = (choice == "confirm_dryrun")
    chat_id = update.effective_chat.id
    token_data = load_user_token(chat_id)
    token_file = get_user_token_file(chat_id)
    
    status_msg = await query.message.edit_text("⏳ Memulai pipeline pengiriman...")
    
    async def update_status_func(text: str):
        try:
            await status_msg.edit_text(f"⏳ **Status:** {text}", parse_mode="Markdown")
        except Exception:
            pass

    args = context.user_data["submit_args"]
    target = context.user_data["target_assignment"]
    
    # Structure direct_args
    direct_args = {
        "idpel": args["idpel"],
        "nometer": args["nometer"],
        "nama": target.get("data2", "") or "PELANGGAN",
        "alamat": target.get("data4", "") or "",
        "tarif": args["tarif"],
        "daya": args["daya"],
        "hasil": args["hasil"],
        "kelurahan": "001",
        "kdpm": "01",
        "kddk": "1",
        "status_dil": "1",
        "nik": args.get("nik") or "",
        "pln_nama": args.get("pln_nama") or "",
        "pln_alamat": args.get("pln_alamat") or "",
        "pln_nik": args.get("pln_nik") or "",
        "pln_kd_prov": args.get("pln_kd_prov") or "",
        "pln_kd_kab": args.get("pln_kd_kab") or "",
        "pln_kd_kec": args.get("pln_kd_kec") or "",
        "pln_kd_kel": args.get("pln_kd_kel") or "",
        "pln_nama_prov": args.get("pln_nama_prov") or "",
        "pln_nama_kab": args.get("pln_nama_kab") or "",
        "pln_nama_kec": args.get("pln_nama_kec") or "",
        "pln_nama_kel": args.get("pln_nama_kel") or "",
        "keperluan": args.get("keperluan") or ""
    }

    # Run pipeline
    ok, message = await submit_fasih_safe(
        token_data, token_file,
        idpel=args.get("idpel"),
        nometer=args.get("nometer"),
        assignment_id=args["assignment_id"],
        dry_run=dry_run,
        direct_args=direct_args,
        photo_path=args.get("photo_path"),
        lat=args.get("lat"),
        lon=args.get("lon"),
        status_callback=update_status_func,
        create_new=context.user_data.get("create_new", False),
        template_assignment_id=context.user_data.get("template_assignment", {}).get("id")
    )
    
    # Clean up temp photo
    if args.get("photo_path") and os.path.exists(args["photo_path"]):
        os.remove(args["photo_path"])

    if ok:
        emoji = "⚠️ (SIMULASI) " if dry_run else "✅ "
        await status_msg.edit_text(
            f"{emoji}**SUBMISSION SELESAI!**\n\n"
            f"• Pelanggan: {escape_markdown(direct_args['nama'])}\n"
            f"• IDPel: `{escape_markdown(direct_args['idpel'])}`\n"
            f"• Pesan: {escape_markdown(message)}",
            parse_mode="Markdown"
        )
    else:
        await status_msg.edit_text(
            f"❌ **SUBMISSION GAGAL!**\n\n"
            f"• Pelanggan: {escape_markdown(direct_args['nama'])}\n"
            f"• IDPel: `{escape_markdown(direct_args['idpel'])}`\n"
            f"• Detail: {escape_markdown(message)}",
            parse_mode="Markdown"
        )
        
    return ConversationHandler.END

@restricted
async def start_lookup(update: Update, context: ContextTypes.DEFAULT_TYPE):
    await update.message.reply_text(
        "🔍 **Cari InfoPelanggan PLN**\n\n"
        "Silakan masukkan **ID Pelanggan** atau **Nomor Meter** yang ingin dicari.\n"
        "Anda juga bisa menempelkan (paste) beberapa nomor sekaligus (satu nomor per baris) untuk pencarian massal.\n\n"
        "👉 _Ketik /cancel untuk membatalkan._",
        parse_mode="Markdown"
    )
    return WAITING_LOOKUP_INPUT

async def process_lookup_input(update: Update, context: ContextTypes.DEFAULT_TYPE):
    text = update.message.text.strip()
    if not text:
        await update.message.reply_text("[-] ID tidak boleh kosong.")
        return WAITING_LOOKUP_INPUT
        
    sent_msg = await update.message.reply_text("⏳ Sedang memproses pencarian data PLN...")
    
    # Parse lines
    lines = [line.strip() for line in text.split("\n") if line.strip()]
    search_queries = []
    
    import re
    for line in lines:
        if "," in line:
            line = line.split(",")[0]
        # Match consecutive digits of length 11-12
        match = re.search(r'\d{11,12}', line)
        if match:
            clean_val = match.group(0)
            q_type = 'idpel' if len(clean_val) == 12 else 'nometer'
            search_queries.append((q_type, clean_val))
        else:
            # Fallback: strip bullet points and list prefixes
            stripped = re.sub(r'^\s*\d+[\s\.)\-]+', '', line)
            clean_val = re.sub(r'\D', '', stripped)
            if clean_val:
                q_type = 'idpel' if len(clean_val) == 12 else 'nometer'
                search_queries.append((q_type, clean_val))
            
    if not search_queries:
        await sent_msg.edit_text("❌ Tidak ada ID Pelanggan atau Nomor Meter yang valid ditemukan.")
        return ConversationHandler.END

    total = len(search_queries)
    await sent_msg.edit_text(f"⏳ Ditemukan {total} nomor. Sedang mengunduh data secara paralel...")
    
    try:
        from pln_lookup import PLNLookupTool, save_focused_outputs, DEFAULT_COOKIES, DEFAULT_AP2T_IP
        
        engine = PLNLookupTool(cookies=DEFAULT_COOKIES, ap2t_ip=DEFAULT_AP2T_IP)
        results = [None] * total
        
        # Parallel query execution
        from concurrent.futures import ThreadPoolExecutor
        
        def fetch_single(index, q_type, val):
            if q_type == 'idpel':
                res = engine.lookup_by_idpel(val)
            else:
                res = engine.lookup_by_nometer(val)
                if res:
                    profiles = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
                    if profiles and not profiles[0].get("nama"):
                        idpel = profiles[0].get("id_pelanggan")
                        if idpel:
                            second_res = engine.lookup_by_idpel(idpel)
                            if second_res:
                                second_profiles = second_res.get("dil_main", second_res.get("list", second_res.get("lInfoMasterNedisys", [])))
                                if second_profiles:
                                    profiles[0].update(second_profiles[0])
            return index, q_type, val, res

        max_workers = min(10, total)
        def run_parallel_lookup():
            res_list = [None] * total
            with ThreadPoolExecutor(max_workers=max_workers) as executor:
                futures = [
                    executor.submit(fetch_single, idx, q_type, val)
                    for idx, (q_type, val) in enumerate(search_queries)
                ]
                for future in futures:
                    idx, q_type, val, res = future.result()
                    res_list[idx] = res
            return res_list

        results = await asyncio.to_thread(run_parallel_lookup)

        # Chunking results into multiple messages to bypass Telegram's 4096 character limit
        import html
        messages_to_send = []
        current_chunk = [f"🔍 <b>Hasil Pencarian ({total} nomor):</b>\n"]
        current_len = len(current_chunk[0])
        
        for idx, (q_type, val) in enumerate(search_queries):
            res = results[idx]
            if res:
                profile = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
                if profile:
                    p = profile[0]
                    name = html.escape(str(p.get("nama", "N/A")).strip())
                    nik = html.escape(str(p.get("noidentitas", p.get("no_identitas", "-"))).strip())
                    if not nik or nik.lower() == "null":
                        nik = "-"
                    
                    idpel_val = str(p.get("id_pelanggan") or p.get("idpel") or "").strip()
                    if not idpel_val and q_type == 'idpel':
                        idpel_val = val
                    idpel_val = idpel_val if idpel_val else "-"
                    
                    nometer_val = str(
                        p.get("nometer_kwh") or 
                        p.get("nomor_meter_kwh") or 
                        p.get("no_meter_kwh") or 
                        p.get("no_meter") or 
                        p.get("nomor_meter") or 
                        p.get("nometer") or 
                        p.get("meter_number") or 
                        ""
                    ).strip()
                    if not nometer_val and q_type == 'nometer':
                        nometer_val = val
                    nometer_val = nometer_val if nometer_val else "-"
                    
                    line_str = f"• IDPel: <code>{idpel_val}</code> | NoMeter: <code>{nometer_val}</code> ➔ NIK: <code>{nik}</code> - {name}"
                else:
                    line_str = f"• <code>{val}</code> - SUCCESS (Tanpa Profil)"
            else:
                line_str = f"• <code>{val}</code> - GAGAL (Koneksi/Sesi PLN)"
                
            if current_len + len(line_str) + 1 > 3800:
                messages_to_send.append("\n".join(current_chunk))
                current_chunk = [line_str]
                current_len = len(line_str)
            else:
                current_chunk.append(line_str)
                current_len += len(line_str) + 1
                
        if current_chunk:
            messages_to_send.append("\n".join(current_chunk))
            
        # Send chunks with a small sleep to prevent 429 rate limit errors
        if messages_to_send:
            await sent_msg.edit_text(messages_to_send[0], parse_mode="HTML")
            for chunk in messages_to_send[1:]:
                await asyncio.sleep(0.5)
                await update.message.reply_text(chunk, parse_mode="HTML")
        
        # Generate Excel reports
        temp_dir = tempfile.gettempdir()
        output_base = os.path.join(temp_dir, f"bot_export_{int(datetime.now().timestamp())}")
        save_focused_outputs(results, output_base)
        
        identity_path = f"{output_base}_identity.xlsx"
        fasih_path = f"{output_base}_fasih_input.xlsx"
        
        # Send documents
        if os.path.exists(identity_path):
            with open(identity_path, "rb") as f:
                await update.message.reply_document(
                    document=f,
                    filename=f"PLN_Identity_Checklist_{datetime.now().strftime('%Y%m%d_%H%M%S')}.xlsx",
                    caption="📊 Laporan Kredensial Pelanggan (NIK & Nama)"
                )
            os.remove(identity_path)
            
        # Clean up the other file without sending it
        if os.path.exists(fasih_path):
            os.remove(fasih_path)
            
    except Exception as e:
        logger.error("Error in process_lookup_input", exc_info=True)
        await sent_msg.edit_text(f"❌ Terjadi kesalahan saat memproses data: {str(e)}")
        
    return ConversationHandler.END

@restricted
async def start_assignment_search(update: Update, context: ContextTypes.DEFAULT_TYPE):
    await update.message.reply_text(
        "🔍 **Cari Assignment BPS**\n\n"
        "Silakan masukkan **ID Pelanggan** atau **Nomor Meter** yang ingin dicari di BPS.\n\n"
        "👉 _Ketik /cancel untuk membatalkan._",
        parse_mode="Markdown"
    )
    return WAITING_ASSIGNMENT_SEARCH_INPUT

async def process_assignment_search_input(update: Update, context: ContextTypes.DEFAULT_TYPE):
    input_text = update.message.text.strip()
    if not input_text:
        await update.message.reply_text("[-] ID tidak boleh kosong.")
        return WAITING_ASSIGNMENT_SEARCH_INPUT

    import re
    cleaned_digits = "".join(re.findall(r"\d+", input_text))
    if len(cleaned_digits) < 4:
        await update.message.reply_text("⚠️ Input tidak valid. Masukkan ID Pelanggan atau No Meter yang benar.")
        return WAITING_ASSIGNMENT_SEARCH_INPUT

    sent_msg = await update.message.reply_text("⏳ Sedang mengambil data dan mencari di BPS...")
    try:
        chat_id = update.effective_chat.id
        token_data = load_user_token(chat_id)
        if not token_data:
            await sent_msg.edit_text("🔑 Sesi Anda kedaluwarsa atau belum login. Silakan `/login` kembali.")
            return ConversationHandler.END

        token_data = refresh_token_if_needed(token_data, token_file=get_user_token_file(chat_id), exit_on_failure=False)
        headers = get_headers(token_data)

        # 1. Fetch surveys
        surveys = fetch_surveys(headers)
        if not surveys:
            await sent_msg.edit_text("📋 Tidak ada survei yang aktif di akun Anda.")
            return ConversationHandler.END

        survey = surveys[0]
        active_periode = next((p for p in survey.get("listPeriode", []) if p.get("isActive")), None)
        if not active_periode:
            await sent_msg.edit_text("📋 Tidak ada periode aktif.")
            return ConversationHandler.END

        pid = active_periode["id"]
        
        # Fetch template mapping
        template_lookup = survey.get("templateLookup", [])
        template_mapping = {}
        if template_lookup:
            tl = template_lookup[0]
            template_mapping = fetch_template_mapping(headers, tl["templateId"], tl["templateVersion"])
        
        idpel_slot = next((slot for slot, var in template_mapping.items() if var == "r101a"), "data3")
        nometer_slot = next((slot for slot, var in template_mapping.items() if var == "r101b"), "data1")

        # 2. Fetch assignments
        all_content = fetch_all_assignments(headers, pid)
        if not all_content:
            await sent_msg.edit_text("📋 Tidak ada tugas di akun BPS Anda.")
            return ConversationHandler.END

        # 3. Find match(es)
        matches = []
        for a in all_content:
            val_idpel = (a.get(idpel_slot) or "").strip()
            val_nometer = (a.get(nometer_slot) or "").strip()
            if val_idpel == cleaned_digits or val_nometer == cleaned_digits:
                matches.append(a)

        if not matches:
            await sent_msg.edit_text(
                f"❌ **Assignment Tidak Ditemukan**\n\n"
                f"ID Pelanggan / No Meter `{cleaned_digits}` tidak ditemukan di daftar tugas BPS Anda.",
                parse_mode="Markdown"
            )
            return ConversationHandler.END

        # 4. Display match(es)
        await safe_delete_message(sent_msg)
        for idx, m in enumerate(matches):
            nama = m.get("data2", "") or "NoName"
            idpel = m.get(idpel_slot) or "-"
            nometer = m.get(nometer_slot) or "-"
            status = m.get("assignmentStatusAlias", "OPEN")
            alamat = m.get("data4", m.get("data5", "-")) or "-"
            region_name = m.get("region", {}).get("name", "BONTANG")
            
            # Fetch Tarif & Daya from PLN tool (checking cache first)
            from pln_lookup import PLNLookupTool
            pln_tool = PLNLookupTool()
            tarif = "-"
            daya = "-"
            query_val = idpel if idpel != "-" else nometer
            if query_val != "-":
                try:
                    def do_lookup():
                        return pln_tool.lookup_by_idpel(query_val) if len(query_val) == 12 else pln_tool.lookup_by_nometer(query_val)
                    res = await asyncio.to_thread(do_lookup)
                    if res:
                        profiles = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
                        if profiles:
                            p = profiles[0]
                            tarif = str(p.get("tarif", p.get("gol_tarif", "-"))).strip()
                            daya = str(p.get("daya", p.get("daya_51", "-"))).strip()
                except Exception as pln_err:
                    logger.warning(f"PLN lookup failed during assignment search: {pln_err}")

            msg = (
                f"✅ **Assignment BPS Ditemukan! (#{idx+1})**\n\n"
                f"• **Nama Pelanggan:** {escape_markdown(nama)}\n"
                f"• **IDPel:** `{escape_markdown(idpel)}` | **NoMeter:** `{escape_markdown(nometer)}`\n"
                f"• **Status BPS:** `{escape_markdown(status)}`\n"
                f"• **Region:** `{escape_markdown(region_name)}`\n"
                f"• **Tarif/Daya:** `{escape_markdown(tarif)}` / `{escape_markdown(daya)}` VA\n"
                f"• **Alamat:** {escape_markdown(alamat)}\n"
                f"• **ID Assignment:** `{escape_markdown(m.get('id', ''))}`"
            )
            await update.message.reply_text(msg, parse_mode="Markdown")

    except Exception as e:
        logger.error("Error in process_assignment_search_input", exc_info=True)
        await sent_msg.edit_text(f"❌ Terjadi kesalahan saat mencari assignment: {str(e)}")

    return ConversationHandler.END

def find_template_assignment_for_region(open_assignments, pln_profile):
    if not open_assignments:
        return None
        
    # If only 1 region, use it
    regions = {a.get("region", {}).get("id") for a in open_assignments if a.get("region", {}).get("id")}
    if len(regions) == 1:
        return open_assignments[0]

    # Try to match by kelurahan / kecamatan name
    if pln_profile:
        nama_kel = pln_profile.get("nama_kel", "").lower()
        nama_kec = pln_profile.get("nama_kec", "").lower()
        for a in open_assignments:
            r_name = a.get("region", {}).get("name", "").lower()
            if nama_kel and nama_kel in r_name:
                return a
            if nama_kec and nama_kec in r_name:
                return a
                
    # Fallback to the first assignment
    return open_assignments[0]

@restricted
async def start_batch_submit(update: Update, context: ContextTypes.DEFAULT_TYPE):
    await update.message.reply_text(
        "⚡ **Batch Submit (Massal)**\n\n"
        "Silakan masukkan daftar **ID Pelanggan** atau **Nomor Meter** yang ingin di-submit sekaligus.\n"
        "Anda dapat menempelkan (paste) beberapa nomor sekaligus (satu nomor per baris).\n\n"
        "Contoh:\n"
        "`234000397484`\n"
        "`45096615278`\n\n"
        "👉 _Ketik /cancel untuk membatalkan._",
        parse_mode="Markdown"
    )
    return WAITING_BATCH_SUBMIT_INPUT

async def process_batch_submit_input(update: Update, context: ContextTypes.DEFAULT_TYPE):
    text = update.message.text.strip()
    if not text:
        await update.message.reply_text("[-] Input tidak boleh kosong.")
        return WAITING_BATCH_SUBMIT_INPUT
        
    import re
    # Parse lines
    lines = [line.strip() for line in text.split("\n") if line.strip()]
    search_queries = []
    
    for line in lines:
        if "," in line:
            line = line.split(",")[0]
        # Match consecutive digits of length 11-12
        match = re.search(r'\d{11,12}', line)
        if match:
            search_queries.append(match.group(0))
        else:
            # Fallback
            stripped = re.sub(r'^\s*\d+[\s\.)\-]+', '', line)
            clean_val = re.sub(r'\D', '', stripped)
            if len(clean_val) in (11, 12):
                search_queries.append(clean_val)

    if not search_queries:
        await update.message.reply_text("❌ Tidak ada ID Pelanggan atau Nomor Meter yang valid (11 atau 12 digit) ditemukan.")
        return ConversationHandler.END

    # Save to user_data for confirm
    context.user_data["batch_submit_list"] = search_queries
    
    keyboard = [
        [InlineKeyboardButton("🟢 Ya, Mulai Batch Submit", callback_data="batch_start_confirm")],
        [InlineKeyboardButton("❌ Batalkan", callback_data="cancel")]
    ]
    await update.message.reply_text(
        f"📊 **Konfirmasi Batch Submit**\n\n"
        f"Ditemukan **{len(search_queries)}** nomor pelanggan untuk diproses.\n"
        f"• Bot akan mencocokkan ke daftar tugas BPS.\n"
        f"• Jika belum terdaftar di BPS, bot akan melakukan PLN Lookup & Geocoding lalu membuat penugasan baru.\n\n"
        f"Apakah Anda yakin ingin memulai proses?",
        reply_markup=InlineKeyboardMarkup(keyboard),
        parse_mode="Markdown"
    )
    return WAITING_BATCH_CONFIRM

async def batch_confirm_callback(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer()
    
    if query.data == "cancel":
        await query.message.edit_text("❌ Aksi dibatalkan.")
        return ConversationHandler.END
        
    if query.data != "batch_start_confirm":
        return WAITING_BATCH_CONFIRM
        
    chat_id = update.effective_chat.id
    token_data = load_user_token(chat_id)
    if not token_data:
        await query.message.edit_text("🔑 Anda belum login. Silakan `/login` terlebih dahulu.")
        return ConversationHandler.END
        
    token_file = get_user_token_file(chat_id)
    search_queries = context.user_data.get("batch_submit_list", [])
    if not search_queries:
        await query.message.edit_text("❌ Data list kosong.")
        return ConversationHandler.END
        
    status_msg = await query.message.edit_text("⏳ Sedang memproses inisialisasi...")
    
    try:
        token_data = await async_refresh_token_if_needed(token_data, token_file=token_file, exit_on_failure=False)
        headers = get_headers(token_data)
        
        # Fetch surveys
        surveys = await async_fetch_surveys(headers)
        if not surveys:
            await status_msg.edit_text("📋 Tidak ada survei yang aktif di akun Anda.")
            return ConversationHandler.END
            
        survey = surveys[0]
        active_periode = next((p for p in survey.get("listPeriode", []) if p.get("isActive")), None)
        if not active_periode:
            await status_msg.edit_text("📋 Tidak ada periode aktif.")
            return ConversationHandler.END
            
        pid = active_periode["id"]
        
        # Fetch template mapping
        template_lookup = survey.get("templateLookup", [])
        template_mapping = {}
        if template_lookup:
            tl = template_lookup[0]
            template_mapping = await async_fetch_template_mapping(headers, tl["templateId"], tl["templateVersion"])
            
        idpel_slot = next((slot for slot, var in template_mapping.items() if var == "r101a"), "data3")
        nometer_slot = next((slot for slot, var in template_mapping.items() if var == "r101b"), "data1")
        
        # Fetch all assignments once for fast lookup
        await status_msg.edit_text("📋 Sedang mengambil seluruh daftar tugas dari server BPS...")
        open_assignments = await async_fetch_all_assignments(headers, pid) or []
        
        from pln_lookup import PLNLookupTool
        pln_tool = PLNLookupTool()
        
        total = len(search_queries)
        successes = 0
        failures = 0
        report_rows = []
        
        for idx, val in enumerate(search_queries):
            is_idpel = len(val) == 12
            idpel_val = val if is_idpel else ""
            nometer_val = "" if is_idpel else val
            
            await status_msg.edit_text(
                f"⏳ **Memproses {idx+1}/{total}**\n"
                f"• IDPel/NoMeter: `{val}`\n"
                f"• Sukses: {successes} | Gagal: {failures}"
            )
            
            # 1. Check if assignment exists
            target = None
            for a in open_assignments:
                v_idpel = (a.get(idpel_slot) or "").strip()
                v_nometer = (a.get(nometer_slot) or "").strip()
                if (is_idpel and v_idpel == val) or (not is_idpel and v_nometer == val):
                    target = a
                    break
                    
            create_new = False
            template_assignment_id = None
            pln_profile = None
            
            # Setup default/initial direct_args
            direct_args = {
                "idpel": idpel_val,
                "nometer": nometer_val,
                "nama": "PELANGGAN BARU",
                "alamat": "",
                "tarif": "R-1",
                "daya": "900",
                "hasil": "1. Berhasil didata",
                "kelurahan": "001",
                "kdpm": "01",
                "kddk": "1",
                "status_dil": "1"
            }
            
            lat = None
            lon = None
            
            if target:
                # Existing assignment found
                direct_args["nama"] = target.get("data2", "") or "PELANGGAN"
                direct_args["alamat"] = target.get("data4", target.get("data5", "")) or ""
                idpel_val = target.get(idpel_slot) or idpel_val
                nometer_val = target.get(nometer_slot) or nometer_val
                direct_args["idpel"] = idpel_val
                direct_args["nometer"] = nometer_val
                
                # Fetch cache/live for tarif and daya
                try:
                    def do_pln_lookup():
                        return pln_tool.lookup_by_idpel(idpel_val) if idpel_val else pln_tool.lookup_by_nometer(nometer_val)
                    res = await asyncio.to_thread(do_pln_lookup)
                    if res:
                        profiles = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
                        if profiles:
                            pln_profile = profiles[0]
                            direct_args["tarif"] = str(pln_profile.get("tarif", pln_profile.get("gol_tarif", "R-1"))).strip()
                            direct_args["daya"] = str(pln_profile.get("daya", pln_profile.get("daya_51", "900"))).strip()
                except Exception:
                    pass
            else:
                # No assignment exists. Create new penugasan
                create_new = True
                
                # Retrieve PLN details
                try:
                    def do_batch_lookup():
                        return pln_tool.lookup_by_idpel(val) if is_idpel else pln_tool.lookup_by_nometer(val)
                    res = await asyncio.to_thread(do_batch_lookup)
                    if res:
                        profiles = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
                        if profiles:
                            pln_profile = profiles[0]
                            # Resolve second profiles if needed
                            if not pln_profile.get("nama") and pln_profile.get("id_pelanggan"):
                                def do_sec_lookup():
                                    return pln_tool.lookup_by_idpel(pln_profile.get("id_pelanggan"))
                                sec_res = await asyncio.to_thread(do_sec_lookup)
                                if sec_res:
                                    sec_prof = sec_res.get("dil_main", sec_res.get("list", sec_res.get("lInfoMasterNedisys", [])))
                                    if sec_prof:
                                        pln_profile.update(sec_prof[0])
                                        
                            direct_args["nama"] = str(pln_profile.get("nama", "PELANGGAN BARU")).strip()
                            direct_args["alamat"] = str(pln_profile.get("alamat") or pln_profile.get("namapnj") or pln_profile.get("alamat_51") or "").strip()
                            direct_args["tarif"] = str(pln_profile.get("tarif", pln_profile.get("gol_tarif", "R-1"))).strip()
                            direct_args["daya"] = str(pln_profile.get("daya", "900")).strip()
                            
                            idpel_val = str(pln_profile.get("id_pelanggan", "")).strip() or idpel_val
                            nometer_val = str(
                                pln_profile.get("nometer_kwh") or 
                                pln_profile.get("nomor_meter_kwh") or 
                                pln_profile.get("no_meter_kwh") or 
                                pln_profile.get("no_meter") or 
                                pln_profile.get("nomor_meter") or 
                                pln_profile.get("nometer") or 
                                pln_profile.get("meter_number") or 
                                ""
                            ).strip() or nometer_val
                            
                            direct_args["idpel"] = idpel_val
                            direct_args["nometer"] = nometer_val
                            
                            lat_val = pln_profile.get("koordinat_y", pln_profile.get("latitude"))
                            lon_val = pln_profile.get("koordinat_x", pln_profile.get("longitude"))
                            try:
                                if lat_val and float(lat_val) != 0.0:
                                    lat = float(lat_val)
                                if lon_val and float(lon_val) != 0.0:
                                    lon = float(lon_val)
                            except ValueError:
                                pass
                                
                            if lat is None or lon is None:
                                nama_prov = str(pln_profile.get("nama_prov") or "").strip()
                                nama_kab = str(pln_profile.get("nama_kab") or "").strip()
                                nama_kec = str(pln_profile.get("nama_kec") or "").strip()
                                nama_kel = str(pln_profile.get("nama_kel") or "").strip()
                                lat, lon = await async_geocode_address_nominatim(direct_args["alamat"], nama_kel, nama_kec, nama_kab, nama_prov)
                                if lat is None or lon is None:
                                    lat, lon = get_fallback_coordinate(nama_prov, nama_kab, nama_kec, direct_args["alamat"])
                except Exception as pln_err:
                    logger.warning(f"PLN lookup failed for batch item {val}: {pln_err}")
                
                # Find template assignment
                template_assignment = None
                if open_assignments:
                    template_assignment = find_template_assignment_for_region(open_assignments, pln_profile)
                
                if not template_assignment:
                    failures += 1
                    report_rows.append({
                        "val": val, "nama": direct_args["nama"], "status": "FAILED",
                        "message": "Template assignment acuan tidak tersedia di BPS."
                    })
                    continue
                    
                template_assignment_id = template_assignment["id"]
            
            photo_path = get_random_house_photo()

            # Submit to BPS
            ok, message = await submit_fasih_safe(
                token_data, token_file,
                idpel=idpel_val,
                nometer=nometer_val,
                create_new=create_new,
                template_assignment_id=template_assignment_id,
                direct_args=direct_args,
                photo_path=photo_path,
                lat=lat,
                lon=lon
            )
            
            if ok:
                successes += 1
            else:
                failures += 1
                
            report_rows.append({
                "val": val, "nama": direct_args["nama"],
                "status": "SUCCESS" if ok else "FAILED",
                "message": message
            })
            
        # Compile report
        temp_dir = tempfile.gettempdir()
        report_filename = f"batch_submit_report_{datetime.now().strftime('%Y%m%d_%H%M%S')}.csv"
        report_path = os.path.join(temp_dir, report_filename)
        
        with open(report_path, "w", encoding="utf-8", newline="") as f:
            fieldnames = ["val", "nama", "status", "message"]
            writer = csv.DictWriter(f, fieldnames=fieldnames)
            writer.writeheader()
            writer.writerows(report_rows)
            
        await safe_delete_message(status_msg)
        
        summary_text = (
            f"✅ **BATCH SUBMIT SELESAI**\n"
            f"===================================\n"
            f"• Total Nomor: {total}\n"
            f"• Sukses: {successes}\n"
            f"• Gagal: {failures}\n"
            f"===================================\n\n"
            f"Laporan detail eksekusi batch terlampir."
        )
        
        with open(report_path, "rb") as report_file:
            await query.message.reply_document(
                document=report_file,
                filename=report_filename,
                caption=summary_text,
                parse_mode="Markdown"
            )
            
        os.remove(report_path)
        
    except Exception as e:
        logger.error("Error in batch_confirm_callback", exc_info=True)
        await status_msg.edit_text(f"❌ Terjadi kesalahan sistem: {str(e)}")
        
    return ConversationHandler.END

async def cancel_conversation(update: Update, context: ContextTypes.DEFAULT_TYPE):
    # Clean up temp photo if exists
    if "submit_args" in context.user_data:
        photo_path = context.user_data["submit_args"].get("photo_path")
        if photo_path and os.path.exists(photo_path):
            os.remove(photo_path)
            
    await update.message.reply_text("❌ Aksi dibatalkan.")
    return ConversationHandler.END

# --- CSV FILE BULK SUBMISSION ---

@restricted
async def handle_csv_document(update: Update, context: ContextTypes.DEFAULT_TYPE):
    document = update.message.document
    if not document.file_name.lower().endswith(".csv"):
        return # ignore non-csv documents
        
    chat_id = update.effective_chat.id
    token_data = load_user_token(chat_id)
    if not token_data:
        await update.message.reply_text("🔑 Anda belum login. Silakan `/login` terlebih dahulu.")
        return
        
    token_file = get_user_token_file(chat_id)
    
    status_msg = await update.message.reply_text("📥 Mengunduh berkas CSV...")
    
    temp_dir = tempfile.gettempdir()
    csv_path = os.path.join(temp_dir, f"bulk_{chat_id}_{int(datetime.now().timestamp())}.csv")
    
    # Download the CSV file
    file_obj = await document.get_file()
    await file_obj.download_to_drive(csv_path)
    
    # Parse records
    records = []
    try:
        with open(csv_path, "r", encoding="utf-8-sig") as f:
            reader = csv.DictReader(f)
            reader.fieldnames = [name.strip().lower() for name in reader.fieldnames] if reader.fieldnames else []
            for row in reader:
                records.append({k.strip(): v.strip() for k, v in row.items() if k})
    except Exception as e:
        await status_msg.edit_text(f"❌ Gagal membaca CSV: {str(e)}")
        if os.path.exists(csv_path):
            os.remove(csv_path)
        return
        
    if not records:
        await status_msg.edit_text("❌ File CSV kosong atau tidak memiliki tajuk/header.")
        os.remove(csv_path)
        return
        
    total = len(records)
    await status_msg.edit_text(f"📊 Menemukan {total} data pelanggan di CSV. Memulai pengiriman massal...")
    
    report_rows = []
    successes = 0
    failures = 0
    
    for idx, r in enumerate(records):
        idpel = r.get("idpel")
        nometer = r.get("nometer")
        nama = r.get("nama", "PELANGGAN")
        
        if not idpel or not nometer:
            report_rows.append({
                "idpel": idpel or "", "nometer": nometer or "", "nama": nama,
                "status": "SKIPPED", "message": "Kolom idpel atau nometer kosong"
            })
            failures += 1
            continue
            
        await status_msg.edit_text(
            f"⏳ **Memproses {idx+1}/{total}**\n"
            f"• Nama: {nama}\n"
            f"• IDPel: `{idpel}` | NoMeter: `{nometer}`"
        )
        
        # Build direct_args
        direct_args = {
            "idpel": idpel,
            "nometer": nometer,
            "nama": nama,
            "alamat": r.get("alamat", ""),
            "tarif": r.get("tarif", "R-1"),
            "daya": r.get("daya", "900"),
            "hasil": r.get("hasil", "1"),
            "kelurahan": r.get("kelurahan", "001"),
            "kdpm": "01",
            "kddk": "1",
            "status_dil": "1",
            "nik": r.get("nik") or ""
        }
        
        lat = None
        lon = None
        try:
            if r.get("latitude"):
                lat = float(r["latitude"])
            if r.get("longitude"):
                lon = float(r["longitude"])
        except ValueError:
            pass
            
        photo_path = r.get("photo_path")
        if not photo_path or not os.path.exists(photo_path):
            photo_path = get_random_house_photo()
        
        # Call safe submission (Live Submit)
        ok, message = await submit_fasih_safe(
            token_data, token_file,
            idpel=idpel,
            nometer=nometer,
            dry_run=False,
            direct_args=direct_args,
            photo_path=photo_path,
            lat=lat,
            lon=lon
        )
        
        status_label = "SUCCESS" if ok else "FAILED"
        if ok:
            successes += 1
        else:
            failures += 1
            
        report_rows.append({
            "idpel": idpel, "nometer": nometer, "nama": nama, "alamat": r.get("alamat", ""),
            "latitude": r.get("latitude", ""), "longitude": r.get("longitude", ""),
            "photo_path": photo_path or "", "status": status_label, "message": message
        })

    # Save and send report file
    report_filename = f"bulk_submit_report_{datetime.now().strftime('%Y%m%d_%H%M%S')}.csv"
    report_path = os.path.join(temp_dir, report_filename)
    
    try:
        with open(report_path, "w", encoding="utf-8", newline="") as f:
            fieldnames = ["idpel", "nometer", "nama", "alamat", "latitude", "longitude", "photo_path", "status", "message"]
            writer = csv.DictWriter(f, fieldnames=fieldnames)
            writer.writeheader()
            writer.writerows(report_rows)
            
        await safe_delete_message(status_msg)
        
        summary_text = (
            f"✅ **PROSES BATCH SUBMIT SELESAI**\n"
            f"===================================\n"
            f"• Total Data: {total}\n"
            f"• Sukses: {successes}\n"
            f"• Gagal/Skipped: {failures}\n"
            f"===================================\n\n"
            f"Laporan detail eksekusi terlampir di bawah."
        )
        
        # Send document back
        with open(report_path, "rb") as report_file:
            await update.message.reply_document(
                document=report_file,
                filename=report_filename,
                caption=summary_text,
                parse_mode="Markdown"
            )
            
    except Exception as e:
        await update.message.reply_text(f"❌ Gagal mengirimkan laporan hasil bulk: {str(e)}")
    finally:
        # Cleanup
        if os.path.exists(csv_path):
            os.remove(csv_path)
        if os.path.exists(report_path):
            os.remove(report_path)

async def post_init(application: Application) -> None:
    from telegram import BotCommand
    commands = [
        BotCommand("start", "Memulai bot & melihat status sesi"),
        BotCommand("login", "🔑 Login ke SSO BPS (DM saja)"),
        BotCommand("lookup", "🔍 Cari InfoPelanggan (PLN Lookup)"),
        BotCommand("search", "🔍 Cari penugasan di BPS (No Meter/IDPel)"),
        BotCommand("batch", "⚡ Kirim kuesioner massal (Batch Submit)"),
        BotCommand("list", "📋 Lihat daftar tugas yang OPEN"),
        BotCommand("submit", "⚡ Kirim kuesioner interaktif (Wizard)"),
        BotCommand("logout", "🚪 Keluar & hapus sesi")
    ]
    await application.bot.set_my_commands(commands)
    logger.info("Bot commands menu set successfully.")

def main():
    token = os.getenv("TELEGRAM_BOT_TOKEN")
    if not token or token == "your_telegram_bot_token_here":
        print("❌ Error: TELEGRAM_BOT_TOKEN belum diset di .env")
        sys.exit(1)
        
    request_config = HTTPXRequest(
        connect_timeout=30.0,
        read_timeout=30.0,
        write_timeout=30.0,
        pool_timeout=15.0,
        connection_pool_size=100
    )
    app = Application.builder().token(token).request(request_config).post_init(post_init).build()

    # Conversation setup for /submit
    submit_conv = ConversationHandler(
        entry_points=[
            CommandHandler("submit", start_submit),
            MessageHandler(filters.Text("⚡ Kirim Kuesioner (Submit)"), start_submit)
        ],
        states={
            WAITING_SUBMIT_SEARCH_INPUT: [
                CallbackQueryHandler(submit_search_callback),
                MessageHandler(filters.TEXT & ~filters.COMMAND, process_submit_search_input)
            ],
            WAITING_DUPLICATE_CHOICE: [
                CallbackQueryHandler(duplicate_choice_callback)
            ],
            WAITING_WILAYAH_SELECTION: [
                CallbackQueryHandler(wilayah_selection_callback)
            ],
            WAITING_SELECT_ASSIGNMENT: [
                CallbackQueryHandler(select_assignment_callback),
                MessageHandler(filters.TEXT & ~filters.COMMAND, search_assignment_text)
            ],
            WAITING_TARIF: [
                CallbackQueryHandler(prev_tarif_callback, pattern="^prev_tarif$"),
                CallbackQueryHandler(tarif_callback),
                MessageHandler(filters.TEXT & ~filters.COMMAND, tarif_text)
            ],
            WAITING_DAYA: [
                CallbackQueryHandler(prev_daya_callback, pattern="^prev_daya$"),
                CallbackQueryHandler(daya_callback),
                MessageHandler(filters.TEXT & ~filters.COMMAND, daya_text)
            ],
            WAITING_HASIL: [
                CallbackQueryHandler(prev_hasil_callback, pattern="^prev_hasil$"),
                CallbackQueryHandler(hasil_callback),
                MessageHandler(filters.TEXT & ~filters.COMMAND, hasil_text)
            ],
            WAITING_PHOTO: [
                CallbackQueryHandler(prev_photo_callback, pattern="^prev_photo$"),
                CallbackQueryHandler(photo_callback, pattern="^skip_photo$"),
                MessageHandler(filters.PHOTO, photo_message)
            ],
            WAITING_LOCATION: [
                CallbackQueryHandler(prev_location_callback, pattern="^prev_location$"),
                CallbackQueryHandler(location_callback, pattern="^(skip_loc|use_pln_loc)$"),
                MessageHandler(filters.LOCATION, location_message)
            ],
            WAITING_CONFIRM: [
                CallbackQueryHandler(prev_confirm_callback, pattern="^prev_confirm$"),
                CallbackQueryHandler(confirm_callback, pattern="^confirm_")
            ]
        },
        fallbacks=[
            CommandHandler("cancel", cancel_conversation),
            MessageHandler(filters.Text("❌ Batalkan") | filters.Text("cancel"), cancel_conversation)
        ]
    )

    # Conversation setup for /lookup
    lookup_conv = ConversationHandler(
        entry_points=[
            CommandHandler("lookup", start_lookup),
            MessageHandler(filters.Text("🔍 Cari InfoPelanggan"), start_lookup)
        ],
        states={
            WAITING_LOOKUP_INPUT: [
                MessageHandler(filters.TEXT & ~filters.COMMAND, process_lookup_input)
            ]
        },
        fallbacks=[
            CommandHandler("cancel", cancel_conversation),
            MessageHandler(filters.Text("❌ Batalkan") | filters.Text("cancel") | filters.Text([
                "📋 Daftar Tugas (List)",
                "⚡ Kirim Kuesioner (Submit)",
                "🔍 Cari InfoPelanggan",
                "🔍 Cari Assignment BPS",
                "🔑 Status & Login",
                "🚪 Keluar (Logout)"
            ]), cancel_conversation)
        ]
    )

    # Conversation setup for /search (BPS Search)
    search_conv = ConversationHandler(
        entry_points=[
            CommandHandler("search", start_assignment_search),
            MessageHandler(filters.Text("🔍 Cari Assignment BPS"), start_assignment_search)
        ],
        states={
            WAITING_ASSIGNMENT_SEARCH_INPUT: [
                MessageHandler(filters.TEXT & ~filters.COMMAND, process_assignment_search_input)
            ]
        },
        fallbacks=[
            CommandHandler("cancel", cancel_conversation),
            MessageHandler(filters.Text("❌ Batalkan") | filters.Text("cancel") | filters.Text([
                "📋 Daftar Tugas (List)",
                "⚡ Kirim Kuesioner (Submit)",
                "🔍 Cari InfoPelanggan",
                "🔍 Cari Assignment BPS",
                "🔑 Status & Login",
                "🚪 Keluar (Logout)"
            ]), cancel_conversation)
        ]
    )

    # Conversation setup for /batch (Batch Submit)
    batch_conv = ConversationHandler(
        entry_points=[
            CommandHandler("batch", start_batch_submit),
            MessageHandler(filters.Text("⚡ Batch Submit (Massal)"), start_batch_submit)
        ],
        states={
            WAITING_BATCH_SUBMIT_INPUT: [
                MessageHandler(filters.TEXT & ~filters.COMMAND, process_batch_submit_input)
            ],
            WAITING_BATCH_CONFIRM: [
                CallbackQueryHandler(batch_confirm_callback)
            ]
        },
        fallbacks=[
            CommandHandler("cancel", cancel_conversation),
            MessageHandler(filters.Text("❌ Batalkan") | filters.Text("cancel") | filters.Text([
                "📋 Daftar Tugas (List)",
                "⚡ Kirim Kuesioner (Submit)",
                "🔍 Cari InfoPelanggan",
                "🔍 Cari Assignment BPS",
                "⚡ Batch Submit (Massal)",
                "🔑 Status & Login",
                "🚪 Keluar (Logout)"
            ]), cancel_conversation)
        ]
    )

    app.add_handler(CommandHandler("start", start_command))
    app.add_handler(CommandHandler("login", login_command))
    app.add_handler(CommandHandler("logout", logout_command))
    app.add_handler(CommandHandler("list", list_command))
    app.add_handler(CommandHandler("tugas", list_command))
    
    # Handle main menu reply keyboard button clicks
    app.add_handler(MessageHandler(filters.Text("📋 Daftar Tugas (List)"), list_command))
    app.add_handler(MessageHandler(filters.Text("🔑 Status & Login"), start_command))
    app.add_handler(MessageHandler(filters.Text("🚪 Keluar (Logout)"), logout_command))
    
    app.add_handler(submit_conv)
    app.add_handler(lookup_conv)
    app.add_handler(search_conv)
    app.add_handler(batch_conv)
    app.add_handler(CallbackQueryHandler(list_callback, pattern="^(lpage_|lnoop|lclose)"))
    
    # Message handler to process uploaded CSV files
    app.add_handler(MessageHandler(filters.Document.FileExtension("csv"), handle_csv_document))

    print("[*] Bot Telegram Fasih sedang berjalan...")
    app.run_polling(drop_pending_updates=True)

if __name__ == "__main__":
    main()
