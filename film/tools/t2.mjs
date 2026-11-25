import { createCanvas } from "@napi-rs/canvas";
import fs from "node:fs";
const bg = await import("../dist/film/bg.js");
const th = await import("../dist/film/theme.js");
const c = createCanvas(400,200); const x = c.getContext("2d");
bg.background(x, 3, th.C.red, 1);
x.fillStyle="#00ff00"; x.fillRect(20,20,100,100);
console.log("alpha after bg:", x.globalAlpha, "transform:", JSON.stringify(x.getTransform?.() ?? "n/a"));
fs.writeFileSync("out/t2.png", c.toBuffer("image/png"));
