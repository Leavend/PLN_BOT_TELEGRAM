#!/bin/bash
# migrate_to_supervisor.sh — pindahkan mesin wilayah dari auto_updater.sh lama
# (cuma ngawasin telegram_bot) ke supervisor.py (server + bot + tunnel + auto-update).
#
# Ini juga yang MENUTUP lubang auth: server lama jalan tanpa .env (PLN_API_KEYS kosong
# -> auth terbuka). Setelah git pull + restart lewat script ini, server.py memuat .env
# (fix load_dotenv) -> auth aktif (401 tanpa key).
#
# PERINGATAN: script ini MEMATIKAN stack lama dan menyalakan cloudflared BARU ->
# URL tunnel BERUBAH. Setelah selesai, update pln_url_<region>.txt + commit + push
# supaya petugas dapat URL baru (script mencetak URL-nya di akhir).
#
# Jalankan dari root repo:  bash deploy/migrate_to_supervisor.sh

set -u
cd "$(cd "$(dirname "$0")/.." && pwd)" || { echo "[-] gagal cd ke root repo"; exit 1; }

if [ ! -f supervisor.py ]; then
    echo "[-] supervisor.py tidak ada di $(pwd). Pastikan di root repo yang benar."
    exit 1
fi

# Deteksi python yang benar-benar bekerja (hindari alias palsu).
PY=""
for c in python3 python py; do
    if command -v "$c" >/dev/null 2>&1 && "$c" -c "import sys" >/dev/null 2>&1; then PY="$c"; break; fi
done
[ -n "$PY" ] || { echo "[-] python tidak ditemukan"; exit 1; }
echo "[*] python: $PY"

echo "[*] git pull (ambil fix load_dotenv + supervisor)..."
git pull || { echo "[-] git pull gagal — perbaiki dulu (uncommitted/conflict?), lalu ulang."; exit 1; }

# Matikan stack lama. pkill kalau ada; kalau tidak, ps|grep|kill.
stop() {
    local pat="$1"
    if command -v pkill >/dev/null 2>&1; then
        pkill -f "$pat" 2>/dev/null
    else
        ps -ef 2>/dev/null | grep "$pat" | grep -v grep | awk '{print $2}' | while read -r pid; do
            [ -n "$pid" ] && kill "$pid" 2>/dev/null
        done
    fi
}
echo "[*] matikan stack lama (auto_updater, server, bot, tunnel)..."
for pat in "auto_updater.sh" "auto_updater.py" "pln_api_server/server.py" "telegram_bot.py" "cloudflared"; do
    stop "$pat"
done
sleep 3

mkdir -p logs
echo "[*] jalankan supervisor.py (full stack, auto-update)..."
nohup "$PY" supervisor.py > logs/supervisor.out 2>&1 &
echo "[+] supervisor PID $!"
echo "[*] tunggu service naik (12s)..."
sleep 12

PORT="$(grep -oE '^[[:space:]]*PLN_API_PORT[[:space:]]*=[[:space:]]*[0-9]+' .env 2>/dev/null | grep -oE '[0-9]+' | head -1)"
[ -n "$PORT" ] || PORT=8900

echo ""
echo "===== VERIFIKASI ====="
if command -v curl >/dev/null 2>&1; then
    echo -n "  /health           : "; curl -s -o /dev/null -w "%{http_code}\n" --max-time 5 "http://localhost:$PORT/health" || echo "gagal"
    echo -n "  unauth lookup     : "; curl -s -o /dev/null -w "%{http_code}" --max-time 5 "http://localhost:$PORT/api/lookup?idpel=234201167356"; echo "  (401 = auth AKTIF / lubang tertutup; 200/404 = MASIH terbuka)"
else
    echo "  (curl tidak ada — cek manual: http://localhost:$PORT/health)"
fi

echo ""
echo "  URL tunnel BARU (cloudflared) — update pln_url + push:"
URL="$(grep -oE 'https://[a-z0-9-]+\.trycloudflare\.com' logs/tunnel.log 2>/dev/null | tail -1)"
if [ -n "$URL" ]; then
    echo "    $URL"
    echo "  Langkah: echo '$URL' > pln_url_\${REGION}.txt  (ganti \${REGION}), lalu git add + commit + push"
else
    echo "    (belum muncul di logs/tunnel.log — tunggu beberapa detik lalu: grep trycloudflare logs/tunnel.log)"
fi
echo "======================"
echo "[+] Selesai. Kalau /health 200 + unauth 401, migrasi sukses & auth tertutup."
