/** Small drawing/utility library shared by all scenes. */
import type { SKRSContext2D } from '@napi-rs/canvas';

type CanvasTextAlign = 'left' | 'right' | 'center' | 'start' | 'end';
type CanvasTextBaseline = 'top' | 'hanging' | 'middle' | 'alphabetic' | 'ideographic' | 'bottom';
import { C, F, H, LAYOUT, W, type Color } from './theme.js';

export type Ctx = SKRSContext2D;

export const clamp = (v: number, a = 0, b = 1): number => Math.min(b, Math.max(a, v));
export const lerp = (a: number, b: number, t: number): number => a + (b - a) * t;
export const seg = (t: number, a: number, b: number): number => clamp((t - a) / (b - a));
export const ease = {
  outCubic: (t: number): number => 1 - (1 - t) ** 3,
  inOutCubic: (t: number): number => (t < 0.5 ? 4 * t ** 3 : 1 - (-2 * t + 2) ** 3 / 2),
  outExpo: (t: number): number => (t >= 1 ? 1 : 1 - 2 ** (-10 * t)),
  outQuint: (t: number): number => 1 - (1 - t) ** 5,
};
export const wave = (t: number, p = 1): number => (1 - Math.cos(t * Math.PI * 2 * p)) / 2;

/** Deterministic PRNG so every render is byte-identical. */
export function rng(seed: number): () => number {
  let s = seed >>> 0 || 1;
  return () => {
    s ^= s << 13; s >>>= 0;
    s ^= s >>> 17;
    s ^= s << 5; s >>>= 0;
    return s / 4294967296;
  };
}

export function alpha(hex: string, a: number): string {
  if (hex.startsWith('rgba') || hex.startsWith('rgb')) return hex;
  const n = parseInt(hex.slice(1), 16);
  const r = (n >> 16) & 255, g = (n >> 8) & 255, b = n & 255;
  return `rgba(${r},${g},${b},${a})`;
}

export function roundRect(ctx: Ctx, x: number, y: number, w: number, h: number, r: number): void {
  const rr = Math.min(r, w / 2, h / 2);
  ctx.beginPath();
  ctx.moveTo(x + rr, y);
  ctx.arcTo(x + w, y, x + w, y + h, rr);
  ctx.arcTo(x + w, y + h, x, y + h, rr);
  ctx.arcTo(x, y + h, x, y, rr);
  ctx.arcTo(x, y, x + w, y, rr);
  ctx.closePath();
}

export function panel(ctx: Ctx, x: number, y: number, w: number, h: number, opts: { r?: number; fill?: string; edge?: string; glow?: string } = {}): void {
  const { r = 18, fill = C.panel as string, edge = C.panelEdge as string, glow } = opts;
  ctx.save();
  if (glow) {
    ctx.shadowColor = glow;
    ctx.shadowBlur = 28;
  }
  roundRect(ctx, x, y, w, h, r);
  ctx.fillStyle = fill;
  ctx.fill();
  ctx.restore();
  roundRect(ctx, x, y, w, h, r);
  ctx.strokeStyle = edge;
  ctx.lineWidth = 1;
  ctx.stroke();
}

export function text(ctx: Ctx, s: string, x: number, y: number, opts: {
  font?: string; fill?: string; align?: CanvasTextAlign; baseline?: CanvasTextBaseline;
  maxWidth?: number; spacing?: number; alpha?: number;
} = {}): void {
  const { font = F.sans(28), fill = C.text, align = 'left', baseline = 'alphabetic', maxWidth, spacing } = opts;
  ctx.save();
  ctx.globalAlpha = opts.alpha ?? 1;
  ctx.font = font;
  ctx.fillStyle = fill;
  ctx.textAlign = align;
  ctx.textBaseline = baseline;
  if (spacing && 'letterSpacing' in ctx) (ctx as unknown as { letterSpacing: string }).letterSpacing = `${spacing}px`;
  if (maxWidth) ctx.fillText(s, x, y, maxWidth); else ctx.fillText(s, x, y);
  ctx.restore();
}

export function wrap(ctx: Ctx, s: string, maxWidth: number, font: string): string[] {
  ctx.save();
  ctx.font = font;
  const words = s.split(/\s+/);
  const lines: string[] = [];
  let line = '';
  for (const word of words) {
    const test = line ? `${line} ${word}` : word;
    if (ctx.measureText(test).width > maxWidth && line) {
      lines.push(line);
      line = word;
    } else line = test;
  }
  if (line) lines.push(line);
  ctx.restore();
  return lines;
}

export function mono(ctx: Ctx, s: string, x: number, y: number, px = 18, fill: string = C.dim, align: CanvasTextAlign = 'left'): void {
  text(ctx, s, x, y, { font: F.mono(px), fill, align });
}

/** Frame chrome: header rule, chapter marker, footer with timecode + citation line. */
export function chrome(ctx: Ctx, t: number, total: number, ch: { index: number; title: string; kicker: string }): void {
  const g = ctx.createLinearGradient(0, 0, W, 0);
  g.addColorStop(0, C.bg);
  g.addColorStop(0.5, C.bg2);
  g.addColorStop(1, C.bg);
  ctx.fillStyle = g;
  ctx.fillRect(0, 0, W, H);

  // faint grid
  ctx.save();
  ctx.strokeStyle = C.hair;
  ctx.globalAlpha = 0.5;
  ctx.lineWidth = 1;
  for (let x = 0; x <= W; x += 80) {
    ctx.beginPath(); ctx.moveTo(x, 0); ctx.lineTo(x, H); ctx.stroke();
  }
  for (let y = 0; y <= H; y += 80) {
    ctx.beginPath(); ctx.moveTo(0, y); ctx.lineTo(W, y); ctx.stroke();
  }
  ctx.restore();

  // top accent
  const accent = ctx.createLinearGradient(0, 0, W, 0);
  accent.addColorStop(0, 'rgba(245,196,81,0)');
  accent.addColorStop(0.15, 'rgba(245,196,81,0.55)');
  accent.addColorStop(0.85, 'rgba(56,189,248,0.55)');
  accent.addColorStop(1, 'rgba(56,189,248,0)');
  ctx.fillStyle = accent;
  ctx.fillRect(0, 0, W, 2);

  // header: chapter index marker
  ctx.save();
  ctx.font = F.mono(20);
  ctx.fillStyle = C.amber;
  ctx.textBaseline = 'middle';
  ctx.fillText(`§${String(ch.index).padStart(2, '0')}`, LAYOUT.margin, LAYOUT.headerY);
  ctx.fillStyle = C.dim;
  ctx.fillText(ch.kicker.toUpperCase(), LAYOUT.margin + 64, LAYOUT.headerY);
  ctx.textAlign = 'right';
  ctx.fillStyle = C.faint;
  ctx.fillText('ZALOPAY 11.17.0 · STATIC ANATOMY', W - LAYOUT.margin, LAYOUT.headerY);
  ctx.restore();

  ctx.strokeStyle = C.hair;
  ctx.beginPath();
  ctx.moveTo(LAYOUT.margin, 108);
  ctx.lineTo(W - LAYOUT.margin, 108);
  ctx.stroke();

  // footer
  ctx.save();
  ctx.font = F.mono(18);
  ctx.fillStyle = C.faint;
  ctx.textBaseline = 'middle';
  ctx.fillText(ch.title, LAYOUT.margin, LAYOUT.footerY);
  ctx.textAlign = 'right';
  const mm = (n: number): string => `${Math.floor(n / 60)}:${String(Math.floor(n % 60)).padStart(2, '0')}`;
  ctx.fillText(`${mm(t)} / ${mm(total)}`, W - LAYOUT.margin, LAYOUT.footerY);
  ctx.restore();
  ctx.strokeStyle = C.hair;
  ctx.beginPath(); ctx.moveTo(LAYOUT.margin, H - 92); ctx.lineTo(W - LAYOUT.margin, H - 92); ctx.stroke();
}

/** Big chapter number / title block used at the start of each act. */
export function chapterCard(ctx: Ctx, t: number, idx: number, kicker: string, title: string, sub: string): void {
  const k = ease.outCubic(seg(t, 0, 0.55));
  const color = [C.amber, C.cyan, C.teal, C.violet, C.magenta][idx % 5] as string;
  ctx.save();
  ctx.globalAlpha = 1 - seg(t, 3.1, 3.9);
  // giant index, right-aligned watermark
  ctx.font = F.sans(360, 700);
  ctx.fillStyle = alpha(color, 0.08);
  ctx.textBaseline = 'alphabetic';
  ctx.textAlign = 'right';
  ctx.fillText(String(idx).padStart(2, '0'), W - LAYOUT.margin, 820 + (1 - k) * 40);
  ctx.textAlign = 'left';
  // kicker
  text(ctx, kicker.toUpperCase(), LAYOUT.margin + 4, 300, { font: F.mono(24), fill: color, spacing: 3 });
  ctx.fillStyle = color;
  ctx.fillRect(LAYOUT.margin, 322, 96 * k, 3);
  // title lines
  const lines = wrap(ctx, title, 1180, F.sans(86, 700));
  lines.forEach((ln, i) => {
    const p = ease.outCubic(seg(t, 0.25 + i * 0.12, 0.9 + i * 0.12));
    text(ctx, ln, LAYOUT.margin, 430 + i * 98 - (1 - p) * 18, { font: F.sans(86, 700), fill: C.text, alpha: p });
  });
  const p2 = ease.outCubic(seg(t, 0.7, 1.4));
  const subLines = wrap(ctx, sub, 1080, F.sans(30));
  subLines.forEach((ln, i) => text(ctx, ln, LAYOUT.margin + 4, 430 + lines.length * 98 + 40 + i * 44,
    { font: F.sans(30), fill: C.dim, alpha: p2 }));
  ctx.restore();
}

/** Statistic readout with a counting animation. */
export function stat(ctx: Ctx, x: number, y: number, label: string, value: number, dur: number, t: number, opts: { fmt?: (n: number) => string; color?: string; size?: number; suffix?: string } = {}): void {
  const p = ease.outExpo(seg(t, 0, dur));
  const f = opts.fmt ?? ((n: number) => n.toLocaleString('en-US'));
  text(ctx, f(Math.round(value * p)), x, y, { font: F.sans(opts.size ?? 74, 700), fill: opts.color ?? C.text });
  if (opts.suffix) text(ctx, opts.suffix, x, y + 34, { font: F.sans(22), fill: C.dim });
  text(ctx, label.toUpperCase(), x, y + 42, { font: F.mono(17), fill: C.faint });
}

/** Progress underline used under titles. */
export function underline(ctx: Ctx, x: number, y: number, w: number, p: number, color: string = C.amber): void {
  ctx.fillStyle = alpha(color, 0.18);
  ctx.fillRect(x, y, w, 2);
  ctx.fillStyle = color;
  ctx.fillRect(x, y, w * p, 2);
}

export function badge(ctx: Ctx, s: string, x: number, y: number, color: string = C.cyan): number {
  ctx.save();
  ctx.font = F.mono(16);
  const w = ctx.measureText(s).width + 26;
  roundRect(ctx, x, y - 15, w, 30, 15);
  ctx.fillStyle = alpha(color, 0.12);
  ctx.fill();
  ctx.strokeStyle = alpha(color, 0.45);
  ctx.stroke();
  ctx.fillStyle = color;
  ctx.textBaseline = 'middle';
  ctx.fillText(s, x + 13, y + 1);
  ctx.restore();
  return w;
}

export function dot(ctx: Ctx, x: number, y: number, r: number, color: string, a = 1): void {
  ctx.save();
  ctx.globalAlpha = a;
  ctx.fillStyle = color;
  ctx.beginPath();
  ctx.arc(x, y, r, 0, Math.PI * 2);
  ctx.fill();
  ctx.restore();
}

/** Vertical bar chart with per-bar easing. */
export function bars(ctx: Ctx, x: number, y: number, w: number, h: number, values: number[], t: number, opts: { color?: string; gap?: number; min?: number } = {}): void {
  const max = Math.max(...values, 1);
  const gap = opts.gap ?? 6;
  const bw = (w - gap * (values.length - 1)) / values.length;
  values.forEach((v, i) => {
    const p = ease.outCubic(seg(t, i * 0.012, 0.6 + i * 0.012));
    const bh = (v / max) * h * p;
    ctx.fillStyle = alpha(opts.color ?? C.cyan, 0.85);
    ctx.fillRect(x + i * (bw + gap), y + h - bh, bw, bh);
  });
}

export function code(ctx: Ctx, s: string, x: number, y: number, px = 17, color: string = C.dim): void {
  text(ctx, s, x, y, { font: F.mono(px), fill: color });
}
