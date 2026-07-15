# Deploy ke Komputer PLN (Windows) Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Komputer PLN Windows kosong bisa disiapkan jadi server wilayah yang jalan sendiri (dan hidup lagi setelah reboot) lewat satu script bootstrap + satu script auto-start, dengan gerbang yang membuktikan jalur Windows supervisor benar-benar jalan.

**Architecture:** `deploy/setup_windows.ps1` memasang prasyarat via winget (idempoten), clone/pull repo, install dependency Python, menulis `.region` + `.env`, lalu **berhenti kalau `python supervisor.py --once` gagal** — gerbang yang membuktikan jalur Windows (yang selama ini hanya dites di macOS). `deploy/register_autostart.ps1` mendaftarkan Task Scheduler at-startup sebagai SYSTEM. Runbook merangkai keduanya + verifikasi di mesin asli.

**Tech Stack:** PowerShell 5.1+ (bawaan Windows 10/11), winget, Python 3.12, Git, cloudflared.

## Global Constraints

- Target: **Windows 10/11**, operator punya **admin** dan internet bebas (GitHub, Cloudflare, ap2t.pln.co.id).
- **D tidak mengubah kode Python apa pun** (A/B/C sudah final). Suite pytest yang ada (35 test) harus tetap hijau sebagai regression gate.
- Script harus **idempoten**: dijalankan ulang tidak merusak (skip tool yang sudah ada, `git pull` bukan clone ulang, `.env` yang sudah ada TIDAK ditimpa, task lama di-unregister sebelum register).
- **Gerbang wajib**: `python supervisor.py --once` harus lulus SEBELUM auto-start didaftarkan. Kalau gagal → berhenti, jangan lanjut.
- Tunnel = **quick tunnel** (URL berputar tiap restart) sampai domain siap → named tunnel (sub-proyek C).
- Bahasa pesan: Indonesia (ikut gaya repo).

## Catatan verifikasi (dibaca sebelum mulai)

**Tidak ada test otomatis untuk D.** PowerShell tidak bisa dijalankan/di-syntax-check dari lingkungan pengembangan (macOS, tanpa `pwsh`), dan tidak ada host Windows di CI. Karena itu:
- Yang bisa dicek di sini: suite pytest tetap 35 hijau (membuktikan D tidak menyentuh Python), dan review manual isi script.
- Yang membuktikan D benar: **langkah runbook di komputer PLN asli** (Task 3), dengan `supervisor.py --once` sebagai gerbang pertama.

Jangan mengarang test PowerShell palsu. Kejujuran soal ini adalah bagian dari deliverable.

---

### Task 1: `deploy/setup_windows.ps1` — bootstrap mesin

**Files:**
- Create: `deploy/setup_windows.ps1`

**Interfaces:**
- Consumes: `requirements.txt`, `supervisor.py --once` (sub-proyek A), `.region` (dibaca `region.py` sub-proyek B).
- Produces: mesin siap + `.region` + `.env` + `house_photos/<region>/`; dipakai Task 2 (`-RepoPath`) dan Task 3 (runbook).

- [ ] **Step 1: Write the script**

Create `deploy/setup_windows.ps1`:

```powershell
<#
.SYNOPSIS
    Bootstrap komputer PLN (Windows) menjadi server wilayah FASIH.
.DESCRIPTION
    Idempoten: aman dijalankan ulang. Memasang prasyarat (winget), clone/pull repo,
    install dependency, menulis .region + .env, lalu menjalankan gerbang
    `supervisor.py --once` yang membuktikan jalur Windows supervisor benar-benar jalan.
.EXAMPLE
    .\deploy\setup_windows.ps1 -Region bontang
#>
[CmdletBinding()]
param(
    [Parameter(Mandatory = $true)][string]$Region,
    [string]$RepoPath = "$HOME\Fasih-Python-Script",
    [string]$RepoUrl  = "https://github.com/Leavend/PLN_BOT_TELEGRAM.git"
)

$ErrorActionPreference = "Stop"
$Region = $Region.Trim().ToLower()

function Info($m) { Write-Host "[*] $m" -ForegroundColor Cyan }
function Ok($m)   { Write-Host "[+] $m" -ForegroundColor Green }
function Warn($m) { Write-Host "[!] $m" -ForegroundColor Yellow }
function Die($m)  { Write-Host "[-] $m" -ForegroundColor Red; exit 1 }

function Refresh-Path {
    $env:Path = [System.Environment]::GetEnvironmentVariable("Path", "Machine") + ";" +
                [System.Environment]::GetEnvironmentVariable("Path", "User")
}

function Ensure-Tool($cmd, $wingetId, $label) {
    if (Get-Command $cmd -ErrorAction SilentlyContinue) { Ok "$label sudah ada"; return }
    Info "Install $label ..."
    winget install --id $wingetId -e --silent --accept-package-agreements --accept-source-agreements
    Refresh-Path
    if (-not (Get-Command $cmd -ErrorAction SilentlyContinue)) {
        Die "$label terpasang tapi belum masuk PATH. Tutup PowerShell, buka lagi sebagai Administrator, jalankan ulang script ini."
    }
    Ok "$label terpasang"
}

# --- 1. winget ---
if (-not (Get-Command winget -ErrorAction SilentlyContinue)) {
    Die @"
winget tidak tersedia di mesin ini (Windows lama / kebijakan IT).
Install manual lalu jalankan ulang script ini:
  Python 3    : https://www.python.org/downloads/windows/  (CENTANG 'Add python.exe to PATH')
  Git         : https://git-scm.com/download/win
  cloudflared : https://github.com/cloudflare/cloudflared/releases (cloudflared-windows-amd64.exe, taruh di PATH)
"@
}

# --- 2. prasyarat ---
Ensure-Tool "python"      "Python.Python.3.12"     "Python 3"
Ensure-Tool "git"         "Git.Git"                "Git"
Ensure-Tool "cloudflared" "Cloudflare.cloudflared" "cloudflared"

# --- 3. repo ---
if (Test-Path $RepoPath) {
    if (-not (Test-Path (Join-Path $RepoPath ".git"))) {
        Die "$RepoPath ada tapi BUKAN git repo. Pindahkan/hapus folder itu lalu jalankan ulang."
    }
    Info "Repo sudah ada — git pull ..."
    Push-Location $RepoPath
    git pull
    Pop-Location
} else {
    Info "Clone repo ke $RepoPath ..."
    git clone $RepoUrl $RepoPath
}
Set-Location $RepoPath
Ok "Repo siap di $RepoPath"

# --- 4. dependency Python ---
Info "Install Python packages ..."
python -m pip install --upgrade pip
python -m pip install -r requirements.txt
python -m pip install flask py7zr
Ok "Dependency terpasang"

# --- 5. config region ---
Set-Content -Path (Join-Path $RepoPath ".region") -Value $Region
$photoDir = Join-Path $RepoPath "house_photos\$Region"
if (-not (Test-Path $photoDir)) { New-Item -ItemType Directory -Path $photoDir -Force | Out-Null }
Ok "Region '$Region' diset (.region + house_photos\$Region)"

# --- 6. .env (JANGAN timpa kalau sudah ada) ---
$envPath = Join-Path $RepoPath ".env"
if (Test-Path $envPath) {
    Warn ".env sudah ada — tidak ditimpa."
    $envTxt = Get-Content $envPath -Raw
    foreach ($k in @("PLN_API_KEYS", "PLN_API_PORT")) {
        if ($envTxt -notmatch "(?m)^\s*$k\s*=\s*\S") { Warn "  $k belum diisi di .env" }
    }
} else {
    $keys = Read-Host "Masukkan PLN_API_KEYS untuk wilayah $Region (pisah koma bila lebih dari satu)"
    $portIn = Read-Host "PLN_API_PORT (ENTER = 8900)"
    if ([string]::IsNullOrWhiteSpace($portIn)) { $portIn = "8900" }
    Set-Content -Path $envPath -Value @("PLN_API_KEYS=$keys", "PLN_API_PORT=$portIn")
    Ok ".env dibuat"
}

# port efektif (untuk pesan verifikasi)
$port = "8900"
$m = Select-String -Path $envPath -Pattern '^\s*PLN_API_PORT\s*=\s*(\d+)' | Select-Object -First 1
if ($m) { $port = $m.Matches[0].Groups[1].Value }

# --- 7. GERBANG: buktikan jalur Windows supervisor jalan ---
Info "Verifikasi supervisor di Windows (self-check) ..."
python supervisor.py --once
if ($LASTEXITCODE -ne 0) {
    Die @"
supervisor.py --once GAGAL di mesin ini.
JANGAN lanjut mendaftarkan auto-start. Jalur Windows supervisor perlu diperbaiki dulu.
Kirim output di atas + isi folder logs\ ke tim.
"@
}
Ok "supervisor --once LULUS — jalur Windows OK"

Write-Host ""
Ok "Bootstrap selesai. Langkah lanjut:"
Write-Host "  1. Login BPS (Git Bash) : bash petugas_client/install_commands.sh && fasih-login"
Write-Host "  2. Daftarkan auto-start : .\deploy\register_autostart.ps1 -RepoPath `"$RepoPath`""
Write-Host "  3. Jalankan stack       : python supervisor.py"
Write-Host "  4. Verifikasi           : curl http://localhost:$port/health"
```

- [ ] **Step 2: Review the script against the constraints**

Baca ulang script dan pastikan semua ini benar (tidak ada test otomatis — review INI gerbangnya):
1. Idempoten: `Ensure-Tool` skip bila `Get-Command` menemukan tool; repo → `git pull` bukan clone ulang; `.env` yang sudah ada TIDAK ditimpa.
2. Gerbang: `python supervisor.py --once` dicek via `$LASTEXITCODE -ne 0` → `Die` (exit 1). Tidak ada langkah auto-start di script ini.
3. `$Region` dinormalisasi `.Trim().ToLower()` (cocok dengan `region.get_region()` yang juga lower).
4. Folder bukan-git → `Die`, tidak menimpa.
5. winget tidak ada → `Die` dengan link installer manual.

- [ ] **Step 3: Verify no Python was touched (regression gate)**

Run: `python3 -m pytest test_region.py test_supervisor.py test_setup_region.py test_server_region.py test_petugas_url.py -q`
Expected: `35 passed` (D tidak menyentuh kode Python)

- [ ] **Step 4: Commit**

```bash
git add deploy/setup_windows.ps1
git commit -m "feat(deploy): setup_windows.ps1 — bootstrap komputer PLN + gerbang supervisor --once"
```

---

### Task 2: `deploy/register_autostart.ps1` — auto-start saat boot

**Files:**
- Create: `deploy/register_autostart.ps1`

**Interfaces:**
- Consumes: `supervisor.py` + `$RepoPath` hasil Task 1.
- Produces: scheduled task bernama `FasihSupervisor`; dirujuk runbook (Task 3).

- [ ] **Step 1: Write the script**

Create `deploy/register_autostart.ps1`:

```powershell
<#
.SYNOPSIS
    Daftarkan supervisor FASIH agar jalan otomatis saat komputer boot.
.DESCRIPTION
    Task Scheduler, trigger At-Startup, dijalankan sebagai SYSTEM supaya hidup
    walau tidak ada yang login (komputer PLN bisa reboot tanpa operator).
    Idempoten: task lama di-unregister lalu didaftar ulang.
    Ini jaring pengaman untuk reboot/crash-total; auto-recover per-service tetap
    tugas supervisor itu sendiri.
.EXAMPLE
    .\deploy\register_autostart.ps1 -RepoPath "$HOME\Fasih-Python-Script"
#>
[CmdletBinding()]
param(
    [string]$RepoPath = "$HOME\Fasih-Python-Script",
    [string]$TaskName = "FasihSupervisor"
)

$ErrorActionPreference = "Stop"

function Ok($m)  { Write-Host "[+] $m" -ForegroundColor Green }
function Info($m){ Write-Host "[*] $m" -ForegroundColor Cyan }
function Die($m) { Write-Host "[-] $m" -ForegroundColor Red; exit 1 }

if (-not (Test-Path (Join-Path $RepoPath "supervisor.py"))) {
    Die "supervisor.py tidak ditemukan di $RepoPath. Jalankan deploy\setup_windows.ps1 dulu."
}

$python = (Get-Command python -ErrorAction SilentlyContinue).Source
if (-not $python) { Die "python tidak ada di PATH. Jalankan deploy\setup_windows.ps1 dulu." }

if (Get-ScheduledTask -TaskName $TaskName -ErrorAction SilentlyContinue) {
    Info "Task '$TaskName' sudah ada — daftar ulang"
    Unregister-ScheduledTask -TaskName $TaskName -Confirm:$false
}

$action    = New-ScheduledTaskAction -Execute $python -Argument "supervisor.py" -WorkingDirectory $RepoPath
$trigger   = New-ScheduledTaskTrigger -AtStartup
$principal = New-ScheduledTaskPrincipal -UserId "SYSTEM" -LogonType ServiceAccount -RunLevel Highest
$settings  = New-ScheduledTaskSettingsSet `
                -RestartCount 3 `
                -RestartInterval (New-TimeSpan -Minutes 1) `
                -DontStopOnIdleEnd `
                -AllowStartIfOnBatteries `
                -DontStopIfGoingOnBatteries `
                -ExecutionTimeLimit ([TimeSpan]::Zero)

Register-ScheduledTask -TaskName $TaskName -Action $action -Trigger $trigger `
                       -Principal $principal -Settings $settings | Out-Null

Ok "Task '$TaskName' terdaftar (At startup, SYSTEM, restart 3x)."
Write-Host "    Test sekarang : Start-ScheduledTask -TaskName $TaskName"
Write-Host "    Lihat status  : Get-ScheduledTask -TaskName $TaskName | Get-ScheduledTaskInfo"
Write-Host "    Hapus         : Unregister-ScheduledTask -TaskName $TaskName -Confirm:`$false"
```

- [ ] **Step 2: Review the script against the constraints**

Pastikan:
1. Idempoten: `Get-ScheduledTask` → `Unregister-ScheduledTask -Confirm:$false` sebelum register.
2. Trigger `-AtStartup` (BUKAN at-logon) + principal SYSTEM → hidup tanpa ada yang login.
3. Guard: `supervisor.py` tidak ada / `python` tidak di PATH → `Die`, tidak mendaftarkan task rusak.
4. `-ExecutionTimeLimit ([TimeSpan]::Zero)` → task tidak dibunuh Task Scheduler karena "kelamaan" (supervisor memang jalan selamanya).

- [ ] **Step 3: Commit**

```bash
git add deploy/register_autostart.ps1
git commit -m "feat(deploy): register_autostart.ps1 — Task Scheduler at-startup untuk supervisor"
```

---

### Task 3: Runbook deploy Windows

**Files:**
- Create: `docs/runbook-deploy-windows.md`

**Interfaces:**
- Consumes: `deploy/setup_windows.ps1` (Task 1), `deploy/register_autostart.ps1` (Task 2).
- Produces: —

- [ ] **Step 1: Write the runbook**

Create `docs/runbook-deploy-windows.md`:

~~~markdown
# Runbook — Deploy Server Wilayah ke Komputer PLN (Windows)

Menyiapkan satu komputer PLN (Windows 10/11) jadi server wilayah yang jalan sendiri.
Butuh: hak **Administrator** + internet (GitHub, Cloudflare, ap2t.pln.co.id).

## 1. Bootstrap

Buka **PowerShell as Administrator**, lalu:

    cd $HOME
    git clone https://github.com/Leavend/PLN_BOT_TELEGRAM.git Fasih-Python-Script
    cd Fasih-Python-Script
    .\deploy\setup_windows.ps1 -Region bontang

Ganti `bontang` dengan wilayah mesin ini (`samarinda`, `balikpapan`, `wahau`).

Script akan: install Python/Git/cloudflared (winget) → pull repo → install dependency →
tulis `.region` + `house_photos\<region>\` → tanya `PLN_API_KEYS` & `PLN_API_PORT` (bikin `.env`) →
menjalankan **gerbang** `python supervisor.py --once`.

> **Gerbang penting:** kalau `supervisor.py --once` GAGAL, script berhenti dan auto-start
> TIDAK didaftarkan. Jalur Windows supervisor belum pernah dites di mesin asli, jadi ini
> memang titik pembuktiannya. Kirim output error + isi `logs\` ke tim; jangan dipaksa lanjut.

## 2. Login BPS

Lewat **Git Bash** (ikut terpasang bersama Git for Windows):

    cd ~/Fasih-Python-Script
    bash petugas_client/install_commands.sh
    fasih-login

Mengisi `fasih_token.json`. Cek: `fasih-status` — harus menampilkan `🌏 Wilayah: <region>`.

## 3. Auto-start saat boot

Di PowerShell (Administrator):

    .\deploy\register_autostart.ps1 -RepoPath "$HOME\Fasih-Python-Script"

Mendaftarkan task `FasihSupervisor` (At startup, SYSTEM) supaya server hidup lagi
sendiri setelah komputer reboot — penting karena tidak ada operator yang jaga.

## 4. Jalankan & verifikasi

    python supervisor.py

Di jendela lain:

    curl http://localhost:8900/health

Harus menampilkan `"region": "<region>"` dan `photos` > 0.

Cek fallback tunnel benar (quick tunnel, karena `.tunnel_named` belum ada):

    python -c "import supervisor; print(supervisor.tunnel_cmd('bontang')[2])"

Harus mencetak `--url`.

Uji auto-start: **reboot** komputer, lalu cek lagi `curl http://localhost:8900/health`
tanpa login/menjalankan apa pun.

## 5. Sebarkan URL tunnel ke petugas

Quick tunnel memberi URL acak yang **berubah tiap restart**. Ambil dari log:

    Select-String -Path logs\tunnel.log -Pattern "trycloudflare.com" | Select-Object -Last 1

Masukkan URL itu ke `pln_url_<region>.txt`, lalu:

    git add pln_url_<region>.txt
    git commit -m "update url <region>"
    git push

Petugas dapat URL baru lewat `fasih-update`.

> **Ini bagian yang paling merepotkan:** tiap komputer PLN reboot → URL ganti → petugas
> putus sampai kamu update+push. Begitu domain siap, pindah ke **named tunnel** (URL
> permanen, sekali set) — lihat `docs/runbook-rollout-wilayah.md`. Sangat disarankan.

## Troubleshooting

| Gejala | Sebab / Tindakan |
|---|---|
| `winget` tidak dikenal | Windows lama/kebijakan IT. Install Python, Git, cloudflared manual (link dicetak script), jalankan ulang. |
| Tool terpasang tapi "belum di PATH" | Tutup PowerShell, buka ulang **as Administrator**, jalankan ulang script. |
| `python` tidak dikenal, adanya `py` | Pastikan saat install Python dicentang "Add python.exe to PATH". Cek `python --version`. |
| `supervisor.py --once` gagal | Jalur Windows bermasalah — JANGAN lanjut. Kirim output + `logs\` ke tim. |
| `/health` jalan tapi petugas tidak bisa akses | URL tunnel berubah (lihat langkah 5) atau `PLN_API_KEYS` di `.env` beda dengan key petugas. |
| Task `FasihSupervisor` tidak jalan | `Get-ScheduledTask -TaskName FasihSupervisor \| Get-ScheduledTaskInfo` → lihat `LastTaskResult`. |

## Catatan

- `.env` (berisi `PLN_API_KEYS`) TIDAK masuk git — kunci tiap wilayah hanya ada di mesinnya.
- Task berjalan sebagai **SYSTEM**. Saat nanti pindah ke named tunnel, kredensial
  cloudflared ada di profil user (`~\.cloudflared`) — SYSTEM tidak melihatnya. Jalur yang
  benar untuk named tunnel di Windows: `cloudflared service install` (tunnel jadi service
  Windows sendiri, lepas dari supervisor). Catat saat rollout named tunnel.
~~~

- [ ] **Step 2: Verify the runbook's commands match the actual code**

Cek satu per satu (tanpa Windows, ini pemeriksaan konsistensi):
1. `supervisor.tunnel_cmd('bontang')[2]` → `--url`.
   Run: `python3 -c "import supervisor; print(supervisor.tunnel_cmd('bontang')[2])"`
   Expected: `--url`
2. `supervisor.py --once` ada sebagai flag.
   Run: `python3 -c "import supervisor; print('--once ok' if 'once' in open('supervisor.py').read() else 'MISSING')"`
   Expected: `--once ok`
3. `/health` memuat `region`.
   Run: `python3 -c "import sys,os; sys.path.insert(0, os.path.join(os.getcwd(),'pln_api_server')); import server; c=server.app.test_client(); print(c.get('/health').get_json().keys())"`
   Expected: keys memuat `region` dan `photos`

- [ ] **Step 3: Full regression + commit**

Run: `python3 -m pytest test_region.py test_supervisor.py test_setup_region.py test_server_region.py test_petugas_url.py -q`
Expected: `35 passed`

```bash
git add docs/runbook-deploy-windows.md
git commit -m "docs(deploy): runbook deploy server wilayah ke komputer PLN Windows"
```

---

## Self-Review

**Spec coverage:**
- `deploy/setup_windows.ps1` (winget prasyarat, clone/pull, pip, `.region`, `.env` tanpa timpa, gerbang `--once`) → Task 1. ✓
- `deploy/register_autostart.ps1` (Task Scheduler At-Startup, SYSTEM, idempoten, restart 3x) → Task 2. ✓
- `docs/runbook-deploy-windows.md` (langkah + verifikasi + troubleshooting + catatan quick vs named tunnel) → Task 3. ✓
- Error handling: winget absen → Die + link manual (T1); `.env` ada → tidak ditimpa (T1); folder bukan-git → Die (T1); `--once` gagal → berhenti, auto-start tidak didaftarkan (T1 + runbook); task lama → unregister dulu (T2). ✓
- Verifikasi berlapis di mesin asli (`--once` → `/health` → `tunnel_cmd` → reboot) → Task 3 runbook langkah 4. ✓
- D tidak mengubah Python → regression gate 35 test di T1 Step 3 + T3 Step 3. ✓
- Catatan SYSTEM vs `~\.cloudflared` untuk named tunnel nanti → Task 3 runbook Catatan. ✓

**Placeholder scan:** tidak ada TBD/TODO; tiap step berisi script/perintah lengkap. Tidak ada test PowerShell palsu — ketidakadaan test otomatis dinyatakan eksplisit di bagian "Catatan verifikasi". ✓

**Type consistency:** `-Region`, `-RepoPath`, `-RepoUrl`, `-TaskName`, `FasihSupervisor`, `.region`, `PLN_API_KEYS`, `PLN_API_PORT`, `supervisor.py --once`, `tunnel_cmd(region)[2]` — konsisten lintas Task 1/2/3 dan cocok dengan kode A/B/C yang ada. `$RepoPath` default `$HOME\Fasih-Python-Script` sama di T1, T2, dan runbook. ✓
