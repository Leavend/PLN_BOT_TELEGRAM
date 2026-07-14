import os, tempfile
from petugas_client import batch_submit as bs


def test_prefers_region_file():
    d = tempfile.mkdtemp()
    with open(os.path.join(d, "pln_url_balikpapan.txt"), "w") as f:
        f.write("https://bpp.example\n")
    with open(os.path.join(d, "pln_url.txt"), "w") as f:
        f.write("https://legacy.example\n")
    assert bs._resolve_pln_url(repo_root=d, region="balikpapan") == "https://bpp.example"


def test_falls_back_to_plain_url_file(monkeypatch):
    d = tempfile.mkdtemp()
    with open(os.path.join(d, "pln_url.txt"), "w") as f:
        f.write("https://legacy.example/\n")  # trailing slash harus dibuang
    monkeypatch.delenv("PLN_API_URL", raising=False)
    # legacy pln_url.txt is only a fallback for the default region (bontang) —
    # non-default regions must NOT silently inherit it (see tests below).
    assert bs._resolve_pln_url(repo_root=d, region="bontang") == "https://legacy.example"


def test_falls_back_to_env_when_no_files(monkeypatch):
    d = tempfile.mkdtemp()
    monkeypatch.setenv("PLN_API_URL", "https://env.example")
    assert bs._resolve_pln_url(repo_root=d, region="samarinda") == "https://env.example"


def test_non_default_region_does_not_fall_back_to_legacy_bontang_url(monkeypatch):
    d = tempfile.mkdtemp()
    with open(os.path.join(d, "pln_url.txt"), "w") as f:
        f.write("https://bontang.example\n")  # legacy = Bontang
    monkeypatch.setenv("PLN_API_URL", "https://env.example")
    # balikpapan without its own file must NOT get the Bontang legacy URL
    assert bs._resolve_pln_url(repo_root=d, region="balikpapan") == "https://env.example"


def test_default_region_still_uses_legacy_fallback(monkeypatch):
    d = tempfile.mkdtemp()
    with open(os.path.join(d, "pln_url.txt"), "w") as f:
        f.write("https://bontang.example\n")
    monkeypatch.delenv("PLN_API_URL", raising=False)
    # bontang (default) still honors the legacy pln_url.txt (backward-compat)
    assert bs._resolve_pln_url(repo_root=d, region="bontang") == "https://bontang.example"
