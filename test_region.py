import os, tempfile
import region as reg


def test_default_when_no_env_no_file(monkeypatch):
    monkeypatch.delenv("FASIH_REGION", raising=False)
    d = tempfile.mkdtemp()  # tidak ada .region di sini
    assert reg.get_region(d) == "bontang"


def test_env_wins_over_file(monkeypatch):
    d = tempfile.mkdtemp()
    with open(os.path.join(d, ".region"), "w") as f:
        f.write("balikpapan\n")
    monkeypatch.setenv("FASIH_REGION", "Samarinda")
    assert reg.get_region(d) == "samarinda"  # env menang + di-lowercase


def test_reads_and_normalizes_file(monkeypatch):
    monkeypatch.delenv("FASIH_REGION", raising=False)
    d = tempfile.mkdtemp()
    with open(os.path.join(d, ".region"), "w") as f:
        f.write("  Balikpapan\n")
    assert reg.get_region(d) == "balikpapan"


def test_ignores_comments_and_blank_lines(monkeypatch):
    monkeypatch.delenv("FASIH_REGION", raising=False)
    d = tempfile.mkdtemp()
    with open(os.path.join(d, ".region"), "w") as f:
        f.write("# ini komentar\n\nwahau\n")
    assert reg.get_region(d) == "wahau"
