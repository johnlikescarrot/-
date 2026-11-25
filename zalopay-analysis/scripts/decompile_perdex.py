#!/usr/bin/env python3
"""Decompile classes from extracted dex files with androguard DAD, one dex at a time.

Usage: decompile_perdex.py [start_index] [max_dex_files] [include_all]
"""
from loguru import logger
logger.remove()

import gc, sys, resource, traceback
from pathlib import Path
from androguard.core.dex import DEX
from androguard.core.analysis.analysis import Analysis
from androguard.decompiler.decompiler import DecompilerDAD

def rss_mb():
    try:
        return resource.getrusage(resource.RUSAGE_SELF).ru_maxrss / 1024
    except Exception:
        return -1

def main():
    args = [a for a in sys.argv[1:]]
    dex_files = []
    start_i, max_files = 0, 1
    include_all = False
    if args:
        first = args[0]
        if first in ("all", "ALL"):
            include_all = True
            rest = args[1:]
        else:
            rest = args
        if rest and all(x.endswith(".dex") for x in rest):
            dex_files = [Path(x) for x in rest]
        elif rest:
            start_i = int(rest[0]); max_files = int(rest[1]) if len(rest) > 1 else 1
    if not dex_files:
        dex_files = sorted(Path("extracted").glob("classes*.dex"), key=lambda p: (p.name != "classes.dex", p.name))
        dex_files = dex_files[start_i:start_i + max_files]

    outdir = Path("reports/decompiled")
    outdir.mkdir(parents=True, exist_ok=True)
    logf = open("reports/decompile_progress.log", "a", encoding="utf-8")

    def log(msg):
        logf.write(msg + "\n"); logf.flush()

    files = dex_files
    log(f"Per-dex decompile start: all={include_all} rss={rss_mb():.0f}MB files={[f.name for f in files]}")

    for di, dpath in enumerate(files):
        log(f"=== Processing {dpath.name} rss={rss_mb():.0f}MB")
        try:
            d = DEX(dpath.read_bytes())
        except Exception as e:
            log(f"DEX parse error {dpath.name}: {e}")
            continue
        names = sorted(d.get_classes_names())
        names_vn = [n for n in names if n.startswith("Lvn")]
        names_other = [n for n in names if not n.startswith("Lvn")]
        log(f"  dex classes: total={len(names)} vn={len(names_vn)} other={len(names_other)} rss={rss_mb():.0f}MB")

        groups = [("vn", names_vn)]
        if include_all:
            groups.append(("other", names_other))
        for tag, clslist in groups:
            if not clslist:
                continue
            dx = Analysis()
            d.set_decompiler(DecompilerDAD(d, dx))
            try:
                dx.add(d)
            except Exception as e:
                log(f"  Analysis.add error for {dpath.name}[{tag}]: {e}")
                continue
            ok = fail = 0
            for idx, cname in enumerate(clslist):
                try:
                    cls = d.get_class(cname)
                    src = d.CM.decompiler_ob.get_source_class(cls)
                    fname = cname.replace("/", "_").replace("$", "_") + ".java"
                    with open(outdir / fname, "w", encoding="utf-8", errors="replace") as f:
                        f.write(src or "")
                    ok += 1
                except Exception as e:
                    fail += 1
                    if fail <= 3:
                        log(f"    FAIL {cname}: {e}")
                if idx % 500 == 0:
                    log(f"    [{tag}] {idx}/{len(clslist)} ok={ok} fail={fail} rss={rss_mb():.0f}MB")
            log(f"  {dpath.name}[{tag}] done ok={ok} fail={fail} rss={rss_mb():.0f}MB")
            gc.collect()
        log(f"=== Finished {dpath.name} rss={rss_mb():.0f}MB")
    log("ALL DONE")
    logf.close()

if __name__ == "__main__":
    try:
        main()
    except Exception:
        traceback.print_exc()
        raise
