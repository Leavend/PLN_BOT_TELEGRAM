#!/usr/bin/env python3
import os
import re
import sys
import json
import argparse
from datetime import datetime
import requests
from urllib3.exceptions import InsecureRequestWarning

# Suppress SSL warnings
requests.packages.urllib3.disable_warnings(category=InsecureRequestWarning)

# ==================== CONFIGURATION ====================
# Default configurations for PT PLN (Persero) AP2T DR Site
DEFAULT_AP2T_URL = "https://ap2t.pln.co.id/infopelanggannewap2t-dr/InfoPelanggan/springGwtServices/infoServiceRpc"
DEFAULT_AP2T_IP = "10.72.35.8"
DEFAULT_GWT_PERMUTATION = "8569CEEBC61845F52D0351099CBBD991"
DEFAULT_GWT_RPC_POLICY = "E77335A8F06900DAEEA242B6FC41A6BB"

# Default cookies (based on your active session)
DEFAULT_COOKIES = (
    "_ga=GA1.1.486795188.1760494250; "
    "_ga_4KK5EDXW9S=GS2.1.s1761115634$o3$g0$t1761115634$j60$l0$h0; "
    "_ga_HSZXNYSSWE=GS2.1.s1761115635$o3$g0$t1761115635$j60$l0$h0; "
    "Pool_InfoPelanggan=1234567890.12345.0000"
)

# Hook to mock DNS resolution for ap2t.pln.co.id -> 10.72.35.8
import urllib3.util.connection as connection

orig_create_connection = connection.create_connection

def mock_create_connection(address, *args, **kwargs):
    host, port = address
    if host == "ap2t.pln.co.id":
        return orig_create_connection(("10.72.35.8", port), *args, **kwargs)
    return orig_create_connection(address, *args, **kwargs)

connection.create_connection = mock_create_connection


class PLNLookupTool:
    def __init__(self, cookies=None, ap2t_ip=None, ap2t_url=None):
        self.cookies = cookies or DEFAULT_COOKIES
        self.ap2t_ip = ap2t_ip or DEFAULT_AP2T_IP
        self.ap2t_url = ap2t_url or DEFAULT_AP2T_URL
        
        # Dynamically set IP if custom IP is requested
        if self.ap2t_ip != "10.72.35.8":
            def mock_create_connection_custom(address, *args, **kwargs):
                host, port = address
                if host == "ap2t.pln.co.id":
                    return orig_create_connection((self.ap2t_ip, port), *args, **kwargs)
                return orig_create_connection(address, *args, **kwargs)
            connection.create_connection = mock_create_connection_custom

        self.session = requests.Session()
        
        # Configure resilient connection pooling and retries with backoff
        from requests.adapters import HTTPAdapter
        from urllib3.util import Retry
        retries = Retry(
            total=2,
            backoff_factor=0.5,
            status_forcelist=[500, 502, 503, 504],
            raise_on_status=False
        )
        adapter = HTTPAdapter(pool_connections=25, pool_maxsize=25, max_retries=retries)
        self.session.mount("https://", adapter)
        self.session.mount("http://", adapter)

    def fetch_fresh_cookies_if_needed(self):
        """Perform a quick handshake to grab a fresh session cookie if needed/on failure."""
        try:
            base_url = "https://ap2t.pln.co.id/infopelanggannewap2t-dr/InfoPelanggan/"
            headers = {
                "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7)"
            }
            resp = self.session.get(base_url, headers=headers, verify=False, timeout=5)
            cookie_dict = self.session.cookies.get_dict()
            if "Pool_InfoPelanggan" in cookie_dict:
                merged = {}
                for item in self.cookies.split(";"):
                    if "=" in item:
                        k, v = item.strip().split("=", 1)
                        merged[k] = v
                for k, v in cookie_dict.items():
                    merged[k] = v
                self.cookies = "; ".join(f"{k}={v}" for k, v in merged.items())
                return True
        except Exception as e:
            print(f"[*] Failed to fetch fresh cookies: {e}")
        return False

    def make_gwt_rpc_request(self, method_name, params):
        """Builds and sends a GWT-RPC request to PLN AP2T server."""
        headers = {
            "Host": "ap2t.pln.co.id",
            "Content-Type": "text/x-gwt-rpc; charset=utf-8",
            "X-GWT-Module-Base": "https://ap2t.pln.co.id/infopelanggannewap2t-dr/InfoPelanggan/",
            "X-GWT-Permutation": DEFAULT_GWT_PERMUTATION,
            "Cookie": self.cookies,
            "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
        }

        # Determine GWT-RPC Payload structure based on parameters
        # Format: 5|0|len(string_pool)|string_pool_elements|module_base_idx|policy_idx|interface_idx|method_idx|num_args|arg_types|arg_values
        string_pool = [
            "https://ap2t.pln.co.id/infopelanggannewap2t-dr/InfoPelanggan/", # 1
            DEFAULT_GWT_RPC_POLICY,                                         # 2
            "com.iconpln.inforekening.client.rpc.InfoServiceRpc",           # 3
            method_name,                                                    # 4
            "java.lang.String/2004016611",                                  # 5
        ]

        # Add parameters to string pool
        param_indices = []
        for p in params:
            if p not in string_pool:
                string_pool.append(p)
            param_indices.append(string_pool.index(p) + 1)

        payload_parts = [
            "5", "0", str(len(string_pool))
        ]
        payload_parts.extend(string_pool)
        payload_parts.extend([
            "1", # module base URL index
            "2", # serialization policy index
            "3", # service interface index
            "4", # method index
            str(len(params)) # number of arguments
        ])
        
        # Argument types index (all parameters are java.lang.String)
        payload_parts.extend(["5"] * len(params))
        
        # Argument values index
        payload_parts.extend(str(idx) for idx in param_indices)
        
        # Trailing pipe
        payload = "|".join(payload_parts) + "|"

        max_retries = 2
        for attempt in range(max_retries):
            headers["Cookie"] = self.cookies
            try:
                # We target the actual URL. The custom adapter resolves ap2t.pln.co.id to 10.72.35.8
                response = self.session.post(self.ap2t_url, data=payload, headers=headers, verify=False, timeout=15)
                if response.status_code != 200:
                    print(f"[-] HTTP Error {response.status_code}: {response.reason}")
                    if attempt == 0:
                        print("[*] Retrying with fresh cookies...")
                        if self.fetch_fresh_cookies_if_needed():
                            continue
                    return None
                return self.parse_gwt_rpc_response(response.text)
            except Exception as e:
                print(f"[-] Connection failed on attempt {attempt + 1}: {e}")
                if attempt == 0:
                    print("[*] Attempting to fetch fresh cookies and retry...")
                    if self.fetch_fresh_cookies_if_needed():
                        continue
                return None

    def parse_gwt_rpc_response(self, text):
        """Extracts the JSON payload from GWT-RPC response format."""
        if not text.startswith("//OK"):
            if "IncompatibleRemoteServiceException" in text:
                print("[-] Error: GWT Serialization Policy mismatch. Policy hash might have changed.")
            else:
                print(f"[-] Invalid response format: {text[:200]}")
            return None
        
        try:
            # GWT response format: //OK[1,["JSON_STRING"],0,5]
            # We strip '//OK' and load as a normal JSON array
            array_text = text[4:]
            parsed_arr = json.loads(array_text)
            
            # The second element contains the return value list
            if len(parsed_arr) > 1 and isinstance(parsed_arr[1], list) and len(parsed_arr[1]) > 0:
                json_str = parsed_arr[1][0]
                return json.loads(json_str)
            return None
        except Exception as e:
            print(f"[-] Error parsing response JSON: {e}")
            return None

    def lookup_by_idpel(self, idpel):
        """Queries detailed customer info by ID Pelanggan (using getJsonInfoDilByIdpel)."""
        data = self.make_gwt_rpc_request("getJsonInfoDilByIdpel", [idpel])
        if not data:
            # Fallback to getJsonMainSearch if DIL is empty
            print(f"[*] Trying main search for IDPel {idpel}...")
            data = self.make_gwt_rpc_request("getJsonMainSearch", [idpel, "01", ""])
        return data

    def lookup_by_nometer(self, nometer):
        """Queries detailed customer info by Meter Number (using getJsonMasterNedisysByNomorMeter)."""
        data = self.make_gwt_rpc_request("getJsonMasterNedisysByNomorMeter", [nometer])
        if not data:
            # Fallback to getJsonMainSearch if Nedisys is empty
            print(f"[*] Trying main search for NoMeter {nometer}...")
            data = self.make_gwt_rpc_request("getJsonMainSearch", [nometer, "02", ""])
        return data


def save_focused_outputs(results_list, output_base):
    """Generates two focused Excel files from results_list:
    1. _identity.xlsx: ID Pelanggan, Nomor Meter, NIK, Nama
    2. _fasih_input.xlsx: Columns structured for BPS Fasih App API
    """
    import pandas as pd
    
    # Strip extension if present to get base path
    if output_base.endswith(".xlsx"):
        base_path = output_base[:-5]
    elif output_base.endswith(".xls"):
        base_path = output_base[:-4]
    else:
        base_path = output_base

    # Check if directory path is specified. If not, default to "outputs" folder
    dir_name = os.path.dirname(base_path)
    file_name = os.path.basename(base_path)
    
    if not dir_name:
        out_dir = "outputs"
        os.makedirs(out_dir, exist_ok=True)
        base_path = os.path.join(out_dir, file_name)
    else:
        os.makedirs(dir_name, exist_ok=True)

    identity_file = f"{base_path}_identity.xlsx"
    fasih_file = f"{base_path}_fasih_input.xlsx"


    # Lists to store row dictionaries
    identity_rows = []
    fasih_rows = []

    for res in results_list:
        if not res:
            continue
        
        # Profile data is normally in "dil_main" or "list"
        profiles = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
        if not profiles:
            continue
            
        for p in profiles:
            idpel = p.get("id_pelanggan", p.get("idpel", ""))
            nometer = (
                p.get("nometer_kwh") or 
                p.get("nomor_meter_kwh") or 
                p.get("no_meter_kwh") or 
                p.get("no_meter") or 
                p.get("nomor_meter") or 
                p.get("nometer") or 
                p.get("meter_number") or 
                ""
            )
            nik = p.get("noidentitas", p.get("no_identitas", ""))
            nama = p.get("nama", "")
            alamat = p.get("alamat") or p.get("namapnj") or p.get("alamat_51") or ""
            tarif = p.get("tarif", p.get("gol_tarif", ""))
            daya = p.get("daya", "")
            
            # Map coordinates if they exist
            latitude = p.get("koordinat_y", p.get("latitude", ""))
            longitude = p.get("koordinat_x", p.get("longitude", ""))

            # Clean and format coordinates/ID/Meter/NIK to string to prevent rounding
            idpel_str = str(idpel).strip() if idpel is not None else ""
            nometer_str = str(nometer).strip() if nometer is not None else ""
            nik_str = str(nik).strip() if nik is not None else ""

            # Add to identity report list
            identity_rows.append({
                "ID Pelanggan": idpel_str,
                "Nomor Meter": nometer_str,
                "NIK": nik_str,
                "Nama": str(nama).strip() if nama is not None else ""
            })

            # Add to BPS Fasih API Input list
            fasih_rows.append({
                "idpel": idpel_str,
                "nometer": nometer_str,
                "nama": str(nama).strip() if nama is not None else "",
                "alamat": str(alamat).strip() if alamat is not None else "",
                "tarif": str(tarif).strip() if tarif is not None else "",
                "daya": str(daya).strip() if daya is not None else "",
                "hasil": "",
                "kelurahan": "",
                "kdpm": "",
                "kddk": "",
                "status_dil": "",
                "photo_path": "",
                "latitude": str(latitude).strip() if latitude is not None else "",
                "longitude": str(longitude).strip() if longitude is not None else ""
            })

    # Save Identity Report
    print(f"\n[*] Writing Identity Report to: {identity_file}")
    df_id = pd.DataFrame(identity_rows)
    for col in ["ID Pelanggan", "Nomor Meter", "NIK", "Nama"]:
        if col not in df_id.columns:
            df_id[col] = ""
    df_id = df_id[["ID Pelanggan", "Nomor Meter", "NIK", "Nama"]]
    
    with pd.ExcelWriter(identity_file, engine="openpyxl") as writer:
        df_id.to_excel(writer, sheet_name="Identity Report", index=False)
        worksheet = writer.sheets["Identity Report"]
        for col in worksheet.columns:
            max_len = max(len(str(cell.value or '')) for cell in col)
            col_letter = col[0].column_letter
            worksheet.column_dimensions[col_letter].width = max(max_len + 3, 10)

    # Save Fasih Input Report
    print(f"[*] Writing Fasih API Input Template to: {fasih_file}")
    df_fasih = pd.DataFrame(fasih_rows)
    expected_cols = [
        "idpel", "nometer", "nama", "alamat", "tarif", "daya", 
        "hasil", "kelurahan", "kdpm", "kddk", "status_dil", 
        "photo_path", "latitude", "longitude"
    ]
    for col in expected_cols:
        if col not in df_fasih.columns:
            df_fasih[col] = ""
    df_fasih = df_fasih[expected_cols]
    
    with pd.ExcelWriter(fasih_file, engine="openpyxl") as writer:
        df_fasih.to_excel(writer, sheet_name="Fasih API Input", index=False)
        worksheet = writer.sheets["Fasih API Input"]
        for col in worksheet.columns:
            max_len = max(len(str(cell.value or '')) for cell in col)
            col_letter = col[0].column_letter
            worksheet.column_dimensions[col_letter].width = max(max_len + 3, 10)

    print(f"[+] Outputs successfully saved:")
    print(f"  -> Identity Report: {identity_file} ({len(df_id)} rows)")
    print(f"  -> Fasih API Input: {fasih_file} ({len(df_fasih)} rows)")



def main():
    parser = argparse.ArgumentParser(description="PT PLN (Persero) AP2T Customer Data Exporter to Excel")
    parser.add_argument("--idpel", nargs="*", help="List of Customer IDs (ID Pelanggan)")
    parser.add_argument("--nometer", nargs="*", help="List of Meter Numbers (Nomor Meter)")
    parser.add_argument("--file", help="Path to text or CSV file containing IDs (one ID per line, or standard csv)")
    parser.add_argument("--output", help="Output Excel filename (default: pln_export_<timestamp>.xlsx)")
    parser.add_argument("--ip", help=f"PLT AP2T Server IP address (default: {DEFAULT_AP2T_IP})")
    parser.add_argument("--cookies", help="Manual cookies override")
    parser.add_argument("--threads", type=int, default=10, help="Number of concurrent threads for parallel queries (default: 10)")
    args = parser.parse_args()

    # Load custom cookies if provided, otherwise defaults
    cookies = args.cookies or DEFAULT_COOKIES
    ap2t_ip = args.ip or DEFAULT_AP2T_IP

    # Collect search inputs
    search_queries = [] # list of tuple (type, val) where type is 'idpel' or 'nometer'
    
    if args.idpel:
        for val in args.idpel:
            search_queries.append(('idpel', val.strip()))
            
    if args.nometer:
        for val in args.nometer:
            search_queries.append(('nometer', val.strip()))

    if args.file:
        if not os.path.exists(args.file):
            print(f"[-] File not found: {args.file}")
            sys.exit(1)
        
        with open(args.file, "r") as f:
            for line in f:
                val = line.strip()
                if not val or val.startswith("#"):
                    continue
                # Determine query type: ID Pelanggan are usually 11-12 digits, Nomor Meter are usually 11 digits
                # If it's a comma-separated values file, extract first field
                if "," in val:
                    val = val.split(",")[0].strip()
                
                # Check numeric length to guess type
                clean_val = re.sub(r"\D", "", val)
                if len(clean_val) >= 9:
                    # Default to ID Pelanggan (can be overridden, or we test as idpel)
                    search_queries.append(('idpel', clean_val))
                else:
                    search_queries.append(('idpel', val))

    # If no arguments are provided, switch to interactive mode
    if not search_queries:
        print("=== PT PLN (Persero) AP2T InfoPelanggan Exporter ===")
        print("Mencari data berdasarkan ID Pelanggan atau Nomor Meter.")
        print("1. Cari dengan ID Pelanggan (IDPEL)")
        print("2. Cari dengan Nomor Meter (NOMETER)")
        choice = input("Pilih tipe pencarian [1-2]: ").strip()
        if choice not in ["1", "2"]:
            print("[-] Pilihan tidak valid.")
            sys.exit(1)
            
        q_type = 'idpel' if choice == "1" else 'nometer'
        print("Masukkan nomor ID / Meter (Bisa paste banyak baris sekaligus. Tekan Enter dua kali jika selesai):")
        lines = []
        while True:
            try:
                line = input().strip()
                if not line:
                    break
                lines.append(line)
            except EOFError:
                break
        
        if not lines:
            print("[-] ID tidak boleh kosong.")
            sys.exit(1)
            
        for val in lines:
            if "," in val:
                val = val.split(",")[0].strip()
            # Strip common list bracket/hash prefixes and index numbers
            val_clean = val.strip().lstrip("[({# ")
            val_clean = re.sub(r"^\d+[\.\-\)\/\s\]]+", "", val_clean).strip()
            clean_val = re.sub(r"\D", "", val_clean)
            if clean_val:
                search_queries.append((q_type, clean_val))

    # Initialize lookup engine
    engine = PLNLookupTool(cookies=cookies, ap2t_ip=ap2t_ip)
    
    total = len(search_queries)
    results = [None] * total
    
    # We will query using thread pool to run concurrently
    from concurrent.futures import ThreadPoolExecutor, as_completed
    
    def fetch_single(index, q_type, val):
        if q_type == 'idpel':
            res = engine.lookup_by_idpel(val)
        else:
            res = engine.lookup_by_nometer(val)
            if res:
                profiles = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
                if profiles and not profiles[0].get("nama"):
                    idpel = profiles[0].get("id_pelanggan")
                    if idpel:
                        second_res = engine.lookup_by_idpel(idpel)
                        if second_res:
                            second_profiles = second_res.get("dil_main", second_res.get("list", second_res.get("lInfoMasterNedisys", [])))
                            if second_profiles:
                                profiles[0].update(second_profiles[0])
        return index, q_type, val, res

    # Limit concurrency to user-defined value, minimum 1 worker
    max_workers = max(1, args.threads)
    # If only 1 query, don't run 10 threads
    max_workers = min(max_workers, total)

    if max_workers > 1:
        print(f"\n[*] Starting query process for {total} items using {max_workers} threads...")
        with ThreadPoolExecutor(max_workers=max_workers) as executor:
            futures = {
                executor.submit(fetch_single, idx, q_type, val): idx 
                for idx, (q_type, val) in enumerate(search_queries)
            }
            
            completed_count = 0
            for future in as_completed(futures):
                idx = futures[future]
                _, q_type, val, res = future.result()
                results[idx] = res
                completed_count += 1
                
                if res:
                    profile = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
                    if profile:
                        p = profile[0]
                        name = p.get("nama", "N/A")
                        tarif = p.get("tarif", "N/A")
                        daya = p.get("daya", "N/A")
                        print(f"[{completed_count}/{total}] Querying {q_type.upper()}: {val} -> SUCCESS! Nama: {name} | Tarif/Daya: {tarif}/{daya}")
                    else:
                        print(f"[{completed_count}/{total}] Querying {q_type.upper()}: {val} -> SUCCESS (No profile)")
                else:
                    print(f"[{completed_count}/{total}] Querying {q_type.upper()}: {val} -> FAILED")
    else:
        print(f"\n[*] Starting query process for {total} items sequentially...")
        for idx, (q_type, val) in enumerate(search_queries):
            print(f"[{idx+1}/{total}] Querying {q_type.upper()}: {val}")
            _, _, _, res = fetch_single(idx, q_type, val)
            results[idx] = res
            if res:
                profile = res.get("dil_main", res.get("list", res.get("lInfoMasterNedisys", [])))
                if profile:
                    p = profile[0]
                    name = p.get("nama", "N/A")
                    addr = p.get("alamat", "N/A")
                    tarif = p.get("tarif", "N/A")
                    daya = p.get("daya", "N/A")
                    print(f"  -> SUCCESS! Nama: {name} | Alamat: {addr} | Tarif/Daya: {tarif}/{daya}")
                else:
                    print("  -> SUCCESS (No profile content)")
            else:
                print("  -> FAILED")

    # Save focused Excel outputs
    output_file = args.output or f"pln_export_{datetime.now().strftime('%Y%m%d_%H%M%S')}.xlsx"
    save_focused_outputs(results, output_file)


if __name__ == "__main__":
    main()
