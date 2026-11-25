#!/usr/bin/env python3
"""Stage 0 — chain of custody & container inventory.

Produces research/data/00_inventory.json:
  * sha256/md5/sha1 of outer ZIP, XAPK, each split
  * XAPK manifest.json (verbatim) + derived split table
  * full entry inventory for each APK (path, size, CRC, method, ratio)
  * extension histogram, largest entries, compression statistics
"""
from __future__ import annotations

import hashlib
import json
import struct
import zipfile
from collections import Counter
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
OUTER = ROOT / "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip"
DATA = ROOT / "research" / "data"
DATA.mkdir(parents=True, exist_ok=True)
EXT = ROOT / "work" / "extracted" / "xapk"


def hashes(p: Path) -> dict:
    h = {a: hashlib.new(a) for a in ("sha256", "sha1", "md5")}
    size = 0
    with open(p, "rb") as f:
        while chunk := f.read(1 << 20):
            size += len(chunk)
            for v in h.values():
                v.update(chunk)
    return {"bytes": size, **{k: v.hexdigest() for k, v in h.items()}}


def zip_entries(p: Path) -> list[dict]:
    out = []
    with zipfile.ZipFile(p) as z:
        for i in z.infolist():
            out.append(
                {
                    "name": i.filename,
                    "size": i.file_size,
                    "csize": i.compress_size,
                    "method": i.compress_type,
                    "crc": f"{i.CRC:08x}",
                    "ratio": round(i.compress_size / i.file_size, 4) if i.file_size else None,
                }
            )
    return out


def zipalign_gap(p: Path) -> dict | None:
    """Report the APK Signing Block and whether local headers are 4-byte aligned."""
    data = p.read_bytes()
    eocd = data.rfind(b"PK\x05\x06")
    if eocd < 0:
        return None
    cd_off = struct.unpack_from("<I", data, eocd + 16)[0]
    block = None
    if data[cd_off - 16 : cd_off] == b"APK Sig Block 42":
        size = struct.unpack_from("<Q", data, cd_off - 24)[0]
        block = {"present": True, "offset": cd_off - size - 8, "size": size}
    else:
        block = {"present": False}
    misaligned = 0
    with zipfile.ZipFile(p) as z:
        for i in z.infolist():
            off = i.header_offset
            name_len, extra_len = struct.unpack_from("<HH", data, off + 26)
            data_start = off + 30 + name_len + extra_len
            if data_start % 4:
                misaligned += 1
    return {"signing_block": block, "misaligned_entries": misaligned}


def main() -> None:
    inv: dict = {"outer": {"file": OUTER.name, **hashes(OUTER)}}
    with zipfile.ZipFile(OUTER) as z:
        inv["outer"]["entries"] = [
            {"name": i.filename, "size": i.file_size, "csize": i.compress_size} for i in z.infolist()
        ]
    xapk = next(EXT.parent.glob("*.xapk"))
    inv["xapk"] = {"file": xapk.name, **hashes(xapk), "entries": zip_entries(xapk)}
    inv["xapk_manifest"] = json.loads((EXT / "manifest.json").read_text(encoding="utf-8"))

    splits = []
    for p in sorted(EXT.glob("*.apk")):
        entries = zip_entries(p)
        ext_hist: Counter[str] = Counter()
        for e in entries:
            suffix = Path(e["name"]).suffix.lower() or "(none)"
            ext_hist[suffix] += 1
        splits.append(
            {
                "name": p.name,
                **hashes(p),
                "entries": len(entries),
                "uncompressed": sum(e["size"] for e in entries),
                "compressed": sum(e["csize"] for e in entries),
                "ext_histogram": dict(ext_hist.most_common()),
                "largest": sorted(entries, key=lambda e: e["size"], reverse=True)[:15],
                "alignment": zipalign_gap(p),
                "entry_list": entries,
            }
        )
    inv["splits"] = splits
    inv["totals"] = {
        "splits": len(splits),
        "entries": sum(s["entries"] for s in splits),
        "uncompressed": sum(s["uncompressed"] for s in splits),
        "dex_files": sum(1 for s in splits for e in s["entry_list"] if e["name"].endswith(".dex")),
        "native_libs": sum(1 for s in splits for e in s["entry_list"] if e["name"].endswith(".so")),
        "xml": sum(1 for s in splits for e in s["entry_list"] if e["name"].endswith(".xml")),
    }
    (DATA / "00_inventory.json").write_text(json.dumps(inv, indent=1, ensure_ascii=False), encoding="utf-8")
    t = inv["totals"]
    print(f"inventory: {t['entries']} entries across {t['splits']} APKs, "
          f"{t['uncompressed']/2**20:.1f} MiB uncompressed, {t['dex_files']} dex, {t['native_libs']} .so")


if __name__ == "__main__":
    main()
