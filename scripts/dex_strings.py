#!/usr/bin/env python3
"""Extract printable strings from DEX files. Stdlib only."""
import re, os, sys, glob
dexdir = sys.argv[1] if len(sys.argv)>1 else "/tmp/zalopay_analysis/dex"
for p in sorted(glob.glob(os.path.join(dexdir, "*.dex"))):
    with open(p,'rb') as f:
        data=f.read()
    strs=[s.decode() for s in re.findall(rb'[\x20-\x7e]{4,}', data)]
    urls=sorted(set(s for s in strs if s.startswith('http')))
    print(f"{os.path.basename(p)}: {len(strs)} strings, {len(urls)} urls, {os.path.getsize(p)} bytes")
    for u in urls[:10]:
        print(f"  {u[:120]}")
