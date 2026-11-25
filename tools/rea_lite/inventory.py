"""Phase 0: complete, byte-level inventory of EVERY member of EVERY container."""
from __future__ import annotations
import os, zipfile, json, hashlib
from collections import Counter
from common import CORPUS, WORK, XAPK, sha256, entropy, dump, ensure_extracted, apk_paths

METHOD = {0: 'stored', 8: 'deflate', 9: 'deflate64', 12: 'bzip2', 14: 'lzma', 93: 'zstd', 95: 'xz'}


def guess(path: str, head: bytes):
    n = path.lower()
    if head[:4] == b'\x03\x00\x08\x00': return 'android-binary-xml (AXML)'
    if head[:4] == b'\x02\x00\x0c\x00': return 'android-resource-table (ARSC)'
    if head[:8] == b'dex\n035\x00' or head[:4] == b'dex\n': return 'dalvik-executable (DEX)'
    if head[:4] == b'\x7fELF': return 'elf-shared-object'
    if head[:4] == b'PK\x03\x04': return 'zip-container'
    if head[:8] == b'APK Sig Block 42': return 'apk-signing-block'
    if head[:4] == b'TFL3': return 'tflite-model'
    if head[:4] == b'\x89PNG': return 'png'
    if head[:6] in (b'RIFF\x00\x00', b'RIFF'): return 'riff/rive'
    if head[:4] == b'\x1f\x8b': return 'gzip'
    for ext, kind in (('.xml','xml'),('.json','json'),('.js','javascript'),('.html','html'),
                      ('.ttf','truetype-font'),('.webp','webp'),('.png','png'),('.gif','gif'),
                      ('.properties','java-properties'),('.version','version-marker'),
                      ('.kotlin_module','kotlin-module'),('.binarypb','protobuf-binary'),
                      ('.pb','protobuf-binary'),('.proto','protobuf-text?'),('.tflite','tflite-model'),
                      ('.bin','binary'),('.dat','binary'),('.amr','amr-audio'),('.riv','rive-animation'),
                      ('.dex','dalvik-executable'),('.so','elf-shared-object'),('.arsc','android-resource-table'),
                      ('.txt','text'),('.md','markdown'),('.emd','markdown'),('.h','c-header'),
                      ('.d','c-dependency'),('.m','objective-c'),('.fb','flatbuffer'),
                      ('.bincfg','binary-config'),('.sf','jar-signature-file'),('.rsa','pkcs7-signature')):
        if n.endswith(ext): return kind
    return 'unknown'


def scan(label: str, path: str):
    out = {'container': label, 'path': os.path.relpath(path, WORK) if path.startswith(WORK) else os.path.basename(path),
           'sha256': sha256(open(path, 'rb').read()), 'bytes': os.path.getsize(path), 'entries': []}
    with zipfile.ZipFile(path) as z:
        for i in z.infolist():
            if i.filename.endswith('/'):
                continue
            try:
                b = z.read(i)
            except Exception as e:
                b = b''
            out['entries'].append({
                'path': i.filename,
                'size': i.file_size,
                'compressed_size': i.compress_size,
                'method': METHOD.get(i.compress_type, str(i.compress_type)),
                'crc32': f'{i.CRC:08x}',
                'local_header_offset': i.header_offset,
                'sha256': sha256(b),
                'sha256_short': sha256(b)[:16],
                'entropy': entropy(b),
                'kind': guess(i.filename, b[:16]),
                'created': list(i.date_time),
                'external_attr': i.external_attr >> 16,
            })
    out['entry_count'] = len(out['entries'])
    out['total_uncompressed'] = sum(e['size'] for e in out['entries'])
    out['total_compressed'] = sum(e['compressed_size'] for e in out['entries'])
    out['methods'] = dict(Counter(e['method'] for e in out['entries']))
    out['kinds'] = dict(Counter(e['kind'] for e in out['entries']).most_common())
    out['compression_ratio'] = round(out['total_compressed'] / max(1, out['total_uncompressed']), 4)
    return out


def main():
    inner = ensure_extracted()
    containers = [scan('outer-zip', CORPUS), scan('xapk-bundle', os.path.join(WORK, 'bundle.xapk'))]
    for p in apk_paths():
        containers.append(scan('apk:' + os.path.basename(p), p))
    manifest = json.load(open(os.path.join(XAPK, 'manifest.json'), encoding='utf-8'))
    icon = os.path.join(XAPK, 'icon.png')
    extras = [{'path': 'icon.png', 'bytes': os.path.getsize(icon), 'sha256': sha256(open(icon, 'rb').read())},
              {'path': 'manifest.json', 'bytes': os.path.getsize(os.path.join(XAPK, 'manifest.json')),
               'sha256': sha256(open(os.path.join(XAPK, 'manifest.json'), 'rb').read())}]
    dump('inventory.json', {
        'schema': 'rea_lite/inventory@1',
        'corpus': {'file': os.path.basename(CORPUS), 'sha256': sha256(open(CORPUS, 'rb').read()),
                   'bytes': os.path.getsize(CORPUS), 'inner_member': inner},
        'xapk_manifest': manifest,
        'standalone_members': extras,
        'containers': containers,
        'limits': ['SHA-256 computed over the full decompressed member bytes',
                   'Shannon entropy computed over the first 65536 bytes of each member',
                   'kind is a magic-number/extension heuristic, not a full format validation'],
    })
    tot = sum(c['entry_count'] for c in containers)
    summary = {
        'schema': 'rea_lite/inventory_summary@1',
        'container_count': len(containers),
        'total_zip_members_inventoried': tot,
        'apk_members_inventoried': sum(c['entry_count'] for c in containers if c['container'].startswith('apk:')),
        'total_uncompressed_bytes': sum(c['total_uncompressed'] for c in containers),
        'per_container': [{'container': c['container'], 'entries': c['entry_count'],
                           'uncompressed': c['total_uncompressed'], 'compressed': c['total_compressed'],
                           'ratio': c['compression_ratio'], 'sha256': c['sha256'][:16]} for c in containers],
        'global_kinds': dict(Counter({k: v for c in containers for k, v in c['kinds'].items()}).most_common(30)),
    }
    dump('inventory_summary.json', summary)
    print('TOTAL ZIP MEMBERS:', tot)


if __name__ == '__main__':
    main()
