import { FONTS } from "./fonts.js";
import { clamp01, easeOutCubic, subProgress } from "./easing.js";

export const WIDTH = 1920;
export const HEIGHT = 1080;

export const COLORS = {
  bgDeep: "#050914",
  bgCard: "#0C1426",
  bgCardAlt: "#111C33",
  border: "#1E2D4D",
  borderBright: "#2F4B7C",
  cyan: "#00D4FF",
  blue: "#3B82F6",
  emerald: "#10B981",
  amber: "#F59E0B",
  rose: "#F43F5E",
  violet: "#8B5CF6",
  textPrimary: "#F1F5F9",
  textSecondary: "#94A3B8",
  textMuted: "#64748B",
};

export function esc(s: string | number): string {
  return String(s)
    .replace(/&/g, "&amp;")
    .replace(/</g, "&lt;")
    .replace(/>/g, "&gt;")
    .replace(/"/g, "&quot;")
    .replace(/'/g, "&apos;");
}

export function fmtNum(n: number): string {
  return Math.round(n).toLocaleString("en-US");
}

export function fmtMiB(bytes: number): string {
  return (bytes / (1024 * 1024)).toFixed(2);
}

export function wrapText(text: string, maxChars: number): string[] {
  const words = text.split(/\s+/);
  const lines: string[] = [];
  let cur = "";
  for (const w of words) {
    if (!cur) cur = w;
    else if (cur.length + 1 + w.length <= maxChars) cur += " " + w;
    else {
      lines.push(cur);
      cur = w;
    }
  }
  if (cur) lines.push(cur);
  return lines;
}

export function defsBlock(): string {
  return `
  <defs>
    <radialGradient id="bgGlow1" cx="20%" cy="18%" r="65%">
      <stop offset="0%" stop-color="#0E2244" stop-opacity="0.72"/>
      <stop offset="55%" stop-color="#070E1E" stop-opacity="0.35"/>
      <stop offset="100%" stop-color="#050914" stop-opacity="0"/>
    </radialGradient>
    <radialGradient id="bgGlow2" cx="84%" cy="82%" r="55%">
      <stop offset="0%" stop-color="#0D2E3D" stop-opacity="0.55"/>
      <stop offset="100%" stop-color="#050914" stop-opacity="0"/>
    </radialGradient>
    <linearGradient id="cyanGrad" x1="0%" y1="0%" x2="100%" y2="0%">
      <stop offset="0%" stop-color="#00D4FF"/>
      <stop offset="100%" stop-color="#3B82F6"/>
    </linearGradient>
    <linearGradient id="emeraldGrad" x1="0%" y1="0%" x2="100%" y2="0%">
      <stop offset="0%" stop-color="#10B981"/>
      <stop offset="100%" stop-color="#00D4FF"/>
    </linearGradient>
    <linearGradient id="amberGrad" x1="0%" y1="0%" x2="100%" y2="0%">
      <stop offset="0%" stop-color="#F59E0B"/>
      <stop offset="100%" stop-color="#F97316"/>
    </linearGradient>
    <linearGradient id="violetGrad" x1="0%" y1="0%" x2="100%" y2="0%">
      <stop offset="0%" stop-color="#8B5CF6"/>
      <stop offset="100%" stop-color="#EC4899"/>
    </linearGradient>
    <pattern id="gridPattern" width="48" height="48" patternUnits="userSpaceOnUse">
      <path d="M 48 0 L 0 0 0 48" fill="none" stroke="#15233F" stroke-width="1"/>
    </pattern>
  </defs>`;
}

export function sceneFrameChrome(params: {
  chapterNum: number;
  totalChapters: number;
  tag: string;
  title: string;
  subtitle: string;
  narration: string;
  progress: number;
  globalProgress: number;
}): string {
  const { chapterNum, totalChapters, tag, title, subtitle, narration, progress, globalProgress } = params;
  const headerAlpha = easeOutCubic(subProgress(progress, 0.0, 0.12));
  const titleDx = (1 - headerAlpha) * 24;

  // Dynamic paged caption window synced cleanly across chapter progress (zero mid-word or end truncation)
  const words = narration.split(/\s+/);
  const allLines = wrapText(narration, 112);
  const totalPages = Math.max(1, Math.ceil(allLines.length / 2));
  const pageIdx = Math.min(totalPages - 1, Math.floor(clamp01(progress * 0.999) * totalPages));
  const capLines = allLines.slice(pageIdx * 2, pageIdx * 2 + 2);
  const activeWordIdx = Math.floor(clamp01((progress - 0.03) / 0.92) * words.length);

  // Subtle ambient grid drift
  const gridShift = (progress * 18).toFixed(2);

  return `
  <rect width="${WIDTH}" height="${HEIGHT}" fill="${COLORS.bgDeep}"/>
  <g transform="translate(${gridShift},0)" opacity="0.55">
    <rect x="-48" y="0" width="${WIDTH + 96}" height="${HEIGHT}" fill="url(#gridPattern)"/>
  </g>
  <rect width="${WIDTH}" height="${HEIGHT}" fill="url(#bgGlow1)"/>
  <rect width="${WIDTH}" height="${HEIGHT}" fill="url(#bgGlow2)"/>

  <!-- Top Academic Telemetry Bar -->
  <g opacity="${headerAlpha.toFixed(3)}">
    <rect x="64" y="34" width="1792" height="44" rx="8" fill="#091122" stroke="${COLORS.border}" stroke-width="1"/>
    <circle cx="88" cy="56" r="5" fill="${COLORS.cyan}"/>
    <text x="106" y="61" font-family="${FONTS.mono}" font-size="14" font-weight="600" fill="${COLORS.cyan}" letter-spacing="1.4">${esc(tag)}</text>
    <text x="1834" y="61" text-anchor="end" font-family="${FONTS.mono}" font-size="13" fill="${COLORS.textSecondary}">
      SECTION 0${chapterNum} / 0${totalChapters}  |  vn.com.vng.zalopay v11.17.0  |  morluto/rea · Androguard · LIEF
    </text>
  </g>

  <!-- Section Title Block -->
  <g transform="translate(0, ${titleDx.toFixed(1)})" opacity="${headerAlpha.toFixed(3)}">
    <text x="68" y="132" font-family="${FONTS.serif}" font-size="40" font-weight="700" fill="${COLORS.textPrimary}">${esc(title)}</text>
    <text x="68" y="166" font-family="${FONTS.sans}" font-size="20" font-weight="400" fill="${COLORS.textSecondary}">${esc(subtitle)}</text>
    <line x1="68" y1="184" x2="1852" y2="184" stroke="${COLORS.border}" stroke-width="1.2"/>
    <line x1="68" y1="184" x2="${(68 + 260 * headerAlpha).toFixed(1)}" y2="184" stroke="${COLORS.cyan}" stroke-width="2.5"/>
  </g>

  <!-- Bottom Scholarly Caption Pill + Progress Bar -->
  <g opacity="0.96">
    <rect x="120" y="962" width="1680" height="76" rx="12" fill="#070D1B" fill-opacity="0.92" stroke="${COLORS.borderBright}" stroke-width="1"/>
    <rect x="136" y="978" width="6" height="44" rx="3" fill="${COLORS.cyan}"/>
    ${capLines
      .map((line, i) => {
        const y = capLines.length === 1 ? 1007 : 995 + i * 26;
        return `<text x="158" y="${y}" font-family="${FONTS.sans}" font-size="19" font-weight="500" fill="${COLORS.textPrimary}">${esc(line)}</text>`;
      })
      .join("\n")}
    <text x="1780" y="1028" text-anchor="end" font-family="${FONTS.mono}" font-size="11" fill="${COLORS.textMuted}">
      W${Math.min(activeWordIdx + 1, words.length)}/${words.length}
    </text>
  </g>

  <!-- Global Documentary Timeline Scrubber -->
  <rect x="0" y="${HEIGHT - 6}" width="${WIDTH}" height="6" fill="#0B1324"/>
  <rect x="0" y="${HEIGHT - 6}" width="${(WIDTH * clamp01(globalProgress)).toFixed(1)}" height="6" fill="url(#cyanGrad)"/>
`;
}

export function card(x: number, y: number, w: number, h: number, opts?: { title?: string; accent?: string; alpha?: number; sub?: string }): string {
  const alpha = opts?.alpha ?? 1;
  const accent = opts?.accent ?? COLORS.borderBright;
  return `
  <g opacity="${alpha.toFixed(3)}">
    <rect x="${x}" y="${y}" width="${w}" height="${h}" rx="14" fill="${COLORS.bgCard}" fill-opacity="0.93" stroke="${COLORS.border}" stroke-width="1.4"/>
    <rect x="${x}" y="${y}" width="${w}" height="4" rx="2" fill="${accent}" opacity="0.88"/>
    ${opts?.title ? `<text x="${x + 22}" y="${y + 34}" font-family="${FONTS.sans}" font-size="18" font-weight="700" fill="${COLORS.textPrimary}">${esc(opts.title)}</text>` : ""}
    ${opts?.sub ? `<text x="${x + w - 22}" y="${y + 34}" text-anchor="end" font-family="${FONTS.mono}" font-size="13" fill="${COLORS.textSecondary}">${esc(opts.sub)}</text>` : ""}
  </g>`;
}
