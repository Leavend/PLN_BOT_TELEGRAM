# Desain: Fitur "Tambah Assignment" di Bot Telegram Fasih

**Tanggal:** 2026-06-29
**Status:** Disetujui (menunggu review spec)
**Goal:** Menu *Kirim Kuesioner* di bot Telegram bisa **menambahkan assignment baru** (pelanggan
ditemukan di lapangan) lalu menuntun user mengisi kuesioner sampai submit — efisien & efektif.

---

## 1. Konteks & Temuan

### 1.1 Kondisi sekarang
Bot ([telegram_bot.py](../../../telegram_bot.py)) hanya bisa **submit kuesioner untuk assignment yang
sudah ada di server**. Alur `submit_conv`: pilih assignment dari datatable → tarif → daya → hasil →
foto → GPS → submit. Pipeline submit (`submit_fasih.submit_questionnaire`) = 3-step S3 upload
(presign → S3 PUT → confirm submit). **Tidak ada jalur "tambah assignment".**

### 1.2 Apa itu "Tambah Assignment" (hasil bongkar APK `id.go.bpsfasih`)
Bukan klaim sampel. **Membuat assignment baru di lapangan:**
- `AssignmentUpdateListingViewModel.addAssignment()` → cek izin lokasi → bangun `AssignmentEntity`
  baru (**id = UUID baru**, `isNew = true`, region ikut konteks wilayah kerja: surveyId/periodId/
  regionId/templateId) → `insertAssignmentJson()` (lokal) → event `assignmentAdded` → buka FormGear
  (kuesioner GCPLN26.PRA).
- User isi kuesioner (IDPel, NoMeter, dll), lalu submit lewat **pipeline S3 yang sama** dengan flag
  **`createStatus = true`** (= `isNew`).

Bukti kunci:
- `AssignmentApiService`: `@POST .../assignment/s3/submit` (sudah dipakai bot).
- `AssignmentRepositoryImpl.assignmentSubmitS3Post(... boolean isNew ...)` →
  `pairArr[4] = TuplesKt.to("createStatus", String.valueOf(isNew))`.
- Field entity pendukung "copy/new": `copyFromId`, `assignmentCopyFromId`, `original`, `isNew`.

**DIPASTIKAN (tidak ada pra-registrasi):** `addAssignment$1$1$1$1` hanya `UUID.randomUUID()` →
`entity.copy(id=UUID, isNew=true, copyFromId=<id-acuan>, status=OPEN, data1–10="")` →
`insertData()` (Room lokal) + `insertAssignmentJson()` (file lokal) + `postValue()` (buka form).
**Nol panggilan jaringan saat "Tambah".** Server hanya tersentuh saat **submit** (`createStatus=true`),
yang sekaligus mendaftarkan + mengunggah. → Bot tidak perlu langkah daftar terpisah.

> **`assign-by-selection`** (`/mobile/assignment-general/api/mobile/assign-by-selection/{periodeId}`,
> body `{surveyPeriodRoleUserIds, assignmentIds, replaceUser}`) adalah **fitur lain** (supervisor
> membagi sampel ke petugas) — **tidak dipakai** untuk fitur ini.

### 1.3 Implikasi (kabar baik)
Tambah assignment = **bukan endpoint baru**. Cukup: generate UUID + region + `isNew=true`, lalu jalankan
pipeline submit yang **sudah ada**. [submit_fasih.py:278](../../../submit_fasih.py) `get_submit_params`
malah sudah mengirim `isNew` (`target.get("isNew", False)`).

---

## 2. Arsitektur

Prinsip: **reuse maksimal, tambah minimal.** Path "assignment baru" = bikin objek `target` sintetis
dengan **meng-clone region/template dari 1 assignment yang sudah ada**, ganti `id`→UUID baru,
`isNew`→true, isi identitas baru (IDPel/NoMeter), lalu lewatkan ke pipeline yang sama.

```
[➕ Tambah Assignment]  (entry baru di submit_conv)
        │
        ▼
1. Ambil assignments user  (fetch_all_assignments — sudah ada)
        │
        ▼
2. Pilih wilayah kerja  → ambil 1 assignment "template" di wilayah itu
   (default: assignment pertama; kalau >1 wilayah, user pilih)
        │
        ▼
3. Input IDPel  ──► CEK DUPLIKAT (lihat §3.3) ──► Input NoMeter ──► CEK DUPLIKAT
        │
        ▼
4. Wizard lama (REUSE): Tarif → Daya → Hasil → Foto → GPS
        │
        ▼
5. Konfirmasi "Yakin tambah assignment baru? Ya / Tidak"   ⚠️ gate wajib
        │ (Ya)
        ▼
6. build_new_assignment_target(template, idpel, nometer)  → target sintetis (UUID, isNew=true)
        │
        ▼
7. submit_questionnaire(...)  (REUSE pipeline S3: encrypt → 7z → presign → upload → submit)
        │
        ▼
8. ✅ "Assignment baru tersimpan" → muncul di /list
```

### 2.1 Komponen yang disentuh

| Komponen | Berkas | Perubahan |
|---|---|---|
| Entry menu + state machine | `telegram_bot.py` | Tombol `➕ Tambah Assignment`; state baru: `WAITING_WILAYAH`, `WAITING_IDPEL`, `WAITING_NOMETER`; sambung ke state wizard lama (`WAITING_TARIF` dst). |
| Builder target baru | `submit_fasih.py` (baru: `build_new_assignment_target`) | Clone template assignment → set `id`=UUID, `isNew`=True, status `OPEN`, isi slot IDPel/NoMeter, kosongkan jejak submit lama. |
| Entry submit reuse | `telegram_bot.py` `submit_fasih_safe` | Tambah param `create_new=True` + `template_assignment_id`. Saat aktif: **lewati lookup target dari server** ([telegram_bot.py:142](../../../telegram_bot.py)), bangun target sintetis dari template (lihat §4). Sisanya jalan apa adanya. |
| Param submit | `submit_fasih.py` `get_submit_params` | Pastikan flag `isNew`/`createStatus` terkirim benar untuk assignment baru (lihat §5 item-1). |
| Cek duplikat | `telegram_bot.py` (helper baru) | Scan assignments untuk IDPel/NoMeter (lihat §3.3). |

### 2.2 Yang TIDAK berubah
Pipeline kripto/arsip/upload (`fasih_crypto`, `fasih_archive`, `fasih_api`, langkah S3), auth/token,
wizard tarif/daya/hasil/foto/GPS. Semua dipakai-ulang apa adanya.

---

## 3. Detail Fungsional

### 3.1 Pilih wilayah kerja
- Ambil `fetch_all_assignments`. Kelompokkan unik per region (level1/2/3).
- **1 wilayah** → otomatis pakai, skip prompt.
- **>1 wilayah** → tampilkan tombol pilih wilayah. Assignment pertama di wilayah terpilih jadi
  "template" (sumber region, kunci enkripsi, kode UPI/UP3/ULP, template mapping).
- **0 assignment** → tolak: "Belum ada assignment acuan; tidak bisa tentukan wilayah/kunci enkripsi.
  Sync/ambil minimal 1 assignment dulu." (lihat §5 item-2 untuk alternatif tanpa acuan).

### 3.2 Input IDPel & No Meter
- Prompt IDPel (teks) → validasi non-kosong, numerik wajar → cek duplikat.
- Prompt NoMeter (teks) → validasi → cek duplikat.
- Simpan ke `submit_args` (dipakai `build_dynamic_answers` lewat slot `r101a`/`r101b` sesuai
  `template_mapping`).

### 3.3 Cek duplikat IDPel / No Meter  *(permintaan eksplisit user)*
Sebelum lanjut, scan assignments yang sudah ada:
- Cocokkan `idpel` ke slot IDPel (`data3` / slot `r101a`) dan `nometer` ke slot NoMeter
  (`data1` / slot `r101b`).
- **Jika ketemu:** tampilkan status assignment itu agar user tahu **sudah diisi atau belum**:
  - status `SUBMITTED` → "⚠️ IDPel `X` sudah ada & **sudah disubmit**. Tetap tambah baru? Ya/Tidak"
  - status `OPEN` → "⚠️ IDPel `X` sudah ada tapi **belum diisi/submit**. Buka yang itu, atau tambah baru? [Buka] [Tambah baru] [Batal]"
- **Jika tidak ketemu:** lanjut normal.
- Cek dijalankan untuk IDPel dan NoMeter (terpisah) — beda customer bisa ketuker salah satu.

### 3.4 Konfirmasi & submit
- Ringkas semua input (IDPel, NoMeter, wilayah, tarif, daya, hasil) → tombol **Ya / Tidak**.
- "Ya" → `build_new_assignment_target` → `submit_questionnaire`. Tampilkan progres tiap langkah.
- "Tidak" → batal, tidak ada yang dikirim.

---

## 4. Aliran Data — `build_new_assignment_target`

```python
def build_new_assignment_target(template: dict, idpel: str, nometer: str,
                                idpel_slot: str, nometer_slot: str) -> dict:
    # Meniru persis addAssignment$1$1$1$1 di APK (entity.copy()).
    t = copy.deepcopy(template)
    t["id"] = str(uuid.uuid4())             # UUID baru = assignment baru
    t["isNew"] = True                       # → createStatus=true saat submit
    t["assignmentStatusAlias"] = "OPEN"     # paksa jalur submit (bukan edit)
    t["copyFromId"] = template["id"]        # SAMA spt app: copy dari assignment acuan
    t["original"] = False
    t["mode"] = ["CAPI"]
    t["submitVersionCode"] = 0
    t["comment"] = '{"dataKey": "","notes": []}'
    for k in ("data1","data2","data3","data4","data5",
              "data6","data7","data8","data9","data10"):
        t[k] = ""                           # app: data1–10 mulai kosong
    t[idpel_slot] = idpel                   # data3 / slot r101a
    t[nometer_slot] = nometer               # data1 / slot r101b
    for k in ("latitude", "longitude", "mediaJson", "remark"):
        t.pop(k, None)                      # bersihkan jejak submit template
    return t
```
- `region`, `surveyPeriodId`, `preDefinedData` (skeleton UPI/UP3/ULP/kode wilayah), `regionMetadata`
  ikut dari template → kunci enkripsi & autofill region benar (lewat `build_dynamic_answers` +
  `get_region_fields` yang sudah ada).
- `is_edit` di pipeline = `status != "OPEN"` → untuk baru selalu **submit** (bukan edit). ✔

**Tempat dipanggil:** di dalam `submit_fasih_safe` saat `create_new=True`. Alurnya: fungsi tetap
`fetch` assignments & resolve `template_mapping` seperti biasa, lalu **alih-alih lookup target dari
server**, ia cari assignment acuan via `template_assignment_id`, panggil `build_new_assignment_target`
(slot IDPel/NoMeter dari `template_mapping`), dan lanjut ke encrypt→7z→presign→upload→submit yang sama.
Begini lookup server di [telegram_bot.py:142](../../../telegram_bot.py) tidak gagal untuk UUID yang
belum ada di server.

---

## 5. Item yang DIVERIFIKASI saat implementasi (jujur — belum 100% pasti)

1. **Nama field flag baru.** Bot kirim `"isNew"`; APK kirim `"createStatus"`. Cek payload `confirm_submit`
   apakah server butuh `createStatus` (atau keduanya) untuk assignment baru. Sesuaikan `get_submit_params`.
2. **`copyFromId` untuk assignment baru.** APK set `copyFromId = id assignment acuan` (bukan kosong),
   `original=false`, `isNew=true` — sudah ditiru di §4. Verifikasi via 1 submit uji bahwa server menerima
   record baru ini & nilai `copyFromId` itu benar.
3. **Skeleton `preDefinedData` wilayah.** Field seperti `cawi_identifier`, `status_sample`, `strata`,
   `kdpm`, `kddk` untuk record baru. Clone-from-template menutup mayoritas; verifikasi nilai wajib.
4. **Slot IDPel/NoMeter** via `template_mapping` (`r101a`→IDPel, `r101b`→NoMeter) — sudah ada di
   `find_assignment_by_direct_args`; pakai ulang.

---

## 6. Keamanan & Keselamatan Data

- **Menulis ke server BPS PRODUKSI.** Wajib gate konfirmasi "Ya/Tidak" sebelum submit.
- **Test 1 dulu**: submit satu assignment baru, verifikasi muncul benar di `/list` & di app, sebelum
  dipakai rutin.
- **`replaceUser` / assign-by-selection tidak disentuh** → tidak ada risiko merebut tugas orang lain.
- Tidak ada trial-error membabi-buta ke server; verifikasi field dari APK + 1 uji terkontrol.
- Kredensial tetap per-user terisolasi seperti sekarang.

---

## 7. Penanganan Error

| Kasus | Perilaku |
|---|---|
| Belum login | "Login dulu via /login." akhiri conversation. |
| 0 assignment acuan | Tolak dengan pesan jelas (§3.1). |
| IDPel/NoMeter kosong/invalid | Minta ulang, tidak lanjut. |
| Duplikat ditemukan | Tawarkan [Buka existing] / [Tambah baru] / [Batal] (§3.3). |
| Submit gagal (S3/HTTP) | Tampilkan error, assignment tidak masuk, user bisa ulang. |
| `/cancel` di state mana pun | Batalkan, tidak ada kiriman. |

---

## 8. Strategi Test

1. **Unit (lazy, 1 cek):** `build_new_assignment_target` → assert `id` UUID valid & beda dari template,
   `isNew is True`, `status == "OPEN"`, slot IDPel/NoMeter terisi, `copyFromId == ""`. (`test_*.py`
   berbasis assert, tanpa framework.)
2. **Cek duplikat:** fungsi dedup → assert match IDPel & NoMeter, dan non-match lolos.
3. **Integrasi terkontrol:** 1 submit assignment baru sungguhan (region asli) → verifikasi di `/list`
   + app. Ini yang membuktikan §5 item-2.

---

## 9. Di Luar Lingkup (sekarang)
- Tarik sampel / `assign-by-selection` (alur supervisor).
- Tambah massal banyak assignment via CSV (bisa menyusul setelah single-add terbukti).
- Edit/hapus assignment baru setelah submit.
