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
    """Executes the 7z subprocess to compress the staging directory."""
    try:
        subprocess.run(
            ["7z", "a", "-t7z", archive_path, staging_dir],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE,
            check=True,
        )
    except FileNotFoundError:
        print("[-] Error: '7z' command not found. Install p7zip.")
        sys.exit(1)
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
