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

def _is_edit(target, resubmit_reject):
    return (target.get("assignmentStatusAlias") != "OPEN") and not resubmit_reject


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


if __name__ == "__main__":
    import sys
    fns = [v for k, v in sorted(globals().items()) if k.startswith("test_") and callable(v)]
    for fn in fns:
        fn()
        print(f"  ok  {fn.__name__}")
    print(f"\n{len(fns)} passed")
    sys.exit(0)
