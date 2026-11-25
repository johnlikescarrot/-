"""Minimal, fast DEX parser: headers, string/type/method/field id tables, class defs."""
import struct, sys, json, os

def uleb128(b, off):
    res=0; shift=0
    while True:
        x=b[off]; off+=1
        res |= (x & 0x7f) << shift
        if not x & 0x80: break
        shift += 7
    return res, off

def mutf8(b):
    try: return b.decode('utf-8')
    except UnicodeDecodeError: return b.decode('utf-8','replace')

class Dex:
    def __init__(self, path):
        self.path=path
        with open(path,'rb') as f: self.b=f.read()
        b=self.b
        self.magic=b[0:8]
        (self.checksum,)=struct.unpack_from('<I', b, 8)
        self.signature=b[12:32].hex()
        (self.file_size, self.header_size, self.endian_tag, self.link_size, self.link_off,
         self.map_off, self.string_ids_size, self.string_ids_off, self.type_ids_size,
         self.type_ids_off, self.proto_ids_size, self.proto_ids_off, self.field_ids_size,
         self.field_ids_off, self.method_ids_size, self.method_ids_off, self.class_defs_size,
         self.class_defs_off, self.data_size, self.data_off)=struct.unpack_from('<20I', b, 32)
        self._strings=None
    def strings(self):
        if self._strings is not None: return self._strings
        b=self.b; out=[]
        for i in range(self.string_ids_size):
            (off,)=struct.unpack_from('<I', b, self.string_ids_off+4*i)
            n, p = uleb128(b, off)
            out.append(mutf8(b[p:p+n]))
        self._strings=out; return out
    def type_ids(self):
        b=self.b; s=self.strings()
        return [struct.unpack_from('<I', b, self.type_ids_off+4*i)[0] for i in range(self.type_ids_size)]
    def class_defs(self):
        b=self.b; s=self.strings(); t=self.type_ids()
        out=[]
        for i in range(self.class_defs_size):
            o=self.class_defs_off+32*i
            class_idx, access, super_idx, ifaces_off, src_idx, anns_off, cdata_off, static_values_off = struct.unpack_from('<8I', b, o)
            out.append(dict(
                name=s[t[class_idx]] if class_idx < len(t) else None,
                access=access,
                superclass=(s[t[super_idx]] if super_idx!=0xffffffff and super_idx < len(t) else None),
                source=(s[src_idx] if src_idx!=0xffffffff else None),
                class_data_off=cdata_off))
        return out
    def method_ids(self):
        b=self.b; s=self.strings(); t=self.type_ids()
        out=[]
        for i in range(self.method_ids_size):
            class_idx, proto_idx, name_idx = struct.unpack_from('<HHI', b, self.method_ids_off+8*i)
            out.append((s[t[class_idx]], s[name_idx]))
        return out
    def field_ids(self):
        b=self.b; s=self.strings(); t=self.type_ids()
        out=[]
        for i in range(self.field_ids_size):
            class_idx, type_idx, name_idx = struct.unpack_from('<HHI', b, self.field_ids_off+8*i)
            out.append((s[t[class_idx]], s[name_idx]))
        return out
    def stats(self):
        return dict(path=self.path, magic=self.magic.hex(), file_size=self.file_size,
                    header_size=self.header_size, strings=self.string_ids_size,
                    types=self.type_ids_size, protos=self.proto_ids_size,
                    fields=self.field_ids_size, methods=self.method_ids_size,
                    classes=self.class_defs_size, data_size=self.data_size)

if __name__=='__main__':
    d=Dex(sys.argv[1]); print(json.dumps(d.stats(), indent=1))
