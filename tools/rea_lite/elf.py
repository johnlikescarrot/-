"""Phase 3a: ELF32/ARM native-library inspection of every .so in the ABI split."""
from __future__ import annotations
import os, struct, zipfile, hashlib
from collections import Counter
from common import apk_paths, dump, sha256, entropy, ensure_extracted

PT = {0: 'NULL', 1: 'LOAD', 2: 'DYNAMIC', 3: 'INTERP', 4: 'NOTE', 5: 'SHLIB',
      6: 'PHDR', 7: 'TLS', 0x6474e550: 'GNU_EH_FRAME', 0x6474e551: 'GNU_STACK',
      0x6474e552: 'GNU_RELRO', 0x6474e553: 'GNU_PROPERTY'}
SHT = {0: 'NULL', 1: 'PROGBITS', 2: 'SYMTAB', 3: 'STRTAB', 4: 'RELA', 5: 'HASH',
       6: 'DYNAMIC', 7: 'NOTE', 8: 'NOBITS', 9: 'REL', 10: 'SHLIB', 11: 'DYNSYM',
       14: 'INIT_ARRAY', 15: 'FINI_ARRAY', 16: 'PREINIT_ARRAY', 17: 'GROUP',
       18: 'SYMTAB_SHNDX', 0x6ffffff6: 'GNU_HASH', 0x6ffffffd: 'GNU_verdef',
       0x6ffffffe: 'GNU_verneed', 0x6fffffff: 'GNU_versym', 0x70000001: 'ARM_EXIDX',
       0x70000003: 'ARM_ATTRIBUTES'}
DT = {1: 'NEEDED', 2: 'PLTRELSZ', 3: 'PLTGOT', 4: 'HASH', 5: 'STRTAB', 6: 'SYMTAB',
      7: 'RELA', 8: 'RELASZ', 9: 'RELAENT', 10: 'STRSZ', 11: 'SYMENT', 12: 'INIT',
      13: 'FINI', 14: 'SONAME', 15: 'RPATH', 16: 'SYMBOLIC', 17: 'REL', 18: 'RELSZ',
      19: 'RELENT', 20: 'PLTREL', 21: 'DEBUG', 22: 'TEXTREL', 23: 'JMPREL',
      24: 'BIND_NOW', 25: 'INIT_ARRAY', 26: 'FINI_ARRAY', 27: 'INIT_ARRAYSZ',
      28: 'FINI_ARRAYSZ', 29: 'RUNPATH', 30: 'FLAGS', 32: 'PREINIT_ARRAY',
      0x6ffffff0: 'VERSYM', 0x6ffffffc: 'VERDEF', 0x6ffffffd: 'VERDEFNUM',
      0x6ffffffe: 'VERNEED', 0x6fffffff: 'VERNEEDNUM', 0x6ffffff9: 'RELCOUNT',
      0x6ffffffb: 'FLAGS_1', 0x6000000d: 'ANDROID_REL', 0x6000000f: 'ANDROID_RELSZ',
      0x60000011: 'ANDROID_RELA', 0x60000012: 'ANDROID_RELASZ'}


def cstr(d, o):
    e = d.find(b'\x00', o)
    return d[o:e if e >= 0 else len(d)].decode('utf-8', 'replace')


def vaddr_to_off(loads, v):
    for lo, off, sz in loads:
        if lo <= v < lo + sz:
            return off + (v - lo)
    return None


def parse_elf(d: bytes, label: str):
    if d[:4] != b'\x7fELF': return {'file': label, 'error': 'not ELF'}
    cls, enc, ver = d[4], d[5], d[6]
    if cls != 1: return {'file': label, 'error': f'ELF class {cls} (expected 32-bit ARM)'}
    u16 = lambda o: struct.unpack_from('<H', d, o)[0]
    u32 = lambda o: struct.unpack_from('<I', d, o)[0]
    etype, machine, eversion, entry, phoff, shoff, flags = struct.unpack_from('<HHIIIII', d, 16)
    ehsize, phentsize, phnum, shentsize, shnum, shstrndx = struct.unpack_from('<HHHHHH', d, 40)
    phs, loads, dynamic = [], [], []
    for k in range(phnum):
        o = phoff + k * phentsize
        p_type, p_off, p_vaddr, p_paddr, p_filesz, p_memsz, p_fl, p_al = struct.unpack_from('<IIIIIIII', d, o)
        phs.append({'type': PT.get(p_type, hex(p_type)), 'offset': p_off, 'vaddr': p_vaddr,
                    'filesz': p_filesz, 'memsz': p_memsz,
                    'flags': ''.join(c for m, c in ((4, 'R'), (2, 'W'), (1, 'X')) if p_fl & m) or '-',
                    'align': p_al})
        if p_type == 1: loads.append((p_vaddr, p_off, p_filesz))
        if p_type == 2: dynamic.append((p_off, p_filesz))
    shs = []
    for k in range(shnum):
        o = shoff + k * shentsize
        v = struct.unpack_from('<IIIIIIIIII', d, o)
        shs.append({'name_off': v[0], 'type': SHT.get(v[1], hex(v[1])), 'flags': f'0x{v[2]:x}',
                    'addr': v[3], 'offset': v[4], 'size': v[5], 'link': v[6],
                    'info': v[7], 'align': v[8], 'entsize': v[9]})
    shstr = shs[shstrndx]['offset'] if shstrndx < len(shs) else 0
    for s in shs: s['name'] = cstr(d, shstr + s['name_off']) if shstr else ''
    secnames = Counter(s['name'] for s in shs if s['name'])
    # dynamic table
    needed, soname, dt_flags, dt_flags1, dynstr_off, dynstr_size = [], None, 0, 0, None, 0
    dyn_flags = {}
    for off, size in dynamic:
        p = off
        while p + 8 <= off + size:
            tag, val = struct.unpack_from('<iI', d, p); p += 8
            if tag == 0: break
            name = DT.get(tag, hex(tag))
            dyn_flags[name] = dyn_flags.get(name, 0) + 1
            if tag == 5: dynstr_off = vaddr_to_off(loads, val)
            elif tag == 10: dynstr_size = val
            elif tag == 30: dt_flags = val
            elif tag == 0x6ffffffb: dt_flags1 = val
        # second pass for strings once dynstr_off known
        if dynstr_off is not None:
            p = off
            while p + 8 <= off + size:
                tag, val = struct.unpack_from('<iI', d, p); p += 8
                if tag == 0: break
                if tag in (1, 14, 15, 29):
                    s = cstr(d, dynstr_off + val)
                    if tag == 1: needed.append(s)
                    elif tag == 14: soname = s
    # dynamic symbols
    dynsym_count, defined, undefined, jni, prefix_hist = 0, 0, 0, 0, Counter()
    for s in shs:
        if s['type'] != 'DYNSYM': continue
        stroff = shs[s['link']]['offset'] if s['link'] < len(shs) else 0
        n = s['size'] // max(1, s['entsize'] or 16)
        for k in range(min(n, 200000)):
            o = s['offset'] + k * (s['entsize'] or 16)
            nm, val, sz, info, other, shndx = struct.unpack_from('<IIIBBH', d, o)
            name = cstr(d, stroff + nm) if stroff else ''
            dynsym_count += 1
            if shndx == 0: undefined += 1
            else: defined += 1
            if name.startswith('Java_') or name.startswith('JNI_') or name == 'JNI_OnLoad': jni += 1
            if name:
                if name.startswith('_Z'): prefix_hist['C++ mangled (_Z)'] += 1
                elif name.startswith('Java_'): prefix_hist['JNI export (Java_)'] += 1
                elif name.startswith('JNI_'): prefix_hist['JNI export (JNI_)'] += 1
                else:
                    pre = name.split('_')[0][:18]
                    prefix_hist[pre] += 1
    # build-id
    build_id = None
    for s in shs:
        if s['name'] == '.note.gnu.build-id' or (s['type'] == 'NOTE' and s['size'] < 256):
            o = s['offset']
            try:
                namesz, descsz, ntype = struct.unpack_from('<III', d, o)
                nm = d[o + 12:o + 12 + namesz]
                desc = d[o + 12 + namesz:o + 12 + namesz + descsz]
                if b'GNU' in nm: build_id = desc.hex()
            except Exception: pass
    has_symtab = any(s['type'] == 'SYMTAB' for s in shs)
    gnu_stack = next((p for p in phs if p['type'] == 'GNU_STACK'), None)
    gnu_relro = next((p for p in phs if p['type'] == 'GNU_RELRO'), None)
    canary = any(k in prefix_hist for k in ('__stack_chk_fail', '__stack_chk'))
    security = {
        'pie_et_dyn': etype == 3,
        'nx_stack_non_executable': (gnu_stack is None) or ('X' not in gnu_stack['flags']),
        'relro_present': gnu_relro is not None,
        'full_relro_bind_now': bool(dt_flags & 0x8 or dt_flags1 & 0x1 or 'BIND_NOW' in dyn_flags),
        'stripped_no_symtab': not has_symtab,
        'stack_protector_import_seen': canary or 'DT_NEEDED' in dyn_flags and '__stack_chk_fail' in ' '.join(needed),
        'packed_upx_marker': b'UPX!' in d[:4096] or b'UPX0' in d[:4096],
    }
    text = next((s for s in shs if s['name'] == '.text'), None)
    text_entropy = entropy(d[text['offset']:text['offset'] + min(text['size'], 1 << 16)]) if text else None
    return {
        'file': label, 'size': len(d), 'sha256': sha256(d)[:16],
        'elf': {'class': 'ELF32', 'data': 'little-endian' if enc == 1 else 'big-endian',
                'type': {1: 'REL', 2: 'EXEC', 3: 'DYN (shared object)'}.get(etype, etype),
                'machine': machine, 'machine_name': {40: 'ARM', 8: 'MIPS', 3: 'x86', 183: 'AArch64'}.get(machine, str(machine)),
                'e_flags': f'0x{flags:08x}', 'eabi_version': (flags >> 24) & 0xFF,
                'entry_point': entry, 'program_header_count': phnum, 'section_count': shnum},
        'program_headers': phs,
        'sections': [{k: s[k] for k in ('name', 'type', 'size', 'flags', 'addr', 'offset')} for s in shs],
        'section_names': dict(secnames.most_common(30)),
        'dynamic': {'soname': soname, 'needed': needed, 'dt_flags': f'0x{dt_flags:08x}',
                    'dt_flags_1': f'0x{dt_flags1:08x}', 'tag_counts': dyn_flags},
        'symbols': {'dynsym_count': dynsym_count, 'defined': defined, 'undefined_imports': undefined,
                    'jni_exports': jni, 'prefix_histogram': dict(prefix_hist.most_common(25))},
        'build_id': build_id,
        'security_features': security,
        'text_section': {'size': text['size'] if text else 0, 'entropy_first_64k': text_entropy},
        'file_entropy_first_64k': entropy(d),
    }


def main():
    ensure_extracted()
    rows, agg = [], {'libraries': 0, 'total_bytes': 0, 'needed': Counter(),
                     'security': Counter(), 'section_names': Counter(), 'machines': Counter(),
                     'build_ids': 0, 'jni_exports': 0, 'dynsym_total': 0}
    for apk in apk_paths():
        label = os.path.basename(apk)
        with zipfile.ZipFile(apk) as z:
            for i in z.infolist():
                if not (i.filename.startswith('lib/') and i.filename.endswith('.so')):
                    continue
                b = z.read(i)
                r = parse_elf(b, label + ':' + i.filename)
                rows.append(r)
                agg['libraries'] += 1; agg['total_bytes'] += len(b)
                agg['machines'][r['elf']['machine_name']] += 1
                for n in r['dynamic']['needed']: agg['needed'][n] += 1
                for k, v in r['security_features'].items():
                    if v: agg['security'][k] += 1
                for k, v in r['section_names'].items(): agg['section_names'][k] += v
                agg['build_ids'] += 1 if r['build_id'] else 0
                agg['jni_exports'] += r['symbols']['jni_exports']
                agg['dynsym_total'] += r['symbols']['dynsym_count']
                print(os.path.basename(i.filename).ljust(38), str(len(b)).rjust(9),
                      r['elf']['machine_name'], 'imports', r['symbols']['undefined_imports'],
                      'JNI', r['symbols']['jni_exports'], 'buildid', (r['build_id'] or '-')[:12])
    dump('elf.json', {
        'schema': 'rea_lite/elf@1', 'per_library': rows,
        'aggregate': {'libraries': agg['libraries'], 'total_bytes': agg['total_bytes'],
                      'machines': dict(agg['machines']), 'build_id_count': agg['build_ids'],
                      'jni_exports_total': agg['jni_exports'], 'dynsym_total': agg['dynsym_total'],
                      'dt_needed_top': dict(agg['needed'].most_common(40)),
                      'security_feature_counts': dict(agg['security']),
                      'section_name_counts': dict(agg['section_names'].most_common(30))},
        'limits': ['Static ELF header/section/dynamic/symbol inspection only; no disassembly, no execution.',
                   'Opaque or protected payloads are described by entropy and structure, never decrypted or bypassed.',
                   'Security flags are presence checks, not vulnerability or exploitability claims.'],
    })


if __name__ == '__main__':
    main()
