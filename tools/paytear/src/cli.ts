#!/usr/bin/env node
/**
 * PayTear CLI — static teardown of a split APK / XAPK artifact.
 *
 *   tsx src/cli.ts analyze <artifact.xapk|.apk|.zip> --out <dir> [--deep]
 *
 * Emits a machine-readable report (report.json), SVG figures and markdown
 * tables. Designed to run with no JDK, no Android SDK and no network access.
 */
import { createHash } from "node:crypto";
import { mkdirSync, writeFileSync } from "node:fs";
import { basename, extname, join, posix } from "node:path";
import { openZip, readEntry, type ZipArchive } from "./zip.js";
import { parseAxml, attr, attrBool, attrInt, children, find, type AxmlNode } from "./axml.js";
import { parseArsc, localeHistogram, resolveEntry } from "./arsc.js";
import { parseDex, packageHistogram, obfuscationMetrics, type DexFileResult } from "./dex.js";
import { parseElf, type ElfResult } from "./elf.js";
import { parseSigningBlock, type SigningReport } from "./signing.js";
import { matchIndicators, extractUrls, domainOf, registrableDomain, findSensitive, redact } from "./strings.js";
import { matchSdks, DANGEROUS_PERMISSIONS, type SdkMatch } from "./fingerprints.js";
import { shannon } from "./entropy.js";
import { hbarChart, lineChart, stackedBar, PALETTE } from "./chart.js";
import { mdTable, fmtBytes, fmtNum, pct, stableJson } from "./report.js";

const TOOL_VERSION = "0.4.0";

interface Args {
  cmd: string;
  input: string;
  out: string;
  deep: boolean;
}

function parseArgs(argv: string[]): Args {
  const [cmd, input, ...rest] = argv;
  let out = "analysis";
  let deep = false;
  for (let i = 0; i < rest.length; i++) {
    if (rest[i] === "--out") out = rest[++i];
    else if (rest[i] === "--deep") deep = true;
  }
  if (!cmd || !input) {
    console.error("usage: paytear analyze <artifact.xapk|.apk> --out <dir> [--deep]");
    process.exit(2);
  }
  return { cmd, input, out, deep };
}

function sha256(buf: Buffer): string {
  return createHash("sha256").update(buf).digest("hex");
}

interface SplitAnalysis {
  name: string;
  size: number;
  sha256: string;
  kind: "base" | "config" | "standalone";
  packageName?: string;
  versionName?: string;
  versionCode?: number;
  minSdk?: number;
  targetSdk?: number;
  compileSdk?: number;
  appClass?: string;
  appAttrs?: Record<string, string>;
  permissions: string[];
  components: { activity: number; service: number; receiver: number; provider: number; alias: number };
  exported: { type: string; name: string | null; permission: string | null; intentFilters?: number }[];
  deepLinks: { scheme: string; host: string | null; path: string | null; component: string | null }[];
}

async function main() {
  const args = parseArgs(process.argv.slice(2));
  if (args.cmd !== "analyze") {
    console.error(`unknown command: ${args.cmd}`);
    process.exit(2);
  }
  const t0 = Date.now();
  const buf = await (await import("node:fs/promises")).readFile(args.input);
  const root = openZip(buf);
  mkdirSync(args.out, { recursive: true });
  mkdirSync(join(args.out, "figures"), { recursive: true });
  mkdirSync(join(args.out, "tables"), { recursive: true });

  // ---- 1. expand container -------------------------------------------------
  const apks: { name: string; buffer: Buffer; zip: ZipArchive }[] = [];
  for (const e of root.entries) {
    if (!/\.(apk|xapk)$/i.test(e.name)) continue;
    const data = readEntry(root, e);
    if (e.name.toLowerCase().endsWith(".apk")) {
      apks.push({ name: posix.basename(e.name), buffer: data, zip: openZip(data) });
    } else {
      const inner = openZip(data);
      for (const ie of inner.entries) {
        if (!/\.apk$/i.test(ie.name)) continue;
        const ib = readEntry(inner, ie);
        apks.push({ name: posix.basename(ie.name), buffer: ib, zip: openZip(ib) });
      }
    }
  }

  const manifestJsonEntry = root.entries.find((e) => e.name === "manifest.json");
  const xapkManifest = manifestJsonEntry ? JSON.parse(readEntry(root, manifestJsonEntry).toString("utf8")) : null;

  // ---- 2. per-split structural analysis ------------------------------------
  const splits: any[] = [];
  const allClassNames: string[] = [];
  const allAssets: string[] = [];
  const nativeLibs: { split: string; name: string; abi: string; buffer: Buffer }[] = [];
  const dexResults: DexFileResult[] = [];
  const allDexStrings = new Set<string>();
  let arscInfo: any = null;
  let appLabel: string | null = null;
  let baseManifest: AxmlNode | null = null;
  let baseSigning: SigningReport | null = null;

  for (const apk of apks) {
    const name = apk.name;
    const isBase = /base\.apk$/i.test(name) && !/config\./.test(name);
    const split: any = {
      name,
      size: apk.buffer.length,
      sha256: sha256(apk.buffer),
      kind: isBase ? "base" : /^config\./.test(name) ? "config" : "standalone",
      entryCount: apk.zip.entries.length,
      uncompressedBytes: apk.zip.entries.reduce((a, e) => a + e.uncompressedSize, 0),
      permissions: [],
      components: { activity: 0, service: 0, receiver: 0, provider: 0, alias: 0 },
      exported: [] as any[],
      deepLinks: [] as any[],
      assets: [] as any[],
      nativeLibs: [] as any[],
      dexFiles: [] as any[],
    };

    // manifest
    if (apk.zip.byName.has("AndroidManifest.xml")) {
      const { root: xmlRoot } = parseAxml(readEntry(apk.zip, "AndroidManifest.xml"));
      if (xmlRoot) {
        if (isBase) baseManifest = xmlRoot;
        split.packageName = attr(xmlRoot, "package");
        split.versionName = attr(xmlRoot, "versionName");
        split.versionCode = attrInt(xmlRoot, "versionCode");
        const sdk = find(xmlRoot, "uses-sdk");
        if (sdk) {
          split.minSdk = attrInt(sdk, "minSdkVersion");
          split.targetSdk = attrInt(sdk, "targetSdkVersion");
        }
        split.compileSdk = attrInt(xmlRoot, "compileSdkVersion") || undefined;
        for (const u of children(xmlRoot, "uses-permission")) {
          const p = attr(u, "name");
          if (p) split.permissions.push(p);
        }
        for (const u of children(xmlRoot, "uses-permission-sdk-23")) {
          const p = attr(u, "name");
          if (p) split.permissions.push(p);
        }
        const app = children(xmlRoot, "application")[0];
        if (app) {
          split.appClass = attr(app, "name");
          split.appAttrs = Object.fromEntries(app.attributes.map((a) => [a.name.replace(/^android:/, ""), String(a.value)]));
          for (const c of app.children) {
            if (c.tag === "activity" || c.tag === "activity-alias") split.components.activity++;
            if (c.tag === "service") split.components.service++;
            if (c.tag === "receiver") split.components.receiver++;
            if (c.tag === "provider") split.components.provider++;
            const exported = attr(c, "exported") === "true";
            if (exported) {
              split.exported.push({
                type: c.tag,
                name: attr(c, "name"),
                permission: attr(c, "permission"),
                intentFilters: c.children.filter((x) => x.tag === "intent-filter").length,
              });
            }
            for (const f of c.children.filter((x) => x.tag === "intent-filter")) {
              for (const d of f.children.filter((x) => x.tag === "data")) {
                const scheme = attr(d, "scheme");
                if (!scheme) continue;
                split.deepLinks.push({
                  component: attr(c, "name"),
                  scheme,
                  host: attr(d, "host") ?? attr(d, "hosts"),
                  path: attr(d, "pathPrefix") ?? attr(d, "path") ?? attr(d, "pathPattern"),
                  autoVerify: attrBool(f, "autoVerify"),
                });
              }
            }
          }
        }
        // application label
        if (app) {
          const label = attr(app, "label");
          if (label && label.startsWith("@0x")) appLabel = label;
        }
      }
    }

    // signing
    const signing = parseSigningBlock(apk.buffer, apk.zip.centralDirectoryOffset, apk.zip.eocdOffset, apk.zip);
    split.signing = {
      hasSigningBlock: signing.hasSigningBlock,
      blockSize: signing.blockSize,
      v1Present: signing.v1Present,
      pairIds: signing.pairIds,
      schemes: signing.schemes.map((s) => ({
        scheme: s.scheme,
        signers: s.signers.length,
        contentDigestVerified: s.contentDigestVerified,
        contentDigestAlgorithm: s.contentDigestAlgorithm,
        contentDigestExpected: s.contentDigestExpected,
        contentDigestComputed: s.contentDigestComputed,
        certificates: s.signers.flatMap((x) => x.certificates),
        algorithms: s.signers.flatMap((x) => x.signatures.map((y) => ({ name: y.algorithmName, verified: y.verified }))),
        minSdk: s.signers[0]?.minSdk,
        maxSdk: s.signers[0]?.maxSdk,
      })),
      wholeFileSha256: signing.wholeFileSha256,
    };
    if (isBase) baseSigning = signing;

    // resources
    if (apk.zip.byName.has("resources.arsc") && !arscInfo) {
      try {
        const table = parseArsc(readEntry(apk.zip, "resources.arsc"));
        const locales = localeHistogram(table);
        let entries = 0, typeNames = new Map<string, number>();
        for (const p of table.packages) for (const t of p.types) {
          typeNames.set(t.name, (typeNames.get(t.name) ?? 0) + t.entryCount);
          for (const b of t.configs) entries += b.entries.size;
        }
        arscInfo = {
          packages: table.packages.map((p) => ({ id: p.id, name: p.name, types: p.types.length })),
          entryCount: entries,
          locales,
          typeHistogram: [...typeNames.entries()].map(([name, count]) => ({ name, count })).sort((a, b) => b.count - a.count),
          appLabel: appLabel && appLabel.startsWith("@0x")
            ? (() => {
                const id = parseInt(appLabel.slice(3), 16);
                const e = resolveEntry(table, id);
                return e?.value?.string ?? null;
              })()
            : null,
        };
      } catch (err) {
        split.arscError = String(err);
      }
    }

    // dex + assets + native libs
    for (const e of apk.zip.entries) {
      if (e.isDirectory) continue;
      const lower = e.name.toLowerCase();
      if (lower.endsWith(".so")) {
        const m = e.name.match(/lib\/([^/]+)\/(.+)$/);
        if (m) nativeLibs.push({ split: name, abi: m[1], name: m[2], buffer: readEntry(apk.zip, e) });
      }
      if (lower.endsWith(".dex")) {
        const data = readEntry(apk.zip, e);
        try {
          const dex = parseDex(data, `${name}:${e.name}`, args.deep);
          dexResults.push(dex);
          for (const c of dex.classes) allClassNames.push(c.name);
          for (const s of dex.strings) if (s.length >= 6 && s.length <= 512) allDexStrings.add(s);
          split.dexFiles.push({ path: e.name, classes: dex.classes.length, methods: dex.stats.methods, size: data.length });
        } catch (err) {
          split.dexFiles.push({ path: e.name, error: String(err) });
        }
      }
      if (lower.startsWith("assets/")) {
        allAssets.push(e.name);
        const plain = /\.(json|js|html|txt|pem|xml|properties|md|param|cfg)$/i.test(lower) || e.name.split("/").pop()?.indexOf(".") === -1;
        let entropy: number | null = null;
        if (e.uncompressedSize < 4 * 1024 * 1024) entropy = Number(shannon(readEntry(apk.zip, e)).toFixed(3));
        split.assets.push({ path: e.name, size: e.uncompressedSize, ext: extname(e.name).toLowerCase(), entropy, text: plain });
      }
    }
    splits.push(split);
  }

  // ---- 3. aggregate census -------------------------------------------------
  const dexTotals = dexResults.reduce(
    (a, d) => ({
      files: a.files + 1,
      classes: a.classes + d.classes.length,
      methods: a.methods + d.stats.methods,
      fields: a.fields + d.stats.fields,
      instructions: a.instructions + d.stats.instructions,
      invokeEdges: a.invokeEdges + d.stats.invokeEdges,
      stringConstants: a.stringConstants + d.stats.stringConstants,
      strings: a.strings + d.strings.length,
      bytes: a.bytes + d.header.fileSize,
    }),
    { files: 0, classes: 0, methods: 0, fields: 0, instructions: 0, invokeEdges: 0, stringConstants: 0, strings: 0, bytes: 0 },
  );

  const allClasses = dexResults.flatMap((d) => d.classes);
  const pkgStats = packageHistogram(allClasses, 3).slice(0, 40);
  const obf = obfuscationMetrics(allClasses);
  const topClasses = [...allClasses].sort((a, b) => b.methodCount - a.methodCount).slice(0, 25)
    .map((c) => ({ name: c.name, methods: c.methodCount, instructionCount: c.instructionCount }));

  const elfResults: ElfResult[] = nativeLibs.map((l) => {
    try { return parseElf(l.buffer, l.name); } catch (e) { return null as any; }
  }).filter(Boolean);

  const libRows = elfResults.map((e) => {
    const meta = nativeLibs.find((l) => l.name === e.file)!;
    return {
      name: e.file,
      abi: meta.abi,
      size: e.size,
      textSize: e.textSize,
      needed: e.needed,
      jniExports: e.jniExports.length,
      exported: e.exportedFunctions.length,
      symbols: e.symbolCount,
      hardening: e.hardening,
      buildId: e.buildId,
      comment: e.comment,
      highEntropyRegions: e.highEntropyRegions,
      sha256: sha256(meta.buffer),
    };
  }).sort((a, b) => b.size - a.size);

  const permSet = new Set<string>(splits.flatMap((s) => s.permissions));
  const dangerous = [...permSet]
    .filter((p) => DANGEROUS_PERMISSIONS[p])
    .map((p) => ({ permission: p, ...DANGEROUS_PERMISSIONS[p] }))
    .sort((a, b) => (a.risk === b.risk ? a.permission.localeCompare(b.permission) : a.risk === "high" ? -1 : 1));

  const sdkMatches = matchSdks({
    classNames: allClassNames,
    nativeLibs: nativeLibs.map((l) => l.name),
    assets: allAssets,
  });

  const indicators = matchIndicators(allDexStrings);
  const urls = extractUrls(allDexStrings);
  const domains = [...new Set(urls.map((u) => domainOf(u.url)).filter((d): d is string => !!d))];
  const domainRollup = [...domains.reduce((m, d) => {
    const r = registrableDomain(d);
    m.set(r, (m.get(r) ?? 0) + 1);
    return m;
  }, new Map<string, number>()).entries()].map(([domain, urls]) => ({ domain, urls })).sort((a, b) => b.urls - a.urls);
  const sensitive = findSensitive(allDexStrings);

  // ---- 4. report ----------------------------------------------------------
  const report = {
    meta: {
      toolkit: "PayTear",
      version: TOOL_VERSION,
      generatedAt: new Date().toISOString(),
      runtime: `node ${process.version}`,
      durationMs: 0,
      deepScan: args.deep,
      input: basename(args.input),
      inputSha256: sha256(buf),
      inputSize: buf.length,
    },
    container: {
      zipComment: root.comment,
      entries: root.entries.length,
      xapkManifest,
      apkSplits: splits.map((s) => ({ name: s.name, size: s.size, sha256: s.sha256, kind: s.kind })),
    },
    app: {
      packageName: splits.find((s) => s.packageName)?.packageName ?? null,
      versionName: splits.find((s) => s.versionName)?.versionName ?? null,
      versionCode: splits.find((s) => s.versionCode)?.versionCode ?? null,
      minSdk: splits.find((s) => s.minSdk)?.minSdk ?? null,
      targetSdk: splits.find((s) => s.targetSdk)?.targetSdk ?? null,
      compileSdk: splits.find((s) => s.compileSdk)?.compileSdk ?? null,
      label: arscInfo?.appLabel ?? null,
      applicationClass: splits.find((s) => s.appClass)?.appClass ?? null,
      abis: [...new Set(nativeLibs.map((l) => l.abi))],
    },
    splits,
    dex: {
      totals: dexTotals,
      perFile: dexResults.map((d) => ({
        file: d.file,
        bytes: d.header.fileSize,
        version: d.header.version,
        classes: d.classes.length,
        methods: d.stats.methods,
        fields: d.stats.fields,
        strings: d.strings.length,
        instructions: d.stats.instructions,
        invokeEdges: d.stats.invokeEdges,
        sha1: d.header.signature,
        checksum: d.header.checksum.toString(16),
      })),
      packageHistogram: pkgStats,
      obfuscation: obf,
      topClasses,
    },
    resources: arscInfo,
    native: {
      count: libRows.length,
      totalBytes: libRows.reduce((a, l) => a + l.size, 0),
      abis: [...new Set(libRows.map((l) => l.abi))],
      libs: libRows,
      hardeningSummary: {
        nx: libRows.filter((l) => l.hardening.nx).length,
        pie: libRows.filter((l) => l.hardening.pie).length,
        fullRelro: libRows.filter((l) => l.hardening.relro === "full").length,
        partialRelro: libRows.filter((l) => l.hardening.relro === "partial").length,
        canary: libRows.filter((l) => l.hardening.canary).length,
        fortify: libRows.filter((l) => l.hardening.fortify).length,
      },
    },
    assets: {
      count: allAssets.length,
      totalBytes: splits.flatMap((s) => s.assets).reduce((a, x) => a + x.size, 0),
      notable: splits.flatMap((s) => s.assets)
        .filter((a: any) => a.size > 100_000 || /\.(dex|zip|jsbundle|dat|pem|tflite|bin|onnx)$/i.test(a.path))
        .sort((a: any, b: any) => b.size - a.size)
        .slice(0, 60),
    },
    supplyChain: {
      sdkMatches,
      vendors: [...new Set(sdkMatches.map((m) => m.vendor))].sort(),
    },
    privacy: {
      permissions: [...permSet].sort(),
      dangerous,
      dangerousCount: dangerous.length,
      highRiskCount: dangerous.filter((d) => d.risk === "high").length,
    },
    indicators,
    network: {
      urlCount: urls.length,
      domainCount: domains.length,
      domains: domains.sort(),
      domainRollup,
      cleartextUrls: urls.filter((u) => u.url.startsWith("http://")).slice(0, 30).map((u) => redact(u.url)),
      topUrls: urls.slice(0, 40).map((u) => redact(u.url)),
    },
    sensitive,
  };
  (report.meta as any).durationMs = Date.now() - t0;

  writeFileSync(join(args.out, "report.json"), stableJson(report));

  // ---- 5. figures ---------------------------------------------------------
  writeFileSync(join(args.out, "figures", "packages.svg"), hbarChart({
    title: "Classes per package (top 24)",
    data: pkgStats.slice(0, 24).map((p, i) => ({
      label: p.pkg.replace(/\//g, "."),
      value: p.classes,
      color: [PALETTE.cyan, PALETTE.blue, PALETTE.violet, PALETTE.green][i % 4],
    })),
    valueFormat: (n) => fmtNum(n),
  }));
  writeFileSync(join(args.out, "figures", "dex-census.svg"), hbarChart({
    title: "DEX payload size by file",
    data: [...dexResults].sort((a, b) => b.header.fileSize - a.header.fileSize).slice(0, 20).map((d) => ({
      label: d.file.replace(/^.*:/, ""),
      value: Math.round(d.header.fileSize / 1024),
      color: PALETTE.blue,
    })),
    valueFormat: (n) => `${fmtNum(n)} KiB`,
  }));
  const biggestText = elfResults.slice().sort((a, b) => b.textSize - a.textSize)[0];
  if (biggestText) {
    writeFileSync(join(args.out, "figures", "entropy.svg"), lineChart({
      title: `.text entropy profile — ${biggestText.file}`,
      values: biggestText.entropyProfile,
      min: 0,
      max: 8,
    }));
  }
  writeFileSync(join(args.out, "figures", "lib-size.svg"), hbarChart({
    title: "Native library payload (MiB)",
    data: libRows.slice(0, 18).map((l, i) => ({
      label: l.name,
      value: Number((l.size / 1048576).toFixed(2)),
      color: [PALETTE.violet, PALETTE.rose, PALETTE.amber, PALETTE.cyan][i % 4],
    })),
    valueFormat: (n) => `${n} MiB`,
  }));
  writeFileSync(join(args.out, "figures", "locale.svg"), hbarChart({
    title: "Localised resource entries",
    data: (arscInfo?.locales ?? []).slice(0, 18).map((l: any) => ({ label: l.locale, value: l.entries, color: PALETTE.green })),
  }));

  // ---- 6. markdown tables -------------------------------------------------
  writeFileSync(join(args.out, "tables", "census.md"), [
    "# Static census",
    "",
    mdTable(["Metric", "Value"], [
      ["APK splits", splits.length],
      ["DEX files", dexTotals.files],
      ["Classes", fmtNum(dexTotals.classes)],
      ["Methods", fmtNum(dexTotals.methods)],
      ["Fields", fmtNum(dexTotals.fields)],
      ["String constants", `${fmtNum(dexTotals.stringConstants)} / ${fmtNum(dexTotals.strings)} in pool`],
      ["Instructions decoded", fmtNum(dexTotals.instructions)],
      ["Invoke edges", fmtNum(dexTotals.invokeEdges)],
      ["DEX bytes", fmtBytes(dexTotals.bytes)],
      ["Native libraries", libRows.length],
      ["Native bytes", fmtBytes(libRows.reduce((a, l) => a + l.size, 0))],
      ["Assets", allAssets.length],
      ["Resource entries", fmtNum(arscInfo?.entryCount ?? 0)],
    ]),
    "",
    "## Native hardening",
    "",
    mdTable(["Library", "MiB", "NX", "RELRO", "Canary", "FORTIFY", "JNI exports"],
      libRows.slice(0, 30).map((l) => [
        l.name, (l.size / 1048576).toFixed(2), l.hardening.nx ? "yes" : "no",
        l.hardening.relro, l.hardening.canary ? "yes" : "no", l.hardening.fortify ? "yes" : "no", l.jniExports,
      ])),
  ].join("\n"));

  writeFileSync(join(args.out, "tables", "supply-chain.md"), [
    "# Supply chain",
    "",
    mdTable(["SDK", "Vendor", "Category", "Evidence", "Value"],
      sdkMatches.map((m) => [m.name, m.vendor, m.category, m.evidence, m.classes ? `${m.value} (${m.classes} classes)` : m.value])),
    "",
    "# Privacy-relevant permissions",
    "",
    mdTable(["Permission", "Group", "Risk", "Note"], dangerous.map((d) => [d.permission, d.group, d.risk, d.note])),
  ].join("\n"));

  writeFileSync(join(args.out, "tables", "indicators.md"), [
    "# Behavioural indicators (string-corroborated)",
    "",
    mdTable(["Indicator", "Category", "Matches", "Sample"], indicators.map((i) => [i.label, i.category, fmtNum(i.matches), i.samples[0] ?? ""])),
  ].join("\n"));

  console.log(`[paytear] ${args.input}`);
  console.log(`  splits=${splits.length} dex=${dexTotals.files} classes=${dexTotals.classes} methods=${dexTotals.methods} libs=${libRows.length}`);
  console.log(`  sdk-fingerprints=${sdkMatches.length} domains=${domains.length} indicators=${indicators.length}`);
  console.log(`  -> ${args.out}/report.json (${Date.now() - t0} ms)`);
}

main().catch((err) => {
  console.error(err);
  process.exit(1);
});
