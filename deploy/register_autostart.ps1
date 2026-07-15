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

$pythonCmd = Get-Command python -ErrorAction SilentlyContinue
if (-not $pythonCmd) { Die "python tidak ada di PATH. Jalankan deploy\setup_windows.ps1 dulu." }
$python = $pythonCmd.Source
if ($python -like "*\WindowsApps\*") {
    Die @"
python yang kebaca adalah stub Microsoft Store ($python) — bukan install asli.
Task bakal terdaftar dan keliatan normal, tapi supervisor TIDAK akan jalan setelah reboot.
Matikan alias-nya: Settings > Apps > Advanced app settings > App execution aliases >
matikan python.exe & python3.exe. Lalu jalankan deploy\setup_windows.ps1 lagi.
"@
}

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
