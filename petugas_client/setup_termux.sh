#!/bin/bash
# Setup script untuk Termux di HP petugas
# Jalankan sekali setelah install Termux

echo "📱 Setup Batch Submit Petugas untuk Termux"
echo "==========================================="

# Update dan install dependencies
pkg update -y
pkg install -y python git

# Install Python packages
pip install requests python-dotenv pycryptodome py7zr

# Clone repo (atau pull kalau sudah ada)
if [ -d "PLN_BOT_TELEGRAM" ]; then
    echo "📥 Updating repo..."
    cd PLN_BOT_TELEGRAM && git pull
else
    echo "📥 Cloning repo..."
    git clone https://github.com/Leavend/PLN_BOT_TELEGRAM.git
    cd PLN_BOT_TELEGRAM
fi

# Buat .env kalau belum ada
if [ ! -f ".env" ]; then
    echo ""
    echo "⚙️  Konfigurasi .env"
    read -p "  URL PLN API Server: " PLN_URL
    read -p "  API Key (kosongkan jika tidak ada): " PLN_KEY

    cat > .env << EOF
PLN_API_URL=$PLN_URL
PLN_API_KEY=$PLN_KEY
EOF
    echo "✅ .env dibuat!"
fi

# Prevent Termux dari mati saat layar mati
termux-wake-lock 2>/dev/null

echo ""
echo "✅ Setup selesai!"
echo ""
echo "Cara pakai:"
echo "  cd PLN_BOT_TELEGRAM"
echo "  python3 petugas_client/batch_submit.py data.txt"
echo ""
echo "Contoh data.txt:"
echo "  234000279419"
echo "  234000093158"
echo "  234000176527"
