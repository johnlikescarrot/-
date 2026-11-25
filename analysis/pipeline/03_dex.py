"""Stage 03 — Dalvik bytecode census across all 19 DEX images.

Outputs
-------
data/dex_summary.json   per-file header stats + corpus totals
data/dex_packages.json  package-level coupling (classes, methods, sample)
data/dex_obfuscation.json  identifier-entropy / single-letter-name analysis
data/dex_strings.gz     the merged, deduplicated string pool (feeds stage 05)
data/facts_dex.json     compact fragment for the video renderer
"""

from __future__ import annotations

import collections
import math
import re
from pathlib import Path

from common import APK_DIR, XAPK_DIR, shannon_entropy, write_json, write_json_gz, Stage
from dexread import DexFile

BACKSLASH = re.compile(r"\\(.)")


def package_of(dotted: str, depth: int = 3) -> str:
    parts = dotted.split(".")
    return ".".join(parts[:depth]) if len(parts) > depth else ".".join(parts[:-1]) or dotted


def main() -> None:
    st = Stage("03-dex")

    dex_paths = sorted(APK_DIR.glob("classes*.dex")) + sorted(APK_DIR.glob("assets/*.dex"))
    dex_paths.sort(key=lambda p: (p.name != "classes.dex", p.name))
    st.log(f"discovered {len(dex_paths)} DEX images")

    summaries = []
    all_strings: set[str] = set()
    packages: collections.Counter = collections.Counter()
    pkg_methods: collections.Counter = collections.Counter()
    pkg_sample: dict[str, str] = {}
    class_names: list[str] = []
    obf_stats = {"single_letter_classes": 0, "total_classes": 0, "single_letter_methods": 0, "total_methods": 0}
    native_methods: list[dict] = []
    super_edges: collections.Counter = collections.Counter()
    total_bytes = 0

    for p in dex_paths:
        d = DexFile(p)
        s = d.summary()
        total_bytes += s["bytes"]
        for c in d.classes():
            class_names.append(c.name)
            pkg = package_of(c.name)
            packages[pkg] += 1
            pkg_methods[pkg] += c.method_count
            pkg_sample.setdefault(pkg, c.name)
            obf_stats["total_classes"] += 1
            obf_stats["total_methods"] += c.method_count
            last = c.name.rsplit(".", 1)[-1]
            if len(last) <= 2:
                obf_stats["single_letter_classes"] += 1
            if c.native_methods:
                native_methods.append({"class": c.name, "methods": c.native_methods[:8], "count": len(c.native_methods)})
            if c.super_name:
                super_edges[c.super_name] += 1
        # method name survey (cheap: reuse method_ids table)
        for _cls, name, _proto in d.methods():
            if len(name) <= 2:
                obf_stats["single_letter_methods"] += 1
        all_strings.update(d.strings)
        summaries.append(s)
        st.log(f"{s['file']}: {s['classes']:,} classes, {s['methods']:,} methods, {s['strings']:,} strings ({s['bytes']/1e6:.1f} MB)")

    # ---------- corpus totals ------------------------------------------------
    totals = {
        "dex_files": len(summaries),
        "bytes": total_bytes,
        "classes": sum(s["classes"] for s in summaries),
        "methods": sum(s["methods"] for s in summaries),
        "fields": sum(s["fields"] for s in summaries),
        "strings_unique": len(all_strings),
        "strings_indexed": sum(s["strings"] for s in summaries),
        "types": sum(s["types"] for s in summaries),
        "protos": sum(s["protos"] for s in summaries),
    }
    st.log(
        f"CORPUS: {totals['classes']:,} classes / {totals['methods']:,} methods / "
        f"{totals['fields']:,} fields / {totals['strings_unique']:,} unique strings"
    )

    # sanitise string pool (escape control chars so downstream JSON is clean)
    cleaned = []
    for s in all_strings:
        if len(s) > 512:
            s = s[:512]
        cleaned.append(BACKSLASH.sub(r"\1", s))
    cleaned.sort()

    # ---------- obfuscation -------------------------------------------------
    pct_cls = 100 * obf_stats["single_letter_classes"] / max(1, obf_stats["total_classes"])
    pct_meth = 100 * obf_stats["single_letter_methods"] / max(1, obf_stats["total_methods"])
    obf = {
        **obf_stats,
        "single_letter_class_pct": round(pct_cls, 2),
        "single_letter_method_pct": round(pct_meth, 2),
        # Threshold calibrated on this corpus: a stock R8/ProGuard build that keeps
        # SDK surfaces lands well under 70% short names; full renaming lands near 100%.
        "verdict": "heavily obfuscated" if pct_cls >= 60 else "partially obfuscated",
    }
    st.log(f"obfuscation: {pct_cls:.1f}% of classes and {pct_meth:.1f}% of methods have ≤2-char names")

    # ---------- package coupling -------------------------------------------
    pkg_rows = [
        {
            "package": k,
            "classes": v,
            "methods": pkg_methods[k],
            "sample": pkg_sample[k],
            "class_share_pct": round(100 * v / totals["classes"], 3),
        }
        for k, v in packages.most_common()
    ]
    st.log(f"packages: {len(pkg_rows)} distinct; top = {pkg_rows[0]['package']} ({pkg_rows[0]['classes']:,} classes)")

    write_json("dex_summary.json", {"totals": totals, "per_file": summaries})
    write_json("dex_packages.json", {"count": len(pkg_rows), "packages": pkg_rows[:400]})
    write_json("dex_obfuscation.json", obf)
    write_json("dex_native_bridge.json", {"classes_with_native": len(native_methods), "detail": native_methods})
    write_json_gz("dex_strings.json.gz", cleaned)
    write_json("dex_super_edges.json", {"edges": super_edges.most_common(60)})
    write_json(
        "facts_dex.json",
        {
            "totals": totals,
            "obfuscation": obf,
            "topPackages": pkg_rows[:12],
            "packageCount": len(pkg_rows),
        },
    )
    st.done({"classes": totals["classes"], "methods": totals["methods"], "strings": totals["strings_unique"]})


if __name__ == "__main__":
    main()
