"""Phase 4a: classify EVERY member, parse config JSON *shapes* (never values), and extract build provenance."""
from __future__ import annotations
import io, json, os, re, zipfile, struct
from collections import Counter
from common import apk_paths, dump, sha256, entropy, ensure_extracted, load

SENSITIVE_PATH = re.compile(r'(?i)(config|public_key|signature|\.dat$|secret|credential|token|key)')
VERSION_RE = re.compile(r'^[0-9][A-Za-z0-9._+\-]{0,39}$')


def shape(node, depth=0, out=None, maxdepth=14):
    """Emit only structural facts about a JSON document: key names, types, lengths."""
    if out is None:
        out = {'keys': Counter(), 'types': Counter(), 'array_lengths': Counter(),
               'string_lengths': Counter(), 'max_depth': 0, 'nodes': 0}
    out['nodes'] += 1
    out['max_depth'] = max(out['max_depth'], depth)
    if out['nodes'] > 250000 or depth > maxdepth:
        return out
    if isinstance(node, dict):
        out['types']['object'] += 1
        out['array_lengths'][min(len(node), 200)] += 1
        for k, v in node.items():
            if isinstance(k, str):
                out['keys'][k[:80]] += 1
            shape(v, depth + 1, out, maxdepth)
    elif isinstance(node, list):
        out['types']['array'] += 1
        out['array_lengths'][min(len(node), 200)] += 1
        for v in node[:200]:
            shape(v, depth + 1, out, maxdepth)
    elif isinstance(node, str):
        out['types']['string'] += 1
        out['string_lengths'][min(len(node) // 16 * 16, 512)] += 1
    elif isinstance(node, bool): out['types']['boolean'] += 1
    elif node is None: out['types']['null'] += 1
    elif isinstance(node, (int, float)): out['types']['number'] += 1
    return out


def short_shape(s):
    return {'nodes': s['nodes'], 'max_depth': s['max_depth'],
            'types': dict(s['types']),
            'key_names_top': dict(s['keys'].most_common(30)),
            'array_length_buckets': {str(k): v for k, v in sorted(s['array_lengths'].items())[:20]},
            'string_length_buckets': {str(k): v for k, v in sorted(s['string_lengths'].items())[:20]}}


def magic_name(b):
    if b[:4] == b'PK\x03\x04': return 'zip'
    if b[4:8] == b'TFL3' or b[:4] == b'TFL3': return 'tflite-flatbuffer'
    if b[:6] == b'#!AMR\n': return 'amr-nb-audio'
    if b[:4] == b'RIFF': return 'riff/rive'
    if b[:4] == b'\x00\x01\x00\x00': return 'truetype-font'
    if b[:4] == b'OTTO': return 'opentype-font'
    if b[:4] == b'PK\x03\x04': return 'zip'
    if b[:4] == b'pro\x00': return 'art-baseline-profile'
    if b[:8] == b'dex\n035\x00': return 'dex'
    if b[:4] == b'\x7fELF': return 'elf'
    if b[:4] == b'\x89PNG': return 'png'
    if b[:4] == b'\x1f\x8b': return 'gzip'
    if b[:8] == b'GIF89a' or b[:8] == b'GIF87a': return 'gif'
    if b[:4] == b'\x03\x00\x08\x00': return 'axml'
    return 'opaque-or-text'


def main():
    ensure_extracted()
    inv = load('inventory.json')
    entries = [(c['container'], e) for c in inv['containers'] for e in c['entries']]
    ext, prefix, entropy_buckets, largest = Counter(), Counter(), Counter(), []
    for cont, e in entries:
        x = os.path.splitext(e['path'])[1].lower() or '<none>'
        ext[x] += 1
        prefix[e['path'].split('/')[0] if '/' in e['path'] else '<root>'] += 1
        entropy_buckets[f'{int(e["entropy"] * 2) / 2:.1f}..{int(e["entropy"] * 2) / 2 + 0.5:.1f}'] += 1
        largest.append((e['size'], cont, e['path'], e['sha256_short'], e['entropy']))
    largest.sort(reverse=True)
    base = os.path.join(os.path.dirname(os.path.abspath(__file__)), '..', '..', 'work', 'xapk', 'vn.com.vng.zalopay.apk')
    z = zipfile.ZipFile(base)
    asset_groups, config_json, models, sensitive, versions = Counter(), [], [], [], {}
    vietnamese_files = 0
    for i in z.infolist():
        n = i.filename
        if not n.startswith('assets/'):
            continue
        b = z.read(i)
        parts = n.split('/')
        group = '/'.join(parts[:2]) if len(parts) > 2 else 'assets/<root>'
        asset_groups[group] += 1
        if b'\xc3\x83' in b or b'\xe1\xbb\x9b' in b or b'\xcc\x9b' in b:
            vietnamese_files += 1
        if n.lower().endswith('.json'):
            try:
                doc = json.loads(b.decode('utf-8', 'replace'))
                config_json.append({'path': n, 'size': len(b), 'sha256': sha256(b)[:16],
                                    'shape': short_shape(shape(doc))})
            except Exception as ex:
                config_json.append({'path': n, 'size': len(b), 'sha256': sha256(b)[:16],
                                    'parse_error': type(ex).__name__})
        if n.lower().endswith(('.tflite', '.binarypb', '.pb', '.fb', '.bincfg', '.param',
                               '.bin', '.ncnn', '.riv', '.amr', '.emd', '.ttf', '.dat', '.prof', '.profm')):
            models.append({'path': n, 'size': len(b), 'sha256': sha256(b)[:16],
                           'magic': magic_name(b), 'entropy_first_64k': entropy(b)})
        if SENSITIVE_PATH.search(n):
            sensitive.append({'path': n, 'size': len(b), 'sha256': sha256(b)[:16],
                              'magic': magic_name(b),
                              'policy': 'content deliberately not published; shape only'})
    # ncnn param header (text: magic, layer_count, blob_count)
    ncnn = []
    for n in z.namelist():
        if n.endswith('.param'):
            lines = z.read(n).decode('utf-8', 'replace').splitlines()
            if len(lines) >= 2:
                nums = lines[1].split()
                ncnn.append({'path': n, 'magic_line': lines[0],
                             'layer_count': int(nums[0]) if nums and nums[0].isdigit() else None,
                             'blob_count': int(nums[1]) if len(nums) > 1 and nums[1].isdigit() else None})
    # version markers across all APKs
    version_rows = []
    for apk in apk_paths():
        zz = zipfile.ZipFile(apk)
        for i in zz.infolist():
            n = i.filename
            if not (n.endswith('.version') or n.endswith('.properties') or n.endswith('.txt') and 'version' in n):
                continue
            b = zz.read(i)
            try: txt = b.decode('utf-8', 'replace')
            except Exception: continue
            vals = []
            for line in txt.splitlines():
                line = line.strip()
                if not line or line.startswith('#'): continue
                v = line.split('=', 1)[1].strip() if '=' in line else line
                if VERSION_RE.match(v): vals.append(v)
            if vals:
                version_rows.append({'container': os.path.basename(apk), 'path': n, 'values': vals[:3]})
    dump('library_versions.json', {
        'schema': 'rea_lite/library_versions@1',
        'count': len(version_rows), 'rows': version_rows,
        'note': 'Values are public SDK/version markers. Files not matching a version-shaped value are omitted.',
    })
    dump('assets.json', {
        'schema': 'rea_lite/assets@1',
        'member_classification': {
            'by_extension': dict(ext.most_common(60)),
            'by_top_level_path': dict(prefix.most_common(20)),
            'entropy_histogram': {k: v for k, v in sorted(entropy_buckets.items())},
            'largest_40_members': [{'container': c, 'path': p, 'size': s,
                                    'sha256_short': h, 'entropy': en}
                                   for s, c, p, h, en in largest[:40]],
        },
        'base_apk_assets': {
            'groups_by_count': dict(asset_groups.most_common(40)),
            'json_files_parsed_as_shape': len(config_json),
            'json_shapes': config_json,
            'model_and_media_payloads': models,
            'model_counts_by_magic': dict(Counter(m['magic'] for m in models)),
            'ncnn_param_headers': ncnn,
            'sensitive_or_config_files': sensitive,
            'files_containing_vietnamese_utf8_bytes': vietnamese_files,
        },
        'limits': [
            'JSON files are reported as key-name/type/length histograms; NO config values are published.',
            'Config/key/opaque files appear only as path + size + SHA-256 + magic; contents are never copied.',
            'Format identification is magic-number based, not full schema validation.',
            'No opaque/protected payload was decrypted, executed or unpacked beyond ZIP structure.',
        ],
    })
    print('members classified', len(entries), 'config json', len(config_json),
          'model/media payloads', len(models), 'version markers', len(version_rows))


if __name__ == '__main__':
    main()
