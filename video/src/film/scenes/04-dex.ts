/** Chapter 04 — the DEX layer: 19 files, 60k classes, 1.6% first-party code. */

import { C, FONT, Svg, T, ease, fmtInt, ramp } from "../../theme.ts";
import { chrome, kicker, panel, para } from "../../components.ts";
import { facts } from "../../data.ts";
import type { Scene } from "./index.ts";

const M = 96;
const TX = 96;
const TY = 520;
const TW = 1080;
const TH = 330;

/** Squarified treemap over dex sizes — deterministic, no randomness. */
function treemap(items: { name: string; value: number; color: string }[], x: number, y: number, w: number, h: number) {
  const out: { name: string; value: number; color: string; x: number; y: number; w: number; h: number }[] = [];
  const total = items.reduce((a, i) => a + i.value, 0);
  const sorted = [...items].sort((a, b) => b.value - a.value);
  let cx = x, cy = y, cw = w, ch = h, rest = total;
  let row: typeof sorted = [];
  let rowSum = 0;

  const flush = (horizontal: boolean) => {
    if (!row.length) return;
    const frac = rowSum / rest;
    if (horizontal) {
      const rh = ch * frac;
      let rx = cx;
      row.forEach((it) => {
        const rw = cw * (it.value / rowSum);
        out.push({ ...it, x: rx, y: cy, w: rw, h: rh });
        rx += rw;
      });
      cy += rh; ch -= rh;
    } else {
      const rw = cw * frac;
      let ry = cy;
      row.forEach((it) => {
        const rh = ch * (it.value / rowSum);
        out.push({ ...it, x: cx, y: ry, w: rw, h: rh });
        ry += rh;
      });
      cx += rw; cw -= rw;
    }
    rest -= rowSum;
    row = [];
    rowSum = 0;
  };

  for (const it of sorted) {
    row.push(it);
    rowSum += it.value;
    const horizontal = cw >= ch;
    const frac = rowSum / Math.max(1, rest);
    const rowDepth = (horizontal ? ch : cw) * frac;
    const widest = Math.max(...row.map((r) => r.value / rowSum));
    const cell = (horizontal ? cw : ch) * widest;
    if (cell > rowDepth * 1.7 && row.length > 1) {
      row.pop();
      rowSum -= it.value;
      flush(horizontal);
      row.push(it);
      rowSum = it.value;
    }
  }
  flush(cw >= ch);
  return out;
}

export const dex: Omit<Scene, "start"> = {
  id: "04-dex",
  chapter: "03 · mã nguồn",
  title: "19 DEX · 60.671 class",
  source: "data/02_dex.json",
  duration: 26,
  render(t, dur) {
    const s = new Svg();
    s.backdrop(t, 0.75);

    kicker(s, M, 148, "DEX · Dalvik executable", C.brandHi, ramp(t, 0, 0.6));
    s.text(M, 236, "Sáu mươi nghìn class. Một phần trăm là của ZaloPay.", {
      size: 56, weight: 800, opacity: ramp(t, 0.15, 0.9), letter: -1,
    });
    para(s, M, 288, "Phần còn lại là hệ sinh thái SDK — và đó mới là điều đáng đo.", {
      w: 1300, size: T.body, fill: C.ink2, opacity: ramp(t, 0.5, 1.2),
    });

    // --- treemap of the 19 dex --------------------------------------------
    const palette = [C.brand, C.brandHi, C.cyan, C.violet, C.amber, C.pink, C.lime];
    const cells = treemap(
      facts.dexSizes.map((d, i) => ({ name: d.name, value: d.size, color: palette[i % palette.length] })),
      TX, TY, TW, TH,
    );
    cells.forEach((c, i) => {
      const k = ramp(t, 0.8 + i * 0.05, 1.5 + i * 0.05, ease.out);
      s.rect(c.x + 1.5, c.y + 1.5, Math.max(0, c.w - 3) * k, Math.max(0, c.h - 3), c.color, {
        r: 3, opacity: 0.16 + 0.5 * k,
      });
      s.rect(c.x + 1.5, c.y + 1.5, Math.max(0, c.w - 3), Math.max(0, c.h - 3), "none", {
        stroke: c.color, sw: 1, opacity: 0.5 * k,
      });
      if (c.w > 96 && c.h > 40) {
        s.text(c.x + 10, c.y + 24, c.name.replace(".dex", ""), {
          size: T.micro, family: FONT.mono, fill: C.ink, opacity: k,
        });
        if (c.h > 58) {
          s.text(c.x + 10, c.y + 44, `${(c.value / 1e6).toFixed(1)} MB`.replace(".", ","), {
            size: T.micro, family: FONT.mono, fill: C.ink2, opacity: 0.85 * k,
          });
        }
      }
    });
    s.text(TX, TY - 18, "DIỆN TÍCH = SỐ BYTE DEX (19 tệp, 78,6 MB)", {
      size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2, opacity: ramp(t, 0.6, 1.2),
    });

    // --- stats column ------------------------------------------------------
    const px = 1236;
    const stats: [string, string, string][] = [
      [fmtInt(facts.classes), "class · 19 tệp DEX", C.ink],
      [fmtInt(facts.strings), "chuỗi duy nhất", C.ink],
      [fmtInt(facts.ownClasses), "class thuộc vn.com.vng.zalopay", C.cyan],
      [fmtInt(facts.nativeMethods), "phương thức native", C.violet],
    ];
    stats.forEach(([v, l, c], i) => {
      const k = ramp(t, 1.2 + i * 0.22, 2.0 + i * 0.22, ease.outExpo);
      const y = 420 + i * 118;
      s.text(px, y, v, { size: 64, weight: 800, fill: c, opacity: k, letter: -1.5 });
      s.text(px, y + 26, l, { size: T.small, fill: C.ink3, opacity: k });
    });

    // own-code share bar (right column, below the stats)
    const kS = ramp(t, 3.0, 4.0);
    s.text(px, 868, `TỈ LỆ MÃ RIÊNG · ${facts.ownSharePct.toFixed(1).replace(".", ",")}%`, {
      size: T.micro, family: FONT.mono, fill: C.cyan, letter: 1.6, opacity: kS,
    });
    s.rect(px, 886, 560, 12, C.line, { r: 6, opacity: 0.5 * kS });
    s.rect(px, 886, Math.max(6, 560 * (facts.ownSharePct / 100) * kS), 12, C.cyan, { r: 6 });
    s.text(px, 936, `${fmtInt(facts.ownClasses)} class / ${fmtInt(facts.classes)}`, {
      size: T.micro, family: FONT.mono, fill: C.ink3, opacity: 0.9 * kS,
    });

    // --- third-party roots -------------------------------------------------
    const kT = ramp(t, 4.2, 5.2);
    s.text(M, 890, "HỆ SINH THÁI SDK TRONG BYTECODE", {
      size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2.2, opacity: kT,
    });
    facts.thirdPartyRoots.slice(0, 4).forEach((r, i) => {
      const k = ramp(t, 4.3 + i * 0.12, 4.9 + i * 0.12);
      const x = M + i * 276;
      s.rect(x, 906, 248 * k, 2, C.brand, { opacity: 0.7 });
      s.text(x, 940, r[0].replace(/\//g, "."), { size: T.micro, family: FONT.mono, fill: C.ink2, opacity: k });
      s.text(x, 964, fmtInt(r[1]) + " class", { size: T.micro, family: FONT.mono, fill: C.ink3, opacity: 0.9 * k });
    });

    chrome(s, { chapter: this.chapter, title: this.title, t, dur, index: "04", source: this.source });
    return s.toSvgString();
  },
};
