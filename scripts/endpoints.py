#!/usr/bin/env python3
"""Endpoint inventory with provenance: DEX string table and the React Native JS bundle."""
import json, os, re
ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
RES = os.path.join(ROOT, "research")

def host_of(u):
    m = re.match(r"^(?:https?|wss?)://([^/:?#\s\"'<>]+)", u)
    return m.group(1).lower() if m else None

def classify(h):
    if "%s" in h or h in {"*", "https", "dummy", "hostname"} or h.startswith("."):
        return "template/placeholder"
    if re.search(r"(^|\.)(localhost|127\.|10\.0\.2\.2)|appx-ng?$|^resource$|^shared$|^usr$", h): return "local/runtime-placeholder"
    if re.search(r"(stg|staging|sandbox|dev|uat|qa|test)", h): return "non-production"
    return "production"

dex = [s for s in open(os.path.join(RES, "dex-strings.txt"), encoding="utf-8", errors="replace").read().splitlines() if re.match(r"^[ -~]{4,}$", s)]
js = open(os.path.join(ROOT, "work/js/main.jsbundle"), encoding="utf-8", errors="replace").read()
worker = open(os.path.join(ROOT, "work/js/workerjs.js"), encoding="utf-8", errors="replace").read()

sources = {"dex": dex, "js:main.jsbundle": re.findall(r"https?://[^\s\"'`\\)<>,]+", js),
           "js:GriverBridge/workerjs.js": re.findall(r"https?://[^\s\"'`\\)<>,]+", worker)}
hosts = {}
for src, items in sources.items():
    for it in items:
        h = host_of(it)
        if not h: continue
        rec = hosts.setdefault(h, {"host": h, "class": classify(h), "seen_in": [], "sample_urls": []})
        if src not in rec["seen_in"]: rec["seen_in"].append(src)
        clean = re.sub(r"[\s\"'`\\)<>,]+$", "", it)
        if len(rec["sample_urls"]) < 3 and clean not in rec["sample_urls"]: rec["sample_urls"].append(clean[:160])
rows = sorted(hosts.values(), key=lambda r: (r["class"], r["host"]))
json.dump(rows, open(os.path.join(RES, "endpoints.json"), "w"), indent=1)
print("hosts:", len(rows))
for cls in ("production", "non-production", "local/runtime-placeholder"):
    sel = [r for r in rows if r["class"] == cls]
    print(f"\n== {cls} ({len(sel)}) ==")
    for r in sel: print(f"  {r['host']:<42} {','.join(r['seen_in'])}")
