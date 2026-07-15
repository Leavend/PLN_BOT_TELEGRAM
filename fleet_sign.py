#!/usr/bin/env python3
"""Tool OWNER (offline) — buat keypair & tanda-tangani control.json.

JANGAN jalankan di server PLN. Kunci privat tetap di mesin owner, jangan di-commit.
  python3 fleet_sign.py gen-key .fasih_fleet_key            # buat keypair, cetak pubkey hex
  python3 fleet_sign.py sign control.json .fasih_fleet_key  # tulis control.sig
"""
import sys
import os
from cryptography.hazmat.primitives.asymmetric.ed25519 import Ed25519PrivateKey
from cryptography.hazmat.primitives import serialization


def gen_key(priv_path):
    priv = Ed25519PrivateKey.generate()
    pem = priv.private_bytes(
        serialization.Encoding.PEM,
        serialization.PrivateFormat.PKCS8,
        serialization.NoEncryption(),
    )
    # O_EXCL: tolak menimpa kunci yang sudah ada (menimpa = kehilangan kontrol fleet).
    # mode 0o600 saat create: tidak ada jendela di mana kunci bisa dibaca akun lain.
    fd = os.open(priv_path, os.O_WRONLY | os.O_CREAT | os.O_EXCL, 0o600)
    with os.fdopen(fd, "wb") as f:
        f.write(pem)
    return priv.public_key().public_bytes(
        serialization.Encoding.Raw, serialization.PublicFormat.Raw).hex()


def _load_priv(priv_path):
    with open(priv_path, "rb") as f:
        return serialization.load_pem_private_key(f.read(), password=None)


def sign(control_path, priv_path):
    priv = _load_priv(priv_path)
    with open(control_path, "rb") as f:
        data = f.read()
    sig_hex = priv.sign(data).hex()
    sig_path = os.path.join(os.path.dirname(os.path.abspath(control_path)), "control.sig")
    with open(sig_path, "w") as f:
        f.write(sig_hex + "\n")
    return sig_path


def main(argv=None):
    argv = argv if argv is not None else sys.argv[1:]
    if len(argv) >= 2 and argv[0] == "gen-key":
        try:
            pub_hex = gen_key(argv[1])
        except FileExistsError:
            print(f"❌ Kunci sudah ada di {argv[1]}. Hapus manual dulu kalau memang mau bikin baru "
                  f"(ingat: ganti kunci = harus update PUBLIC_KEY_HEX di fleet.py + tanda-tangani ulang).")
            return 1
        print(f"✅ Keypair dibuat. Kunci privat: {argv[1]} (RAHASIA — jangan commit).")
        print(f'Tempel ke fleet.py:\n  PUBLIC_KEY_HEX = "{pub_hex}"')
        return 0
    if len(argv) >= 3 and argv[0] == "sign":
        print(f"✅ Ditandatangani -> {sign(argv[1], argv[2])}")
        return 0
    print("Usage:\n  fleet_sign.py gen-key <priv_path>\n"
          "  fleet_sign.py sign <control.json> <priv_path>")
    return 1


if __name__ == "__main__":
    sys.exit(main())
