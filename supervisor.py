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
