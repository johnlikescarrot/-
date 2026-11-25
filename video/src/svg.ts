/**
 * SVG slide generator (TypeScript, no native deps).
 * Renders 1280x720 academic slides with DejaVu Sans (Vietnamese-safe).
 */
import type { Scene } from "./scenes";
import { VIDEO_META } from "./scenes";

function esc(s: string): string {
  return s
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;");
}

/** Wrap text to max chars per line (simple word wrap). */
export function wrap(text: string, maxChars: number): string[] {
  const words = text.split(/\s+/);
  const lines: string[] = [];
  let cur = "";
  for (const w of words) {
    if ((cur + " " + w).trim().length > maxChars && cur) {
      lines.push(cur);
      cur = w;
    } else {
      cur = (cur + " " + w).trim();
    }
  }
  if (cur) lines.push(cur);
  return lines;
}

function bulletSvg(bullets: string[], x: number, y: number, maxChars: number, fontSize: number, lineH: number): string {
  let out = "";
  let cy = y;
  for (const b of bullets) {
    const lines = wrap(b, maxChars);
    // bullet dot
    out += `<circle cx="${x}" cy="${cy + fontSize * 0.35}" r="5" fill="#2563eb"/>`;
    lines.forEach((ln, i) => {
      out += `<text x="${x + 22}" y="${cy + fontSize * (i + 0.9)}" font-family="DejaVu Sans" font-size="${fontSize}" fill="#1e293b">${esc(ln)}</text>`;
    });
    cy += lines.length * lineH + 10;
  }
  return out;
}

function chartSvg(scene: Scene, x: number, y: number, w: number, h: number): string {
  if (!scene.chart) return "";
  const bars = scene.chart.bars;
  const maxV = Math.max(...bars.map((b) => b.value), 1);
  const barH = Math.min(34, (h - 60) / bars.length - 8);
  let out = `<text x="${x}" y="${y}" font-family="DejaVu Sans" font-size="15" font-weight="bold" fill="#0f172a">${esc(scene.chart.title)}</text>`;
  let cy = y + 18;
  for (const b of bars) {
    const bw = Math.max(8, (b.value / maxV) * (w - 220));
    const color = b.color ?? "#2563eb";
    out += `<text x="${x}" y="${cy + barH * 0.7}" font-family="DejaVu Sans" font-size="13" fill="#334155">${esc(b.label)}</text>`;
    out += `<rect x="${x + 150}" y="${cy}" width="${bw}" height="${barH}" rx="4" fill="${color}"/>`;
    out += `<text x="${x + 158 + bw}" y="${cy + barH * 0.7}" font-family="DejaVu Sans" font-size="13" font-weight="bold" fill="#0f172a">${b.value}${scene.chart.unit ?? ""}</text>`;
    cy += barH + 10;
  }
  return out;
}

export function sceneToSvg(scene: Scene, index: number, total: number): string {
  const W = VIDEO_META.width;
  const H = VIDEO_META.height;
  const hasChart = !!scene.chart;
  const contentW = hasChart ? 620 : 1080;
  const chartX = 700;
  let chartY = 250;
  const chartW = 520;
  const chartH = 380;

  const titleLines = wrap(scene.title, hasChart ? 34 : 52);
  const subLines = wrap(scene.subtitle, hasChart ? 48 : 78);

  const titleStartY = 135;
  const titleLineH = 46;
  let titleSvg = "";
  titleLines.forEach((ln, i) => {
    titleSvg += `<text x="60" y="${titleStartY + i * titleLineH}" font-family="DejaVu Sans" font-size="40" font-weight="bold" fill="#0f172a">${esc(ln)}</text>`;
  });
  const subStartY = titleStartY + titleLines.length * titleLineH + 18;
  let subSvg = "";
  subLines.forEach((ln, i) => {
    subSvg += `<text x="60" y="${subStartY + i * 26}" font-family="DejaVu Sans" font-size="19" fill="#475569">${esc(ln)}</text>`;
  });

  const dividerY = subStartY + subLines.length * 26 + 8;
  const bulletsY = dividerY + 40;
  const bullets = bulletSvg(scene.bullets, 60, bulletsY, hasChart ? 56 : 104, 19, 27);
  chartY = dividerY + 26;
  const chart = hasChart ? chartSvg(scene, chartX, chartY, chartW, chartH) : "";

  // Progress bar
  const progW = ((index + 1) / total) * (W - 120);

  return `<svg xmlns="http://www.w3.org/2000/svg" width="${W}" height="${H}" viewBox="0 0 ${W} ${H}">
<defs>
  <linearGradient id="bg" x1="0" y1="0" x2="1" y2="1">
    <stop offset="0" stop-color="#f8fafc"/><stop offset="1" stop-color="#eff6ff"/>
  </linearGradient>
  <linearGradient id="accent" x1="0" y1="0" x2="1" y2="0">
    <stop offset="0" stop-color="#2563eb"/><stop offset="1" stop-color="#0ea5e9"/>
  </linearGradient>
</defs>
<rect width="${W}" height="${H}" fill="url(#bg)"/>
<rect x="0" y="0" width="${W}" height="8" fill="url(#accent)"/>
<text x="60" y="52" font-family="DejaVu Sans" font-size="16" font-weight="bold" letter-spacing="2" fill="#2563eb">${esc(scene.kicker)}</text>
<text x="${W - 60}" y="52" text-anchor="end" font-family="DejaVu Sans" font-size="15" fill="#64748b">${index + 1} / ${total}</text>
${titleSvg}
${subSvg}
<line x1="60" y1="${dividerY}" x2="${hasChart ? 640 : 1220}" y2="${dividerY}" stroke="#cbd5e1" stroke-width="2"/>
${bullets}
${chart}
<rect x="60" y="${H - 52}" width="${W - 120}" height="6" rx="3" fill="#e2e8f0"/>
<rect x="60" y="${H - 52}" width="${progW}" height="6" rx="3" fill="#2563eb"/>
<text x="60" y="${H - 18}" font-family="DejaVu Sans" font-size="13" fill="#64748b">${esc(scene.footer)}</text>
<text x="${W - 60}" y="${H - 18}" text-anchor="end" font-family="DejaVu Sans" font-size="13" fill="#94a3b8">ZP-RE-2026-11-17.0</text>
</svg>`;
}

export function titleSvg(): string {
  const W = VIDEO_META.width;
  const H = VIDEO_META.height;
  return `<svg xmlns="http://www.w3.org/2000/svg" width="${W}" height="${H}" viewBox="0 0 ${W} ${H}">
<defs>
  <linearGradient id="bg2" x1="0" y1="0" x2="1" y2="1">
    <stop offset="0" stop-color="#0f172a"/><stop offset="1" stop-color="#1e3a5f"/>
  </linearGradient>
</defs>
<rect width="${W}" height="${H}" fill="url(#bg2)"/>
<text x="640" y="240" text-anchor="middle" font-family="DejaVu Sans" font-size="22" font-weight="bold" letter-spacing="4" fill="#38bdf8">ZP-RE-2026-11-17.0 · ACADEMIC VIDEO</text>
<text x="640" y="320" text-anchor="middle" font-family="DejaVu Sans" font-size="56" font-weight="bold" fill="#ffffff">ZaloPay 11.17.0</text>
<text x="640" y="375" text-anchor="middle" font-family="DejaVu Sans" font-size="28" fill="#cbd5e1">Reverse Engineering a Vietnamese Super-App</text>
<text x="640" y="430" text-anchor="middle" font-family="DejaVu Sans" font-size="20" fill="#94a3b8">6,471 entries · Offline static analysis · TypeScript pipeline</text>
<rect x="440" y="470" width="400" height="4" rx="2" fill="#2563eb"/>
<text x="640" y="520" text-anchor="middle" font-family="DejaVu Sans" font-size="16" fill="#64748b">github.com/johnlikescarrot/- · MP4 · H.264 + AAC</text>
</svg>`;
}
