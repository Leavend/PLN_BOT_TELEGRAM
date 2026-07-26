import os
import re
import json
import logging
import time
from typing import Optional, Dict

# Set up local import paths
import sys
sys.path.insert(0, os.path.dirname(os.path.dirname(os.path.abspath(__file__))))

from fasih_auth import perform_login, refresh_token_if_needed, get_headers

logger = logging.getLogger(__name__)

REPO_ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
USERS_FILE = os.path.join(REPO_ROOT, "users.json")
CACHE_FILE = os.path.join(REPO_ROOT, ".fasih_checkers_cache.json")

def load_regional_pools() -> dict:
    """
    Loads all active BPS accounts from users.json and categorizes them by region prefix:
    - Kaltim/Kaltara (IDPel starts with 23, NIK starts with 64/65): Tanjung Redeb, Malinau
    - Sulteng (IDPel starts with 31, NIK starts with 72): Palukota, Kolonodale, Donggala, Bungku, Parigi
    """
    pools = {
        "kaltimra": [],  # prefix 23 / 64 / 65
        "sulteng": []    # prefix 31 / 72
    }
    if not os.path.exists(USERS_FILE):
        return pools

    try:
        with open(USERS_FILE, "r") as f:
            users = json.load(f)
        for u in users:
            if u.get("is_disabled"):
                continue
            email = u.get("email")
            pwd = u.get("password")
            grp = (u.get("group") or "").strip().upper()
            if not email or not pwd:
                continue

            acc_info = {"email": email, "password": pwd}

            # Map ULP groups to matching regional pools
            if grp in ("TANJUNG REDEB", "MALINAU"):
                pools["kaltimra"].append(acc_info)
            elif grp in ("PALUKOTA", "KOLONODALE", "DONGGALA", "BUNGKU", "PARIGI"):
                pools["sulteng"].append(acc_info)
    except Exception as e:
        logger.warning(f"Error loading users.json for checker pools: {e}")
    
    return pools

class CheckerPool:
    def __init__(self):
        self.pools = load_regional_pools()
        self.cache = self._load_cache()

    def _load_cache(self) -> dict:
        if os.path.exists(CACHE_FILE):
            try:
                with open(CACHE_FILE, "r") as f:
                    return json.load(f)
            except Exception:
                pass
        return {}

    def _save_cache(self):
        try:
            with open(CACHE_FILE, "w") as f:
                json.dump(self.cache, f, indent=2)
        except Exception:
            pass

    def get_checker_headers(self, fallback_headers: dict, query_key: str) -> dict:
        """
        Gets a valid auth header for the given IDPel/NIK's region:
        - If query starts with '31' or '72' (Sulteng), uses the Sulteng pool.
        - Otherwise, uses the Kaltimra pool.
        """
        query_key = str(query_key).strip()
        pool_name = "sulteng" if (query_key.startswith("31") or query_key.startswith("72")) else "kaltimra"
        pool = self.pools.get(pool_name, [])

        if not pool:
            return fallback_headers

        num_accounts = len(pool)
        current_idx_key = f"_idx_{pool_name}"
        current_idx = self.cache.get(current_idx_key, 0)

        # Attempt to find a valid checker account, rotating up to 1 full loop
        for _ in range(num_accounts):
            if current_idx >= len(pool):
                current_idx = 0
            acc = pool[current_idx]
            email = acc["email"]
            pwd = acc["password"]

            # Check if this checker is cooling down
            cooldown_until = self.cache.get(f"{email}_cooldown_until", 0)
            if time.time() < cooldown_until:
                current_idx = (current_idx + 1) % num_accounts
                continue

            token_data = self.cache.get(email)
            valid = False

            if token_data:
                # Try in-memory refresh
                try:
                    refreshed = refresh_token_if_needed(token_data, token_file=None, exit_on_failure=False)
                    if refreshed and "access_token" in refreshed:
                        self.cache[email] = refreshed
                        token_data = refreshed
                        valid = True
                except Exception:
                    pass

            if not valid:
                logger.info(f"🔑 [CheckerPool] Logging in to checker account: {email}")
                try:
                    td = perform_login(email, pwd, exit_on_failure=False)
                    if td and "access_token" in td:
                        self.cache[email] = td
                        token_data = td
                        valid = True
                    else:
                        logger.warning(f"❌ [CheckerPool] Login failed for checker {email}")
                except Exception as e:
                    logger.warning(f"❌ [CheckerPool] Login error for checker {email}: {e}")

            if valid and token_data:
                self.cache[current_idx_key] = current_idx
                self._save_cache()
                return get_headers(token_data)

            # Rotate to the next account
            current_idx = (current_idx + 1) % num_accounts

        self.cache[current_idx_key] = current_idx
        self._save_cache()
        return fallback_headers

    def mark_checker_429(self, query_key: str):
        """
        Cooldown the current checker account in the target region pool for 1 hour.
        """
        query_key = str(query_key).strip()
        pool_name = "sulteng" if (query_key.startswith("31") or query_key.startswith("72")) else "kaltimra"
        pool = self.pools.get(pool_name, [])
        if not pool:
            return

        current_idx_key = f"_idx_{pool_name}"
        current_idx = self.cache.get(current_idx_key, 0)
        if current_idx >= len(pool):
            current_idx = 0
        email = pool[current_idx]["email"]

        # Cooldown for 1 hour
        self.cache[f"{email}_cooldown_until"] = time.time() + 3600
        logger.warning(f"⚠️ [CheckerPool] Checker account {email} hit 429 rate limit. Cooling down for 1 hour.")

        # Rotate immediately
        self.cache[current_idx_key] = (current_idx + 1) % len(pool)
        self._save_cache()

# Singleton instance
_pool_instance = None

def get_checker_headers(fallback_headers: dict, query_key: str) -> dict:
    global _pool_instance
    if _pool_instance is None:
        _pool_instance = CheckerPool()
    return _pool_instance.get_checker_headers(fallback_headers, query_key)

def mark_checker_429(query_key: str):
    global _pool_instance
    if _pool_instance is not None:
        _pool_instance.mark_checker_429(query_key)
