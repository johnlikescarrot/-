#!/usr/bin/env python3
import json, os, sys, hashlib, zipfile, datetime, traceback
from pathlib import Path
from loguru import logger
logger.remove(); logger.add(sys.stderr, level="WARNING")

BASE = Path("/home/user/-")
APK = BASE / "analysis/extracted/xapk/vn.com.vng.zalopay.apk"
OUT = BASE / "analysis/reports"
OUT.mkdir(parents=True, exist_ok=True)

def sha256(path):
    h = hashlib.sha256()
    with open(path,'rb') as f:
        for c in iter(lambda: f.read(1024*1024), b''): h.update(c)
    return h.hexdigest()

report = {"generated_at": datetime.datetime.utcnow().isoformat()+"Z", "apk": str(APK.name)}
st = APK.stat()
report["file"] = {"size_bytes": st.st_size, "sha256": sha256(APK)}

with zipfile.ZipFile(APK) as z:
    infos = z.infolist()
    report["zip"] = {
        "entry_count": len(infos),
        "total_uncompressed": sum(i.file_size for i in infos),
    }
    cats = {}
    for i in infos:
        ext = os.path.splitext(i.filename)[1].lower() or "<none>"
        cats[ext] = cats.get(ext,0)+1
    report["zip"]["extension_counts"] = dict(sorted(cats.items(), key=lambda x:-x[1])[:60])
    native=[]
    for i in infos:
        if i.filename.startswith('lib/') and i.filename.endswith('.so'):
            native.append({"name":i.filename,"size":i.file_size, "compressed":i.compress_size})
    report["native_libs"] = native
    report["zip"]["entries_sample"] = [{"name":i.filename,"size":i.file_size,"compressed":i.compress_size} for i in infos[:300]]

try:
    from androguard.core.apk import APK as A
    a = A(str(APK))
    report["app"] = {
        "package": a.get_package(),
        "app_name": a.get_app_name(),
        "version_name": a.get_androidversion_name(),
        "version_code": a.get_androidversion_code(),
        "min_sdk": a.get_min_sdk_version(),
        "target_sdk": a.get_target_sdk_version(),
        "max_sdk": a.get_max_sdk_version(),
        "main_activity": a.get_main_activity(),
        "is_signed": a.is_signed(),
        "is_signed_v1": a.is_signed_v1(),
        "is_signed_v2": a.is_signed_v2(),
        "is_signed_v3": a.is_signed_v3(),
    }
    report["permissions"] = sorted(a.get_permissions() or [])
    report["activities"] = sorted(a.get_activities() or [])
    report["services"] = sorted(a.get_services() or [])
    report["receivers"] = sorted(a.get_receivers() or [])
    report["providers"] = sorted(a.get_providers() or [])
    report["libraries"] = sorted(a.get_libraries() or [])
    certs=[]
    try:
        for c in a.get_certificates():
            certs.append({
                "subject": str(c.subject.rfc4514_string()),
                "issuer": str(c.issuer.rfc4514_string()),
                "serial": str(c.serial_number),
                "not_before": str(c.not_valid_before_utc),
                "not_after": str(c.not_valid_after_utc),
                "sha256": c.fingerprint(hashes=None) if False else None,
            })
            # compute fingerprints
            from cryptography.hazmat.primitives import hashes
            for algo,name in [(hashes.SHA256(),'sha256'),(hashes.SHA1(),'sha1'),(hashes.MD5(),'md5')]:
                certs[-1][name]=c.fingerprint(algo).hex()
    except Exception as e:
        certs.append({"error": str(e)})
    report["certificates"] = certs
    # DEX: class names only, no analysis graph
    classes=[]
    dex_names=[]
    strings_sample=[]
    for dex_name in a.get_dex_names():
        dex_names.append(dex_name)
        dex_bytes = a.get_file(dex_name)
        from androguard.core.dex import DEX
        d = DEX(dex_bytes)
        for cls in d.get_classes():
            name = cls.get_name()
            classes.append(name)
        if len(strings_sample) < 500:
            for s in d.get_strings():
                if isinstance(s,str) and 6 <= len(s) <= 180:
                    strings_sample.append(s)
                if len(strings_sample)>=500: break
        del d, dex_bytes
    report["dex"] = {"names": dex_names, "count": len(dex_names)}
    report["classes"] = {"count": len(classes), "sample": classes[:300]}
    pkgs={}
    for c in classes:
        p = c[1:c.rfind('/')].replace('/','.') if c.startswith('L') and '/' in c else c
        top = '.'.join(p.split('.')[:3])
        pkgs[top]=pkgs.get(top,0)+1
    report["packages_top"] = dict(sorted(pkgs.items(), key=lambda x:-x[1])[:100])
    report["strings_sample"] = strings_sample[:300]
    report["androguard"] = {"ok": True, "mode": "lightweight"}
except Exception as e:
    report["androguard"] = {"ok": False, "error": str(e), "trace": traceback.format_exc()[-4000:]}

(OUT/'apk_analysis.json').write_text(json.dumps(report, ensure_ascii=False, indent=2))
summary = dict(report)
if 'classes' in summary and 'all' in summary['classes']:
    del summary['classes']['all']
(OUT/'apk_summary.json').write_text(json.dumps(summary, ensure_ascii=False, indent=2))
print('ok', report.get('androguard',{}).get('ok'))
print('classes', report.get('classes',{}).get('count'))
print('pkg', report.get('app',{}).get('package'))
