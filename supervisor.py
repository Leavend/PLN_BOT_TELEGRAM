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
import tempfile
from region import get_region

REPO_ROOT = os.path.dirname(os.path.abspath(__file__))

# .env dimuat di sini supaya PORT benar dan anak proses (server/bot) mewarisi env.
try:
    from dotenv import load_dotenv
    load_dotenv(os.path.join(REPO_ROOT, ".env"))
except ImportError:
    print("[!] python-dotenv tidak terpasang — .env TIDAK dimuat. "
          "PLN_API_KEYS bisa kosong (auth server terbuka). Jalankan: pip install -r requirements.txt")

BRANCH = "main"
INTERVAL = 15
PORT = int(os.getenv("PLN_API_PORT", "8900"))
REGION = get_region()


def log(msg):
    print(f"[{time.strftime('%Y-%m-%d %H:%M:%S')}] {msg}", flush=True)


def tunnel_cmd(region, repo_root=REPO_ROOT):
    """Named tunnel (hostname stabil) bila wilayah sudah dikonfigurasi (penanda
    .tunnel_named ada), selain itu quick tunnel (Bontang legacy, URL berputar)."""
    if os.path.exists(os.path.join(repo_root, ".tunnel_named")):
        return ["cloudflared", "tunnel", "run", region]
    return ["cloudflared", "tunnel", "--url", f"http://localhost:{PORT}"]


DEFAULT_SERVICES = [
    {"name": "pln_server", "cmd": [sys.executable, "pln_api_server/server.py"],
     "restart_on_update": True, "lock_file": None},
    {"name": "telegram_bot", "cmd": [sys.executable, "telegram_bot.py"],
     "restart_on_update": True, "lock_file": "bot_active_runs.lock"},
    {"name": "tunnel", "cmd": tunnel_cmd(REGION),
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


class Supervisor:
    def __init__(self, services, branch=BRANCH, logdir=None):
        self.services = services
        self.branch = branch
        self.logdir = logdir or os.path.join(REPO_ROOT, "logs")
        os.makedirs(self.logdir, exist_ok=True)
        self.procs = {}
        self._pull_fails = 0

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
            if p is None or p.poll() is not None:
                log(f"auto-recover {s['name']} (mati/gagal-start)")
                self.start(s)

    def tick(self):
        local, remote = git_revisions(self.branch)
        action = decide(local, remote, active_lock(self.services))
        if action == "defer":
            log("update tertunda — service ber-lock lagi aktif (batch)")
        elif action == "update":
            log("kode baru di remote — pull + restart")
            if git_pull(self.branch):
                self._pull_fails = 0
                self.apply_update()
            else:
                self._pull_fails += 1
                log(f"⚠️  git pull GAGAL {self._pull_fails}x — stack ketinggalan versi, "
                    f"cek manual (uncommitted/conflict/detached HEAD?)")
        self.recover()

    def run(self):
        _install_signal_handlers(self)
        log(f"supervisor mulai — {len(self.services)} service, poll {INTERVAL}s")
        self.start_all()
        while True:
            time.sleep(INTERVAL)
            try:
                self.tick()
            except Exception as e:
                log(f"tick error (lanjut): {e}")


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
