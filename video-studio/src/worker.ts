import { parentPort, workerData } from "node:worker_threads";
import { mkdirSync, writeFileSync } from "node:fs";
import { join } from "node:path";
import { createCanvas } from "@napi-rs/canvas";
import { registerStudioFonts, W, H, FPS } from "./theme.js";
import { loadResearchDataset, loadZalopayIcon } from "./data.js";
import { renderFrame } from "./scenes.js";
import type { ChapterTiming } from "./types.js";

interface WorkerPayload {
  startFrame: number;
  endFrame: number;
  framesDir: string;
  chapters: ChapterTiming[];
}

async function run(): Promise<void> {
  registerStudioFonts();
  const payload = workerData as WorkerPayload;
  mkdirSync(payload.framesDir, { recursive: true });
  const dataset = loadResearchDataset();
  const icon = await loadZalopayIcon();
  const canvas = createCanvas(W, H);
  const ctx = canvas.getContext("2d");

  for (let f = payload.startFrame; f < payload.endFrame; f++) {
    renderFrame(ctx, f, FPS, payload.chapters, dataset, icon);
    const outPath = join(payload.framesDir, `frame_${String(f).padStart(6, "0")}.bmp`);
    // Fast raw uncompressed BMP or PNG; PNG is compact and fast with @napi-rs/canvas
    writeFileSync(
      join(payload.framesDir, `frame_${String(f).padStart(6, "0")}.png`),
      canvas.toBuffer("image/png")
    );
    if ((f - payload.startFrame) % 150 === 0) {
      parentPort?.postMessage({ worker: payload.startFrame, frame: f });
    }
    void outPath;
  }
  parentPort?.postMessage({ done: true });
}

run().catch((err) => {
  console.error(err);
  process.exit(1);
});
