#!/usr/bin/env python3
"""Read-only recursive ZIP/TAR inventory, including each file's SHA-256.

Members are read as bytes, never extracted by their names. ZIP and TAR are
recognized by magic, including ZIPs disguised as .amr. Gzip streams are also
expanded and hashed; if their content is an archive, that archive is visited.
Any admission or decoding failure makes coverage partial and returns exit 2.
"""
from __future__ import annotations

import argparse
import gzip
import hashlib
import io
import json
import sys
import tarfile
import zipfile
from collections import Counter
from pathlib import Path
from typing import Any

ZIP_HEADERS = (b"PK\x03\x04", b"PK\x05\x06", b"PK\x07\x08")


def kind_of(name: str, data: bytes) -> str:
    if data.startswith(ZIP_HEADERS):
        return "zip"
    if len(data) >= 512 and data[257:262] == b"ustar":
        return "tar"
    if data.startswith(b"dex\n"):
        return "dex"
    if data.startswith(b"\x7fELF"):
        return "elf"
    if data.startswith(b"\x89PNG\r\n\x1a\n"):
        return "png"
    if data.startswith(b"\xff\xd8\xff"):
        return "jpeg"
    if data.startswith(b"RIFF") and data[8:12] == b"WEBP":
        return "webp"
    if data.startswith(b"\x1f\x8b"):
        return "gzip"
    if name.endswith(".xml") and data[:4] == b"\x03\x00\x08\x00":
        return "android_binary_xml"
    if name.endswith(".arsc") and data[:2] == b"\x02\x00":
        return "android_resources"
    return "other"


def inventory(path: Path, *, max_depth: int = 8, max_member: int = 128 * 1024**2,
              max_expanded: int = 1024**3, max_entries: int = 100_000) -> dict[str, Any]:
    rows: list[dict[str, Any]] = []
    errors: list[dict[str, str]] = []
    archives: list[dict[str, Any]] = []
    expanded = 0
    if path.stat().st_size > max_member:
        raise ValueError("root archive exceeds input byte limit")
    root_bytes = path.read_bytes()
    root_sha256 = hashlib.sha256(root_bytes).hexdigest()

    def label_for(chain: list[dict[str, Any]]) -> str:
        return "!/".join([path.name] + [str(x["name"]) for x in chain])

    def visit(payload: bytes, chain: list[dict[str, Any]], depth: int, fmt: str) -> None:
        nonlocal expanded
        label = label_for(chain)
        try:
            if fmt == "zip":
                container = zipfile.ZipFile(io.BytesIO(payload))
                members = container.infolist()
                names = Counter(m.filename for m in members)
            elif fmt == "tar":
                container = tarfile.open(fileobj=io.BytesIO(payload), mode="r:")
                members = container.getmembers()
                names = Counter(m.name for m in members)
            else:
                raise ValueError(f"unsupported container: {fmt}")
        except (zipfile.BadZipFile, tarfile.TarError, OSError, ValueError) as exc:
            errors.append({"path": label, "error": f"cannot read {fmt}: {exc}"})
            return
        archives.append({"path": label, "depth": depth, "format": fmt, "entries": len(members)})
        with container:
            for index, info in enumerate(members):
                if len(rows) >= max_entries:
                    errors.append({"path": label, "error": "entry limit exceeded"})
                    return
                name = info.filename if fmt == "zip" else info.name
                size = info.file_size if fmt == "zip" else info.size
                is_dir = info.is_dir() if fmt == "zip" else info.isdir()
                is_file = (not is_dir) if fmt == "zip" else info.isfile()
                child_chain = chain + [{"index": index, "name": name}]
                child_path = label_for(child_chain)
                record: dict[str, Any] = {
                    "path": child_path, "chain": child_chain, "container_format": fmt,
                    "size": size,
                    "compressed_size": info.compress_size if fmt == "zip" else None,
                    "crc32": f"{info.CRC:08x}" if fmt == "zip" else None,
                    "compression_method": info.compress_type if fmt == "zip" else None,
                    "directory": is_dir, "regular_file": is_file,
                    "duplicate_name_in_container": names[name] > 1,
                }
                rows.append(record)
                if is_dir:
                    record.update({"kind": "directory", "sha256": None})
                    continue
                if not is_file:
                    # In particular, never follow TAR symlinks or hardlinks.
                    record.update({"kind": "special", "sha256": None,
                                   "tar_type": repr(info.type), "linkname": info.linkname})
                    continue
                if fmt == "zip" and info.flag_bits & 1:
                    record["error"] = "encrypted member"
                elif size > max_member:
                    record["error"] = "member size limit exceeded"
                elif expanded + size > max_expanded:
                    record["error"] = "expanded byte limit exceeded"
                else:
                    try:
                        if fmt == "zip":
                            # ZipFile.read checks CRC and uses ZipInfo to disambiguate duplicates.
                            data = container.read(info)
                        else:
                            stream = container.extractfile(info)
                            if stream is None:
                                raise ValueError("regular TAR member has no data")
                            with stream:
                                data = stream.read(max_member + 1)
                        if len(data) != size:
                            raise ValueError("decoded size differs from declaration")
                        expanded += len(data)
                        record["sha256"] = hashlib.sha256(data).hexdigest()
                        record["kind"] = kind_of(name, data)
                        nested_data, nested_kind = data, record["kind"]
                        nested_chain = child_chain
                        if nested_kind == "gzip":
                            with gzip.GzipFile(fileobj=io.BytesIO(data)) as gz:
                                decoded = gz.read(max_member + 1)
                            if len(decoded) > max_member or expanded + len(decoded) > max_expanded:
                                raise ValueError("gzip expanded byte limit exceeded")
                            expanded += len(decoded)
                            record["gzip_expanded_size"] = len(decoded)
                            record["gzip_expanded_sha256"] = hashlib.sha256(decoded).hexdigest()
                            nested_data = decoded
                            nested_kind = kind_of("<gzip-content>", decoded)
                            record["gzip_expanded_kind"] = nested_kind
                            nested_chain = child_chain + [{"index": 0, "name": "<gzip-content>"}]
                        if nested_kind in ("zip", "tar"):
                            if depth >= max_depth:
                                record["error"] = "nested depth limit exceeded"
                            else:
                                visit(nested_data, nested_chain, depth + 1, nested_kind)
                    except (ValueError, OSError, EOFError, RuntimeError, NotImplementedError,
                            zipfile.BadZipFile, tarfile.TarError) as exc:
                        record["error"] = f"read failed: {type(exc).__name__}: {exc}"
                if "error" in record:
                    errors.append({"path": child_path, "error": str(record["error"])})

    if kind_of(path.name, root_bytes) != "zip":
        raise ValueError("root must be a ZIP archive")
    visit(root_bytes, [], 0, "zip")
    files = [r for r in rows if r["regular_file"]]
    summary = {
        "schema_version": 2,
        "root": {"name": path.name, "bytes": len(root_bytes), "sha256": root_sha256},
        "coverage": "complete" if not errors else "partial",
        "archives": archives,
        "entry_count_including_nonfiles": len(rows),
        "file_occurrences": len(files),
        "directories": sum(bool(r["directory"]) for r in rows),
        "special_entries": sum(not r["regular_file"] and not r["directory"] for r in rows),
        "file_occurrences_hashed": sum("sha256" in r for r in files),
        "expanded_bytes_including_container_members_and_gzip_streams": expanded,
        "types_by_file_occurrence": dict(sorted(Counter(r.get("kind", "unread") for r in files).items())),
        "duplicate_name_occurrences": sum(bool(r["duplicate_name_in_container"]) for r in rows),
        "limits": {"max_depth": max_depth, "max_member_bytes": max_member,
                   "max_expanded_bytes": max_expanded, "max_entries": max_entries},
        "errors": errors,
        "scope_note": "ZIP central-directory and TAR logical entries; nested ZIP/TAR/gzip identified by leading magic (TAR ustar header). Gzip streams are hashed separately from their enclosing files. Other compression, encrypted, custom or self-extracting containers are not expanded. The root ZIP is summarized separately.",
    }
    return {"summary": summary, "rows": rows}


def write_outputs(result: dict[str, Any], outdir: Path) -> None:
    outdir.mkdir(parents=True, exist_ok=True)
    (outdir / "inventory-summary.json").write_text(
        json.dumps(result["summary"], ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    # mtime=0 and no embedded filename make the compressed inventory stable.
    with (outdir / "inventory.jsonl.gz").open("wb") as raw:
        with gzip.GzipFile(filename="", mode="wb", fileobj=raw, mtime=0) as out:
            for row in result["rows"]:
                out.write((json.dumps(row, ensure_ascii=False, sort_keys=True) + "\n").encode("utf-8"))


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("archive", type=Path)
    parser.add_argument("--out", type=Path, default=Path("research"))
    args = parser.parse_args()
    result = inventory(args.archive)
    write_outputs(result, args.out)
    print(json.dumps(result["summary"], ensure_ascii=False, indent=2))
    return 0 if result["summary"]["coverage"] == "complete" else 2


if __name__ == "__main__":
    sys.exit(main())
