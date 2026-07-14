# test_setup_region.py
import os, tempfile
import setup_region as sr


def test_write_region_config_named_writes_all_and_marker():
    repo = tempfile.mkdtemp(); cfd = tempfile.mkdtemp()
    w = sr.write_region_config("balikpapan", "contoh.com", repo, cfd, named_ok=True, port=8900)
    assert open(os.path.join(repo, ".region")).read().strip() == "balikpapan"
    assert open(os.path.join(repo, "pln_url_balikpapan.txt")).read().strip() == "https://balikpapan.contoh.com"
    assert os.path.isdir(os.path.join(repo, "house_photos", "balikpapan"))
    cfg = open(os.path.join(cfd, "config.yml")).read()
    assert "balikpapan.contoh.com" in cfg
    assert "http://localhost:8900" in cfg
    assert os.path.exists(os.path.join(repo, ".tunnel_named"))
    assert "marker" in w


def test_write_region_config_not_named_skips_marker():
    repo = tempfile.mkdtemp(); cfd = tempfile.mkdtemp()
    w = sr.write_region_config("wahau", "contoh.com", repo, cfd, named_ok=False, port=8900)
    assert os.path.exists(os.path.join(repo, "pln_url_wahau.txt"))  # file lain tetap ditulis
    assert not os.path.exists(os.path.join(repo, ".tunnel_named"))  # penanda TIDAK dibuat
    assert "marker" not in w


def test_run_cloudflared_absent_returns_false(monkeypatch):
    monkeypatch.setattr("shutil.which", lambda name: None)
    assert sr.run_cloudflared("balikpapan", "balikpapan.contoh.com") is False
