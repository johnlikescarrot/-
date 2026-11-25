#!/usr/bin/env python3
"""
Stage 2b — Custom DEX constant-pool xref extractor (no external deps).

R8/ProGuard renaming hides intent in *identifiers*; the surviving signal is the
string constant pool. This module implements a minimal, dependency-free DEX
reader that walks class_data -> code_item and decodes `const-string`
(0x1a, 21c) and `const-string/jumbo` (0x1b, 31c) instructions, producing a
class -> strings index. That lets us attribute security-relevant literals
(SPKI pins, anti-tamper messages, API hosts) to concrete classes.

Output: analysis/data/04_dex_xref.json
"""
import os, re, json, struct, collections, sys

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
DATA = os.path.join(ROOT, "data")
WORK = os.environ.get("ZPAY_WORK", "/home/user/work")
DEXDIR = os.path.join(WORK, "apk", "vn.com.vng.zalopay")


def uleb128(buf, off):
    res = shift = 0
    while True:
        b = buf[off]
        off += 1
        res |= (b & 0x7F) << shift
        if not b & 0x80:
            return res, off
        shift += 7


class Dex:
    def __init__(self, path):
        self.path = path
        self.buf = buf = open(path, "rb").read()
        h = struct.unpack_from("<8sI20s20s", buf, 0)  # magic, checksum, sig, (file_size follows)
        self.magic = h[0]
        (self.file_size, self.header_size, self.endian_tag, self.link_size, self.link_off,
         self.map_off, self.string_ids_size, self.string_ids_off, self.type_ids_size,
         self.type_ids_off, self.proto_ids_size, self.proto_ids_off, self.field_ids_size,
         self.field_ids_off, self.method_ids_size, self.method_ids_off, self.class_defs_size,
         self.class_defs_off, self.data_size, self.data_off) = struct.unpack_from("<20I", buf, 32)
        self._strings = None
        self._types = None
        self._methods = None

    # ── string table ──────────────────────────────────────────────────────
    def strings(self):
        if self._strings is not None:
            return self._strings
        buf, out = self.buf, []
        for i in range(self.string_ids_size):
            off = struct.unpack_from("<I", buf, self.string_ids_off + 4 * i)[0]
            _, p = uleb128(buf, off)
            end = buf.index(b"\x00", p)
            out.append(buf[p:end].decode("utf-8", "replace"))
        self._strings = out
        return out

    def types(self):
        if self._types is not None:
            return self._types
        s = self.strings()
        self._types = [s[struct.unpack_from("<I", self.buf, self.type_ids_off + 4 * i)[0]]
                       for i in range(self.type_ids_size)]
        return self._types

    def methods(self):
        if self._methods is not None:
            return self._methods
        out = []
        for i in range(self.method_ids_size):
            cls, proto, name = struct.unpack_from("<HHI", self.buf, self.method_ids_off + 8 * i)
            out.append((cls, name))
        self._methods = out
        return out

    # ── class data walk ───────────────────────────────────────────────────
    def iter_code_items(self):
        buf = self.buf
        for i in range(self.class_defs_size):
            base = self.class_defs_off + 32 * i
            class_idx, flags, super_idx, ifaces, src, anno, cdata_off, sval = struct.unpack_from("<8I", buf, base)
            if not cdata_off:
                continue
            off = cdata_off
            sf, off = uleb128(buf, off)
            inf, off = uleb128(buf, off)
            dm, off = uleb128(buf, off)
            vm, off = uleb128(buf, off)
            for _ in range(sf + inf):           # encoded fields
                _, off = uleb128(buf, off)
                _, off = uleb128(buf, off)
            for _ in range(dm + vm):            # encoded methods
                mdiff, off = uleb128(buf, off)
                acc, off = uleb128(buf, off)
                code_off, off = uleb128(buf, off)
                if code_off:
                    yield i, class_idx, code_off


def scan_const_strings(dex: Dex):
    """→ list of (class_def_index, class_name, set(string_indices))"""
    buf = dex.buf
    types = dex.types()
    nstr = dex.string_ids_size
    out = []
    for cdi, class_idx, code_off in dex.iter_code_items():
        registers, ins, outs, tries, dbg, insns_size = struct.unpack_from("<HHHHII", buf, code_off)
        p = code_off + 16
        end = p + insns_size * 2
        found = set()
        # linear scan: const-string AA|1a, BBBB  /  const-string/jumbo AA|1b, BBBBBBBB
        while p + 4 <= end:
            w = struct.unpack_from("<H", buf, p)[0]
            op = w & 0xFF
            if op == 0x1A:
                idx = struct.unpack_from("<H", buf, p + 2)[0]
                if idx < nstr:
                    found.add(idx)
                p += 4
                continue
            if op == 0x1B:
                if p + 6 <= end:
                    idx = struct.unpack_from("<I", buf, p + 2)[0]
                    if idx < nstr:
                        found.add(idx)
                    p += 6
                    continue
            p += 2
        if found:
            out.append((cdi, types[class_idx], found))
    return out


SEC_RX = re.compile(
    r"frida|FRIDA|isEmulator|goldfish|ranchu|genymotion|bluestacks|qemu|superuser|/system/xbin/su|"
    r"test-keys|magisk|Magisk|xposed|Xposed|ptrace|/proc/self/maps|27042|/system/app/Superuser|"
    r"debugger|Debug\b|isDeviceRooted|root(ed)?\b|tamper|integrity|hook|instrument|signature|"
    r"emulator|detect|security|secure|jailbreak|cheat|overlay|screen.?record", re.I)


def main():
    dexfiles = sorted([f for f in os.listdir(DEXDIR) if re.fullmatch(r"classes\d*\.dex", f)],
                      key=lambda s: int(re.sub(r"\D", "", s) or 1))
    all_classes, per_dex = {}, []
    total_code_units = 0
    for fn in dexfiles:
        dex = Dex(os.path.join(DEXDIR, fn))
        strings = dex.strings()
        anchors = scan_const_strings(dex)
        total_code_units += sum(0 for _ in ())  # placeholder, computed below
        for cdi, name, idxs in anchors:
            key = (fn, name)
            # keep the 400 most informative literals per class
            keep = sorted(idxs, key=lambda i: len(strings[i]), reverse=True)[:400]
            all_classes.setdefault(key, set()).update(keep)
        per_dex.append({"file": fn, "classes_with_code_strings": len(anchors),
                        "string_ids": dex.string_ids_size, "methods": dex.method_ids_size})
        print(f"  [{fn}] {len(anchors):>6} classes w/ const-strings")

    # invert index to strings, and collect security-relevant attribution
    sec_classes = collections.defaultdict(set)
    pin_classes = collections.defaultdict(set)
    host_classes = collections.defaultdict(set)
    for (fn, cname), idxs in all_classes.items():
        dex = Dex(os.path.join(DEXDIR, fn))
        strings = dex.strings()
        for i in idxs:
            s = strings[i]
            if SEC_RX.search(s) and 3 < len(s) < 200:
                sec_classes[(fn, cname)].add(s)
            if re.fullmatch(r"[A-Za-z0-9+/]{43}=", s):
                pin_classes[(fn, cname)].add(s)
            if re.fullmatch(r"[a-z0-9.-]+\.(vn|com|net|io|me|app)", s, re.I):
                host_classes[(fn, cname)].add(s)

    def top(d, n=25, label=""):
        rows = sorted(d.items(), key=lambda kv: -len(kv[1]))[:n]
        return [{"dex": k[0], "class": k[1], "count": len(v),
                 "sample": sorted(v)[:6]} for k, v in rows]

    out = {
        "per_dex": per_dex,
        "security_string_classes": top(sec_classes, 40),
        "pin_classes": top(pin_classes, 12),
        "host_classes": top(host_classes, 20),
        "totals": {
            "classes_indexed": len(all_classes),
            "classes_with_security_literals": len(sec_classes),
            "classes_with_spki_pins": len(pin_classes),
            "distinct_pin_literals": len({s for v in pin_classes.values() for s in v}),
        },
    }
    with open(os.path.join(DATA, "04_dex_xref.json"), "w") as f:
        json.dump(out, f, indent=1, ensure_ascii=False)
    print("[stage2b] indexed", len(all_classes), "classes;", out["totals"])


if __name__ == "__main__":
    main()
