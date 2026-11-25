#!/usr/bin/env python3
"""Publication-grade figures (dark, 16:9) reused by the report and the film."""
from __future__ import annotations
import json, os
import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt
from matplotlib.patches import Rectangle
import matplotlib.patheffects as pe

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
D = os.path.join(ROOT, "data")
FIG = os.path.join(D, "figures")
os.makedirs(FIG, exist_ok=True)

BG, FG, GRID = "#080B10", "#E8EEF5", "#1B2430"
TEAL, AMBER, RED, BLUE, VIOLET = "#2FE3C8", "#FFC24B", "#FF5C7A", "#6AA6FF", "#B48CFF"
PALETTE = [TEAL, AMBER, BLUE, VIOLET, "#7BE495", "#FF8A65", "#4DD0E1", "#F06292"]

plt.rcParams.update({
    "figure.facecolor": BG, "axes.facecolor": BG, "savefig.facecolor": BG,
    "text.color": FG, "axes.labelcolor": FG, "xtick.color": "#93A3B5", "ytick.color": "#93A3B5",
    "axes.edgecolor": GRID, "grid.color": GRID, "font.family": "DejaVu Sans",
    "font.size": 15, "axes.titlesize": 25, "figure.dpi": 110,
})


def load(n):
    with open(os.path.join(D, n)) as fh:
        return json.load(fh)


def frame(ax):
    for s in ("top", "right"):
        ax.spines[s].set_visible(False)
    ax.spines["left"].set_color(GRID)
    ax.spines["bottom"].set_color(GRID)


def barh(ax, labels, values, colors, fmt="{:,}", bar_h=0.62):
    y = range(len(labels))[::-1]
    ax.barh(list(y), values, color=colors, height=bar_h)
    ax.set_yticks(list(y))
    ax.set_yticklabels(labels, fontsize=15)
    mx = max(values) if values else 1
    for yy, v in zip(y, values):
        ax.text(v + mx * 0.015, yy, fmt.format(v), va="center", fontsize=13, color="#B9C6D4")
    ax.set_xlim(0, mx * 1.18)
    ax.xaxis.set_visible(False)
    frame(ax)


def save(fig, name):
    fig.savefig(os.path.join(FIG, name), bbox_inches="tight", pad_inches=0.35)
    plt.close(fig)
    print("wrote", name)


def fig_composition():
    res, prov = load("07-resources.json"), load("01-provenance.json")
    comp = res["composition"]
    base = comp["base_apk"]
    parts = [
        ("resources (res/)", base["res"]["bytes"]),
        ("assets (ML models, JS bridges)", base["assets"]["bytes"]),
        ("DEX bytecode (19 files)", sum(v["files"] and v["bytes"] for k, v in base.items() if k.startswith("classes"))),
        ("native libraries (split)", base["lib"]["bytes"] if "lib" in base else comp.get("config.armeabi_v7a.apk", {}).get("bytes", 0)),
        ("resources.arsc", base["resources.arsc"]["bytes"]),
        ("META-INF + misc", sum(v["bytes"] for k, v in base.items()
                                if k not in ("res", "assets", "resources.arsc") and not k.startswith("classes") and k != "lib")),
    ]
    parts.sort(key=lambda p: -p[1])
    fig, ax = plt.subplots(figsize=(13.2, 7.4))
    barh(ax, [p[0] for p in parts], [p[1] / 1e6 for p in parts],
         [PALETTE[i % len(PALETTE)] for i in range(len(parts))], fmt="{:.1f} MB")
    ax.set_title("XAPK payload composition — where the megabytes live", loc="left", pad=18)
    fig.text(0.012, 0.02, f"total container {prov['container']['xapk_bytes']/1e6:.1f} MB  ·  "
                          f"base APK {prov['split_apks']['vn.com.vng.zalopay.apk']['sha256'][:16]}…",
             fontsize=11, color="#7C8B9B", family="DejaVu Sans Mono")
    save(fig, "composition.png")


def fig_vendors():
    dex = load("03-dex.json")
    roots = dex["top_root_packages"][:14]
    labels = [r[0] for r in roots]
    vals = [r[1] for r in roots]
    fig, ax = plt.subplots(figsize=(13.2, 7.4))
    colors = [RED if l == "vn" else TEAL if i % 2 == 0 else BLUE for i, l in enumerate(labels)]
    barh(ax, labels, vals, colors, fmt="{:,}")
    ax.set_title("Namespace census — 60 671 classes, sorted by owner", loc="left", pad=18)
    ax.annotate("first-party root\n(1.6 % of the binary)", xy=(vals[labels.index("vn")], len(labels) - 1 - labels.index("vn")),
                xytext=(max(vals) * 0.45, len(labels) - 2.1), color=RED, fontsize=13,
                arrowprops=dict(arrowstyle="->", color=RED, lw=1.4))
    save(fig, "vendors.png")


def fig_signals():
    sig = load("06-signals.json")
    items = sorted(((k, v["total_hits"]) for k, v in sig["signals"].items()), key=lambda kv: kv[1])
    fig, ax = plt.subplots(figsize=(13.2, 7.8))
    colors = [AMBER if k in ("jsbridge", "payment_sensitive", "anti_tamper_packaging") else TEAL for k, _ in items]
    barh(ax, [k.replace("_", " ") for k, _ in items], [v for _, v in items], colors, fmt="{:,}", bar_h=0.68)
    ax.set_title("Static signal families — string-table prevalence", loc="left", pad=18)
    fig.text(0.012, 0.015, "counts are matching strings, not proof of runtime behaviour — see research/10-methodology.md",
             fontsize=11, color="#7C8B9B", family="DejaVu Sans Mono")
    save(fig, "signals.png")


def fig_domains():
    sig = load("06-signals.json")
    cls = sig["domains"]["by_class"]
    labels = list(cls.keys())
    vals = [cls[k]["host_count"] for k in labels]
    order = sorted(range(len(vals)), key=lambda i: -vals[i])
    labels = [labels[i] for i in order]
    vals = [vals[i] for i in order]
    fig, ax = plt.subplots(figsize=(13.2, 7.0))
    barh(ax, labels, vals, [PALETTE[i % len(PALETTE)] for i in range(len(vals))], fmt="{:,}", bar_h=0.6)
    ax.set_title(f"Network reach — {sig['domains']['unique_hosts']} distinct hosts in the string table", loc="left", pad=18)
    save(fig, "domains.png")


def fig_rubric():
    import numpy as np
    s = load("08-summary.json")
    dims = s["rubric"]["dimensions"]
    labels = [d["id"].replace("_", "\n") for d in dims]
    vals = [d["score"] for d in dims]
    ang = np.linspace(0, 2 * np.pi, len(dims), endpoint=False).tolist()
    vals_c = vals + vals[:1]
    ang_c = ang + ang[:1]
    fig = plt.figure(figsize=(10.4, 9.6))
    ax = fig.add_subplot(111, polar=True)
    ax.set_facecolor(BG)
    ax.plot(ang_c, vals_c, color=TEAL, lw=2.4)
    ax.fill(ang_c, vals_c, color=TEAL, alpha=0.18)
    ax.scatter(ang, vals, color=AMBER, s=70, zorder=5)
    ax.set_xticks(ang)
    ax.set_xticklabels(labels, fontsize=12)
    ax.tick_params(axis="x", pad=26)
    ax.set_ylim(0, 108)
    ax.set_yticks([25, 50, 75, 100])
    ax.set_yticklabels(["25", "50", "75", "100"], fontsize=10, color="#5C6B7C")
    ax.grid(color=GRID)
    ax.spines["polar"].set_color(GRID)
    fig.suptitle(f"Posture rubric — weighted {s['rubric']['weighted_score']}/100", x=0.06, y=0.965,
                 ha="left", fontsize=22)
    save(fig, "rubric.png")


def fig_dex():
    dex = load("03-dex.json")
    per = sorted(dex["per_dex"], key=lambda d: -d["bytes"])
    fig, ax = plt.subplots(figsize=(13.2, 7.0))
    xs = range(len(per))
    ax.bar(list(xs), [d["bytes"] / 1e6 for d in per], color=TEAL, width=0.66)
    ax.plot(list(xs), [d["methods"] / 1e6 * 6 for d in per], color=AMBER, marker="o", lw=2, label="methods (scaled)")
    ax.axhline(65536 / 1e6 * 6, color=RED, ls="--", lw=1.2)
    ax.text(len(per) - 0.4, 65536 / 1e6 * 6 * 1.02, "64K method-id ceiling", ha="right", fontsize=11, color=RED)
    ax.set_xticks(list(xs))
    ax.set_xticklabels([d["name"].replace("classes", "c").replace(".dex", "") for d in per], fontsize=10, rotation=0)
    ax.set_ylabel("MB per DEX")
    ax.set_title("Multidex layout — 19 archives, none past the method-id ceiling", loc="left", pad=18)
    ax.legend(frameon=False, fontsize=12, loc="upper right")
    frame(ax)
    save(fig, "dex.png")


def fig_models():
    res = load("07-resources.json")
    models = res["assets"]["models"][:10]
    fig, ax = plt.subplots(figsize=(13.2, 6.4))
    barh(ax, [m["path"].split("/")[-1] for m in models], [m["bytes"] / 1e6 for m in models],
         [VIOLET, BLUE, TEAL, AMBER] * 3, fmt="{:.2f} MB", bar_h=0.6)
    ax.set_title("On-device ML inventory — 17 models shipped with the APK", loc="left", pad=18)
    save(fig, "models.png")


def fig_prov():
    prov = load("01-provenance.json")
    blocks = prov["base_apk"]["signing_block"]["scheme_blocks"]
    fig, ax = plt.subplots(figsize=(14, 4.2))
    ax.set_xlim(0, 100); ax.set_ylim(0, 10)
    ax.axis("off")
    x = 2
    names = [("ZIP container\nXAPK v2", 14), ("APK Signing Block\n8 192 B", 20),
             ("v2 · RSA-2048\nSHA-256", 18), ("v3 · SDK 24→\nkey-rotation ready", 20), ("Play source\nstamp", 14)]
    for i, (label, w) in enumerate(names):
        ax.add_patch(Rectangle((x, 3.2), w, 3.4, facecolor="none", edgecolor=[TEAL, BLUE, AMBER, VIOLET, GRID][i], lw=2))
        ax.text(x + w / 2, 4.9, label, ha="center", va="center", fontsize=13)
        if i < len(names) - 1:
            ax.annotate("", xy=(x + w + 2.2, 4.9), xytext=(x + w + 0.2, 4.9),
                        arrowprops=dict(arrowstyle="->", color="#5C6B7C", lw=1.6))
        x += w + 2.4
    ax.text(2, 1.2, f"signer  CN=Vu Manh Hieu (self-signed, 2016-2041)  ·  SHA-256 "
                    f"{prov['base_apk']['signing_block']['scheme_blocks'][0]['signers'][0]['certificates'][0]['sha256_fingerprint'][:24]}…",
            fontsize=11, color="#8B9AAB", family="DejaVu Sans Mono")
    ax.set_title("Provenance chain recovered without apksigner", loc="left", fontsize=22, pad=10)
    save(fig, "provenance.png")


if __name__ == "__main__":
    fig_composition(); fig_vendors(); fig_signals(); fig_domains(); fig_rubric(); fig_dex(); fig_models(); fig_prov()
