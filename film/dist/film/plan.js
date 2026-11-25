/**
 * Timeline planning: converts the scene list + measured narration timing into
 * an absolute clock, and hands every consumer (renderer, audio, manifest) the
 * same numbers.
 */
import fs from "node:fs";
import path from "node:path";
import { fileURLToPath } from "node:url";
const HERE = path.dirname(fileURLToPath(import.meta.url));
const DATA = path.resolve(HERE, "../../data");
/** Time a scene needs for its narration line, plus breathing room. */
export const NARR_GAP_BEFORE = 0.55;
export const NARR_GAP_AFTER = 1.05;
export function loadTiming() {
    const p = path.join(DATA, "audio_timing.json");
    if (!fs.existsSync(p))
        return null;
    try {
        return JSON.parse(fs.readFileSync(p, "utf8"));
    }
    catch {
        return null;
    }
}
/** Per-scene voice duration (seconds). Falls back to proportional char split. */
export function sceneVoiceDurations(scenes, timing) {
    const out = {};
    for (const s of scenes) {
        const measured = timing?.scenes?.[s.id];
        if (typeof measured === "number" && measured > 0)
            out[s.id] = measured;
    }
    return out;
}
export function planTimeline(scenes, timing) {
    const voice = sceneVoiceDurations(scenes, timing);
    let cursor = 0;
    const plan = [];
    let sceneNum = 0;
    for (const s of scenes) {
        sceneNum++;
        const v = voice[s.id];
        let dur;
        if (v && v > 0.4)
            dur = Math.max(v + NARR_GAP_BEFORE + NARR_GAP_AFTER, 2.2);
        else
            dur = s.dur;
        plan.push({ scene: s, start: cursor, dur, actIndex: 0, sceneNum });
        cursor += dur;
    }
    const total = cursor;
    return { plan, total };
}
/** Narration overlay state for a local time within a scene. */
export function narrationAt(scene, localT, voice) {
    if (!voice || voice <= 0) {
        // still show the subtitle, timed to the designed scene length
        const p = localT / scene.dur;
        if (p > 0.995)
            return null;
        return { p: Math.min(1, p * 1.15) };
    }
    const start = NARR_GAP_BEFORE * 0.5;
    const end = start + voice + NARR_GAP_AFTER * 0.7;
    const p = (localT - start) / (end - start);
    if (p < 0 || p > 1)
        return null;
    return { p };
}
export function writeManifest(plan, total) {
    const scenes = plan.map((p) => ({
        id: p.scene.id,
        act: p.scene.act,
        title: p.scene.title,
        start: +p.start.toFixed(3),
        dur: +p.dur.toFixed(3),
        evidence: p.scene.evidence,
    }));
    const out = {
        generated: new Date().toISOString(),
        total: +total.toFixed(3),
        fps: 30,
        width: 1920,
        height: 1080,
        scenes,
        acts: plan.reduce((acc, p) => {
            const a = p.scene.act;
            if (!acc[a])
                acc[a] = { start: +p.start.toFixed(3), end: +(p.start + p.dur).toFixed(3) };
            else
                acc[a].end = +(p.start + p.dur).toFixed(3);
            return acc;
        }, {}),
    };
    fs.writeFileSync(path.join(DATA, "timeline.json"), JSON.stringify(out, null, 1));
}
