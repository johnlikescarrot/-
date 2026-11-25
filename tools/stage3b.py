#!/usr/bin/env python3
"""Stage 3b: lightweight per-DEX parsing (no cross-DEX analysis graph)."""
import os, json, re, gc, collections
from loguru import logger
logger.remove()
from androguard.core.dex import DEX

OUT = '/tmp/zp/reports'
DEXDIR = '/tmp/zp/dex'
report = {'dex_files': [], 'per_dex': {}, 'counts': {'classes': 0, 'methods': 0, 'fields': 0, 'strings': 0},
          'top_packages': collections.Counter(), 'largest_classes': [], 'symbols': {},
          'native_methods': [], 'string_keywords': collections.Counter(), 'url_hosts': collections.Counter()}
patterns = {
    'crypto': re.compile(r'(AES|RSA|HMAC|Signature|Cipher|KeyStore|SecretKey|MessageDigest|Crypto)', re.I),
    'network_tls': re.compile(r'(OkHttp|Retrofit|Http|SSL|TLS|Certificate|X509|TrustManager|HostnameVerifier|Pinning)', re.I),
    'jni': re.compile(r'(/JNI/|/Jni/|/native/|loadLibrary)', re.I),
    'anti_tamper': re.compile(r'(frida|Frida|magisk|emulator|Emulator|Root|Hook|Integrity|SafetyNet)', re.I),
    'biometric': re.compile(r'(Biometric|Fingerprint|Liveness)', re.I),
    'nfc_hce': re.compile(r'(Apdu|Nfc|NFC|Hce|HCE)', re.I),
    'miniapp_js': re.compile(r'(JsBridge|WebView|Griver|Ariver)', re.I),
    'ml_ocr': re.compile(r'(MLKit|Ocr|OCR|Tflite|TensorFlow|Barcode)', re.I),
}
keywords = ['https://', 'http://', 'zalopay', 'secret', 'password', 'AES', 'RSA', 'HMAC',
            'frida', 'root', 'emulator', 'SSL', 'TLS', 'pinning', 'certificate',
            'api_key', 'access_token', 'signature', 'firebase', 'appsflyer', 'adjust',
            'grpc', 'protobuf', 'hmac']
symbols = {k: [] for k in patterns}

for fn in sorted(os.listdir(DEXDIR)):
    path = os.path.join(DEXDIR, fn)
    d = DEX(open(path, 'rb').read())
    report['dex_files'].append(fn)
    classes = list(d.get_classes())
    methods = list(d.get_methods())
    report['per_dex'][fn] = {'bytes': os.path.getsize(path), 'classes': len(classes),
                             'methods': len(methods), 'strings': len(list(d.get_strings()))}
    report['counts']['classes'] += len(classes)
    report['counts']['methods'] += len(methods)
    for c in classes:
        name = c.get_name().strip('L;').replace('/', '.')
        parts = name.split('.')
        report['top_packages']['.'.join(parts[:2]) if len(parts) > 1 else name] += 1
        n_methods = len(list(c.get_methods()))
        if n_methods >= 100:
            report['largest_classes'].append((n_methods, c.get_name()))
        for label, pat in patterns.items():
            if pat.search(c.get_name()) and len(symbols[label]) < 150:
                symbols[label].append(c.get_name())
    for c in classes:
        for m in c.get_methods():
            try:
                if 'native' in str(m.get_access_flags_string()).lower():
                    report['native_methods'].append(c.get_name() + '->' + m.get_name())
            except Exception:
                pass
    for v in d.get_strings():
        if not isinstance(v, str):
            continue
        lv = v.lower()
        for k in keywords:
            if k in lv:
                report['string_keywords'][k] += 1
        if 'http://' in lv or 'https://' in lv:
            for h in re.findall(r'https?://([A-Za-z0-9.\-]+)', v)[:2]:
                report['url_hosts'][h] += 1
        for tag in ('zalopay://', 'hmac', 'secret', 'pinning'):
            if tag in lv and len(report.setdefault('string_examples', {}).setdefault(tag, [])) < 12 and len(v) < 160:
                report['string_examples'][tag].append(v)
    del d, classes, methods
    gc.collect()
    print('parsed', fn, report['per_dex'][fn], flush=True)

report['top_packages'] = report['top_packages'].most_common(70)
report['largest_classes'] = [{'class': n, 'methods': c} for c, n in sorted(report['largest_classes'], reverse=True)[:50]]
report['symbols'] = symbols
report['string_keywords'] = dict(report['string_keywords'])
report['url_hosts'] = report['url_hosts'].most_common(80)
report['native_methods'] = report['native_methods'][:200]
json.dump(report, open(os.path.join(OUT, 'dex-report.json'), 'w'), indent=2, ensure_ascii=False)
print('DONE', report['counts'])
