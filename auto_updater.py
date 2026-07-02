import os
import sys
import time
import subprocess

BOT_SCRIPT = "telegram_bot.py"
INTERVAL = 5
BRANCH = "main"

def get_git_revision():
    try:
        # Get remote hash fast without locking git index
        ls_remote_out = subprocess.check_output(["git", "ls-remote", "origin", f"refs/heads/{BRANCH}"]).decode().strip()
        if not ls_remote_out:
            return None, None
        remote_hash = ls_remote_out.split()[0]
        local_hash = subprocess.check_output(["git", "rev-parse", "HEAD"]).decode().strip()
        return local_hash, remote_hash
    except Exception as e:
        print(f"[-] Gagal memeriksa status Git: {e}")
        return None, None

def main():
    print(f"[*] Auto-Updater Python Bot Telegram dimulai.")
    print(f"[*] Mengawasi perubahan di branch '{BRANCH}' setiap {INTERVAL} detik...")
    
    # Pindah ke direktori script
    script_dir = os.path.dirname(os.path.abspath(__file__))
    if script_dir:
        os.chdir(script_dir)
    
    bot_process = None
    
    try:
        # Jalankan bot pertama kali
        print("[*] Menjalankan Bot Telegram...")
        bot_process = subprocess.Popen([sys.executable, BOT_SCRIPT])
        print(f"[+] Bot berhasil dijalankan dengan PID: {bot_process.pid}")
        
        while True:
            time.sleep(INTERVAL)
            
            # Cek status git
            local_hash, remote_hash = get_git_revision()
            if local_hash and remote_hash and local_hash != remote_hash:
                print("[+] Terdeteksi kode baru di remote! Melakukan pull...")
                pull_res = subprocess.run(["git", "pull", "origin", BRANCH], capture_output=True, text=True)
                print(pull_res.stdout)
                
                # Matikan bot lama
                if bot_process:
                    print(f"[*] Menghentikan bot yang sedang berjalan (PID: {bot_process.pid})...")
                    bot_process.terminate()
                    try:
                        bot_process.wait(timeout=5)
                    except subprocess.TimeoutExpired:
                        bot_process.kill()
                        bot_process.wait()
                    print("[+] Bot berhasil dihentikan.")
                
                # Jalankan bot baru
                print("[+] Menjalankan kembali Bot Telegram...")
                bot_process = subprocess.Popen([sys.executable, BOT_SCRIPT])
                print(f"[+] Bot berhasil dijalankan kembali dengan PID: {bot_process.pid}")
            else:
                # Cek jika proses bot tiba-tiba mati sendiri (Auto-Recovery)
                if bot_process and bot_process.poll() is not None:
                    print("[-] Bot terdeteksi mati secara tidak sengaja. Memulai ulang bot...")
                    bot_process = subprocess.Popen([sys.executable, BOT_SCRIPT])
                    print(f"[+] Bot berhasil dihidupkan kembali dengan PID: {bot_process.pid}")
                    
    except KeyboardInterrupt:
        print("\n[*] Mematikan Auto-Updater...")
        if bot_process:
            print(f"[*] Menghentikan bot (PID: {bot_process.pid})...")
            bot_process.terminate()
            bot_process.wait()
        print("[+] Selesai.")

if __name__ == "__main__":
    main()
