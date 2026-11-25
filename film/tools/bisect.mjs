import { createCanvas } from "@napi-rs/canvas";
import fs from "node:fs";
const R = await import("../dist/film/render.js");
const bg = await import("../dist/film/bg.js");
const d = await import("../dist/film/draw.js");
const th = await import("../dist/film/theme.js");
const { loadEvidence } = await import("../dist/research/evidence.js");
const mods = ["opening","artifact","identity","code","native","trust","verdict"];
const SCENES = [];
for (const m of mods) { const mod = await import(`../dist/film/scenes/${m}.js`); const k = Object.keys(mod).find(k=>k.startsWith("ACT")&&Array.isArray(mod[k])); if (k) SCENES.push(...mod[k]); }
R.registerFonts(); const ev = loadEvidence();
const id = process.argv[2], t = Number(process.argv[3]??5);
const sc = SCENES.find(s=>s.id===id);
const steps = ["scene","bg+scene","bloom","vignette","grain"];
const c = createCanvas(1920,1080); const x = c.getContext("2d");
x.fillStyle="#000"; x.fillRect(0,0,1920,1080);
for (const s of steps) {
  if (s==="bg+scene") bg.background(x, 30, th.C.red, 1);
  if (s==="scene") sc.draw(x, t, ev, {width:1920,height:1080,fps:30,dpi:1,narration:false,timing:null});
  if (s==="bloom") bg.edgeBloom(x, th.C.red, 1);
  if (s==="vignette") d.vignette(x,1920,1080,0.5);
  if (s==="grain") d.grain(x,1920,1080,0.028,33);
  fs.writeFileSync(`out/bis_${s.replace("+","_")}.png`, c.toBuffer("image/png"));
}
console.log("done", steps.join(","));
