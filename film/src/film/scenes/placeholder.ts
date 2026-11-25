import type { SceneMeta } from "../timeline.js";
import { heading } from "../components.js";
import { C } from "../theme.js";

export const stub = (id: string, act: SceneMeta["act"], title: string): SceneMeta => ({
  id,
  act,
  title,
  dur: 10,
  evidence: [],
  draw(ctx, t) {
    heading(ctx, act, title, Math.min(1, t), C.green);
  },
});
