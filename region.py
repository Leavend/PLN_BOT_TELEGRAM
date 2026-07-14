"""Sumber tunggal identitas wilayah untuk satu mesin.

Urutan: env FASIH_REGION > file .region (repo root, gitignored) > default 'bontang'.
Dipakai bersama oleh pln_api_server/server.py dan petugas_client/batch_submit.py.
"""
import os

_REPO = os.path.dirname(os.path.abspath(__file__))
DEFAULT_REGION = "bontang"


def get_region(repo_root=_REPO):
    env = os.getenv("FASIH_REGION")
    if env and env.strip():
        return env.strip().lower()
    try:
        with open(os.path.join(repo_root, ".region")) as f:
            for line in f:
                s = line.strip()
                if s and not s.startswith("#"):
                    return s.lower()
    except OSError:
        pass
    return DEFAULT_REGION
