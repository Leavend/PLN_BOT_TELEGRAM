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
