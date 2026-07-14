# Rollout Wilayah via Named Tunnel (Sub-proyek C)

**Tanggal:** 2026-07-14
**Status:** Design — approved, siap plan
**Bagian dari:** Multi-region server ops (A → B → C). A = supervisor (DONE, di main). B = region identity + isolasi (DONE, di main). Ini **C**.

## Masalah

Wilayah baru (Samarinda, Balikpapan, Wahau) perlu di-onboard sebagai server penuh. Hambatan utama: URL tunnel. Quick tunnel (trycloudflare) mengganti URL tiap restart, dan auto-push URL ke `main` akan memicu semua server restart (via auto-pull supervisor A) + race push. Solusi: **named tunnel** dengan hostname permanen `<region>.<domain>` — `pln_url_<region>.txt` di-set sekali, tidak pernah berubah. Ini menghilangkan seluruh masalah propagasi URL.

## Keputusan

- **Named tunnel** per wilayah → hostname stabil `<region>.<domain>`. `pln_url_<region>.txt = https://<region>.<domain>`, commit sekali.
- Supervisor menjalankan named tunnel (`cloudflared tunnel run <region>`) bila wilayah sudah dikonfigurasi (penanda file `.tunnel_named`), selain itu quick tunnel (Bontang legacy, tidak putus).
- Onboarding lokal diotomatisasi lewat `setup_region.py`; langkah interaktif Cloudflare (beli domain, `cloudflared tunnel login`) tetap manual — dituntun runbook.

## Non-Goals

- Membeli domain / membuat akun Cloudflare / `cloudflared tunnel login` secara otomatis (interaktif, milik user).
- Provisioning fisik mesin (HP/VPS), instalasi OS/cloudflared.
- Auto-capture / auto-push URL (dihilangkan oleh named tunnel).

## Prasyarat (manual, milik user — dituntun runbook)

1. Punya domain, tambahkan ke Cloudflare, zone aktif.
2. Di tiap mesin wilayah: install `cloudflared`, jalankan `cloudflared tunnel login` (buka browser, auth ke akun CF).

## Arsitektur

### Komponen yang dibangun

**1. Supervisor tunnel region-aware (`supervisor.py`)**
- Tambah `from region import get_region` + `REGION = get_region()` (supervisor tetap stdlib-only — `region.py` tanpa dependency).
- Fungsi baru `tunnel_cmd(region, repo_root=REPO_ROOT) -> list[str]`:
  - Bila `<repo_root>/.tunnel_named` ADA → `["cloudflared", "tunnel", "run", region]` (named).
  - Selain itu → `["cloudflared", "tunnel", "--url", f"http://localhost:{PORT}"]` (quick, legacy).
- `DEFAULT_SERVICES` entry `tunnel` memakai `tunnel_cmd(REGION)` (bukan cmd statik). `REGION` + `tunnel_cmd` didefinisikan sebelum `DEFAULT_SERVICES`.
- `.tunnel_named` = penanda per-mesin (gitignored), dibuat oleh `setup_region.py` setelah named tunnel dikonfigurasi.

**2. `setup_region.py` (script onboarding)**
- `write_region_config(region, domain, repo_root, cloudflared_dir, named_ok) -> dict`: menulis (a) `<repo_root>/.region` = region, (b) buat `<repo_root>/house_photos/<region>/`, (c) `<repo_root>/pln_url_<region>.txt` = `https://<region>.<domain>`, (d) `<cloudflared_dir>/config.yml` (ingress: hostname `<region>.<domain>` → `http://localhost:<PORT>`), dan **(e) touch `<repo_root>/.tunnel_named` HANYA bila `named_ok` True** — supaya supervisor tidak mencoba named tunnel yang belum jadi (kalau `named_ok` False, tanpa marker → tetap quick tunnel, server tetap reachable). Return path yang ditulis. Fungsi murni-fs, ditest dengan temp dir.
- `run_cloudflared(region, hostname) -> bool`: shell ke `cloudflared tunnel create <region>` (skip bila tunnel sudah ada) + `cloudflared tunnel route dns <region> <hostname>`. Return True bila sukses; bila `cloudflared` tidak ada di PATH atau gagal → cetak instruksi manual, return False (tidak crash).
- `main(argv)`: parse `<region> <domain>`, `named_ok = run_cloudflared(...)`, lalu `write_region_config(..., named_ok=named_ok)`, cetak langkah lanjut (set `PLN_API_KEYS` di `.env`, commit `pln_url_<region>.txt`, jalankan `python3 supervisor.py`). Bila `named_ok` False, cetak peringatan: login/create tunnel dulu lalu jalankan ulang untuk mengaktifkan named tunnel.
- Wrapper `fasih-setup-region` ditambah ke `petugas_client/install_commands.sh`.

**3. Runbook (`docs/runbook-rollout-wilayah.md`)**
Langkah lengkap:
- One-time: daftar domain → add ke Cloudflare → zone aktif.
- Per mesin wilayah: install cloudflared → `cloudflared tunnel login` → `python3 setup_region.py <region> <domain>` → set `PLN_API_KEYS` di `.env` → `git add pln_url_<region>.txt && commit && push` → `python3 supervisor.py`.
- Catatan deploy: jalankan `python3 server.py` lewat supervisor (bukan gunicorn) supaya `load_photos()` jalan (item deferred dari B).
- Verifikasi: `curl https://<region>.<domain>/health` menampilkan `"region": "<region>"`.

### `.gitignore`
Tambah `.tunnel_named` (penanda per-mesin). `~/.cloudflared/` di luar repo (tidak perlu ignore). `.env` sudah ignored.

### Alur data (setelah onboarding)
Petugas wilayah X `.region=X` → baca `pln_url_X.txt` = `https://X.<domain>` (stabil) → hit server X. Server X `.region=X` sajikan `house_photos/X/`. URL tidak pernah berubah → tidak ada propagasi/restart-cascade.

## Error handling

- `cloudflared` tidak terpasang → `run_cloudflared` cetak instruksi + return False; `write_region_config` tetap jalan (file lokal ditulis), supervisor tetap quick-tunnel sampai `.tunnel_named` dibuat. Tidak crash.
- `setup_region.py` dijalankan ulang (idempoten) → `tunnel create` di-skip bila sudah ada; file ditimpa dengan nilai sama.
- `.tunnel_named` ada tapi named tunnel belum benar (mis. login belum) → `cloudflared tunnel run` gagal → supervisor auto-recover mencoba lagi + `logs/tunnel.log` menunjukkan error (perilaku A). Runbook mengingatkan urutan: login dulu, baru setup.
- Bontang existing tanpa `.tunnel_named` → tetap quick tunnel, tidak berubah (backward-compatible).

## Testing / verifikasi

`test_setup_region.py` (pytest, tanpa jaringan/cloudflared):
1. `write_region_config("balikpapan", "contoh.com", tmp_repo, tmp_cfdir, named_ok=True)` → `.region` berisi `balikpapan`; `pln_url_balikpapan.txt` = `https://balikpapan.contoh.com`; `house_photos/balikpapan/` ada; `config.yml` memuat hostname `balikpapan.contoh.com` + `http://localhost:<PORT>`; `.tunnel_named` ada.
2. `write_region_config(..., named_ok=False)` → semua file lain ditulis TAPI `.tunnel_named` TIDAK dibuat (supervisor tetap quick tunnel).
3. `run_cloudflared` dengan `cloudflared` absen → return False, tidak raise.

Tambah ke `test_supervisor.py`:
3. `tunnel_cmd("balikpapan", tmp_repo)` tanpa `.tunnel_named` → quick tunnel (`--url ...`).
4. `tunnel_cmd("balikpapan", tmp_repo)` dengan `.tunnel_named` ada → `["cloudflared","tunnel","run","balikpapan"]`.

Runbook: diverifikasi manual saat rollout (curl /health).

## File yang disentuh

- **Baru:** `setup_region.py`, `test_setup_region.py`, `docs/runbook-rollout-wilayah.md`.
- **Ubah:** `supervisor.py` (REGION import + `tunnel_cmd` + DEFAULT_SERVICES tunnel), `test_supervisor.py` (2 test tunnel_cmd), `petugas_client/install_commands.sh` (wrapper fasih-setup-region), `.gitignore` (+`.tunnel_named`).
- **Tidak disentuh:** logika submit/lookup/koordinat; server/petugas region (B); proses supervisi lain (A).

## Risiko & mitigasi

- **Domain belum ada** → C dibangun sekarang, deploy named tunnel menunggu domain. Bontang tetap jalan (quick tunnel). Diterima.
- **Named tunnel belum siap (login/create gagal)** → `run_cloudflared` return False → `named_ok=False` → `.tunnel_named` TIDAK dibuat → supervisor tetap quick tunnel, server tetap reachable. Runbook mengarahkan: login + jalankan ulang `setup_region.py` setelah sukses untuk mengaktifkan named tunnel (baru saat itu `.tunnel_named` dibuat).
- **Supervisor stdlib-only** → `from region import get_region` aman (region.py stdlib-only). Tidak menambah dependency.
