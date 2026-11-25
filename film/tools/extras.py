#!/usr/bin/env python3
"""
Deep-dive extractors that complement collect.py:
  * network security config (cleartext / trust anchors / pin-set)
  * exported component inventory with intent filters
  * first-party class inventory (vn.com.vng.zalopay.*) with member counts
  * native library JNI export table + per-library purpose heuristics
  * cryptographic provider census inside the DEX set
  * resource-level telemetry / SDK attribution
"""
from __future__ import annotations

import collections
import json
import logging
import os
import re
import sys
from pathlib import Path

logging.disable(logging.CRITICAL)
OUT = Path(sys.argv[2] if len(sys.argv) > 2 else "film/data")
BASE = Path(os.environ.get("ZP_BASE", "work/unpacked/base"))
OUT.mkdir(parents=True, exist_ok=True)


def dump(name, obj):
    p = OUT / name
    p.write_text(json.dumps(obj, indent=1, ensure_ascii=False), encoding="utf-8")
    print(f"  wrote {p}")


# ---------------------------------------------------------------- NSC
def network_security_config():
    from androguard.core.axml import AXMLPrinter
    p = BASE / "res/xml/network_security_config.xml"
    xml = AXMLPrinter(p.read_bytes()).get_xml().decode("utf-8", "replace")
    domains = re.findall(r"<domain[^>]*>([^<]+)</domain>", xml)
    cleartext = re.findall(r'cleartextTrafficPermitted="(\w+)"', xml)
    anchors = re.findall(r'<certificates src="(\w+)"', xml)
    has_pin = "pin-set" in xml
    # also look for pinning in code strings
    d = {"raw_xml": xml, "domains": domains,
         "cleartext_flags": cleartext, "trust_anchors": anchors,
         "has_pin_set": has_pin,
         "cleartext_global": 'base-config cleartextTrafficPermitted="true"' in xml,
         "user_ca_trusted": "user" in anchors,
         "finding": None}
    d["finding"] = (
        "base-config permits cleartext application-wide; debug-overrides trusts "
        "user-installed CAs; no <pin-set> declared (pinning is therefore enforced "
        "only in code / native layers)"
        if d["cleartext_global"] and d["user_ca_trusted"] else "standard"
    )
    dump("09_nsc.json", d)


# ---------------------------------------------------- exported components
def exported_components():
    from androguard.core.apk import APK
    a = APK(sys.argv[1])
    ns = "{http://schemas.android.com/apk/res/android}"
    raw = a.get_android_manifest_xml()
    out = []
    for tag in ("activity", "activity-alias", "service", "receiver", "provider"):
        for el in raw.iter(tag):
            if el.get(ns + "exported") != "true":
                continue
            filters = []
            for f in el.iter("action"):
                v = f.get(ns + "name")
                if v:
                    filters.append(v)
            for f in el.iter("data"):
                sch = f.get(ns + "scheme")
                if sch:
                    filters.append(f"scheme:{sch}")
            out.append({"type": tag, "name": el.get(ns + "name"),
                        "permission": el.get(ns + "permission"),
                        "actions": sorted(set(filters)),
                        "priority": el.get(ns + "priority")})
    dump("10_exported.json", {
        "count": len(out),
        "by_type": dict(collections.Counter(o["type"] for o in out)),
        "components": out,
        "unprotected": [o["name"] for o in out if not o["permission"]],
    })


# --------------------------------------------------------- first party
def first_party():
    from androguard.core.dex import DEX
    rows = []
    for f in sorted(BASE.glob("classes*.dex")):
        d = DEX(f.read_bytes())
        for c in d.get_classes():
            n = c.get_name()[1:-1].replace("/", ".")
            if n.startswith("vn.com.vng.zalopay") or n.startswith("vn.zalopay"):
                rows.append({"class": n, "dex": f.name,
                             "methods": len(c.get_methods()),
                             "fields": len(c.get_fields()),
                             "super": c.get_superclassname()[1:-1].replace("/", "."),
                             "ifaces": [i[1:-1].replace("/", ".") for i in c.get_interfaces()]})
    dom = collections.Counter()
    for r in rows:
        parts = r["class"].split(".")
        dom[parts[3] if len(parts) > 4 else ".".join(parts[:4])] += 1
    methods = sum(r["methods"] for r in rows)
    dump("11_firstparty_map.json", {
        "count": len(rows), "methods": methods,
        "domains": dict(dom.most_common(80)),
        "classes": sorted(rows, key=lambda r: -r["methods"])[:600],
        "packages": sorted({".".join(r["class"].split(".")[:5]) for r in rows}),
    })


# --------------------------------------------------------------- native
PURPOSE = {
    "libtaibmcf.so": "Anti-fraud / runtime integrity engine (12.6 MB, zip inflate + mprotect + AAsset)",
    "libfgcpkjvqe.so": "Obfuscated vendor security module (JNI_OnLoad only, high-entropy .rodata)",
    "libljsfrcwmw.so": "Obfuscated vendor security module (name-mangled)",
    "liblytcuhe.so": "Obfuscated vendor security module (twin of rcmefqonnx)",
    "librcmefqonnx.so": "Obfuscated vendor security module (twin of lytcuhe)",
    "libohxbee.so": "TLS/OkHttp interceptor shim for trueID face-auth SDK",
    "liba97d.so": "Obfuscated JNI bridge (mprotect, M FNJNIzD symbol)",
    "libsecurity_lite.so": "Alipay SecurityGuardLite: AES encrypt/decrypt + request signing (2048-bit bignum)",
    "libsqlcipher.so": "SQLCipher 4.x native page cipher for the local wallet database",
    "libVFaceLib.so": "Face liveness / anti-spoof inference",
    "libpicodet_ncnn.so": "PicoDet NCNN object detection (card / document edge detection)",
    "libbarhopper_v3.so": "Google ML Kit barcode scanning (PDF417, QR, Code128)",
    "libface_detector_v2_jni.so": "ML Kit face detection",
    "libmlkit_google_ocr_pipeline.so": "ML Kit on-device OCR pipeline",
    "libmlkitcommonpipeline.so": "ML Kit shared inference pipeline",
    "librive-android.so": "Rive animation runtime (UI motion graphics)",
    "libtrueidlib.so": "trueID eKYC core",
    "libc++_shared.so": "LLVM libc++ runtime",
}


def native_deep():
    base = json.loads((OUT / "04_native.json").read_text())
    for lib in base["libs"]:
        lib["purpose"] = PURPOSE.get(lib["name"], "unknown")
    # anti-tamper signal census across native code
    sigs = ["__obfuscator_version__", "mprotect", "ptrace", "frida", "urandom",
            "AAssetManager_open", "inflateInit2_", "getxattr", "sched_setaffinity",
            "__system_property_get", "pthread_create", "dl_iterate_phdr", "syscall"]
    census = {}
    for lib in base["libs"]:
        p = BASE / "lib/armeabi-v7a" / lib["name"]
        if not p.exists():
            continue
        blob = p.read_bytes()
        census[lib["name"]] = {s: (s.encode() in blob) for s in sigs}
    dump("12_native_deep.json", {"libs": base["libs"], "signal_census": census,
                                 "signals": sigs})


# ------------------------------------------------------- crypto census
def crypto_census():
    from androguard.core.dex import DEX
    prov = collections.Counter()
    api = collections.Counter()
    for f in sorted(BASE.glob("classes*.dex")):
        d = DEX(f.read_bytes())
        for c in d.get_classes():
            n = c.get_name()[1:-1].replace("/", ".")
            if n.startswith("java.security.") or n.startswith("javax.crypto."):
                prov[n] += 1
            if n.startswith("android.security.keystore."):
                prov[n] += 1
            for m in c.get_methods():
                for tag in ("Ljavax/crypto/Cipher;", "Ljava/security/MessageDigest;",
                            "Ljavax/crypto/Mac;", "Ljava/security/KeyStore;",
                            "Landroid/security/keystore/KeyGenParameterSpec$Builder;",
                            "Landroidx/security/crypto/", "Lnet/sqlcipher/"):
                    if tag in str(m.get_descriptor()) or tag in str(m.get_class_name()):
                        api[tag] += 1
    dump("13_crypto.json", {"jca_classes": dict(prov.most_common(40)),
                            "call_sites": dict(api.most_common(30))})


def main():
    print("[a] nsc"); network_security_config()
    print("[b] exported"); exported_components()
    print("[c] first party"); first_party()
    print("[d] native deep"); native_deep()
    print("[e] crypto"); crypto_census()


if __name__ == "__main__":
    main()
