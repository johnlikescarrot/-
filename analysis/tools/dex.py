"""Minimal DEX parser: class inventory + string extraction. Pure Python."""
import struct, sys, re
from collections import Counter

def uleb128(data, off):
    r = 0; s = 0
    while True:
        b = data[off]; off += 1
        r |= (b & 0x7f) << s
        if not (b & 0x80): break
        s += 7
    return r, off

def parse_dex(path):
    data = open(path, 'rb').read()
    assert data[:3] == b'dex', path
    (file_size, header_size, endian_tag, link_size, link_off, map_off,
     sids, sids_off, tids, tids_off, pids, pids_off, fids, fids_off,
     mids, mids_off, cdefs, cdefs_off, data_size, data_off) = struct.unpack_from('<20I', data, 32)
    strings = []
    for i in range(sids):
        (soff,) = struct.unpack_from('<I', data, sids_off + i*4)
        n, p = uleb128(data, soff)
        raw = data[p:p+n*3+1]
        end = raw.find(b'\x00')
        s = raw[:end if end>=0 else len(raw)].decode('utf-8', 'replace')
        strings.append(s)
    types = []
    for i in range(tids):
        (didx,) = struct.unpack_from('<I', data, tids_off + i*4)
        types.append(strings[didx] if didx < len(strings) else '')
    classes = []
    for i in range(cdefs):
        (cidx,) = struct.unpack_from('<I', data, cdefs_off + i*32)
        classes.append(types[cidx] if cidx < len(types) else '')
    return {'file_size': file_size, 'strings': strings, 'classes': classes,
            'string_count': sids, 'type_count': tids, 'class_count': cdefs,
            'method_count': mids, 'field_count': fids}

if __name__ == '__main__':
    r = parse_dex(sys.argv[1])
    print('size=%d strings=%d types=%d classes=%d methods=%d fields=%d' % (
        r['file_size'], r['string_count'], r['type_count'], r['class_count'],
        r['method_count'], r['field_count']))
    for c in r['classes'][:10]: print(' ', c)
