#!/usr/bin/env python3
"""Extract the outer ZIP -> XAPK -> split APKs into work/extracted/ (reproducible)."""
import glob, hashlib, io, json, os, zipfile
ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
OUT = os.path.join(ROOT, "work", "extracted")
os.makedirs(OUT, exist_ok=True)
zips = glob.glob(os.path.join(ROOT, "*.zip"))
assert zips, "outer zip not found"
outer = zips[0]
print(f"outer: {outer}")
with zipfile.ZipFile(outer) as z:
    print(z.namelist())
    z.extractall(OUT)
xapks = glob.glob(os.path.join(OUT, "*.xapk"))
assert xapks, "xapk not found"
xapk = xapks[0]
print(f"xapk: {xapk}")
with zipfile.ZipFile(xapk) as z:
    for n in z.namelist():
        print(f"  {n}")
    z.extractall(os.path.join(OUT, "xapk"))
print("done ->", OUT)
