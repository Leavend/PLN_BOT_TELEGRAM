#!/bin/bash
# auto_updater.sh
# Menjalankan pengecekan Git repository secara berkala untuk melakukan auto-update bot.

BOT_SCRIPT="telegram_bot.py"
INTERVAL=5 # Cek update lebih cepat (setiap 5 detik)
BRANCH="main" # Ganti dengan nama branch Anda jika berbeda

echo "[*] Auto-Updater Bot Telegram dimulai..."

# Memastikan kita berada di direktori repositori
cd "$(dirname "$0")"

# Fungsi untuk mengetes apakah perintah python benar-benar berfungsi (menghindari alias palsu Microsoft Store)
is_python_working() {
    local cmd="$1"
    if command -v "$cmd" &>/dev/null; then
        if "$cmd" -c "import sys" &>/dev/null; then
            return 0
        fi
    fi
    return 1
}

# Deteksi perintah py/python3/python yang bekerja
if is_python_working "py"; then
    PYTHON_CMD="py"
elif is_python_working "python3"; then
    PYTHON_CMD="python3"
elif is_python_working "python"; then
    PYTHON_CMD="python"
else
    echo "[-] Python tidak ditemukan atau tidak dapat dijalankan di laptop ini!"
    echo "[-] Silakan install Python dan pastikan bisa dijalankan dengan perintah 'py', 'python3', atau 'python'."
    exit 1
fi

echo "[*] Menggunakan perintah Python: $PYTHON_CMD"

# Fungsi mencari PID bot yang kompatibel dengan Git Bash Windows & Unix
get_bot_pid() {
    ps -ef | grep "$BOT_SCRIPT" | grep -v grep | grep -v "auto_updater.sh" | awk '{print $2}'
}

# Fungsi mematikan bot secara paksa dan bersih (kompatibel dengan Windows & Unix)
terminate_bot() {
    # 1. Coba matikan lewat Windows PowerShell jika berada di Windows/Git Bash
    if [[ "$OSTYPE" == "msys" || "$OSTYPE" == "cygwin" || "$OSTYPE" == "win32" ]]; then
        echo "[*] Menghentikan proses Windows Python yang menjalankan $BOT_SCRIPT..."
        if command -v powershell.exe &>/dev/null; then
            powershell.exe -Command "Get-CimInstance Win32_Process -Filter \"CommandLine like '%$BOT_SCRIPT%'\" | ForEach-Object { Stop-Process -Id \$_.ProcessId -Force }" &>/dev/null
            sleep 2
        elif command -v wmic &>/dev/null; then
            wmic process where "CommandLine like '%$BOT_SCRIPT%'" call terminate &>/dev/null
            sleep 2
        fi
    fi

    # 2. Cara standar Unix/Linux (dan fallback untuk Windows)
    local pids=$(get_bot_pid)
    if [ -n "$pids" ]; then
        for pid in $pids; do
            echo "[*] Menghentikan bot (PID: $pid)..."
            kill -15 $pid 2>/dev/null
            sleep 1
            if ps -p $pid >/dev/null 2>&1; then
                echo "[!] Bot (PID: $pid) masih berjalan, mengirimkan kill -9..."
                kill -9 $pid 2>/dev/null
            fi
        done
        sleep 1
    fi
}

# Selalu bersihkan bot lama saat script pertama kali dijalankan untuk mencegah duplikasi
echo "[*] Membersihkan seluruh proses Bot Telegram yang lama..."
terminate_bot

echo "[*] Menjalankan Bot Telegram..."
$PYTHON_CMD "$BOT_SCRIPT" > bot.log 2>&1 &
sleep 2
PID=$(get_bot_pid)
if [ -n "$PID" ]; then
    echo "[+] Bot berhasil dijalankan dengan PID: $PID"
else
    echo "[-] Gagal menjalankan bot. Silakan periksa file bot.log untuk detail error."
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
            
            echo "[*] Menghentikan bot lama..."
            terminate_bot
            
            echo "[+] Menjalankan kembali Bot Telegram dengan kode terbaru..."
            $PYTHON_CMD "$BOT_SCRIPT" > bot.log 2>&1 &
            sleep 2
            PID=$(get_bot_pid)
            echo "[+] Bot berhasil diperbarui dan dijalankan kembali dengan PID: $PID!"
        fi
    else
        echo "[-] Gagal terhubung ke Git remote. Memeriksa kembali nanti..."
    fi
    
    sleep $INTERVAL
done
