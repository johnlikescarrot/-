#!/usr/bin/env python3
"""ELF forensics for the armeabi-v7a split: headers, hardening, symbols, fingerprints."""
from __future__ import annotations
import hashlib, json, os, re, struct
from collections import Counter

from elftools.elf.elffile import ELFFile
from elftools.elf.sections import SymbolTableSection

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
LIBDIR = os.path.join(ROOT, "work/lib/lib/armeabi-v7a")
DATA = os.path.join(ROOT, "data")

PROTECT_MARKERS = ["secneo", "bangcle", "ijiami", "nagain", "legu", "jiagu", "sheild", "shield",
                   "StubApp", "StubShell", "apkprotect", "qihoo", "baidu.protect", "taibmcf",
                   "whitebox", "wbcrypto", "frida", "xposed", "substrate", "magisk"]
TECH_MARKERS = ["flutter", "hermes", "react-native", "ncnn", "tensorflow", "onnxruntime", "sqlcipher",
                "openssl", "boringssl", "libc++", "v8", "quickjs", "javascriptcore", "webrtc",
                "opus", "ffmpeg", "zstd", "snappy", "protobuf", "grpc", "barhopper", "mlkit",
                "rive", "skia", "harfbuzz", "freetype", "sqlite", "crashlytics", "firebase"]


def strings_of(path: str, minlen: int = 6, limit: int = 400_000) -> list[str]:
    out, buf = [], b""
    with open(path, "rb") as fh:
        data = fh.read()
    for b in data:
        if 32 <= b < 127:
            buf += bytes([b])
            if len(buf) > 200:
                out.append(buf.decode("ascii"))
                buf = b""
        else:
            if len(buf) >= minlen:
                out.append(buf.decode("ascii"))
            buf = b""
            if len(out) >= limit:
                break
    if len(buf) >= minlen:
        out.append(buf.decode("ascii"))
    return out


def analyse(path: str) -> dict:
    name = os.path.basename(path)
    with open(path, "rb") as fh:
        elf = ELFFile(fh)
        info = {
            "name": name, "bytes": os.path.getsize(path),
            "sha256": hashlib.sha256(open(path, "rb").read()).hexdigest(),
            "elf_class": elf.elfclass, "endian": elf.little_endian and "little" or "big",
            "type": elf["e_type"], "machine": elf["e_machine"],
            "entry": hex(elf["e_entry"]), "abi_version": elf["e_flags"],
            "section_count": elf.num_sections(),
            "program_header_count": elf.num_segments(),
            "has_symtab": False, "has_debug_info": False,
            "dynamic": {}, "relro": False, "bind_now": False, "nx_stack": None,
            "textrel": False, "exported_symbols": 0, "jni_exports": [],
            "build_id": None, "comment": None, "needed_libs": [],
        }
        if elf.get_section_by_name(".symtab") is not None:
            info["has_symtab"] = True
        if elf.get_section_by_name(".debug_info") is not None:
            info["has_debug_info"] = True
        sec = elf.get_section_by_name(".comment")
        if sec is not None:
            try:
                info["comment"] = sec.data().decode("utf-8", "replace").strip("\x00").replace("\x00", " | ")[:200]
            except Exception:  # noqa: BLE001
                pass
        nid = elf.get_section_by_name(".note.gnu.build-id")
        if nid is not None:
            info["build_id"] = nid.data()[-20:].hex()
        dyn = elf.get_section_by_name(".dynamic")
        flags, flags1 = 0, 0
        if dyn is not None:
            for tag in dyn.iter_tags():
                key = tag.entry.d_tag
                if key == "DT_NEEDED":
                    info["needed_libs"].append(tag.needed)
                elif key == "DT_FLAGS":
                    flags = tag.entry.d_val
                elif key == "DT_FLAGS_1":
                    flags1 = tag.entry.d_val
                elif key == "DT_TEXTREL":
                    info["textrel"] = True
                elif key in ("DT_SONAME",):
                    info["dynamic"]["soname"] = tag.soname
                elif key == "DT_RPATH":
                    info["dynamic"]["rpath"] = tag.rpath
        info["bind_now"] = bool(flags & 0x8) or bool(flags1 & 0x1)
        info["textrel"] = info["textrel"] or bool(flags & 0x4)
        for seg in elf.iter_segments():
            if seg["p_type"] == "PT_GNU_RELRO":
                info["relro"] = True
            elif seg["p_type"] == "PT_GNU_STACK":
                info["nx_stack"] = not bool(seg["p_flags"] & 0x1)
        dynsym = elf.get_section_by_name(".dynsym")
        if isinstance(dynsym, SymbolTableSection):
            info["exported_symbols"] = sum(1 for s in dynsym.iter_symbols()
                                           if s["st_info"]["bind"] in ("STB_GLOBAL", "STB_WEAK")
                                           and s["st_shndx"] != "SHN_UNDEF")
            info["jni_exports"] = [s.name for s in dynsym.iter_symbols() if s.name.startswith("Java_")][:40]
        info["pie"] = elf["e_type"] == "ET_DYN"
        info["stripped"] = not info["has_symtab"]
    body = strings_of(path)
    blob = "\n".join(body).lower()
    info["string_count_ascii6"] = len(body)
    def hits(markers, text):
        found = {}
        for m in markers:
            pat = re.compile(r"(?<![a-z0-9])" + re.escape(m) + r"(?![a-z0-9])")
            sample = None
            for line in body[:200000]:
                if pat.search(line.lower()):
                    sample = line[:160]
                    break
            if sample:
                found[m] = sample
        return found
    pm = hits(PROTECT_MARKERS, blob)
    tm = hits(TECH_MARKERS, blob)
    info["protect_markers"] = sorted(pm)
    info["protect_marker_samples"] = pm
    info["tech_markers"] = sorted(tm)
    info["build_path_leaks"] = sorted({m.group(0)[:120] for line in body
                                       for m in [re.search(r"(?:/home/|/Users/|/buildbot/|/work/)[A-Za-z0-9_./+-]{6,}", line)] if m})[:12]
    info["build_path_leak_count"] = sum(1 for line in body if re.search(r"(?:/home/|/Users/|/buildbot/|/work/)[A-Za-z0-9_./+-]{6,}", line))
    info["compiler_hints"] = sorted({m for m in ("clang version", "GCC:", "Android (", "rustc",
                                                 "ndk-build", "LLVM") if m in "\n".join(body)})
    info["jni_class_refs"] = sorted({m.group(1).replace("/", ".")
                                      for s in body[:120000]
                                      for m in [re.search(r"L?((?:com|vn|org|io|net)/[A-Za-z0-9_/$]{6,})", s)] if m})[:25]
    return info


def main() -> None:
    libs = [analyse(os.path.join(LIBDIR, f)) for f in sorted(os.listdir(LIBDIR)) if f.endswith(".so")]
    libs.sort(key=lambda i: -i["bytes"])
    hard = {
        "all_pie": all(l["pie"] for l in libs),
        "all_nx_stack": all(l["nx_stack"] for l in libs),
        "relro_count": sum(1 for l in libs if l["relro"]),
        "bind_now_count": sum(1 for l in libs if l["bind_now"]),
        "stripped_count": sum(1 for l in libs if l["stripped"]),
        "debug_info_count": sum(1 for l in libs if l["has_debug_info"]),
        "textrel_count": sum(1 for l in libs if l["textrel"]),
        "total_bytes": sum(l["bytes"] for l in libs),
        "with_jni_exports": sum(1 for l in libs if l["jni_exports"]),
        "protect_marker_union": sorted({m for l in libs for m in l["protect_markers"]}),
        "tech_marker_union": sorted({m for l in libs for m in l["tech_markers"]}),
    }
    report = {"lib_count": len(libs), "hardening": hard, "libs": libs}
    with open(os.path.join(DATA, "05-native.json"), "w") as fh:
        json.dump(report, fh, indent=2)
    print(json.dumps(hard, indent=2))
    for l in libs:
        print(f"{l['name']:<38} {l['bytes']:>9}  jni={len(l['jni_exports']):>3} dyn={l['exported_symbols']:>5} "
              f"relro={int(l['relro'])} nx={int(bool(l['nx_stack']))} strip={int(l['stripped'])} "
              f"{','.join(l['protect_markers'][:3])}")


if __name__ == "__main__":
    main()
