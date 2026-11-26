#!/usr/bin/env python3
"""Build Vietnamese-complete display fonts for the film.

Google Fonts ships Be Vietnam Pro split into unicode-range subsets: the `latin`
file has basic Latin, the `vietnamese` file has the stacked diacritics, and
neither is usable alone for Vietnamese copy that also contains Latin numerals
and evidence strings.  This script merges the two subsets of the same family
and weight into one TTF and rewrites the family name so the renderer can
address it unambiguously.

Run:  .venv/bin/python video/tools/build_fonts.py
Output: video/assets/fonts/merged/*.ttf  (committed; renderer input)
"""
from __future__ import annotations

import os
import sys

from fontTools.merge import Merger
from fontTools.ttLib import TTFont

HERE = os.path.dirname(os.path.abspath(__file__))
FONTS = os.path.join(HERE, "..", "assets", "fonts")
OUT = os.path.join(FONTS, "merged")

# (latin subset, vietnamese subset, output name, family, subfamily, weight class)
JOBS = [
    ("BeVietnamPro-Latin-Regular.ttf", "BeVietnamPro-Regular.ttf",
     "BVN-Regular.ttf", "BVN", "Regular", 400),
    ("BeVietnamPro-Latin-Regular.ttf", "BeVietnamPro-SemiBold.ttf",
     "BVN-SemiBold.ttf", "BVN", "SemiBold", 600),
    ("BeVietnamPro-Latin-ExtraBold.ttf", "BeVietnamPro-ExtraBold.ttf",
     "BVN-ExtraBold.ttf", "BVN", "ExtraBold", 800),
    ("IBMPlexMono-Regular.ttf", "IBMPlexMono-VN-Regular.ttf",
     "PlexMono-Regular.ttf", "PlexMono", "Regular", 400),
    ("IBMPlexMono-SemiBold.ttf", "IBMPlexMono-VN-SemiBold.ttf",
     "PlexMono-SemiBold.ttf", "PlexMono", "SemiBold", 600),
]

VN_PROBE = "ẫữơạĐđệĂÂÊÔƠƯăâêôơưỹ"
LATIN_PROBE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.,:;()[]%+/&#—–·"


def rename(font: TTFont, family: str, subfamily: str, weight: int) -> None:
    name = font["name"]
    full = f"{family} {subfamily}"
    ps = f"{family}-{subfamily}"
    for rec in list(name.names):
        if rec.nameID == 1:
            name.setName(family, 1, rec.platformID, rec.platEncID, rec.langID)
        elif rec.nameID == 2:
            name.setName(subfamily, 2, rec.platformID, rec.platEncID, rec.langID)
        elif rec.nameID == 4:
            name.setName(full, 4, rec.platformID, rec.platEncID, rec.langID)
        elif rec.nameID == 6:
            name.setName(ps, 6, rec.platformID, rec.platEncID, rec.langID)
        elif rec.nameID in (16, 17):
            name.removeNames(nameID=rec.nameID)
    font["OS/2"].usWeightClass = weight
    if "fvar" in font:
        del font["fvar"]


def coverage(font: TTFont) -> tuple[int, int]:
    cmap = font.getBestCmap()
    vn = sum(1 for ch in VN_PROBE if ord(ch) in cmap)
    la = sum(1 for ch in LATIN_PROBE if ord(ch) in cmap)
    return vn, la


def main() -> int:
    os.makedirs(OUT, exist_ok=True)
    failures = 0
    for latin, viet, out_name, family, sub, weight in JOBS:
        a = os.path.join(FONTS, latin)
        b = os.path.join(FONTS, viet)
        if not (os.path.exists(a) and os.path.exists(b)):
            print(f"skip {out_name}: missing subset")
            failures += 1
            continue
        # Sorted glyph IDs per source keep the merged output byte-stable.
        merger = Merger()
        merged = merger.merge([TTFont(a), TTFont(b)])
        rename(merged, family, sub, weight)
        merged["OS/2"].ulUnicodeRange1 |= 1 << 0
        dst = os.path.join(OUT, out_name)
        merged.save(dst)
        f = TTFont(dst)
        vn, la = coverage(f)
        print(f"{out_name:<26}{os.path.getsize(dst)//1024:>4} KB  glyphs={len(f.getGlyphOrder()):>4} "
              f"vn={vn}/{len(VN_PROBE)} latin={la}/{len(LATIN_PROBE)} family={family} {sub}")
        if vn < len(VN_PROBE) or la < len(LATIN_PROBE):
            failures += 1
    return 1 if failures else 0


if __name__ == "__main__":
    sys.exit(main())
