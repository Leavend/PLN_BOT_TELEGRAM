#!/bin/bash
# auto_updater.sh
# Menjalankan pengecekan Git repository secara berkala untuk melakukan auto-update bot.

BOT_SCRIPT="telegram_bot.py"
INTERVAL=60 # Cek update setiap 60 detik
BRANCH="main" # Ganti dengan nama branch Anda jika berbeda (misal: master)

echo "[*] Auto-Updater Bot Telegram dimulai. Memeriksa pembaruan setiap $INTERVAL detik..."

# Memastikan kita berada di direktori repositori
cd "$(dirname "$0")"

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
            PID=$(pgrep -f "python3 $BOT_SCRIPT")
            if [ -n "$PID" ]; then
                echo "[*] Menghentikan bot yang sedang berjalan (PID: $PID)..."
                kill -15 $PID
                sleep 3
                # Jika masih hidup, paksa kill
                kill -9 $PID 2>/dev/null
            fi
            
            echo "[+] Menjalankan kembali Bot Telegram..."
            nohup python3 $BOT_SCRIPT > bot.log 2>&1 &
            echo "[+] Bot berhasil diperbarui dan dijalankan kembali!"
        fi
    else
        echo "[-] Gagal terhubung ke Git remote. Memeriksa kembali nanti..."
    fi
    
    sleep $INTERVAL
done
