import { line, measure, para, rect, roundRect, strokeRect } from "../draw.js";
import { text } from "../draw.js";
import { C, F, G, W } from "../theme.js";
import { clamp, ease, inv, rgba } from "../util.js";
import { chip, heading, scanSweep, squarify, stackedBar } from "../components.js";
import { fmtBytes } from "../../research/evidence.js";
export const s_unpack = {
    id: "unpack",
    act: "artifact",
    title: "Container chain",
    dur: 22,
    evidence: ["01"],
    draw(ctx, t, ev) {
        heading(ctx, "Layer 01 — Container", "One archive, nested twice", inv(t, 0.2, 1.4), C.green, { size: 48 });
        const zip = ev.container.outer;
        const totalSplit = ev.container.xapk_entries.reduce((a, e) => a + e.size, 0);
        const nodes = [
            { id: "zip", label: "ZIP", sub: zip ? zip.name.slice(0, 34) + "…" : "outer", size: zip?.size ?? 0, x: 132, y: 420, w: 300, color: C.green },
            { id: "xapk", label: "XAPK", sub: "1 entry · 105.6 MB", size: ev.container.xapk_size, x: 620, y: 420, w: 300, color: C.cyan },
        ];
        const splits = ev.container.xapk_entries.filter((e) => e.name.endsWith(".apk"));
        const base = splits.find((s) => s.name === "vn.com.vng.zalopay.apk");
        const others = splits.filter((s) => s.name !== "vn.com.vng.zalopay.apk");
        const rows = [
            { id: "base", label: "base APK", sub: base.name, size: base.size, x: 1130, y: 300, w: 620, color: C.amber },
            ...others.map((o, i) => ({
                id: o.name, label: o.name.replace("config.", "config."), sub: `${(o.size / 1e6).toFixed(2)} MB`,
                size: o.size, x: 1130, y: 380 + i * 74, w: 620, color: C.violet,
            })),
        ];
        // connectors
        const drawLink = (a, b, p) => {
            const x1 = a.x + a.w;
            const y1 = a.y + 40;
            const x2 = b.x;
            const y2 = b.y + 40;
            const mx = (x1 + x2) / 2;
            ctx.save();
            ctx.globalAlpha = clamp(p);
            ctx.strokeStyle = rgba(C.green, 0.5);
            ctx.lineWidth = 1.4;
            ctx.beginPath();
            ctx.moveTo(x1, y1);
            const e = ease.outQuint(clamp(p));
            ctx.bezierCurveTo(lerpV(x1, mx, e), y1, lerpV(x2, mx, e), y2, lerpV(x1, x2, e), lerpV(y1, y2, e));
            ctx.stroke();
            ctx.restore();
        };
        const p1 = inv(t, 1.2, 2.6);
        const p2 = inv(t, 3.0, 4.4);
        const p3 = inv(t, 5.2, 7.6);
        drawLink(nodes[0], nodes[1], p1);
        if (p2 > 0)
            for (const r of [rows[0]])
                drawLink(nodes[1], r, p2);
        if (p3 > 0)
            for (const r of rows.slice(1))
                drawLink(nodes[1], r, p3);
        // node cards
        const card = (n, p) => {
            const e = ease.outQuint(clamp(p));
            if (e <= 0)
                return;
            ctx.save();
            ctx.globalAlpha = e;
            rect(ctx, n.x, n.y, n.w, 80, C.panel, 0.9, 8);
            strokeRect(ctx, n.x, n.y, n.w, 80, n.color, 1, 0.55, 8);
            rect(ctx, n.x, n.y, 3, 80, n.color, 0.9);
            text(ctx, n.label, n.x + 20, n.y + 32, { font: F.displayBold, size: 20, color: C.white });
            text(ctx, n.sub, n.x + 20, n.y + 56, { font: F.mono, size: 12, color: C.dim2, maxWidth: n.w - 40 });
            text(ctx, fmtBytes(n.size), n.x + n.w - 18, n.y + 32, { font: F.monoMed, size: 15, color: n.color, align: "right" });
            ctx.restore();
        };
        card(nodes[0], p1);
        card(nodes[1], p1);
        card(rows[0], p2);
        rows.slice(1).forEach((r) => card(r, p3));
        // split-count callout
        const cp = inv(t, 8.0, 9.4);
        if (cp > 0) {
            text(ctx, "5", G.margin, 700, { font: F.displayBold, size: 96, color: C.white, alpha: clamp(cp) });
            text(ctx, "APKs in one install", G.margin + 68, 700, { font: F.mono, size: 15, color: C.dim, alpha: clamp(cp) });
            text(ctx, "targetSdk 36 · minSdk 26", G.margin, 736, { font: F.mono, size: 14, color: C.dim2, alpha: clamp(cp) });
        }
        // compression ratio strip
        const rp = inv(t, 9.6, 11.4);
        if (rp > 0) {
            text(ctx, "UNCOMPRESSED PAYLOAD", G.margin, 800, { font: F.mono, size: 11, color: C.dim2, letter: 2, alpha: clamp(rp) });
            const total = totalSplit;
            const parts = [
                { label: "base", value: base.size, color: C.amber },
                ...others.map((o) => ({ label: o.name.replace("config.", ""), value: o.size, color: C.violet })),
            ];
            stackedBar(ctx, G.margin, 816, 1680, 14, parts, rp);
            text(ctx, fmtBytes(total), G.margin + 1680, 850, { font: F.monoMed, size: 13, color: C.white, align: "right", alpha: clamp(rp) });
            let lx = G.margin;
            parts.forEach((pt) => {
                const w = measure(ctx, `${pt.label} ${(pt.value / total * 100).toFixed(1)}%`, 11, F.mono, 0.6) + 26;
                ctx.save();
                ctx.globalAlpha = clamp(rp);
                ctx.fillStyle = pt.color;
                ctx.beginPath();
                ctx.arc(lx + 4, 862, 4, 0, Math.PI * 2);
                ctx.fill();
                ctx.restore();
                text(ctx, `${pt.label} ${(pt.value / total * 100).toFixed(1)}%`, lx + 14, 866, { font: F.mono, size: 11, color: C.dim, letter: 0.6, alpha: clamp(rp) });
                lx += w;
            });
        }
        scanSweep(ctx, 1130, 292, 620, 370, t, C.green);
    },
};
function lerpV(a, b, t) {
    return a + (b - a) * t;
}
/* ------------------------------------------------------- mass composition */
export const s_mass = {
    id: "mass",
    act: "artifact",
    title: "Mass composition",
    dur: 20,
    evidence: ["03", "04", "05"],
    draw(ctx, t, ev) {
        heading(ctx, "Layer 01 — Mass", "Where 105 megabytes actually go", inv(t, 0.2, 1.4), C.green, { size: 48 });
        const nativeBytes = ev.native.total_bytes;
        const assetsBytes = ev.assets.assets_bytes;
        const resBytes = ev.assets.arsc_bytes;
        const dexBytes = ev.dex.dex_files.reduce((a, d) => a + d.bytes, 0);
        const items = [
            { label: "native .so", value: nativeBytes, color: C.magenta },
            { label: "assets", value: assetsBytes, color: C.violet },
            { label: "dex", value: dexBytes, color: C.cyan },
            { label: "resources.arsc", value: resBytes, color: C.amber },
            { label: "res/ (compiled)", value: 52_000_000 - resBytes, color: C.green },
        ];
        const total = items.reduce((a, b) => a + b.value, 0);
        const p = inv(t, 1.0, 3.0);
        // left: squarified treemap
        const bx = G.margin, by = 360, bw = 940, bh = 520;
        const cells = squarify(items, bx, by, bw, bh);
        cells.forEach((c, i) => {
            const ip = inv(p, i * 0.06, 0.5 + i * 0.06);
            if (ip <= 0)
                return;
            const a = clamp(ip);
            roundRect(ctx, c.x + 2, c.y + 2, Math.max(0, c.w - 4) * ease.outQuint(a), Math.max(0, c.h - 4) * ease.outQuint(a), 6);
            ctx.save();
            ctx.globalAlpha = 0.16;
            ctx.fillStyle = c.color;
            ctx.fill();
            ctx.restore();
            strokeRect(ctx, c.x + 2, c.y + 2, Math.max(0, c.w - 4), Math.max(0, c.h - 4), c.color, 1, 0.5 * a, 6);
            if (c.w > 90 && c.h > 54) {
                text(ctx, c.label, c.x + 16, c.y + 30, { font: F.monoMed, size: 13, color: c.color, alpha: a });
                text(ctx, fmtBytes(c.value), c.x + 16, c.y + 54, { font: F.displayBold, size: 18, color: C.white, alpha: a });
                text(ctx, `${((c.value / total) * 100).toFixed(1)}%`, c.x + 16, c.y + 76, { font: F.mono, size: 11, color: C.dim2, alpha: a });
            }
        });
        // right: stacked bar + numbers
        const rx = 1150;
        text(ctx, "TOTAL INSTALLED MASS", rx, 380, { font: F.mono, size: 11, color: C.dim2, letter: 2.2, alpha: clamp(inv(t, 3.0, 4.0)) });
        text(ctx, fmtBytes(total), rx, 444, { font: F.displayBold, size: 56, color: C.white, alpha: clamp(inv(t, 3.0, 4.4)) });
        stackedBar(ctx, rx, 476, 600, 16, items.map((i) => ({ label: i.label, value: i.value, color: i.color })), inv(t, 4.0, 5.6));
        items.forEach((it, i) => {
            const ip = inv(t, 5.0 + i * 0.3, 6.2 + i * 0.3);
            const y = 546 + i * 52;
            ctx.save();
            ctx.globalAlpha = clamp(ip);
            rect(ctx, rx, y - 14, 10, 10, it.color, 1, 2);
            text(ctx, it.label, rx + 24, y - 4, { font: F.bodyMed, size: 15, color: C.white });
            text(ctx, fmtBytes(it.value), rx + 600, y - 4, { font: F.monoMed, size: 15, color: it.color, align: "right" });
            text(ctx, `${((it.value / total) * 100).toFixed(1)}%`, rx + 690, y - 4, { font: F.mono, size: 13, color: C.dim2, align: "right" });
            line(ctx, rx, y + 12, rx + 690, y + 12, C.line, 1, 0.6 * clamp(ip));
            ctx.restore();
        });
        // footer insight
        const fp = inv(t, 8.4, 10.0);
        para(ctx, "Native code is the single largest block — 57 MB of the 105 MB installed. A payments client spends more space on C and C++ than on its own Java and Kotlin.", G.margin, 940, { size: 16, color: C.dim, width: 1200, lh: 26, maxLines: 2, alpha: clamp(fp) });
    },
};
/* ---------------------------------------------------------- file census */
export const s_files = {
    id: "files",
    act: "artifact",
    title: "File census",
    dur: 18,
    evidence: ["01", "05"],
    draw(ctx, t, ev) {
        heading(ctx, "Layer 01 — Census", "6,192 files, one APK", inv(t, 0.2, 1.4), C.green, { size: 48 });
        text(ctx, `base APK: ${ev.assets.total_files_in_base_apk.toLocaleString()} files  ·  ${ev.assets.res_files.toLocaleString()} compiled resources  ·  ${ev.assets.assets_count} assets  ·  ${ev.dex.dex_files.length} DEX`, G.margin, 300, { font: F.mono, size: 15, color: C.dim, alpha: clamp(inv(t, 0.8, 2.0)) });
        // top-level byte composition from container data
        const top = ev.container.by_extension;
        const rows = Object.entries(top)
            .map(([k, v]) => ({ ext: k, ...v }))
            .filter((r) => r.bytes > 200_000)
            .sort((a, b) => b.bytes - a.bytes)
            .slice(0, 12);
        const max = Math.max(...rows.map((r) => r.bytes));
        rows.forEach((r, i) => {
            const p = inv(t, 1.6 + i * 0.22, 2.6 + i * 0.22);
            if (p <= 0)
                return;
            const y = 372 + i * 46;
            const e = ease.outQuint(p);
            ctx.save();
            ctx.globalAlpha = clamp(p);
            text(ctx, r.ext === "(none)" ? "no-ext" : r.ext, G.margin, y, { font: F.monoMed, size: 15, color: C.white });
            text(ctx, `${r.files.toLocaleString()} files`, G.margin + 190, y, { font: F.mono, size: 13, color: C.dim2 });
            const bw = (r.bytes / max) * 900 * e;
            rect(ctx, G.margin + 330, y - 14, 900, 16, C.line, 0.35, 3);
            rect(ctx, G.margin + 330, y - 14, bw, 16, i === 0 ? C.magenta : C.green, 0.8, 3);
            text(ctx, fmtBytes(r.bytes), G.margin + 330 + 912, y, { font: F.monoMed, size: 14, color: C.dim, align: "left" });
            ctx.restore();
        });
        // right column: asset types
        const rx = 1420;
        text(ctx, "ASSET TYPES", rx, 372, { font: F.mono, size: 11, color: C.dim2, letter: 2, alpha: clamp(inv(t, 3.0, 3.8)) });
        const at = Object.entries(ev.assets.assets_by_ext).slice(0, 10);
        at.forEach(([k, v], i) => {
            const p = inv(t, 3.2 + i * 0.16, 4.0 + i * 0.16);
            text(ctx, k, rx, 404 + i * 30, { font: F.mono, size: 13, color: C.dim, alpha: clamp(p) });
            text(ctx, String(v), rx + 330, 404 + i * 30, { font: F.monoMed, size: 13, color: C.white, align: "right", alpha: clamp(p) });
            line(ctx, rx, 412 + i * 30, rx + 330, 412 + i * 30, C.line, 1, 0.4 * clamp(p));
        });
        // highlights of the biggest assets
        const hp = inv(t, 6.0, 7.4);
        if (hp > 0) {
            text(ctx, "HEAVIEST ASSETS", G.margin, 900, { font: F.mono, size: 11, color: C.dim2, letter: 2, alpha: clamp(hp) });
            ev.assets.largest_assets.slice(0, 4).forEach((a, i) => {
                const ap = inv(t, 6.2 + i * 0.2, 7.0 + i * 0.2);
                text(ctx, `${i + 1}. ${a.path}`, G.margin, 928 + i * 24, { font: F.mono, size: 12, color: C.dim, alpha: clamp(ap) });
                text(ctx, fmtBytes(a.bytes), G.margin + 1180, 928 + i * 24, { font: F.monoMed, size: 12, color: C.cyan, align: "right", alpha: clamp(ap) });
            });
        }
        chip(ctx, W - G.margin - 210, 268, "ZERO SYMBOLIC LINKS", C.green, inv(t, 8.0, 9.0), { filled: true });
    },
};
export const ACT2_SCENES = [s_unpack, s_mass, s_files];
