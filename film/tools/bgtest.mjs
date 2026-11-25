import { createCanvas } from "@napi-rs/canvas";
import fs from "node:fs";
const { background } = await import("../dist/film/bg.js");
const { C } = await import("../dist/film/theme.js");
const c = createCanvas(1920,1080); const x = c.getContext("2d");
x.fillStyle="#000"; x.fillRect(0,0,1920,1080);
background(x, 12.5, C.red, 1);
fs.writeFileSync("out/bgtest.png", c.toBuffer("image/png"));
console.log("ok");
