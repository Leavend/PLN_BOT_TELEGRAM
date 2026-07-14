# Rollout Wilayah via Named Tunnel Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Sebuah wilayah baru bisa di-onboard dengan named tunnel Cloudflare (hostname stabil `<region>.<domain>`) lewat satu script, dan supervisor otomatis menjalankan named tunnel bila wilayah sudah dikonfigurasi — tanpa perlu propagasi URL yang berputar.

**Architecture:** Supervisor (A) memilih perintah tunnel via `tunnel_cmd(region)`: named tunnel bila penanda `.tunnel_named` ada, selain itu quick tunnel (Bontang legacy). Script `setup_region.py` menulis config region (`.region`, `pln_url_<region>.txt`, folder foto, `~/.cloudflared/config.yml`) dan membuat penanda `.tunnel_named` HANYA bila `cloudflared` berhasil menyiapkan named tunnel. Runbook menuntun prasyarat manual (domain + `cloudflared tunnel login`).

**Tech Stack:** Python 3 stdlib, pytest 9.x, cloudflared (runtime only, tidak dibutuhkan saat test).

## Global Constraints

- Python 3, **stdlib only** untuk `supervisor.py` dan `setup_region.py` (tanpa dependency baru). `region.py` (dipakai supervisor) juga stdlib-only.
- `PORT = int(os.getenv("PLN_API_PORT", "8900"))` konsisten di supervisor + setup_region.
- Penanda named tunnel = file `<repo>/.tunnel_named` (gitignored, per-mesin). Dibuat HANYA saat `named_ok` True.
- Backward-compatible: tanpa `.tunnel_named` → quick tunnel; Bontang existing tidak berubah.
- Test framework = `pytest`. Test **tidak boleh** butuh jaringan atau `cloudflared` (mock/skip via `shutil.which`).
- Hostname = `<region>.<domain>`; `pln_url_<region>.txt` = `https://<region>.<domain>`.

---

### Task 1: Supervisor `tunnel_cmd` region-aware

**Files:**
- Modify: `supervisor.py` (imports ~14; constants ~19; before `DEFAULT_SERVICES` ~26; tunnel entry line 31)
- Test: `test_supervisor.py` (append)

**Interfaces:**
- Consumes: `region.get_region` (dari sub-proyek B), `REPO_ROOT`, `PORT` (supervisor).
- Produces:
  - `REGION: str` (`= get_region()`).
  - `tunnel_cmd(region, repo_root=REPO_ROOT) -> list[str]` — `["cloudflared","tunnel","run",region]` bila `<repo_root>/.tunnel_named` ada, else `["cloudflared","tunnel","--url",f"http://localhost:{PORT}"]`.
  - `DEFAULT_SERVICES` entry `tunnel` memakai `tunnel_cmd(REGION)`.

- [ ] **Step 1: Write the failing test**

```python
# append to test_supervisor.py
def test_tunnel_cmd_quick_when_no_marker():
    d = tempfile.mkdtemp()
    cmd = sup.tunnel_cmd("balikpapan", repo_root=d)
    assert cmd[0] == "cloudflared" and cmd[1] == "tunnel" and cmd[2] == "--url"
    assert "run" not in cmd


def test_tunnel_cmd_named_when_marker_present():
    d = tempfile.mkdtemp()
    open(os.path.join(d, ".tunnel_named"), "w").close()
    cmd = sup.tunnel_cmd("balikpapan", repo_root=d)
    assert cmd == ["cloudflared", "tunnel", "run", "balikpapan"]
```

- [ ] **Step 2: Run test to verify it fails**

Run: `python3 -m pytest test_supervisor.py -k tunnel_cmd -v`
Expected: FAIL — `AttributeError: module 'supervisor' has no attribute 'tunnel_cmd'`

- [ ] **Step 3: Write minimal implementation**

In `supervisor.py`, add the region import after `import tempfile` (line 14):

```python
from region import get_region
```

Add `REGION` after the `PORT` line (line 19):

```python
REGION = get_region()
```

Add `tunnel_cmd` immediately after the `log()` function (after line 23), before `DEFAULT_SERVICES`:

```python
def tunnel_cmd(region, repo_root=REPO_ROOT):
    """Named tunnel (hostname stabil) bila wilayah sudah dikonfigurasi (penanda
    .tunnel_named ada), selain itu quick tunnel (Bontang legacy, URL berputar)."""
    if os.path.exists(os.path.join(repo_root, ".tunnel_named")):
        return ["cloudflared", "tunnel", "run", region]
    return ["cloudflared", "tunnel", "--url", f"http://localhost:{PORT}"]
```

Change the `tunnel` entry in `DEFAULT_SERVICES` (line 31) from:

```python
    {"name": "tunnel", "cmd": ["cloudflared", "tunnel", "--url", f"http://localhost:{PORT}"],
     "restart_on_update": False, "lock_file": None},
```

to:

```python
    {"name": "tunnel", "cmd": tunnel_cmd(REGION),
     "restart_on_update": False, "lock_file": None},
```

- [ ] **Step 4: Run test to verify it passes**

Run: `python3 -m pytest test_supervisor.py -v`
Expected: PASS (19 tests — 17 existing + 2 new)

- [ ] **Step 5: Commit**

```bash
git add supervisor.py test_supervisor.py
git commit -m "feat(supervisor): region-aware tunnel_cmd — named tunnel when configured"
```

---

### Task 2: `setup_region.py` onboarding script

**Files:**
- Create: `setup_region.py`
- Test: `test_setup_region.py`

**Interfaces:**
- Consumes: —
- Produces:
  - `PORT: int` (`= int(os.getenv("PLN_API_PORT","8900"))`), `REPO_ROOT`, `CLOUDFLARED_DIR = os.path.expanduser("~/.cloudflared")`.
  - `_config_yml(region, hostname, port) -> str`.
  - `write_region_config(region, domain, repo_root, cloudflared_dir, named_ok, port=PORT) -> dict` — menulis `.region`, `house_photos/<region>/`, `pln_url_<region>.txt`, `<cloudflared_dir>/config.yml`; touch `.tunnel_named` HANYA bila `named_ok`. Return dict path.
  - `run_cloudflared(region, hostname) -> bool`.
  - `main(argv=None) -> int`.

- [ ] **Step 1: Write the failing test**

```python
# test_setup_region.py
import os, tempfile
import setup_region as sr


def test_write_region_config_named_writes_all_and_marker():
    repo = tempfile.mkdtemp(); cfd = tempfile.mkdtemp()
    w = sr.write_region_config("balikpapan", "contoh.com", repo, cfd, named_ok=True, port=8900)
    assert open(os.path.join(repo, ".region")).read().strip() == "balikpapan"
    assert open(os.path.join(repo, "pln_url_balikpapan.txt")).read().strip() == "https://balikpapan.contoh.com"
    assert os.path.isdir(os.path.join(repo, "house_photos", "balikpapan"))
    cfg = open(os.path.join(cfd, "config.yml")).read()
    assert "balikpapan.contoh.com" in cfg
    assert "http://localhost:8900" in cfg
    assert os.path.exists(os.path.join(repo, ".tunnel_named"))
    assert "marker" in w


def test_write_region_config_not_named_skips_marker():
    repo = tempfile.mkdtemp(); cfd = tempfile.mkdtemp()
    w = sr.write_region_config("wahau", "contoh.com", repo, cfd, named_ok=False, port=8900)
    assert os.path.exists(os.path.join(repo, "pln_url_wahau.txt"))  # file lain tetap ditulis
    assert not os.path.exists(os.path.join(repo, ".tunnel_named"))  # penanda TIDAK dibuat
    assert "marker" not in w


def test_run_cloudflared_absent_returns_false(monkeypatch):
    monkeypatch.setattr("shutil.which", lambda name: None)
    assert sr.run_cloudflared("balikpapan", "balikpapan.contoh.com") is False
```

- [ ] **Step 2: Run test to verify it fails**

Run: `python3 -m pytest test_setup_region.py -v`
Expected: FAIL — `ModuleNotFoundError: No module named 'setup_region'`

- [ ] **Step 3: Write minimal implementation**

```python
# setup_region.py
#!/usr/bin/env python3
"""Onboarding wilayah baru: tulis config region + siapkan named tunnel Cloudflare.

Usage: python3 setup_region.py <region> <domain>
Prasyarat: cloudflared terpasang + `cloudflared tunnel login` sudah dilakukan.
"""
import os
import sys
import shutil
import subprocess

REPO_ROOT = os.path.dirname(os.path.abspath(__file__))
PORT = int(os.getenv("PLN_API_PORT", "8900"))
CLOUDFLARED_DIR = os.path.expanduser("~/.cloudflared")


def _config_yml(region, hostname, port):
    return (
        f"tunnel: {region}\n"
        f"ingress:\n"
        f"  - hostname: {hostname}\n"
        f"    service: http://localhost:{port}\n"
        f"  - service: http_status:404\n"
    )


def write_region_config(region, domain, repo_root, cloudflared_dir, named_ok, port=PORT):
    hostname = f"{region}.{domain}"
    written = {}

    p = os.path.join(repo_root, ".region")
    with open(p, "w") as f:
        f.write(region + "\n")
    written["region"] = p

    pd = os.path.join(repo_root, "house_photos", region)
    os.makedirs(pd, exist_ok=True)
    written["photo_dir"] = pd

    up = os.path.join(repo_root, f"pln_url_{region}.txt")
    with open(up, "w") as f:
        f.write(f"https://{hostname}\n")
    written["pln_url"] = up

    os.makedirs(cloudflared_dir, exist_ok=True)
    cp = os.path.join(cloudflared_dir, "config.yml")
    with open(cp, "w") as f:
        f.write(_config_yml(region, hostname, port))
    written["config"] = cp

    if named_ok:
        mp = os.path.join(repo_root, ".tunnel_named")
        open(mp, "w").close()
        written["marker"] = mp

    return written


def run_cloudflared(region, hostname):
    """Create named tunnel + route DNS. False bila cloudflared tak ada / gagal (tidak raise)."""
    if not shutil.which("cloudflared"):
        print("⚠️  cloudflared tidak terpasang / tidak di PATH.")
        print("   Install cloudflared + jalankan `cloudflared tunnel login`, lalu jalankan ulang.")
        return False
    try:
        r = subprocess.run(["cloudflared", "tunnel", "create", region],
                           capture_output=True, text=True)
        out = (r.stdout + r.stderr).lower()
        if r.returncode != 0 and "already exists" not in out:
            print(f"⚠️  gagal create tunnel: {(r.stderr or r.stdout).strip()[:200]}")
            return False
        r2 = subprocess.run(["cloudflared", "tunnel", "route", "dns", region, hostname],
                            capture_output=True, text=True)
        out2 = (r2.stdout + r2.stderr).lower()
        if r2.returncode != 0 and "already exists" not in out2:
            print(f"⚠️  gagal route dns: {(r2.stderr or r2.stdout).strip()[:200]}")
            return False
        return True
    except Exception as e:
        print(f"⚠️  cloudflared error: {e}")
        return False


def main(argv=None):
    argv = argv if argv is not None else sys.argv[1:]
    if len(argv) < 2:
        print("Usage: python3 setup_region.py <region> <domain>")
        return 1
    region = argv[0].strip().lower()
    domain = argv[1].strip().lower()
    hostname = f"{region}.{domain}"
    named_ok = run_cloudflared(region, hostname)
    written = write_region_config(region, domain, REPO_ROOT, CLOUDFLARED_DIR, named_ok)
    print(f"✅ Region '{region}' dikonfigurasi:")
    for k, v in written.items():
        print(f"   {k}: {v}")
    print("\nLangkah lanjut:")
    print(f"  1. Set PLN_API_KEYS (key wilayah {region}) di .env")
    print(f"  2. git add pln_url_{region}.txt && git commit -m 'add {region} url' && git push")
    print("  3. python3 supervisor.py")
    if not named_ok:
        print("\n⚠️  Named tunnel BELUM aktif — server pakai quick tunnel dulu.")
        print("   Setelah `cloudflared tunnel login`, jalankan ulang script ini untuk aktifkan named tunnel.")
    return 0


if __name__ == "__main__":
    sys.exit(main())
```

- [ ] **Step 4: Run test to verify it passes**

Run: `python3 -m pytest test_setup_region.py -v`
Expected: PASS (3 tests)

- [ ] **Step 5: Commit**

```bash
git add setup_region.py test_setup_region.py
git commit -m "feat(region): setup_region.py — onboard wilayah + named tunnel config"
```

---

### Task 3: Runbook + wrapper + gitignore

**Files:**
- Create: `docs/runbook-rollout-wilayah.md`
- Modify: `petugas_client/install_commands.sh` (tambah wrapper `fasih-setup-region` + baris bantuan), `.gitignore` (+`.tunnel_named`)

**Interfaces:**
- Consumes: `setup_region.py` (Task 2), `supervisor.py` (Task 1).
- Produces: —

- [ ] **Step 1: Add `.tunnel_named` to .gitignore**

Tambah baris ke `.gitignore` (bila belum ada):

```
.tunnel_named
```

- [ ] **Step 2: Add the fasih-setup-region wrapper**

In `petugas_client/install_commands.sh`, add a new wrapper alongside the others (e.g. after the `fasih-update` block), before the `chmod +x` line:

```bash
# fasih-setup-region: onboard wilayah baru (named tunnel)
cat > "$BIN/fasih-setup-region" << EOF
#!/bin/bash
cd "$REPO"
python3 setup_region.py "\$@"
EOF
```

Add a help line in the final echo block (after the `fasih-status` line):

```bash
echo "  fasih-setup-region bpp domain.com  Onboard wilayah baru (named tunnel)"
```

- [ ] **Step 3: Write the runbook**

Create `docs/runbook-rollout-wilayah.md`:

```markdown
# Runbook — Rollout Wilayah Baru (named tunnel)

Onboard satu wilayah (mis. `balikpapan`) sebagai server penuh dengan hostname stabil.

## Prasyarat (one-time, seluruh proyek)

1. Punya domain, tambahkan ke Cloudflare, tunggu zone **Active**.
   (`<region>.<domain>` akan jadi hostname tiap wilayah, mis. `balikpapan.pln-fasih.com`.)

## Per mesin wilayah

1. **Install cloudflared** dan login ke akun Cloudflare:

       cloudflared tunnel login

   (Membuka browser; pilih domain yang tadi diaktifkan.)

2. **Jalankan onboarding** (dari root repo, setelah `git pull`):

       python3 setup_region.py balikpapan pln-fasih.com
       # atau: fasih-setup-region balikpapan pln-fasih.com

   Script akan: `cloudflared tunnel create balikpapan` + `route dns` →
   tulis `.region`, `house_photos/balikpapan/`, `pln_url_balikpapan.txt`
   (= `https://balikpapan.pln-fasih.com`), `~/.cloudflared/config.yml`, dan
   penanda `.tunnel_named` (hanya bila cloudflared sukses).

   > Bila cloudflared belum siap/login, script tetap menulis file region tapi
   > TIDAK membuat `.tunnel_named` → server jalan pakai quick tunnel dulu.
   > Login lalu jalankan ulang script untuk mengaktifkan named tunnel.

3. **Set API key wilayah** di `.env`:

       PLN_API_KEYS=<key-balikpapan>

4. **Commit URL wilayah** agar petugas Balikpapan bisa `git pull`:

       git add pln_url_balikpapan.txt && git commit -m "add balikpapan url" && git push

5. **Jalankan stack:**

       python3 supervisor.py

   (Deploy HARUS lewat `python3 server.py` di supervisor, BUKAN gunicorn —
   `load_photos()` hanya jalan di `__main__`.)

## Verifikasi

    curl https://balikpapan.pln-fasih.com/health

Harus menampilkan `"region": "balikpapan"` dan jumlah foto > 0.

## Petugas wilayah

Di HP petugas Balikpapan: `echo balikpapan > .region`, lalu `fasih-update`
(git pull). `fasih-status` menampilkan `🌏 Wilayah: balikpapan` + region server
dari `/health`. Petugas otomatis pakai `pln_url_balikpapan.txt`.

> Bila `cloudflared tunnel run` mengeluh soal credentials, tambahkan baris
> `credentials-file: ~/.cloudflared/<UUID>.json` ke `~/.cloudflared/config.yml`
> (UUID dari output `cloudflared tunnel create`).
```

- [ ] **Step 4: Verify wiring**

Run: `python3 -c "import setup_region; print('setup_region OK'); import supervisor; print('tunnel default:', supervisor.tunnel_cmd('bontang')[2])"`
Expected: prints `setup_region OK` and `tunnel default: --url` (no `.tunnel_named` present → quick tunnel).

- [ ] **Step 5: Full test run + commit**

Run: `python3 -m pytest test_supervisor.py test_setup_region.py test_region.py -v`
Expected: PASS (19 + 3 + 4 = 26)

```bash
git add docs/runbook-rollout-wilayah.md petugas_client/install_commands.sh .gitignore
git commit -m "docs(region): rollout runbook + fasih-setup-region wrapper + gitignore .tunnel_named"
```

---

## Self-Review

**Spec coverage:**
- Supervisor `tunnel_cmd` region-aware (named bila `.tunnel_named`, else quick) → Task 1. ✓
- `REGION` import di supervisor (stdlib-only) → Task 1. ✓
- `setup_region.py` `write_region_config` (.region/foto/pln_url/config.yml, marker gated on named_ok) → Task 2. ✓
- `run_cloudflared` (skip bila cloudflared absen, tidak raise) → Task 2. ✓
- `main` cetak langkah lanjut + peringatan named_ok False → Task 2. ✓
- Runbook (prereq domain + login + per-mesin + verifikasi + catatan gunicorn) → Task 3. ✓
- Wrapper `fasih-setup-region` → Task 3. ✓
- `.gitignore` +`.tunnel_named` → Task 3. ✓
- Backward-compat Bontang (tanpa marker → quick) → Task 1 (default) + verify Task 3 Step 4. ✓
- Test tanpa jaringan/cloudflared (shutil.which mock) → Task 2 test 3. ✓

**Placeholder scan:** tidak ada TBD/TODO; semua step berisi kode/perintah nyata. ✓

**Type consistency:** `tunnel_cmd(region, repo_root)`, `REGION`, `write_region_config(region, domain, repo_root, cloudflared_dir, named_ok, port)`, `run_cloudflared(region, hostname)`, `main(argv)`, `_config_yml(region, hostname, port)` — konsisten lintas task + test. Penanda `.tunnel_named` seragam. `PORT` default 8900 seragam supervisor + setup_region. ✓
```
