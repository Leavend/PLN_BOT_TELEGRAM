# Signed Fleet Manifest — Verify Core Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Menyediakan primitive kripto fleet: modul `fleet.py` yang memverifikasi manifest bertanda-tangan Ed25519 (fail-closed) dan menentukan apakah mesin ini terotorisasi, plus tool owner offline `fleet_sign.py` untuk membuat keypair dan menandatangani `control.json`.

**Architecture:** `fleet.py` menyimpan kunci PUBLIK owner (`PUBLIC_KEY_HEX`) dan mengekspos `verify_signature`/`load_and_verify` (kripto) + `authorize` (kebijakan fail-closed: expiry, region, fingerprint). `fleet_sign.py` adalah tool owner terpisah (kunci PRIVAT hanya di sisi owner) untuk `gen-key` dan `sign`. E1 tidak menegakkan apa pun di runtime — supervisor baru memakainya di E3.

**Tech Stack:** Python 3 stdlib + `cryptography` (Ed25519), pytest 9.x.

## Global Constraints

- Ed25519 via `cryptography` (tersedia; jadikan eksplisit di `requirements.txt`).
- **Fail-closed**: kunci kosong / sig invalid / manifest hilang / kadaluarsa / fingerprint tak terdaftar → tidak diizinkan. Tidak pernah fail-open, tidak pernah raise ke pemanggil.
- Kunci privat **tidak pernah** di `fleet.py` atau repo — hanya di `fleet_sign.py` runtime pada mesin owner; di-gitignore.
- Tanda tangan atas **byte mentah** `control.json` (detached), disimpan hex di `control.sig`.
- `PUBLIC_KEY_HEX` awalnya `""` (kosong → tolak semua). Diisi owner setelah `gen-key`.
- E1 **tidak** mengubah kode A/B/C/D dan **tidak** membuat `control.json`/`control.sig` produksi (itu urusan owner via `fleet_sign.py`). Suite existing (35 test) tetap hijau.
- Test framework = pytest; tanpa jaringan; keypair EPHEMERAL dibuat di test (tanpa kunci produksi).

---

### Task 1: `fleet.py` — verify_signature + load_and_verify

**Files:**
- Create: `fleet.py`
- Test: `test_fleet.py`

**Interfaces:**
- Consumes: `cryptography` (Ed25519).
- Produces:
  - `PUBLIC_KEY_HEX = ""` (konstanta).
  - `verify_signature(data: bytes, sig_hex: str, pubkey_hex: str) -> bool` — fail-closed.
  - `load_and_verify(repo_root, pubkey_hex=None) -> dict | None` — baca+verifikasi+parse; None bila gagal. `pubkey_hex=None` → pakai `PUBLIC_KEY_HEX`.

- [ ] **Step 1: Write the failing test**

```python
# test_fleet.py
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
```

- [ ] **Step 2: Run test to verify it fails**

Run: `python3 -m pytest test_fleet.py -v`
Expected: FAIL — `ModuleNotFoundError: No module named 'fleet'`

- [ ] **Step 3: Write minimal implementation**

```python
# fleet.py
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
    except OSError:
        return None
    if not verify_signature(data, sig_hex, pubkey_hex):
        return None
    try:
        return json.loads(data)
    except Exception:
        return None
```

- [ ] **Step 4: Run test to verify it passes**

Run: `python3 -m pytest test_fleet.py -v`
Expected: PASS (4 tests)

- [ ] **Step 5: Commit**

```bash
git add fleet.py test_fleet.py
git commit -m "feat(fleet): Ed25519 verify_signature + load_and_verify (fail-closed)"
```

---

### Task 2: `fleet.py` — authorize (fail-closed policy)

**Files:**
- Modify: `fleet.py`
- Test: `test_fleet.py` (append)

**Interfaces:**
- Consumes: `datetime`/`timezone` (sudah di-import Task 1).
- Produces:
  - `authorize(manifest, region, fingerprint, now=None) -> tuple[bool, str, dict]` — `(ok, reason, region_ctl)`. `region_ctl = {"enabled": bool, "pin": str|None}` saat ok, else `{}`. `now` default `datetime.now(timezone.utc)`.
  - `_parse_iso(s) -> datetime|None` (helper, aware UTC).

- [ ] **Step 1: Write the failing test**

```python
# append to test_fleet.py
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
```

- [ ] **Step 2: Run test to verify it fails**

Run: `python3 -m pytest test_fleet.py -k authorize -v`
Expected: FAIL — `AttributeError: module 'fleet' has no attribute 'authorize'`

- [ ] **Step 3: Write minimal implementation**

```python
# add to fleet.py

def _parse_iso(s):
    """ISO-8601 -> aware UTC datetime, atau None."""
    try:
        s = s.strip()
        if s.endswith("Z"):
            s = s[:-1] + "+00:00"
        dt = datetime.fromisoformat(s)
        if dt.tzinfo is None:
            dt = dt.replace(tzinfo=timezone.utc)
        return dt.astimezone(timezone.utc)
    except Exception:
        return None


def authorize(manifest, region, fingerprint, now=None):
    """(ok, reason, region_ctl). Fail-closed. region_ctl = {enabled, pin} saat ok, else {}."""
    if now is None:
        now = datetime.now(timezone.utc)
    if now.tzinfo is None:
        now = now.replace(tzinfo=timezone.utc)
    if not isinstance(manifest, dict):
        return False, "manifest invalid", {}
    na = _parse_iso(str(manifest.get("not_after", "")))
    if na is None:
        return False, "not_after invalid", {}
    if now > na:
        return False, "expired", {}
    regions = manifest.get("regions") or {}
    rc = regions.get(region)
    if not isinstance(rc, dict):
        return False, "region tak terdaftar", {}
    machines = rc.get("machines") or []
    if fingerprint not in machines:
        return False, "mesin tak terotorisasi", {}
    return True, "ok", {"enabled": bool(rc.get("enabled", False)), "pin": rc.get("pin")}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `python3 -m pytest test_fleet.py -v`
Expected: PASS (11 tests total — 4 + 7)

- [ ] **Step 5: Commit**

```bash
git add fleet.py test_fleet.py
git commit -m "feat(fleet): authorize — fail-closed expiry/region/fingerprint policy"
```

---

### Task 3: `fleet_sign.py` owner tool + wiring

**Files:**
- Create: `fleet_sign.py`
- Test: `test_fleet_sign.py`
- Modify: `requirements.txt`, `.gitignore`

**Interfaces:**
- Consumes: `fleet.load_and_verify`, `fleet.authorize` (Tasks 1-2); `cryptography`.
- Produces:
  - `gen_key(priv_path) -> str` (pubkey hex; menulis PEM privat, chmod 600).
  - `sign(control_path, priv_path) -> str` (menulis `control.sig` di dir yang sama; return path).
  - `main(argv=None) -> int` (`gen-key <priv>` / `sign <control.json> <priv>`).

- [ ] **Step 1: Write the failing test**

```python
# test_fleet_sign.py
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
```

- [ ] **Step 2: Run test to verify it fails**

Run: `python3 -m pytest test_fleet_sign.py -v`
Expected: FAIL — `ModuleNotFoundError: No module named 'fleet_sign'`

- [ ] **Step 3: Write minimal implementation**

```python
# fleet_sign.py
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
    with open(priv_path, "wb") as f:
        f.write(pem)
    try:
        os.chmod(priv_path, 0o600)
    except OSError:
        pass
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
        pub_hex = gen_key(argv[1])
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
```

- [ ] **Step 4: Run test to verify it passes**

Run: `python3 -m pytest test_fleet_sign.py -v`
Expected: PASS (2 tests)

- [ ] **Step 5: Wire requirements + gitignore**

Add `cryptography` to `requirements.txt` (append a line):

```
cryptography
```

Add the private-key patterns to `.gitignore` (only if not already present):

```
.fasih_fleet_key
.fasih_fleet_key*
*.fleetkey
```

- [ ] **Step 6: Full suite + commit**

Run: `python3 -m pytest test_fleet.py test_fleet_sign.py test_region.py test_supervisor.py test_setup_region.py test_server_region.py test_petugas_url.py -q`
Expected: PASS (11 + 2 + 35 = 48)

```bash
git add fleet_sign.py test_fleet_sign.py requirements.txt .gitignore
git commit -m "feat(fleet): fleet_sign.py owner tool (gen-key + sign) + deps/gitignore"
```

---

## Self-Review

**Spec coverage:**
- `fleet.py` `PUBLIC_KEY_HEX` + `verify_signature` + `load_and_verify` (fail-closed, byte mentah) → Task 1. ✓
- `authorize` (not_after/region/fingerprint fail-closed, return enabled/pin) → Task 2. ✓
- `_parse_iso` (aware UTC, hindari TypeError naive-vs-aware) → Task 2. ✓
- `fleet_sign.py` `gen-key` (PEM privat, chmod 600, cetak pubkey) + `sign` (control.sig hex atas byte mentah) → Task 3. ✓
- `requirements.txt` +`cryptography` → Task 3 Step 5. ✓
- `.gitignore` pola kunci privat → Task 3 Step 5. ✓
- Test keypair ephemeral, tanpa jaringan, pakai `pubkey_hex=` param (bukan PUBLIC_KEY_HEX kosong) → Task 1-3 tests. ✓
- E1 tidak mengubah A/B/C/D, tidak membuat control.json produksi, 35 test tetap hijau → Task 3 Step 6 (48 total). ✓
- Integrasi sign→verify end-to-end (fleet_sign output diverifikasi fleet) → Task 3 test. ✓

**Placeholder scan:** tidak ada TBD/TODO; semua step berisi kode nyata. `PUBLIC_KEY_HEX = ""` adalah nilai awal yang disengaja (fail-closed), bukan placeholder yang harus diisi implementer. ✓

**Type consistency:** `verify_signature(data, sig_hex, pubkey_hex) -> bool`, `load_and_verify(repo_root, pubkey_hex=None) -> dict|None`, `authorize(manifest, region, fingerprint, now=None) -> (bool, str, dict)`, `gen_key(priv_path) -> str`, `sign(control_path, priv_path) -> str` — konsisten lintas task + test. `control.sig` (hex), `control.json` (byte mentah) seragam. ✓
