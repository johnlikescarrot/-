"""Full static-analysis pipeline for the Zalopay XAPK. Emits analysis/summary.json."""
import os, re, json, struct, zipfile, sys
from collections import Counter
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
from dex import parse_dex
from axml import StrPool

XAPK = '/home/user/-/work/xapk'
OUT = '/home/user/-/analysis/summary.json'

def zip_list(apk):
    with zipfile.ZipFile(apk) as z:
        return [(i.filename, i.file_size) for i in z.infolist()]

def main():
    summary = {}
    apks = {
        'base': f'{XAPK}/vn.com.vng.zalopay.apk',
        'armeabi_v7a': f'{XAPK}/config.armeabi_v7a.apk',
        'en': f'{XAPK}/config.en.apk',
        'vi': f'{XAPK}/config.vi.apk',
        'xhdpi': f'{XAPK}/config.xhdpi.apk',
    }
    # --- per-APK inventory
    summary['apks'] = {}
    for name, path in apks.items():
        files = zip_list(path)
        summary['apks'][name] = {
            'file_count': len(files),
            'total_uncompressed': sum(s for _, s in files),
            'extensions': Counter(os.path.splitext(f)[1].lower() for f, _ in files).most_common(15),
        }
    # --- native libs
    libs = []
    for name, path in apks.items():
        for f, s in zip_list(path):
            if f.endswith('.so'):
                libs.append({'apk': name, 'path': f, 'size': s})
    summary['native_libs'] = sorted(libs, key=lambda x: -x['size'])
    # --- DEX analysis
    dex_dir = '/tmp/apk/dex'
    dex_stats, all_classes, url_counter, domain_counter = [], [], Counter(), Counter()
    pkg_counter = Counter()
    for fn in sorted(os.listdir(dex_dir)):
        r = parse_dex(os.path.join(dex_dir, fn))
        dex_stats.append({k: r[k] for k in ('file_size','string_count','type_count','class_count','method_count','field_count')})
        dex_stats[-1]['file'] = fn
        all_classes.extend(r['classes'])
        for s in r['strings']:
            for u in re.findall(r'https?://[^\s"\'<>\\]+', s):
                url_counter[u] += 1
                m = re.match(r'https?://([^/:\s]+)', u)
                if m: domain_counter[m.group(1)] += 1
    for c in all_classes:
        m = re.match(r'L([a-zA-Z0-9_]+)/([a-zA-Z0-9_]+)/', c)
        if m: pkg_counter[m.group(1)+'.'+m.group(2)] += 1
        elif c.startswith('L'):
            m2 = re.match(r'L([a-zA-Z0-9_]+)/', c)
            if m2: pkg_counter[m2.group(1)] += 1
    summary['dex'] = {
        'files': dex_stats,
        'total_classes': len(all_classes),
        'total_strings': sum(d['string_count'] for d in dex_stats),
        'total_methods': sum(d['method_count'] for d in dex_stats),
        'total_fields': sum(d['field_count'] for d in dex_stats),
        'top_packages': pkg_counter.most_common(40),
        'top_domains': domain_counter.most_common(60),
        'top_urls': url_counter.most_common(40),
    }
    # --- resources.arsc global string pool
    arsc = open('/tmp/apk/resources.arsc','rb').read()
    # global header 12 bytes then string pool chunk
    pool = StrPool(arsc, 12)
    summary['resources'] = {'global_string_count': len(pool.strings)}
    # --- library versions from .version files
    versions = {}
    with zipfile.ZipFile(apks['base']) as z:
        for i in z.infolist():
            if i.filename.endswith('.version'):
                try: versions[i.filename] = z.read(i.filename).decode('utf-8','replace').strip()
                except Exception: pass
    summary['library_versions'] = versions
    # --- assets inventory
    assets = [(f, s) for f, s in zip_list(apks['base']) if f.startswith('assets/')]
    summary['assets'] = {'count': len(assets), 'total_size': sum(s for _, s in assets),
                         'largest': sorted(assets, key=lambda x: -x[1])[:25]}
    # --- manifest stats
    import xml.etree.ElementTree as ET
    tree = ET.parse('/home/user/-/analysis/AndroidManifest.decoded.xml')
    root = tree.getroot()
    def tagcount(t): return len(root.findall('.//'+t))
    acts = root.findall('.//activity') + root.findall('.//activity-alias')
    exported = [a.get('name') for a in acts if a.get('exported') == 'true']
    schemes, hosts = Counter(), Counter()
    for d in root.findall('.//data'):
        if d.get('scheme'): schemes[d.get('scheme')] += 1
        if d.get('host'): hosts[d.get('host')] += 1
    app = root.find('.//application')
    summary['manifest'] = {
        'package': root.get('package'), 'versionCode': root.get('versionCode'),
        'versionName': root.get('versionName'),
        'minSdk': root.find('.//uses-sdk').get('minSdkVersion'),
        'targetSdk': root.find('.//uses-sdk').get('targetSdkVersion'),
        'activities': len(acts), 'exported_activities': len(exported),
        'exported_activity_names': exported[:40],
        'services': tagcount('service'), 'receivers': tagcount('receiver'),
        'providers': tagcount('provider'),
        'permissions': [p.get('name') for p in root.findall('.//uses-permission')],
        'features': [f.get('name') for f in root.findall('.//uses-feature')],
        'application_name': app.get('name') if app is not None else None,
        'deep_link_schemes': schemes.most_common(30),
        'deep_link_hosts': hosts.most_common(30),
    }
    json.dump(summary, open(OUT, 'w'), indent=1, ensure_ascii=False)
    print('wrote', OUT)
    print('classes=%d strings=%d methods=%d' % (summary['dex']['total_classes'], summary['dex']['total_strings'], summary['dex']['total_methods']))
    print('native libs=%d assets=%d arsc strings=%d' % (len(libs), len(assets), len(pool.strings)))
    print('top pkgs:', pkg_counter.most_common(12))
    print('top domains:', domain_counter.most_common(15))

if __name__ == '__main__':
    main()
