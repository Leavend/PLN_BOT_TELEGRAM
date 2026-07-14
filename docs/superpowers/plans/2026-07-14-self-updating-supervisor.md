# Self-Updating Supervisor Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Satu proses `supervisor.py` per mesin yang menjalankan full stack (pln_server + telegram_bot + cloudflared tunnel), auto-pull dari GitHub lalu restart service saat ada update, dan auto-recover proses yang mati.

**Architecture:** Supervisor Python memegang handle `subprocess.Popen` tiap service (kill lintas-OS via process-group, tanpa PID-hunting). Logika keputusan update dipisah jadi fungsi murni `decide()` yang mudah dites; git-poll + lock-check + recovery diorkestrasi di kelas `Supervisor`. Tunnel sengaja tidak di-restart saat update supaya URL trycloudflare tidak berganti.

**Tech Stack:** Python 3 stdlib (`subprocess`, `os`, `signal`, `json`, `time`), pytest 9.x untuk test.

## Global Constraints

- Python 3, **stdlib only** untuk `supervisor.py` (tidak menambah dependency baru).
- Harus jalan di **Termux (Android), Windows (Git Bash), Linux VPS** — semua branching OS lewat `os.name`.
- Branch git = `main`. Interval poll default **15** detik. Port default `PLN_API_PORT` = **8900**.
- Tunnel (`restart_on_update=False`) **tidak pernah** di-restart saat code update — hanya auto-recover kalau mati.
- Lock file batch = `bot_active_runs.lock` (sudah dipakai `telegram_bot.py`).
- Test framework = `pytest` (sudah terpasang). Test **tidak boleh** butuh `cloudflared` atau jaringan — pakai dummy proses `python -c "..."`.
- Semua log user-facing boleh Bahasa Indonesia (ikut gaya repo).

---

### Task 1: Service config loader

**Files:**
- Create: `supervisor.py`
- Test: `test_supervisor.py`

**Interfaces:**
- Consumes: —
- Produces:
  - `DEFAULT_SERVICES: list[dict]` — tiap dict `{"name": str, "cmd": list[str], "restart_on_update": bool, "lock_file": str|None}`.
  - `load_services(repo_root=REPO_ROOT) -> list[dict]` — baca `services.local.json` kalau ada & valid (list non-kosong), selain itu `DEFAULT_SERVICES`.
  - `REPO_ROOT: str`, `BRANCH="main"`, `INTERVAL=15`, `PORT: int`.
  - `log(msg: str) -> None`.

- [ ] **Step 1: Write the failing test**

```python
# test_supervisor.py
import json, os, tempfile
import supervisor as sup


def test_load_services_default_when_no_file():
    d = tempfile.mkdtemp()
    assert sup.load_services(d) == sup.DEFAULT_SERVICES


def test_load_services_reads_local_override():
    d = tempfile.mkdtemp()
    custom = [{"name": "only_server", "cmd": ["python", "x.py"],
               "restart_on_update": True, "lock_file": None}]
    with open(os.path.join(d, "services.local.json"), "w") as f:
        json.dump(custom, f)
    assert sup.load_services(d) == custom


def test_load_services_falls_back_on_invalid_json():
    d = tempfile.mkdtemp()
    with open(os.path.join(d, "services.local.json"), "w") as f:
        f.write("{ not json")
    assert sup.load_services(d) == sup.DEFAULT_SERVICES


def test_default_services_shape():
    names = {s["name"] for s in sup.DEFAULT_SERVICES}
    assert names == {"pln_server", "telegram_bot", "tunnel"}
    bot = next(s for s in sup.DEFAULT_SERVICES if s["name"] == "telegram_bot")
    assert bot["lock_file"] == "bot_active_runs.lock"
    tun = next(s for s in sup.DEFAULT_SERVICES if s["name"] == "tunnel")
    assert tun["restart_on_update"] is False
```

- [ ] **Step 2: Run test to verify it fails**

Run: `pytest test_supervisor.py -v`
Expected: FAIL with `ModuleNotFoundError: No module named 'supervisor'`

- [ ] **Step 3: Write minimal implementation**

```python
#!/usr/bin/env python3
"""Self-updating supervisor: jalankan full stack, auto git-pull + restart, auto-recover.

Ganti auto_updater.py / auto_updater.sh (deprecated). Jalankan:
    python3 supervisor.py            # supervise full stack
    python3 supervisor.py --once     # self-check (recovery + stop bersih), lalu exit
"""
import os
import sys
import json
import time
import signal
import subprocess

REPO_ROOT = os.path.dirname(os.path.abspath(__file__))
BRANCH = "main"
INTERVAL = 15
PORT = int(os.getenv("PLN_API_PORT", "8900"))


def log(msg):
    print(f"[{time.strftime('%Y-%m-%d %H:%M:%S')}] {msg}", flush=True)


DEFAULT_SERVICES = [
    {"name": "pln_server", "cmd": [sys.executable, "pln_api_server/server.py"],
     "restart_on_update": True, "lock_file": None},
    {"name": "telegram_bot", "cmd": [sys.executable, "telegram_bot.py"],
     "restart_on_update": True, "lock_file": "bot_active_runs.lock"},
    {"name": "tunnel", "cmd": ["cloudflared", "tunnel", "--url", f"http://localhost:{PORT}"],
     "restart_on_update": False, "lock_file": None},
]


def load_services(repo_root=REPO_ROOT):
    """services.local.json (list non-kosong) override DEFAULT_SERVICES; invalid → default."""
    path = os.path.join(repo_root, "services.local.json")
    try:
        with open(path) as f:
            data = json.load(f)
        if isinstance(data, list) and data:
            return data
        log("services.local.json bukan list non-kosong — pakai default")
    except FileNotFoundError:
        pass
    except Exception as e:
        log(f"services.local.json invalid ({e}) — pakai default")
    return DEFAULT_SERVICES
```

- [ ] **Step 4: Run test to verify it passes**

Run: `pytest test_supervisor.py -v`
Expected: PASS (4 tests)

- [ ] **Step 5: Commit**

```bash
git add supervisor.py test_supervisor.py
git commit -m "feat(supervisor): service config loader + defaults"
```

---

### Task 2: Cross-platform process helpers

**Files:**
- Modify: `supervisor.py`
- Test: `test_supervisor.py`

**Interfaces:**
- Consumes: `REPO_ROOT`, `log` (Task 1).
- Produces:
  - `spawn(cmd: list[str], logpath: str) -> subprocess.Popen` — start proses baru dalam process-group sendiri (POSIX `start_new_session`, Windows `CREATE_NEW_PROCESS_GROUP`), stdout+stderr → `logpath` (append).
  - `is_alive(proc) -> bool` — `proc is not None and proc.poll() is None`.
  - `kill(proc, timeout=5) -> None` — SIGTERM ke group → tunggu → SIGKILL; no-op kalau sudah mati/None.

- [ ] **Step 1: Write the failing test**

```python
# append to test_supervisor.py
import sys as _sys


def _dummy_cmd(seconds=30):
    return [_sys.executable, "-c", f"import time; time.sleep({seconds})"]


def test_spawn_is_alive_then_kill():
    d = tempfile.mkdtemp()
    p = sup.spawn(_dummy_cmd(), os.path.join(d, "dummy.log"))
    try:
        assert sup.is_alive(p) is True
    finally:
        sup.kill(p)
    assert sup.is_alive(p) is False


def test_kill_none_is_noop():
    sup.kill(None)  # tidak boleh raise


def test_spawn_writes_logfile():
    d = tempfile.mkdtemp()
    lp = os.path.join(d, "hello.log")
    p = sup.spawn([_sys.executable, "-c", "print('halo dari service')"], lp)
    p.wait(timeout=10)
    with open(lp) as f:
        assert "halo dari service" in f.read()
```

- [ ] **Step 2: Run test to verify it fails**

Run: `pytest test_supervisor.py -k "spawn or kill" -v`
Expected: FAIL with `AttributeError: module 'supervisor' has no attribute 'spawn'`

- [ ] **Step 3: Write minimal implementation**

```python
# add to supervisor.py (after load_services)

def spawn(cmd, logpath):
    """Start proses dalam process-group sendiri; output → logpath (append)."""
    logf = open(logpath, "ab")
    kw = {"stdout": logf, "stderr": subprocess.STDOUT, "cwd": REPO_ROOT}
    if os.name == "nt":
        kw["creationflags"] = subprocess.CREATE_NEW_PROCESS_GROUP
    else:
        kw["start_new_session"] = True  # setsid → bisa killpg anak-cucu
    return subprocess.Popen(cmd, **kw)


def is_alive(proc):
    return proc is not None and proc.poll() is None


def kill(proc, timeout=5):
    """SIGTERM ke process-group → tunggu → SIGKILL. No-op kalau sudah mati/None."""
    if proc is None or proc.poll() is not None:
        return
    try:
        if os.name == "nt":
            proc.terminate()
        else:
            os.killpg(os.getpgid(proc.pid), signal.SIGTERM)
    except Exception:
        try:
            proc.terminate()
        except Exception:
            pass
    try:
        proc.wait(timeout=timeout)
        return
    except subprocess.TimeoutExpired:
        pass
    try:
        if os.name == "nt":
            proc.kill()
        else:
            os.killpg(os.getpgid(proc.pid), signal.SIGKILL)
    except Exception:
        try:
            proc.kill()
        except Exception:
            pass
    try:
        proc.wait(timeout=timeout)
    except Exception:
        pass
```

- [ ] **Step 4: Run test to verify it passes**

Run: `pytest test_supervisor.py -v`
Expected: PASS (7 tests total)

- [ ] **Step 5: Commit**

```bash
git add supervisor.py test_supervisor.py
git commit -m "feat(supervisor): cross-platform spawn/is_alive/kill via process-group"
```

---

### Task 3: Git check, update decision, lock check

**Files:**
- Modify: `supervisor.py`
- Test: `test_supervisor.py`

**Interfaces:**
- Consumes: `REPO_ROOT`, `BRANCH`, `log` (Task 1).
- Produces:
  - `active_lock(services, repo_root=REPO_ROOT) -> str|None` — nama lock_file pertama yang ada di disk, else None.
  - `decide(local, remote, lock) -> str` — `"none"` (sama / hash kosong), `"defer"` (beda tapi lock aktif), `"update"` (beda & tidak ada lock). **Fungsi murni.**
  - `git_revisions(branch=BRANCH) -> tuple[str|None, str|None]` — `(local_hash, remote_hash)`; `(None, None)` kalau gagal (offline).
  - `git_pull(branch=BRANCH) -> bool` — True kalau exit 0.

- [ ] **Step 1: Write the failing test**

```python
# append to test_supervisor.py

def test_decide_matrix():
    assert sup.decide("aaa", "aaa", None) == "none"      # sama
    assert sup.decide(None, "bbb", None) == "none"        # gagal fetch
    assert sup.decide("aaa", None, None) == "none"
    assert sup.decide("aaa", "bbb", None) == "update"     # beda, bebas
    assert sup.decide("aaa", "bbb", "bot_active_runs.lock") == "defer"  # beda, terkunci


def test_active_lock_detects_existing_file():
    d = tempfile.mkdtemp()
    services = [
        {"name": "bot", "cmd": [], "restart_on_update": True, "lock_file": "bot_active_runs.lock"},
        {"name": "srv", "cmd": [], "restart_on_update": True, "lock_file": None},
    ]
    assert sup.active_lock(services, d) is None
    open(os.path.join(d, "bot_active_runs.lock"), "w").close()
    assert sup.active_lock(services, d) == "bot_active_runs.lock"


def test_git_revisions_returns_equal_hashes_in_this_repo():
    # dijalankan di dalam repo yang bersih & sinkron → dua hash 40-char yang sama-ada
    local, remote = sup.git_revisions()
    # boleh (None, None) kalau offline; kalau tidak, keduanya hash valid
    if local is not None and remote is not None:
        assert len(local) == 40 and len(remote) == 40
```

- [ ] **Step 2: Run test to verify it fails**

Run: `pytest test_supervisor.py -k "decide or active_lock or git_revisions" -v`
Expected: FAIL with `AttributeError: module 'supervisor' has no attribute 'decide'`

- [ ] **Step 3: Write minimal implementation**

```python
# add to supervisor.py

def active_lock(services, repo_root=REPO_ROOT):
    for s in services:
        lf = s.get("lock_file")
        if lf and os.path.exists(os.path.join(repo_root, lf)):
            return lf
    return None


def decide(local, remote, lock):
    """Pure: apa yang harus dilakukan siklus ini."""
    if not local or not remote or local == remote:
        return "none"
    return "defer" if lock else "update"


def git_revisions(branch=BRANCH):
    try:
        subprocess.run(["git", "fetch", "origin", branch], cwd=REPO_ROOT,
                       stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL, timeout=60)
        local = subprocess.check_output(["git", "rev-parse", "HEAD"], cwd=REPO_ROOT).decode().strip()
        remote = subprocess.check_output(["git", "rev-parse", f"origin/{branch}"], cwd=REPO_ROOT).decode().strip()
        return local, remote
    except Exception as e:
        log(f"git check gagal (offline?): {e}")
        return None, None


def git_pull(branch=BRANCH):
    try:
        r = subprocess.run(["git", "pull", "origin", branch], cwd=REPO_ROOT,
                           capture_output=True, text=True, timeout=120)
        log((r.stdout or r.stderr).strip())
        return r.returncode == 0
    except Exception as e:
        log(f"git pull gagal: {e}")
        return False
```

- [ ] **Step 4: Run test to verify it passes**

Run: `pytest test_supervisor.py -v`
Expected: PASS (10 tests total)

- [ ] **Step 5: Commit**

```bash
git add supervisor.py test_supervisor.py
git commit -m "feat(supervisor): git revisions + pure update decision + lock check"
```

---

### Task 4: Supervisor orchestrator

**Files:**
- Modify: `supervisor.py`
- Test: `test_supervisor.py`

**Interfaces:**
- Consumes: `spawn`, `is_alive`, `kill` (Task 2); `active_lock`, `decide`, `git_revisions`, `git_pull` (Task 3); `log`, `REPO_ROOT`, `BRANCH`, `INTERVAL` (Task 1).
- Produces:
  - `class Supervisor(services, branch=BRANCH, logdir=None)` dengan atribut `procs: dict[str, Popen|None]` dan method:
    - `start(svc)`, `stop(name)`, `start_all()`, `stop_all()`
    - `apply_update()` — restart tiap service `restart_on_update=True` (stop lalu start).
    - `recover()` — restart service yang `poll()` tidak None (mati).
    - `tick()` — satu siklus: `git_revisions` → `decide` → pull+`apply_update` / defer / none, lalu `recover()`.
    - `run()` — pasang signal handler, `start_all()`, loop `sleep(INTERVAL)`+`tick()`.

- [ ] **Step 1: Write the failing test**

```python
# append to test_supervisor.py

def _dummy_service(name, restart=False):
    return {"name": name, "cmd": _dummy_cmd(), "restart_on_update": restart, "lock_file": None}


def test_start_all_then_stop_all():
    s = sup.Supervisor([_dummy_service("a"), _dummy_service("b")], logdir=tempfile.mkdtemp())
    s.start_all()
    try:
        assert sup.is_alive(s.procs["a"]) and sup.is_alive(s.procs["b"])
    finally:
        s.stop_all()
    assert not sup.is_alive(s.procs["a"]) and not sup.is_alive(s.procs["b"])


def test_recover_restarts_dead_process_with_new_pid():
    s = sup.Supervisor([_dummy_service("a")], logdir=tempfile.mkdtemp())
    s.start_all()
    try:
        pid1 = s.procs["a"].pid
        sup.kill(s.procs["a"])            # simulasi crash
        s.recover()
        pid2 = s.procs["a"].pid
        assert pid1 != pid2
        assert sup.is_alive(s.procs["a"])
    finally:
        s.stop_all()


def test_apply_update_only_restarts_flagged_services():
    s = sup.Supervisor([_dummy_service("keep", restart=False),
                        _dummy_service("roll", restart=True)], logdir=tempfile.mkdtemp())
    s.start_all()
    try:
        keep_pid = s.procs["keep"].pid
        roll_pid = s.procs["roll"].pid
        s.apply_update()
        assert s.procs["keep"].pid == keep_pid   # tidak di-restart
        assert s.procs["roll"].pid != roll_pid    # di-restart
    finally:
        s.stop_all()
```

- [ ] **Step 2: Run test to verify it fails**

Run: `pytest test_supervisor.py -k "start_all or recover or apply_update" -v`
Expected: FAIL with `AttributeError: module 'supervisor' has no attribute 'Supervisor'`

- [ ] **Step 3: Write minimal implementation**

```python
# add to supervisor.py

class Supervisor:
    def __init__(self, services, branch=BRANCH, logdir=None):
        self.services = services
        self.branch = branch
        self.logdir = logdir or os.path.join(REPO_ROOT, "logs")
        os.makedirs(self.logdir, exist_ok=True)
        self.procs = {}

    def _logpath(self, name):
        return os.path.join(self.logdir, f"{name}.log")

    def start(self, svc):
        name = svc["name"]
        try:
            self.procs[name] = spawn(svc["cmd"], self._logpath(name))
            log(f"start {name} pid={self.procs[name].pid}")
        except Exception as e:
            log(f"gagal start {name}: {e}")
            self.procs[name] = None

    def stop(self, name):
        kill(self.procs.get(name))
        self.procs[name] = None

    def start_all(self):
        for s in self.services:
            self.start(s)

    def stop_all(self):
        for s in self.services:
            self.stop(s["name"])

    def apply_update(self):
        for s in self.services:
            if s.get("restart_on_update"):
                log(f"restart {s['name']} (update)")
                self.stop(s["name"])
                self.start(s)

    def recover(self):
        for s in self.services:
            p = self.procs.get(s["name"])
            if p is not None and p.poll() is not None:
                log(f"auto-recover {s['name']} (mati)")
                self.start(s)

    def tick(self):
        local, remote = git_revisions(self.branch)
        action = decide(local, remote, active_lock(self.services))
        if action == "defer":
            log("update tertunda — service ber-lock lagi aktif (batch)")
        elif action == "update":
            log("kode baru di remote — pull + restart")
            if git_pull(self.branch):
                self.apply_update()
        self.recover()

    def run(self):
        _install_signal_handlers(self)
        log(f"supervisor mulai — {len(self.services)} service, poll {INTERVAL}s")
        self.start_all()
        while True:
            time.sleep(INTERVAL)
            self.tick()
```

- [ ] **Step 4: Run test to verify it passes**

Run: `pytest test_supervisor.py -v`
Expected: PASS (13 tests total)

- [ ] **Step 5: Commit**

```bash
git add supervisor.py test_supervisor.py
git commit -m "feat(supervisor): Supervisor orchestrator — start/stop/recover/update tick"
```

---

### Task 5: CLI entry, --once self-check, signal handlers

**Files:**
- Modify: `supervisor.py`
- Test: `test_supervisor.py`

**Interfaces:**
- Consumes: `Supervisor` (Task 4); `spawn`, `is_alive`, `kill` (Task 2); `log` (Task 1).
- Produces:
  - `_install_signal_handlers(sup: Supervisor) -> None` — SIGINT/SIGTERM → `stop_all()` → exit 0.
  - `self_check() -> int` — start dummy, kill, recover, assert PID berubah & stop bersih; print `✅`; return 0 (raise/return 1 kalau gagal).
  - `main(argv=None) -> int` — `--once` → `self_check()`; selain itu `Supervisor(load_services()).run()`.
  - `if __name__ == "__main__": sys.exit(main())`.

- [ ] **Step 1: Write the failing test**

```python
# append to test_supervisor.py
import subprocess as _sp


def test_self_check_returns_zero():
    assert sup.self_check() == 0


def test_once_cli_exits_zero_and_prints_ok():
    r = _sp.run([_sys.executable, "supervisor.py", "--once"],
                cwd=os.path.dirname(os.path.abspath(sup.__file__)),
                capture_output=True, text=True, timeout=60)
    assert r.returncode == 0
    assert "✅" in r.stdout
```

- [ ] **Step 2: Run test to verify it fails**

Run: `pytest test_supervisor.py -k "self_check or once_cli" -v`
Expected: FAIL with `AttributeError: module 'supervisor' has no attribute 'self_check'`

- [ ] **Step 3: Write minimal implementation**

```python
# add to supervisor.py (before the __main__ block)
import tempfile


def _install_signal_handlers(sup_obj):
    def handler(signum, frame):
        log(f"sinyal {signum} — matikan semua service")
        sup_obj.stop_all()
        sys.exit(0)
    for sig in (signal.SIGINT, signal.SIGTERM):
        try:
            signal.signal(sig, handler)
        except Exception:
            pass  # SIGTERM tidak selalu ada di Windows


def self_check():
    """Start dummy → bunuh → recover → pastikan PID berganti & stop bersih."""
    dummy = {"name": "dummy",
             "cmd": [sys.executable, "-c", "import time; time.sleep(60)"],
             "restart_on_update": False, "lock_file": None}
    s = Supervisor([dummy], logdir=tempfile.mkdtemp())
    s.start_all()
    pid1 = s.procs["dummy"].pid
    kill(s.procs["dummy"])
    s.recover()
    pid2 = s.procs["dummy"].pid
    ok = (pid1 != pid2) and is_alive(s.procs["dummy"])
    s.stop_all()
    ok = ok and not is_alive(s.procs["dummy"])
    if ok:
        log("✅ self-check OK: recovery jalan + stop bersih")
        return 0
    log("❌ self-check GAGAL")
    return 1


def main(argv=None):
    argv = argv if argv is not None else sys.argv[1:]
    if "--once" in argv:
        return self_check()
    Supervisor(load_services()).run()
    return 0


if __name__ == "__main__":
    sys.exit(main())
```

Move `import tempfile` to the top import block with the others (keep imports grouped); shown here inline only for locality.

- [ ] **Step 4: Run test to verify it passes**

Run: `pytest test_supervisor.py -v`
Expected: PASS (15 tests total)

Also run the self-check directly:
Run: `python3 supervisor.py --once`
Expected: prints `✅ self-check OK: recovery jalan + stop bersih`, exit 0

- [ ] **Step 5: Commit**

```bash
git add supervisor.py test_supervisor.py
git commit -m "feat(supervisor): CLI + --once self-check + signal handlers"
```

---

### Task 6: Wiring — gitignore, example config, deprecate old updaters, docs

**Files:**
- Create: `services.local.json.example`
- Modify: `.gitignore`, `auto_updater.py`, `auto_updater.sh`, `README_WORKFLOW.md`, `pln_api_server/README.md`

**Interfaces:**
- Consumes: `supervisor.py` (Tasks 1-5).
- Produces: —

- [ ] **Step 1: Create example override config**

```json
// services.local.json.example
// Copy ke services.local.json (gitignored) di mesin yang mau trim service.
// Contoh: mesin tanpa telegram bot (cuma server + tunnel).
[
  {"name": "pln_server", "cmd": ["python3", "pln_api_server/server.py"], "restart_on_update": true, "lock_file": null},
  {"name": "tunnel", "cmd": ["cloudflared", "tunnel", "--url", "http://localhost:8900"], "restart_on_update": false, "lock_file": null}
]
```

Note: JSON asli tidak boleh ada komentar; simpan file `.example` dengan dua baris komentar teratas dihapus saat disalin, atau simpan komentar sebagai key `"_comment"`. Gunakan bentuk tanpa `//` berikut untuk file `.example`:

```json
[
  {"_comment": "Copy ke services.local.json (gitignored). Contoh: mesin tanpa bot.", "name": "pln_server", "cmd": ["python3", "pln_api_server/server.py"], "restart_on_update": true, "lock_file": null},
  {"name": "tunnel", "cmd": ["cloudflared", "tunnel", "--url", "http://localhost:8900"], "restart_on_update": false, "lock_file": null}
]
```

(`load_services` mengabaikan key ekstra seperti `_comment`.)

- [ ] **Step 2: Update .gitignore**

Tambah baris berikut ke `.gitignore` (kalau belum ada):

```
logs/
services.local.json
```

- [ ] **Step 3: Verify supervisor ignores the example + reads only services.local.json**

Run: `python3 -c "import supervisor; print(len(supervisor.load_services()))"`
Expected: `3` (default, karena belum ada `services.local.json` — hanya `.example`)

- [ ] **Step 4: Mark old updaters deprecated**

Sisipkan baris pertama komentar di `auto_updater.py` (setelah shebang/awal file):

```python
# DEPRECATED (2026-07-14): diganti supervisor.py — jalankan `python3 supervisor.py`.
# supervisor.py mengawasi seluruh stack (pln_server + telegram_bot + tunnel), bukan hanya bot ini.
```

Sisipkan di `auto_updater.sh` setelah baris `#!/bin/bash`:

```bash
# DEPRECATED (2026-07-14): diganti supervisor.py — jalankan `python3 supervisor.py`.
```

- [ ] **Step 5: Update docs**

Di `README_WORKFLOW.md`, tambah/ubah bagian menjalankan server jadi:

```markdown
## Menjalankan stack (auto-update)

Satu perintah mengawasi PLN server + Telegram bot + cloudflared tunnel, dan
otomatis `git pull` + restart service saat ada update di GitHub (tunnel tidak
di-restart supaya URL-nya tetap):

    python3 supervisor.py

Cek sehat cepat (recovery + shutdown bersih), tanpa loop:

    python3 supervisor.py --once

Mesin yang tidak perlu semua service: salin `services.local.json.example` ke
`services.local.json` (gitignored) dan hapus baris yang tidak dipakai.

> `auto_updater.py` / `auto_updater.sh` sudah deprecated — pakai `supervisor.py`.
```

Di `pln_api_server/README.md`, tambah satu baris di bawah cara run manual:

```markdown
> Produksi: jangan jalankan manual — `python3 supervisor.py` dari repo root akan
> menjalankan server ini + auto-update. Lihat README_WORKFLOW.md.
```

- [ ] **Step 6: Full test run + commit**

Run: `pytest test_supervisor.py -v`
Expected: PASS (15 tests)

```bash
git add services.local.json.example .gitignore auto_updater.py auto_updater.sh README_WORKFLOW.md pln_api_server/README.md
git commit -m "chore(supervisor): example config, gitignore, deprecate auto_updater, docs"
```

---

## Self-Review

**Spec coverage:**
- Supervisor Python pegang Popen → Task 2, 4. ✓
- Service list + `services.local.json` override → Task 1, 6. ✓
- Loop git-poll → pull → restart `restart_on_update`, tunnel dilewati → Task 4 (`tick`/`apply_update`), Task 1 (default `tunnel.restart_on_update=False`). ✓
- Lock defer → Task 3 (`decide`/`active_lock`), Task 4. ✓
- Auto-recover → Task 4 (`recover`). ✓
- Kill lintas-OS via process-group → Task 2. ✓
- Log per-service → Task 4 (`_logpath` + `spawn`). ✓
- Shutdown bersih signal → Task 5. ✓
- `--once` self-check assert-based → Task 5. ✓
- Deprecate `auto_updater.*` + docs → Task 6. ✓
- Error handling git offline / start gagal / json rusak → Task 3 (`git_revisions` try/except), Task 4 (`start` try/except), Task 1 (`load_services` fallback). ✓

**Placeholder scan:** tidak ada TBD/TODO; semua step berisi kode nyata. ✓

**Type consistency:** `spawn/is_alive/kill`, `decide/active_lock/git_revisions/git_pull`, `Supervisor.procs/start/stop/start_all/stop_all/apply_update/recover/tick/run`, `self_check/main` — nama konsisten dipakai lintas task. `lock_file` (bukan `needs_lock`) dipakai seragam di DEFAULT_SERVICES, `active_lock`, dan test. ✓
