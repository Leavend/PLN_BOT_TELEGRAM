import json, os, tempfile
import supervisor as sup


def test_load_services_default_when_no_file():
    d = tempfile.mkdtemp()
    assert sup.load_services(d) == sup.DEFAULT_SERVICES


def test_load_services_reads_local_override():
    d = tempfile.mkdtemp()
    custom = [{"name": "only_server", "cmd": ["python", "x.py"],
               "restart_on_update": True, "lock_file": None}]
    with open(os.path.join(d, "services.local.json"), "w") as f:
        json.dump(custom, f)
    assert sup.load_services(d) == custom


def test_load_services_falls_back_on_invalid_json():
    d = tempfile.mkdtemp()
    with open(os.path.join(d, "services.local.json"), "w") as f:
        f.write("{ not json")
    assert sup.load_services(d) == sup.DEFAULT_SERVICES


def test_default_services_shape():
    names = {s["name"] for s in sup.DEFAULT_SERVICES}
    assert names == {"pln_server", "telegram_bot", "tunnel"}
    bot = next(s for s in sup.DEFAULT_SERVICES if s["name"] == "telegram_bot")
    assert bot["lock_file"] == "bot_active_runs.lock"
    tun = next(s for s in sup.DEFAULT_SERVICES if s["name"] == "tunnel")
    assert tun["restart_on_update"] is False
