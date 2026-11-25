import { Svg } from "../../theme.ts";
import type { Scene } from "./index.ts";
export const manifest: Omit<Scene, "start"> = { id: "03-manifest", chapter: "stub", title: "stub", duration: 1, render() { return new Svg().toSvgString(); } };
