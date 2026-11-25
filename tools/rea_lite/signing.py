"""Phase 3b: APK Signing Block (v2/v3), JAR v1 signatures and split consistency."""
from __future__ import annotations
import os, struct, zipfile, hashlib, subprocess, tempfile
from collections import Counter
from common import apk_paths, dump, sha256, ensure_extracted

BLOCK_MAGIC = b'APK Sig Block 42'
IDS = {0x7109871a: 'APK Signature Scheme v2', 0xf05368c0: 'APK Signature Scheme v3',
       0x1b93ad61: 'APK Signature Scheme v3.1', 0x42726577: 'verity padding block',
       0x559f8b02: 'source stamp', 0x26525549: 'unknown/stamp', 0x6b6f6f62: 'unknown'}
SIG_ALGOS = {0x0101: 'RSASSA-PSS SHA2-256', 0x0102: 'RSASSA-PSS SHA2-512',
             0x0103: 'RSA PKCS#1 v1.5 SHA2-256', 0x0104: 'RSA PKCS#1 v1.5 SHA2-512',
             0x0201: 'ECDSA SHA2-256', 0x0202: 'ECDSA SHA2-512',
             0x0301: 'DSA SHA2-256', 0x0421: 'RSA PKCS#1 v1.5 SHA2-256 (legacy)',
             0x0423: 'ECDSA SHA2-256 (legacy)', 0x0425: 'DSA SHA2-256 (legacy)'}


def signing_block(d: bytes):
    eocd = d.rfind(b'PK\x05\x06')
    if eocd < 0: return None
    cd_off = struct.unpack_from('<I', d, eocd + 16)[0]
    if d[cd_off - 16:cd_off] != BLOCK_MAGIC: return None
    size = struct.unpack_from('<Q', d, cd_off - 24)[0]
    start = cd_off - 8 - size
    if start < 0: return None
    size1 = struct.unpack_from('<Q', d, start)[0]
    pairs, p, end = [], start + 8, cd_off - 24
    while p + 12 <= end:
        L = struct.unpack_from('<Q', d, p)[0]
        if L < 4 or p + 8 + L > end: break
        pid = struct.unpack_from('<I', d, p + 8)[0]
        val = d[p + 12:p + 8 + L]
        pairs.append({'id': f'0x{pid:08x}', 'scheme': IDS.get(pid, 'unknown'), 'length': L - 4,
                      'value': val})
        p += 8 + L
    return {'eocd_offset': eocd, 'central_directory_offset': cd_off,
            'block_start': start, 'block_size': size + 8,
            'leading_size': size1, 'trailing_size': size, 'sizes_match': size1 == size,
            'pairs': pairs}


def parse_signers(val: bytes, scheme: str):
    def u32(p): return struct.unpack_from('<I', val, p)[0]
    try:
        p = 0; seq_len = u32(p); p += 4; seq_end = min(p + seq_len, len(val))
        signers = []
        while p + 4 <= seq_end:
            slen = u32(p); p += 4; send = min(p + slen, len(val))
            sdlen = u32(p); p += 4; sdend = min(p + sdlen, len(val))
            # digests sequence
            dglen = u32(p); p += 4; dgend = min(p + dglen, len(val))
            algos, digest_lengths = [], []
            while p + 8 <= dgend:
                a = u32(p); dl = u32(p + 4); p += 8
                algos.append({'id': f'0x{a:04x}', 'name': SIG_ALGOS.get(a, 'unknown')})
                digest_lengths.append(dl); p += dl
            p = dgend
            # certificate sequence
            clen = u32(p); p += 4; cend = min(p + clen, len(val))
            certs = []
            while p + 4 <= cend:
                c = u32(p); p += 4
                cert = val[p:p + c]; p += c
                certs.append({'length': c, 'sha256': hashlib.sha256(cert).hexdigest()})
            p = cend
            # v3 min/max sdk (optional)
            minsdk = maxsdk = None
            if 'v3' in scheme and p + 8 <= sdend:
                minsdk, maxsdk = u32(p), u32(p + 4)
            signers.append({'algorithms': algos, 'digest_lengths': digest_lengths,
                            'certificates': certs, 'min_sdk': minsdk, 'max_sdk': maxsdk,
                            'certificate_count': len(certs)})
            p = send
        return {'signer_count': len(signers), 'signers': signers, 'parsed': True}
    except Exception as e:
        return {'parsed': False, 'error': str(e)}


def openssl_cert(path: bytes):
    with tempfile.TemporaryDirectory() as td:
        p = os.path.join(td, 'sig.der'); open(p, 'wb').write(path)
        try:
            pem = subprocess.run(['openssl', 'pkcs7', '-inform', 'DER', '-in', p, '-print_certs', '-out',
                                  os.path.join(td, 'c.pem')], capture_output=True, timeout=30)
            if pem.returncode != 0: return {'error': pem.stderr.decode()[:200]}
            out = subprocess.run(['openssl', 'x509', '-in', os.path.join(td, 'c.pem'), '-noout',
                                  '-subject', '-issuer', '-dates', '-serial', '-fingerprint', '-sha256'],
                                 capture_output=True, timeout=30)
            lines = [l.strip() for l in out.stdout.decode('utf-8', 'replace').splitlines() if l.strip()]
            return {'fields': lines}
        except Exception as e:
            return {'error': str(e)}


def jar_v1(z: zipfile.ZipFile, label: str):
    out = {'manifest_entries': 0, 'digest_algorithms': Counter(), 'signature_files': [],
           'certificate_files': []}
    names = z.namelist()
    for n in names:
        if n.upper() == 'META-INF/MANIFEST.MF':
            txt = z.read(n).decode('utf-8', 'replace')
            out['manifest_entries'] = sum(1 for l in txt.splitlines() if l.startswith('Name: '))
            for l in txt.splitlines():
                if '-Digest:' in l or '-Digest-Manifest:' in l:
                    out['digest_algorithms'][l.split(':')[0].strip()] += 1
        elif n.upper().endswith('.SF'):
            txt = z.read(n).decode('utf-8', 'replace')
            out['signature_files'].append({'path': n,
                                          'entries': sum(1 for l in txt.splitlines() if l.startswith('Name: ')),
                                          'sha256': sha256(z.read(n))[:16]})
        elif n.upper().endswith(('.RSA', '.DSA', '.EC')):
            b = z.read(n)
            out['certificate_files'].append({'path': n, 'length': len(b),
                                             'sha256_of_pkcs7': hashlib.sha256(b).hexdigest(),
                                             'openssl_x509': openssl_cert(b)})
    out['digest_algorithms'] = dict(out['digest_algorithms'])
    return out


def main():
    ensure_extracted()
    res, fingerprints = {}, Counter()
    for apk in apk_paths():
        label = os.path.basename(apk)
        raw = open(apk, 'rb').read()
        sb = signing_block(raw)
        with zipfile.ZipFile(apk) as z:
            v1 = jar_v1(z, label)
            stamp = []
            for n in z.namelist():
                if 'stamp-cert-sha256' in n:
                    stamp.append({'path': n, 'hex': z.read(n).hex()})
            for c in v1['certificate_files']:
                for f in c['openssl_x509'].get('fields', []):
                    if f.lower().startswith('sha256 fingerprint'):
                        fingerprints[f.split('=')[-1].strip().replace(':', '').lower()] += 1
        pair_out = []
        for pr in (sb['pairs'] if sb else []):
            e = {'id': pr['id'], 'scheme': pr['scheme'], 'length': pr['length']}
            if pr['id'] in ('0x7109871a', '0xf05368c0', '0x1b93ad61'):
                e['signers'] = parse_signers(pr['value'], pr['scheme'])
                for s in e['signers'].get('signers', []):
                    for c in s['certificates']:
                        fingerprints[c['sha256']] += 1
            else:
                e['value_sha256'] = hashlib.sha256(pr['value']).hexdigest()
            pair_out.append(e)
        res[label] = {'apk_sha256': hashlib.sha256(raw).hexdigest()[:16], 'apk_bytes': len(raw),
                      'signing_block': ({k: v for k, v in sb.items() if k != 'pairs'} if sb else None),
                      'block_pairs': pair_out, 'jar_v1': v1, 'stamp_cert_sha256': stamp}
        print(label, 'block', bool(sb), 'pairs', len(pair_out),
              'v1 manifest entries', v1['manifest_entries'], 'cert files', len(v1['certificate_files']))
    dump('signing.json', {
        'schema': 'rea_lite/signing@1', 'per_apk': res,
        'aggregate': {
            'apks_with_signing_block': sum(1 for r in res.values() if r['signing_block']),
            'signer_certificate_fingerprints': dict(fingerprints),
            'single_signer_identity_across_all_apks': len(fingerprints) == 1,
            'schemes_observed': dict(Counter(p['scheme'] for r in res.values() for p in r['block_pairs'])),
        },
        'limits': ['Certificate bytes are never published; only SHA-256 fingerprints and public X.509 subject/issuer fields.',
                   'Signature verification against a trusted root is out of scope; these are structural observations.',
                   'No signing keys, keystores or private material are present or derived.'],
    })


if __name__ == '__main__':
    main()
