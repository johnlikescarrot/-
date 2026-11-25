#!/usr/bin/env python3
"""Deep static analysis across ALL 20 DEX files, Maven .properties, Kotlin metadata, and ART baseline profile."""

import json
import re
import struct
import zipfile
from collections import Counter, defaultdict
from pathlib import Path

from loguru import logger
logger.disable("androguard")
from androguard.core.dex import DEX

REPO_ROOT = Path.cwd()
BASE_DIR = Path("/tmp/zalopay_corpus/apks/vn.com.vng.zalopay")
BASE_APK = Path("/tmp/zalopay_corpus/xapk/vn.com.vng.zalopay.apk")
OUT_DIR = REPO_ROOT / "data"

ACC_NATIVE = 0x0100
ACC_PUBLIC = 0x0001
ACC_INTERFACE = 0x0200
ACC_ABSTRACT = 0x0400


def parse_dex_header(raw: bytes) -> dict:
    magic = raw[:8]
    ver = magic[4:7].decode("ascii", errors="replace")
    checksum, sig, file_size, header_size, endian_tag, link_size, link_off, map_off, \
        string_ids_size, string_ids_off, type_ids_size, type_ids_off, \
        proto_ids_size, proto_ids_off, field_ids_size, field_ids_off, \
        method_ids_size, method_ids_off, class_defs_size, class_defs_off, \
        data_size, data_off = struct.unpack_from("<I20sIIIIIIIIIIIIIIIIIIII", raw, 8)
    return {
        "dex_version": ver,
        "file_size": file_size,
        "adler32": f"{checksum:08x}",
        "sha1": sig.hex(),
        "string_ids": string_ids_size,
        "type_ids": type_ids_size,
        "proto_ids": proto_ids_size,
        "field_ids": field_ids_size,
        "method_ids": method_ids_size,
        "class_defs": class_defs_size,
        "data_size": data_size,
    }


def is_obfuscated_ident(ident: str) -> bool:
    if not ident:
        return False
    # Typical R8/ProGuard single/two char names (excluding standard inner $1 etc.)
    base = ident.split("$")[0]
    if len(base) <= 2 and base.isalpha():
        return True
    if len(base) == 3 and base.islower() and base.isalpha() and base not in ("api", "app", "pay", "sdk", "map", "net", "log", "key", "aes", "rsa", "otp", "nfc", "qr", "pin", "kyc", "ocr", "url", "uri", "uid", "tag", "tab", "box", "row", "col", "env", "err", "msg", "req", "res", "ret", "ctx", "val", "ext", "lib", "jni", "sys", "dev", "web", "bio"):
        return True
    return False


def is_high_entropy_random_ident(pkg_or_cls: str) -> bool:
    # Detect randomized packer/shield identifiers like jwbmqnsll / xvndbxlk (consistently >=7 lowercase consonants/mixed without vowels or recognizable words)
    if not pkg_or_cls:
        return False
    parts = [p for p in pkg_or_cls.split(".") if p]
    if len(parts) == 1 and len(parts[0]) >= 6 and parts[0].islower() and parts[0].isalpha():
        vowels = sum(1 for c in parts[0] if c in "aeiou")
        if vowels / len(parts[0]) < 0.25:
            return True
    return False


def classify_package(pkg: str) -> str:
    if not pkg or pkg == "(default)":
        return "R8/Default-Root Package (Obfuscated)"
    if pkg.startswith("vn.com.vng.zalopay") or pkg.startswith("vn.zalopay.") or pkg.startswith("vn.vng.") or pkg.startswith("com.vng.") or pkg.startswith("com.zing."):
        return "First-Party ZaloPay / VNG / Zalo Ecosystem"
    if pkg.startswith("com.xq.cxzmoiwuegvp") or pkg.startswith("jwbmqnsll") or pkg.startswith("wmoi") or pkg.startswith("jipm"):
        return "App Shield / RASP & Native Integrity Stubs"
    if pkg.startswith("com.alipay") or pkg.startswith("com.alibaba") or pkg.startswith("com.iap") or pkg.startswith("com.ut.") or pkg.startswith("com.ta.") or pkg.startswith("anet.") or pkg.startswith("mtopsdk."):
        return "Alipay+ / Griver Mini-Program & IAP Container"
    if pkg.startswith("com.visa") or pkg.startswith("com.vnpay") or pkg.startswith("vn.napas") or pkg.startswith("com.mastercard") or pkg.startswith("com.cybersource") or pkg.startswith("com.android.billingclient"):
        return "Payment Rails & Tokenization (Visa CBP / VNPAY / Billing)"
    if pkg.startswith("vn.ai.trueid") or pkg.startswith("vn.ai.faceauth") or pkg.startswith("org.jmrtd") or pkg.startswith("com.google.mlkit") or pkg.startswith("com.google.android.gms.vision") or pkg.startswith("com.google.barhopper") or pkg.startswith("org.tensorflow") or pkg.startswith("com.tencent.ncnn") or pkg.startswith("org.opencv") or pkg.startswith("com.facetec"):
        return "On-Device eKYC, Vision, ML & NFC e-ID (TrueID / MLKit / JMRTD)"
    if pkg.startswith("androidx.") or pkg.startswith("android.support.") or pkg.startswith("com.google.android.material"):
        return "AndroidX / Jetpack / Compose / Media3"
    if pkg.startswith("com.google.firebase") or pkg.startswith("com.google.android.gms") or pkg.startswith("com.google.android.play") or pkg.startswith("com.google."):
        return "Google Play Services, Firebase & Google SDKs"
    if pkg.startswith("com.facebook.react") or pkg.startswith("com.facebook.hermes") or pkg.startswith("com.facebook.soloader") or pkg.startswith("com.facebook.yoga") or pkg.startswith("com.facebook.fresco") or pkg.startswith("com.facebook.imagepipeline"):
        return "React Native & Fresco Media Stack"
    if pkg.startswith("com.facebook.") or pkg.startswith("com.appsflyer") or pkg.startswith("com.adjust") or pkg.startswith("com.clevertap") or pkg.startswith("com.braze"):
        return "Analytics, Attribution & Ad Networks"
    if pkg.startswith("app.rive") or pkg.startswith("com.airbnb.lottie") or pkg.startswith("com.bumptech.glide") or pkg.startswith("com.github.bumptech.glide"):
        return "Vector Animation & Rendering (Rive / Lottie / Glide)"
    if pkg.startswith("org.bouncycastle") or pkg.startswith("net.sqlcipher") or pkg.startswith("net.zetetic") or pkg.startswith("com.nimbusds") or pkg.startswith("com.scottyab") or pkg.startswith("com.shield"):
        return "Cryptography, JOSE & Encrypted Storage (BouncyCastle / SQLCipher)"
    if pkg.startswith("kotlin") or pkg.startswith("kotlinx"):
        return "Kotlin Standard Library & Coroutines"
    if pkg.startswith("okhttp3") or pkg.startswith("okio") or pkg.startswith("retrofit2") or pkg.startswith("io.reactivex") or pkg.startswith("rx.") or pkg.startswith("com.squareup") or pkg.startswith("org.apache") or pkg.startswith("io.grpc") or pkg.startswith("com.fasterxml") or pkg.startswith("org.json"):
        return "Networking, Reactive Streams & Serialization"
    if len(pkg.split(".")[0]) <= 2:
        return "R8 Short-Identifier / Minified Package"
    if is_high_entropy_random_ident(pkg.split(".")[0]):
        return "App Shield / RASP & Native Integrity Stubs"
    return "Other Third-Party Libraries"


def dex_descriptor_to_java(desc: str) -> tuple[str, str]:
    # Lpkg/sub/Class; -> (pkg.sub, Class)
    s = desc
    if s.startswith("L") and s.endswith(";"):
        s = s[1:-1]
    parts = s.split("/")
    if len(parts) == 1:
        return "(default)", parts[0]
    return ".".join(parts[:-1]), parts[-1]


dex_paths = sorted(
    [p for p in BASE_DIR.glob("classes*.dex")],
    key=lambda p: (0 if p.name == "classes.dex" else int(re.search(r"\d+", p.name).group())),
)
dex_paths.append(BASE_DIR / "assets/audience_network.dex")

dex_summaries = []
package_stats = defaultdict(lambda: {"classes": 0, "methods": 0, "native_methods": 0, "obfuscated_classes": 0, "dex_files": set()})
prefix2_stats = defaultdict(lambda: {"classes": 0, "methods": 0, "native_methods": 0})
ecosystem_stats = defaultdict(lambda: {"classes": 0, "methods": 0, "native_methods": 0, "obfuscated_classes": 0})
native_declarations = []
entry_app_class_info = None

total_classes = 0
total_defined_methods = 0
total_defined_fields = 0
total_native_methods = 0
total_obfuscated_classes = 0

for dp in dex_paths:
    rel = str(dp.relative_to(BASE_DIR))
    raw = dp.read_bytes()
    hdr = parse_dex_header(raw)
    d = DEX(raw)
    classes = list(d.get_classes())

    cls_count = len(classes)
    meth_count = 0
    fld_count = 0
    nat_count = 0
    obf_cls_count = 0
    local_pkg_counts = Counter()

    for c in classes:
        cname_raw = c.get_name()
        pkg, cls_simple = dex_descriptor_to_java(cname_raw)
        is_obf = is_obfuscated_ident(cls_simple) or (pkg == "(default)") or (len(pkg.split(".")[0]) <= 2)
        if is_obf:
            obf_cls_count += 1

        methods = c.get_methods()
        fields = c.get_fields()
        m_len = len(methods)
        f_len = len(fields)
        meth_count += m_len
        fld_count += f_len

        c_nat = 0
        for m in methods:
            if m.get_access_flags() & ACC_NATIVE:
                c_nat += 1
                nat_count += 1
                native_declarations.append({
                    "dex": rel,
                    "class_descriptor": cname_raw,
                    "package": pkg,
                    "class_name": f"{pkg}.{cls_simple}" if pkg != "(default)" else cls_simple,
                    "method_name": m.get_name(),
                    "descriptor": m.get_descriptor(),
                    "access_flags": m.get_access_flags_string(),
                })

        if pkg == "jwbmqnsll" and cls_simple == "xvndbxlk":
            entry_app_class_info = {
                "dex": rel,
                "class_name": "jwbmqnsll.xvndbxlk",
                "superclass": c.get_superclassname(),
                "interfaces": c.get_interfaces(),
                "access_flags": c.get_access_flags_string(),
                "fields": [{"name": f.get_name(), "descriptor": f.get_descriptor(), "flags": f.get_access_flags_string()} for f in fields],
                "methods": [{"name": m.get_name(), "descriptor": m.get_descriptor(), "flags": m.get_access_flags_string()} for m in methods],
            }

        ps = package_stats[pkg]
        ps["classes"] += 1
        ps["methods"] += m_len
        ps["native_methods"] += c_nat
        if is_obf:
            ps["obfuscated_classes"] += 1
        ps["dex_files"].add(rel)

        p2 = ".".join(pkg.split(".")[:3]) if pkg != "(default)" else "(default)"
        p2s = prefix2_stats[p2]
        p2s["classes"] += 1
        p2s["methods"] += m_len
        p2s["native_methods"] += c_nat

        eco = classify_package(pkg)
        es = ecosystem_stats[eco]
        es["classes"] += 1
        es["methods"] += m_len
        es["native_methods"] += c_nat
        if is_obf:
            es["obfuscated_classes"] += 1

        local_pkg_counts[p2] += 1

    total_classes += cls_count
    total_defined_methods += meth_count
    total_defined_fields += fld_count
    total_native_methods += nat_count
    total_obfuscated_classes += obf_cls_count

    dex_summaries.append({
        "dex_file": rel,
        **hdr,
        "defined_classes": cls_count,
        "defined_methods": meth_count,
        "defined_fields": fld_count,
        "native_methods": nat_count,
        "obfuscated_classes": obf_cls_count,
        "obfuscation_ratio": round(obf_cls_count / cls_count, 4) if cls_count else 0.0,
        "top_packages": [{"prefix": k, "classes": v} for k, v in local_pkg_counts.most_common(12)],
    })
    print(f"  {rel:30s} ver={hdr['dex_version']} size={hdr['file_size']:10,d} classes={cls_count:6d} methods={meth_count:6d} native={nat_count:4d} obf={obf_cls_count/max(cls_count,1)*100:5.1f}% top={local_pkg_counts.most_common(2)}")

# Maven / Gradle .properties descriptors at APK root
maven_props = []
for p in sorted(BASE_DIR.glob("*.properties")):
    txt = p.read_text(encoding="utf-8", errors="replace").strip()
    kv = {}
    for line in txt.splitlines():
        if "=" in line and not line.startswith("#"):
            k, v = line.split("=", 1)
            kv[k.strip()] = v.strip()
    maven_props.append({"file": p.name, "metadata": kv})

# META-INF/*.version files (Jetpack / AndroidX / Kotlin versions!)
metainf_versions = {}
metainf_dir = BASE_DIR / "META-INF"
if metainf_dir.exists():
    for vp in sorted(metainf_dir.glob("*.version")):
        metainf_versions[vp.name] = vp.read_text(encoding="utf-8", errors="replace").strip()

# Kotlin tooling metadata & DebugProbes
kotlin_tooling = json.loads((BASE_DIR / "kotlin-tooling-metadata.json").read_text(encoding="utf-8"))

# ART baseline profile header
bp_raw = (BASE_DIR / "assets/dexopt/baseline.prof").read_bytes()
bp_m_raw = (BASE_DIR / "assets/dexopt/baseline.profm").read_bytes()

# Top packages & prefix summaries
top_prefixes = sorted(
    [{"prefix": k, **v} for k, v in prefix2_stats.items()],
    key=lambda x: x["classes"],
    reverse=True,
)[:120]

top_packages = sorted(
    [{"package": k, "classes": v["classes"], "methods": v["methods"], "native_methods": v["native_methods"], "obfuscated_classes": v["obfuscated_classes"], "dex_count": len(v["dex_files"])} for k, v in package_stats.items()],
    key=lambda x: x["classes"],
    reverse=True,
)[:150]

eco_out = sorted(
    [{"category": k, **v, "class_share": round(v["classes"] / max(total_classes, 1), 4)} for k, v in ecosystem_stats.items()],
    key=lambda x: x["classes"],
    reverse=True,
)

# Summarize native declarations by class
native_by_class = defaultdict(list)
for nd in native_declarations:
    native_by_class[nd["class_name"]].append({
        "dex": nd["dex"],
        "method": nd["method_name"],
        "descriptor": nd["descriptor"],
    })

out = {
    "totals": {
        "dex_file_count": len(dex_summaries),
        "primary_multidex_count": len(dex_summaries) - 1,
        "total_dex_bytes": sum(d["file_size"] for d in dex_summaries),
        "total_defined_classes": total_classes,
        "total_defined_methods": total_defined_methods,
        "total_defined_fields": total_defined_fields,
        "total_native_methods": total_native_methods,
        "total_native_declaring_classes": len(native_by_class),
        "total_obfuscated_classes": total_obfuscated_classes,
        "overall_obfuscation_ratio": round(total_obfuscated_classes / max(total_classes, 1), 4),
        "unique_packages_count": len(package_stats),
    },
    "per_dex": dex_summaries,
    "ecosystem_breakdown": eco_out,
    "top_120_prefixes": top_prefixes,
    "top_150_packages": top_packages,
    "entry_application_class": entry_app_class_info,
    "native_declarations_by_class": {k: v for k, v in sorted(native_by_class.items())},
    "maven_properties": maven_props,
    "meta_inf_versions": metainf_versions,
    "kotlin_tooling_metadata": kotlin_tooling,
    "art_baseline_profile": {
        "baseline_prof_bytes": len(bp_raw),
        "baseline_prof_magic": bp_raw[:8].hex(),
        "baseline_profm_bytes": len(bp_m_raw),
        "baseline_profm_magic": bp_m_raw[:8].hex(),
    },
}

(OUT_DIR / "dex_ecology.json").write_text(json.dumps(out, indent=2), encoding="utf-8")
print("\n=== DEX ECOLOGY SUMMARY ===")
print(json.dumps(out["totals"], indent=2))
print("Entry Application class jwbmqnsll.xvndbxlk:", json.dumps(entry_app_class_info, indent=2))
print("Ecosystem breakdown:")
for e in eco_out:
    print(f"  {e['category']:50s} cls={e['classes']:6d} ({e['class_share']*100:5.2f}%) meth={e['methods']:6d} nat={e['native_methods']:4d}")
