#!/usr/bin/env python3
"""
Stage 60 -- resources.arsc + res/ + split geometry.

Two purposes.  First, a quantitative account of *where the bytes live* (the
storage story of the film).  Second, an account of the app's declared
linguistic and hardware reach: which locales are compiled in, how much of the
binary is compressed drawable, how the APK splits divide the payload across
density / language / ABI, and how much of the archive simply cannot be
recompressed (already-deflated or encrypted media).
"""

from __future__ import annotations

import logging
import re
import zipfile
from collections import Counter
from pathlib import Path

from androguard.core.axml import ARSCParser

from common import BASE_PKG, UNPACKED, XAPK_DIR, banner, dump, human

logging.disable(logging.CRITICAL)
BASE = UNPACKED / BASE_PKG


def main() -> None:
    banner("Stage 60 · resources & split geometry")
    arsc_path = BASE / "resources.arsc"
    arsc = ARSCParser(arsc_path.read_bytes())

    packages = arsc.get_packages_names()

    def arsc_profile(path: Path) -> dict:
        """Locale / type / string-pool census for one resources.arsc."""
        try:
            tbl = ARSCParser(path.read_bytes())
        except Exception as exc:
            return {"error": str(exc), "path": str(path)}
        locales, types = [], Counter()
        string_pool_bytes = 0
        for pkg in tbl.get_packages_names():
            for loc in tbl.get_locales(pkg):
                label = loc.replace("\x00", "") or "default"
                if label not in locales:
                    locales.append(label)
                for t in tbl.get_types(pkg, loc):
                    types[t] += 1
                try:
                    string_pool_bytes += len(tbl.get_string_resources(pkg, loc))
                except Exception:
                    pass
        return {"path": str(path.relative_to(UNPACKED)), "bytes": path.stat().st_size,
                "packages": tbl.get_packages_names(), "locales": locales,
                "string_pool_bytes": string_pool_bytes, "types": dict(types.most_common(12))}

    base_profile = arsc_profile(arsc_path)
    split_profiles = [arsc_profile(p) for p in
                      [UNPACKED / "config.vi" / "resources.arsc",
                       UNPACKED / "config.en" / "resources.arsc",
                       UNPACKED / "config.xhdpi" / "resources.arsc"]
                      if p.exists()]

    locale_strings = Counter({l: 1 for l in base_profile.get("locales", [])})
    locale_types = Counter()
    type_counts = Counter(base_profile.get("types", {}))
    for sp in split_profiles:
        for loc in sp.get("locales", []):
            locale_strings[f"split:{sp['path'].split('/')[0]}:{loc}"] += 1

    res_dirs = Counter()
    res_bytes = 0
    for p in (BASE / "res").rglob("*"):
        if p.is_file():
            res_dirs[p.parent.name] += 1
            res_bytes += p.stat().st_size

    # ---- split geometry -----------------------------------------------------
    splits = []
    for apk in sorted(XAPK_DIR.glob("*.apk")):
        with zipfile.ZipFile(apk) as zf:
            entries = [i for i in zf.infolist() if not i.is_dir()]
            un = sum(i.file_size for i in entries)
            co = sum(i.compress_size for i in entries)
            by_ext = Counter()
            for i in entries:
                by_ext[Path(i.filename).suffix.lower() or "(none)"] += i.file_size
            splits.append({
                "split": "base" if apk.name.startswith(BASE_PKG) else apk.stem,
                "installed_bytes": apk.stat().st_size,
                "entries": len(entries),
                "uncompressed": un,
                "compressed": co,
                "ratio": round(un / max(1, co), 2),
                "install_share": None,
                "top_ext": [{"ext": e, "bytes": b} for e, b in by_ext.most_common(6)],
            })
    total_un = sum(s["uncompressed"] for s in splits)
    for s in splits:
        s["share_of_uncompressed"] = round(s["uncompressed"] / total_un, 4)

    # ---- compression economics ---------------------------------------------
    with zipfile.ZipFile(XAPK_DIR / f"{BASE_PKG}.apk") as zf:
        infos = [i for i in zf.infolist() if not i.is_dir()]
    stored = [i for i in infos if i.compress_type == 0]
    deflated = [i for i in infos if i.compress_type == 8]
    dead_bytes = sum(i.file_size for i in stored)
    econ = {
        "entries": len(infos),
        "deflated_entries": len(deflated),
        "stored_entries": len(stored),
        "stored_bytes": dead_bytes,
        "stored_share_of_payload": round(dead_bytes / max(1, sum(i.file_size for i in infos)), 4),
        "largest_stored": [{"name": i.filename, "bytes": i.file_size}
                           for i in sorted(stored, key=lambda x: -x.file_size)[:8]],
    }

    # ---- localisation -------------------------------------------------------
    strings_vi = (UNPACKED / "config.vi").glob("resources.arsc")
    locales_sorted = locale_strings.most_common()
    vn = [l for l in locale_strings if re.search(r"(?i)vi|VN", l)]
    doc = {
        "arsc": {
            "bytes": arsc_path.stat().st_size,
            "packages": packages,
            "locales": len(locale_strings),
            "base_profile": base_profile,
            "split_profiles": split_profiles,
            "locale_entries": [{"locale": l, "present": n}
                               for l, n in locales_sorted[:40]],
            "vietnamese_locales": vn,
            "type_counts": dict(type_counts.most_common(20)),
        },
        "res": {"files": sum(res_dirs.values()), "bytes": res_bytes,
                "top_dirs": [{"dir": d, "files": c} for d, c in res_dirs.most_common(15)]},
        "splits": splits,
        "compression_economics": econ,
        "config_splits": {
            sid: {"files": len(list((UNPACKED / sid).rglob("*"))),
                  "bytes": sum(p.stat().st_size for p in (UNPACKED / sid).rglob("*") if p.is_file())}
            for sid in ("config.vi", "config.en", "config.xhdpi", "config.armeabi_v7a")
        },
    }
    dump("60_resources.json", doc)

    print(f"  arsc {human(doc['arsc']['bytes'])}  packages={packages} locales={len(locale_strings)}")
    print(f"  most complete locales: {locales_sorted[:6]}")
    print(f"  res/: {doc['res']['files']} files, {human(res_bytes)}")
    for s in splits:
        print(f"   • {s['split']:<22} {human(s['installed_bytes']):>9} installed  "
              f"{human(s['uncompressed']):>9} raw  ratio {s['ratio']}")
    print(f"  stored (incompressible) bytes: {human(dead_bytes)} "
          f"({econ['stored_share_of_payload']*100:.1f}%)  entries={len(stored)}")


if __name__ == "__main__":
    main()
