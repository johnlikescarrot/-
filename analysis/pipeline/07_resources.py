"""Stage 07 — resource table, locale coverage and asset classification.

The base APK's `resources.arsc` holds **13 package chunks** — the base package
plus one per Google Play Feature Delivery module, which is why the app can ship a
cashier, a liveness check and a PIN reset flow as separately-installed modules
while still referencing them from the base manifest.

Outputs: data/resources.json, data/assets.json, data/facts_resources.json
"""

from __future__ import annotations

import collections
import re
import zipfile
from pathlib import Path

from common import APK_DIR, XAPK_DIR, human_bytes, read_json, shannon_entropy, write_json, Stage

LOCALE_RE = re.compile(r"^[a-z]{2,3}(-r[A-Z]{2})?(-.*)?$")


def main() -> None:
    st = Stage("07-resources")

    base = XAPK_DIR / "vn.com.vng.zalopay.apk"
    zf = zipfile.ZipFile(base)

    # ---------- 1. resource table ------------------------------------------
    arsc_raw = zf.read("resources.arsc")
    st.log(f"resources.arsc: {human_bytes(len(arsc_raw))}, entropy {shannon_entropy(arsc_raw):.3f}")

    import struct

    # ARSC header: type(2) headerSize(2) size(4) packageCount(4) — actually the
    # RES_TABLE_TYPE header is 12 bytes: chunk header(8) + packageCount(4).
    pkg_count = struct.unpack_from("<I", arsc_raw, 8)[0]
    st.log(f"ARSC declares {pkg_count} package chunks (base + Play Feature Delivery modules)")

    module_pkgs = []
    try:



        from androguard.core.axml import ARSCParser

        arsc = ARSCParser(arsc_raw)
        pkgs = arsc.get_packages_names()
        for p in pkgs:
            locales = arsc.get_locales(p)
            module_pkgs.append(
                {
                    "package": p,
                    "module": "base" if p == "vn.com.vng.zalopay" else p.replace("vn.com.vng.zalopay.", ""),
                    "locales": [l for l in locales if l != "\x00\x00"],
                    "locale_count": len([l for l in locales if l != "\x00\x00"]),
                }
            )
        st.log(f"resource packages: {len(module_pkgs)} → {[m['module'] for m in module_pkgs]}")
    except Exception as exc:  # noqa: BLE001
        st.log(f"ARSC deep parse skipped: {exc!r}")

    # ---------- 2. drawable / layout / raw census ---------------------------
    res_names = [n for n in zf.namelist() if n.startswith("res/")]
    res_types = collections.Counter()
    res_dirs = collections.Counter()
    for n in res_names:
        parts = n.split("/")
        res_types[parts[0] + "/" + (parts[1].split("-")[0] if len(parts) > 1 else "")] += 1
        res_dirs["-".join(parts[1].split("-")[1:]) or "default"] += 1
    st.log(f"res/: {len(res_names):,} files across {len(res_types)} type buckets, {len(res_dirs)} qualifier sets")

    drawables = [n for n in res_names if n.startswith("res/drawable")]
    layouts = [n for n in res_names if n.startswith("res/layout")]
    xmls = [n for n in res_names if n.endswith(".xml")]
    pngs = [n for n in res_names if n.endswith(".png")]
    webps = [n for n in res_names if n.endswith(".webp")]
    ninepatch = [n for n in res_names if n.endswith(".9.png")]
    st.log(f"drawables {len(drawables):,} (png {len(pngs):,} / webp {len(webps):,} / 9-patch {len(ninepatch):,}) · layouts {len(layouts):,} · xml {len(xmls):,}")

    # ---------- 3. locale coverage across all splits ------------------------
    locales = set()
    for apk in sorted(XAPK_DIR.glob("config.*.apk")):
        tag = apk.stem.split(".", 1)[1]
        if tag in ("en", "vi"):
            locales.add(tag)
    for d in res_dirs:
        for tok in d.split("-"):
            if re.fullmatch(r"[a-z]{2,3}", tok) and tok not in ("v21", "v26", "v24", "v31", "v33", "v35", "v4", "v5", "ldrtl"):
                locales.add(tok)
    locales = sorted(l for l in locales if l not in {"hdpi", "mdpi", "xhdpi", "xxhdpi", "xxxhdpi", "anydpi", "nodpi", "tvdpi"})
    st.log(f"locale coverage: {len(locales)} language tags → {', '.join(locales[:24])}{'…' if len(locales) > 24 else ''}")

    # ---------- 4. assets ---------------------------------------------------
    assets = []
    for n in zf.namelist():
        if not n.startswith("assets/") or n.endswith("/"):
            continue
        info = zf.getinfo(n)
        blob = zf.read(n)
        ent = shannon_entropy(blob[: 1 << 16])
        assets.append(
            {
                "path": n,
                "bytes": info.file_size,
                "entropy": round(ent, 4),
                "class": classify_asset(n, blob[:16]),
                "protected": ent > 7.95 and info.file_size > 4096,
            }
        )
    assets.sort(key=lambda a: -a["bytes"])
    protected = [a for a in assets if a["protected"]]
    st.log(f"assets/: {len(assets)} files, {human_bytes(sum(a['bytes'] for a in assets))} total, {len(protected)} high-entropy")

    classes = collections.Counter(a["class"] for a in assets)
    st.log("asset classes: " + ", ".join(f"{k}={v}" for k, v in classes.most_common()))

    # ---------- 5. native lib inventory from the ABI split ------------------
    nat = zipfile.ZipFile(XAPK_DIR / "config.armeabi_v7a.apk")
    libs = [
        {"name": i.filename.rsplit("/", 1)[-1], "bytes": i.file_size}
        for i in nat.infolist()
        if i.filename.endswith(".so")
    ]
    libs.sort(key=lambda x: -x["bytes"])
    st.log(f"ABI split: {len(libs)} .so files, {human_bytes(sum(l['bytes'] for l in libs))} (armeabi-v7a only)")

    write_json(
        "resources.json",
        {
            "arsc_bytes": len(arsc_raw),
            "package_chunks": pkg_count,
            "modules": module_pkgs,
            "res_files": len(res_names),
            "type_buckets": res_types.most_common(40),
            "qualifier_sets": res_dirs.most_common(40),
            "locales": locales,
            "media": {
                "png": len(pngs),
                "webp": len(webps),
                "ninepatch": len(ninepatch),
                "layouts": len(layouts),
                "xml": len(xmls),
            },
            "native_libs": libs,
        },
    )
    write_json(
        "assets.json",
        {
            "count": len(assets),
            "bytes": sum(a["bytes"] for a in assets),
            "classes": dict(classes),
            "protected": protected,
            "all": assets,
        },
    )
    write_json(
        "facts_resources.json",
        {
            "modules": [m["module"] for m in module_pkgs],
            "moduleCount": len(module_pkgs),
            "resFiles": len(res_names),
            "locales": len(locales),
            "assets": len(assets),
            "assetBytes": sum(a["bytes"] for a in assets),
            "protectedAssets": len(protected),
            "nativeLibs": len(libs),
            "nativeBytes": sum(l["bytes"] for l in libs),
        },
    )
    st.done({"modules": len(module_pkgs), "assets": len(assets), "protected": len(protected)})


def classify_asset(path: str, head: bytes) -> str:
    low = path.lower()
    if low.endswith(".dex"):
        return "dalvik"
    if low.endswith(".js"):
        return "javascript"
    if low.endswith(".json"):
        return "config-json"
    if low.endswith(".pem"):
        return "public-key"
    if low.endswith((".ncnn.bin", ".ncnn.param", ".onnx")) or "model" in low:
        return "ml-model"
    if low.endswith(".dat"):
        return "encrypted-blob"
    if low.endswith(".zip") or head.startswith(b"PK"):
        return "bundle-zip"
    if low.endswith((".ttf", ".otf", ".woff")):
        return "font"
    if head.startswith(b"\x89PNG") or low.endswith((".png", ".webp", ".jpg")):
        return "image"
    if "/griver/" in low or low.startswith("assets/griver"):
        return "miniapp-runtime"
    if "/config/" in low:
        return "config"
    return "other"


if __name__ == "__main__":
    main()
