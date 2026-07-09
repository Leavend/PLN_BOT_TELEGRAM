#!/bin/bash
# Install shortcut commands ke Termux $PATH
# Jalankan sekali: bash petugas_client/install_commands.sh

BIN="$HOME/.local/bin"
REPO="$(cd "$(dirname "$0")/.." && pwd)"

mkdir -p "$BIN"

# fasih-submit: batch submit
cat > "$BIN/fasih-submit" << EOF
#!/bin/bash
cd "$REPO"
python3 petugas_client/batch_submit.py "\$@"
EOF

# fasih-login: login SSO BPS
cat > "$BIN/fasih-login" << EOF
#!/bin/bash
cd "$REPO"
python3 -c "
from petugas_client.batch_submit import ensure_login
t = ensure_login()
print('✅ Login OK —', t.get('access_token','')[:20] + '...')
"
EOF

# fasih-lookup: quick PLN lookup
cat > "$BIN/fasih-lookup" << EOF
#!/bin/bash
cd "$REPO"
python3 -c "
import sys, json
from petugas_client.batch_submit import pln_lookup
if len(sys.argv) < 2:
    print('Usage: fasih-lookup <idpel/nometer>')
    sys.exit(1)
val = sys.argv[1]
r = pln_lookup(idpel=val) if len(val)==12 else pln_lookup(nometer=val)
if r:
    print(json.dumps(r, indent=2, ensure_ascii=False))
else:
    print('❌ Tidak ditemukan')
" "\$@"
EOF

# fasih-submit-batch: interactive paste mode
cat > "$BIN/fasih-submit-batch" << 'OUTER'
#!/bin/bash
cd "REPO_PLACEHOLDER"
python3 -c "
import sys, re

print('📋 Paste ID Pelanggan (satu per baris)')
print('   Tekan ENTER 2x untuk mulai submit')
print('─' * 40)

lines = []
empty = 0
while True:
    try:
        line = input()
    except EOFError:
        break
    stripped = line.strip()
    if not stripped:
        empty += 1
        if empty >= 2:
            break
        continue
    empty = 0
    # extract 12-digit numbers from line
    ids = re.findall(r'\b\d{12}\b', stripped)
    if ids:
        lines.extend(ids)
    elif stripped.isdigit() and len(stripped) >= 8:
        lines.append(stripped)

if not lines:
    print('❌ Tidak ada ID valid')
    sys.exit(1)

# dedup, preserve order
seen = set()
unique = []
for x in lines:
    if x not in seen:
        seen.add(x)
        unique.append(x)

print(f'\n✅ {len(unique)} ID Pelanggan siap submit:')
for i, x in enumerate(unique, 1):
    print(f'   {i}. {x}')

confirm = input(f'\nLanjut submit {len(unique)} ID? (y/n): ').strip().lower()
if confirm not in ('y', 'yes', ''):
    print('❌ Dibatalkan')
    sys.exit(0)

import subprocess
ids_str = ','.join(unique)
subprocess.run([sys.executable, 'petugas_client/batch_submit.py'] + sys.argv[1:] + ['--list', ids_str])
" "$@"
OUTER
sed -i "s|REPO_PLACEHOLDER|$REPO|g" "$BIN/fasih-submit-batch" 2>/dev/null || \
  sed -i '' "s|REPO_PLACEHOLDER|$REPO|g" "$BIN/fasih-submit-batch"

# fasih-reregister: paste mode + --force (submit ulang record lama yang belum tercatat)
cat > "$BIN/fasih-reregister" << 'OUTER'
#!/bin/bash
cd "REPO_PLACEHOLDER"
python3 -c "
import sys, re

print('🔁 RE-REGISTER — submit ulang record yang BELUM tercatat di FASIH')
print('   (yang sudah tercatat otomatis dilewati, tidak dobel)')
print('📋 Paste ID Pelanggan (satu per baris), ENTER 2x untuk mulai')
print('─' * 40)

lines = []
empty = 0
while True:
    try:
        line = input()
    except EOFError:
        break
    stripped = line.strip()
    if not stripped:
        empty += 1
        if empty >= 2:
            break
        continue
    empty = 0
    ids = re.findall(r'\b\d{12}\b', stripped)
    if ids:
        lines.extend(ids)
    elif stripped.isdigit() and len(stripped) >= 8:
        lines.append(stripped)

if not lines:
    print('❌ Tidak ada ID valid')
    sys.exit(1)

seen = set()
unique = []
for x in lines:
    if x not in seen:
        seen.add(x)
        unique.append(x)

print(f'\n✅ {len(unique)} ID Pelanggan siap RE-REGISTER:')
for i, x in enumerate(unique, 1):
    print(f'   {i}. {x}')

confirm = input(f'\nLanjut re-register {len(unique)} ID? (y/n): ').strip().lower()
if confirm not in ('y', 'yes', ''):
    print('❌ Dibatalkan')
    sys.exit(0)

import subprocess
ids_str = ','.join(unique)
subprocess.run([sys.executable, 'petugas_client/batch_submit.py', '--force'] + sys.argv[1:] + ['--list', ids_str])
" "$@"
OUTER
sed -i "s|REPO_PLACEHOLDER|$REPO|g" "$BIN/fasih-reregister" 2>/dev/null || \
  sed -i '' "s|REPO_PLACEHOLDER|$REPO|g" "$BIN/fasih-reregister"

# fasih-logout: hapus token, login ulang
cat > "$BIN/fasih-logout" << EOF
#!/bin/bash
cd "$REPO"
if [ -f fasih_token.json ]; then
    rm fasih_token.json
    echo "✅ Logout berhasil. Jalankan fasih-login untuk login akun lain."
else
    echo "ℹ️  Belum login."
fi
EOF

# fasih-update: pull latest code
cat > "$BIN/fasih-update" << EOF
#!/bin/bash
cd "$REPO"
git pull
echo "✅ Updated!"
EOF

# fasih-status: check config
cat > "$BIN/fasih-status" << EOF
#!/bin/bash
cd "$REPO"
python3 -c "
import os, json, requests
# Resolve URL like submit does: pln_url.txt (git-tracked) wins, .env only fallback
from petugas_client.batch_submit import PLN_API_URL as url, PLN_API_KEY as key
token = os.path.exists('fasih_token.json')
print('📡 PLN API:', url or '❌ NOT SET')
print('🔑 API Key:', ('✅ ' + key[:8] + '...') if key else '⚠️  kosong')
print('🎫 BPS Token:', '✅ ada' if token else '❌ belum login')
if url:
    try:
        d = requests.get(url + '/health', timeout=5).json()
        print('🏥 PLN Server:', '✅ online —', d.get('photos',0), 'foto')
    except:
        print('🏥 PLN Server: ❌ offline')
# BPS readiness: probe the SAME python path submit uses. A curl probe would
# false-positive — the BPS WAF resets python-requests' TLS but not curl's, so
# 'BPS up via curl' does NOT mean 'submit will work'. This runs the real call.
if token:
    try:
        from fasih_auth import get_headers
        from fasih_api import fetch_surveys
        with open('fasih_token.json') as f: td = json.load(f)
        n = len(fetch_surveys(get_headers(td)))
        print('🏛️  BPS Survey: ✅ ready (' + str(n) + ' survei) — submit bisa')
    except Exception as e:
        m = str(e)
        if any(c in m for c in ('500','502','503','504')):
            print('🏛️  BPS Survey: ❌ down 5xx — tunggu beberapa menit')
        elif ('reset' in m.lower()) or ('Max retries' in m) or ('refused' in m.lower()):
            print('🏛️  BPS Survey: ❌ diblok/reset (WAF/jaringan) — coba lagi / ganti jaringan')
        else:
            print('🏛️  BPS Survey: ❌ gagal — ' + m[:50])
else:
    print('🏛️  BPS Survey: ⚠️  login dulu (fasih-login)')
"
EOF

chmod +x "$BIN"/fasih-*

# Add to PATH if not already
if ! echo "$PATH" | grep -q "$BIN"; then
    echo "export PATH=\"$BIN:\$PATH\"" >> "$HOME/.bashrc"
    echo "export PATH=\"$BIN:\$PATH\"" >> "$HOME/.zshrc" 2>/dev/null
    export PATH="$BIN:$PATH"
fi

echo ""
echo "✅ Commands installed!"
echo ""
echo "  fasih-submit data.txt     Batch submit dari file"
echo "  fasih-submit -l 234..     Submit langsung"
echo "  fasih-submit-batch        Paste ID, enter 2x, jalan"
echo "  fasih-reregister          Submit ulang record lama yang belum tercatat"
echo "  fasih-login               Login BPS SSO"
echo "  fasih-logout              Logout & ganti akun"
echo "  fasih-lookup 234000...    Cek data PLN"
echo "  fasih-update              Update script"
echo "  fasih-status              Cek koneksi & config"
echo ""
