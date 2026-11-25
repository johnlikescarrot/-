/**
 * Entry point — three phases:
 *   plan     compute the timeline (needs audio_timing.json if narration is on)
 *   frames   rasterise a frame range:  node main.js frames <from> <to> <outDir>
 *   audio    synthesise the score:     node main.js audio  <out.wav>
 */
import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
import { loadEvidence } from "../research/evidence.js";
import { actCardProgress, makeSurface, registerFonts, renderFrame, writeFrame, type FrameJob, type PlannedScene } from "./render.js";
import { linesFor, type RenderCfg, type SceneMeta } from "./timeline.js";
import { loadTiming, narrationAt, planTimeline, sceneVoiceDurations, writeManifest } from "./plan.js";
import { ACT1_SCENES } from "./scenes/opening.js";
import { ACT2_SCENES } from "./scenes/artifact.js";
import { ACT3_SCENES } from "./scenes/identity.js";
import { ACT4_SCENES } from "./scenes/code.js";
import { ACT5_SCENES } from "./scenes/native.js";
import { ACT6_SCENES } from "./scenes/trust.js";
import { ACT7_SCENES } from "./scenes/verdict.js";
import { scoreToWav } from "./compose.js";

const HERE = path.dirname(fileURLToPath(import.meta.url));
const ROOT = path.resolve(HERE, "../..");

export const SCENES: SceneMeta[] = [
  ...ACT1_SCENES,
  ...ACT2_SCENES,
  ...ACT3_SCENES,
  ...ACT4_SCENES,
  ...ACT5_SCENES,
  ...ACT6_SCENES,
  ...ACT7_SCENES,
];

const cfg: RenderCfg = {
  width: 1920,
  height: 1080,
  fps: 30,
  dpi: 1,
  narration: process.env.NO_NARRATION !== "1",
  timing: loadTiming(),
};

function buildPlan() {
  const { plan, total } = planTimeline(SCENES, cfg.timing);
  plan.forEach((p, i) => (p.actIndex = i));
  return { plan, total };
}

function sceneAt(plan: PlannedScene[], t: number): { p: PlannedScene; local: number; idx: number } | null {
  for (let i = 0; i < plan.length; i++) {
    const p = plan[i]!;
    if (t >= p.start && t < p.start + p.dur) return { p, local: t - p.start, idx: i };
  }
  const last = plan[plan.length - 1]!;
  return { p: last, local: last.dur - 0.001, idx: plan.length - 1 };
}

async function main() {
  const [mode, a1, a2, a3] = process.argv.slice(2);
  registerFonts();
  const evidence = loadEvidence();
  const { plan, total } = buildPlan();

  if (mode === "plan" || mode === "info") {
    writeManifest(plan, total);
    const voice = sceneVoiceDurations(SCENES, cfg.timing);
    console.log(`scenes=${plan.length} total=${total.toFixed(1)}s narration=${Object.keys(voice).length}`);
    for (const p of plan) {
      console.log(
        `${p.start.toFixed(2).padStart(7)}s  +${p.dur.toFixed(2).padStart(5)}s  ${p.scene.act.padEnd(9)} ${p.scene.id.padEnd(14)} ${linesFor(p.scene.id).length ? voice[p.scene.id]?.toFixed(1) ?? "—" : "  "}s  ${p.scene.title}`,
      );
    }
    return;
  }

  if (mode === "frames") {
    const from = Number(a1 ?? 0);
    const to = Number(a2 ?? 60);
    const outDir = a3 ?? path.join(ROOT, "frames");
    fs.mkdirSync(outDir, { recursive: true });
    const { canvas, ctx } = makeSurface();
    for (let f = from; f < to; f++) {
      const t = f / cfg.fps;
      const hit = sceneAt(plan, t);
      if (!hit) continue;
      const voice = sceneVoiceDurations(SCENES, cfg.timing)[hit.p.scene.id] ?? 0;
      const narrP = narrationAt(hit.p.scene, hit.local, voice);
      const lines = linesFor(hit.p.scene.id);
      const narr = narrP && lines[0] ? { en: lines[0].en, vi: lines[0].vi, p: narrP.p } : null;
      const job: FrameJob = {
        scene: hit.p.scene,
        localT: hit.local,
        sceneDur: hit.p.dur,
        actCardP: actCardProgress(plan, hit.p.scene, hit.local),
        act: hit.p.scene.act,
        actIndex: hit.idx,
        sceneNum: hit.p.sceneNum,
        sceneCount: plan.length,
        globalT: t,
        total,
        narr,
      };
      renderFrame(ctx, job, cfg);
      writeFrame(canvas, path.join(outDir, `f${String(f).padStart(6, "0")}.png`));
      if (f % 30 === 0) process.stderr.write(`\rframe ${f}/${to}  ${t.toFixed(1)}s   `);
    }
    process.stderr.write(`\nframes ${from}..${to} done -> ${outDir}\n`);
    return;
  }

  if (mode === "audio") {
    const out = a1 ?? path.join(ROOT, "out/score.wav");
    fs.mkdirSync(path.dirname(out), { recursive: true });
    const buf = scoreToWav(plan, total);
    fs.writeFileSync(out, buf);
    console.log(`score -> ${out}  (${(buf.length / 1e6).toFixed(1)} MB, ${total.toFixed(1)}s)`);
    return;
  }

  if (mode === "still") {
    // Render a single frame for design review: node main.js still <sceneId> <localT> out.png
    const id = a1 ?? SCENES[0]!.id;
    const local = Number(a2 ?? 1);
    const out = a3 ?? path.join(ROOT, "out/still.png");
    const idx = plan.findIndex((p) => p.scene.id === id);
    if (idx < 0) throw new Error(`unknown scene ${id}`);
    const p = plan[idx]!;
    fs.mkdirSync(path.dirname(out), { recursive: true });
    const { canvas, ctx } = makeSurface();
    const voice = sceneVoiceDurations(SCENES, cfg.timing)[id] ?? 0;
    const narrP = narrationAt(p.scene, local, voice);
    const lines = linesFor(id);
    renderFrame(
      ctx,
      {
        scene: p.scene,
        localT: local,
        sceneDur: p.dur,
        actCardP: null,
        act: p.scene.act,
        actIndex: idx,
        sceneNum: p.sceneNum,
        sceneCount: plan.length,
        globalT: p.start + local,
        total,
        narr: narrP && lines[0] ? { en: lines[0].en, vi: lines[0].vi, p: narrP.p } : null,
      },
      cfg,
    );
    writeFrame(canvas, out);
    console.log(`still -> ${out}`);
    return;
  }

  console.error("usage: main.js plan|frames <from> <to> <dir>|audio <wav>|still <scene> <t> <png>");
  process.exit(1);
}

main().catch((e) => {
  console.error(e);
  process.exit(1);
});
