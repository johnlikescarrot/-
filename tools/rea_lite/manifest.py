"""Phase 1a: AndroidManifest.xml of every APK + a full AXML corpus scan."""
from __future__ import annotations
import os, re, zipfile, hashlib
from collections import Counter
from common import apk_paths, dump, sha256, ensure_extracted, XAPK
from formats import parse_axml

SENSITIVE = re.compile(r'(key|secret|token|password|credential|api|auth|client.?id|app.?id|cert)', re.I)
# Opaque high-entropy blobs (keys/tokens), deliberately NOT matching dotted
# Java/Kotlin class names, which are structural evidence we do want to publish.
LONG_OPAQUE = re.compile(r'^[A-Za-z0-9+/=_\-]{40,}$')
KEY_PREFIX = re.compile(r'^(AIza|AKIA|sk_|pk_|rk_|-----BEGIN|eyJ[A-Za-z0-9_\-]{20,})')


def mask(v: str):
    return {'redacted': True, 'length': len(v), 'sha256_16': sha256(v.encode())[:16]}


def safe_value(name: str, v):
    if isinstance(v, str) and (SENSITIVE.search(name) or LONG_OPAQUE.match(v) or KEY_PREFIX.match(v)):
        return mask(v)
    return v


def flat_attrs(node):
    out = {}
    for a in node.get('attrs', []):
        tv = a['value']
        val = tv.get('raw_string', tv.get('string', tv.get('bool', tv.get('int', tv.get('hex', tv.get('color', tv.get('float', tv['data'])))))))
        # <meta-data android:value="..."> is a classic credential location: always
        # publish only its length and a digest, never the literal.
        if node.get('tag') == 'meta-data' and 'value' in a['name'].lower() and isinstance(val, str):
            out[a['name']] = mask(val)
        else:
            out[a['name']] = safe_value(a['name'], val)
    return out


def walk(nodes):
    for n in nodes:
        yield n
        yield from walk(n.get('children', []))


def summarize_manifest(data: bytes, label: str):
    p = parse_axml(data)
    ns = list(walk(p['tree']))
    groups = Counter(n['tag'] for n in ns)
    root = flat_attrs(ns[0]) if ns else {}
    def collect(tag):
        return [flat_attrs(n) for n in ns if n['tag'] == tag]
    def component(tag):
        out = []
        for n in ns:
            if n['tag'] != tag:
                continue
            a = flat_attrs(n)
            filters = []
            for ch in walk(n.get('children', [])):
                if ch['tag'] == 'intent-filter':
                    fa = flat_attrs(ch)
                    acts = [flat_attrs(x).get('name') for x in walk(ch.get('children', [])) if x['tag'] == 'action']
                    cats = [flat_attrs(x).get('name') for x in walk(ch.get('children', [])) if x['tag'] == 'category']
                    datas = [flat_attrs(x) for x in walk(ch.get('children', [])) if x['tag'] == 'data']
                    filters.append({'priority': fa.get('priority'), 'actions': acts,
                                    'categories': cats,
                                    'data_scheme_count': len([d for d in datas if d.get('scheme')]),
                                    'data_host_count': len([d for d in datas if d.get('host')]),
                                    'data_path_count': len([d for d in datas if d.get('pathPrefix') or d.get('path')])})
            out.append({k: a.get(k) for k in ('name', 'exported', 'enabled', 'permission',
                                              'process', 'launchMode', 'directBootAware',
                                              'taskAffinity', 'authorities', 'screenOrientation',
                                              'windowSoftInputMode', 'configChanges') if a.get(k) is not None}
                       | {'intent_filters': filters})
        return out
    app = next((flat_attrs(n) for n in ns if n['tag'] == 'application'), {})
    meta = []
    for n in ns:
        if n['tag'] != 'meta-data':
            continue
        a = flat_attrs(n)
        meta.append({'name': a.get('name'), 'value': a.get('value', {'redacted': True, 'length': 0})})
    queries = [flat_attrs(x).get('name') for x in walk(p['tree']) if x['tag'] == 'package']
    return {
        'file': label, 'size': len(data), 'sha256': sha256(data)[:16],
        'parsed': p['ok'], 'errors': p['errors'],
        'string_pool_count': p['pool']['count'] if p['pool'] else 0,
        'string_pool_utf8': p['pool']['utf8'] if p['pool'] else None,
        'resource_map_size': len(p['resource_map']),
        'element_count': p['element_count'], 'chunk_counts': p['chunk_counts'],
        'root_attributes': root,
        'tag_counts': dict(groups.most_common()),
        'uses_sdk': collect('uses-sdk'),
        'permissions': [x.get('name') for x in collect('uses-permission')],
        'permission_definitions': [x.get('name') for x in collect('permission')],
        'features': [x.get('name') for x in collect('uses-feature')],
        'uses_libraries': [x.get('name') for x in collect('uses-library')],
        'application': app,
        'components': {t: component(t) for t in ('activity', 'activity-alias', 'service', 'receiver', 'provider')},
        'meta_data': meta,
        'queries_packages': queries[:60], 'queries_packages_total': len(queries),
        'limits': ['meta-data / key-like attribute values are redacted to length + digest',
                   'intent-filter data hosts/paths are counted, not copied'],
        'tree': p['tree'],
    }


def main():
    ensure_extracted()
    manifests, agg = {}, {'files_total': 0, 'axml_files': 0, 'plain_text_xml': 0,
                          'other_magic': 0, 'parse_failures': [], 'total_elements': 0,
                          'total_attributes': 0, 'chunk_counts': Counter(),
                          'element_names': Counter(), 'attribute_names': Counter(),
                          'reference_packages': Counter(), 'size_buckets': Counter(),
                          'files_per_apk': {}}
    for apk in apk_paths():
        label = os.path.basename(apk)
        n = 0
        with zipfile.ZipFile(apk) as z:
            for i in z.infolist():
                if not i.filename.lower().endswith('.xml'):
                    continue
                b = z.read(i)
                n += 1
                agg['files_total'] += 1
                agg['size_buckets'][f'{(len(b) // 256) * 256}..{(len(b) // 256) * 256 + 255}'] += 1
                if i.filename == 'AndroidManifest.xml':
                    manifests[label] = summarize_manifest(b, label + ':AndroidManifest.xml')
                if b[:4] == b'\x03\x00\x08\x00':
                    agg['axml_files'] += 1
                    try:
                        p = parse_axml(b)
                        agg['total_elements'] += p['element_count']
                        agg['total_attributes'] += sum(p['attribute_names'].values())
                        for k, v in p['chunk_counts'].items(): agg['chunk_counts'][k] += v
                        for k, v in p['element_names'].items(): agg['element_names'][k] += v
                        for k, v in p['attribute_names'].items(): agg['attribute_names'][k] += v
                        if p['errors']: agg['parse_failures'].append({'file': label + ':' + i.filename, 'error': p['errors'][0]})
                        for k, v in p['element_names'].items(): pass
                    except Exception as e:
                        agg['parse_failures'].append({'file': label + ':' + i.filename, 'error': str(e)})
                elif b.lstrip()[:1] == b'<':
                    agg['plain_text_xml'] += 1
                else:
                    agg['other_magic'] += 1
        agg['files_per_apk'][label] = n
    out = {
        'schema': 'rea_lite/manifest@1',
        'manifests': {k: {kk: vv for kk, vv in v.items() if kk != 'tree'} for k, v in manifests.items()},
        'axml_corpus': {
            **{k: (dict(v.most_common(60)) if isinstance(v, Counter) else v) for k, v in agg.items()},
            'parse_failure_count': len(agg['parse_failures']),
        },
        'evidence': 'Every count derives from a full ResXMLTree chunk walk of each .xml member.',
    }
    dump('manifest.json', out)
    print('AXML files parsed:', agg['axml_files'], 'elements:', agg['total_elements'],
          'failures:', len(agg['parse_failures']))
    for k, v in manifests.items():
        print(k, 'pkg', v['root_attributes'].get('package'), 'activities', len(v['components']['activity']),
              'permissions', len(v['permissions']))


if __name__ == '__main__':
    main()
