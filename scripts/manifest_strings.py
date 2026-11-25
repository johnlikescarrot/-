#!/usr/bin/env python3
"""Extract UTF-16LE strings from binary AndroidManifest.xml. Stdlib only."""
import zipfile, re, sys
apk = sys.argv[1] if len(sys.argv)>1 else "/tmp/zalopay_analysis/extracted/vn.com.vng.zalopay.apk"
with zipfile.ZipFile(apk) as z:
    data=z.read("AndroidManifest.xml")
    txt=data.decode('utf-16le', errors='ignore')
    strs=re.findall(r'[\x20-\x7e]{4,}', txt)
    print(f"Manifest strings: {len(strs)}")
    for s in strs:
        print(s)
