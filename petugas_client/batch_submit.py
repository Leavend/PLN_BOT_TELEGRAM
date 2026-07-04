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
import csv
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
)
from fasih_crypto import compute_md5, compute_md5_base64
from fasih_archive import create_7z_archive
from submit_fasih import (
    build_dynamic_answers, stage_and_encrypt, clean_pln_name,
    build_new_assignment_target, get_fallback_coordinate,
    geocode_address,
    STATIC_LEGACY_KEY,
)

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

PLN_API_URL = os.getenv("PLN_API_URL", "").rstrip("/")
PLN_API_KEY = os.getenv("PLN_API_KEY", "")
TOKEN_FILE = os.path.join(REPO_ROOT, "fasih_token.json")

# --- PLN API Client ---

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

def _is_prabayar(tarif: str) -> bool:
    """PLN tarif ending in M = Prabayar (prepaid token), else Pascabayar."""
    return tarif.strip().upper().endswith("M")


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


def submit_single(
    token_data: dict,
    val: str,
    survey_caches: dict,
    dry_run: bool = False,
    temp_dir: str = "",
) -> tuple[bool, str]:
    """Submit single item — picks correct survey (Prabayar/Pascabayar) automatically."""
    try:
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

        if target:
            sc = survey_caches[matched_key]
            tm = sc["template_mapping"]
            i_slot = next((s for s, v in tm.items() if v == "r101a"), "data3")
            n_slot = next((s for s, v in tm.items() if v == "r101b"), "data1")
            status_alias = target.get("assignmentStatusAlias") or ""
            if "SUBMITTED" in status_alias or "DONE" in status_alias or "APPROVED" in status_alias:
                return True, f"Sudah terkirim (Status: {status_alias})."
            direct_args["nama"] = target.get("data2", "") or "PELANGGAN"
            direct_args["alamat"] = target.get("data4", target.get("data5", "")) or ""
            idpel_val = target.get(i_slot) or idpel_val
            nometer_val = target.get(n_slot) or nometer_val
            direct_args["idpel"] = idpel_val
            direct_args["nometer"] = nometer_val

        # Step 5: PLN lookup via server API
        lat, lon = None, None
        pln_data = pln_lookup(idpel=idpel_val, nometer=nometer_val)
        photo_path = None
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

        if not target:
            # No existing assignment — determine survey from PLN tarif
            tarif = direct_args.get("tarif", "")
            if _is_prabayar(tarif) and "PRABAYAR" in survey_caches:
                matched_key = "PRABAYAR"
            elif not _is_prabayar(tarif) and "PASCABAYAR" in survey_caches:
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
            template_assignment_id = template_assignment["id"]
            target = build_new_assignment_target(
                template_assignment, idpel_val, nometer_val, sc["template_mapping"])
            target["data2"] = direct_args.get("nama") or ""
            target["data4"] = direct_args.get("alamat") or ""
            target["data5"] = direct_args.get("alamat") or ""

        # Resolve active survey cache
        sc = survey_caches[matched_key]
        cached_template_mapping = sc["template_mapping"]
        cached_regions = sc["regions"]
        pid = sc["periode"]["id"]

        # Step 6: Build answers
        answers = build_dynamic_answers(target, direct_args, cached_template_mapping)

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

        # Coordinates — PLN coords → target coords → Mapbox geocoding → fallback
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
            alamat_geo = direct_args.get("alamat", "")
            nama_kel = direct_args.get("pln_nama_kel", "") or pln_data.get("nama_kel", "") if pln_data else ""
            nama_kec = direct_args.get("pln_nama_kec", "") or pln_data.get("nama_kec", "") if pln_data else ""
            nama_kab = direct_args.get("pln_nama_kab", "") or pln_data.get("nama_kab", "") if pln_data else ""
            nama_prov = direct_args.get("pln_nama_prov", "") or pln_data.get("nama_prov", "") if pln_data else ""
            lat, lon = geocode_address(alamat_geo, nama_kel, nama_kec, nama_kab, nama_prov)

        if lat is None or lon is None:
            region_name = (target.get("region") or {}).get("name", "")
            addr = direct_args.get("alamat", "")
            lat, lon = get_fallback_coordinate(region_name, "", "", addr)

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

        is_edit = target.get("assignmentStatusAlias") != "OPEN"
        copy_from_id = target.get("copyFromId")
        presign_resp = request_presign_url(
            headers, target["id"], pid,
            [f"{target['id']}.7z"], is_edit, copy_from_id
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
            "paradata": "", "comment": "", "note": ""
        }

        if not dry_run:
            submit_resp = confirm_submit(headers, params, is_edit=is_edit)
            return True, "Sukses: Data berhasil dikirimkan ke BPS!"
        else:
            return True, "Sukses (DRY RUN)"

    except Exception as e:
        logger.error(f"Submit error for {val}: {e}", exc_info=True)
        return False, f"Error: {str(e)}"


# --- Main ---

def main():
    parser = argparse.ArgumentParser(description="Batch Submit Petugas")
    parser.add_argument("input", nargs="?", help="File .txt berisi daftar IDPel/NoMeter (satu per baris)")
    parser.add_argument("--list", "-l", help="Daftar IDPel/NoMeter dipisah koma")
    parser.add_argument("--dry-run", action="store_true", help="Test tanpa submit ke BPS")
    parser.add_argument("--delay", type=float, default=2.0, help="Rata-rata delay antar item (detik)")
    args = parser.parse_args()

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
    else:
        parser.print_help()
        sys.exit(1)

    if not items:
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
    print(f"\n📋 Total item: {len(items)}")
    if args.dry_run:
        print("🧪 Mode: DRY RUN (tidak submit ke BPS)")
    print()

    # Step 1: Login
    token_data = ensure_login()
    headers = get_headers(token_data)

    # Step 2-3: Fetch surveys + assignments (cached once, both Prabayar & Pascabayar)
    print("📊 Mengambil data survei dari BPS...")
    surveys = fetch_surveys(headers)
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

        print(f"📋 Mengambil tugas {skey}...")
        assignments = fetch_all_assignments(headers, pid)
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

    # Process items
    print(f"\n{'='*50}")
    print(f"⚡ MEMULAI BATCH SUBMIT — {len(items)} item")
    print(f"{'='*50}\n")

    successes = 0
    failures = 0
    report_rows = []
    start_time = time.time()

    with tempfile.TemporaryDirectory() as temp_dir:
        for idx, val in enumerate(items):
            elapsed = time.time() - start_time
            rate = (idx / (elapsed / 60)) if elapsed > 0 and idx > 0 else 0
            remaining = len(items) - idx
            eta = (remaining / rate * 60) if rate > 0 else 0

            print(f"[{idx+1}/{len(items)}] {val}", end=" ... ", flush=True)

            ok, message = submit_single(
                token_data, val, survey_caches,
                dry_run=args.dry_run, temp_dir=temp_dir
            )

            if ok:
                successes += 1
                print(f"✅ {message}")
            else:
                failures += 1
                print(f"❌ {message}")

            report_rows.append({
                "val": val, "status": "SUCCESS" if ok else "FAILED", "message": message
            })

            # Cleanup temp files from this iteration
            for f in os.listdir(temp_dir):
                try:
                    os.remove(os.path.join(temp_dir, f))
                except OSError:
                    pass

            # Progress bar
            pct = int((idx + 1) / len(items) * 100)
            filled = pct // 5
            bar = "█" * filled + "░" * (20 - filled)
            elapsed_str = f"{int(elapsed//60)}m{int(elapsed%60)}s"
            eta_str = f"{int(eta//60)}m{int(eta%60)}s" if eta > 0 else "—"
            print(f"   [{bar}] {pct}% | ✅{successes} ❌{failures} | ⏱{elapsed_str} ETA:{eta_str} | {rate:.1f}/min")

            # Human-like delay
            if idx < len(items) - 1:
                delay = max(1.0, min(5.0, random.gauss(args.delay, 1.0)))
                time.sleep(delay)

    # Report
    elapsed_total = time.time() - start_time
    print(f"\n{'='*50}")
    print(f"🏁 BATCH SELESAI")
    print(f"   ✅ Sukses: {successes}")
    print(f"   ❌ Gagal:  {failures}")
    print(f"   ⏱  Waktu:  {int(elapsed_total//60)}m {int(elapsed_total%60)}s")
    print(f"{'='*50}")

    # Save CSV report
    report_file = f"batch_report_{datetime.now().strftime('%Y%m%d_%H%M%S')}.csv"
    with open(report_file, "w", encoding="utf-8", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=["val", "status", "message"])
        writer.writeheader()
        writer.writerows(report_rows)
    print(f"\n📄 Report: {report_file}")


if __name__ == "__main__":
    main()
