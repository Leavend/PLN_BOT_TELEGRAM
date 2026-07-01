#!/usr/bin/env python3
"""
BPS Mitra Bulk Registration via Direct API
No browser needed. No CAPTCHA needed.

Usage: python3 mitra_bps_register.py
"""

import re
import requests

# ── Config ─────────────────────────────────────────────────────
API_BASE = "https://mitra-pengguna-api.bps.go.id"
HEADERS = {
    "Origin": "https://mitra.bps.go.id",
    "Referer": "https://mitra.bps.go.id/",
}

# Defaults
DEF_NIK = "YOUR_DEFAULT_NIK"
DEF_PASSWORD = "YOUR_DEFAULT_PASSWORD"

# Strip leading "123.", "123<tab>", or "123 " prefixes from pasted data
LINE_NUM_RE = re.compile(r"^\d+[\t\s.]+")


def read_list(label):
    """Read multi-line input until empty line. Strips line-number prefixes."""
    print(f"  Masukkan {label} (satu per baris, kosongkan untuk selesai):")
    items = []
    while True:
        line = input("    > ").strip()
        if not line:
            break
        line = LINE_NUM_RE.sub("", line).strip()
        if line:
            items.append(line)
    return items


def register_with_code(email, nik, nama, password):
    """Register account via /api/users/withcode endpoint."""
    url = f"{API_BASE}/api/users/withcode"
    data = {
        "nama": nama,
        "nik": nik,
        "email": email,
        "username": email,
        "password": password,
        "captcha": "",
        "captcha_key": "",
        "captcha_value": "",
    }
    resp = requests.post(url, data=data, headers=HEADERS, timeout=30)
    return resp.json()


def main():
    print("=" * 50)
    print("  BPS Mitra - Bulk API Registration")
    print("=" * 50)
    print()

    emails = read_list("Email")
    if not emails:
        print("❌ Tidak ada email. Batal.")
        return

    print()
    names = read_list("Nama Lengkap")
    if not names:
        print("❌ Tidak ada nama. Batal.")
        return

    if len(emails) != len(names):
        print(f"❌ Jumlah email ({len(emails)}) ≠ nama ({len(names)}). Harus sama!")
        return

    print()
    nik = input(f"  NIK [{DEF_NIK}]: ").strip() or DEF_NIK
    password = input(f"  Password [{DEF_PASSWORD}]: ").strip() or DEF_PASSWORD

    print(f"\n{'=' * 50}")
    print(f"  Akan mendaftarkan {len(emails)} akun:")
    print(f"{'=' * 50}")
    for i, (e, n) in enumerate(zip(emails, names), 1):
        print(f"  {i}. {n} <{e}>")
    print(f"  NIK: {nik} | Password: {password}")
    print()

    confirm = input("  Lanjutkan? (y/n): ").strip().lower()
    if confirm != "y":
        print("  Dibatalkan.")
        return

    print()
    success = 0
    fail = 0
    for i, (email, nama) in enumerate(zip(emails, names), 1):
        print(f"[{i}/{len(emails)}] {nama} <{email}> ... ", end="", flush=True)
        try:
            result = register_with_code(email, nik, nama, password)
            if "userMitra" in result:
                user = result["userMitra"]
                print(f"✅ ID={user.get('id_mitra', '-')}")
                success += 1
            elif "errors" in result:
                print(f"❌ {result['errors']}")
                fail += 1
            else:
                print(f"⚠️ {result}")
                fail += 1
        except Exception as ex:
            print(f"❌ Error: {ex}")
            fail += 1

    print(f"\n{'=' * 50}")
    print(f"  Selesai! ✅ {success} berhasil, ❌ {fail} gagal")
    print(f"{'=' * 50}")
    if success:
        print(f"\n📧 Cek email masing-masing untuk link aktivasi!")


if __name__ == "__main__":
    main()
