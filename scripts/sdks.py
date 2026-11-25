#!/usr/bin/env python3
"""Third-party SDK census: vendor detection with evidence counts from the DEX string table
and the React Native bundle; versions only where the artifact states them explicitly."""
import json, os, re
ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
RES = os.path.join(ROOT, "research")
R = lambda p: os.path.join(RES, p)

strs = open(R("dex-strings.txt"), encoding="utf-8", errors="replace").read().splitlines()
strs = [s for s in strs if re.match(r"^[ -~]{4,}$", s)]
js = open(os.path.join(ROOT, "work/js/main.jsbundle"), encoding="utf-8", errors="replace").read()
jsl = js.splitlines()

VENDORS = {
 "Google Firebase / Play services": r"firebase|com/google/android/gms|crashlytics",
 "Google ML Kit (vision/text)": r"mlkit|com/google/mlkit|barhopper",
 "Alipay / Ant Griver mini-programs": r"alipay|griver|appx|Ariver|marmot",
 "Facebook Graph / Audience Network": r"com/facebook|audience_network|fb\.com",
 "AppsFlyer attribution": r"appsflyer",
 "Visa Click-to-Pay / CBP (NFC)": r"com/visa/cbp|com\.visa|dpe\.consumerapi\.digital\.visa",
 "VNPAY": r"com/vnpay|vnpay",
 "TrueID eKYC (VNG/TrueID)": r"vn/ai/trueid|vn/ai/faceauth",
 "FPT.AI eKYC": r"com/fpt/fci|api\.fpt\.ai",
 "Nimbus JOSE+JWT": r"com/nimbusds",
 "Bouncy Castle crypto": r"org/bouncycastle",
 "JMrtd eMRTD (passport/NFC ID)": r"org/jmrtd",
 "SQLCipher": r"sqlcipher|net/sqlcipher|SQLiteDatabaseHook",
 "Rive animation": r"app/rive|librive-android",
 "Glide image pipeline": r"com/bumptech/glide",
 "OkHttp / Retrofit": r"okhttp3|retrofit2",
 "jsoup HTML parser": r"org/jsoup",
 "Sentry crash reporting": r"io/sentry|sentry\.io",
 "Adjust attribution": r"com/adjust/sdk",
 "TikTok / ByteDance SDK": r"com/tiktok|bytedance|analytics\.us\.tiktok",
 "Media3 / ExoPlayer": r"androidx/media3",
 "CameraX": r"androidx/camera",
 "Compose UI": r"androidx/compose",
 "Glance app widgets": r"androidx/glance",
 "React Native": r"com/facebook/react|react-native",
 "NC CNN (picoDet / FastestDet)": r"ncnn|picodet|FastestDet",
 "Zalo SDK": r"com/zing/zalo",
 "EMVCo QR (zlp-ofp)": r"zlp-ofp|emvco|qrcode_emv",
 "SafetyNet / Play Integrity": r"safetynet|play.*integrity|AttestationResult",
}
out = {}
for name, pat in VENDORS.items():
    rx = re.compile(pat, re.I)
    hits = [s for s in strs if rx.search(s)]
    jshits = [l for l in jsl if rx.search(l)]
    versions = set()
    for s in hits:
        for m in re.finditer(r"(?:\b(?:version|Version|ver)\b[\"':= ]{0,3})(\d+\.\d+\.\d+(?:[-.][A-Za-z0-9]+)?)", s):
            versions.add(m.group(1))
        for m in re.finditer(r"/([A-Za-z][A-Za-z0-9+.\-]*)/(\d+\.\d+\.\d+)", s):
            versions.add(f"{m.group(1)}/{m.group(2)}")
    out[name] = {"dex_string_hits": len(hits), "js_line_hits": len(jshits),
                 "stated_versions": sorted(versions)[:8],
                 "evidence": [h[:160] for h in hits[:6]]}
    print(f"{name:<40} dex={len(hits):>6} js={len(jshits):>5} versions={sorted(versions)[:3]}")
json.dump(out, open(R("sdk-inventory.json"), "w"), indent=1, ensure_ascii=False)

# react-native bridge modules discovered in the bundle
mods = {}
for m in re.finditer(r"NativeModules[.,]([A-Za-z0-9_]+)", js):
    mods.setdefault(m.group(1), 0)
    mods[m.group(1)] += 1
for m in re.finditer(r"TurboModuleRegistry\.get(?:Enforcing)?\(['\"]([A-Za-z0-9_]+)", js):
    mods[m.group(1)] = mods.get(m.group(1), 0) + 1
json.dump(mods, open(R("react-native-modules.json"), "w"), indent=1, sort_keys=True)
print("RN modules:", len(mods), sorted(mods)[:40])
