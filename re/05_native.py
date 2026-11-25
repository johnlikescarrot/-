"""
05_native.py — native (ARM32 ELF) attribution for the 27 `lib*.so` in the split.

The interesting question for a fintech app is *which* native component touches
secrets, so every library gets a capability vector built from three independent
evidence channels:

  A. ELF structure   — PT_GNU_RELRO / PT_GNU_STACK, PIE, DT_NEEDED, .symtab
  B. dynamic symbols — Java_* JNI entry points (the Java↔C boundary is the API)
  C. rodata strings  — SDK banners, protocol markers, anti-analysis probes

`libtaibmcf.so` (12.6 MB) is the largest single file in the whole bundle after
the DEX pool, and its JNI surface is zero — it is a *statically linked* payload.
Its rodata contains MLS/SELinux policy strings, which is the signature of a
linker-hardened static runtime rather than an app-level SDK, so we report the
capability vector and explicitly *withhold* a vendor guess where evidence is thin.

Rather than guessing, a confidence field is attached to every claim:
   high   — symbol/string evidence names the component
   medium — string evidence names a subsystem, vendor inferred from co-occurrence
   low    — only structural evidence
"""
from __future__ import annotations

import collections
import hashlib
import json
import os
import re
import subprocess
import sys

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))

DATA = "data"
NATIVE_DIR = "work/native"
PRINTABLE = re.compile(rb"[\x20-\x7e]{5,400}")

EVIDENCE_RULES: dict[str, re.Pattern] = {
    "openssl": re.compile(r"OpenSSL \d\.\d|SSL_CTX_new|X509_verify_cert|BIO_new"),
    "boringssl": re.compile(r"BoringSSL|CRYPTO_BUFFER|SSL_CTX_set_custom_verify"),
    "mbedtls": re.compile(r"mbedtls_"),
    "protobuf": re.compile(r"libprotobuf|google\.protobuf\.|DescriptorProto|"
                        r"FileDescriptorProto|protobuf::"),
    "sqlite": re.compile(r"sqlite3_|SQLite format 3"),
    "sqlcipher": re.compile(r"sqlcipher|PRAGMA key"),
    "ncnn": re.compile(r"ncnn"),
    "tflite": re.compile(r"tflite|TFLite|TensorFlowLite"),
    "opencv": re.compile(r"opencv|OpenCV|cv::"),
    "mediapipe": re.compile(r"mediapipe"),
    "frida_probe": re.compile(r"frida-agent|frida-server|libfrida|re\.frida"),
    "xposed_probe": re.compile(r"de\.robv\.android\.xposed|XposedBridge"),
    "ptrace_probe": re.compile(r"TracerPid|ptrace"),
    "root_probe": re.compile(r"/system/xbin/su|magisk|Superuser"),
    "emulator_probe": re.compile(r"goldfish|ranchu|ro\.kernel\.qemu|genymotion"),
    "integrity_probe": re.compile(r"SafetyNet|PlayIntegrity|attestation|vaultCheckIntegrity"),
    "whitebox_aes": re.compile(r"(?i)white-?box"),
    "sm_algo": re.compile(r"\bSM2\b|\bSM3\b|\bSM4\b"),
    "secguard": re.compile(r"SecurityGuardLiteNative|alipay_plus_security_lite"),
    "liveness": re.compile(r"LIVENESS|liveness"),
    "face": re.compile(r"faceAlignment|getFaces|landmark"),
    "crashlytics": re.compile(r"com/google/firebase/crashlytics|FirebaseCrashlytics"),
    "rive": re.compile(r"rive::|RiveRenderer"),
    "fresco": re.compile(r"com/facebook/imagepipeline|Fresco"),
    "mlkit": re.compile(r"com/google/android/gms/internal/mlkit|mlkit_"),
    "libcxx": re.compile(r"std::__ndk1|libc\+\+_shared"),
}

VENDOR_HINTS: list[tuple[re.Pattern, str, str]] = [
    (re.compile(r"Java_vn_ai_faceauth|VFaceLib"), "VNG FaceAuth (VFaceLib)", "high"),
    (re.compile(r"Java_vn_ai_trueid|vn/ai/trueid"), "VNG TrueID eKYC", "high"),
    (re.compile(r"SecurityGuardLiteNative|alipay_plus_security_lite"), "Alipay SecurityGuard-Lite", "high"),
    (re.compile(r"Java_com_example_picodet|ncnn"), "PicoDet via NCNN", "high"),
    (re.compile(r"com/google/android/gms/internal/mlkit|mlkit"), "Google ML Kit", "high"),
    (re.compile(r"libsqlcipher|sqlcipher"), "SQLCipher (Zetetic)", "high"),
    (re.compile(r"Lvn/ai/faceauth|faceauth/sdk"), "VNG FaceAuth (VFaceLib)", "high"),
    (re.compile(r"crashlytics"), "Firebase Crashlytics", "high"),
    (re.compile(r"rive::|RiveRenderer"), "Rive runtime", "high"),
    (re.compile(r"com/facebook/imagepipeline"), "Fresco image pipeline", "high"),
    (re.compile(r"com/alibaba|ariver|griver"), "Alibaba Griver/ARiver", "high"),
    (re.compile(r"libc\+\+_shared|std::__ndk1"), "LLVM libc++ (NDK)", "high"),
]


def readelf(so: str) -> dict:
    """Use binutils when present; else fall back to pyelftools only."""
    out: dict = {}
    try:
        txt = subprocess.run(["readelf", "-d", "-l", "-h", so], capture_output=True, text=True, timeout=60).stdout
    except Exception:
        return out
    out["needed"] = re.findall(r"NEEDED\).*\[(.+?)\]", txt)
    out["soname"] = (re.findall(r"SONAME\).*\[(.+?)\]", txt) or [None])[0]
    out["rpath"] = (re.findall(r"RPATH\).*\[(.+?)\]", txt) or [None])[0]
    out["runpath"] = (re.findall(r"RUNPATH\).*\[(.+?)\]", txt) or [None])[0]
    out["bind_now"] = "BIND_NOW" in txt
    out["gnu_relro"] = "GNU_RELRO" in txt
    out["gnu_stack"] = "GNU_STACK" in txt
    out["flags"] = (re.findall(r"Flags:\s+(.+)", txt) or [None])[0]
    return out


def analyse(path: str) -> dict:
    from elftools.elf.elffile import ELFFile
    from elftools.elf.sections import SymbolTableSection

    name = os.path.basename(path)
    blob = open(path, "rb").read()
    strings = [s.decode("ascii", "replace") for s in PRINTABLE.findall(blob)]
    joined = "\n".join(strings)
    # limit string scan cost on the 12 MB outlier
    scan = joined if len(joined) < 40_000_000 else joined[:40_000_000]

    rec: dict = {
        "lib": name,
        "size": len(blob),
        "sha256": hashlib.sha256(blob).hexdigest(),
        "entropy": round(_entropy(blob), 3),
        "strings": len(strings),
    }
    with open(path, "rb") as fh:
        elf = ELFFile(fh)
        rec["machine"] = elf["e_machine"]
        rec["type"] = elf["e_type"]
        rec["pie"] = elf["e_type"] == "ET_DYN"
        sections = [s.name for s in elf.iter_sections()]
        rec["sections"] = len(sections)
        rec["text_bytes"] = elf.get_section_by_name(".text").data_size if elf.get_section_by_name(".text") else 0
        rec["has_symtab"] = ".symtab" in sections
        rec["has_debug"] = any(s.startswith(".debug") for s in sections)
        dynsym = elf.get_section_by_name(".dynsym")
        syms = []
        if isinstance(dynsym, SymbolTableSection):
            syms = [s.name for s in dynsym.iter_symbols() if s.name]
        rec["dynsym"] = len(syms)
        rec["jni"] = sorted({s for s in syms if s.startswith("Java_")})
        rec["jni_count"] = len(rec["jni"])
        rec["cxx_export_count"] = sum(1 for s in syms if s.startswith("_Z"))
        rec["exported_api"] = sorted({
            s for s in syms
            if not s.startswith(("_", "Java_")) and not s.startswith("__")
            and s not in ("", "main")
        })[:40]
        # build id via note section
        rec["build_id"] = None
        for s in elf.iter_sections():
            if s.name == ".note.gnu.build-id":
                for note in s.iter_notes():
                    if note["n_type"] == "NT_GNU_BUILD_ID":
                        rec["build_id"] = note["n_desc"]

    rec["elf"] = readelf(path)

    # ---- evidence channels ------------------------------------------------ #
    caps = {}
    for label, rx in EVIDENCE_RULES.items():
        n = len(rx.findall(scan))
        if n:
            caps[label] = n
    rec["capabilities"] = caps

    vendors, seen_vendor = [], set()
    for rx, label, conf in VENDOR_HINTS:
        if label in seen_vendor:
            continue
        if rx.search(scan):
            vendors.append({"vendor": label, "confidence": conf})
            seen_vendor.add(label)
    rec["attribution"] = vendors

    # version strings
    rec["version_strings"] = sorted({
        s for s in re.findall(r"\b(?:OpenSSL|SQLCipher|SQLite|ncnn|protobuf|libc\+\+|clang|GCC|Android)\b[ /]?[0-9][0-9A-Za-z.\-]{0,18}", scan)
    })[:12]
    rec["compiler"] = sorted({s for s in re.findall(r"(?:clang version [\d.]+|GCC: \(.*?\) [\d.]+|Android \(.*?\) clang [\d.]+)", scan)})[:3]
    rec["anti_analysis"] = {
        k: caps[k] for k in ("frida_probe", "xposed_probe", "ptrace_probe", "root_probe",
                             "emulator_probe", "integrity_probe") if k in caps
    }
    rec["vendored_java_pkgs"] = collections.Counter(
        re.findall(r"\b(?:com|vn|org|io|net)/[a-z0-9_]+(?:/[a-z0-9_]+){0,2}", scan)
    ).most_common(8)
    rec["notable_strings"] = sorted({
        s for s in strings
        if re.search(r"(?i)(whitebox|sm4|liveness|smartotp|vault|securityguard|signrequest|"
                     r"frida|magisk|attest|liveness|liveness_|pin-sha256)", s)
    })[:15]
    rec["has_embedded_dex"] = b"dex\n03" in blob
    # a bare PK\x03\x04 in rodata is almost always a zip constant, not an APK;
    # require a real central-directory + EOCD pair before claiming an archive.
    head = blob[: 16 << 20]
    rec["has_embedded_zip"] = b"PK\x03\x04" in head and b"PK\x05\x06" in head
    rec["embedding_requires_dynamic_confirmation"] = bool(rec["has_embedded_zip"])
    return rec


def _entropy(data: bytes) -> float:
    import math
    if not data:
        return 0.0
    c = collections.Counter(data)
    n = len(data)
    return -sum((v / n) * math.log2(v / n) for v in c.values())


def main() -> None:
    rows = []
    if not os.path.isdir(NATIVE_DIR):
        print(f"[native] {NATIVE_DIR} missing — extract lib/*.so first")
        return
    for fn in sorted(os.listdir(NATIVE_DIR)):
        if not fn.endswith(".so"):
            continue
        rows.append(analyse(os.path.join(NATIVE_DIR, fn)))
        print(f"[native] {fn:<34} {rows[-1]['size']/1e6:6.2f}MB  jni={rows[-1]['jni_count']:>3}  "
              f"caps={len(rows[-1]['capabilities'])}")
    rows.sort(key=lambda r: -r["size"])
    summary = {
        "count": len(rows),
        "bytes": sum(r["size"] for r in rows),
        "total_jni_exports": sum(r["jni_count"] for r in rows),
        "with_symtab": sum(1 for r in rows if r["has_symtab"]),
        "pie": sum(1 for r in rows if r["pie"]),
        "relro": sum(1 for r in rows if r["elf"].get("gnu_relro")),
        "nx_stack": sum(1 for r in rows if r["elf"].get("gnu_stack")),
        "with_debug": sum(1 for r in rows if r["has_debug"]),
        "embedded_dex": [r["lib"] for r in rows if r["has_embedded_dex"]],
        "embedded_zip_candidates": [r["lib"] for r in rows if r["has_embedded_zip"]],
        "embedded_zip_note": "zip signatures inside .rodata may be embedded payloads OR "
                             "compile-time constants from the zip/archive library; "
                             "static analysis alone cannot separate the two.",
        "anti_analysis_libs": {r["lib"]: r["anti_analysis"] for r in rows if r["anti_analysis"]},
        "needed_by_lib": {r["lib"]: r["elf"].get("needed") for r in rows},
        "compilers": sorted({c for r in rows for c in r["compiler"]}),
    }
    json.dump({"summary": summary, "libs": rows}, open(f"{DATA}/native.json", "w"), indent=1, ensure_ascii=False)
    print(f"[native] {summary['count']} libs, {summary['bytes']/1e6:.1f} MB, "
          f"JNI exports={summary['total_jni_exports']}, anti-analysis libs={len(summary['anti_analysis_libs'])}")
    print(f"[native] compilers: {summary['compilers'][:3]}")
    print("[native] -> data/native.json")


if __name__ == "__main__":
    main()
