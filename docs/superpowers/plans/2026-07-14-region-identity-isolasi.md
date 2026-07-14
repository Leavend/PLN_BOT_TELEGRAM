# Region Identity + Isolasi Akses Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Tiap mesin wilayah punya identitas region sendiri; PLN server menyajikan hanya foto wilayahnya dan melaporkan region-nya, dan petugas otomatis memakai URL server wilayahnya — sehingga akses per wilayah terpisah lewat deployment.

**Architecture:** Satu helper `region.py` (`get_region()`) jadi sumber tunggal identitas wilayah (env `FASIH_REGION` > file `.region` > default `bontang`), dipakai bersama oleh `pln_api_server/server.py` (scope folder foto + field `/health`) dan `petugas_client/batch_submit.py` (resolve `pln_url_<region>.txt`). Migrasi memindahkan foto Bontang ke `house_photos/bontang/`. Semua backward-compatible: tanpa `.region`, default `bontang` menjaga mesin lama tetap jalan.

**Tech Stack:** Python 3 stdlib, Flask (server, sudah terpasang), pytest 9.x.

## Global Constraints

- Python 3, **stdlib only** untuk `region.py` (tanpa dependency baru).
- `get_region()` prioritas: env `FASIH_REGION` > isi file `.region` (repo root) > default `"bontang"`. Normalisasi `strip().lower()`; abaikan baris kosong / komentar `#`.
- Isolasi = **deployment-only**. Server TIDAK memeriksa/menolak region idpel pada lookup.
- Foto tetap git-tracked; server hanya menyajikan `house_photos/<region>/`.
- Backward-compatible: default `bontang`, `pln_url.txt` dipertahankan sebagai fallback.
- Test framework = `pytest`. Test **tidak boleh** memanggil jaringan (AP2T/BPS/cloudflared) atau butuh cloudflared.
- Base dir server & petugas = repo root: `os.path.dirname(os.path.dirname(os.path.abspath(__file__)))` (server di subdir) / `REPO_ROOT` (petugas).

---

### Task 1: `region.py` — helper identitas wilayah

**Files:**
- Create: `region.py`
- Test: `test_region.py`

**Interfaces:**
- Consumes: —
- Produces:
  - `DEFAULT_REGION = "bontang"`
  - `get_region(repo_root=<region.py dir>) -> str` — env `FASIH_REGION` > baris valid pertama file `.region` di `repo_root` > `DEFAULT_REGION`; hasil `strip().lower()`.

- [ ] **Step 1: Write the failing test**

```python
# test_region.py
import os, tempfile
import region as reg


def test_default_when_no_env_no_file(monkeypatch):
    monkeypatch.delenv("FASIH_REGION", raising=False)
    d = tempfile.mkdtemp()  # tidak ada .region di sini
    assert reg.get_region(d) == "bontang"


def test_env_wins_over_file(monkeypatch):
    d = tempfile.mkdtemp()
    with open(os.path.join(d, ".region"), "w") as f:
        f.write("balikpapan\n")
    monkeypatch.setenv("FASIH_REGION", "Samarinda")
    assert reg.get_region(d) == "samarinda"  # env menang + di-lowercase


def test_reads_and_normalizes_file(monkeypatch):
    monkeypatch.delenv("FASIH_REGION", raising=False)
    d = tempfile.mkdtemp()
    with open(os.path.join(d, ".region"), "w") as f:
        f.write("  Balikpapan\n")
    assert reg.get_region(d) == "balikpapan"


def test_ignores_comments_and_blank_lines(monkeypatch):
    monkeypatch.delenv("FASIH_REGION", raising=False)
    d = tempfile.mkdtemp()
    with open(os.path.join(d, ".region"), "w") as f:
        f.write("# ini komentar\n\nwahau\n")
    assert reg.get_region(d) == "wahau"
```

- [ ] **Step 2: Run test to verify it fails**

Run: `python3 -m pytest test_region.py -v`
Expected: FAIL with `ModuleNotFoundError: No module named 'region'`

- [ ] **Step 3: Write minimal implementation**

```python
# region.py
"""Sumber tunggal identitas wilayah untuk satu mesin.

Urutan: env FASIH_REGION > file .region (repo root, gitignored) > default 'bontang'.
Dipakai bersama oleh pln_api_server/server.py dan petugas_client/batch_submit.py.
"""
import os

_REPO = os.path.dirname(os.path.abspath(__file__))
DEFAULT_REGION = "bontang"


def get_region(repo_root=_REPO):
    env = os.getenv("FASIH_REGION")
    if env and env.strip():
        return env.strip().lower()
    try:
        with open(os.path.join(repo_root, ".region")) as f:
            for line in f:
                s = line.strip()
                if s and not s.startswith("#"):
                    return s.lower()
    except OSError:
        pass
    return DEFAULT_REGION
```

- [ ] **Step 4: Run test to verify it passes**

Run: `python3 -m pytest test_region.py -v`
Expected: PASS (4 tests)

- [ ] **Step 5: Commit**

```bash
git add region.py test_region.py
git commit -m "feat(region): get_region helper — env/.region/default bontang"
```

---

### Task 2: Server region-aware (foto per-wilayah + /health region)

**Files:**
- Modify: `pln_api_server/server.py` (imports ~27; `PHOTO_DIRS` block lines 41-44; `/health` handler ~177-184)
- Test: `test_server_region.py`

**Interfaces:**
- Consumes: `region.get_region` (Task 1).
- Produces:
  - `REGION: str` (module-level, `= get_region()`).
  - `get_photo_dirs(region) -> list[str]` — `[<repo>/house_photos/<region>]`.
  - `PHOTO_DIRS = get_photo_dirs(REGION)`.
  - `/health` JSON menambah `"region": REGION`.

- [ ] **Step 1: Write the failing test**

```python
# test_server_region.py
import os, sys, tempfile

# server.py melakukan sys.path.insert(repo_root) sendiri; kita hanya perlu bisa import 'server'.
sys.path.insert(0, os.path.join(os.path.dirname(os.path.abspath(__file__)), "pln_api_server"))
import server  # noqa: E402


def test_get_photo_dirs_scopes_to_region():
    dirs = server.get_photo_dirs("balikpapan")
    assert len(dirs) == 1
    assert dirs[0].endswith(os.path.join("house_photos", "balikpapan"))


def test_health_includes_region():
    c = server.app.test_client()
    r = c.get("/health")
    assert r.status_code == 200
    assert r.get_json().get("region") == server.REGION


def test_load_photos_only_reads_configured_dirs(monkeypatch):
    d = tempfile.mkdtemp()
    with open(os.path.join(d, "x.webp"), "wb") as f:
        f.write(b"\x00")
    monkeypatch.setattr(server, "PHOTO_DIRS", [d])
    server.load_photos()
    assert len(server._photo_list) == 1
    assert server._photo_list[0].endswith("x.webp")
```

- [ ] **Step 2: Run test to verify it fails**

Run: `python3 -m pytest test_server_region.py -v`
Expected: FAIL — `AttributeError: module 'server' has no attribute 'get_photo_dirs'` (dan `/health` belum punya `region`).

- [ ] **Step 3: Write minimal implementation**

In `pln_api_server/server.py`, add the region import right after the Flask import (the `sys.path.insert` at line 25 already put repo root on the path):

```python
from region import get_region
```

Replace the `PHOTO_DIRS` block (lines 41-44):

```python
PHOTO_DIRS = [
    os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "house_photos"),
    os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), "FOTORUMAH_PAK_ANWAR"),
]
```

with:

```python
REPO = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
REGION = get_region()


def get_photo_dirs(region):
    return [os.path.join(REPO, "house_photos", region)]


PHOTO_DIRS = get_photo_dirs(REGION)
```

In the `/health` handler (currently returns a dict with `"photos"`), add the region field. Change:

```python
    return jsonify({
        "status": "ok",
        "photos": len(_photo_list),
```

to include region as the first field:

```python
    return jsonify({
        "status": "ok",
        "region": REGION,
        "photos": len(_photo_list),
```

(Keep the rest of the health dict unchanged.)

- [ ] **Step 4: Run test to verify it passes**

Run: `python3 -m pytest test_server_region.py -v`
Expected: PASS (3 tests)

- [ ] **Step 5: Commit**

```bash
git add pln_api_server/server.py test_server_region.py
git commit -m "feat(server): region-aware photo dir + /health region field"
```

---

### Task 3: Petugas resolve URL per-wilayah

**Files:**
- Modify: `petugas_client/batch_submit.py` (imports near top ~line 34-52; `_resolve_pln_url` lines 68-82)
- Test: `test_petugas_url.py`

**Interfaces:**
- Consumes: `region.get_region` (Task 1).
- Produces:
  - `_resolve_pln_url(repo_root=REPO_ROOT, region=None) -> str` — baca `pln_url_<region>.txt` > `pln_url.txt` > env `PLN_API_URL`; region default `get_region()`; buang trailing `/`.

- [ ] **Step 1: Write the failing test**

```python
# test_petugas_url.py
import os, tempfile
from petugas_client import batch_submit as bs


def test_prefers_region_file():
    d = tempfile.mkdtemp()
    with open(os.path.join(d, "pln_url_balikpapan.txt"), "w") as f:
        f.write("https://bpp.example\n")
    with open(os.path.join(d, "pln_url.txt"), "w") as f:
        f.write("https://legacy.example\n")
    assert bs._resolve_pln_url(repo_root=d, region="balikpapan") == "https://bpp.example"


def test_falls_back_to_plain_url_file(monkeypatch):
    d = tempfile.mkdtemp()
    with open(os.path.join(d, "pln_url.txt"), "w") as f:
        f.write("https://legacy.example/\n")  # trailing slash harus dibuang
    monkeypatch.delenv("PLN_API_URL", raising=False)
    assert bs._resolve_pln_url(repo_root=d, region="balikpapan") == "https://legacy.example"


def test_falls_back_to_env_when_no_files(monkeypatch):
    d = tempfile.mkdtemp()
    monkeypatch.setenv("PLN_API_URL", "https://env.example")
    assert bs._resolve_pln_url(repo_root=d, region="samarinda") == "https://env.example"
```

- [ ] **Step 2: Run test to verify it fails**

Run: `python3 -m pytest test_petugas_url.py -v`
Expected: FAIL — `TypeError: _resolve_pln_url() got an unexpected keyword argument 'repo_root'`.

- [ ] **Step 3: Write minimal implementation**

In `petugas_client/batch_submit.py`, add the region import alongside the other repo-root imports (near the `from submit_fasih import ...` block, ~line 48):

```python
from region import get_region
```

Replace `_resolve_pln_url` (lines 68-82) with:

```python
def _resolve_pln_url(repo_root=REPO_ROOT, region=None) -> str:
    """PLN API URL source of truth = git-tracked pln_url_<region>.txt (propagated via
    `fasih-update`). Per-wilayah: tiap server punya tunnel URL sendiri. Fallback ke
    pln_url.txt (legacy, satu URL) lalu env PLN_API_URL. Region default = get_region()."""
    region = region or get_region()
    for fname in (f"pln_url_{region}.txt", "pln_url.txt"):
        try:
            with open(os.path.join(repo_root, fname)) as f:
                for line in f:
                    line = line.strip()
                    if line and not line.startswith("#"):
                        return line.rstrip("/")
        except OSError:
            pass
    return os.getenv("PLN_API_URL", "").rstrip("/")
```

(The module-level `PLN_API_URL = _resolve_pln_url()` call just below stays as-is — it now resolves per region.)

- [ ] **Step 4: Run test to verify it passes**

Run: `python3 -m pytest test_petugas_url.py -v`
Expected: PASS (3 tests)

- [ ] **Step 5: Commit**

```bash
git add petugas_client/batch_submit.py test_petugas_url.py
git commit -m "feat(petugas): resolve pln_url_<region>.txt (fallback pln_url.txt)"
```

---

### Task 4: Migrasi Bontang + wiring (gitignore, .region.example, fasih-status)

**Files:**
- Move: `house_photos/*.webp` + `FOTORUMAH_PAK_ANWAR/*` → `house_photos/bontang/`
- Create: `pln_url_bontang.txt`, `.region.example`
- Modify: `.gitignore`, `petugas_client/install_commands.sh` (fasih-status block ~250-299), `telegram_bot.py` (`get_random_house_photo` ~314)

**Interfaces:**
- Consumes: `region.py`, server (Task 2), petugas (Task 3).
- Produces: —

- [ ] **Step 1: Migrate Bontang photos into region subdir**

```bash
mkdir -p house_photos/bontang
git mv house_photos/*.webp house_photos/bontang/
# FOTORUMAH_PAK_ANWAR juga milik Bontang
git mv FOTORUMAH_PAK_ANWAR/* house_photos/bontang/ 2>/dev/null || true
```

Verify no stray photos remain at the flat level:

Run: `ls house_photos/*.webp 2>/dev/null | wc -l | tr -d ' '`
Expected: `0` (semua sudah pindah ke `house_photos/bontang/`)

- [ ] **Step 2: Verify server loads the migrated photos for region bontang**

Run:
```bash
python3 -c "import sys, os; sys.path.insert(0, os.path.join(os.getcwd(),'pln_api_server')); import server; server.PHOTO_DIRS = server.get_photo_dirs('bontang'); server.load_photos(); print(len(server._photo_list))"
```
Expected: a non-zero count (≈555) — the migrated Bontang photos are found under `house_photos/bontang/`.

- [ ] **Step 3: Create per-region URL file + region example**

```bash
cp pln_url.txt pln_url_bontang.txt
printf 'bontang\n' > .region.example
```

(`pln_url.txt` is kept as the legacy fallback — do not delete it.)

- [ ] **Step 4: Ignore the machine-local `.region`**

Add to `.gitignore` (only if not already present):

```
.region
```

- [ ] **Step 5: Show region in fasih-status**

In `petugas_client/install_commands.sh`, inside the `fasih-status` python block, add a region line. Find the line that prints the PLN API URL:

```python
    print('📡 PLN API:', url or '❌ NOT SET')
```

and insert immediately before it:

```python
    from region import get_region
    print('🌏 Wilayah:', get_region())
```

- [ ] **Step 6: Make telegram_bot photo lookup region-aware**

`telegram_bot.py`'s `get_random_house_photo()` reads from the flat dirs that Step 1 just emptied, so it must point at the region subdir too. `telegram_bot.py` is at repo root, so `from region import get_region` resolves directly. Add the import near the top of `telegram_bot.py` (with the other stdlib imports, after `import os`):

```python
from region import get_region
```

Then, inside `get_random_house_photo()`, replace:

```python
    dirs_to_check = ["house_photos", "FOTORUMAH_PAK_ANWAR"]
```

with:

```python
    dirs_to_check = [os.path.join("house_photos", get_region())]
```

Verify it resolves to a non-empty photo path after migration:

Run: `python3 -c "import telegram_bot; p = telegram_bot.get_random_house_photo(); print(p)"`
Expected: a path under `house_photos/bontang/` (not `None`, since the migrated photos live there).

- [ ] **Step 7: Commit**

```bash
git add house_photos/bontang pln_url_bontang.txt .region.example .gitignore petugas_client/install_commands.sh telegram_bot.py
git add -A house_photos FOTORUMAH_PAK_ANWAR
git commit -m "chore(region): migrate Bontang photos to house_photos/bontang, per-region url, telegram_bot + fasih-status region"
```

- [ ] **Step 8: Full test run**

Run: `python3 -m pytest test_region.py test_server_region.py test_petugas_url.py -v`
Expected: PASS (10 tests: 4 + 3 + 3)

---

## Self-Review

**Spec coverage:**
- `region.py` `get_region()` env>file>default + normalisasi → Task 1. ✓
- Server `PHOTO_DIRS = house_photos/<region>/` → Task 2 (`get_photo_dirs`). ✓
- `/health` field region → Task 2. ✓
- API keys tetap env (tanpa perubahan) → tidak butuh task (dokumen di spec). ✓
- Petugas `_resolve_pln_url` baca `pln_url_<region>.txt` fallback → Task 3. ✓
- `fasih-status` tampilkan region → Task 4 Step 5. ✓
- Migrasi foto Bontang → `house_photos/bontang/` → Task 4 Step 1-2. ✓
- `pln_url.txt` → `pln_url_bontang.txt` (pln_url.txt dipertahankan) → Task 4 Step 3. ✓
- `.gitignore` +`.region`, `.region.example` → Task 4 Step 3-4. ✓
- Backward-compatible default bontang → Task 1 (default) + fallback pln_url.txt (Task 3). ✓
- Error handling: house_photos/<region>/ hilang → load_photos 0 foto (loop existing, tidak crash); pln_url_<region>.txt hilang → fallback. Terpenuhi oleh implementasi Task 2/3. ✓

**Placeholder scan:** tidak ada TBD/TODO; semua step berisi kode/perintah nyata. ✓

**Type consistency:** `get_region(repo_root)`, `get_photo_dirs(region)`, `REGION`, `PHOTO_DIRS`, `_resolve_pln_url(repo_root, region)` — nama & signature konsisten lintas task dan test. Default region `"bontang"` seragam. ✓
