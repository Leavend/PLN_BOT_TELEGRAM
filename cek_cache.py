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
COOLDOWN_FILE = os.path.join(REPO, ".fasih_cooldown.json")
TOKEN_FILE = os.path.join(REPO, "fasih_token.json")
DEF_PW = "Pln@1234"

# cooldown per jenis error (detik). 429 = kuota CEK habis (lama); 403 = WAF/permission;
# timeout = jaringan sesaat. Akun kena cooldown di-skip run berikutnya sampai reset.
CD_429 = 6 * 3600
CD_403 = 30 * 60
CD_TIMEOUT = 10 * 60
CD_LOGINFAIL = 2 * 3600


def load_cache() -> set:
    try:
        return set(json.load(open(CACHE_FILE)))
    except Exception:
        return set()


def save_cache(s: set):
    json.dump(sorted(s), open(CACHE_FILE, "w"))


def load_cooldown() -> dict:
    try:
        return json.load(open(COOLDOWN_FILE))
    except Exception:
        return {}


def save_cooldown(cd: dict):
    now = time.time()
    json.dump({e: t for e, t in cd.items() if t > now}, open(COOLDOWN_FILE, "w"))  # buang yg udah lewat


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
    """Rotasi akun, sadar cooldown: akun yg 429/403 di-skip sampai reset (persist ke disk)."""
    def __init__(self, accounts):
        self.cd = load_cooldown()
        now = time.time()
        # buang akun yg masih cooldown; kalau semua cooldown, coba yg paling deket reset
        live = [a for a in accounts if self.cd.get(a[0], 0) <= now]
        skipped = len(accounts) - len(live)
        if not live and accounts:
            live = [min(accounts, key=lambda a: self.cd.get(a[0], 0))]
            print(f"  ⚠️  semua akun cooldown — coba yg paling deket reset: {live[0][0]}", flush=True)
        elif skipped:
            print(f"  ⏳ skip {skipped} akun cooldown (429/limit), {len(live)} akun siap", flush=True)
        self.accounts = live
        self.i = 0
        self.headers = None
        self.cur = None
        if not accounts:  # fallback: token tunggal
            from fasih_auth import get_headers
            self.headers = get_headers(json.load(open(TOKEN_FILE)))
            self.cur = "fasih_token.json"

    def _mark(self, em, secs):
        if em and em != "fasih_token.json":
            self.cd[em] = time.time() + secs

    def _next(self) -> bool:
        from fasih_auth import perform_login, get_headers
        while self.i < len(self.accounts):
            em, pw = self.accounts[self.i]; self.i += 1
            try:
                td = perform_login(em, pw, exit_on_failure=False)
            except Exception:
                td = None
            if td:
                self.headers = get_headers(td); self.cur = em
                print(f"  [akun {em}]", flush=True)
                return True
            self._mark(em, CD_LOGINFAIL)  # login gagal → cooldown biar gak dicoba lagi
        return False

    def cek(self, idpel):
        """True/False/None (None = akun habis / error). Klasifikasi error → cooldown akun."""
        from fasih_api import check_idpln
        tries = len(self.accounts) + 1 if self.accounts else 1
        for _ in range(tries):
            if self.headers is None and not self._next():
                return None
            err = ""
            for _t in range(2):
                try:
                    fe = check_idpln(self.headers, str(uuid.uuid4()), idpel).get("data", {}).get("fasih_exists")
                    self.cd.pop(self.cur, None)  # sukses → akun sehat, hapus cooldown
                    return fe
                except Exception as e:
                    err = str(e)
            if "429" in err:
                self._mark(self.cur, CD_429)
            elif "403" in err:
                self._mark(self.cur, CD_403)
            else:
                self._mark(self.cur, CD_TIMEOUT)
            if not self.accounts or not self._next():
                return None
        return None

    def close(self):
        save_cooldown(self.cd)


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
        chk.close()  # persist cooldown akun (429/403) buat run berikutnya
        n_cd = sum(1 for t in chk.cd.values() if t > time.time())
        if n_cd:
            print(f"  ⏳ {n_cd} akun ditandai cooldown (di-skip run berikutnya sampai reset)", flush=True)

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
    # cooldown: akun expired dibuang, yg aktif tetap; Checker skip yg cooldown
    global COOLDOWN_FILE
    COOLDOWN_FILE = os.path.join(os.path.dirname(CACHE_FILE), "cd.json")
    save_cooldown({"a@gmail.com": time.time() + 9999, "b@gmail.com": time.time() - 10})
    cd = load_cooldown()
    assert "a@gmail.com" in cd and "b@gmail.com" not in cd, "cooldown buang yg expired"
    chk = Checker([("a@gmail.com", "x"), ("c@gmail.com", "x")])
    assert [e for e, _ in chk.accounts] == ["c@gmail.com"], "Checker skip akun cooldown"
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
