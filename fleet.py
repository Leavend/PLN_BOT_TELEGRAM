"""Verifikasi manifest fleet bertanda-tangan (Ed25519). Fail-closed.

Kunci PUBLIK owner tertanam di PUBLIC_KEY_HEX. Kunci PRIVAT tidak pernah di sini —
tetap offline di sisi owner (lihat fleet_sign.py). Modul ini hanya memverifikasi.
"""
import os
import json
from datetime import datetime, timezone

PUBLIC_KEY_HEX = ""  # diisi owner setelah `python3 fleet_sign.py gen-key <path>`


def verify_signature(data, sig_hex, pubkey_hex):
    """True bila sig_hex tanda tangan Ed25519 valid atas `data` oleh pubkey_hex.
    Fail-closed: error apa pun (kunci kosong, hex rusak, sig salah) -> False."""
    try:
        from cryptography.hazmat.primitives.asymmetric.ed25519 import Ed25519PublicKey
        from cryptography.exceptions import InvalidSignature
        if not pubkey_hex or not sig_hex:
            return False
        pub = Ed25519PublicKey.from_public_bytes(bytes.fromhex(pubkey_hex))
        try:
            pub.verify(bytes.fromhex(sig_hex), data)
            return True
        except InvalidSignature:
            return False
    except Exception:
        return False


def load_and_verify(repo_root, pubkey_hex=None):
    """Baca control.json (byte mentah) + control.sig (hex), verifikasi, parse.
    Return dict manifest, atau None bila hilang/invalid/rusak."""
    if pubkey_hex is None:
        pubkey_hex = PUBLIC_KEY_HEX
    try:
        with open(os.path.join(repo_root, "control.json"), "rb") as f:
            data = f.read()
        with open(os.path.join(repo_root, "control.sig")) as f:
            sig_hex = f.read().strip()
    except (OSError, ValueError):
        return None
    if not verify_signature(data, sig_hex, pubkey_hex):
        return None
    try:
        return json.loads(data)
    except Exception:
        return None
