"""Pure-Python readers for Android binary formats (REA-style offline artifact readers).

No external deps: everything is derived from the bytes on disk so that each claim can be
traced to an offset + digest. Handles ResStringPool, ResXMLTree (AXML) and ResTable (ARSC).
"""
from __future__ import annotations
import struct, hashlib

# ResChunk_header types
RES_STRING_POOL_TYPE = 0x0001
RES_TABLE_TYPE = 0x0002
RES_XML_TYPE = 0x0003
RES_XML_START_NAMESPACE_TYPE = 0x0100
RES_XML_END_NAMESPACE_TYPE = 0x0101
RES_XML_START_ELEMENT_TYPE = 0x0102
RES_XML_END_ELEMENT_TYPE = 0x0103
RES_XML_CDATA_TYPE = 0x0104
RES_XML_RESOURCE_MAP_TYPE = 0x0180
RES_TABLE_PACKAGE_TYPE = 0x0200
RES_TABLE_TYPE_TYPE = 0x0201
RES_TABLE_TYPE_SPEC_TYPE = 0x0202
RES_TABLE_LIBRARY_TYPE = 0x0203

DATA_TYPES = {
    0x00: 'null', 0x01: 'reference', 0x02: 'attribute', 0x03: 'string',
    0x04: 'float', 0x05: 'dimension', 0x06: 'fraction', 0x07: 'dynamic_reference',
    0x08: 'dynamic_attribute', 0x10: 'int_dec', 0x11: 'int_hex', 0x12: 'int_boolean',
    0x1c: 'color_argb8', 0x1d: 'color_rgb8', 0x1e: 'color_argb4', 0x1f: 'color_rgb4',
}

FLAG_UTF8 = 0x00000100
FLAG_SORTED = 0x00000001
FLAG_COMPLEX = 0x0001
FLAG_PUBLIC = 0x0002
FLAG_WEAK_SYMBOL = 0x0004


class Cursor:
    __slots__ = ('d', 'o')
    def __init__(self, d: bytes, o: int = 0):
        self.d, self.o = d, o
    def u8(self):
        v = self.d[self.o]; self.o += 1; return v
    def u16(self):
        v = struct.unpack_from('<H', self.d, self.o)[0]; self.o += 2; return v
    def u32(self):
        v = struct.unpack_from('<I', self.d, self.o)[0]; self.o += 4; return v
    def i32(self):
        v = struct.unpack_from('<i', self.d, self.o)[0]; self.o += 4; return v
    def f32(self):
        v = struct.unpack_from('<f', self.d, self.o)[0]; self.o += 4; return v
    def take(self, n):
        v = self.d[self.o:self.o + n]; self.o += n; return v


def header(d: bytes, off: int):
    """(type, headerSize, chunkSize)"""
    return struct.unpack_from('<HHI', d, off)


def chunks(d: bytes, start: int, end: int):
    """Yield (type, headerSize, size, offset) for a chunk sequence."""
    off = start
    while off + 8 <= end:
        t, hs, sz = header(d, off)
        if sz < 8 or hs < 8 or off + sz > end + 4:
            return  # truncated / trailing padding: stop safely
        yield t, hs, sz, off
        off += sz
        if sz == 0:
            return


def _varlen8(c: Cursor):
    a = c.u8()
    if a & 0x80:
        return ((a & 0x7F) << 8) | c.u8()
    return a


def _varlen16(c: Cursor):
    a = c.u16()
    if a & 0x8000:
        return ((a & 0x7FFF) << 16) | c.u16()
    return a


def parse_string_pool(d: bytes, off: int):
    """Return {'strings': [...], 'count', 'style_count', 'flags', 'utf8', 'size', 'styles_start'}."""
    t, hs, sz = header(d, off)
    if t != RES_STRING_POOL_TYPE:
        raise ValueError(f'expected string pool at {off}, got type 0x{t:04x}')
    # Fixed fields sit immediately after the 8-byte ResChunk_header; the offset
    # array starts at headerSize (28 for a standard pool).
    c = Cursor(d, off + 8)
    count, style_count, flags, strings_start, styles_start = c.u32(), c.u32(), c.u32(), c.u32(), c.u32()
    c = Cursor(d, off + hs)
    offsets = [c.u32() for _ in range(count)]
    style_offsets = [c.u32() for _ in range(style_count)] if styles_start else []
    base = off + strings_start
    utf8 = bool(flags & FLAG_UTF8)
    strings = []
    for rel in offsets:
        c = Cursor(d, base + rel)
        try:
            if utf8:
                _varlen8(c)                  # UTF-16 length (unused)
                nbytes = _varlen8(c)         # UTF-8 byte length
                raw = c.take(nbytes)
                strings.append(raw.decode('utf-8', 'replace'))
            else:
                n = _varlen16(c)
                raw = c.take(n * 2)
                strings.append(raw.decode('utf-16-le', 'replace'))
        except (IndexError, struct.error):
            strings.append('')
    return {'strings': strings, 'count': count, 'style_count': style_count,
            'flags': flags, 'utf8': utf8, 'size': sz, 'styles_start': styles_start,
            'style_offset_count': len(style_offsets)}


def typed_value(c: Cursor, pool_strings):
    size = c.u16(); _res0 = c.u8(); dt = c.u8(); data = c.u32()
    name = DATA_TYPES.get(dt, f'type_0x{dt:02x}')
    out = {'type': name, 'data': data, 'size': size}
    if dt == 0x03:
        out['string'] = pool_strings[data] if 0 <= data < len(pool_strings) else None
    elif dt == 0x12:
        out['bool'] = bool(data)
    elif dt in (0x01, 0x02, 0x07, 0x08, 0x11):
        out['hex'] = f'0x{data:08x}'
    elif dt == 0x04:
        out['float'] = struct.unpack('<f', struct.pack('<I', data))[0]
    elif dt == 0x10:
        out['int'] = struct.unpack('<i', struct.pack('<I', data))[0]
    elif dt in (0x1c, 0x1d, 0x1e, 0x1f):
        out['color'] = f'#{data:08x}'
    return out


def parse_axml(d: bytes):
    """Parse a complete Android binary XML file into a tree + evidence counters."""
    t, hs, sz = header(d, 0)
    if t != RES_XML_TYPE:
        raise ValueError(f'not AXML (type=0x{t:04x})')
    pool, rmap, nodes, errors = None, [], [], []
    chunk_counts, elements, attr_names, elem_names = {}, [], {}, {}
    stack, root_children = [], []
    for ct, chs, csz, off in chunks(d, hs, min(sz, len(d))):
        chunk_counts[f'0x{ct:04x}'] = chunk_counts.get(f'0x{ct:04x}', 0) + 1
        if ct == RES_STRING_POOL_TYPE:
            try:
                pool = parse_string_pool(d, off)
            except Exception as e:
                errors.append(f'string pool @0x{off:x}: {e}')
        elif ct == RES_XML_RESOURCE_MAP_TYPE:
            c = Cursor(d, off + chs)
            rmap = [c.u32() for _ in range((csz - chs) // 4)]
        elif ct in (RES_XML_START_ELEMENT_TYPE, RES_XML_END_ELEMENT_TYPE,
                    RES_XML_START_NAMESPACE_TYPE, RES_XML_END_NAMESPACE_TYPE, RES_XML_CDATA_TYPE):
            c = Cursor(d, off + 8)
            line = c.u32(); comment = c.u32()
            if ct == RES_XML_START_ELEMENT_TYPE:
                ns_ref, name_ref = c.u32(), c.u32()
                attr_start, attr_size, attr_count = c.u16(), c.u16(), c.u16()
                _id, _cls, _style = c.u16(), c.u16(), c.u16()
                ps = pool['strings'] if pool else []
                name = ps[name_ref] if name_ref < len(ps) and name_ref != 0xFFFFFFFF else f'@{name_ref}'
                elem_names[name] = elem_names.get(name, 0) + 1
                attrs = []
                for k in range(attr_count):
                    aoff = off + 16 + attr_start + k * (attr_size or 20)
                    ac = Cursor(d, aoff)
                    ans, aname, araw = ac.u32(), ac.u32(), ac.u32()
                    tv = typed_value(ac, ps)
                    an = ps[aname] if aname < len(ps) and aname != 0xFFFFFFFF else f'@{aname}'
                    res_id = rmap[aname] if aname < len(rmap) else None
                    if araw != 0xFFFFFFFF and araw < len(ps):
                        tv['raw_string'] = ps[araw]
                    attrs.append({'name': an, 'ns': ans, 'resource_id': res_id, 'value': tv})
                    attr_names[an] = attr_names.get(an, 0) + 1
                node = {'tag': name, 'line': line, 'attrs': attrs, 'children': []}
                nodes.append(node)
                if stack:
                    stack[-1]['children'].append(node)
                else:
                    root_children.append(node)
                if ct == RES_XML_START_ELEMENT_TYPE:
                    stack.append(node)
                elements.append(node)
            elif ct == RES_XML_END_ELEMENT_TYPE:
                if stack:
                    stack.pop()
    return {'ok': not errors, 'size': len(d), 'declared_size': sz,
            'pool': pool, 'resource_map': rmap, 'tree': root_children,
            'element_count': len(elements), 'chunk_counts': chunk_counts,
            'element_names': elem_names, 'attribute_names': attr_names,
            'errors': errors}


def parse_arsc(d: bytes):
    """Parse resources.arsc into packages, type/config chunks and entry statistics."""
    t, hs, sz = header(d, 0)
    if t != RES_TABLE_TYPE:
        raise ValueError(f'not ARSC (type=0x{t:04x})')
    # ResTable_header = 8-byte chunk header + uint32 packageCount; the first
    # sub-chunk starts at headerSize (12), not headerSize+4.
    c = Cursor(d, 8)
    package_count = c.u32()
    gpool = None
    packages, errors = [], []
    off = hs
    for ct, chs, csz, coff in chunks(d, off, min(sz, len(d))):
        if ct == RES_STRING_POOL_TYPE:
            try:
                gpool = parse_string_pool(d, coff)
            except Exception as e:
                errors.append(f'global pool: {e}')
            continue
        if ct != RES_TABLE_PACKAGE_TYPE:
            continue
        pc = Cursor(d, coff + 8)   # id/name/pool offsets follow the chunk header
        pid = pc.u32()
        name_raw = pc.take(256)
        try:
            pname = name_raw.decode('utf-16-le', 'replace').split('\x00')[0]
        except Exception:
            pname = ''
        type_strings_off, last_public_type = pc.u32(), pc.u32()
        key_strings_off, last_public_key = pc.u32(), pc.u32()
        try:
            tsp = parse_string_pool(d, coff + type_strings_off)
            ksp = parse_string_pool(d, coff + key_strings_off)
        except Exception as e:
            errors.append(f'package 0x{pid:02x} pools: {e}')
            continue
        type_names, key_names = tsp['strings'], ksp['strings']
        type_chunks, spec_chunks = [], []
        # sub-chunks start after the package header (chs includes the 288-byte fixed header)
        for sct, schs, scsz, soff in chunks(d, coff + chs, min(coff + csz, len(d))):
            if sct == RES_TABLE_TYPE_SPEC_TYPE:
                sc = Cursor(d, soff + 8)
                tid, _r0, _r1, entry_count = sc.u8(), sc.u8(), sc.u16(), sc.u32()
                spec_chunks.append({'id': tid, 'entry_count': entry_count})
            elif sct == RES_TABLE_TYPE_TYPE:
                # ResTable_type: header(8) + id/res0/res1(4) + entryCount(4)
                # + entriesStart(4) + ResTable_config (starts at +20).
                sc = Cursor(d, soff + 8)
                tid = sc.u8(); sc.u8(); sc.u16()
                entry_count, entries_start = sc.u32(), sc.u32()
                conf_size = sc.u32()
                conf = d[soff + 20: soff + 20 + min(conf_size, 64)]
                # ResTable_config: size(4), mcc(2), mnc(2), language(2),
                # country(2), orientation(1), touchscreen(1), density(2),
                # keyboard/navigation/input(4), screenWidth/Height(4), sdk(2).
                lang = conf[8:10].decode('latin1') if len(conf) >= 10 else ''
                country = conf[10:12].decode('latin1') if len(conf) >= 12 else ''
                density = struct.unpack_from('<H', conf, 14)[0] if len(conf) >= 16 else 0
                sdk = struct.unpack_from('<H', conf, 24)[0] if len(conf) >= 26 else 0
                entry_offsets = [struct.unpack_from('<I', d, soff + schs + 4 * k)[0]
                                 for k in range(entry_count)]
                value_types, keys, string_lengths, ref_packages = {}, [], [], {}
                for eo in entry_offsets:
                    if eo == 0xFFFFFFFF:
                        continue
                    epos = soff + entries_start + eo
                    if epos + 8 > len(d):
                        continue
                    esize, eflags, ekey = struct.unpack_from('<HHI', d, epos)
                    kn = key_names[ekey] if ekey < len(key_names) else f'@{ekey}'
                    keys.append(kn)
                    if not (eflags & FLAG_COMPLEX):
                        vpos = epos + (esize or 8)
                        if vpos + 8 <= len(d):
                            _vs, _vr, vdt, vdata = struct.unpack_from('<HBBI', d, vpos)
                            vt = DATA_TYPES.get(vdt, f'0x{vdt:02x}')
                            value_types[vt] = value_types.get(vt, 0) + 1
                            if vdt == 0x03 and gpool and vdata < len(gpool['strings']):
                                string_lengths.append(len(gpool['strings'][vdata]))
                            if vdt in (0x01, 0x07) and vdata:
                                pk = (vdata >> 24) & 0xFF
                                ref_packages[f'0x{pk:02x}'] = ref_packages.get(f'0x{pk:02x}', 0) + 1
                type_chunks.append({
                    'type_id': tid,
                    'type_name': type_names[tid - 1] if 0 < tid <= len(type_names) else f'@{tid}',
                    'config': {'locale': (lang.strip('\x00') + ('-' + country.strip('\x00') if country.strip('\x00') else '')),
                               'language': lang.strip('\x00'), 'country': country.strip('\x00'),
                               'density': density, 'sdk': sdk, 'config_size': conf_size},
                    'entry_count': entry_count, 'defined_entries': len(keys),
                    'value_types': value_types,
                    'string_value_count': len(string_lengths),
                    'string_length_mean': round(sum(string_lengths) / max(1, len(string_lengths)), 2),
                    'string_length_max': max(string_lengths, default=0),
                    'reference_packages': ref_packages,
                    'key_names_sample': keys[:24],
                })
        packages.append({
            'id': pid, 'name': pname, 'chunk_size': csz, 'header_size': chs,
            'type_string_count': len(type_names), 'key_string_count': len(key_names),
            'type_names': type_names[:80], 'last_public_type': last_public_type,
            'last_public_key': last_public_key,
            'type_spec_chunks': spec_chunks, 'type_chunks': type_chunks,
        })
    return {'ok': not errors, 'size': len(d), 'declared_size': sz,
            'package_count': package_count,
            'global_pool': {'count': gpool['count'] if gpool else 0,
                            'utf8': gpool['utf8'] if gpool else None,
                            'style_count': gpool['style_count'] if gpool else 0} if gpool else None,
            'packages': packages, 'errors': errors}
