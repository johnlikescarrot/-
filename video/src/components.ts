/** Reusable scene furniture: chrome, stat blocks, charts, tables, code panels. */

import { C, FONT, GRID, H, Svg, T, W, clamp01, ease, fmtInt, ramp } from "./theme.ts";

export interface ChromeOpts {
  chapter: string;
  title: string;
  t: number;
  dur: number;
  source?: string;
  index?: string;
}

/** Persistent letterbox furniture: kicker, chapter, progress, source line. */
export function chrome(s: Svg, o: ChromeOpts): void {
  const appear = ramp(o.t, 0.05, 0.5);
  // top hairline
  s.line(GRID.margin, 58, W - GRID.margin, 58, C.line, 1, 0.7 * appear);
  s.text(GRID.margin, 44, o.chapter.toUpperCase(), {
    size: T.micro, family: FONT.mono, fill: C.brandHi, letter: 3.4, opacity: 0.95 * appear,
  });
  s.text(W - GRID.margin, 44, o.title, {
    size: T.micro, family: FONT.mono, fill: C.ink3, letter: 1.6, anchor: "end", opacity: appear,
  });

  // bottom: progress + timecode
  const p = clamp01(o.t / Math.max(0.001, o.dur));
  s.rect(GRID.margin, H - 52, W - GRID.margin * 2, 2, C.line, { opacity: 0.8 });
  s.rect(GRID.margin, H - 52, (W - GRID.margin * 2) * p, 2, C.brandHi, { opacity: 0.9 });
  const mm = Math.floor(o.dur / 60);
  const ss = Math.floor(o.dur % 60);
  s.text(GRID.margin, H - 26, `${o.index ?? "00"} · ${mm}:${ss.toString().padStart(2, "0")}`, {
    size: T.micro, family: FONT.mono, fill: C.ink3, letter: 1.2, opacity: 0.9,
  });
  if (o.source) {
    s.text(W - GRID.margin, H - 26, o.source, {
      size: T.micro, family: FONT.mono, fill: C.ink3, anchor: "end", opacity: 0.75,
    });
  }
}

export function kicker(s: Svg, x: number, y: number, label: string, color = C.brandHi, opacity = 1): void {
  s.rect(x, y - 9, 26, 2, color, { opacity });
  s.text(x + 36, y, label.toUpperCase(), {
    size: T.micro, family: FONT.mono, fill: color, letter: 3, opacity,
  });
}

export function headline(
  s: Svg,
  x: number,
  y: number,
  txt: string,
  o: { size?: number; fill?: string; weight?: number; opacity?: number; max?: number } = {},
): void {
  s.text(x, y, txt, {
    size: o.size ?? T.h1,
    weight: o.weight ?? 800,
    fill: o.fill ?? C.ink,
    opacity: o.opacity,
    lineHeight: (o.size ?? T.h1) * 1.08,
    clip: o.max ? { x: x - 4, y: y - (o.size ?? T.h1) * 1.05, w: o.max, h: (o.size ?? T.h1) * 2.4 } : undefined,
  });
}

/** Panel with a subtle top accent and optional index label. */
export function panel(
  s: Svg,
  b: { x: number; y: number; w: number; h: number },
  o: { label?: string; accent?: string; opacity?: number; hi?: boolean } = {},
): void {
  const op = o.opacity ?? 1;
  s.rect(b.x, b.y, b.w, b.h, o.hi ? C.panelHi : C.panel, {
    r: 14, stroke: C.line, sw: 1.2, opacity: 0.92 * op,
  });
  if (o.accent) s.rect(b.x + 1, b.y + 18, 3, b.h - 36, o.accent, { opacity: 0.95 * op });
  if (o.label) {
    s.text(b.x + 24, b.y + 34, o.label.toUpperCase(), {
      size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2.6, opacity: op,
    });
  }
}

/** Large number with unit and caption; counts up on entry. */
export function bigStat(
  s: Svg,
  x: number,
  y: number,
  value: number,
  o: {
    label: string;
    suffix?: string;
    color?: string;
    t?: number;
    size?: number;
    decimals?: number;
    note?: string;
    mono?: boolean;
  } = { label: "" },
): void {
  const k = ease.outExpo(clamp01(o.t ?? 1));
  const v = value * k;
  const shown = o.decimals ? v.toFixed(o.decimals) : fmtInt(Math.round(v));
  s.text(x, y, shown, {
    size: o.size ?? 96,
    weight: 800,
    fill: o.color ?? C.ink,
    family: o.mono ? FONT.mono : FONT.sans,
    letter: -1.5,
  });
  if (o.suffix) {
    const wx = s.measureApprox(shown, o.size ?? 96, o.mono);
    s.text(x + wx + 10, y, o.suffix, {
      size: (o.size ?? 96) * 0.36, weight: 600, fill: o.color ?? C.ink2, opacity: 0.9,
    });
  }
  s.text(x, y + 30, o.label, { size: T.small, fill: C.ink2, opacity: 0.95 });
  if (o.note) s.text(x, y + 54, o.note, { size: T.micro, family: FONT.mono, fill: C.ink3 });
}

/** Animated horizontal bar with label, value and optional secondary track. */
export function barRow(
  s: Svg,
  x: number,
  y: number,
  w: number,
  o: { label: string; value: number; max: number; t: number; color?: string; fmt?: string; sub?: string },
): void {
  const k = ease.outQuint(clamp01(o.t));
  const bw = Math.max(2, (w - 260) * (o.value / o.max) * k);
  s.text(x, y + 15, o.label, { size: T.small, fill: C.ink2 });
  s.rect(x + 250, y + 2, w - 260, 12, C.line, { r: 6, opacity: 0.65 });
  s.rect(x + 250, y + 2, bw, 12, o.color ?? C.brand, { r: 6 });
  s.text(x + w, y + 14, o.fmt ?? fmtInt(o.value), {
    size: T.small, family: FONT.mono, fill: C.ink, anchor: "end", opacity: 0.95,
  });
  if (o.sub) s.text(x + 250, y + 34, o.sub, { size: T.micro, family: FONT.mono, fill: C.ink3 });
}

/** Horizontal stacked bar used for byte-composition scenes. */
export function stackedBar(
  s: Svg,
  x: number,
  y: number,
  w: number,
  h: number,
  parts: { label: string; value: number; color: string }[],
  t: number,
  gap = 3,
): void {
  const total = parts.reduce((a, p) => a + p.value, 0) || 1;
  let cx = x;
  parts.forEach((p, i) => {
    const k = ease.outQuint(clamp01((t - i * 0.07) / 0.75));
    const pw = Math.max(0, ((w - gap * (parts.length - 1)) * p.value) / total) * k;
    if (pw > 0.5) s.rect(cx, y, pw, h, p.color, { r: Math.min(6, h / 2), opacity: 0.95 });
    cx += pw + gap * k;
  });
}

/** Wrapped body copy with citation-safe width. */
export function para(
  s: Svg,
  x: number,
  y: number,
  txt: string,
  o: { w: number; size?: number; fill?: string; opacity?: number; lineHeight?: number; weight?: number } = { w: 700 },
): void {
  const size = o.size ?? T.lead;
  const lh = o.lineHeight ?? size * 1.42;
  const lines = wrap(txt, o.w, size);
  lines.forEach((ln, i) => {
    s.text(x, y + i * lh, ln, { size, fill: o.fill ?? C.ink2, opacity: o.opacity, weight: o.weight ?? 400 });
  });
}

/** Greedy wrap tuned for the Be Vietnam Pro metrics used here. */
export function wrap(txt: string, w: number, size: number): string[] {
  const perChar = size * 0.505;
  const max = Math.max(8, Math.floor(w / perChar));
  const out: string[] = [];
  for (const hard of txt.split("\n")) {
    let line = "";
    for (const word of hard.split(" ")) {
      if ((line + " " + word).trim().length <= max) line = (line + " " + word).trim();
      else {
        if (line) out.push(line);
        line = word;
      }
    }
    if (line) out.push(line);
  }
  return out;
}

/** Monospace evidence block with a filename tab and optional highlighted lines. */
export function codeBlock(
  s: Svg,
  b: { x: number; y: number; w: number; h: number },
  o: { title: string; lines: string[]; t: number; perLine?: number; hi?: number[]; color?: string },
): void {
  s.rect(b.x, b.y, b.w, b.h, "#080D18", { r: 12, stroke: C.line, sw: 1.2 });
  s.rect(b.x, b.y, b.w, 38, "#0D1526", { r: 12 });
  s.rect(b.x, b.y + 26, b.w, 12, "#0D1526");
  [C.coral, C.amber, C.lime].forEach((c, i) => s.circle(b.x + 22 + i * 18, b.y + 19, 5, c, { opacity: 0.8 }));
  s.text(b.x + 86, b.y + 25, o.title, { size: T.micro, family: FONT.mono, fill: C.ink3, letter: 1.2 });

  const size = T.mono;
  const lh = 27;
  o.lines.forEach((ln, i) => {
    const app = clamp01((o.t - (o.perLine ?? 0.06) * i) / 0.24);
    if (app <= 0.02) return;
    const y = b.y + 68 + i * lh;
    if (y > b.y + b.h - 16) return;
    const isHi = o.hi?.includes(i);
    if (isHi) s.rect(b.x + 8, y - 18, b.w - 16, lh - 2, o.color ?? C.brand, { r: 5, opacity: 0.14 });
    s.text(b.x + 54, y, ln, {
      size, family: FONT.mono, fill: isHi ? C.ink : C.ink2, opacity: app,
    });
    s.text(b.x + 30, y, String(i + 1).padStart(2, "0"), {
      size: T.monoSm, family: FONT.mono, fill: C.ink3, anchor: "end", opacity: 0.6 * app,
    });
  });
}

/** Two-column comparison of claims vs. limits — the honesty device of the film. */
export function claimPair(
  s: Svg,
  x: number,
  y: number,
  w: number,
  o: { claim: string; limit: string; t: number; accent?: string },
): void {
  const k = ramp(o.t, 0, 0.5);
  s.rect(x, y, 3, 76, o.accent ?? C.cyan, { opacity: 0.9 * k });
  s.text(x + 24, y + 12, "CLAIM", { size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2.4, opacity: k });
  const cl = wrap(o.claim, w - 40, T.body);
  cl.slice(0, 2).forEach((ln, i) => s.text(x + 24, y + 42 + i * 28, ln, { size: T.body, fill: C.ink, opacity: k }));
  const y2 = y + 42 + cl.length * 28 + 12;
  s.text(x + 24, y2, "LIMIT  ", { size: T.micro, family: FONT.mono, fill: C.amber, letter: 2.4, opacity: k });
  const li = wrap(o.limit, w - 40, T.small);
  li.slice(0, 2).forEach((ln, i) =>
    s.text(x + 24 + 62, y2 + i * 24, ln, { size: T.small, fill: C.ink3, opacity: k }),
  );
}

/** Thin ring gauge (0..1) used for percentages. */
export function ring(
  s: Svg,
  cx: number,
  cy: number,
  r: number,
  frac: number,
  o: { t?: number; color?: string; track?: string; sw?: number; label?: string; value?: string } = {},
): void {
  const k = ease.outQuint(clamp01(o.t ?? 1));
  const sw = o.sw ?? 12;
  const circ = 2 * Math.PI * r;
  s.circle(cx, cy, r, "none", { stroke: o.track ?? C.line, sw });
  const sweep = circ * clamp01(frac * k);
  s.raw(
    `<circle cx="${cx}" cy="${cy}" r="${r}" fill="none" stroke="${o.color ?? C.brandHi}" stroke-width="${sw}" ` +
      `stroke-linecap="round" stroke-dasharray="${sweep.toFixed(1)} ${circ.toFixed(1)}" ` +
      `transform="rotate(-90 ${cx} ${cy})"/>`,
  );
  if (o.value) s.text(cx, cy + 8, o.value, { size: 34, weight: 800, anchor: "middle", fill: C.ink });
  if (o.label) s.text(cx, cy + 34, o.label, { size: T.micro, family: FONT.mono, fill: C.ink3, anchor: "middle" });
}

/** Scene title card used at the head of every chapter. */
export function chapterCard(
  s: Svg,
  t: number,
  o: { index: string; title: string; subtitle: string; accent?: string },
): void {
  const a = ramp(t, 0, 0.55);
  const b = ramp(t, 0.25, 0.9);
  s.text(GRID.margin, 300, o.index, {
    size: 190, weight: 800, fill: "none", opacity: 0.16,
  });
  kicker(s, GRID.margin, 366, "chapter", o.accent ?? C.brandHi, a);
  s.text(GRID.margin, 470, o.title, { size: T.h1, weight: 800, opacity: b, lineHeight: 100 });
  para(s, GRID.margin, 560, o.subtitle, { w: 1080, size: T.lead, fill: C.ink2, opacity: b });
}

// Approximate advance width so suffixes do not collide with big numbers.
declare module "./theme.ts" {
  interface Svg {
    measureApprox(text: string, size: number, mono?: boolean): number;
  }
}

Svg.prototype.measureApprox = function (text: string, size: number, mono = false): number {
  return text.length * size * (mono ? 0.60 : 0.585);
};
