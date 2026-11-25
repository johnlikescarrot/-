#!/usr/bin/env python3
"""Decompile classes from the extracted APK with androguard DAD and write sources to reports/decompiled/."""
from loguru import logger
logger.remove()

import os, sys, gc, traceback
from pathlib import Path
from androguard.misc import AnalyzeAPK

def main():
    apk = sys.argv[1] if len(sys.argv) > 1 else "vn.com.vng.zalopay.apk"
    outdir = Path("reports/decompiled")
    outdir.mkdir(parents=True, exist_ok=True)
    logf = open("reports/decompile_progress.log", "w", encoding="utf-8")

    def log(msg):
        logf.write(msg + "\n"); logf.flush()

    log(f"Starting AnalyzeAPK({apk})")
    a, d, dx = AnalyzeAPK(apk)
    log(f"Analyzed: {len(d)} dex files")
    total_ok, total_fail = 0, 0
    for i, dex in enumerate(d):
        names = dex.get_classes_names()
        ok, fail = 0, 0
        for idx, cname in enumerate(names):
            try:
                cls = dex.get_class(cname)
                src = dex.decompiler.get_source_class(cls)
                # sanitize file name
                fname = cname.replace("/", "_").replace("$", "_") + ".java"
                with open(outdir / fname, "w", encoding="utf-8", errors="replace") as f:
                    f.write(src if src else "")
                ok += 1
            except Exception as e:
                fail += 1
                if fail <= 5:
                    log(f"FAIL {cname}: {e}")
            if idx % 200 == 0:
                log(f"dex[{i}] class {idx}/{len(names)} ok={ok} fail={fail}")
        log(f"dex[{i}] done: {len(names)} classes, ok={ok} fail={fail}")
        total_ok += ok; total_fail += fail
        gc.collect()
    log(f"ALL DONE: total_ok={total_ok} total_fail={total_fail}")
    logf.close()

if __name__ == "__main__":
    try:
        main()
    except Exception:
        traceback.print_exc()
        raise
