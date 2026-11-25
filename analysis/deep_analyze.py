#!/usr/bin/env python3
import json, os, re, hashlib, collections, datetime, sys, logging
from pathlib import Path
logging.basicConfig(level=logging.WARNING)
try:
    from loguru import logger
    logger.remove()
    logger.add(sys.stderr, level="WARNING")
except Exception:
    pass
from androguard.core.apk import APK

WORK = Path("/home/user/-/work")
OUT = Path("/home/user/-/analysis")
base_apk = WORK/"xapk/vn.com.vng.zalopay.apk"
print("Loading APK...", flush=True)
a = APK(str(base_apk))
print("Loaded APK", flush=True)

report = {}
report["package"] = a.get_package()
report["app_name"] = a.get_app_name()
report["version_name"] = a.get_androidversion_name()
report["version_code"] = a.get_androidversion_code()
report["min_sdk"] = a.get_min_sdk_version()
report["target_sdk"] = a.get_target_sdk_version()
report["max_sdk"] = a.get_max_sdk_version()
report["main_activity"] = a.get_main_activity()
report["permissions"] = sorted(a.get_permissions())
report["activities"] = sorted(a.get_activities())
report["services"] = sorted(a.get_services())
report["receivers"] = sorted(a.get_receivers())
report["providers"] = sorted(a.get_providers())
report["libraries"] = list(a.get_libraries())
report["features"] = list(a.get_features())
report["files_count"] = len(a.get_files())

# certs
try:
    from cryptography.hazmat.primitives import hashes
    certs=[]
    for cert in a.get_certificates():
        certs.append({
            "subject": cert.subject.rfc4514_string(),
            "issuer": cert.issuer.rfc4514_string(),
            "serial": str(cert.serial_number),
            "not_before": str(cert.not_valid_before_utc),
            "not_after": str(cert.not_valid_after_utc),
            "sha256_fingerprint": cert.fingerprint(hashes.SHA256()).hex(),
            "signature_algo": cert.signature_algorithm_oid._name,
        })
    report["certificates"] = certs
except Exception as e:
    report["certificates"] = [{"error": str(e)}]

# native libs
report["native_libs"] = sorted([f for f in a.get_files() if f.endswith('.so')])

# DEX - lightweight
url_re = re.compile(r'https?://[^\s"\'<>\\)]+')
ip_re = re.compile(r'\b(?:(?:25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(?:25[0-5]|2[0-4]\d|[01]?\d\d?)\b')
secret_re = re.compile(r'(api[_-]?key|secret|token|password|private[_-]?key|bearer|authorization|firebase|supabase)', re.I)
urls=set(); ips=set(); secret_hints=[]
pkg_counts=collections.Counter()
class_count=0; method_count=0; field_count=0; string_count=0

for dex_name in sorted([f for f in a.get_files() if f.endswith('.dex')]):
    print(f"DEX {dex_name}", flush=True)
    try:
        dex_bytes = a.get_file(dex_name)
        from androguard.core.dex import DEX
        d = DEX(dex_bytes)
        for s in d.get_strings():
            string_count += 1
            if len(s) > 600: continue
            for m in url_re.findall(s):
                urls.add(m[:200])
            for m in ip_re.findall(s):
                if not m.startswith('0.') and not m.startswith('127.'): ips.add(m)
            if secret_re.search(s) and 8 < len(s) < 200:
                secret_hints.append(s[:200])
        for cls in d.get_classes():
            class_count += 1
            name = cls.get_name()
            if name.startswith('L') and name.endswith(';'):
                name = name[1:-1]
            pkg = '/'.join(name.split('/')[:3]) if '/' in name else name
            pkg_counts[pkg.replace('/','.')] += 1
            method_count += len(list(cls.get_methods()))
            field_count += len(list(cls.get_fields()))
        del d, dex_bytes
    except Exception as e:
        print(f"ERR {dex_name}: {e}", flush=True)

report["dex_stats"] = {"dex_files": len([f for f in a.get_files() if f.endswith('.dex')]), "classes": class_count, "methods": method_count, "fields": field_count, "strings": string_count}
report["top_packages"] = pkg_counts.most_common(80)
report["urls"] = sorted(urls)[:400]
report["ips"] = sorted(ips)[:200]
report["secret_string_hints_count"] = len(secret_hints)
report["secret_string_hints_sample"] = secret_hints[:120]

(OUT/"deep_analysis.json").write_text(json.dumps(report, indent=2, ensure_ascii=False))
print("ok", report["dex_stats"], flush=True)
