"""
mitmproxy addon: simpan body .7z yang di-PUT app FASIH ke object storage.
Dipakai SEKALI untuk mengambil 1 payload asli app (buat menyamakan wrap_answers).

Cara pakai (di Mac):
  1) pip install mitmproxy
  2) mitmdump -s tools/capture_7z.py --listen-port 8888
  3) HP: set proxy Wi-Fi ke <IP-Mac>:8888, pasang cert mitmproxy (http://mitm.it)
     (sama seperti waktu setup HTTP Toolkit — matikan HTTP Toolkit dulu biar port bebas)
  4) Di app FASIH: buka 1 data REJECT lalu SUBMIT (perbaiki) seperti biasa.
  5) File tersimpan otomatis: captured_app_<assignmentId>_<ts>.7z di folder repo.
  6) Balik ke chat, bilang nama file-nya.

Addon hanya menyimpan PUT ke *.obj.bps.go.id berakhiran .7z — tidak menyentuh lalu-lintas lain.
"""
import os


def response(flow):
    req = flow.request
    if req.method != "PUT":
        return
    if "obj.bps.go.id" not in req.pretty_host:
        return
    if not req.path.split("?")[0].endswith(".7z"):
        return
    body = req.raw_content or b""
    if not body:
        return
    # nama file: ambil basename dari path object
    base = req.path.split("?")[0].rsplit("/", 1)[-1]
    out = os.path.join(os.getcwd(), f"captured_app_{base}")
    with open(out, "wb") as f:
        f.write(body)
    print(f"\n[capture_7z] SAVED {len(body)} bytes -> {out}\n")
