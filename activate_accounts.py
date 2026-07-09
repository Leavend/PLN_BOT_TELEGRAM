#!/usr/bin/env python3
"""Auto-aktivasi akun BPS Mitra. Kumpulin link aktivasi (Gmail IMAP atau file
paste), buka tiap link di browser BENERAN (Playwright headless) biar SPA-nya
jalan + lolos WAF, lalu akun yang sukses di-append ke .fasih_accounts.txt.

Kenapa browser: token aktivasi ber-tanda-tangan (HMAC server) + halaman SPA +
WAF nolak request mentah. Ini cuma otomatisasi klik link-mu sendiri.

Setup:
  pip install playwright && playwright install chromium
  # (opsional) Gmail IMAP — bikin .gmail_config isinya:  base.email@gmail.com:app_password
  #   app password: Google Account -> Security -> 2-Step -> App passwords

Usage:
  python3 activate_accounts.py                 # IMAP kalau ada .gmail_config, else activation_links.txt
  python3 activate_accounts.py --file links.txt
  python3 activate_accounts.py --headful       # browser keliatan (debug)
  python3 activate_accounts.py --selftest
"""
import sys, os, re, argparse, time

REPO = os.path.dirname(os.path.abspath(__file__))
GMAIL_CFG = os.path.join(REPO, ".gmail_config")
LINKS_FILE = os.path.join(REPO, "activation_links.txt")
ACCTS_FILE = os.path.join(REPO, ".fasih_accounts.txt")
UA = ("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 "
      "(KHTML, like Gecko) Chrome/126.0 Safari/537.36")

LINK_RE = re.compile(r"https://mitra\.bps\.go\.id/verifikasi-akun/[A-Za-z0-9+/=_%-]+")
EMAIL_RE = re.compile(r"[A-Za-z0-9._%+-]+@gmail\.com")
# SPA nampilin toast setelah manggil verify API:
#   fresh activate -> "berhasil"/"aktivasi berhasil"
#   sudah aktif    -> "WHERE conditions required" (backend gak nemu row utk di-update)
#   token invalid  -> "kadaluarsa"/"tidak valid"/dst
OK_KW = ("berhasil", "sukses", "aktivasi berhasil", "diaktifkan", "terverifikasi", "verified", "success")
ALREADY_KW = ("where conditions required", "sudah aktif", "already", "sudah diaktifkan", "sudah terverifikasi")
FAIL_KW = ("kadaluarsa", "expired", "tidak valid", "invalid", "waf block", "tidak ditemukan", "not found", "token salah")


def get_body(msg):
    parts = []
    if msg.is_multipart():
        for p in msg.walk():
            if p.get_content_type() in ("text/plain", "text/html"):
                try:
                    parts.append(p.get_payload(decode=True).decode(p.get_content_charset() or "utf-8", "ignore"))
                except Exception:
                    pass
    else:
        try:
            parts.append(msg.get_payload(decode=True).decode(msg.get_content_charset() or "utf-8", "ignore"))
        except Exception:
            pass
    return "\n".join(parts)


def pairs_from_text(to_hint, body):
    """[(account_email, activation_link)] dari satu email."""
    links = LINK_RE.findall(body)
    acct = (EMAIL_RE.findall(to_hint) or EMAIL_RE.findall(body) or [""])[0]
    return [(acct, l) for l in dict.fromkeys(links)]  # dedup link, keep order


def fetch_imap():
    import imaplib, email
    raw = open(GMAIL_CFG).read().strip()
    user, _, pw = raw.partition(":")
    user, pw = user.strip(), pw.strip()
    M = imaplib.IMAP4_SSL("imap.gmail.com")
    M.login(user, pw)
    M.select("INBOX")
    typ, data = M.search(None, '(BODY "verifikasi-akun")')
    pairs = []
    for num in data[0].split():
        typ, md = M.fetch(num, "(RFC822)")
        msg = email.message_from_bytes(md[0][1])
        pairs += pairs_from_text(msg.get("To", ""), get_body(msg))
    M.logout()
    # dedup by link
    seen, out = set(), []
    for a, l in pairs:
        if l not in seen:
            seen.add(l); out.append((a, l))
    return out


def fetch_file(path):
    out, seen = [], set()
    for line in open(path):
        for l in LINK_RE.findall(line):
            if l not in seen:
                seen.add(l)
                acct = (EMAIL_RE.findall(line) or [""])[0]
                out.append((acct, l))
    return out


def activate(pairs, headful):
    try:
        from playwright.sync_api import sync_playwright
    except ImportError:
        print("❌ Playwright belum ada. Jalankan:\n   pip install playwright && playwright install chromium")
        sys.exit(1)
    ok, fail, ambig = [], [], []
    with sync_playwright() as p:
        br = p.chromium.launch(headless=not headful)
        ctx = br.new_context(user_agent=UA, viewport={"width": 1280, "height": 800})
        pg = ctx.new_page()
        for i, (acct, link) in enumerate(pairs, 1):
            tag = acct or link[-24:]
            try:
                pg.goto(link, wait_until="networkidle", timeout=35000)
                time.sleep(2.0)
                txt = pg.inner_text("body").lower()
                if any(k in txt for k in FAIL_KW):
                    fail.append((acct, "gagal/expired")); print(f"[{i}/{len(pairs)}] ❌ {tag} — gagal/expired")
                elif any(k in txt for k in OK_KW):
                    ok.append(acct); print(f"[{i}/{len(pairs)}] ✅ {tag} — aktivasi berhasil")
                elif any(k in txt for k in ALREADY_KW):
                    ok.append(acct); print(f"[{i}/{len(pairs)}] ✅ {tag} — sudah aktif")
                else:
                    ambig.append(acct); print(f"[{i}/{len(pairs)}] ➕ {tag} — loaded (cek --headful sekali)")
            except Exception as e:
                fail.append((acct, str(e)[:40])); print(f"[{i}/{len(pairs)}] ❌ {tag} — {str(e)[:40]}")
            time.sleep(0.5)
        br.close()
    return ok, fail, ambig


def append_accounts(emails):
    existing = set()
    if os.path.exists(ACCTS_FILE):
        existing = {e.lower() for e in re.findall(r"[A-Za-z0-9._%+-]+@gmail\.com", open(ACCTS_FILE).read())}
    new = [e for e in dict.fromkeys(emails) if e and e.lower() not in existing]
    if new:
        with open(ACCTS_FILE, "a") as f:
            f.write("\n# auto-activated " + time.strftime("%Y-%m-%d %H:%M") + "\n" + "\n".join(new) + "\n")
    return new


def selftest():
    body = ("Email : b.a.gu.spr.at.a.m.a.s.ety.a.0.4@gmail.com\n"
            "atau link: https://mitra.bps.go.id/verifikasi-akun/YmFndXMabc123-_=\n")
    pr = pairs_from_text("to b.a.gu.spr@gmail.com", body)
    assert len(pr) == 1, "1 link"
    assert pr[0][1].endswith("/verifikasi-akun/YmFndXMabc123-_="), "link utuh"
    assert pr[0][0] == "b.a.gu.spr@gmail.com", "email dari To"
    # dedup link
    two = pairs_from_text("", body + body)
    assert len(two) == 1, "dedup link"
    print("✅ selftest OK")


def main():
    ap = argparse.ArgumentParser(description="Auto-aktivasi akun BPS Mitra (Playwright)")
    ap.add_argument("--file", help="file berisi link aktivasi (default: activation_links.txt)")
    ap.add_argument("--headful", action="store_true", help="tampilkan browser (debug)")
    ap.add_argument("--selftest", action="store_true")
    args = ap.parse_args()
    if args.selftest:
        selftest(); return

    if args.file:
        pairs = fetch_file(args.file)
        src = args.file
    elif os.path.exists(GMAIL_CFG):
        print("📧 Tarik link dari Gmail (IMAP)...")
        pairs = fetch_imap(); src = "Gmail IMAP"
    elif os.path.exists(LINKS_FILE):
        pairs = fetch_file(LINKS_FILE); src = LINKS_FILE
    else:
        print(f"❌ Gak ada sumber link. Bikin .gmail_config (email:app_password) atau {LINKS_FILE} (paste link).")
        sys.exit(1)

    if not pairs:
        print("❌ Gak nemu link verifikasi-akun."); sys.exit(1)
    print(f"🔗 {len(pairs)} link aktivasi (sumber: {src})\n")
    ok, fail, ambig = activate(pairs, args.headful)
    added = append_accounts(ok + ambig)

    print(f"\n===== SELESAI =====")
    print(f"  ✅ aktif      : {len(ok)}")
    print(f"  ➕ loaded?    : {len(ambig)} (gak kedeteksi teks sukses/gagal)")
    print(f"  ❌ gagal      : {len(fail)}")
    print(f"  📝 ditambah ke .fasih_accounts.txt: {len(added)}")
    if fail:
        print("\ngagal:")
        for a, why in fail:
            print(f"  {a or '?'} — {why}")


if __name__ == "__main__":
    main()
