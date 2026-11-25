#!/usr/bin/env python3
import hashlib, json, os, sys, zipfile, datetime, collections, re, subprocess
from pathlib import Path

WORK = Path("/home/user/-/work")
XAPK_DIR = WORK / "xapk"
OUT = Path("/home/user/-/analysis")
OUT.mkdir(parents=True, exist_ok=True)

def sha256(p):
    h = hashlib.sha256()
    with open(p,'rb') as f:
        for c in iter(lambda:f.read(1024*1024), b''):
            h.update(c)
    return h.hexdigest()

def md5(p):
    h = hashlib.md5()
    with open(p,'rb') as f:
        for c in iter(lambda:f.read(1024*1024), b''):
            h.update(c)
    return h.hexdigest()

def file_type(name):
    n=name.lower()
    if n.endswith('.dex'): return 'dex'
    if n.endswith('.so'): return 'native'
    if n.endswith('.xml'): return 'xml'
    if n.endswith('.json'): return 'json'
    if n.endswith('.png') or n.endswith('.webp') or n.endswith('.jpg') or n.endswith('.gif'): return 'image'
    if n.endswith('.arsc'): return 'arsc'
    if n.endswith('.sf') or n.endswith('.rsa') or n.endswith('.mf'): return 'signature'
    if n.endswith('.properties'): return 'properties'
    if n.endswith('.ttf') or n.endswith('.otf'): return 'font'
    if n.endswith('.kotlin_module'): return 'kotlin'
    if n.endswith('.version'): return 'version'
    if n.endswith('.proto'): return 'proto'
    if n.endswith('.bnf') or n.endswith('.bin'): return 'binary'
    if n.endswith('/'): return 'dir'
    return 'other'

inventory = []
summary = {"total_files":0,"total_size":0,"by_type":collections.Counter(),"by_extension":collections.Counter()}
apk_files = []

for root, dirs, files in os.walk(XAPK_DIR):
    for fn in sorted(files):
        p = Path(root)/fn
        rel = str(p.relative_to(WORK))
        size = p.stat().st_size
        ext = os.path.splitext(fn)[1].lower() or '(none)'
        ft = file_type(fn)
        rec = {
            "path": rel,
            "size": size,
            "sha256": sha256(p),
            "md5": md5(p),
            "type": ft,
            "ext": ext,
        }
        inventory.append(rec)
        summary["total_files"] += 1
        summary["total_size"] += size
        summary["by_type"][ft] += 1
        summary["by_extension"][ext] += 1
        if fn.endswith('.apk'):
            apk_files.append(str(p))

# Deep inventory inside each apk
inner_inventory = []
for apk in apk_files:
    apk_rel = str(Path(apk).relative_to(WORK))
    try:
        with zipfile.ZipFile(apk) as z:
            for zi in z.infolist():
                inner_inventory.append({
                    "container": apk_rel,
                    "path": zi.filename,
                    "size": zi.file_size,
                    "compressed_size": zi.compress_size,
                    "type": file_type(zi.filename),
                    "ext": os.path.splitext(zi.filename)[1].lower() or '(none)',
                })
    except Exception as e:
        inner_inventory.append({"container": apk_rel, "error": str(e)})

summary["by_type"] = dict(summary["by_type"].most_common())
summary["by_extension"] = dict(summary["by_extension"].most_common())
summary["apk_files"] = [str(Path(a).relative_to(WORK)) for a in apk_files]
summary["inner_file_count"] = len(inner_inventory)

(OUT/"file_inventory.json").write_text(json.dumps(inventory, indent=2, ensure_ascii=False))
(OUT/"inner_file_inventory.json").write_text(json.dumps(inner_inventory, indent=2, ensure_ascii=False))
(OUT/"summary.json").write_text(json.dumps(summary, indent=2, ensure_ascii=False))
print(json.dumps({"outer_files":len(inventory),"inner_files":len(inner_inventory),"apks":summary["apk_files"]}))
