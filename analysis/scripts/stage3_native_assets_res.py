#!/usr/bin/env python3
"""
Stage 3 — Native (.so), assets, and resource-layer forensics.

Covers the non-DEX attack surface of ZaloPay 11.17.0:
  * ELF census: arch, NEEDED deps, exported JNI symbols, PLT imports
  * packed/obfuscated blob detection (bshield.dat, *.dat payloads)
  * TFLite/NCNN on-device AI model inventory (head-parser, no runtime)
  * Alibaba Griver mini-app containers (.amr == tar archives)
  * resource layer: library header prefixes + APK entry-point native risk

Output: analysis/data/05_native.json, analysis/data/06_assets.json, analysis/data/07_resources.json
"""
import os, re, json, glob, tarfile, struct, collections, hashlib, io

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DATA = os.path.join(ROOT, "data")
WORK = os.environ.get("ZPAY_WORK", "/home/user/work")
APK = os.path.join(WORK, "apk", "vn.com.vng.zalopay")
LIB = os.path.join(WORK, "apk", "config.armeabi_v7a", "lib", "armeabi-v7a")
os.makedirs(DATA, exist_ok=True)

from elftools.elf.elffile import ELFFile
from elftools.elf.dynamic import DynamicSection

# First-party libraries ZaloPay ships (obfuscated names are a deliberate control)
KNOWN_VENDOR = {
    "libsqlcipher.so": ("SQLCipher (encrypted SQLite)", "Zetetic"),
    "libc++_shared.so": ("libc++ runtime", "LLVM"),
    "libcrashlytics.so": ("Firebase Crashlytics NDK", "Google"),
    "libcrashlytics-common.so": ("Firebase Crashlytics NDK", "Google"),
    "libcrashlytics-handler.so": ("Firebase Crashlytics NDK", "Google"),
    "libcrashlytics-trampoline.so": ("Firebase Crashlytics NDK", "Google"),
    "libmlkit_google_ocr_pipeline.so": ("ML Kit text recognition", "Google"),
    "libmlkitcommonpipeline.so": ("ML Kit pipeline", "Google"),
    "libbarhopper_v3.so": ("ML Kit barcode decoder", "Google"),
    "libface_detector_v2_jni.so": ("MediaPipe face detector", "Google"),
    "libimage_processing_util_jni.so": ("MediaPipe image utils", "Google"),
    "libimagepipeline.so": ("Android image pipeline", "AOSP"),
    "libnative-filters.so": ("Fresco native filters", "Meta"),
    "libnative-imagetranscoder.so": ("Fresco transcoder", "Meta"),
    "librive-android.so": ("Rive animation runtime", "Rive"),
    "libpicodet_ncnn.so": ("PicoDet object detector (NCNN)", "OSS"),
    "libandroidx.graphics.path.so": ("AndroidX path", "Google"),
    "libVFaceLib.so": ("Face engine (vendor SDK)", "FPT/trueID"),
    "libtrueidlib.so": ("trueID SDK", "FPT"),
}
OBF_RE = re.compile(r"^lib[a-z]{6,10}\.so$")   # liba97d / libfgcpkjvqe / libohxbee …
SUSPICIOUS_IMPORTS = re.compile(
    r"ptrace|process_vm_readv|__system_property_get|fopen|openat|stat|access|readlink|getenv|"
    r"dl_iterate_phdr|syscall|kill|fork|pthread_create|execve|/proc/self", re.I)


def elf_report():
    libs, ent_notes = [], []
    for f in sorted(glob.glob(os.path.join(LIB, "*.so"))):
        name = os.path.basename(f)
        with open(f, "rb") as fh:
            data = fh.read()
        try:
            e = ELFFile(io.BytesIO(data))
        except Exception as ex:
            libs.append({"file": name, "error": repr(ex)})
            continue
        needed, soname, rpath = [], None, None
        for sec in e.iter_sections():
            if isinstance(sec, DynamicSection):
                for t in sec.iter_tags():
                    if t.entry.d_tag == "DT_NEEDED":
                        needed.append(t.needed)
                    elif t.entry.d_tag == "DT_SONAME":
                        soname = t.soname
                    elif t.entry.d_tag == "DT_RPATH":
                        rpath = t.rpath
        dynsym = e.get_section_by_name(".dynsym")
        exported, jni = [], []
        if dynsym:
            for s in dynsym.iter_symbols():
                if s.name and s.entry.st_info.type == "STT_FUNC" and s["st_shndx"] != "SHN_UNDEF":
                    exported.append(s.name)
                    if s.name.startswith("Java_"):
                        jni.append(s.name)
        # imported (undefined) symbol names from .dynsym
        undefined = []
        if dynsym:
            for s in dynsym.iter_symbols():
                if s.name and s["st_shndx"] == "SHN_UNDEF":
                    undefined.append(s.name)
        sus = sorted({u for u in undefined + exported if SUSPICIOUS_IMPORTS.search(u)})
        # .comment / build-id style strings
        comp = None
        sec = e.get_section_by_name(".comment")
        if sec:
            try:
                comp = sec.data().decode("latin-1").replace("\x00", " ").strip()[:120]
            except Exception:
                comp = None
        vend = KNOWN_VENDOR.get(name)
        libs.append({
            "file": name, "bytes": len(data),
            "sha256": hashlib.sha256(data).hexdigest(),
            "arch": e.header.e_machine, "elf_type": e.header.e_type,
            "needed": needed, "soname": soname, "rpath": rpath,
            "sections": e.num_sections(),
            "exported_symbols": len(exported),
            "jni_exports": len(jni),
            "jni_sample": sorted(jni)[:6],
            "undefined_symbols": len(undefined),
            "suspicious_symbols": sus[:16],
            "compiler_comment": comp,
            "obfuscated_name": bool(OBF_RE.match(name)) and name not in KNOWN_VENDOR,
            "vendor_guess": (vend[0] if vend else None),
            "vendor": (vend[1] if vend else ("ZaloPay (first-party)" if not OBF_RE.match(name) else "unattributed")),
        })
    first_party = [l for l in libs if l.get("obfuscated_name")]
    return {
        "library_count": len(libs),
        "total_bytes": sum(l.get("bytes", 0) for l in libs),
        "libraries": libs,
        "obfuscated_first_party": [l["file"] for l in first_party],
        "notes": [
            "All shipped ABIs: armeabi-v7a only in this XAPK split (32-bit); no arm64 split included in the bundle.",
            "Obfuscated lib names (liba97d, libfgcpkjvqe, libohxbee, libljsfrcwmw, liblytcuhe, librcmefqonnx, libtaibmcf) "
            "indicate a hardened first-party security/crypto layer whose identity is deliberately hidden.",
        ],
    }


def assets_report():
    base = os.path.join(APK, "assets")
    files = []
    for root, _, fns in os.walk(base):
        for fn in fns:
            p = os.path.join(root, fn)
            files.append((os.path.relpath(p, base), os.path.getsize(p)))
    files.sort(key=lambda x: -x[1])
    out = {
        "file_count": len(files),
        "total_bytes": sum(s for _, s in files),
        "largest": [{"path": p, "bytes": s} for p, s in files[:25]],
        "by_kind": collections.Counter(
            (p.rsplit(".", 1)[-1].lower() if "." in p else "(none)") for p, _ in files).most_common(),
    }
    # ── TFLite models (flatbuffer file_identifier "TFL3" at byte 4..8) ────
    models = []
    for p, s in files:
        if p.lower().endswith(".tflite"):
            full = os.path.join(base, p)
            head = open(full, "rb").read(16)
            ok = head[4:8] == b"TFL3"
            models.append({"path": p, "bytes": s, "tflite_magic_ok": ok,
                           "sha256": hashlib.sha256(open(full, "rb").read()).hexdigest()[:16]})
    # ── NCNN models: .param is text, .bin is raw weights ─────────────────
    ncnn = []
    for p, s in files:
        if ".ncnn." in p:
            ncnn.append({"path": p, "bytes": s})
    # ── Griver mini-app containers (.amr => ustar tar) ───────────────────
    griver = []
    for p, s in files:
        if p.endswith(".amr"):
            full = os.path.join(base, p)
            try:
                with tarfile.open(full) as t:
                    members = t.getmembers()
                    griver.append({"path": p, "bytes": s, "entries": len(members),
                                   "sample": [m.name for m in members[:6]],
                                   "uncompressed": sum(m.size for m in members)})
            except Exception as e:
                griver.append({"path": p, "bytes": s, "error": repr(e)[:120]})
    # ── encrypted / shield blobs ─────────────────────────────────────────
    blobs = []
    for p, s in files:
        if p.endswith((".dat", ".bin", ".pb")) and s > 1000:
            full = os.path.join(base, p)
            head = open(full, "rb").read(16)
            blobs.append({"path": p, "bytes": s, "head_hex": head.hex()[:32],
                          "printable_ratio": round(sum(32 <= c < 127 for c in head) / max(1, len(head)), 2)})
    # ── bundled DEX payload (dynamic code loading) ───────────────────────
    dex_assets = []
    for p, s in files:
        if p.endswith(".dex") or p.endswith(".jar"):
            full = os.path.join(base, p)
            head = open(full, "rb").read(8)
            dex_assets.append({"path": p, "bytes": s, "dex_magic": head[:4] == b"dex\n"})
    out.update({"tflite_models": models, "ncnn_models": ncnn, "griver_bundles": griver,
                "opaque_blobs": blobs, "bundled_dex": dex_assets})
    return out


def resources_report():
    import xml.etree.ElementTree as ET
    from loguru import logger
    logger.remove()
    from androguard.core.axml import ARSCParser, AXMLPrinter
    arsc = ARSCParser(open(os.path.join(APK, "resources.arsc"), "rb").read())
    pkg = "vn.com.vng.zalopay"
    xml = arsc.get_string_resources(pkg).decode("utf-8", "replace")
    root = ET.fromstring(xml)
    items = {e.get("name"): (e.text or "") for e in root}
    # Vietnamese copy volume as a localisation signal
    vi_chars = sum(1 for v in items.values() if re.search(r"[ăâđêôơưàảãáạằẳẵắặầẩẫấậèẻẽéẹềểễếệìỉĩíịòỏõóọồổỗốộờởỡớợùủũúụừửữứựỳỷỹýỵ]", v, re.I))
    keys = collections.Counter(re.sub(r"[._].*", "", k) for k in items)
    # network security config (decoded earlier) re-read for the record
    nsc_path = os.path.join(APK, "res", "xml", "network_security_config.xml")
    nsc = None
    try:
        nsc = AXMLPrinter(open(nsc_path, "rb").read()).get_xml().decode()
    except Exception:
        pass
    return {
        "arsc_bytes": os.path.getsize(os.path.join(APK, "resources.arsc")),
        "packages": arsc.get_packages_names(),
        "package_count": len(arsc.get_packages_names()),
        "string_resources": len(items),
        "vietnamese_strings": vi_chars,
        "type_names": arsc.get_types(pkg),
        "top_key_namespaces": keys.most_common(20),
        "network_security_config": nsc,
        "vector_drawables": len(glob.glob(os.path.join(APK, "res", "drawable", "*.xml"))),
    }


def main():
    print("[stage3] ELF …")
    with open(os.path.join(DATA, "05_native.json"), "w") as f:
        json.dump(elf_report(), f, indent=1, ensure_ascii=False)
    print("[stage3] assets …")
    with open(os.path.join(DATA, "06_assets.json"), "w") as f:
        json.dump(assets_report(), f, indent=1, ensure_ascii=False)
    print("[stage3] resources …")
    with open(os.path.join(DATA, "07_resources.json"), "w") as f:
        json.dump(resources_report(), f, indent=1, ensure_ascii=False)
    print("[stage3] done")


if __name__ == "__main__":
    main()
