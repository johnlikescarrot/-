#!/usr/bin/env python3
"""Deterministic figure generation (PIL) from research/*.json -> research/figs/*.png"""
import json, os
from PIL import Image, ImageDraw, ImageFont

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
RES = os.path.join(ROOT, "research")
FIGS = os.path.join(RES, "figs")
os.makedirs(FIGS, exist_ok=True)
W, H = 1600, 900
BG = (11, 15, 25)
FG = (232, 238, 246)
MUTED = (140, 156, 178)
ACC = (0, 220, 170)
ACC2 = (86, 156, 255)
ACC3 = (255, 138, 92)
FD = "/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf"
FDB = "/usr/share/fonts/truetype/dejavu/DejaVuSans-Bold.ttf"
F = lambda n=26: ImageFont.truetype(FD, n)
FB = lambda n=44: ImageFont.truetype(FDB, n)
FS = lambda n=20: ImageFont.truetype(FD, n)
FM = lambda n: ImageFont.truetype("/usr/share/fonts/truetype/dejavu/DejaVuSansMono.ttf", n)

def canvas(title, sub=""):
    im = Image.new("RGB", (W, H), BG); d = ImageDraw.Draw(im)
    d.text((60, 40), title, font=FB(44), fill=FG)
    if sub: d.text((62, 100), sub, font=FS(22), fill=MUTED)
    d.line([(60, 140), (W-60, 140)], fill=(40, 52, 72), width=2)
    return im, d

def save(im, name):
    p = os.path.join(FIGS, name); im.save(p); print("wrote", p)

def hbars(title, sub, rows, unit="", color=ACC, top=170, maxw=1100):
    im, d = canvas(title, sub)
    mx = max(v for _, v in rows) or 1
    y = top; bh = min(38, (H - top - 70) // max(len(rows), 1) - 10)
    for label, v in rows:
        d.text((60, y), label[:46], font=F(22), fill=FG)
        bw = int(maxw * v / mx)
        d.rounded_rectangle([640, y+4, 640+bw, y+bh], 6, fill=color)
        d.text((650 + bw, y + 5), f"{v:,}{unit}", font=FS(20), fill=MUTED)
        y += bh + 12
    save(im, title.split("|")[0].strip())

# fig01 size budget
sizes = [("base APK (dex+res+assets)", 79359087), ("config.armeabi_v7a (native)", 24828408),
         ("config.xhdpi (density res)", 1232563), ("config.vi (locale)", 86425), ("config.en (locale)", 74137)]
im, d = canvas("Artifact size budget", "Zalopay 11.17.0 XAPK — 105,580,620 bytes across 5 APKs")
tot = sum(v for _, v in sizes); y = 190
for label, v in sizes:
    w = int(1300 * v / tot)
    d.rounded_rectangle([60, y, 60 + max(w, 4), y + 62], 8, fill=ACC if "base" in label else ACC2 if "native" in label else ACC3)
    d.text((60 + max(w, 4) + 14, y + 8), f"{label}", font=F(22), fill=FG)
    d.text((60 + max(w, 4) + 14, y + 36), f"{v:,} B · {100*v/tot:.1f}%", font=FS(19), fill=MUTED)
    y += 92
d.text((60, H-80), "Observation: the ABI split is 23.5% of the payload; the base APK is dominated by 19 DEX files (110,677 classes).", font=FS(20), fill=MUTED)
save(im, "fig01-size-budget.png")

# fig02 classes per dex
inv = json.load(open(os.path.join(RES, "dex-inventory.json")))
rows = []
for k, v in inv.items():
    if isinstance(v, dict) and "classes" in v: rows.append((k, v["classes"]))
rows.sort(key=lambda r: -r[1])
hbars("fig02-dex-classes.png | Classes per DEX file", "19 DEX files · 110,677 classes · 351,248 methods", rows[:14], color=ACC2)

# fig03 top packages
pk = json.load(open(os.path.join(RES, "packages.json")))
if isinstance(pk, dict) and "top" in pk: pk = pk["top"]
rows = sorted(((k, v) for k, v in pk.items() if isinstance(v, int)), key=lambda r: -r[1])[:16]
hbars("fig03-top-packages.png | Top packages by class count", "First-party code (vn/com, vn/ai, vn/zalopay) is a minority of the artifact", rows, color=ACC)

# fig04 native sizes
nh = json.load(open(os.path.join(RES, "native-hardening.json")))
rows = sorted(((k, v["size"]) for k, v in nh.items()), key=lambda r: -r[1])[:16]
hbars("fig04-native-sizes.png | Native libraries (armeabi-v7a)", "27 ELF shared objects · all PIE, NX, RELRO, BIND_NOW", [(k, v//1024) for k, v in rows], unit=" KiB", color=ACC3)

# fig05 permission risk
man = json.load(open(os.path.join(RES, "android-manifest.json")))
perms = man.get("permissions") or []
cats = {"Identity & biometrics": ["USE_BIOMETRIC", "USE_FINGERPRINT", "CAMERA", "RECORD_AUDIO", "NFC"],
        "Contacts / calendar / storage": ["READ_CONTACTS", "WRITE_CONTACTS", "READ_CALENDAR", "WRITE_CALENDAR", "READ_EXTERNAL_STORAGE", "WRITE_EXTERNAL_STORAGE", "READ_MEDIA_AUDIO"],
        "Location": ["ACCESS_FINE_LOCATION", "ACCESS_COARSE_LOCATION"],
        "Device inventory / telephony": ["READ_PHONE_STATE", "READ_BASIC_PHONE_STATE", "QUERY_ALL_PACKAGES", "ACCESS_WIFI_STATE", "CHANGE_WIFI_STATE", "REORDER_TASKS", "DETECT_SCREEN_CAPTURE"],
        "Advertising / billing": ["AD_ID", "ACCESS_ADSERVICES_AD_ID", "ACCESS_ADSERVICES_ATTRIBUTION", "ACCESS_ADSERVICES_CUSTOM_AUDIENCE", "ACCESS_ADSERVICES_TOPICS", "BILLING"],
        "Background execution": ["FOREGROUND_SERVICE", "RECEIVE_BOOT_COMPLETED", "WAKE_LOCK", "POST_NOTIFICATIONS"]}
counts = []
for c, keys in cats.items():
    counts.append((c, sum(1 for p in perms if any(k in p for k in keys))))
hbars("fig05-permission-risk.png | Declared permissions by purpose", f"{len(perms)} permissions in the merged manifest", counts, color=ACC2)

# fig06 exported components
comp = json.load(open(os.path.join(RES, "components.json")))
rows = []
for t, n in comp.get("counts", {}).items():
    exported = sum(1 for c in comp.get("exported", []) if c.get("type") == t)
    rows.append((f"{t} (exported {exported}/{n})", exported))
rows.sort(key=lambda r: -r[1])
hbars("fig06-exported-components.png | Exported components (attack surface)", "android:exported=true in the base manifest", rows, color=ACC3)

# fig07 layer stack
im, d = canvas("Runtime layers recovered from a single artifact", "Kotlin/Java · React Native JS bundle · Alipay-style mini-programs · 27 native ELFs")
layers = [("Kotlin + Java (19 DEX, 110,677 classes)", ACC), ("React Native JS bundle (3.6 MB, 21 native modules)", ACC2),
          ("Mini-program runtime (Griver 2.70.0 / AppContainer 10.5.10)", ACC3), ("Native ELF (face, OCR, barcode, SQLCipher, Rive)", (190, 140, 255))]
y = 200
for i, (label, col) in enumerate(layers):
    x0 = 80 + i * 40
    d.rounded_rectangle([x0, y, W - 120 - i * 20, y + 120], 14, outline=col, width=3)
    d.text((x0 + 26, y + 26), f"L{i}", font=FM(34), fill=col)
    d.text((x0 + 100, y + 40), label, font=F(24), fill=FG)
    y += 150
d.text((80, H - 60), "Evidence: scripts/triage.py, scripts/deep.py, scripts/hardening.py — see research/*.json", font=FS(20), fill=MUTED)
save(im, "fig07-layer-stack.png")
