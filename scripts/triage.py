#!/usr/bin/env python3
"""P1+P2 triage: hashes, XAPK manifest, AXML manifest, DEX inventory, native libs, assets.
Stdlib only. Outputs research/*.json + research/01-triage.md"""
import hashlib, io, json, os, re, struct, subprocess, sys, zipfile
from collections import Counter, defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
XAPK_DIR = os.path.join(ROOT, "work", "extracted")
RES = os.path.join(ROOT, "research")
os.makedirs(RES, exist_ok=True)

def sha256(b): return hashlib.sha256(b).hexdigest()
def sha256_file(p):
    h = hashlib.sha256()
    with open(p, "rb") as f:
        for c in iter(lambda: f.read(1<<20), b""): h.update(c)
    return h.hexdigest()

# ---------- AXML parser ----------
def parse_axml(data: bytes):
    # Returns (strings, elements) where elements = list of (tag, attrs dict)
    assert data[:4] == b"\x03\x00\x08\x00", "not AXML"
    pos = 8
    strings = []
    elements = []
    # resource ids map not needed for names; attrs use string indices
    while pos < len(data):
        if pos + 8 > len(data): break
        ctype, hsize, size = struct.unpack_from("<HHI", data, pos)
        if size == 0: break
        chunk = data[pos:pos+size]
        if ctype == 0x0001:  # string pool
            scount, stylecount, flags, sstart, style_start = struct.unpack_from("<IIIII", chunk, 8)
            offsets = struct.unpack_from(f"<{scount}I", chunk, 28)
            is_utf8 = (flags & 0x100) != 0
            base = 28 + scount*4
            for i in range(scount):
                o = offsets[i]
                try:
                    if is_utf8:
                        # utf8: two varints (len chars, len bytes) then bytes
                        p = base + o
                        # skip char len
                        b0 = chunk[p]; p += 1
                        if b0 & 0x80: p += 1
                        b0 = chunk[p]; p += 1
                        if b0 & 0x80: p += 1
                        end = chunk.index(b"\x00", p)
                        strings.append(chunk[p:end].decode("utf-8", "replace"))
                    else:
                        p = base + o
                        ln = struct.unpack_from("<H", chunk, p)[0]; p += 2
                        if ln & 0x8000:
                            ln = ((ln & 0x7FFF) << 16) | struct.unpack_from("<H", chunk, p)[0]; p += 2
                        strings.append(chunk[p:p+ln*2].decode("utf-16-le", "replace"))
                except Exception:
                    strings.append("")
        elif ctype == 0x0102:  # start element
            # header(8) + line(4)+comment(4)+ns(4)+name(4)+attrStart(2)+attrSize(2)+attrCount(2)+idIdx(2)+classIdx(2)+styleIdx(2)
            ns, name, astart, asize, acount = struct.unpack_from("<IIHHH", chunk, 16)
            attrs = {}
            for i in range(acount):
                ao = 36 + i*20  # 8 hdr + 4+4+4+4+2+2+2+2+2+2 = 36? verify: 8+28=36
                # attr: ns(4) name(4) rawValue(4) typedSize(2) res0(1) dataType(1) data(4)
                ans, aname, raw, tsize, res0, dtype, dval = struct.unpack_from("<IIIHBBI", chunk, ao)
                key = strings[aname] if aname < len(strings) else f"#{aname}"
                if raw != 0xFFFFFFFF and raw < len(strings):
                    val = strings[raw]
                elif dtype == 0x03:
                    val = strings[dval] if dval < len(strings) else f"#{dval}"
                elif dtype == 0x10:
                    val = str(dval)
                elif dtype == 0x12:
                    val = "true" if dval else "false"
                else:
                    val = f"0x{dval:x}"
                attrs[key] = val
            tag = strings[name] if name < len(strings) else f"#{name}"
            elements.append((tag, attrs))
        pos += size
    return strings, elements

# ---------- DEX parser ----------
def parse_dex(data: bytes):
    magic = data[:8]
    assert magic.startswith(b"dex\n"), f"bad dex magic {magic}"
    string_ids_size, string_ids_off = struct.unpack_from("<II", data, 0x38)
    type_ids_size, type_ids_off = struct.unpack_from("<II", data, 0x40)
    proto_ids_size, proto_ids_off = struct.unpack_from("<II", data, 0x48)
    field_ids_size, field_ids_off = struct.unpack_from("<II", data, 0x50)
    method_ids_size, method_ids_off = struct.unpack_from("<II", data, 0x58)
    class_defs_size, class_defs_off = struct.unpack_from("<II", data, 0x60)
    strings = []
    for i in range(string_ids_size):
        off = struct.unpack_from("<I", data, string_ids_off + i*4)[0]
        # uleb128 len then bytes (modified utf8, nul-terminated)
        p = off
        # decode uleb128
        shift = 0; ln = 0
        while True:
            b = data[p]; p += 1
            ln |= (b & 0x7F) << shift; shift += 7
            if not (b & 0x80): break
        end = data.index(b"\x00", p)
        strings.append(data[p:end].decode("utf-8", "replace"))
    types = []
    for i in range(type_ids_size):
        idx = struct.unpack_from("<I", data, type_ids_off + i*4)[0]
        types.append(strings[idx] if idx < len(strings) else "?")
    methods = []
    for i in range(method_ids_size):
        cidx, pidx, nidx = struct.unpack_from("<HHI", data, method_ids_off + i*8)
        methods.append((types[cidx] if cidx < len(types) else "?", strings[nidx] if nidx < len(strings) else "?"))
    classes = [t for t in types if t.startswith("L") and t.endswith(";")]
    return {"strings": strings, "types": types, "classes": classes, "methods": methods,
            "counts": {"strings": len(strings), "types": len(types), "methods": len(methods), "classes": len(classes)}}

def main():
    print("== hashes ==")
    outer = [f for f in os.listdir(ROOT) if f.endswith(".zip")][0]
    print("outer", outer, sha256_file(os.path.join(ROOT, outer)))
    xapk_path = [os.path.join(XAPK_DIR, f) for f in os.listdir(XAPK_DIR) if f.endswith(".xapk")][0]
    print("xapk", xapk_path, sha256_file(xapk_path))
    with zipfile.ZipFile(xapk_path) as xz:
        manifest = json.loads(xz.read("manifest.json"))
        print(json.dumps(manifest, indent=1, ensure_ascii=False))
        apks = {}
        for name in xz.namelist():
            if name.endswith(".apk"):
                b = xz.read(name)
                apks[name] = b
                print(f"  {name}: {len(b)} bytes sha256={sha256(b)}")
    with open(os.path.join(RES, "xapk-manifest.json"), "w") as f:
        json.dump(manifest, f, indent=1, ensure_ascii=False)

    base = apks["vn.com.vng.zalopay.apk"]
    bz = zipfile.ZipFile(io.BytesIO(base))
    names = bz.namelist()
    print(f"base apk entries: {len(names)}")

    # signing certs
    certs = {}
    for n in names:
        if n.startswith("META-INF/") and (n.endswith(".RSA") or n.endswith(".DSA") or n.endswith(".EC") or n.endswith(".SF")):
            b = bz.read(n)
            certs[n] = {"size": len(b), "sha256": sha256(b)}
    print("META-INF:", json.dumps(certs, indent=1))
    with open(os.path.join(RES, "signing.json"), "w") as f:
        json.dump({"outer_zip": outer, "outer_sha256": sha256_file(os.path.join(ROOT, outer)),
                   "xapk_sha256": sha256_file(xapk_path),
                   "apks": {k: {"size": len(v), "sha256": sha256(v)} for k, v in apks.items()},
                   "meta_inf": certs}, f, indent=1)

    # AndroidManifest
    axml = bz.read("AndroidManifest.xml")
    strings, elements = parse_axml(axml)
    print(f"AXML: {len(strings)} strings, {len(elements)} elements")
    manifest_out = {"elements": [{"tag": t, "attrs": a} for t, a in elements]}
    with open(os.path.join(RES, "android-manifest.json"), "w") as f:
        json.dump(manifest_out, f, indent=1, ensure_ascii=False)
    # summarize
    for tag in ("manifest", "uses-sdk", "package"):
        for t, a in elements:
            if t == tag: print(tag, a)
    perms = sorted({a.get("name") for t, a in elements if t == "uses-permission" and a.get("name")})
    print(f"permissions ({len(perms)}):")
    for p in perms: print("  ", p)
    comps = defaultdict(list)
    for t, a in elements:
        if t in ("activity", "activity-alias", "service", "receiver", "provider"):
            comps[t].append(a)
    for t, lst in comps.items():
        exp = sum(1 for a in lst if a.get("exported") == "true")
        print(f"{t}: {len(lst)} (exported={exp})")
    # exported components detail
    exported = []
    for t, lst in comps.items():
        for a in lst:
            if a.get("exported") == "true":
                exported.append({"type": t, "name": a.get("name"), "permission": a.get("permission"), "enabled": a.get("enabled")})
    print(f"exported components: {len(exported)}")
    for e in exported[:60]: print("  ", e)
    with open(os.path.join(RES, "components.json"), "w") as f:
        json.dump({"counts": {k: len(v) for k, v in comps.items()},
                   "exported": exported,
                   "permissions": perms}, f, indent=1, ensure_ascii=False)

    # DEX inventory
    dex_names = sorted([n for n in names if re.match(r"classes\d*\.dex$", n)])
    print(f"DEX files: {dex_names}")
    all_classes, all_strings, all_methods = [], [], []
    dex_info = {}
    for dn in dex_names:
        d = bz.read(dn)
        info = parse_dex(d)
        dex_info[dn] = {"size": len(d), "sha256": sha256(d), **info["counts"]}
        print(f"  {dn}: {info['counts']}")
        all_classes += info["classes"]; all_strings += info["strings"]; all_methods += info["methods"]
    print(f"TOTAL classes={len(all_classes)} strings={len(all_strings)} methods={len(all_methods)}")
    with open(os.path.join(RES, "dex-inventory.json"), "w") as f:
        json.dump(dex_info, f, indent=1)
    # package histogram (top 2 levels)
    pkg = Counter()
    for c in all_classes:
        parts = c[1:-1].split("/")
        pkg["/".join(parts[:2]) if len(parts) >= 2 else parts[0]] += 1
    print("top packages:")
    for k, v in pkg.most_common(50): print(f"  {v:>6}  {k}")
    with open(os.path.join(RES, "packages.json"), "w") as f:
        json.dump(dict(pkg.most_common(300)), f, indent=1)

    # native libs
    libs = [n for n in names if n.startswith("lib/") and n.endswith(".so")]
    print(f"native libs ({len(libs)}):")
    for n in sorted(libs): print(f"  {bz.getinfo(n).file_size:>10}  {n}")
    with open(os.path.join(RES, "native-libs.json"), "w") as f:
        json.dump([{"path": n, "size": bz.getinfo(n).file_size} for n in sorted(libs)], f, indent=1)

    # assets inventory
    assets = [n for n in names if n.startswith("assets/")]
    print(f"assets ({len(assets)}):")
    for n in sorted(assets, key=lambda x: -bz.getinfo(x).file_size)[:60]:
        print(f"  {bz.getinfo(n).file_size:>10}  {n}")
    with open(os.path.join(RES, "assets.json"), "w") as f:
        json.dump([{"path": n, "size": bz.getinfo(n).file_size} for n in sorted(assets)], f, indent=1)

    # string mining over DEX strings: urls, hosts, sdk fingerprints
    url_re = re.compile(r"https?://[^\s\"'<>\\]+")
    urls = Counter()
    for s in all_strings:
        for m in url_re.findall(s):
            urls[m[:120]] += 1
    print(f"unique urls in DEX strings: {len(urls)}")
    for u, c in urls.most_common(80): print(f"  {c:>4}  {u}")
    with open(os.path.join(RES, "urls.json"), "w") as f:
        json.dump(dict(urls.most_common(500)), f, indent=1, ensure_ascii=False)
    # save all strings for later mining (sorted unique)
    uniq = sorted(set(all_strings))
    with open(os.path.join(RES, "dex-strings.txt"), "w", encoding="utf-8") as f:
        f.write("\n".join(uniq))
    print(f"wrote dex-strings.txt ({len(uniq)} unique)")

if __name__ == "__main__":
    main()
