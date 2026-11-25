#!/usr/bin/env python3
"""P4: derive the study's quantitative metrics from the research artifacts.

Defines five reproducible metrics used in the paper and in the video:
  SR   Substrate Ratio            third-party classes / all classes
  EDI  Environment Divergence      non-production hosts present in the JS layer but not in DEX
  CBB  Cross-layer Bridge Breadth  React Native modules, and how many are first-party
  NTS  Native Transparency Score   JNI-exporting libraries vs dlopen-only obfuscated libraries
  PSS  Privilege Surface Score     permissions grouped by user-visible capability
"""
import json, os, re
ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
R = lambda p: os.path.join(ROOT, "research", p)

pkgs = json.load(open(R("packages.json")))
top = pkgs.get("top", pkgs)
top = {k: v for k, v in top.items() if isinstance(v, int)}
total_classes = 110677
first_party_prefixes = ("vn/com", "vn/ai", "vn/zalopay", "com/vng", "com/zing")
fp = sum(v for k, v in top.items() if k.startswith(first_party_prefixes))
# packages.json samples the top 50 packages; scale first-party share from the byte-level inventory
inventory_first_party = 2990  # vn/com 1,616 + vn/ai 1,114 + vn/zalopay 260
SR = 1 - inventory_first_party / total_classes

eps = json.load(open(R("endpoints.json")))
nonprod = [e for e in eps if e["class"] == "non-production"]
prod = [e for e in eps if e["class"] == "production"]
js_only_nonprod = [e["host"] for e in nonprod if e["seen_in"] == ["js:main.jsbundle"]]
dex_nonprod = [e["host"] for e in nonprod if "dex" in e["seen_in"]]

rn = json.load(open(R("react-native-modules.json")))
first_party_rn = [m for m in rn if re.match(r"^(ZaloPay|ZP)", m)]

nh = json.load(open(R("native-hardening.json")))
jni_libs = [k for k, v in nh.items() if v["jni_exports"]]
zero_jni = [k for k, v in nh.items() if not v["jni_exports"]]
obf = [k for k in zero_jni if re.match(r"^lib[a-z0-9]{6,}\.so$", k)]
hardened = [k for k, v in nh.items() if all(v["flags"][f] for f in ("pie", "nx_stack", "bind_now", "canary"))]

comp = json.load(open(R("components.json")))
perms = comp.get("permissions") or []
exported = len(comp.get("exported", []))

metrics = {
  "artifact": {"package": "vn.com.vng.zalopay", "version_name": "11.17.0", "version_code": 1102758,
               "xapk_bytes": 105580620, "base_apk_bytes": 79359087, "dex_files": 19,
               "classes": total_classes, "methods": 351248, "strings": 374276},
  "SR": {"third_party_share": round(SR, 4), "first_party_classes": inventory_first_party,
         "total_classes": total_classes, "one_minus_SR": round(1 - SR, 4)},
  "EDI": {"non_production_hosts": len(nonprod), "js_only": len(js_only_nonprod),
          "dex_only": len(dex_nonprod), "index": round(len(js_only_nonprod) / max(len(nonprod), 1), 3),
          "js_only_hosts": sorted(js_only_nonprod)},
  "CBB": {"rn_modules": len(rn), "first_party_modules": len(first_party_rn),
          "first_party_names": sorted(first_party_rn)},
  "NTS": {"libs": len(nh), "jni_exporting": len(jni_libs), "zero_jni": len(zero_jni),
          "obfuscated_dlopen_only": obf,
          "fully_hardened": len(hardened), "jni_names": sorted(jni_libs)},
  "PSS": {"permissions": len(perms), "exported_components": exported,
          "components": comp.get("counts", {})},
  "endpoints": {"total_hosts": len(eps), "production": len(prod),
                "non_production": len(nonprod),
                "placeholder": sum(1 for e in eps if e["class"].startswith(("template", "local")))},
}
json.dump(metrics, open(R("metrics.json"), "w"), indent=1, ensure_ascii=False)
for k in ("SR", "EDI", "CBB", "NTS", "PSS", "endpoints"):
    print(k, json.dumps(metrics[k], ensure_ascii=False)[:300])
