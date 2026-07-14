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
