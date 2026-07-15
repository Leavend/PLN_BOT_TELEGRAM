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
