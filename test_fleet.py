import os, json, tempfile
import fleet


def _keypair():
    from cryptography.hazmat.primitives.asymmetric.ed25519 import Ed25519PrivateKey
    from cryptography.hazmat.primitives import serialization
    priv = Ed25519PrivateKey.generate()
    pub_hex = priv.public_key().public_bytes(
        serialization.Encoding.Raw, serialization.PublicFormat.Raw).hex()
    return priv, pub_hex


def _sign(priv, data: bytes) -> str:
    return priv.sign(data).hex()


def test_verify_signature_roundtrip():
    priv, pub_hex = _keypair()
    data = b'{"hello":"world"}'
    sig = _sign(priv, data)
    assert fleet.verify_signature(data, sig, pub_hex) is True
    assert fleet.verify_signature(data + b"x", sig, pub_hex) is False   # tampered
    _, other_pub = _keypair()
    assert fleet.verify_signature(data, sig, other_pub) is False        # wrong key


def test_verify_signature_failclosed_on_garbage():
    assert fleet.verify_signature(b"x", "", "ab" * 32) is False          # empty sig
    assert fleet.verify_signature(b"x", "aa", "") is False               # empty key
    assert fleet.verify_signature(b"x", "nothex", "nothex") is False     # bad hex


def test_load_and_verify_ok_and_tamper():
    priv, pub_hex = _keypair()
    d = tempfile.mkdtemp()
    manifest = {"not_after": "2099-01-01T00:00:00Z", "regions": {}}
    data = json.dumps(manifest).encode()
    with open(os.path.join(d, "control.json"), "wb") as f:
        f.write(data)
    with open(os.path.join(d, "control.sig"), "w") as f:
        f.write(_sign(priv, data))
    assert fleet.load_and_verify(d, pubkey_hex=pub_hex) == manifest
    with open(os.path.join(d, "control.json"), "wb") as f:
        f.write(data + b" ")   # tamper after signing
    assert fleet.load_and_verify(d, pubkey_hex=pub_hex) is None


def test_load_and_verify_missing_files():
    d = tempfile.mkdtemp()
    assert fleet.load_and_verify(d, pubkey_hex="ab" * 32) is None
