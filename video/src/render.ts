/**
 * Render slides: SVG (TypeScript) -> PNG via sharp.
 * Output: video/frames/*.png + video/narration.json + video/subtitles.srt
 */
import * as fs from "fs";
import * as path from "path";
import sharp from "sharp";
import { SCENES, VIDEO_META } from "./scenes";
import { sceneToSvg, titleSvg } from "./svg";

const ROOT = path.resolve(__dirname, "..");
const FRAMES = path.join(ROOT, "frames");
const AUDIO = path.join(ROOT, "audio");

function srtTime(sec: number): string {
  const h = Math.floor(sec / 3600);
  const m = Math.floor((sec % 3600) / 60);
  const s = Math.floor(sec % 60);
  const ms = Math.floor((sec % 1) * 1000);
  const p = (n: number, l = 2) => String(n).padStart(l, "0");
  return `${p(h)}:${p(m)}:${p(s)},${p(ms, 3)}`;
}

async function main() {
  fs.mkdirSync(FRAMES, { recursive: true });
  fs.mkdirSync(AUDIO, { recursive: true });

  // Title frame
  const titlePng = path.join(FRAMES, "00-title.png");
  await sharp(Buffer.from(titleSvg())).png().toFile(titlePng);
  console.log(`[render] ${titlePng}`);

  const narration: Array<{ id: string; png: string; audio: string; text: string }> = [];
  let srt = "";
  let cursor = 0;
  const EST_SEC_PER_SCENE = 45; // placeholder until real audio durations known

  for (let i = 0; i < SCENES.length; i++) {
    const scene = SCENES[i];
    const svg = sceneToSvg(scene, i, SCENES.length);
    const png = path.join(FRAMES, `${scene.id}.png`);
    await sharp(Buffer.from(svg)).png().toFile(png);
    console.log(`[render] ${png} (${scene.bullets.length} bullets)`);

    const audio = path.join(AUDIO, `${scene.id}.mp3`);
    narration.push({ id: scene.id, png, audio, text: scene.narration });

    const start = cursor;
    const end = cursor + EST_SEC_PER_SCENE;
    srt += `${i + 1}\n${srtTime(start)} --> ${srtTime(end)}\n${scene.narration}\n\n`;
    cursor = end;
  }

  fs.writeFileSync(path.join(ROOT, "narration.json"), JSON.stringify(narration, null, 2), "utf-8");
  fs.writeFileSync(path.join(ROOT, "subtitles.srt"), srt, "utf-8");
  fs.writeFileSync(
    path.join(ROOT, "narration.txt"),
    narration.map((n) => `=== ${n.id} ===\n${n.text}\n`).join("\n"),
    "utf-8"
  );
  console.log(`[render] narration.json + subtitles.srt (${cursor}s estimated)`);
  console.log(`[render] ${VIDEO_META.width}x${VIDEO_META.height} @ ${VIDEO_META.fps}fps, ${SCENES.length} scenes`);
}

main().catch((e) => {
  console.error(e);
  process.exit(1);
});
