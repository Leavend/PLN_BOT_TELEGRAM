# Region Identity + Isolasi Akses per Wilayah (Sub-proyek B)

**Tanggal:** 2026-07-14
**Status:** Design — approved, siap plan
**Bagian dari:** Multi-region server ops (A → B → C). A = self-updating supervisor (DONE). Ini **B**. C = rollout multi-wilayah.

## Masalah

Tiap wilayah (Bontang, Samarinda, Balikpapan, Wahau) menjalankan full stack sendiri (hasil A). Sekarang belum ada konsep "wilayah" di code: `pln_api_server` menyajikan foto dari folder flat `house_photos/` dan `pln_url.txt` adalah **satu URL git-tracked yang dipakai semua petugas**. Dengan server terpisah per wilayah, tiap wilayah butuh: folder foto sendiri, URL tunnel sendiri, dan identitas wilayah sendiri — supaya petugas Balikpapan hanya mengakses server Balikpapan (foto + AP2T lookup Balikpapan), petugas Bontang hanya Bontang, dst.

## Model isolasi (keputusan)

**Deployment-only.** Isolasi dicapai lewat pemisahan deploy, BUKAN enforcement idpel di server:
- Tiap wilayah = server sendiri + `PLN_API_KEYS` sendiri + folder foto sendiri + URL tunnel sendiri.
- Petugas suatu wilayah hanya diberi URL + key wilayahnya → secara alami hanya bisa akses server wilayahnya.
- Server **tidak** memeriksa region idpel pada tiap lookup (tidak menolak idpel luar wilayah). Enforcement aktif (server tolak idpel lintas-wilayah) sengaja **di luar scope** B.

## Non-Goals (sengaja di luar B)

- Enforcement region pada lookup (server menolak idpel yang `nama_kab`-nya beda wilayah).
- Pemisahan storage foto sungguhan (gitignore foto per wilayah supaya mesin tidak membawa foto wilayah lain). Foto tetap git-tracked untuk B; dicatat sebagai opsi masa depan.
- Kredensial AP2T PLN terpisah per wilayah. AP2T tetap satu sumber (login yang sama); isolasi ada di lapisan akses server, bukan sumber data.
- Rollout mesin Samarinda/Balikpapan/Wahau → sub-proyek C.

## Pendekatan yang dipilih

**`.region` file + folder foto per-wilayah + file URL per-wilayah.** Satu helper region dipakai bersama server & petugas; perubahan code kecil; meniru pola `pln_url.txt` git-tracked yang sudah ada.

Alternatif yang ditolak:
- **Semua via `.env` per-mesin** — URL tunnel berganti tiap restart, jadi tiap HP petugas harus edit `.env` manual tiap URL baru; membuang keuntungan "git pull dapat URL baru" yang jadi alasan `pln_url.txt` masuk git.
- **Satu `regions.json` manifest pusat** — banyak server mengedit satu file → konflik merge saat push. File per-wilayah menghindari itu.

## Arsitektur

### Komponen

**1. `region.py` (baru, repo root)** — sumber tunggal identitas wilayah.
- `get_region() -> str`: urutan prioritas `FASIH_REGION` (env) > isi file `.region` (repo root, satu baris, gitignored) > default `"bontang"`.
- Normalisasi: `strip().lower()`; abaikan baris kosong / komentar `#`.
- Dipakai `pln_api_server/server.py` dan `petugas_client/batch_submit.py`.

**2. Server region-aware (`pln_api_server/server.py`)**
- `REGION = get_region()` saat startup.
- `PHOTO_DIRS = [house_photos/<REGION>/]` — hanya folder wilayahnya. (Ganti daftar flat `[house_photos, FOTORUMAH_PAK_ANWAR]`.)
- `/health` menambah field `"region": REGION` (untuk verifikasi URL mana melayani wilayah mana).
- `PLN_API_KEYS` tetap dari env — tiap mesin men-set key wilayahnya sendiri. Tidak ada perubahan mekanisme auth.

**3. Petugas resolve URL per-wilayah (`petugas_client/batch_submit.py`)**
- `_resolve_pln_url()` menjadi region-aware: baca `pln_url_<REGION>.txt` lebih dulu; jika tidak ada/kosong → fallback `pln_url.txt` (legacy); jika tetap kosong → env `PLN_API_URL`.
- `REGION = get_region()` di petugas (HP petugas punya `.region` sendiri).

**4. `fasih-status`** — tampilkan region aktif (baris `🌏 Wilayah: <region>`).

### Migrasi (dijalankan di mesin Bontang yang sudah ada)
- `git mv` seluruh isi `house_photos/*.webp` → `house_photos/bontang/`. Isi `FOTORUMAH_PAK_ANWAR/` juga dipindah ke `house_photos/bontang/` (semua foto saat ini milik Bontang).
- Salin `pln_url.txt` → `pln_url_bontang.txt` (git-tracked). `pln_url.txt` **tetap ada** sebagai fallback agar tidak ada yang putus saat transisi.
- `.gitignore`: tambah `.region`. Buat `.region.example` (isi contoh `bontang`).

### Alur data
Petugas HP `.region=balikpapan` → `_resolve_pln_url()` baca `pln_url_balikpapan.txt` + kirim key Balikpapan (dari `.env` PLN_API_KEY) → hit server Balikpapan → server `.region=balikpapan` sajikan `house_photos/balikpapan/` + AP2T. Kepisah per wilayah.

## Error handling

- `.region` tidak ada & `FASIH_REGION` tidak di-set → `get_region()` return `"bontang"` (default) → mesin Bontang lama jalan tanpa perubahan (backward-compatible).
- `house_photos/<region>/` tidak ada → server log warning, `_photo_list` kosong; endpoint foto balas 404 (perilaku `random_photo`/`get_photo` yang sudah ada). Tidak crash.
- `pln_url_<region>.txt` tidak ada/kosong → fallback `pln_url.txt` → env. Petugas Bontang lama (tanpa file per-region) tetap jalan lewat `pln_url.txt`.
- `.region` berisi baris aneh (spasi/uppercase) → dinormalisasi `strip().lower()`.

## Testing / verifikasi

`test_region.py` (pytest):
1. `get_region()` default `"bontang"` saat tidak ada env & tidak ada file.
2. Env `FASIH_REGION` menang atas file `.region`.
3. File `.region` dibaca & dinormalisasi (mis. ` Balikpapan\n` → `"balikpapan"`).
4. Baris komentar `#`/kosong di `.region` diabaikan.

Server (`test_server_region.py` atau tambahkan ke test server bila ada):
5. `/health` memuat `"region"` sesuai `get_region()`.
6. `load_photos()` hanya memuat dari `house_photos/<region>/` (buat 2 subdir dgn file berbeda, set region, pastikan hanya subdir wilayah termuat).

Petugas:
7. `_resolve_pln_url()` memilih `pln_url_<region>.txt` bila ada; fallback ke `pln_url.txt` bila tidak. (Uji dengan file temp + monkeypatch region.)

Semua test stdlib/pytest, tanpa jaringan (tidak memanggil AP2T/BPS/cloudflared).

## File yang disentuh

- **Baru:** `region.py`, `.region.example`, `test_region.py`, `house_photos/bontang/` (hasil migrasi), `pln_url_bontang.txt`.
- **Ubah:** `pln_api_server/server.py` (REGION, PHOTO_DIRS, /health), `petugas_client/batch_submit.py` (_resolve_pln_url region-aware, import get_region), `petugas_client/install_commands.sh` (fasih-status tampilkan region), `.gitignore` (+`.region`).
- **Tidak disentuh:** logika submit/lookup/koordinat/paradata; mekanisme auth API key.

## Risiko & mitigasi

- **Migrasi foto 555 file (`git mv`)** → commit besar (rename). Diterima; git menangani rename. Verifikasi `load_photos()` menemukan foto di lokasi baru setelah migrasi.
- **Mesin Bontang existing** harus dapat `.region=bontang` (atau andalkan default). Karena default `"bontang"`, mesin lama aman meski `.region` belum dibuat.
- **Foto tetap git-tracked** → tiap mesin membawa foto semua wilayah (bloat git tumbuh seiring wilayah). Diterima untuk B; opsi gitignore-per-wilayah dicatat untuk masa depan (C).
- **Petugas belum update** (masih baca `pln_url.txt`) → tetap jalan karena `pln_url.txt` dipertahankan sebagai fallback; setelah `fasih-update` + set `.region`, pindah ke URL per-wilayah.
