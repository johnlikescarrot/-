#!/usr/bin/env python3
import json, os, hashlib, collections, zipfile, traceback, re, sys
# silence loguru
try:
    from loguru import logger
    logger.remove()
    logger.add(sys.stderr, level="WARNING")
except Exception:
    pass

REPORTS = '/home/user/-/research/reports'
os.makedirs(REPORTS, exist_ok=True)

def sha256(path):
    h = hashlib.sha256()
    with open(path,'rb') as f:
        for chunk in iter(lambda: f.read(65536), b''):
            h.update(chunk)
    return h.hexdigest()

def analyze(apk_path, label):
    out = {'label': label, 'path': apk_path, 'sha256': sha256(apk_path), 'size': os.path.getsize(apk_path)}
    try:
        from androguard.core.apk import APK
        a = APK(apk_path)
        out['package'] = a.get_package()
        out['version_name'] = a.get_androidversion_name()
        out['version_code'] = a.get_androidversion_code()
        out['min_sdk'] = a.get_min_sdk_version()
        out['max_sdk'] = a.get_max_sdk_version()
        out['target_sdk'] = a.get_target_sdk_version()
        out['permissions'] = sorted(a.get_permissions())
        out['activities'] = sorted(a.get_activities())
        out['services'] = sorted(a.get_services())
        out['receivers'] = sorted(a.get_receivers())
        out['providers'] = sorted(a.get_providers())
        out['libraries'] = sorted(a.get_libraries())
        out['features'] = sorted([str(x) for x in a.get_features()])
        out['main_activity'] = a.get_main_activity()
        out['is_signed'] = a.is_signed()
        out['is_signed_v1'] = a.is_signed_v1()
        out['is_signed_v2'] = a.is_signed_v2()
        out['is_signed_v3'] = a.is_signed_v3()
        # certs
        certs = []
        try:
            from cryptography.hazmat.primitives import hashes
            for cert in a.get_certificates():
                certs.append({
                    'subject': cert.subject.rfc4514_string(),
                    'issuer': cert.issuer.rfc4514_string(),
                    'serial': str(cert.serial_number),
                    'not_before': str(getattr(cert,'not_valid_before_utc',cert.not_valid_before)),
                    'not_after': str(getattr(cert,'not_valid_after_utc',cert.not_valid_after)),
                    'sha1': cert.fingerprint(hashes.SHA1()).hex(),
                    'sha256': cert.fingerprint(hashes.SHA256()).hex(),
                })
        except Exception as e:
            out['cert_error'] = str(e)
        out['certificates'] = certs
        # files in apk
        files = list(a.get_files())
        out['file_count'] = len(files)
        # dex
        dex_names = list(a.get_dex_names())
        out['dex_names'] = list(dex_names)
        dex_info = []
        class_names = []
        strings_total = 0
        findings = {'urls':[], 'ips':[], 'secrets':[], 'firebase':[], 'crypto':[], 'native':[]}
        patterns = {
            'urls': re.compile(r'https?://[^\s"\'<>\\)]+'),
            'ips': re.compile(r'\b(?:\d{1,3}\.){3}\d{1,3}\b'),
            'secrets': re.compile(r'(?i)(api[_-]?key|secret|token|password|bearer|authorization)["\']?\s*[:=]\s*["\'][^"\']{6,}'),
            'firebase': re.compile(r'firebaseio\.com|googleapis\.com|appspot\.com|crashlytics\.com'),
            'crypto': re.compile(r'(?i)(AES/|RSA/|HmacSHA|SHA-?256|PBKDF2|KeyStore|javax\.crypto|Cipher\.getInstance)'),
            'native': re.compile(r'System\.loadLibrary|dlopen|\.so'),
        }
        pkg_counter = collections.Counter()
        from androguard.core.dex import DEX
        for dex_name in dex_names:
            try:
                dex_bytes = a.get_file(dex_name)
                d = DEX(dex_bytes)
                classes = list(d.get_classes())
                methods = list(d.get_methods())
                fields = list(d.get_fields())
                strings = list(d.get_strings())
                dex_info.append({
                    'name': dex_name,
                    'classes': len(classes),
                    'methods': len(methods),
                    'fields': len(fields),
                    'strings': len(strings),
                    'size': len(dex_bytes),
                })
                strings_total += len(strings)
                for s in strings:
                    val = s.get_value() if hasattr(s,'get_value') else str(s)
                    if not val: 
                        continue
                    for k, pat in patterns.items():
                        if len(findings[k]) < 300 and pat.search(val):
                            findings[k].append(val[:300])
                for cls in classes:
                    name = cls.get_name()
                    class_names.append(name)
                    clean = name.lstrip('L').replace('/','.').rstrip(';')
                    parts = clean.split('.')
                    if len(parts) >= 3:
                        pkg_counter['.'.join(parts[:3])] += 1
                    elif len(parts) >= 2:
                        pkg_counter['.'.join(parts[:2])] += 1
            except Exception as e:
                dex_info.append({'name': dex_name, 'error': str(e)})
        out['dex'] = dex_info
        out['strings_total'] = strings_total
        out['sensitive_string_findings'] = findings
        out['top_packages'] = pkg_counter.most_common(150)
        out['class_count'] = len(class_names)
        out['class_names'] = sorted(class_names)
    except Exception as e:
        out['error'] = str(e)
        out['traceback'] = traceback.format_exc()
    return out

def main():
    apks = [
        ('/tmp/apk_work/xapk/vn.com.vng.zalopay.apk', 'main'),
        ('/tmp/apk_work/xapk/config.armeabi_v7a.apk', 'config.armeabi_v7a'),
        ('/tmp/apk_work/xapk/config.en.apk', 'config.en'),
        ('/tmp/apk_work/xapk/config.vi.apk', 'config.vi'),
        ('/tmp/apk_work/xapk/config.xhdpi.apk', 'config.xhdpi'),
    ]
    for path, label in apks:
        print('ANALYZING', label, flush=True)
        r = analyze(path, label)
        with open(os.path.join(REPORTS, f'androguard_{label}.json'), 'w') as f:
            json.dump(r, f, indent=2, ensure_ascii=False)
        # summary without class_names
        summary = {k:v for k,v in r.items() if k != 'class_names'}
        with open(os.path.join(REPORTS, f'androguard_{label}_summary.json'), 'w') as f:
            json.dump(summary, f, indent=2, ensure_ascii=False)
        print('DONE', label, 'classes', r.get('class_count'), 'err', r.get('error'), flush=True)
    print('ALL_DONE', flush=True)

if __name__ == '__main__':
    main()
