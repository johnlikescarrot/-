#!/usr/bin/env python3
"""Stage 2 — DEX census: per-file structure, package histogram, native bindings.

Output: data/02_dex.json, data/dex_strings/<name>.txt (one string per line)
"""
from __future__ import annotations

import collections
import glob
import json
import os
import re
import sys
import zipfile
from datetime import datetime, timezone

sys.path.insert(0, os.path.join(os.path.dirname(__file__)))
from dexlib import parse_dex  # noqa: E402

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", ".."))
XAPK = os.path.join(ROOT, "work", "xapk")
OUT = os.path.join(ROOT, "data")
STRDIR = os.path.join(OUT, "dex_strings")

DEX_RE = re.compile(r"^classes(\d*)\.dex$")


def sort_key(name: str):
    m = DEX_RE.match(name)
    if not m:
        return (999, name)
    return (1 if not m.group(1) else int(m.group(1)), name)


def main() -> None:
    os.makedirs(STRDIR, exist_ok=True)
    base = os.path.join(XAPK, "vn.com.vng.zalopay.apk")
    result = {"generated_at": datetime.now(timezone.utc).isoformat(), "dex": [], "totals": {}}

    with zipfile.ZipFile(base) as z:
        dex_names = sorted([n for n in z.namelist() if DEX_RE.match(n)], key=sort_key)
        asset_dex = [n for n in z.namelist() if n.endswith(".dex") and n not in dex_names]

        all_classes: list[str] = []
        all_strings: set[str] = set()
        all_native: list[str] = []
        for name in dex_names:
            blob = z.read(name)
            stats = parse_dex(name, blob)
            all_classes.extend(stats.classes)
            all_strings.update(stats.strings)
            all_native.extend(stats.native_methods)
            with open(os.path.join(STRDIR, name + ".txt"), "w") as fh:
                fh.write("\n".join(stats.strings).encode("utf-8", "surrogatepass").decode("utf-8", "replace"))
            d = stats.to_json(keep_strings=False)
            d["native_method_count"] = len(stats.native_methods)
            d["native_methods_sample"] = stats.native_methods[:25]
            d["own_classes"] = sum(1 for c in stats.classes if c.startswith("Lvn/com/vng/zalopay/"))
            result["dex"].append(d)
            print(f"  {name:<14} classes={stats.class_defs:>6} methods={stats.method_ids:>7} strings={stats.string_ids:>7} own={d['own_classes']:>5}")

    # package histogram over the app's own namespace
    packages = collections.Counter()
    top_dirs = collections.Counter()
    for c in all_classes:
        if not c.startswith("Lvn/com/vng/zalopay/"):
            continue
        parts = c[1:-1].split("/")
        top_dirs[parts[4] if len(parts) > 4 else "(root)"] += 1
        packages["/".join(parts[:5])] += 1

    # third-party namespace census (root package)
    roots = collections.Counter()
    for c in all_classes:
        p = c[1:-1]
        head = p.split("/")[0]
        segs = p.split("/")
        roots["/".join(segs[:3]) if head in ("com", "org", "io", "net") else head] += 1

    result["totals"] = {
        "dex_files": len(dex_names),
        "asset_dex": asset_dex,
        "classes": len(all_classes),
        "unique_classes": len(set(all_classes)),
        "unique_strings": len(all_strings),
        "native_methods": len(all_native),
        "own_classes": sum(v for k, v in top_dirs.items()),
        "own_packages": len(packages),
    }
    result["own_namespace_top"] = top_dirs.most_common(30)
    result["third_party_roots"] = roots.most_common(40)
    result["own_packages_top"] = packages.most_common(60)
    result["native_bindings"] = all_native[:400]
    result["native_libraries_referenced"] = sorted({s for s in all_strings if re.fullmatch(r"[\w-]+\.so", s)})

    with open(os.path.join(OUT, "02_dex.json"), "w") as fh:
        json.dump(result, fh, indent=1, ensure_ascii=False)
    with open(os.path.join(OUT, "all_strings.txt"), "w") as fh:
        fh.write("\n".join(sorted(all_strings)).encode("utf-8", "surrogatepass").decode("utf-8", "replace"))
    print("totals:", json.dumps(result["totals"]))
    print("own top:", top_dirs.most_common(10))
    print("3p roots:", roots.most_common(12))
    print("native libs:", result["native_libraries_referenced"])


if __name__ == "__main__":
    main()
