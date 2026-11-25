#!/usr/bin/env python3
"""
Stage 00 -- byte-level inventory of every file shipped in the artifact.

Scope: the outer APKPure ZIP, the nested XAPK container, and all five APK
splits.  For each member we record size, *compressed* size (the real transport
cost), SHA-256, Shannon entropy and a sniffed type -- this is the foundation
for the storage/protection analysis in later stages.
"""

from __future__ import annotations

import json
import zipfile
from collections import defaultdict
from pathlib import Path

from common import (BASE_PKG, DATA, OUTER_ZIP, UNPACKED, XAPK, XAPK_DIR,
                    banner, dump, entropy_file, human, sha256_file, sniff)


def zip_members(zpath: Path):
    """Yield (name, uncompressed, compressed, crc, method) for a zip archive."""
    with zipfile.ZipFile(zpath) as zf:
        for i in zf.infolist():
            if i.is_dir():
                continue
            yield i.filename, i.file_size, i.compress_size, i.CRC, i.compress_type


def header_integrity(zpath: Path):
    """CRC-32 verification of every entry (zipfile.testzip scans once)."""
    with zipfile.ZipFile(zpath) as zf:
        entries = [i for i in zf.infolist() if not i.is_dir()]
        bad_name = zf.testzip()
    return {"entries": len(entries),
            "entries_verified": len(entries) - (1 if bad_name else 0),
            "first_corrupt_entry": bad_name}


def main() -> None:
    banner("Stage 00 · inventory")
    containers = []

    # ---- layer 1: outer ZIP -------------------------------------------------
    outer = {
        "layer": "L1",
        "name": OUTER_ZIP.name,
        "bytes": OUTER_ZIP.stat().st_size,
        "sha256": sha256_file(OUTER_ZIP),
        "members": [{"name": n, "size": s, "compressed": c, "crc": f"{crc:08x}",
                     "method": "deflate" if m == 8 else "store"}
                    for n, s, c, crc, m in zip_members(OUTER_ZIP)],
    }
    outer["integrity"] = header_integrity(OUTER_ZIP)
    containers.append(outer)

    # ---- layer 2: XAPK ------------------------------------------------------
    xapk = {
        "layer": "L2",
        "name": XAPK.name,
        "bytes": XAPK.stat().st_size,
        "sha256": sha256_file(XAPK),
        "members": [{"name": n, "size": s, "compressed": c, "crc": f"{crc:08x}",
                     "method": "deflate" if m == 8 else "store"}
                    for n, s, c, crc, m in zip_members(XAPK)],
    }
    xapk["integrity"] = header_integrity(XAPK)
    containers.append(xapk)
    manifest = json.loads((XAPK_DIR / "manifest.json").read_text())
    xapk["manifest"] = manifest

    # ---- layer 3: splits ----------------------------------------------------
    splits = []
    for apk in sorted(XAPK_DIR.glob("*.apk")):
        split_id = "base" if apk.name.startswith(BASE_PKG) else apk.stem
        members = [{"name": n, "size": s, "compressed": c, "crc": f"{crc:08x}",
                    "method": "deflate" if m == 8 else "store"}
                   for n, s, c, crc, m in zip_members(apk)]
        splits.append({
            "split_id": split_id,
            "file": apk.name,
            "bytes": apk.stat().st_size,
            "sha256": sha256_file(apk),
            "entries": len(members),
            "uncompressed": sum(m["size"] for m in members),
            "compressed": sum(m["compressed"] for m in members),
            "integrity": header_integrity(apk),
            "members": members,
        })
    containers.append({"layer": "L3", "splits": splits})

    # ---- extracted-file census ---------------------------------------------
    files = []
    by_split = defaultdict(lambda: {"files": 0, "bytes": 0, "types": defaultdict(int)})
    for split_dir in sorted(UNPACKED.iterdir()):
        if not split_dir.is_dir():
            continue
        sid = split_dir.name
        for path in split_dir.rglob("*"):
            if not path.is_file():
                continue
            rel = path.relative_to(split_dir).as_posix()
            size = path.stat().st_size
            kind = sniff(path)
            files.append({
                "split": sid, "path": rel, "size": size, "kind": kind,
                "sha256": sha256_file(path) if size < 8 << 20 else None,
                "entropy": round(entropy_file(path), 4),
            })
            b = by_split[sid]
            b["files"] += 1
            b["bytes"] += size
            b["types"][kind] += 1

    # ---- aggregate ----------------------------------------------------------
    total_uncompressed = sum(f["size"] for f in files)
    kind_hist = defaultdict(lambda: {"files": 0, "bytes": 0})
    for f in files:
        k = kind_hist[f["kind"]]
        k["files"] += 1
        k["bytes"] += f["size"]
    ext_hist = defaultdict(lambda: {"files": 0, "bytes": 0})
    for f in files:
        e = Path(f["path"]).suffix.lower() or "(none)"
        ext_hist[e]["files"] += 1
        ext_hist[e]["bytes"] += f["size"]

    # entropy profile: how much of the payload is incompressible?
    buckets = {"<4": 0, "4-6": 0, "6-7": 0, "7-7.5": 0, "7.5-8": 0}
    for f in files:
        e = f["entropy"]
        if e < 4:
            buckets["<4"] += f["size"]
        elif e < 6:
            buckets["4-6"] += f["size"]
        elif e < 7:
            buckets["6-7"] += f["size"]
        elif e < 7.5:
            buckets["7-7.5"] += f["size"]
        else:
            buckets["7.5-8"] += f["size"]

    doc = {
        "artifact": {
            "app": "ZaloPay — Thanh toán & Tài chính",
            "package": manifest["package_name"],
            "version_name": manifest["version_name"],
            "version_code": manifest["version_code"],
            "min_sdk": manifest["min_sdk_version"],
            "target_sdk": manifest["target_sdk_version"],
            "distribution": "APKPure (XAPK bundle, split APKs)",
            "sha256_xapk": xapk["sha256"],
            "sha256_base_apk": next(s["sha256"] for s in splits if s["split_id"] == "base"),
        },
        "containers": containers,
        "census": {
            "files": len(files),
            "bytes_uncompressed": total_uncompressed,
            "splits": [
                {"split_id": sid,
                 "files": v["files"],
                 "bytes": v["bytes"],
                 "bytes_human": human(v["bytes"]),
                 "kinds": dict(sorted(v["types"].items(), key=lambda kv: -kv[1]))}
                for sid, v in sorted(by_split.items())
            ],
            "kinds": [{"kind": k, "files": v["files"], "bytes": v["bytes"]}
                      for k, v in sorted(kind_hist.items(), key=lambda kv: -kv[1]["bytes"])],
            "extensions": [{"ext": k, "files": v["files"], "bytes": v["bytes"]}
                           for k, v in sorted(ext_hist.items(), key=lambda kv: -kv[1]["bytes"])][:40],
            "entropy_buckets_bytes": buckets,
            "high_entropy_bytes": buckets["7.5-8"],
            "high_entropy_share": round(buckets["7.5-8"] / total_uncompressed, 4),
        },
        "files": files,
    }
    dump("00_inventory.json", doc)

    print(f"  files={len(files):,}  bytes={human(total_uncompressed)}")
    print(f"  high-entropy payload: {human(buckets['7.5-8'])} "
          f"({doc['census']['high_entropy_share']*100:.1f}%)")
    for s in doc["census"]["splits"]:
        print(f"   • {s['split_id']:<22} {s['files']:>5} files  {s['bytes_human']:>9}")


if __name__ == "__main__":
    main()
