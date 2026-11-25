"""Phase 2: Dalvik Executable (DEX) structural analysis.

Reads every .dex member in every APK, verifies the DEX checksum/signature, walks
the map_list, all class_defs, all class_data items and all code_items, and emits
aggregate (never literal-sensitive) evidence.
"""
from __future__ import annotations
import os, re, struct, zlib, hashlib, zipfile
from collections import Counter
from common import apk_paths, dump, sha256, ensure_extracted

MAP_TYPES = {0x0000: 'header_item', 0x0001: 'string_id_item', 0x0002: 'type_id_item',
             0x0003: 'proto_id_item', 0x0004: 'field_id_item', 0x0005: 'method_id_item',
             0x0006: 'class_def_item', 0x0007: 'call_site_id_item', 0x0008: 'method_handle_item',
             0x1000: 'map_list', 0x1001: 'type_list', 0x1002: 'annotation_set_ref_list',
             0x1003: 'annotation_set_item', 0x2000: 'class_data_item', 0x2001: 'code_item',
             0x2002: 'string_data_item', 0x2003: 'debug_info_item', 0x2004: 'annotation_item',
             0x2005: 'encoded_array_item', 0x2006: 'annotations_directory_item'}

ACC = [(0x1, 'public'), (0x2, 'private'), (0x4, 'protected'), (0x8, 'static'),
       (0x10, 'final'), (0x20, 'synchronized'), (0x40, 'bridge'), (0x80, 'varargs'),
       (0x100, 'native'), (0x400, 'abstract'), (0x800, 'strictfp'), (0x1000, 'synthetic'),
       (0x10000, 'constructor'), (0x20000, 'declared_synchronized')]

# Dalvik instruction widths, in 16-bit code units, for DEX versions 035..039.
W = [1] * 256
for i in (0x02, 0x05, 0x08): W[i] = 2
for i in (0x03, 0x06, 0x09): W[i] = 3
for i, n in {0x13: 2, 0x14: 3, 0x15: 2, 0x16: 2, 0x17: 3, 0x18: 5, 0x19: 2,
             0x1a: 2, 0x1b: 3, 0x1c: 2, 0x1f: 2, 0x20: 2, 0x22: 2, 0x23: 2,
             0x24: 3, 0x25: 3, 0x26: 3, 0x29: 2, 0x2a: 3, 0x2b: 3, 0x2c: 3,
             0x6e: 3, 0x6f: 3, 0x70: 3, 0x71: 3, 0x72: 3,
             0x74: 3, 0x75: 3, 0x76: 3, 0x77: 3, 0x78: 3,
             0xf0: 4, 0xf1: 4, 0xf2: 3, 0xf3: 3, 0xfa: 2, 0xfb: 2}.items(): W[i] = n
for i in range(0x2d, 0x3e): W[i] = 2
for i in range(0x44, 0x6e): W[i] = 2
for i in range(0x90, 0xb0): W[i] = 2
for i in range(0xb0, 0xd0): W[i] = 1
for i in range(0xd0, 0xe3): W[i] = 2

URL_RE = re.compile(r'https?://([A-Za-z0-9._:-]+)', re.I)
SECRET_RE = re.compile(r'(?i)(api[_-]?key|secret|token|password|private[_-]?key|bearer|authorization)')
SDK_MARKERS = {
    'androidx': 'Android Jetpack', 'kotlin': 'Kotlin stdlib', 'kotlinx': 'Kotlinx coroutines',
    'com.google.android.gms': 'Google Play Services', 'com.google.firebase': 'Firebase',
    'com.google.mlkit': 'ML Kit', 'com.google.gson': 'Gson', 'com.google.protobuf': 'Protobuf',
    'com.facebook': 'Facebook / Audience Network', 'com.appsflyer': 'AppsFlyer',
    'com.squareup.okhttp': 'OkHttp (Square)', 'okhttp3': 'OkHttp 3', 'retrofit2': 'Retrofit',
    'io.reactivex': 'RxJava', 'com.zalo': 'Zalo SDK', 'com.zing.zalo': 'Zalo SDK',
    'vn.com.vng.zalopay': 'ZaloPay first-party', 'com.visa': 'Visa CBP',
    'com.alipay': 'Alipay / Griver mini-app container', 'com.iapconnect': 'Griver/IAPConnect',
    'com.alibaba': 'Alibaba / Griver', 'com.tencent': 'Tencent', 'com.bumptech.glide': 'Glide',
    'com.facebook.imagepipeline': 'Fresco', 'org.tensorflow': 'TensorFlow Lite',
    'net.sqlcipher': 'SQLCipher', 'com.google.zxing': 'ZXing', 'com.airbnb.lottie': 'Lottie',
    'app.rive': 'Rive', 'com.scottyab': 'RootBeer / security-lite', 'com.nimbusds': 'Nimbus JOSE',
    'org.bouncycastle': 'Bouncy Castle', 'com.microsoft': 'Microsoft',
    'com.samsung': 'Samsung', 'com.huawei': 'Huawei', 'android.support': 'Android Support Library',
}


def uleb(d, off):
    r = 0; shift = 0
    for _ in range(5):
        b = d[off]; off += 1
        r |= (b & 0x7F) << shift
        if not (b & 0x80): return r, off
        shift += 7
    return r, off


def mutf8(d, off):
    n, off = uleb(d, off)
    end = d.find(b'\x00', off)
    if end < 0: return '', off
    return d[off:end].decode('utf-8', 'replace'), end + 1


def parse_dex(d: bytes, label: str, want_strings=True):
    u32 = lambda o: struct.unpack_from('<I', d, o)[0]
    magic = d[:8]
    checksum, signature = u32(8), d[12:32]
    file_size, header_size, endian = u32(32), u32(36), u32(40)
    map_off = u32(52)
    sizes = {k: u32(o) for k, o in (('string_ids', 56), ('type_ids', 64), ('proto_ids', 72),
                                    ('field_ids', 80), ('method_ids', 88), ('class_defs', 96))}
    offs = {k: u32(o) for k, o in (('string_ids', 60), ('type_ids', 68), ('proto_ids', 76),
                                   ('field_ids', 84), ('method_ids', 92), ('class_defs', 100))}
    checks = {
        'file_size_matches': file_size == len(d),
        'header_size': header_size,
        'endian_tag_ok': endian == 0x12345678,
        'adler32_checksum_ok': (zlib.adler32(d[12:]) & 0xFFFFFFFF) == checksum,
        'sha1_signature_ok': hashlib.sha1(d[32:]).digest() == signature,
    }
    # string table
    strs = []
    if offs['string_ids'] and sizes['string_ids']:
        for k in range(sizes['string_ids']):
            so = u32(offs['string_ids'] + 4 * k)
            s, _ = mutf8(d, so)
            strs.append(s)
    # type table -> class descriptors
    types = []
    for k in range(sizes['type_ids']):
        si = u32(offs['type_ids'] + 4 * k)
        types.append(strs[si] if si < len(strs) else '')
    # map list
    map_items = []
    if map_off:
        n = u32(map_off)
        for k in range(min(n, 64)):
            t, _u, sz, o = struct.unpack_from('<HHII', d, map_off + 4 + 12 * k)
            map_items.append({'type': f'0x{t:04x}', 'name': MAP_TYPES.get(t, 'unknown'),
                              'count': sz, 'offset': o})
    # class_defs + class_data + code_items
    namespaces = Counter(); flags = Counter(); method_flags = Counter()
    code_items = 0; code_units = 0; methods_with_code = 0; methods_total = 0
    fields_total = 0; tries_total = 0; register_hist = Counter(); opcodes = Counter()
    short_names = 0; first_party = 0; classes = 0
    for k in range(sizes['class_defs']):
        co = offs['class_defs'] + 32 * k
        if co + 32 > len(d): break
        cidx, acc, sup, iface, src, anno, cdoff, sval = struct.unpack_from('<IIIIIIII', d, co)
        desc = types[cidx] if cidx < len(types) else ''
        name = desc[1:-1].replace('/', '.') if desc.startswith('L') and desc.endswith(';') else desc
        classes += 1
        parts = name.split('.')
        if parts:
            namespaces['.'.join(parts[:2]) if len(parts) >= 2 else parts[0]] += 1
            namespaces['.'.join(parts[:3])] += 1
        if len(parts) >= 2 and parts[-1] in ('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z') and len(parts[-1]) == 1:
            short_names += 1
        if name.startswith('vn.com.vng.zalopay'): first_party += 1
        for bit, nm in ACC:
            if acc & bit: flags[nm] += 1
        if not cdoff: continue
        p = cdoff
        try:
            nsf, p = uleb(d, p); nif, p = uleb(d, p); ndm, p = uleb(d, p); nvm, p = uleb(d, p)
            fields_total += nsf + nif; methods_total += ndm + nvm
            for _ in range(nsf + nif):
                _idx, p = uleb(d, p); _af, p = uleb(d, p)
            for _ in range(ndm + nvm):
                _idx, p = uleb(d, p); af, p = uleb(d, p); coff, p = uleb(d, p)
                for bit, nm in ACC:
                    if af & bit: method_flags[nm] += 1
                if not coff or coff + 16 > len(d): continue
                methods_with_code += 1
                reg, ins, outs, ntries, dbg, insns_size = struct.unpack_from('<HHHHII', d, coff)
                code_items += 1; code_units += insns_size; tries_total += ntries
                register_hist[min(reg, 32)] += 1
                # instruction-boundary opcode walk
                q = coff + 16; end = q + insns_size * 2
                guard = 0
                while q + 2 <= end and guard < 100000:
                    guard += 1
                    unit = struct.unpack_from('<H', d, q)[0]
                    op = unit & 0xFF
                    width = W[op]
                    if op == 0x00 and (unit >> 8) in (0x01, 0x02, 0x03):
                        ident = unit >> 8
                        cnt = struct.unpack_from('<H', d, q + 2)[0] if q + 4 <= end else 0
                        if ident == 0x01: width = 4 + 2 * cnt
                        elif ident == 0x02: width = 2 + 3 * cnt
                        else:
                            ew = struct.unpack_from('<H', d, q + 2)[0] if q + 4 <= end else 1
                            n = u32(q + 4) if q + 8 <= end else 0
                            width = 4 + (n * max(1, ew) + 1) // 2
                        opcodes[f'0x00/payload_{ident:02x}'] += 1
                    else:
                        opcodes[f'0x{op:02x}'] += 1
                    if width < 1: width = 1
                    q += width * 2
        except (IndexError, struct.error):
            pass
    # strings statistics (aggregate only; no literal dumps of secrets)
    lengths = Counter(); domains = Counter(); secret_hits = 0; url_hits = 0
    sdk_presence = Counter()
    for s in strs:
        lengths[min(len(s) // 8 * 8, 256)] += 1
        if SECRET_RE.search(s): secret_hits += 1
        for m in URL_RE.finditer(s):
            url_hits += 1
            host = m.group(1).split(':')[0].lower()
            parts2 = host.split('.')
            dom = '.'.join(parts2[-2:]) if len(parts2) >= 2 else host
            domains[dom] += 1
        low = s.lower()
        for k in SDK_MARKERS:
            if k.lower() in low: sdk_presence[k] += 1
    return {
        'file': label, 'size': len(d), 'sha256': sha256(d)[:16],
        'magic': magic.decode('latin1', 'replace'), 'checks': checks,
        'header': {'file_size': file_size, 'header_size': header_size, 'endian_tag': f'0x{endian:08x}',
                   'map_off': map_off, 'data_size': u32(104), 'data_off': u32(108),
                   'link_size': u32(44), 'link_off': u32(48)},
        'counts': {'strings': sizes['string_ids'], 'types': sizes['type_ids'],
                   'protos': sizes['proto_ids'], 'fields': sizes['field_ids'],
                   'methods': sizes['method_ids'], 'class_defs': sizes['class_defs'],
                   'classes_walked': classes, 'fields_in_class_data': fields_total,
                   'methods_in_class_data': methods_total, 'methods_with_code': methods_with_code,
                   'code_items': code_items, 'code_units': code_units, 'try_blocks': tries_total,
                   'short_obfuscated_class_names': short_names, 'first_party_classes': first_party},
        'map_list': map_items,
        'class_access_flags': dict(flags.most_common()),
        'method_access_flags': dict(method_flags.most_common()),
        'register_histogram': dict(sorted(register_hist.items())),
        'opcode_histogram': dict(opcodes.most_common(40)),
        'string_length_histogram': {str(k): v for k, v in sorted(lengths.items())},
        'domains_top': dict(domains.most_common(40)),
        'url_string_hits': url_hits,
        'secret_like_string_count_not_published': secret_hits,
        'namespaces_top': dict(namespaces.most_common(45)),
        'sdk_string_markers': dict(sdk_presence.most_common()),
    }


def main():
    ensure_extracted()
    per, agg = [], {'dex_files': 0, 'total_bytes': 0, 'counts': Counter(),
                    'opcodes': Counter(), 'namespaces': Counter(), 'domains': Counter(),
                    'flags': Counter(), 'verification_failures': []}
    for apk in apk_paths():
        label = os.path.basename(apk)
        with zipfile.ZipFile(apk) as z:
            for i in z.infolist():
                if not i.filename.endswith('.dex'):
                    continue
                b = z.read(i)
                r = parse_dex(b, label + ':' + i.filename)
                per.append(r)
                agg['dex_files'] += 1; agg['total_bytes'] += len(b)
                for k, v in r['counts'].items(): agg['counts'][k] += v
                for k, v in r['opcode_histogram'].items(): agg['opcodes'][k] += v
                for k, v in r['namespaces_top'].items(): agg['namespaces'][k] += v
                for k, v in r['domains_top'].items(): agg['domains'][k] += v
                for k, v in r['class_access_flags'].items(): agg['flags'][k] += v
                if not all([r['checks']['file_size_matches'], r['checks']['adler32_checksum_ok'],
                            r['checks']['sha1_signature_ok'], r['checks']['endian_tag_ok']]):
                    agg['verification_failures'].append(r['file'])
                print(label, i.filename, 'classes', r['counts']['classes_walked'],
                      'methods', r['counts']['methods'], 'code_units', r['counts']['code_units'],
                      'checksum', r['checks']['adler32_checksum_ok'], r['checks']['sha1_signature_ok'])
    dump('dex.json', {
        'schema': 'rea_lite/dex@1',
        'per_file': per,
        'aggregate': {
            'dex_files': agg['dex_files'], 'total_dex_bytes': agg['total_bytes'],
            'counts': dict(agg['counts']),
            'opcode_histogram_top': dict(agg['opcodes'].most_common(40)),
            'namespace_histogram_top': dict(agg['namespaces'].most_common(80)),
            'domains_top': dict(agg['domains'].most_common(50)),
            'class_access_flags': dict(agg['flags'].most_common()),
            'verification_failures': agg['verification_failures'],
        },
        'limits': [
            'DEX strings are analysed only in aggregate; no literal string dumps.',
            'Domains are reduced to the last two labels; query strings and paths are never parsed.',
            'Instruction widths follow the DEX 035-039 opcode table; quickened/oat opcodes are treated as width 1.',
            'Structural analysis only: no decompilation, control-flow reconstruction or behaviour claims.',
        ],
    })
    # supply chain view: namespace counts mapped onto known SDK markers
    sdk = {}
    for k, desc in SDK_MARKERS.items():
        hits = sum(v for n, v in agg['namespaces'].items() if n == k or n.startswith(k + '.'))
        if hits: sdk[k] = {'description': desc, 'namespace_class_count': hits}
    dump('supply_chain.json', {
        'schema': 'rea_lite/supply_chain@1',
        'method': 'class descriptors from 21 DEX files, aggregated by namespace prefix',
        'sdk_namespace_evidence': dict(sorted(sdk.items(), key=lambda x: -x[1]['namespace_class_count'])),
        'top_namespaces_overall': dict(agg['namespaces'].most_common(60)),
        'note': 'Presence is a namespace-level observation, not a version or license assertion.',
    })


if __name__ == '__main__':
    main()
