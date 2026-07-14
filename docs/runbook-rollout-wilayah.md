# Runbook — Rollout Wilayah Baru (named tunnel)

Onboard satu wilayah (mis. `balikpapan`) sebagai server penuh dengan hostname stabil.

## Prasyarat (one-time, seluruh proyek)

1. Punya domain, tambahkan ke Cloudflare, tunggu zone **Active**.
   (`<region>.<domain>` akan jadi hostname tiap wilayah, mis. `balikpapan.pln-fasih.com`.)

## Per mesin wilayah

1. **Install cloudflared** dan login ke akun Cloudflare:

       cloudflared tunnel login

   (Membuka browser; pilih domain yang tadi diaktifkan.)

2. **Jalankan onboarding** (dari root repo, setelah `git pull`):

       python3 setup_region.py balikpapan pln-fasih.com
       # atau: fasih-setup-region balikpapan pln-fasih.com

   Script akan: `cloudflared tunnel create balikpapan` + `route dns` →
   tulis `.region`, `house_photos/balikpapan/`, `pln_url_balikpapan.txt`
   (= `https://balikpapan.pln-fasih.com`), `~/.cloudflared/config.yml`, dan
   penanda `.tunnel_named` (hanya bila cloudflared sukses).

   > Bila cloudflared belum siap/login, script tetap menulis file region tapi
   > TIDAK membuat `.tunnel_named` → server jalan pakai quick tunnel dulu.
   > Login lalu jalankan ulang script untuk mengaktifkan named tunnel.

3. **Set API key wilayah** di `.env`:

       PLN_API_KEYS=<key-balikpapan>

4. **Commit URL wilayah** agar petugas Balikpapan bisa `git pull`:

       git add pln_url_balikpapan.txt && git commit -m "add balikpapan url" && git push

5. **Jalankan stack:**

       python3 supervisor.py

   (Deploy HARUS lewat `python3 server.py` di supervisor, BUKAN gunicorn —
   `load_photos()` hanya jalan di `__main__`.)

## Verifikasi

    curl https://balikpapan.pln-fasih.com/health

Harus menampilkan `"region": "balikpapan"` dan jumlah foto > 0.

## Petugas wilayah

Di HP petugas Balikpapan: `echo balikpapan > .region`, lalu `fasih-update`
(git pull). `fasih-status` menampilkan `🌏 Wilayah: balikpapan` + region server
dari `/health`. Petugas otomatis pakai `pln_url_balikpapan.txt`.

> Bila `cloudflared tunnel run` mengeluh soal credentials, tambahkan baris
> `credentials-file: ~/.cloudflared/<UUID>.json` ke `~/.cloudflared/config.yml`
> (UUID dari output `cloudflared tunnel create`).
