#!/usr/bin/env python3
"""P2 deep-dive: native ELF, SDK fingerprints, crypto, configs, embedded DEX, ARSC."""
import hashlib, io, json, math, os, re, subprocess, zipfile
from collections import Counter
ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
RES = os.path.join(ROOT, "research")
NATIVE = os.path.join(ROOT, "work", "native")
ASSETS = os.path.join(ROOT, "work", "assets")

def run(cmd):
    try:
        return subprocess.run(cmd, capture_output=True, text=True, timeout=60).stdout
    except Exception as e:
        return f"ERR {e}"

def entropy(b):
    if not b: return 0.0
    c = Counter(b); n = len(b)
    return -sum((v/n)*math.log2(v/n) for v in c.values())

# ---- native ELF ----
native = {}
for f in sorted(os.listdir(NATIVE)):
    p = os.path.join(NATIVE, f)
    hdr = run(["readelf", "-h", p])
    dyn = run(["readelf", "-d", p])
    needed = re.findall(r"NEEDED.*\[(.+?)\]", dyn)
    # exported JNI-ish symbols count
    syms = run(["nm", "-D", "--defined-only", p])
    jni = [l for l in syms.splitlines() if "Java_" in l]
    # strings mining (top interesting)
    s = run(["strings", "-n", "6", p])
    slines = s.splitlines()
    urls = sorted(set(re.findall(r"https?://[^\s\"'<>\\]+", s)))[:30]
    interesting = [l for l in slines if re.search(r"(zalopay|alipay|vng|encrypt|decrypt|AES|RSA|SHA|HMAC|ssl|http|api|token|key|sign|verify|root|frida|xposed|magisk|debug|ptrace)", l, re.I)][:40]
    native[f] = {"size": os.path.getsize(p), "needed": needed,
                 "jni_exports": len(jni), "jni_sample": jni[:10],
                 "urls": urls, "interesting_strings": interesting[:25],
                 "total_strings": len(slines)}
    print(f"{f}: needed={needed} jni={len(jni)} strings={len(slines)}")
with open(os.path.join(RES, "native-deep.json"), "w") as fh:
    json.dump(native, fh, indent=1, ensure_ascii=False)

# ---- DEX strings mining ----
strings = open(os.path.join(RES, "dex-strings.txt"), encoding="utf-8").read().splitlines()
print(f"dex strings: {len(strings)}")
def grep(pat, limit=60):
    rx = re.compile(pat, re.I)
    return [s for s in strings if rx.search(s)][:limit]
sdk = {
  "firebase": grep(r"firebase|crashlytics|fcm|gms\.|googleapis|measurement"),
  "facebook": grep(r"facebook|fb\.com|audience_network|katana"),
  "alipay": grep(r"alipay|griver|appx|kylin|yuyan"),
  "appsflyer": grep(r"appsflyer|af-appx"),
  "visa": grep(r"visa|cbp|tap.?to.?pay|nfc"),
  "vnpay": grep(r"vnpay"),
  "trueid_ekyc": grep(r"trueid|ekyc|liveness|vneid|vnid"),
  "crypto": grep(r"^(AES|RSA|SHA|HMAC|PBKDF|BouncyCastle|Cipher|MessageDigest|KeyStore|EncryptedSharedPreferences|sqlcipher)"),
  "network": grep(r"okhttp|retrofit|volley|grpc|websocket|certificate.?pin|network.?security|cleartext"),
  "obfuscation": grep(r"proguard|obfuscat|dexguard|bangcle|jiagu|legu|mob\.|secur|anti.?debug|frida|xposed|magisk|root.?check|emulator"),
  "deeplink": grep(r"zalopay://|zalo://|alipays://|intent://|deep.?link|applink|assetlinks"),
  "biometric": grep(r"biometric|fingerprint|face.?id|pin.?code|otp|2fa"),
  "analytics": grep(r"analytics|tracking|telemetry|mixpanel|amplitude|segment|sentry|bugly"),
}
with open(os.path.join(RES, "sdk-fingerprints.json"), "w") as fh:
    json.dump(sdk, fh, indent=1, ensure_ascii=False)
for k, v in sdk.items():
    print(f"--- {k} ({len(v)}) ---")
    for s in v[:12]: print("   ", s[:160])

# first-party packages detail
pkgs = json.load(open(os.path.join(RES, "packages.json")))
fp = {k: v for k, v in pkgs.items() if k.startswith("vn/") or k.startswith("com/vng") or k.startswith("com/zing")}
print("first-party:", fp)
with open(os.path.join(RES, "first-party.json"), "w") as fh:
    json.dump(fp, fh, indent=1)

# ---- configs ----
for name in ["assets__config__zalopay_config.json", "assets__config__miniapp_config.json",
             "assets__taibmcf.json", "assets__vdfmoer.json", "assets__bundle.json",
             "assets__Griver__appx.json", "assets__client_tracking.json"]:
    p = os.path.join(ASSETS, name)
    if not os.path.exists(p): continue
    b = open(p, "rb").read()
    print(f"=== {name} ({len(b)} bytes, entropy {entropy(b):.2f}) ===")
    try:
        j = json.loads(b)
        if isinstance(j, dict):
            print("  keys:", list(j.keys())[:30])
            print("  ", json.dumps(j, ensure_ascii=False)[:800])
        elif isinstance(j, list):
            print(f"  list len {len(j)}; first: {json.dumps(j[0], ensure_ascii=False)[:500] if j else ''}")
    except Exception as e:
        print("  not json:", e, b[:200])

# binary assets entropy
for name in ["assets__fgcpkjvqe.dat", "assets__taibmcf.dat", "assets__bshield.dat"]:
    p = os.path.join(ASSETS, name)
    b = open(p, "rb").read()
    print(f"{name}: size={len(b)} entropy={entropy(b):.3f} head={b[:32].hex()} sha256={hashlib.sha256(b).hexdigest()[:16]}")

# android.1.zip listing
z = zipfile.ZipFile(os.path.join(ASSETS, "assets__android.1.zip"))
print("android.1.zip entries:", len(z.namelist()))
for n in z.namelist()[:40]: print("  ", n)
# audience_network.dex header
b = open(os.path.join(ASSETS, "assets__audience_network.dex"), "rb").read()
print("audience_network.dex magic:", b[:8], "size:", len(b))

# resources.arsc + res/xml + network security
bz = zipfile.ZipFile(os.path.join(ROOT, "work", "extracted", "xapk", "vn.com.vng.zalopay.apk"))
arsc = bz.read("resources.arsc")
print(f"resources.arsc: {len(arsc)} bytes")
# extract printable strings from arsc (utf16 + utf8)
txt16 = arsc.decode("utf-16-le", "ignore")
cands = [s for s in re.findall(r"[ -~]{6,}", txt16) if len(s) < 200]
print(f"arsc utf16 strings: {len(cands)}; sample:", cands[:20])
xmls = [n for n in bz.namelist() if n.startswith("res/xml") or "network" in n.lower() or n.endswith(".xml")]
print("xml-ish entries:", xmls[:40])
with open(os.path.join(RES, "arsc-strings.txt"), "w") as fh:
    fh.write("\n".join(cands))
