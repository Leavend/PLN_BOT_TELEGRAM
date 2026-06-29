#!/usr/bin/env python3
"""
Fasih BPS Survey Data Decryption Utility
Author: Antigravity

This script decrypts localized Android survey data from the Fasih BPS application (id.go.bpsfasih)
using either the legacy PBKDF2-derived AES-CBC key scheme or the modern AES-GCM cipher scheme.
"""

import os
import sys
import base64
import hashlib
import argparse
import tempfile
import shutil
import subprocess
import zipfile
from typing import Optional
from Crypto.Cipher import AES
from Crypto.Util.Padding import unpad

# Static encryption key for legacy CBC mode
STATIC_LEGACY_KEY = "Z!,vDKUPv;.Jy0Q4Eq1wVCY-a_!GnT"

def pkcs12_kdf(password: bytes, salt: bytes, id_byte: int, iterations: int, key_len: int) -> bytes:
    """
    Implements PKCS#12 v1.1 Key Derivation Function (KDF) using SHA-256.
    Replicates BouncyCastle's implementation.
    """
    u = 32  # SHA-256 digest size in bytes
    v = 64  # SHA-256 block size in bytes
    
    # D: diversification block
    D = bytes([id_byte]) * v
    
    # S: salt block repeated to fill v-byte block size multiple
    if salt:
        S_len = v * ((len(salt) + v - 1) // v)
        S = (salt * ((S_len // len(salt)) + 1))[:S_len]
    else:
        S = b""
        
    # P: password block repeated to fill v-byte block size multiple
    if password:
        P_len = v * ((len(password) + v - 1) // v)
        P = (password * ((P_len // len(password)) + 1))[:P_len]
    else:
        P = b""
        
    I = bytearray(S + P)
    c = (key_len + u - 1) // u
    out = bytearray()
    
    for i in range(1, c + 1):
        # Step 1: Hash D + I
        h = hashlib.sha256()
        h.update(D)
        h.update(I)
        A = h.digest()
        
        # Step 2: Iterate hash A
        for _ in range(iterations - 1):
            h = hashlib.sha256()
            h.update(A)
            A = h.digest()
            
        # Step 3: Construct B
        B = (A * ((v + u - 1) // u))[:v]
        
        # Step 4: Adjust I by adding B + 1 to each v-byte block (big-endian block addition)
        new_I = bytearray()
        for offset in range(0, len(I), v):
            block = I[offset:offset+v]
            val_I = int.from_bytes(block, 'big')
            val_B = int.from_bytes(B, 'big')
            val_sum = (val_I + val_B + 1) & ((1 << (8 * v)) - 1)
            new_I.extend(val_sum.to_bytes(v, 'big'))
        I = new_I
        
        # Step 5: Append to output
        if i == c:
            remaining = key_len - len(out)
            out.extend(A[:remaining])
        else:
            out.extend(A)
            
    return bytes(out)

def decrypt_legacy(encrypted_payload: str, secret_key: str = STATIC_LEGACY_KEY, verbose: bool = False) -> str:
    """
    Decrypts legacy encrypted payload from Kripto.java.
    Format: Base64(CipherText)#Base64(SHA256(CipherText))#Base64(Salt)#Base64(IV)
    """
    parts = encrypted_payload.strip().split("#")
    if len(parts) < 4:
        raise ValueError("Invalid encrypted payload format: Expected 4 segments separated by '#' (Legacy Kripto)")
        
    ciphertext = base64.b64decode(parts[0].strip())
    expected_digest = base64.b64decode(parts[1].strip())
    salt_bytes = base64.b64decode(parts[2].strip())
    iv = base64.b64decode(parts[3].strip())
    
    # 1. Verify SHA-256 digest of ciphertext
    actual_digest = hashlib.sha256(ciphertext).digest()
    if actual_digest != expected_digest:
        raise ValueError("Ciphertext integrity validation failed (Data is corrupted)")
        
    if verbose:
        print("[+] Integrity check passed successfully.")
        
    # 2. Replicate Java UTF-8 salt representation roundtrip quirk
    salt_str_bytes = salt_bytes.decode('utf-8', errors='replace').encode('utf-8')
    
    # 3. Replicate PKCS12 PasswordToBytes conversion (UTF-16BE + 2 null-byte terminator)
    password_bytes = secret_key.encode('utf-16be') + b'\x00\x00'
    
    # 4. Derive AES-256 key (32 bytes)
    if verbose:
        print("[*] Deriving AES key using PKCS12 KDF with 11000 iterations...")
    key = pkcs12_kdf(password_bytes, salt_str_bytes, 1, 11000, 32)
    
    # 5. Decrypt using AES-256-CBC
    cipher = AES.new(key, AES.MODE_CBC, iv)
    decrypted_bytes = unpad(cipher.decrypt(ciphertext), AES.block_size)
    
    return decrypted_bytes.decode('utf-8')

def decrypt_gcm(encrypted_payload: str, wrapped_key_base64: str, verbose: bool = False) -> str:
    """
    Decrypts modern AES-GCM encrypted payload from CryptoGCM.java.
    Format: Base64(IV [12 bytes] + Ciphertext + Tag [16 bytes])
    """
    key_bytes = base64.b64decode(wrapped_key_base64.strip())
    if len(key_bytes) not in (16, 24, 32):
        raise ValueError(f"Invalid AES key length: {len(key_bytes)} bytes (Must be 16, 24, or 32)")
        
    decoded = base64.b64decode(encrypted_payload.strip())
    if len(decoded) < 28: # 12 bytes IV + at least 16 bytes tag
        raise ValueError("Payload too short for GCM (Must contain 12-byte IV and 16-byte GCM Tag)")
        
    iv = decoded[:12]
    ciphertext_with_tag = decoded[12:]
    
    tag_len = 16
    ciphertext = ciphertext_with_tag[:-tag_len]
    tag = ciphertext_with_tag[-tag_len:]
    
    if verbose:
        print(f"[*] Decrypted key size: {len(key_bytes)} bytes")
        print(f"[*] GCM IV (hex): {iv.hex()}")
        print(f"[*] GCM Tag (hex): {tag.hex()}")
        print(f"[*] Ciphertext length: {len(ciphertext)} bytes")
        
    cipher = AES.new(key_bytes, AES.MODE_GCM, nonce=iv)
    decrypted_bytes = cipher.decrypt_and_verify(ciphertext, tag)
    
    return decrypted_bytes.decode('utf-8')

def handle_file_decryption(input_path: str, output_path: Optional[str], wrapped_key: Optional[str], force_legacy: bool, verbose: bool):
    """
    Handles decryption of a file (either direct encrypted text file or a .7z archive).
    """
    # 1. Check if the input file is a .7z archive
    is_archive = False
    with open(input_path, 'rb') as f:
        header = f.read(6)
        if header == b'7z\xbc\xaf\x27\x1c':
            is_archive = True
            
    if is_archive:
        if verbose:
            print(f"[+] Input '{input_path}' detected as 7z archive.")
        handle_archive_decryption(input_path, output_path, wrapped_key, force_legacy, verbose)
        return
        
    # 2. Direct file decryption
    with open(input_path, 'r', encoding='utf-8', errors='ignore') as f:
        encrypted_text = f.read().strip()
        
    if not encrypted_text:
        print("[-] Error: Input file is empty.")
        sys.exit(1)
        
    decrypted_text = perform_auto_decryption(encrypted_text, wrapped_key, force_legacy, verbose)
    
    if output_path:
        with open(output_path, 'w', encoding='utf-8') as f:
            f.write(decrypted_text)
        print(f"[+] Decrypted output saved to: {output_path}")
    else:
        print("\n--- Decrypted Content ---")
        print(decrypted_text)
        print("-------------------------")

def perform_auto_decryption(encrypted_text: str, wrapped_key: Optional[str], force_legacy: bool, verbose: bool) -> str:
    """
    Decides between legacy and GCM mode and executes decryption.
    """
    # If forced legacy or contains '#' (characteristic of Legacy delimiter)
    if force_legacy or ("#" in encrypted_text):
        if verbose:
            print("[*] Using Legacy CBC decryption scheme...")
        return decrypt_legacy(encrypted_text, STATIC_LEGACY_KEY, verbose)
    else:
        if not wrapped_key:
            # Fallback check
            print("[-] Warning: No wrappedDataKey provided, trying to fall back to Legacy CBC...")
            try:
                return decrypt_legacy(encrypted_text, STATIC_LEGACY_KEY, verbose)
            except Exception as legacy_err:
                print(f"[-] Legacy fallback failed: {legacy_err}")
                print("[-] Error: GCM decryption requires a base64 key (-k/--key option).")
                sys.exit(1)
        if verbose:
            print("[*] Using Modern AES-GCM decryption scheme...")
        return decrypt_gcm(encrypted_text, wrapped_key, verbose)

def handle_archive_decryption(archive_path: str, output_path: Optional[str], wrapped_key: Optional[str], force_legacy: bool, verbose: bool):
    """
    Extracts 7z, decrypts data.json, extracts reference.zip to reference.json,
    and places outputs into output directory.
    """
    # Target output directory
    if output_path:
        out_dir = output_path
    else:
        # Default output directory: same folder as archive, with _extracted suffix
        base, _ = os.path.splitext(archive_path)
        out_dir = base + "_extracted"
        
    os.makedirs(out_dir, exist_ok=True)
    
    with tempfile.TemporaryDirectory() as temp_dir:
        if verbose:
            print(f"[*] Extracting {archive_path} to temporary directory {temp_dir}...")
            
        # Run system 7z to extract
        try:
            res = subprocess.run(
                ["7z", "x", "-y", f"-o{temp_dir}", archive_path],
                stdout=subprocess.PIPE,
                stderr=subprocess.PIPE,
                check=True
            )
            if verbose:
                print(res.stdout.decode('utf-8', errors='ignore'))
        except Exception as e:
            print(f"[-] Extraction error. Make sure '7z' is installed and in PATH. Error: {e}")
            sys.exit(1)
            
        # List of files in the extracted archive
        extracted_files = os.listdir(temp_dir)
        if verbose:
            print(f"[+] Extracted files: {extracted_files}")
            
        # 1. Process data / data.json
        data_file = None
        for name in ['data.json', 'data']:
            if name in extracted_files:
                data_file = name
                break
                
        if data_file:
            data_path = os.path.join(temp_dir, data_file)
            with open(data_path, 'r', encoding='utf-8', errors='ignore') as f:
                raw_content = f.read().strip()
                
            print(f"[*] Decrypting {data_file}...")
            try:
                decrypted_data = perform_auto_decryption(raw_content, wrapped_key, force_legacy, verbose)
                
                # Save decrypted data.json to final destination
                dest_data_path = os.path.join(out_dir, "data.json")
                with open(dest_data_path, 'w', encoding='utf-8') as f:
                    f.write(decrypted_data)
                print(f"[+] Saved decrypted data to: {dest_data_path}")
            except Exception as e:
                print(f"[-] Failed to decrypt data: {e}")
        else:
            print("[-] Warning: No 'data' or 'data.json' file found in archive.")
            
        # 2. Process reference / reference.zip
        ref_file = None
        for name in ['reference.zip', 'reference']:
            if name in extracted_files:
                ref_file = name
                break
                
        if ref_file:
            ref_path = os.path.join(temp_dir, ref_file)
            # If it's the raw zip, extract it
            if zipfile.is_zipfile(ref_path):
                print(f"[*] Extracting {ref_file}...")
                with zipfile.ZipFile(ref_path, 'r') as z:
                    # Find reference.json inside reference.zip
                    json_name = None
                    for zname in z.namelist():
                        if 'reference.json' in zname or 'reference' in zname:
                            json_name = zname
                            break
                    if json_name:
                        z.extract(json_name, out_dir)
                        # If extracted structure has subfolders, flatten it
                        extracted_json = os.path.join(out_dir, json_name)
                        dest_ref_json = os.path.join(out_dir, "reference.json")
                        if extracted_json != dest_ref_json:
                            shutil.move(extracted_json, dest_ref_json)
                        print(f"[+] Saved extracted reference to: {dest_ref_json}")
                    else:
                        print("[-] Warning: No reference file inside zip archive.")
            else:
                # If not a zip, copy directly
                dest_ref_json = os.path.join(out_dir, "reference.json")
                shutil.copy(ref_path, dest_ref_json)
                print(f"[+] Copied reference file directly to: {dest_ref_json}")
                
        # 3. Copy checksum.md5 or any other files
        for name in ['checksum.md5']:
            if name in extracted_files:
                shutil.copy(os.path.join(temp_dir, name), os.path.join(out_dir, name))
                if verbose:
                    print(f"[+] Copied {name} to output directory.")
                    
    print(f"[+] Extraction and decryption finished. Output directory: {out_dir}")

def main():
    parser = argparse.ArgumentParser(
        description="Decrypt localized Android survey data from Fasih BPS app using Legacy CBC or Modern GCM encryption schemes."
    )
    parser.add_argument(
        "-i", "--input",
        required=True,
        help="Path to encrypted file (data, data.json, or .7z archive) or raw base64 string."
    )
    parser.add_argument(
        "-o", "--output",
        help="Path to write decrypted output file or directory (for archives)."
    )
    parser.add_argument(
        "-k", "--key",
        help="Base64 encoded wrappedDataKey (required for modern AES-GCM mode)."
    )
    parser.add_argument(
        "--legacy",
        action="store_true",
        help="Force legacy PBKDF2-derived AES-CBC decryption."
    )
    parser.add_argument(
        "-v", "--verbose",
        action="store_true",
        help="Print verbose steps and keys."
    )
    
    args = parser.parse_args()
    
    input_val = args.input
    
    # Check if input is a path or a raw base64 string
    if os.path.exists(input_val):
        handle_file_decryption(input_val, args.output, args.key, args.legacy, args.verbose)
    else:
        # Try raw base64 string decryption
        try:
            decrypted = perform_auto_decryption(input_val, args.key, args.legacy, args.verbose)
            if args.output:
                with open(args.output, 'w', encoding='utf-8') as f:
                    f.write(decrypted)
                print(f"[+] Decrypted raw string saved to: {args.output}")
            else:
                print("\n--- Decrypted Content ---")
                print(decrypted)
                print("-------------------------")
        except Exception as e:
            print(f"[-] Error decrypting raw string: {e}")
            sys.exit(1)

if __name__ == '__main__':
    main()
