import { Svg } from "../../theme.ts";
import type { Scene } from "./index.ts";
export const capability: Omit<Scene, "start"> = { id: "07-capability", chapter: "stub", title: "stub", duration: 1, render() { return new Svg().toSvgString(); } };
