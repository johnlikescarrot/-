/**
 * SVG slide renderer (1920x1080). Pure string generation — no native deps.
 * Sharp rasterizes SVG → PNG in build.ts.
 */
import type { Scene } from "./scenes.js";

export const W = 1920;
export const H = 1080;

const BG = "#0B1020";
const PANEL = "#131A33";
const ACCENT = "#2E6BFF";
const TEAL = "#2DD4BF";
const TEXT = "#F1F5F9";
const MUTED = "#94A3B8";
const FONT = "DejaVu Sans, sans-serif";

function esc(s: string): string {
  return s
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;");
}

function wrapLines(text: string, maxChars: number): string[] {
  const words = text.split(/\s+/);
  const lines: string[] = [];
  let cur = "";
  for (const w of words) {
    if ((cur + " " + w).trim().length > maxChars && cur) {
      lines.push(cur.trim());
      cur = w;
    } else {
      cur = (cur + " " + w).trim();
    }
  }
  if (cur.trim()) lines.push(cur.trim());
  return lines;
}

function renderChart(scene: Scene, x: number, y: number, w: number, h: number): string {
  if (!scene.chart || scene.chart.length === 0) return "";
  const max = Math.max(...scene.chart.map((d) => d.value));
  const rowH = h / scene.chart.length;
  let out = `<text x="${x}" y="${y - 18}" fill="${MUTED}" font-size="26" font-family="${FONT}" font-weight="bold">${esc(scene.chartTitle ?? "")}</text>`;
  scene.chart.forEach((d, i) => {
    const cy = y + i * rowH;
    const bw = Math.max(8, (d.value / max) * (w - 420));
    out += `
      <text x="${x}" y="${cy + rowH / 2 + 9}" fill="${TEXT}" font-size="26" font-family="${FONT}">${esc(d.label)}</text>
      <rect x="${x + 400}" y="${cy + 8}" width="${bw}" height="${rowH - 20}" rx="6" fill="${i === 0 ? ACCENT : TEAL}" opacity="0.92"/>
      <text x="${x + 400 + bw + 14}" y="${cy + rowH / 2 + 9}" fill="${TEXT}" font-size="26" font-family="${FONT}" font-weight="bold">${esc(d.display)}</text>`;
  });
  return out;
}

export function renderScene(scene: Scene, index: number, total: number): string {
  const titleLines = scene.title.split("\n");
  const titleSvg = titleLines
    .map(
      (l, i) =>
        `<text x="110" y="${210 + i * 78}" fill="${TEXT}" font-size="62" font-weight="bold" font-family="${FONT}">${esc(l)}</text>`
    )
    .join("\n");

  const subLines = wrapLines(scene.subtitle, 78).slice(0, 2);
  const subSvg = subLines
    .map(
      (l, i) =>
        `<text x="110" y="${360 + i * 42}" fill="${MUTED}" font-size="30" font-family="${FONT}">${esc(l)}</text>`
    )
    .join("\n");

  const bullets = scene.bullets.slice(0, 4);
  const bulletSvg = bullets
    .map((b, i) => {
      const lines = wrapLines(b, 62).slice(0, 2);
      const y0 = 470 + i * 108;
      const text = lines
        .map(
          (l, j) =>
            `<text x="150" y="${y0 + j * 38}" fill="${TEXT}" font-size="28" font-family="${FONT}">${esc(l)}</text>`
        )
        .join("\n");
      return `<circle cx="118" cy="${y0 - 10}" r="9" fill="${TEAL}"/>\n${text}`;
    })
    .join("\n");

  const hasChart = !!scene.chart && scene.chart.length > 0;
  const chartSvg = hasChart
    ? renderChart(scene, 110, 940, 1700, 0) // placeholder replaced below
    : "";

  // Layout: if chart, bullets move up and chart occupies bottom band.
  // Simpler: chart scenes use right panel for big number + bottom chart.
  let body: string;
  if (hasChart) {
    const bulletsCompact = scene.bullets
      .slice(0, 3)
      .map((b, i) => {
        const lines = wrapLines(b, 70).slice(0, 2);
        const y0 = 470 + i * 96;
        const text = lines
          .map(
            (l, j) =>
              `<text x="150" y="${y0 + j * 36}" fill="${TEXT}" font-size="27" font-family="${FONT}">${esc(l)}</text>`
          )
          .join("\n");
        return `<circle cx="118" cy="${y0 - 10}" r="9" fill="${TEAL}"/>\n${text}`;
      })
      .join("\n");
    const chartBlock = renderChart(scene, 110, 800, 1700, 210);
    body = `${bulletsCompact}\n${chartBlock}`;
  } else {
    body = bulletSvg;
  }

  const bigSvg = scene.bigNumber
    ? `
    <rect x="1380" y="120" width="430" height="220" rx="18" fill="${PANEL}" stroke="${ACCENT}" stroke-width="2"/>
    <text x="1595" y="215" text-anchor="middle" fill="${TEXT}" font-size="64" font-weight="bold" font-family="${FONT}">${esc(scene.bigNumber)}</text>
    <text x="1595" y="265" text-anchor="middle" fill="${MUTED}" font-size="24" font-family="${FONT}">${esc(scene.bigLabel ?? "")}</text>`
    : "";

  const progress = ((index + 1) / total) * W;

  return `<svg xmlns="http://www.w3.org/2000/svg" width="${W}" height="${H}" viewBox="0 0 ${W} ${H}">
  <rect width="${W}" height="${H}" fill="${BG}"/>
  <rect width="${W}" height="10" fill="${ACCENT}"/>
  <text x="110" y="80" fill="${TEAL}" font-size="26" font-weight="bold" font-family="${FONT}" letter-spacing="2">${esc(scene.kicker)}</text>
  <text x="1810" y="80" text-anchor="end" fill="${MUTED}" font-size="24" font-family="${FONT}">${index + 1} / ${total}</text>
  ${titleSvg}
  ${subSvg}
  ${bigSvg}
  ${body}
  <rect x="0" y="${H - 12}" width="${progress}" height="12" fill="${TEAL}"/>
  <text x="110" y="${H - 34}" fill="${MUTED}" font-size="22" font-family="${FONT}">Zalopay 11.17.0 · Static RE · tools/analyze_apk.py → docs/analysis.json</text>
</svg>`;
}

export function renderPoster(): string {
  return `<svg xmlns="http://www.w3.org/2000/svg" width="${W}" height="${H}" viewBox="0 0 ${W} ${H}">
  <rect width="${W}" height="${H}" fill="${BG}"/>
  <rect width="${W}" height="10" fill="${ACCENT}"/>
  <text x="960" y="300" text-anchor="middle" fill="${TEAL}" font-size="30" font-weight="bold" font-family="${FONT}" letter-spacing="3">NGHIÊN CỨU TÁI TẠO ĐƯỢC · 2026</text>
  <text x="960" y="430" text-anchor="middle" fill="${TEXT}" font-size="84" font-weight="bold" font-family="${FONT}">Dịch ngược tĩnh toàn diện</text>
  <text x="960" y="530" text-anchor="middle" fill="${TEXT}" font-size="84" font-weight="bold" font-family="${FONT}">Zalopay 11.17.0 (XAPK)</text>
  <text x="960" y="640" text-anchor="middle" fill="${MUTED}" font-size="34" font-family="${FONT}">20 DEX · 212 Activity · 27 native libs · 373 mini-apps · 43 ngân hàng</text>
  <text x="960" y="720" text-anchor="middle" fill="${MUTED}" font-size="30" font-family="${FONT}">TypeScript → MP4 (H.264 + AAC) · Phụ đề SRT · docs/analysis.json</text>
  <rect x="660" y="800" width="600" height="90" rx="45" fill="${ACCENT}"/>
  <text x="960" y="858" text-anchor="middle" fill="white" font-size="34" font-weight="bold" font-family="${FONT}">▶ Xem video học thuật</text>
</svg>`;
}
