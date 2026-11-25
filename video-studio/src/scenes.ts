import { type Image, type SKRSContext2D } from "@napi-rs/canvas";
import {
  PALETTE,
  W,
  H,
  clamp01,
  easeOutCubic,
  windowProgress,
  drawBackdrop,
  drawGlassPanel,
  drawPill,
  drawWrappedText,
  roundRectPath,
} from "./theme.js";
import {
  drawDonutChart,
  drawElfHardeningMatrix,
  drawEntropyScatter,
  drawHorizontalBars,
} from "./charts.js";
import { formatInt, formatMB } from "./data.js";
import type { ChapterTiming, ResearchDataset } from "./types.js";

function drawMetricCard(
  ctx: SKRSContext2D,
  x: number,
  y: number,
  w: number,
  h: number,
  value: string,
  label: string,
  sub: string,
  color: string,
  alpha: number
): void {
  drawGlassPanel(ctx, x, y, w, h, { accentTop: color, alpha });
  ctx.save();
  ctx.globalAlpha *= alpha;
  ctx.fillStyle = color;
  ctx.font = "700 42px SpaceGrotesk, BeVietnamPro";
  ctx.textBaseline = "top";
  ctx.fillText(value, x + 26, y + 26);

  ctx.fillStyle = PALETTE.text;
  ctx.font = "600 20px BeVietnamPro, SpaceGrotesk";
  ctx.fillText(label, x + 26, y + 82);

  ctx.fillStyle = PALETTE.textMuted;
  ctx.font = "400 15px JetBrainsMono, BeVietnamPro";
  ctx.fillText(sub, x + 26, y + 114);
  ctx.restore();
}

function drawChrome(
  ctx: SKRSContext2D,
  ch: ChapterTiming,
  allChapters: ChapterTiming[],
  globalSec: number,
  localSec: number,
  totalSec: number
): void {
  const ratio = clamp01(localSec / ch.durationSec);
  const headAlpha = clamp01(localSec / 0.45) * clamp01((ch.durationSec - localSec) / 0.35 + 0.25);

  // Top Header Bar
  ctx.save();
  ctx.globalAlpha = clamp01(headAlpha);
  drawPill(ctx, 80, 36, ch.tag, PALETTE.cyan);

  // Right study badge
  ctx.textAlign = "right";
  ctx.font = "600 16px JetBrainsMono";
  ctx.fillStyle = PALETTE.textMuted;
  ctx.fillText("ZALOPAY v11.17.0 · REA v6.10.1 STATIC CORPUS", W - 80, 56);

  // Title + Subtitle
  ctx.textAlign = "left";
  ctx.textBaseline = "top";
  ctx.font = "700 40px SpaceGrotesk, BeVietnamPro";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText(ch.title, 80, 82);

  ctx.font = "400 20px BeVietnamPro";
  ctx.fillStyle = PALETTE.textMuted;
  ctx.fillText(ch.subtitle, 80, 128);
  ctx.restore();

  // Bottom Captions Strip + Progress Rail
  const activeCap =
    ch.captions.find((c) => ratio >= c.startRatio && ratio <= c.endRatio) ??
    ch.captions[ch.captions.length - 1];

  if (activeCap) {
    drawGlassPanel(ctx, 80, 954, W - 160, 68, {
      radius: 12,
      borderColor: "rgba(56, 189, 248, 0.24)",
      fill: "rgba(7, 11, 20, 0.90)",
    });
    ctx.save();
    ctx.fillStyle = PALETTE.cyan;
    ctx.fillRect(80, 968, 4, 40);
    ctx.font = "600 21px BeVietnamPro, SpaceGrotesk";
    ctx.fillStyle = PALETTE.text;
    ctx.textBaseline = "middle";
    ctx.textAlign = "left";
    ctx.fillText(activeCap.text, 104, 988);
    ctx.restore();
  }

  // Chapter Segmented Timeline Rail (y = 1040..1048)
  const railX = 80;
  const railW = W - 160;
  const railY = 1040;
  let curX = railX;
  for (const item of allChapters) {
    const segW = (item.durationSec / totalSec) * railW - 6;
    ctx.save();
    roundRectPath(ctx, curX, railY, Math.max(8, segW), 6, 3);
    ctx.fillStyle = "rgba(148, 163, 184, 0.18)";
    ctx.fill();

    let fillFrac = 0;
    if (globalSec >= item.endSec) fillFrac = 1;
    else if (globalSec > item.startSec) fillFrac = (globalSec - item.startSec) / item.durationSec;
    if (fillFrac > 0) {
      roundRectPath(ctx, curX, railY, Math.max(6, segW * fillFrac), 6, 3);
      ctx.fillStyle = item.index === ch.index ? PALETTE.cyan : PALETTE.emerald;
      ctx.fill();
    }
    ctx.restore();
    curX += segW + 6;
  }

  // Timestamp bottom-right
  ctx.save();
  ctx.font = "600 14px JetBrainsMono";
  ctx.fillStyle = PALETTE.textDim;
  ctx.textAlign = "right";
  const m = Math.floor(globalSec / 60);
  const s = Math.floor(globalSec % 60);
  const tm = Math.floor(totalSec / 60);
  const ts = Math.floor(totalSec % 60);
  ctx.fillText(
    `${String(m).padStart(2, "0")}:${String(s).padStart(2, "0")} / ${String(tm).padStart(2, "0")}:${String(ts).padStart(2, "0")}`,
    W - 80,
    1066
  );
  ctx.restore();
}

// ---------------------------------------------------------------------------
// Scene 1: Opening Thesis & Target Provenance
// ---------------------------------------------------------------------------
function drawScene1(
  ctx: SKRSContext2D,
  t: number,
  d: ResearchDataset,
  icon: Image | null
): void {
  const p1 = windowProgress(t, 0.2, 0.9);
  const p2 = windowProgress(t, 0.9, 1.0);
  const p3 = windowProgress(t, 1.8, 1.2);

  // Left Focal Artifact Card with ZaloPay Emblem + Rotating Telemetry Ring
  drawGlassPanel(ctx, 80, 174, 700, 748, { accentTop: PALETTE.cyan, alpha: p1 });
  ctx.save();
  ctx.globalAlpha = p1;

  // Cryptographic Orbit Rings around Icon
  const cx = 430;
  const cy = 380;
  ctx.save();
  ctx.translate(cx, cy);
  ctx.rotate(t * 0.25);
  ctx.strokeStyle = "rgba(56, 189, 248, 0.35)";
  ctx.lineWidth = 2;
  ctx.setLineDash([12, 10]);
  ctx.beginPath();
  ctx.arc(0, 0, 145, 0, Math.PI * 2);
  ctx.stroke();
  ctx.rotate(-t * 0.45);
  ctx.strokeStyle = "rgba(16, 185, 129, 0.38)";
  ctx.setLineDash([6, 12]);
  ctx.beginPath();
  ctx.arc(0, 0, 168, 0, Math.PI * 2);
  ctx.stroke();
  ctx.setLineDash([]);
  ctx.restore();

  if (icon) {
    ctx.save();
    roundRectPath(ctx, cx - 104, cy - 104, 208, 208, 44);
    ctx.clip();
    ctx.drawImage(icon, cx - 104, cy - 104, 208, 208);
    ctx.restore();
    ctx.strokeStyle = PALETTE.cyan;
    ctx.lineWidth = 2.5;
    roundRectPath(ctx, cx - 104, cy - 104, 208, 208, 44);
    ctx.stroke();
  }

  ctx.textAlign = "center";
  ctx.fillStyle = PALETTE.text;
  ctx.font = "700 32px BeVietnamPro, SpaceGrotesk";
  ctx.fillText("Zalopay: Thanh toán & Tài chính", cx, 590);

  ctx.fillStyle = PALETTE.cyan;
  ctx.font = "600 20px JetBrainsMono";
  ctx.fillText("vn.com.vng.zalopay · v11.17.0 (1102758)", cx, 632);

  ctx.textAlign = "left";
  const rows = [
    ["Outer ZIP SHA-256", `${d.target.outer_zip.sha256.slice(0, 26)}...`],
    ["XAPK Container", `${formatMB(d.target.xapk.bytes)} · 7 members`],
    ["SDK Target Range", `minSdk ${d.manifest.min_sdk} (O) → targetSdk ${d.manifest.target_sdk} (A16)`],
    ["Tooling", "REA v6.10.1 + Androguard + LIEF + Capstone"],
  ];
  let ry = 690;
  for (const [k, v] of rows) {
    ctx.fillStyle = PALETTE.textMuted;
    ctx.font = "600 16px BeVietnamPro";
    ctx.fillText(k, 120, ry);
    ctx.fillStyle = PALETTE.text;
    ctx.font = "400 16px JetBrainsMono";
    ctx.fillText(v, 315, ry);
    ry += 44;
  }
  ctx.restore();

  // Right 2x3 Quantitative KPI Grid
  const totInstr =
    d.dex_and_bytecode.primary_totals.total_dalvik_instructions +
    d.dex_and_bytecode.audience_network_dynamic_dex.counts.total_dalvik_instructions;

  const cards: Array<[string, string, string, string]> = [
    ["5 Split APKs", "Distribution Corpus", "Base + ARMv7 + xhdpi + vi + en", PALETTE.cyan],
    ["20 DEX Files", "Managed Bytecode", `${formatInt(totInstr)} Dalvik instructions`, PALETTE.emerald],
    ["60,671 Classes", "Primary Type Graph", `${d.dex_and_bytecode.obfuscation_rates.short_class_name_pct}% R8 obfuscated`, PALETTE.violet],
    ["27 ARMv7 .so", "Native Codebase", `${formatMB(d.native_elf_summary.total_bytes)} ELF binaries`, PALETTE.amber],
    ["124 Assets", "Embedded Runtimes", "React Native + Griver + NCNN/TFLite", PALETTE.rose],
    ["267 IPC Units", "Manifest Surface", `49 perms · 7 isolated processes`, PALETTE.cyan],
  ];

  cards.forEach(([val, lbl, sub, col], idx) => {
    const colIdx = idx % 2;
    const rowIdx = Math.floor(idx / 2);
    const cp = windowProgress(t, 0.7 + idx * 0.25, 0.7);
    const kx = 815 + colIdx * 525;
    const ky = 174 + rowIdx * 255;
    drawMetricCard(ctx, kx, ky, 500, 232, val, lbl, sub, col, cp * p2);
  });

  // Subtle formula badge bottom of left card
  if (p3 > 0) {
    ctx.save();
    ctx.globalAlpha = p3;
    ctx.font = "400 15px JetBrainsMono";
    ctx.fillStyle = PALETTE.emerald;
    ctx.fillText("100% Artifact-Bound Static Evidence · Zero Dynamic Execution", 120, 882);
    ctx.restore();
  }
}

// ---------------------------------------------------------------------------
// Scene 2: Distribution Packaging, Signatures & Resource Splits
// ---------------------------------------------------------------------------
function drawScene2(ctx: SKRSContext2D, t: number, d: ResearchDataset): void {
  const p1 = windowProgress(t, 0.2, 0.9);
  const p2 = windowProgress(t, 1.2, 1.0);
  const p3 = windowProgress(t, 2.5, 1.0);

  // Left: 5-APK Breakdown Bars
  drawGlassPanel(ctx, 80, 174, 880, 420, { accentTop: PALETTE.cyan, alpha: p1 });
  ctx.save();
  ctx.globalAlpha = p1;
  ctx.font = "700 24px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("XAPK Split Member Footprint (Compressed vs Uncompressed)", 110, 212);
  ctx.restore();

  const apks = [
    { label: "vn.com.vng.zalopay.apk", value: 79359087, color: PALETTE.cyan, displayValue: "79.36 MB (6,162)" },
    { label: "config.armeabi_v7a.apk", value: 24828408, color: PALETTE.amber, displayValue: "24.83 MB (32)" },
    { label: "config.xhdpi.apk", value: 1232563, color: PALETTE.violet, displayValue: "1.23 MB (258)" },
    { label: "config.vi.apk", value: 86425, color: PALETTE.emerald, displayValue: "86.4 KB (6)" },
    { label: "config.en.apk", value: 74137, color: PALETTE.textMuted, displayValue: "74.1 KB (6)" },
  ];
  drawHorizontalBars(ctx, 100, 255, 840, 315, apks, p1, { labelWidth: 250, valueWidth: 195 });

  // Bottom-Left: Signature Parity Card
  drawGlassPanel(ctx, 80, 620, 880, 302, { accentTop: PALETTE.emerald, alpha: p2 });
  ctx.save();
  ctx.globalAlpha = p2;
  ctx.font = "700 24px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("REA Cryptographic Signature & Provenance Audit", 110, 656);

  const sigLines = [
    ["Signing Certificate", "CN=Vu Manh Hieu · RSA · Serial 153EA9FD (2016–2041)", PALETTE.text],
    ["Fingerprint SHA-256", "59dd31fdf36070a0d7f8c8cde843fac371df7f1a915c0136...", PALETTE.cyan],
    ["Base APK Scheme", "v2 + v3 ONLY (v1 JAR dropped; minSdk=26)", PALETTE.emerald],
    ["Split APKs Scheme", "v1 (BNDLTOOL.RSA) + v2 + v3 · 100% Signer Parity", PALETTE.emerald],
    ["Google SourceStamp", "Present on 5/5 APKs · O=Google Inc., CN=Android", PALETTE.violet],
  ];
  let sy = 706;
  for (const [k, v, c] of sigLines) {
    ctx.font = "600 17px BeVietnamPro";
    ctx.fillStyle = PALETTE.textMuted;
    ctx.fillText(k, 110, sy);
    ctx.font = "600 16px JetBrainsMono";
    ctx.fillStyle = c;
    ctx.fillText(v, 315, sy);
    sy += 40;
  }
  ctx.restore();

  // Right: resources.arsc & Locale Comparison
  drawGlassPanel(ctx, 990, 174, 850, 748, { accentTop: PALETTE.violet, alpha: p3 });
  ctx.save();
  ctx.globalAlpha = p3;
  ctx.font = "700 24px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("Resource Table Architecture (resources.arsc)", 1025, 212);

  ctx.font = "400 18px BeVietnamPro";
  ctx.fillStyle = PALETTE.textMuted;
  ctx.fillText("Base APK declares 14,680 resource keys across 21 Android resource types", 1025, 248);
  ctx.restore();

  const resBars = [
    { label: "id", value: 3460, color: PALETTE.cyan, displayValue: "3,460" },
    { label: "drawable", value: 3199, color: PALETTE.violet, displayValue: "3,199" },
    { label: "string (base)", value: 2100, color: PALETTE.emerald, displayValue: "2,100" },
    { label: "attr", value: 1826, color: PALETTE.amber, displayValue: "1,826" },
    { label: "layout", value: 1616, color: PALETTE.cyan, displayValue: "1,616" },
    { label: "color + style", value: 1503, color: PALETTE.rose, displayValue: "1,503" },
    { label: "dimen", value: 688, color: PALETTE.textMuted, displayValue: "688" },
  ];
  drawHorizontalBars(ctx, 1015, 290, 800, 340, resBars, p3, { labelWidth: 165, valueWidth: 100 });

  // Locale asymmetry highlight box
  drawGlassPanel(ctx, 1025, 656, 780, 232, {
    fill: "rgba(16, 185, 129, 0.08)",
    borderColor: PALETTE.emerald,
    alpha: p3,
  });
  ctx.save();
  ctx.globalAlpha = p3;
  ctx.fillStyle = PALETTE.emerald;
  ctx.font = "700 21px BeVietnamPro";
  ctx.fillText("Locale Split Asymmetry: Vietnamese Primary Focus", 1055, 692);
  ctx.fillStyle = PALETTE.text;
  ctx.font = "600 23px SpaceGrotesk";
  ctx.fillText("config.vi: 496 keys (492 strings)  vs  config.en: 370 keys", 1055, 740);
  ctx.fillStyle = PALETTE.textMuted;
  ctx.font = "400 18px BeVietnamPro";
  drawWrappedText(
    ctx,
    "34% higher Vietnamese localization density reflects domestic VNPAY, eKYC, and taxi flows, paired with 6.96 MB res/raw/ Lottie & OpenCV assets.",
    1055,
    788,
    720,
    26
  );
  ctx.restore();
}

// ---------------------------------------------------------------------------
// Scene 3: Manifest IPC Surface, Permissions & Network Security Policy
// ---------------------------------------------------------------------------
function drawScene3(ctx: SKRSContext2D, t: number, d: ResearchDataset): void {
  const p1 = windowProgress(t, 0.2, 1.0);
  const p2 = windowProgress(t, 1.3, 1.0);
  const p3 = windowProgress(t, 2.8, 1.0);

  // Left: Permission Taxonomy (49 declared)
  drawGlassPanel(ctx, 80, 174, 860, 410, { accentTop: PALETTE.amber, alpha: p1 });
  ctx.save();
  ctx.globalAlpha = p1;
  ctx.font = "700 24px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("Permission Taxonomy (49 Manifest Permissions)", 112, 212);
  ctx.restore();

  const pc = d.manifest.permission_categories;
  const permBars = [
    { label: "Dangerous Runtime", value: pc.dangerous_runtime ?? 14, color: PALETTE.rose, displayValue: "14 (Cam/Mic/Loc/Contacts)" },
    { label: "Normal System", value: pc.normal_system ?? 10, color: PALETTE.cyan, displayValue: "10 (Net/Boot/FG Service)" },
    { label: "OEM Launchers", value: pc.oem_launcher_vendor ?? 9, color: PALETTE.textMuted, displayValue: "9 (Samsung/Huawei/Vivo)" },
    { label: "Security & Special", value: pc.special_or_security ?? 6, color: PALETTE.amber, displayValue: "6 (Biometric/NFC/ScreenCap)" },
    { label: "Privacy Sandbox", value: pc.adservices_privacy_sandbox ?? 4, color: PALETTE.violet, displayValue: "4 (AdServices Attribution)" },
    { label: "SDK & Ecosystem", value: (pc.third_party_sdk ?? 4) + (pc.first_party_zalo_ecosystem ?? 2), color: PALETTE.emerald, displayValue: "6 (Zalo Auth + Billing)" },
  ];
  drawHorizontalBars(ctx, 100, 255, 810, 305, permBars, p1, { labelWidth: 205, valueWidth: 255 });

  // Bottom-Left: IPC Component Exposure & Multi-process Isolation
  drawGlassPanel(ctx, 80, 608, 860, 314, { accentTop: PALETTE.cyan, alpha: p2 });
  ctx.save();
  ctx.globalAlpha = p2;
  ctx.font = "700 24px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("IPC Boundary Exposure (267 Components · 7 Processes)", 112, 646);

  const cs = d.manifest.component_summary;
  const rows: Array<[string, number, number, number, string]> = [
    ["Activities + Aliases", cs.activity.total + cs["activity-alias"].total, cs.activity.exported + cs["activity-alias"].exported, cs.activity.exported_without_permission + cs["activity-alias"].exported_without_permission, PALETTE.amber],
    ["Services (HCE / Jobs)", cs.service.total, cs.service.exported, cs.service.exported_without_permission, PALETTE.cyan],
    ["Broadcast Receivers", cs.receiver.total, cs.receiver.exported, cs.receiver.exported_without_permission, PALETTE.amber],
    ["Content Providers", cs.provider.total, cs.provider.exported, cs.provider.exported_without_permission, PALETTE.emerald],
  ];

  ctx.font = "600 15px JetBrainsMono";
  ctx.fillStyle = PALETTE.textMuted;
  ctx.fillText("TYPE", 112, 694);
  ctx.fillText("TOTAL", 430, 694);
  ctx.fillText("EXPORTED", 545, 694);
  ctx.fillText("NO-PERM", 695, 694);

  let ry = 730;
  for (const [lbl, tot, exp, nop, col] of rows) {
    ctx.font = "600 18px BeVietnamPro";
    ctx.fillStyle = PALETTE.text;
    ctx.fillText(lbl, 112, ry);
    ctx.font = "700 19px JetBrainsMono";
    ctx.fillStyle = PALETTE.textMuted;
    ctx.fillText(String(tot), 430, ry);
    ctx.fillStyle = col;
    ctx.fillText(String(exp), 545, ry);
    ctx.fillStyle = nop === 0 ? PALETTE.emerald : PALETTE.rose;
    ctx.fillText(String(nop), 695, ry);
    ry += 42;
  }

  ctx.font = "600 15px JetBrainsMono";
  ctx.fillStyle = PALETTE.cyan;
  ctx.fillText("Processes: :lite1..:lite5  :taibmcf  :xcmzqsawpqoefcds", 112, 892);
  ctx.restore();

  // Right: Trust Boundary Divergence Spotlight (network_security_config.xml)
  drawGlassPanel(ctx, 970, 174, 870, 748, { accentTop: PALETTE.rose, alpha: p3 });
  ctx.save();
  ctx.globalAlpha = p3;
  drawPill(ctx, 1005, 212, "CRITICAL ARCHITECTURAL FINDING", PALETTE.rose);
  ctx.font = "700 28px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("Declarative vs. Programmatic TLS Trust", 1005, 264);

  // XML Code Box
  drawGlassPanel(ctx, 1005, 314, 800, 232, {
    fill: "#04070E",
    borderColor: "rgba(244, 63, 94, 0.4)",
    radius: 10,
  });
  const xmlLines = [
    `<network-security-config>`,
    `  <domain-config>`,
    `    <domain includeSubdomains="true">storage.googleapis.com</domain>`,
    `  </domain-config>`,
    `  <base-config cleartextTrafficPermitted="true">  <!-- ! -->`,
    `    <trust-anchors><certificates src="system"/></trust-anchors>`,
    `  </base-config>`,
    `</network-security-config>`,
  ];
  ctx.font = "400 16px JetBrainsMono";
  xmlLines.forEach((line, i) => {
    ctx.fillStyle = line.includes("cleartextTrafficPermitted") ? PALETTE.rose : PALETTE.cyan;
    ctx.fillText(line, 1028, 344 + i * 25);
  });

  // Security Assessment Cards
  const findings: Array<[string, string, string]> = [
    ["POSITIVE BASELINE", "allowBackup=false · debuggable=false · 0/9 exported providers · VNeID (com.vnid) & Zalo scoped via <queries>.", PALETTE.emerald],
    ["POLICY DIVERGENCE", "Base config allows cleartext HTTP globally & lacks declarative <pin-set> anchors; pinning relies solely on OkHttp & native SDK code.", PALETTE.rose],
    ["NFC PAYMENT SURFACE", "EMVCo Host Card Emulation (apdu_service.xml) exports payment routing for 2PAY.SYS.DDF01 & Visa AIDs with requireDeviceUnlock=false.", PALETTE.amber],
  ];
  let fy = 574;
  for (const [tag, body, col] of findings) {
    drawGlassPanel(ctx, 1005, fy, 800, 104, { fill: "rgba(15, 23, 42, 0.7)", borderColor: col, radius: 10 });
    ctx.fillStyle = col;
    ctx.font = "700 15px JetBrainsMono";
    ctx.fillText(tag, 1025, fy + 16);
    ctx.fillStyle = PALETTE.text;
    ctx.font = "400 17px BeVietnamPro";
    drawWrappedText(ctx, body, 1025, fy + 44, 760, 24);
    fy += 116;
  }
  ctx.restore();
}

// ---------------------------------------------------------------------------
// Scene 4: 20-DEX Morphology, R8 Obfuscation & Dalvik Opcode Profile
// ---------------------------------------------------------------------------
function drawScene4(ctx: SKRSContext2D, t: number, d: ResearchDataset): void {
  const p1 = windowProgress(t, 0.2, 1.0);
  const p2 = windowProgress(t, 1.2, 1.0);
  const p3 = windowProgress(t, 2.6, 1.0);

  // Left: DEX & Opcode Donut + Obfuscation Scorecard
  drawGlassPanel(ctx, 80, 174, 880, 748, { accentTop: PALETTE.emerald, alpha: p1 });
  ctx.save();
  ctx.globalAlpha = p1;
  ctx.font = "700 24px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("Dalvik Bytecode Instruction Profile (8.71M Primary)", 112, 212);
  ctx.restore();

  const fams = d.dex_and_bytecode.opcode_families;
  const totalOps = d.dex_and_bytecode.primary_totals.total_dalvik_instructions;
  const slices = [
    { label: "invoke-* Calls", value: fams.invoke_call, color: PALETTE.cyan, pctText: `${((fams.invoke_call / totalOps) * 100).toFixed(1)}%` },
    { label: "move* Register", value: fams.move_and_result, color: PALETTE.emerald, pctText: `${((fams.move_and_result / totalOps) * 100).toFixed(1)}%` },
    { label: "Arith & Logic", value: fams.binary_arithmetic_logic, color: PALETTE.violet, pctText: `${((fams.binary_arithmetic_logic / totalOps) * 100).toFixed(1)}%` },
    { label: "const* Loads", value: fams.const_load, color: PALETTE.amber, pctText: `${((fams.const_load / totalOps) * 100).toFixed(1)}%` },
    { label: "Field Access", value: fams.instance_field_get_put + fams.static_field_get_put, color: PALETTE.rose, pctText: `${(((fams.instance_field_get_put + fams.static_field_get_put) / totalOps) * 100).toFixed(1)}%` },
    { label: "Check / Branch / Flow", value: totalOps - fams.invoke_call - fams.move_and_result - fams.binary_arithmetic_logic - fams.const_load - fams.instance_field_get_put - fams.static_field_get_put, color: PALETTE.textMuted, pctText: "30.7%" },
  ];
  drawDonutChart(ctx, 310, 445, 165, 96, slices, "8.71M", "DALVIK OPS", p1);

  // Legend right of donut
  ctx.save();
  ctx.globalAlpha = p1;
  let ly = 305;
  for (const s of slices) {
    ctx.fillStyle = s.color;
    roundRectPath(ctx, 520, ly, 16, 16, 4);
    ctx.fill();
    ctx.fillStyle = PALETTE.text;
    ctx.font = "600 18px BeVietnamPro";
    ctx.fillText(s.label, 550, ly - 2);
    ctx.fillStyle = s.color;
    ctx.font = "700 17px JetBrainsMono";
    ctx.textAlign = "right";
    ctx.fillText(s.pctText, 920, ly - 1);
    ctx.textAlign = "left";
    ly += 48;
  }

  // R8 Quantitative Obfuscation Panel
  drawGlassPanel(ctx, 112, 650, 816, 240, {
    fill: "rgba(167, 139, 250, 0.08)",
    borderColor: PALETTE.violet,
    alpha: p2,
  });
  ctx.globalAlpha = p2;
  ctx.fillStyle = PALETTE.violet;
  ctx.font = "700 18px JetBrainsMono";
  ctx.fillText("R8 / PROGUARD QUANTITATIVE OBFUSCATION INDEX", 140, 682);

  const obf = d.dex_and_bytecode.obfuscation_rates;
  const obfStats: Array<[string, string, string]> = [
    [`${obf.short_class_name_pct}%`, "Short Leaf Classes", "41,374 / 60,671 (≤2 chars)"],
    [`${obf.obfuscated_root_pkg_pct}%`, "Obfuscated Roots", "29,210 classes in short pkgs"],
    [`${obf.short_method_name_pct}%`, "Short Method IDs", "73,786 / 264,525 defined"],
  ];
  obfStats.forEach(([num, lbl, sub], i) => {
    const ox = 140 + i * 265;
    ctx.fillStyle = PALETTE.text;
    ctx.font = "700 42px SpaceGrotesk";
    ctx.fillText(num, ox, 728);
    ctx.fillStyle = PALETTE.violet;
    ctx.font = "600 17px BeVietnamPro";
    ctx.fillText(lbl, ox, 782);
    ctx.fillStyle = PALETTE.textMuted;
    ctx.font = "400 14px JetBrainsMono";
    ctx.fillText(sub, ox, 814);
  });
  ctx.restore();

  // Right: Multi-DEX & Ecosystem Namespace Distribution
  drawGlassPanel(ctx, 990, 174, 850, 748, { accentTop: PALETTE.cyan, alpha: p3 });
  ctx.save();
  ctx.globalAlpha = p3;
  ctx.font = "700 24px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("Dominant Managed Subsystems (DEX Classes)", 1025, 212);
  ctx.fillStyle = PALETTE.textMuted;
  ctx.font = "400 17px BeVietnamPro";
  ctx.fillText("Kotlin 2.2.10 · Gradle 8.14.5 · JVM 17 · 141 SBOM versions + 69 properties", 1025, 248);
  ctx.restore();

  const fc = d.dex_and_bytecode.framework_and_security_classes;
  const subBars = [
    { label: "Alipay+ Griver/Ariver", value: fc.alipay_griver_miniapp, color: PALETTE.violet, displayValue: "5,136 cls" },
    { label: "BouncyCastle (PQC+JCA)", value: 4900, color: PALETTE.emerald, displayValue: "~4,900 cls" },
    { label: "Audience Net Dynamic DEX", value: 3156, color: PALETTE.rose, displayValue: "3,156 cls (4.86 MB)" },
    { label: "Visa CBP + NFC HCE", value: fc.visa_cbp_nfc_hce, color: PALETTE.amber, displayValue: "1,370 cls" },
    { label: "VNG ZaloPay First-Party", value: fc.vng_zalopay_first_party, color: PALETTE.cyan, displayValue: "961 cls" },
    { label: "TrueID / FaceAuth eKYC", value: fc.trueid_faceauth_ekyc, color: PALETTE.emerald, displayValue: "689 cls" },
    { label: "Jetpack Compose 1.7.2", value: fc.jetpack_compose, color: PALETTE.cyan, displayValue: "504 cls" },
    { label: "AppsFlyer / Rive / MLKit", value: fc.appsflyer_attribution + fc.rive_lottie_motion + fc.google_mlkit_vision, color: PALETTE.textMuted, displayValue: "900 cls" },
  ];
  drawHorizontalBars(ctx, 1015, 290, 800, 460, subBars, p3, { labelWidth: 245, valueWidth: 215 });

  // Bottom highlight box
  drawGlassPanel(ctx, 1025, 770, 780, 122, {
    fill: "rgba(56, 189, 248, 0.08)",
    borderColor: PALETTE.cyan,
    alpha: p3,
  });
  ctx.save();
  ctx.globalAlpha = p3;
  ctx.fillStyle = PALETTE.cyan;
  ctx.font = "700 17px JetBrainsMono";
  ctx.fillText("JNI BRIDGE DENSITY & HOTSPOT", 1052, 796);
  ctx.fillStyle = PALETTE.text;
  ctx.font = "400 18px BeVietnamPro";
  drawWrappedText(
    ctx,
    "2,026 native JNI method declarations span 405 classes (led by obfuscated Ljipm/njcx [53] & Lwmoi/igob [50]); classes2.dex alone houses 3.06M instructions.",
    1052,
    828,
    730,
    25
  );
  ctx.restore();
}

// ---------------------------------------------------------------------------
// Scene 5: Tri-Engine Hybrid Super-App Architecture
// ---------------------------------------------------------------------------
function drawScene5(ctx: SKRSContext2D, t: number, d: ResearchDataset): void {
  const p1 = windowProgress(t, 0.2, 0.9);
  const p2 = windowProgress(t, 1.0, 1.0);
  const p3 = windowProgress(t, 1.9, 1.0);

  const engines = [
    {
      x: 80,
      col: PALETTE.cyan,
      alpha: p1,
      badge: "ENGINE 1 · NATIVE ANDROID",
      title: "Kotlin + Jetpack Compose",
      metrics: [
        "Kotlin 2.2.10 · Compose 1.7.2",
        "504 Compose UI Classes",
        "CameraX 1.4.0 · Room 2.8.4",
        "Media3 ExoPlayer · Rive GLES3",
      ],
      foot: "Main Host & Financial Core",
    },
    {
      x: 676,
      col: PALETTE.amber,
      alpha: p2,
      badge: "ENGINE 2 · METRO JS BUNDLE",
      title: "Embedded React Native",
      metrics: [
        `assets/android.1.zip (${formatMB(3913966)})`,
        "main.jsbundle: 3,613,478 B",
        `${formatInt(d.hybrid_runtimes_and_ai.react_native.metro_module_define_count)} Metro __d() Modules`,
        "325 Drawable Feature Assets",
      ],
      foot: "Tx History · Clearing · Banking",
    },
    {
      x: 1272,
      col: PALETTE.violet,
      alpha: p3,
      badge: "ENGINE 3 · MINI-PROGRAM SANDBOX",
      title: "Alipay+ Griver / Ariver",
      metrics: [
        "5,136 Ariver + Griver + IAP Classes",
        "5 Processes (:lite1 – :lite5)",
        "3 Pre-Bundled .amr (TAR+CERT)",
        "8 Bridge JS (5,361 AST Nodes)",
      ],
      foot: "Multi-Tenant Merchant Ecosystem",
    },
  ];

  for (const e of engines) {
    drawGlassPanel(ctx, e.x, 174, 568, 520, { accentTop: e.col, alpha: e.alpha });
    ctx.save();
    ctx.globalAlpha = e.alpha;
    drawPill(ctx, e.x + 28, 206, e.badge, e.col);

    ctx.fillStyle = PALETTE.text;
    ctx.font = "700 30px SpaceGrotesk";
    ctx.fillText(e.title, e.x + 28, 260);

    let my = 322;
    for (const m of e.metrics) {
      drawGlassPanel(ctx, e.x + 28, my, 512, 62, {
        fill: "rgba(15, 23, 42, 0.72)",
        borderColor: "rgba(148, 163, 184, 0.2)",
        radius: 10,
      });
      ctx.fillStyle = e.col;
      ctx.beginPath();
      ctx.arc(e.x + 50, my + 31, 5, 0, Math.PI * 2);
      ctx.fill();
      ctx.fillStyle = PALETTE.text;
      ctx.font = "600 18px JetBrainsMono, BeVietnamPro";
      ctx.fillText(m, e.x + 68, my + 22);
      my += 76;
    }

    ctx.fillStyle = e.col;
    ctx.font = "600 17px BeVietnamPro";
    ctx.fillText(`Role: ${e.foot}`, e.x + 28, 650);
    ctx.restore();
  }

  // Bottom Convergence Bus connecting all 3 engines
  const pBus = windowProgress(t, 2.6, 1.0);
  drawGlassPanel(ctx, 80, 722, 1760, 200, { accentTop: PALETTE.emerald, alpha: pBus });
  ctx.save();
  ctx.globalAlpha = pBus;
  ctx.font = "700 24px SpaceGrotesk";
  ctx.fillStyle = PALETTE.emerald;
  ctx.fillText("Unified Security & Bridge Spine Across All Three Execution Engines", 116, 756);

  const spineNodes = [
    ["JS Bridge Surface (REA)", "8 bridge scripts · 0 parse errors · appx_inline / h5_bridge / workerjs"],
    ["Griver .amr Verification", "ZIP → CERT.json + Manifest.xml + TAR (appx-ng/af-appx.min.js)"],
    ["Shared Native Vault & DB", "libsecurity_lite.so + libsqlcipher.so (480 exports) + libtaibmcf.so"],
  ];
  spineNodes.forEach(([title, desc], i) => {
    const sx = 116 + i * 575;
    drawGlassPanel(ctx, sx, 800, 545, 96, {
      fill: "rgba(16, 185, 129, 0.08)",
      borderColor: "rgba(16, 185, 129, 0.35)",
      radius: 10,
    });
    ctx.fillStyle = PALETTE.text;
    ctx.font = "700 18px BeVietnamPro";
    ctx.fillText(title, sx + 18, 818);
    ctx.fillStyle = PALETTE.textMuted;
    ctx.font = "400 15px JetBrainsMono";
    drawWrappedText(ctx, desc, sx + 18, 846, 510, 21);
  });
  ctx.restore();
}

// ---------------------------------------------------------------------------
// Scene 6: Edge AI eKYC Vision & Contactless HCE / X25519 Hardware Stack
// ---------------------------------------------------------------------------
function drawScene6(ctx: SKRSContext2D, t: number): void {
  const p1 = windowProgress(t, 0.2, 1.0);
  const p2 = windowProgress(t, 1.5, 1.0);

  // Left: 4 Embedded Computer Vision Pipelines
  drawGlassPanel(ctx, 80, 174, 860, 748, { accentTop: PALETTE.cyan, alpha: p1 });
  ctx.save();
  ctx.globalAlpha = p1;
  ctx.font = "700 24px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("Quad-Engine Edge Computer Vision & eKYC Stack", 112, 212);

  const aiStacks = [
    [
      "1. Tencent NCNN FastestDet",
      "libpicodet_ncnn.so (2.99 MB, 2,152 exports) + FastestDet.ncnn.bin (2.42 MB)",
      "172 Layers · 198 Blobs · 52 Conv + 14 DepthWise + 18 HardSwish + OpenMP",
      PALETTE.cyan,
    ],
    [
      "2. TensorFlow Lite Custom Models",
      "object_labeler.tflite (3.77 MB, TFL3 FlatBuffer) + tflite_langid.tflite (315 KB)",
      "Document / ID-card classification & text language detection offline",
      PALETTE.violet,
    ],
    [
      "3. Google ML Kit Bundled Pipelines",
      "mlkitcommonpipeline (6.93 MB) · google_ocr (6.78 MB) · face_detector_v2 (5.39 MB)",
      "19.1 MB combined native models + libbarhopper_v3.so (3.24 MB QR/VietQR)",
      PALETTE.emerald,
    ],
    [
      "4. OpenCV & TrueID FaceAuth",
      "libVFaceLib.so (1.96 MB) + haarcascade_frontalface_default.xml (1.79 MB)",
      "35 native JNI FaceMatch methods + Lottie 3D NFC/eKYC guidance",
      PALETTE.amber,
    ],
  ];

  let ay = 266;
  for (const [title, sub1, sub2, col] of aiStacks) {
    drawGlassPanel(ctx, 112, ay, 796, 146, {
      fill: "rgba(15, 23, 42, 0.72)",
      borderColor: col,
      radius: 12,
    });
    ctx.fillStyle = col;
    ctx.font = "700 20px SpaceGrotesk";
    ctx.fillText(title, 136, ay + 20);
    ctx.fillStyle = PALETTE.text;
    ctx.font = "600 15px JetBrainsMono";
    ctx.fillText(sub1, 136, ay + 54);
    ctx.fillStyle = PALETTE.textMuted;
    ctx.font = "400 17px BeVietnamPro";
    ctx.fillText(sub2, 136, ay + 90);
    ay += 162;
  }
  ctx.restore();

  // Right: Contactless EMVCo NFC HCE + X25519 SmartOTP Cryptographic Enclave
  drawGlassPanel(ctx, 970, 174, 870, 360, { accentTop: PALETTE.amber, alpha: p2 });
  ctx.save();
  ctx.globalAlpha = p2;
  ctx.font = "700 24px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("EMVCo NFC HCE & Visa Cloud-Based Payments (CBP)", 1005, 212);

  const aids = [
    ["325041592E5359532E4444463031", "EMVCo PPSE (2PAY.SYS.DDF01)"],
    ["A0000000031010", "Visa Credit / Debit AID"],
    ["A0000000032010 / 2020 / 3010", "Visa Electron / V PAY / Interlink"],
    ["A0000000980840", "US Common Debit AID"],
  ];
  let hy = 264;
  for (const [hex, desc] of aids) {
    ctx.fillStyle = PALETTE.amber;
    ctx.font = "600 16px JetBrainsMono";
    ctx.fillText(hex, 1005, hy);
    ctx.fillStyle = PALETTE.text;
    ctx.font = "400 17px BeVietnamPro";
    ctx.fillText(desc, 1375, hy);
    hy += 38;
  }
  ctx.fillStyle = PALETTE.rose;
  ctx.font = "600 16px JetBrainsMono";
  ctx.fillText("+ 4 obfuscated assets/com/visa/cbp/* payloads (H = 7.98–7.999)", 1005, 434);
  ctx.fillStyle = PALETTE.textMuted;
  ctx.font = "400 16px BeVietnamPro";
  ctx.fillText("Magic header 'java/lang/Object' preceding encrypted class bytes.", 1005, 466);
  ctx.restore();

  // Bottom-Right: TrueID SmartOTP X25519 + RASP
  drawGlassPanel(ctx, 970, 560, 870, 362, { accentTop: PALETTE.emerald, alpha: p2 });
  ctx.save();
  ctx.globalAlpha = p2;
  ctx.font = "700 24px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("X25519 SmartOTP Vault & Anti-Tamper Telemetry", 1005, 598);

  const vaultBox = [
    ["libtrueidlib.so (79.9 KB)", "Java_vn_ai_trueid_sdk_utils_SmartOTPHelper_{vaultX25519Pubkey, vaultAuthCeremony, vaultComputeQReg} + hmac_sha256", PALETTE.emerald],
    ["Runtime Integrity Checks", "Static strings probe ro.debuggable, frida, magisk, /proc/self/maps, and TrueIDSSLPinningConfig.", PALETTE.cyan],
    ["Encrypted Storage Spine", "libsqlcipher.so (3.56 MB, 426 exports, AES-256 SQLite via OpenSSL/libcrypto) + SecurityGuardLiteNative.", PALETTE.violet],
  ];
  let vy = 648;
  for (const [k, v, col] of vaultBox) {
    ctx.fillStyle = col;
    ctx.font = "700 17px JetBrainsMono";
    ctx.fillText(k, 1005, vy);
    ctx.fillStyle = PALETTE.text;
    ctx.font = "400 16px BeVietnamPro";
    drawWrappedText(ctx, v, 1005, vy + 26, 800, 22);
    vy += 88;
  }
  ctx.restore();
}

// ---------------------------------------------------------------------------
// Scene 7: Native ELF Architecture, C++ Twins & REA Hardening Matrix
// ---------------------------------------------------------------------------
function drawScene7(ctx: SKRSContext2D, t: number, d: ResearchDataset): void {
  const p1 = windowProgress(t, 0.2, 1.0);
  const p2 = windowProgress(t, 1.5, 1.0);

  // Left: Shannon Entropy Scatter across 27 .so + High-Entropy Assets
  drawGlassPanel(ctx, 80, 174, 920, 495, { accentTop: PALETTE.amber, alpha: p1 });
  ctx.save();
  ctx.globalAlpha = p1;
  ctx.font = "700 23px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("Shannon Entropy Topography: 27 .so Binaries vs. Encrypted Assets", 108, 206);
  ctx.restore();
  drawEntropyScatter(
    ctx,
    92,
    236,
    895,
    418,
    d.native_elf_summary.libraries,
    d.hybrid_runtimes_and_ai.encrypted_or_high_entropy_assets,
    p1
  );

  // Bottom-Left: C++ Twin & Shield Discovery Box
  drawGlassPanel(ctx, 80, 692, 920, 230, { accentTop: PALETTE.violet, alpha: p1 });
  ctx.save();
  ctx.globalAlpha = p1;
  ctx.font = "700 21px SpaceGrotesk";
  ctx.fillStyle = PALETTE.violet;
  ctx.fillText("Discovery: Obfuscated Enclaves & Isolated C++ Runtime Twins", 110, 722);
  ctx.fillStyle = PALETTE.text;
  ctx.font = "400 18px BeVietnamPro";
  drawWrappedText(
    ctx,
    "• libtaibmcf.so (12.62 MB, 132 inits, 94.3% .text) & libfgcpkjvqe.so (6.52 MB, 36 inits) pair with H=7.9998 encrypted payloads.\n• liblytcuhe.so & librcmefqonnx.so share identical 178,628-byte ELF layouts (Δ=8,799 B), isolating VFace & Griver from libc++_shared.so.",
    110,
    760,
    860,
    27
  );
  ctx.restore();

  // Right: Full REA Binary Hardening Scorecard Matrix
  drawGlassPanel(ctx, 1025, 174, 815, 748, { accentTop: PALETTE.emerald, alpha: p2 });
  ctx.save();
  ctx.globalAlpha = p2;
  ctx.font = "700 23px SpaceGrotesk";
  ctx.fillStyle = PALETTE.text;
  ctx.fillText("REA Binary Hardening Scorecard (27 ARMv7 .so)", 1055, 206);
  ctx.font = "600 16px JetBrainsMono";
  ctx.fillStyle = PALETTE.emerald;
  ctx.fillText("NX 27/27 · Canary 25/27 · RELRO 23/27 · Stripped 26/27", 1055, 240);
  ctx.restore();

  drawElfHardeningMatrix(ctx, 1055, 276, 755, 560, d.native_elf_summary.libraries, p2);

  // Crashlytics RELRO callout
  drawGlassPanel(ctx, 1055, 846, 755, 58, {
    fill: "rgba(244, 63, 94, 0.14)",
    borderColor: PALETTE.rose,
    radius: 8,
    alpha: p2,
  });
  ctx.save();
  ctx.globalAlpha = p2;
  ctx.fillStyle = PALETTE.rose;
  ctx.font = "600 16px JetBrainsMono";
  ctx.fillText("100% of RELRO:disabled binaries (4/27) trace strictly to libcrashlytics*.so", 1075, 868);
  ctx.restore();
}

// ---------------------------------------------------------------------------
// Scene 8: Synthesis, Recommendations & Academic Citation Artifacts
// ---------------------------------------------------------------------------
function drawScene8(ctx: SKRSContext2D, t: number): void {
  const p1 = windowProgress(t, 0.2, 0.9);
  const p2 = windowProgress(t, 1.3, 1.0);
  const p3 = windowProgress(t, 2.5, 1.0);

  // Top 3 Engineering Recommendations
  const recs = [
    {
      num: "01",
      title: "Tighten Base Network Policy",
      desc: "Flip base-config cleartextTrafficPermitted to false and add declarative backup <pin-set> SHA-256 digests alongside OkHttp pinners.",
      col: PALETTE.rose,
      p: p1,
    },
    {
      num: "02",
      title: "Enforce Full RELRO on NDK Telemetry",
      desc: "Recompile or relink Firebase Crashlytics ARMv7 shared objects with -Wl,-z,relro,-z,now to achieve 27/27 GOT hardening parity.",
      col: PALETTE.amber,
      p: p2,
    },
    {
      num: "03",
      title: "Constrain Exported IPC Entrypoints",
      desc: "Audit the 22 unguarded exported activities, receivers, and services; add signature-level permissions where external invocation isn't needed.",
      col: PALETTE.emerald,
      p: p3,
    },
  ];

  recs.forEach((r, idx) => {
    const rx = 80 + idx * 598;
    drawGlassPanel(ctx, rx, 174, 564, 340, { accentTop: r.col, alpha: r.p });
    ctx.save();
    ctx.globalAlpha = r.p;
    ctx.textBaseline = "top";
    ctx.fillStyle = r.col;
    ctx.font = "700 44px SpaceGrotesk";
    ctx.fillText(r.num, rx + 30, 206);
    ctx.fillStyle = PALETTE.text;
    ctx.font = "700 24px SpaceGrotesk";
    ctx.fillText(r.title, rx + 30, 268);
    ctx.fillStyle = PALETTE.textMuted;
    ctx.font = "400 19px BeVietnamPro";
    drawWrappedText(ctx, r.desc, rx + 30, 318, 504, 29);
    ctx.restore();
  });

  // Bottom Citation & Open Research Corpus Card
  drawGlassPanel(ctx, 80, 544, 1760, 378, { accentTop: PALETTE.cyan, alpha: p2 });
  ctx.save();
  ctx.globalAlpha = p2;
  drawPill(ctx, 114, 578, "REPRODUCIBLE ACADEMIC ARTIFACTS", PALETTE.cyan);
  ctx.fillStyle = PALETTE.text;
  ctx.font = "700 26px SpaceGrotesk";
  ctx.fillText("Repository & Google Scholar Citation Package", 114, 628);

  const bibLines = [
    `@article{zalopay_static_re_2026,`,
    `  title   = {Anatomy of a Tier-1 Southeast Asian Fintech Super-App: Static Reverse Engineering of ZaloPay v11.17.0},`,
    `  year    = {2026},`,
    `  note    = {Corpus: 5 Split APKs, 20 DEXs (9.13M Dalvik ops), 27 ARMv7 ELF .so, 124 Assets | Tools: morluto/rea v6.10.1, Androguard, LIEF, Capstone},`,
    `  url     = {docs/ZALOPAY_11.17.0_REVERSE_ENGINEERING_PAPER.md}`,
    `}`,
  ];
  drawGlassPanel(ctx, 114, 678, 1692, 168, {
    fill: "#04070E",
    borderColor: "rgba(56, 189, 248, 0.35)",
    radius: 10,
  });
  ctx.font = "400 16px JetBrainsMono";
  bibLines.forEach((line, i) => {
    ctx.fillStyle = i === 1 ? PALETTE.emerald : PALETTE.cyan;
    ctx.fillText(line, 138, 706 + i * 24);
  });

  ctx.fillStyle = PALETTE.textMuted;
  ctx.font = "400 17px BeVietnamPro";
  ctx.fillText(
    "All evidence contracts (research/evidence/rea/*.json + deep/*.json) are SHA-256 verified against the immutable APKPure distribution.",
    114,
    872
  );
  ctx.restore();
}

export function renderFrame(
  ctx: SKRSContext2D,
  globalFrame: number,
  fps: number,
  chapters: ChapterTiming[],
  dataset: ResearchDataset,
  icon: Image | null
): void {
  const globalSec = globalFrame / fps;
  const totalSec = chapters[chapters.length - 1].endSec;
  const ch =
    chapters.find((c) => globalSec >= c.startSec && globalSec < c.endSec) ??
    chapters[chapters.length - 1];
  const localSec = Math.max(0, globalSec - ch.startSec);

  // Clear + Animated Engineering Backdrop
  drawBackdrop(ctx, globalSec);

  // Chapter transition fade envelope
  const fadeIn = clamp01(localSec / 0.35);
  const fadeOut = ch.index === chapters.length ? clamp01((ch.durationSec - localSec) / 0.6) : clamp01((ch.durationSec - localSec) / 0.25);
  ctx.save();
  ctx.globalAlpha = easeOutCubic(Math.min(fadeIn, fadeOut));

  switch (ch.index) {
    case 1:
      drawScene1(ctx, localSec, dataset, icon);
      break;
    case 2:
      drawScene2(ctx, localSec, dataset);
      break;
    case 3:
      drawScene3(ctx, localSec, dataset);
      break;
    case 4:
      drawScene4(ctx, localSec, dataset);
      break;
    case 5:
      drawScene5(ctx, localSec, dataset);
      break;
    case 6:
      drawScene6(ctx, localSec);
      break;
    case 7:
      drawScene7(ctx, localSec, dataset);
      break;
    case 8:
      drawScene8(ctx, localSec);
      break;
  }
  ctx.restore();

  // Persistent HUD & Subtitles
  drawChrome(ctx, ch, chapters, globalSec, localSec, totalSec);
}
