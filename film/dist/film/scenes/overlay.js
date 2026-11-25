import { line, measure, rect, text } from "../draw.js";
import { C, F, G, H, W } from "../theme.js";
import { clamp, ease, inv, rgba } from "../util.js";
import { ACTS } from "../timeline.js";
/** Cinematic lower third: English line, then Vietnamese line beneath it. */
export function lowerThird(ctx, o) {
    const p = clamp(o.p);
    if (p <= 0.001)
        return;
    const x = G.margin;
    const baseY = H - 168;
    const inP = ease.outQuint(inv(p, 0, 0.35));
    const outP = 1 - ease.inOutQuint(inv(p, 0.82, 1));
    const a = Math.min(inP, outP);
    const dy = (1 - inP) * 12;
    const maxW = W - G.margin * 2 - 40;
    ctx.save();
    ctx.globalAlpha = a;
    // scrim
    const g = ctx.createLinearGradient(0, H - 320, 0, H - 90);
    g.addColorStop(0, "rgba(4,6,10,0)");
    g.addColorStop(0.55, "rgba(4,6,10,0.72)");
    g.addColorStop(1, "rgba(4,6,10,0.9)");
    ctx.fillStyle = g;
    ctx.fillRect(0, H - 320, W, 240);
    // accent tick
    rect(ctx, x, baseY - 46 + dy, 3, 78, o.accent, 0.95);
    ctx.font = `30px ${F.display}`;
    const enLines = paraWrap(ctx, o.en, maxW, 2);
    ctx.font = `22px ${F.body}`;
    const viLines = paraWrap(ctx, o.vi, maxW, 2);
    let y = baseY + dy;
    enLines.forEach((l) => {
        text(ctx, l, x + 26, y, { font: F.display, size: 30, color: C.white, alpha: 0.98 });
        y += 40;
    });
    y += 4;
    viLines.forEach((l) => {
        text(ctx, l, x + 26, y, { font: F.body, size: 22, color: rgba(o.accent, 0.92), alpha: 0.95 });
        y += 30;
    });
    if (o.evidence) {
        const w = measure(ctx, o.evidence, 11, F.mono, 1.6);
        text(ctx, o.evidence, W - G.margin, H - 62, {
            font: F.mono,
            size: 11,
            color: C.dim2,
            letter: 1.6,
            align: "right",
            alpha: 0.9,
        });
        line(ctx, W - G.margin - w, H - 74, W - G.margin, H - 74, C.line, 1, 0.6);
    }
    ctx.restore();
}
function paraWrap(ctx, s, maxW, maxLines) {
    const words = s.split(/\s+/);
    const out = [];
    let cur = "";
    for (const w of words) {
        const test = cur ? `${cur} ${w}` : w;
        if (ctx.measureText(test).width <= maxW)
            cur = test;
        else {
            if (cur)
                out.push(cur);
            cur = w;
            if (out.length === maxLines)
                break;
        }
    }
    if (cur && out.length < maxLines)
        out.push(cur);
    return out;
}
/** Act intertitle: numeral, label, title, sweep rule. */
export function actCard(ctx, o) {
    const meta = ACTS[o.act];
    const p = clamp(o.p);
    if (p <= 0.001 || p >= 0.999)
        return;
    const a = Math.min(ease.outQuint(inv(p, 0, 0.22)), 1 - ease.inOutQuint(inv(p, 0.78, 1)));
    ctx.save();
    ctx.globalAlpha = a;
    rect(ctx, 0, 0, W, H, "#04060a", 0.94);
    // numeral
    const nx = G.margin;
    const ny = H / 2 + 40;
    text(ctx, meta.roman, nx, ny, {
        font: F.displayBold,
        size: 220,
        color: rgba(meta.color, 0.14),
        letter: 0,
    });
    text(ctx, `ACT ${String(o.n).padStart(2, "0")} / ${String(o.total).padStart(2, "0")}`, nx + 8, H / 2 - 170, {
        font: F.mono,
        size: 13,
        color: meta.color,
        letter: 4,
        alpha: 0.95,
    });
    line(ctx, nx + 8, H / 2 - 150, nx + 8 + 120 * ease.outQuint(inv(p, 0.1, 0.5)), H / 2 - 150, meta.color, 2, 0.8);
    text(ctx, meta.label, nx + 8, H / 2 - 76, {
        font: F.displayBold,
        size: 68,
        color: C.white,
        letter: 1.5,
        alpha: ease.outQuint(inv(p, 0.12, 0.45)),
    });
    text(ctx, meta.title, nx + 10, H / 2 - 26, {
        font: F.display,
        size: 30,
        color: rgba(meta.color, 0.9),
        letter: 0.5,
        alpha: ease.outQuint(inv(p, 0.2, 0.55)),
    });
    // right side: act colour block
    rect(ctx, W - G.margin - 2, H / 2 - 176, 2, 200 * ease.outQuint(inv(p, 0.15, 0.6)), meta.color, 0.85);
    ctx.restore();
}
/** Citation chip used when a claim lands. */
export function citation(ctx, label, p, accent) {
    const a = clamp(p);
    if (a <= 0)
        return;
    const x = G.margin;
    const y = 128;
    const w = measure(ctx, label, 11, F.monoMed, 1.6) + 26;
    ctx.save();
    ctx.globalAlpha = a;
    rect(ctx, x, y - 14, w, 24, accent, 0.12, 3);
    text(ctx, label, x + 13, y + 2, { font: F.monoMed, size: 11, color: accent, letter: 1.6 });
    ctx.restore();
}
/** Style of tiny lane diagram used in a couple of scenes. */
export function lanes(ctx, x, y, w, items, p) {
    const total = items.reduce((s, i) => s + i.value, 0) || 1;
    let cy = y;
    items.forEach((it, i) => {
        const ip = inv(p, i * 0.08, 0.4 + i * 0.08);
        const bw = (it.value / total) * w * ease.outQuint(ip);
        rect(ctx, x, cy, bw, 10, it.color, 0.9, 5);
        text(ctx, it.label, x + w + 16, cy + 9, { font: F.mono, size: 11, color: C.dim, alpha: ip });
        cy += 22;
    });
}
