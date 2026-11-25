#!/usr/bin/env python3
"""Stage 1: corpus inventory, hashes, split-APK manifests and signing metadata."""
import hashlib, json, os, zipfile, collections, sys, logging
logging.disable(logging.CRITICAL)
from androguard.core.apk import APK

OUT = '/tmp/zp/reports'
os.makedirs(OUT, exist_ok=True)
ROOT = '/tmp/zp'


def sha256(path):
    h = hashlib.sha256()
    with open(path, 'rb') as f:
        for chunk in iter(lambda: f.read(1 << 20), b''):
            h.update(chunk)
    return h.hexdigest()


def inventory():
    result = []
    paths = [os.path.join(ROOT, f) for f in os.listdir(ROOT) if f.endswith(('.xapk', '.apk', '.png', '.json'))]
    paths.append('/home/user/-/Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip')
    for path in sorted(paths):
        st = os.stat(path)
        result.append({'file': os.path.basename(path), 'bytes': st.st_size, 'sha256': sha256(path)})
    # nested xapk entries
    xapk = [p for p in result if p['file'].endswith('.xapk')][0]
    with zipfile.ZipFile(os.path.join(ROOT, xapk['file'])) as z:
        xapk['entries'] = [{'file': i.filename, 'bytes': i.file_size, 'compressed': i.compress_size,
                            'crc32': format(i.CRC, '08x'),
                            'sha256': hashlib.sha256(z.read(i.filename)).hexdigest(),
                            'method': i.compress_type} for i in z.infolist()]
    return result


def apk_report(path):
    name = os.path.basename(path)
    apk = APK(path)
    rep = {'file': name, 'valid': apk.is_valid_APK(), 'package': apk.get_package(),
           'version_name': apk.get_androidversion_name(), 'version_code': apk.get_androidversion_code(),
           'min_sdk': apk.get_min_sdk_version(), 'target_sdk': apk.get_effective_target_sdk_version(),
           'signed': {'v1': apk.is_signed_v1(), 'v2': apk.is_signed_v2(), 'v3': apk.is_signed_v3(),
                      'v31': apk.is_signed_v31()},
           'multidex': apk.is_multidex(), 'libraries': apk.get_libraries(),
           'permissions': apk.get_permissions(), 'requested_aosp': apk.get_requested_aosp_permissions(),
           'requested_third_party': apk.get_requested_third_party_permissions(),
           'declared': sorted(apk.get_declared_permissions()),
           'components': {'activities': apk.get_activities(), 'services': apk.get_services(),
                          'receivers': apk.get_receivers(), 'providers': apk.get_providers(),
                          'activity_aliases': apk.get_activity_aliases(),
                          'main_activity': apk.get_main_activity()},
           'features': apk.get_features()}
    try:
        xml = apk.get_android_manifest_axml().get_xml()
        rep['manifest_xml'] = xml.decode('utf-8', 'replace') if isinstance(xml, bytes) else str(xml)
    except Exception as e:
        rep['manifest_error'] = repr(e)
    for scheme, fn in [('v1', apk.get_certificates), ('v2', apk.get_certificates_v2),
                       ('v3', apk.get_certificates_v3), ('v31', apk.get_certificates_v31)]:
        try:
            certs = fn()
            rep['signed'][scheme + '_certs'] = []
            for c in certs or []:
                rep['signed'][scheme + '_certs'].append({
                    'subject': c.subject.human_friendly if hasattr(c.subject, 'human_friendly') else str(c.subject),
                    'issuer': str(c.issuer), 'serial': str(c.serial_number),
                    'not_before': str(c.not_valid_before), 'not_after': str(c.not_valid_after),
                    'sha256': c.sha256.hex() if hasattr(c, 'sha256') else hashlib.sha256(c.to_der()).hexdigest(),
                    'sha1': c.sha1.hex() if hasattr(c, 'sha1') else '',
                })
        except Exception as e:
            rep['signed'][scheme + '_error'] = repr(e)
    return rep


if __name__ == '__main__':
    inv = inventory()
    json.dump(inv, open(os.path.join(OUT, 'inventory.json'), 'w'), indent=2, ensure_ascii=False)
    reports = []
    for apk in sorted(os.listdir(os.path.join(ROOT, 'out'))):
        if apk.endswith('.apk'):
            print('APK', apk, flush=True)
            reports.append(apk_report(os.path.join(ROOT, 'out', apk)))
    json.dump(reports, open(os.path.join(OUT, 'apk-metadata.json'), 'w'), indent=2, ensure_ascii=False)
    print('done', len(reports))
