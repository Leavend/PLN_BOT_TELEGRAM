import os, json, tempfile
import fleet, fleet_sign


def test_genkey_sign_verify_end_to_end():
    d = tempfile.mkdtemp()
    priv_path = os.path.join(d, ".fasih_fleet_key")
    pub_hex = fleet_sign.gen_key(priv_path)
    assert os.path.exists(priv_path)
    assert len(bytes.fromhex(pub_hex)) == 32          # Ed25519 pubkey = 32 byte
    manifest = {"not_after": "2099-01-01T00:00:00Z",
                "regions": {"bontang": {"enabled": True, "pin": None, "machines": ["fp1"]}}}
    control_path = os.path.join(d, "control.json")
    with open(control_path, "w") as f:
        json.dump(manifest, f)
    sig_path = fleet_sign.sign(control_path, priv_path)
    assert os.path.exists(sig_path)
    loaded = fleet.load_and_verify(d, pubkey_hex=pub_hex)   # fleet verifies fleet_sign's output
    assert loaded == manifest
    ok, reason, ctl = fleet.authorize(loaded, "bontang", "fp1")
    assert ok and ctl == {"enabled": True, "pin": None}


def test_sign_then_tamper_fails_verify():
    d = tempfile.mkdtemp()
    priv_path = os.path.join(d, ".fasih_fleet_key")
    pub_hex = fleet_sign.gen_key(priv_path)
    control_path = os.path.join(d, "control.json")
    with open(control_path, "w") as f:
        f.write('{"not_after":"2099-01-01T00:00:00Z","regions":{}}')
    fleet_sign.sign(control_path, priv_path)
    with open(control_path, "a") as f:
        f.write("  ")                                  # tamper after signing
    assert fleet.load_and_verify(d, pubkey_hex=pub_hex) is None
