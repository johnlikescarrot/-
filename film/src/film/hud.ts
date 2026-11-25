/**
 * Persistent instrument HUD: act slug, scene counter, timecode, evidence ID and
 * an "EVIDENCE VERIFIED" lamp. Placed in the outer margins so scene content
 * never collides with it.
 */
import type { Ctx } from "./draw.js";
import { line, text } from "./draw.js";
import { C, F, G, H } from "./theme.js";
import { ACTS, type ActId } from "./timeline.js";
import { pad2, rgba } from "./util.js";

export interface HudState {
  act: ActId;
  sceneIndex: number;
  sceneCount: number;
  sceneNum: number;
  evidenceId: string;
  accent: string;
  t: number;
  total: number;
}

export function hud(ctx: Ctx, s: HudState): void {
  const y = 62;
  // left: wordmark
  text(ctx, "ZALOPAY 11.17.0", G.margin, y, {
    font: F.monoBold,
    size: 15,
    color: C.white,
    letter: 2.6,
    alpha: 0.9,
  });
  text(ctx, "FORENSIC STATIC ANALYSIS", G.margin, y + 22, {
    font: F.mono,
    size: 12,
    color: C.dim2,
    letter: 2.2,
    alpha: 0.9,
  });

  // right: act slug + scene counter
  const rx = 1920 - G.margin;
  text(ctx, `ACT ${ACTS[s.act].roman} — ${ACTS[s.act].label}`, rx, y, {
    font: F.monoMed,
    size: 13,
    color: s.accent,
    letter: 2.4,
    align: "right",
    alpha: 0.95,
  });
  text(ctx, `SCENE ${pad2(s.sceneNum)} / ${pad2(s.sceneCount)}`, rx, y + 22, {
    font: F.mono,
    size: 12,
    color: C.dim2,
    letter: 2.2,
    align: "right",
  });

  // rule under HUD
  line(ctx, G.margin, 104, 1920 - G.margin, 104, C.line, 1, 0.9);

  // bottom-left: evidence provenance
  text(ctx, "EVIDENCE", G.margin, H - 52, {
    font: F.mono,
    size: 11,
    color: C.dim2,
    letter: 2.4,
  });
  text(ctx, s.evidenceId, G.margin, H - 32, {
    font: F.monoMed,
    size: 12,
    color: rgba(s.accent, 0.85),
    letter: 1.2,
  });

  // bottom-right: timecode
  const mm = Math.floor(s.t / 60);
  const ss = Math.floor(s.t % 60);
  const ff = Math.floor((s.t % 1) * 30);
  text(ctx, `${pad2(mm)}:${pad2(ss)}:${pad2(ff)}`, 1920 - G.margin, H - 32, {
    font: F.monoMed,
    size: 12,
    color: C.dim,
    letter: 1.6,
    align: "right",
  });

  // verified lamp
  const lampX = 1920 - G.margin - 132;
  const pulse = 0.55 + 0.45 * Math.sin(s.t * 2.4);
  ctx.save();
  ctx.globalAlpha = 0.9;
  ctx.beginPath();
  ctx.arc(lampX, H - 49, 4, 0, Math.PI * 2);
  ctx.fillStyle = rgba(s.accent, 0.35 + 0.5 * pulse);
  ctx.fill();
  ctx.restore();
  text(ctx, "EVIDENCE VERIFIED", lampX - 12, H - 45, {
    font: F.mono,
    size: 10,
    color: C.dim2,
    letter: 1.8,
    align: "right",
  });
}
