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

function Assert-LastExit($what) {
    if ($LASTEXITCODE -ne 0) { Die "$what GAGAL (exit $LASTEXITCODE). Perbaiki dulu lalu jalankan ulang." }
}

function Refresh-Path {
    $env:Path = [System.Environment]::GetEnvironmentVariable("Path", "Machine") + ";" +
                [System.Environment]::GetEnvironmentVariable("Path", "User")
}

function Test-RealCommand($cmd) {
    $c = Get-Command $cmd -ErrorAction SilentlyContinue
    if (-not $c) { return $false }
    # Stub "App Execution Alias" Windows Store — kebaca Get-Command tapi bukan install beneran
    if ($c.Source -like "*\WindowsApps\*") { return $false }
    return $true
}

function Ensure-Tool($cmd, $wingetId, $label, $manualUrl) {
    if (Test-RealCommand $cmd) { Ok "$label sudah ada"; return }
    Info "Install $label ..."
    winget install --id $wingetId -e --silent --accept-package-agreements --accept-source-agreements
    Refresh-Path
    if (-not (Test-RealCommand $cmd)) {
        Die @"
$label belum terdeteksi sebagai install asli.
  - Kalau baru terpasang: tutup PowerShell, buka lagi sebagai Administrator, jalankan ulang script ini.
  - Kalau yang kebaca stub Microsoft Store (path ...\WindowsApps\): matikan alias-nya di
    Settings > Apps > Advanced app settings > App execution aliases > matikan python.exe & python3.exe,
    lalu jalankan ulang.
  - Install manual: $manualUrl
"@
    }
    Ok "$label terpasang"
}

# --- 0. wajib Administrator ---
$isAdmin = ([Security.Principal.WindowsPrincipal][Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)
if (-not $isAdmin) {
    Die "Script WAJIB dijalankan sebagai Administrator (butuh git --system + Task Scheduler). Tutup PowerShell, klik kanan ikon PowerShell > Run as Administrator, jalankan ulang."
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
Ensure-Tool "python"      "Python.Python.3.12"     "Python 3"    "https://www.python.org/downloads/windows/"
Ensure-Tool "git"         "Git.Git"                "Git"         "https://git-scm.com/download/win"
Ensure-Tool "cloudflared" "Cloudflare.cloudflared" "cloudflared" "https://github.com/cloudflare/cloudflared/releases"

# Jaringan PLN sering blokir cek revokasi sertifikat (CRYPT_E_NO_REVOCATION_CHECK) -> git
# via schannel gagal saat clone/pull/fetch. Matikan cek revokasi git (validasi rantai
# sertifikat tetap jalan; hanya lookup CRL/OCSP yang di-skip). global = kepakai supervisor juga.
try { git config --system http.schannelCheckRevoke false 2>&1 | Out-Null } catch { }

# --- 3. repo ---
if (Test-Path $RepoPath) {
    if (-not (Test-Path (Join-Path $RepoPath ".git"))) {
        Die "$RepoPath ada tapi BUKAN git repo. Pindahkan/hapus folder itu lalu jalankan ulang."
    }
    Info "Repo sudah ada — git pull ..."
    Push-Location $RepoPath
    git pull
    Assert-LastExit "git pull"
    Pop-Location
} else {
    Info "Clone repo ke $RepoPath ..."
    git clone $RepoUrl $RepoPath
    Assert-LastExit "git clone"
}
Set-Location $RepoPath
Ok "Repo siap di $RepoPath"

# Task Scheduler menjalankan supervisor sebagai SYSTEM, sedangkan repo ada di profil user.
# Tanpa ini git menolak repo milik SID lain ("detected dubious ownership") — auto-update
# sub-proyek A mati diam-diam dan cuma kelihatan sebagai "git check gagal (offline?)".
$repoForGit = $RepoPath -replace '\\', '/'
git config --system --add safe.directory "$repoForGit"
Assert-LastExit "git config safe.directory"
Ok "safe.directory diset untuk SYSTEM ($repoForGit)"

# --- 4. dependency Python ---
Info "Install Python packages ..."
python -m pip install --upgrade pip
Assert-LastExit "pip upgrade"
python -m pip install -r requirements.txt
Assert-LastExit "pip install requirements.txt"
python -m pip install flask py7zr
Assert-LastExit "pip install flask py7zr"
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
