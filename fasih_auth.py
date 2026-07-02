import os
import sys
import json
import base64
import re
import requests
import urllib3
from typing import Optional, Dict
from urllib.parse import urlparse, parse_qs
from dotenv import load_dotenv
load_dotenv()

# Suppress insecure HTTPS warning since we use direct IP connections to bypass Webshare filters
urllib3.disable_warnings(urllib3.exceptions.InsecureRequestWarning)


SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
TOKEN_FILE = os.path.join(SCRIPT_DIR, "fasih_token.json")
SSO_BASE = "https://sso.bps.go.id"
REALM_EKSTERNAL = "eksternal"
CLIENT_ID_EKSTERNAL = "03310-icscapi-k09"
REDIRECT_URI_EKSTERNAL = "id.go.bps://fasih-sso-eksternal"
REALM_INTERNAL = "pegawai-bps"
CLIENT_ID_INTERNAL = "03310-fasih-c0m"
REDIRECT_URI_INTERNAL = "id.go.bps://fasih-sso-internal"
USER_AGENT = "Dalvik/2.1.0 (Linux; U; Android 8.1.0; Android SDK built for x86 Build/OSM1.180201.021)"


def format_proxy_url(proxy_str: str) -> str:
    proxy_str = proxy_str.strip()
    if not proxy_str:
        return ""
    # Handle HOST:PORT:USER:PASS format
    parts = proxy_str.split(":")
    if len(parts) == 4:
        host, port, user, pw = parts
        return f"http://{user}:{pw}@{host}:{port}"
    # Ensure scheme is present
    if not (proxy_str.startswith("http://") or proxy_str.startswith("https://") or proxy_str.startswith("socks5://")):
        return f"http://{proxy_str}"
    return proxy_str

def get_bps_proxy() -> Optional[dict]:
    # 1. First check if a context sticky proxy is set
    try:
        from fasih_api import sticky_proxy_var
        sticky = sticky_proxy_var.get()
        if sticky:
            formatted_sticky = format_proxy_url(sticky)
            return {"http": formatted_sticky, "https": formatted_sticky}
    except Exception:
        pass

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
        except Exception:
            pass
            
    if pool:
        import random
        p = random.choice(pool)
        return {"http": p, "https": p}
    return None

def get_headers(token_data: dict) -> dict:
    """Returns headers with bearer authorization token."""
    return {
        "Authorization": f"Bearer {token_data['access_token']}",
        "User-Agent": USER_AGENT,
        "Content-Type": "application/json; charset=UTF-8",
        "Accept": "application/json",
        "Accept-Encoding": "gzip",
        "Connection": "keep-alive",
    }

def get_sso_session() -> requests.Session:
    session = requests.Session()
    session.headers.update({"User-Agent": "Mozilla/5.0 (Linux; Android 13)"})
    cf_proxy = os.getenv("CLOUDFLARE_PROXY_URL")
    if cf_proxy:
        original_send = session.send
        def wrapped_send(request, **kwargs):
            original_url = request.url
            request.url = cf_proxy
            request.headers['x-target-url'] = original_url
            return original_send(request, **kwargs)
        session.send = wrapped_send
    else:
        bps_proxy = get_bps_proxy()
        if bps_proxy:
            session.proxies.update(bps_proxy)
            # Bypassing Webshare "client_connect_forbidden_host" block by using direct IP resolution
            original_send = session.send
            def wrapped_send(request, **kwargs):
                original_url = request.url
                parsed = urlparse(original_url)
                hostname = parsed.hostname
                if hostname and not hostname.replace('.', '').isdigit():
                    try:
                        import socket
                        ip = socket.gethostbyname(hostname)
                        request.url = original_url.replace(hostname, ip, 1)
                        request.headers['Host'] = hostname
                        kwargs['verify'] = False # Disable SSL verification for IP-based requests to bypass SSL mismatch
                    except Exception:
                        pass
                return original_send(request, **kwargs)
            session.send = wrapped_send
    return session

def try_direct_grant(email, password, realm, client_id):
    """Mencoba Resource Owner Password Credentials (direct access grant)."""
    token_url = f"{SSO_BASE}/auth/realms/{realm}/protocol/openid-connect/token"
    data = {
        "client_id": client_id,
        "grant_type": "password",
        "username": email,
        "password": password,
    }
    try:
        session = get_sso_session()
        resp = session.post(token_url, data=data, timeout=15)
        if resp.status_code == 200:
            return resp.json()
    except Exception:
        pass
    return None

def get_login_action(html: str) -> Optional[str]:
    """Extracts kc-form-login action from login page html."""
    action_match = re.search(r'<form[^>]*id="kc-form-login"[^>]*action="([^"]+)"', html)
    if not action_match:
        action_match = re.search(r'action="(https://[^"]*login-actions/authenticate[^"]*)"', html)
    return action_match.group(1).replace("&amp;", "&") if action_match else None

def get_auth_code(location: str) -> Optional[str]:
    """Parses query string to extract authorization code."""
    parsed = urlparse(location)
    qs = parse_qs(parsed.query)
    return qs["code"][0] if "code" in qs else None

def exchange_code_for_token(token_url, client_id, redirect_uri, code):
    """Exchanges authorization code for access token."""
    exchange_data = {
        "client_id": client_id,
        "grant_type": "authorization_code",
        "redirect_uri": redirect_uri,
        "code": code,
    }
    try:
        session = get_sso_session()
        resp = session.post(token_url, data=exchange_data, timeout=15)
        return resp.json() if resp.status_code == 200 else None
    except Exception:
        return None

def try_browser_auth_code_flow(email, password, realm, client_id, redirect_uri):
    """Simulasi browser-based authorization code flow."""
    auth_url = f"{SSO_BASE}/auth/realms/{realm}/protocol/openid-connect/auth"
    token_url = f"{SSO_BASE}/auth/realms/{realm}/protocol/openid-connect/token"
    params = {"client_id": client_id, "redirect_uri": redirect_uri, "response_type": "code"}
    session = get_sso_session()
    try:
        resp = session.get(auth_url, params=params, timeout=15)
        action = get_login_action(resp.text) if resp.status_code == 200 else None
        if action:
            resp2 = session.post(action, data={"username": email, "password": password}, timeout=15, allow_redirects=False)
            code = get_auth_code(resp2.headers.get("Location", "")) if resp2.status_code in (302, 303) else None
            if code:
                return exchange_code_for_token(token_url, client_id, redirect_uri, code)
    except Exception:
        pass
    return None

def perform_login(email: str, password: str, exit_on_failure: bool = True) -> dict:
    """Melakukan login ke Keycloak SSO BPS menggunakan realm Eksternal/Internal."""
    flows = [
        lambda: try_direct_grant(email, password, REALM_EKSTERNAL, CLIENT_ID_EKSTERNAL),
        lambda: try_browser_auth_code_flow(email, password, REALM_EKSTERNAL, CLIENT_ID_EKSTERNAL, REDIRECT_URI_EKSTERNAL),
        lambda: try_direct_grant(email, password, REALM_INTERNAL, CLIENT_ID_INTERNAL),
        lambda: try_browser_auth_code_flow(email, password, REALM_INTERNAL, CLIENT_ID_INTERNAL, REDIRECT_URI_INTERNAL)
    ]
    for flow in flows:
        token_data = flow()
        if token_data:
            return token_data
    if exit_on_failure:
        print("[-] Semua metode autentikasi SSO Keycloak BPS gagal.")
        sys.exit(1)
    else:
        raise Exception("Semua metode autentikasi SSO Keycloak BPS gagal. Periksa kembali email dan password Anda.")

def load_token() -> dict:
    """Load saved token from disk, requesting credentials if file doesn't exist."""
    if os.path.exists(TOKEN_FILE):
        with open(TOKEN_FILE) as f:
            return json.load(f)
    print("[*] Berkas token (fasih_token.json) tidak ditemukan.")
    email = input("  Masukkan Email BPS: ").strip()
    import getpass
    password = getpass.getpass("  Masukkan Password BPS: ")
    print(f"[*] Menghubungi SSO BPS untuk melakukan autentikasi: {email}...")
    token_data = perform_login(email, password)
    with open(TOKEN_FILE, "w") as f:
        json.dump(token_data, f, indent=2)
    print("[+] Login berhasil! Token disimpan ke:", TOKEN_FILE)
    return token_data

def is_token_valid(token_data: dict) -> bool:
    """Checks if current token is valid and not expired."""
    try:
        payload_b64 = token_data["access_token"].split(".")[1]
        payload_b64 += "=" * (4 - len(payload_b64) % 4)
        jwt_payload = json.loads(base64.b64decode(payload_b64))
        return int(datetime.now().timestamp()) < jwt_payload.get("exp", 0) - 60
    except Exception:
        return False

def refresh_token_if_needed(token_data: dict, token_file: Optional[str] = None, exit_on_failure: bool = True) -> dict:
    """Check token expiry and refresh if needed."""
    if is_token_valid(token_data):
        return token_data
    print("[*] Token kedaluwarsa atau tidak valid, memperbarui token...")
    refresh_token = token_data.get("refresh_token")
    if not refresh_token:
        if exit_on_failure:
            print("[-] Tidak ada refresh token tersedia.")
            sys.exit(1)
        else:
            raise Exception("Tidak ada refresh token tersedia.")
    token_url = f"{SSO_BASE}/auth/realms/{REALM_EKSTERNAL}/protocol/openid-connect/token"
    try:
        session = get_sso_session()
        resp = session.post(token_url, data={
            "client_id": CLIENT_ID_EKSTERNAL,
            "grant_type": "refresh_token",
            "refresh_token": refresh_token,
        }, timeout=15)
        if resp.status_code == 200:
            new_token = resp.json()
            target_file = token_file or TOKEN_FILE
            with open(target_file, "w") as f:
                json.dump(new_token, f, indent=2)
            return new_token
        status_code = resp.status_code
    except Exception as e:
        if not exit_on_failure:
            raise Exception(f"Gagal memperbarui token (error jaringan): {e}")
        status_code = "Connection Error"
    
    if exit_on_failure:
        print(f"[-] Gagal memperbarui token: {status_code}")
        sys.exit(1)
    else:
        raise Exception(f"Gagal memperbarui token: status {status_code}")
