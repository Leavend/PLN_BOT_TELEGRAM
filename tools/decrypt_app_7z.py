#!/usr/bin/env python3
"""Decrypt a FASIH assignment .7z -> print the envelope. Usage: decrypt_app_7z.py <file.7z> [assignmentId]"""
import sys, json, base64, tempfile, os
sys.path.insert(0,"/Users/leavend/Fasih-Python-Script")
from fasih_crypto import decrypt_gcm_verify
from petugas_client import batch_submit as bs
import fasih_api
SEVENZ=sys.argv[1]
REGID="4147fae0-75d9-4be1-a872-b1498e9fecce"; PID="d63e9832-13c6-4ec7-bf5b-59229c2f90f9"
tok=bs.ensure_login(); hdr=bs.get_headers(tok)
regions=fasih_api.fetch_regions(hdr,PID)
wk=next((r.get("wrappedDatakey") for r in regions if r.get("region_id")==REGID or (r.get("region") or {}).get("id")==REGID),None)
key=base64.b64decode(wk)
import py7zr
wd=tempfile.mkdtemp()
with py7zr.SevenZipFile(SEVENZ,'r') as z: z.extractall(wd)
dj=None
for root,_,fs in os.walk(wd):
    for f in fs:
        if f=="data.json": dj=open(os.path.join(root,f)).read()
env=json.loads(decrypt_gcm_verify(dj,key))
print("=== ENVELOPE keys:", list(env.keys()))
for k in ("dataKey","templateDataKey","templateVersion","validationVersion","createdAt","createdBy","updatedBy","isForceSubmit","description"):
    if k in env: print(f"  {k}: {env[k]!r}")
ans=env.get("answers",[])
print(f"  answers: {len(ans)} items, dataKeys:", [a.get('dataKey') for a in ans])
if ans: print("  answer[0] keys:", list(ans[0].keys()), "| sample:", json.dumps(ans[0],ensure_ascii=False)[:120])
open("/Users/leavend/Fasih-Python-Script/app_envelope.json" if len(sys.argv)>2 and sys.argv[2]=="save" else os.path.join(wd,"env.json"),"w").write(json.dumps(env,indent=1,ensure_ascii=False))
print("full envelope saved.")
