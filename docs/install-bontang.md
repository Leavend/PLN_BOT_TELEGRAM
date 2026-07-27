# Install Sheet — Server Wilayah Bontang ke Komputer PLN (Windows)

Panduan tunggal, urut, copy-paste. Memasang region **bontang** dari nol ke satu PC Windows PLN.

> **Peringatan kejujuran:** jalur PowerShell ini belum pernah dites di Windows asli — gerbang
> `python supervisor.py --once` HANYA membuktikan mekanik proses (spawn/kill proses dummy),
> jadi verifikasi layanan nyata di Bagian 3 **wajib**, bukan opsional.

Disusun dari audit adversarial 5-lensa + verify pass (blocker & gap sudah difold ke sini).

---

## 0. Premis penting (baca 30 detik)

- **Tidak ada login browser cloudflared di install ini.** Bontang pakai **quick tunnel** (tidak ada `.tunnel_named` → `supervisor.tunnel_cmd()` selalu `cloudflared tunnel --url ...`). Quick tunnel = URL **acak yang ganti tiap tunnel restart** (lihat Bagian 4 — ini konsekuensi besar).
- **`fasih-login` (SSO BPS) itu opsional** — cuma perlu kalau PC ini juga dipakai jadi klien uji petugas. Server-nya sendiri (`server.py`) tidak pernah menyentuh `fasih_token.json`.
- **URL yang tercommit sekarang SUDAH MATI.** `pln_url_bontang.txt` isinya URL trycloudflare lama; dijamin basi begitu server baru start. Jangan uji petugas pakai itu sebelum Bagian 4 beres.
- **Auth sekarang AKTIF.** Server memuat `.env` → kalau `PLN_API_KEYS` tidak memuat kunci petugas, semua petugas kena 401.

---

## 1. Pre-flight — konfirmasi SEBELUM menyentuh box

| # | Cek | Cara | Harus |
|---|---|---|---|
| 1 | OS | `winver` | Windows 10 (1803+) / 11 |
| 2 | Admin | PowerShell → klik kanan **Run as Administrator** | judul window: "Administrator: Windows PowerShell" |
| 3 | Internet | `curl.exe -I https://github.com` ; `curl.exe -I https://ap2t.pln.co.id` | dapat respons HTTP (bukan timeout) |
| 4 | Python lama | `python --version` | Kalau sudah ada, **wajib ≥ 3.10** (server.py sekarang import-safe di 3.9, tapi paket lain lebih aman di 3.10+). Kalau < 3.10 atau membuka Microsoft Store → nonaktifkan: **Settings > Apps > Advanced app settings > App execution aliases** → matikan `python.exe` & `python3.exe`. |
| 5 | **Nilai `PLN_API_KEYS`** | siapkan string kunci | **Harus MEMUAT** tiap `PLN_API_KEY` yang sudah ada di HP petugas Bontang (server split koma jadi set; klien kirim satu `X-API-Key`). Ketik kunci baru yang beda → SEMUA petugas 401. **Reuse kunci Bontang yang sudah dipakai — jangan bikin baru.** |
| 6 | **Token Telegram** (kalau PC ini juga jalanin bot) | siapkan `TELEGRAM_BOT_TOKEN` + username | Tanpa token, `telegram_bot.py` `exit(1)` lalu di-respawn supervisor tiap 15 dtk **selamanya, senyap**. Kalau PC ini **server-only** (tanpa bot), lihat 2.2b untuk drop bot. |
| 7 | **Identitas + kredensial git** | lihat catatan | Bagian 4 perlu `git commit`+`push`. Box fresh **tidak punya** `user.name/email` maupun kredensial tulis → operator **tidak bisa push**. **Rencana default: operator kirim URL baru ke OWNER, owner yang commit+push** (lihat Bagian 4). Kalau mau operator push sendiri, pre-provision dulu (catatan bawah). |
| 8 | Ruang & waktu | — | Fresh clone menarik **~1061 foto Bontang (~104 MB)** SEBELUM apa pun jalan. Di jaringan kantor ini langkah paling lambat — bukan hang, tunggu. |

> **Kalau operator harus push sendiri** (bukan owner): sebelum install, di box jalankan
> `git config --global user.email "x@y.z"` + `git config --global user.name "Bontang PLN"`, dan
> simpan PAT ber-izin **write** ke repo (`git config --global credential.helper store` lalu `git push`
> sekali untuk cache). Tanpa ini `git push` gagal 403 / minta login OAuth interaktif.

---

## 2. Instalasi — copy-paste berurutan

### 2.1 Bootstrap (PowerShell **as Administrator**)

```powershell
cd $HOME
curl.exe -fLO https://raw.githubusercontent.com/Leavend/PLN_BOT_TELEGRAM/main/deploy/setup_windows.ps1
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass -Force
.\setup_windows.ps1 -Region bontang
```

Script (gotcha sudah tertangani di dalamnya): install Python 3.12/Git/cloudflared via winget (guard stub Store `\WindowsApps\`) → clone repo ke `$HOME\Fasih-Python-Script` (**tarikan 104 MB foto — sabar**) → `git config --system safe.directory` (SYSTEM tidak tolak repo user) → `pip install -r requirements.txt` + `flask py7zr` → tulis `.region=bontang` + folder foto → **prompt `PLN_API_KEYS`** (isi dari Pre-flight #5, **jangan ENTER kosong** = auth fail-open) + `PLN_API_PORT` (ENTER=8900) → gerbang `python supervisor.py --once`.

> **`winget` tidak dikenal** → install manual (link dicetak script), taruh di PATH, ulang.
> **"belum di PATH"** → tutup PowerShell, buka ulang as Administrator, ulang (idempoten, `.env` tidak ditimpa).

### 2.2 Lengkapi `.env` (token Telegram TIDAK ditanya script)

```powershell
cd $HOME\Fasih-Python-Script
Add-Content .env "TELEGRAM_BOT_TOKEN=<token_bot>"
Add-Content .env "ALLOWED_TELEGRAM_USERNAMES=<user1,user2>"
Add-Content .env "ADMIN_TELEGRAM_USERNAMES=<admin1>"
Add-Content .env "MAPBOX_ACCESS_TOKEN=<mapbox_token>"
Get-Content .env      # pastikan PLN_API_KEYS TIDAK kosong
```

### 2.2b (Server-only, tanpa bot) — drop service telegram_bot

Kalau PC ini murni server (tidak jalanin bot), hindari crash-loop bot: buat `services.local.json` di root repo (supervisor pakai daftar ini kalau ada):

```powershell
@'
[
  {"name":"pln_server","cmd":["python","pln_api_server/server.py"],"restart_on_update":true,"lock_file":null},
  {"name":"tunnel","cmd":["cloudflared","tunnel","--url","http://localhost:8900"],"restart_on_update":false,"lock_file":null}
]
'@ | Set-Content -Encoding utf8 services.local.json
```

### 2.3 Login BPS (OPSIONAL — cuma kalau PC ini juga klien uji petugas)

Lewat **Git Bash**. **Gotcha:** wrapper `fasih-*` panggil `python3`, installer python.org bikin `python.exe` saja → bikin shim dulu:

```bash
cd ~/Fasih-Python-Script
PYDIR=$(dirname "$(python -c 'import sys;print(sys.executable)')")
cp "$PYDIR/python.exe" "$PYDIR/python3.exe"   # kalau "permission denied": python di Program Files → pakai Git Bash elevated
python3 --version                              # WAJIB jalan sebelum lanjut
bash petugas_client/install_commands.sh
which fasih-login || (touch ~/.bashrc && source ~/.bashrc)
fasih-login                                    # <- browser SSO BPS
fasih-status                                   # harus: 🌏 Wilayah: bontang
```

### 2.4 Auto-start saat boot (PowerShell **as Administrator**, sesi BARU)

```powershell
cd $HOME\Fasih-Python-Script
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass -Force
.\deploy\register_autostart.ps1 -RepoPath "$HOME\Fasih-Python-Script"
```

Task `FasihSupervisor` (At startup, SYSTEM, RunLevel Highest, restart 3x, `ExecutionTimeLimit=PT0S`).

---

## 3. Gerbang verifikasi — bukti sukses (jalankan SEMUA)

> `--once` di 2.1 TIDAK menjalankan service asli (proses dummy `sleep`). Flask rusak / token bot kosong / cloudflared gagal — semua LULUS `--once`. Gate nyata ada di sini.

Jalankan foreground: `cd $HOME\Fasih-Python-Script ; python supervisor.py`. Di window kedua:

### 3.1 Health + region + foto (dengan retry — port belum tentu langsung listen)

```powershell
foreach ($i in 1..5) { try { $r = Invoke-RestMethod http://localhost:8900/health; break } catch { Start-Sleep 3 } }
$r | ConvertTo-Json
```
Harus: `"status":"ok"`, **`"region":"bontang"`**, **`"photos"` ~1061**.

### 3.2 Auth tertutup — unauth = 401 (WAJIB)

```powershell
curl.exe -o NUL -s -w "%{http_code}`n" "http://localhost:8900/api/lookup?idpel=234201167356"
```
Harus **`401`**. Kalau `200/404` → `PLN_API_KEYS` kosong/tak terbaca (fail-open) → perbaiki `.env`, restart.

### 3.2b PLN lookup BENERAN balik data (bukan cuma auth ON)

Auth ON belum berarti lookup jalan — cookie AP2T (`pln_lookup.DEFAULT_COOKIES`) bisa kadaluarsa → lookup ter-otorisasi malah 500, petugas diam-diam "PLN enrichment skipped".

```powershell
curl.exe -s -H "X-API-Key: <SALAH_SATU_PLN_API_KEYS>" "http://localhost:8900/api/lookup?idpel=<IDPEL_12DIGIT_YANG_VALID>"
```
Harus balik profil (nama/alamat terisi), **bukan** 500/404. Kalau 500 → refresh cookie AP2T sebelum go-live.

### 3.3 Tunnel = quick tunnel (fallback benar)

```powershell
python -c "import supervisor; print(supervisor.tunnel_cmd('bontang')[2])"
```
Harus **`--url`**.

### 3.4 Bot hidup (tidak crash-loop)

```powershell
Get-Content logs\telegram_bot.log -Tail 15
```
Tidak boleh ada `TELEGRAM_BOT_TOKEN belum diset` berulang (kecuali sengaja server-only via 2.2b).

### 3.5 Uji reboot (bukti auto-start)

**Ctrl+C** `python supervisor.py` foreground dulu (hindari dua supervisor rebut port 8900 + bot 409). Lalu `Restart-Computer`. Setelah boot, **tanpa login/jalanin apa pun**:

```powershell
foreach ($i in 1..8) { try { Invoke-RestMethod http://localhost:8900/health; break } catch { Start-Sleep 4 } }
(Get-ScheduledTask -TaskName FasihSupervisor).State     # harus: Running
```
Health hijau + State **Running** = lolos. (Catatan: `LastTaskResult`=`267009`/`0x41301` itu NORMAL untuk task jalan-selamanya; `0` malah berarti supervisor KELUAR.)

---

## 4. Post-install — sebarkan URL tunnel

Quick tunnel = URL acak, **ganti tiap tunnel restart** (tiap reboot, tiap Windows Update reboot, tiap supervisor respawn tunnel yang mati). Yang tercommit pasti basi. Ambil yang baru:

```powershell
cd $HOME\Fasih-Python-Script
Select-String -Path logs\tunnel.log -Pattern "trycloudflare.com" | Select-Object -Last 1
```

**Publikasikan URL (pilih satu):**

- **Default — owner yang push** (box fresh tidak punya kredensial git tulis): operator **kirim URL ke owner**; owner update `pln_url_bontang.txt` baris non-komentar + `git commit + push` dari mesin owner yang sudah authenticated. Paling aman.
- **Operator push sendiri** (hanya kalau Pre-flight #7 sudah di-provision): **hentikan supervisor dulu** biar tidak tabrakan `index.lock` dengan auto-pull 15-detik:
  ```powershell
  Stop-ScheduledTask -TaskName FasihSupervisor    # kalau via task; atau Ctrl+C foreground
  # edit pln_url_bontang.txt -> URL baru
  git add pln_url_bontang.txt; git commit -m "update url bontang"; git push
  Start-ScheduledTask -TaskName FasihSupervisor
  ```

Konfirmasi: petugas `fasih-update` → dapat URL baru → `fasih-status` tampil `🏥 PLN Server: ✅ online`.

> **Ini bukan sekali seumur hidup.** URL churn tiap reboot. **Sangat disarankan pindah ke named tunnel**
> (hostname permanen, sekali set) begitu domain siap — lihat `docs/runbook-rollout-wilayah.md`.
> Itu MENGHILANGKAN seluruh tarian Bagian 4.

---

## 5. Rollback — mundur aman

1. **Cegah balik saat reboot:** `Unregister-ScheduledTask -TaskName FasihSupervisor -Confirm:$false`
2. **Matikan stack:** `Get-Process python,cloudflared -ErrorAction SilentlyContinue | Stop-Process -Force`
3. **Kalau gara-gara commit buruk:** revert/reset di **origin/main**, bukan lokal — supervisor auto-pull `origin/main` tiap 15 dtk; `git reset --hard` lokal akan ditimpa ulang.
4. **Kalau gara-gara auth mismatch:** perbaiki nilai `PLN_API_KEYS`/`PLN_API_KEY` yang benar lalu restart. **JANGAN** blank-kan `PLN_API_KEYS` untuk "sementara buka" — server fail-open → auth mati total.
5. **Install ulang bersih:** hanya setelah task + proses berhenti, aman jalankan ulang `setup_windows.ps1` (idempoten).

> Jangan `Start-ScheduledTask` sambil ada `python supervisor.py` manual — dua Flask rebut 8900 (satu mati senyap), dua poller Telegram → `409 Conflict`. Satu saja.
> `deploy/migrate_to_supervisor.sh` **bukan** untuk jalur ini (itu migrasi stack Linux/mac lama, bukan fresh Windows).

---

**File terkait:** `deploy/setup_windows.ps1`, `deploy/register_autostart.ps1`, `supervisor.py`, `pln_api_server/server.py`, `petugas_client/install_commands.sh`, `pln_url_bontang.txt`, `.env` (gitignored).
