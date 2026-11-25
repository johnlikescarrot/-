import { execFile, spawn } from "node:child_process";
import { promisify } from "node:util";
import fs from "node:fs/promises";
import path from "node:path";

const execFileAsync = promisify(execFile);
const ROOT = "/home/user/-";
const REA_BIN = path.join(ROOT, "node_modules/.bin/rea");

async function runReaCli(args, timeoutMs = 120000) {
  try {
    const { stdout } = await execFileAsync(REA_BIN, [...args, "--format", "json"], {
      cwd: ROOT,
      maxBuffer: 128 * 1024 * 1024,
      timeout: timeoutMs,
    });
    return { ok: true, data: JSON.parse(stdout) };
  } catch (err) {
    return {
      ok: false,
      error: err.message,
      stderr: err.stderr?.slice(0, 500) ?? "",
    };
  }
}

class ReaMcpClient {
  constructor() {
    this.nextId = 1;
    this.pending = new Map();
    this.buffer = "";
    this.proc = spawn(REA_BIN, ["--mcp"], { cwd: ROOT, stdio: ["pipe", "pipe", "pipe"] });
    this.proc.stdout.on("data", (chunk) => {
      this.buffer += chunk.toString();
      const lines = this.buffer.split("\n");
      this.buffer = lines.pop() ?? "";
      for (const line of lines) {
        if (!line.trim()) continue;
        try {
          const msg = JSON.parse(line);
          if (msg.id != null && this.pending.has(msg.id)) {
            const { resolve, reject } = this.pending.get(msg.id);
            this.pending.delete(msg.id);
            if (msg.error) reject(new Error(JSON.stringify(msg.error)));
            else resolve(msg.result);
          }
        } catch {
          // ignore
        }
      }
    });
    this.proc.stderr.on("data", () => {});
  }

  request(method, params = {}, timeoutMs = 60000) {
    const id = this.nextId++;
    return new Promise((resolve, reject) => {
      const timer = setTimeout(() => {
        if (this.pending.has(id)) {
          this.pending.delete(id);
          reject(new Error(`MCP timeout for ${method}`));
        }
      }, timeoutMs);
      this.pending.set(id, {
        resolve: (v) => {
          clearTimeout(timer);
          resolve(v);
        },
        reject: (e) => {
          clearTimeout(timer);
          reject(e);
        },
      });
      this.proc.stdin.write(JSON.stringify({ jsonrpc: "2.0", id, method, params }) + "\n");
    });
  }

  async init() {
    await this.request("initialize", {
      protocolVersion: "2024-11-05",
      capabilities: {},
      clientInfo: { name: "zalopay-research-pipeline", version: "1.0.0" },
    });
    this.proc.stdin.write(JSON.stringify({ jsonrpc: "2.0", method: "notifications/initialized" }) + "\n");
  }

  async callTool(name, args, timeoutMs = 45000) {
    try {
      const res = await this.request("tools/call", { name, arguments: args }, timeoutMs);
      if (res?.structuredContent) return { ok: !res.isError, data: res.structuredContent };
      const txt = res?.content?.find((c) => c.type === "text")?.text;
      if (txt) {
        try {
          const parsed = JSON.parse(txt);
          if (res.isError) return { ok: false, error: parsed };
          return { ok: true, data: parsed };
        } catch {
          return { ok: !res.isError, data: txt };
        }
      }
      return { ok: !res?.isError, data: res };
    } catch (err) {
      return { ok: false, error: err.message };
    }
  }

  close() {
    this.proc.kill();
  }
}

async function listFiles(dir, extFilter = null) {
  const out = [];
  const entries = await fs.readdir(dir, { withFileTypes: true });
  for (const e of entries) {
    const full = path.join(dir, e.name);
    if (e.isDirectory()) {
      out.push(...(await listFiles(full, extFilter)));
    } else if (!extFilter || extFilter(full)) {
      out.push(full);
    }
  }
  return out.sort();
}

function summarizeArtifactEvidence(res) {
  if (!res.ok) return { ok: false, error: res.error };
  const nr = res.data?.normalized_result ?? {};
  const obs = nr.observations?.[0]?.value ?? {};
  const inv = nr.substeps?.[0]?.evidence?.normalized_result ?? {};
  return {
    ok: true,
    evidence_id: res.data.evidence_id,
    subject: res.data.subject,
    manifest_id: obs.manifest_id ?? inv.manifest?.manifest_id ?? null,
    graph_sha256: obs.graph_sha256 ?? inv.manifest?.graph_sha256 ?? null,
    node_count: obs.node_count ?? inv.manifest?.node_count ?? null,
    occurrence_count: obs.occurrence_count ?? inv.manifest?.occurrence_count ?? null,
    edge_count: obs.edge_count ?? inv.manifest?.edge_count ?? null,
    integrity_contradictions: inv.integrity_contradictions ?? [],
    coverage: nr.coverage ?? null,
    limitations: res.data.limitations ?? [],
  };
}

function summarizeJsEvidence(res) {
  if (!res.ok) return { ok: false, error: res.error };
  const nr = res.data?.normalized_result ?? {};
  if (nr.graph) {
    const nodes = nr.graph.nodes ?? [];
    const edges = nr.graph.edges ?? [];
    const kindCounts = {};
    for (const n of nodes) kindCounts[n.kind] = (kindCounts[n.kind] || 0) + 1;
    const edgeKinds = {};
    for (const e of edges) {
      const k = e.kind || e.relation || "unknown";
      edgeKinds[k] = (edgeKinds[k] || 0) + 1;
    }
    const semFamilies = (nr.semantic_graph?.families ?? []).map((f) => ({
      family: f.family,
      status: f.status,
      retained_relations: f.retained_relations,
      omitted_relations: f.omitted_relations,
    }));
    const assets = nodes
      .filter((n) => n.kind === "javascript-asset" || n.kind === "module" || n.kind === "bundle")
      .slice(0, 30)
      .map((n) => ({
        kind: n.kind,
        label: n.observations?.[0]?.label ?? null,
        bytes: n.observations?.[0]?.properties?.bytes ?? null,
      }));
    return {
      ok: true,
      mode: "complete",
      evidence_id: res.data.evidence_id,
      input_path: nr.input_path,
      root_artifact_sha256: nr.root_artifact_sha256,
      inventory_graph_sha256: nr.inventory_graph_sha256,
      total_nodes: nodes.length,
      total_edges: edges.length,
      node_kind_counts: kindCounts,
      edge_kind_counts: edgeKinds,
      semantic_families: semFamilies,
      sample_assets: assets,
      limitations: res.data.limitations ?? [],
    };
  }
  return {
    ok: true,
    mode: "summary",
    evidence_id: res.data.evidence_id,
    normalized_result: nr,
    limitations: res.data.limitations ?? [],
  };
}

async function runPool(items, concurrency, fn) {
  const results = [];
  let idx = 0;
  async function worker() {
    while (idx < items.length) {
      const cur = idx++;
      results[cur] = await fn(items[cur], cur);
    }
  }
  await Promise.all(Array.from({ length: concurrency }, worker));
  return results;
}

async function main() {
  const report = {
    rea_tool: "morluto/rea (rea-agents@6.10.1 / rea@6.7.0 CLI + MCP)",
    generated_at: new Date().toISOString(),
    containers: {},
    flutter_checks: {},
    javascript_applications: {},
    native_elf_rea_audits: {},
  };

  const zipPath = path.join(ROOT, "Zalopay-Thanh+toán+&+Tài+chính_11.17.0_APKPure.zip");
  const xapkPath = (await listFiles(path.join(ROOT, "tmp_work/zip_contents"), (p) => p.endsWith(".xapk")))[0];
  const apkFiles = await listFiles(path.join(ROOT, "tmp_work/xapk_contents"), (p) => p.endsWith(".apk"));
  const nestedContainers = [
    path.join(ROOT, "tmp_work/apks/vn.com.vng.zalopay/assets/android.1.zip"),
    ...(await listFiles(path.join(ROOT, "tmp_work/apks/vn.com.vng.zalopay/assets/Griver"), (p) => p.endsWith(".amr"))),
    ...(await listFiles(path.join(ROOT, "tmp_work/nested"), (p) => p.endsWith(".tar"))),
  ];

  console.log("1. Running REA inspect-artifact across all containers (ZIP, XAPK, 5 APKs, nested ZIP/AMR/TAR)...");
  const containerTasks = [
    { label: "outer_zip", target: zipPath },
    { label: "xapk", target: xapkPath },
    ...apkFiles.map((a) => ({ label: path.basename(a), target: a })),
    ...nestedContainers.map((n) => ({ label: path.relative(path.join(ROOT, "tmp_work"), n), target: n })),
  ];
  const containerRes = await runPool(containerTasks, 4, async (t) => {
    console.log(`   - inspect-artifact: ${t.label}`);
    return { label: t.label, summary: summarizeArtifactEvidence(await runReaCli(["inspect-artifact", t.target])) };
  });
  for (const r of containerRes) {
    report.containers[r.label] = r.summary;
  }

  let client = new ReaMcpClient();
  await client.init();
  try {
    console.log("2. Running REA identify_flutter_build across all 5 split APKs...");
    for (const apk of apkFiles) {
      const name = path.basename(apk);
      const fl = await client.callTool("identify_flutter_build", { path: apk });
      report.flutter_checks[name] = fl.ok
        ? {
            evidence_id: fl.data.evidence_id,
            flutter_detected: fl.data.normalized_result?.flutter_detected,
            abis: fl.data.normalized_result?.abis,
          }
        : { ok: false, error: fl.error };
    }

    console.log("3. Running REA analyze_javascript_application on JS/Griver/ReactNative targets...");
    const jsDirs = [
      { name: "assets/GriverBridge", dir: path.join(ROOT, "tmp_work/apks/vn.com.vng.zalopay/assets/GriverBridge"), detail: "complete" },
      { name: "assets/android.1.zip (React Native Bundle)", dir: path.join(ROOT, "tmp_work/nested/android_1_zip"), detail: "summary" },
      { name: "Griver_66666692_tar", dir: path.join(ROOT, "tmp_work/nested/griver_66666692_1.24.8/tar_extracted"), detail: "summary" },
      { name: "Griver_66666693_tar", dir: path.join(ROOT, "tmp_work/nested/griver_66666693_2.0.7/tar_extracted"), detail: "complete" },
      { name: "Griver_68687209_tar", dir: path.join(ROOT, "tmp_work/nested/griver_68687209_2.9.7.8/tar_extracted"), detail: "summary" },
    ];
    for (const item of jsDirs) {
      console.log(`   - ${item.name} (detail=${item.detail})`);
      const jsRes = await client.callTool(
        "analyze_javascript_application",
        { input_path: item.dir, max_heap_mb: 1536, analysis_timeout_ms: 25000, detail: item.detail },
        35000,
      );
      report.javascript_applications[item.name] = summarizeJsEvidence(jsRes);
      if (!jsRes.ok) {
        // Restart MCP client cleanly if a heavy AST worker timed out
        client.close();
        client = new ReaMcpClient();
        await client.init();
      }
    }

    console.log("4. Auditing all 27 native ELF .so binaries with REA MCP (Rust, Go, objdump)...");
    const soFiles = await listFiles(path.join(ROOT, "tmp_work/apks/config.armeabi_v7a/lib/armeabi-v7a"), (p) => p.endsWith(".so"));
    for (const so of soFiles) {
      const name = path.basename(so);
      console.log(`   - ${name}`);
      const rustRes = await client.callTool("inspect_rust_binary", { path: so });
      const goRes = await client.callTool("inspect_go_binary", { path: so });
      const hdrRes = await client.callTool("inspect_with_objdump", { path: so, operation: "file_headers" });
      const secRes = await client.callTool("inspect_with_objdump", { path: so, operation: "section_headers" });
      report.native_elf_rea_audits[name] = {
        sha256: rustRes.data?.subject?.digest?.sha256 ?? goRes.data?.subject?.digest?.sha256 ?? null,
        bytes: rustRes.data?.normalized_result?.artifact?.bytes ?? null,
        rust_auditable_metadata: rustRes.data?.normalized_result?.auditable_metadata ?? null,
        rustc_commit: rustRes.data?.normalized_result?.rustc_commit ?? null,
        go_build_info: goRes.data?.normalized_result?.build_info ?? null,
        objdump_file_headers_ok: hdrRes.ok,
        objdump_file_headers_evidence_id: hdrRes.data?.evidence_id ?? null,
        objdump_section_headers_evidence_id: secRes.data?.evidence_id ?? null,
        objdump_file_headers_excerpt: hdrRes.data?.normalized_result?.raw_output?.slice(0, 500) ?? hdrRes.data?.raw_result?.stdout?.slice(0, 500) ?? null,
      };
    }
  } finally {
    client.close();
  }

  const outPath = path.join(ROOT, "data/rea_summary.json");
  await fs.writeFile(outPath, JSON.stringify(report, null, 2));
  console.log(`Saved REA comprehensive evidence summary to ${outPath}`);
}

main().catch((err) => {
  console.error(err);
  process.exit(1);
});
