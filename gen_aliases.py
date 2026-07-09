#!/usr/bin/env python3
"""Generate Gmail dot-alias variants. Gmail abaikan titik (inbox sama), tapi BPS
anggap tiap string ber-titik = akun terpisah → 1 Gmail bisa jadi banyak akun BPS.

Skip alias yang SUDAH kepake (.fasih_accounts.txt + mitra_bps_results*.txt).
Output N email + N nama acak (jumlah cocok) buat langsung di-paste ke mitra_bps_register.

Usage:
  python3 gen_aliases.py leavendplnadalahjayajaya 50
  python3 gen_aliases.py le.avend.plnadalahjayajaya@gmail.com 50
  python3 gen_aliases.py --selftest
"""
import sys, os, re, glob, random, argparse

REPO = os.path.dirname(os.path.abspath(__file__))
DOMAIN = "@gmail.com"

FIRST = ("Ahmad Muhammad Ismail Ibrahim Rusdi Baharuddin Zainuddin Marwan Herman Suriansyah "
         "Junaidi Rahman Firmansyah Wahyudi Hasanuddin Syarifuddin Ramadhan Ridwan Rahmat Hamdani "
         "Rusli Yusransyah Erwinsyah Sutrisno Supriadi Bahrul Fadli Irfansyah Rusmadi Jailani "
         "Aisyah Norhayati Rusmini Nurhasanah Halimah Fatimah Rahmawati Suryani Marlina Rusmiati "
         "Hasanah Rosdiana Wahyuni Kartini Nurjannah Salamah Siti Rusnah Andi Raden").split()
LAST = ("Effendi Mansyur Nuraini Hartono Yusran Handayani Budiman Wijaya Wardhana Diningrat "
        "Panji Puspita Rahim Lestari Bahrun Idrus Suriyadi Rusman Kesuma Setia Perkasa Kertanegara "
        "Sakti Mahmud Zulkarnain Baihaqi Fauzi Ismail Syahrani Baderun Rusadi Anom Sulaiman Hasan "
        "Karta Suria Batara Purnama Wirya Ningsih Anggraini Damayanti Safitri Astuti Yuliana "
        "Herawati Susanti Wulandari Fitriani Rahmadani Oktaviani Maharani Cahyani").split()


def canonical(base: str) -> str:
    """Buang @domain + semua titik → huruf dasar."""
    base = base.strip().lower().split("@")[0]
    return base.replace(".", "")


def make_variant(canon: str) -> str:
    """Sisip titik acak di antara huruf (gak pernah di ujung / dobel)."""
    out = canon[0]
    for c in canon[1:]:
        if random.random() < 0.45:
            out += "."
        out += c
    return out + DOMAIN


def load_used() -> set:
    used = set()
    files = [os.path.join(REPO, ".fasih_accounts.txt")] + glob.glob(os.path.join(REPO, "mitra_bps_results*.txt"))
    for f in files:
        if os.path.exists(f):
            for m in re.findall(r"[A-Za-z0-9.]+@gmail\.com", open(f, errors="ignore").read()):
                used.add(m.lower())
    return used


def generate(base: str, n: int, used: set):
    canon = canonical(base)
    if len(canon) < 2:
        raise ValueError("base terlalu pendek")
    out, seen = [], set(used)
    cap = n * 500 + 1000
    for _ in range(cap):
        if len(out) >= n:
            break
        v = make_variant(canon)
        if v in seen:
            continue
        seen.add(v); out.append(v)
    return out, canon


def selftest():
    used = {"a.b.c@gmail.com"}
    out, canon = generate("abcdefgh", 20, used)
    assert canon == "abcdefgh"
    assert len(out) == len(set(out)), "harus unik"
    for e in out:
        local = e[:-len(DOMAIN)]
        assert not local.startswith(".") and not local.endswith("."), "no titik ujung"
        assert ".." not in local, "no titik dobel"
        assert local.replace(".", "") == "abcdefgh", "canonical utuh"
        assert e not in used, "skip yg used"
    print("✅ selftest OK")


def main():
    ap = argparse.ArgumentParser(description="Generate Gmail dot-alias buat akun BPS")
    ap.add_argument("base", nargs="?", help="email/base gmail (mis. leavendplnadalahjayajaya)")
    ap.add_argument("count", nargs="?", type=int, help="jumlah alias")
    ap.add_argument("--selftest", action="store_true")
    args = ap.parse_args()

    if args.selftest:
        selftest(); return
    if not args.base or not args.count:
        ap.error("kasih: <base_email> <jumlah>  (contoh: gen_aliases.py leavendplnadalahjayajaya 50)")

    used = load_used()
    emails, canon = generate(args.base, args.count, used)
    names = [f"{random.choice(FIRST)} {random.choice(LAST)}" for _ in emails]

    ef = os.path.join(REPO, "aliases_emails.txt")
    nf = os.path.join(REPO, "aliases_names.txt")
    open(ef, "w").write("\n".join(emails) + "\n")
    open(nf, "w").write("\n".join(names) + "\n")

    print(f"✅ {len(emails)} alias unik (base: {canon}) — skip {len(used)} yg udah kepake")
    if len(emails) < args.count:
        print(f"⚠️  cuma dapet {len(emails)}/{args.count} (sisanya bentrok/used)")
    print(f"📧 email → {ef}\n👤 nama  → {nf}\n")
    print("─── EMAIL (paste ke prompt 'Email') ───")
    print("\n".join(emails))
    print("\n─── NAMA (paste ke prompt 'Nama Lengkap') ───")
    print("\n".join(names))
    print("\n⚠️  Di mitra_bps_register: NIK KETIK 6474010508010008, Password KETIK Pln@1234 (JANGAN enter kosong!)")


if __name__ == "__main__":
    main()
