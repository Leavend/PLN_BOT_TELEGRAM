# Deploy ke Komputer PLN (Windows) — Design

**Tanggal:** 2026-07-14
**Status:** Design — approved, siap plan
**Bagian dari:** Multi-region server ops. A (supervisor), B (region identity + isolasi), C (rollout named tunnel) sudah live di main. Ini **D**: menyiapkan mesin target sesungguhnya — komputer PLN Windows per wilayah.

## Masalah

Server tiap wilayah akan jalan di **komputer PLN (Windows 10/11)**, bukan HP Termux. Bootstrap yang ada (`petugas_client/setup_termux.sh`) Termux-only (`pkg install`) dan tidak berguna di Windows. Belum ada jalur yang menyiapkan mesin Windows kosong menjadi server wilayah yang jalan sendiri (dan bertahan setelah reboot).

Selain itu: **jalur Windows di `supervisor.py` belum pernah dieksekusi** — seluruh pengembangan dan test A/B/C dilakukan di macOS. Kode Windows-nya ada (`CREATE_NEW_PROCESS_GROUP`, `Popen.terminate()`), tapi belum terbukti. Ini harus diverifikasi di mesin asli sebagai gerbang pertama, bukan diasumsikan.

## Lingkungan target (dikonfirmasi)

- OS: **Windows 10/11** (komputer kantor PLN).
- Akses: **admin bebas install**, internet keluar bebas (GitHub, Cloudflare, `ap2t.pln.co.id` semua kebuka).
- Server jalan di dalam jaringan PLN → AP2T dijangkau langsung; tunnel dipakai untuk mengekspos server ke HP petugas di luar.

## Non-Goals

- Wrapper PowerShell native untuk `fasih-*` (tetap bash via Git Bash yang ikut Git for Windows).
- Named tunnel / domain (itu sub-proyek C; deploy awal pakai quick tunnel sampai domain siap).
- Provisioning fisik / izin IT PLN.
- Mengubah logika supervisor/server/petugas (A/B/C sudah final) — D hanya menyiapkan mesin dan membuktikan jalur Windows.

## Arsitektur

### Komponen

**1. `deploy/setup_windows.ps1` — bootstrap sekali jalan, idempoten**

Argumen: `-Region <region>` (wajib), `-RepoPath <path>` (opsional, default `$HOME\Fasih-Python-Script`).

Langkah:
1. **Prasyarat via `winget`** (skip bila sudah ada — cek `Get-Command`): `Python.Python.3`, `Git.Git`, `Cloudflare.cloudflared`.
2. **Repo**: bila `-RepoPath` sudah berisi repo → `git pull`; bila belum → `git clone https://github.com/Leavend/PLN_BOT_TELEGRAM.git`.
3. **Dependencies**: `python -m pip install -r requirements.txt` + `flask` + `py7zr`.
4. **Config region**: tulis `.region` = region; `mkdir house_photos\<region>` (bila belum ada).
5. **`.env`**: bila belum ada, buat dari prompt — `PLN_API_KEYS` (key wilayah) dan `PLN_API_PORT` (default 8900). Bila `.env` sudah ada, JANGAN ditimpa (idempoten) — cukup laporkan key mana yang belum diisi.
6. **Verifikasi jalur Windows**: jalankan `python supervisor.py --once`. Ini gerbang utama — bukti `spawn`/`kill`/`recover` benar-benar jalan di Windows. Bila gagal → script berhenti dengan pesan jelas (jangan lanjut daftarkan auto-start).

Script mencetak ringkasan + langkah lanjut (`fasih-login`, cek `/health`, daftarkan auto-start).

**2. Auto-start saat boot — Task Scheduler**

`deploy/register_autostart.ps1` (atau langkah di dalam setup): daftarkan scheduled task `FasihSupervisor`:
- Trigger: **At startup** (bukan at-logon — komputer PLN bisa reboot tanpa ada yang login).
- Action: `python.exe <RepoPath>\supervisor.py`, working directory `<RepoPath>`.
- Setting: `-RestartCount 3 -RestartInterval 1min` (Task Scheduler restart bila task gagal), `RunLevel Highest`, `-DontStopOnIdleEnd`.
- Idempoten: bila task `FasihSupervisor` sudah ada → hapus lalu daftar ulang (`Unregister-ScheduledTask -Confirm:$false`).

> Task Scheduler hanya jaring pengaman untuk reboot/crash-total. Auto-recover per-service tetap tugas supervisor (A).

**3. `docs/runbook-deploy-windows.md`**

Urutan operator di mesin PLN:
1. Buka PowerShell **as Administrator**.
2. `.\deploy\setup_windows.ps1 -Region bontang` (atau region lain).
3. `python supervisor.py --once` harus lulus (sudah dijalankan script; ini gerbang).
4. `fasih-login` (SSO BPS) lewat Git Bash — isi `fasih_token.json`.
5. Isi `.env`: `PLN_API_KEYS`.
6. `.\deploy\register_autostart.ps1 -RepoPath <path>` → auto-start saat boot.
7. Jalankan `python supervisor.py` (atau restart mesin untuk menguji auto-start).
8. **Verifikasi**: `curl http://localhost:8900/health` → `"region": "<region>"` + `photos` > 0. Lalu ambil URL tunnel dari `logs\tunnel.log`, update `pln_url_<region>.txt`, commit+push agar petugas dapat.

Runbook juga memuat: catatan quick-tunnel vs named-tunnel (arahkan ke runbook C setelah domain siap), dan troubleshooting (winget tidak ada di Windows lama → link installer manual; `python` vs `py` launcher).

### Struktur file

- **Baru:** `deploy/setup_windows.ps1`, `deploy/register_autostart.ps1`, `docs/runbook-deploy-windows.md`.
- **Tidak disentuh:** `supervisor.py`, `region.py`, `setup_region.py`, `pln_api_server/`, `petugas_client/` — D hanya menyiapkan mesin.

## Error handling

- `winget` tidak tersedia (Windows lama / kebijakan) → script mendeteksi, cetak link installer manual per tool, berhenti dengan exit code non-nol.
- Prasyarat sudah terpasang → skip install (idempoten), lanjut.
- `.env` sudah ada → jangan timpa; laporkan key yang kosong.
- `supervisor.py --once` gagal → **berhenti**, jangan daftarkan auto-start; cetak isi error + arahkan ke `logs\`.
- Repo sudah ada tapi bukan git repo → berhenti dengan pesan jelas (jangan clone menimpa).
- Task Scheduler sudah punya `FasihSupervisor` → unregister lalu register ulang.

## Testing / verifikasi

PowerShell tidak bisa dites dari lingkungan pengembangan (macOS), dan tidak ada test host Windows. Karena itu **verifikasi = langkah runbook yang dijalankan di mesin PLN asli**, dengan gerbang berlapis:

1. `python supervisor.py --once` → self-check A (spawn → kill → recover → stop bersih) di Windows. **Gerbang utama**: membuktikan jalur Windows yang selama ini belum teruji.
2. `curl http://localhost:8900/health` → `"region"` benar + `photos` > 0 (membuktikan B region-aware jalan).
3. `python -c "import supervisor; print(supervisor.tunnel_cmd('<region>')[2])"` → `--url` (quick tunnel, karena `.tunnel_named` belum ada) — membuktikan C fallback benar di Windows.
4. Reboot mesin → supervisor hidup sendiri (membuktikan Task Scheduler).

Yang BISA dites dari sini: tidak ada kode Python baru di D, jadi suite pytest yang ada (35 test) tetap hijau dan tidak perlu ditambah. Script PowerShell diverifikasi lewat review + eksekusi di lokasi.

## Risiko & mitigasi

- **Jalur Windows supervisor belum teruji** → gerbang `--once` di langkah 3 setup. Bila gagal, D berhenti di situ dan kita perbaiki `supervisor.py` sebelum lanjut (kemungkinan area: `os.killpg` tidak ada di Windows — kode sudah bercabang `os.name == "nt"`, tapi belum terbukti; `Popen.terminate()` tidak membunuh cucu proses cloudflared → tunnel yatim, sudah dicatat sebagai risiko diterima di A).
- **`python` vs `py` launcher di Windows** → runbook menyebut keduanya; script memakai `python` hasil winget (ada di PATH), dan `supervisor.py` sendiri memakai `sys.executable` sehingga anak proses konsisten.
- **Quick tunnel URL muter tiap restart** → tiap reboot komputer PLN, URL ganti → petugas putus sampai `pln_url_<region>.txt` di-update+push. Ini alasan kuat menyegerakan named tunnel (C) begitu domain siap. Runbook menegaskan ini.
- **Repo publik** → siapa pun bisa clone; `.env` (API key) tetap lokal per mesin dan tidak masuk git. Tidak diubah oleh D.
