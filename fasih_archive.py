import os
import sys
import shutil
import subprocess

def prepare_staging(assignment_id: str, data_json_encrypted: str, work_dir: str) -> str:
    """Prepares the staging directory and writes the encrypted data.json."""
    staging_parent = os.path.join(work_dir, "staging")
    if os.path.exists(staging_parent):
        shutil.rmtree(staging_parent)
    staging_dir = os.path.join(staging_parent, assignment_id)
    os.makedirs(staging_dir, exist_ok=True)
    with open(os.path.join(staging_dir, "data.json"), "w", encoding="utf-8") as f:
        f.write(data_json_encrypted)
    return staging_dir

def run_compress(archive_path: str, staging_dir: str):
    """Executes the 7z subprocess to compress the staging directory with multi-platform fallbacks."""
    # 1. Check if '7z' is in system PATH
    cmd = shutil.which("7z")
    
    # 2. If not in PATH, and we are on Windows, search common 7-Zip installation paths
    if not cmd and os.name == 'nt':
        common_paths = [
            os.path.join(os.environ.get("ProgramFiles", "C:\\Program Files"), "7-Zip", "7z.exe"),
            os.path.join(os.environ.get("ProgramFiles(x86)", "C:\\Program Files (x86)"), "7-Zip", "7z.exe"),
            "C:\\Program Files\\7-Zip\\7z.exe",
            "C:\\Program Files (x86)\\7-Zip\\7z.exe"
        ]
        for p in common_paths:
            if os.path.exists(p):
                cmd = p
                break

    # 3. If still not found, try to use py7zr python library
    if not cmd:
        try:
            import py7zr
            with py7zr.SevenZipFile(archive_path, 'w') as archive:
                archive.writeall(staging_dir, arcname=os.path.basename(staging_dir))
            return
        except ImportError:
            print("[-] Error: '7z' executable not found in PATH or standard installation directories.")
            if os.name == 'nt':
                print("[-] Silakan install 7-Zip (https://www.7-zip.org/) dan pastikan terinstall di C:\\Program Files\\7-Zip\\")
                print("[-] Atau install library python: pip install py7zr")
            else:
                print("[-] Silakan install p7zip (sudo apt install p7zip-full / brew install p7zip)")
                print("[-] Atau install library python: pip install py7zr")
            sys.exit(1)

    # 4. Run standard 7z subprocess
    try:
        subprocess.run(
            [cmd, "a", "-t7z", archive_path, staging_dir],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            check=True,
        )
    except subprocess.CalledProcessError as e:
        print(f"[-] 7z archive creation failed: {e.stderr.decode()}")
        sys.exit(1)

def create_7z_archive(data_json_encrypted: str, assignment_id: str, work_dir: str) -> str:
    """Creates a passwordless .7z archive containing the encrypted survey payload."""
    staging_dir = prepare_staging(assignment_id, data_json_encrypted, work_dir)
    archive_path = os.path.join(work_dir, f"{assignment_id}.7z")
    if os.path.exists(archive_path):
        os.remove(archive_path)
    run_compress(archive_path, staging_dir)
    return archive_path
