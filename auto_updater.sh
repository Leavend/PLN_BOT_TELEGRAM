#!/bin/bash
# auto_updater.sh
# Menjalankan pengecekan Git repository secara berkala untuk melakukan auto-update bot.

BOT_SCRIPT="telegram_bot.py"
INTERVAL=60 # Cek update setiap 60 detik
BRANCH="main" # Ganti dengan nama branch Anda jika berbeda

echo "[*] Auto-Updater Bot Telegram dimulai..."

# Memastikan kita berada di direktori repositori
cd "$(dirname "$0")"

# Deteksi perintah py/python3/python yang tersedia
if command -v py &>/dev/null; then
    PYTHON_CMD="py"
elif command -v python3 &>/dev/null; then
    PYTHON_CMD="python3"
elif command -v python &>/dev/null; then
    PYTHON_CMD="python"
else
    echo "[-] Python tidak ditemukan di laptop ini! Harap install Python terlebih dahulu."
    exit 1
fi

echo "[*] Menggunakan perintah Python: $PYTHON_CMD"

# Fungsi mencari PID bot yang kompatibel dengan Git Bash Windows & Unix
get_bot_pid() {
    ps -ef | grep "$BOT_SCRIPT" | grep -v grep | awk '{print $2}'
}

# Jalankan bot saat script ini pertama kali dibuka jika belum berjalan
PID=$(get_bot_pid)
if [ -z "$PID" ]; then
    echo "[*] Bot belum berjalan. Menjalankan Bot Telegram..."
    $PYTHON_CMD "$BOT_SCRIPT" > bot.log 2>&1 &
    sleep 2
    PID=$(get_bot_pid)
    if [ -n "$PID" ]; then
        echo "[+] Bot berhasil dijalankan dengan PID: $PID"
    else
        echo "[-] Gagal menjalankan bot. Silakan periksa file bot.log untuk detail error."
    fi
else
    echo "[*] Bot sudah berjalan dengan PID: $PID"
fi

while true; do
    # Ambil status terbaru dari remote
    git fetch origin $BRANCH &>/dev/null
    
    if [ $? -eq 0 ]; then
        LOCAL=$(git rev-parse HEAD)
        REMOTE=$(git rev-parse @{u})
        
        if [ "$LOCAL" != "$REMOTE" ]; then
            echo "[+] Terdeteksi kode baru di remote! Melakukan pull..."
            git pull origin $BRANCH
            
            # Cari PID dari bot yang sedang berjalan
            PID=$(get_bot_pid)
            if [ -n "$PID" ]; then
                echo "[*] Menghentikan bot yang sedang berjalan (PID: $PID)..."
                kill -15 $PID
                sleep 3
                # Jika masih hidup, paksa kill
                kill -9 $PID 2>/dev/null
            fi
            
            echo "[+] Menjalankan kembali Bot Telegram..."
            $PYTHON_CMD "$BOT_SCRIPT" > bot.log 2>&1 &
            echo "[+] Bot berhasil diperbarui dan dijalankan kembali!"
        fi
    else
        echo "[-] Gagal terhubung ke Git remote. Memeriksa kembali nanti..."
    fi
    
    sleep $INTERVAL
done
