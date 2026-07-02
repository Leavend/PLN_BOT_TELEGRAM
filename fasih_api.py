import os
import requests
from typing import List, Dict, Any
from requests.adapters import HTTPAdapter
from urllib3.util import Retry

import random
import logging

import urllib3
import urllib.parse
from dotenv import load_dotenv
load_dotenv()

# Suppress insecure HTTPS warning since we use direct IP connections to bypass Webshare filters
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)

logger = logging.getLogger(__name__)

BASE_URL = "https://fasih-survey.bps.go.id"
USER_AGENT = "Dalvik/2.1.0 (Linux; U; Android 8.1.0; Android SDK built for x86 Build/OSM1.180201.021)"

WEBSHARE_IPS = [
    "23.81.44.182",
    "89.187.161.115",
    "23.106.231.9",
    "199.254.199.189",
    "23.81.44.112",
    "23.81.44.12",
    "173.234.67.227",
    "199.254.199.110"
]

_proxy_dns_cache = {}
def resolve_proxy_host(proxy_url: str) -> str:
    if not proxy_url:
        return ""
    try:
        parsed = urllib.parse.urlparse(proxy_url)
        hostname = parsed.hostname
        if hostname == "p.webshare.io":
            ip = _proxy_dns_cache.get(hostname)
            if not ip:
                try:
                    import socket
                    ips = socket.getaddrinfo(hostname, None)
                    ip_list = list(set([item[4][0] for item in ips if ":" not in item[4][0]]))
                    if ip_list:
                        ip = random.choice(ip_list)
                        _proxy_dns_cache[hostname] = ip
                        logger.info(f"Dynamically resolved {hostname} to IP {ip} for proxy gateway.")
                except Exception as dns_err:
                    logger.warning(f"Failed to dynamically resolve {hostname}: {dns_err}. Using fallback.")
            
            if not ip:
                fallback_ips = [
                    "185.24.10.161", "185.24.10.162", "91.242.215.211", "66.203.112.161",
                    "91.242.215.219", "104.36.49.13", "185.24.10.166", "104.36.49.21"
                ]
                ip = random.choice(fallback_ips)
            
            netloc = parsed.netloc.replace(hostname, ip, 1)
            new_parsed = parsed._replace(netloc=netloc)
            return urllib.parse.urlunparse(new_parsed)
    except Exception as e:
        logger.warning(f"Failed to resolve proxy host in {proxy_url}: {e}")
    return proxy_url

def format_proxy_url(proxy_str: str) -> str:
    proxy_str = proxy_str.strip()
    if not proxy_str:
        return ""
    formatted = proxy_str
    # If it already starts with a scheme, it is already a formatted URL
    if not (proxy_str.startswith("http://") or proxy_str.startswith("https://") or proxy_str.startswith("socks5://")):
        # Handle HOST:PORT:USER:PASS format
        parts = proxy_str.split(":")
        if len(parts) == 4:
            host, port, user, pw = parts
            formatted = f"http://{user}:{pw}@{host}:{port}"
        else:
            # Ensure scheme is present
            formatted = f"http://{proxy_str}"
            
    # Resolve hostnames to IP addresses if applicable to bypass DNS block (like p.webshare.io)
    return resolve_proxy_host(formatted)

def load_proxy_pool() -> list:
    pool = []
    # 1. Single proxy from environment
    single_proxy = os.getenv("BPS_PROXY")
    if single_proxy:
        formatted = format_proxy_url(single_proxy)
        if formatted:
            pool.append(formatted)
        
    # 2. Proxy pool from environment (comma-separated list)
    env_pool = os.getenv("BPS_PROXY_POOL") or os.getenv("BPS_PROXIES")
    if env_pool:
        for p in env_pool.split(","):
            formatted = format_proxy_url(p)
            if formatted:
                pool.append(formatted)
                
    # 3. Proxy file
    proxy_file = os.getenv("BPS_PROXY_FILE")
    if proxy_file and os.path.exists(proxy_file):
        try:
            with open(proxy_file, "r") as f:
                for line in f:
                    line_str = line.strip()
                    if line_str and not line_str.startswith("#"):
                        formatted = format_proxy_url(line_str)
                        if formatted:
                            pool.append(formatted)
        except Exception as e:
            logger.error(f"Error reading BPS_PROXY_FILE: {e}")
            
    # Deduplicate while preserving order
    seen = set()
    deduped_pool = [x for x in pool if not (x in seen or seen.add(x))]
    return deduped_pool

import contextvars
import threading
import time

class ProxyManager:
    def __init__(self, proxy_pool: list):
        self.proxy_pool = proxy_pool
        self.lock = threading.Lock()
        # Maps proxy_url -> expiry timestamp (float)
        self.blocked_proxies = {}
        # Maps proxy_url -> consecutive failure count (int)
        self.failure_counts = {}
        if self.proxy_pool:
            logger.info(f"ProxyManager initialized with pool size: {len(self.proxy_pool)}")
        
    def report_failure(self, proxy: str, is_waf: bool = False):
        if not proxy:
            return
        with self.lock:
            # WAF block: cool down for 5 minutes (300 seconds)
            # Timeout/Other network error: cool down for 2 minutes (120 seconds)
            cooldown = 300 if is_waf else 120
            self.blocked_proxies[proxy] = time.time() + cooldown
            self.failure_counts[proxy] = self.failure_counts.get(proxy, 0) + 1
            logger.warning(
                f"Proxy reported failure: ...{proxy[-25:]} (is_waf={is_waf}). "
                f"Cool-down active for {cooldown}s. Fail count: {self.failure_counts[proxy]}"
            )
            
    def report_success(self, proxy: str):
        if not proxy:
            return
        with self.lock:
            if proxy in self.blocked_proxies:
                del self.blocked_proxies[proxy]
            self.failure_counts[proxy] = 0

    def get_proxy(self, exclude_proxy: str = None) -> str:
        if not self.proxy_pool:
            return None
            
        with self.lock:
            now = time.time()
            # Clean up expired blocks
            expired = [p for p, expiry in self.blocked_proxies.items() if now >= expiry]
            for p in expired:
                del self.blocked_proxies[p]
                
            # Filter active (not in cool-down) proxies
            available = [p for p in self.proxy_pool if p not in self.blocked_proxies and p != exclude_proxy]
            
            if available:
                # Sort available proxies to prefer those with fewer failures
                available.sort(key=lambda p: self.failure_counts.get(p, 0))
                # Choose randomly from the top 10% or at least top 3 least-failed to add load-balancing randomness
                pool_to_select = available[:max(3, len(available) // 10)]
                return random.choice(pool_to_select)
                
            # If all proxies are blocked, fallback to the one that expires earliest
            if self.blocked_proxies:
                sorted_blocked = sorted(self.blocked_proxies.items(), key=lambda x: x[1])
                return sorted_blocked[0][0]
                
            return random.choice(self.proxy_pool)

# Context local variable to hold a sticky proxy for a single customer submit context
sticky_proxy_var = contextvars.ContextVar("sticky_proxy", default=None)

# Load proxy list and initialize ProxyManager
proxy_list = load_proxy_pool()
proxy_manager = ProxyManager(proxy_list)

class RotatingProxySession(requests.Session):
    def __init__(self, proxy_pool: list):
        super().__init__()
        self.proxy_pool = proxy_pool
        if self.proxy_pool:
            logger.info(f"Initialized BPS RotatingProxySession with pool size: {len(self.proxy_pool)}")

    def send(self, request, **kwargs):
        # 1. Check if Cloudflare Worker Proxy is active
        cf_proxy_url = os.getenv("CLOUDFLARE_PROXY_URL")
        if cf_proxy_url:
            # Route requests through Cloudflare Worker Tunnel Proxy
            original_url = request.url
            request.url = cf_proxy_url
            request.headers['x-target-url'] = original_url
            logger.debug(f"Routing BPS request through Cloudflare Worker proxy tunnel to: {original_url}")
            return super().send(request, **kwargs)

        # 2. Otherwise fall back to Standard Proxy Rotation (Context/Sticky)
        proxy = sticky_proxy_var.get()
        if not proxy and self.proxy_pool:
            proxy = proxy_manager.get_proxy()
            
        if proxy:
            kwargs['proxies'] = {
                'http': proxy,
                'https': proxy
            }
            logger.debug(f"Routing BPS request through proxy: {proxy}")
        return super().send(request, **kwargs)

session = RotatingProxySession(proxy_list)

# Configure resilient connection pooling and retries with backoff
retries = Retry(
    total=3,
    backoff_factor=1,
    status_forcelist=[500, 502, 503, 504],
    raise_on_status=False
)
adapter = HTTPAdapter(pool_connections=25, pool_maxsize=25, max_retries=retries)
session.mount("https://", adapter)
session.mount("http://", adapter)

def fetch_surveys(headers: dict) -> list:
    """Fetch surveys assigned to the current user."""
    resp = session.get(f"{BASE_URL}/mobile/assignment-sync/api/mobile/survey/get-survey-for-capi", headers=headers, timeout=30)
    resp.raise_for_status()
    res_json = resp.json()
    if not res_json.get("success"):
        raise requests.exceptions.HTTPError(
            f"BPS Server Error: {res_json.get('message') or 'Gagal mengambil daftar survei'}",
            response=resp
        )
    return res_json.get("data") or []

def fetch_assignments(headers: dict, survey_period_id: str, page: int = 0) -> dict:
    """Fetch assignment datatable for a given survey period."""
    resp = session.get(
        f"{BASE_URL}/mobile/assignment-sync/api/mobile/s3/assignment/datatable",
        headers=headers, params={"surveyPeriodId": survey_period_id, "page": page}, timeout=30
    )
    resp.raise_for_status()
    res_json = resp.json()
    if not res_json.get("success"):
        raise requests.exceptions.HTTPError(
            f"BPS Server Error: {res_json.get('message') or 'Gagal mengambil daftar tugas'}",
            response=resp
        )
    return res_json


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
        res = fetch_assignments(headers, survey_period_id, page=p)
        return p, (res.get("data") or {}).get("content", [])
            
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
    resp = session.get(
        f"{BASE_URL}/mobile/assignment-sync/api/mobile/assignment-region/get-by-survey-periode-id",
        headers=headers, params={"surveyPeriodeId": survey_period_id}, timeout=30
    )
    resp.raise_for_status()
    res_json = resp.json()
    if not res_json.get("success"):
        raise requests.exceptions.HTTPError(
            f"BPS Server Error: {res_json.get('message') or 'Gagal mengambil daftar region'}",
            response=resp
        )
    return res_json.get("data") or []


def request_presign_url(headers: dict, assignment_id: str, survey_period_id: str, file_names: list, is_edit: bool = False, copy_from_id: str = None) -> dict:
    """Step 1: Request presigned upload URL from server."""
    url_path = "edit/presign-url" if is_edit else "presign-url"
    body = {
        "assignmentId": assignment_id,
        "fileNames": file_names
    }
    if copy_from_id:
        body["copyFromId"] = copy_from_id
    resp = session.post(
        f"{BASE_URL}/mobile/assignment-submit-2/api/assignment/s3/{url_path}",
        headers=headers, json=body,
        params={"surveyPeriodId": survey_period_id}, timeout=30
    )
    resp.raise_for_status()
    res_json = resp.json()
    if not res_json.get("success"):
        raise requests.exceptions.HTTPError(
            f"BPS Server Error: {res_json.get('message') or 'Gagal request presign url'}",
            response=resp
        )
    return res_json

def upload_to_s3(presigned_url: str, file_path: str) -> bool:
    """Step 2: Upload .7z file to S3 using presigned URL."""
    with open(file_path, "rb") as f:
        file_data = f.read()
    resp = session.put(
        presigned_url, data=file_data,
        headers={"Content-Type": "application/x-7z-compressed", "User-Agent": USER_AGENT}, timeout=60
    )
    if resp.status_code not in (200, 201):
        resp.raise_for_status()
    return True

def request_photo_presign_put(headers: dict, assignment_id: str, copy_from_id: str, survey_period_id: str, filename: str, size: int, md5_base64: str) -> dict:
    """Request presigned PUT URL for media upload."""
    body = [{
        "assignmentId": assignment_id,
        "copyFromId": copy_from_id or "",
        "fileNames": [{"fileName": filename, "mimeType": "image/png", "fileSize": size, "contentMD5": md5_base64}]
    }]
    resp = session.post(
        f"{BASE_URL}/mobile/assignment-submit-2/api/image/v2/presigned-url-put",
        headers=headers, json=body, params={"surveyPeriodId": survey_period_id}, timeout=30
    )
    resp.raise_for_status()
    res_json = resp.json()
    if not res_json.get("success"):
        raise requests.exceptions.HTTPError(
            f"BPS Server Error: {res_json.get('message') or 'Gagal request photo presign put'}",
            response=resp
        )
    return res_json

def upload_photo_to_s3(presigned_url: str, file_path: str, md5_base64: str) -> bool:
    """PUT upload photo to S3 with MD5 checksum."""
    size = os.path.getsize(file_path)
    with open(file_path, "rb") as f:
        file_data = f.read()
    resp = session.put(
        presigned_url, data=file_data,
        headers={"Content-Type": "image/png", "Content-MD5": md5_base64, "Content-Length": str(size), "User-Agent": USER_AGENT},
        timeout=60
    )
    if resp.status_code not in (200, 201):
        resp.raise_for_status()
    return True

def request_photo_presign_get(headers: dict, assignment_id: str, copy_from_id: str, survey_period_id: str, filename: str) -> dict:
    """Request presigned GET URL for media download/reference."""
    body = [{"assignmentId": assignment_id, "copyFromId": copy_from_id or "", "fileNames": [filename]}]
    resp = session.post(
        f"{BASE_URL}/mobile/assignment-submit-2/api/image/presigned-url-get",
        headers=headers, json=body, params={"surveyPeriodId": survey_period_id}, timeout=30
    )
    resp.raise_for_status()
    res_json = resp.json()
    if not res_json.get("success"):
        raise requests.exceptions.HTTPError(
            f"BPS Server Error: {res_json.get('message') or 'Gagal request photo presign get'}",
            response=resp
        )
    return res_json

def confirm_submit(headers: dict, params: dict, is_edit: bool = False) -> dict:
    """Step 3: Confirm submission with metadata."""
    url_path = "edit" if is_edit else "submit"
    resp = session.post(
        f"{BASE_URL}/mobile/assignment-submit-2/api/assignment/s3/{url_path}",
        headers=headers, json=params, timeout=60
    )
    if resp.status_code != 200:
        # Raise HTTPError with body text to allow propagation to the user
        raise requests.exceptions.HTTPError(
            f"HTTP {resp.status_code}: {resp.text}",
            response=resp
        )
    res_json = resp.json()
    if not res_json.get("success"):
        raise requests.exceptions.HTTPError(
            f"BPS Server Error: {res_json.get('message') or 'Gagal konfirmasi submit'}",
            response=resp
        )
    return res_json

def fetch_template_mapping(headers: dict, template_id: str, version: str) -> dict:
    """Fetch template custom data mapping (data1-data10 → field keys)."""
    resp = session.get(
        f"{BASE_URL}/mobile/assignment-sync/api/mobile/template/custom-data/{template_id}",
        headers=headers, params={"version": version}, timeout=30
    )
    resp.raise_for_status()
    res_json = resp.json()
    if not res_json.get("success"):
        raise requests.exceptions.HTTPError(
            f"BPS Server Error: {res_json.get('message') or 'Gagal mengambil template mapping'}",
            response=resp
        )
    mapping = {}
    if res_json.get("data"):
        for slot_name, slot_data in res_json["data"].items():
            if slot_data and isinstance(slot_data, dict) and slot_data.get("dataKey"):
                mapping[slot_name] = slot_data["dataKey"]
    return mapping


def map_answers_to_data_slots(answers: dict, template_mapping: dict) -> dict:
    """Map answer keys to data1-data10 slots based on template mapping."""
    result = {}
    for slot, field_key in template_mapping.items():
        result[slot] = answers.get(field_key, "")
    return result
