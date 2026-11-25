import fs from "node:fs";
import path from "node:path";
import os from "node:os";
import { Worker, isMainThread, parentPort, workerData } from "node:worker_threads";
import { Resvg } from "@resvg/resvg-js";
import { FONT_FILES } from "./fonts.js";
import { renderSceneSvg } from "./scenes.js";
import { SCENE_CATALOG } from "./scriptCatalog.js";
import type { MasterMetrics, SceneTiming } from "./types.js";

const ROOT = "/home/user/-";
const FPS = 30;

interface AudioManifestItem {
  id: string;
  durationSec: number;
}

function loadMetrics(): MasterMetrics {
  return JSON.parse(fs.readFileSync(path.join(ROOT, "data/master_metrics.json"), "utf8"));
}

function loadTimings(): SceneTiming[] {
  const manifestPath = path.join(ROOT, "video/audio_manifest.json");
  if (fs.existsSync(manifestPath)) {
    const manifest: AudioManifestItem[] = JSON.parse(fs.readFileSync(manifestPath, "utf8"));
    const map = new Map(manifest.map((m) => [m.id, m.durationSec]));
    return SCENE_CATALOG.map((s) => ({
      ...s,
      durationSec: map.get(s.id) ?? s.durationSec,
    }));
  }
  return SCENE_CATALOG;
}

function renderOneFrame(svg: string, outPath: string): void {
  const resvg = new Resvg(svg, {
    fitTo: { mode: "width", value: 1920 },
    font: {
      fontFiles: FONT_FILES,
      loadSystemFonts: false,
      defaultFontFamily: "Inter",
    },
  });
  const pngData = resvg.render();
  fs.writeFileSync(outPath, pngData.asPng());
}

if (!isMainThread && workerData) {
  const { jobs, metrics, scenes, totalDuration } = workerData as {
    jobs: Array<{ frameIdx: number; sceneIdx: number; localProgress: number; globalProgress: number; outPath: string }>;
    metrics: MasterMetrics;
    scenes: SceneTiming[];
    totalDuration: number;
  };
  void totalDuration;
  for (const job of jobs) {
    const scene = scenes[job.sceneIdx];
    const svg = renderSceneSvg(scene, metrics, job.localProgress, job.globalProgress, scenes.length);
    renderOneFrame(svg, job.outPath);
  }
  parentPort?.postMessage({ done: jobs.length });
  process.exit(0);
}

async function main() {
  const args = process.argv.slice(2);
  const previewMode = args.includes("--preview");
  const metrics = loadMetrics();
  const scenes = loadTimings();

  if (previewMode) {
    const previewDir = path.join(ROOT, "tmp_work/preview_frames");
    fs.mkdirSync(previewDir, { recursive: true });
    console.log(`Rendering ${scenes.length} high-res preview keyframes to ${previewDir}...`);
    const t0 = Date.now();
    for (let i = 0; i < scenes.length; i++) {
      const s = scenes[i];
      const svg = renderSceneSvg(s, metrics, 0.88, (i + 0.88) / scenes.length, scenes.length);
      const out = path.join(previewDir, `${s.id}.png`);
      renderOneFrame(svg, out);
      console.log(`  rendered ${s.id}.png`);
    }
    console.log(`Preview complete in ${((Date.now() - t0) / 1000).toFixed(2)}s`);
    return;
  }

  const framesDir = path.join(ROOT, "video/frames");
  fs.rmSync(framesDir, { recursive: true, force: true });
  fs.mkdirSync(framesDir, { recursive: true });

  const totalDuration = scenes.reduce((acc, s) => acc + s.durationSec, 0);
  const jobs: Array<{ frameIdx: number; sceneIdx: number; localProgress: number; globalProgress: number; outPath: string }> = [];
  let frameCounter = 0;
  let elapsed = 0;

  for (let sIdx = 0; sIdx < scenes.length; sIdx++) {
    const scene = scenes[sIdx];
    const numFrames = Math.max(1, Math.round(scene.durationSec * FPS));
    for (let f = 0; f < numFrames; f++) {
      const localProgress = numFrames === 1 ? 1 : f / (numFrames - 1);
      const globalTime = elapsed + (f / FPS);
      const globalProgress = totalDuration > 0 ? globalTime / totalDuration : 0;
      const outPath = path.join(framesDir, `frame_${String(frameCounter).padStart(6, "0")}.png`);
      jobs.push({ frameIdx: frameCounter, sceneIdx: sIdx, localProgress, globalProgress, outPath });
      frameCounter++;
    }
    elapsed += scene.durationSec;
  }

  const numWorkers = Math.max(1, Math.min(os.cpus().length, 4));
  console.log(`Rendering ${jobs.length} frames (${totalDuration.toFixed(2)}s @ ${FPS}fps) using ${numWorkers} TypeScript worker threads...`);
  const chunkSize = Math.ceil(jobs.length / numWorkers);
  const t0 = Date.now();
  let completed = 0;

  await Promise.all(
    Array.from({ length: numWorkers }, (_, wIdx) => {
      const slice = jobs.slice(wIdx * chunkSize, (wIdx + 1) * chunkSize);
      if (slice.length === 0) return Promise.resolve();
      return new Promise<void>((resolve, reject) => {
        const w = new Worker(new URL(import.meta.url), {
          workerData: { jobs: slice, metrics, scenes, totalDuration },
        });
        w.on("message", (m: { done: number }) => {
          completed += m.done;
          console.log(`  worker ${wIdx} finished ${m.done} frames (total ${completed}/${jobs.length})`);
        });
        w.on("error", reject);
        w.on("exit", (code) => {
          if (code !== 0) reject(new Error(`Worker exited with ${code}`));
          else resolve();
        });
      });
    })
  );

  console.log(`Rendered ${jobs.length} 1080p PNG frames in ${((Date.now() - t0) / 1000).toFixed(2)}s`);
}

main().catch((err) => {
  console.error(err);
  process.exit(1);
});
