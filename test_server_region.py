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


def _auth_headers():
    """Kirim key kalau server memang mengunci (tergantung .env mesin yang menjalankan test)."""
    return {"X-API-Key": next(iter(server.API_KEYS))} if server.API_KEYS else {}


def test_config_endpoint_returns_region_and_mapbox():
    c = server.app.test_client()
    r = c.get("/api/config", headers=_auth_headers())
    assert r.status_code == 200
    j = r.get_json()
    assert j["region"] == server.REGION
    assert "mapbox_token" in j          # kosong pun tak apa — klien fallback ke .env lokal


def test_config_endpoint_is_auth_gated_when_keys_configured():
    if not server.API_KEYS:
        return                          # server tanpa key = terbuka by design; tak ada yang diuji
    c = server.app.test_client()
    assert c.get("/api/config").status_code == 401           # tanpa key -> ditolak
    assert c.get("/api/config", headers={"X-API-Key": "salah"}).status_code == 401
