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


class _FakeResp:
    status_code = 200
    def __init__(self, tok): self._tok = tok
    def json(self): return {"region": "samarinda", "mapbox_token": self._tok}


def test_apply_region_config_server_token_wins_over_local_env(monkeypatch):
    # HP petugas yang sudah ter-setup dengan token wilayah LAIN (Bontang)
    monkeypatch.setenv("MAPBOX_ACCESS_TOKEN", "pk.eyJ1IjoibGVhdmVuZCJ9.x")
    monkeypatch.setattr(bs, "PLN_API_URL", "http://fake")
    monkeypatch.setattr(bs, "PLN_API_KEY", "k")
    monkeypatch.setattr(bs.req_lib, "get", lambda *a, **k: _FakeResp("pk.eyJ1IjoidmVuZHNhbWFyaW5kYSJ9.y"))
    acct = bs.apply_region_config()
    # server MENANG -> HP lama otomatis pindah ke token wilayahnya cuma dgn fasih-update
    assert acct == "vendsamarinda"
    assert bs._mapbox_account(os.environ["MAPBOX_ACCESS_TOKEN"]) == "vendsamarinda"


def test_apply_region_config_falls_back_to_local_when_server_has_none(monkeypatch):
    monkeypatch.setenv("MAPBOX_ACCESS_TOKEN", "pk.eyJ1IjoibGVhdmVuZCJ9.x")
    monkeypatch.setattr(bs, "PLN_API_URL", "http://fake")
    monkeypatch.setattr(bs, "PLN_API_KEY", "k")
    monkeypatch.setattr(bs.req_lib, "get", lambda *a, **k: _FakeResp(""))   # server tanpa token
    assert bs.apply_region_config() == "leavend"


def test_apply_region_config_survives_server_down(monkeypatch):
    monkeypatch.setenv("MAPBOX_ACCESS_TOKEN", "pk.eyJ1IjoibGVhdmVuZCJ9.x")
    monkeypatch.setattr(bs, "PLN_API_URL", "http://fake")
    monkeypatch.setattr(bs, "PLN_API_KEY", "k")
    def boom(*a, **k): raise OSError("server mati")
    monkeypatch.setattr(bs.req_lib, "get", boom)
    assert bs.apply_region_config() == "leavend"       # tidak raise, fallback lokal
