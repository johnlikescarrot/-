#!/usr/bin/env python3
"""Resource table, assets and payload composition analysis."""
from __future__ import annotations
import hashlib, json, os, re, sys
from collections import Counter, defaultdict

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
APK = os.path.join(ROOT, "work/zip/xapk/vn.com.vng.zalopay.apk")
SPLITS = os.path.join(ROOT, "work/zip/xapk")
APKDIR = os.path.join(ROOT, "work/apk")
DATA = os.path.join(ROOT, "data")


def dir_size(path: str) -> tuple[int, int]:
    total = files = 0
    for dp, _dn, fn in os.walk(path):
        for f in fn:
            try:
                total += os.path.getsize(os.path.join(dp, f))
                files += 1
            except OSError:
                pass
    return total, files


def main() -> None:
    sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
    import arscmeta

    tables = {}
    locales: Counter = Counter()
    type_counts: dict[str, int] = defaultdict(int)
    entry_total = 0
    packages: list[str] = []
    import zipfile
    for split in sorted(os.listdir(SPLITS)):
        if not split.endswith(".apk"):
            continue
        with zipfile.ZipFile(os.path.join(SPLITS, split)) as zf:
            try:
                blob = zf.read("resources.arsc")
            except KeyError:
                continue
        tmp = os.path.join(ROOT, "work", "arsc-" + split + ".arsc")
        open(tmp, "wb").write(blob)
        parsed = arscmeta.parse(tmp)
        tables[split] = {"bytes": len(blob),
                         "packages": [p["name"] for p in parsed["packages"]],
                         "types": {p["name"]: p["types"] for p in parsed["packages"]},
                         "locales": {p["name"]: p["locales"] for p in parsed["packages"]},
                         "entries": sum(p["entry_total"] for p in parsed["packages"]),
                         "configs": sum(p["config_count"] for p in parsed["packages"])}
        for p in parsed["packages"]:
            packages.append(p["name"])
            for loc, n in p["locales"].items():
                for c in p["locales"]:
                    pass
            for loc, cfgcount in p["locales"].items():
                locales[loc] += cfgcount
            for tname, n in p["types"].items():
                type_counts[tname] += n
            entry_total += p["entry_total"]
    # payload composition across base + splits
    composition = {}
    base_dirs = defaultdict(lambda: [0, 0])
    for dp, _dn, fn in os.walk(APKDIR):
        for f in fn:
            rel = os.path.relpath(os.path.join(dp, f), APKDIR)
            top = rel.split(os.sep)[0]
            sz = os.path.getsize(os.path.join(dp, f))
            base_dirs[top][0] += sz
            base_dirs[top][1] += 1
    composition["base_apk"] = {k: {"bytes": v[0], "files": v[1]} for k, v in
                               sorted(base_dirs.items(), key=lambda kv: -kv[1][0])}
    for split in sorted(os.listdir(SPLITS)):
        if not split.endswith(".apk") or split == "vn.com.vng.zalopay.apk":
            continue
        d = os.path.join(ROOT, "work/split-" + split.replace(".apk", ""))
        if not os.path.isdir(d):
            import zipfile
            with zipfile.ZipFile(os.path.join(SPLITS, split)) as zf:
                zf.extractall(d)
        total = sum(os.path.getsize(os.path.join(dp, f)) for dp, _dn, fn in os.walk(d) for f in fn)
        composition[split] = {"bytes": total}
    # assets inventory
    assets = []
    adir = os.path.join(APKDIR, "assets")
    for dp, _dn, fn in os.walk(adir):
        for f in fn:
            p = os.path.join(dp, f)
            rel = os.path.relpath(p, adir)
            assets.append({"path": rel, "bytes": os.path.getsize(p),
                           "sha256": hashlib.sha256(open(p, "rb").read()).hexdigest()[:16],
                           "kind": ("ml-model" if rel.endswith((".bin", ".tflite", ".param")) else
                                    "js-bundle" if rel.endswith(".js") else
                                    "config" if rel.endswith((".json", ".dat", ".xml")) else
                                    "font" if rel.endswith((".ttf", ".otf")) else "other")})
    assets.sort(key=lambda a: -a["bytes"])
    models = [a for a in assets if a["kind"] == "ml-model"]
    js = [a for a in assets if a["kind"] == "js-bundle"]
    hash_dirs = [d for d in os.listdir(adir) if re.fullmatch(r"[0-9a-f]{64}", d)]
    hash_dir_contents = {}
    for hd in hash_dirs:
        tot, n = dir_size(os.path.join(adir, hd))
        names = os.listdir(os.path.join(adir, hd))
        hash_dir_contents[hd[:12] + "..."] = {"bytes": tot, "files": n, "names": names[:6]}
    raw = sorted(os.listdir(os.path.join(APKDIR, "res/raw"))) if os.path.isdir(os.path.join(APKDIR, "res/raw")) else []
    report = {
        "resource_tables_per_apk": tables,
        "resource_table": {
            "packages": packages, "locale_count": len(locales),
            "locales": [l for l, _ in locales.most_common(80)],
            "type_counts": dict(sorted(type_counts.items(), key=lambda kv: -kv[1])[:25]),
            "entry_total": entry_total,
            "arsc_bytes": os.path.getsize(os.path.join(APKDIR, "resources.arsc")),
        },
        "composition": composition,
        "assets": {"count": len(assets), "total_bytes": sum(a["bytes"] for a in assets),
                   "models": models, "js_bundles": js,
                   "top": assets[:25], "hash_named_dirs": hash_dir_contents,
                   "all_paths": [a["path"] for a in assets]},
        "res_raw": raw,
        "drawable_count": len([f for f in os.listdir(os.path.join(APKDIR, "res/drawable"))]) if os.path.isdir(os.path.join(APKDIR, "res/drawable")) else 0,
        "layout_count": len([f for f in os.listdir(os.path.join(APKDIR, "res/layout"))]) if os.path.isdir(os.path.join(APKDIR, "res/layout")) else 0,
    }
    with open(os.path.join(DATA, "07-resources.json"), "w") as fh:
        json.dump(report, fh, indent=2, ensure_ascii=False)
    print(json.dumps({k: report["resource_table"][k] for k in ("locale_count", "entry_total", "arsc_bytes")}, indent=1))
    print("package count:", len(packages))
    print("locales:", report["resource_table"]["locales"][:30])
    print("types:", list(report["resource_table"]["type_counts"].items())[:12])
    print("composition:", json.dumps(composition, indent=1)[:900])
    print("models:", [(m["path"], m["bytes"]) for m in models])
    print("hash dirs:", hash_dir_contents)


if __name__ == "__main__":
    main()
