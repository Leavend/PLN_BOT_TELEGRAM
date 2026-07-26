import os
import sys
import time
import glob
import logging
import pandas as pd
from concurrent.futures import ThreadPoolExecutor, as_completed

# Add repo root to path
REPO_ROOT = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, REPO_ROOT)
os.environ["FASIH_MULTI_REGION"] = "1"  # Enable multi-region PLN AP2T pool

from petugas_client.batch_submit import pln_lookup

logging.basicConfig(level=logging.INFO, format="%(asctime)s [%(levelname)s] %(message)s", datefmt="%H:%M:%S")
logger = logging.getLogger("DataCleansing")

OUTPUT_DIR = os.path.join(REPO_ROOT, "Folder-Runner-Cleansed")
os.makedirs(OUTPUT_DIR, exist_ok=True)

# 1. Load all completed IDPels from checkpoint files to avoid re-cleansing/re-submitting completed data
completed_idpels = set()
for cp in glob.glob("Folder-Runner/*.checkpoint.csv"):
    try:
        ckpt_df = pd.read_csv(cp)
        if not ckpt_df.empty and "catatan" in ckpt_df.columns and "idpel" in ckpt_df.columns:
            s_mask = ckpt_df["catatan"] == "Sukses: Data berhasil dikirimkan ke BPS!"
            for val in ckpt_df[s_mask]["idpel"].dropna():
                clean_id = str(val).strip()
                if clean_id:
                    completed_idpels.add(clean_id)
    except Exception as e:
        logger.warning(f"Error reading checkpoint {cp}: {e}")

logger.info(f"Loaded {len(completed_idpels):,} unique completed IDPels from checkpoint logs.")

def kel_ok(d):
    k = str((d or {}).get("kd_kel") or "").strip()
    return len(k) == 10 and k.isdigit()

def check_idpel_pln(idpel: str) -> bool:
    """Verify IDPel against local PLN AP2T server pool (0 BPS quota consumed)."""
    for attempt in range(1, 3):
        pln_data = pln_lookup(idpel=idpel)
        if pln_data:
            if kel_ok(pln_data):
                return True
        if attempt < 2:
            time.sleep(0.1)
    return False

def process_cleansing_file(file_path: str):
    file_name = os.path.basename(file_path)
    logger.info(f"\n==================================================")
    logger.info(f"🔄 MEMULAI CLEANSING: {file_name}")
    logger.info(f"==================================================")
    
    start_time = time.time()
    engine = "calamine" if file_path.endswith(".xls") else None
    
    try:
        df = pd.read_excel(file_path, engine=engine)
    except Exception as e:
        logger.error(f"❌ Failed loading {file_name}: {e}")
        return

    initial_count = len(df)
    logger.info(f"📋 Total Baris Awal: {initial_count:,} baris")

    # Find IDPel column
    idpel_col = next((c for c in df.columns if str(c).upper().strip() in ("IDPEL", "ID_PELANGGAN", "ID_PEL", "IDPELANGGAN")), None)
    if not idpel_col:
        logger.error(f"❌ Kolom IDPel tidak ditemukan pada {file_name}!")
        return

    # Find Tarif column
    tarif_col = next((c for c in df.columns if str(c).upper().strip() in ("TARIF", "TARIF_DAYA", "KDTARIF")), None)
    
    # Find Status Padan column
    padan_col = next((c for c in df.columns if str(c).upper().strip() in ("STATUS_PADAN", "PADAN_STATUS", "STATUSPADAN")), None)

    # Step 1: Pre-filter DataFrame vectorially
    df["IDPEL_CLEAN"] = df[idpel_col].astype(str).str.strip()
    
    # Rule 1: Exclude completed IDPels
    mask_pending = ~df["IDPEL_CLEAN"].isin(completed_idpels)
    cnt_already_completed = initial_count - mask_pending.sum()
    logger.info(f"  [Rule 1] Excluded {cnt_already_completed:,} IDPel yang sudah SUKSES di checkpoint.")

    # Rule 2: Filter Tarif Tipe R (Rumah Tangga Only)
    cnt_non_r = 0
    if tarif_col:
        tarif_series = df[tarif_col].fillna("").astype(str).str.strip().str.upper()
        # Keep if starts with R
        mask_r = tarif_series.str.startswith("R")
        cnt_non_r = mask_pending.sum() - (mask_pending & mask_r).sum()
        mask_pending = mask_pending & mask_r
        logger.info(f"  [Rule 2] Excluded {cnt_non_r:,} IDPel dengan Tarif Non-Rumah Tangga (bukan tipe R).")

    # Rule 3: Filter STATUS_PADAN == 'BELUM PADAN' (Exclude 'SUDAH PADAN')
    cnt_already_padan = 0
    if padan_col:
        padan_series = df[padan_col].fillna("").astype(str).str.strip().str.upper()
        # Exclude SUDAH PADAN or PADAN (keep BELUM PADAN or empty/blank)
        mask_not_padan = ~padan_series.str.contains("SUDAH|SUDAH PADAN|^PADAN$", regex=True)
        cnt_already_padan = mask_pending.sum() - (mask_pending & mask_not_padan).sum()
        mask_pending = mask_pending & mask_not_padan
        logger.info(f"  [Rule 3] Excluded {cnt_already_padan:,} IDPel yang STATUS_PADAN-nya 'SUDAH PADAN'.")

    # De-duplicate within the file itself
    df_filtered = df[mask_pending].drop_duplicates(subset=["IDPEL_CLEAN"]).copy()
    candidate_idpels = df_filtered["IDPEL_CLEAN"].tolist()
    logger.info(f"🔎 Total Kandidat IDPel Siap Di-Verify via PLN AP2T Lookup: {len(candidate_idpels):,} IDPel")

    if not candidate_idpels:
        logger.warning(f"⚠️ Tidak ada kandidat tersisa untuk {file_name}.")
        return

    # Step 2: Parallel PLN Lookup Verification (40 workers)
    logger.info(f"⚡ Verifikasi PLN AP2T (40 parallel workers)...")
    valid_idpels = set()
    invalid_pln_cnt = 0
    
    with ThreadPoolExecutor(max_workers=40) as executor:
        future_to_idpel = {executor.submit(check_idpel_pln, idp): idp for idp in candidate_idpels}
        completed = 0
        total = len(candidate_idpels)
        
        for future in as_completed(future_to_idpel):
            idp = future_to_idpel[future]
            completed += 1
            try:
                is_valid = future.result()
                if is_valid:
                    valid_idpels.add(idp)
                else:
                    invalid_pln_cnt += 1
            except Exception:
                invalid_pln_cnt += 1
            
            if completed % 1000 == 0 or completed == total:
                logger.info(f"   [PLN VERIFY] Progress: {completed:,}/{total:,} ({completed/total*100:.1f}%) | Valid: {len(valid_idpels):,} | Invalid/KdKel Empty: {invalid_pln_cnt:,}")

    # Step 3: Final Filter & Save to Folder-Runner-Cleansed
    df_final = df_filtered[df_filtered["IDPEL_CLEAN"].isin(valid_idpels)].copy()
    df_final.drop(columns=["IDPEL_CLEAN"], inplace=True, errors="ignore")

    # Output filename as .xlsx
    base_out_name = os.path.splitext(file_name)[0] + ".xlsx"
    out_path = os.path.join(OUTPUT_DIR, base_out_name)
    
    df_final.to_excel(out_path, engine="openpyxl", index=False)
    elapsed = time.time() - start_time
    
    logger.info(f"\n✅ CLEANSING SELESAI: {base_out_name}")
    logger.info(f"  - Path Output            : {out_path}")
    logger.info(f"  - Total Baris Awal       : {initial_count:,}")
    logger.info(f"  - Total Baris Hasil Clean : {len(df_final):,} ({len(df_final)/initial_count*100:.1f}%)")
    logger.info(f"  - Dibuang (Sudah Sukses)  : {cnt_already_completed:,}")
    logger.info(f"  - Dibuang (Non-R Tarif)   : {cnt_non_r:,}")
    logger.info(f"  - Dibuang (Sudah Padan)   : {cnt_already_padan:,}")
    logger.info(f"  - Dibuang (PLN Tak Lengkap): {invalid_pln_cnt:,}")
    logger.info(f"  - Waktu Eksekusi          : {elapsed:.1f} detik")

if __name__ == "__main__":
    if len(sys.argv) > 1:
        target_files = sys.argv[1:]
    else:
        target_files = [
            "Folder-Runner/PASCA&PRABAYAR PARIGI.xls",
            "Folder-Runner/DIL DONGGALA SEPTEMBER 2025.xlsx",
            "Folder-Runner/Bungku DIL no filter.xlsx"
        ]
    for tf in target_files:
        full_p = os.path.join(REPO_ROOT, tf) if not os.path.isabs(tf) else tf
        if os.path.exists(full_p):
            process_cleansing_file(full_p)
        else:
            logger.error(f"❌ File not found: {tf}")
