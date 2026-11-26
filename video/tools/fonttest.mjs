import { Resvg } from "@resvg/resvg-js";
import { writeFileSync } from "node:fs";
const FONTS = ["BVN-Regular.ttf","BVN-SemiBold.ttf","BVN-ExtraBold.ttf","PlexMono-Regular.ttf","PlexMono-SemiBold.ttf"].map(f=>new URL(`../assets/fonts/merged/${f}`, import.meta.url).pathname);
const mk = (w, fam) => `<svg xmlns="http://www.w3.org/2000/svg" width="1200" height="240"><rect width="1200" height="240" fill="#060A14"/><text x="30" y="100" font-family="${fam}" font-weight="${w}" font-size="60" fill="#fff">Giải phẫu ẫữơạ 11.17.0</text><text x="30" y="180" font-family="PlexMono" font-size="34" fill="#7ef">sha256 34b9b6b6 · 1 070 703</text></svg>`;
for (const [w,fam,name] of [[400,"BVN","w400"],[600,"BVN","w600"],[800,"BVN","w800"],[400,"PlexMono","mono400"]]) {
  const png = new Resvg(mk(w,fam), {font:{fontFiles:FONTS, loadSystemFonts:false}}).render().asPng();
  writeFileSync(new URL(`../out/ft-${name}.png`, import.meta.url).pathname, png);
  console.log(name, png.length);
}
