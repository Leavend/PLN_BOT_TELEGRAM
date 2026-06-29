# Panduan Penggunaan Fasih BPS Auto-Fill & Submit Utility

Skrip ini adalah alat automasi untuk mempermudah pengisian kuesioner pada platform survei **Fasih BPS** (khususnya Survei Groundcheck Pelanggan Listrik PT PLN Prabayar) secara otomatis langsung ke server BPS menggunakan terminal.

---

## 🚀 Persyaratan Sistem & Instalasi

Sebelum menjalankan skrip, pastikan komputer Anda telah memenuhi persyaratan berikut:

### 1. Install Library Python
Skrip ini membutuhkan pustaka kriptografi pihak ketiga. Install dengan perintah:
```bash
pip3 install requests pycryptodome
```

### 2. Install Utilitas Kompresi 7z
Skrip menggunakan perintah sistem `7z` untuk membuat arsip.
* **macOS (via Homebrew)**:
  ```bash
  brew install p7zip
  ```
* **Linux (Debian/Ubuntu)**:
  ```bash
  sudo apt-get install p7zip-full
  ```
* **Windows**: Download installer 7-Zip dari [situs resmi 7-zip](https://www.7-zip.org/) dan masukkan path instalasi ke Environment Variables sistem Anda.

---

## 🔑 Langkah Persiapan Token Autentikasi

Skrip menggunakan berkas `fasih_token.json` di dalam folder yang sama untuk melakukan autentikasi ke server BPS secara aman. 

### Cara Login Interaktif (Sangat Direkomendasikan):
Anda tidak perlu repot menyusun token secara manual. Cukup jalankan perintah list atau submit seperti biasa:
```bash
python3 submit_fasih.py --list
```
Jika berkas `fasih_token.json` belum ada, skrip akan **secara otomatis mendeteksi** hal tersebut dan meminta Anda memasukkan email & password langsung di terminal secara interaktif:
1. Skrip akan meminta email Anda: `Masukkan Email BPS:`
2. Skrip akan meminta password Anda secara aman (karakter tidak akan muncul di layar saat diketik): `Masukkan Password BPS:`
3. Skrip melakukan login ke SSO Keycloak BPS dan menyimpan hasilnya ke `fasih_token.json`.

### Cara Login via Argumen CLI (Alternatif):
Anda juga bisa memicu login secara langsung menggunakan parameter:
```bash
python3 submit_fasih.py --email email_anda@gmail.com
```

*Catatan: Setelah login berhasil satu kali, skrip akan secara otomatis menggunakan dan memperbarui `access_token` menggunakan `refresh_token` yang disimpan jika mendeteksi token telah kedaluwarsa.*

---

## 📖 Panduan Penggunaan Skrip

Skrip ini memiliki beberapa mode interaksi utama:

### 1. Melihat Daftar Tugas Aktif (`--list`)
Untuk melihat semua tugas (*assignment*) yang ditugaskan kepada Anda beserta ID, status, wilayah, dan data pelanggan aslinya:
```bash
python3 submit_fasih.py --list
```
**Contoh Keluaran:**
> ⚪ [1] Assignment: `34ce40ef-5852-4e4a-af30-1db837d34fec`  
> Status: `OPEN` | Mode: `CAPI`  
> Region: `SANGATTA (BONTANG)`  
> Data: `d1=14362253875 (No Meter)` `d3=234000320194 (ID Pel)`

---

### 2. Uji Coba Pengisian Otomatis (`--dry-run`)
Untuk mensimulasikan pengisian kuesioner tanpa benar-benar mengirimkannya ke server BPS (sangat disarankan untuk mengecek kebenaran data hasil auto-fill):
```bash
python3 submit_fasih.py --idpel 234000320194 --nometer 14362253875 --dry-run
```
*Skrip akan otomatis mendeteksi region penugasan yang cocok, menyusun data alamat berdasarkan kode wilayah BPS, dan menampilkan ringkasan data kuesioner siap kirim.*

---

### 3. Kirim / Submit Data Kuesioner (Live Submit)
Untuk langsung mengenkripsi, mengompresi, mengunggah ke S3, dan melakukan submit konfirmasi kuesioner ke API BPS:
```bash
python3 submit_fasih.py --idpel 234000320194 --nometer 14362253875
```
**Hasil Berhasil:**
> `✅ ASSIGNMENT BERHASIL DI-SUBMIT!`

---

## 🛠️ Opsi Kustomisasi Data Kuesioner

Secara default, skrip menggunakan data standar untuk PLN Prabayar (seperti tarif R-1, daya 900, kelurahan 001). Anda bisa melakukan override data tersebut menggunakan argumen di bawah ini:

| Argumen CLI | Nilai Default | Deskripsi |
|---|---|---|
| `--nama` | `"RUDIMIKAEL"` | Nama pelanggan PLN |
| `--alamat` | *(Otomatis)* | Alamat kuesioner (jika kosong, diset otomatis berdasarkan wilayah kecamatan/kabupaten tugas) |
| `--tarif` | `"R-1"` | Tarif daya listrik pelanggan (e.g. `R-1`, `R-1T`, `R-2`) |
| `--daya` | `"900"` | Daya listrik (e.g. `450`, `900`, `1300`) |
| `--hasil` | `"1"` | Hasil pendataan (`1` = Ditemukan, `2` = Tidak ditemukan, dst.) |
| `--kelurahan`| `"001"` | Kode kelurahan kuesioner |
| `--photo` | *None* | Path ke file gambar/foto rumah tampak depan (e.g. `foto_rumah.png`) |
| `--lat` | *None* | Nilai koordinat Latitude (e.g. `-0.1234`) |
| `--lon` | *None* | Nilai koordinat Longitude (e.g. `117.1234`) |
| `-v` / `--verbose`| *False* | Menampilkan informasi debug yang sangat mendalam ke terminal |

**Contoh pengisian kustom dengan Foto & GPS:**
```bash
python3 submit_fasih.py --idpel 234000320194 --nometer 14362253875 --photo /path/to/image.png --lat -0.1234 --lon 117.1234
```

---

## 📊 Automasi Massal (Bulk Submission Orchestrator)

Untuk melakukan pengisian dan pengiriman massal (100+ pelanggan) sekaligus, gunakan skrip `bulk_submit.py`.

### 1. Format Berkas CSV (`pelanggan.csv`)
Buat berkas CSV dengan nama `pelanggan.csv` yang memiliki kolom-kolom berikut:
```csv
idpel,nometer,nama,alamat,latitude,longitude,photo_path
234000427982,86084439602,BUDI SANTOSO,JL. AHMAD YANI,-0.1234,117.1234,/Users/user/Pictures/foto1.png
234000358510,01123340794,SITI NURFATIMAH,JL. SUDIRMAN,-0.5678,117.5678,/Users/user/Pictures/foto2.png
```

### 2. Cara Menjalankan Uji Coba Massal (Dry-Run)
Lakukan simulasi pengisian massal tanpa melakukan submit riil ke server BPS untuk memastikan seluruh data input terpetakan dengan benar:
```bash
python3 bulk_submit.py pelanggan.csv --dry-run
```

### 3. Cara Menjalankan Submit Massal Aktual (Live Submit)
Jalankan perintah berikut untuk mengeksekusi pengiriman massal ke server BPS secara otomatis:
```bash
python3 bulk_submit.py pelanggan.csv
```

### 4. Hasil Laporan (*Execution Report*)
Setelah proses selesai, skrip akan secara otomatis memproduksi laporan berbentuk CSV dengan nama `bulk_submit_report_[TIMESTAMP].csv` berisi status eksekusi (`SUCCESS` atau `FAILED`) serta pesan galat detail untuk masing-masing baris pelanggan.

---

---

## ❓ Troubleshooting & Solusi

### 1. Muncul error `[-] No OPEN assignments found.`
* **Penyebab**: Tugas dengan parameter tersebut telah berhasil dikirimkan sebelumnya, atau memang tidak ada tugas berstatus `OPEN` di akun Anda.
* **Solusi**: Cek status tugas Anda dengan perintah `python3 submit_fasih.py --list`. Jika berstatus `SUBMITTED BY Pencacah`, berarti data Anda sudah aman berada di server BPS.

### 2. Muncul error `[-] Error: '7z' command not found.`
* **Penyebab**: Utilitas 7-Zip tidak terpasang di komputer Anda atau belum dimasukkan ke variabel PATH.
* **Solusi**: Install utilitas `p7zip` sesuai sistem operasi Anda seperti panduan instalasi di bagian atas.
