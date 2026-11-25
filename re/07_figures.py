#!/usr/bin/env python3
"""
07_figures.py — publication figures (300 dpi PNG + the palette shared with the video).

F1  bundle anatomy: category x uncompressed bytes (log axis), annotated
F2  code ownership: 16 owner buckets, sorted, with the first-party slice called out
F3  DEX panel: per-file bytes vs classes, highlighting audience_network.dex
F4  native capability matrix: 27 libraries x capability channels
F5  subsystem graph: measured DEX classes vs measured native MB, bubble = JNI
F6  risk strip: 12 findings by severity

A single palette constant is exported to video/src/palette.ts so the film and the
paper look like one artefact.
"""
from __future__ import annotations

import json
import os

import matplotlib

matplotlib.use("Agg")
import matplotlib.pyplot as plt
import numpy as np
from matplotlib.patches import Rectangle

DATA, OUT = "data", "paper/figures"
os.makedirs(OUT, exist_ok=True)

INK = "#0b1220"
PAPER = "#f7f5f0"
ACCENT = "#2ee6a8"   # ZaloPay-ish green
ACCENT2 = "#ff5c7a"
ACCENT3 = "#4ea8ff"
MUTED = "#8a93a5"
SEV = {"high": "#ff4d5e", "medium": "#ffa64d", "low": "#ffd84d", "info": "#7cc6ff"}

plt.rcParams.update({
    "figure.facecolor": PAPER,
    "axes.facecolor": PAPER,
    "savefig.facecolor": PAPER,
    "text.color": INK,
    "axes.labelcolor": INK,
    "xtick.color": INK,
    "ytick.color": INK,
    "axes.edgecolor": MUTED,
    "font.family": "DejaVu Sans",
    "font.size": 9,
    "axes.titlesize": 12,
    "axes.titleweight": "bold",
    "figure.dpi": 300,
})


def f1_anatomy(census: dict) -> str:
    cats = {k: v for k, v in census["categories"].items() if v["files"] > 0}
    rows = sorted(cats.items(), key=lambda kv: -kv[1]["bytes"])[:14]
    labels = [k.replace(":", " · ").replace("asset", "asset").replace("res", "res") for k, _ in rows]
    mb = [v["bytes"] / 1e6 for _, v in rows]
    files = [v["files"] for _, v in rows]
    fig, ax = plt.subplots(figsize=(9, 4.6))
    y = np.arange(len(rows))
    colors = [ACCENT if k.startswith(("code", "native")) else ACCENT3 if k.startswith("asset")
              else ACCENT2 if k.startswith("res") else MUTED for k, _ in rows]
    ax.barh(y, mb, color=colors, height=0.68)
    ax.set_yticks(y, labels, fontsize=8)
    ax.invert_yaxis()
    ax.set_xscale("log")
    ax.set_xlabel("uncompressed size (MB, log scale)")
    ax.set_title("Fig. 1 — Bundle anatomy: 6,466 entries, 195.4 MB uncompressed")
    for i, (m, f) in enumerate(zip(mb, files)):
        ax.text(m * 1.12, i, f"{m:,.1f} MB · {f:,} files", va="center", fontsize=7.5, color=MUTED)
    ax.set_xlim(0.05, max(mb) * 12)
    ax.grid(axis="x", color="#d9d5cc", lw=0.5, ls=":")
    ax.set_axisbelow(True)
    for s in ("top", "right", "left"):
        ax.spines[s].set_visible(False)
    fig.text(0.01, 0.005, "code + native = 65 % of the bundle; resources only 13 %",
             fontsize=7.5, color=MUTED)
    p = f"{OUT}/f1_bundle_anatomy.png"
    fig.tight_layout()
    fig.savefig(p)
    plt.close(fig)
    return p


def f2_ownership(dex: dict) -> str:
    owners = [(o["owner"], o["classes"]) for o in dex["owners"] if o["classes"] > 100][:13]
    other = dex["totals"]["classes"] - sum(c for _, c in owners)
    if other > 0:
        owners.append(("all other (<100 classes each)", other))
    labels = [o for o, _ in owners]
    vals = [c for _, c in owners]
    fig, ax = plt.subplots(figsize=(9, 4.4))
    y = np.arange(len(owners))
    colors = []
    for o in labels:
        if "ZaloPay" in o:
            colors.append(ACCENT2)
        elif "R8" in o:
            colors.append("#5b6478")
        elif "Bouncy" in o:
            colors.append("#b98cff")
        else:
            colors.append(ACCENT3)
    ax.barh(y, vals, color=colors, height=0.7)
    ax.set_yticks(y, labels, fontsize=8)
    ax.invert_yaxis()
    ax.set_xlabel("classes")
    ax.set_title("Fig. 2 — Who wrote this app: 63,827 classes by owner")
    total = dex["totals"]["classes"]
    for i, v in enumerate(vals):
        ax.text(v + total * 0.006, i, f"{v:,}  ({100*v/total:.2f} %)", va="center", fontsize=7.5, color=MUTED)
    ax.set_xlim(0, max(vals) * 1.22)
    ax.grid(axis="x", color="#d9d5cc", lw=0.5, ls=":")
    ax.set_axisbelow(True)
    for s in ("top", "right", "left"):
        ax.spines[s].set_visible(False)
    fig.text(0.01, 0.01, "first-party code still under vn/com/vng = 1.65 % of the app "
                         "(lower bound: R8 relocated the rest)",
             fontsize=7.5, color=ACCENT2)
    p = f"{OUT}/f2_code_ownership.png"
    fig.tight_layout()
    fig.savefig(p)
    plt.close(fig)
    return p


def f3_dex(dex: dict) -> str:
    files = sorted(dex["per_dex"], key=lambda d: -d["size"])
    names = [f["file"] for f in files]
    mb = [f["size"] / 1e6 for f in files]
    cls = [f["classes"] for f in files]
    fig, (a1, a2) = plt.subplots(1, 2, figsize=(10, 4.2), gridspec_kw={"width_ratios": [1.05, 1]})
    x = np.arange(len(files))
    cols = [ACCENT2 if n.startswith("audience") else ACCENT for n in names]
    a1.bar(x, mb, color=cols)
    a1.set_xticks(x, names, rotation=70, ha="right", fontsize=6.5)
    a1.set_ylabel("MB")
    a1.set_title("bytes per DEX", fontsize=9)
    a2.bar(x, cls, color=cols)
    a2.set_xticks(x, names, rotation=70, ha="right", fontsize=6.5)
    a2.set_ylabel("classes")
    a2.set_title("classes per DEX", fontsize=9)
    for a in (a1, a2):
        a.grid(axis="y", color="#d9d5cc", lw=0.5, ls=":")
        a.set_axisbelow(True)
        for s in ("top", "right"):
            a.spines[s].set_visible(False)
    fig.suptitle("Fig. 3 — 20 DEX files, 69.7 MB, 63,827 classes (assets/audience_network.dex in red)", fontsize=11,
                 fontweight="bold")
    fig.text(0.01, 0.01, "audience_network.dex is 4.9 MB / 3,156 classes and is invisible to a "
                         "glob('classes*.dex') — the single most likely sampling error",
             fontsize=7.2, color=ACCENT2)
    p = f"{OUT}/f3_dex_panel.png"
    fig.tight_layout(rect=(0, 0.04, 1, 0.95))
    fig.savefig(p)
    plt.close(fig)
    return p


CAPS = ["openssl", "sqlite", "sqlcipher", "protobuf", "ncnn", "opencv", "liveness",
        "face", "frida_probe", "xposed_probe", "ptrace_probe", "root_probe",
        "emulator_probe", "integrity_probe", "secguard", "rive", "mlkit", "crashlytics"]


def f4_native(native: dict) -> str:
    libs = sorted(native["libs"], key=lambda l: -l["size"])
    grid = np.zeros((len(libs), len(CAPS)))
    for i, lib in enumerate(libs):
        caps = lib.get("capabilities", {})
        for j, c in enumerate(CAPS):
            grid[i, j] = 1 if c in caps else 0
    fig, ax = plt.subplots(figsize=(10.4, 8.4))
    ax.imshow(grid, cmap=matplotlib.colors.ListedColormap(["#eeeae2", ACCENT]),
              aspect="auto", vmin=0, vmax=1)
    ax.set_xticks(range(len(CAPS)), CAPS, rotation=55, ha="right", fontsize=7.5)
    ax.set_yticks(range(len(libs)),
                  [f"{l['lib'].replace('lib','').replace('.so','')}  ({l['size']/1e6:.2f} MB, JNI {l['jni_count']})"
                   for l in libs], fontsize=6.6)
    ax.set_title("Fig. 4 — Native capability matrix: 27 ARM32 libraries, 57.5 MB, 284 JNI exports", fontsize=10.5)
    for i in range(len(libs) + 1):
        ax.axhline(i - 0.5, color=PAPER, lw=1.2)
    for j in range(len(CAPS) + 1):
        ax.axvline(j - 0.5, color=PAPER, lw=1.2)
    ax.tick_params(length=0)
    for s in ax.spines.values():
        s.set_visible(False)
    fig.text(0.01, 0.005, "evidence channel: rodata strings + dynamic symbols; "
                          "libtrueidlib.so (80 KB) carries 7 of 18 capability channels",
             fontsize=7.2, color=MUTED)
    p = f"{OUT}/f4_native_matrix.png"
    fig.tight_layout(rect=(0.0, 0.02, 0.985, 1.0))
    fig.savefig(p)
    plt.close(fig)
    return p


def f5_subsystems(corr: dict) -> str:
    """Paired bars, not a scatter: 5 of the 8 subsystems sit at ~0 MB native,
    which makes any 2-D scatter unreadable. Two aligned panels keep the point
    ('class count is not risk') visible without label collisions."""
    subs = corr["subsystems"]
    order = sorted(subs, key=lambda n: -subs[n]["measured_dex_classes"])
    cls = [subs[n]["measured_dex_classes"] for n in order]
    mb = [subs[n]["measured_native_bytes"] / 1e6 for n in order]
    jni = [subs[n]["measured_native_jni"] for n in order]
    fig, (a1, a2) = plt.subplots(1, 2, figsize=(10.4, 4.6), sharey=True,
                                 gridspec_kw={"width_ratios": [1, 1]})
    y = np.arange(len(order))
    cols = []
    for n in order:
        if n.startswith("NFC"):
            cols.append(ACCENT2)
        elif n.startswith(("eKYC", "Crypt")):
            cols.append(ACCENT3)
        else:
            cols.append(ACCENT)
    a1.barh(y, cls, color=cols, height=0.66)
    a2.barh(y, mb, color=cols, height=0.66)
    labels = [f"{n}\n{jni[i]} JNI export(s)" if jni[i] else n for i, n in enumerate(order)]
    a1.set_yticks(y, labels, fontsize=8)
    a1.invert_yaxis()
    a1.set_xlabel("measured Java/Kotlin classes")
    a2.set_xlabel("measured native bytes (MB)")
    a1.set_title("Java/Kotlin namespace weight", fontsize=9.5)
    a2.set_title("native weight", fontsize=9.5)
    for i, v in enumerate(cls):
        a1.text(v + max(cls) * 0.015, i, f"{v:,}", va="center", fontsize=7.5, color=MUTED)
    for i, v in enumerate(mb):
        a2.text(v + max(mb) * 0.02, i, f"{v:.2f} MB", va="center", fontsize=7.5, color=MUTED)
    a1.set_xlim(0, max(cls) * 1.2)
    a2.set_xlim(0, max(mb) * 1.28)
    for a in (a1, a2):
        a.grid(axis="x", color="#d9d5cc", lw=0.5, ls=":")
        a.set_axisbelow(True)
        for s_ in ("top", "right", "left"):
            a.spines[s_].set_visible(False)
    fig.suptitle("Fig. 5 — Eight subsystems across four evidence layers", fontsize=12, fontweight="bold")
    fig.text(0.01, 0.02, "NFC card emulation (red) owns 11 classes and ~0 MB of native code — "
                         "and produces the study's only high-severity finding. "
                         "eKYC's work sits below the language boundary.",
             fontsize=7.4, color=ACCENT2)
    p = f"{OUT}/f5_subsystems.png"
    fig.tight_layout(rect=(0, 0.05, 1, 0.94))
    fig.savefig(p)
    plt.close(fig)
    return p


def f6_risk(risk: dict) -> str:
    r = risk["risks"]
    fig, ax = plt.subplots(figsize=(9.4, 3.6))
    for i, item in enumerate(r):
        c = SEV[item["severity"]]
        ax.add_patch(Rectangle((0, i - 0.4), 1, 0.8, color=c, alpha=0.85))
        ax.text(1.06, i, f"{item['id']}  [{item['severity']}]  {item['title']}", va="center", fontsize=8.2)
    ax.set_xlim(0, 9)
    ax.set_ylim(-0.7, len(r) - 0.3)
    ax.invert_yaxis()
    ax.axis("off")
    counts = risk.get("by_severity", {})
    ax.set_title("Fig. 6 — Risk register: 12 findings "
                 f"({counts.get('high',0)} high · {counts.get('medium',0)} medium · "
                 f"{counts.get('low',0)} low · {counts.get('info',0)} info)", loc="left", pad=18)
    fig.text(0.01, 0.008, "every entry carries an evidence pointer and the experiment that would "
                          "confirm or refute it", fontsize=7.2, color=MUTED)
    p = f"{OUT}/f6_risk_strip.png"
    fig.tight_layout(rect=(0, 0.03, 1, 0.97))
    fig.savefig(p)
    plt.close(fig)
    return p


def write_palette() -> None:
    ts = f"""// AUTO-GENERATED by re/07_figures.py — shared palette for paper + video.
export const INK = "{INK}";
export const PAPER = "{PAPER}";
export const ACCENT = "{ACCENT}";
export const ACCENT2 = "{ACCENT2}";
export const ACCENT3 = "{ACCENT3}";
export const MUTED = "{MUTED}";
export const SEV = {{ high: "{SEV['high']}", medium: "{SEV['medium']}", low: "{SEV['low']}", info: "{SEV['info']}" }};
"""
    os.makedirs("video/src", exist_ok=True)
    with open("video/src/palette.ts", "w") as fh:
        fh.write(ts)


def main() -> None:
    census = json.load(open(f"{DATA}/census.json"))
    dex = json.load(open(f"{DATA}/dex.json"))
    native = json.load(open(f"{DATA}/native.json"))
    corr = json.load(open(f"{DATA}/correlation.json"))
    risk = json.load(open(f"{DATA}/risk.json"))
    outs = [f1_anatomy(census), f2_ownership(dex), f3_dex(dex),
            f4_native(native), f5_subsystems(corr), f6_risk(risk)]
    write_palette()
    for o in outs:
        print(f"[figures] {o} ({os.path.getsize(o)/1024:.0f} KB)")
    print("[figures] palette -> video/src/palette.ts")


if __name__ == "__main__":
    main()
