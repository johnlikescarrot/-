#!/usr/bin/env python3
"""Reverse-engineering inventory for an APK.
Usage: .re-env/bin/python analyze.py <path-to-apk> [out-dir]
Uses zipfile (reliable) + androguard (best-effort, no Java required).
"""
import os, sys, json, zipfile, hashlib, re
from collections import Counter, defaultdict

APK = sys.argv[1] if len(sys.argv) > 1 else '/home/user/-/Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip'
OUT = sys.argv[2] if len(sys.argv) > 2 else '/home/user/-/reverse-engineering'
os.makedirs(OUT, exist_ok=True)

def sha256_of_file(path):
    h = hashlib.sha256()
    with open(path, 'rb') as f:
        for chunk in iter(lambda: f.read(1 << 16), b''):
            h.update(chunk)
    return h.hexdigest()

APK_SHA256 = sha256_of_file(APK)
APK_SIZE = os.path.getsize(APK)

cats = defaultdict(list)
entries = []
with zipfile.ZipFile(APK) as z:
    for info in z.infolist():
        e = {
            'name': info.filename,
            'size': info.file_size,
            'compressed_size': info.compress_size,
            'is_dir': info.filename.endswith('/'),
        }
        entries.append(e)
        n = e['name']
        if n.startswith('META-INF/'): cat = 'META-INF'
        elif n == 'AndroidManifest.xml': cat = 'MANIFEST'
        elif n == 'resources.arsc': cat = 'RESOURCES'
        elif n == 'classes.dex' or n.endswith('.dex'): cat = 'DEX'
        elif n.startswith('lib/'): cat = 'NATIVE'
        elif n.startswith('assets/'): cat = 'ASSETS'
        elif n.startswith('res/'): cat = 'RES'
        else: cat = 'OTHER'
        cats[cat].append(e)
entries.sort(key=lambda e: e['name'])

inventory = {
    'apk': os.path.basename(APK),
    'size': APK_SIZE,
    'sha256': APK_SHA256,
    'total_entries': len(entries),
    'by_category': {k: len(v) for k, v in sorted(cats.items())},
    'entries': entries,
}
with open(os.path.join(OUT, 'apk_inventory.json'), 'w', encoding='utf-8') as f:
    json.dump(inventory, f, ensure_ascii=False, indent=2)

ag = {'ok': False, 'error': None, 'data': {}}
try:
    from androguard.misc import AnalyzeAPK
    a, d, dx = AnalyzeAPK(APK)

    def g(obj, *names, default=None):
        for nm in names:
            if obj is None:
                return default
            fn = getattr(obj, nm, None)
            if callable(fn):
                try:
                    return fn()
                except Exception:
                    continue
            else:
                return fn
        return default

    manifest_data = {
        'package': g(a, 'get_package'),
        'min_sdk': g(a, 'get_min_sdk_version'),
        'target_sdk': g(a, 'get_target_sdk_version'),
        'max_sdk': g(a, 'get_max_sdk_version'),
        'version_code': g(a, 'get_androidversion_code', 'get_version_code'),
        'version_name': g(a, 'get_androidversion_name', 'get_version_name'),
        'permissions': sorted(g(a, 'get_permissions', default=[]) or []),
        'activities': sorted(g(a, 'get_activities', default=[]) or []),
        'services': sorted(g(a, 'get_services', default=[]) or []),
        'receivers': sorted(g(a, 'get_receivers', default=[]) or []),
        'providers': sorted(g(a, 'get_providers', default=[]) or []),
    }
    ag['data']['manifest'] = manifest_data

    classes = g(dx, 'get_classes', default=[]) or []
    methods = g(dx, 'get_methods', default=[]) or []
    ag['data']['dex'] = {
        'classes': len(classes),
        'methods': len(methods),
        'dex_files': [x.name for x in (g(d, 'get_dex_files', default=[]) or [])],
    }
    pkg_counter = Counter()
    for c in classes:
        try:
            cn = c.get_class_name() if hasattr(c, 'get_class_name') else str(c)
        except Exception:
            cn = str(c)
        pkg = cn.split('/')[0] if '/' in cn else cn
        pkg_counter[pkg] += 1
    ag['data']['packages'] = sorted(pkg_counter.items(), key=lambda x: -x[1])[:60]
    ag['ok'] = True
except Exception as ex:
    ag['ok'] = False
    ag['error'] = repr(ex)

with open(os.path.join(OUT, 'androguard_result.json'), 'w', encoding='utf-8') as f:
    json.dump(ag, f, ensure_ascii=False, indent=2)

url_re = re.compile(rb'https?://[A-Za-z0-9._~:%/?#\[\]@!$&\'()*+,;=-]+')
endpoint_re = re.compile(rb'(?:api|ssl|gw|pay|payment|callback|notify|check|config|zalopay|zalogw)[A-Za-z0-9._/-]{2,}', re.I)

def strings_of(data):
    return re.findall(rb'[\x20-\x7e]{6,}', data)

endpoints = set()
suspicious = set()
with zipfile.ZipFile(APK) as z:
    for info in z.infolist():
        n = info.filename
        if not (n == 'AndroidManifest.xml' or n == 'resources.arsc' or n.endswith('.dex') or n.endswith('.xml') or n.endswith('.txt') or n.endswith('.json')):
            continue
        data = z.read(info.filename)
        for m in url_re.findall(data):
            try:
                endpoints.add(m.decode('utf-8', 'ignore'))
            except Exception:
                pass
        for run in strings_of(data):
            try:
                t = run.decode('utf-8', 'ignore')
            except Exception:
                continue
            if endpoint_re.search(run):
                suspicious.add(t)

with open(os.path.join(OUT, 'endpoints.json'), 'w', encoding='utf-8') as f:
    json.dump({'endpoints': sorted(endpoints), 'suspicious_strings': sorted(suspicious)[:300]}, f, ensure_ascii=False, indent=2)

print('=== APK RE INVENTORY ===')
print('APK:', os.path.basename(APK), 'size:', APK_SIZE, 'sha256:', APK_SHA256[:16], '...')
print('total entries:', len(entries))
for k in sorted(cats):
    print(f'  {k}: {len(cats[k])}')
print('androguard ok:', ag['ok'], ag.get('error', ''))
if ag['ok']:
    md = ag['data']['manifest']
    print('package:', md['package'])
    print('min/target/max sdk:', md['min_sdk'], md['target_sdk'], md['max_sdk'])
    print('version:', md['version_code'], md['version_name'])
    print('permissions:', len(md['permissions']))
    print('activities:', len(md['activities']), 'services:', len(md['services']),
          'receivers:', len(md['receivers']), 'providers:', len(md['providers']))
    print('dex classes:', ag['data']['dex']['classes'], 'methods:', ag['data']['dex']['methods'])
    print('dex files:', ag['data']['dex']['dex_files'])
    print('top packages:', ag['data']['packages'][:10])
print('endpoints found:', len(endpoints))
print('suspicious strings:', len(suspicious))
print('reports written to', OUT)
