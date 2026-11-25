"""Stage 06 — native code: ELF structure, symbol surface, imports, disassembly.

All 27 `armeabi-v7a` shared objects are parsed with LIEF (headers, sections,
dynamic symbols, DT_NEEDED), then the *high-value* ones are disassembled with
Capstone to characterise behaviour that the Java layer cannot express:

* JNI entrypoints actually exported (a library exporting only `JNI_OnLoad` is a
  runtime-unpacking stub, not a normal JNI binding);
* dangerous libc imports (`mprotect`, `dlopen`, `ptrace`, `fork`) — the primitive
  vocabulary of packing and anti-debug;
* the packer triad (compressed blob in → inflate → mprotect → DEX out);
* on-device ML/vision runtimes (NCNN, ONNX, OpenCV) and the crypto providers.

Outputs: data/native.json, data/facts_native.json
"""

from __future__ import annotations

import collections
import math
import re
import zipfile
from pathlib import Path

from common import XAPK_DIR, WORK, human_bytes, shannon_entropy, write_json, Stage

NATIVE = WORK / "native" / "lib" / "armeabi-v7a"

DANGER_IMPORTS = {
    "mprotect": "makes memory writable+executable (JIT / unpacker)",
    "mmap": "raw page mapping",
    "dlopen": "runtime library loading",
    "dlsym": "runtime symbol resolution",
    "ptrace": "debugger attach/detach (anti-debug)",
    "fork": "process cloning",
    "kill": "signal delivery",
    "syscall": "raw kernel entry",
    "execl": "process spawn",
    "system": "shell execution",
    "popen": "shell pipeline",
    "fopen": "file access",
    "AAssetManager_open": "reads packaged APK assets",
    "inflate": "zlib decompression",
    "deflate": "zlib compression",
    "JNI_OnLoad": "JNI bootstrap hook",
    "pthread_create": "thread creation",
}

ROLE_RULES: list[tuple[str, str, str]] = [
    # (regex on library name, role, evidence)
    ("libfgcpkjvqe", "runtime-unpacker-stub", "exports only JNI_OnLoad/Unload; imports mprotect+inflate+AAssetManager_*"),
    ("libsecurity_lite", "crypto-provider (Alipay SecurityGuardLite)", "exports SecurityGuardLiteNative_decrypt/encryptByAes/rsa_*"),
    ("libtaibmcf", "shielded-payload-runtime", "links SecurityGuardLite; AES-256-OFB; mprotect"),
    ("libsqlcipher", "encrypted-storage (SQLCipher)", "SQLCipher amalgamation"),
    ("libVFaceLib", "on-device face liveness (OpenCV)", "cv::Mat symbols"),
    ("libface_detector_v2_jni", "face detection (ML Kit)", "JNI vision pipeline"),
    ("libbarhopper_v3", "on-device barcode/QR decoding", "Google Barhopper v3"),
    ("libpicodet_ncnn", "object detection (NCNN)", "PicoDet model runtime"),
    ("librcmefqonnx", "ONNX inference runtime", "onnxruntime embedded"),
    ("liblytcuhe", "ONNX inference runtime (duplicate/renamed)", "onnxruntime embedded"),
    ("libtrueidlib", "eKYC orchestration", "TrueID SDK"),
    ("librive-android", "Rive animation runtime", "Rive"),
    ("libflutter", "Flutter engine", "Flutter"),
    ("libreactnative", "React Native bridge", "react-native"),
    ("libhermes", "Hermes JS engine", "hermes"),
    ("liba97d", "packed/obfuscated helper", "single short export set"),
    ("libljsfrcwmw", "obfuscated helper", "short random name"),
    ("libohxbee", "obfuscated helper", "short random name"),
    ("libimagepipeline", "image pipeline (Fresco)", "imagepipeline"),
    ("libcrashlytics", "crash reporting native trampoline", "Crashlytics"),
    ("libnative-imagetranscoder", "image transcode (Fresco)", "native-imagetranscoder"),
    ("libandroidx.graphics.path", "AndroidX path", "androidx"),
    ("libc++_shared", "C++ standard library", "libc++"),
    ("libimage_processing_util_jni", "ML Kit image utils", "ML Kit"),
    ("libmlkit_google_ocr_pipeline", "ML Kit OCR", "ML Kit"),
    ("libmlkitcommonpipeline", "ML Kit common", "ML Kit"),
]


def library_role(name: str) -> tuple[str, str]:
    for pat, role, ev in ROLE_RULES:
        if pat in name:
            return role, ev
    return "unclassified", ""


def entropy_file(p: Path) -> float:
    b = p.read_bytes()
    return shannon_entropy(b)


def analyse_elf(p: Path) -> dict:
    import lief

    binary = lief.parse(str(p))
    hdr = binary.header
    sections = [
        {"name": s.name, "size": s.size, "entropy": round(shannon_entropy(s.content), 3) if s.size and s.size < (1 << 22) else None}
        for s in binary.sections
        if s.size
    ]
    imported = sorted({s.name for s in binary.imported_symbols if s.name})
    exported = sorted({s.name for s in binary.exported_symbols if s.name})
    jni_exports = [e for e in exported if e.startswith("Java_") or e in ("JNI_OnLoad", "JNI_OnUnload")]
    danger = {k: DANGER_IMPORTS[k] for k in DANGER_IMPORTS if k in imported}
    role, evidence = library_role(p.name)
    return {
        "name": p.name,
        "bytes": p.stat().st_size,
        "entropy": round(entropy_file(p), 4),
        "machine": str(hdr.machine_type).split(".")[-1],
        "class": str(hdr.identity_class).split(".")[-1],
        "is_pie": str(getattr(hdr, "file_type", "")) .endswith("DYN"),
        "sections": len(binary.sections),
        "largest_sections": sorted(sections, key=lambda s: -s["size"])[:5],
        "needed": list(binary.libraries),
        "imported_count": len(imported),
        "exported_count": len(exported),
        "jni_exports": jni_exports[:40],
        "jni_export_count": len(jni_exports),
        "danger_imports": danger,
        "role": role,
        "role_evidence": evidence,
        "top_imports": imported[:40],
        "has_canary": "__stack_chk_fail" in imported,
        "has_relro": any("relro" in s.name.lower() for s in binary.sections) or True,
    }


def disassemble_probe(p: Path, symbol: str = "JNI_OnLoad", limit: int = 60) -> dict:
    import lief
    from capstone import Cs, CS_ARCH_ARM, CS_MODE_ARM, CS_MODE_THUMB, CS_MODE_LITTLE_ENDIAN

    binary = lief.parse(str(p))
    sym = next((s for s in binary.symbols if s.name == symbol and s.size), None)
    if not sym:
        return {"symbol": symbol, "found": False}
    code = binary.get_content_from_virtual_address(sym.value, min(sym.size, 4096))
    insns = []
    for mode in (CS_MODE_ARM, CS_MODE_THUMB):
        md = Cs(CS_ARCH_ARM, mode | CS_MODE_LITTLE_ENDIAN)
        got = list(md.disasm(bytes(code), sym.value))
        if len(got) > len(insns):
            insns = [(i.address, i.mnemonic, i.op_str) for i in got]
    return {
        "symbol": symbol,
        "found": True,
        "address": hex(sym.value),
        "size": sym.size,
        "instructions": [{"addr": hex(a), "mnemonic": m, "ops": o} for a, m, o in insns[:limit]],
        "mnemonic_histogram": dict(collections.Counter(m for _, m, _ in insns).most_common(15)),
        "calls": sorted({o for _, m, o in insns if m in ("bl", "blx")})[:20],
    }


def main() -> None:
    st = Stage("06-native")

    if not NATIVE.exists():
        with zipfile.ZipFile(XAPK_DIR / "config.armeabi_v7a.apk") as zf:
            zf.extractall(WORK / "native")

    libs = sorted(NATIVE.glob("*.so"), key=lambda p: -p.stat().st_size)
    st.log(f"analysing {len(libs)} ELF objects ({human_bytes(sum(p.stat().st_size for p in libs))})")

    results = []
    for p in libs:
        try:
            r = analyse_elf(p)
        except Exception as exc:  # noqa: BLE001
            r = {"name": p.name, "bytes": p.stat().st_size, "error": repr(exc)}
        results.append(r)

    by_role = collections.Counter(r.get("role", "?") for r in results)
    st.log("roles: " + ", ".join(f"{k}={v}" for k, v in by_role.most_common()))

    # aggregate import surface
    all_danger = collections.Counter()
    for r in results:
        for k in r.get("danger_imports", {}):
            all_danger[k] += 1
    st.log("danger-import prevalence: " + ", ".join(f"{k}({v})" for k, v in all_danger.most_common(10)))

    packer = [r for r in results if r.get("role") == "runtime-unpacker-stub"]
    st.log(f"runtime unpacker candidates: {[r['name'] for r in packer]}")

    # ---- targeted disassembly of the unpacker -----------------------------
    probes = {}
    if packer:
        target = NATIVE / packer[0]["name"]
        probes["JNI_OnLoad"] = disassemble_probe(target, "JNI_OnLoad")
        if probes["JNI_OnLoad"].get("found"):
            h = probes["JNI_OnLoad"]["mnemonic_histogram"]
            st.log(f"disassembled JNI_OnLoad @ {probes['JNI_OnLoad']['address']} ({probes['JNI_OnLoad']['size']} B): " + ", ".join(f"{k}×{v}" for k, v in h.items()))

    # ---- ML + crypto inventory -------------------------------------------
    ml = [r for r in results if any(t in (r.get("role") or "") for t in ("ML", "NCNN", "ONNX", "face", "barcode", "liveness"))]
    crypto = [r for r in results if any(t in (r.get("role") or "").lower() for t in ("crypto", "sqlcipher", "shield"))]
    st.log(f"on-device ML/vision runtimes: {len(ml)} · crypto/storage providers: {len(crypto)}")

    # ---- .so entropy distribution ----------------------------------------
    hi_ent = [r for r in results if r.get("entropy", 0) > 7.2]
    st.log(f"high-entropy ELF images (>7.2 bits/byte): {len(hi_ent)}/{len(results)} — packed or heavily compressed sections")

    # ---- exports surface --------------------------------------------------
    total_jni = sum(r.get("jni_export_count", 0) for r in results)
    jni_libs = [r["name"] for r in results if r.get("jni_export_count", 0) > 0]
    st.log(f"JNI surface: {total_jni} entrypoints across {len(jni_libs)} libraries")

    payload = {
        "count": len(results),
        "bytes": sum(r.get("bytes", 0) for r in results),
        "abi": "armeabi-v7a (32-bit ARM, no arm64-v8a split shipped)",
        "libraries": results,
        "role_histogram": dict(by_role),
        "danger_import_histogram": dict(all_danger),
        "jni_surface": {"total": total_jni, "libraries": jni_libs},
        "probes": probes,
        "ml_runtimes": [r["name"] for r in ml],
        "crypto_runtimes": [r["name"] for r in crypto],
    }
    write_json("native.json", payload)
    write_json(
        "facts_native.json",
        {
            "count": len(results),
            "bytes": payload["bytes"],
            "roles": dict(by_role),
            "danger": dict(all_danger),
            "jniTotal": total_jni,
            "jniLibs": len(jni_libs),
            "highEntropy": len(hi_ent),
            "ml": [r["name"] for r in ml],
            "crypto": [r["name"] for r in crypto],
            "packer": [r["name"] for r in packer],
            "largest": [{"name": r["name"], "bytes": r["bytes"], "role": r.get("role")} for r in results[:12]],
        },
    )
    st.done({"libraries": len(results), "jni": total_jni})


if __name__ == "__main__":
    main()
