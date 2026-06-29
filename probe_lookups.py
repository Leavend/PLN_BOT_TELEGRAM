#!/usr/bin/env python3
import os
import sys
import json
import requests
import base64
from datetime import datetime
import zipfile
import io

SCRIPT_DIR = os.path.dirname(os.path.abspath(__file__))
TOKEN_FILE = os.path.join(SCRIPT_DIR, "fasih_token.json")
BASE_URL = "https://fasih-survey.bps.go.id"
SSO_BASE = "https://sso.bps.go.id"
REALM_EKSTERNAL = "eksternal"
CLIENT_ID_EKSTERNAL = "03310-icscapi-k09"

def load_token() -> dict:
    if not os.path.exists(TOKEN_FILE):
        print("[-] Token file not found.")
        sys.exit(1)
    with open(TOKEN_FILE) as f:
        return json.load(f)

def refresh_token_if_needed(token_data: dict) -> dict:
    try:
        payload_b64 = token_data["access_token"].split(".")[1]
        payload_b64 += "=" * (4 - len(payload_b64) % 4)
        jwt_payload = json.loads(base64.b64decode(payload_b64))
        exp = jwt_payload.get("exp", 0)
        now = int(datetime.now().timestamp())
        if now < exp - 60:
            return token_data
    except Exception:
        pass

    print("[*] Token expired, refreshing...")
    refresh_token = token_data.get("refresh_token")
    if not refresh_token:
        print("[-] No refresh token.")
        sys.exit(1)

    token_url = f"{SSO_BASE}/auth/realms/{REALM_EKSTERNAL}/protocol/openid-connect/token"
    resp = requests.post(token_url, data={
        "client_id": CLIENT_ID_EKSTERNAL,
        "grant_type": "refresh_token",
        "refresh_token": refresh_token,
    }, timeout=15)

    if resp.status_code == 200:
        new_token = resp.json()
        with open(TOKEN_FILE, "w") as f:
            json.dump(new_token, f, indent=2)
        return new_token
    else:
        print(f"[-] Refresh failed: {resp.status_code}")
        sys.exit(1)

def main():
    token_data = load_token()
    token_data = refresh_token_if_needed(token_data)
    
    headers = {
        "Authorization": f"Bearer {token_data['access_token']}",
        "User-Agent": "Dalvik/2.1.0 (Linux; U; Android 8.1.0; Android SDK built for x86 Build/OSM1.180201.021)",
    }

    lookups = [
        {"id": "67c29abf-5bb6-49f4-8473-696cfd558aee", "version": "v1"},
        {"id": "4693570b-7488-49cb-90f9-8938bef3cd61", "version": "1"},
        {"id": "f1fb5db7-505c-43d2-b208-c6be780b18ad", "version": "1"},
        {"id": "b32a1482-bdea-4de3-8934-5a6c3ec486d2", "version": "1"},
    ]

    os.makedirs(os.path.join(SCRIPT_DIR, "lookups"), exist_ok=True)

    for lookup in lookups:
        lid = lookup["id"]
        ver = lookup["version"]
        print(f"\n[*] Fetching lookup {lid} (version: {ver})...")
        
        # Build url matching Downloader.kt:
        # BASE_URL + "/mobile/lookup/api//v1/collections/" + lookup.getId() + "/download-zip-v2?version=" + lookup.getVersion()
        url = f"{BASE_URL}/mobile/lookup/api//v1/collections/{lid}/download-zip-v2"
        
        resp = requests.get(url, params={"version": ver}, headers=headers, timeout=60)
        
        if resp.status_code == 200:
            print(f"[+] Download successful! Size: {len(resp.content)} bytes")
            # Try to unzip in memory
            try:
                z = zipfile.ZipFile(io.BytesIO(resp.content))
                for name in z.namelist():
                    print(f"    - File in zip: {name}")
                    z.extract(name, os.path.join(SCRIPT_DIR, "lookups"))
                    json_path = os.path.join(SCRIPT_DIR, "lookups", name)
                    if os.path.exists(json_path):
                        with open(json_path) as f:
                            data = json.load(f)
                        fields = data.get("fields", [])
                        data_rows = data.get("data", [])
                        print(f"      Fields: {fields}")
                        print(f"      Rows count: {len(data_rows) // max(1, len(fields))}")
                        if data_rows:
                            print(f"      Sample row: {data_rows[:len(fields)]}")
            except Exception as e:
                print(f"    [-] Zip error: {e}")
        else:
            print(f"[-] Download failed: {resp.status_code} {resp.text[:200]}")

if __name__ == "__main__":
    main()
