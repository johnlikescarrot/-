import { readdirSync, mkdirSync, writeFileSync, statSync } from "node:fs";
import { join, resolve } from "node:path";
import { execFile } from "node:child_process";
import { promisify } from "node:util";

const execFileAsync = promisify(execFile);
const SHIM_PATH = resolve("research/scripts/rea_shim.mjs");

async function runRea(args) {
  try {
    const { stdout, stderr } = await execFileAsync(process.execPath, [SHIM_PATH, ...args], {
      maxBuffer: 32 * 1024 * 1024,
      timeout: 60_000,
    });
    return { ok: true, json: JSON.parse(stdout), stderr: stderr.trim() || null };
  } catch (err) {
    const stdout = err.stdout?.toString?.() ?? "";
    const stderr = err.stderr?.toString?.() ?? "";
    try {
      return { ok: false, json: JSON.parse(stdout), stderr: stderr.trim() || null };
    } catch {
      return {
        ok: false,
        code: err.code ?? 1,
        stdout: stdout.slice(0, 600),
        stderr: stderr.slice(0, 600),
      };
    }
  }
}

async function mapLimit(items, limit, fn) {
  const results = new Array(items.length);
  let idx = 0;
  async function worker() {
    while (idx < items.length) {
      const i = idx++;
      results[i] = await fn(items[i], i);
    }
  }
  await Promise.all(Array.from({ length: Math.min(limit, items.length) }, worker));
  return results;
}

const xapkDir = resolve(process.argv[2] || "/tmp/zalopay_re/xapk");
const baseExtracted = resolve(process.argv[3] || "/tmp/zalopay_re/extracted/base");
const soDir = resolve(process.argv[4] || "/tmp/zalopay_re/extracted/armeabi-v7a/lib/armeabi-v7a");
const outDir = resolve(process.argv[5] || "research/evidence/rea");
mkdirSync(outDir, { recursive: true });

// 1. APKs: signatures + Flutter detection
const apkFiles = readdirSync(xapkDir).filter((f) => f.endsWith(".apk")).sort();
const apkResults = {};
for (const apk of apkFiles) {
  const apkPath = join(xapkDir, apk);
  console.log(`[REA] inspecting APK: ${apk}`);
  const signature = await runRea(["inspect-apk-signature", apkPath, "--json"]);
  const flutter =
    apk === "vn.com.vng.zalopay.apk" || apk === "config.armeabi_v7a.apk"
      ? await runRea(["identify-flutter-build", apkPath, "--json"])
      : null;
  apkResults[apk] = {
    signature: signature.json ?? signature,
    flutter: flutter ? (flutter.json ?? flutter) : null,
  };
}
writeFileSync(join(outDir, "rea_apk_signatures_and_flutter.json"), JSON.stringify(apkResults, null, 2));

// 2. Key encrypted/native/model assets byte entropy via REA
const keyAssets = [
  "assets/bshield.dat",
  "assets/fgcpkjvqe.dat",
  "assets/trueidlib.so",
  "assets/android.1.zip",
  "assets/FastestDet.ncnn.bin",
  "assets/custom_models/object_labeler.tflite",
  "assets/audience_network.dex",
  "assets/Griver/68687209_2.9.7.8.amr",
  "assets/Griver/66666692_1.24.8.amr",
  "assets/Griver/66666693_2.0.7.amr",
];
const assetEntropy = {};
for (const rel of keyAssets) {
  const p = join(baseExtracted, rel);
  try {
    const size = statSync(p).size;
    console.log(`[REA] asset entropy: ${rel}`);
    const ent = await runRea(["inspect-byte-entropy", p, "--block-size", "4096", "--json"]);
    const nr = ent.json?.normalized_result;
    assetEntropy[rel] = {
      bytes: size,
      overall_entropy_bits_per_byte: nr?.overall_entropy_bits_per_byte ?? nr?.file_entropy ?? nr,
      summary: nr,
    };
  } catch (e) {
    assetEntropy[rel] = { error: String(e) };
  }
}
writeFileSync(join(outDir, "rea_asset_entropy.json"), JSON.stringify(assetEntropy, null, 2));

// 3. All 27 native .so libraries via REA ELF / hardening / Rust / Go / exports / initializers / entropy
const soFiles = readdirSync(soDir).filter((f) => f.endsWith(".so")).sort();
const nativeResults = {};
await mapLimit(soFiles, 4, async (so) => {
  const soPath = join(soDir, so);
  const size = statSync(soPath).size;
  console.log(`[REA] native inspection: ${so} (${size}B)`);
  const [hardening, deps, notes, exportsRes, initializers, dwarf, rust, go, entropy] = await Promise.all([
    runRea(["inspect-binary-hardening", soPath, "--json"]),
    runRea(["inspect-elf-dependencies", soPath, "--json"]),
    runRea(["inspect-elf-notes", soPath, "--json"]),
    runRea(["inspect-native-exports", soPath, "--json"]),
    runRea(["inspect-native-initializers", soPath, "--limit", "256", "--json"]),
    runRea(["inspect-dwarf-units", soPath, "--json"]),
    runRea(["inspect-rust-binary", soPath, "--json"]),
    runRea(["inspect-go-binary", soPath, "--json"]),
    runRea(["inspect-byte-entropy", soPath, "--block-size", "4096", "--json"]),
  ]);
  // Trim giant block lists in entropy to keep evidence compact while keeping summary + block stats
  const entNorm = entropy.json?.normalized_result ?? entropy;
  const blocks = Array.isArray(entNorm?.blocks) ? entNorm.blocks : [];
  const compactEntropy = entNorm?.blocks
    ? {
        ...entNorm,
        block_count: blocks.length,
        min_block_entropy: blocks.length ? Math.min(...blocks.map((b) => b.entropy_bits_per_byte ?? b.entropy ?? 8)) : null,
        max_block_entropy: blocks.length ? Math.max(...blocks.map((b) => b.entropy_bits_per_byte ?? b.entropy ?? 0)) : null,
        high_entropy_blocks_gt_7_5: blocks.filter((b) => (b.entropy_bits_per_byte ?? b.entropy ?? 0) > 7.5).length,
        // downsample to max 64 representative windows for plotting!
        blocks_sampled: blocks.filter((_, idx) => idx % Math.max(1, Math.floor(blocks.length / 64)) === 0),
        blocks: undefined,
      }
    : entNorm;

  nativeResults[so] = {
    sizeBytes: size,
    hardening: hardening.json?.normalized_result ?? hardening,
    dependencies: deps.json?.normalized_result ?? deps,
    notes: notes.json?.normalized_result ?? notes,
    exports: exportsRes.json?.normalized_result ?? exportsRes,
    initializers: initializers.json?.normalized_result ?? initializers,
    dwarf: dwarf.json?.normalized_result ?? dwarf,
    rust: rust.json?.normalized_result ?? rust,
    go: go.json?.normalized_result ?? go,
    entropy: compactEntropy,
  };
});

writeFileSync(join(outDir, "rea_native_elf_suite.json"), JSON.stringify(nativeResults, null, 2));
console.log(`[REA] Finished ${apkFiles.length} APKs, ${keyAssets.length} assets, and ${soFiles.length} native libraries.`);
