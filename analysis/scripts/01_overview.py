import json, os, hashlib, zipfile, collections, sys
from androguard.core.apk import APK
from androguard.core.axml import AXMLPrinter

BASE="work/xapk/split"
out={}
apk=APK(os.path.join(BASE,"vn.com.vng.zalopay.apk"))
print("package:", apk.get_package())
print("version:", apk.get_androidversion_name(), apk.get_androidversion_code())
print("min/target sdk:", apk.get_min_sdk_version(), apk.get_target_sdk_version())
print("main activity:", apk.get_main_activity())
print("activities:", len(apk.get_activities()), "services:", len(apk.get_services()), "receivers:", len(apk.get_receivers()), "providers:", len(apk.get_providers()))
certs=apk.get_certificates()
for c in certs:
    print("CERT subject:", c.subject.rfc4514_string() if hasattr(c.subject,'rfc4514_string') else c.subject)
    print("CERT issuer :", c.issuer.rfc4514_string() if hasattr(c.issuer,'rfc4514_string') else c.issuer)
    print("CERT serial :", hex(c.serial_number) if c.serial_number else None)
    print("CERT notBefore/After:", c.not_valid_before, c.not_valid_after)
    print("CERT SHA256 fp:", c.sha256_fingerprint if hasattr(c,'sha256_fingerprint') else hashlib.sha256(c.dump()).hexdigest())
print("signed v1:", apk.is_signed_v1() if hasattr(apk,'is_signed_v1') else '?', "v2:", apk.is_signed_v2() if hasattr(apk,'is_signed_v2') else '?', "v3:", apk.is_signed_v3() if hasattr(apk,'is_signed_v3') else '?')
print("num permissions:", len(apk.get_permissions()))
print("num declared permissions:", len(apk.get_declared_permissions()))
