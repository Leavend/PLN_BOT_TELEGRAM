import os
import sys
import json
import base64
import re
import requests
from urllib.parse import urlparse, parse_qs
from datetime import datetime
from typing import Optional

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

def get_headers(token_data: dict) -> dict:
    """Returns headers with bearer authorization token."""
    return {
        "Authorization": f"Bearer {token_data['access_token']}",
        "User-Agent": USER_AGENT,
        "Content-Type": "application/json",
    }

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
        resp = requests.post(token_url, data=data, timeout=15)
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
    resp = requests.post(token_url, data=exchange_data, timeout=15)
    return resp.json() if resp.status_code == 200 else None

def try_browser_auth_code_flow(email, password, realm, client_id, redirect_uri):
    """Simulasi browser-based authorization code flow."""
    auth_url = f"{SSO_BASE}/auth/realms/{realm}/protocol/openid-connect/auth"
    token_url = f"{SSO_BASE}/auth/realms/{realm}/protocol/openid-connect/token"
    params = {"client_id": client_id, "redirect_uri": redirect_uri, "response_type": "code"}
    session = requests.Session()
    session.headers.update({"User-Agent": "Mozilla/5.0 (Linux; Android 13)"})
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
        resp = requests.post(token_url, data={
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
