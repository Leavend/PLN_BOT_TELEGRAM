#!/bin/bash
# Setup script untuk Termux di HP petugas
# Jalankan sekali setelah install Termux

echo "📱 Setup Batch Submit Petugas untuk Termux"
echo "==========================================="

# Update dan install dependencies
pkg update -y
pkg install -y python git p7zip

# Install Python packages
pip install requests python-dotenv pycryptodome py7zr pandas openpyxl

# Navigate to repo root (detect if already inside, or clone fresh)
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
if [ -f "$SCRIPT_DIR/../submit_fasih.py" ]; then
    cd "$SCRIPT_DIR/.."
    echo "📥 Updating repo..."
    git pull
elif [ -d "PLN_BOT_TELEGRAM" ]; then
    echo "📥 Updating repo..."
    cd PLN_BOT_TELEGRAM && git pull
else
    echo "📥 Cloning repo..."
    git clone https://github.com/Leavend/PLN_BOT_TELEGRAM.git
    cd PLN_BOT_TELEGRAM
fi

# Configure Git Sparse-Checkout to exclude heavy house photos on HP Petugas
if [ -d ".git" ]; then
    git config core.sparseCheckout true 2>/dev/null
    mkdir -p .git/info
    cat > .git/info/sparse-checkout << 'SPARSE_EOF'
/*
!house_photos/
!Samarinda_Photos/
!Bontang_Photos/
!Balikpapan_Photos/
!*_Photos/
!*.webp
!*.7z
SPARSE_EOF
    git read-tree -mu HEAD 2>/dev/null || true
    rm -rf house_photos Samarinda_Photos Bontang_Photos Balikpapan_Photos *_Photos fasih_downloaded extract_* extracted_* photo_*.webp 2>/dev/null
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

# Install shortcut commands
bash petugas_client/install_commands.sh

echo ""
echo "✅ Setup selesai! Restart terminal, lalu:"
echo ""
echo "  fasih-status              Cek koneksi"
echo "  fasih-login               Login BPS"
echo "  fasih-submit data.txt     Batch submit"
echo "  fasih-lookup 234000...    Cek PLN"
echo "  fasih-update              Update script"
echo ""
echo "Contoh data.txt:"
echo "  234000279419"
echo "  234000093158"
