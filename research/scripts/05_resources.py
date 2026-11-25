#!/usr/bin/env python3
"""Stage 5 — resource tables, localisation split, on-device ML models, KYC/security assets."""
from __future__ import annotations

import json
import struct
import zipfile
from collections import Counter
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
DATA = ROOT / "research" / "data"
BASE = ROOT / "work" / "apks" / "vn.com.vng.zalopay.apk"
SPLITS = ROOT / "work" / "apks"


class Arsc:
    """Just enough of resources.arsc to count types and configurations."""

    def __init__(self, blob: bytes) -> None:
        self.b = blob
        self.pkg_count = 0
        self.type_names: set[str] = set()
        self.configs: Counter[str] = Counter()
        self._walk()

    def _string_pool(self, off: int) -> list[str]:
        b = self.b
        _type, hsize, size = struct.unpack_from("<HHI", b, off)
        count, _styles = struct.unpack_from("<II", b, off + 8)
        flags, strings_start, _ = struct.unpack_from("<III", b, off + 16)
        utf8 = bool(flags & 0x100)
        offsets = struct.unpack_from(f"<{count}I", b, off + hsize)
        out = []
        for o in offsets:
            p = off + strings_start + o
            if utf8:
                n = b[p]
                p += 2 if n & 0x80 else 1
                ln = b[p]
                if ln & 0x80:
                    ln = ((ln & 0x7F) << 8) | b[p + 1]
                    p += 2
                else:
                    p += 1
                out.append(b[p : p + ln].decode("utf-8", "replace"))
            else:
                ln = struct.unpack_from("<H", b, p)[0]
                p += 2
                if ln & 0x8000:
                    ln = ((ln & 0x7FFF) << 16) | struct.unpack_from("<H", b, p)[0]
                    p += 2
                out.append(b[p : p + ln * 2].decode("utf-16-le", "replace"))
        return out

    def _walk(self) -> None:
        b = self.b
        if struct.unpack_from("<H", b, 0)[0] != 0x0002:
            return
        _t, hsize, total = struct.unpack_from("<HHI", b, 0)
        pos = hsize
        while pos < min(total, len(b)) - 8:
            ctype, chsize, csize = struct.unpack_from("<HHI", b, pos)
            if csize <= 0:
                break
            if ctype == 0x0001:  # string pool (global)
                self.global_strings = self._string_pool(pos)
            elif ctype == 0x0200:  # table package
                self.pkg_count += 1
                ppos = pos + chsize
                pkg_end = pos + csize
                while ppos < pkg_end - 8:
                    t, h, s = struct.unpack_from("<HHI", b, ppos)
                    if s <= 0:
                        break
                    if t == 0x0201:  # type spec
                        type_id = b[ppos + 8]
                        self.type_names.add(str(type_id))
                    elif t == 0x0202:  # type
                        type_id = b[ppos + 8]
                        cfg_size = struct.unpack_from("<I", b, ppos + 20)[0]
                        # config: language/country at +8/+10 inside config struct
                        lang = b[ppos + 8 + 8 : ppos + 8 + 10]
                        country = b[ppos + 8 + 10 : ppos + 8 + 12]
                        dpi = struct.unpack_from("<H", b, ppos + 8 + 14)[0] if cfg_size >= 16 else 0
                        tag = bytes(lang + country).decode("ascii", "replace").strip("\x00") or "default"
                        if dpi:
                            tag += f"+{dpi}dpi"
                        self.configs[tag] += 1
                    ppos += s
            pos += csize


def main() -> None:
    with zipfile.ZipFile(BASE) as z:
        arsc = Arsc(z.read("resources.arsc"))
        names = z.namelist()
        models = [n for n in names if n.endswith((".tflite", ".tfl", ".bin", ".param", ".emd"))
                  and ("model" in n or "mlkit" in n or "ncnn" in n or "FastestDet" in n or "contours" in n)]
        assets = z.namelist()
        sizes = {i.filename: i.file_size for i in z.infolist()}
        geo = [n for n in names if n.startswith("res/") and any(k in n for k in
               ("img_guide", "kyc", "nfc", "liveness", "face", "scan", "card"))]
        security_assets = [n for n in assets if n.startswith("assets/") and any(
            k in n for k in ("bshield", "taibmcf", "fgcpkjvqe", "public.pem", "zoom.public",
                             "safemode", "vdfmoer", "iapconnect", "dabc8a718", "containererrorpage"))]
        model_rows = sorted(({"asset": n, "bytes": sizes[n]} for n in set(models)),
                            key=lambda r: -r["bytes"])
        ml_total = sum(r["bytes"] for r in model_rows)
    out = {
        "arsc": {
            "bytes": sizes.get("resources.arsc"),
            "packages": arsc.pkg_count,
            "type_ids": len(arsc.type_names),
            "configurations": len(arsc.configs),
            "top_configs": arsc.configs.most_common(25),
            "global_strings": len(getattr(arsc, "global_strings", [])),
        },
        "locale_splits": {
            "config.en.apk": "English resources split",
            "config.vi.apk": "Vietnamese resources split",
            "note": "base APK ships both; 5,209 XML layouts/drawables in base",
        },
        "on_device_ml": {"count": len(model_rows), "total_bytes": ml_total,
                         "models": model_rows,
                         "note": "models are the byte payloads; the runtime code lives in lib/*.so"},
        "kyc_security_assets": {
            "count": len(security_assets),
            "assets": [{"asset": n, "bytes": sizes[n]} for n in sorted(security_assets)],
        },
        "kyc_ui_resources": {"count": len(geo), "sample": sorted(geo)[:30]},
    }
    (DATA / "05_resources.json").write_text(json.dumps(out, indent=1, ensure_ascii=False), encoding="utf-8")
    print(f"resources: arsc {out['arsc']['bytes']/2**20:.2f} MiB, {out['arsc']['configurations']} configs, "
          f"{out['arsc']['global_strings']:,} strings; ML models {len(model_rows)} "
          f"({ml_total/2**20:.1f} MiB); security assets {len(security_assets)}")


if __name__ == "__main__":
    main()
