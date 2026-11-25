#!/usr/bin/env python3
"""Reproducible inventory of ZaloPay XAPK chain. Stdlib only."""
import zipfile, os, hashlib, json, csv, sys
ZIP_PATH = sys.argv[1] if len(sys.argv)>1 else "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"
def sha256_file(p):
    h=hashlib.sha256()
    with open(p,'rb') as f:
        for chunk in iter(lambda: f.read(1<<20), b''):
            h.update(chunk)
    return h.hexdigest()
print(f"ZIP: {ZIP_PATH} size={os.path.getsize(ZIP_PATH)} sha256={sha256_file(ZIP_PATH)}")
with zipfile.ZipFile(ZIP_PATH) as z:
    print("ZIP contents:", z.namelist())
    for info in z.infolist():
        print(f"  {info.file_size:>12} {info.filename}")
