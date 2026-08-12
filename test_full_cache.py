#!/usr/bin/env python3
"""Cek cache full open/reject: label full, gating require_full, patch status."""
import os, sys, time, json
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import petugas_client.batch_submit as bs


def _caches():
    return {"PRABAYAR": {
        "periode": {"id": "p1"},
        "template_mapping": {"data3": "r101a"},
        "assignments": [
            {"data3": "111111111111", "assignmentStatusId": "0"},   # OPEN
            {"data3": "222222222222", "assignmentStatusId": "3"},   # REJECT
            {"data3": "333333333333", "assignmentStatusId": "1"},   # sudah submit
        ],
        "regions": [],
        "template_version": "0.6.7",
    }}


def test_mark_flips_open_and_reject_to_submitted():
    sc = _caches()
    assert bs._mark_assignment_submitted(sc, "111111111111") is True   # 0 -> 1
    assert bs._mark_assignment_submitted(sc, "222222222222") is True   # 3 -> 1
    assert bs._mark_assignment_submitted(sc, "333333333333") is False  # sudah 1
    assert bs._mark_assignment_submitted(sc, "999999999999") is False  # tak ada
    ids = {a["data3"]: a["assignmentStatusId"] for a in sc["PRABAYAR"]["assignments"]}
    assert ids == {"111111111111": "1", "222222222222": "1", "333333333333": "1"}
    # setelah patch, tak ada lagi yang OPEN/REJECT → discovery kosong
    assert bs._open_idpels(sc) == []
    assert bs._reject_idpels(sc) == []


def test_require_full_gating_roundtrip():
    email = "unittest_fullcache@example.com"
    cfile = bs._survey_cache_file_for(email)
    try:
        # page-0 (full absen) → ditolak require_full, diterima mode biasa
        bs._save_survey_cache(email, _caches(), full=False)
        assert bs._load_survey_cache(email, require_full=True) is None
        assert bs._load_survey_cache(email) is not None
        # full=True → diterima require_full
        bs._save_survey_cache(email, _caches(), full=True)
        assert bs._load_survey_cache(email, require_full=True) is not None
        # cek label benar-benar tertulis
        assert json.load(open(cfile)).get("full") is True
    finally:
        if os.path.exists(cfile):
            os.remove(cfile)


def test_full_cache_respects_ttl():
    email = "unittest_ttl@example.com"
    cfile = bs._survey_cache_file_for(email)
    try:
        bs._save_survey_cache(email, _caches(), full=True)
        d = json.load(open(cfile))
        d["ts"] = time.time() - (bs._SURVEY_CACHE_TTL + 10)  # kadaluarsa
        json.dump(d, open(cfile, "w"))
        assert bs._load_survey_cache(email, require_full=True) is None
    finally:
        if os.path.exists(cfile):
            os.remove(cfile)


if __name__ == "__main__":
    test_mark_flips_open_and_reject_to_submitted()
    test_require_full_gating_roundtrip()
    test_full_cache_respects_ttl()
    print("✅ semua test cache full lulus")
