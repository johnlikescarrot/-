import json, os, zipfile, hashlib, logging, re, subprocess, sys
from collections import Counter, defaultdict
logging.disable(logging.CRITICAL)

BASE = '/home/user/-'
WORK = '/home/user/-/work'
OUT = '/home/user/-/analysis'
os.makedirs(OUT, exist_ok=True)
os.makedirs(OUT+'/inventories', exist_ok=True)
os.makedirs(OUT+'/manifests', exist_ok=True)
os.makedirs(OUT+'/security', exist_ok=True)

def sha256(path):
    h = hashlib.sha256()
    with open(path,'rb') as f:
        for c in iter(lambda:f.read(65536), b''):
            h.update(c)
    return h.hexdigest()

def file_type(name):
    ext = os.path.splitext(name)[1].lower()
    return ext or '(none)'

# 1. Inventory outer zip
outer = os.path.join(BASE, 'Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip')
inventory = []
with zipfile.ZipFile(outer) as z:
    for i in z.infolist():
        inventory.append({
            'archive': 'outer_zip',
            'path': i.filename,
            'size': i.file_size,
            'compressed_size': i.compress_size,
            'crc32': hex(i.CRC),
        })
with open(OUT+'/inventories/outer_zip.json','w') as f:
    json.dump(inventory,f,indent=2,ensure_ascii=False)

# 2. XAPK contents
xapk = os.path.join(WORK,'extracted/xapk_contents')
xapk_inv = []
for root, dirs, files in os.walk(xapk):
    for fn in files:
        p = os.path.join(root,fn)
        rel = os.path.relpath(p, xapk)
        xapk_inv.append({
            'archive': 'xapk',
            'path': rel,
            'size': os.path.getsize(p),
            'sha256': sha256(p),
            'type': file_type(fn),
        })
with open(OUT+'/inventories/xapk_contents.json','w') as f:
    json.dump(xapk_inv,f,indent=2,ensure_ascii=False)

# 3. Per-APK inventories
apk_names = ['vn.com.vng.zalopay.apk','config.armeabi_v7a.apk','config.en.apk','config.vi.apk','config.xhdpi.apk']
all_apk_inv = {}
for name in apk_names:
    p = os.path.join(xapk,name)
    inv = []
    with zipfile.ZipFile(p) as z:
        for i in z.infolist():
            inv.append({
                'apk': name,
                'path': i.filename,
                'size': i.file_size,
                'compressed_size': i.compress_size,
                'crc32': hex(i.CRC),
                'type': file_type(i.filename),
            })
    all_apk_inv[name] = inv
    with open(OUT+f'/inventories/{name}.json','w') as f:
        json.dump(inv,f,indent=2,ensure_ascii=False)

# 4. Aggregate stats
type_counts = Counter()
total_files = 0
total_size = 0
for name, inv in all_apk_inv.items():
    for e in inv:
        type_counts[e['type']] += 1
        total_files += 1
        total_size += e['size']

summary = {
    'outer_zip': os.path.basename(outer),
    'outer_zip_size': os.path.getsize(outer),
    'xapk_file_count': len(xapk_inv),
    'apk_count': len(apk_names),
    'total_embedded_files': total_files,
    'total_embedded_uncompressed_size': total_size,
    'file_type_counts': dict(type_counts.most_common()),
    'per_apk': {n:{'file_count':len(inv),'uncompressed_size':sum(e['size'] for e in inv)} for n,inv in all_apk_inv.items()}
}
with open(OUT+'/inventories/summary.json','w') as f:
    json.dump(summary,f,indent=2,ensure_ascii=False)

print('inventories done', total_files)
