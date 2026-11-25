#!/usr/bin/env python3
"""
Stage 30 -- native layer (ELF): provenance, hardening, JNI surface, protector
fingerprints and out-of-tree payloads.

ZaloPay ships 27 `.so` files inside the ABI split plus one `.so` parked in
`assets/` -- a file that is *not* loaded by the platform linker and must be
mapped by application code.  We dissect every ELF: ABI, hardening (PIE/NX/
RELRO/BIND_NOW), exported JNI entry points (which reveal the Java package that
owns the code), compiler provenance from `.comment`, and behavioural strings
that indicate anti-tamper / root / hooking detection.
"""

from __future__ import annotations

import logging
import re
from collections import Counter
from pathlib import Path

from elftools.elf.elffile import ELFFile
from elftools.elf.dynamic import DynamicSection
from elftools.elf.sections import SymbolTableSection

from common import UNPACKED, banner, dump, entropy_file, human

logging.disable(logging.CRITICAL)
UNPACKED_ROOT = UNPACKED

PROTECTOR_SIGNS = {
    "anti-debug": ["ptrace", "__ptrace", "TracerPid", "anti_debug", "debugger"],
    "root-detection": ["su_binary", "/system/xbin/su", "/system/bin/su", "busybox",
                       "superuser", "magisk", "Magisk", "zygisk", "Zygisk"],
    "hook-detection": ["frida", "Frida", "xposed", "Xposed", "gum-js-loop",
                       "substrate", "inline_hook", "hook_detect"],
    "emulator-detection": ["goldfish", "ranchu", "qemu", "nox", "bluestacks",
                           "Genymotion", "vbox", "emulator"],
    "integrity": ["check_integrity", "signature_check", "crc32_check",
                  "dex_hash", "tamper", "Tamper"],
    "obfuscation-vm": ["vm_exec", "bytecode", "interpreter_loop", "VMProtect",
                       "OLLVM", "ctrlflow", "flatten"],
    "crypto": ["AES", "RSA", "EC_KEY", "SHA256", "HMAC", "chacha", "poly1305",
               "cbc", "gcm", "openssl"],
    "network": ["curl", "libcurl", "openssl", "mbedtls", "BoringSSL",
                "SSL_write", "socket"],
    "telemetry": ["crashpad", "breakpad", "minidump", "sentry", "bugly"],
}

JNI_RE = re.compile(rb"Java_([A-Za-z0-9_]+)")


def elf_report(path: Path) -> dict:
    raw = path.read_bytes()
    out = {
        "path": str(path.relative_to(UNPACKED_ROOT)),
        "bytes": len(raw),
        "sha256": __import__("hashlib").sha256(raw).hexdigest(),
        "entropy": round(entropy_file(path), 3),
    }
    try:
        with open(path, "rb") as fh:
            elf = ELFFile(fh)
            out["class"] = elf.elfclass
            out["machine"] = elf.get_machine_arch()
            out["type"] = elf.header.e_type
            out["osabi"] = elf.header["e_ident"]["EI_OSABI"]
            out["entry"] = hex(elf.header.e_entry)
            out["sections"] = elf.num_sections()
            out["segments"] = elf.num_segments()

            dyn = elf.get_section_by_name(".dynamic")
            needed, soname, flags, bind_now = [], None, 0, False
            if isinstance(dyn, DynamicSection):
                for tag in dyn.iter_tags():
                    t = tag.entry.d_tag
                    if t == "DT_NEEDED":
                        needed.append(tag.needed)
                    elif t == "DT_SONAME":
                        soname = tag.soname
                    elif t == "DT_FLAGS":
                        flags = tag.entry.d_val
                    elif t == "DT_FLAGS_1":
                        bind_now = bool(tag.entry.d_val & 0x1)
            out["needed"] = needed
            out["soname"] = soname
            out["bind_now"] = bind_now
            out["df_1_bind_now"] = bool(flags & 0x8)

            # hardening: PIE + GNU_RELRO + GNU_STACK (NX)
            out["pie"] = out["type"] == "ET_DYN"
            relro = stack = []
            for seg in elf.iter_segments():
                if seg.header.p_type == "PT_GNU_RELRO":
                    relro.append(seg.header.p_memsz)
                if seg.header.p_type == "PT_GNU_STACK":
                    stack.append(seg.header.p_flags)
            out["gnu_relro"] = bool(relro)
            out["nx_stack"] = bool(stack) and not (stack[0] & 0x1)
            out["rwx_segments"] = sum(
                1 for seg in elf.iter_segments()
                if seg.header.p_type == "PT_LOAD"
                and (seg.header.p_flags & 0x7) == 0x7)

            # symbols
            exported = imported = 0
            jni = set()
            jni_onload = register_natives = False
            exported_names: list[str] = []
            for sec in elf.iter_sections():
                if not isinstance(sec, SymbolTableSection):
                    continue
                is_dyn = sec.name == ".dynsym"
                for sym in sec.iter_symbols():
                    if sym["st_shndx"] == "SHN_UNDEF":
                        imported += 1
                    elif is_dyn and sym["st_info"]["bind"] in ("STB_GLOBAL", "STB_WEAK") \
                            and sym["st_info"]["type"] in ("STT_FUNC", "STT_OBJECT"):
                        exported += 1
                        if len(exported_names) < 400:
                            exported_names.append(sym.name)
                        if sym.name.startswith("Java_"):
                            jni.add(sym.name)
                        if sym.name == "JNI_OnLoad":
                            jni_onload = True
                        if sym.name == "RegisterNatives":
                            register_natives = True
            out["exported_symbols"] = exported
            out["imported_symbols"] = imported
            out["jni_entrypoints"] = len(jni)
            out["jni_onload"] = jni_onload
            out["register_natives"] = register_natives
            out["jni_packages"] = sorted({"_".join(n[5:].split("_")[:-1])
                                          for n in list(jni)[:200]})[:8]
            out["sample_exports"] = sorted(exported_names)[:25]

            comment = elf.get_section_by_name(".comment")
            out["compiler"] = (comment.data().replace(b"\x00", b" ")
                               .decode("utf-8", "replace").strip()[:120]
                               if comment else None)
            # debug leftovers
            out["debug_sections"] = [s.name for s in elf.iter_sections()
                                     if s.name.startswith(".debug")]
    except Exception as exc:  # truncated / malformed ELF
        out["error"] = f"{type(exc).__name__}: {exc}"

    # behavioural string scan (bounded: first 24 MiB keeps it honest & fast)
    blob = raw[:24 << 20]
    signs = {k: sum(blob.count(s.encode()) for s in v) for k, v in PROTECTOR_SIGNS.items()}
    out["behaviour_hits"] = {k: v for k, v in signs.items() if v}
    out["jni_names_sample"] = [m.group(1).decode("ascii", "ignore")
                               for m in list(JNI_RE.finditer(blob))[:12]]
    libs = Counter(re.findall(rb"lib[A-Za-z0-9_+\-]{2,24}\.so", blob))
    out["embedded_lib_strings"] = [l.decode() for l, _ in libs.most_common(10)]
    return out


def main() -> None:
    banner("Stage 30 · native (ELF)")
    libs = []
    for p in sorted(UNPACKED_ROOT.rglob("*.so")):
        r = elf_report(p)
        libs.append(r)
        print(f"   • {Path(r['path']).name:<34} {human(r['bytes']):>8}  "
              f"{r.get('machine','?'):<12} jni={r.get('jni_entrypoints','?'):>3}  "
              f"H={r['entropy']}")

    total = sum(r["bytes"] for r in libs)
    hardening = {
        "pie": sum(1 for r in libs if r.get("pie")),
        "nx_stack": sum(1 for r in libs if r.get("nx_stack")),
        "relro": sum(1 for r in libs if r.get("gnu_relro")),
        "bind_now": sum(1 for r in libs if r.get("bind_now") or r.get("df_1_bind_now")),
        "rwx_segments": sum(1 for r in libs if r.get("rwx_segments")),
        "with_debug_sections": sum(1 for r in libs if r.get("debug_sections")),
        "jni_onload": sum(1 for r in libs if r.get("jni_onload")),
        "register_natives": sum(1 for r in libs if r.get("register_natives")),
        "stripped_no_dyn_exports": sum(1 for r in libs if r.get("exported_symbols") == 0),
        "total": len(libs),
    }

    # assets shipped as native payloads
    payloads = []
    for p in (UNPACKED_ROOT / "vn.com.vng.zalopay" / "assets").rglob("*"):
        if not p.is_file():
            continue
        head = p.read_bytes()[:8]
        if head.startswith(b"\x7fELF"):
            payloads.append({"asset": str(p.relative_to(UNPACKED_ROOT / "vn.com.vng.zalopay")),
                             "bytes": p.stat().st_size, "kind": "ELF shared object"})
        elif p.suffix in {".tflite", ".bin", ".param"}:
            payloads.append({"asset": str(p.relative_to(UNPACKED_ROOT / "vn.com.vng.zalopay")),
                             "bytes": p.stat().st_size,
                             "kind": {"tflite": "TensorFlow Lite model",
                                      ".bin": "NCNN weights",
                                      ".param": "NCNN topology"}.get(p.suffix, "model")})

    agg = Counter()
    for r in libs:
        for k, v in r.get("behaviour_hits", {}).items():
            agg[k] += 1

    doc = {
        "totals": {"libraries": len(libs), "bytes": total,
                   "bytes_human": human(total),
                   "abi": sorted({r.get("machine") for r in libs if r.get("machine")})},
        "hardening": hardening,
        "libraries": libs,
        "protector_fingerprints": dict(agg),
        "out_of_tree_payloads": payloads,
        "largest": [{"name": Path(r["path"]).name, "bytes": r["bytes"]}
                    for r in sorted(libs, key=lambda x: -x["bytes"])[:8]],
    }
    dump("30_native.json", doc)

    print(f"  {len(libs)} ELF / {human(total)}  hardening={hardening}")
    print(f"  protector fingerprints touched: {dict(agg)}")
    print(f"  out-of-tree payloads: "
          f"{[(p['asset'], human(p['bytes']), p['kind']) for p in payloads]}")


if __name__ == "__main__":
    main()
