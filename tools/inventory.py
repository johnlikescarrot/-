#!/usr/bin/env python3
"""Chain-of-custody inventory for the ZaloPay XAPK research corpus.

Hashes every archive member without loading a 100 MB file into memory, writes a
machine-readable provenance record and a short Markdown manifest.
"""
from __future__ import annotations

import argparse
import hashlib
import json
import zipfile
from pathlib import Path

CHUNK = 1 << 20


def sha256_file(path: Path) -> str:
    h = hashlib.sha256()
    with path.open("rb") as fh:
        for chunk in iter(lambda: fh.read(CHUNK), b""):
            h.update(chunk)
    return h.hexdigest()


def sha256_member(zf: zipfile.ZipFile, name: str) -> str:
    h = hashlib.sha256()
    with zf.open(name) as fh:
        for chunk in iter(lambda: fh.read(CHUNK), b""):
            h.update(chunk)
    return h.hexdigest()


def main() -> None:
    ap = argparse.ArgumentParser()
    ap.add_argument("--archive", type=Path, required=True)
    ap.add_argument("--out", type=Path, default=Path("research"))
    ap.add_argument("--extract", type=Path, default=Path("work/extracted"))
    args = ap.parse_args()

    args.out.mkdir(parents=True, exist_ok=True)
    record: dict = {"outer_archive": {"file": args.archive.name,
                                       "bytes": args.archive.stat().st_size,
                                       "sha256": sha256_file(args.archive)},
                    "containers": []}

    with zipfile.ZipFile(args.archive) as outer:
        for outer_name in outer.namelist():
            if not outer_name.lower().endswith(".xapk"):
                continue
            xapk_hash = sha256_member(outer, outer_name)
            container: dict = {"file": outer_name, "sha256": xapk_hash,
                               "members": [], "manifest": None}
            args.extract.mkdir(parents=True, exist_ok=True)
            xapk_path = args.extract / Path(outer_name).name
            with outer.open(outer_name) as src, xapk_path.open("wb") as dst:
                while True:
                    chunk = src.read(CHUNK)
                    if not chunk:
                        break
                    dst.write(chunk)
            with zipfile.ZipFile(xapk_path) as inner:
                for info in inner.infolist():
                    member = {"name": info.filename, "bytes": info.file_size,
                              "compressed_bytes": info.compress_size,
                              "sha256": sha256_member(inner, info.filename)}
                    container["members"].append(member)
                    if info.filename == "manifest.json":
                        container["manifest"] = json.loads(inner.read(info.filename))
                inner.extractall(args.extract / "xapk")
            record["containers"].append(container)

    (args.out / "provenance.json").write_text(
        json.dumps(record, indent=2, ensure_ascii=False) + "\n", encoding="utf-8")

    lines = ["# Provenance / chain of custody", "",
             f"Outer archive: `{record['outer_archive']['file']}` — "
             f"{record['outer_archive']['bytes']:,} bytes — "
             f"SHA-256 `{record['outer_archive']['sha256']}`", ""]
    for c in record["containers"]:
        lines += [f"Container: `{c['file']}` — SHA-256 `{c['sha256']}`", "",
                  "| Member | Bytes | SHA-256 |", "| --- | ---: | --- |"]
        for m in c["members"]:
            lines.append(f"| `{m['name']}` | {m['bytes']:,} | `{m['sha256']}` |")
        lines.append("")
    (args.out / "PROVENANCE.md").write_text("\n".join(lines), encoding="utf-8")
    print(json.dumps({"archive_sha256": record["outer_archive"]["sha256"],
                      "members": len(record["containers"][0]["members"])}, indent=2))


if __name__ == "__main__":
    main()
