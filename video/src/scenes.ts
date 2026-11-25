import { FONTS } from "./fonts.js";
import { COLORS, WIDTH, HEIGHT, card, defsBlock, esc, fmtMiB, fmtNum, sceneFrameChrome, wrapText } from "./svgHelpers.js";
import { clamp01, easeInOutCubic, easeOutCubic, easeOutExpo, lerp, subProgress } from "./easing.js";
import type { MasterMetrics, SceneTiming } from "./types.js";

export function renderSceneSvg(
  scene: SceneTiming,
  metrics: MasterMetrics,
  localProgress: number,
  globalProgress: number,
  totalScenes: number
): string {
  const p = clamp01(localProgress);
  const chrome = sceneFrameChrome({
    chapterNum: scene.index,
    totalChapters: totalScenes,
    tag: `EMPIRICAL REVERSE ENGINEERING MONOGRAPH  ·  ${scene.id.toUpperCase()}`,
    title: scene.title,
    subtitle: scene.subtitle,
    narration: scene.narration,
    progress: p,
    globalProgress,
  });

  let body = "";
  switch (scene.id) {
    case "01_title_thesis":
      body = renderTitleScene(metrics, p);
      break;
    case "02_methodology_rea":
      body = renderMethodologyScene(metrics, p);
      break;
    case "03_container_signing":
      body = renderContainerSigningScene(metrics, p);
      break;
    case "04_manifest_sandbox":
      body = renderManifestSandboxScene(metrics, p);
      break;
    case "05_dex_ecosystem":
      body = renderDexEcosystemScene(metrics, p);
      break;
    case "06_native_hardening":
      body = renderNativeHardeningScene(metrics, p);
      break;
    case "07_hybrid_griver":
      body = renderHybridGriverScene(metrics, p);
      break;
    case "08_edge_ai_ekyc":
      body = renderEdgeAiScene(metrics, p);
      break;
    case "09_conclusions_citation":
      body = renderConclusionsScene(metrics, p);
      break;
  }

  return `<svg xmlns="http://www.w3.org/2000/svg" width="${WIDTH}" height="${HEIGHT}" viewBox="0 0 ${WIDTH} ${HEIGHT}">
  ${defsBlock()}
  ${chrome}
  ${body}
</svg>`;
}

// ---------------------------------------------------------------------------
// SCENE 1: Title & Thesis Overview
// ---------------------------------------------------------------------------
function renderTitleScene(m: MasterMetrics, p: number): string {
  const ringT = easeOutExpo(subProgress(p, 0.05, 0.55));
  const cardsT = easeOutCubic(subProgress(p, 0.12, 0.72));
  const rot = p * 36;

  const kpis = [
    { label: "Total Artifacts Audited", val: m.container_scale.total_analyzed_artifacts, suffix: "", sub: "6,464 APK + 360 Nested", color: COLORS.cyan },
    { label: "Uncompressed Payload", val: 186.28, decimals: 2, suffix: " MiB", sub: "5 XAPK Split APKs", color: COLORS.blue },
    { label: "Dalvik Bytecode (20 DEX)", val: m.dex_architecture.aggregate.total_class_defs, suffix: " cls", sub: "378,040 Method IDs", color: COLORS.violet },
    { label: "Native ARMv7 ELF (.so)", val: m.native_elf_hardening.summary.total_elf_libraries, suffix: " libs", sub: "54.79 MiB · 6 NDK Gens", color: COLORS.emerald },
    { label: "On-Device ML & Mini-Apps", val: m.hybrid_and_ai_stack.ml_total_artifacts, suffix: " ML", sub: "373 Configured Mini-Apps", color: COLORS.amber },
  ];

  const kpiSvg = kpis
    .map((k, idx) => {
      const t = easeOutCubic(subProgress(p, 0.14 + idx * 0.08, 0.55 + idx * 0.08));
      const y = 230 + idx * 136;
      const dx = (1 - t) * 40;
      const curVal = k.decimals ? (k.val * t).toFixed(k.decimals) : fmtNum(k.val * t);
      return `
      <g transform="translate(${dx.toFixed(1)}, 0)" opacity="${t.toFixed(3)}">
        <rect x="1080" y="${y}" width="772" height="114" rx="14" fill="${COLORS.bgCard}" stroke="${COLORS.border}" stroke-width="1.4"/>
        <rect x="1080" y="${y}" width="6" height="114" rx="3" fill="${k.color}"/>
        <text x="1112" y="${y + 42}" font-family="${FONTS.sans}" font-size="16" font-weight="600" fill="${COLORS.textSecondary}">${esc(k.label.toUpperCase())}</text>
        <text x="1112" y="${y + 84}" font-family="${FONTS.mono}" font-size="34" font-weight="700" fill="${COLORS.textPrimary}">${esc(curVal + k.suffix)}</text>
        <text x="1826" y="${y + 72}" text-anchor="end" font-family="${FONTS.mono}" font-size="15" fill="${k.color}">${esc(k.sub)}</text>
      </g>`;
    })
    .join("\n");

  // Left: Concentric Quad-Hybrid Orbital Diagram
  const cx = 540;
  const cy = 560;
  const layers = [
    { r: 280, label: "LAYER 4: ANT GRIVER MINI-APPS (373)", color: COLORS.amber, dash: "10 6" },
    { r: 215, label: "LAYER 3: REACT NATIVE METRO (2,346)", color: COLORS.violet, dash: "16 8" },
    { r: 150, label: "LAYER 2: NATIVE ELF + EDGE AI (27 .so)", color: COLORS.emerald, dash: "8 4" },
    { r: 88, label: "CORE: KOTLIN + COMPOSE (20 DEX)", color: COLORS.cyan, dash: "" },
  ];

  const orbitsSvg = layers
    .map((l, i) => {
      const rCur = l.r * ringT;
      const angle = (rot * (i % 2 === 0 ? 1 : -1) + i * 45) * (Math.PI / 180);
      const nx = cx + Math.cos(angle) * rCur;
      const ny = cy + Math.sin(angle) * rCur;
      return `
      <circle cx="${cx}" cy="${cy}" r="${rCur.toFixed(1)}" fill="none" stroke="${l.color}" stroke-width="${i === 3 ? 2.8 : 1.6}" stroke-dasharray="${l.dash}" opacity="0.72"/>
      <circle cx="${nx.toFixed(1)}" cy="${ny.toFixed(1)}" r="7" fill="${l.color}"/>
      <rect x="${cx - 190}" y="${cy - rCur - 13}" width="380" height="22" rx="6" fill="#070D1B" stroke="${l.color}" stroke-opacity="0.55" opacity="${ringT.toFixed(2)}"/>
      <text x="${cx}" y="${cy - rCur + 3}" text-anchor="middle" font-family="${FONTS.mono}" font-size="11.5" font-weight="600" fill="${l.color}" opacity="${ringT.toFixed(2)}">${esc(l.label)}</text>`;
    })
    .join("\n");

  return `
  ${card(68, 212, 956, 710, { title: "QUAD-HYBRID RUNTIME ARCHITECTURE", sub: "100% STATIC EVIDENCE · ZERO EXPLOIT SYNTHESIS", accent: COLORS.cyan, alpha: cardsT })}
  ${orbitsSvg}
  <circle cx="${cx}" cy="${cy}" r="${(48 * ringT).toFixed(1)}" fill="#0E2244" stroke="${COLORS.cyan}" stroke-width="2.5"/>
  <text x="${cx}" y="${cy - 4}" text-anchor="middle" font-family="${FONTS.mono}" font-size="14" font-weight="700" fill="${COLORS.textPrimary}" opacity="${ringT.toFixed(2)}">ZaloPay</text>
  <text x="${cx}" y="${cy + 16}" text-anchor="middle" font-family="${FONTS.mono}" font-size="12" fill="${COLORS.cyan}" opacity="${ringT.toFixed(2)}">v11.17.0</text>
  <text x="94" y="896" font-family="${FONTS.mono}" font-size="13" fill="${COLORS.textSecondary}" opacity="${cardsT.toFixed(2)}">
    Target SHA-256: ${m.study_metadata.source_archive_sha256.slice(0, 32)}...  |  API 26 → 36 (Android 16)
  </text>
  ${kpiSvg}`;
}

// ---------------------------------------------------------------------------
// SCENE 2: Reproducible Methodology & morluto/rea Pipeline
// ---------------------------------------------------------------------------
function renderMethodologyScene(m: MasterMetrics, p: number): string {
  const stages = [
    { x: 96, title: "1. APKPure ZIP", stat: "94.24 MiB", desc: "Outer Distribution Archive", detail: "100% SHA-256 Verified", color: COLORS.textSecondary },
    { x: 452, title: "2. XAPK v2 Bundle", stat: "100.72 MiB", desc: "5 Split APKs + Manifest", detail: "Base + ABI + DPI + Locales", color: COLORS.blue },
    { x: 808, title: "3. morluto/rea MCP", stat: "6,824 Nodes", desc: "ArtifactGraph + JS AST", detail: "0 Integrity Contradictions", color: COLORS.cyan },
    { x: 1164, title: "4. Androguard + LIEF", stat: "20 DEX + 27 .so", desc: "AXML / DEX / ELF Parsers", detail: "Hardening + SDK Taxonomy", color: COLORS.emerald },
    { x: 1520, title: "5. Master Dataset", stat: "master_metrics.json", desc: "Scholarly Paper + MP4", detail: "Full Reproducibility", color: COLORS.amber },
  ];

  const cards = stages
    .map((s, i) => {
      const t = easeOutCubic(subProgress(p, 0.08 + i * 0.1, 0.35 + i * 0.1));
      const dy = (1 - t) * 28;
      const connT = easeInOutCubic(subProgress(p, 0.18 + i * 0.1, 0.42 + i * 0.1));
      const arrow =
        i < stages.length - 1
          ? `<line x1="${s.x + 300}" y1="380" x2="${(s.x + 300 + 56 * connT).toFixed(1)}" y2="380" stroke="${COLORS.cyan}" stroke-width="3" stroke-dasharray="6 4"/>
             <circle cx="${(s.x + 300 + 56 * connT).toFixed(1)}" cy="380" r="5" fill="${COLORS.cyan}" opacity="${connT.toFixed(2)}"/>`
          : "";
      return `
      ${arrow}
      <g transform="translate(0, ${dy.toFixed(1)})" opacity="${t.toFixed(3)}">
        <rect x="${s.x}" y="246" width="300" height="270" rx="14" fill="${COLORS.bgCard}" stroke="${s.color}" stroke-width="1.6"/>
        <rect x="${s.x}" y="246" width="300" height="6" rx="3" fill="${s.color}"/>
        <text x="${s.x + 20}" y="290" font-family="${FONTS.sans}" font-size="19" font-weight="700" fill="${COLORS.textPrimary}">${esc(s.title)}</text>
        <text x="${s.x + 20}" y="344" font-family="${FONTS.mono}" font-size="22" font-weight="700" fill="${s.color}">${esc(s.stat)}</text>
        <text x="${s.x + 20}" y="384" font-family="${FONTS.sans}" font-size="16" fill="${COLORS.textSecondary}">${esc(s.desc)}</text>
        <line x1="${s.x + 20}" y1="414" x2="${s.x + 280}" y2="414" stroke="${COLORS.border}" stroke-width="1"/>
        <text x="${s.x + 20}" y="446" font-family="${FONTS.mono}" font-size="13.5" fill="${COLORS.textPrimary}">${esc(s.detail)}</text>
        <text x="${s.x + 20}" y="482" font-family="${FONTS.mono}" font-size="12" fill="${COLORS.textMuted}">STAGE 0${i + 1} · VERIFIED</text>
      </g>`;
    })
    .join("\n");

  const panelT = easeOutCubic(subProgress(p, 0.45, 0.85));
  return `
  ${cards}
  ${card(96, 558, 848, 360, { title: "MORLUTO/REA DETERMINISTIC EVIDENCE PROVENANCE", sub: "rea-agents@6.10.1 · MCP + CLI", accent: COLORS.cyan, alpha: panelT })}
  <g opacity="${panelT.toFixed(3)}" font-family="${FONTS.mono}" font-size="14.5">
    <text x="122" y="630" fill="${COLORS.cyan}">$ rea inspect-artifact vn.com.vng.zalopay.apk --format json</text>
    <text x="122" y="662" fill="${COLORS.textSecondary}">  graph_sha256 : ${m.signing_integrity.rea_graph_sha256_base_apk.slice(0, 44)}...</text>
    <text x="122" y="690" fill="${COLORS.textSecondary}">  nodes/edges  : 5,675 nodes · 6,163 occurrences · 0 contradictions</text>
    <text x="122" y="732" fill="${COLORS.emerald}">$ rea analyze-javascript-application assets/GriverBridge</text>
    <text x="122" y="764" fill="${COLORS.textSecondary}">  ast_graph    : 16 graph nodes · 16 edges · 4 timer semantic relations</text>
    <text x="122" y="806" fill="${COLORS.amber}">$ rea inspect-rust-binary / inspect-go-binary / inspect-with-objdump</text>
    <text x="122" y="838" fill="${COLORS.textSecondary}">  elf_coverage : 27/27 ARMv7 .so audited · 0 Go · 0 Cargo-Auditable Rust</text>
    <text x="122" y="880" fill="${COLORS.textMuted}">  authority    : shipped-artifact (inert static inspection, no runtime execution)</text>
  </g>

  ${card(976, 558, 844, 360, { title: "ETHICAL & MATHEMATICAL TELEMETRY FRAMEWORK", sub: "NON-EXPLOITATION GUARANTEE", accent: COLORS.emerald, alpha: panelT })}
  <g opacity="${panelT.toFixed(3)}">
    <text x="1004" y="632" font-family="${FONTS.sans}" font-size="18" font-weight="600" fill="${COLORS.emerald}">1. Zero Exploit / Secret Harvesting Boundary</text>
    <text x="1004" y="662" font-family="${FONTS.sans}" font-size="16" fill="${COLORS.textSecondary}">Strictly measures container taxonomy, signing integrity, compiler flags &amp; defensive posture.</text>
    <text x="1004" y="718" font-family="${FONTS.sans}" font-size="18" font-weight="600" fill="${COLORS.cyan}">2. Shannon Entropy Anomaly Detector</text>
    <text x="1004" y="754" font-family="${FONTS.mono}" font-size="18" fill="${COLORS.textPrimary}">H(X) = - Σ p(x_i) · log₂ p(x_i)   ∈ [0.00, 8.00] bits/byte</text>
    <text x="1004" y="806" font-family="${FONTS.sans}" font-size="18" font-weight="600" fill="${COLORS.amber}">3. Composite Binary Hardening Score</text>
    <text x="1004" y="842" font-family="${FONTS.mono}" font-size="17" fill="${COLORS.textPrimary}">H_bin = (PIE + NX + FullRELRO + Canary + FORTIFY + Stripped) / 6</text>
    <text x="1004" y="882" font-family="${FONTS.mono}" font-size="14" fill="${COLORS.textMuted}">Evaluated exhaustively across N = 27 ELF shared objects (54.79 MiB).</text>
  </g>`;
}

// ---------------------------------------------------------------------------
// SCENE 3: Container Anatomy, Deduplication & Signing
// ---------------------------------------------------------------------------
function renderContainerSigningScene(m: MasterMetrics, p: number): string {
  const catColors = [COLORS.cyan, COLORS.emerald, COLORS.blue, COLORS.amber, COLORS.violet, COLORS.rose, COLORS.textSecondary];
  const cats = m.container_scale.categories.slice(0, 7);
  const barT = easeOutCubic(subProgress(p, 0.08, 0.65));

  // Stacked Horizontal Allocation Bar constrained neatly inside card (x=96..1140)
  let curX = 96;
  const totalW = 1044;
  const stackedSegments = cats
    .map((c, i) => {
      const segW = Math.max(4, (totalW * c.share_pct) / 100) * barT;
      const out = `<rect x="${curX.toFixed(1)}" y="276" width="${segW.toFixed(1)}" height="38" fill="${catColors[i]}" rx="2"/>`;
      curX += segW;
      return out;
    })
    .join("\n");

  const catRows = cats
    .map((c, i) => {
      const y = 354 + i * 52;
      const t = easeOutCubic(subProgress(p, 0.12 + i * 0.05, 0.55 + i * 0.05));
      const bw = 310 * (c.share_pct / 40) * t;
      return `
      <g opacity="${t.toFixed(3)}">
        <circle cx="110" cy="${y - 5}" r="6" fill="${catColors[i]}"/>
        <text x="128" y="${y}" font-family="${FONTS.sans}" font-size="16.5" font-weight="600" fill="${COLORS.textPrimary}">${esc(c.category)}</text>
        <rect x="496" y="${y - 16}" width="310" height="18" rx="4" fill="#081020"/>
        <rect x="496" y="${y - 16}" width="${Math.min(310, bw).toFixed(1)}" height="18" rx="4" fill="${catColors[i]}"/>
        <text x="822" y="${y}" font-family="${FONTS.mono}" font-size="14.5" fill="${COLORS.textPrimary}">${fmtMiB(c.uncompressed_bytes)} MiB (${c.share_pct.toFixed(1)}%)</text>
        <text x="1140" y="${y}" text-anchor="end" font-family="${FONTS.mono}" font-size="13.5" fill="${COLORS.textSecondary}">${fmtNum(c.count)} files</text>
      </g>`;
    })
    .join("\n");

  const rightT = easeOutCubic(subProgress(p, 0.25, 0.78));
  const splits = m.container_scale.split_apks;
  const splitRows = splits
    .map((s, i) => {
      const y = 304 + i * 50;
      return `
      <g opacity="${rightT.toFixed(3)}">
        <text x="1220" y="${y}" font-family="${FONTS.mono}" font-size="15" font-weight="600" fill="${COLORS.cyan}">${esc(s.name)}</text>
        <text x="1630" y="${y}" text-anchor="end" font-family="${FONTS.mono}" font-size="15" fill="${COLORS.textPrimary}">${fmtMiB(s.apk_bytes)} MiB</text>
        <text x="1820" y="${y}" text-anchor="end" font-family="${FONTS.mono}" font-size="14" fill="${COLORS.textSecondary}">${fmtNum(s.entries)} entries</text>
      </g>`;
    })
    .join("\n");

  return `
  ${card(68, 212, 1100, 520, { title: "UNCOMPRESSED STORAGE TAXONOMY (186.28 MiB / 6,464 ENTRIES)", sub: "COMPRESSION RATIO: 0.535", accent: COLORS.cyan })}
  <rect x="96" y="276" width="1728" height="42" rx="6" fill="#070E1C" opacity="0"/>
  ${stackedSegments}
  ${catRows}

  ${card(1192, 212, 660, 352, { title: "5 XAPK v2 SPLIT APKs", sub: "ZERO CONTRADICTIONS", accent: COLORS.blue, alpha: rightT })}
  ${splitRows}

  ${card(1192, 586, 660, 336, { title: "SIGNING BLOCK & DEDUPLICATION", sub: "APK SIG BLOCK 42 (8,192 B)", accent: COLORS.emerald, alpha: rightT })}
  <g opacity="${rightT.toFixed(3)}" font-family="${FONTS.mono}" font-size="14.5">
    <text x="1220" y="652" fill="${COLORS.emerald}">✓ APK Signature Scheme v2 (0x7109871a) + v3 (0xf05368c0)</text>
    <text x="1220" y="684" fill="${COLORS.emerald}">✓ Source Stamp v1 + Verity Padding + Play NDF! (0x2146444e)</text>
    <text x="1220" y="716" fill="${COLORS.textSecondary}">  Split v1 RSA-2048: CN=Vu Manh Hieu (2016–2041)</text>
    <line x1="1220" y1="744" x2="1820" y2="744" stroke="${COLORS.border}"/>
    <text x="1220" y="782" fill="${COLORS.amber}" font-weight="700">! Deduplication Audit: 299 Identical SHA-256 Groups</text>
    <text x="1220" y="814" fill="${COLORS.textPrimary}">  959,564 bytes (~937 KiB) wasted across duplicate files</text>
    <text x="1220" y="846" fill="${COLORS.textSecondary}">  e.g. UnisectVnu.ttf ≡ ocr-a-tribute.ttf (48,216 B)</text>
    <text x="1220" y="874" fill="${COLORS.textSecondary}">  &amp; duplicated BouncyCastle PKIX/X509 properties (92 KiB)</text>
  </g>

  ${card(68, 754, 1100, 168, { title: "SHANNON ENTROPY DISTRIBUTION ACROSS PAYLOAD CLASSES", sub: "PACKED / ENCRYPTED DETECTION", accent: COLORS.amber, alpha: rightT })}
  <g opacity="${rightT.toFixed(3)}" font-family="${FONTS.mono}" font-size="15">
    <text x="98" y="828" fill="${COLORS.textPrimary}">Base APK Mean: <tspan fill="${COLORS.cyan}" font-weight="700">4.31 bits/B</tspan></text>
    <text x="410" y="828" fill="${COLORS.textPrimary}">ABI Split Mean: <tspan fill="${COLORS.emerald}" font-weight="700">6.38 bits/B</tspan></text>
    <text x="740" y="828" fill="${COLORS.textPrimary}">Opaque Shield Assets (.dat / stub):</text>
    <text x="98" y="868" fill="${COLORS.amber}">• assets/fgcpkjvqe.dat (776 KB) &amp; assets/bshield.dat (929 B) &amp; assets/trueidlib.so (392 B, H=7.54)</text>
  </g>`;
}

// ---------------------------------------------------------------------------
// SCENE 4: Manifest Surface, Permissions & Process Isolation
// ---------------------------------------------------------------------------
function renderManifestSandboxScene(m: MasterMetrics, p: number): string {
  const tLeft = easeOutCubic(subProgress(p, 0.06, 0.55));
  const tRight = easeOutCubic(subProgress(p, 0.22, 0.78));

  const comps = [
    { name: "Activities (+Aliases)", total: m.manifest_surface.components.activity.total + m.manifest_surface.components["activity-alias"].total, exp: m.manifest_surface.components.activity.exported_true + m.manifest_surface.components["activity-alias"].exported_true, color: COLORS.cyan },
    { name: "Services", total: m.manifest_surface.components.service.total, exp: m.manifest_surface.components.service.exported_true, color: COLORS.emerald },
    { name: "BroadcastReceivers", total: m.manifest_surface.components.receiver.total, exp: m.manifest_surface.components.receiver.exported_true, color: COLORS.amber },
    { name: "ContentProviders", total: m.manifest_surface.components.provider.total, exp: m.manifest_surface.components.provider.exported_true, color: COLORS.violet },
  ];

  const compSvg = comps
    .map((c, i) => {
      const y = 296 + i * 76;
      const bw = 540 * (c.total / 202) * tLeft;
      return `
      <g opacity="${tLeft.toFixed(3)}">
        <text x="96" y="${y}" font-family="${FONTS.sans}" font-size="18" font-weight="600" fill="${COLORS.textPrimary}">${esc(c.name)}</text>
        <text x="860" y="${y}" text-anchor="end" font-family="${FONTS.mono}" font-size="16" fill="${c.color}">${c.total} total · ${c.exp} exported</text>
        <rect x="96" y="${y + 12}" width="764" height="16" rx="4" fill="#081020"/>
        <rect x="96" y="${y + 12}" width="${Math.max(8, bw).toFixed(1)}" height="16" rx="4" fill="${c.color}"/>
      </g>`;
    })
    .join("\n");

  const procs = m.manifest_surface.isolated_processes;
  const procNodes = procs
    .map((pr, i) => {
      const col = i < 5 ? COLORS.amber : COLORS.rose;
      const role = i < 5 ? "Griver Mini-App Worker" : pr === ":taibmcf" ? "Native Shield Isolate" : "Obfuscated Service";
      const px = i < 4 ? 970 + i * 212 : 970 + (i - 4) * 282;
      const py = i < 4 ? 426 : 552;
      const pw = i < 4 ? 196 : 264;
      return `
      <g opacity="${tRight.toFixed(3)}">
        <line x1="1380" y1="342" x2="${px + pw / 2}" y2="${py}" stroke="${col}" stroke-width="1.6" stroke-dasharray="5 4" opacity="0.75"/>
        <rect x="${px}" y="${py}" width="${pw}" height="88" rx="10" fill="${COLORS.bgCardAlt}" stroke="${col}" stroke-width="1.5"/>
        <text x="${px + pw / 2}" y="${py + 36}" text-anchor="middle" font-family="${FONTS.mono}" font-size="16" font-weight="700" fill="${col}">${esc(pr)}</text>
        <text x="${px + pw / 2}" y="${py + 64}" text-anchor="middle" font-family="${FONTS.sans}" font-size="13.5" fill="${COLORS.textSecondary}">${esc(role)}</text>
      </g>`;
    })
    .join("\n");

  return `
  ${card(68, 212, 824, 418, { title: "267 DECLARED ANDROID COMPONENTS", sub: "targetSdk=36 (Android 16) · minSdk=26", accent: COLORS.cyan, alpha: tLeft })}
  ${compSvg}

  ${card(68, 650, 824, 272, { title: "49 PERMISSIONS & NETWORK SECURITY POSTURE", sub: "ALLOWBACKUP = FALSE", accent: COLORS.rose, alpha: tLeft })}
  <g opacity="${tLeft.toFixed(3)}" font-family="${FONTS.sans}" font-size="16">
    <text x="96" y="722" fill="${COLORS.textPrimary}"><tspan font-family="${FONTS.mono}" font-weight="700" fill="${COLORS.rose}">14</tspan> Dangerous (Camera, Fine Location, Contacts, Audio, Storage)</text>
    <text x="96" y="756" fill="${COLORS.textPrimary}"><tspan font-family="${FONTS.mono}" font-weight="700" fill="${COLORS.amber}">25</tspan> Sensitive / OEM (DETECT_SCREEN_CAPTURE, NFC, Biometrics, QUERY_ALL_PACKAGES)</text>
    <text x="96" y="790" fill="${COLORS.textPrimary}"><tspan font-family="${FONTS.mono}" font-weight="700" fill="${COLORS.cyan}">10</tspan> Normal Platform Permissions + Zalo OAuth Authorization</text>
    <rect x="96" y="814" width="764" height="84" rx="10" fill="#1A0E18" stroke="${COLORS.rose}" stroke-width="1.2"/>
    <text x="118" y="846" font-family="${FONTS.mono}" font-size="15" font-weight="700" fill="${COLORS.rose}">! FINDING: res/xml/network_security_config.xml</text>
    <text x="118" y="876" font-family="${FONTS.mono}" font-size="14" fill="${COLORS.textPrimary}">&lt;base-config cleartextTrafficPermitted="true"&gt; — legacy compatibility exposure</text>
  </g>

  ${card(924, 212, 928, 710, { title: "MULTI-PROCESS ISOLATION & DEEP-LINK SURFACE", sub: "7 ISOLATED PROCESSES", accent: COLORS.amber, alpha: tRight })}
  <g opacity="${tRight.toFixed(3)}">
    <rect x="1140" y="274" width="480" height="68" rx="12" fill="#0F1F3D" stroke="${COLORS.cyan}" stroke-width="2"/>
    <text x="1380" y="304" text-anchor="middle" font-family="${FONTS.mono}" font-size="15" font-weight="700" fill="${COLORS.cyan}">PRIMARY PROCESS: vn.com.vng.zalopay</text>
    <text x="1380" y="328" text-anchor="middle" font-family="${FONTS.mono}" font-size="13" fill="${COLORS.textSecondary}">Entrypoint: ${esc(m.manifest_surface.application_entrypoint)}</text>
  </g>
  ${procNodes}
  <g opacity="${tRight.toFixed(3)}">
    <rect x="970" y="684" width="836" height="206" rx="12" fill="#081020" stroke="${COLORS.borderBright}" stroke-width="1"/>
    <text x="996" y="720" font-family="${FONTS.sans}" font-size="17" font-weight="700" fill="${COLORS.textPrimary}">7 Registered Deep-Link URI Schemes</text>
    <text x="996" y="758" font-family="${FONTS.mono}" font-size="16" fill="${COLORS.cyan}">zalopay://   zalopay-1://   zalopay-vnpay://   zalopay.api.v2://</text>
    <text x="996" y="792" font-family="${FONTS.mono}" font-size="16" fill="${COLORS.blue}">https://     fbconnect://   tagmanager.c.vn.com.vng.zalopay://</text>
    <text x="996" y="842" font-family="${FONTS.sans}" font-size="15" fill="${COLORS.textSecondary}">
      Multi-process isolation separates third-party Griver mini-apps (:lite1–:lite5) and the 12 MiB
    </text>
    <text x="996" y="866" font-family="${FONTS.sans}" font-size="15" fill="${COLORS.textSecondary}">
      native shield engine (:taibmcf) from core payment memory spaces.
    </text>
  </g>`;
}

// ---------------------------------------------------------------------------
// SCENE 5: Multi-DEX Bytecode & SDK Ecosystem
// ---------------------------------------------------------------------------
function dexSortKey(name: string): number {
  if (name === "classes.dex") return 1;
  const m = name.match(/^classes(\d+)\.dex$/);
  if (m) return Number(m[1]);
  return 999;
}

function renderDexEcosystemScene(m: MasterMetrics, p: number): string {
  const dexList = [...m.dex_architecture.per_dex].sort((a, b) => dexSortKey(a.filename) - dexSortKey(b.filename));
  const tBars = easeOutCubic(subProgress(p, 0.08, 0.72));
  const tRight = easeOutCubic(subProgress(p, 0.2, 0.8));

  // Left: 20 DEX Method Count Bars + 60k ceiling line
  const maxMeth = 66500;
  const chartX = 110;
  const chartY = 296;
  const chartW = 876;
  const chartH = 434;
  const barW = 30;
  const gap = (chartW - 20 * barW) / 21;
  const ceilingY = chartY + chartH - (60000 / maxMeth) * chartH;

  const dexBars = dexList
    .map((d, i) => {
      const h = (d.method_ids_count / maxMeth) * chartH * tBars;
      const x = chartX + gap + i * (barW + gap);
      const y = chartY + chartH - h;
      const isDyn = d.filename.includes("audience_network");
      const atCeiling = d.method_ids_count >= 59900;
      const col = isDyn ? COLORS.rose : atCeiling ? COLORS.amber : COLORS.cyan;
      const shortName = isDyn ? "aud" : d.filename.replace("classes", "c").replace(".dex", "");
      return `
      <rect x="${x.toFixed(1)}" y="${y.toFixed(1)}" width="${barW}" height="${Math.max(2, h).toFixed(1)}" rx="4" fill="${col}" opacity="0.9"/>
      <text x="${(x + barW / 2).toFixed(1)}" y="${chartY + chartH + 20}" text-anchor="middle" font-family="${FONTS.mono}" font-size="11.5" fill="${isDyn ? COLORS.rose : COLORS.textSecondary}">${esc(shortName)}</text>`;
    })
    .join("\n");

  // Right: SDK Ecosystem Distribution
  const sdks = m.dex_architecture.sdk_distribution.slice(0, 9);
  const sdkColors = [COLORS.rose, COLORS.cyan, COLORS.emerald, COLORS.amber, COLORS.blue, COLORS.violet, COLORS.textSecondary, "#14B8A6", "#EC4899"];
  const sdkBars = sdks
    .map((s, i) => {
      const y = 286 + i * 64;
      const t = easeOutCubic(subProgress(p, 0.16 + i * 0.05, 0.65 + i * 0.05));
      const bw = 420 * (s.share_pct / 45) * t;
      return `
      <g opacity="${t.toFixed(3)}">
        <text x="1080" y="${y}" font-family="${FONTS.sans}" font-size="16" font-weight="600" fill="${COLORS.textPrimary}">${esc(s.ecosystem)}</text>
        <text x="1816" y="${y}" text-anchor="end" font-family="${FONTS.mono}" font-size="15" fill="${sdkColors[i]}">${fmtNum(s.classes)} (${s.share_pct.toFixed(1)}%)</text>
        <rect x="1080" y="${y + 10}" width="736" height="14" rx="4" fill="#081020"/>
        <rect x="1080" y="${y + 10}" width="${Math.min(736, bw * 1.7).toFixed(1)}" height="14" rx="4" fill="${sdkColors[i]}"/>
      </g>`;
    })
    .join("\n");

  return `
  ${card(68, 212, 956, 710, { title: "20 DALVIK EXECUTABLES (66.47 MiB · 378,040 METHOD IDs)", sub: "D8/R8 60K SPLITTER THRESHOLD", accent: COLORS.cyan })}
  <line x1="${chartX}" y1="${ceilingY.toFixed(1)}" x2="${chartX + chartW}" y2="${ceilingY.toFixed(1)}" stroke="${COLORS.amber}" stroke-width="1.8" stroke-dasharray="8 5"/>
  <text x="${chartX + chartW - 8}" y="${(ceilingY - 8).toFixed(1)}" text-anchor="end" font-family="${FONTS.mono}" font-size="13" font-weight="700" fill="${COLORS.amber}">60,000-Method Soft Split Ceiling (classes, classes4, classes7)</text>
  <line x1="${chartX}" y1="${chartY + chartH}" x2="${chartX + chartW}" y2="${chartY + chartH}" stroke="${COLORS.borderBright}" stroke-width="1.5"/>
  ${dexBars}
  <g opacity="${tRight.toFixed(3)}" font-family="${FONTS.mono}" font-size="14.5">
    <rect x="96" y="794" width="900" height="104" rx="10" fill="#081020" stroke="${COLORS.border}"/>
    <text x="122" y="828" fill="${COLORS.textPrimary}">Total Classes: <tspan fill="${COLORS.cyan}" font-weight="700">63,827</tspan>   |   R8 Short-Name Obfuscated: <tspan fill="${COLORS.rose}" font-weight="700">44,452 (69.64%)</tspan></text>
    <text x="122" y="858" fill="${COLORS.textPrimary}">Jetpack Compose 1.7.2 Classes: <tspan fill="${COLORS.emerald}" font-weight="700">509</tspan>   |   Dynamic Asset DEX: <tspan fill="${COLORS.rose}">audience_network.dex (97.3% obf)</tspan></text>
    <text x="122" y="884" fill="${COLORS.textSecondary}">String Pool Entries: 404,914   |   Field IDs: 317,841   |   AndroidX .version manifests: 141</text>
  </g>

  ${card(1050, 212, 802, 710, { title: "CLASS DISTRIBUTION BY SDK ECOSYSTEM", sub: "63,827 TOTAL CLASS DEFS", accent: COLORS.violet, alpha: tRight })}
  ${sdkBars}`;
}

// ---------------------------------------------------------------------------
// SCENE 6: Native ELF Hardening & 6-Generation Clang Archeology
// ---------------------------------------------------------------------------
function renderNativeHardeningScene(m: MasterMetrics, p: number): string {
  const libs = [...m.native_elf_hardening.libraries].sort((a, b) => b.bytes - a.bytes);
  const tHeat = easeOutCubic(subProgress(p, 0.08, 0.72));
  const tRight = easeOutCubic(subProgress(p, 0.25, 0.85));

  // Left: 27x6 Binary Hardening Heatmap Matrix
  const cols = ["PIE", "NX", "RELRO", "CANARY", "FORTIFY", "STRIP"];
  const colHeaders = cols
    .map((c, j) => `<text x="${410 + j * 82}" y="274" text-anchor="middle" font-family="${FONTS.mono}" font-size="13" font-weight="700" fill="${COLORS.textSecondary}">${c}</text>`)
    .join("\n");

  const rowsSvg = libs
    .map((lib, i) => {
      const y = 296 + i * 22.5;
      const rowReveal = easeOutCubic(subProgress(p, 0.06 + i * 0.015, 0.32 + i * 0.015));
      const flags = [
        lib.pie ? 1 : 0,
        lib.nx ? 1 : 0,
        lib.relro === "full" ? 1 : lib.relro === "partial" ? 0.5 : 0,
        lib.stack_canary ? 1 : 0,
        lib.fortify ? 1 : 0,
        lib.stripped ? 1 : 0,
      ];
      const cells = flags
        .map((v, j) => {
          const fill = v === 1 ? COLORS.emerald : v === 0.5 ? COLORS.amber : COLORS.rose;
          const label = v === 1 ? "✓" : v === 0.5 ? "~" : "✕";
          return `<rect x="${374 + j * 82}" y="${(y - 14).toFixed(1)}" width="72" height="17" rx="3" fill="${fill}" fill-opacity="0.24" stroke="${fill}" stroke-width="0.9"/>
                  <text x="${410 + j * 82}" y="${(y - 1).toFixed(1)}" text-anchor="middle" font-family="${FONTS.mono}" font-size="11.5" font-weight="700" fill="${fill}">${label}</text>`;
        })
        .join("");
      const shortLib = lib.name.length > 26 ? lib.name.slice(0, 24) + ".." : lib.name;
      const isCrash = lib.name.includes("crashlytics");
      return `
      <g opacity="${rowReveal.toFixed(3)}">
        <text x="96" y="${y.toFixed(1)}" font-family="${FONTS.mono}" font-size="12.5" fill="${isCrash ? COLORS.rose : COLORS.textPrimary}">${esc(shortLib)}</text>
        <text x="356" y="${y.toFixed(1)}" text-anchor="end" font-family="${FONTS.mono}" font-size="11.5" fill="${COLORS.textMuted}">${(lib.bytes / (1024 * 1024)).toFixed(2)}M</text>
        ${cells}
      </g>`;
    })
    .join("\n");

  // Right Top: Hardening Aggregates + Right Bottom: 6 Clang Toolchain Generations
  const mitigations = [
    { name: "PIE (ET_DYN)", pct: 100.0, frac: "27/27", col: COLORS.emerald },
    { name: "NX Non-Exec Stack", pct: 100.0, frac: "27/27", col: COLORS.emerald },
    { name: "Stripped .symtab", pct: 96.3, frac: "26/27", col: COLORS.cyan },
    { name: "Stack Canary", pct: 92.6, frac: "25/27", col: COLORS.cyan },
    { name: "Full RELRO", pct: 85.2, frac: "23/27", col: COLORS.amber },
    { name: "FORTIFY_SOURCE", pct: 55.6, frac: "15/27", col: COLORS.rose },
  ];

  const mitBars = mitigations
    .map((mt, i) => {
      const y = 290 + i * 44;
      const bw = 460 * (mt.pct / 100) * tRight;
      return `
      <g opacity="${tRight.toFixed(3)}">
        <text x="976" y="${y}" font-family="${FONTS.sans}" font-size="16" font-weight="600" fill="${COLORS.textPrimary}">${mt.name}</text>
        <text x="1816" y="${y}" text-anchor="end" font-family="${FONTS.mono}" font-size="15" font-weight="700" fill="${mt.col}">${mt.pct.toFixed(1)}% (${mt.frac})</text>
        <rect x="976" y="${y + 8}" width="840" height="12" rx="4" fill="#081020"/>
        <rect x="976" y="${y + 8}" width="${(bw * 1.82).toFixed(1)}" height="12" rx="4" fill="${mt.col}"/>
      </g>`;
    })
    .join("\n");

  const clangs = [
    { ver: "Clang 7.0.2 (2018)", libs: "libsecurity_lite.so (H=7.30)", col: COLORS.rose },
    { ver: "Clang 14.0.6", libs: "libcrashlytics* (4), libljsfrcwmw, liblytcuhe, libohxbee, librcmefqonnx, libtrueidlib", col: COLORS.amber },
    { ver: "Clang 14.0.7", libs: "libsqlcipher.so (3.39 MiB), libandroidx.graphics.path, libimage_processing_util_jni", col: COLORS.amber },
    { ver: "Clang 17.0.2", libs: "libtaibmcf.so (12.03 MiB isolated :taibmcf shield)", col: COLORS.cyan },
    { ver: "Clang 18.0.1–3", libs: "libVFaceLib, librive-android (167 JNI), libc++_shared, Fresco image pipeline", col: COLORS.emerald },
    { ver: "Clang 21.0.0", libs: "libfgcpkjvqe.so (6.22 MiB + .dat) & libpicodet_ncnn.so (2.85 MiB)", col: COLORS.violet },
  ];

  const clangRows = clangs
    .map((cl, i) => {
      const y = 636 + i * 44;
      return `
      <g opacity="${tRight.toFixed(3)}">
        <circle cx="990" cy="${y - 5}" r="5.5" fill="${cl.col}"/>
        <text x="1008" y="${y}" font-family="${FONTS.mono}" font-size="14" font-weight="700" fill="${cl.col}">${esc(cl.ver)}</text>
        <text x="1200" y="${y}" font-family="${FONTS.sans}" font-size="14" fill="${COLORS.textSecondary}">${esc(cl.libs)}</text>
      </g>`;
    })
    .join("\n");

  return `
  ${card(68, 212, 850, 710, { title: "27×6 NATIVE ELF HARDENING MATRIX (ARMv7)", sub: "54.79 MiB · LIEF + PYELFTOOLS", accent: COLORS.emerald, alpha: tHeat })}
  ${colHeaders}
  ${rowsSvg}

  ${card(946, 212, 906, 344, { title: "EXPLOIT MITIGATION COVERAGE SUMMARY", sub: "CRASHLYTICS LACKS FULL RELRO", accent: COLORS.amber, alpha: tRight })}
  ${mitBars}

  ${card(946, 574, 906, 348, { title: "6 CO-EXISTING ANDROID NDK CLANG GENERATIONS", sub: "ELF .COMMENT PROVENANCE", accent: COLORS.violet, alpha: tRight })}
  <line x1="990" y1="628" x2="990" y2="868" stroke="${COLORS.borderBright}" stroke-width="1.5"/>
  ${clangRows}`;
}

// ---------------------------------------------------------------------------
// SCENE 7: Quad-Hybrid React Native & Ant Group Griver
// ---------------------------------------------------------------------------
function renderHybridGriverScene(m: MasterMetrics, p: number): string {
  const t1 = easeOutCubic(subProgress(p, 0.06, 0.55));
  const t2 = easeOutCubic(subProgress(p, 0.18, 0.72));

  const rnMods = Object.entries(m.hybrid_and_ai_stack.react_native.asset_feature_modules);
  const rnBars = rnMods
    .map(([name, cnt], i) => {
      const y = 446 + i * 52;
      const w = 360 * (cnt / 110) * t1;
      return `
      <g opacity="${t1.toFixed(3)}">
        <text x="108" y="${y}" font-family="${FONTS.mono}" font-size="15.5" fill="${COLORS.textPrimary}">${esc(name)}</text>
        <rect x="380" y="${y - 14}" width="360" height="16" rx="4" fill="#081020"/>
        <rect x="380" y="${y - 14}" width="${w.toFixed(1)}" height="16" rx="4" fill="${COLORS.violet}"/>
        <text x="760" y="${y}" font-family="${FONTS.mono}" font-size="14" fill="${COLORS.violet}">${cnt} assets</text>
      </g>`;
    })
    .join("\n");

  const grivers = [
    { id: "66666692_1.24.8.amr", fw: "Legacy appx Runtime", sz: "946 KiB TAR (10 files)", col: COLORS.blue },
    { id: "68687209_2.9.7.8.amr", fw: "Next-Gen appx-ng Runtime", sz: "3.14 MiB TAR (13 files)", col: COLORS.cyan },
    { id: "66666693_2.0.7.amr", fw: "Pre-Bundled Mini-App", sz: "55 KiB TAR (7 files)", col: COLORS.emerald },
  ];

  const griverCards = grivers
    .map((g, i) => {
      const y = 396 + i * 98;
      return `
      <g opacity="${t2.toFixed(3)}">
        <rect x="1008" y="${y}" width="804" height="80" rx="10" fill="#081020" stroke="${g.col}" stroke-width="1.3"/>
        <text x="1032" y="${y + 32}" font-family="${FONTS.mono}" font-size="16" font-weight="700" fill="${g.col}">${esc(g.id)}</text>
        <text x="1032" y="${y + 58}" font-family="${FONTS.sans}" font-size="15" fill="${COLORS.textSecondary}">${esc(g.fw)}</text>
        <text x="1788" y="${y + 46}" text-anchor="end" font-family="${FONTS.mono}" font-size="15" fill="${COLORS.textPrimary}">${esc(g.sz)}</text>
      </g>`;
    })
    .join("\n");

  return `
  ${card(68, 212, 868, 710, { title: "REACT NATIVE METRO CONTAINER (assets/android.1.zip)", sub: "2,346 __d MODULES", accent: COLORS.violet, alpha: t1 })}
  <g opacity="${t1.toFixed(3)}">
    <rect x="104" y="274" width="796" height="114" rx="12" fill="#13102B" stroke="${COLORS.violet}" stroke-width="1.4"/>
    <text x="132" y="314" font-family="${FONTS.mono}" font-size="22" font-weight="700" fill="${COLORS.violet}">main.jsbundle : 3,613,478 bytes (2,358 lines)</text>
    <text x="132" y="348" font-family="${FONTS.sans}" font-size="16" fill="${COLORS.textPrimary}">325 Bundled Asset Nodes · @zalopay/components (45) · react-navigation-stack</text>
    <text x="132" y="372" font-family="${FONTS.mono}" font-size="13.5" fill="${COLORS.textSecondary}">Verified via morluto/rea inspect-artifact (graph: 23a82d6383a92aff)</text>
    <text x="108" y="416" font-family="${FONTS.sans}" font-size="16" font-weight="700" fill="${COLORS.textSecondary}">VERTICAL FEATURE BUNDLES INSIDE REACT NATIVE:</text>
  </g>
  ${rnBars}
  <g opacity="${t1.toFixed(3)}" font-family="${FONTS.mono}" font-size="14">
    <text x="108" y="796" fill="${COLORS.textSecondary}">• Dedicated RN screens: TransactionHistoryV2, ClearingAccount, RegisterOnlineBanking</text>
    <text x="108" y="828" fill="${COLORS.textSecondary}">• Co-exists natively alongside Jetpack Compose 1.7.2 &amp; Alipay+ Griver WebViews</text>
  </g>

  ${card(968, 212, 884, 710, { title: "ANT GROUP / ALIPAY+ GRIVER MINI-APP ENGINE", sub: "373 MINI-APPS REGISTERED", accent: COLORS.cyan, alpha: t2 })}
  <g opacity="${t2.toFixed(3)}">
    <rect x="1008" y="274" width="804" height="96" rx="12" fill="#0B1E36" stroke="${COLORS.cyan}" stroke-width="1.4"/>
    <text x="1032" y="312" font-family="${FONTS.mono}" font-size="21" font-weight="700" fill="${COLORS.cyan}">assets/config/miniapp_config.json : 373 Mini-Apps</text>
    <text x="1032" y="346" font-family="${FONTS.sans}" font-size="15.5" fill="${COLORS.textPrimary}">Sandboxed across :lite1–:lite5 processes · IAPConnect + Alipay+ JSBridge</text>
  </g>
  ${griverCards}
  <g opacity="${t2.toFixed(3)}">
    <rect x="1008" y="704" width="804" height="186" rx="12" fill="#081020" stroke="${COLORS.borderBright}"/>
    <text x="1032" y="740" font-family="${FONTS.sans}" font-size="17" font-weight="700" fill="${COLORS.textPrimary}">morluto/rea AST Verification (assets/GriverBridge/)</text>
    <text x="1032" y="774" font-family="${FONTS.mono}" font-size="14.5" fill="${COLORS.cyan}">Files: appx_inline_bootstrap_min.js · h5_bridge.js · workerjs.js · cover_component.js</text>
    <text x="1032" y="806" font-family="${FONTS.mono}" font-size="14.5" fill="${COLORS.emerald}">Result: 16 Graph Nodes · 16 AST Edges · 4 Timer Relations · 0 Errors</text>
    <text x="1032" y="844" font-family="${FONTS.sans}" font-size="14.5" fill="${COLORS.textSecondary}">Pre-bundled .amr ZIPs wrap deterministic .tar runtimes + CERT.json signatures.</text>
  </g>`;
}

// ---------------------------------------------------------------------------
// SCENE 8: On-Device Edge AI & Vietnamese eKYC Vision Stack
// ---------------------------------------------------------------------------
function renderEdgeAiScene(m: MasterMetrics, p: number): string {
  const engines = [
    {
      title: "Face Detection & Blink Liveness",
      models: "6.30 MiB Models + libVFaceLib.so (1.87 MiB) + libface_detector_v2_jni.so (5.14 MiB)",
      files: "OpenCV lbfmodel.yaml (3.60M) · contours.tfl · blazeface.tfl · fssd_v5 · BCLjoy/eyeclosed.emd",
      use: "Anti-spoofing smile/blink eKYC verification",
      note: "7 facial & expression models (BlazeFace, FSSD, LBF, BCL) verify live user presence locally.",
      col: COLORS.emerald,
    },
    {
      title: "Google ML Kit OCR & Aksara",
      models: "3.17 MiB Models + libmlkit_google_ocr_pipeline.so (6.47M) + libmlkitcommonpipeline.so (6.61M)",
      files: "GOCR conv/lstm models · Taser text detector · Aksara RPN-GCN layout · tflite_langid",
      use: "Offline Vietnamese CCCD National ID OCR",
      note: "Dual OCR stack: GOCR LSTM sequence models + Taser RPN text detectors parse CCCD IDs offline.",
      col: COLORS.cyan,
    },
    {
      title: "Object Detection & Barcode SSD",
      models: "7.03 MiB Models + libbarhopper_v3.so (3.09 MiB)",
      files: "object_labeler.tflite (3.60M) · labeler/localizer_with_validation · barcode_ssd_mobilenet_v1",
      use: "QR VietQR / EMVCo payment scanning & framing",
      note: "Quantized SSD MobileNet v1 & TFLite validation models scan VietQR & EMVCo merchant codes.",
      col: COLORS.amber,
    },
    {
      title: "Tencent NCNN & ONNX eKYC",
      models: "2.33 MiB Models + libpicodet_ncnn.so (2.85 MiB) + librcmefqonnx.so + libtrueidlib.so",
      files: "FastestDet.ncnn.bin (2.31 MiB) + FastestDet.ncnn.param · TrueID / ONNX Runtime",
      use: "Real-time edge card-corner & document localization",
      note: "2.85 MiB NCNN FastestDet engine + ONNX/TrueID corner detection run locally at camera frame rate.",
      col: COLORS.violet,
    },
  ];

  const cardsSvg = engines
    .map((e, i) => {
      const colIdx = i % 2;
      const rowIdx = Math.floor(i / 2);
      const x = 68 + colIdx * 908;
      const y = 212 + rowIdx * 268;
      const t = easeOutCubic(subProgress(p, 0.08 + i * 0.1, 0.52 + i * 0.1));
      const dy = (1 - t) * 24;
      return `
      <g transform="translate(0, ${dy.toFixed(1)})" opacity="${t.toFixed(3)}">
        ${card(x, y, 876, 242, { title: e.title, sub: e.use, accent: e.col })}
        <text x="${x + 24}" y="${y + 82}" font-family="${FONTS.mono}" font-size="14.5" font-weight="700" fill="${e.col}">${esc(e.models)}</text>
        <text x="${x + 24}" y="${y + 124}" font-family="${FONTS.mono}" font-size="13.5" fill="${COLORS.textPrimary}">${esc(e.files)}</text>
        <rect x="${x + 24}" y="${y + 154}" width="828" height="62" rx="8" fill="#081020" stroke="${COLORS.border}"/>
        <text x="${x + 42}" y="${y + 190}" font-family="${FONTS.sans}" font-size="15.5" fill="${COLORS.textSecondary}">${esc(e.note)}</text>
      </g>`;
    })
    .join("\n");

  const botT = easeOutCubic(subProgress(p, 0.45, 0.88));
  return `
  ${cardsSvg}
  ${card(68, 754, 1784, 168, { title: "COMBINED EDGE AI & VISA NFC / eKYC FOOTPRINT", sub: "51 MODEL ARTIFACTS + 7 CV SHARED OBJECTS", accent: COLORS.cyan, alpha: botT })}
  <g opacity="${botT.toFixed(3)}" font-family="${FONTS.mono}" font-size="17">
    <text x="104" y="828" fill="${COLORS.cyan}" font-weight="700">18.83 MiB ML Weights + 26.28 MiB CV Engines = 45.11 MiB Total Edge AI Stack (24.2% of APK Payload)</text>
    <text x="104" y="868" font-family="${FONTS.sans}" font-size="16" fill="${COLORS.textPrimary}">
      Paired with Visa Cloud-Based Payments (assets/com/visa/cbp/*) &amp; ISO-14443 NFC Chip eKYC Lottie guidance workflows.
    </text>
  </g>`;
}

// ---------------------------------------------------------------------------
// SCENE 9: Conclusions & Google Scholar Citation Benchmark
// ---------------------------------------------------------------------------
function renderConclusionsScene(m: MasterMetrics, p: number): string {
  const t1 = easeOutCubic(subProgress(p, 0.06, 0.55));
  const t2 = easeOutCubic(subProgress(p, 0.25, 0.78));

  const takeaways = [
    {
      num: "01",
      title: "Quad-Hybrid Velocity vs. Binary Footprint",
      desc: "Combining Kotlin/Compose, React Native (2,346 modules), Griver (373 mini-apps), and 27 C++ libraries scales super-app velocity at the cost of 186.28 MiB storage and 6 NDK Clang generations.",
      col: COLORS.cyan,
    },
    {
      num: "02",
      title: "Third-Party Dependencies Drive Hardening Gaps",
      desc: "Core app shields enable PIE/NX/Full RELRO/Canaries, yet bundled third-party Firebase Crashlytics binaries account for 100% of RELRO-missing libraries (4/4) and the sole unstripped .symtab.",
      col: COLORS.amber,
    },
    {
      num: "03",
      title: "Layered Defense Meets Legacy Compatibility",
      desc: "7 isolated processes (:lite1–:lite5, :taibmcf) + 69.6% R8 obfuscation + SQLCipher + DETECT_SCREEN_CAPTURE coexist with legacy cleartextTrafficPermitted='true' in base-config.",
      col: COLORS.rose,
    },
    {
      num: "04",
      title: "Offline-First FinTech Edge AI Paradigm",
      desc: "45.11 MiB of local ML models & engines prove Vietnamese mobile banking increasingly shifts OCR, QR, and blink-liveness anti-spoofing onto client NPUs/CPUs.",
      col: COLORS.emerald,
    },
  ];

  const rows = takeaways
    .map((tw, i) => {
      const y = 220 + i * 168;
      const t = easeOutCubic(subProgress(p, 0.06 + i * 0.1, 0.48 + i * 0.1));
      const lines = wrapText(tw.desc, 96);
      return `
      <g opacity="${t.toFixed(3)}">
        <rect x="68" y="${y}" width="1100" height="148" rx="14" fill="${COLORS.bgCard}" stroke="${COLORS.border}" stroke-width="1.4"/>
        <rect x="68" y="${y}" width="8" height="148" rx="4" fill="${tw.col}"/>
        <text x="104" y="${y + 52}" font-family="${FONTS.mono}" font-size="26" font-weight="800" fill="${tw.col}">${tw.num}</text>
        <text x="160" y="${y + 48}" font-family="${FONTS.sans}" font-size="21" font-weight="700" fill="${COLORS.textPrimary}">${esc(tw.title)}</text>
        ${lines.map((ln, li) => `<text x="160" y="${y + 84 + li * 26}" font-family="${FONTS.sans}" font-size="16" fill="${COLORS.textSecondary}">${esc(ln)}</text>`).join("\n")}
      </g>`;
    })
    .join("\n");

  return `
  ${rows}
  ${card(1200, 220, 652, 652, { title: "REPRODUCIBLE CITATION PACKAGE", sub: "OPEN RESEARCH ARTIFACTS", accent: COLORS.cyan, alpha: t2 })}
  <g opacity="${t2.toFixed(3)}" font-family="${FONTS.mono}" font-size="14.5">
    <text x="1228" y="294" fill="${COLORS.cyan}" font-weight="700">Repository Deliverables:</text>
    <text x="1228" y="330" fill="${COLORS.textPrimary}">• paper/RESEARCH_REPORT.md (English IEEE Monograph)</text>
    <text x="1228" y="362" fill="${COLORS.textPrimary}">• paper/RESEARCH_REPORT_VI.md (Vietnamese Companion)</text>
    <text x="1228" y="394" fill="${COLORS.textPrimary}">• data/*.json (6,824-Artifact Verified Datasets)</text>
    <text x="1228" y="426" fill="${COLORS.textPrimary}">• scripts/01–05 (REA + Androguard + LIEF Pipeline)</text>
    <text x="1228" y="458" fill="${COLORS.textPrimary}">• video/src/*.ts (TypeScript 1080p Render Engine)</text>
    <text x="1228" y="490" fill="${COLORS.textPrimary}">• CITATION.cff &amp; paper/references.bib</text>
    <line x1="1228" y1="522" x2="1824" y2="522" stroke="${COLORS.borderBright}"/>
    <text x="1228" y="560" fill="${COLORS.amber}" font-weight="700">BibTeX Citation Key:</text>
    <rect x="1228" y="578" width="596" height="196" rx="10" fill="#081020" stroke="${COLORS.border}"/>
    <text x="1246" y="610" font-size="13" fill="${COLORS.emerald}">@misc{zalopay2026quadhybrid,</text>
    <text x="1246" y="636" font-size="13" fill="${COLORS.textPrimary}">  title={Anatomy of a Quad-Hybrid FinTech Super-App:</text>
    <text x="1246" y="660" font-size="13" fill="${COLORS.textPrimary}">         Static Reverse Engineering of ZaloPay 11.17.0},</text>
    <text x="1246" y="686" font-size="13" fill="${COLORS.textPrimary}">  year={2026}, version={1.0.0},</text>
    <text x="1246" y="712" font-size="13" fill="${COLORS.textPrimary}">  note={Tools: morluto/rea, Androguard, LIEF}</text>
    <text x="1246" y="738" font-size="13" fill="${COLORS.emerald}">}</text>
    <text x="1526" y="836" text-anchor="middle" font-family="${FONTS.sans}" font-size="16" font-weight="600" fill="${COLORS.cyan}">
      Thank you for watching · Open Science &amp; Binary Security
    </text>
  </g>`;
}
