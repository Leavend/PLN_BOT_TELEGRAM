#!/usr/bin/env python3
"""CEK fasih_exists dengan cache. `fasih_exists=true` itu PERMANEN (sekali tercatat,
selamanya tercatat) → ID yang udah true disimpan & gak pernah dicek ulang. Cuma yang
belum/baru yang kena call BPS → hemat kuota CEK ~90%.

Usage:
  python3 cek_cache.py --list 234...,234...
  python3 cek_cache.py ids.txt
  pbpaste | python3 cek_cache.py            # paste dari stdin
  python3 cek_cache.py --list ... --refresh # abaikan cache, cek ulang semua
  python3 cek_cache.py --selftest           # tes logika (tanpa jaringan)

Akun rotasi: baca .fasih_accounts.txt (satu email per baris, atau email:password;
gitignored). Kalau file gak ada → pakai fasih_token.json (1 akun).
"""
import sys, os, json, uuid, time, argparse, re

REPO = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, REPO)

CACHE_FILE = os.path.join(REPO, ".fasih_tercatat_cache.json")
ACCTS_FILE = os.path.join(REPO, ".fasih_accounts.txt")
TOKEN_FILE = os.path.join(REPO, "fasih_token.json")
DEF_PW = "Pln@1234"


def load_cache() -> set:
    try:
        return set(json.load(open(CACHE_FILE)))
    except Exception:
        return set()


def save_cache(s: set):
    json.dump(sorted(s), open(CACHE_FILE, "w"))


def load_accounts() -> list:
    """[(email, password)] dari .fasih_accounts.txt; kosong kalau file gak ada."""
    out = []
    if os.path.exists(ACCTS_FILE):
        for line in open(ACCTS_FILE):
            line = line.strip()
            if not line or line.startswith("#"):
                continue
            em, _, pw = line.partition(":")
            out.append((em.strip(), pw.strip() or DEF_PW))
    return out


def parse_ids(raw: str) -> list:
    """Ambil semua 12-digit unik (urut asli), buang prefix nomor/bullet."""
    seen, out = set(), []
    for m in re.findall(r"\b\d{12}\b", raw):
        if m not in seen:
            seen.add(m)
            out.append(m)
    return out


class Checker:
    """Rotasi akun dari file, atau fallback ke token tunggal. Fast-fail 18s, 2 try."""
    def __init__(self, accounts):
        self.accounts = accounts
        self.i = 0
        self.headers = None
        self.cur = None
        if not accounts:  # fallback: token tunggal
            from fasih_auth import get_headers
            self.headers = get_headers(json.load(open(TOKEN_FILE)))
            self.cur = "fasih_token.json"

    def _next(self) -> bool:
        from fasih_auth import perform_login, get_headers
        while self.i < len(self.accounts):
            em, pw = self.accounts[self.i]; self.i += 1
            td = perform_login(em, pw, exit_on_failure=False)
            if td:
                self.headers = get_headers(td); self.cur = em
                print(f"  [akun {em}]", flush=True)
                return True
        return False

    def cek(self, idpel):
        """True/False/None (None = gak bisa dipastikan: akun habis / error)."""
        from fasih_api import check_idpln
        tries = len(self.accounts) + 1 if self.accounts else 1
        for _ in range(tries):
            if self.headers is None and not self._next():
                return None
            for _t in range(2):
                try:
                    return check_idpln(self.headers, str(uuid.uuid4()), idpel).get("data", {}).get("fasih_exists")
                except Exception:
                    pass
            if not self.accounts or not self._next():
                return None
        return None


def run(ids, refresh=False):
    cache = load_cache()
    accounts = load_accounts()
    cached = [] if refresh else [i for i in ids if i in cache]
    tocheck = [i for i in ids if refresh or i not in cache]

    print(f"📋 {len(ids)} ID | cache-hit (tercatat): {len(cached)} | perlu cek BPS: {len(tocheck)}")
    print(f"🔑 {len(accounts)} akun rotasi" if accounts
          else "🔑 pakai fasih_token.json (1 akun) — bikin .fasih_accounts.txt buat rotasi")

    tercatat = list(cached)
    belum, err = [], []
    if tocheck:
        chk = Checker(accounts)
        for n, idp in enumerate(tocheck, 1):
            fe = chk.cek(idp)
            if fe is True:
                tercatat.append(idp); cache.add(idp)
            elif fe is False:
                belum.append(idp)
            else:
                err.append(idp)
            if n % 25 == 0:
                print(f"  {n}/{len(tocheck)} | tercatat_baru={len(tercatat)-len(cached)} belum={len(belum)} err={len(err)} [{chk.cur}]", flush=True)
            time.sleep(0.05)
        save_cache(cache)

    print(f"\n===== {len(ids)} ID =====")
    print(f"  ✅ TERCATAT : {len(tercatat)}  ({len(cached)} dari cache, {len(tercatat)-len(cached)} baru)")
    print(f"  ❌ BELUM    : {len(belum)}")
    print(f"  ⚠️  err/skip : {len(err)}")
    if belum:
        print("\nBELUM (feed ke fasih-reregister):\n" + "\n".join(belum))
    if err:
        print("\nERR (akun habis / cek ulang nanti):\n" + "\n".join(err))
    print(f"\n💾 cache: {len(cache)} ID tercatat tersimpan di .fasih_tercatat_cache.json")
    return tercatat, belum, err


def selftest():
    assert parse_ids("1. 234201605398\n2. 234201618648\n234201605398 dup") == \
        ["234201605398", "234201618648"], "parse_ids dedup/prefix"
    assert parse_ids("no 12digit here 123") == [], "parse_ids reject non-12"
    global CACHE_FILE
    import tempfile
    CACHE_FILE = os.path.join(tempfile.mkdtemp(), "c.json")
    save_cache({"234201605398", "234201618648"})
    assert load_cache() == {"234201605398", "234201618648"}, "cache round-trip"
    assert load_cache() >= {"234201605398"}, "cache membership"
    print("✅ selftest OK")


def main():
    ap = argparse.ArgumentParser(description="CEK fasih_exists berbasis cache")
    ap.add_argument("input", nargs="?", help="File berisi daftar ID (satu per baris)")
    ap.add_argument("--list", "-l", help="Daftar ID dipisah koma")
    ap.add_argument("--refresh", action="store_true", help="Abaikan cache, cek ulang semua")
    ap.add_argument("--selftest", action="store_true", help="Tes logika tanpa jaringan")
    args = ap.parse_args()

    if args.selftest:
        selftest(); return

    raw = ""
    if args.list:
        raw = args.list.replace(",", " ")
    elif args.input and os.path.exists(args.input):
        raw = open(args.input).read()
    elif not sys.stdin.isatty():
        raw = sys.stdin.read()
    ids = parse_ids(raw)
    if not ids:
        print("❌ Gak ada ID 12-digit. Kasih --list, file, atau paste via stdin.")
        sys.exit(1)
    run(ids, refresh=args.refresh)


if __name__ == "__main__":
    main()
