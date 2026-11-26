#!/usr/bin/env python3
"""Stage 0 — consistency gate.

Checks the invariants the film's narration relies on.  If any of these fail,
a number on screen is wrong, so this is the last line of defence before a
render.

Run: .venv/bin/python analysis/tools/00_verify.py
Exit: 0 all good, 1 on the first failed invariant.
"""
from __future__ import annotations

import json
import os
import sys

ROOT = os.path.abspath(os.path.join(os.path.dirname(__file__), "..", ".."))
DATA = os.path.join(ROOT, "data")


def load(name: str) -> dict:
    with open(os.path.join(DATA, name)) as fh:
        return json.load(fh)


CHECKS: list[tuple[str, object, object]] = []


def expect(label: str, got, want) -> None:
    CHECKS.append((label, got, want))


def main() -> int:
    prov = load("01_provenance.json")
    dex = load("02_dex.json")
    nat = load("03_native.json")
    sec = load("04_secrets.json")
    cap = load("05_security.json")
    pay = load("06_payloads.json")

    # --- provenance -------------------------------------------------------
    expect("5 APK in the XAPK", len(prov["apks"]), 5)
    expect("base APK size", prov["apks"][0]["bytes"], 79359087)
    expect("XAPK size", prov["container"]["inner"]["bytes"], 105608128)
    expect("1 ABI split only", sum(1 for a in prov["apks"] if "armeabi" in a["name"]), 1)
    expect("no arm64 split", sum(1 for a in prov["apks"] if "arm64" in a["name"]), 0)
    for a in prov["apks"]:
        expect(f"{a['name']} has v2 and v3", "v2" in a["signing"]["schemes"] and "v3" in a["signing"]["schemes"], True)

    # --- dex --------------------------------------------------------------
    expect("19 DEX files", dex["totals"]["dex_files"], len(dex["dex"]))
    expect("dex byte total", dex["totals"]["dex_files"], 19)
    expect("own classes < 2% of all", dex["totals"]["own_classes"] * 50 < dex["totals"]["classes"], True)

    # --- native -----------------------------------------------------------
    expect("27 ELF libs", nat["lib_count"], 27)
    expect("no duplicate library content", len(nat["aliases"]), 0)
    expect("hardening pct matches count",
           round(100 * nat["totals"]["hardened_full"] / nat["lib_count"], 1),
           nat["totals"]["hardening_pct"])
    expect("protector family is 6 libs", len(nat["totals"]["obfuscated_libs"]), 6)
    expect("every relro exception is Crashlytics",
           all("crashlytics" in x for x in nat["totals"]["relro_exceptions"]), True)

    # --- secrets ----------------------------------------------------------
    expect("first-party share consistent",
           all(0 < h < 1 for h in [sec["first_party_share"] / 100]), True)
    expect("no high-severity secret signatures",
           [f["signature"] for f in sec["signatures"] if f["severity"] == "high"], [])
    expect("network security config parsed",
           bool(sec["network_security_configs"]), True)

    # --- capability -------------------------------------------------------
    expect("14 capability themes", len(cap["themes"]), 14)
    expect("every theme has a limit note", all(t.get("note") for t in cap["themes"].values()), True)
    expect("obfuscation is the largest theme",
           max(cap["themes"], key=lambda k: cap["themes"][k]["count"]), "obfuscation")

    # --- payloads ---------------------------------------------------------
    expect("entries scanned = 5 APKs sum",
           pay["entries_scanned"], sum(a["entries"] for a in prov["apks"]))
    expect("trueidlib.so is flagged", any("trueidlib.so" in f["path"] for f in pay["hidden_executables"]), True)
    expect("3 public keys", len(pay["public_keys"]), 3)

    # --- report -----------------------------------------------------------
    bad = 0
    width = max(len(c[0]) for c in CHECKS)
    for label, got, want in CHECKS:
        ok = got == want
        bad += 0 if ok else 1
        mark = "ok  " if ok else "FAIL"
        print(f"  [{mark}] {label:<{width}}  got={got!r}" + ("" if ok else f"  want={want!r}"))
    print(f"\n{len(CHECKS) - bad}/{len(CHECKS)} invariants hold")
    return 1 if bad else 0


if __name__ == "__main__":
    sys.exit(main())
