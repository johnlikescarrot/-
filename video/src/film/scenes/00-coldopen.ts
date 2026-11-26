/** Cold open: the artifact as a physical object, then the film's title. */

import { C, FONT, GRID, H, Svg, T, W, clamp01, ease, fmtInt, ramp } from "../../theme.ts";
import { chrome, kicker, para } from "../../components.ts";
import { facts } from "../../data.ts";
import type { Scene } from "./index.ts";

const HASH = facts.containerSha;

export const coldOpen: Omit<Scene, "start"> = {
  id: "coldopen",
  chapter: "mở đầu",
  title: "ZaloPay 11.17.0",
  source: "sha256 " + HASH.slice(0, 18) + "…",
  duration: 12.5,
  render(t, dur) {
    const s = new Svg();
    s.backdrop(t);

    // --- byte ribbon: the real SHA-256, scrolling, as the only texture -------
    const scroll = t * 46;
    for (let row = 0; row < 7; row++) {
      const y = 120 + row * 132;
      const text = (HASH + "0123456789abcdef").repeat(6);
      for (let i = 0; i < 3; i++) {
        const x = -220 - ((scroll * (1 + row * 0.06) + i * 760) % 760);
        s.text(x, y, text.slice(0, 74), {
          size: 78, family: FONT.mono, weight: 400,
          fill: row === 3 ? C.brand : C.ink3,
          opacity: (row === 3 ? 0.07 : 0.028) * ramp(t, 0, 1.6),
          letter: 6,
        });
      }
    }

    // --- scan sweep --------------------------------------------------------
    const sy = ((t * 260) % (H + 300)) - 150;
    const g = s.lin(0, sy - 120, 0, sy + 120, [
      [0, C.brandHi, 0],
      [0.5, C.brandHi, 0.09],
      [1, C.brandHi, 0],
    ]);
    s.rect(0, sy - 120, W, 240, g);

    // --- title -------------------------------------------------------------
    const t0 = 1.1;
    const k1 = ramp(t, t0, t0 + 0.9, ease.outExpo);
    const k2 = ramp(t, t0 + 0.5, t0 + 1.5, ease.outExpo);
    const k3 = ramp(t, t0 + 1.1, t0 + 2.0, ease.out);

    kicker(s, GRID.margin, 318, "nghiên cứu tĩnh · toàn bộ artifact", C.cyan, k1);

    s.text(GRID.margin, 430, "Giải phẫu", {
      size: 132, weight: 800, fill: C.ink, opacity: k2, letter: -3,
    });
    s.text(GRID.margin, 560, "một ví điện tử", {
      size: 132, weight: 800, opacity: k2, letter: -3,
      fill: s.lin(GRID.margin, 470, GRID.margin + 1180, 610, [
        [0, C.brandHi, 1], [0.55, C.cyan, 1], [1, C.violet, 1],
      ]),
    });

    if (k3 > 0.01) {
      s.rect(GRID.margin, 612, 168 * k3, 3, C.cyan, { opacity: 0.9 });
      para(
        s, GRID.margin, 676,
        "ZaloPay 11.17.0 — XAPK 98,8 MB · 5 APK · 19 DEX · 27 thư viện native.\n" +
          "Không chạy ứng dụng: chỉ đọc byte, đối chiếu bằng chứng, ghi lại giới hạn.",
        { w: 1320, size: 32, fill: C.ink2, opacity: k3 },
      );
    }

    // --- corner facts ------------------------------------------------------
    const k4 = ramp(t, t0 + 1.8, t0 + 2.6, ease.out);
    const items: [string, string][] = [
      [fmtInt(facts.entriesScanned), "entry đã quét"],
      [fmtInt(facts.classes), "class trong DEX"],
      [fmtInt(facts.libCount), "thư viện ELF"],
      ["0", "lần chạy app"],
    ];
    const usable = W - GRID.margin * 2;
    items.forEach(([v, l], i) => {
      const x = GRID.margin + i * (usable / 4);
      const a = clamp01(k4 * 1.6 - i * 0.14);
      s.line(x, 806, x, 900, C.line, 1, 0.6 * a);
      s.text(x + 30, 852, v, {
        size: 52, weight: 800, fill: i === 3 ? C.lime : C.ink, opacity: a, letter: -1,
      });
      s.text(x + 30, 886, l, {
        size: T.micro, family: FONT.mono, fill: C.ink3, opacity: 0.9 * a,
      });
    });

    chrome(s, { chapter: "mở đầu", title: "cold open", t, dur, index: "00", source: "sha256 " + HASH.slice(0, 16) + "…" });
    return s.toSvgString();
  },
};
