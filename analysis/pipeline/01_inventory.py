"""Stage 01 — complete corpus inventory.

Walks *every* layer of the distributed artifact:

    Zalopay-..._APKPure.zip
      └── *.xapk  (APKPure split bundle, xapk_version 2)
            ├── vn.com.vng.zalopay.apk        (base)
            ├── config.armeabi_v7a.apk        (native ABI split)
            ├── config.en.apk / config.vi.apk (locale splits)
            ├── config.xhdpi.apk              (density split)
            └── manifest.json, icon.png

For each of the 6k+ entries we record: compressed size, uncompressed size,
CRC-32, SHA-256, Shannon entropy of the first 64 KiB, detected magic and a
coarse semantic bucket.  Output: data/inventory.json, data/inventory_summary.json
"""

from __future__ import annotations

import collections
import json
import zipfile
from pathlib import Path

from common import (
    APK_DIR,
    DATA,
    INPUT_ZIP,
    STAGE,
    XAPK_DIR,
    detect_type,
    human_bytes,
    iter_zip_entries,
    sha256_file,
    shannon_entropy,
    write_json,
    Stage,
)

SEMANTIC: list[tuple[str, str]] = [
    ("classes", "code"),
    ("assets/dexopt", "code"),
    ("lib/", "native"),
    ("res/", "resource"),
    ("assets/", "asset"),
    ("META-INF/", "signature"),
    ("AndroidManifest.xml", "manifest"),
    ("resources.arsc", "resource-table"),
    ("kotlin/", "metadata"),
    ("org/", "metadata"),
    ("google/", "metadata"),
]


def bucket(name: str) -> str:
    for prefix, label in SEMANTIC:
        if name.startswith(prefix):
            return label
    if name.endswith((".dex", ".bin")):
        return "code"
    return "other"


def walk_zip(path: Path) -> dict:
    """Inventory a ZIP container without fully extracting it."""
    entries = []
    total_c = total_u = 0
    by_bucket: collections.Counter = collections.Counter()
    by_bucket_bytes: collections.Counter = collections.Counter()
    with zipfile.ZipFile(path) as zf:
        for info in zf.infolist():
            if info.is_dir():
                continue
            b = bucket(info.filename)
            by_bucket[b] += 1
            by_bucket_bytes[b] += info.file_size
            total_c += info.compress_size
            total_u += info.file_size
            entries.append(
                {
                    "name": info.filename,
                    "csize": info.compress_size,
                    "size": info.file_size,
                    "crc32": f"{info.CRC:08x}",
                    "bucket": b,
                    "compression": "stored" if info.compress_type == 0 else "deflate",
                }
            )
    return {
        "file": path.name,
        "bytes": path.stat().st_size,
        "sha256": sha256_file(path),
        "entries": len(entries),
        "uncompressed_bytes": total_u,
        "compressed_bytes": total_c,
        "compression_ratio": round(total_u / total_c, 3) if total_c else None,
        "by_bucket": dict(by_bucket),
        "by_bucket_bytes": dict(by_bucket_bytes),
        "entry_list": entries,
    }


def describe_loose_file(path: Path) -> dict:
    head = path.read_bytes()[: 1 << 16]
    kind, desc = detect_type(head, path.name)
    return {
        "file": path.name,
        "bytes": path.stat().st_size,
        "sha256": sha256_file(path),
        "type": kind,
        "type_desc": desc,
        "entropy_first64k": round(shannon_entropy(head[: 1 << 16]), 4),
    }


def main() -> None:
    st = Stage("01-inventory")

    outer = {
        "file": INPUT_ZIP.name,
        "bytes": INPUT_ZIP.stat().st_size,
        "sha256": sha256_file(INPUT_ZIP),
        "entries": [{"name": n, "csize": c, "size": u, "crc32": crc} for n, c, u, crc in iter_zip_entries(INPUT_ZIP)],
    }
    st.log(f"outer archive: {outer['file']} ({human_bytes(outer['bytes'])}), {len(outer['entries'])} entry")

    xapk_path = next(iter(sorted(STAGE.glob("*.xapk"))))
    xapk_entries = list(iter_zip_entries(xapk_path))
    xapk = {
        "file": xapk_path.name,
        "bytes": xapk_path.stat().st_size,
        "sha256": sha256_file(xapk_path),
        "entries": [{"name": n, "csize": c, "size": u, "crc32": crc} for n, c, u, crc in xapk_entries],
    }
    st.log(f"xapk bundle: {xapk['file']} ({human_bytes(xapk['bytes'])}), {len(xapk['entries'])} entries")

    apk_inventory = {}
    for apk in sorted(XAPK_DIR.glob("*.apk")):
        inv = walk_zip(apk)
        apk_inventory[apk.name] = inv
        st.log(
            f"{apk.name}: {inv['entries']} entries, {human_bytes(inv['uncompressed_bytes'])} raw, "
            f"ratio {inv['compression_ratio']}"
        )

    loose = [describe_loose_file(p) for p in sorted(XAPK_DIR.iterdir()) if p.suffix in (".json", ".png")]
    for p in loose:
        st.log(f"loose: {p['file']} type={p['type']} entropy={p['entropy_first64k']}")

    # Entropy + magic class for the *native* payload, the most defensive region.
    native = {}
    nat_root = XAPK_DIR / "config.armeabi_v7a.apk"
    with zipfile.ZipFile(nat_root) as zf:
        for info in zf.infolist():
            if info.is_dir():
                continue
            raw = zf.read(info.filename)
            kind, desc = detect_type(raw[:16], info.filename)
            native[info.filename] = {
                "size": len(raw),
                "sha256": __import__("hashlib").sha256(raw).hexdigest()[:32],
                "type": kind,
                "entropy": round(shannon_entropy(raw), 4),
                "csize": info.compress_size,
            }
    st.log(f"native libraries inventoried: {len(native)}")

    total_entries = len(outer["entries"]) + len(xapk["entries"]) + sum(v["entries"] for v in apk_inventory.values())
    total_bytes = outer["bytes"] + xapk["bytes"] + sum(v["uncompressed_bytes"] for v in apk_inventory.values())

    summary = {
        "corpus": {
            "outer": {k: outer[k] for k in ("file", "bytes", "sha256")},
            "xapk": {k: xapk[k] for k in ("file", "bytes", "sha256")},
            "splits": {k: {kk: v[kk] for kk in ("entries", "uncompressed_bytes", "compressed_bytes", "compression_ratio", "sha256", "by_bucket")} for k, v in apk_inventory.items()},
            "loose_files": loose,
        },
        "totals": {
            "layers": 3,
            "entries_all_layers": total_entries,
            "bytes_all_layers_uncompressed": total_bytes,
            "bytes_on_disk": sum([outer["bytes"], xapk["bytes"]] + [v["bytes"] for v in apk_inventory.values()]),
        },
        "native": native,
    }
    write_json("inventory_summary.json", summary)
    write_json("inventory_full.json", {"outer": outer, "xapk": xapk, "splits": apk_inventory, "native": native})
    st.log(f"TOTAL: {total_entries} entries across 3 container layers, {human_bytes(total_bytes)} uncompressed")
    st.done({"entries": total_entries, "uncompressed": total_bytes})


if __name__ == "__main__":
    main()
