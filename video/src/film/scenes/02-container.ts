import { Svg } from "../../theme.ts";
import type { Scene } from "./index.ts";
export const container: Omit<Scene, "start"> = { id: "02-container", chapter: "stub", title: "stub", duration: 1, render() { return new Svg().toSvgString(); } };
