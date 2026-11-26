import { dirname, join } from "node:path";
import { fileURLToPath } from "node:url";

const HERE = dirname(fileURLToPath(import.meta.url));
// Merged subsets produced by video/tools/build_fonts.py:
// Latin + Vietnamese glyphs in one file per weight, renamed to BVN / PlexMono
// so family addressing cannot pick the wrong (partial) subset.
const F = join(HERE, "..", "assets", "fonts", "merged");

export const FONTS = [
  join(F, "BVN-Regular.ttf"),
  join(F, "BVN-SemiBold.ttf"),
  join(F, "BVN-ExtraBold.ttf"),
  join(F, "PlexMono-Regular.ttf"),
  join(F, "PlexMono-SemiBold.ttf"),
];
