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
    assert bs._resolve_pln_url(repo_root=d, region="balikpapan") == "https://legacy.example"


def test_falls_back_to_env_when_no_files(monkeypatch):
    d = tempfile.mkdtemp()
    monkeypatch.setenv("PLN_API_URL", "https://env.example")
    assert bs._resolve_pln_url(repo_root=d, region="samarinda") == "https://env.example"
