/** Render representative stills for design review (not part of the deliverable). */
import { createCanvas } from '@napi-rs/canvas';
import { mkdirSync, writeFileSync } from 'node:fs';
import { resolve } from 'node:path';
import { ROOT } from './data.js';
import { FPS, H, W } from './theme.js';
import { drawIntro } from './scenes/intro.js';
import { drawContainer } from './scenes/container.js';
import { drawCode } from './scenes/code.js';
import { drawNative } from './scenes/native.js';
import { drawDataflow } from './scenes/dataflow.js';
import { drawIntegrity } from './scenes/integrity.js';
import { drawConclusion } from './scenes/conclusion.js';

const out = resolve(ROOT, 'work/stills');
mkdirSync(out, { recursive: true });
const canvas = createCanvas(W, H);
const ctx = canvas.getContext('2d');
const shots: [string, (c: typeof ctx, t: number) => void, number][] = [
  ['intro-title', drawIntro, 2.6],
  ['intro-question', drawIntro, 14],
  ['container-card', drawContainer, 2],
  ['container-graph', drawContainer, 8],
  ['container-zip', drawContainer, 20],
  ['container-align', drawContainer, 36],
  ['code-scale', drawCode, 6],
  ['code-dex', drawCode, 20],
  ['code-sdk', drawCode, 36],
  ['native-map', drawNative, 8],
  ['native-hardening', drawNative, 22],
  ['native-crypto', drawNative, 38],
  ['flow-topology', drawDataflow, 8],
  ['flow-miniapps', drawDataflow, 22],
  ['flow-privacy', drawDataflow, 36],
  ['integrity-cert', drawIntegrity, 8],
  ['integrity-block', drawIntegrity, 22],
  ['integrity-verdict', drawIntegrity, 36],
  ['conclusion-findings', drawConclusion, 14],
  ['conclusion-method', drawConclusion, 26],
  ['conclusion-cite', drawConclusion, 37],
];
shots.forEach(([name, fn, t]) => {
  fn(ctx, t);
  writeFileSync(resolve(out, `${name}.png`), canvas.toBuffer('image/png'));
  console.log('still', name);
});
