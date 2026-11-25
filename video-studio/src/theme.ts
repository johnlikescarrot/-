import { GlobalFonts, type SKRSContext2D } from "@napi-rs/canvas";
import { resolve, dirname } from "node:path";
import { fileURLToPath } from "node:url";

const __dirname = dirname(fileURLToPath(import.meta.url));
const STUDIO_ROOT = resolve(__dirname, "..");

export const W = 1920;
export const H = 1080;
export const FPS = 30;

export const PALETTE = {
  bg0: "#050912",
  bg1: "#0B1221",
  panel: "rgba(15, 23, 42, 0.86)",
  panelSolid: "#0F172A",
  panelBorder: "rgba(56, 189, 248, 0.22)",
  panelBorderSubtle: "rgba(148, 163, 184, 0.16)",
  grid: "rgba(56, 189, 248, 0.045)",
  gridAccent: "rgba(56, 189, 248, 0.11)",
  text: "#F8FAFC",
  textMuted: "#94A3B8",
  textDim: "#64748B",
  cyan: "#38BDF8",
  cyanGlow: "rgba(56, 189, 248, 0.28)",
  emerald: "#10B981",
  emeraldGlow: "rgba(16, 185, 129, 0.24)",
  amber: "#F59E0B",
  amberGlow: "rgba(245, 158, 11, 0.25)",
  rose: "#F43F5E",
  roseGlow: "rgba(244, 63, 94, 0.25)",
  violet: "#A78BFA",
  violetGlow: "rgba(167, 139, 250, 0.24)",
  indigo: "#6366F1",
};

let fontsRegistered = false;

export function registerStudioFonts(): void {
  if (fontsRegistered) return;
  const bvp = resolve(STUDIO_ROOT, "node_modules/@fontsource/be-vietnam-pro/files");
  const sg = resolve(STUDIO_ROOT, "node_modules/@fontsource/space-grotesk/files");
  const jm = resolve(STUDIO_ROOT, "node_modules/@fontsource/jetbrains-mono/files");

  for (const w of [400, 600, 700]) {
    GlobalFonts.registerFromPath(`${bvp}/be-vietnam-pro-vietnamese-${w}-normal.woff2`, "BeVietnamPro");
    GlobalFonts.registerFromPath(`${bvp}/be-vietnam-pro-latin-ext-${w}-normal.woff2`, "BeVietnamPro");
    GlobalFonts.registerFromPath(`${bvp}/be-vietnam-pro-latin-${w}-normal.woff2`, "BeVietnamPro");

    GlobalFonts.registerFromPath(`${sg}/space-grotesk-vietnamese-${w}-normal.woff2`, "SpaceGrotesk");
    GlobalFonts.registerFromPath(`${sg}/space-grotesk-latin-${w}-normal.woff2`, "SpaceGrotesk");

    GlobalFonts.registerFromPath(`${jm}/jetbrains-mono-vietnamese-${w}-normal.woff2`, "JetBrainsMono");
    GlobalFonts.registerFromPath(`${jm}/jetbrains-mono-latin-${w}-normal.woff2`, "JetBrainsMono");
  }
  fontsRegistered = true;
}

export const clamp01 = (v: number): number => Math.max(0, Math.min(1, v));

export const easeOutCubic = (t: number): number => {
  const x = clamp01(t);
  return 1 - Math.pow(1 - x, 3);
};

export const easeInOutCubic = (t: number): number => {
  const x = clamp01(t);
  return x < 0.5 ? 4 * x * x * x : 1 - Math.pow(-2 * x + 2, 3) / 2;
};

export const windowProgress = (localSec: number, startSec: number, durationSec: number): number =>
  easeOutCubic((localSec - startSec) / Math.max(0.001, durationSec));

export function roundRectPath(
  ctx: SKRSContext2D,
  x: number,
  y: number,
  w: number,
  h: number,
  r: number
): void {
  const radius = Math.min(r, w / 2, h / 2);
  ctx.beginPath();
  ctx.moveTo(x + radius, y);
  ctx.arcTo(x + w, y, x + w, y + h, radius);
  ctx.arcTo(x + w, y + h, x, y + h, radius);
  ctx.arcTo(x, y + h, x, y, radius);
  ctx.arcTo(x, y, x + w, y, radius);
  ctx.closePath();
}

export function drawGlassPanel(
  ctx: SKRSContext2D,
  x: number,
  y: number,
  w: number,
  h: number,
  opts: {
    radius?: number;
    borderColor?: string;
    fill?: string;
    accentTop?: string;
    alpha?: number;
  } = {}
): void {
  const {
    radius = 16,
    borderColor = PALETTE.panelBorder,
    fill = PALETTE.panel,
    accentTop,
    alpha = 1,
  } = opts;
  ctx.save();
  ctx.globalAlpha *= clamp01(alpha);
  roundRectPath(ctx, x, y, w, h, radius);
  ctx.fillStyle = fill;
  ctx.fill();
  ctx.lineWidth = 1.5;
  ctx.strokeStyle = borderColor;
  ctx.stroke();

  if (accentTop) {
    ctx.beginPath();
    ctx.moveTo(x + radius, y + 1);
    ctx.lineTo(x + w - radius, y + 1);
    ctx.strokeStyle = accentTop;
    ctx.lineWidth = 3;
    ctx.stroke();
  }
  ctx.restore();
}

export function drawPill(
  ctx: SKRSContext2D,
  x: number,
  y: number,
  label: string,
  color: string,
  bgAlpha = 0.16
): number {
  ctx.save();
  ctx.font = "600 17px JetBrainsMono, BeVietnamPro";
  const tw = ctx.measureText(label).width;
  const pw = tw + 26;
  const ph = 32;
  roundRectPath(ctx, x, y, pw, ph, 16);
  ctx.fillStyle = color;
  ctx.globalAlpha = bgAlpha;
  ctx.fill();
  ctx.globalAlpha = 0.75;
  ctx.lineWidth = 1.25;
  ctx.strokeStyle = color;
  ctx.stroke();
  ctx.globalAlpha = 1;
  ctx.fillStyle = color;
  ctx.textBaseline = "middle";
  ctx.fillText(label, x + 13, y + ph / 2 + 0.5);
  ctx.restore();
  return pw;
}

export function drawBackdrop(ctx: SKRSContext2D, globalTimeSec: number): void {
  const grad = ctx.createRadialGradient(W * 0.25, H * 0.2, 60, W * 0.5, H * 0.5, W * 0.9);
  grad.addColorStop(0, "#0F1B33");
  grad.addColorStop(0.55, PALETTE.bg1);
  grad.addColorStop(1, PALETTE.bg0);
  ctx.fillStyle = grad;
  ctx.fillRect(0, 0, W, H);

  // Subtle ambient drifting glow blobs
  const driftX = Math.sin(globalTimeSec * 0.17) * 90;
  const driftY = Math.cos(globalTimeSec * 0.13) * 50;
  const g1 = ctx.createRadialGradient(1480 + driftX, 240 + driftY, 10, 1480 + driftX, 240 + driftY, 520);
  g1.addColorStop(0, "rgba(56, 189, 248, 0.08)");
  g1.addColorStop(1, "rgba(56, 189, 248, 0)");
  ctx.fillStyle = g1;
  ctx.fillRect(0, 0, W, H);

  const g2 = ctx.createRadialGradient(340 - driftX * 0.7, 860 - driftY, 10, 340 - driftX * 0.7, 860 - driftY, 480);
  g2.addColorStop(0, "rgba(16, 185, 129, 0.06)");
  g2.addColorStop(1, "rgba(16, 185, 129, 0)");
  ctx.fillStyle = g2;
  ctx.fillRect(0, 0, W, H);

  // Engineering coordinate grid (48px pitch)
  ctx.save();
  ctx.strokeStyle = PALETTE.grid;
  ctx.lineWidth = 1;
  ctx.beginPath();
  for (let x = 0; x <= W; x += 48) {
    ctx.moveTo(x, 0);
    ctx.lineTo(x, H);
  }
  for (let y = 0; y <= H; y += 48) {
    ctx.moveTo(0, y);
    ctx.lineTo(W, y);
  }
  ctx.stroke();

  // Accent crosshair ticks every 240px
  ctx.strokeStyle = PALETTE.gridAccent;
  ctx.beginPath();
  for (let x = 240; x < W; x += 240) {
    for (let y = 240; y < H; y += 240) {
      ctx.moveTo(x - 6, y);
      ctx.lineTo(x + 6, y);
      ctx.moveTo(x, y - 6);
      ctx.lineTo(x, y + 6);
    }
  }
  ctx.stroke();
  ctx.restore();
}

export function drawWrappedText(
  ctx: SKRSContext2D,
  text: string,
  x: number,
  y: number,
  maxWidth: number,
  lineHeight: number
): number {
  const words = text.split(" ");
  let line = "";
  let curY = y;
  for (let i = 0; i < words.length; i++) {
    const test = line ? `${line} ${words[i]}` : words[i];
    if (ctx.measureText(test).width > maxWidth && line) {
      ctx.fillText(line, x, curY);
      line = words[i];
      curY += lineHeight;
    } else {
      line = test;
    }
  }
  if (line) {
    ctx.fillText(line, x, curY);
    curY += lineHeight;
  }
  return curY;
}
