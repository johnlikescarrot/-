/** Design system: palette, type scale, grid, and the SVG drawing surface. */

export const W = 1920;
export const H = 1080;
export const FPS = 30;

export const C = {
  bg: "#060A14",
  bg2: "#0B1222",
  panel: "#101A2E",
  panelHi: "#16233C",
  line: "#22304A",
  lineHi: "#31507A",
  ink: "#F3F6FC",
  ink2: "#A9B8D0",
  ink3: "#6C7F9B",
  brand: "#0A84FF",
  brandHi: "#4DA8FF",
  cyan: "#43E0D8",
  lime: "#B8F24A",
  amber: "#FFB44D",
  coral: "#FF6B6B",
  violet: "#A78BFA",
  pink: "#F472B6",
} as const;

export const FONT = {
  sans: "BVN",
  sansLatin: "BVN",
  mono: "PlexMono",
};

export const T = {
  hero: 132,
  h1: 92,
  h2: 64,
  h3: 44,
  lead: 34,
  body: 26,
  small: 21,
  micro: 16,
  monoLg: 30,
  mono: 20,
  monoSm: 16,
};

export const GRID = { margin: 104, gutter: 24, cols: 12 };

export function col(n: number, span = 1, total = 12): { x: number; w: number } {
  const usable = W - GRID.margin * 2;
  const unit = (usable - GRID.gutter * (total - 1)) / total;
  return { x: GRID.margin + n * (unit + GRID.gutter), w: unit * span + GRID.gutter * (span - 1) };
}

export const esc = (s: string): string =>
  s.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");

export interface TextOpts {
  size?: number;
  fill?: string;
  family?: string;
  weight?: number;
  anchor?: "start" | "middle" | "end";
  opacity?: number;
  letter?: number;
  lineHeight?: number;
  clip?: { x: number; y: number; w: number; h: number };
}

/** Minimal, allocation-friendly SVG builder with a defence against invalid geometry. */
export class Svg {
  private buf: string[] = [];
  private defs: string[] = [];
  private uid = 0;

  constructor(public readonly w = W, public readonly h = H, public readonly bg = C.bg) {}

  nextId(p = "g"): string {
    this.uid += 1;
    return `${p}${this.uid}`;
  }

  raw(s: string): this {
    this.buf.push(s);
    return this;
  }

  def(s: string): this {
    this.defs.push(s);
    return this;
  }

  /** Horizontal or vertical linear gradient; returns a url(#id) reference. */
  lin(x1: number, y1: number, x2: number, y2: number, stops: [number, string, number?][]): string {
    const id = this.nextId("lg");
    const body = stops
      .map(([o, c, a]) => `<stop offset="${o}" stop-color="${c}"${a === undefined ? "" : ` stop-opacity="${a}"`}/>`)
      .join("");
    this.def(`<linearGradient id="${id}" x1="${x1}" y1="${y1}" x2="${x2}" y2="${y2}" gradientUnits="userSpaceOnUse">${body}</linearGradient>`);
    return `url(#${id})`;
  }

  rad(cx: number, cy: number, r: number, stops: [number, string, number?][]): string {
    const id = this.nextId("rg");
    const body = stops
      .map(([o, c, a]) => `<stop offset="${o}" stop-color="${c}"${a === undefined ? "" : ` stop-opacity="${a}"`}/>`)
      .join("");
    this.def(`<radialGradient id="${id}" cx="${cx}" cy="${cy}" r="${r}" gradientUnits="userSpaceOnUse">${body}</radialGradient>`);
    return `url(#${id})`;
  }

  rect(x: number, y: number, w: number, h: number, fill = "none", o: { r?: number; stroke?: string; sw?: number; opacity?: number; dash?: string } = {}): this {
    if (!isFinite(x) || !isFinite(y) || !isFinite(w) || !isFinite(h) || w <= 0 || h <= 0) return this;
    const r = o.r ? ` rx="${o.r}"` : "";
    return this.raw(
      `<rect x="${x.toFixed(2)}" y="${y.toFixed(2)}" width="${w.toFixed(2)}" height="${h.toFixed(2)}"${r} fill="${fill}"` +
        (o.stroke ? ` stroke="${o.stroke}" stroke-width="${o.sw ?? 1.5}"` : "") +
        (o.dash ? ` stroke-dasharray="${o.dash}"` : "") +
        (o.opacity !== undefined ? ` opacity="${o.opacity}"` : "") +
        "/>",
    );
  }

  text(x: number, y: number, s: string, o: TextOpts = {}): this {
    if (!s) return this;
    const size = o.size ?? T.body;
    const fam = o.family ?? FONT.sans;
    const weight = o.weight ?? 400;
    const anchor = o.anchor ?? "start";
    const lines = String(s).split("\n");
    const lh = o.lineHeight ?? size * 1.34;
    const clipAttr = o.clip
      ? ` clip-path="url(#${this.clipRect(o.clip)})"`
      : "";
    const body = lines
      .map((ln, i) => `<tspan x="${x.toFixed(2)}" dy="${i === 0 ? 0 : lh.toFixed(2)}">${esc(ln)}</tspan>`)
      .join("");
    return this.raw(
      `<text x="${x.toFixed(2)}" y="${y.toFixed(2)}" font-family="${fam}" font-size="${size}" font-weight="${weight}" ` +
        `fill="${o.fill ?? C.ink}" text-anchor="${anchor}"` +
        (o.opacity !== undefined ? ` opacity="${o.opacity}"` : "") +
        (o.letter ? ` letter-spacing="${o.letter}"` : "") +
        `${clipAttr}>${body}</text>`,
    );
  }

  line(x1: number, y1: number, x2: number, y2: number, stroke = C.line, sw = 1.5, opacity = 1, dash?: string): this {
    if (![x1, y1, x2, y2].every(isFinite)) return this;
    return this.raw(
      `<line x1="${x1.toFixed(2)}" y1="${y1.toFixed(2)}" x2="${x2.toFixed(2)}" y2="${y2.toFixed(2)}" stroke="${stroke}" stroke-width="${sw}" opacity="${opacity}"${dash ? ` stroke-dasharray="${dash}"` : ""} stroke-linecap="round"/>`,
    );
  }

  circle(cx: number, cy: number, r: number, fill = "none", o: { stroke?: string; sw?: number; opacity?: number } = {}): this {
    if (![cx, cy, r].every(isFinite) || r <= 0) return this;
    return this.raw(
      `<circle cx="${cx.toFixed(2)}" cy="${cy.toFixed(2)}" r="${r.toFixed(2)}" fill="${fill}"` +
        (o.stroke ? ` stroke="${o.stroke}" stroke-width="${o.sw ?? 1.5}"` : "") +
        (o.opacity !== undefined ? ` opacity="${o.opacity}"` : "") +
        "/>",
    );
  }

  path(d: string, o: { fill?: string; stroke?: string; sw?: number; opacity?: number; cap?: string } = {}): this {
    return this.raw(
      `<path d="${d}" fill="${o.fill ?? "none"}"` +
        (o.stroke ? ` stroke="${o.stroke}" stroke-width="${o.sw ?? 2}" stroke-linecap="${o.cap ?? "round"}" stroke-linejoin="round"` : "") +
        (o.opacity !== undefined ? ` opacity="${o.opacity}"` : "") +
        "/>",
    );
  }

  /** Rounded-rect clip used for text/bar reveals. */
  clipRect(b: { x: number; y: number; w: number; h: number }): string {
    const id = this.nextId("cp");
    this.def(`<clipPath id="${id}"><rect x="${b.x}" y="${b.y}" width="${Math.max(0, b.w)}" height="${Math.max(0, b.h)}"/></clipPath>`);
    return id;
  }

  group(attrs: string, fn: (s: Svg) => void): this {
    this.buf.push(`<g ${attrs}>`);
    fn(this);
    this.buf.push("</g>");
    return this;
  }

  /** Film-wide background: deep gradient, two soft light pools, vignette, grid ticks. */
  backdrop(t = 0, intensity = 1): this {
    const g = this.lin(0, 0, 0, H, [
      [0, "#070C18", 1],
      [0.55, "#060A14", 1],
      [1, "#04060D", 1],
    ]);
    this.rect(0, 0, W, H, g);
    const p1 = this.rad(W * 0.18 + Math.sin(t * 0.11) * 90, H * 0.12, 980, [
      [0, C.brand, 0.20 * intensity],
      [0.6, C.brand, 0.05 * intensity],
      [1, C.brand, 0],
    ]);
    this.rect(0, 0, W, H, p1);
    const p2 = this.rad(W * 0.86 + Math.cos(t * 0.09) * 70, H * 0.92, 900, [
      [0, C.cyan, 0.13 * intensity],
      [0.65, C.violet, 0.04 * intensity],
      [1, C.violet, 0],
    ]);
    this.rect(0, 0, W, H, p2);
    const v = this.rad(W / 2, H / 2, Math.max(W, H) * 0.75, [
      [0.55, "#000000", 0],
      [1, "#000000", 0.72],
    ]);
    this.rect(0, 0, W, H, v);
    return this;
  }

  toSvgString(): string {
    const defs = this.defs.length ? `<defs>${this.defs.join("")}</defs>` : "";
    return (
      `<svg xmlns="http://www.w3.org/2000/svg" width="${this.w}" height="${this.h}" viewBox="0 0 ${this.w} ${this.h}">` +
      defs +
      `<rect width="${this.w}" height="${this.h}" fill="${this.bg}"/>` +
      this.buf.join("") +
      `</svg>`
    );
  }
}

/** Easing + small numeric helpers used by every scene. */
export const ease = {
  linear: (t: number) => t,
  out: (t: number) => 1 - Math.pow(1 - t, 3),
  inOut: (t: number) => (t < 0.5 ? 4 * t * t * t : 1 - Math.pow(-2 * t + 2, 3) / 2),
  outQuint: (t: number) => 1 - Math.pow(1 - t, 5),
  outExpo: (t: number) => (t >= 1 ? 1 : 1 - Math.pow(2, -10 * t)),
  inOutSine: (t: number) => -(Math.cos(Math.PI * t) - 1) / 2,
  back: (t: number) => 1 + 2.7 * Math.pow(t - 1, 3) + 1.7 * Math.pow(t - 1, 2),
};

export const clamp01 = (x: number) => (x < 0 ? 0 : x > 1 ? 1 : x);

/** 0→1 ramp across [a,b] seconds, eased. */
export function ramp(t: number, a: number, b: number, f: (x: number) => number = ease.out): number {
  if (b <= a) return t >= b ? 1 : 0;
  return f(clamp01((t - a) / (b - a)));
}

/** Fade in then out across a window. */
export function pulse(t: number, a: number, b: number, fade = 0.45): number {
  return Math.min(ramp(t, a, a + fade), 1 - ramp(t, b - fade, b));
}

export const lerp = (a: number, b: number, x: number) => a + (b - a) * x;

export function fmtBytes(n: number): string {
  if (n >= 1e9) return `${(n / 1e9).toFixed(2)} GB`;
  if (n >= 1e6) return `${(n / 1e6).toFixed(2)} MB`;
  if (n >= 1e3) return `${(n / 1e3).toFixed(1)} KB`;
  return `${n} B`;
}

export const fmtInt = (n: number) => n.toLocaleString("en-US").replace(/,/g, " ");

export function fmtTime(s: number): string {
  const m = Math.floor(s / 60);
  const sec = Math.floor(s % 60);
  return `${m}:${sec.toString().padStart(2, "0")}`;
}
