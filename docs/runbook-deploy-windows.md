# Runbook — Deploy Server Wilayah ke Komputer PLN (Windows)

Menyiapkan satu komputer PLN (Windows 10/11) jadi server wilayah yang jalan sendiri.
Butuh: hak **Administrator** + internet (GitHub, Cloudflare, ap2t.pln.co.id).

## 1. Bootstrap

Buka **PowerShell as Administrator**, lalu:

    cd $HOME
    curl.exe -fLO https://raw.githubusercontent.com/Leavend/PLN_BOT_TELEGRAM/main/deploy/setup_windows.ps1
    Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass -Force
    .\setup_windows.ps1 -Region bontang

> Ganti `bontang` dengan wilayah mesin ini (`samarinda`, `balikpapan`, `wahau`).
> `Set-ExecutionPolicy -Scope Process` cuma berlaku untuk sesi PowerShell ini saja —
> tidak mengubah setting mesin permanen. Mesin belum punya Git di titik ini, jadi
> script diambil berdiri sendiri dulu (`curl.exe` ikut Windows 10 1803+); script ini
> akan meng-install Git lalu clone repo sendiri ke `$HOME\Fasih-Python-Script`.
> Langkah berikutnya dijalankan dari dalam folder itu:
>
>     cd $HOME\Fasih-Python-Script

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
