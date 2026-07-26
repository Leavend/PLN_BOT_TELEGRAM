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
ACCTS_FILE = os.path.join(REPO_ROOT, ".fasih_accounts.txt")
CACHE_FILE = os.path.join(REPO_ROOT, ".fasih_checkers_cache.json")

def load_checker_emails() -> list[str]:
    emails = []
    if not os.path.exists(ACCTS_FILE):
        return emails
    try:
        with open(ACCTS_FILE, "r") as f:
            for line in f:
                stripped = line.strip()
                if stripped.startswith("#"):
                    clean = stripped.lstrip("#").strip()
                    m = re.search(r"([A-Za-z0-9._%+-]+@gmail\.com)", clean)
                    if m:
                        email = m.group(1).strip()
                        # Exclude akbar and huana
                        if "akbar" not in email.lower() and "huana" not in email.lower():
                            if email not in emails:
                                emails.append(email)
    except Exception as e:
        logger.warning(f"Error parsing .fasih_accounts.txt for checkers: {e}")
    return emails

class CheckerPool:
    def __init__(self):
        self.emails = load_checker_emails()
        self.cache = self._load_cache()
        self.current_index = self.cache.get("_current_index", 0)

    def _load_cache(self) -> dict:
        if os.path.exists(CACHE_FILE):
            try:
                with open(CACHE_FILE, "r") as f:
                    return json.load(f)
            except Exception:
                pass
        return {}

    def _save_cache(self):
        self.cache["_current_index"] = self.current_index
        try:
            with open(CACHE_FILE, "w") as f:
                json.dump(self.cache, f, indent=2)
        except Exception:
            pass

    def get_checker_headers(self, fallback_headers: dict) -> dict:
        """
        Gets a valid auth header for check-idpln from the checker pool.
        If a checker account fails login or gets blacklisted/429, rotates to the next one.
        Returns fallback_headers if the pool is empty or all checker logins fail.
        """
        if not self.emails:
            return fallback_headers

        num_emails = len(self.emails)
        # Attempt to find a valid checker account, rotating up to 1 full loop
        for _ in range(num_emails):
            if self.current_index >= len(self.emails):
                self.current_index = 0
            email = self.emails[self.current_index]
            
            # Check if this checker has hit a 429 cooldown today
            cooldown_until = self.cache.get(f"{email}_cooldown_until", 0)
            if time.time() < cooldown_until:
                # Rotate to next
                self.current_index = (self.current_index + 1) % num_emails
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
                # Login fresh
                logger.info(f"🔑 [CheckerPool] Logging in to checker account: {email}")
                try:
                    # Default password is Pln@1234
                    td = perform_login(email, "Pln@1234", exit_on_failure=False)
                    if td and "access_token" in td:
                        self.cache[email] = td
                        token_data = td
                        valid = True
                    else:
                        logger.warning(f"❌ [CheckerPool] Login failed for checker {email}")
                except Exception as e:
                    logger.warning(f"❌ [CheckerPool] Login error for checker {email}: {e}")

            if valid and token_data:
                self._save_cache()
                return get_headers(token_data)

            # If login failed, rotate to the next one
            self.current_index = (self.current_index + 1) % num_emails

        self._save_cache()
        return fallback_headers

    def mark_checker_429(self, checker_headers: dict):
        """
        If check-idpln returned a 429 rate limit, set cooldown_until for the current checker account
        and force rotation to the next one.
        """
        if not self.emails:
            return
        
        if self.current_index >= len(self.emails):
            self.current_index = 0
        email = self.emails[self.current_index]
        # Cooldown for 1 hour
        self.cache[f"{email}_cooldown_until"] = time.time() + 3600
        logger.warning(f"⚠️ [CheckerPool] Checker account {email} hit 429 rate limit. Cooling down for 1 hour.")
        
        # Rotate immediately
        self.current_index = (self.current_index + 1) % len(self.emails)
        self._save_cache()

# Singleton instance
_pool_instance = None

def get_checker_headers(fallback_headers: dict) -> dict:
    global _pool_instance
    if _pool_instance is None:
        _pool_instance = CheckerPool()
    return _pool_instance.get_checker_headers(fallback_headers)

def mark_checker_429(checker_headers: dict):
    global _pool_instance
    if _pool_instance is not None:
        _pool_instance.mark_checker_429(checker_headers)
