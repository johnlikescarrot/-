/** Scene registry. Total duration is derived, never hand-maintained. */

export interface Scene {
  id: string;
  chapter: string;
  title: string;
  source?: string;
  duration: number;
  start: number;
  render(t: number, dur: number, abs: number): string;
}

import { coldOpen } from "./00-coldopen.ts";
import { thesis } from "./01-thesis.ts";
import { container } from "./02-container.ts";
import { manifest } from "./03-manifest.ts";
import { dex } from "./04-dex.ts";
import { native } from "./05-native.ts";
import { network } from "./06-network.ts";
import { capability } from "./07-capability.ts";
import { payloads } from "./08-payloads.ts";
import { method } from "./09-method.ts";
import { close } from "./10-close.ts";

const RAW = [coldOpen, thesis, container, manifest, dex, native, network, capability, payloads, method, close];

export const SCENES: Scene[] = (() => {
  let acc = 0;
  return RAW.map((s) => {
    const sc = { ...s, start: acc };
    acc += s.duration;
    return sc;
  });
})();

export const TOTAL_DURATION = SCENES.reduce((a, s) => a + s.duration, 0);

export function sceneAt(t: number): Scene {
  for (const s of SCENES) if (t < s.start + s.duration) return s;
  return SCENES[SCENES.length - 1];
}
