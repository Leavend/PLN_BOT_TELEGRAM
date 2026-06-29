import os
import requests
from typing import List, Dict, Any

BASE_URL = "https://fasih-survey.bps.go.id"
USER_AGENT = "Dalvik/2.1.0 (Linux; U; Android 8.1.0; Android SDK built for x86 Build/OSM1.180201.021)"

def fetch_surveys(headers: dict) -> list:
    """Fetch surveys assigned to the current user."""
    resp = requests.get(f"{BASE_URL}/mobile/assignment-sync/api/mobile/survey/get-survey-for-capi", headers=headers, timeout=15)
    resp.raise_for_status()
    return resp.json().get("data", [])

def fetch_assignments(headers: dict, survey_period_id: str, page: int = 0) -> dict:
    """Fetch assignment datatable for a given survey period."""
    resp = requests.get(
        f"{BASE_URL}/mobile/assignment-sync/api/mobile/s3/assignment/datatable",
        headers=headers, params={"surveyPeriodId": survey_period_id, "page": page}, timeout=15
    )
    resp.raise_for_status()
    return resp.json()

def fetch_all_assignments(headers: dict, survey_period_id: str) -> list:
    """Fetch all assignments from BPS server in parallel using ThreadPoolExecutor."""
    import math
    from concurrent.futures import ThreadPoolExecutor
    
    first_page = fetch_assignments(headers, survey_period_id, page=0)
    data_wrapper = first_page.get("data") or {}
    content_p0 = data_wrapper.get("content", [])
    total_server = data_wrapper.get("total", 0)
    
    all_content = [None] * total_server
    for idx, item in enumerate(content_p0):
        if idx < len(all_content):
            all_content[idx] = item
            
    page_size = data_wrapper.get("pageable", {}).get("size") or len(content_p0) or 100
    num_pages = math.ceil(total_server / page_size)
    if num_pages <= 1:
        return [x for x in all_content if x is not None]
        
    pages_to_fetch = list(range(1, num_pages))
    
    def fetch_page_worker(p):
        try:
            res = fetch_assignments(headers, survey_period_id, page=p)
            return p, (res.get("data") or {}).get("content", [])
        except Exception:
            return p, []
            
    with ThreadPoolExecutor(max_workers=5) as executor:
        futures = {executor.submit(fetch_page_worker, p): p for p in pages_to_fetch}
        for future in futures:
            p, page_content = future.result()
            start_idx = p * page_size
            for offset, item in enumerate(page_content):
                idx = start_idx + offset
                if idx < len(all_content):
                    all_content[idx] = item
                    
    return [x for x in all_content if x is not None]

def fetch_regions(headers: dict, survey_period_id: str) -> list:
    """Fetch assignment regions (contains wrappedDataKey)."""
    resp = requests.get(
        f"{BASE_URL}/mobile/assignment-sync/api/mobile/assignment-region/get-by-survey-periode-id",
        headers=headers, params={"surveyPeriodeId": survey_period_id}, timeout=15
    )
    resp.raise_for_status()
    return resp.json().get("data", [])

def request_presign_url(headers: dict, assignment_id: str, survey_period_id: str, file_names: list, is_edit: bool = False, copy_from_id: str = None) -> dict:
    """Step 1: Request presigned upload URL from server."""
    url_path = "edit/presign-url" if is_edit else "presign-url"
    body = {
        "assignmentId": assignment_id,
        "fileNames": file_names
    }
    if copy_from_id:
        body["copyFromId"] = copy_from_id
    resp = requests.post(
        f"{BASE_URL}/mobile/assignment-submit-2/api/assignment/s3/{url_path}",
        headers=headers, json=body,
        params={"surveyPeriodId": survey_period_id}, timeout=15
    )
    resp.raise_for_status()
    return resp.json()

def upload_to_s3(presigned_url: str, file_path: str) -> bool:
    """Step 2: Upload .7z file to S3 using presigned URL."""
    with open(file_path, "rb") as f:
        file_data = f.read()
    resp = requests.put(
        presigned_url, data=file_data,
        headers={"Content-Type": "application/x-7z-compressed", "User-Agent": USER_AGENT}, timeout=60
    )
    return resp.status_code in (200, 201)

def request_photo_presign_put(headers: dict, assignment_id: str, copy_from_id: str, survey_period_id: str, filename: str, size: int, md5_base64: str) -> dict:
    """Request presigned PUT URL for media upload."""
    body = [{
        "assignmentId": assignment_id,
        "copyFromId": copy_from_id or "",
        "fileNames": [{"fileName": filename, "mimeType": "image/png", "fileSize": size, "contentMD5": md5_base64}]
    }]
    resp = requests.post(
        f"{BASE_URL}/mobile/assignment-submit-2/api/image/v2/presigned-url-put",
        headers=headers, json=body, params={"surveyPeriodId": survey_period_id}, timeout=15
    )
    resp.raise_for_status()
    return resp.json()

def upload_photo_to_s3(presigned_url: str, file_path: str, md5_base64: str) -> bool:
    """PUT upload photo to S3 with MD5 checksum."""
    size = os.path.getsize(file_path)
    with open(file_path, "rb") as f:
        file_data = f.read()
    resp = requests.put(
        presigned_url, data=file_data,
        headers={"Content-Type": "image/png", "Content-MD5": md5_base64, "Content-Length": str(size), "User-Agent": USER_AGENT},
        timeout=60
    )
    return resp.status_code in (200, 201)

def request_photo_presign_get(headers: dict, assignment_id: str, copy_from_id: str, survey_period_id: str, filename: str) -> dict:
    """Request presigned GET URL for media download/reference."""
    body = [{"assignmentId": assignment_id, "copyFromId": copy_from_id or "", "fileNames": [filename]}]
    resp = requests.post(
        f"{BASE_URL}/mobile/assignment-submit-2/api/image/presigned-url-get",
        headers=headers, json=body, params={"surveyPeriodId": survey_period_id}, timeout=15
    )
    resp.raise_for_status()
    return resp.json()

def confirm_submit(headers: dict, params: dict, is_edit: bool = False) -> dict:
    """Step 3: Confirm submission with metadata."""
    url_path = "edit" if is_edit else "submit"
    resp = requests.post(
        f"{BASE_URL}/mobile/assignment-submit-2/api/assignment/s3/{url_path}",
        headers=headers, json=params, timeout=15
    )
    if resp.status_code != 200:
        print(f"[-] Confirm submission failed (status {resp.status_code}):\n{resp.text}")
    resp.raise_for_status()
    return resp.json()

def fetch_template_mapping(headers: dict, template_id: str, version: str) -> dict:
    """Fetch template custom data mapping (data1-data10 → field keys)."""
    resp = requests.get(
        f"{BASE_URL}/mobile/assignment-sync/api/mobile/template/custom-data/{template_id}",
        headers=headers, params={"version": version}, timeout=15
    )
    resp.raise_for_status()
    result = resp.json()
    mapping = {}
    if result.get("data"):
        for slot_name, slot_data in result["data"].items():
            if slot_data and isinstance(slot_data, dict) and slot_data.get("dataKey"):
                mapping[slot_name] = slot_data["dataKey"]
    return mapping

def map_answers_to_data_slots(answers: dict, template_mapping: dict) -> dict:
    """Map answer keys to data1-data10 slots based on template mapping."""
    result = {}
    for slot, field_key in template_mapping.items():
        result[slot] = answers.get(field_key, "")
    return result
