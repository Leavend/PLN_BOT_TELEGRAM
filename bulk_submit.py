#!/usr/bin/env python3
import os
import sys
import csv
import argparse
import subprocess
from datetime import datetime

def perform_initial_login(email: str, password: str):
    """Initial session login/caching run."""
    print("[*] Running initial login to cache the session token...")
    login_cmd = ["python3", "submit_fasih.py", "--email", email]
    if password:
        login_cmd.extend(["--password", password])
    login_cmd.append("--list")
    result = subprocess.run(login_cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    if result.returncode != 0:
        print("[-] Initial login failed:")
        print(result.stderr or result.stdout)
        sys.exit(1)
    print("[+] Initial login successful! Token cached.")

def read_csv_records(csv_path: str) -> list:
    """Read and normalize records from target CSV path."""
    if not os.path.exists(csv_path):
        print(f"[-] CSV file not found: {csv_path}")
        sys.exit(1)
    records = []
    with open(csv_path, "r", encoding="utf-8-sig") as f:
        reader = csv.DictReader(f)
        reader.fieldnames = [name.strip().lower() for name in reader.fieldnames] if reader.fieldnames else []
        for row in reader:
            records.append({k.strip(): v.strip() for k, v in row.items() if k})
    print(f"\n[+] Read {len(records)} records from {csv_path}")
    if not records:
        print("[-] No records found in CSV.")
        sys.exit(1)
    return records

def build_subprocess_cmd(r: dict, dry_run: bool) -> list:
    """Construct argument list for the submit_fasih.py execution."""
    cmd = ["python3", "submit_fasih.py", "--idpel", r["idpel"], "--nometer", r["nometer"]]
    for key in ["nama", "alamat", "latitude", "longitude", "photo_path"]:
        val = r.get(key)
        if val:
            flag = "--lat" if key == "latitude" else "--lon" if key == "longitude" else f"--{key.replace('_path', '')}"
            cmd.extend([flag, val])
    if dry_run:
        cmd.append("--dry-run")
    return cmd

def parse_subprocess_result(result: subprocess.CompletedProcess, dry_run: bool) -> tuple:
    """Assess and display output of run sub-process."""
    output = result.stdout + "\n" + result.stderr
    is_success = result.returncode == 0 and ("BERHASIL DI-SUBMIT" in output or "DRY RUN" in output or "Dry run" in output)
    if is_success:
        msg = "Dry-run check passed" if dry_run else "Successfully submitted"
        print(f"      ✅ {msg}")
        return True, msg
    err_lines = [line.strip() for line in output.split("\n") if line.strip().startswith("[-]")]
    msg = err_lines[0] if err_lines else "Submission failed"
    print(f"      ❌ Failed: {msg}")
    return False, msg

def process_single_record(idx: int, total: int, r: dict, dry_run: bool) -> dict:
    """Handle verification and child process run for one CSV row."""
    idpel, nometer = r.get("idpel"), r.get("nometer")
    if not idpel or not nometer:
        print(f"[-] Row {idx+1}: Missing idpel or nometer. Skipping.")
        return {"idpel": idpel or "", "nometer": nometer or "", "status": "SKIPPED", "message": "Missing idpel/nometer"}
    print(f"[{idx+1}/{total}] Processing IDPel: {idpel} | NoMeter: {nometer} | Nama: {r.get('nama','')}")
    cmd = build_subprocess_cmd(r, dry_run)
    result = subprocess.run(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
    ok, message = parse_subprocess_result(result, dry_run)
    return {
        "idpel": idpel, "nometer": nometer, "nama": r.get("nama", ""), "alamat": r.get("alamat", ""),
        "latitude": r.get("latitude", ""), "longitude": r.get("longitude", ""), "photo_path": r.get("photo_path", ""),
        "status": "SUCCESS" if ok else "FAILED", "message": message
    }

def write_report_file(report_path: str, report_rows: list):
    """Write outcome details into execution report CSV."""
    with open(report_path, "w", encoding="utf-8", newline="") as f:
        fieldnames = ["idpel", "nometer", "nama", "alamat", "latitude", "longitude", "photo_path", "status", "message"]
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(report_rows)

def print_summary(total: int, successes: int, failures: int, path: str):
    """Output batch execution outcomes summary."""
    print("\n" + "=" * 60)
    print("  BULK SUBMISSION SUMMARY")
    print("=" * 60)
    print(f"  Total processed : {total}")
    print(f"  Successes       : {successes}")
    print(f"  Failures        : {failures}")
    print(f"  Report saved to : {path}")
    print("=" * 60)

def run_bulk_submit(csv_path: str, dry_run: bool, email: str = None, password: str = None):
    """Orchestrate bulk submission flow."""
    if email:
        perform_initial_login(email, password)
    records = read_csv_records(csv_path)
    report_rows = []
    for idx, r in enumerate(records):
        report_rows.append(process_single_record(idx, len(records), r, dry_run))
    successes = sum(1 for row in report_rows if row["status"] == "SUCCESS")
    failures = sum(1 for row in report_rows if row["status"] == "FAILED")
    report_path = f"bulk_submit_report_{datetime.now().strftime('%Y%m%d_%H%M%S')}.csv"
    write_report_file(report_path, report_rows)
    print_summary(len(records), successes, failures, report_path)

def main():
    parser = argparse.ArgumentParser(description="BPS Fasih Bulk Submission Orchestrator")
    parser.add_argument("csv_path", help="Path to input CSV file")
    parser.add_argument("--dry-run", action="store_true", help="Perform dry-run checks without submitting")
    parser.add_argument("--email", help="BPS Account email for SSO login")
    parser.add_argument("--password", help="BPS Account password")
    args = parser.parse_args()
    run_bulk_submit(args.csv_path, args.dry_run, args.email, args.password)

if __name__ == "__main__":
    main()
