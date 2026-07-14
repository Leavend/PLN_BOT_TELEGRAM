# Self-Updating Supervisor (Sub-proyek A)

**Tanggal:** 2026-07-14
**Status:** Design — approved, siap plan
**Bagian dari:** Multi-region server ops (A → B → C). Ini **A**. B = region identity + isolasi data. C = rollout multi-wilayah.

## Masalah

Tiap mesin wilayah bakal jalanin full stack: `pln_api_server/server.py` (backend foto + AP2T lookup), `telegram_bot.py`, dan cloudflared tunnel. Saat ada update di repo GitHub, PLN server sekarang **harus di-restart manual** — beda dengan `telegram_bot.py` yang udah punya `auto_updater.sh` (git-poll → pull → restart). Butuh satu supervisor yang auto-update **seluruh stack** dan jaga semua proses tetap hidup, seragam di fleet heterogen (Termux Android, laptop Windows/Git Bash, VPS Linux).

## Non-Goals (sengaja di luar A)

- **Region-awareness** (foto/AP2T/API-key per wilayah) → sub-proyek B.
- **Auto-capture URL cloudflared + commit `pln_url.txt`** → side-effectful (butuh git cred di server); URL jarang berubah karena tunnel tidak di-restart saat code update. Tetap manual commit URL untuk sekarang.
- **Rollout mesin baru** (Samarinda/Balikpapan/Wahau) → sub-proyek C.

## Pendekatan yang dipilih

**Supervisor Python (`supervisor.py`)** yang memegang handle `subprocess.Popen` tiap service.

Alternatif yang ditolak:
- Perluas `auto_updater.sh` (bash) ke N service — kerumitan array bash + PID-hunting per-OS tidak layak dirawat. (Justru 90% kerumitan bash yang ada = nyari/bunuh PID by name, yang jadi gratis kalau Popen yang pegang PID.)
- Process manager (pm2/systemd/supervisord) — fleet heterogen bikin tidak seragam; glue git-poll tetap harus custom. Overkill.

## Arsitektur

Satu proses `supervisor.py` per mesin, mengawasi daftar service.

### Definisi service

Default `SERVICES` (list of dict) di dalam `supervisor.py`:

| name | cmd | restart_on_update | needs_lock |
|---|---|---|---|
| `pln_server` | `python pln_api_server/server.py` | ✔ | ✗ |
| `telegram_bot` | `python telegram_bot.py` | ✔ | ✔ (`bot_active_runs.lock`) |
| `tunnel` | `cloudflared tunnel --url http://localhost:8900` | ✗ | ✗ |

Tiap service: `{name, cmd (list argv), restart_on_update (bool), needs_lock (bool)}`.

**Override per-mesin:** jika `services.local.json` (gitignored) ada di repo root, dipakai menggantikan default `SERVICES` — supaya mesin tanpa bot / tanpa tunnel bisa trim tanpa edit code. Format = JSON array of the same dict shape. Port tunnel diambil dari env `PLN_API_PORT` (default 8900) agar konsisten dengan server.

### Loop utama

Interval poll `INTERVAL` detik (default 15).

```
start semua service (Popen), simpan handle di dict {name: Popen}
loop:
  sleep INTERVAL
  git fetch origin <branch>
  if local_hash != remote_hash:
      if ada service needs_lock yang lock-file-nya aktif:
          log "tunda update — bot lagi batch (N aktif)"; continue
      git pull origin <branch>
      for svc where restart_on_update: stop(svc); start(svc)
      # tunnel TIDAK di-restart → URL tetap, petugas tidak putus
  for svc in services:
      if handle.poll() is not None:   # mati
          log "auto-recover <name>"; start(svc)
```

### Manajemen proses (lintas OS)

Satu helper start/stop:
- **Start:** `Popen(cmd, stdout=logfile, stderr=STDOUT, ...)` dengan process-group:
  - POSIX: `start_new_session=True` (setsid) → bisa `os.killpg` seluruh anak-cucu (penting untuk cloudflared yang bisa spawn child).
  - Windows: `creationflags=CREATE_NEW_PROCESS_GROUP`.
- **Stop:** POSIX `os.killpg(os.getpgid(pid), SIGTERM)` → tunggu 5s → `SIGKILL`. Windows `Popen.terminate()` → tunggu → `kill()`.
- **Log:** tiap service nulis ke `logs/<name>.log` (append, dibuat kalau belum ada). `logs/` gitignored.

### Lock (anti-restart saat batch)

Reuse `bot_active_runs.lock` yang sudah dipakai `telegram_bot.py`. Service dengan `needs_lock=True` menunda restart-on-update selama file lock ada. Auto-recovery (proses mati) tetap jalan tanpa peduli lock (proses sudah mati, bukan di-restart paksa). Isi lock (jumlah batch aktif) hanya untuk pesan log.

### Shutdown

Handler `SIGINT`/`SIGTERM` → stop semua service (killpg/terminate) → exit. Idempoten.

## Entry point & migrasi

- Baru: `python3 supervisor.py` (repo root).
- `auto_updater.py` dan `auto_updater.sh` → **deprecated**. Diganti oleh supervisor. Tinggalkan file lama untuk transisi (harmless), tandai deprecated di header + README; hapus di PR lanjutan setelah semua mesin migrasi.
- Update `README_WORKFLOW.md` / `pln_api_server/README.md`: cara jalanin stack = `python3 supervisor.py`.

## Error handling

- `git fetch`/`git pull` gagal (offline) → log, skip siklus, coba lagi interval berikut (jangan crash). Persis pola updater lama.
- Service gagal start (cmd tidak ada, mis. cloudflared belum terinstall) → log error, tandai service itu disabled untuk siklus ini, JANGAN bikin supervisor mati. Service lain tetap jalan.
- `services.local.json` rusak/invalid → log, fallback ke default `SERVICES`.

## Testing / verifikasi (runnable check)

`python3 supervisor.py --once` (mode self-check, tanpa git-poll tak berujung):
1. Start satu service dummy (`sleep`/loop pendek) dari daftar test.
2. Bunuh manual, pastikan `poll()` mendeteksi mati lalu auto-recover (PID berubah).
3. Assert: PID awal ≠ PID setelah recover; stop bersih tidak menyisakan proses.
Exit 0 kalau lolos. Ini satu check assert-based sesuai aturan (logic non-trivial: supervisi + recovery).

## File yang disentuh

- **Baru:** `supervisor.py`, `services.local.json` (opsional, gitignored — hanya contoh/`.example`).
- **Ubah:** `.gitignore` (+`logs/`, `+services.local.json`), `README_WORKFLOW.md`, `pln_api_server/README.md`, header `auto_updater.py`/`auto_updater.sh` (deprecated note).
- **Tidak disentuh:** logika submit/lookup/koordinat.

## Risiko & mitigasi

- **Restart tunnel tak sengaja → URL ganti → petugas putus.** Mitigasi: `tunnel.restart_on_update=False` by default; hanya auto-recover kalau benar-benar mati.
- **Windows process-group kill tidak bersih (child tunnel yatim).** Mitigasi: `CREATE_NEW_PROCESS_GROUP` + terminate; kalau ada sisa, auto-recovery siklus berikut menormalkan (tunnel baru diangkat). Diterima untuk A.
- **Termux Android membunuh proses background (Doze).** Di luar scope A (setting OS); dicatat untuk C (wake-lock / termux-services).
