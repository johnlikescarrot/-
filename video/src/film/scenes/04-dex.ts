import { Svg } from "../../theme.ts";
import type { Scene } from "./index.ts";
export const dex: Omit<Scene, "start"> = { id: "04-dex", chapter: "stub", title: "stub", duration: 1, render() { return new Svg().toSvgString(); } };
