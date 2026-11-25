import { Svg } from "../../theme.ts";
import type { Scene } from "./index.ts";
export const close: Omit<Scene, "start"> = { id: "10-close", chapter: "stub", title: "stub", duration: 1, render() { return new Svg().toSvgString(); } };
