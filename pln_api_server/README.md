# PLN Data + Photo API Server

REST API server yang expose PLN AP2T lookup dan foto rumah untuk diakses petugas lapangan.

## Setup

```bash
cd pln_api_server
pip install -r requirements.txt
```

## Konfigurasi (.env di root repo)

```env
# API key untuk proteksi akses (wajib di production!)
PLN_API_KEYS=key_petugas_a,key_petugas_b,key_petugas_c

# Port dan host (opsional)
PLN_API_PORT=8900
PLN_API_HOST=0.0.0.0
```

## Jalankan

```bash
python3 pln_api_server/server.py
```

## Endpoints

### `GET /health`
Health check — tidak perlu API key.

### `GET /api/lookup?idpel=234000123456`
Lookup data pelanggan. Response:
```json
{
  "idpel": "234000123456",
  "nometer": "52123456789",
  "nama": "SITI AMINAH",
  "alamat": "JL MANGGA NO. 12 RT. 001 RW. 002",
  "nik": "6472...",
  "tarif": "R-1",
  "daya": "900",
  "photo_url": "/api/photo/a1b2c3d4e5f6"
}
```

### `GET /api/photo/random`
Foto rumah acak.

### `GET /api/photo/<photo_id>`
Foto spesifik by ID (dari field `photo_url` di response lookup).

### Auth
Semua `/api/*` endpoint butuh header `X-API-Key` atau query param `?api_key=xxx`.
