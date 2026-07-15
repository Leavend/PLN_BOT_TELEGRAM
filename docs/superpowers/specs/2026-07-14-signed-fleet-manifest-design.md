# Signed Fleet Manifest — Verify Core (Sub-proyek E1)

**Tanggal:** 2026-07-14
**Status:** Design — approved, siap plan
**Bagian dari:** Fleet control + licensing (E). A/B/C/D live di main. Ini **E1** = inti crypto (sign + verify). Menyusul: E2 (fingerprint + enrollment), E3 (supervisor patuh), E4 (CLI owner `fasih-control`), E5 opsional (obfuscation).

## Masalah

Owner ingin: (1) kontrol per-region atas server yang menjalankan codebase-nya (on/off/versi untuk Bontang/Balikpapan/Samarinda/Berau), dan (2) codebase yang dicopy/di-clone **tidak bisa jalan tanpa izin owner**. Mekanisme yang benar untuk keduanya BUKAN mengenkripsi byte kode (mesin admin bisa dekripsi), melainkan **otorisasi bertanda-tangan**: kode menolak jalan kecuali melihat manifest yang ditandatangani owner yang mengizinkan mesin ini.

E1 membangun fondasinya: sebuah manifest JSON yang ditandatangani (Ed25519) plus modul verifikasi fail-closed. Belum menyentuh supervisor (E3) atau fingerprint asli (E2) — E1 hanya menyediakan primitive "manifest ini asli dari owner, dan mesin dengan fingerprint X di region Y diizinkan, dengan state enabled/pin".

## Prinsip (mengikat desain)

- **Signed control plane** — hanya owner (pemegang kunci privat) bisa menghasilkan manifest sah. Ed25519.
- **Fail-closed** — tanda tangan invalid / manifest hilang / kadaluarsa / mesin tak terdaftar → **tidak diizinkan**. Tidak pernah fail-open.
- **Kunci privat offline** — tidak pernah masuk repo atau mesin server. Hanya kunci publik yang tertanam di kode (aman terbuka; hanya untuk verifikasi).
- **Tanda tangan atas byte mentah** `control.json` (detached signature) — tidak ada ambiguitas kanonikalisasi.

## Non-Goals (di luar E1)

- Fingerprint mesin sungguhan + cara enroll → E2 (E1 memakai fingerprint sebagai string opaque).
- Supervisor membaca/menegakkan manifest (start/stop/pin) → E3.
- CLI owner untuk edit+sign+push → E4.
- Obfuscation → E5.
- Membuat repo private / rotasi kunci operasional → tindakan owner (dipandu, bukan kode).

## Arsitektur

### Skema manifest (`control.json`, di repo root)

```json
{
  "not_after": "2026-08-01T00:00:00Z",
  "regions": {
    "bontang":    { "enabled": true,  "pin": null,   "machines": ["<fp>"] },
    "balikpapan": { "enabled": true,  "pin": null,   "machines": ["<fp>"] },
    "samarinda":  { "enabled": false, "pin": null,   "machines": ["<fp>"] },
    "berau":      { "enabled": true,  "pin": "<sha>", "machines": ["<fp>"] }
  }
}
```

- `not_after`: ISO-8601 UTC. Mesin menolak bila waktu sekarang melewatinya (memaksa owner tanda-tangani ulang berkala → membunuh copy-an basi).
- `regions.<region>.machines`: daftar fingerprint yang diizinkan menjalankan region itu.
- `enabled`/`pin`: state operasional (diterapkan di E3; E1 hanya mengembalikannya).

### `control.sig` (di repo root)

Detached signature Ed25519 atas **byte mentah** `control.json`, disimpan **hex** satu baris.

### Komponen yang dibangun

**1. `fleet.py` — modul verifikasi (tertanam di kode server + client)**
- `PUBLIC_KEY_HEX = ""` — konstanta kunci publik owner (hex 32-byte). Placeholder kosong sampai keypair dibuat; **kosong → verify selalu gagal (fail-closed)**.
- `verify_signature(data: bytes, sig_hex: str, pubkey_hex: str) -> bool` — Ed25519 verify; return False pada error apa pun (pubkey/sig/format).
- `load_and_verify(repo_root, pubkey_hex=PUBLIC_KEY_HEX) -> dict | None` — baca `control.json` (byte mentah) + `control.sig` (hex); verifikasi; parse JSON; return dict manifest, atau `None` bila file hilang / sig invalid / JSON rusak.
- `authorize(manifest, region, fingerprint, now=None) -> tuple[bool, str, dict]` — `now` default `datetime.now(timezone.utc)`. Cek berurutan (fail-closed, alasan spesifik):
  1. `not_after` ada & `now <= not_after` → else `(False, "expired", {})`.
  2. `region` ada di `manifest["regions"]` → else `(False, "region tak terdaftar", {})`.
  3. `fingerprint` ada di `regions[region]["machines"]` → else `(False, "mesin tak terotorisasi", {})`.
  4. Sukses → `(True, "ok", {"enabled": <bool>, "pin": <str|None>})`.

**2. `fleet_sign.py` — tool owner (offline, tidak pernah jalan di server)**
- `python3 fleet_sign.py gen-key <priv_path>` — generate keypair Ed25519; simpan kunci privat (PEM, `chmod 600`) ke `priv_path`; cetak `PUBLIC_KEY_HEX` untuk ditempel ke `fleet.py`.
- `python3 fleet_sign.py sign <control.json> <priv_path>` — tulis `control.sig` (hex) di direktori yang sama, signature atas byte `control.json`.

### `.gitignore`
Tambah pola kunci privat (`*.fleetkey`, `.fasih_fleet_key*`) — kunci privat **tidak pernah** ke repo. `control.json` + `control.sig` **di-track** (dibaca fleet lewat git).

### `requirements.txt`
Tambah `cryptography` (sudah tersedia sebagai transitive; jadikan eksplisit — E1 bergantung padanya untuk Ed25519).

## Error handling (semua fail-closed)

- `PUBLIC_KEY_HEX` kosong → `verify_signature`/`load_and_verify` gagal → `load_and_verify` return `None`. (Sebelum owner menempel kunci publik, sistem menolak semua — aman; E3 akan menangani transisi Bontang lama tanpa mematikan mendadak, itu urusan E3.)
- `control.json`/`control.sig` hilang → `load_and_verify` return `None`.
- Sig tidak cocok / hex rusak / JSON rusak → `None`.
- `not_after` tidak ada / format salah → `authorize` return `(False, ...)`.
- `now` naif (tanpa tz) vs `not_after` aware → `authorize` menormalkan `not_after` ke UTC-aware; bandingkan aware-vs-aware (hindari TypeError).

## Testing / verifikasi

`test_fleet.py` (pytest, tanpa jaringan, keypair EPHEMERAL dibuat di test — tidak butuh kunci nyata):
1. `verify_signature`: sign data dengan priv ephemeral → `verify_signature(data, sig, pub)` True; data diubah 1 byte → False; pubkey lain → False.
2. `load_and_verify`: tulis `control.json` + `control.sig` (ditandatangani) di tmp → return dict; ubah `control.json` setelah sign → `None`; `control.sig` hilang → `None`.
3. `authorize`: manifest dengan region+fp terdaftar & `not_after` masa depan → `(True, "ok", {...})`; fp tak terdaftar → `(False, "mesin tak terotorisasi", {})`; region tak ada → `(False, ...)`; `not_after` masa lalu → `(False, "expired", {})`.
4. `authorize` mengembalikan `enabled`/`pin` dari region yang benar.

Semua test memakai `pubkey_hex=<ephemeral>` (bukan `PUBLIC_KEY_HEX` yang masih kosong), sehingga lulus tanpa keypair produksi.

Suite existing (35 test) tetap hijau — E1 menambah file baru, tidak mengubah kode A/B/C/D.

## File yang disentuh

- **Baru:** `fleet.py`, `fleet_sign.py`, `test_fleet.py`.
- **Ubah:** `requirements.txt` (+`cryptography`), `.gitignore` (+pola kunci privat).
- **Tidak disentuh:** supervisor/server/petugas/region (E1 belum menegakkan apa pun — itu E3).

## Risiko & mitigasi

- **Kunci privat bocor** → siapa pun bisa menandatangani manifest → seluruh kontrol jatuh. Mitigasi: kunci privat offline, `chmod 600`, di-gitignore, hanya di mesin owner; rotasi = ganti `PUBLIC_KEY_HEX` + tanda-tangani ulang.
- **`PUBLIC_KEY_HEX` masih kosong saat E1 di-merge** → `load_and_verify` menolak semua. Ini AMAN untuk E1 karena E1 belum menegakkan apa pun (tidak ada pemanggil di jalur runtime). E3 yang menambahkan penegakan HARUS memastikan kunci publik sudah terisi + manifest sah sudah ada sebelum mengaktifkan fail-closed, agar tidak mematikan Bontang mendadak. Dicatat sebagai prasyarat E3.
- **Ceiling jujur** (dari diskusi): staff admin yang sangat teknis bisa menambal keluar pemanggilan `authorize` dari kode (kode terbaca di mesin mereka). Tanda tangan membuat pemalsuan izin mustahil; penghapusan cek tetap mungkin bagi ahli. Obfuscation (E5) menaikkan palang. Proporsional untuk ancaman "staff PLN mencoba clone/run sendiri".
