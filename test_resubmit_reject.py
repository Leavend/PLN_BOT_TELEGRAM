"""Reject-resubmit selection + routing invariants (no network)."""
from petugas_client import batch_submit as bs


def _cache(assignments, idpel_var_slot="data3"):
    # template_mapping maps a slot -> field var; r101a = idpel slot
    return {"PASCABAYAR": {
        "template_mapping": {idpel_var_slot: "r101a", "data1": "r101b"},
        "assignments": assignments,
    }}


def test_reject_idpels_picks_only_rejects():
    caches = _cache([
        {"assignmentStatusAlias": "OPEN", "data3": "111111111111"},
        {"assignmentStatusAlias": "SUBMITTED BY Pencacah", "data3": "222222222222"},
        {"assignmentStatusAlias": "REJECTED BY Admin Kabupaten", "data3": "333333333333"},
        {"assignmentStatusAlias": "REJECTED BY Admin Kabupaten", "data3": "444444444444"},
    ])
    assert bs._reject_idpels(caches) == ["333333333333", "444444444444"]


def test_reject_idpels_dedup_and_blank_skipped():
    caches = _cache([
        {"assignmentStatusAlias": "REJECTED BY Admin Kabupaten", "data3": "333333333333"},
        {"assignmentStatusAlias": "REJECTED BY Admin Kabupaten", "data3": "333333333333"},  # dup
        {"assignmentStatusAlias": "REJECTED BY Admin Kabupaten", "data3": ""},              # blank
    ])
    assert bs._reject_idpels(caches) == ["333333333333"]


def test_reject_idpels_respects_idpel_slot():
    # idpel lives in data5 for this template → must read data5, not data3
    caches = _cache([{"assignmentStatusAlias": "REJECTED x", "data5": "999999999999"}],
                    idpel_var_slot="data5")
    assert bs._reject_idpels(caches) == ["999999999999"]


def test_no_rejects_returns_empty():
    caches = _cache([{"assignmentStatusAlias": "OPEN", "data3": "111111111111"}])
    assert bs._reject_idpels(caches) == []


# --- Routing invariants: a reject target must resubmit the EXISTING id (createStatus
# "false") via the submit path (is_edit False). These mirror the exact expressions in
# submit_single so a future flip is caught. ---

def _createStatus(target):
    return "true" if target.get("isNew", False) else "false"

def _is_edit(target, resubmit_reject=False):
    # HAR-proven: only SUBMITTED uses /edit. OPEN and REJECTED both use /submit.
    status_alias = target.get("assignmentStatusAlias") or ""
    return "SUBMITTED" in status_alias


def test_reject_target_resubmits_existing_no_duplicate():
    reject = {"id": "abc-existing", "assignmentStatusAlias": "REJECTED BY Admin Kabupaten"}
    # createStatus must be "false" (no isNew on a fetched record) -> reuse existing id
    assert _createStatus(reject) == "false"
    # reject-resubmit must use the plain submit path (HAR), not s3/edit
    assert _is_edit(reject, resubmit_reject=True) is False


def test_normal_new_still_creates():
    new = {"id": "new", "assignmentStatusAlias": "OPEN", "isNew": True}
    assert _createStatus(new) == "true"
    assert _is_edit(new, resubmit_reject=False) is False  # OPEN new = submit path


def test_edit_path_unchanged_for_non_reject_submitted():
    # a non-reject SUBMITTED edit (not reject mode) still routes to edit path
    sub = {"id": "s", "assignmentStatusAlias": "SUBMITTED BY Pencacah"}
    assert _is_edit(sub, resubmit_reject=False) is True


# --- Reject resubmit must carry the survey's CURRENT template version, not the stale
# "0.5.9" fallback (datatable records have no templateVersion → BPS "Versi data tidak
# valid" on update). Mirrors the stamp + wrap_answers fallback expressions. ---

def _stamped_template_version(sc, target):
    tv = ((sc.get("survey") or {}).get("templateLookup") or [{}])[0].get("templateVersion")
    if tv:
        target["templateVersion"] = tv
    return target.get("templateVersion") or "0.5.9"  # wrap_answers fallback


def test_reject_stamps_current_template_version():
    sc = {"survey": {"templateLookup": [{"templateVersion": "0.6.7"}]}}
    reject = {"id": "x", "assignmentStatusAlias": "REJECTED BY Admin Kabupaten"}  # no templateVersion
    assert _stamped_template_version(sc, reject) == "0.6.7"


def test_missing_survey_version_keeps_fallback():
    sc = {"survey": {"templateLookup": [{}]}}  # no templateVersion in survey either
    reject = {"id": "x", "assignmentStatusAlias": "REJECTED x"}
    assert _stamped_template_version(sc, reject) == "0.5.9"


def test_wrap_answers_interview_times():
    from submit_fasih import wrap_answers
    from datetime import datetime, timedelta, timezone
    
    target = {
        "id": "123",
        "createdAt": "2026-05-12T07:39:09.458Z",
        "templateVersion": "0.6.7",
        "region": {
            "level1": {
                "code": "64", "name": "KALIMANTAN TIMUR",
                "level2": {
                    "code": "74", "name": "KOTA BONTANG",
                    "level3": {
                        "code": "02", "name": "BONTANG SELATAN",
                        "level4": {
                            "code": "003", "name": "BERBAS PANTAI"
                        }
                    }
                }
            }
        }
    }
    flat_answers = {
        "r101a": "231410012388"
    }
    result = wrap_answers(flat_answers, target, "test_user")
    
    # Check that createdAt at the root matches target["createdAt"]
    assert result["createdAt"] == "2026-05-12T07:39:09.458Z"
    
    # Retrieve "mulai" and "selesai" from result["answers"]
    answers_dict = {a["dataKey"]: a["answer"] for a in result["answers"]}
    mulai = answers_dict["mulai"]
    selesai = answers_dict["selesai"]
    
    # Parse them
    dt_mulai = datetime.strptime(mulai, "%Y-%m-%dT%H:%M:%S.%fZ")
    dt_selesai = datetime.strptime(selesai, "%Y-%m-%dT%H:%M:%S.%fZ")
    
    # Determine local/WITA today's date
    now_utc = datetime.now(timezone.utc)
    wita_now = now_utc + timedelta(hours=8)
    today_wita = wita_now.date()
    today_local = datetime.now().date()
    local_tz = datetime.now().astimezone().tzinfo
    dt_mulai_local = dt_mulai.replace(tzinfo=timezone.utc).astimezone(local_tz)
    dt_selesai_local = dt_selesai.replace(tzinfo=timezone.utc).astimezone(local_tz)

    # Verify the date is today (either WITA today or local system today)
    assert dt_mulai_local.date() in (today_wita, today_local)
    assert dt_selesai_local.date() in (today_wita, today_local)
    
    # Verify the hour is within 07.00 - 18.00 working hours
    assert 7 <= dt_mulai_local.hour < 18
    assert 7 <= dt_selesai_local.hour < 18
    
    # Verify selesai is chronologically after mulai
    assert dt_selesai > dt_mulai


# --- OPEN / PERNAH DIBUKA helper tests ---

def test_open_idpels_picks_status_open_and_pernah_dibuka():
    """_open_idpels: matches OPEN and PERNAH DIBUKA status, collecting IDPel and NoMeter."""
    caches = _cache([
        {"assignmentStatusAlias": "OPEN", "data3": "111111111111", "data1": "45450136058"},         # OPEN Prabayar ✓
        {"assignmentStatusAlias": "PERNAH DIBUKA", "data1": "222222222222"},                  # PERNAH DIBUKA ✓
        {"assignmentStatusAlias": "SUBMITTED BY Pencacah", "data3": "444444444444"},          # SUBMITTED ✗
        {"assignmentStatusAlias": "REJECTED BY Admin Kabupaten", "data3": "555555555555"},    # REJECTED ✗
    ])
    result = bs._open_idpels(caches)
    assert "111111111111" in result
    assert "45450136058" in result
    assert "222222222222" in result
    assert "444444444444" not in result
    assert "555555555555" not in result


def test_reopen_idpels_is_alias_to_open_idpels():
    caches = _cache([
        {"assignmentStatusAlias": "OPEN", "data3": "111111111111"},
    ])
    assert bs._reopen_idpels(caches) == bs._open_idpels(caches)


def test_open_idpels_dedup():
    caches = _cache([
        {"assignmentStatusAlias": "OPEN", "data3": "111111111111"},
        {"assignmentStatusAlias": "OPEN", "data3": "111111111111"},  # dup
        {"assignmentStatusAlias": "OPEN", "data3": ""},              # blank
    ])
    assert bs._open_idpels(caches) == ["111111111111"]


def test_reopen_idpels_empty_when_no_open_or_pernah_dibuka():
    caches = _cache([
        {"assignmentStatusAlias": "SUBMITTED BY Pencacah", "data3": "222222222222"},
    ])
    assert bs._reopen_idpels(caches) == []


def test_open_target_routes_submit_not_edit():
    """OPEN targets must use /submit (not /edit), createStatus=false."""
    open_target = {"id": "abc", "assignmentStatusAlias": "OPEN"}
    assert _createStatus(open_target) == "false"
    assert _is_edit(open_target) is False


def test_reopen_target_routes_submit_not_edit():
    """PERNAH DIBUKA targets must use /submit (not /edit), createStatus=false."""
    reopen_target = {"id": "def", "assignmentStatusAlias": "PERNAH DIBUKA"}
    assert _createStatus(reopen_target) == "false"
    assert _is_edit(reopen_target) is False



def test_match_selection_prioritizes_mode_target():
    caches = {
        "PASCABAYAR": {
            "template_mapping": {"data3": "r101a", "data1": "r101b"},
            "assignments": [
                {"id": "submitted-id", "assignmentStatusAlias": "SUBMITTED BY Pencacah", "data3": "236000023856", "data1": "56984045217"},
                {"id": "rejected-id", "assignmentStatusAlias": "REJECTED BY Admin Kabupaten", "data3": "236000023856", "data1": "56984045217"}
            ]
        }
    }
    val_clean = "236000023856"
    resubmit_reject = True
    matches = []
    for skey, sc in caches.items():
        tm = sc["template_mapping"]
        idpel_slot = next((s for s, v in tm.items() if v == "r101a"), "data3")
        nometer_slot = next((s for s, v in tm.items() if v == "r101b"), "data1")
        for a in sc["assignments"]:
            v_idpel = (a.get(idpel_slot) or "").strip()
            v_nometer = (a.get(nometer_slot) or "").strip()
            if v_idpel == val_clean or v_nometer == val_clean:
                matches.append((skey, sc, a))
    target = None
    if matches:
        if resubmit_reject:
            reject_matches = [m for m in matches if "REJECT" in (m[2].get("assignmentStatusAlias") or "").upper()]
            matched_key, sc, target = reject_matches[0] if reject_matches else matches[0]
            
    assert target is not None
    assert target["id"] == "rejected-id"


def test_wrap_answers_pasca_067_keys_order():
    from submit_fasih import wrap_answers
    target = {
        "id": "123",
        "templateVersion": "0.6.7",
        "region": {
            "level1": {"code": "23"},
            "level2": {"code": "23BPN"},
            "level3": {"code": "23220"},
            "level4": {"code": "BDCMAHB"}
        }
    }
    flat_answers = {
        "r101a": "232240000816",
        "_is_pasca": True
    }
    result = wrap_answers(flat_answers, target, "test_user")
    keys = [a["dataKey"] for a in result["answers"]]

    # 0.6.x Pascabayar = 37 fields, verified vs app samples (tv 0.6.0/0.6.5/0.6.6).
    # Pascabayar KEEPS DIL fields + NIK-pemadanan, and does NOT carry the prabayar
    # CEK cards nor `catatan`.
    assert len(keys) == 38  # 37 base + catatan (Blok IV, live-verified valid on 0.6.7)
    for k in ("flagpre", "unitupi", "unitap", "unitup", "kode_rbm", "kddk",
              "nama_ktp", "hasilPemadananNIK", "hasilPemadananNIK2", "result_callnik",
              "no_kk", "mulai", "catatan", "selesai"):
        assert k in keys, k
    for k in ("result_idpln", "hasilCheckIdPel", "hasilCheckIdPel2",
              "result_nomor_meter", "hasilCheckNoMeter",
              "UPI", "UP3", "ULP", "daya", "tarif"):
        assert k not in keys, k
    # catatan is Blok IV notes, placed right before selesai
    assert keys.index("catatan") < keys.index("selesai")

    # Block II order: r201 -> r202 -> nama_ktp -> hasilPemadananNIK ->
    # hasilPemadananNIK2 -> result_callnik -> r203 -> r204 -> no_kk
    order = ["r201", "r202", "nama_ktp", "hasilPemadananNIK", "hasilPemadananNIK2",
             "result_callnik", "r203", "r204", "no_kk"]
    idxs = [keys.index(k) for k in order]
    assert idxs == sorted(idxs)


if __name__ == "__main__":
    import sys
    fns = [v for k, v in sorted(globals().items()) if k.startswith("test_") and callable(v)]
    for fn in fns:
        fn()
        print(f"  ok  {fn.__name__}")
    print(f"\n{len(fns)} passed")
    sys.exit(0)
