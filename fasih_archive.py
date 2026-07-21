import os
import sys
import shutil
import subprocess

def prepare_staging(assignment_id: str, data_json_encrypted: str, work_dir: str, principal_data: dict = None) -> str:
    """Prepares the staging directory and writes the encrypted data.json and companion files safely for parallel workers."""
    staging_dir = os.path.join(work_dir, "staging", assignment_id)
    if os.path.exists(staging_dir):
        shutil.rmtree(staging_dir, ignore_errors=True)
    os.makedirs(staging_dir, exist_ok=True)
    with open(os.path.join(staging_dir, "data.json"), "w", encoding="utf-8") as f:
        f.write(data_json_encrypted)
    # Add checksum.md5 (static placeholder expected by BPS backend)
    with open(os.path.join(staging_dir, "checksum.md5"), "w", encoding="utf-8") as f:
        f.write("This file will contain MD5")
    # Add reference.zip (empty zip file structure expected by BPS backend)
    with open(os.path.join(staging_dir, "reference.zip"), "wb") as f:
        f.write(b"PK\x05\x06\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00")
    # Add principal.json if provided
    if principal_data:
        import json
        with open(os.path.join(staging_dir, "principal.json"), "w", encoding="utf-8") as f:
            json.dump(principal_data, f, ensure_ascii=False)
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
            msg = (
                "Error: '7z' executable not found in PATH or standard installation directories.\n"
                "Silakan install 7-Zip (Windows) atau p7zip (Mac/Linux), atau install library py7zr: pip install py7zr"
            )
            print(f"[-] {msg}")
            raise FileNotFoundError(msg)

    # 4. Run standard 7z subprocess
    try:
        subprocess.run(
            [cmd, "a", "-t7z", archive_path, staging_dir],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            check=True,
        )
    except subprocess.CalledProcessError as e:
        err_msg = f"7z archive creation failed: {e.stderr.decode()}"
        print(f"[-] {err_msg}")
        raise RuntimeError(err_msg)

def create_7z_archive(data_json_encrypted: str, assignment_id: str, work_dir: str, principal_data: dict = None) -> str:
    """Creates a passwordless .7z archive containing the encrypted survey payload and companion files."""
    staging_dir = prepare_staging(assignment_id, data_json_encrypted, work_dir, principal_data)
    archive_path = os.path.join(work_dir, f"{assignment_id}.7z")
    if os.path.exists(archive_path):
        try:
            os.remove(archive_path)
        except Exception:
            pass
    try:
        run_compress(archive_path, staging_dir)
    finally:
        if os.path.exists(staging_dir):
            try:
                shutil.rmtree(staging_dir, ignore_errors=True)
            except Exception:
                pass
    return archive_path
