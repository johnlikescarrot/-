import { Svg } from "../../theme.ts";
import type { Scene } from "./index.ts";
export const payloads: Omit<Scene, "start"> = { id: "08-payloads", chapter: "stub", title: "stub", duration: 1, render() { return new Svg().toSvgString(); } };
