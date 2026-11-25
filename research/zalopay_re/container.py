"""APK container + entry-level inventory.

The artifact under study is a three-level nesting:

    Zalopay-...APKPure.zip          (plain zip, 1 member, 98.8 MB)
      └── ...APKPure.xapk           (zip with APKPure metadata, 105.6 MB)
            ├── vn.com.vng.zalopay.apk            base module  (78 MB)
            ├── config.armeabi_v7a.apk            ABI split    (25 MB)
            ├── config.{en,vi}.apk                locale splits
            ├── config.xhdpi.apk                  density split
            └── manifest.json                     APKPure manifest (not AOSP)

Every directory entry of every member is catalogued: size, compressed size,
SHA-256, Shannon entropy and a semantic category.  That gives an auditable
"what is actually in the box" table before any decoding happens.
"""

from __future__ import annotations

import hashlib
import io
import json
import math
import os
import zipfile
from collections import Counter, defaultdict
from dataclasses import dataclass
from typing import Dict, Iterable, List, Optional, Tuple

from .elf import shannon_entropy

# ---------------------------------------------------------------------------
# semantic classification of entry paths
# ---------------------------------------------------------------------------
CATEGORY_RULES: List[Tuple[str, str]] = [
    (".dex", "dalvik-bytecode"),
    ("assets/audience_network.dex", "dalvik-bytecode (secondary)"),
    ("lib/", "native-code"),
    ("res/", "android-resource"),
    ("resources.arsc", "resource-table"),
    ("AndroidManifest.xml", "binary-manifest"),
    ("META-INF/", "signature-or-service-metadata"),
    ("assets/", "bundled-asset"),
    ("classes", "dalvik-bytecode"),
    ("kotlin/", "kotlin-metadata"),
    (".version", "library-version-stamp"),
    (".properties", "library-config"),
]

RES_SUBTYPES = {
    "anim": "animation", "animator": "animation", "color": "color", "drawable": "image-vector",
    "font": "font", "interpolator": "animation", "layout": "layout", "menu": "menu",
    "mipmap": "icon", "navigation": "navigation", "raw": "raw-blob", "values": "values",
    "xml": "xml-config", "transition": "animation",
}


def categorize(path: str) -> str:
    for needle, cat in CATEGORY_RULES:
        if needle in path:
            return cat
    return "other"


def res_subtype(path: str) -> Optional[str]:
    if not path.startswith("res/"):
        return None
    parts = path.split("/")
    if len(parts) < 2:
        return None
    return RES_SUBTYPES.get(parts[1], parts[1])


def ext_of(path: str) -> str:
    base = os.path.basename(path)
    if "." not in base:
        return "<none>"
    return "." + base.rsplit(".", 1)[1].lower()


@dataclass
class Entry:
    apk: str
    path: str
    size: int
    csize: int
    sha256: str
    ext: str
    category: str
    res_subtype: Optional[str]
    entropy: float

    def to_json(self) -> dict:
        return {
            "apk": self.apk, "path": self.path, "size": self.size, "csize": self.csize,
            "sha256": self.sha256, "ext": self.ext, "category": self.category,
            "res_subtype": self.res_subtype, "entropy": round(self.entropy, 3),
        }


def hash_stream(fh, limit_chunks: int = 0) -> Tuple[str, int]:
    h = hashlib.sha256()
    n = 0
    while True:
        b = fh.read(1 << 20)
        if not b:
            break
        h.update(b)
        n += len(b)
    return h.hexdigest(), n


def inventory_apk(apk_path: str, apk_label: str, entropy: bool = True) -> List[Entry]:
    out: List[Entry] = []
    with zipfile.ZipFile(apk_path) as z:
        for info in z.infolist():
            if info.is_dir():
                continue
            blob = z.read(info) if (entropy and info.file_size <= 8 << 20) else b""
            sha = hashlib.sha256(blob).hexdigest() if blob else ""
            ent = shannon_entropy(blob) if blob else 0.0
            out.append(Entry(
                apk=apk_label, path=info.filename, size=info.file_size,
                csize=info.compress_size, sha256=sha, ext=ext_of(info.filename),
                category=categorize(info.filename), res_subtype=res_subtype(info.filename),
                entropy=ent,
            ))
    return out


def container_tree(zip_path: str) -> dict:
    """Walk the three nesting levels and record hashes/sizes/metadata."""
    node = {"path": os.path.basename(zip_path), "size": os.path.getsize(zip_path)}
    with open(zip_path, "rb") as fh:
        node["sha256"] = hashlib.sha256(fh.read()).hexdigest()
    node["kind"] = "zip"
    members = []
    with zipfile.ZipFile(zip_path) as z:
        for info in z.infolist():
            members.append({"name": info.filename, "size": info.file_size,
                            "csize": info.compress_size})
    node["members"] = members
    return node


def summarize(entries: List[Entry]) -> dict:
    by_apk = Counter(e.apk for e in entries)
    by_cat = Counter(e.category for e in entries)
    by_ext = Counter(e.ext for e in entries)
    res_types = Counter(e.res_subtype for e in entries if e.res_subtype)
    bytes_by_cat = defaultdict(int)
    for e in entries:
        bytes_by_cat[e.category] += e.size
    dupes = defaultdict(set)
    for e in entries:
        dupes[e.path].add(e.apk)
    duplicated = {p: sorted(v) for p, v in dupes.items() if len(v) > 1}
    high_entropy = [e.to_json() for e in entries if e.entropy > 7.5 and e.size > 4096][:200]
    return {
        "entries": len(entries),
        "entries_by_apk": dict(by_apk),
        "by_category": dict(by_cat),
        "bytes_by_category": dict(bytes_by_cat),
        "by_extension": dict(by_ext.most_common(40)),
        "resource_subtypes": dict(res_types.most_common(40)),
        "duplicated_paths": dict(list(duplicated.items())[:50]),
        "high_entropy_entries": high_entropy,
        "total_uncompressed": sum(e.size for e in entries),
        "total_compressed": sum(e.csize for e in entries),
    }
