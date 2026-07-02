#!/usr/bin/env python3
import os
import sys
import json
import base64
import argparse
import tempfile
import hashlib
import requests
from datetime import datetime
from typing import Optional, Dict, Any

# Import modular helper scripts
from fasih_auth import load_token, refresh_token_if_needed, get_headers
from fasih_crypto import encrypt_gcm, decrypt_gcm_verify, compute_md5, compute_md5_base64
from fasih_archive import create_7z_archive
from fasih_api import (
    fetch_surveys, fetch_assignments, fetch_all_assignments, fetch_regions, request_presign_url, upload_to_s3,
    request_photo_presign_put, upload_photo_to_s3, request_photo_presign_get, confirm_submit,
    fetch_template_mapping, map_answers_to_data_slots
)

STATIC_LEGACY_KEY = "Z!,vDKUPv;.Jy0Q4Eq1wVCY-a_!GnT"

def print_assignment(i: int, a: dict):
    aid = a.get("id", "")
    status = a.get("assignmentStatusAlias", "?")
    mode = a.get("mode", [])
    strata = a.get("strata", "?")
    d1, d2, d3, d4 = a.get("data1", ""), a.get("data2", ""), a.get("data3", ""), a.get("data4", "")
    region = a.get("region") or {}
    l1 = region.get("level1") or {}
    l2 = l1.get("level2") or {}
    l3 = l2.get("level3") or {}
    region_str = f"{l3.get('name','')} ({l2.get('name','')})"
    status_icon = "🟢" if status == "OPEN" else "🔵" if status == "SUBMITTED" else "⚪"
    print(f"\n     {status_icon} [{i+1}] Assignment: {aid[:20]}...")
    print(f"        Status: {status} | Mode: {','.join(mode)} | Strata: {strata}")
    print(f"        Region: {region_str}")
    print(f"        Data: d1={d1} d2={d2} d3={d3} d4={d4}")

def list_periode_assignments(headers: dict, pid: str):
    try:
        content = fetch_all_assignments(headers, pid)
        total = len(content)
        print(f"     📊 Total assignments: {total}")
        for i, a in enumerate(content):
            print_assignment(i, a)
    except Exception as e:
        print(f"     ⚠️  Error fetching assignments: {e}")

def list_survey_periodes(headers: dict, survey: dict):
    survey_name = survey.get("name", "Unknown")
    survey_id = survey.get("id")
    print(f"\n  📋 Survei: {survey_name}")
    print(f"     ID: {survey_id}")
    for periode in survey.get("listPeriode", []):
        pid = periode.get("id")
        pname = periode.get("name")
        start = periode.get("startDate", "?")
        end = periode.get("endDate", "?")
        active = periode.get("isActive", False)
        print(f"\n     📅 Periode: {pname} ({start} → {end}) {'✅ AKTIF' if active else '❌'}")
        if active:
            list_periode_assignments(headers, pid)

def cmd_list(headers: dict):
    """List all OPEN assignments."""
    print("\n" + "=" * 70)
    print("  DAFTAR ASSIGNMENT")
    print("=" * 70)
    surveys = fetch_surveys(headers)
    if not surveys:
        print("  Tidak ada survei yang ditemukan.")
        return
    for survey in surveys:
        list_survey_periodes(headers, survey)

def load_answers_from_input(input_path: str, verbose: bool) -> dict:
    with open(input_path, "r", encoding="utf-8") as f:
        answers = json.load(f)
    print(f"      Loaded {len(answers)} fields from {input_path}")
    if verbose:
        for k, v in answers.items():
            print(f"        {k}: {v}")
    return answers

def find_assignment_by_id(content: list, assignment_id: str) -> Optional[dict]:
    for a in content:
        if a.get("id") == assignment_id:
            return a
    return None

def find_assignment_by_direct_args(content: list, template_mapping: dict, idpel: Optional[str], nometer: Optional[str]) -> Optional[dict]:
    idpel_slot = next((slot for slot, var in template_mapping.items() if var == "r101a"), "data3")
    nometer_slot = next((slot for slot, var in template_mapping.items() if var == "r101b"), "data1")
    for a in content:
        match_idpel = True
        match_nometer = True
        if idpel:
            match_idpel = (a.get(idpel_slot) == idpel)
        if nometer:
            match_nometer = (a.get(nometer_slot) == nometer)
        if (idpel or nometer) and (match_idpel and match_nometer):
            return a
    return None

def build_new_assignment_target(template: dict, idpel: str, nometer: str, template_mapping: dict) -> dict:
    import uuid
    import copy
    t = copy.deepcopy(template)
    t["id"] = str(uuid.uuid4())
    t["isNew"] = True
    t["assignmentStatusAlias"] = "OPEN"
    t["copyFromId"] = template["id"]
    t["original"] = False
    t["mode"] = ["CAPI"]
    t["submitVersionCode"] = 0
    t["comment"] = '{"dataKey": "","notes": []}'
    
    # Reset custom data slots
    for k in ("data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10"):
        t[k] = ""
        
    idpel_slot = next((slot for slot, var in template_mapping.items() if var == "r101a"), "data3")
    nometer_slot = next((slot for slot, var in template_mapping.items() if var == "r101b"), "data1")
    t[idpel_slot] = idpel
    t[nometer_slot] = nometer
    
    for k in ("latitude", "longitude", "mediaJson", "remark"):
        t.pop(k, None)
    return t

def find_first_open_assignment(content: list) -> Optional[dict]:
    for a in content:
        if a.get("assignmentStatusAlias") == "OPEN":
            return a
    return None

def select_target_assignment(content: list, template_mapping: dict, assignment_id: Optional[str], direct_args: Optional[dict]) -> dict:
    if assignment_id:
        target = find_assignment_by_id(content, assignment_id)
    elif direct_args:
        target = find_assignment_by_direct_args(content, template_mapping, direct_args["idpel"], direct_args["nometer"])
    else:
        target = find_first_open_assignment(content)
    if not target:
        print("[-] Target assignment not found.")
        sys.exit(1)
    return target

def resolve_survey_period_and_mapping(surveys: list, headers: dict) -> tuple:
    if not surveys:
        print("[-] No surveys found.")
        sys.exit(1)
    survey = surveys[0]
    active_periode = next((p for p in survey.get("listPeriode", []) if p.get("isActive")), None)
    if not active_periode:
        print("[-] No active period found.")
        sys.exit(1)
    template_lookup = survey.get("templateLookup", [])
    template_mapping = {}
    if template_lookup:
        tl = template_lookup[0]
        template_mapping = fetch_template_mapping(headers, tl["templateId"], tl["templateVersion"])
    return active_periode, template_mapping

def resolve_answers(input_path: Optional[str], target: dict, direct_args: Optional[dict], template_mapping: dict, verbose: bool) -> dict:
    if input_path:
        return load_answers_from_input(input_path, verbose)
    elif direct_args:
        return build_dynamic_answers(target, direct_args, template_mapping)
    else:
        print("[-] Either --input or --idpel/--nometer must be specified.")
        sys.exit(1)

def parse_predefined(target: dict) -> dict:
    answers = {}
    pre_defined_str = target.get("preDefinedData")
    if pre_defined_str:
        try:
            predata = json.loads(pre_defined_str).get("predata", [])
            for item in predata:
                key = item.get("dataKey")
                if key:
                    answers[key] = item.get("answer") or ""
        except Exception as e:
            print(f"      ⚠️  Error parsing preDefinedData: {e}")
    return answers

def generate_random_nik(province_code: str = "64", regency_code: str = "74", district_code: str = "02") -> str:
    import random
    p_c = str(province_code or "64")[:2].zfill(2)
    r_c = str(regency_code or "74")[:2].zfill(2)
    d_c = str(district_code or "02")[:2].zfill(2)
    dob_day = random.randint(1, 28)
    if random.choice([True, False]): # female
        dob_day += 40
    dob_month = random.randint(1, 12)
    dob_year = random.randint(50, 99)
    dob_str = f"{dob_day:02d}{dob_month:02d}{dob_year:02d}"
    seq = random.randint(1, 999)
    return f"{p_c}{r_c}{d_c}{dob_str}{seq:04d}"

def generate_random_phone() -> str:
    import random
    prefix = random.choice(["0812", "0813", "0821", "0822", "0852", "0853", "0811", "0817", "0818", "0819", "0859", "0815", "0816"])
    digits = "".join(str(random.randint(0, 9)) for _ in range(8))
    return f"{prefix}{digits}"

def generate_random_comment() -> str:
    import random
    comments = [
        "Pendataan berjalan lancar, responden sangat kooperatif.",
        "Rumah sesuai dengan koordinat, data ID pelanggan valid.",
        "Responden kooperatif, informasi tarif dan daya sesuai.",
        "Data berhasil dicatat lengkap, kondisi fisik rumah sesuai foto.",
        "Wawancara dilaksanakan dengan lancar bersama pemilik rumah.",
        "Kondisi meteran berfungsi dengan baik, tidak ada kendala."
    ]
    return random.choice(comments)

import re

def clean_pln_name(name: str) -> str:
    if not name:
        return ""
    # Strip any numeric suffixes/digits
    # E.g., "ABDUL RAHMAN 02" or "ABDUL RAHMAN02" or "01 ABDUL"
    # To handle trailing digits specifically (like "ABDUL RAHMAN 02"):
    cleaned = re.sub(r'\s*\d+\s*$', '', name) # removes trailing digits with optional space
    # Remove any other remaining digits anywhere
    cleaned = re.sub(r'\d+', '', cleaned)
    # Remove any character not in [a-zA-Z\s\'.]
    cleaned = re.sub(r"[^a-zA-Z\s'\.]", '', cleaned)
    # Normalize spaces
    cleaned = re.sub(r'\s+', ' ', cleaned).strip()
    return cleaned.upper()

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

def resolve_r204_from_keperluan(keperluan: str) -> str:
    if not keperluan:
        return "1. Milik sendiri"
    kep_upper = keperluan.upper()
    if any(x in kep_upper for x in ["KOST", "KOS", "KONTRAK", "SEWA"]):
        return "2. Kontrak/sewa"
    if "DINAS" in kep_upper:
        return "4. Dinas"
    return "1. Milik sendiri"

_kec_lookup_cache = None
_desa_lookup_cache = None

def load_regional_lookups():
    global _kec_lookup_cache, _desa_lookup_cache
    if _kec_lookup_cache is not None and _desa_lookup_cache is not None:
        return _kec_lookup_cache, _desa_lookup_cache
        
    script_dir = os.path.dirname(os.path.abspath(__file__))
    kec_path = os.path.join(script_dir, "lookups", "mfd25s1_kec_1_batch.json")
    desa_path = os.path.join(script_dir, "lookups", "mfd25s1_desa_1_batch.json")
    
    kec_cache = {}
    desa_cache = {}
    
    if os.path.exists(kec_path):
        try:
            import json
            import ast
            with open(kec_path, "r", encoding="utf-8") as f:
                data = json.load(f)
            for chunk in data.get("data", []):
                parsed = ast.literal_eval(f"[{chunk}]")
                for row in parsed:
                    code = row[0]
                    name_raw = row[1]
                    if name_raw.startswith("[") and "]" in name_raw:
                        name = name_raw.split("]", 1)[1].strip().upper()
                    else:
                        name = name_raw.strip().upper()
                    kec_cache[name] = {
                        "code": code,
                        "full_name": name_raw,
                        "l1_code": row[3],
                        "l2_code": row[4]
                    }
        except Exception as e:
            print(f"[!] Error loading kec lookup: {e}")
            
    if os.path.exists(desa_path):
        try:
            import json
            import ast
            with open(desa_path, "r", encoding="utf-8") as f:
                data = json.load(f)
            for chunk in data.get("data", []):
                parsed = ast.literal_eval(f"[{chunk}]")
                for row in parsed:
                    kec_code = row[0]
                    name_raw = row[1]
                    full_code = row[2]
                    if name_raw.startswith("[") and "]" in name_raw:
                        name = name_raw.split("]", 1)[1].strip().upper()
                    else:
                        name = name_raw.strip().upper()
                    desa_cache[(kec_code, name)] = {
                        "full_code": full_code,
                        "full_name": name_raw
                    }
                    if name not in desa_cache:
                        desa_cache[name] = {
                            "full_code": full_code,
                            "full_name": name_raw,
                            "kec_code": kec_code
                        }
        except Exception as e:
            print(f"[!] Error loading desa lookup: {e}")
            
    _kec_lookup_cache = kec_cache
    _desa_lookup_cache = desa_cache
    return kec_cache, desa_cache

def resolve_region_codes_and_names(target: dict, direct_args: dict):
    # Default from BPS target assignment
    region = target.get("region") or {}
    l1 = region.get("level1") or {}
    l2 = l1.get("level2") or {}
    l3 = l2.get("level3") or {}
    l4 = l3.get("level4") or {}
    
    l1_code = l1.get("code") or "64"
    l1_name = l1.get("name") or "KALIMANTAN TIMUR"
    l2_code = l2.get("code") or "74"
    l2_name = l2.get("name") or "KOTA BONTANG"
    l2_fullcode = l2.get("fullCode") or "6474"
    l3_code = l3.get("code") or "02"
    l3_name = l3.get("name") or "BONTANG SELATAN"
    l3_fullcode = l3.get("fullCode") or "6474020"
    l4_code = l4.get("code") or "003"
    l4_name = l4.get("name") or "BERBAS PANTAI"
    l4_fullcode = l4.get("fullCode") or "6474020003"
    
    # Try name-based lookup resolution using the BPS lookups
    pln_nama_kec = str(direct_args.get("pln_nama_kec") or "").strip().upper()
    pln_nama_kel = str(direct_args.get("pln_nama_kel") or "").strip().upper()
    
    lookup_success = False
    if pln_nama_kec and pln_nama_kel:
        try:
            kec_cache, desa_cache = load_regional_lookups()
            target_kec = pln_nama_kec
            target_kel = pln_nama_kel
            
            kec_info = kec_cache.get(target_kec)
            if kec_info:
                l1_code = kec_info["l1_code"]
                l1_name = "KALIMANTAN TIMUR"
                l2_code = kec_info["l2_code"][-2:]
                l2_name = "KOTA BONTANG"
                l2_fullcode = kec_info["l2_code"]
                l3_code = kec_info["code"][-3:]
                l3_name = kec_info["full_name"]
                l3_fullcode = kec_info["code"]
                
                # Look up village using kec_code and kel_name
                desa_info = desa_cache.get((kec_info["code"], target_kel))
                if not desa_info:
                    desa_info = desa_cache.get(target_kel)
                    
                if desa_info:
                    l4_code = desa_info["full_code"][-3:]
                    l4_name = desa_info["full_name"]
                    l4_fullcode = desa_info["full_code"]
                    lookup_success = True
        except Exception as e:
            print(f"[!] Warning during name-based lookup: {e}")
            
    if not lookup_success:
        # Override with PLN/AP2T database values if present (slicing fallback)
        pln_kd_kel = str(direct_args.get("pln_kd_kel") or "").strip()
        if len(pln_kd_kel) == 10 and pln_kd_kel.isdigit():
            # PLN format: 2 Prov + 2 Kab + 2 Kec + 4 Kel = 10 digits
            # BPS format: 2 Prov + 2 Kab + 3 Kec + 3 Kel = 10 digits
            pln_prov = pln_kd_kel[0:2]
            pln_kab = pln_kd_kel[2:4]
            pln_kec = pln_kd_kel[4:6]
            pln_kel = pln_kd_kel[6:10]

            l1_code = pln_prov
            l2_code = pln_kab
            l2_fullcode = pln_prov + pln_kab
            l3_code = pln_kec + "0"
            l3_fullcode = pln_prov + pln_kab + pln_kec + "0"
            l4_code = pln_kel[1:4]
            l4_fullcode = pln_prov + pln_kab + pln_kec + "0" + pln_kel[1:4]
            
            l1_name = str(direct_args.get("pln_nama_prov") or l1_name).strip().upper()
            l2_name = str(direct_args.get("pln_nama_kab") or l2_name).strip().upper()
            l3_name = str(direct_args.get("pln_nama_kec") or l3_name).strip().upper()
            l4_name = str(direct_args.get("pln_nama_kel") or l4_name).strip().upper()
        else:
            # Fallback to name-only overrides if no code is present but names are
            if direct_args.get("pln_nama_prov"):
                l1_name = str(direct_args.get("pln_nama_prov")).strip().upper()
            if direct_args.get("pln_nama_kab"):
                l2_name = str(direct_args.get("pln_nama_kab")).strip().upper()
            if direct_args.get("pln_nama_kec"):
                l3_name = str(direct_args.get("pln_nama_kec")).strip().upper()
            if direct_args.get("pln_nama_kel"):
                l4_name = str(direct_args.get("pln_nama_kel")).strip().upper()

    return {
        "l1_code": l1_code, "l1_name": l1_name,
        "l2_code": l2_code, "l2_name": l2_name, "l2_fullcode": l2_fullcode,
        "l3_code": l3_code, "l3_name": l3_name, "l3_fullcode": l3_fullcode,
        "l4_code": l4_code, "l4_name": l4_name, "l4_fullcode": l4_fullcode
    }

def get_region_fields(target: dict, direct_args: dict) -> dict:
    res = resolve_region_codes_and_names(target, direct_args)
    l1_code, l1_name = res["l1_code"], res["l1_name"]
    l2_code, l2_name = res["l2_code"], res["l2_name"]
    l3_code, l3_name = res["l3_code"], res["l3_name"]
    l4_code, l4_name = res["l4_code"], res["l4_name"]
    
    r103_name = target.get("data2") or direct_args.get("nama") or ""
    if r103_name:
        r103_name = clean_pln_name(str(r103_name))
        
    return {
        "r102a": f"[{l1_code}] {l1_name}",
        "r102b": f"[{l2_code}] {l2_name}",
        "r102c": f"[{l3_code}] {l3_name}",
        "r102d": f"[{l4_code}] {l4_name}",
        "r102e": target.get("data4") or direct_args.get("alamat") or "",
        "r103": r103_name
    }

def build_dynamic_answers(target: dict, direct_args: dict, template_mapping: dict) -> dict:
    answers = parse_predefined(target)
    for slot, field_key in template_mapping.items():
        val = target.get(slot)
        if val:
            answers[field_key] = val
    
    # Update region-based fields
    answers.update(get_region_fields(target, direct_args))
    
    # Baseline/PLN-specific metadata fields
    answers.update({
        "tarif": direct_args.get("tarif") or "R-1",
        "daya": direct_args.get("daya") or "900",
        "kdpm": direct_args.get("kdpm") or "01",
        "kddk": direct_args.get("kddk") or "1",
        "layanan": "PRABAYAR" if "PRABAYAR" in target.get("assignmentStatusAlias", "") else "PASCABAYAR",
        "r104": direct_args.get("hasil") or "1. Berhasil didata",
        "status_dil": direct_args.get("status_dil") or "1"
    })
    
    # Populate Blok II, III, and IV fields
    res_reg = resolve_region_codes_and_names(target, direct_args)
    l1_code = res_reg["l1_code"]
    l1_name = res_reg["l1_name"]
    l2_code = res_reg["l2_code"]
    l2_name = res_reg["l2_name"]
    l2_fullcode = res_reg["l2_fullcode"]
    l3_code = res_reg["l3_code"]
    l3_name = res_reg["l3_name"]
    l3_fullcode = res_reg["l3_fullcode"]
    l4_code = res_reg["l4_code"]
    l4_name = res_reg["l4_name"]
    l4_fullcode = res_reg["l4_fullcode"]
    
    # Store resolved region info for wrap_answers
    answers.update({
        "_l1_code": l1_code,
        "_l1_name": l1_name,
        "_l2_code": l2_code,
        "_l2_name": l2_name,
        "_l2_fullcode": l2_fullcode,
        "_l3_code": l3_code,
        "_l3_name": l3_name,
        "_l3_fullcode": l3_fullcode,
        "_l4_code": l4_code,
        "_l4_name": l4_name,
        "_l4_fullcode": l4_fullcode
    })
    
    # Prioritize name from PLN/AP2T if available
    pln_nama = direct_args.get("pln_nama") or ""
    pln_nama = pln_nama.strip()
    if pln_nama and pln_nama != "NoName":
        r201_name = clean_pln_name(pln_nama)
        answers["r103"] = r201_name
    else:
        r201_name = clean_pln_name(answers.get("r103") or "PELANGGAN")
        answers["r103"] = r201_name

    # Prioritize NIK from PLN/AP2T if available and valid
    nik = direct_args.get("pln_nik") or direct_args.get("nik") or ""
    nik = str(nik).strip()
    if not nik or len(nik) != 16 or not nik.isdigit():
        nik = generate_random_nik(l1_code, l2_code, l3_code)

    # Blok II (Keterangan Penghuni Bangunan Tempat Tinggal)
    r204_val = resolve_r204_from_keperluan(direct_args.get("keperluan"))
    answers.update({
        "r201": r201_name,
        "r202": nik,
        "r203": generate_random_phone(),
        "r204": r204_val
    })
    
    # Prioritize address from PLN/AP2T if available
    pln_alamat = direct_args.get("pln_alamat") or direct_args.get("alamat") or ""
    pln_alamat = pln_alamat.strip()
    if pln_alamat:
        r301e_val = pln_alamat
        answers["r102e"] = pln_alamat
    else:
        r301e_val = answers.get("r102e") or ""

    # Blok III (Keterangan Keluarga Pengguna Meteran)
    answers.update({
        "r301a": f"[{l1_code}] {l1_name}",
        "r301b": f"[{l2_code}] {l2_name}",
        "r301c": f"[{l3_code}] {l3_name}",
        "r301d": f"[{l4_code}] {l4_name}",
        "r301e": r301e_val,
        "r302a": 1,
        "r302a_var": "1",
        "r302a_no#1": 1,
        "r302b_1#1": nik
    })
    
    # Blok IV (Catatan)
    answers.update({
        "catatan": generate_random_comment()
    })
    
    return answers

def get_s3_put_url(headers: dict, target: dict, filename: str, size: int, md5_base64: str, dry_run: bool = False) -> str:
    try:
        resp = request_photo_presign_put(headers, target.get("id"), target.get("copyFromId") or "", target.get("surveyPeriodId"), filename, size, md5_base64)
        if not resp.get("success"):
            if dry_run:
                return "http://mock-photo-put-url"
            raise Exception(f"Failed to get S3 PUT URL: {resp}")
        data = resp.get("data", [])
        urls = data[0].get("presignedUrls", []) if data else []
        put_url = urls[0].get("presignedUrl") or urls[0].get("url") if urls else None
        if not put_url:
            if dry_run:
                return "http://mock-photo-put-url"
            raise Exception(f"S3 PUT URL empty: {resp}")
        return put_url
    except Exception as e:
        if dry_run:
            return "http://mock-photo-put-url"
        raise e

def get_s3_get_url(headers: dict, target: dict, filename: str, dry_run: bool = False) -> str:
    try:
        resp = request_photo_presign_get(headers, target.get("id"), target.get("copyFromId") or "", target.get("surveyPeriodId"), filename)
        data = resp.get("data", [])
        urls = data[0].get("presignedUrls", []) if data else []
        return urls[0].get("presignedUrl") or urls[0].get("url") or ""
    except Exception as e:
        if dry_run:
            return "http://mock-photo-get-url"
        raise e

def handle_photo_upload(headers: dict, target: dict, answers: dict, photo_path: str, dry_run: bool):
    if not photo_path:
        return
    if not os.path.exists(photo_path):
        print(f"[-] Photo file not found: {photo_path}")
        sys.exit(1)
    tid = target.get("id")
    filename = f"{tid}_r106.png"
    md5_b64 = compute_md5_base64(photo_path)
    put_url = get_s3_put_url(headers, target, filename, os.path.getsize(photo_path), md5_b64, dry_run)
    if not dry_run:
        if not upload_photo_to_s3(put_url, photo_path, md5_b64):
            print("[-] S3 photo upload failed.")
            sys.exit(1)
    get_url = get_s3_get_url(headers, target, filename, dry_run)
    answers["r106"] = json.dumps({
        "filename": filename,
        "uri": f"content://media/external/images/media/{hashlib.md5(tid.encode()).hexdigest()[:8]}",
        "url": get_url
    }, ensure_ascii=False)

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

def handle_coords(answers: dict, lat: Optional[float], lon: Optional[float], target: dict) -> tuple:
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
            region_name = (target.get("region") or {}).get("name", "")
            lat, lon = get_fallback_coordinate(region_name, "", "", addr)
            
    answers["r105"] = {
        "coordinat": {"latitude": lat, "longitude": lon},
        "remark": "",
        "accuracy": 10.0
    }
    return lat, lon

def get_encryption_key(headers: dict, target: dict, region_id: str) -> bytes:
    pid = target.get("surveyPeriodId")
    regions = fetch_regions(headers, pid)
    wrapped_key = None
    for r in regions:
        if r.get("region_id") == region_id or (r.get("region") or {}).get("id") == region_id:
            wrapped_key = r.get("wrappedDatakey")
            break
    if not wrapped_key:
        wrapped_key = STATIC_LEGACY_KEY
    try:
        return base64.b64decode(wrapped_key.encode("utf-8"))
    except Exception:
        return STATIC_LEGACY_KEY.encode("utf-8")

def get_user_name_from_headers(headers: dict) -> str:
    try:
        auth = headers.get("Authorization") or ""
        if auth.startswith("Bearer "):
            token = auth.split(" ")[1]
            payload_b64 = token.split(".")[1]
            payload_b64 += "=" * (4 - len(payload_b64) % 4)
            jwt_payload = json.loads(base64.urlsafe_b64decode(payload_b64.encode("utf-8")))
            return jwt_payload.get("name") or jwt_payload.get("email") or "Nadif Firjatullah"
    except Exception:
        pass
    return "Nadif Firjatullah"

def wrap_answers(flat_answers: dict, target: dict, user_name: str) -> dict:
    import time
    from datetime import datetime, timedelta
    
    region = target.get("region") or {}
    l1 = region.get("level1") or {}
    l2 = l1.get("level2") or {}
    l3 = l2.get("level3") or {}
    l4 = l3.get("level4") or {}
    
    l1_code = flat_answers.get("_l1_code") or l1.get("code") or "64"
    l1_name = flat_answers.get("_l1_name") or l1.get("name") or "KALIMANTAN TIMUR"
    l2_code = flat_answers.get("_l2_code") or l2.get("code") or "74"
    l2_name = flat_answers.get("_l2_name") or l2.get("name") or "KOTA BONTANG"
    l2_fullcode = flat_answers.get("_l2_fullcode") or l2.get("fullCode") or "6474"
    l3_code = flat_answers.get("_l3_code") or l3.get("code") or "02"
    l3_name = flat_answers.get("_l3_name") or l3.get("name") or "BONTANG SELATAN"
    l3_fullcode = flat_answers.get("_l3_fullcode") or l3.get("fullCode") or "6474020"
    l4_code = flat_answers.get("_l4_code") or l4.get("code") or "003"
    l4_name = flat_answers.get("_l4_name") or l4.get("name") or "BERBAS PANTAI"
    l4_fullcode = flat_answers.get("_l4_fullcode") or l4.get("fullCode") or "6474020003"
    
    now_ms = int(time.time() * 1000)
    now = datetime.now()
    created_at = now.strftime("%Y-%m-%dT%H:%M:%S.%f")[:-3] + "Z"

    # 15 minutes duration
    start_time = (now - timedelta(minutes=15)).strftime("%Y-%m-%dT%H:%M:%S")
    end_time = now.strftime("%Y-%m-%dT%H:%M:%S")
    
    # IDPel HTML Check status
    idpel = flat_answers.get("r101a") or ""
    hasil_check_html = f"""
        <div class="font-normal border-2 text-center note"
        style="padding: 0.8em; color: rgb(21, 128, 61); border-color: rgb(21, 128, 61); font-size: 12px;">
        <table style="border-collapse: collapse;">
          <tr>
            <td style="vertical-align: top; white-space: nowrap;">ID PELANGGAN</td>
            <td style="vertical-align: top;">
                : <b>[ <span style="white-space: nowrap;">{idpel}</span> ]</b>
            </td>
          </tr>
          <tr>
            <td style="vertical-align: top;">STATUS</td>
            <td style="vertical-align: top;">
                : <b>DITEMUKAN DAN BELUM TERCATAT PADA SISTEM FASIH</b>
            </td>
          </tr>
        </table>
        </div>"""
        
    # NIK Check HTML status
    nik = flat_answers.get("r202") or ""
    hasil_nik_html = f"""
            <div class="font-normal border-2 text-center"
            style="padding: 0.8em; color: rgb(122, 32, 64); border-color: rgb(122, 32, 64); font-size: 12.5px;">
            <b>NIK : [ {nik} ] <br>TIDAK DITEMUKAN</b>
            </div>"""
            
    # PLN lookup data
    pln_data = {
        "data": {
            "alamat": flat_answers.get("r102e") or "",
            "exists": True,
            "fasih_exists": False,
            "fasih_is_prelist": None,
            "fasih_source": None,
            "kode_desa": l4_fullcode,
            "kode_kab": l2_fullcode,
            "kode_kec": l3_fullcode,
            "kode_prov": l1_code,
            "nama": flat_answers.get("r103") or "",
            "nama_desa": l4_name,
            "nama_kab": l2_name,
            "nama_kec": l3_name,
            "nama_prov": l1_name,
            "nomor_meter": flat_answers.get("r101b") or "",
            "prelist_source": "prabayar",
            "success": True,
            "unitap": flat_answers.get("unitap") or "23BTG"
        },
        "success": True,
        "message": "Successfully hit an API.",
        "httpStatus": "OK"
    }
    
    result_callnik_str = '{"data":{"alamat":null,"exists":false,"nama":null,"nomor_kartu_keluarga":null,"success":true},"success":true,"message":"Successfully hit an API.","httpStatus":"OK"}'
    
    # Parse r105 (coords)
    r105_val = flat_answers.get("r105")
    lat, lon = 0.0, 0.0
    if isinstance(r105_val, dict):
        coord = r105_val.get("coordinat") or {}
        lat = coord.get("latitude") or 0.0
        lon = coord.get("longitude") or 0.0
    elif isinstance(r105_val, list):
        r105_answer_list = r105_val
        
    if not isinstance(r105_val, list):
        r105_answer_list = [
            {"label": f"https://maps.google.com/maps?q={lat},{lon}", "value": {"latitude": lat, "accuracy": 3.7, "longitude": lon}},
            {"label": "map", "value": f"https://maps.google.com/maps?q={lat},{lon}"},
            {"label": "latitude", "value": lat},
            {"label": "longitude", "value": lon},
            {"label": "accuracy", "value": 3.7}
        ]
        
    # Parse r106 (photo)
    r106_val = flat_answers.get("r106")
    r106_answer_list = []
    if r106_val:
        try:
            if isinstance(r106_val, str):
                r106_data = json.loads(r106_val)
            else:
                r106_data = r106_val
            
            if isinstance(r106_data, list):
                r106_answer_list = r106_data
            elif isinstance(r106_data, dict):
                r106_answer_list = [r106_data]
        except Exception:
            pass
            
    # Map raw value of r104
    r104_val = flat_answers.get("r104") or "1. Berhasil didata"
    r104_code = "1"
    if r104_val and "." in r104_val:
        r104_code = r104_val.split(".")[0].strip()
    r104_answer = [{"description": "", "label": r104_val, "value": r104_code, "open": False}]
    
    # Map raw value of r204
    r204_val = flat_answers.get("r204") or "1. Milik sendiri"
    r204_code = "1"
    if r204_val and "." in r204_val:
        r204_code = r204_val.split(".")[0].strip()
    r204_answer = [{"description": "", "label": r204_val, "value": r204_code, "open": False}]
    
    answers_list = [
        {"dataKey": "mulai", "answer": start_time},
        {"dataKey": "r101a", "answer": flat_answers.get("r101a") or ""},
        {"dataKey": "result_idpln", "answer": json.dumps(pln_data, ensure_ascii=False)},
        {"dataKey": "hasilCheckIdPel2", "answer": "2"},
        {"dataKey": "hasilCheckIdPel", "answer": hasil_check_html},
        {"dataKey": "r101b", "answer": flat_answers.get("r101b") or ""},
        {"dataKey": "hasilCheckNoMeter2", "answer": None},
        {"dataKey": "r102a", "answer": flat_answers.get("r102a") or ""},
        {"dataKey": "r102b", "answer": flat_answers.get("r102b") or ""},
        {"dataKey": "r102c", "answer": flat_answers.get("r102c") or ""},
        {"dataKey": "r102d", "answer": flat_answers.get("r102d") or ""},
        {"dataKey": "r102e", "answer": flat_answers.get("r102e") or ""},
        {"dataKey": "r103", "answer": flat_answers.get("r103") or ""},
        {"dataKey": "r104", "answer": r104_answer},
        {"dataKey": "r105", "answer": r105_answer_list},
        {"dataKey": "r106", "answer": r106_answer_list},
        {"dataKey": "unitup", "answer": flat_answers.get("unitup") or ""},
        {"dataKey": "unitupi", "answer": flat_answers.get("unitupi") or ""},
        {"dataKey": "unitap", "answer": flat_answers.get("unitap") or "23BTG"},
        {"dataKey": "r201", "createdAt": now_ms, "answer": flat_answers.get("r201") or "", "updatedAt": now_ms},
        {"dataKey": "r202", "createdAt": now_ms, "answer": flat_answers.get("r202") or "", "updatedAt": now_ms},
        {"dataKey": "hasilPemadananNIK", "createdAt": now_ms, "answer": hasil_nik_html, "updatedAt": now_ms},
        {"dataKey": "hasilPemadananNIK2", "createdAt": now_ms, "answer": "2", "updatedAt": now_ms},
        {"dataKey": "no_kk", "createdAt": now_ms, "answer": "", "updatedAt": now_ms},
        {"dataKey": "result_callnik", "createdAt": now_ms, "answer": result_callnik_str, "updatedAt": now_ms},
        {"dataKey": "r203", "createdAt": now_ms, "answer": flat_answers.get("r203") or "", "updatedAt": now_ms},
        {"dataKey": "r204", "createdAt": now_ms, "answer": r204_answer, "updatedAt": now_ms},
        {"dataKey": "nama_ktp", "createdAt": now_ms, "answer": "", "updatedAt": now_ms},
        {"dataKey": "r301a", "createdAt": now_ms, "answer": [{"label": flat_answers.get("r301a") or f"[{l1_code}] {l1_name}", "value": l1_code}], "updatedAt": now_ms},
        {"dataKey": "r301b", "createdAt": now_ms, "answer": [{"label": flat_answers.get("r301b") or f"[{l2_code}] {l2_name}", "value": l2_fullcode}], "updatedAt": now_ms},
        {"dataKey": "r301c", "createdAt": now_ms, "answer": [{"label": flat_answers.get("r301c") or f"[{l3_code}] {l3_name}", "value": l3_fullcode}], "updatedAt": now_ms},
        {"dataKey": "r301d", "createdAt": now_ms, "answer": [{"label": flat_answers.get("r301d") or f"[{l4_code}] {l4_name}", "value": l4_fullcode}], "updatedAt": now_ms},
        {"dataKey": "r301e", "createdAt": now_ms, "answer": flat_answers.get("r301e") or "", "updatedAt": now_ms},
        {"dataKey": "r302a", "createdAt": now_ms, "answer": 1, "updatedAt": now_ms},
        {"dataKey": "r302a_var", "createdAt": now_ms, "answer": "1", "updatedAt": now_ms},
        {"dataKey": "r302a_no#1", "createdAt": now_ms, "answer": 1, "updatedAt": now_ms},
        {"dataKey": "r302b_1#1", "createdAt": now_ms, "answer": flat_answers.get("r302b_1#1") or "", "updatedAt": now_ms},
        {"dataKey": "catatan", "createdAt": now_ms, "answer": flat_answers.get("catatan") or "", "updatedAt": now_ms},
        {"dataKey": "selesai", "createdAt": now_ms, "answer": end_time, "updatedAt": now_ms}
    ]
    
    return {
        "dataKey": "",
        "createdAt": created_at,
        "createdBy": user_name,
        "updatedBy": user_name,
        "answers": answers_list,
        "description": "",
        "isForceSubmit": False,
        "templateVersion": target.get("templateVersion") or "0.5.9",
        "validationVersion": "0.0.2",
        "updatedAt": created_at
    }

def stage_and_encrypt(answers: dict, key_bytes: bytes, target: dict, user_name: str) -> str:
    if "answers" in answers and isinstance(answers["answers"], list):
        wrapped = answers
    else:
        wrapped = wrap_answers(answers, target, user_name)
    plaintext = json.dumps(wrapped, ensure_ascii=False)
    encrypted = encrypt_gcm(plaintext, key_bytes)
    decrypted = decrypt_gcm_verify(encrypted, key_bytes)
    if decrypted != plaintext:
        print("[-] Encryption integrity check failed!")
        sys.exit(1)
    return encrypted

def upload_archive_flow(headers: dict, target: dict, archive_path: str, dry_run: bool) -> str:
    tid = target.get("id")
    pid = target.get("surveyPeriodId")
    is_edit = target.get("assignmentStatusAlias") != "OPEN"
    copy_from_id = target.get("copyFromId")
    presign_resp = request_presign_url(headers, tid, pid, [f"{tid}.7z"], is_edit, copy_from_id)
    data_obj = presign_resp.get("data", {})
    if isinstance(data_obj, list):
        urls = data_obj
    elif isinstance(data_obj, dict):
        urls = data_obj.get("presignedUrls", [])
    else:
        urls = []
    put_url = urls[0].get("presignedUrl") or urls[0].get("url") if urls else None
    if not put_url:
        if dry_run:
            put_url = "http://mock-s3-url"
        else:
            print(f"[-] Presigned PUT URL empty in response: {presign_resp}")
            sys.exit(1)
    if not dry_run:
        print("      Uploading archive to S3...")
        if not upload_to_s3(put_url, archive_path):
            print("[-] S3 archive upload failed.")
            sys.exit(1)
    return compute_md5(archive_path)

def get_submit_params(target: dict, data_slots: dict, archive_md5: str, lat: Optional[float], lon: Optional[float]) -> dict:
    region_id = (target.get("region") or {}).get("id") or ""
    # Ensure all data1-data10 keys are present and string-serialized
    for i in range(1, 11):
        key = f"data{i}"
        if key not in data_slots:
            data_slots[key] = ""
        else:
            data_slots[key] = str(data_slots[key]) if data_slots[key] is not None else ""

    return {
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

def confirm_submission_flow(headers: dict, target: dict, answers: dict, template_mapping: dict, archive_md5: str, lat: Optional[float], lon: Optional[float], dry_run: bool):
    is_edit = target.get("assignmentStatusAlias") != "OPEN"
    params = get_submit_params(target, map_answers_to_data_slots(answers, template_mapping), archive_md5, lat, lon)
    if not dry_run:
        print("      Confirming submission with server...")
        confirm_submit(headers, params, is_edit=is_edit)
        print(f"\n[+] ASSIGNMENT BERHASIL DI-SUBMIT!")
    else:
        print("\n============================================================")
        print("  ⚠️  Gunakan tanpa --dry-run untuk submit sebenarnya")

def cmd_submit(headers: dict, input_path: Optional[str], assignment_id: Optional[str],
               dry_run: bool, verbose: bool, direct_args: Optional[dict] = None,
               photo_path: Optional[str] = None, lat: Optional[float] = None, lon: Optional[float] = None):
    print("\n[1/7] Loading/Preparing answers...")
    surveys = fetch_surveys(headers)
    active_periode, template_mapping = resolve_survey_period_and_mapping(surveys, headers)
    pid = active_periode["id"]
    content = fetch_all_assignments(headers, pid)
    target = select_target_assignment(content, template_mapping, assignment_id, direct_args)
    answers = resolve_answers(input_path, target, direct_args, template_mapping, verbose)
    handle_photo_upload(headers, target, answers, photo_path, dry_run)
    lat, lon = handle_coords(answers, lat, lon, target)
    key_bytes = get_encryption_key(headers, target, (target.get("region") or {}).get("id", ""))
    user_name = get_user_name_from_headers(headers)
    encrypted = stage_and_encrypt(answers, key_bytes, target, user_name)
    with tempfile.TemporaryDirectory() as work_dir:
        archive_path = create_7z_archive(encrypted, target["id"], work_dir)
        archive_md5 = upload_archive_flow(headers, target, archive_path, dry_run)
        confirm_submission_flow(headers, target, answers, template_mapping, archive_md5, lat, lon, dry_run)

def verify_auth_with_sso(token_data: dict, headers: dict):
    try:
        payload_b64 = token_data["access_token"].split(".")[1]
        payload_b64 += "=" * (4 - len(payload_b64) % 4)
        jwt_payload = json.loads(base64.urlsafe_b64decode(payload_b64))
        iss = jwt_payload.get("iss", "")
        realm_name = iss.split("/")[-1] if iss else "eksternal"
        r = requests.get(
            f"https://sso.bps.go.id/auth/realms/{realm_name}/protocol/openid-connect/userinfo",
            headers=headers, timeout=10
        )
        if r.status_code == 200:
            user = r.json()
            print(f"  👤 Login as: {user.get('name')} ({user.get('email')})")
            return
    except Exception as e:
        print(f"[-] Kesalahan saat memverifikasi autentikasi: {e}")
    sys.exit(1)

def setup_token_and_headers(args) -> tuple:
    if args.email:
        password = args.password
        if not password:
            import getpass
            password = getpass.getpass("  Masukkan Password BPS: ")
        from fasih_auth import perform_login, TOKEN_FILE
        print(f"[*] Menghubungi SSO BPS untuk melakukan autentikasi: {args.email}...")
        token_data = perform_login(args.email, password)
        with open(TOKEN_FILE, "w") as f:
            json.dump(token_data, f, indent=2)
        print("[+] Login berhasil! Token disimpan ke:", TOKEN_FILE)
    else:
        token_data = load_token()
    token_data = refresh_token_if_needed(token_data)
    headers = get_headers(token_data)
    verify_auth_with_sso(token_data, headers)
    return headers

def add_custom_args(parser):
    parser.add_argument("--idpel", help="ID Pelanggan PLN")
    parser.add_argument("--nometer", help="Nomor Meter PLN")
    parser.add_argument("--nama", help="Nama Pelanggan")
    parser.add_argument("--alamat", help="Alamat Pelanggan")
    parser.add_argument("--tarif", help="Tarif PLN")
    parser.add_argument("--daya", help="Daya PLN")
    parser.add_argument("--hasil", help="Hasil pendataan")
    parser.add_argument("--kelurahan", help="Kode Kelurahan/Desa")
    parser.add_argument("--kdpm", help="Kode pembaca meter")
    parser.add_argument("--kddk", help="Kode kedudukan")
    parser.add_argument("--status-dil", help="Status DIL")
    parser.add_argument("--photo", help="Path ke file foto")
    parser.add_argument("--lat", type=float, help="Latitude")
    parser.add_argument("--lon", type=float, help="Longitude")

def parse_arguments():
    parser = argparse.ArgumentParser(description="Fasih BPS Auto-Fill")
    parser.add_argument("--list", action="store_true", help="Tampilkan list")
    parser.add_argument("-i", "--input", help="File input")
    parser.add_argument("--assignment-id", help="Assignment ID")
    parser.add_argument("--dry-run", action="store_true", help="Dry run")
    parser.add_argument("-v", "--verbose", action="store_true", help="Verbose")
    parser.add_argument("--email", help="Email BPS")
    parser.add_argument("--password", help="Password BPS")
    add_custom_args(parser)
    return parser, parser.parse_args()

def execute_args(args, headers: dict, parser):
    if args.list:
        cmd_list(headers)
    elif args.input:
        if not os.path.exists(args.input):
            print(f"[-] Input file not found: {args.input}")
            sys.exit(1)
        cmd_submit(headers, args.input, args.assignment_id, args.dry_run, args.verbose,
                   photo_path=args.photo, lat=args.lat, lon=args.lon)
    elif args.idpel or args.nometer:
        idpel = args.idpel or ""
        nometer = args.nometer or ""
        
        direct = {
            "idpel": idpel,
            "nometer": nometer,
            "nama": args.nama,
            "alamat": args.alamat,
            "tarif": args.tarif,
            "daya": args.daya,
            "hasil": args.hasil,
            "kelurahan": args.kelurahan,
            "kdpm": args.kdpm,
            "kddk": args.kddk,
            "status_dil": args.status_dil
        }
        lat = args.lat
        lon = args.lon
        
        # Check if we should fetch missing details from PLN AP2T
        missing_details = not direct["nama"] or not direct["alamat"] or not direct["tarif"] or not direct["daya"] or lat is None or lon is None or not direct["idpel"] or not direct["nometer"] or not direct.get("keperluan")
        if missing_details:
            print("[*] InfoPelanggan details missing or incomplete. Querying AP2T database...")
            try:
                from pln_lookup import PLNLookupTool
                engine = PLNLookupTool()
                res = None
                if idpel:
                    res = engine.lookup_by_idpel(idpel)
                if not res and nometer:
                    res = engine.lookup_by_nometer(nometer)
                
                if res:
                    profiles = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
                    if profiles:
                        p = profiles[0]
                        pln_name_cleaned = clean_pln_name(str(p.get("nama") or "").strip())
                        direct["pln_nama"] = pln_name_cleaned
                        if not direct["nama"]:
                            direct["nama"] = pln_name_cleaned
                            print(f"    -> Auto-filled Nama: {direct['nama']}")
                        
                        constructed_addr = construct_pln_alamat(p)
                        if constructed_addr:
                            direct["alamat"] = constructed_addr
                            direct["pln_alamat"] = constructed_addr
                            print(f"    -> Auto-filled Alamat: {direct['alamat']}")
                        elif not direct["alamat"] and p.get("alamat"):
                            direct["alamat"] = str(p.get("alamat")).strip()
                            print(f"    -> Auto-filled Alamat: {direct['alamat']}")
                            
                        if not direct["tarif"] and (p.get("tarif") or p.get("gol_tarif")):
                            direct["tarif"] = str(p.get("tarif", p.get("gol_tarif", ""))).strip()
                            print(f"    -> Auto-filled Tarif: {direct['tarif']}")
                        if not direct["daya"] and p.get("daya"):
                            direct["daya"] = str(p.get("daya")).strip()
                            print(f"    -> Auto-filled Daya: {direct['daya']}")
                        
                        # Populate coordinates
                        lat_val = p.get("koordinat_y", p.get("latitude"))
                        lon_val = p.get("koordinat_x", p.get("longitude"))
                        if lat is None and lat_val:
                            try:
                                lat = float(lat_val)
                                print(f"    -> Auto-filled Latitude: {lat}")
                            except ValueError:
                                pass
                        if lon is None and lon_val:
                            try:
                                lon = float(lon_val)
                                print(f"    -> Auto-filled Longitude: {lon}")
                            except ValueError:
                                pass
                        
                        # Auto-fill missing ID or meter number
                        if not direct["idpel"] and p.get("id_pelanggan"):
                            direct["idpel"] = str(p.get("id_pelanggan")).strip()
                            print(f"    -> Auto-filled IDPel: {direct['idpel']}")
                        if not direct["nometer"] and (p.get("no_meter") or p.get("nomor_meter") or p.get("nometer")):
                            direct["nometer"] = str(p.get("no_meter", p.get("nomor_meter", p.get("nometer", "")))).strip()
                            print(f"    -> Auto-filled NoMeter: {direct['nometer']}")

                        # Populate region fields and necessities
                        direct["pln_nik"] = str(p.get("noidentitas") or p.get("no_identitas") or "").strip()
                        direct["nik"] = direct["pln_nik"]
                        direct["pln_kd_prov"] = str(p.get("kd_prov") or "").strip()
                        direct["pln_kd_kab"] = str(p.get("kd_kab") or "").strip()
                        direct["pln_kd_kec"] = str(p.get("kd_kec") or "").strip()
                        direct["pln_kd_kel"] = str(p.get("kd_kel") or "").strip()
                        direct["pln_nama_prov"] = str(p.get("nama_prov") or "").strip()
                        direct["pln_nama_kab"] = str(p.get("nama_kab") or "").strip()
                        direct["pln_nama_kec"] = str(p.get("nama_kec") or "").strip()
                        direct["pln_nama_kel"] = str(p.get("nama_kel") or "").strip()
                        direct["keperluan"] = str(p.get("keperluan") or "").strip()
                else:
                    print("    [!] Warning: No data found in PLN database for this customer.")
            except Exception as e:
                print(f"    [!] Error performing auto-fill query: {e}")
                
        cmd_submit(headers, None, args.assignment_id, args.dry_run, args.verbose, direct_args=direct,
                   photo_path=args.photo, lat=lat, lon=lon)
    else:
        parser.print_help()

def main():
    parser, args = parse_arguments()
    print("=" * 60)
    print("  Fasih BPS Auto-Fill Tool")
    print(f"  {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}")
    print("=" * 60)
    try:
        headers = setup_token_and_headers(args)
        execute_args(args, headers, parser)
    except (FileNotFoundError, RuntimeError) as e:
        print(f"[-] {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()
