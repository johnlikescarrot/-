/** Reusable scene widgets: counters, bars, chips, cards, tables, gauges. */
import type { Ctx } from "./draw.js";
import { circle, clipRound, line, measure, panel, para, rect, ring, roundRect, strokeRect, text } from "./draw.js";
import { C, F } from "./theme.js";
import { clamp, ease, lerp, nf, rgba } from "./util.js";

/** Animated integer counter with tabular feel. */
export function counter(
  ctx: Ctx,
  value: number,
  x: number,
  y: number,
  p: number,
  opts: { size?: number; color?: string; font?: string; suffix?: string; prefix?: string; align?: "left" | "center" | "right"; alpha?: number; maxWidth?: number } = {},
): void {
  const v = Math.round(value * ease.outQuint(p));
  text(ctx, `${opts.prefix ?? ""}${nf(v)}${opts.suffix ?? ""}`, x, y, {
    size: opts.size ?? 72,
    font: opts.font ?? F.displayBold,
    color: opts.color ?? C.white,
    align: opts.align ?? "left",
    alpha: opts.alpha,
    maxWidth: opts.maxWidth,
  });
}

export function bigStat(
  ctx: Ctx,
  x: number,
  y: number,
  label: string,
  value: string,
  p: number,
  accent: string,
  opts: { size?: number; unit?: string; w?: number } = {},
): void {
  const a = clamp(p * 1.6);
  text(ctx, label.toUpperCase(), x, y, {
    font: F.mono,
    size: 12,
    color: C.dim2,
    letter: 2.2,
    alpha: a,
  });
  text(ctx, value, x, y + (opts.size ?? 54) * 0.92, {
    font: F.displayBold,
    size: opts.size ?? 54,
    color: C.white,
    alpha: a,
  });
  if (opts.unit) {
    const w = measure(ctx, value, opts.size ?? 54, F.displayBold);
    text(ctx, opts.unit, x + w + 8, y + (opts.size ?? 54) * 0.92, {
      font: F.mono,
      size: 15,
      color: accent,
      alpha: a,
    });
  }
  line(ctx, x, y + 14, x + (opts.w ?? 150) * ease.outQuint(p), y + 14, accent, 2, 0.7);
}

/** Horizontal bar with easing, label and value. */
export function bar(
  ctx: Ctx,
  x: number,
  y: number,
  w: number,
  h: number,
  p: number,
  color: string,
  opts: { bg?: string; label?: string; value?: string; labelSize?: number; valueColor?: string; radius?: number; alpha?: number } = {},
): void {
  const r = opts.radius ?? 2;
  rect(ctx, x, y, w, h, opts.bg ?? C.line, 0.55, r);
  const bw = w * clamp(p);
  if (bw > 0.5) rect(ctx, x, y, bw, h, color, opts.alpha ?? 1, r);
  if (opts.label) {
    text(ctx, opts.label, x, y - 6, { font: F.mono, size: opts.labelSize ?? 13, color: C.dim, alpha: clamp(p * 2) });
  }
  if (opts.value) {
    text(ctx, opts.value, x + w, y - 6, {
      font: F.monoMed,
      size: opts.labelSize ?? 13,
      color: opts.valueColor ?? C.white,
      align: "right",
      alpha: clamp(p * 2),
    });
  }
}

/** Small uppercase pill. */
export function chip(
  ctx: Ctx,
  x: number,
  y: number,
  label: string,
  color: string,
  p: number,
  opts: { size?: number; filled?: boolean; pad?: number; alpha?: number } = {},
): number {
  const size = opts.size ?? 12;
  const w = measure(ctx, label, size, F.monoMed, 1.4) + (opts.pad ?? 22) * 2;
  const h = size + (opts.pad ?? 22) * 0.9;
  const a = clamp(p) * (opts.alpha ?? 1);
  ctx.save();
  ctx.globalAlpha = a;
  if (opts.filled) rect(ctx, x, y, w, h, color, 0.16, h / 2);
  strokeRect(ctx, x, y, w, h, color, 1, 0.65, h / 2);
  text(ctx, label, x + w / 2, y + h / 2 + size * 0.36, {
    font: F.monoMed,
    size,
    color,
    letter: 1.4,
    align: "center",
  });
  ctx.restore();
  return w;
}

/** Section heading: eyebrow + rule + title. */
export function heading(ctx: Ctx, eyebrow: string, title: string, p: number, accent: string, opts: { y?: number; x?: number; size?: number } = {}): void {
  const y = opts.y ?? 190;
  const x = opts.x ?? 120;
  const a = clamp(p * 1.4);
  text(ctx, eyebrow.toUpperCase(), x, y, { font: F.mono, size: 13, color: accent, letter: 3, alpha: a });
  const tw = measure(ctx, title, opts.size ?? 62, F.displayBold);
  text(ctx, title, x, y + (opts.size ?? 62) * 1.06, {
    font: F.displayBold,
    size: opts.size ?? 62,
    color: C.white,
    alpha: a,
  });
  line(ctx, x, y + (opts.size ?? 62) * 1.06 + 26, x + tw * ease.outQuint(p), y + (opts.size ?? 62) * 1.06 + 26, accent, 2, 0.85);
}

/** Labelled key/value row for evidence tables. */
export function kvRow(
  ctx: Ctx,
  x: number,
  y: number,
  w: number,
  k: string,
  v: string,
  p: number,
  opts: { keyColor?: string; valueColor?: string; size?: number; mono?: boolean; rule?: boolean } = {},
): void {
  const a = clamp(p * 1.5);
  const size = opts.size ?? 15;
  text(ctx, k, x, y, { font: F.mono, size, color: opts.keyColor ?? C.dim2, letter: 0.6, alpha: a });
  text(ctx, v, x + w, y, {
    font: opts.mono === false ? F.bodyMed : F.monoMed,
    size,
    color: opts.valueColor ?? C.white,
    align: "right",
    alpha: a,
    maxWidth: w * 0.66,
  });
  if (opts.rule !== false) line(ctx, x, y + 9, x + w, y + 9, C.line, 1, a * 0.8);
}

export function panelCard(
  ctx: Ctx,
  x: number,
  y: number,
  w: number,
  h: number,
  p: number,
  accent: string,
  opts: { title?: string; body?: string; index?: string; alpha?: number } = {},
): void {
  const a = clamp(p);
  const lift = (1 - ease.outQuint(p)) * 14;
  ctx.save();
  ctx.globalAlpha = a;
  panel(ctx, x, y + lift, w, h, { fill: C.panel, stroke: C.line2, radius: 8, alpha: 0.92 });
  rect(ctx, x, y + lift, 3, h, accent, 0.9);
  if (opts.index) {
    text(ctx, opts.index, x + 24, y + lift + 38, { font: F.monoBold, size: 13, color: accent, letter: 2 });
  }
  if (opts.title) {
    text(ctx, opts.title, x + 24, y + lift + 72, { font: F.displayBold, size: 24, color: C.white, maxWidth: w - 48 });
  }
  if (opts.body) {
    para(ctx, opts.body, x + 24, y + lift + 108, {
      size: 14,
      font: F.body,
      color: C.dim,
      width: w - 48,
      lh: 22,
      maxLines: 5,
    });
  }
  ctx.restore();
}

/** Radial gauge used for entropy and coverage. */
export function gauge(
  ctx: Ctx,
  x: number,
  y: number,
  r: number,
  p: number,
  value: string,
  label: string,
  accent: string,
  opts: { max?: number; size?: number } = {},
): void {
  const a = clamp(p * 1.4);
  ring(ctx, x, y, r, C.line, 6, a * 0.9);
  const end = -Math.PI / 2 + Math.PI * 2 * clamp(p);
  ctx.save();
  ctx.globalAlpha = a;
  ctx.strokeStyle = accent;
  ctx.lineWidth = 6;
  ctx.lineCap = "round";
  ctx.beginPath();
  ctx.arc(x, y, r, -Math.PI / 2, end);
  ctx.stroke();
  ctx.restore();
  text(ctx, value, x, y + (opts.size ?? 22) * 0.4, {
    font: F.displayBold,
    size: opts.size ?? 22,
    color: C.white,
    align: "center",
    alpha: a,
  });
  text(ctx, label, x, y + r + 22, { font: F.mono, size: 10, color: C.dim2, letter: 1.6, align: "center", alpha: a });
}

/** Horizontal stacked composition bar. */
export function stackedBar(
  ctx: Ctx,
  x: number,
  y: number,
  w: number,
  h: number,
  parts: { label: string; value: number; color: string }[],
  p: number,
): void {
  const total = parts.reduce((s, q) => s + q.value, 0) || 1;
  let cx = x;
  const e = ease.outQuint(p);
  for (const part of parts) {
    const pw = (part.value / total) * w * e;
    if (pw > 0.4) rect(ctx, cx, y, pw, h, part.color, 1, 1.5);
    cx += pw;
  }
  strokeRect(ctx, x, y, w, h, C.line2, 1, 0.8, 1.5);
}

/** Dot matrix: one cell per unit, colour by category. Used for class-name length. */
export function dotMatrix(
  ctx: Ctx,
  x: number,
  y: number,
  w: number,
  h: number,
  cols: number,
  values: number[],
  colorOf: (v: number) => string,
  p: number,
  seed = 1,
): void {
  const rows = Math.ceil(values.length / cols);
  const cw = w / cols;
  const ch = h / Math.max(1, rows);
  const s = Math.max(1.2, Math.min(cw, ch) * 0.62);
  const n = Math.floor(values.length * clamp(p * 1.15));
  for (let i = 0; i < n; i++) {
    const cx = x + (i % cols) * cw;
    const cy = y + Math.floor(i / cols) * ch;
    const a = 0.25 + 0.75 * clamp((p * 1.15 * values.length - i) / 80);
    circle(ctx, cx + cw / 2, cy + ch / 2, s / 2, rgba(colorOf(values[i]), a * 0.95));
  }
}

/** Scanline sweep across a region — draws attention without clutter. */
export function scanSweep(ctx: Ctx, x: number, y: number, w: number, h: number, t: number, color: string): void {
  const sy = y + ((t * 0.35) % 1) * h;
  const g = ctx.createLinearGradient(0, sy - 40, 0, sy + 40);
  g.addColorStop(0, rgba(color, 0));
  g.addColorStop(0.5, rgba(color, 0.16));
  g.addColorStop(1, rgba(color, 0));
  ctx.save();
  clipRound(ctx, x, y, w, h, 6);
  ctx.fillStyle = g;
  ctx.fillRect(x, sy - 40, w, 80);
  ctx.restore();
}

/** Typewriter reveal, mono. */
export function typewriter(ctx: Ctx, s: string, x: number, y: number, p: number, opts: { size?: number; color?: string; font?: string; alpha?: number; cps?: number; letter?: number } = {}): number {
  const n = Math.floor(s.length * clamp(p));
  const shown = s.slice(0, n);
  text(ctx, shown, x, y, {
    size: opts.size ?? 18,
    font: opts.font ?? F.mono,
    color: opts.color ?? C.white,
    alpha: opts.alpha,
    letter: opts.letter,
  });
  if (n < s.length) {
    const w = measure(ctx, shown, opts.size ?? 18, opts.font ?? F.mono, opts.letter ?? 0);
    rect(ctx, x + w + 3, y - (opts.size ?? 18) * 0.78, 8, (opts.size ?? 18), C.green, 0.85);
  }
  return n;
}

/** Rounded treemap layout (squarified) for file-mass compositions. */
export function squarify(items: { label: string; value: number; color: string }[], x: number, y: number, w: number, h: number) {
  const total = items.reduce((s, i) => s + i.value, 0) || 1;
  const out: { label: string; value: number; color: string; x: number; y: number; w: number; h: number }[] = [];
  let cx = x;
  let cy = y;
  let cw = w;
  let ch = h;
  let rest = [...items].sort((a, b) => b.value - a.value);
  while (rest.length) {
    const horizontal = cw >= ch;
    const take = rest.slice(0, Math.max(1, Math.ceil(rest.length / 2)));
    const sum = take.reduce((s, i) => s + i.value, 0);
    if (horizontal) {
      const bw = (sum / total) * cw;
      let iy = cy;
      for (const it of take) {
        const ih = (it.value / sum) * ch;
        out.push({ ...it, x: cx, y: iy, w: bw, h: ih });
        iy += ih;
      }
      cx += bw;
      cw -= bw;
    } else {
      const bh = (sum / total) * ch;
      let ix = cx;
      for (const it of take) {
        const iw = (it.value / sum) * cw;
        out.push({ ...it, x: ix, y: cy, w: iw, h: bh });
        ix += iw;
      }
      cy += bh;
      ch -= bh;
    }
    rest = rest.slice(take.length);
  }
  return out;
}
