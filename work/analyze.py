#!/usr/bin/env python3
"""Comprehensive static reverse-engineering analysis of Zalopay 11.17.0 XAPK.
Uses androguard + manual AXML/DEX/native parsing. No dynamic execution."""
import os, re, json, hashlib, struct, subprocess, collections, zipfile
from pathlib import Path

ROOT = Path("work/apk")
BASE = ROOT/"base"
OUT = Path("research")
OUT.mkdir(parents=True, exist_ok=True)

def sha256(p):
    h=hashlib.sha256()
    with open(p,'rb') as f:
        for c in iter(lambda: f.read(1<<20), b''): h.update(c)
    return h.hexdigest()

def run(cmd):
    try: return subprocess.run(cmd, capture_output=True, text=True, timeout=60).stdout
    except Exception as e: return f"ERR {e}"

# --- File inventory ---
inventory=[]
for apk in sorted((ROOT/"xapk").glob("*.apk")):
    with zipfile.ZipFile(apk) as z:
        for info in z.infolist():
            inventory.append({"apk":apk.name,"name":info.filename,"size":info.file_size,"compress_size":info.compress_size})
for p in sorted(BASE.rglob("*")):
    if p.is_file():
        inventory.append({"apk":"base-extracted","name":str(p.relative_to(BASE)),"size":p.stat().st_size})

# --- Androguard APK analysis ---
from androguard.core.apk import APK
from androguard.core.dex import DEX
apk_path = str(ROOT/"xapk/vn.com.vng.zalopay.apk")
a = APK(apk_path)
print("package:", a.get_package(), "version:", a.get_androidversion_name(), a.get_androidversion_code())
print("min/target sdk:", a.get_min_sdk_version(), a.get_target_sdk_version())
print("permissions:", len(a.get_permissions()))
print("activities:", len(a.get_activities()), "services:", len(a.get_services()), "receivers:", len(a.get_receivers()), "providers:", len(a.get_providers()))
print("libraries:", a.get_libraries())
print("application label:", a.get_app_name())
print("main activity:", a.get_main_activity())
print("cert:", a.get_certificates())

# DEX analysis
dex_files = sorted(BASE.glob("classes*.dex"))
dex_stats=[]
all_strings=set()
class_names=set()
method_count=0
for df in dex_files:
    data = df.read_bytes()
    d = DEX(data)
    classes = d.get_classes()
    methods = d.get_methods()
    strings = d.get_strings()
    dex_stats.append({"file":df.name,"size":len(data),"classes":len(classes),"methods":len(methods),"strings":len(strings)})
    method_count += len(methods)
    for c in classes: class_names.add(c.get_name())
    for s in strings:
        try: all_strings.add(s)
        except: pass
    print(df.name, len(data), "classes", len(classes), "methods", len(methods), "strings", len(strings))

# Package histogram (top-level 3 segments)
pkg_counter=collections.Counter()
for cn in class_names:
    parts=cn.strip('L;').split('/')
    pkg_counter['/'.join(parts[:3])]+=1
top_packages=pkg_counter.most_common(40)

# Interesting strings
def grep_strings(pattern, limit=60):
    rx=re.compile(pattern, re.I)
    hits=sorted([s for s in all_strings if rx.search(s)])
    return hits[:limit], len(hits)

urls, n_urls = grep_strings(r'https?://[^\s"\'<>]+')
domains, n_dom = grep_strings(r'\b[a-z0-9-]+\.(com|vn|net|org|io)\b')
apis, n_api = grep_strings(r'(api|gateway|endpoint|/v\d+/|grpc|graphql)')
crypto, n_crypto = grep_strings(r'(AES|RSA|SHA-?256|HmacSHA|encrypt|decrypt|Cipher|KeyStore|certificate|pinning|SSL|TLS|root|frida|xposed|magisk|emulator|debug)')
firebase, n_fb = grep_strings(r'(firebase|fcm|crashlytics|analytics|appsflyer|facebook|adjust)')
zalopay_strs, n_zp = grep_strings(r'zalopay|vng|zalo')
payment, n_pay = grep_strings(r'(payment|transfer|wallet|bank|card|qr|vietqr|napas|interbank|transaction)')
biometric, n_bio = grep_strings(r'(biometric|fingerprint|face|FaceID|trueid|vface|liveness|ekyc|ocr|mlkit)')
obfuscated_libs = [p.name for p in (ROOT/"split/lib/armeabi-v7a").glob("*.so")]

# Native libs
native=[]
for so in sorted((ROOT/"split/lib/armeabi-v7a").glob("*.so")):
    dyn = run(["readelf","-d",str(so)])
    needed = re.findall(r'NEEDED.*\[(.+?)\]', dyn)
    exported = run(["nm","-D","--defined-only",str(so)])[:2000]
    native.append({"file":so.name,"size":so.stat().st_size,"sha256":sha256(so)[:16],"needed":needed,"exports_sample":exported[:800]})

# Assets
assets=[]
for p in sorted((BASE/"assets").rglob("*")):
    if p.is_file():
        assets.append({"path":str(p.relative_to(BASE/"assets")),"size":p.stat().st_size})
asset_by_ext=collections.Counter(Path(x["path"]).suffix.lower() for x in assets)
asset_by_top=collections.Counter(Path(x["path"]).parts[0] for x in assets)

# Resources
res_files=list((BASE/"res").rglob("*"))
res_by_top=collections.Counter(p.relative_to(BASE/"res").parts[0] for p in res_files if p.is_file())

# Manifest raw strings (binary AXML - extract printable)
manifest_bytes=(BASE/"AndroidManifest.xml").read_bytes()
manifest_strings=re.findall(rb'[\x20-\x7e]{4,}', manifest_bytes)
manifest_text=[s.decode() for s in manifest_strings]

# Config JSONs
configs={}
for name in ["assets/config/zalopay_config.json","assets/config/miniapp_config.json","assets/home_data.json","assets/bundle.json","getbanklist_zpm_success.json"]:
    p=BASE/name
    if p.exists():
        try:
            j=json.loads(p.read_text())
            configs[name]={"keys":list(j.keys())[:20],"size":p.stat().st_size}
            if "api" in str(j)[:5000]:
                pass
        except Exception as e:
            configs[name]={"error":str(e)}

# zalopay_config deep dive
zp_cfg=json.loads((BASE/"assets/config/zalopay_config.json").read_text())
api_names=zp_cfg.get("api",{}).get("api_names",[])
api_info=zp_cfg.get("api",{}).get("api_info",{})

data={
 "app":{"package":a.get_package(),"version_name":a.get_androidversion_name(),"version_code":a.get_androidversion_code(),
        "min_sdk":a.get_min_sdk_version(),"target_sdk":a.get_target_sdk_version(),"label":a.get_app_name(),
        "main_activity":a.get_main_activity(),"permissions":sorted(a.get_permissions()),
        "activities":sorted(a.get_activities()),"services":sorted(a.get_services()),
        "receivers":sorted(a.get_receivers()),"providers":sorted(a.get_providers()),
        "libraries":a.get_libraries(),"cert_sha256":[hashlib.sha256(c.dump()).hexdigest() if hasattr(c,"dump") else str(c)[:64] for c in a.get_certificates()]},
 "xapk_manifest":json.loads((ROOT/"xapk/manifest.json").read_text()),
 "dex":dex_stats,"total_classes":len(class_names),"total_methods":method_count,"total_unique_strings":len(all_strings),
 "top_packages":top_packages,
 "strings":{"urls":urls,"n_urls":n_urls,"domains":domains,"n_domains":n_dom,"apis":apis,"n_apis":n_api,
             "crypto":crypto,"n_crypto":n_crypto,"firebase":firebase,"n_firebase":n_fb,
             "zalopay":zalopay_strs,"n_zalopay":n_zp,"payment":payment,"n_payment":n_pay,"biometric":biometric,"n_biometric":n_bio},
 "native":native,"assets":assets,"asset_by_ext":dict(asset_by_ext),"asset_by_top":dict(asset_by_top),
 "res_by_top":dict(res_by_top),"res_total":len([p for p in res_files if p.is_file()]),
 "configs":configs,"api_names":api_names,"api_info_keys":list(api_info.keys())[:80],
 "zp_general":zp_cfg.get("general",{}),"zp_zpc":zp_cfg.get("zpc",{}),
 "manifest_strings_sample":manifest_text[:120],
 "inventory_count":len(inventory),
 "hashes":{"xapk_zip":sha256("Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"),
           "base_apk":sha256(ROOT/"xapk/vn.com.vng.zalopay.apk")}
}
(OUT/"data.json").write_text(json.dumps(data,ensure_ascii=False,indent=2))
print("WROTE", OUT/"data.json", "classes", len(class_names), "strings", len(all_strings))
print("top packages:", top_packages[:15])
print("urls:", n_urls, "crypto hits:", n_crypto, "payment hits:", n_pay)
print("api_names:", len(api_names))
