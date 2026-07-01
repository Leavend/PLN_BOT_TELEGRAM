import requests
import json

# Configuration
API_PENGGUNA = 'https://mitra-pengguna-api.bps.go.id'
TOKEN = 'YOUR_JWT_TOKEN_HERE'
COOKIE_F5 = 'f5avraaaaaaaaaaaaaaaa_session_=YOUR_SESSION_COOKIE_HERE'

HEADERS = {
    'Origin': 'https://mitra.bps.go.id',
    'Referer': 'https://mitra.bps.go.id/profil',
    'X-App-Version': 'v1.8.7',
    'X-App-Name': 'sobat-web',
    'Authorization': f'Bearer {TOKEN}',
    'Cookie': COOKIE_F5,
    'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/137.0.0.0 Safari/537.36',
    'Content-Type': 'application/json'
}

def verify_nik():
    # User Profile Data
    nama = 'YOUR_NAME_HERE'
    nik = 'YOUR_NIK_HERE'
    
    payload = {
        'nama': nama,
        'nik': nik,
        'captcha': '',
        'captcha_key': '',
        'captcha_value': ''
    }
    
    print(f"[*] Mengirim verifikasi NIK untuk {nama} ({nik})...")
    url = f"{API_PENGGUNA}/api/userm/verify-nik"
    
    response = requests.post(url, json=payload, headers=HEADERS, timeout=10)
    print(f"[Status] HTTP Code: {response.status_code}")
    
    try:
        res_json = response.json()
        print("[Response]", json.dumps(res_json, indent=2))
        return res_json
    except:
        print("[Response Text]", response.text)
        return None

if __name__ == '__main__':
    verify_nik()
