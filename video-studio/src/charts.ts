import { type SKRSContext2D } from "@napi-rs/canvas";
import * as d3 from "d3";
import { PALETTE, clamp01, easeOutCubic, roundRectPath } from "./theme.js";
import type { NativeLibRecord } from "./types.js";

export interface BarDatum {
  label: string;
  value: number;
  color: string;
  displayValue: string;
  sublabel?: string;
}

export function drawHorizontalBars(
  ctx: SKRSContext2D,
  x: number,
  y: number,
  w: number,
  h: number,
  items: BarDatum[],
  progress: number,
  opts: { maxValue?: number; labelWidth?: number; valueWidth?: number } = {}
): void {
  const maxVal = opts.maxValue ?? Math.max(1, ...items.map((d) => d.value));
  const labelW = opts.labelWidth ?? 230;
  const valW = opts.valueWidth ?? 130;
  const barAreaW = Math.max(60, w - labelW - valW - 16);

  const scaleX = d3.scaleLinear().domain([0, maxVal]).range([0, barAreaW]);
  const scaleY = d3
    .scaleBand<number>()
    .domain(items.map((_, i) => i))
    .range([y, y + h])
    .padding(0.28);

  const bw = scaleY.bandwidth();

  items.forEach((d, idx) => {
    const stagger = clamp01((progress - idx * 0.045) / 0.55);
    const anim = easeOutCubic(stagger);
    const ry = scaleY(idx) ?? y;

    ctx.save();
    ctx.globalAlpha *= clamp01(anim * 1.4);

    // Label
    ctx.font = "600 18px BeVietnamPro, SpaceGrotesk";
    ctx.fillStyle = PALETTE.text;
    ctx.textBaseline = "middle";
    ctx.textAlign = "right";
    ctx.fillText(d.label, x + labelW - 14, ry + bw / 2);

    // Track
    const bx = x + labelW;
    roundRectPath(ctx, bx, ry + bw * 0.14, barAreaW, bw * 0.72, 6);
    ctx.fillStyle = "rgba(148, 163, 184, 0.10)";
    ctx.fill();

    // Animated Fill
    const fillW = Math.max(4, scaleX(d.value) * anim);
    roundRectPath(ctx, bx, ry + bw * 0.14, fillW, bw * 0.72, 6);
    const g = ctx.createLinearGradient(bx, 0, bx + fillW, 0);
    g.addColorStop(0, d.color);
    g.addColorStop(1, d.color);
    ctx.fillStyle = g;
    ctx.fill();

    // Subtle right tip highlight
    ctx.fillStyle = "#FFFFFF";
    ctx.globalAlpha *= 0.45;
    ctx.fillRect(bx + fillW - 3, ry + bw * 0.14, 3, bw * 0.72);
    ctx.globalAlpha /= 0.45;

    // Value text
    ctx.font = "600 17px JetBrainsMono, BeVietnamPro";
    ctx.fillStyle = d.color;
    ctx.textAlign = "left";
    ctx.fillText(d.displayValue, bx + barAreaW + 14, ry + bw / 2);
    ctx.restore();
  });
}

export interface SliceDatum {
  label: string;
  value: number;
  color: string;
  pctText: string;
}

export function drawDonutChart(
  ctx: SKRSContext2D,
  cx: number,
  cy: number,
  outerR: number,
  innerR: number,
  slices: SliceDatum[],
  centerTop: string,
  centerBottom: string,
  progress: number
): void {
  const anim = easeOutCubic(progress);
  const pieGen = d3
    .pie<SliceDatum>()
    .value((d) => d.value)
    .sort(null)
    .padAngle(0.025);
  const arcs = pieGen(slices);
  const arcGen = d3.arc<d3.PieArcDatum<SliceDatum>>().innerRadius(innerR).outerRadius(outerR).cornerRadius(5);

  ctx.save();
  ctx.translate(cx, cy);
  const maxAngle = -Math.PI / 2 + Math.PI * 2 * anim;

  for (const a of arcs) {
    const startA = a.startAngle - Math.PI / 2;
    const endA = Math.min(a.endAngle - Math.PI / 2, maxAngle);
    if (endA <= startA) continue;
    const clipped = { ...a, startAngle: a.startAngle, endAngle: Math.min(a.endAngle, a.startAngle + (endA - startA)) };
    const pathData = arcGen(clipped);
    if (!pathData) continue;
    // Draw arc manually cleanly via canvas arc to guarantee zero SVG path overhead
    ctx.beginPath();
    ctx.arc(0, 0, outerR, startA, endA);
    ctx.arc(0, 0, innerR, endA, startA, true);
    ctx.closePath();
    ctx.fillStyle = a.data.color;
    ctx.fill();
  }

  // Center telemetry readout
  ctx.textAlign = "center";
  ctx.textBaseline = "middle";
  ctx.fillStyle = PALETTE.text;
  ctx.font = "700 34px SpaceGrotesk, BeVietnamPro";
  ctx.fillText(centerTop, 0, -10);
  ctx.fillStyle = PALETTE.textMuted;
  ctx.font = "600 16px JetBrainsMono, BeVietnamPro";
  ctx.fillText(centerBottom, 0, 24);
  ctx.restore();
}

export function drawEntropyScatter(
  ctx: SKRSContext2D,
  x: number,
  y: number,
  w: number,
  h: number,
  libs: NativeLibRecord[],
  assets: Array<{ path: string; bytes: number; entropy: number }>,
  progress: number
): void {
  const padL = 74;
  const padR = 30;
  const padT = 30;
  const padB = 54;
  const pw = w - padL - padR;
  const ph = h - padT - padB;
  const px = x + padL;
  const py = y + padT;

  const scaleX = d3.scaleLog().domain([250, 16_000_000]).range([px, px + pw]).clamp(true);
  const scaleY = d3.scaleLinear().domain([4.4, 8.22]).range([py + ph, py]).clamp(true);

  ctx.save();
  // Grid & axes
  ctx.strokeStyle = "rgba(148, 163, 184, 0.14)";
  ctx.lineWidth = 1;
  for (const hVal of [5.0, 6.0, 7.0, 8.0]) {
    const yy = scaleY(hVal);
    ctx.beginPath();
    ctx.moveTo(px, yy);
    ctx.lineTo(px + pw, yy);
    ctx.stroke();
    ctx.font = "400 15px JetBrainsMono";
    ctx.fillStyle = PALETTE.textMuted;
    ctx.textAlign = "right";
    ctx.textBaseline = "middle";
    ctx.fillText(hVal.toFixed(1), px - 10, yy);
  }

  // Highlight encrypted/packed threshold band (H >= 7.5)
  const y75 = scaleY(7.5);
  ctx.fillStyle = "rgba(244, 63, 94, 0.08)";
  ctx.fillRect(px, py, pw, y75 - py);
  ctx.strokeStyle = "rgba(244, 63, 94, 0.45)";
  ctx.setLineDash([6, 6]);
  ctx.beginPath();
  ctx.moveTo(px, y75);
  ctx.lineTo(px + pw, y75);
  ctx.stroke();
  ctx.setLineDash([]);
  ctx.font = "600 13px JetBrainsMono";
  ctx.fillStyle = PALETTE.rose;
  ctx.textAlign = "left";
  ctx.fillText("Threshold H >= 7.5 (Encrypted / Packed)", px + 10, y75 + 16);

  // X log ticks
  for (const [val, lbl] of [
    [1024, "1 KB"],
    [16384, "16 KB"],
    [262144, "256 KB"],
    [1048576, "1 MB"],
    [10485760, "10 MB"],
  ] as Array<[number, string]>) {
    const xx = scaleX(val);
    ctx.strokeStyle = "rgba(148, 163, 184, 0.14)";
    ctx.beginPath();
    ctx.moveTo(xx, py);
    ctx.lineTo(xx, py + ph);
    ctx.stroke();
    ctx.fillStyle = PALETTE.textMuted;
    ctx.font = "400 15px JetBrainsMono";
    ctx.textAlign = "center";
    ctx.fillText(lbl, xx, py + ph + 20);
  }

  ctx.fillStyle = PALETTE.textDim;
  ctx.font = "600 15px BeVietnamPro";
  ctx.textAlign = "center";
  ctx.fillText("Artifact Size (Log Scale)", px + pw / 2, py + ph + 44);

  // Plot High-Entropy Assets
  const anim = easeOutCubic(progress);
  for (let i = 0; i < assets.length; i++) {
    const a = assets[i];
    const cx = scaleX(Math.max(300, a.bytes));
    const cy = scaleY(a.entropy);
    const r = Math.max(5, Math.min(14, Math.sqrt(a.bytes / 8000))) * anim;
    ctx.beginPath();
    ctx.arc(cx, cy, r, 0, Math.PI * 2);
    ctx.fillStyle = "rgba(244, 63, 94, 0.55)";
    ctx.fill();
    ctx.strokeStyle = PALETTE.rose;
    ctx.lineWidth = 1.5;
    ctx.stroke();
  }

  // Plot all 27 ELF .so binaries
  for (const lib of libs) {
    const cx = scaleX(lib.bytes);
    const cy = scaleY(lib.overall_entropy);
    const r = Math.max(6, Math.min(20, Math.sqrt(lib.bytes / 35000))) * anim;
    const isShield = ["libtaibmcf.so", "libfgcpkjvqe.so", "liba97d.so", "libljsfrcwmw.so", "libohxbee.so", "libsecurity_lite.so", "libtrueidlib.so"].includes(lib.name);
    const col = isShield ? PALETTE.amber : PALETTE.cyan;
    ctx.beginPath();
    ctx.arc(cx, cy, r, 0, Math.PI * 2);
    ctx.fillStyle = isShield ? "rgba(245, 158, 11, 0.55)" : "rgba(56, 189, 248, 0.45)";
    ctx.fill();
    ctx.strokeStyle = col;
    ctx.lineWidth = 1.75;
    ctx.stroke();
  }

  // Selected clean callout labels (strictly separated in whitespace)
  if (anim > 0.4) {
    ctx.globalAlpha *= clamp01((anim - 0.4) / 0.4);
    const callouts: Array<{ lbl: string; bx: number; by: number; tx: number; ty: number; align: CanvasTextAlign; col: string }> = [
      { lbl: "libtaibmcf.so (12.6 MB)", bx: scaleX(12617752), by: scaleY(6.124), tx: scaleX(12617752) - 42, ty: scaleY(5.32), align: "right", col: PALETTE.amber },
      { lbl: "libfgcpkjvqe.so (6.5 MB)", bx: scaleX(6524160), by: scaleY(6.266), tx: scaleX(6524160) - 95, ty: scaleY(5.68), align: "right", col: PALETTE.amber },
      { lbl: "fgcpkjvqe.dat (H=7.9998)", bx: scaleX(776320), by: scaleY(7.9998), tx: scaleX(776320) - 96, ty: scaleY(7.68), align: "right", col: PALETTE.rose },
      { lbl: "android.1.zip (3.9 MB)", bx: scaleX(3913966), by: scaleY(7.9955), tx: scaleX(3913966) + 28, ty: scaleY(7.68), align: "left", col: PALETTE.rose },
      { lbl: "libsecurity_lite.so (.rodata H=7.91)", bx: scaleX(154840), by: scaleY(7.303), tx: scaleX(154840) - 24, ty: scaleY(6.88), align: "right", col: PALETTE.amber },
    ];
    ctx.font = "600 13px JetBrainsMono";
    for (const c of callouts) {
      ctx.strokeStyle = c.col;
      ctx.lineWidth = 1.2;
      ctx.beginPath();
      ctx.moveTo(c.bx, c.by);
      ctx.lineTo(c.tx, c.ty);
      ctx.stroke();
      ctx.fillStyle = c.col;
      ctx.textAlign = c.align;
      ctx.fillText(c.lbl, c.tx - 4, c.ty + 4);
    }
  }
  ctx.restore();
}

export function drawElfHardeningMatrix(
  ctx: SKRSContext2D,
  x: number,
  y: number,
  w: number,
  h: number,
  libs: NativeLibRecord[],
  progress: number
): void {
  const showLibs = libs.slice(0, 10);
  const extras = libs.filter((l) =>
    ["libljsfrcwmw.so", "liblytcuhe.so", "librcmefqonnx.so", "libohxbee.so", "libsecurity_lite.so", "libtrueidlib.so", "libcrashlytics-common.so", "libcrashlytics.so", "libcrashlytics-handler.so", "libcrashlytics-trampoline.so"].includes(
      l.name
    )
  );
  const merged = [...showLibs];
  for (const ex of extras) {
    if (!merged.some((m) => m.name === ex.name)) merged.push(ex);
  }
  const rows = merged.slice(0, 19);
  const cols = ["NX", "RELRO", "CANARY", "FORTIFY", "STRIP"];
  const nameW = 285;
  const colW = (w - nameW - 12) / cols.length;
  const rowH = (h - 36) / rows.length;

  ctx.save();
  ctx.font = "700 14px JetBrainsMono";
  ctx.fillStyle = PALETTE.textMuted;
  ctx.textAlign = "center";
  cols.forEach((c, ci) => {
    ctx.fillText(c, x + nameW + ci * colW + colW / 2, y + 16);
  });

  rows.forEach((lib, ri) => {
    const ry = y + 32 + ri * rowH;
    const p = easeOutCubic(clamp01((progress - ri * 0.022) / 0.45));
    ctx.globalAlpha = p;

    ctx.font = "600 14px JetBrainsMono";
    ctx.fillStyle = lib.name.includes("crashlytics") ? PALETTE.rose : PALETTE.text;
    ctx.textAlign = "left";
    ctx.textBaseline = "middle";
    ctx.fillText(lib.name, x + 4, ry + rowH / 2);

    const states = [
      lib.hardening.nx,
      lib.hardening.relro,
      lib.hardening.stack_protector,
      lib.hardening.fortify,
      lib.hardening.stripped,
    ];
    states.forEach((st, ci) => {
      const cx = x + nameW + ci * colW + 4;
      const cw = colW - 8;
      const ch = Math.max(12, rowH - 5);
      roundRectPath(ctx, cx, ry + 2.5, cw, ch, 4);
      if (st === "enabled") {
        ctx.fillStyle = "rgba(16, 185, 129, 0.25)";
        ctx.strokeStyle = PALETTE.emerald;
      } else if (st === "disabled") {
        ctx.fillStyle = "rgba(244, 63, 94, 0.28)";
        ctx.strokeStyle = PALETTE.rose;
      } else {
        ctx.fillStyle = "rgba(245, 158, 11, 0.16)";
        ctx.strokeStyle = "rgba(245, 158, 11, 0.5)";
      }
      ctx.fill();
      ctx.lineWidth = 1;
      ctx.stroke();

      ctx.font = "700 12px JetBrainsMono";
      ctx.fillStyle = st === "enabled" ? PALETTE.emerald : st === "disabled" ? PALETTE.rose : PALETTE.amber;
      ctx.textAlign = "center";
      ctx.fillText(st === "enabled" ? "ON" : st === "disabled" ? "OFF" : "UNK", cx + cw / 2, ry + rowH / 2 + 0.5);
    });
  });
  ctx.restore();
}
