/**
 * Frame renderer. Each worker process owns one @napi-rs/canvas surface and
 * rasterises a contiguous frame range to PNG. Composition (HUD, background,
 * subtitles, act cards, progress spine, grain, vignette) happens here so that
 * individual scenes only ever draw their own content.
 */
import { createCanvas, GlobalFonts, type Canvas, type SKRSContext2D } from "@napi-rs/canvas";
import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { background, edgeBloom, progressSpine } from "./bg.js";
import { grain, rect, vignette, frameMarks } from "./draw.js";
import { hud } from "./hud.js";
import { actCard, citation, lowerThird } from "./scenes/overlay.js";
import { ACTS, type RenderCfg, type SceneMeta } from "./timeline.js";
import { C, F, FPS, H, W } from "./theme.js";
import { clamp, inv } from "./util.js";
import { loadEvidence } from "../research/evidence.js";

const HERE = path.dirname(fileURLToPath(import.meta.url));
const FONT_DIR = path.resolve(HERE, "../../node_modules/@expo-google-fonts");

let fontsReady = false;
export function registerFonts(): void {
  if (fontsReady) return;
  const reg = (p: string, name: string) => {
    try {
      GlobalFonts.registerFromPath(p, name);
    } catch {
      /* fall back silently to DejaVu */
    }
  };
  const j = (...parts: string[]) => path.join(FONT_DIR, ...parts);
  reg(j("space-grotesk/700Bold/SpaceGrotesk_700Bold.ttf"), F.displayBold);
  reg(j("space-grotesk/500Medium/SpaceGrotesk_500Medium.ttf"), F.display);
  reg(j("inter/400Regular/Inter_400Regular.ttf"), F.body);
  reg(j("inter/500Medium/Inter_500Medium.ttf"), F.bodyMed);
  reg(j("inter/600SemiBold/Inter_600SemiBold.ttf"), F.bodySemi);
  reg(j("inter/700Bold/Inter_700Bold.ttf"), F.bodyBold);
  reg(j("jetbrains-mono/400Regular/JetBrainsMono_400Regular.ttf"), F.mono);
  reg(j("jetbrains-mono/500Medium/JetBrainsMono_500Medium.ttf"), F.monoMed);
  reg(j("jetbrains-mono/700Bold/JetBrainsMono_700Bold.ttf"), F.monoBold);
  fontsReady = true;
}

export interface FrameJob {
  scene: SceneMeta;
  localT: number;
  sceneDur: number;
  /** Act-intertitle overlay progress, or null. */
  actCardP: number | null;
  act: SceneMeta["act"];
  actIndex: number;
  sceneNum: number;
  sceneCount: number;
  globalT: number;
  total: number;
  /** Narration overlay */
  narr: { en: string; vi: string; p: number } | null;
}

const evidence = loadEvidence();

export function renderFrame(ctx: SKRSContext2D, job: FrameJob, cfg: RenderCfg): void {
  const { scene, localT: t } = job;
  ctx.save();
  ctx.clearRect(0, 0, W, H);
  ctx.fillStyle = C.ink;
  ctx.fillRect(0, 0, W, H);

  const accent = ACTS[job.act].color;
  background(ctx, job.globalT, accent, 1);

  // ---- scene content, isolated in its own save/restore
  ctx.save();
  scene.draw(ctx, t, evidence, cfg);
  ctx.restore();

  if (scene.post) {
    ctx.save();
    scene.post(ctx, t);
    ctx.restore();
  }

  // ---- projection: edge bloom, vignette
  edgeBloom(ctx, accent, 1);
  vignette(ctx, W, H, 0.5);

  // ---- subtitles
  if (job.narr && cfg.narration) {
    lowerThird(ctx, {
      en: job.narr.en,
      vi: job.narr.vi,
      p: job.narr.p,
      accent,
      evidence: scene.evidence.length ? `EVIDENCE ${scene.evidence.join(" · ")}` : undefined,
    });
  }

  // ---- persistent HUD
  hud(ctx, {
    act: job.act,
    sceneIndex: job.sceneNum,
    sceneCount: job.sceneCount,
    sceneNum: job.sceneNum,
    evidenceId: scene.evidence.length ? `film/data/${scene.evidence[0].padStart(2, "0")}_*.json` : "film/data",
    accent,
    t: job.globalT,
    total: job.total,
  });

  progressSpine(ctx, job.globalT, job.total, accent);
  frameMarks(ctx, W, H, C.line2, 0.28);
  grain(ctx, W, H, 0.028, Math.floor(job.globalT * FPS));

  // ---- act intertitle on top of everything
  if (job.actCardP !== null) {
    actCard(ctx, { act: job.act, p: job.actCardP, n: job.actIndex + 1, total: Object.keys(ACTS).length });
  }
  ctx.restore();
}

export function makeSurface(): { canvas: Canvas; ctx: SKRSContext2D } {
  const canvas = createCanvas(W, H);
  return { canvas, ctx: canvas.getContext("2d") as unknown as SKRSContext2D };
}

export function writeFrame(canvas: Canvas, file: string): void {
  const buf = canvas.toBuffer("image/png");
  fs.writeFileSync(file, buf);
}

export interface PlannedScene {
  scene: SceneMeta;
  start: number;
  dur: number;
  actIndex: number;
  sceneNum: number;
}

/** Compute act-card windows: first ~1.9 s of an act's first scene. */
export function actCardProgress(plan: PlannedScene[], scene: SceneMeta, localT: number): number | null {
  const first = plan.find((p) => p.scene.act === scene.act);
  if (!first || first.scene.id !== scene.id) return null;
  const win = 2.0;
  if (localT > win) return null;
  return clamp(localT / win);
}
