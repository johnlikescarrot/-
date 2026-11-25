"""
Pure-Python ELF32 / ELF64 structural header, program-header, section-header,
and DT_NEEDED dependency-graph analyzer for Android native libraries (.so).
Evaluates ABI architecture, section size anatomy, toolchain provenance,
dynamic link topology, and Android 16 (16 KB page-size) PT_LOAD alignment.
Performs NO machine-code disassembly and NO vulnerability/hardening-gap auditing.
"""
from __future__ import annotations

import struct
from typing import Any, Dict, List

PT_LOAD = 1
PT_DYNAMIC = 2
PT_NOTE = 4
DT_NULL = 0
DT_NEEDED = 1
DT_SONAME = 14
SHT_SYMTAB = 2
SHT_STRTAB = 3
SHT_DYNAMIC = 6
SHT_DYNSYM = 11

EM_NAMES = {
    3: "x86",
    40: "ARM (32-bit)",
    62: "x86_64",
    183: "AArch64 (64-bit)",
}


def classify_native_lib(filename: str) -> str:
    low = filename.lower()
    if any(k in low for k in ("c++_shared", "gnustl", "stlport", "llvm", "unwind")):
        return "C++ Standard Runtime & Toolchain"
    if any(k in low for k in ("flutter", "dart", "hermes", "jsc", "v8", "react", "yoga", "fbjni", "soloader", "jsi", "fabric", "turbomodule", "reanimated", "folly", "glog")):
        return "Hybrid / Cross-Platform Runtime (Flutter/RN/JS)"
    if any(k in low for k in ("tflite", "tensorflow", "onnx", "ncnn", "mnn", "opencv", "mediapipe", "mlkit", "barhopper", "zxing", "tesseract", "paddle", "face", "ocr", "liveness", "vision", "eigen", "nnapi", "xnnpack")):
        return "On-Device AI / ML / Computer Vision"
    if any(k in low for k in ("sqlite", "sqlcipher", "realm", "mmkv", "leveldb", "rocksdb", "objectbox", "wcddb", "wcdb")):
        return "Embedded Storage & KV/SQL Engines"
    if any(k in low for k in ("cronet", "ssl", "crypto", "boring", "conscrypt", "curl", "grpc", "quic", "http", "mars", "xlog")):
        return "Networking, TLS & Transport"
    if any(k in low for k in ("webp", "png", "jpeg", "turbojpeg", "gif", "avif", "heif", "skia", "image", "ffmpeg", "avcodec", "avformat", "swscale", "opus", "vpx", "dav1d", "webrtc", "exoplayer", "media", "yuv", "fresco", "lottie", "pag", "rive")):
        return "Media, Graphics & Image Codecs"
    if any(k in low for k in ("zstd", "lz4", "brotli", "zlib", "snappy", "7z", "bz2", "deflate")):
        return "Compression & Serialization"
    if any(k in low for k in ("crashlytics", "breakpad", "sentry", "bugly", "plcrash", "datadog", "firebase", "apm", "perfetto", "profiler")):
        return "Observability & Crash Reporting"
    return "Domain / Vendor / Application Native Modules"


def parse_elf_structural(data: bytes, path: str, zip_aligned_4k: bool, zip_aligned_16k: bool, compressed: bool, compressed_size: int) -> Dict[str, Any]:
    """Parse structural ELF metadata for a single .so binary."""
    filename = path.rsplit("/", 1)[-1]
    if len(data) < 64 or data[:4] != b"\x7fELF":
        return {"path": path, "filename": filename, "valid": False}

    ei_class = data[4]  # 1 = 32-bit, 2 = 64-bit
    ei_data = data[5]   # 1 = little-endian, 2 = big-endian
    endian = "<" if ei_data == 1 else ">"
    is_64 = ei_class == 2

    if is_64:
        e_type, e_machine, e_version, e_entry, e_phoff, e_shoff, e_flags, e_ehsize, e_phentsize, e_phnum, e_shentsize, e_shnum, e_shstrndx = struct.unpack_from(
            f"{endian}HHIQQQIHHHHHH", data, 16
        )
    else:
        e_type, e_machine, e_version, e_entry, e_phoff, e_shoff, e_flags, e_ehsize, e_phentsize, e_phnum, e_shentsize, e_shnum, e_shstrndx = struct.unpack_from(
            f"{endian}HHIIIIIHHHHHH", data, 16
        )

    # Program headers: inspect PT_LOAD alignments (4 KB vs 16 KB page-size readiness)
    pt_load_aligns: List[int] = []
    pt_load_memsz = 0
    for i in range(e_phnum):
        off = e_phoff + i * e_phentsize
        if off + e_phentsize > len(data):
            break
        if is_64:
            p_type, p_flags, p_offset, p_vaddr, p_paddr, p_filesz, p_memsz, p_align = struct.unpack_from(
                f"{endian}IIQQQQQQ", data, off
            )
        else:
            p_type, p_offset, p_vaddr, p_paddr, p_filesz, p_memsz, p_flags, p_align = struct.unpack_from(
                f"{endian}IIIIIIII", data, off
            )
        if p_type == PT_LOAD:
            pt_load_aligns.append(p_align)
            pt_load_memsz += p_memsz

    min_load_align = min(pt_load_aligns) if pt_load_aligns else 0
    max_load_align = max(pt_load_aligns) if pt_load_aligns else 0
    elf_16k_aligned = bool(pt_load_aligns) and all(a >= 16384 for a in pt_load_aligns)

    # Section headers
    sections: List[Dict[str, Any]] = []
    for i in range(e_shnum):
        off = e_shoff + i * e_shentsize
        if off + e_shentsize > len(data):
            break
        if is_64:
            sh_name, sh_type, sh_flags, sh_addr, sh_offset, sh_size, sh_link, sh_info, sh_addralign, sh_entsize = struct.unpack_from(
                f"{endian}IIQQQQIIQQ", data, off
            )
        else:
            sh_name, sh_type, sh_flags, sh_addr, sh_offset, sh_size, sh_link, sh_info, sh_addralign, sh_entsize = struct.unpack_from(
                f"{endian}IIIIIIIIII", data, off
            )
        sections.append({
            "name_off": sh_name,
            "type": sh_type,
            "offset": sh_offset,
            "size": sh_size,
            "link": sh_link,
            "entsize": sh_entsize,
        })

    shstr_bytes = b""
    if 0 <= e_shstrndx < len(sections):
        s = sections[e_shstrndx]
        shstr_bytes = data[s["offset"] : s["offset"] + s["size"]]

    def read_cstr(buf: bytes, off: int) -> str:
        if off < 0 or off >= len(buf):
            return ""
        end = buf.find(b"\x00", off)
        if end == -1:
            end = len(buf)
        return buf[off:end].decode("utf-8", errors="replace")

    section_sizes: Dict[str, int] = {}
    has_symtab = False
    dynsym_count = 0
    dyn_section = None
    comment_strings: List[str] = []

    for s in sections:
        sname = read_cstr(shstr_bytes, s["name_off"])
        s["name"] = sname
        if sname:
            section_sizes[sname] = section_sizes.get(sname, 0) + s["size"]
        if s["type"] == SHT_SYMTAB:
            has_symtab = True
        elif s["type"] == SHT_DYNSYM and s["entsize"] > 0:
            dynsym_count = s["size"] // s["entsize"]
        elif s["type"] == SHT_DYNAMIC:
            dyn_section = s
        elif sname == ".comment" and s["size"] > 0 and s["offset"] + s["size"] <= len(data):
            raw_c = data[s["offset"] : s["offset"] + s["size"]]
            for part in raw_c.split(b"\x00"):
                txt = part.decode("utf-8", errors="ignore").strip()
                if txt and txt not in comment_strings:
                    comment_strings.append(txt[:120])

    # Parse DT_NEEDED & DT_SONAME from .dynamic
    dt_needed: List[str] = []
    dt_soname: str = ""
    if dyn_section is not None and 0 <= dyn_section["link"] < len(sections):
        dynstr_sec = sections[dyn_section["link"]]
        dynstr_buf = data[dynstr_sec["offset"] : dynstr_sec["offset"] + dynstr_sec["size"]]
        ent_sz = dyn_section["entsize"] or (16 if is_64 else 8)
        off = dyn_section["offset"]
        end_off = min(len(data), off + dyn_section["size"])
        while off + ent_sz <= end_off:
            if is_64:
                d_tag, d_val = struct.unpack_from(f"{endian}qQ", data, off)
            else:
                d_tag, d_val = struct.unpack_from(f"{endian}iI", data, off)
            off += ent_sz
            if d_tag == DT_NULL:
                break
            elif d_tag == DT_NEEDED:
                lib = read_cstr(dynstr_buf, d_val)
                if lib:
                    dt_needed.append(lib)
            elif d_tag == DT_SONAME:
                dt_soname = read_cstr(dynstr_buf, d_val)

    # Extract concise toolchain label from .comment
    toolchain = "Unknown / Stripped"
    for c in comment_strings:
        cl = c.lower()
        if "clang" in cl or "android" in cl:
            toolchain = c
            break
        if "gcc" in cl or "rustc" in cl or "go " in cl:
            toolchain = c
            break
    if toolchain == "Unknown / Stripped" and comment_strings:
        toolchain = comment_strings[0]

    abi = path.split("/")[1] if path.startswith("lib/") and len(path.split("/")) >= 3 else "unknown"

    return {
        "path": path,
        "filename": filename,
        "abi": abi,
        "valid": True,
        "category": classify_native_lib(filename),
        "bits": 64 if is_64 else 32,
        "machine": EM_NAMES.get(e_machine, f"EM_{e_machine}"),
        "file_size_bytes": len(data),
        "compressed": compressed,
        "compressed_size_bytes": compressed_size,
        "virtual_mem_bytes": pt_load_memsz,
        "pt_load_alignments": sorted(set(pt_load_aligns)),
        "min_pt_load_align": min_load_align,
        "max_pt_load_align": max_load_align,
        "elf_16kb_aligned": elf_16k_aligned,
        "zip_4kb_aligned": zip_aligned_4k,
        "zip_16kb_aligned": zip_aligned_16k,
        "stripped_symtab": not has_symtab,
        "dynsym_entries": dynsym_count,
        "soname": dt_soname,
        "dt_needed": dt_needed,
        "toolchain": toolchain,
        "sections_summary": {
            "text": section_sizes.get(".text", 0),
            "rodata": section_sizes.get(".rodata", 0),
            "data": section_sizes.get(".data", 0) + section_sizes.get(".data.rel.ro", 0),
            "bss": section_sizes.get(".bss", 0),
            "eh_frame": section_sizes.get(".eh_frame", 0) + section_sizes.get(".ARM.exidx", 0) + section_sizes.get(".ARM.extab", 0),
            "dynsym_dynstr": section_sizes.get(".dynsym", 0) + section_sizes.get(".dynstr", 0),
        },
    }
