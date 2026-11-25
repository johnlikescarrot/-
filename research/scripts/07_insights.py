#!/usr/bin/env python3
"""Stage 7 — derived insights: obfuscation, mini-app catalogue, PIN envelope surface."""
from __future__ import annotations

import json
import re
import sys
import zipfile
from collections import Counter
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent))
from dex_parser import DexFile  # noqa: E402

ROOT = Path(__file__).resolve().parents[2]
DATA = ROOT / "research" / "data"
BASE = ROOT / "work" / "apks" / "vn.com.vng.zalopay.apk"
DEXDIR = ROOT / "work" / "dex"

OBF_RE = re.compile(r"^(?:[a-z]{1,3}[0-9]{0,2}|[A-Z][a-z]?)$")


def main() -> None:
    # ---- obfuscation ------------------------------------------------------
    pkgs = Counter()
    classes = 0
    for p in sorted(DEXDIR.glob("classes*.dex")):
        for c in DexFile(p).classes():
            classes += 1
            name = c.name.strip("L;")
            pkgs["/".join(name.split("/")[:-1])] += 1
    pkg_names = list(pkgs)
    obf = [n for n in pkg_names if n and all(OBF_RE.match(seg) for seg in n.split("/"))]
    obf_classes = sum(pkgs[n] for n in obf)

    # ---- mini-app catalogue ----------------------------------------------
    with zipfile.ZipFile(BASE) as z:
        mini = json.loads(z.read("assets/config/miniapp_config.json"))
        home = json.loads(z.read("assets/home_data.json"))
        kotlin_modules = [n for n in z.namelist() if n.endswith(".kotlin_module")]
        griver = sorted(n for n in z.namelist() if n.startswith("assets/Griver"))
    apps = mini.get("data", [])
    cats = Counter(a.get("category_name") for a in apps)
    hosts = Counter(re.sub(r"^https?://", "", a.get("base_url", "")).split("/")[0] for a in apps)
    deep = [a.get("deep_link") for a in apps if a.get("deep_link")]

    api = json.loads((DATA / "03_sdk.json").read_text())
    paths = [p for p, _ in api["api_paths"]["top"]]

    def pick(pattern: str) -> list[str]:
        return [p for p in paths if re.search(pattern, p)]

    out = {
        "obfuscation": {
            "classes": classes,
            "packages": len(pkg_names),
            "obfuscated_packages": len(obf),
            "obfuscated_package_pct": round(100 * len(obf) / max(1, len(pkg_names)), 1),
            "classes_in_obfuscated_packages": obf_classes,
            "classes_in_obfuscated_pct": round(100 * obf_classes / max(1, classes), 1),
            "sample": sorted(obf)[:25],
            "toolchain_evidence": {
                "kotlin_modules": len(kotlin_modules),
                "source_file_attribute": "normalised to 'SourceFile' for 100% of classes (R8 without line/attribute retention)",
                "griver_assets": len(griver),
            },
        },
        "mini_apps": {
            "count": len(apps),
            "categories": cats.most_common(),
            "base_url_hosts": hosts.most_common(10),
            "deep_link_pattern": deep[0] if deep else None,
            "source": "assets/config/miniapp_config.json",
            "home_shortcuts": len(home.get("shortcut_bar", {}).get("app_profiles", [])),
        },
        "pin_envelope_surface": {
            "note": "Endpoint names are evidence of protocol *shape* only; no keys, PINs or payloads are recovered in this study.",
            "key_discovery": pick(r"public-key|/salt$|pre-check-update-pin"),
            "session_and_otp": pick(r"otp|logindevice|session"),
            "ekyc": pick(r"ekyc|liveness|face-authen"),
            "cashier": pick(r"cashier|topup-order|agreement"),
            "pci": pick(r"pci"),
        },
    }
    (DATA / "07_insights.json").write_text(json.dumps(out, indent=1, ensure_ascii=False), encoding="utf-8")
    o = out["obfuscation"]
    print(f"insights: classes {classes:,}, obfuscated pkgs {o['obfuscated_package_pct']}% "
          f"> {o['classes_in_obfuscated_pct']}% of classes | mini-apps {len(apps)} in {len(cats)} categories "
          f"| pin surface {len(out['pin_envelope_surface']['key_discovery'])} endpoints")


if __name__ == "__main__":
    main()
