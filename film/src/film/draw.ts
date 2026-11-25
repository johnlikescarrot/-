/** Primitive drawing helpers on top of @napi-rs/canvas. */
import type { SKRSContext2D } from "@napi-rs/canvas";
import { C, F } from "./theme.js";
import { clamp, rgba } from "./util.js";

export type Ctx = SKRSContext2D;

export function text(
  ctx: Ctx,
  s: string,
  x: number,
  y: number,
  opts: {
    font?: string;
    size?: number;
    color?: string;
    align?: "left" | "center" | "right";
    baseline?: "alphabetic" | "top" | "middle" | "bottom" | "ideographic" | "hanging";
    alpha?: number;
    letter?: number;
    maxWidth?: number;
  } = {},
): number {
  const size = opts.size ?? 24;
  const family = opts.font ?? F.body;
  ctx.save();
  ctx.globalAlpha = clamp(opts.alpha ?? 1);
  ctx.font = `${size}px ${family}`;
  ctx.fillStyle = opts.color ?? C.white;
  ctx.textAlign = opts.align ?? "left";
  ctx.textBaseline = opts.baseline ?? "alphabetic";
  if (opts.letter && opts.letter !== 0) {
    drawSpaced(ctx, s, x, y, opts.letter, opts.align ?? "left", !!opts.maxWidth, opts.maxWidth);
  } else if (opts.maxWidth) {
    ctx.fillText(s, x, y, opts.maxWidth);
  } else {
    ctx.fillText(s, x, y);
  }
  ctx.restore();
  return ctx.measureText(s).width;
}

function drawSpaced(
  ctx: Ctx,
  s: string,
  x: number,
  y: number,
  letter: number,
  align: "left" | "center" | "right",
  _clip: boolean,
  maxWidth?: number,
) {
  const widths = [...s].map((ch) => ctx.measureText(ch).width);
  const total = widths.reduce((a, b) => a + b, 0) + letter * Math.max(0, widths.length - 1);
  if (maxWidth && total > maxWidth) {
    ctx.fillText(s, x, y, maxWidth);
    return;
  }
  let cx = align === "center" ? x - total / 2 : align === "right" ? x - total : x;
  const prevAlign = ctx.textAlign;
  ctx.textAlign = "left";
  [...s].forEach((ch, i) => {
    ctx.fillText(ch, cx, y);
    cx += widths[i] + letter;
  });
  ctx.textAlign = prevAlign;
}

export function measure(ctx: Ctx, s: string, size: number, family = F.body, letter = 0): number {
  ctx.save();
  ctx.font = `${size}px ${family}`;
  const w = ctx.measureText(s).width + letter * Math.max(0, s.length - 1);
  ctx.restore();
  return w;
}

/** Word-wrap into at most `maxLines` lines, eliding with an ellipsis. */
export function wrap(ctx: Ctx, s: string, maxWidth: number, maxLines = 99): string[] {
  const words = s.split(/\s+/);
  const lines: string[] = [];
  let cur = "";
  for (const w of words) {
    const test = cur ? cur + " " + w : w;
    if (ctx.measureText(test).width <= maxWidth || !cur) cur = test;
    else {
      lines.push(cur);
      cur = w;
      if (lines.length === maxLines) break;
    }
  }
  if (lines.length < maxLines && cur) lines.push(cur);
  if (lines.length === maxLines) {
    const joined = lines.join(" ");
    if (joined.length < s.length) {
      let last = lines[maxLines - 1];
      while (ctx.measureText(last + "…").width > maxWidth && last.length > 1) last = last.slice(0, -1);
      lines[maxLines - 1] = last + "…";
    }
  }
  return lines;
}

export function para(
  ctx: Ctx,
  s: string,
  x: number,
  y: number,
  opts: { size?: number; font?: string; color?: string; width: number; lh?: number; alpha?: number; maxLines?: number; align?: "left" | "center" | "right" },
): number {
  const size = opts.size ?? 26;
  ctx.save();
  ctx.font = `${size}px ${opts.font ?? F.body}`;
  const lines = wrap(ctx, s, opts.width, opts.maxLines ?? 99);
  ctx.restore();
  const lh = opts.lh ?? size * 1.5;
  lines.forEach((l, i) => {
    text(ctx, l, opts.align === "center" ? x + opts.width / 2 : x, y + i * lh, {
      size,
      font: opts.font,
      color: opts.color,
      alpha: opts.alpha,
      align: opts.align,
    });
  });
  return lines.length * lh;
}

export function line(ctx: Ctx, x1: number, y1: number, x2: number, y2: number, color: string = C.line, width = 1, alpha = 1) {
  ctx.save();
  ctx.globalAlpha = clamp(alpha);
  ctx.strokeStyle = color;
  ctx.lineWidth = width;
  ctx.beginPath();
  ctx.moveTo(x1, y1);
  ctx.lineTo(x2, y2);
  ctx.stroke();
  ctx.restore();
}

export function rect(ctx: Ctx, x: number, y: number, w: number, h: number, fill?: string, alpha = 1, radius = 0) {
  ctx.save();
  ctx.globalAlpha = clamp(alpha);
  if (radius > 0) roundRect(ctx, x, y, w, h, radius);
  else {
    ctx.beginPath();
    ctx.rect(x, y, w, h);
  }
  if (fill) {
    ctx.fillStyle = fill;
    ctx.fill();
  }
  ctx.restore();
}

export function strokeRect(ctx: Ctx, x: number, y: number, w: number, h: number, color: string, width = 1, alpha = 1, radius = 0) {
  ctx.save();
  ctx.globalAlpha = clamp(alpha);
  ctx.strokeStyle = color;
  ctx.lineWidth = width;
  if (radius > 0) roundRect(ctx, x + 0.5, y + 0.5, w - 1, h - 1, radius);
  else {
    ctx.beginPath();
    ctx.rect(x + 0.5, y + 0.5, w - 1, h - 1);
  }
  ctx.stroke();
  ctx.restore();
}

export function roundRect(ctx: Ctx, x: number, y: number, w: number, h: number, r: number) {
  const rr = Math.min(r, w / 2, h / 2);
  ctx.beginPath();
  ctx.moveTo(x + rr, y);
  ctx.arcTo(x + w, y, x + w, y + h, rr);
  ctx.arcTo(x + w, y + h, x, y + h, rr);
  ctx.arcTo(x, y + h, x, y, rr);
  ctx.arcTo(x, y, x + w, y, rr);
  ctx.closePath();
}

export function circle(ctx: Ctx, x: number, y: number, r: number, fill?: string, alpha = 1) {
  ctx.save();
  ctx.globalAlpha = clamp(alpha);
  ctx.beginPath();
  ctx.arc(x, y, Math.max(0, r), 0, Math.PI * 2);
  if (fill) {
    ctx.fillStyle = fill;
    ctx.fill();
  }
  ctx.restore();
}

export function ring(ctx: Ctx, x: number, y: number, r: number, color: string, width = 2, alpha = 1, a0 = 0, a1 = Math.PI * 2) {
  ctx.save();
  ctx.globalAlpha = clamp(alpha);
  ctx.strokeStyle = color;
  ctx.lineWidth = width;
  ctx.beginPath();
  ctx.arc(x, y, Math.max(0, r), a0, a1);
  ctx.stroke();
  ctx.restore();
}

/** Radial glow used behind key numbers. */
export function glow(ctx: Ctx, x: number, y: number, r: number, color: string, alpha = 0.5) {
  const g = ctx.createRadialGradient(x, y, 0, x, y, r);
  g.addColorStop(0, rgba(color, alpha));
  g.addColorStop(0.45, rgba(color, alpha * 0.28));
  g.addColorStop(1, rgba(color, 0));
  ctx.save();
  ctx.fillStyle = g;
  ctx.fillRect(x - r, y - r, r * 2, r * 2);
  ctx.restore();
}

export function vignette(ctx: Ctx, w: number, h: number, strength = 0.55) {
  const g = ctx.createRadialGradient(w / 2, h / 2, Math.min(w, h) * 0.32, w / 2, h / 2, Math.max(w, h) * 0.78);
  g.addColorStop(0, "rgba(0,0,0,0)");
  g.addColorStop(1, `rgba(0,0,0,${strength})`);
  ctx.save();
  ctx.fillStyle = g;
  ctx.fillRect(0, 0, w, h);
  ctx.restore();
}

/** Fine procedural grain — sells the "instrument capture" texture. */
export function grain(ctx: Ctx, w: number, h: number, alpha = 0.035, seed = 0) {
  ctx.save();
  ctx.globalAlpha = alpha;
  const step = 3;
  for (let y = 0; y < h; y += step) {
    for (let x = 0; x < w; x += step) {
      const n = Math.sin((x * 12.9898 + y * 78.233 + seed * 3.1) * 43758.5453) % 1;
      if (n > 0.82) {
        ctx.fillStyle = n > 0.94 ? "#ffffff" : "#000000";
        ctx.fillRect(x, y, 1, 1);
      }
    }
  }
  ctx.restore();
}

/** Corner registration marks — repeated in every scene for continuity. */
export function frameMarks(ctx: Ctx, w: number, h: number, color: string = C.line2, alpha = 0.5, inset = 44, len = 26) {
  const pts: [number, number, number, number][] = [
    [inset, inset, 1, 1],
    [w - inset, inset, -1, 1],
    [inset, h - inset, 1, -1],
    [w - inset, h - inset, -1, -1],
  ];
  for (const [x, y, dx, dy] of pts) {
    line(ctx, x, y, x + len * dx, y, color, 1, alpha);
    line(ctx, x, y, x, y + len * dy, color, 1, alpha);
  }
}

/** 12-column layout guides, faint. */
export function gridOverlay(ctx: Ctx, w: number, h: number, margin = 120, cols = 12, alpha = 0.035) {
  const cw = (w - margin * 2) / cols;
  ctx.save();
  ctx.globalAlpha = alpha;
  ctx.strokeStyle = C.white;
  ctx.lineWidth = 1;
  for (let i = 0; i <= cols; i++) {
    const x = Math.round(margin + i * cw) + 0.5;
    ctx.beginPath();
    ctx.moveTo(x, margin);
    ctx.lineTo(x, h - margin);
    ctx.stroke();
  }
  ctx.restore();
}

export function hexToRgba(hex: string, a: number): string {
  return rgba(hex, a);
}

/** Clip a rounded panel and run a draw callback inside it. */
export function panel(ctx: Ctx, x: number, y: number, w: number, h: number, opts: { fill?: string; stroke?: string; radius?: number; alpha?: number; strokeAlpha?: number } = {}) {
  const r = opts.radius ?? 10;
  rect(ctx, x, y, w, h, opts.fill ?? C.panel, opts.alpha ?? 1, r);
  if (opts.stroke) strokeRect(ctx, x, y, w, h, opts.stroke, 1, opts.strokeAlpha ?? 1, r);
}

export function clipRound(ctx: Ctx, x: number, y: number, w: number, h: number, r: number) {
  ctx.save();
  roundRect(ctx, x, y, w, h, r);
  ctx.clip();
}

/**
 * Filled surface with a subtle vertical gradient and a hairline top highlight —
 * the standard "instrument card" used across the film. Keeps dark UI legible on
 * an almost-black plate.
 */
export function card(
  ctx: Ctx,
  x: number,
  y: number,
  w: number,
  h: number,
  opts: { accent?: string; radius?: number; alpha?: number; top?: string; bottom?: string } = {},
): void {
  const r = opts.radius ?? 8;
  const a = clamp(opts.alpha ?? 1);
  const g = ctx.createLinearGradient(x, y, x, y + h);
  g.addColorStop(0, opts.top ?? "#182437");
  g.addColorStop(1, opts.bottom ?? "#0e1622");
  ctx.save();
  ctx.globalAlpha = a;
  roundRect(ctx, x, y, w, h, r);
  ctx.fillStyle = g;
  ctx.fill();
  ctx.strokeStyle = "rgba(120,150,190,0.16)";
  ctx.lineWidth = 1;
  ctx.stroke();
  // top highlight
  ctx.beginPath();
  ctx.moveTo(x + r, y + 1);
  ctx.lineTo(x + w - r, y + 1);
  ctx.strokeStyle = "rgba(190,215,245,0.12)";
  ctx.stroke();
  ctx.restore();
  if (opts.accent) rect(ctx, x, y, 3, h, opts.accent, 0.95 * a, 2);
}
