#!/usr/bin/env python3
"""kirim_report.py — kirim batch_report terakhir dari HP Petugas ke WhatsApp.

Dipakai lewat perintah `fasih-kirim-report`.

Dua jalur, otomatis dipilih:
  1. termux-share  -> membuka menu Bagikan Android; petugas tinggal pilih WhatsApp
                      dan pilih tujuannya sendiri. Butuh app Termux:API +
                      `pkg install termux-api`.
  2. salin ke /sdcard/Download -> kalau termux-share tidak tersedia, file disalin
     ke penyimpanan bersama supaya bisa dilampirkan manual dari WhatsApp.

Sengaja TIDAK mengirim otomatis ke nomor tertentu: petugas yang memilih tujuan
dan menekan kirim, jadi tidak ada laporan yang lolos ke pihak yang salah.
"""
import os
import sys
import csv
import glob
import shutil
import subprocess
from datetime import datetime

REPO_ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))


def _laporan_terbaru(n: int = 1):
    pola = [os.path.join(REPO_ROOT, "batch_report_*.csv"),
            os.path.join(REPO_ROOT, "Folder-Runner", "report", "batch_report_*.csv")]
    files = []
    for p in pola:
        files.extend(glob.glob(p))
    files.sort(key=os.path.getmtime, reverse=True)
    return files[:n]


def _ringkas(path: str) -> str:
    """Ringkasan singkat supaya isi laporan terbaca tanpa membuka file."""
    total = sukses = gagal = 0
    akun = set()
    try:
        with open(path, encoding="utf-8") as f:
            for r in csv.DictReader(f):
                total += 1
                pesan = (r.get("message") or "").lower()
                if "berhasil dikirim" in pesan or (r.get("status") or "").upper() == "SUCCESS":
                    sukses += 1
                else:
                    gagal += 1
                # wizard menulis kolom "email", runner menulis "petugas"
                em = r.get("email") or r.get("petugas")
                if em:
                    akun.add(em.strip())
    except Exception:
        return ""
    return (f"Laporan {os.path.basename(path)}\n"
            f"Total {total} data | Terkirim {sukses} | Gagal {gagal}\n"
            f"Akun: {len(akun)}")


def _punya(cmd: str) -> bool:
    return shutil.which(cmd) is not None


def main():
    args = [a for a in sys.argv[1:] if not a.startswith("-")]
    path = args[0] if args else None

    if path:
        if not os.path.exists(path):
            print(f"❌ File tidak ditemukan: {path}")
            sys.exit(1)
    else:
        cari = _laporan_terbaru(1)
        if not cari:
            print("❌ Belum ada batch_report. Jalankan fasih-submit-batch dulu.")
            sys.exit(1)
        path = cari[0]

    umur = datetime.fromtimestamp(os.path.getmtime(path)).strftime("%d %b %Y %H:%M")
    print(f"\n📄 Laporan : {os.path.basename(path)}")
    print(f"🕒 Dibuat  : {umur}")
    ring = _ringkas(path)
    if ring:
        print("\n" + "\n".join("   " + b for b in ring.splitlines()))

    # Jalur 1: menu Bagikan Android
    if _punya("termux-share"):
        print("\n📲 Membuka menu Bagikan — pilih WhatsApp, lalu pilih tujuannya.")
        try:
            subprocess.run(["termux-share", "-a", "send", "-t", "text/csv", path], check=True)
            print("✅ Menu Bagikan terbuka. Kalau WhatsApp tidak muncul, geser daftarnya.")
            return
        except subprocess.CalledProcessError as e:
            print(f"⚠️  termux-share gagal ({e}). Pakai cara salin file.")

    # Jalur 2: salin ke penyimpanan bersama
    tujuan = None
    for d in ("/sdcard/Download", os.path.expanduser("~/storage/downloads")):
        if os.path.isdir(d):
            tujuan = d
            break
    if tujuan:
        akhir = os.path.join(tujuan, os.path.basename(path))
        shutil.copy(path, akhir)
        print(f"\n✅ Laporan disalin ke: {akhir}")
        print("   Buka WhatsApp → chat tujuan → 📎 → Dokumen → Download → pilih file ini.")
    else:
        print("\n⚠️  termux-share belum ada dan penyimpanan bersama belum aktif.")
        print("   Aktifkan sekali saja, lalu ulangi perintah ini:")
        print("     pkg install termux-api      # + pasang app Termux:API dari Play Store")
        print("     termux-setup-storage")
        print(f"\n   Sementara itu, file ada di: {path}")


if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\nDibatalkan.")
