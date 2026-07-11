#!/usr/bin/env python3
"""fasih-cek-submit — verifikasi assignment yang SUDAH disubmit script sesuai harapan.

Buat tiap ID Pelanggan: cari record terbaru di akun yang login, DECRYPT archive-nya,
lalu cek:
  • TERCATAT di FASIH (check-idpln fasih_exists)
  • BLOK I  (r102 b/c/d) Kab/Kec/Desa terisi
  • BLOK III (r301 b/c/d) terisi + kode VALID di master wilayah app (bukan blank/PLN)
  • Nama (r103) ke-mask (ada '*')
Report per-ID: ✅ OK / ⚠️ ada masalah (apa) / ❌ record gak ketemu.

Usage:
  python3 fasih_cek_submit.py --list 234...,234...
  python3 fasih_cek_submit.py ids.txt
  pbpaste | python3 fasih_cek_submit.py
"""
import sys, os, json, base64, tempfile, glob, argparse, re, uuid

REPO = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, REPO)
TOKEN_FILE = os.path.join(REPO, "fasih_token.json")


def parse_ids(raw):
    seen, out = set(), []
    for m in re.findall(r"\b\d{12}\b", raw):
        if m not in seen:
            seen.add(m); out.append(m)
    return out


def _valid_code_sets():
    from submit_fasih import load_regional_lookups
    kec, desa = load_regional_lookups()
    vkec = {str(v["code"]) for v in kec.values()}
    vdesa = {str(v["full_code"]) for v in desa.values()}
    return vkec, vdesa


def _newest_records(h):
    """idpel -> (record, periodeId), record terbaru per idpel."""
    from fasih_api import fetch_surveys, fetch_all_assignments
    idx = {}
    def ts(rec):
        m = re.search(r"_(\d{13})\.7z", rec.get("basePath") or "")
        return int(m.group(1)) if m else 0
    for s in fetch_surveys(h):
        for p in s.get("listPeriode", []):
            if not p.get("isActive"):
                continue
            for a in fetch_all_assignments(h, p["id"]):
                d3 = a.get("data3")
                if not d3:
                    continue
                if d3 not in idx or ts(a) > ts(idx[d3][0]):
                    idx[d3] = (a, p["id"])
    return idx


def _decrypt(h, rec, pid):
    import requests, py7zr
    from fasih_api import session, fetch_regions
    from fasih_crypto import decrypt_gcm_verify
    from submit_fasih import STATIC_LEGACY_KEY
    BASE = "https://fasih-survey.bps.go.id"
    fn = rec["basePath"].split("/")[-1]
    subdir = rec["basePath"].split("/")[-2]
    body = [{"assignmentId": rec["id"], "copyFromId": subdir, "fileNames": [fn]}]
    r = session.post(f"{BASE}/mobile/assignment-sync/api/mobile/s3/assignment/presign-url",
                     headers=h, json=body, params={"surveyPeriodId": pid}, timeout=30)
    d = (r.json() or {}).get("data")
    if not d:
        return None
    blob = requests.get(d[0]["presignedUrls"][0]["presignedUrl"], timeout=60).content
    wd = tempfile.mkdtemp(); zp = os.path.join(wd, "a.7z"); open(zp, "wb").write(blob)
    with py7zr.SevenZipFile(zp, "r") as z:
        z.extractall(wd)
    enc = open(glob.glob(os.path.join(wd, "**", "data.json"), recursive=True)[0]).read()
    keys = [STATIC_LEGACY_KEY.encode()]
    try:
        keys += [base64.b64decode(rg["wrappedDatakey"]) for rg in fetch_regions(h, pid) if rg.get("wrappedDatakey")]
    except Exception:
        pass
    for k in keys:
        try:
            return json.loads(decrypt_gcm_verify(enc, k))
        except Exception:
            pass
    return None


def _r301_value(ans, key):
    v = ans.get(key)
    if isinstance(v, list) and v and isinstance(v[0], dict):
        return str(v[0].get("value") or ""), str(v[0].get("label") or "")
    return "", ""


def check_one(h, idpel, idx, vkec, vdesa, tercatat):
    if idpel not in idx:
        return "❌", "record gak ketemu (akun beda / belum submit)"
    rec, pid = idx[idpel]
    j = _decrypt(h, rec, pid)
    if not j:
        return "❌", "gagal decrypt archive"
    ans = {a["dataKey"]: a["answer"] for a in j.get("answers", [])}
    issues = []
    # BLOK I
    if not all(str(ans.get(f"r102{c}") or "").strip() for c in "bcd"):
        issues.append("BLOK I kosong")
    # BLOK III value valid?
    kv, klab = _r301_value(ans, "r301c")
    dv, dlab = _r301_value(ans, "r301d")
    if not kv or not dv:
        issues.append("BLOK III kosong")
    else:
        if kv not in vkec:
            issues.append(f"r301c kode invalid ({kv})")
        if dv not in vdesa:
            issues.append(f"r301d kode invalid ({dv})")
    # nama mask — dicek di data slot (data2), yg tampil di app; archive r103 sengaja full
    nama_slot = str(rec.get("data2") or "")
    if nama_slot and "*" not in nama_slot:
        issues.append("nama tak ter-mask (data2)")
    # tercatat
    if tercatat is False:
        issues.append("BELUM tercatat")
    region = f"{_r301_value(ans,'r301b')[1]} / {klab} / {dlab}".replace("[", "").replace("]", "")
    if issues:
        return "⚠️", f"{'; '.join(issues)}  [{region}]"
    tag = "tercatat" if tercatat else "submit ok"
    return "✅", f"{tag} | BLOK I+III ✓ | {region}"


def main():
    ap = argparse.ArgumentParser(description="Verifikasi assignment hasil submit script")
    ap.add_argument("input", nargs="?")
    ap.add_argument("--list", "-l")
    ap.add_argument("--no-tercatat", action="store_true", help="skip cek tercatat (lebih cepat)")
    args = ap.parse_args()

    raw = args.list.replace(",", " ") if args.list else \
        (open(args.input).read() if args.input and os.path.exists(args.input) else
         (sys.stdin.read() if not sys.stdin.isatty() else ""))
    ids = parse_ids(raw)
    if not ids:
        print("❌ Gak ada ID 12-digit."); sys.exit(1)

    from fasih_auth import get_headers
    from fasih_api import check_idpln
    h = get_headers(json.load(open(TOKEN_FILE)))
    print(f"🔎 Verifikasi {len(ids)} assignment...")
    print("📥 Ambil daftar assignment + decrypt (bisa lama)...")
    idx = _newest_records(h)
    vkec, vdesa = _valid_code_sets()

    ok = warn = bad = 0
    for i, idp in enumerate(ids, 1):
        terc = None
        if not args.no_tercatat and idp in idx:
            try:
                terc = check_idpln(h, str(uuid.uuid4()), idp).get("data", {}).get("fasih_exists")
            except Exception:
                terc = None
        icon, msg = check_one(h, idp, idx, vkec, vdesa, terc)
        print(f"[{i}/{len(ids)}] {idp} {icon} {msg}", flush=True)
        ok += icon == "✅"; warn += icon == "⚠️"; bad += icon == "❌"

    print(f"\n===== {len(ids)} =====")
    print(f"  ✅ OK      : {ok}")
    print(f"  ⚠️  masalah : {warn}")
    print(f"  ❌ gagal    : {bad}")


if __name__ == "__main__":
    main()
