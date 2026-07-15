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


def test_load_and_verify_corrupt_sig_bytes_does_not_raise():
    d = tempfile.mkdtemp()
    with open(os.path.join(d, "control.json"), "wb") as f:
        f.write(b'{"regions":{}}')
    # control.sig with invalid UTF-8 bytes — must be rejected cleanly, not raise
    with open(os.path.join(d, "control.sig"), "wb") as f:
        f.write(b"\xff\xfe\x80not-hex")
    assert fleet.load_and_verify(d, pubkey_hex="ab" * 32) is None


def _manifest(**region_over):
    base = {"enabled": True, "pin": None, "machines": ["fp-me"]}
    base.update(region_over)
    return {"not_after": "2099-01-01T00:00:00Z", "regions": {"bontang": base}}


def test_authorize_ok():
    ok, reason, ctl = fleet.authorize(_manifest(), "bontang", "fp-me")
    assert ok is True and reason == "ok"
    assert ctl == {"enabled": True, "pin": None}


def test_authorize_unlisted_fingerprint():
    ok, reason, ctl = fleet.authorize(_manifest(), "bontang", "fp-stranger")
    assert ok is False and reason == "mesin tak terotorisasi" and ctl == {}


def test_authorize_missing_region():
    ok, reason, ctl = fleet.authorize(_manifest(), "balikpapan", "fp-me")
    assert ok is False and "region" in reason and ctl == {}


def test_authorize_expired():
    m = _manifest()
    m["not_after"] = "2000-01-01T00:00:00Z"
    ok, reason, ctl = fleet.authorize(m, "bontang", "fp-me")
    assert ok is False and reason == "expired"


def test_authorize_returns_enabled_pin():
    m = _manifest(enabled=False, pin="abc123")
    ok, reason, ctl = fleet.authorize(m, "bontang", "fp-me")
    assert ok is True and ctl == {"enabled": False, "pin": "abc123"}


def test_authorize_bad_not_after():
    m = _manifest()
    m["not_after"] = "garbage"
    ok, reason, ctl = fleet.authorize(m, "bontang", "fp-me")
    assert ok is False and reason == "not_after invalid"


def test_authorize_non_dict_manifest():
    ok, reason, ctl = fleet.authorize(None, "bontang", "fp-me")
    assert ok is False and ctl == {}


def test_authorize_regions_not_dict_failclosed():
    for bad in ("not-a-dict", ["x"], 1, True):
        m = {"not_after": "2099-01-01T00:00:00Z", "regions": bad}
        ok, reason, ctl = fleet.authorize(m, "bontang", "fp-me")
        assert ok is False and ctl == {}


def test_authorize_machines_as_string_no_substring_bypass():
    m = _manifest()
    m["regions"]["bontang"]["machines"] = "fp-legit"   # string, not list
    # empty fingerprint or a substring must NOT authorize
    assert fleet.authorize(m, "bontang", "")[0] is False
    assert fleet.authorize(m, "bontang", "fp-leg")[0] is False
    assert fleet.authorize(m, "bontang", "fp-legit")[0] is False   # even exact string content


def test_authorize_machines_non_list_failclosed():
    for bad in (1, True, {"fp-me": 1}):
        m = _manifest()
        m["regions"]["bontang"]["machines"] = bad
        ok, reason, ctl = fleet.authorize(m, "bontang", "fp-me")
        assert ok is False and ctl == {}


def test_load_and_verify_non_str_repo_root_no_raise():
    assert fleet.load_and_verify(None, pubkey_hex="ab" * 32) is None
    assert fleet.load_and_verify(123, pubkey_hex="ab" * 32) is None


def test_load_and_verify_signed_non_dict_json_is_none():
    priv, pub_hex = _keypair()
    d = tempfile.mkdtemp()
    data = b"[1, 2, 3]"                       # valid JSON, but not an object
    with open(os.path.join(d, "control.json"), "wb") as f:
        f.write(data)
    with open(os.path.join(d, "control.sig"), "w") as f:
        f.write(_sign(priv, data))
    assert fleet.load_and_verify(d, pubkey_hex=pub_hex) is None   # signature valid, shape wrong -> None


def test_authorize_non_datetime_now_no_raise():
    ok, reason, ctl = fleet.authorize(_manifest(), "bontang", "fp-me", now="garbage")
    assert ok is True          # coerced to real now(); manifest not_after is 2099 -> still valid
    ok2, _, _ = fleet.authorize(_manifest(), "bontang", "fp-me", now=12345)
    assert ok2 is True
