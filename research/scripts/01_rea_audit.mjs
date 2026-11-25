import { execFile } from "node:child_process";
import { promisify } from "node:util";
import fs from "node:fs/promises";
import path from "node:path";

const execFileAsync = promisify(execFile);
const REA = "/tmp/rea-tools/node_modules/.bin/rea";
const XAPK_DIR = "/tmp/zalopay_re/xapk";
const APKS_DIR = "/tmp/zalopay_re/apks";
const SO_DIR = path.join(APKS_DIR, "config.armeabi_v7a/lib/armeabi-v7a");
const OUT_DIR = path.resolve("research/data");
const RAW_DIR = "/tmp/zalopay_re/rea_raw";

async function runRea(args) {
  try {
    const { stdout } = await execFileAsync(REA, [...args, "--json"], {
      maxBuffer: 64 * 1024 * 1024,
      timeout: 60000,
    });
    return { ok: true, data: JSON.parse(stdout) };
  } catch (err) {
    return {
      ok: false,
      error: err.message,
      stderr: err.stderr ? String(err.stderr).slice(0, 800) : "",
    };
  }
}

async function mapLimit(items, limit, fn) {
  const results = new Array(items.length);
  let idx = 0;
  const workers = Array.from({ length: limit }, async () => {
    while (idx < items.length) {
      const i = idx++;
      results[i] = await fn(items[i], i);
    }
  });
  await Promise.all(workers);
  return results;
}

async function main() {
  await fs.mkdir(OUT_DIR, { recursive: true });
  await fs.mkdir(RAW_DIR, { recursive: true });

  const xapkEntries = await fs.readdir(XAPK_DIR);
  const apkFiles = xapkEntries.filter((f) => f.endsWith(".apk")).sort();

  console.log(`[REA] Auditing ${apkFiles.length} APK files...`);
  const apkSignatures = {};
  const flutterChecks = {};
  const apkArtifacts = {};

  for (const apk of apkFiles) {
    const full = path.join(XAPK_DIR, apk);
    const sigRes = await runRea(["inspect-apk-signature", full]);
    apkSignatures[apk] = sigRes.ok ? sigRes.data.normalized_result : sigRes;

    const fltRes = await runRea(["identify-flutter-build", full]);
    flutterChecks[apk] = fltRes.ok ? fltRes.data.normalized_result : fltRes;

    const artRes = await runRea(["inspect-artifact", full]);
    apkArtifacts[apk] = artRes.ok ? artRes.data.normalized_result : artRes;
  }

  const soEntries = (await fs.readdir(SO_DIR)).filter((f) => f.endsWith(".so")).sort();
  console.log(`[REA] Auditing ${soEntries.length} ELF shared libraries...`);

  const elfAudit = await mapLimit(soEntries, 4, async (soName) => {
    const rawPath = path.join(RAW_DIR, `${soName}.rea.json`);
    let parsedRaw = null;
    try {
      parsedRaw = JSON.parse(await fs.readFile(rawPath, "utf-8"));
    } catch {
      // run fresh
    }

    let hardening, deps, notes, exportsRes, inits, entropy, rustRes, goRes, dwarfRes;
    if (parsedRaw) {
      ({ hardening, deps, notes, exportsRes, inits, entropy, rustRes, goRes, dwarfRes } = parsedRaw);
    } else {
      const full = path.join(SO_DIR, soName);
      [hardening, deps, notes, exportsRes, inits, entropy, rustRes, goRes, dwarfRes] =
        await Promise.all([
          runRea(["inspect-binary-hardening", full]),
          runRea(["inspect-elf-dependencies", full]),
          runRea(["inspect-elf-notes", full]),
          runRea(["inspect-native-exports", full]),
          runRea(["inspect-native-initializers", full, "--limit", "512"]),
          runRea(["inspect-byte-entropy", full, "--block-size", "65536"]),
          runRea(["inspect-rust-binary", full]),
          runRea(["inspect-go-binary", full]),
          runRea(["inspect-dwarf-units", full, "--limit", "64"]),
        ]);

      await fs.writeFile(
        rawPath,
        JSON.stringify(
          { hardening, deps, notes, exportsRes, inits, entropy, rustRes, goRes, dwarfRes },
          null,
          2
        )
      );
    }

    const hRows = hardening.ok
      ? Object.fromEntries(
          (hardening.data.normalized_result.images?.[0]?.rows || []).map((r) => [
            r.property,
            { state: r.state, basis: r.basis },
          ])
        )
      : {};

    const nEntries = notes.ok ? notes.data.normalized_result.entries || [] : [];
    const androidNote = nEntries.find((e) => e.android)?.android || null;
    const buildId = nEntries.find((e) => e.gnu?.build_id_hex)?.gnu?.build_id_hex || null;

    const depNorm = deps.ok ? deps.data.normalized_result : null;
    const expNorm = exportsRes.ok ? exportsRes.data.normalized_result : null;
    const initNorm = inits.ok ? inits.data.normalized_result : null;
    const entNorm = entropy.ok ? entropy.data.normalized_result : null;

    const allExports = expNorm?.exports || [];
    const jniExports = allExports
      .map((e) => e.name)
      .filter((n) => n && (n.startsWith("Java_") || n === "JNI_OnLoad" || n === "JNI_OnUnload"));

    return {
      name: soName,
      bytes: hardening.ok ? hardening.data.normalized_result.artifact.bytes : null,
      sha256: hardening.ok ? hardening.data.normalized_result.artifact.sha256 : null,
      hardening: hRows,
      android_ndk: androidNote,
      gnu_build_id: buildId,
      dt_needed: depNorm?.needed_libraries || [],
      soname: depNorm?.soname || null,
      import_count: depNorm?.imported_symbols?.length ?? 0,
      export_count: allExports.length,
      jni_export_count: jniExports.length,
      jni_exports_sample: jniExports.slice(0, 60),
      export_sample: allExports.slice(0, 30).map((e) => e.name),
      initializer_count: initNorm?.entries?.length ?? initNorm?.total_count ?? 0,
      file_entropy_bits_per_byte: entNorm?.file_entropy ?? entNorm?.overall_entropy ?? null,
      entropy_summary: entNorm ? {
        overall: entNorm.overall_entropy ?? entNorm.file_entropy ?? null,
        min_block: entNorm.min_block_entropy ?? null,
        max_block: entNorm.max_block_entropy ?? null,
        block_count: entNorm.block_count ?? entNorm.blocks?.length ?? null,
      } : null,
      rust_detected: rustRes.ok
        ? (rustRes.data.normalized_result?.packages?.length || 0) > 0
        : false,
      rust_packages: rustRes.ok ? rustRes.data.normalized_result?.packages || [] : [],
      go_detected: goRes.ok ? Boolean(goRes.data.normalized_result?.go_version) : false,
      go_version: goRes.ok ? goRes.data.normalized_result?.go_version || null : null,
      dwarf_units_count: dwarfRes.ok ? dwarfRes.data.normalized_result?.units?.length || 0 : 0,
    };
  });

  // Also run REA JS analysis on GriverBridge and root assets JS files, plus entropy on opaque assets
  console.log("[REA] Auditing JS bridges & opaque binary assets...");
  const jsBridgeRes = await runRea([
    "analyze-javascript-application",
    path.join(APKS_DIR, "vn.com.vng.zalopay/assets/GriverBridge"),
  ]);

  const opaqueAssetPaths = [
    "assets/fgcpkjvqe.dat",
    "assets/bshield.dat",
    "assets/trueidlib.so",
    "assets/android.1.zip",
    "assets/audience_network.dex",
    "assets/FastestDet.ncnn.bin",
    "assets/custom_models/object_labeler.tflite",
    "assets/Griver/66666692_1.24.8.amr",
    "assets/Griver/66666693_2.0.7.amr",
    "assets/Griver/68687209_2.9.7.8.amr",
    "assets/dabc8a718ce8084d304bab46923b374602a37c02804a1938f5200c73dcc9b2be/9f979174-fb94-f7f5-21c5-afddb32d1e32",
  ];

  const opaqueAssetEntropy = {};
  for (const rel of opaqueAssetPaths) {
    const full = path.join(APKS_DIR, "vn.com.vng.zalopay", rel);
    const eRes = await runRea(["inspect-byte-entropy", full, "--block-size", "65536"]);
    opaqueAssetEntropy[rel] = eRes.ok ? eRes.data.normalized_result : eRes;
  }

  const apkArtifactsSummary = {};
  for (const [apk, art] of Object.entries(apkArtifacts)) {
    if (art && art.inspection_id) {
      apkArtifactsSummary[apk] = {
        inspection_id: art.inspection_id,
        subject: art.subject,
        coverage: art.coverage,
        substeps: art.substeps,
        observation_count: art.observations?.length ?? 0,
        limitations: art.limitations,
      };
    } else {
      apkArtifactsSummary[apk] = art;
    }
  }

  const griverJsSummary = jsBridgeRes.ok
    ? {
        input_path: jsBridgeRes.data.normalized_result.input_path,
        format: jsBridgeRes.data.normalized_result.format,
        root_artifact_sha256: jsBridgeRes.data.normalized_result.root_artifact_sha256,
        summary: jsBridgeRes.data.normalized_result.summary,
        statistics: jsBridgeRes.data.normalized_result.statistics,
        route_inventory_stats: jsBridgeRes.data.normalized_result.route_inventory?.statistics,
        graph_node_count: jsBridgeRes.data.normalized_result.graph?.nodes?.length ?? 0,
        graph_edge_count: jsBridgeRes.data.normalized_result.graph?.edges?.length ?? 0,
        semantic_node_count: jsBridgeRes.data.normalized_result.semantic_graph?.nodes?.length ?? 0,
        semantic_relation_count: jsBridgeRes.data.normalized_result.semantic_graph?.relations?.length ?? 0,
        limitations: jsBridgeRes.data.normalized_result.limitations,
      }
    : jsBridgeRes;

  await fs.writeFile(
    path.join(RAW_DIR, "rea_full.json"),
    JSON.stringify({ apkArtifacts, jsBridgeRes }, null, 2)
  );

  const summary = {
    rea_version: "6.10.1",
    generated_at: new Date().toISOString(),
    apk_signatures: apkSignatures,
    flutter_checks: flutterChecks,
    apk_artifacts: apkArtifactsSummary,
    elf_libraries: elfAudit,
    griver_js_bridge_analysis: griverJsSummary,
    opaque_asset_entropy: opaqueAssetEntropy,
  };

  await fs.writeFile(
    path.join(OUT_DIR, "rea_evidence_summary.json"),
    JSON.stringify(summary, null, 2)
  );
  console.log("[REA] Wrote research/data/rea_evidence_summary.json successfully!");
}

main().catch((e) => {
  console.error(e);
  process.exit(1);
});
