"""ELF probing for Android native libraries (`.so`), with JNI surface extraction.

Answers four questions per library without a debugger:

1. What ABI / file type is it?
2. What does it export to the JVM (`JNI_OnLoad`, `Java_*` stubs)?
3. What does it import from libc / other DSOs?
4. Is it plausibly packed or stripped? (symbol density + section entropy)
"""

from __future__ import annotations

import io
import math
import re
from collections import Counter
from dataclasses import dataclass, field
from typing import Dict, List

from elftools.elf.elffile import ELFFile

JNI_NAME_RE = re.compile(r"^Java_[A-Za-z0-9_]+$")
INTERESTING = re.compile(
    rb"(frida|xposed|magisk|ptrace|TracerPid|/proc/self/(status|maps)|"
    rb"su\b|Superuser|emulator|goldfish|ranchu|anti|tamper|integrity|"
    rb"rootBeer|isDebugger|SSL_CTX_new|EVP_|AES_|RSA_|HMAC|/system/bin)",
    re.IGNORECASE,
)


def shannon_entropy(data: bytes) -> float:
    if not data:
        return 0.0
    freq = Counter(data)
    n = len(data)
    return -sum((c / n) * math.log2(c / n) for c in freq.values())


@dataclass
class ElfReport:
    name: str
    size: int
    machine: str = ""
    elf_class: int = 0
    elf_type: str = ""
    soname: str = ""
    needed: List[str] = field(default_factory=list)
    jni_onload: bool = False
    java_exports: List[str] = field(default_factory=list)
    export_count: int = 0
    import_count: int = 0
    notable_imports: List[str] = field(default_factory=list)
    section_entropy: Dict[str, float] = field(default_factory=dict)
    text_entropy: float = 0.0
    symbol_density: float = 0.0
    stripped: bool = False
    packed_likelihood: str = "low"
    interesting_strings: List[str] = field(default_factory=list)

    def as_dict(self) -> Dict[str, object]:
        return dict(self.__dict__)


def analyse_elf(name: str, blob: bytes) -> ElfReport:
    rep = ElfReport(name=name, size=len(blob))
    stream = io.BytesIO(blob)
    elf = ELFFile(stream)
    header = elf.header
    rep.machine = header["e_machine"]
    rep.elf_class = elf.elfclass
    rep.elf_type = header["e_type"]

    # ---- dynamic section: SONAME / NEEDED
    dyn = elf.get_section_by_name(".dynamic")
    if dyn is not None:
        for tag in dyn.iter_tags():
            if tag.entry.d_tag == "DT_SONAME":
                rep.soname = tag.soname
            elif tag.entry.d_tag == "DT_NEEDED":
                rep.needed.append(tag.needed)

    # ---- symbol tables
    exports: List[str] = []
    imports: List[str] = []
    for sec_name in (".dynsym", ".symtab"):
        sec = elf.get_section_by_name(sec_name)
        if sec is None:
            continue
        for sym in sec.iter_symbols():
            if not sym.name:
                continue
            if sym["st_shndx"] == "SHN_UNDEF":
                imports.append(sym.name)
            elif sym["st_info"]["bind"] == "STB_GLOBAL" or sym["st_info"]["bind"] == "STB_WEAK":
                exports.append(sym.name)
    rep.export_count = len(exports)
    rep.import_count = len(imports)
    rep.java_exports = sorted({e for e in exports if JNI_NAME_RE.match(e)})[:40]
    rep.jni_onload = any(e in ("JNI_OnLoad", "JNI_OnUnload") for e in exports)
    notable = ("ptrace", "dlopen", "dlsym", "system", "exec", "fork", "socket", "open", "mmap", "prctl")
    rep.notable_imports = sorted({i for i in imports if any(k in i.lower() for k in notable)})[:30]
    rep.stripped = elf.get_section_by_name(".symtab") is None

    # ---- section entropy
    for sec_name in (".text", ".rodata", ".data", ".got"):
        sec = elf.get_section_by_name(sec_name)
        if sec is None:
            continue
        try:
            raw = sec.data()
        except Exception:
            continue
        if raw:
            rep.section_entropy[sec_name] = round(shannon_entropy(raw[:4_000_000]), 4)
    rep.text_entropy = rep.section_entropy.get(".text", 0.0)
    rep.symbol_density = round((rep.export_count + rep.import_count) / max(1, rep.size) * 1e6, 3)

    # ---- packing heuristic: high .text entropy + very low symbol density
    score = 0
    if rep.text_entropy > 7.4:
        score += 1
    if rep.symbol_density < 25:
        score += 1
    if not rep.java_exports and rep.jni_onload:
        score += 1
    rep.packed_likelihood = {0: "low", 1: "low", 2: "medium", 3: "high"}[score]

    # ---- interesting strings (sampled, bounded)
    found = set()
    for m in INTERESTING.finditer(blob[:8_000_000]):
        s = m.group(0).decode("latin-1")
        found.add(s)
        if len(found) >= 25:
            break
    rep.interesting_strings = sorted(found)
    return rep
