/** Design tokens: a single dark "instrument panel" language for the whole film. */
import { GlobalFonts } from '@napi-rs/canvas';
import { resolve } from 'node:path';
import { ROOT } from './data.js';

GlobalFonts.registerFromPath(resolve(ROOT, 'video/assets/fonts/Inter-Medium.otf'), 'Inter');
GlobalFonts.registerFromPath('/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf', 'DejaVu');
GlobalFonts.registerFromPath('/usr/share/fonts/truetype/dejavu/DejaVuSansMono.ttf', 'DejaVuMono');

export const W = 1920;
export const H = 1080;
export const FPS = 30;

export const C = {
  bg: '#070b12',
  bg2: '#0b1220',
  panel: 'rgba(15,23,36,0.86)',
  panelEdge: 'rgba(120,160,200,0.16)',
  hair: 'rgba(148,180,215,0.14)',
  text: '#e7eef8',
  dim: '#93a7bf',
  faint: '#5b7089',
  amber: '#f5c451',
  cyan: '#38bdf8',
  teal: '#2dd4bf',
  magenta: '#f472b6',
  violet: '#a78bfa',
  red: '#fb7185',
  green: '#34d399',
  white: '#ffffff',
} as const satisfies Record<string, string>;

type CKey = keyof typeof C;
export type Color = (typeof C)[CKey] | string;

export const F = {
  sans: (px: number, weight = 400): string => `${weight} ${px}px "Inter, DejaVu"`,
  mono: (px: number): string => `${px}px "DejaVuMono, Inter, DejaVu"`,
};

export const LAYOUT = {
  margin: 96,
  headerY: 74,
  footerY: H - 56,
} as const;

export const chapterColor = (i: number): string =>
  [C.amber, C.cyan, C.teal, C.violet, C.magenta, C.amber, C.cyan, C.teal, C.violet, C.amber, C.cyan][i % 11] as string;
