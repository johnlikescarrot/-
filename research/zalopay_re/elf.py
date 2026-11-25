"""ELF reader for the native payload (config.armeabi_v7a split).

Implements just enough of the ELF spec to answer the hardening / provenance
questions a mobile-security reviewer asks about a shipped ``lib*.so``:

    * ABI class + machine + endianness
    * PIE / ET_DYN, entry point, load segments
    * GNU_STACK flags  -> non-executable stack (NX)
    * GNU_RELRO + BIND_NOW -> full RELRO
    * undefined ``__stack_chk_fail`` -> stack protector
    * ``__cxa_finalize`` / ``_ITM_*`` -> C++ unwind / fortify
    * DT_NEEDED graph (which third-party .so each library expects)
    * exported ``Java_*`` JNI entry points and their count
    * ``.comment`` compiler banner, ``.note.gnu.build-id``
    * section-level entropy (packed / encrypted sections stand out)

Reference: Tool Interface Standard (TIS) ELF v1.2, gABI, and
"ELF Handling For Thread-Local Storage" for the PT_GNU_* extensions.
"""

from __future__ import annotations

import hashlib
import struct
from collections import Counter
from dataclasses import dataclass, field
from typing import Dict, List, Optional, Tuple

PT_LOAD, PT_DYNAMIC, PT_NOTE, PT_GNU_STACK, PT_GNU_RELRO, PT_GNU_EH_FRAME = 1, 2, 4, 0x6474E551, 0x6474E552, 0x6474E550
DT_NULL, DT_NEEDED, DT_SONAME, DT_RPATH, DT_RUNPATH, DT_FLAGS, DT_FLAGS_1, DT_BIND_NOW = 0, 1, 14, 15, 29, 30, 0x6FFFFFFB, 24
DF_BIND_NOW, DF_1_NOW = 0x8, 0x1
EM_386, EM_ARM, EM_X86_64, EM_AARCH64 = 3, 40, 62, 183
MACHINES = {EM_386: "x86", EM_ARM: "arm", EM_X86_64: "x86-64", EM_AARCH64: "aarch64"}


@dataclass
class ElfInfo:
    name: str
    size: int
    sha256: str
    elf_class: int = 0
    bits: int = 0
    endian: str = "little"
    machine: str = "?"
    e_type: int = 0
    is_pie: bool = False
    entry: int = 0
    nx_stack: Optional[bool] = None
    relro: bool = False
    bind_now: bool = False
    full_relro: bool = False
    stack_canary: bool = False
    fortify: bool = False
    needed: List[str] = field(default_factory=list)
    soname: Optional[str] = None
    rpath: List[str] = field(default_factory=list)
    jni_symbols: List[str] = field(default_factory=list)
    dynsym_count: int = 0
    exported_count: int = 0
    stripped: Optional[bool] = None
    compiler: Optional[str] = None
    build_id: Optional[str] = None
    section_entropy: Dict[str, float] = field(default_factory=dict)
    max_section_entropy: float = 0.0
    high_entropy_sections: List[str] = field(default_factory=list)
    sections: int = 0
    debug_sections: List[str] = field(default_factory=list)

    def to_json(self) -> dict:
        d = dict(self.__dict__)
        d["hardening_score"] = self.hardening_score()
        return d

    def hardening_score(self) -> int:
        """0-6: a crude but reproducible hardening index (Android NDK defaults
        already give 4/6 on modern toolchains, so 6/6 means *deliberately*
        hardened and <3 means an old or unusual lib)."""
        return sum([self.is_pie, bool(self.nx_stack), self.relro, self.bind_now,
                    self.stack_canary, self.fortify])


def shannon_entropy(blob: bytes) -> float:
    if not blob:
        return 0.0
    counts = Counter(blob)
    n = len(blob)
    import math
    return -sum((c / n) * math.log2(c / n) for c in counts.values())


def parse_elf(name: str, data: bytes, section_names: int = 12) -> ElfInfo:
    info = ElfInfo(name=name, size=len(data), sha256=hashlib.sha256(data).hexdigest())
    if data[:4] != b"\x7fELF":
        raise ValueError(f"{name}: not ELF")
    ei_class, ei_data = data[4], data[5]
    info.elf_class = ei_class
    info.bits = 32 if ei_class == 1 else 64
    info.endian = "little" if ei_data == 1 else "big"
    end = "<" if ei_data == 1 else ">"
    is64 = info.bits == 64

    if is64:
        (info.e_type, machine, _ver, info.entry, phoff, shoff, _flags, _ehsize,
         phentsize, phnum, shentsize, shnum, shstrndx) = struct.unpack_from(end + "HHIQQQIHHHHHH", data, 16)
    else:
        (info.e_type, machine, _ver, info.entry, phoff, shoff, _flags, _ehsize,
         phentsize, phnum, shentsize, shnum, shstrndx) = struct.unpack_from(end + "HHIIIIIHHHHHH", data, 16)
    info.machine = MACHINES.get(machine, f"EM_{machine}")
    info.is_pie = info.e_type == 3  # ET_DYN

    segs: List[Tuple[int, int, int, int, int, int]] = []  # type, off, vaddr, filesz, flags, align
    for i in range(phnum):
        off = phoff + i * phentsize
        if is64:
            p_type, p_flags, p_offset, p_vaddr, _paddr, p_filesz, _memsz, _align = struct.unpack_from(
                end + "IIQQQQQQ", data, off)
        else:
            p_type, p_offset, p_vaddr, _paddr, p_filesz, _memsz, p_flags, _align = struct.unpack_from(
                end + "IIIIIIII", data, off)
        segs.append((p_type, p_offset, p_vaddr, p_filesz, p_flags, _align))
        if p_type == PT_GNU_STACK:
            info.nx_stack = not bool(p_flags & 0x1)   # PF_X
        elif p_type == PT_GNU_RELRO:
            info.relro = True

    dyn_off = dyn_size = None
    for p_type, p_offset, _vaddr, p_filesz, _flags, _a in segs:
        if p_type == PT_DYNAMIC:
            dyn_off, dyn_size = p_offset, p_filesz

    if dyn_off is not None:
        entsize = 16 if is64 else 8
        n = dyn_size // entsize
        for i in range(n):
            off = dyn_off + i * entsize
            if is64:
                d_tag, d_val = struct.unpack_from(end + "qQ", data, off)
            else:
                d_tag, d_val = struct.unpack_from(end + "iI", data, off)
            if d_tag == DT_NULL:
                break
            if d_tag == DT_NEEDED:
                info.needed.append(f"@strtab:{d_val}")
            elif d_tag == DT_SONAME:
                info.soname = f"@strtab:{d_val}"
            elif d_tag in (DT_RPATH, DT_RUNPATH):
                info.rpath.append(f"@strtab:{d_val}")
            elif d_tag == DT_BIND_NOW:
                info.bind_now = True
            elif d_tag == DT_FLAGS and d_val & DF_BIND_NOW:
                info.bind_now = True
            elif d_tag == DT_FLAGS_1 and d_val & DF_1_NOW:
                info.bind_now = True
    info.full_relro = info.relro and info.bind_now

    # ---- sections: names, entropy, dynsym census, .comment, build-id -----
    shdrs = []
    for i in range(shnum):
        off = shoff + i * shentsize
        if is64:
            sh_name, sh_type, sh_flags, sh_addr, sh_offset, sh_size, sh_link, sh_info, sh_align, sh_entsize = struct.unpack_from(
                end + "IIQQQQIIQQ", data, off)
        else:
            sh_name, sh_type, sh_flags, sh_addr, sh_offset, sh_size, sh_link, sh_info, sh_align, sh_entsize = struct.unpack_from(
                end + "IIIIIIIIII", data, off)
        shdrs.append(dict(name_off=sh_name, type=sh_type, flags=sh_flags, offset=sh_offset,
                          size=sh_size, link=sh_link, entsize=sh_entsize))
    info.sections = len(shdrs)

    shstr = b""
    if shstrndx < len(shdrs):
        s = shdrs[shstrndx]
        shstr = data[s["offset"]:s["offset"] + s["size"]]

    def sname(idx_off: int) -> str:
        e = shstr.find(b"\x00", idx_off)
        return shstr[idx_off:e].decode("utf-8", "replace")

    ents = []
    for sh in shdrs:
        nm = sname(sh["name_off"]) if shstr else ""
        ents.append((nm, sh))
        if nm.startswith(".debug") or nm == ".symtab":
            if nm == ".symtab":
                info.stripped = False
            info.debug_sections.append(nm)
        if sh["size"] >= 4096 and sh["type"] != 8:  # not NOBITS
            blob = data[sh["offset"]:sh["offset"] + sh["size"]]
            e = shannon_entropy(blob)
            if e >= info.max_section_entropy:
                info.max_section_entropy = e
            if e > 7.2:
                info.high_entropy_sections.append(nm)
            if len(info.section_entropy) < section_names * 4:
                info.section_entropy[nm] = round(e, 3)
    if info.stripped is None:
        info.stripped = True

    # .comment => compiler banner
    for nm, sh in ents:
        if nm == ".comment":
            raw = data[sh["offset"]:sh["offset"] + sh["size"]]
            txt = [t for t in raw.split(b"\x00") if t.strip()]
            if txt:
                info.compiler = txt[0].decode("utf-8", "replace")

    # .note.gnu.build-id
    for nm, sh in ents:
        if "build-id" in nm:
            raw = data[sh["offset"]:sh["offset"] + sh["size"]]
            if len(raw) > 16:
                namesz, descsz, ntype = struct.unpack_from(end + "III", raw, 0)
                desc = raw[12 + ((namesz + 3) // 4) * 4:][:descsz]
                info.build_id = desc.hex()

    # dynamic symbol table: JNI exports + canary detection + strtab resolution
    dynsym = dynstr = None
    for nm, sh in ents:
        if nm == ".dynsym":
            import itertools
            # strtab is the section referenced by sh_link
            dynsym = sh
            if sh["link"] < len(shdrs):
                dynstr = shdrs[sh["link"]]
    symbols: List[Tuple[str, int, int, int]] = []
    if dynsym and dynstr:
        strtab = data[dynstr["offset"]:dynstr["offset"] + dynstr["size"]]
        n = dynsym["size"] // (24 if is64 else 16)
        info.dynsym_count = n
        for i in range(n):
            off = dynsym["offset"] + i * (24 if is64 else 16)
            if is64:
                st_name, st_info, st_other, st_shndx, st_value, st_size = struct.unpack_from(end + "IBBHQQ", data, off)
            else:
                st_name, st_value, st_size, st_info, st_other, st_shndx = struct.unpack_from(end + "IIIBBH", data, off)
            e = strtab.find(b"\x00", st_name)
            sym = strtab[st_name:e].decode("utf-8", "replace") if st_name else ""
            if not sym:
                continue
            undefined = st_shndx == 0
            exported = (not undefined) and ((st_info >> 4) in (1, 2)) and ((st_info & 0xF) in (1, 2))
            if exported:
                info.exported_count += 1
            symbols.append((sym, st_value, st_size, st_shndx))

    # resolve @strtab: placeholders (DT_NEEDED/RPATH) via the dynamic strtab
    strtab_blobs = []
    for nm, sh in ents:
        if nm == ".dynstr":
            strtab_blobs.append(data[sh["offset"]:sh["offset"] + sh["size"]])

    def resolve(v: str) -> str:
        if not v.startswith("@strtab:"):
            return v
        off = int(v.split(":", 1)[1])
        for blob in strtab_blobs:
            if off < len(blob):
                e = blob.find(b"\x00", off)
                return blob[off:e].decode("utf-8", "replace")
        return v

    info.needed = [resolve(x) for x in info.needed]
    if info.soname:
        info.soname = resolve(info.soname)
    info.rpath = [resolve(x) for x in info.rpath]

    for sym, _v, _s, shndx in symbols:
        if sym.startswith("Java_") and shndx != 0:
            if len(info.jni_symbols) < 200:
                info.jni_symbols.append(sym)
        if sym == "__stack_chk_fail":
            info.stack_canary = True
        if sym in ("__memcpy_chk", "__sprintf_chk", "__vsnprintf_chk", "__strcpy_chk"):
            info.fortify = True
    return info
