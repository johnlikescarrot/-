"""Nested containers + Metro/React-Native JS bundle static analysis (no execution)."""
from __future__ import annotations
import io, os, re, json, zipfile, struct
from collections import Counter
from common import apk_paths, dump, sha256, entropy, ensure_extracted, WORK, XAPK

MOD_RE = re.compile(r'\},(\d+),\[([0-9,]*)\]\);')
PKG_MARKERS = ['react-native', 'react.navigation', '@react-navigation', 'redux', 'mobx',
               'zalopaycomponents', 'miniapps', 'hermes', 'jsc', 'Expo', 'axios',
               'react-native-safe-area', 'rn-fetch-blob', 'crypto-js', 'moment']


def main():
    ensure_extracted()
    apk = os.path.join(XAPK, 'vn.com.vng.zalopay.apk')
    z = zipfile.ZipFile(apk)
    nested = []
    for i in z.infolist():
        b = z.read(i)
        if not (i.filename.endswith('.zip') or i.filename.endswith('.jar') or b[:4] == b'PK\x03\x04'):
            continue
        try:
            zz = zipfile.ZipFile(io.BytesIO(b))
            ents = [{'path': e.filename, 'size': e.file_size, 'compressed': e.compress_size,
                     'crc32': f'{e.CRC:08x}', 'sha256': sha256(zz.read(e))[:16]}
                    for e in zz.infolist() if not e.filename.endswith('/')]
            nested.append({'path': i.filename, 'size': len(b), 'sha256': sha256(b)[:16],
                           'member_count': len(ents),
                           'uncompressed_bytes': sum(e['size'] for e in ents),
                           'extensions': dict(Counter(os.path.splitext(e['path'])[1] or '<none>' for e in ents).most_common(15)),
                           'members': ents})
        except Exception as e:
            nested.append({'path': i.filename, 'error': str(e)})
    dump('nested.json', {
        'schema': 'rea_lite/nested@1', 'nested_containers': nested,
        'note': 'Recursive (one-level-deep) enumeration of ZIP containers embedded in APK assets.',
        'limits': ['Only ZIP-family containers are expanded; .amr, .tflite, .riv, .dat and .pb are inventoried but not parsed into behaviour.',
                   'Opaque/encrypted-looking payloads are described by size and entropy only; no decryption attempted.'],
    })
    # Metro bundle
    jp = os.path.join(WORK, 'jsapp', 'main.jsbundle')
    if not os.path.exists(jp):
        os.makedirs(os.path.dirname(jp), exist_ok=True)
        for nc in nested:
            if nc['path'] == 'assets/android.1.zip':
                pass
        zz = zipfile.ZipFile(io.BytesIO(z.read('assets/android.1.zip')))
        open(jp, 'wb').write(zz.read('main.jsbundle'))
    b = open(jp, 'rb').read()
    text = b.decode('utf-8', 'replace')
    mods = MOD_RE.findall(text)
    ids = [int(m[0]) for m in mods]
    deps = [[int(x) for x in m[1].split(',') if x.strip()] for m in mods]
    indeg = Counter(d for ds in deps for d in ds)
    entry = [int(x) for x in re.findall(r'__r\((\d+)\);', text)]
    pkg = {k: text.count(k) for k in PKG_MARKERS if text.count(k)}
    out = {
        'schema': 'rea_lite/jsbundle@1',
        'source': 'assets/android.1.zip!main.jsbundle (React Native / Metro production bundle)',
        'bytes': len(b), 'sha256': sha256(b)[:16], 'entropy_first_64k': entropy(b),
        'bundle': {'dev_flag': '__DEV__=false' in text[:128],
                   'module_definitions_counted_by___d': b.count(b'__d('),
                   'module_definitions_parsed': len(mods),
                   'module_id_min': min(ids) if ids else None,
                   'module_id_max': max(ids) if ids else None,
                   'distinct_module_ids': len(set(ids)),
                   'dependency_edges': sum(len(x) for x in deps),
                   'max_dependencies_of_one_module': max((len(x) for x in deps), default=0),
                   'mean_dependencies': round(sum(len(x) for x in deps) / max(1, len(deps)), 3),
                   'most_required_module_ids': [{'id': k, 'count': v} for k, v in indeg.most_common(20)],
                   'entry_points___r': entry,
                   'factory_signature': 'function(g,r,i,a,m,e,d)',
                   'require_call_sites_r_d': len(re.findall(r'\br\(d\[\d+\]\)', text)),
                   'library_marker_counts': dict(sorted(pkg.items(), key=lambda x: -x[1]))},
        'limits': ['Regex-based module recovery from inert text; no bundle bootstrap or factory execution.',
                   'Dependency edges are numeric module IDs; no module source code is published.',
                   'Counts are structural evidence of a Metro bundle, not runtime behaviour.'],
    }
    dump('jsbundle.json', out)
    print('nested containers', len(nested), 'members', sum(n.get('member_count', 0) for n in nested))
    print('metro modules', len(mods), 'dependency edges', sum(len(x) for x in deps), 'entry points', entry)


if __name__ == '__main__':
    main()
