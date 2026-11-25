#!/usr/bin/env python3
"""Multidex inventory: per-file statistics, class/package histogram, obfuscation metrics."""
from __future__ import annotations
import hashlib, json, math, os, re, sys
from collections import Counter, defaultdict

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import dexmeta

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
APKDIR = os.path.join(ROOT, "work/apk")
OUT = os.path.join(ROOT, "data")

IDENT = re.compile(r"^[A-Za-z_$][A-Za-z0-9_$]*$")
SINGLE = re.compile(r"^[A-Za-z]{1,2}$")


def entropy(text: str) -> float:
    if not text:
        return 0.0
    counts = Counter(text)
    n = len(text)
    return -sum(c / n * math.log2(c / n) for c in counts.values())


def package_of(desc: str) -> str:
    # Lcom/foo/Bar; -> com.foo
    inner = desc[1:-1] if desc.startswith("L") and desc.endswith(";") else desc
    parts = inner.split("/")
    if len(parts) >= 3:
        return ".".join(parts[:-1])
    if len(parts) == 2:
        return parts[0]
    return "<default>"


def root_package(pkg: str) -> str:
    parts = pkg.split(".")
    if len(parts) >= 2:
        return ".".join(parts[:2]) if parts[0] in ("com", "org", "net", "io", "android", "androidx") else parts[0]
    return parts[0]


def main() -> None:
    dex_files = sorted([f for f in os.listdir(APKDIR) if re.fullmatch(r"classes\d*\.dex", f)],
                       key=lambda n: (len(n), n))
    per_dex = []
    all_classes: list[dict] = []
    all_strings: Counter = Counter()
    class_pkgs: Counter = Counter()
    root_pkgs: Counter = Counter()
    source_files: Counter = Counter()
    for name in dex_files:
        path = os.path.join(APKDIR, name)
        with open(path, "rb") as fh:
            raw = fh.read()
        dex = dexmeta.DexFile(raw)
        classes = dex.classes()
        h = dex.header
        # integrity: recompute SHA-1 over everything after signature field
        sha1 = hashlib.sha1(raw[32:]).hexdigest()
        adler = __import__("zlib").adler32(raw[12:]) & 0xFFFFFFFF
        entry = {
            "name": name, "sha256": hashlib.sha256(raw).hexdigest(), "bytes": len(raw),
            "dex_version": h.version, "header_file_size_ok": h.file_size == len(raw),
            "sha1_signature_ok": sha1 == h.signature_hex,
            "adler32_ok": adler == h.checksum,
            "strings": h.string_ids_size, "types": h.type_ids_size, "protos": h.proto_ids_size,
            "fields": h.field_ids_size, "methods": h.method_ids_size, "classes": h.class_defs_size,
            "class_defs_parsed": len(classes),
            "data_section_ratio": round(h.data_size / len(raw), 4),
            "classes_with_source_file": sum(1 for c in classes if c.source_file),
            "class_kinds": dict(Counter(c.kind for c in classes)),
            "avg_methods_per_class": round(h.method_ids_size / max(h.class_defs_size, 1), 2),
        }
        per_dex.append(entry)
        for c in classes:
            pkg = package_of(c.name)
            class_pkgs[pkg] += 1
            root_pkgs[root_package(pkg)] += 1
            if c.source_file:
                source_files[c.source_file] += 1
            all_classes.append({"name": c.name, "dex": name, "super": c.superclass,
                                "pkg": pkg, "methods": c.method_count, "fields": c.field_count,
                                "source": c.source_file, "kind": c.kind})
        # strings
        for i in range(h.string_ids_size):
            all_strings[dex.string(i)] += 1

    total_classes = len(all_classes)
    # --- obfuscation metrics -------------------------------------------------
    seg_lens = Counter()
    short_pkg = 0
    for pkg, n in class_pkgs.items():
        segs = pkg.split(".")
        if all(SINGLE.match(s) for s in segs):
            short_pkg += n
        for s in segs:
            seg_lens[len(s)] += n
    single_char_classes = sum(1 for c in all_classes
                              if SINGLE.match(c["name"].rstrip(";").split("/")[-1]))
    flat_short = sum(1 for c in all_classes
                     if re.match(r"^L[A-Za-z]{1,4}/[A-Za-z]{1,4};$", c["name"]))
    name_entropy = entropy("".join(c["name"].split("/")[-1].rstrip(";")[:1] for c in all_classes[:5000]))
    synthetic = sum(1 for c in all_classes if "$$" in c["name"] or "Lambda" in c["name"])
    nameless_sources = total_classes - sum(1 for c in all_classes if c["source"])
    # keep every class name for later SDK mapping, but cap the JSON on disk
    report = {
        "dex_count": len(dex_files),
        "total": {
            "classes": total_classes,
            "methods": sum(d["methods"] for d in per_dex),
            "fields": sum(d["fields"] for d in per_dex),
            "strings": sum(d["strings"] for d in per_dex),
            "types": sum(d["types"] for d in per_dex),
            "protos": sum(d["protos"] for d in per_dex),
            "dex_bytes": sum(d["bytes"] for d in per_dex),
            "unique_strings": len(all_strings),
        },
        "per_dex": per_dex,
        "top_packages": class_pkgs.most_common(60),
        "top_root_packages": root_pkgs.most_common(40),
        "package_depth_histogram": dict(Counter(len(p.split(".")) for p in class_pkgs).most_common()),
        "obfuscation": {
            "classes_without_source_file": nameless_sources,
            "classes_without_source_file_pct": round(100 * nameless_sources / max(total_classes, 1), 2),
            "single_char_class_names": single_char_classes,
            "flat_short_names_Lxx_Yyy": flat_short,
            "classes_in_fully_short_packages": short_pkg,
            "classes_in_fully_short_packages_pct": round(100 * short_pkg / max(total_classes, 1), 2),
            "synthetic_lambda_classes": synthetic,
            "top_source_file_attributes": source_files.most_common(12),
            "package_segment_length_histogram": dict(sorted(seg_lens.items())[:12]),
        },
        "classes": all_classes,
        "strings_sample": [s for s, _ in all_strings.most_common(400)],
    }
    with open(os.path.join(OUT, "03-dex.json"), "w") as fh:
        json.dump(report, fh, indent=2, ensure_ascii=False)
    # full string dump for downstream scanners (not committed: ~ tens of MB)
    os.makedirs(os.path.join(ROOT, "work"), exist_ok=True)
    with open(os.path.join(ROOT, "work/all-strings.txt"), "w", encoding="utf-8", errors="replace") as fh:
        for s, n in all_strings.most_common():
            fh.write(f"{n}\t{s[:400]}\n")
    print(json.dumps({k: report[k] for k in ("dex_count", "total", "obfuscation")}, indent=2)[:2200])
    print("roots:", report["top_root_packages"][:20])


if __name__ == "__main__":
    main()
