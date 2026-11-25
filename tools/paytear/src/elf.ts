/**
 * ELF (Android .so) parser: identity, program headers, dynamic section,
 * exported JNI entry points, section entropy and binary-hardening posture
 * (PIE, NX, RELRO, BIND_NOW, stack canaries, FORTIFY).
 */
import { shannon, chiSquare, entropyProfile } from "./entropy.js";

export interface ElfProgramHeader {
  type: number;
  typeName: string;
  flags: number;
  offset: number;
  vaddr: number;
  filesz: number;
  memsz: number;
}

export interface ElfSymbol {
  name: string;
  value: number;
  size: number;
  bind: string;
  type: string;
  sectionIndex: number;
}

export interface ElfSection {
  name: string;
  type: number;
  offset: number;
  size: number;
  addr: number;
  entropy: number;
  chiSquare: number;
}

export interface ElfResult {
  file: string;
  bits: 32 | 64;
  machine: string;
  endian: "little" | "big";
  type: string;
  entry: number;
  programHeaders: ElfProgramHeader[];
  sections: ElfSection[];
  needed: string[];
  soname: string | null;
  rpath: string[];
  exportedFunctions: string[];
  jniExports: string[];
  symbolCount: number;
  buildId: string | null;
  comment: string | null;
  hardening: {
    pie: boolean;
    nx: boolean;
    relro: "none" | "partial" | "full";
    bindNow: boolean;
    canary: boolean;
    fortify: boolean;
    textRelocations: boolean;
  };
  size: number;
  textSize: number;
  highEntropyRegions: number;
  entropyProfile: number[];
}

const PT_NAMES: Record<number, string> = {
  0: "NULL", 1: "LOAD", 2: "DYNAMIC", 3: "INTERP", 4: "NOTE", 5: "SHLIB",
  6: "PHDR", 7: "TLS", 0x6474e550: "GNU_EH_FRAME", 0x6474e551: "GNU_STACK",
  0x6474e552: "GNU_RELRO", 0x6474e553: "GNU_P PROPERTY",
};

const SH_NAMES: Record<number, string> = {
  0: "NULL", 1: "PROGBITS", 2: "SYMTAB", 3: "STRTAB", 4: "RELA", 5: "HASH",
  6: "DYNAMIC", 7: "NOTE", 8: "NOBITS", 9: "REL", 10: "SHLIB", 11: "DYNSYM",
  14: "INIT_ARRAY", 15: "FINI_ARRAY", 16: "PREINIT_ARRAY", 17: "GROUP",
  18: "SYMTAB_SHNDX", 0x6ffffff6: "GNU_HASH", 0x6ffffffe: "VERNEED",
  0x6fffffff: "VERSYM", 0x6ffffffd: "VERDEF",
};

const MACHINES: Record<number, string> = {
  3: "x86", 8: "MIPS", 40: "ARM", 62: "x86-64", 183: "AArch64", 243: "RISC-V",
};

export function parseElf(buffer: Buffer, file: string): ElfResult {
  if (buffer.readUInt32BE(0) !== 0x7f454c46) throw new Error(`${file}: not an ELF`);
  const bits = buffer[4] === 2 ? 64 : 32;
  const endian = buffer[5] === 2 ? "big" : "little";
  if (endian !== "little") throw new Error(`${file}: big-endian ELF not supported`);
  const machine = MACHINES[buffer.readUInt16LE(18)] ?? `machine_${buffer.readUInt16LE(18)}`;
  const etype = buffer.readUInt16LE(16);
  const typeName = etype === 3 ? "DYN" : etype === 2 ? "EXEC" : String(etype);
  const entry = bits === 64 ? Number(buffer.readBigUInt64LE(24)) : buffer.readUInt32LE(24);
  const phoff = bits === 64 ? Number(buffer.readBigUInt64LE(32)) : buffer.readUInt32LE(28);
  const shoff = bits === 64 ? Number(buffer.readBigUInt64LE(40)) : buffer.readUInt32LE(32);
  const phentsize = buffer.readUInt16LE(bits === 64 ? 54 : 42);
  const phnum = buffer.readUInt16LE(bits === 64 ? 56 : 44);
  const shentsize = buffer.readUInt16LE(bits === 64 ? 58 : 46);
  const shnum = buffer.readUInt16LE(bits === 64 ? 60 : 48);
  const shstrndx = buffer.readUInt16LE(bits === 64 ? 62 : 50);

  const programHeaders: ElfProgramHeader[] = [];
  for (let i = 0; i < phnum; i++) {
    const o = phoff + i * phentsize;
    if (bits === 64) {
      programHeaders.push({
        type: buffer.readUInt32LE(o),
        typeName: PT_NAMES[buffer.readUInt32LE(o)] ?? `0x${buffer.readUInt32LE(o).toString(16)}`,
        flags: buffer.readUInt32LE(o + 4),
        offset: Number(buffer.readBigUInt64LE(o + 8)),
        vaddr: Number(buffer.readBigUInt64LE(o + 16)),
        filesz: Number(buffer.readBigUInt64LE(o + 32)),
        memsz: Number(buffer.readBigUInt64LE(o + 40)),
      });
    } else {
      programHeaders.push({
        type: buffer.readUInt32LE(o),
        typeName: PT_NAMES[buffer.readUInt32LE(o)] ?? `0x${buffer.readUInt32LE(o).toString(16)}`,
        flags: buffer.readUInt32LE(o + 24),
        offset: buffer.readUInt32LE(o + 4),
        vaddr: buffer.readUInt32LE(o + 8),
        filesz: buffer.readUInt32LE(o + 16),
        memsz: buffer.readUInt32LE(o + 20),
      });
    }
  }

  // section headers
  const rawSections: { nameOff: number; type: number; addr: number; offset: number; size: number }[] = [];
  for (let i = 0; i < shnum; i++) {
    const o = shoff + i * shentsize;
    if (bits === 64) {
      rawSections.push({
        nameOff: buffer.readUInt32LE(o), type: buffer.readUInt32LE(o + 4),
        addr: Number(buffer.readBigUInt64LE(o + 16)), offset: Number(buffer.readBigUInt64LE(o + 24)),
        size: Number(buffer.readBigUInt64LE(o + 32)),
      });
    } else {
      rawSections.push({
        nameOff: buffer.readUInt32LE(o), type: buffer.readUInt32LE(o + 4),
        addr: buffer.readUInt32LE(o + 12), offset: buffer.readUInt32LE(o + 16),
        size: buffer.readUInt32LE(o + 20),
      });
    }
  }
  const shstr = rawSections[shstrndx];
  const shstrData = shstr ? buffer.subarray(shstr.offset, shstr.offset + shstr.size) : Buffer.alloc(0);
  const sectionName = (off: number) => {
    const end = shstrData.indexOf(0, off);
    return shstrData.toString("latin1", off, end < 0 ? undefined : end);
  };

  const sections: ElfSection[] = rawSections.map((s) => {
    const data = buffer.subarray(s.offset, Math.min(s.offset + s.size, buffer.length));
    return {
      name: sectionName(s.nameOff) || `section_${s.type}`,
      type: s.type,
      offset: s.offset,
      size: s.size,
      addr: s.addr,
      entropy: s.type === 8 ? 0 : Number(shannon(data).toFixed(3)),
      chiSquare: s.type === 8 ? 0 : Math.round(chiSquare(data)),
    };
  });

  const text = sections.find((s) => s.name === ".text");
  const profile = text ? entropyProfile(buffer.subarray(text.offset, text.offset + text.size), 4096, 16384) : [];
  const highEntropy = profile.filter((e) => e > 7.2).length;

  // dynamic section
  const dynamic = programHeaders.find((p) => p.type === 2);
  const needed: string[] = [];
  const rpath: string[] = [];
  let soname: string | null = null;
  let bindNow = false;
  const strtabOffsets: number[] = [];
  if (dynamic) {
    const dyn = buffer.subarray(dynamic.offset, dynamic.offset + dynamic.filesz);
    const stride = bits === 64 ? 16 : 8;
    const tags: { tag: number; val: number }[] = [];
    for (let o = 0; o + stride <= dyn.length; o += stride) {
      const tag = bits === 64 ? Number(dyn.readBigInt64LE(o)) : dyn.readInt32LE(o);
      const val = bits === 64 ? Number(dyn.readBigUInt64LE(o + 8)) : dyn.readUInt32LE(o + 4);
      if (tag === 0) break;
      tags.push({ tag, val });
    }
    const strsz = tags.find((t) => t.tag === 10)?.val ?? 0;
    const straddr = tags.find((t) => t.tag === 5)?.val ?? 0;
    const strtab = dyn.subarray(straddr, straddr + strsz);
    const readStr = (off: number) => {
      const end = strtab.indexOf(0, off);
      return strtab.toString("latin1", off, end < 0 ? undefined : end);
    };
    for (const t of tags) {
      if (t.tag === 1) needed.push(readStr(t.val));
      else if (t.tag === 14) soname = readStr(t.val);
      else if (t.tag === 15 || t.tag === 29) rpath.push(readStr(t.val));
      else if (t.tag === 30 || t.tag === 0x6ffffffb) { if (t.val & 0x8) bindNow = true; }
    }
    strtabOffsets.push(straddr);
  }

  // dynamic symbols (JNI exports)
  const dynsym = sections.find((s) => s.name === ".dynsym");
  const dynstr = sections.find((s) => s.name === ".dynstr");
  const exportedFunctions: string[] = [];
  let symbolCount = 0;
  if (dynsym && dynstr) {
    const data = buffer.subarray(dynsym.offset, dynsym.offset + dynsym.size);
    const strData = buffer.subarray(dynstr.offset, dynstr.offset + dynstr.size);
    const entsize = bits === 64 ? 24 : 16;
    symbolCount = Math.floor(data.length / entsize);
    for (let o = 0; o + entsize <= data.length; o += entsize) {
      const nameOff = data.readUInt32LE(o);
      const info = data[o + (bits === 64 ? 4 : 12)];
      const shndx = data.readUInt16LE(o + (bits === 64 ? 6 : 14));
      if (shndx === 0) continue;
      const bind = info >> 4;
      const type = info & 0xf;
      if (type !== 2 && type !== 10) continue; // FUNC / GNU_IFUNC
      if (bind !== 1 && bind !== 2) continue; // GLOBAL / WEAK
      const end = strData.indexOf(0, nameOff);
      if (end < 0) continue;
      exportedFunctions.push(strData.toString("latin1", nameOff, end));
    }
  }

  const buildId = (() => {
    const note = sections.find((s) => s.name === ".note.gnu.build-id");
    if (!note) return null;
    const data = buffer.subarray(note.offset, note.offset + note.size);
    const nameSz = data.readUInt32LE(0);
    const descSz = data.readUInt32LE(4);
    return data.subarray(12 + nameSz, 12 + nameSz + descSz).toString("hex");
  })();

  const commentSection = sections.find((s) => s.name === ".comment");
  const comment = commentSection
    ? buffer.toString("latin1", commentSection.offset, commentSection.offset + Math.min(commentSection.size, 512)).split("\0").filter(Boolean).join(" | ")
    : null;

  const hasRelro = programHeaders.some((p) => p.type === 0x6474e552);
  const gnuStack = programHeaders.find((p) => p.type === 0x6474e551);
  const stackFlags = gnuStack ? gnuStack.flags : 0;
  const textRelocs = sections.some((s) => s.name === ".rel.dyn" || s.name === ".rela.dyn")
    ? programHeaders.some((p) => p.type === 2) && false
    : false;

  const jniExports = exportedFunctions.filter((f) => f.startsWith("Java_"));

  return {
    file,
    bits,
    machine,
    endian,
    type: typeName,
    entry,
    programHeaders,
    sections,
    needed,
    soname,
    rpath,
    exportedFunctions,
    jniExports,
    symbolCount,
    buildId,
    comment,
    hardening: {
      pie: typeName === "DYN",
      nx: stackFlags ? (stackFlags & 0x1) === 0 : true,
      relro: hasRelro ? (bindNow ? "full" : "partial") : "none",
      bindNow,
      canary: buffer.includes("__stack_chk_fail"),
      fortify: ["__memcpy_chk", "__sprintf_chk", "__strcpy_chk", "__fortify_chk", "__memset_chk"].some((s) => buffer.includes(s)),
      textRelocations: textRelocs,
    },
    size: buffer.length,
    textSize: text?.size ?? 0,
    highEntropyRegions: highEntropy,
    entropyProfile: profile,
  };
}
