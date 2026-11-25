import { createCanvas } from "@napi-rs/canvas";
import fs from "node:fs";
const R = await import("../dist/film/render.js");
R.registerFonts();
const { loadEvidence } = await import("../dist/research/evidence.js");
const mods = ["opening","artifact","identity","code","native","trust","verdict"];
const SCENES = [];
for (const m of mods) {
  const mod = await import(`../dist/film/scenes/${m}.js`);
  const key = Object.keys(mod).find(k => k.startsWith("ACT") && Array.isArray(mod[k]));
  if (key) SCENES.push(...mod[key]);
}
const ev = loadEvidence();
const id = process.argv[2], t = Number(process.argv[3] ?? 5);
const sc = SCENES.find(s => s.id === id);
if (!sc) { console.error("no scene", id, SCENES.map(s=>s.id)); process.exit(1); }
const c = createCanvas(1920,1080); const x = c.getContext("2d");
x.fillStyle = "#3a3f4a"; x.fillRect(0,0,1920,1080);
sc.draw(x, t, ev, { width:1920, height:1080, fps:30, dpi:1, narration:false, timing:null });
fs.writeFileSync(`out/iso_${id}.png`, c.toBuffer("image/png"));
console.log("ok", id);
