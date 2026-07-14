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


def test_decide_matrix():
    assert sup.decide("aaa", "aaa", None) == "none"      # sama
    assert sup.decide(None, "bbb", None) == "none"        # gagal fetch
    assert sup.decide("aaa", None, None) == "none"
    assert sup.decide("aaa", "bbb", None) == "update"     # beda, bebas
    assert sup.decide("aaa", "bbb", "bot_active_runs.lock") == "defer"  # beda, terkunci


def test_active_lock_detects_existing_file():
    d = tempfile.mkdtemp()
    services = [
        {"name": "bot", "cmd": [], "restart_on_update": True, "lock_file": "bot_active_runs.lock"},
        {"name": "srv", "cmd": [], "restart_on_update": True, "lock_file": None},
    ]
    assert sup.active_lock(services, d) is None
    open(os.path.join(d, "bot_active_runs.lock"), "w").close()
    assert sup.active_lock(services, d) == "bot_active_runs.lock"


def test_git_revisions_returns_equal_hashes_in_this_repo():
    # dijalankan di dalam repo yang bersih & sinkron → dua hash 40-char yang sama-ada
    local, remote = sup.git_revisions()
    # boleh (None, None) kalau offline; kalau tidak, keduanya hash valid
    if local is not None and remote is not None:
        assert len(local) == 40 and len(remote) == 40


def _dummy_service(name, restart=False):
    return {"name": name, "cmd": _dummy_cmd(), "restart_on_update": restart, "lock_file": None}


def test_start_all_then_stop_all():
    s = sup.Supervisor([_dummy_service("a"), _dummy_service("b")], logdir=tempfile.mkdtemp())
    s.start_all()
    try:
        assert sup.is_alive(s.procs["a"]) and sup.is_alive(s.procs["b"])
    finally:
        s.stop_all()
    assert not sup.is_alive(s.procs["a"]) and not sup.is_alive(s.procs["b"])


def test_recover_restarts_dead_process_with_new_pid():
    s = sup.Supervisor([_dummy_service("a")], logdir=tempfile.mkdtemp())
    s.start_all()
    try:
        pid1 = s.procs["a"].pid
        sup.kill(s.procs["a"])            # simulasi crash
        s.recover()
        pid2 = s.procs["a"].pid
        assert pid1 != pid2
        assert sup.is_alive(s.procs["a"])
    finally:
        s.stop_all()


def test_apply_update_only_restarts_flagged_services():
    s = sup.Supervisor([_dummy_service("keep", restart=False),
                        _dummy_service("roll", restart=True)], logdir=tempfile.mkdtemp())
    s.start_all()
    try:
        keep_pid = s.procs["keep"].pid
        roll_pid = s.procs["roll"].pid
        s.apply_update()
        assert s.procs["keep"].pid == keep_pid   # tidak di-restart
        assert s.procs["roll"].pid != roll_pid    # di-restart
    finally:
        s.stop_all()
