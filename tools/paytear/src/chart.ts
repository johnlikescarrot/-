/** Tiny dependency-free SVG chart generator used by the report and the video. */

export interface Series {
  label: string;
  value: number;
  color?: string;
}

export const PALETTE = {
  ink: "#0b1220",
  panel: "#111a2e",
  grid: "#26344f",
  text: "#e8eefc",
  muted: "#93a4c4",
  cyan: "#38e1ff",
  blue: "#4f8dff",
  violet: "#a78bfa",
  amber: "#ffb84d",
  rose: "#ff6b8b",
  green: "#3ddc97",
};

function esc(s: string) {
  return s.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
}

export function hbarChart(opts: {
  title: string;
  data: Series[];
  width?: number;
  barHeight?: number;
  valueFormat?: (n: number) => string;
}): string {
  const width = opts.width ?? 980;
  const barHeight = opts.barHeight ?? 34;
  const padL = 300, padR = 120, padT = 70;
  const height = padT + opts.data.length * barHeight + 30;
  const max = Math.max(...opts.data.map((d) => d.value), 1);
  const fmt = opts.valueFormat ?? ((n: number) => n.toLocaleString("en-US"));
  const rows = opts.data.map((d, i) => {
    const y = padT + i * barHeight;
    const w = Math.max(2, ((width - padL - padR) * d.value) / max);
    const color = d.color ?? PALETTE.cyan;
    return `
    <text x="${padL - 14}" y="${y + 21}" fill="${PALETTE.text}" font-size="17" text-anchor="end" font-family="Inter, sans-serif">${esc(d.label)}</text>
    <rect x="${padL}" y="${y + 6}" width="${w}" height="${barHeight - 14}" rx="4" fill="${color}" opacity="0.9"/>
    <text x="${padL + w + 12}" y="${y + 21}" fill="${PALETTE.muted}" font-size="15" font-family="Inter, sans-serif">${fmt(d.value)}</text>`;
  }).join("");
  return `<svg xmlns="http://www.w3.org/2000/svg" width="${width}" height="${height}" viewBox="0 0 ${width} ${height}">
  <rect width="100%" height="100%" fill="${PALETTE.ink}"/>
  <text x="32" y="42" fill="${PALETTE.text}" font-size="26" font-weight="600" font-family="Inter, sans-serif">${esc(opts.title)}</text>
  ${rows}
</svg>`;
}

export function lineChart(opts: {
  title: string;
  values: number[];
  width?: number;
  height?: number;
  min?: number;
  max?: number;
  color?: string;
  xLabel?: string;
  yLabel?: string;
}): string {
  const width = opts.width ?? 980;
  const height = opts.height ?? 320;
  const padL = 60, padR = 30, padT = 64, padB = 44;
  const min = opts.min ?? 0;
  const max = opts.max ?? Math.max(...opts.values, 1);
  const n = opts.values.length;
  const pts = opts.values.map((v, i) => {
    const x = padL + ((width - padL - padR) * i) / Math.max(n - 1, 1);
    const y = height - padB - ((height - padT - padB) * (v - min)) / Math.max(max - min, 1e-9);
    return `${x.toFixed(1)},${y.toFixed(1)}`;
  }).join(" ");
  const gridLines = [0, 0.25, 0.5, 0.75, 1].map((f) => {
    const y = height - padB - (height - padT - padB) * f;
    const val = (min + (max - min) * f).toFixed(2);
    return `<line x1="${padL}" y1="${y}" x2="${width - padR}" y2="${y}" stroke="${PALETTE.grid}" stroke-width="1"/>
      <text x="${padL - 10}" y="${y + 4}" fill="${PALETTE.muted}" font-size="12" text-anchor="end">${val}</text>`;
  }).join("");
  return `<svg xmlns="http://www.w3.org/2000/svg" width="${width}" height="${height}" viewBox="0 0 ${width} ${height}">
  <rect width="100%" height="100%" fill="${PALETTE.ink}"/>
  <text x="24" y="38" fill="${PALETTE.text}" font-size="22" font-weight="600" font-family="Inter, sans-serif">${esc(opts.title)}</text>
  ${gridLines}
  <polyline points="${pts}" fill="none" stroke="${opts.color ?? PALETTE.cyan}" stroke-width="2.5"/>
</svg>`;
}

export function stackedBar(opts: {
  title: string;
  segments: { label: string; value: number; color: string }[];
  width?: number;
}): string {
  const width = opts.width ?? 980;
  const total = opts.segments.reduce((a, s) => a + s.value, 0) || 1;
  let x = 40;
  const bar = opts.segments.map((s) => {
    const w = ((width - 80) * s.value) / total;
    const el = `<rect x="${x.toFixed(1)}" y="90" width="${w.toFixed(1)}" height="46" fill="${s.color}"/>`;
    x += w;
    return el;
  }).join("");
  let lx = 40;
  const legend = opts.segments.map((s) => {
    const el = `<rect x="${lx}" y="170" width="14" height="14" fill="${s.color}"/>
      <text x="${lx + 22}" y="182" fill="${PALETTE.muted}" font-size="14" font-family="Inter, sans-serif">${esc(s.label)} — ${s.value.toLocaleString("en-US")}</text>`;
    lx += 22 + s.label.length * 8 + 90;
    return el;
  }).join("");
  return `<svg xmlns="http://www.w3.org/2000/svg" width="${width}" height="220" viewBox="0 0 ${width} 220">
  <rect width="100%" height="100%" fill="${PALETTE.ink}"/>
  <text x="40" y="52" fill="${PALETTE.text}" font-size="24" font-weight="600" font-family="Inter, sans-serif">${esc(opts.title)}</text>
  ${bar}${legend}
</svg>`;
}
