import { createCanvas } from "@napi-rs/canvas";
import fs from "node:fs";
const R = await import("../dist/film/render.js");
const bg = await import("../dist/film/bg.js");
const d = await import("../dist/film/draw.js");
const hudm = await import("../dist/film/hud.js");
const th = await import("../dist/film/theme.js");
R.registerFonts();
const c = createCanvas(1920,1080); const x = c.getContext("2d");
const stages = process.argv.slice(2);
x.fillStyle="#000"; x.fillRect(0,0,1920,1080);
for (const s of stages) {
  if (s==="bg") bg.background(x, 12.5, "#ff5f6d", 1);
  if (s==="bloom") bg.edgeBloom(x, "#ff5f6d", 1);
  if (s==="vig") d.vignette(x,1920,1080,0.5);
  if (s==="marks") d.frameMarks(x,1920,1080,th.C.line2,0.28);
  if (s==="grain") d.grain(x,1920,1080,0.028,10);
  if (s==="spine") bg.progressSpine(x, 100, 317, "#ff5f6d");
  if (s==="hud") hudm.hud(x,{act:"trust",sceneIndex:1,sceneCount:24,sceneNum:1,evidenceId:"film/data/09_*.json",accent:"#ff5f6d",t:12,total:317});
}
fs.writeFileSync("out/stages_"+stages.join("-")+".png", c.toBuffer("image/png"));
console.log("ok", stages.join("+"));
