/** ACT I — Overture: cold open, title card, the six-layer method, the question. */
import type { SceneMeta } from "../timeline.js";
import { glow, line, measure, para, rect, circle } from "../draw.js";
import { text } from "../draw.js";
import { C, F, G, H, W } from "../theme.js";
import { clamp, ease, inv, rgba } from "../util.js";
import { chip, heading, typewriter } from "../components.js";

/* --------------------------------------------------------------- cold open */

export const s_open: SceneMeta = {
  id: "open",
  act: "overture",
  title: "Cold open",
  dur: 8.5,
  evidence: ["01"],
  draw(ctx, t) {
    const p1 = inv(t, 0.3, 3.4);
    const p2 = inv(t, 3.2, 6.2);
    const fade = 1 - inv(t, 7.4, 8.5);

    ctx.save();
    ctx.globalAlpha = 0.4 * fade;
    for (let i = 0; i < 60; i++) {
      const a = (i / 60) * Math.PI * 2;
      const r = 260 + Math.sin(t * 0.8 + i) * 60;
      circle(ctx, W / 2 + Math.cos(a) * r * 0.9, H / 2 + Math.sin(a) * r * 0.32, 1.2, rgba(C.cyan, 0.5 * (0.4 + 0.6 * Math.sin(t * 2 + i))));
    }
    ctx.restore();

    // wireframe container
    const cx = W / 2;
    const cy = H / 2 - 40;
    const s = 120 * ease.outQuint(p1) + 62;
    const rot = t * 0.2;
    const proj = (vx: number, vy: number, vz: number): [number, number] => {
      const c = Math.cos(rot), sn = Math.sin(rot);
      const X = vx * c - vz * sn;
      const Z = vx * sn + vz * c;
      const tilt = 0.42;
      const Y = vy * Math.cos(tilt) - Z * Math.sin(tilt);
      const persp = 1 + (vy * Math.sin(tilt) + Z * Math.cos(tilt)) / 900;
      return [cx + X * persp * 1.6, cy + Y * persp * 1.6];
    };
    const V: [number, number, number][] = [[-1, -1, -1], [1, -1, -1], [1, 1, -1], [-1, 1, -1], [-1, -1, 1], [1, -1, 1], [1, 1, 1], [-1, 1, 1]];
    const E = [[0, 1], [1, 2], [2, 3], [3, 0], [4, 5], [5, 6], [6, 7], [7, 4], [0, 4], [1, 5], [2, 6], [3, 7]];
    const P = V.map(([a, b, c]) => proj(a * s, b * s, c * s));
    ctx.save();
    ctx.globalAlpha = (0.35 + 0.5 * p1) * fade;
    ctx.strokeStyle = C.cyan;
    ctx.lineWidth = 1.1;
    for (const [i, j] of E) {
      ctx.beginPath();
      ctx.moveTo(P[i][0], P[i][1]);
      ctx.lineTo(P[j][0], P[j][1]);
      ctx.stroke();
    }
    ctx.restore();
    for (const [x, y] of P) circle(ctx, x, y, 2.2, rgba(C.cyan, 0.85 * fade));
    glow(ctx, cx, cy, 420, C.cyan, 0.1 * p1 * fade);

    if (p2 > 0) {
      const title = "ANATOMY OF A SUPER-APP WALLET";
      const w = measure(ctx, title, 44, F.displayBold, 4);
      text(ctx, title, W / 2, H / 2 + 250, { font: F.displayBold, size: 44, color: C.white, align: "center", letter: 4, alpha: clamp(p2 * 1.3) * fade });
      line(ctx, W / 2 - (w * ease.outQuint(p2)) / 2, H / 2 + 282, W / 2 + (w * ease.outQuint(p2)) / 2, H / 2 + 282, C.cyan, 1, 0.7 * fade);
      text(ctx, "A FORENSIC STATIC ANALYSIS OF VN.COM.VNG.ZALOPAY 11.17.0", W / 2, H / 2 + 318, {
        font: F.mono, size: 13, color: C.dim, align: "center", letter: 3.2, alpha: clamp(inv(t, 4.4, 6)) * fade,
      });
    }
    if (t < 2.2) {
      const n = Math.floor(t * 24) % 3;
      text(ctx, ["SIGNAL 0xA7 / LOCK", "SIGNAL 0xA7 / LOCK .", "SIGNAL 0xA7 / LOCK .."][n]!, G.margin, H - 120, {
        font: F.mono, size: 12, color: C.cyan, letter: 2, alpha: 0.7 * (1 - t / 2.2),
      });
    }
  },
  post(ctx, t) {
    rect(ctx, 0, 0, W, H, "#000", 1 - (1 - inv(t, 7.2, 8.5)));
    rect(ctx, 0, 0, W, H, "#000", 1 - inv(t, 0.4, 1.2));
  },
};

/* -------------------------------------------------------------- title card */

export const s_title: SceneMeta = {
  id: "title",
  act: "overture",
  title: "Title",
  dur: 16,
  evidence: ["01"],
  draw(ctx, t) {
    const p = inv(t, 0.2, 2.2);
    const q = inv(t, 1.6, 3.6);
    const r = inv(t, 3.4, 5.4);
    glow(ctx, W / 2, H * 0.46, 780, C.cyan, 0.06);
    text(ctx, "THE ANATOMY OF", W / 2, H * 0.36, { font: F.monoMed, size: 15, color: C.dim, align: "center", letter: 6, alpha: clamp(p) });
    text(ctx, "A SUPER-APP", W / 2, H * 0.36 + 92, { font: F.displayBold, size: 104, color: C.white, align: "center", letter: 1, alpha: clamp(inv(t, 0.8, 2.6)) });
    text(ctx, "WALLET", W / 2, H * 0.36 + 176, { font: F.displayBold, size: 104, color: C.cyan, align: "center", letter: 14, alpha: clamp(inv(t, 1.3, 3.1)) });
    line(ctx, W / 2 - 300 * ease.outQuint(q), H * 0.36 + 224, W / 2 + 300 * ease.outQuint(q), H * 0.36 + 224, C.cyan, 1, 0.55);
    text(ctx, "ZALOPAY 11.17.0  ·  vn.com.vng.zalopay", W / 2, H * 0.36 + 274, { font: F.mono, size: 17, color: C.dim, align: "center", letter: 2, alpha: clamp(q) });
    para(
      ctx,
      "A complete static reverse engineering of the 105 MB XAPK distributed on APKPure: container, manifest, 19 DEX files, 27 native libraries, and the trust boundaries holding a national payment app together.",
      W / 2 - 520, H * 0.36 + 330,
      { size: 17, color: C.dim2, width: 1040, lh: 28, maxLines: 3, align: "center", alpha: clamp(r) },
    );
    text(ctx, "AN ARENA AGENT MODE RESEARCH FILM", W / 2, H - 168, { font: F.mono, size: 11, color: C.mute, align: "center", letter: 3, alpha: clamp(r) });
    text(ctx, "RENDERED IN TYPESCRIPT  ·  1080p30  ·  SYNTHESISED SCORE", W / 2, H - 144, { font: F.mono, size: 11, color: C.mute, align: "center", letter: 2.4, alpha: clamp(r) });
  },
  post(ctx, t) {
    rect(ctx, 0, 0, W, H, "#000", 1 - inv(t, 0, 1.0));
  },
};

/* ---------------------------------------------------------- six layer method */

export const s_layers: SceneMeta = {
  id: "layers",
  act: "overture",
  title: "Six layers",
  dur: 15,
  evidence: ["01", "02", "03", "04", "09"],
  draw(ctx, t, ev) {
    heading(ctx, "Method", "Six layers, one question", inv(t, 0.2, 1.6), C.cyan, { size: 52 });
    const items = [
      { k: "01", n: "CONTAINER", d: "ZIP → XAPK → base + 4 split APKs", v: "5 APKs" },
      { k: "02", n: "MANIFEST", d: "binary AXML + resources.arsc", v: `${ev.manifest.counts.activities} activities` },
      { k: "03", n: "BYTECODE", d: "19 DEX · class graph · string pools", v: `${ev.dex.total_classes.toLocaleString()} classes` },
      { k: "04", n: "NATIVE", d: "27 ARM32 ELF · stripped", v: `${(ev.native.total_bytes / 1e6).toFixed(1)} MB` },
      { k: "05", n: "TRUST", d: "network security config · pinning · keystore", v: "1 config file" },
      { k: "06", n: "BEHAVIOUR", d: "capability triage over 37 families", v: `${ev.triage.verdict.capabilities_present}/${ev.triage.verdict.capabilities_total}` },
    ];
    items.forEach((it, i) => {
      const p = inv(t, 1.0 + i * 0.5, 2.2 + i * 0.5);
      if (p <= 0) return;
      const y = 400 + i * 92;
      const x = G.margin;
      const w = 1680;
      ctx.save();
      ctx.globalAlpha = clamp(p);
      rect(ctx, x, y - 34, w * ease.outQuint(p), 74, C.panel, 0.55, 6);
      rect(ctx, x, y - 34, 3, 74, C.cyan, 0.9);
      text(ctx, it.k, x + 26, y + 12, { font: F.monoBold, size: 20, color: C.cyan, alpha: 0.9 });
      text(ctx, it.n, x + 96, y + 12, { font: F.displayBold, size: 24, color: C.white });
      text(ctx, it.d, x + 360, y + 12, { font: F.mono, size: 15, color: C.dim2, letter: 0.4 });
      text(ctx, it.v, x + w - 30, y + 12, { font: F.monoMed, size: 18, color: C.white, align: "right" });
      ctx.restore();
    });
  },
};

/* ----------------------------------------------------------- the question */

export const s_question: SceneMeta = {
  id: "question",
  act: "overture",
  title: "The question",
  dur: 15,
  evidence: ["03", "09"],
  draw(ctx, t) {
    glow(ctx, W / 2, H / 2, 700, C.cyan, 0.05);
    text(ctx, "What is actually inside", W / 2, H / 2 - 60, { font: F.displayBold, size: 62, color: C.white, align: "center", alpha: clamp(inv(t, 0.6, 3.2)) });
    text(ctx, "a national payment app?", W / 2, H / 2 + 30, { font: F.displayBold, size: 62, color: C.cyan, align: "center", alpha: clamp(inv(t, 1.4, 3.8)) });
    para(
      ctx,
      "Not marketing copy. Not a vendor whitepaper. The shipped bytes: what the installer contains, what it declares to Android, which code it will run, and where its trust decisions actually happen.",
      W / 2 - 480, H / 2 + 130,
      { size: 18, color: C.dim, width: 960, lh: 30, align: "center", maxLines: 3, alpha: clamp(inv(t, 3.6, 6.4)) },
    );
    const labels = ["NO RUNTIME HOOKING", "NO TRAFFIC CAPTURE", "NO CREDENTIAL ACCESS"];
    const totalW = labels.reduce((s, l) => s + measure(ctx, l, 12, F.monoMed, 1.6) + 44, 0) + 2 * 18;
    let cx = W / 2 - totalW / 2;
    labels.forEach((l, i) => {
      cx += chip(ctx, cx, H / 2 + 300, l, C.green, inv(t, 5.0 + i * 0.35, 6.2 + i * 0.35), { filled: true }) + 18;
    });
    typewriter(ctx, "$ python3 film/tools/collect.py work/xapk/vn.com.vng.zalopay.apk film/data", G.margin, H - 130, inv(t, 6.4, 9.4), { size: 15, color: C.dim2 });
  },
};

export const ACT1_SCENES = [s_open, s_title, s_layers, s_question];
