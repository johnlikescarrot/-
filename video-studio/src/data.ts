import { readFileSync } from "node:fs";
import { resolve, dirname } from "node:path";
import { fileURLToPath } from "node:url";
import { loadImage, type Image } from "@napi-rs/canvas";
import type { ResearchDataset } from "./types.js";

const __dirname = dirname(fileURLToPath(import.meta.url));
const REPO_ROOT = resolve(__dirname, "../..");

export function loadResearchDataset(): ResearchDataset {
  const p = resolve(REPO_ROOT, "research/evidence/zalopay_summary_dataset.json");
  return JSON.parse(readFileSync(p, "utf-8")) as ResearchDataset;
}

export async function loadZalopayIcon(): Promise<Image | null> {
  const candidates = [
    resolve("/tmp/zalopay_re/xapk/icon.png"),
    resolve(REPO_ROOT, "research/evidence/icon.png"),
  ];
  for (const p of candidates) {
    try {
      return await loadImage(p);
    } catch {
      // try next
    }
  }
  return null;
}

export const formatInt = (n: number): string => n.toLocaleString("en-US");
export const formatMB = (bytes: number): string => `${(bytes / 1_000_000).toFixed(2)} MB`;
