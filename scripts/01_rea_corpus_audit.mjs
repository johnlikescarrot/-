import { existsSync, mkdirSync, readdirSync, writeFileSync } from "node:fs";
import { basename, join, resolve } from "node:path";
import { PassThrough } from "node:stream";
import { pathToFileURL } from "node:url";

const REA_CANDIDATES = [
  process.env.REA_AGENTS_ROOT,
  "/tmp/rea-tools/node_modules/rea-agents",
  resolve(process.cwd(), "node_modules/rea-agents"),
].filter(Boolean);

const REA_ROOT = REA_CANDIDATES.find((p) =>
  existsSync(resolve(p, "dist/cli-runtime/cliOneShot.js")),
);
if (!REA_ROOT) {
  throw new Error("rea-agents@6.10.1 runtime not found.");
}

const { runOneShotCli } = await import(
  pathToFileURL(resolve(REA_ROOT, "dist/cli-runtime/cliOneShot.js")).href
);

async function runRea(args) {
  let out = "";
  let err = "";
  let code = 0;
  let closeFn = null;
  const dest = new PassThrough();
  dest.on("data", (chunk) => {
    out += chunk.toString("utf8");
  });
  await runOneShotCli(args, {
    environment: process.env,
    runtime: {
      packageName: "rea-agents",
      packageVersion: "6.10.1",
      versions: process.versions,
      platform: process.platform,
      architecture: process.arch,
    },
    stdoutIsTerminal: false,
    resultDestination: dest,
    closedStdoutStatus: 141,
    writeStdout: (s) => {
      out += s;
    },
    writeStderr: (s) => {
      err += s;
    },
    stdoutClosed: () => false,
    exitCode: () => code,
    setExitCode: (c) => {
      code = c;
    },
    ownCliResources: (fn) => {
      closeFn = fn;
    },
    exitRequested: () => false,
    exit: (c) => {
      code = c;
    },
  });
  if (typeof closeFn === "function") {
    try {
      await closeFn();
    } catch {
      // ignore resource cleanup error
    }
  }
  const trimmed = out.trim();
  let parsed = null;
  try {
    parsed = JSON.parse(trimmed);
  } catch (e) {
    parsed = { parse_error: String(e), raw_head: trimmed.slice(0, 1000) };
  }
  return { code, data: parsed, stderr: err };
}

const REPO_ROOT = resolve(process.cwd());
const CORPUS = "/tmp/zalopay_corpus";
const RAW_DIR = join(CORPUS, "rea_raw");
const EVID_DIR = join(REPO_ROOT, "evidence");
mkdirSync(RAW_DIR, { recursive: true });
mkdirSync(EVID_DIR, { recursive: true });

const OUTER_ZIP = join(
  REPO_ROOT,
  "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip",
);
const XAPK_FILES = readdirSync(CORPUS).filter((f) => f.endsWith(".xapk"));
if (XAPK_FILES.length === 0) throw new Error("No .xapk found in /tmp/zalopay_corpus");
const XAPK_PATH = join(CORPUS, XAPK_FILES[0]);

const APKS_DIR = join(CORPUS, "xapk");
const APK_FILES = readdirSync(APKS_DIR)
  .filter((f) => f.endsWith(".apk"))
  .sort();

console.log("[1/5] Inspecting outer ZIP, inner XAPK, all 5 APKs, and android.1.zip...");
const nestedZip = join(
  CORPUS,
  "apks/vn.com.vng.zalopay/assets/android.1.zip",
);
const containerTargets = [
  { label: "outer_zip", path: OUTER_ZIP },
  { label: "inner_xapk", path: XAPK_PATH },
  ...APK_FILES.map((f) => ({ label: f, path: join(APKS_DIR, f) })),
  { label: "assets/android.1.zip", path: nestedZip },
];

const containersOut = [];
for (const t of containerTargets) {
  const art = await runRea(["inspect-artifact", t.path, "--json"]);
  const exp = await runRea(["audit-release-exposure", t.path, "--json"]);
  const invStep = art.data?.normalized_result?.substeps?.find(
    (s) => s.operation === "inventory_artifact",
  );
  const manifest = invStep?.evidence?.normalized_result?.manifest ?? null;
  const occurrences = invStep?.evidence?.normalized_result?.occurrences ?? [];
  const samplePaths = occurrences
    .slice(0, 25)
    .map((o) => ({ path: o.logical_path, size: o.declared_size, format: o.artifact_format }));

  containersOut.push({
    label: t.label,
    path: t.path,
    inspect_artifact: {
      evidence_id: art.data?.evidence_id ?? null,
      subject_sha256: art.data?.subject?.identity?.sha256 ?? null,
      manifest,
      occurrence_count: occurrences.length,
      sample_occurrences: samplePaths,
      limitations: art.data?.limitations ?? [],
    },
    release_exposure: {
      evidence_id: exp.data?.evidence_id ?? null,
      normalized_result: exp.data?.normalized_result ?? exp.data,
    },
  });
  console.log(
    `  container ${t.label}: occurrences=${occurrences.length}, ev=${art.data?.evidence_id?.slice(0, 16)}`,
  );
}
writeFileSync(
  join(EVID_DIR, "rea_containers.json"),
  JSON.stringify({ tool: "rea-agents@6.10.1", containers: containersOut }, null, 2),
);

console.log("[2/5] Inspecting APK v1/v2/v3 signatures & Google Play Source Stamps...");
const apkSignatures = [];
for (const f of APK_FILES) {
  const p = join(APKS_DIR, f);
  const sig = await runRea(["inspect-apk-signature", p, "--json"]);
  apkSignatures.push({
    apk: f,
    evidence_id: sig.data?.evidence_id ?? null,
    confidence: sig.data?.confidence ?? null,
    normalized_result: sig.data?.normalized_result ?? sig.data,
    limitations: sig.data?.limitations ?? [],
  });
  const nr = sig.data?.normalized_result;
  console.log(
    `  sig ${f}: v1=${nr?.schemes?.v1?.present} v2=${nr?.schemes?.v2?.present} v3=${nr?.schemes?.v3?.present} stamp=${nr?.source_stamp?.present} consistent=${nr?.posture?.self_consistent}`,
  );
}
writeFileSync(
  join(EVID_DIR, "rea_apk_signatures.json"),
  JSON.stringify({ tool: "rea-agents@6.10.1", signatures: apkSignatures }, null, 2),
);

console.log("[3/5] Auditing ALL 27 native ELF .so libraries with 9 REA commands each...");
const SO_DIR = join(CORPUS, "apks/config.armeabi_v7a/lib/armeabi-v7a");
const SO_FILES = readdirSync(SO_DIR)
  .filter((f) => f.endsWith(".so"))
  .sort();

const nativeAudit = [];
for (const so of SO_FILES) {
  const p = join(SO_DIR, so);
  const [hard, deps, notes, exps, init, unwind, dwarf, dbg, ent] =
    await Promise.all([
      runRea(["inspect-binary-hardening", p, "--json"]),
      runRea(["inspect-elf-dependencies", p, "--json"]),
      runRea(["inspect-elf-notes", p, "--json"]),
      runRea(["inspect-native-exports", p, "--json"]),
      runRea(["inspect-native-initializers", p, "--json"]),
      runRea(["inspect-elf-unwind-ranges", p, "--json"]),
      runRea(["inspect-dwarf-units", p, "--json"]),
      runRea(["inspect-elf-debug-link", p, "--json"]),
      runRea(["inspect-byte-entropy", p, "--json"]),
    ]);

  const hardRows = hard.data?.normalized_result?.images?.[0]?.rows ?? [];
  const hardMap = Object.fromEntries(hardRows.map((r) => [r.property, r.state]));
  const exportList = exps.data?.normalized_result?.exports ?? [];
  const jniExports = exportList
    .map((e) => e.name)
    .filter((n) => n && (n.startsWith("Java_") || n.includes("JNI_OnLoad")));

  const initArr = init.data?.normalized_result?.initializers ?? [];
  const finArr = init.data?.normalized_result?.finalizers ?? [];

  const rec = {
    name: so,
    bytes: hard.data?.normalized_result?.artifact?.bytes ?? null,
    sha256: hard.data?.normalized_result?.artifact?.sha256 ?? null,
    evidence_ids: {
      hardening: hard.data?.evidence_id ?? null,
      dependencies: deps.data?.evidence_id ?? null,
      notes: notes.data?.evidence_id ?? null,
      exports: exps.data?.evidence_id ?? null,
      initializers: init.data?.evidence_id ?? null,
      unwind_ranges: unwind.data?.evidence_id ?? null,
      dwarf_units: dwarf.data?.evidence_id ?? null,
      debug_link: dbg.data?.evidence_id ?? null,
      byte_entropy: ent.data?.evidence_id ?? null,
    },
    hardening: hardMap,
    dependencies: {
      soname: deps.data?.normalized_result?.dynamic?.soname?.name ?? null,
      bind_now: deps.data?.normalized_result?.dynamic?.flags?.names ?? [],
      needed: (deps.data?.normalized_result?.needed ?? []).map((x) => x.name),
      import_count: deps.data?.normalized_result?.imports?.length ?? 0,
      sample_imports: (deps.data?.normalized_result?.imports ?? [])
        .slice(0, 40)
        .map((i) => i.name),
    },
    notes: notes.data?.normalized_result?.notes ?? [],
    exports: {
      total: exportList.length,
      jni_related: jniExports,
      top_exports: exportList.slice(0, 30).map((e) => ({
        name: e.name,
        type: e.type,
        binding: e.binding,
        address: e.address ?? e.value ?? null,
      })),
    },
    initializers: {
      initializer_count: initArr.length,
      finalizer_count: finArr.length,
      details: init.data?.normalized_result ?? null,
    },
    unwind_ranges_summary: {
      status: unwind.data?.normalized_result?.status ?? null,
      range_count:
        unwind.data?.normalized_result?.ranges?.length ??
        unwind.data?.normalized_result?.entry_count ??
        null,
      raw: unwind.data?.normalized_result ?? null,
    },
    dwarf_units: dwarf.data?.normalized_result ?? null,
    debug_link: dbg.data?.normalized_result ?? null,
    byte_entropy: {
      overall_entropy:
        ent.data?.normalized_result?.overall?.shannon_entropy ??
        ent.data?.normalized_result?.shannon_entropy ??
        null,
      raw_summary: ent.data?.normalized_result ?? null,
    },
  };
  nativeAudit.push(rec);
  console.log(
    `  so ${so}: ${rec.bytes}B | NX=${hardMap.nx} RELRO=${hardMap.relro} Canary=${hardMap.stack_protector} Stripped=${hardMap.stripped} | needed=${rec.dependencies.needed.length} exports=${rec.exports.total} jni=${jniExports.length}`,
  );
}
writeFileSync(
  join(RAW_DIR, "rea_native_audit_full.json"),
  JSON.stringify(nativeAudit, null, 2),
);

// Write a clean compact version to evidence/rea_native_audit.json (omit massive raw unwind arrays if large)
const compactNative = nativeAudit.map((r) => ({
  ...r,
  unwind_ranges_summary: {
    status: r.unwind_ranges_summary.status,
    range_count: r.unwind_ranges_summary.range_count,
    sections: r.unwind_ranges_summary.raw?.sections ?? null,
  },
  byte_entropy: {
    overall: r.byte_entropy.raw_summary?.overall ?? r.byte_entropy.raw_summary?.file ?? null,
    classifications: r.byte_entropy.raw_summary?.classifications ?? null,
    high_entropy_block_ratio: r.byte_entropy.raw_summary?.high_entropy_block_ratio ?? null,
    block_count: r.byte_entropy.raw_summary?.blocks?.length ?? null,
  },
}));
writeFileSync(
  join(EVID_DIR, "rea_native_audit.json"),
  JSON.stringify({ tool: "rea-agents@6.10.1", count: compactNative.length, libraries: compactNative }, null, 2),
);

console.log("[4/5] Running REA inspect-byte-entropy across DEX, DAT, NCNN, TFLite, baseline.prof, trueidlib.so...");
const BASE_APK_DIR = join(CORPUS, "apks/vn.com.vng.zalopay");
const dexFiles = readdirSync(BASE_APK_DIR)
  .filter((f) => f.endsWith(".dex"))
  .sort((a, b) => a.localeCompare(b, undefined, { numeric: true }))
  .map((f) => ({ category: "dex", rel: f, path: join(BASE_APK_DIR, f) }));

const extraEntropyTargets = [
  ...dexFiles,
  { category: "dex_asset", rel: "assets/audience_network.dex", path: join(BASE_APK_DIR, "assets/audience_network.dex") },
  { category: "profile", rel: "assets/dexopt/baseline.prof", path: join(BASE_APK_DIR, "assets/dexopt/baseline.prof") },
  { category: "obfuscated_asset", rel: "assets/trueidlib.so", path: join(BASE_APK_DIR, "assets/trueidlib.so") },
  { category: "obfuscated_asset", rel: "assets/fgcpkjvqe.dat", path: join(BASE_APK_DIR, "assets/fgcpkjvqe.dat") },
  { category: "obfuscated_asset", rel: "assets/taibmcf.dat", path: join(BASE_APK_DIR, "assets/taibmcf.dat") },
  { category: "obfuscated_asset", rel: "assets/bshield.dat", path: join(BASE_APK_DIR, "assets/bshield.dat") },
  { category: "ncnn_model", rel: "assets/FastestDet.ncnn.bin", path: join(BASE_APK_DIR, "assets/FastestDet.ncnn.bin") },
];

const entropyResults = [];
for (const t of extraEntropyTargets) {
  const ent = await runRea(["inspect-byte-entropy", t.path, "--json"]);
  const nr = ent.data?.normalized_result ?? {};
  entropyResults.push({
    category: t.category,
    rel_path: t.rel,
    evidence_id: ent.data?.evidence_id ?? null,
    bytes: nr.artifact?.bytes ?? null,
    sha256: nr.artifact?.sha256 ?? null,
    normalized_summary: {
      overall: nr.overall ?? nr.file ?? null,
      block_size: nr.block_size ?? null,
      block_count: nr.blocks?.length ?? null,
      summary: nr.summary ?? null,
    },
  });
}
writeFileSync(
  join(EVID_DIR, "rea_entropy_targets.json"),
  JSON.stringify({ tool: "rea-agents@6.10.1", targets: entropyResults }, null, 2),
);

console.log("[5/5] Running REA analyze-javascript-application on Griver/JSBridge assets...");
const jsRes = await runRea([
  "analyze-javascript-application",
  join(BASE_APK_DIR, "assets"),
  "--json",
]);
writeFileSync(join(RAW_DIR, "rea_js_bridge_full.json"), JSON.stringify(jsRes.data, null, 2));

const jsNr = jsRes.data?.normalized_result ?? {};
const jsSummary = {
  tool: "rea-agents@6.10.1",
  evidence_id: jsRes.data?.evidence_id ?? null,
  confidence: jsRes.data?.confidence ?? null,
  limitations: jsRes.data?.limitations ?? [],
  discovered_sources: jsNr.route_inventory?.sources ?? [],
  application_graph_summary: {
    module_count: jsNr.application_graph?.modules?.length ?? null,
    edge_count: jsNr.application_graph?.edges?.length ?? null,
    entrypoints: jsNr.application_graph?.entrypoints ?? null,
    electron_boundaries: jsNr.application_graph?.electron_boundaries ?? null,
  },
  semantic_graph_summary: {
    families: (jsNr.semantic_graph?.families ?? []).map((f) => ({
      family: f.family,
      status: f.status,
      retained_relations: f.retained_relations,
      omitted_relations: f.omitted_relations,
    })),
    endpoints: jsNr.semantic_graph?.endpoints ?? null,
    storage: jsNr.semantic_graph?.storage ?? null,
  },
  normalized_keys: Object.keys(jsNr),
};
writeFileSync(
  join(EVID_DIR, "rea_js_bridge.json"),
  JSON.stringify(jsSummary, null, 2),
);
console.log("Done! Check evidence/ sizes:");
