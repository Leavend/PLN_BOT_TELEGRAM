import json, os, tempfile, sys as _sys
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


def _dummy_cmd(seconds=30):
    return [_sys.executable, "-c", f"import time; time.sleep({seconds})"]


def test_spawn_is_alive_then_kill():
    d = tempfile.mkdtemp()
    p = sup.spawn(_dummy_cmd(), os.path.join(d, "dummy.log"))
    try:
        assert sup.is_alive(p) is True
    finally:
        sup.kill(p)
    assert sup.is_alive(p) is False


def test_kill_none_is_noop():
    sup.kill(None)  # tidak boleh raise


def test_spawn_writes_logfile():
    d = tempfile.mkdtemp()
    lp = os.path.join(d, "hello.log")
    p = sup.spawn([_sys.executable, "-c", "print('halo dari service')"], lp)
    p.wait(timeout=10)
    with open(lp) as f:
        assert "halo dari service" in f.read()
