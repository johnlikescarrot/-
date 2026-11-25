#!/usr/bin/env python3
import json, os, sys, hashlib, collections, zipfile, traceback
from datetime import datetime, timezone

REPORTS = '/home/user/-/research/reports'
os.makedirs(REPORTS, exist_ok=True)

def sha256(path):
    h = hashlib.sha256()
    with open(path,'rb') as f:
        for chunk in iter(lambda: f.read(65536), b''):
            h.update(chunk)
    return h.hexdigest()

def analyze_with_androguard(apk_path, label):
    out = {'label': label, 'path': apk_path, 'sha256': sha256(apk_path), 'size': os.path.getsize(apk_path)}
    try:
        from androguard.misc import AnalyzeAPK
        a, d, dx = AnalyzeAPK(apk_path)
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
        # certs
        certs = []
        try:
            for cert in a.get_certificates():
                certs.append({
                    'subject': cert.subject.rfc4514_string() if hasattr(cert.subject,'rfc4514_string') else str(cert.subject),
                    'issuer': cert.issuer.rfc4514_string() if hasattr(cert.issuer,'rfc4514_string') else str(cert.issuer),
                    'serial': str(cert.serial_number),
                    'not_before': str(cert.not_valid_before),
                    'not_after': str(cert.not_valid_after),
                    'sha256_fingerprint': cert.fingerprint(hashes=None).hex() if False else None,
                })
        except Exception as e:
            out['cert_error'] = str(e)
        # better cert fingerprints
        try:
            from cryptography.hazmat.primitives import hashes
            certs2 = []
            for cert in a.get_certificates():
                certs2.append({
                    'subject': cert.subject.rfc4514_string(),
                    'issuer': cert.issuer.rfc4514_string(),
                    'serial': str(cert.serial_number),
                    'not_before': str(cert.not_valid_before_utc) if hasattr(cert,'not_valid_before_utc') else str(cert.not_valid_before),
                    'not_after': str(cert.not_valid_after_utc) if hasattr(cert,'not_valid_after_utc') else str(cert.not_valid_after),
                    'sha1': cert.fingerprint(hashes.SHA1()).hex(),
                    'sha256': cert.fingerprint(hashes.SHA256()).hex(),
                })
            certs = certs2
        except Exception as e:
            out['cert_error2'] = str(e)
        out['certificates'] = certs
        # dex summary
        dex_info = []
        for i, dd in enumerate(d):
            dex_info.append({
                'name': dd.get_name() if hasattr(dd,'get_name') else f'classes{i+1 if i else ""}.dex',
                'classes_count': len(list(dd.get_classes())),
                'methods_count': len(list(dd.get_methods())),
                'fields_count': len(list(dd.get_fields())),
                'strings_count': len(list(dd.get_strings())),
            })
        out['dex'] = dex_info
        # strings sample and sensitive patterns
        all_strings = []
        for s in dx.get_strings():
            val = s.get_value()
            if val:
                all_strings.append(val)
        out['strings_total'] = len(all_strings)
        # sensitive patterns
        import re
        patterns = {
            'urls': re.compile(r'https?://[^\s"\'<>]+'),
            'ips': re.compile(r'\b(?:\d{1,3}\.){3}\d{1,3}\b'),
            'api_keys_like': re.compile(r'(?i)(api[_-]?key|secret|token|password|bearer|authorization)["\']?\s*[:=]\s*["\'][^"\']{6,}'),
            'firebase': re.compile(r'firebaseio\.com|googleapis\.com|appspot\.com'),
            'crypto': re.compile(r'(?i)(AES|RSA|HMAC|SHA-?256|CBC|GCM|keystore|cipher)'),
            'native_libs': re.compile(r'System\.loadLibrary\(|dlopen\('),
        }
        findings = {k: [] for k in patterns}
        for s in all_strings:
            for k, pat in patterns.items():
                if pat.search(s):
                    if len(findings[k]) < 200:
                        findings[k].append(s[:300])
        out['sensitive_string_findings'] = findings
        # top packages
        pkg_counter = collections.Counter()
        for cls in dx.get_classes():
            name = cls.name if hasattr(cls,'name') else str(cls)
            name = name.lstrip('L').replace('/','.').rstrip(';')
            parts = name.split('.')
            if len(parts) >= 3:
                pkg_counter['.'.join(parts[:3])] += 1
            elif len(parts) >= 2:
                pkg_counter['.'.join(parts[:2])] += 1
        out['top_packages'] = pkg_counter.most_common(100)
        # class list (names only) - can be large
        class_names = []
        for cls in dx.get_classes():
            name = cls.name if hasattr(cls,'name') else str(cls)
            class_names.append(name)
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
    all_reports = {}
    for path, label in apks:
        print('ANALYZING', label, flush=True)
        r = analyze_with_androguard(path, label)
        all_reports[label] = r
        with open(os.path.join(REPORTS, f'androguard_{label}.json'), 'w') as f:
            json.dump(r, f, indent=2, ensure_ascii=False)
        print('DONE', label, 'classes', r.get('class_count'), 'err', r.get('error'), flush=True)
    # summary
    summary = {k: {kk: vv for kk,vv in v.items() if kk != 'class_names'} for k,v in all_reports.items()}
    with open(os.path.join(REPORTS, 'androguard_summary.json'), 'w') as f:
        json.dump(summary, f, indent=2, ensure_ascii=False)
    print('SUMMARY_WRITTEN', flush=True)

if __name__ == '__main__':
    main()
