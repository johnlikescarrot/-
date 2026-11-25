#!/usr/bin/env python3
"""Run every stage of the analysis pipeline in dependency order."""
from __future__ import annotations

import importlib
import sys
import time

STAGES = [
    "01_inventory", "02_manifest", "03_dex", "04_sdk", "05_secrets",
    "06_native", "07_resources", "08_graph", "09_findings", "10_report",
]

def main(argv: list[str]) -> int:
    wanted = [a for a in argv[1:] if not a.startswith("-")] or STAGES
    for name in wanted:
        mod = importlib.import_module(name)
        t0 = time.time()
        mod.main()
        print(f"--- {name} finished in {time.time() - t0:.1f}s", flush=True)
    return 0

if __name__ == "__main__":
    sys.path.insert(0, __file__.rsplit("/", 1)[0])
    raise SystemExit(main(sys.argv))
