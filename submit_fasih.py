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
    
    for k in ("latitude", "longitude", "mediaJson", "remark", "basePath"):
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
            pd_idpel = None
            for item in predata:
                key = item.get("dataKey")
                if key == "r101a":
                    pd_idpel = item.get("answer")
                if key:
                    answers[key] = item.get("answer") or ""
            
            # Check IDPel mismatch
            idpel = target.get("data1")
            if pd_idpel and idpel and str(pd_idpel).strip() != str(idpel).strip():
                # Stale/mismatched preDefinedData! Ignore it.
                return {}
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
    # Replace punctuation that separates words (dots, hyphens) with space first
    cleaned = re.sub(r'[.\-/,;:]+', ' ', cleaned)
    # BPS only allows: A-Z, a-z, space
    cleaned = re.sub(r"[^a-zA-Z\s]", '', cleaned)
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

_kec_lookup_cache = None
_desa_lookup_cache = None

def _norm_region(name_raw):
    """Normalize a region name for matching: drop a leading '[code] ' prefix and all
    non-alphanumerics/spaces (PLN vs BPS differ, e.g. 'NEHES LIAH BING' vs 'NEHESLIAH BING')."""
    import re
    n = str(name_raw)
    n = n.split("]", 1)[1] if n.startswith("[") and "]" in n else n
    return re.sub(r"[^A-Z0-9]", "", n.upper())

def load_regional_lookups():
    global _kec_lookup_cache, _desa_lookup_cache
    if _kec_lookup_cache is not None and _desa_lookup_cache is not None:
        return _kec_lookup_cache, _desa_lookup_cache
        
    import json
    script_dir = os.path.dirname(os.path.abspath(__file__))
    # PRIMARY: FASIH app's own region lookup (complete: 7288 kec, 84156 desa; exact
    # codes the r301 cascade validates against). Falls back to the older MFD batch
    # file if the app files aren't present.
    kec_path = os.path.join(script_dir, "lookups", "fasih_kec.json")
    desa_path = os.path.join(script_dir, "lookups", "fasih_desa.json")
    if not (os.path.exists(kec_path) and os.path.exists(desa_path)):
        kec_path = os.path.join(script_dir, "lookups", "mfd25s1_kec_1_batch.json")
        desa_path = os.path.join(script_dir, "lookups", "mfd25s1_desa_1_batch.json")

    def _rows(path):
        """Yield [code, name, ...] rows from either the plain-list app file or the
        older ast-chunk MFD file."""
        data = json.load(open(path, encoding="utf-8")).get("data", [])
        for item in data:
            if isinstance(item, (list, tuple)):
                yield list(item)
            else:  # MFD chunk = comma-joined tuples
                import ast
                for row in ast.literal_eval(f"[{item}]"):
                    yield list(row)

    _clean = _norm_region  # key by normalized name (no spaces/punct) — PLN & BPS spell
                           # village names differently ("NEHES LIAH BING" vs "NEHESLIAH BING")

    kec_cache = {}
    desa_cache = {}
    if os.path.exists(kec_path):
        try:
            for row in _rows(kec_path):
                # app: [kdprovkabkec, namakec, kdprov, kdprovkab]; MFD: [code, name, ?, l1, l2]
                kec_cache[_clean(row[1])] = {
                    "code": row[0], "full_name": row[1],
                    "l1_code": row[2], "l2_code": row[3],
                }
        except Exception as e:
            print(f"[!] Error loading kec lookup: {e}")
    if os.path.exists(desa_path):
        try:
            for row in _rows(desa_path):
                kec_code, name_raw, full_code = row[0], row[1], row[2]
                name = _clean(name_raw)
                desa_cache[(kec_code, name)] = {"full_code": full_code, "full_name": name_raw}
                if name not in desa_cache:
                    desa_cache[name] = {"full_code": full_code, "full_name": name_raw, "kec_code": kec_code}
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

    # Parse preDefinedData region overrides if present
    pd_resolved = False
    pd_str = target.get("preDefinedData")
    if pd_str:
        try:
            import json
            pd_data = json.loads(pd_str) if isinstance(pd_str, str) else pd_str
            pd_map = {item["dataKey"]: item.get("answer") for item in pd_data.get("predata", []) if "dataKey" in item}
            
            # Check IDPel mismatch
            idpel = direct_args.get("idpel") or target.get("data1")
            pd_idpel = pd_map.get("r101a")
            if pd_idpel and idpel and str(pd_idpel).strip() != str(idpel).strip():
                raise ValueError("Mismatched preDefinedData")
            
            def parse_bracket_pd(val):
                if not val:
                    return "", ""
                import re
                m = re.match(r'^\[(.*?)\]\s*(.*)$', val)
                if m:
                    return m.group(1), m.group(2)
                return "", str(val)

            # Get names and codes from preDefinedData bracket strings
            pd_prov_code, pd_prov_name = "", ""
            pd_kab_code, pd_kab_name = "", ""
            pd_kec_code, pd_kec_name = "", ""
            pd_kel_code, pd_kel_name = "", ""
            if pd_map.get("r102a"):
                pd_prov_code, pd_prov_name = parse_bracket_pd(str(pd_map.get("r102a")))
            if pd_map.get("r102b"):
                pd_kab_code, pd_kab_name = parse_bracket_pd(str(pd_map.get("r102b")))
            if pd_map.get("r102c"):
                pd_kec_code, pd_kec_name = parse_bracket_pd(str(pd_map.get("r102c")))
            if pd_map.get("r102d"):
                pd_kel_code, pd_kel_name = parse_bracket_pd(str(pd_map.get("r102d")))

            kec_cache, desa_cache = load_regional_lookups()
            target_kec = _norm_region(pd_kec_name)
            target_kel = _norm_region(pd_kel_name)
            
            kec_info = kec_cache.get(target_kec)
            if kec_info:
                l1_code = kec_info["l1_code"]
                l1_name = pd_prov_name or "KALIMANTAN TIMUR"
                l2_code = kec_info["l2_code"][-2:]
                l2_name = pd_kab_name or "KAB. PENAJAM PASER UTARA"
                l2_fullcode = kec_info["l2_code"]
                l3_code = kec_info["code"][-3:]
                l3_name = pd_kec_name or "SEPAKU"
                l3_fullcode = kec_info["code"]
                
                desa_info = desa_cache.get((kec_info["code"], target_kel))
                if not desa_info:
                    desa_info = desa_cache.get(target_kel)
                if desa_info:
                    l4_code = desa_info["full_code"][-3:]
                    l4_name = pd_kel_name or "TELEMOW"
                    l4_fullcode = desa_info["full_code"]
                    pd_resolved = True
        except Exception:
            pass

    if pd_resolved:
        return {
            "l1_code": l1_code, "l1_name": l1_name,
            "l2_code": l2_code, "l2_name": l2_name, "l2_fullcode": l2_fullcode,
            "l3_code": l3_code, "l3_name": l3_name, "l3_fullcode": l3_fullcode,
            "l4_code": l4_code, "l4_name": l4_name, "l4_fullcode": l4_fullcode,
            "l2_fullcode_pln": pd_kab_code,
            "l3_fullcode_pln": pd_kec_code,
            "l4_fullcode_pln": pd_kel_code
        }

    # HIGHEST PRIORITY: use the exact PLN/AP2T region codes — they equal the BPS
    # codes returned by check-idpln (kd_prov=64, kd_kab=6408, kd_kec=640808,
    # kd_kel=6408082007). The r301/r102 cascade in the app only fills its
    # Kab/Kec/Desa dropdowns when the fullcodes match BPS exactly. Format is
    # prov2 + kab2 + kec2 + kel4 (kecamatan is 2 digits, e.g. kd_kec=640808 ->
    # kec code is kd_kel[0:6], NOT [0:7]). Fall back to slicing kd_kel when a
    # field is missing. Names come from PLN/AP2T (never hardcode Bontang).
    bps_kel = str(direct_args.get("pln_kd_kel") or "").strip()
    bps_kec = str(direct_args.get("pln_kd_kec") or "").strip()
    bps_kab = str(direct_args.get("pln_kd_kab") or "").strip()
    bps_prov = str(direct_args.get("pln_kd_prov") or "").strip()
    # PLN codes (kd_kel 2+2+2+4) as a FALLBACK ONLY. The app's r301 cascade validates
    # against the BPS MFD lookup (verified from the app's own region lookup dataset):
    # Kutai Timur = kab 6404 (not PLN 6408), Muara Ancalong = kec 6404010 (3-digit),
    # Kelinjau Ulu = desa 6404010003. So raw PLN kd_kel (6408012003) is NOT in the app
    # master → r301 blanks. Set PLN codes here but DON'T return — the name-based MFD
    # lookup below overrides with the app codes when the kec/desa name is found (names
    # still come from PLN so the label stays e.g. "KAB. KUTAI TIMUR").
    if len(bps_kel) == 10 and bps_kel.isdigit():
        l1fc = bps_prov if (len(bps_prov) == 2 and bps_prov.isdigit()) else bps_kel[0:2]
        l2fc = bps_kab if (len(bps_kab) == 4 and bps_kab.isdigit()) else bps_kel[0:4]
        l3fc = bps_kec if (len(bps_kec) == 6 and bps_kec.isdigit()) else bps_kel[0:6]
        l1_code = l1fc
        l1_name = str(direct_args.get("pln_nama_prov") or l1_name).strip().upper()
        l2_code, l2_fullcode = l2fc[-2:], l2fc
        l2_name = str(direct_args.get("pln_nama_kab") or l2_name).strip().upper()
        l3_code, l3_fullcode = l3fc[-2:], l3fc
        l3_name = str(direct_args.get("pln_nama_kec") or l3_name).strip().upper()
        l4_code, l4_fullcode = bps_kel[6:10], bps_kel
        l4_name = str(direct_args.get("pln_nama_kel") or l4_name).strip().upper()

    pln_nama_kec = str(direct_args.get("pln_nama_kec") or "").strip().upper()
    pln_nama_kel = str(direct_args.get("pln_nama_kel") or "").strip().upper()

    lookup_success = False
    if pln_nama_kec and pln_nama_kel:
        try:
            kec_cache, desa_cache = load_regional_lookups()
            target_kec = _norm_region(pln_nama_kec)
            target_kel = _norm_region(pln_nama_kel)
            
            kec_info = kec_cache.get(target_kec)
            if kec_info:
                l1_code = kec_info["l1_code"]
                l1_name = str(direct_args.get("pln_nama_prov") or "KALIMANTAN TIMUR").strip().upper()
                l2_code = kec_info["l2_code"][-2:]
                # Never hardcode Bontang — use the customer's actual kabupaten
                l2_name = str(direct_args.get("pln_nama_kab") or l2_name).strip().upper()
                l2_fullcode = kec_info["l2_code"]
                l3_code = kec_info["code"][-3:]
                # clean PLN name (kec_info["full_name"] has a "[010] " prefix that would
                # double up when the answer label re-adds "[code] ")
                l3_name = str(direct_args.get("pln_nama_kec") or l3_name).strip().upper()
                l3_fullcode = kec_info["code"]

                # Look up village using kec_code and kel_name
                desa_info = desa_cache.get((kec_info["code"], target_kel))
                if not desa_info:
                    desa_info = desa_cache.get(target_kel)

                if desa_info:
                    l4_code = desa_info["full_code"][-3:]
                    l4_name = str(direct_args.get("pln_nama_kel") or l4_name).strip().upper()
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

    pln_kd_kel = str(direct_args.get("pln_kd_kel") or "").strip()
    pln_kd_kec = str(direct_args.get("pln_kd_kec") or "").strip()
    pln_kd_kab = str(direct_args.get("pln_kd_kab") or "").strip()
    pln_kel_val = pln_kd_kel
    pln_kec_val = pln_kd_kec or (pln_kd_kel[:6] if len(pln_kd_kel) >= 6 else "")
    pln_kab_val = pln_kd_kab or (pln_kd_kel[:4] if len(pln_kd_kel) >= 4 else "")

    return {
        "l1_code": l1_code, "l1_name": l1_name,
        "l2_code": l2_code, "l2_name": l2_name, "l2_fullcode": l2_fullcode,
        "l3_code": l3_code, "l3_name": l3_name, "l3_fullcode": l3_fullcode,
        "l4_code": l4_code, "l4_name": l4_name, "l4_fullcode": l4_fullcode,
        "l2_fullcode_pln": pln_kab_val or l2_fullcode,
        "l3_fullcode_pln": pln_kec_val or l3_fullcode,
        "l4_fullcode_pln": pln_kel_val or l4_fullcode
    }

def get_region_fields(target: dict, direct_args: dict) -> dict:
    res_reg = resolve_region_codes_and_names(target, direct_args)
    r102a_code = res_reg["l1_code"]
    r102a_name = res_reg["l1_name"]
    r102b_code = res_reg.get("l2_fullcode_pln") or res_reg["l2_fullcode"]
    r102b_name = res_reg["l2_name"]
    r102c_code = res_reg.get("l3_fullcode_pln") or res_reg["l3_fullcode"]
    r102c_name = res_reg["l3_name"]
    r102d_code = res_reg.get("l4_fullcode_pln") or res_reg["l4_fullcode"]
    r102d_name = res_reg["l4_name"]
    
    r103_name = target.get("data2") or direct_args.get("nama") or ""
    if r103_name:
        r103_name = clean_pln_name(str(r103_name))
        
    return {
        "r102a": f"[{r102a_code}] {r102a_name}",
        "r102b": f"[{r102b_code}] {r102b_name}",
        "r102c": f"[{r102c_code}] {r102c_name}",
        "r102d": f"[{r102d_code}] {r102d_name}",
        "r102e": target.get("data4") or direct_args.get("alamat") or "",
        "r103": r103_name
    }

def build_dynamic_answers(target: dict, direct_args: dict, template_mapping: dict) -> dict:
    answers = parse_predefined(target)
    for slot, field_key in template_mapping.items():
        val = target.get(slot)
        if val and field_key not in answers:
            answers[field_key] = val

    # Unconditionally align form fields with active slots to maintain internal consistency
    idpel = direct_args.get("idpel") or target.get("data1")
    if idpel:
        answers["r101a"] = idpel
    nometer = direct_args.get("nometer") or target.get("data3")
    if nometer:
        answers["r101b"] = nometer
    nama = direct_args.get("nama") or target.get("data2")
    if nama:
        answers["r103"] = clean_pln_name(nama)
    alamat = direct_args.get("alamat") or target.get("data4")
    if alamat:
        answers["r102e"] = alamat.strip()
    
    # Update region-based fields unconditionally
    reg_fields = get_region_fields(target, direct_args)
    for k, v in reg_fields.items():
        answers[k] = v
    
    is_pasca = (
        (target and target.get("prelist_source") == "pascabayar") or
        "layanan" in template_mapping.values() or
        "kddk" in template_mapping.values()
    )
    
    # Baseline/PLN-specific metadata fields
    answers.update({
        "tarif": direct_args.get("tarif") or answers.get("tarif") or "R-1",
        "daya": direct_args.get("daya") or answers.get("daya") or "900",
        "kdpm": direct_args.get("kdpm") or answers.get("kdpm") or "01",
        "layanan": "PASCABAYAR" if is_pasca else "PRABAYAR",
        "r104": direct_args.get("hasil") or "1. Berhasil didata",
        "status_dil": direct_args.get("status_dil") or answers.get("status_dil") or "1",
        "_is_pasca": is_pasca
    })
    
    if is_pasca:
        kddk_val = answers.get("kddk") or target.get("data6") or direct_args.get("kddk") or "1"
        kode_rbm_val = answers.get("kode_rbm") or kddk_val[:7] or "1"
        answers["kddk"] = kddk_val
        answers["kode_rbm"] = kode_rbm_val
    
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
    if "idpln_response" in direct_args:
        answers["_idpln_response"] = direct_args["idpln_response"]
    
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
    filename = f"{tid}__r106__c.jpg"
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


def _clean_address_for_geocoding(addr: str) -> str:
    """Strip PLN address noise that confuses geocoders."""
    import re
    s = addr.upper()
    s = re.sub(r'\bNO\.?\s*0\b', '', s)
    s = re.sub(r'\bRT\.?\s*\d+', '', s)
    s = re.sub(r'\bRW\.?\s*\d+', '', s)
    s = re.sub(r'\b(JL\.?\s*){2,}', 'JL ', s)
    s = re.sub(r'\s+', ' ', s).strip()
    return s


COORD_CACHE_FILE = os.path.join(os.path.dirname(os.path.abspath(__file__)), ".fasih_coord_cache.json")


def _clean_admin(name):
    """Strip 'KAB./KABUPATEN/KOTA ' prefix + [code] noise so geocoders match cleanly."""
    import re
    s = re.sub(r"^\[.*?\]\s*", "", str(name or "").strip())
    s = re.sub(r"^(KAB\.?|KABUPATEN|KOTA)\s+", "", s, flags=re.IGNORECASE).strip()
    return s


def _geo_ladder(alamat, kel, kec, kab, prov):
    """Ordered geocode queries, most-specific first. Desa-present rungs come BEFORE
    kec-only rungs — dropping the desa is what made the pin jump ~45km to the kec town."""
    kelc, kecc, kabc, provc = (_clean_admin(x) for x in (kel, kec, kab, prov))
    street = expand_indonesian_address_abbreviations(_clean_address_for_geocoding(alamat))
    ladder = []
    if street and kelc:
        ladder.append([street, kelc, kecc, kabc, provc])   # street + full admin
    if kelc and kecc:
        ladder.append([kelc, kecc, kabc, provc])           # desa centroid (deterministic)
    if kecc:
        ladder.append([kecc, kabc, provc])                 # kec town
    if kabc:
        ladder.append([kabc, provc])                       # kabupaten
    # dedup, keep order, drop empty parts
    seen, out = set(), []
    for parts in ladder:
        q = ", ".join(p for p in parts if p) + ", Indonesia"
        if q not in seen:
            seen.add(q); out.append(q)
    return out


def geocode_address(alamat, kel="", kec="", kab="", prov=""):
    """Geocode via admin hierarchy (Mapbox → Nominatim → Google Maps fallback).
    No jitter here — determinism is handled by the per-idpel cache in resolve_coordinate()."""
    import requests as _req
    import urllib.parse
    queries = _geo_ladder(alamat, kel, kec, kab, prov)
    if not queries:
        return None, None

    mapbox_raw = os.getenv("MAPBOX_ACCESS_TOKEN") or ""
    mapbox_tokens = [t.strip() for t in mapbox_raw.split(",") if t.strip()]
    for mapbox_token in mapbox_tokens:
        token_valid = True
        for q in queries:
            try:
                url = f"https://api.mapbox.com/geocoding/v5/mapbox.places/{urllib.parse.quote(q)}.json"
                r = _req.get(url, params={"access_token": mapbox_token, "limit": 1, "country": "id"}, timeout=6)
                if r.status_code in (401, 402, 429):
                    token_valid = False
                    break
                feats = (r.json() or {}).get("features") or []
                if feats:
                    c = feats[0]["geometry"]["coordinates"]
                    return float(c[1]), float(c[0])
            except Exception:
                pass
        if not token_valid:
            continue

    # Google Maps Geocoding API — higher accuracy than Nominatim. Comma-separated
    # keys rotate on quota/denied (OVER_QUERY_LIMIT / REQUEST_DENIED), like Mapbox.
    gmaps_keys = [k.strip() for k in (os.getenv("GOOGLE_MAPS_KEY") or "").split(",") if k.strip()]
    for gmaps_key in gmaps_keys:
        key_dead = False
        for q in queries:
            try:
                r = _req.get(
                    "https://maps.googleapis.com/maps/api/geocode/json",
                    params={"address": q, "key": gmaps_key, "region": "id"},
                    timeout=6
                )
                data = r.json()
                status = data.get("status")
                if status in ("OVER_QUERY_LIMIT", "REQUEST_DENIED"):
                    key_dead = True
                    break
                if status == "OK" and data.get("results"):
                    loc = data["results"][0]["geometry"]["location"]
                    return float(loc["lat"]), float(loc["lng"])
            except Exception:
                pass
        if key_dead:
            continue

    for q in queries:
        try:
            r = _req.get(
                "https://nominatim.openstreetmap.org/search",
                params={"q": q, "format": "json", "limit": 1},
                headers={"User-Agent": "FasihBPSBot/1.0"}, timeout=6
            )
            res = r.json()
            if res:
                return float(res[0]["lat"]), float(res[0]["lon"])
        except Exception:
            pass

    return None, None


def _load_coord_cache():
    try:
        with open(COORD_CACHE_FILE) as f:
            return json.load(f)
    except Exception:
        return {}


def _save_coord_cache(cache):
    try:
        with open(COORD_CACHE_FILE, "w") as f:
            json.dump(cache, f)
    except Exception:
        pass


def _idpel_offset(idpel, span=0.0003):
    """Deterministic ±span offset seeded by idpel (~±33m). Same idpel → same nudge,
    so houses in one desa aren't pixel-identical yet the pin never wanders between runs."""
    h = int(hashlib.md5(str(idpel).encode()).hexdigest(), 16)
    dlat = ((h & 0xFFFF) / 0xFFFF * 2 - 1) * span
    dlon = (((h >> 16) & 0xFFFF) / 0xFFFF * 2 - 1) * span
    return dlat, dlon


def resolve_coordinate(idpel, alamat, kel, kec, kab, prov, pln_lat=None, pln_lon=None):
    """Deterministic coordinate for an idpel. Priority: valid PLN coord → cache →
    Mapbox admin geocode (frozen in cache) → province fallback. Once resolved it is
    cached per idpel, so re-register/re-submit produces the exact same pin — no 'lari'."""
    try:
        if pln_lat and pln_lon and float(pln_lat) != 0.0 and float(pln_lon) != 0.0:
            return float(pln_lat), float(pln_lon)
    except (ValueError, TypeError):
        pass

    cache = _load_coord_cache() if idpel else {}
    if idpel and idpel in cache:
        c = cache[idpel]
        return c[0], c[1]

    lat, lon = geocode_address(alamat, kel, kec, kab, prov)
    if lat is not None and lon is not None and idpel:
        dlat, dlon = _idpel_offset(idpel)
        lat, lon = lat + dlat, lon + dlon
    if lat is None or lon is None:
        lat, lon = get_fallback_coordinate(prov, kab, kec, alamat)

    if idpel and lat is not None and lon is not None:
        cache[idpel] = [lat, lon]
        _save_coord_cache(cache)
    return lat, lon


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
    # ponytail: ±0.01° ≈ ±1.1km — a last-resort centroid nudge, NOT the old ±0.06°
    # (~6.6km) that flung rural pins far outside their kabupaten.
    lat += random.uniform(-0.01, 0.01)
    lon += random.uniform(-0.01, 0.01)
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
    if regions:
        for r in regions:
            r_id = r.get("region_id") or r.get("id") or (r.get("region") or {}).get("id")
            if r_id and r_id == region_id:
                wrapped_key = r.get("wrappedDatakey")
                break
        if not wrapped_key and len(regions) > 0:
            wrapped_key = regions[0].get("wrappedDatakey")

    if wrapped_key:
        try:
            kb = base64.b64decode(wrapped_key.encode("utf-8"))
            if len(kb) in (16, 24, 32):
                return kb
        except Exception:
            pass

    return hashlib.sha256(STATIC_LEGACY_KEY.encode("utf-8")).digest()


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

def generate_random_device_info(target: dict = None, user_name: str = None) -> dict:
    import random as _rnd
    import time
    
    # List of realistic Android devices popular in Indonesia
    devices = [
        ("Xiaomi", "Xiaomi", "M2103K19PG", "13 - 33", "TP1A.220624.014"),  # POCO M3 Pro 5G
        ("POCO", "Xiaomi", "2201116PG", "13 - 33", "TKQ1.221114.001"),    # POCO M4 Pro
        ("Samsung", "samsung", "SM-A536B", "14 - 34", "UP1A.231005.007"),  # Galaxy A53 5G
        ("Samsung", "samsung", "SM-G991B", "13 - 33", "TP1A.220624.014"),  # Galaxy S21 5G
        ("Oppo", "OPPO", "CPH2201", "12 - 31", "SP1A.210812.016"),         # Oppo Reno5 5G
        ("Oppo", "OPPO", "CPH2529", "13 - 33", "TP1A.220624.014"),         # Oppo A78
        ("Vivo", "vivo", "V2105", "13 - 33", "TP1A.220624.014"),            # Vivo Y53s
        ("Vivo", "vivo", "V2204", "14 - 34", "UKQ1.230917.001"),            # Vivo Y36
        ("Realme", "realme", "RMX3363", "13 - 33", "TP1A.220624.014"),      # Realme GT Master Edition
        ("Realme", "realme", "RMX3771", "14 - 34", "UKQ1.230917.001"),      # Realme 11 Pro 5G
        ("Infinix", "Infinix", "X6711", "13 - 33", "TP1A.220624.014"),      # Infinix Note 30 Pro
        ("Infinix", "Infinix", "X6831", "13 - 33", "TP1A.220624.014")       # Infinix Hot 30
    ]
    
    brand, manufacture, model, android_version, build_id = _rnd.choice(devices)
    
    # Realistic RAM sizes (MB)
    ram_options = [
        (4096, 1500, 2596),
        (6144, 2200, 3944),
        (8192, 3500, 4692),
        (12288, 5500, 6788)
    ]
    total_ram, avail_ram, used_ram = _rnd.choice(ram_options)
    avail_ram += _rnd.randint(-150, 150)
    used_ram = total_ram - avail_ram
    
    # Realistic Storage sizes (MB)
    storage_options = [
        (65536, 12000, 53536),
        (131072, 45000, 86072),
        (262144, 110000, 152144)
    ]
    total_storage, avail_storage, used_storage = _rnd.choice(storage_options)
    avail_storage += _rnd.randint(-2000, 2000)
    used_storage = total_storage - avail_storage
    
    providers = ["TELKOMSEL", "Indosat Ooredoo", "XL Axiata", "Tri", "Smartfren"]
    provider = _rnd.choice(providers)
    
    user_id = ""
    uname = ""
    if isinstance(target, dict):
        user_id = target.get("currentUserId") or ""
        uname = target.get("currentUserFullname") or target.get("currentUserUsername") or ""
    if user_name and not uname:
        uname = user_name
        
    now_ms = int(time.time() * 1000)
    open_ts = str(now_ms - 300000)
    save_ts = str(now_ms - 5000)
    
    return {
        "actionLogEntities": [
            {
                "action": "OPEN",
                "batteryInfo": {"batteryLevel": _rnd.randint(30, 95), "batteryTemperature": _rnd.randint(28, 42)},
                "timestamp": open_ts,
                "userId": user_id,
                "userName": uname
            },
            {
                "action": "SAVE",
                "batteryInfo": {"batteryLevel": _rnd.randint(30, 95), "batteryTemperature": _rnd.randint(28, 42)},
                "timestamp": save_ts,
                "userId": user_id,
                "userName": uname
            }
        ],
        "deviceInfo": {
            "androidVersion": android_version,
            "brand": brand,
            "host": "pangu-build-component-system-321494-v5rcz-320dz-rrnhj",
            "id": build_id,
            "isEmulator": False,
            "isRootDevice": False,
            "manufacture": manufacture,
            "model": model,
            "serial": "unknown",
            "type": "user",
            "user": "builder",
            "version": 0,
            "versionRelease": "2.16.7 - 140"
        },
        "memoryInfo": {
            "memoryAvail": str(avail_ram),
            "memoryTotal": str(total_ram),
            "memoryUsage": str(used_ram)
        },
        "storageInfo": {
            "storageAvail": str(avail_storage),
            "storageTotal": str(total_storage),
            "storageUsage": str(used_storage)
        },
        "signalInfo": {
            "detailSignalStrength": "",
            "provider": provider,
            "type": "1"
        },
        "encryptionType": 2,
        "formgear_version": "",
        "data": "",
        "totalDuration": 0
    }

def build_paradata(lat, lon, user_id: str, user_name: str, duration_s: int = None, start_time_str: str = None, end_time_str: str = None) -> str:
    """Build a realistic paradata action-log like the FASIH app sends. The app's
    submit carries this (interview OPEN/CLOSE/SUBMIT with GPS/battery + device
    telemetry); records submitted with an EMPTY paradata are stored but do not
    register into the FASIH frame (check-idpln fasih_exists stays false). Mirrors
    the HAR structure with plausible values."""
    import random as _rnd
    import time
    from datetime import datetime
    now_ms = int(time.time() * 1000)
    dur = duration_s or _rnd.randint(120, 360)
    try:
        latf, lonf = float(lat), float(lon)
    except (TypeError, ValueError):
        latf, lonf = 0.0, 0.0

    def _entry(action, ts):
        return {
            "action": action,
            "batteryInfo": {"batteryLevel": _rnd.randint(45, 95),
                            "batteryTemperature": round(_rnd.uniform(30.0, 41.5), 1)},
            "timestamp": str(ts), "userId": user_id or "", "userName": user_name or "Petugas",
        }

    use_mapped = False
    if start_time_str and end_time_str:
        try:
            def parse_iso(ts_str):
                cleaned = ts_str.replace("Z", "+00:00")
                dt = datetime.fromisoformat(cleaned)
                return int(dt.timestamp() * 1000)
            open_ts = parse_iso(start_time_str)
            submit_ts = parse_iso(end_time_str)
            dur = max(1, int((submit_ts - open_ts) / 1000))
            close_ts = max(open_ts, submit_ts - 15000)
            acts = [_entry("OPEN", open_ts), _entry("CLOSE", close_ts), _entry("SUBMIT", submit_ts)]
            use_mapped = True
        except Exception:
            pass

    if not use_mapped:
        open_ts = now_ms - dur * 1000
        acts = [_entry("OPEN", open_ts), _entry("CLOSE", now_ms - 15000), _entry("SUBMIT", now_ms)]
    
    dev_info = generate_random_device_info()
    return json.dumps({
        "actionLogEntities": acts, "data": "",
        "deviceInfo": dev_info["deviceInfo"],
        "encryptionType": 2, "formgear_version": "",
        "memoryInfo": dev_info["memoryInfo"],
        "signalInfo": dev_info["signalInfo"],
        "storageInfo": dev_info["storageInfo"],
        "totalDuration": dur,
    }, ensure_ascii=False)


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
    now = datetime.utcnow()
    
    if flat_answers.get("createdAt"):
        created_at = flat_answers["createdAt"]
    elif isinstance(target, dict) and target.get("createdAt"):
        created_at = target["createdAt"]
    else:
        created_at = now.strftime("%Y-%m-%dT%H:%M:%S.%f")[:-3] + "Z"
        bp = target.get("basePath") if isinstance(target, dict) else None
        if bp:
            import re
            m = re.search(r'_(\d+)\.7z$', bp)
            if m:
                try:
                    ts_ms = int(m.group(1))
                    from datetime import timezone
                    dt = datetime.fromtimestamp(ts_ms / 1000.0, timezone.utc)
                    created_at = dt.strftime("%Y-%m-%dT%H:%M:%S.%f")[:-3] + "Z"
                except Exception:
                    pass

    import random as _rnd
    try:
        if created_at.endswith("Z"):
            created_dt = datetime.strptime(created_at, "%Y-%m-%dT%H:%M:%S.%fZ")
        else:
            created_dt = datetime.strptime(created_at, "%Y-%m-%dT%H:%M:%S.%f")
    except:
        created_dt = now
    
    # Determine the current date using local OS timezone and convert to UTC
    from datetime import timezone
    local_tz = datetime.now().astimezone().tzinfo
    local_now = datetime.now(local_tz)
    
    hour_start = _rnd.randint(7, 16)
    minute_start = _rnd.randint(0, 59)
    second_start = _rnd.randint(0, 59)
    ms_start = _rnd.randint(100, 999)
    
    local_start = local_now.replace(hour=hour_start, minute=minute_start, second=second_start, microsecond=ms_start*1000)
    # Durasi survei realistis 10–20 menit (app asli ~28 mnt; 2–6 mnt lama terlalu
    # cepat untuk survei rumah = tell tidak wajar). Tetap dalam jam kerja (clamp <18).
    duration_secs = _rnd.randint(600, 1200)
    local_end = local_start + timedelta(seconds=duration_secs)
    if local_end.hour >= 18:
        local_end = local_end.replace(hour=17, minute=_rnd.randint(45, 59))
        
    utc_start = local_start.astimezone(timezone.utc)
    utc_end = local_end.astimezone(timezone.utc)
    
    is_pasca = flat_answers.get("_is_pasca", False)
    tv = target.get("templateVersion") or "0.5.9" if target else "0.5.9"
    is_tambahan_real = str(flat_answers.get("is_assignment_tambahan") or target.get("is_assignment_tambahan") or "0").strip() == "1"

    use_legacy_format = (tv == "0.5.9")
    if use_legacy_format:
        start_time = local_start.strftime("%Y-%m-%dT%H:%M:%S")
        end_time = local_end.strftime("%Y-%m-%dT%H:%M:%S")
    else:
        start_time = utc_start.strftime("%Y-%m-%dT%H:%M:%S.%f")[:-3] + "Z"
        end_time = utc_end.strftime("%Y-%m-%dT%H:%M:%S.%f")[:-3] + "Z"
    
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
        
    # NIK pemadanan — reflect the real check-nikpln result. flat_answers["_nikpln"]
    # holds the connector's data dict (exists, nama, nomor_kartu_keluarga). Falls
    # back to the "not found" card when absent (e.g. the bot path not yet wired).
    nik = flat_answers.get("r202") or ""
    nikpln = flat_answers.get("_nikpln") or {}
    nik_exists = bool(nikpln.get("exists"))
    no_kk_val = str(nikpln.get("nomor_kartu_keluarga") or "")
    if nik_exists:
        nik_nama = nikpln.get("nama") or ""
        hasil_nik_html = f"""
            <div class="font-normal border-2 text-center"
            style="padding: 0.8em; color: rgb(21, 128, 61); border-color: rgb(21, 128, 61); font-size: 12.5px;">
            <b>NIK : [ {nik} ]<br>DITEMUKAN<br>{nik_nama}</b>
            </div>"""
    else:
        hasil_nik_html = f"""
            <div class="font-normal border-2 text-center"
            style="padding: 0.8em; color: rgb(122, 32, 64); border-color: rgb(122, 32, 64); font-size: 12.5px;">
            <b>NIK : [ {nik} ] <br>TIDAK DITEMUKAN</b>
            </div>"""
            
    # PLN lookup data
    idpln_resp = flat_answers.get("_idpln_response") or flat_answers.get("idpln_response")
    if idpln_resp and isinstance(idpln_resp, dict) and "exists" in idpln_resp:
        pln_data = {
            "data": idpln_resp,
            "success": True,
            "message": "Successfully hit an API.",
            "httpStatus": "OK"
        }
        result_idpln_val = json.dumps(json.dumps(pln_data, ensure_ascii=False, separators=(',', ':')), ensure_ascii=False, separators=(',', ':'))
    elif flat_answers.get("result_idpln"):
        result_idpln_val = flat_answers.get("result_idpln")
    else:
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
                "prelist_source": "pascabayar" if flat_answers.get("_is_pasca", False) else "prabayar",
                "success": True,
                "unitap": flat_answers.get("unitap") or "23BTG"
            },
            "success": True,
            "message": "Successfully hit an API.",
            "httpStatus": "OK"
        }
        result_idpln_val = json.dumps(json.dumps(pln_data, ensure_ascii=False, separators=(',', ':')), ensure_ascii=False, separators=(',', ':'))
    
    if nik_exists:
        result_callnik_str = json.dumps(json.dumps({
            "data": {"alamat": nikpln.get("alamat"), "exists": True,
                     "nama": nikpln.get("nama"), "nomor_kartu_keluarga": no_kk_val,
                     "success": True},
            "success": True, "message": "Successfully hit an API.", "httpStatus": "OK"
        }, ensure_ascii=False, separators=(',', ':')), ensure_ascii=False, separators=(',', ':'))
    else:
        result_callnik_str = json.dumps('{"data":{"alamat":null,"exists":false,"nama":null,"nomor_kartu_keluarga":null,"success":true},"success":true,"message":"Successfully hit an API.","httpStatus":"OK"}', ensure_ascii=False, separators=(',', ':'))

    # --- 0.6.7 NOMOR METER verification (form engine 0.2.7 added `result_nomor_meter`
    # + `hasilCheckNoMeter`). Mirrors the CEK-IDPel card but for the meter number.
    nometer_val = flat_answers.get("r101b") or ""
    hasil_nometer_html = (
        '\n            <div class="font-normal border-2 text-center note"'
        '\n            style="padding: 0.8em; color: rgb(21, 128, 61); border-color: rgb(21, 128, 61); font-size: 12px;">'
        '\n            <table style="border-collapse: collapse;">'
        '\n              <tr>'
        '\n                <td style="vertical-align: top; white-space: nowrap;">NOMOR METER</td>'
        f'\n                <td style="vertical-align: top;">: <b>[ <span style="white-space: nowrap;">{nometer_val}</span> ]</b></td>'
        '\n              </tr>'
        '\n              <tr>'
        '\n                <td style="vertical-align: top;">STATUS</td>'
        '\n                <td style="vertical-align: top;">: <b>DITEMUKAN DAN BELUM TERCATAT PADA SISTEM FASIH</b></td>'
        '\n              </tr>'
        '\n            </table>'
        '\n            </div>'
    )
    _rnm = {
        "data": {
            "alamat": flat_answers.get("r102e") or "",
            "exists": True, "fasih_exists": False,
            "id_pelanggan": flat_answers.get("r101a") or "",
            "kode_desa": l4_fullcode, "kode_kab": l2_fullcode,
            "kode_kec": l3_fullcode, "kode_prov": l1_code,
            "nama": flat_answers.get("r103") or "",
            "nama_desa": l4_name, "nama_kab": l2_name,
            "nama_kec": l3_name, "nama_prov": l1_name,
            "success": True,
            "unitap": flat_answers.get("unitap") or l2.get("code") or "23BTG",
        },
        "success": True, "message": "Successfully hit an API.", "httpStatus": "OK",
    }
    result_nomor_meter_val = json.dumps(json.dumps(_rnm, ensure_ascii=False, separators=(',', ':')), ensure_ascii=False, separators=(',', ':'))

    is_pasca = flat_answers.get("_is_pasca", False)
    is_tambahan = use_legacy_format


    # Parse r105 (coords)
    r105_val = flat_answers.get("r105")
    lat, lon = 0.0, 0.0
    if isinstance(r105_val, dict):
        coord = r105_val.get("coordinat") or {}
        lat = coord.get("latitude") or 0.0
        lon = coord.get("longitude") or 0.0
    elif isinstance(r105_val, list) and r105_val:
        for item in r105_val:
            if isinstance(item, dict) and "value" in item and isinstance(item["value"], dict):
                lat = item["value"].get("latitude") or 0.0
                lon = item["value"].get("longitude") or 0.0
                break
        
    if is_tambahan:
        r105_answer_list = {"coordinat": {"latitude": lat, "longitude": lon}, "remark": "", "accuracy": 10.0}
    else:
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
            
    if is_tambahan:
        if r106_answer_list:
            r106_answer_list = json.dumps(r106_answer_list[0])
        else:
            r106_answer_list = "{}"
            
    # Map raw value of r104
    r104_val = flat_answers.get("r104") or "1. Berhasil didata"
    r104_code = "1"
    if r104_val and "." in r104_val:
        r104_code = r104_val.split(".")[0].strip()
        
    if is_tambahan:
        r104_answer = r104_val
    else:
        r104_answer = [{"description": "", "label": r104_val, "value": r104_code, "open": False}]
    
    # Map raw value of r204
    r204_val = flat_answers.get("r204") or "1. Milik sendiri"
    r204_code = "1"
    if r204_val and "." in r204_val:
        r204_code = r204_val.split(".")[0].strip()
        
    if is_tambahan:
        r204_answer = r204_val
    else:
        r204_answer = [{"description": "", "label": r204_val, "value": r204_code, "open": False}]
    
    # Cast flagpre to appropriate type
    flagpre_val = flat_answers.get("flagpre")
    if flagpre_val is None:
        flagpre_val = target.get("data5") if target else ""
    if flagpre_val is None:
        flagpre_val = ""
    flagpre_val = str(flagpre_val).strip()
    if flagpre_val:
        tv = target.get("templateVersion") or "0.5.9"
        if tv == "0.5.9":
            try:
                flagpre_val = str(int(float(flagpre_val)))
            except:
                flagpre_val = "1"
        else:
            try:
                flagpre_val = float(flagpre_val) if is_pasca else int(flagpre_val)
            except:
                pass

    # Select the schema key list dynamically based on templateVersion and type
    if is_pasca:
        if tv == "0.6.7":
            # 0.6.7 Pascabayar schema (includes verification fields, different order)
            keys_list = [
                "mulai", "r101a", "result_idpln", "hasilCheckIdPel2", "hasilCheckIdPel",
                "r101b", "r102a", "r102b", "r102c", "r102d", "r102e", "r103",
                "r104", "r105", "r106",
                "r201", "r202", "nama_ktp", "hasilPemadananNIK", "hasilPemadananNIK2", "result_callnik",
                "r203", "r204", "no_kk",
                "r301a", "r301b", "r301c", "r301d", "r301e", "r302a", "r302a_var", "r302a_no#1", "r302b_1#1",
                "catatan", "selesai"
            ]
        else:
            # 0.5.9 Pascabayar schema (legacy)
            keys_list = [
                "flagpre", "mulai", "r101a", "r101b",
                "r102a", "r102b", "r102c", "r102d", "r102e", "r103",
                "r104", "r105", "r106", "unitupi", "unitap", "unitup",
                "kode_rbm", "kddk", "selesai",
                "UPI", "UP3", "ULP", "RBM", "daya", "tarif", "kdpm", "layanan", "status_dil",
                "r201", "r202", "r203", "r204",
                "r301a", "r301b", "r301c", "r301d", "r301e", "r302a", "r302a_var", "r302a_no#1", "r302b_1#1",
                "catatan"
            ]
    elif tv == "0.5.9":
        # 0.5.9 Prabayar schema: NO verification fields included!
        keys_list = [
            "mulai", "r101a", "result_idpln", "hasilCheckIdPel2", "hasilCheckIdPel",
            "r101b", "r102a", "r102b", "r102c", "r102d", "r102e", "r103",
            "r104", "r105", "r106", "unitupi", "unitap", "unitup",
            "r201", "r202", "r203", "r204",
            "r301a", "r301b", "r301c", "r301d", "r301e", "r302a", "r302a_var", "r302a_no#1", "r302b_1#1",
            "catatan", "selesai"
        ]
    else:
        # 0.6.7 Prabayar schema — EXACT match to form-engine-0.2.7 app payload
        # (verified vs decrypted app .7z 2026-07-31). 36 fields, precise order.
        # NOTE vs old: +result_nomor_meter +hasilCheckNoMeter, and NO hasilCheckIdPel
        # /unitupi/unitap/unitup/catatan (those broke rendering → "data corrupt").
        keys_list = [
            "mulai", "r101a", "result_idpln", "hasilCheckIdPel2",
            "r101b", "result_nomor_meter", "hasilCheckNoMeter2", "hasilCheckNoMeter",
            "r102a", "r102b", "r102c", "r102d", "r102e", "r103",
            "r104", "r105", "r106",
            "r201", "r202", "hasilPemadananNIK", "hasilPemadananNIK2", "no_kk", "result_callnik",
            "r203", "r204", "nama_ktp",
            "r301a", "r301b", "r301c", "r301d", "r301e", "r302a", "r302a_var", "r302a_no#1", "r302b_1#1",
            "selesai"
        ]

    # Pre-compute all answers mapped to their values
    computed_answers = {
        "flagpre": flagpre_val,
        "mulai": start_time,
        "r101a": flat_answers.get("r101a") or "",
        "result_idpln": result_idpln_val,
        "hasilCheckIdPel2": "2",
        "hasilCheckIdPel": hasil_check_html,
        "r101b": flat_answers.get("r101b") or "",
        "result_nomor_meter": result_nomor_meter_val,
        "hasilCheckNoMeter2": "2",
        "hasilCheckNoMeter": hasil_nometer_html,
        "r102a": flat_answers.get("r102a") or f"[{l1_code}] {l1_name}",
        "r102b": flat_answers.get("r102b") or f"[{l2_code}] {l2_name}",
        "r102c": flat_answers.get("r102c") or f"[{l3_code}] {l3_name}",
        "r102d": flat_answers.get("r102d") or f"[{l4_code}] {l4_name}",
        "r102e": flat_answers.get("r102e") or "",
        "r103": flat_answers.get("r103") or "",
        "r104": r104_answer,
        "r105": r105_answer_list,
        "r106": r106_answer_list,
        "unitupi": flat_answers.get("unitupi") or l1.get("code") or "",
        "unitap": flat_answers.get("unitap") or l2.get("code") or "23BTG",
        "unitup": flat_answers.get("unitup") or l3.get("code") or "",
        "kode_rbm": flat_answers.get("kode_rbm") or l4.get("code") or "",
        "kddk": flat_answers.get("kddk") or (target.get("data6") if target else "") or "",
        "is_assignment_tambahan": "1" if is_tambahan_real else "0",
        
        # 0.5.9 legacy fields
        "UPI": str(flat_answers.get("UPI") or flat_answers.get("unitupi") or "").strip(),
        "UP3": str(flat_answers.get("UP3") or flat_answers.get("unitap") or "").strip(),
        "ULP": str(flat_answers.get("ULP") or flat_answers.get("unitup") or "").strip(),
        "RBM": str(flat_answers.get("RBM") or flat_answers.get("kode_rbm") or "").strip(),
        "daya": str(int(float(flat_answers.get("daya")))) if flat_answers.get("daya") is not None and str(flat_answers.get("daya")).strip() != "" else "",
        "tarif": str(flat_answers.get("tarif") or "").strip(),
        "kdpm": "01" if not str(flat_answers.get("kdpm") or "").strip() or str(flat_answers.get("kdpm")).strip() == "M" else str(flat_answers.get("kdpm")).strip(),
        "layanan": str(flat_answers.get("layanan") or "").strip(),
        "status_dil": "1" if str(flat_answers.get("status_dil") or "").strip().upper() in ("AKTIF", "1") else "2",
        
        "r201": flat_answers.get("r201") or "",
        "r202": flat_answers.get("r202") or "",
        "nama_ktp": flat_answers.get("nama_ktp") or "",
        "hasilPemadananNIK": hasil_nik_html,
        "hasilPemadananNIK2": "1" if nik_exists else "2",
        "result_callnik": result_callnik_str,
        "r203": flat_answers.get("r203") or "",
        "r204": r204_answer,
        "no_kk": no_kk_val,
        "r301a": (flat_answers.get("r301a") or f"[{l1_code}] {l1_name}") if is_tambahan else [{"label": flat_answers.get("r301a") or f"[{l1_code}] {l1_name}", "value": l1_code}],
        "r301b": (flat_answers.get("r301b") or f"[{l2_code[-2:]}] {l2_name}") if is_tambahan else [{"label": flat_answers.get("r301b") or f"[{l2_code[-2:]}] {l2_name}", "value": l2_fullcode}],
        "r301c": (flat_answers.get("r301c") or f"[{l3_code[-3:]}] {l3_name}") if is_tambahan else [{"label": flat_answers.get("r301c") or f"[{l3_code[-3:]}] {l3_name}", "value": l3_fullcode}],
        "r301d": (flat_answers.get("r301d") or f"[{l4_code[-3:]}] {l4_name}") if is_tambahan else [{"label": flat_answers.get("r301d") or f"[{l4_code[-3:]}] {l4_name}", "value": l4_fullcode}],
        "r301e": flat_answers.get("r301e") or "",
        "r302a": 1,
        "r302a_var": "1",
        "r302a_no#1": 1,
        "r302b_1#1": flat_answers.get("r302b_1#1") or "",
        "catatan": flat_answers.get("catatan") or "",
        "selesai": end_time
    }

    # Generate the formatted answers list
    answers_list = []
    keys_with_timestamps = {
        "r104", "catatan"
    }
    # form-engine 0.2.7 (template 0.6.7) stamps updatedAt/createdAt on EVERY answer
    # item (epoch-ms int); older 0.5.9 only on selected blocks. In the real app each
    # item's timestamp = WHEN that field was filled during the interview — spread
    # across [mulai, selesai], increasing with field order — NOT the submit instant.
    # So distribute per-item ts over the interview window instead of a single now_ms
    # (all-same-ms would be an impossible-survey tell).
    all_items_ts = (tv == "0.6.7")
    if all_items_ts:
        try:
            _start_ms = int(utc_start.timestamp() * 1000)
            _end_ms = int(utc_end.timestamp() * 1000)
        except Exception:
            _start_ms = _end_ms = now_ms
        if _end_ms < _start_ms:
            _end_ms = _start_ms
        _span = max(1, len(keys_list) - 1)
    for i, k in enumerate(keys_list):
        ans_obj = {
            "dataKey": k,
            "answer": computed_answers[k]
        }
        if all_items_ts:
            # per-item ts within [mulai, selesai], monotonic by field order —
            # mirrors the app (mulai→start, selesai→end).
            ts = _start_ms + round(i * (_end_ms - _start_ms) / _span)
            ans_obj["updatedAt"] = ts
            ans_obj["createdAt"] = ts
        elif k in keys_with_timestamps or k.startswith("r2") or k.startswith("r3") or k in ("nama_ktp", "hasilPemadananNIK", "hasilPemadananNIK2", "result_callnik", "no_kk"):
            ans_obj["updatedAt"] = now_ms
            ans_obj["createdAt"] = now_ms
        answers_list.append(ans_obj)
        
    return {
        "dataKey": "",
        "createdAt": created_at,
        "createdBy": flat_answers.get("createdBy") or user_name,
        "updatedBy": user_name,
        "answers": answers_list,
        "description": "",
        "isForceSubmit": False,
        "templateVersion": target.get("templateVersion") or "0.5.9",
        "validationVersion": target.get("validationVersion") or "0.0.2",
        "updatedAt": now.strftime("%Y-%m-%dT%H:%M:%S.%f")[:-3] + "Z"
    }

def build_principal_json(answers: dict, target: dict, user_name: str) -> dict:
    if "answers" in answers and isinstance(answers["answers"], list):
        wrapped = answers
        answers_list = wrapped["answers"]
    else:
        wrapped = wrap_answers(answers, target, user_name)
        answers_list = wrapped["answers"]

    def find_ans(key):
        for item in answers_list:
            if item.get("dataKey") == key:
                return item.get("answer")
        return ""

    principals = []
    
    # 1. ID Pelanggan
    idpel = find_ans("r101a")
    if idpel:
        principals.append({"dataKey": "r101a", "answer": str(idpel), "principal": 1, "columnName": "ID Pelanggan"})
    # 2. Nama
    nama = find_ans("r103")
    if nama:
        principals.append({"dataKey": "r103", "answer": str(nama), "principal": 2, "columnName": "Nama"})
    # 3. No. Meter
    nometer = find_ans("r101b")
    if nometer:
        principals.append({"dataKey": "r101b", "answer": str(nometer), "principal": 3, "columnName": "No. Meter"})
    # 4. Alamat
    alamat = find_ans("r102e")
    if alamat:
        principals.append({"dataKey": "r102e", "answer": str(alamat), "principal": 4, "columnName": "Alamat"})
    use_legacy_format = (target.get("templateVersion") or "0.5.9" if target else "0.5.9") == "0.5.9"
    if use_legacy_format:
        # 5. IsPrelist
        flagpre = find_ans("flagpre")
        if flagpre is not None and flagpre != "":
            principals.append({"dataKey": "flagpre", "answer": flagpre, "principal": 5, "columnName": "IsPrelist"})
        # 6. Kddk
        kddk = find_ans("kddk")
        if kddk:
            principals.append({"dataKey": "kddk", "answer": str(kddk), "principal": 6, "columnName": "Kddk"})
    # 7. Hasil pendataan
    r104 = find_ans("r104")
    if r104:
        principals.append({"dataKey": "r104", "answer": r104, "principal": 7, "columnName": "Hasil pendataan"})

    from datetime import datetime
    return {
        "principals": principals,
        "templateVersion": target.get("templateVersion") or "0.5.9",
        "validationVersion": target.get("validationVersion") or "0.0.2",
        "updatedAt": wrapped.get("updatedAt") or datetime.utcnow().strftime("%Y-%m-%dT%H:%M:%S.%f")[:-3] + "Z",
        "updatedBy": user_name,
        "createdAt": wrapped.get("createdAt") or target.get("createdAt") or datetime.utcnow().strftime("%Y-%m-%dT%H:%M:%S.%f")[:-3] + "Z",
        "createdBy": wrapped.get("createdBy") or user_name
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
    status_alias = target.get("assignmentStatusAlias") or ""
    is_edit = "SUBMITTED" in status_alias  # REJECTED uses /submit, not /edit
    copy_from_id = target.get("copyFromId") if is_edit else None
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
        "copyFromId": str(target.get("copyFromId") or "") if target.get("isNew", False) or "SUBMITTED" in str(target.get("assignmentStatusAlias") or "") else "",
        "statusApproval": "false",
        "sourceFrom": "CAPI",
        "paradata": "",
        "comment": "",
        "note": ""
    }

def confirm_submission_flow(headers: dict, target: dict, answers: dict, template_mapping: dict, archive_md5: str, lat: Optional[float], lon: Optional[float], dry_run: bool):
    status_alias = target.get("assignmentStatusAlias") or ""
    is_edit = "SUBMITTED" in status_alias  # REJECTED uses /submit, not /edit
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
