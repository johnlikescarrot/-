#!/usr/bin/env python3
"""Summarize dex files and class enumeration of the extracted APK using androguard."""
import logging
logging.disable(logging.CRITICAL)
import sys
from pathlib import Path
from collections import Counter
from androguard.core.dex import DEX

def main(extracted_dir, out_path):
    out = open(out_path, "w", encoding="utf-8")
    dex_files = sorted(Path(extracted_dir).glob("classes*.dex"), key=lambda p: p.name)
    dex_files = sorted(dex_files, key=lambda p: (p.stem != "classes", p.stem.replace("classes", "").lstrip("") or "0"))
    out.write(f"Total dex files: {len(dex_files)}\n\n")
    out.write(f"{'Dex file':<22} {'Size (bytes)':>14} {'Classes':>8}\n")
    out.write("-" * 48 + "\n")
    class_by_dex = {}
    total_classes = 0
    for p in dex_files:
        try:
            d = DEX(p.read_bytes())
            cls = d.get_classes_names()
        except Exception as e:
            cls = []
            out.write(f"{p.name:<22} {p.stat().st_size:>14} {'ERR '+str(e):>8}\n")
            continue
        out.write(f"{p.name:<22} {p.stat().st_size:>14} {len(cls):>8}\n")
        total_classes += len(cls)
        class_by_dex[p.name] = cls
    out.write(f"\nTotal classes across all dex: {total_classes}\n")
    pkg_counter = Counter()
    for dex, classes in class_by_dex.items():
        for c in classes:
            parts = c.rsplit(".", 1)
            pkg = parts[0] if len(parts) > 1 else c
            top = pkg.split(".")[0] if pkg else c
            pkg_counter[top] += 1
    out.write("\nTop-level packages (by class count):\n")
    for pkg, cnt in pkg_counter.most_common(40):
        out.write(f"  {cnt:>6}  {pkg}\n")
    out.write("\n")
    with open(out_path + ".classes.txt", "w", encoding="utf-8") as cf:
        for dex in sorted(class_by_dex):
            cf.write(f"# Dex: {dex}\n")
            for c in sorted(class_by_dex[dex]):
                cf.write(c + "\n")
    out.write(f"Full class list saved to {out_path}.classes.txt ({total_classes} classes)\n")
    out.close()
    print("Wrote", out_path)

if __name__ == "__main__":
    main(sys.argv[1], sys.argv[2])
