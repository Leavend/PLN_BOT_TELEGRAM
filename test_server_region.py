import os, sys, tempfile

# server.py melakukan sys.path.insert(repo_root) sendiri; kita hanya perlu bisa import 'server'.
sys.path.insert(0, os.path.join(os.path.dirname(os.path.abspath(__file__)), "pln_api_server"))
import server  # noqa: E402


def test_get_photo_dirs_scopes_to_region():
    dirs = server.get_photo_dirs("balikpapan")
    assert len(dirs) == 1
    assert dirs[0].endswith(os.path.join("house_photos", "balikpapan"))


def test_health_includes_region():
    c = server.app.test_client()
    r = c.get("/health")
    assert r.status_code == 200
    assert r.get_json().get("region") == server.REGION


def test_load_photos_only_reads_configured_dirs(monkeypatch):
    d = tempfile.mkdtemp()
    with open(os.path.join(d, "x.webp"), "wb") as f:
        f.write(b"\x00")
    monkeypatch.setattr(server, "PHOTO_DIRS", [d])
    server.load_photos()
    assert len(server._photo_list) == 1
    assert server._photo_list[0].endswith("x.webp")
