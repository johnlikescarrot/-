"""Minimal binary Android XML (AXML) parser — pure Python, no deps."""
import struct, sys

class StrPool:
    def __init__(self, data, off):
        ctype, hsize, size = struct.unpack_from('<HHI', data, off)
        assert ctype == 0x0001, hex(ctype)
        scount, stcount, flags, sstart, ststart = struct.unpack_from('<IIIII', data, off+8)
        self.utf8 = bool(flags & 0x100)
        self.strings = []
        offs = struct.unpack_from('<%dI' % scount, data, off+hsize)
        base = off + sstart
        for o in offs:
            p = base + o
            if self.utf8:
                # UTF-8 pool: utf16-len prefix, then utf8-len prefix, then bytes
                n16 = data[p]; p += 1
                if n16 & 0x80: p += 1
                n = data[p]; p += 1
                if n & 0x80: n = ((n & 0x7f) << 8) | data[p]; p += 1
                s = data[p:p+n].decode('utf-8', 'replace')
            else:
                n = struct.unpack_from('<H', data, p)[0]; p += 2
                if n & 0x8000:
                    n = ((n & 0x7fff) << 16) | struct.unpack_from('<H', data, p)[0]; p += 2
                s = data[p:p+n*2].decode('utf-16-le', 'replace')
            self.strings.append(s)
        self.size = size
    def get(self, i):
        return self.strings[i] if 0 <= i < len(self.strings) else None

def parse_axml(path):
    data = open(path, 'rb').read()
    assert data[:4] == b'\x03\x00\x08\x00', 'not AXML'
    pool = None
    events = []  # (kind, ns, name, attrs)
    off = 8
    while off < len(data):
        ctype, hsize, size = struct.unpack_from('<HHI', data, off)
        if size == 0: break
        if ctype == 0x0001:
            pool = StrPool(data, off)
        elif ctype == 0x0102:  # start element
            ln, comment, ns, name = struct.unpack_from('<IIII', data, off+8)
            astart, asize, acount, ididx, clidx, stidx = struct.unpack_from('<HHHHHH', data, off+24)
            attrs = []
            ap = off + 16 + astart  # attributeStart is relative to ResXMLTree_attrExt
            for _ in range(acount):
                ans, aname, raw, vs, vres, vtype, vdata = struct.unpack_from('<IIIHBBI', data, ap)
                an = pool.get(aname) if pool else None
                if vtype == 0x03:
                    val = pool.get(vdata)
                elif vtype == 0x10:
                    val = vdata if vdata < 0x80000000 else vdata - 0x100000000
                elif vtype == 0x12:
                    val = 'true' if vdata else 'false'
                elif vtype == 0x01:
                    val = '@%08x' % vdata
                elif vtype == 0x02:
                    val = '?%08x' % vdata
                else:
                    val = '0x%x' % vdata
                attrs.append((an, val))
                ap += asize
            events.append(('start', pool.get(ns), pool.get(name), attrs))
        elif ctype == 0x0103:
            ln, comment, ns, name = struct.unpack_from('<IIII', data, off+8)
            events.append(('end', pool.get(ns), pool.get(name), None))
        off += size
    return events

def to_xml(events):
    out, depth = [], 0
    for kind, ns, name, attrs in events:
        if kind == 'start':
            a = ''.join(' %s="%s"' % (k, str(v if v is not None else '').replace('"','&quot;')) for k, v in attrs if k)
            out.append('  '*depth + '<%s%s>' % (name, a))
            depth += 1
        else:
            depth -= 1
            out.append('  '*depth + '</%s>' % name)
    return '\n'.join(out)

if __name__ == '__main__':
    ev = parse_axml(sys.argv[1])
    print(to_xml(ev))
