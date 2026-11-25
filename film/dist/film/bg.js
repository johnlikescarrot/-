import { circle, glow, line, rect } from "./draw.js";
import { C, H, W } from "./theme.js";
import { clamp, lerp, rgba, rng } from "./util.js";
const PARTICLES = 220;
const P = (() => {
    const r = rng(0x5eed);
    return Array.from({ length: PARTICLES }, () => ({
        x: r(),
        y: r(),
        z: 0.25 + r() * 0.75,
        s: 0.6 + r() * 1.8,
        ph: r() * Math.PI * 2,
    }));
})();
export function background(ctx, t, accent, intensity = 1) {
    // base
    const g = ctx.createLinearGradient(0, 0, W * 0.35, H);
    g.addColorStop(0, "#0a1020");
    g.addColorStop(0.5, "#06090f");
    g.addColorStop(1, "#04060a");
    ctx.fillStyle = g;
    ctx.fillRect(0, 0, W, H);
    // horizon glow, tied to the act colour
    glow(ctx, W * 0.5, H * 0.92, 900, accent, 0.055 * intensity);
    glow(ctx, W * 0.08, H * 0.12, 620, accent, 0.03 * intensity);
    // instrument grid
    ctx.save();
    ctx.globalAlpha = 0.055;
    ctx.strokeStyle = "#8ea6c4";
    ctx.lineWidth = 1;
    const step = 60;
    const off = (t * 6) % step;
    for (let x = -step; x <= W + step; x += step) {
        ctx.beginPath();
        ctx.moveTo(Math.round(x + off) + 0.5, 0);
        ctx.lineTo(Math.round(x + off) + 0.5, H);
        ctx.stroke();
    }
    for (let y = -step; y <= H + step; y += step) {
        ctx.beginPath();
        ctx.moveTo(0, Math.round(y + off * 0.35) + 0.5);
        ctx.lineTo(W, Math.round(y + off * 0.35) + 0.5);
        ctx.stroke();
    }
    ctx.restore();
    // particle field with parallax
    for (const p of P) {
        const y = ((p.y + t * 0.008 * p.z) % 1) * H;
        const x = ((p.x + Math.sin(t * 0.12 + p.ph) * 0.006 * p.z) % 1) * W;
        const a = 0.05 + 0.16 * p.z * (0.5 + 0.5 * Math.sin(t * 1.3 + p.ph));
        circle(ctx, x, y, p.s * p.z * 1.5, rgba(accent, a));
    }
    // faint scanline sweep
    const sy = ((t * 0.045) % 1) * (H + 400) - 200;
    const sg = ctx.createLinearGradient(0, sy - 160, 0, sy + 160);
    sg.addColorStop(0, rgba(accent, 0));
    sg.addColorStop(0.5, rgba(accent, 0.028));
    sg.addColorStop(1, rgba(accent, 0));
    ctx.fillStyle = sg;
    ctx.fillRect(0, sy - 160, W, 320);
}
/** Thin progress spine at the very bottom of frame. */
export function progressSpine(ctx, t, total, accent) {
    const p = clamp(t / total);
    rect(ctx, 0, H - 3, W, 3, "#000", 0.5);
    rect(ctx, 0, H - 3, W * p, 3, accent, 0.55);
    // chapter ticks
    ctx.save();
    ctx.globalAlpha = 0.35;
    for (let i = 1; i < 8; i++) {
        const x = (W * i) / 8;
        line(ctx, x, H - 7, x, H - 3, C.white, 1, 0.6);
    }
    ctx.restore();
}
/** Subtle chromatic edge bloom to make dark frames feel filmic. */
export function edgeBloom(ctx, accent, a = 0.5) {
    const g1 = ctx.createLinearGradient(0, 0, 0, 160);
    g1.addColorStop(0, rgba(accent, 0.07 * a));
    g1.addColorStop(1, rgba(accent, 0));
    ctx.fillStyle = g1;
    ctx.fillRect(0, 0, W, 160);
    const g2 = ctx.createLinearGradient(0, H - 200, 0, H);
    g2.addColorStop(0, rgba("#000000", 0));
    g2.addColorStop(1, "rgba(0,0,0,0.55)");
    ctx.fillStyle = g2;
    ctx.fillRect(0, H - 200, W, 200);
}
export function lerpAccent(a, b, t) {
    const pa = parseInt(a.replace("#", ""), 16);
    const pb = parseInt(b.replace("#", ""), 16);
    const r = Math.round(lerp((pa >> 16) & 255, (pb >> 16) & 255, clamp(t)));
    const g = Math.round(lerp((pa >> 8) & 255, (pb >> 8) & 255, clamp(t)));
    const bl = Math.round(lerp(pa & 255, pb & 255, clamp(t)));
    return `rgb(${r},${g},${bl})`;
}
