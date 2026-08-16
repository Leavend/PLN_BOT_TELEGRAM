# Runbook — Pindah Server Wilayah ke Komputer Lain

Memindahkan server wilayah (mis. `samarinda`) dari komputer Windows lama ke komputer
Windows baru **tanpa mengubah URL** yang sudah dipegang petugas
(`https://<region>.xyzfasih.site`).

Ada dua jenis tunnel di proyek ini — cek dulu yang mana:

| Jenis | Ciri | Pindahannya |
|---|---|---|
| **Dashboard-managed** (dipakai Samarinda) | punya **token** `eyJ...` dari Cloudflare Zero Trust → Networks → Tunnels | pasang token di mesin baru, uninstall di mesin lama. Ingress ada di dashboard, tidak ada file kredensial |
| File-based (`cloudflared tunnel create`) | ada `%USERPROFILE%\.cloudflared\<UUID>.json` + `config.yml` | salin folder `.cloudflared` (lihat bagian akhir) |

Token = kredensial. Jangan commit, jangan kirim ke grup. Setelah pindahan selesai,
rotate lewat dashboard bila token sempat tersebar.

---

## Yang TIDAK ikut `git clone` (harus dibawa manual)

| Item | Isi | Kalau hilang |
|---|---|---|
| `.env` | `PLN_API_KEYS`, `PLN_API_PORT`, `TELEGRAM_BOT_TOKEN`, `PLN_AP2T_OFFLINE` | petugas 401 / auth terbuka |
| `fasih_token.json` | sesi login BPS | bikin ulang: `fasih-login` |
| `pln_cache.db` | cache lookup AP2T | lookup kosong saat AP2T offline |
| `services.local.json` | daftar service mesin itu | supervisor pakai default → ikut menjalankan cloudflared sendiri (bentrok) |
| `.region` | nama wilayah | dibuat ulang oleh `setup_windows.ps1` |
| token tunnel | connector Cloudflare | 502 |

Foto rumah (`house_photos/<region>/`) **ikut git** — ±800 MB semua wilayah, jadi
`git clone` butuh waktu + ±1,6 GB disk. Lebih cepat: salin folder repo utuh dari mesin
lama lewat flashdisk, lalu `git pull` di mesin baru.

---

## 0. Mesin LAMA — ambil bekal, lalu matikan

Dua connector aktif untuk tunnel yang sama membuat Cloudflare membagi trafik ke
keduanya; petugas kena 502 berselang-seling begitu yang lama tak punya origin hidup.

    $dst = "E:\pindah-fasih"        # ganti huruf drive
    New-Item -ItemType Directory -Force $dst | Out-Null
    Copy-Item "$HOME\Fasih-Python-Script\.env",
              "$HOME\Fasih-Python-Script\fasih_token.json",
              "$HOME\Fasih-Python-Script\pln_cache.db",
              "$HOME\Fasih-Python-Script\services.local.json" $dst -ErrorAction SilentlyContinue

Matikan (PowerShell **Administrator**):

    Unregister-ScheduledTask -TaskName FasihSupervisor -Confirm:$false -ErrorAction SilentlyContinue
    cloudflared.exe service uninstall

Tutup juga jendela `python supervisor.py` yang masih jalan (Ctrl+C).

## 1. Mesin BARU — bootstrap

PowerShell **Administrator**:

    cd $HOME
    curl.exe -fLO https://raw.githubusercontent.com/Leavend/PLN_BOT_TELEGRAM/main/deploy/setup_windows.ps1
    Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass -Force
    .\setup_windows.ps1 -Region samarinda

Memasang Python/Git/cloudflared, clone repo ke `$HOME\Fasih-Python-Script`, install
dependency, menanyakan `PLN_API_KEYS` (isi kunci wilayah Samarinda), lalu menjalankan
gerbang `supervisor.py --once`. Gerbang gagal → berhenti, jangan lanjut.

## 2. Kembalikan file bawaan

    $src = "E:\pindah-fasih"; $repo = "$HOME\Fasih-Python-Script"
    Copy-Item "$src\.env", "$src\fasih_token.json", "$src\pln_cache.db" $repo -ErrorAction SilentlyContinue

`services.local.json` — tunnel dijalankan sebagai service Windows, jadi jangan
didaftarkan sebagai anak supervisor:

    Set-Content "$repo\services.local.json" '[{"name":"pln_server","cmd":["python","pln_api_server/server.py"],"restart_on_update":true,"lock_file":null}]'

(Tambahkan entri `telegram_bot` hanya bila `TELEGRAM_BOT_TOKEN` benar-benar diisi di
`.env`; tanpa token botnya exit dan supervisor menyalakannya ulang tiap 15 detik.)

## 3. Pasang connector tunnel

PowerShell **Administrator**, token dari Cloudflare Zero Trust → Networks → Tunnels →
`samarinda` → Configure:

    cloudflared.exe service install <TOKEN>
    Get-Service cloudflared

`Status` harus `Running`, `StartType` `Automatic`. Di dashboard, Public Hostname tunnel
itu harus `samarinda.xyzfasih.site` → `HTTP` → `localhost:8900`, dan status connector
berubah jadi **HEALTHY** dari mesin baru.

## 4. AP2T: intranet PLN atau bukan

    curl.exe -m 5 https://ap2t.pln.co.id/infopelanggannewap2t-dr/InfoPelanggan/

Tersambung → hapus `PLN_AP2T_OFFLINE` dari `.env` (lookup live, data terlengkap).
Tidak tersambung → set `PLN_AP2T_OFFLINE=true`; `/api/lookup` memakai `pln_cache.db`
lalu fallback ke server Balikpapan.

## 5. Auto-start + verifikasi

    .\deploy\register_autostart.ps1 -RepoPath "$HOME\Fasih-Python-Script"
    Start-ScheduledTask -TaskName FasihSupervisor
    Start-Sleep 25
    curl.exe http://localhost:8900/health

Lalu dari mana saja:

    curl.exe https://samarinda.xyzfasih.site/health
    curl.exe -H "X-API-Key: <kunci-samarinda>" "https://samarinda.xyzfasih.site/api/lookup?idpel=234000248265"

`/health` harus `"region":"samarinda"` + `photos` 6715; `/api/lookup` harus 200 berisi data.
Terakhir **reboot** mesin, ulangi dua perintah itu tanpa login — membuktikan auto-start jalan.

---

## Lampiran — tunnel file-based (bukan Samarinda)

Bila wilayah memakai `cloudflared tunnel create` (ada `<UUID>.json`), salin
`%USERPROFILE%\.cloudflared\` (cert.pem + `<UUID>.json` + config.yml) ke profil user
mesin baru, perbaiki baris `credentials-file:` agar menunjuk path user baru, dan buat
penanda `.tunnel_named` di root repo. Bila kredensial hilang total: `cloudflared tunnel
login` → `cloudflared tunnel delete <region>` (connector lama harus mati) → `python
setup_region.py <region> xyzfasih.site`. Tanpa `delete`, `tunnel create` membalas
"already exists" dan kredensial JSON **tidak pernah ditulis** → `tunnel run` gagal.

## Troubleshooting

| Gejala | Sebab |
|---|---|
| Domain 502, `localhost:8900` OK | connector mati → `Get-Service cloudflared`, cek status tunnel di dashboard |
| Domain 502 berselang-seling | mesin lama masih jadi connector — `cloudflared.exe service uninstall` di sana |
| `/api/lookup` 404 padahal `/health` 200 | AP2T tak terjangkau **dan** fallback Balikpapan mati/URL usang → cek `pln_url_balikpapan.txt` sudah versi terbaru |
| `photos: 0` | `git clone` belum selesai / `house_photos\samarinda` kosong |
| Petugas 401 | `PLN_API_KEYS` di `.env` ≠ `PLN_API_KEY` di HP petugas |
| `git pull GAGAL Nx` berulang di log | ada file yang diubah lokal (mis. `pln_url_*.txt`) → `git checkout -- <file>` |
| Task terdaftar tapi server tak listen | `python` yang kebaca stub Microsoft Store, atau python per-user tak terjangkau SYSTEM |
