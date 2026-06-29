import base64
import hashlib
from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes

def encrypt_gcm(plaintext: str, key_bytes: bytes) -> str:
    """Encrypt plaintext using AES-GCM."""
    iv = get_random_bytes(12)
    cipher = AES.new(key_bytes, AES.MODE_GCM, nonce=iv)
    ciphertext, tag = cipher.encrypt_and_digest(plaintext.encode("utf-8"))
    return base64.b64encode(iv + ciphertext + tag).decode("ascii")

def decrypt_gcm_verify(encrypted_b64: str, key_bytes: bytes) -> str:
    """Verify encryption by decrypting."""
    decoded = base64.b64decode(encrypted_b64)
    iv, ciphertext_and_tag = decoded[:12], decoded[12:]
    ciphertext, tag = ciphertext_and_tag[:-16], ciphertext_and_tag[-16:]
    cipher = AES.new(key_bytes, AES.MODE_GCM, nonce=iv)
    return cipher.decrypt_and_verify(ciphertext, tag).decode("utf-8")

def compute_md5(file_path: str) -> str:
    """Compute hex MD5 hash of a file."""
    h = hashlib.md5()
    with open(file_path, "rb") as f:
        for chunk in iter(lambda: f.read(8192), b""):
            h.update(chunk)
    return h.hexdigest()

def compute_md5_base64(file_path: str) -> str:
    """Compute Base64 MD5 hash of a file."""
    h = hashlib.md5()
    with open(file_path, "rb") as f:
        for chunk in iter(lambda: f.read(4096), b""):
            h.update(chunk)
    return base64.b64encode(h.digest()).decode("ascii").strip()
