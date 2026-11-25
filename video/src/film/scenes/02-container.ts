/** Chapter 02 — container anatomy: one ZIP, two levels, five APKs. */

import { C, FONT, Svg, T, ease, fmtBytes, fmtPct, ramp } from "../../theme.ts";
import { chrome, kicker, panel, para, stackedBar } from "../../components.ts";
import { facts } from "../../data.ts";
import type { Scene } from "./index.ts";

const M = 96;

export const container: Omit<Scene, "start"> = {
  id: "02-container",
  chapter: "01 · vật chứa",
  title: "container · xapk · 5 apk",
  source: "data/01_provenance.json",
  duration: 26,
  render(t, dur) {
    const s = new Svg();
    s.backdrop(t, 0.8);

    kicker(s, M, 150, "lớp 0 — vật chứa", C.cyan, ramp(t, 0, 0.6));
    s.text(M, 228, "Một tệp ZIP. Bên trong là một tệp ZIP khác.", {
      size: 60, weight: 800, opacity: ramp(t, 0.15, 0.9), letter: -1,
    });
    para(s, M, 282, `sha256 ${facts.containerSha.slice(0, 40)}…`, {
      w: 1500, size: T.small, fill: C.ink3, opacity: ramp(t, 0.4, 1.1),
    });

    // --- chain: zip → xapk → 5 apk -----------------------------------------
    const chain = [
      { kick: "LỚP 0", name: "ZIP", size: fmtBytes(facts.containerBytes), note: "tải về từ APKPure", color: C.brand },
      { kick: "LỚP 1", name: "XAPK", size: fmtBytes(facts.xapkBytes), note: "1 base + 4 split", color: C.cyan },
      { kick: "LỚP 2", name: "5 APK", size: `${fmtBytes(facts.totalInstalled)}`, note: "khi giải nén trên máy", color: C.violet },
    ];
    chain.forEach((c, i) => {
      const k = ramp(t, 0.7 + i * 0.3, 1.5 + i * 0.3, ease.outExpo);
      const x = M + i * 596;
      const y = 350 - (1 - k) * 18;
      s.rect(x, y, 520, 132, C.panel, { r: 14, stroke: C.line, opacity: 0.9 * k });
      s.rect(x + 1, y + 16, 4, 100, c.color, { opacity: k });
      s.text(x + 28, y + 36, c.kick, { size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2.6, opacity: k });
      s.text(x + 28, y + 84, c.name, { size: 40, weight: 800, fill: C.ink, opacity: k, letter: -0.5 });
      s.text(x + 160, y + 84, c.size, { size: T.body, family: FONT.mono, fill: c.color, opacity: k });
      s.text(x + 28, y + 114, c.note, { size: T.micro, family: FONT.mono, fill: C.ink3, opacity: k });
      if (i < 2) {
        const ax = x + 528;
        const ak = ramp(t, 1.4 + i * 0.3, 1.9 + i * 0.3);
        s.line(ax, y + 66, ax + 52, y + 66, C.lineHi, 2, ak);
        s.path(`M ${ax + 52} ${y + 66} l -10 -7 l 0 14 z`, { fill: C.ink3, opacity: ak });
      }
    });

    // --- split table -------------------------------------------------------
    const rows = [
      { label: "vn.com.vng.zalopay.apk", bytes: facts.base.bytes, note: "code · res · assets", color: C.brand },
      ...facts.splits.map((x, i) => ({
        label: x.name,
        bytes: x.bytes,
        note: i === 0 ? "27 thư viện native" : i === 3 ? "mật độ xhdpi" : "tài nguyên ngôn ngữ",
        color: i === 0 ? C.cyan : i === 3 ? C.violet : C.ink3,
      })),
    ];
    const maxB = Math.max(...rows.map((r) => r.bytes));
    s.text(M, 566, "NĂM TỆP APK", { size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2.6, opacity: ramp(t, 1.6, 2.2) });
    rows.forEach((r, i) => {
      const k = ramp(t, 1.9 + i * 0.22, 2.7 + i * 0.22, ease.out);
      const y = 606 + i * 62;
      s.text(M, y, r.label, { size: T.small, family: FONT.mono, fill: C.ink2, opacity: k });
      s.rect(560, y - 14, 380, 12, C.line, { r: 6, opacity: 0.45 * k });
      s.rect(560, y - 14, 380 * (r.bytes / maxB) * k, 12, r.color, { r: 6, opacity: 0.95 });
      s.text(956, y, fmtBytes(r.bytes), { size: T.small, family: FONT.mono, fill: C.ink, opacity: k });
      s.text(1090, y, r.note, { size: T.micro, family: FONT.mono, fill: C.ink3, opacity: 0.9 * k });
    });

    // --- composition panel -------------------------------------------------
    const kC = ramp(t, 3.4, 4.4);
    const px = 1300;
    panel(s, { x: px, y: 520, w: 560, h: 200 }, { label: "phân bố byte trong payload", accent: C.brandHi, opacity: kC });
    const top2 = (facts.base.bytes + facts.nativeSplitBytes) / facts.xapkBytes;
    s.text(px + 26, 604, fmtPct(top2 * 100), { size: 46, weight: 800, fill: C.ink, opacity: kC });
    s.text(px + 190, 602, "nằm trong 2 tệp", { size: T.body, fill: C.ink2, opacity: kC });
    stackedBar(s, px + 26, 620, 508, 16, [
      { label: "base", value: facts.base.bytes, color: C.brand },
      { label: "native", value: facts.nativeSplitBytes, color: C.cyan },
      { label: "density", value: 1232563, color: C.violet },
      { label: "lang", value: 74137 + 86425, color: C.ink3 },
    ], t - 3.4);
    ([[C.brand, "base", fmtBytes(facts.base.bytes)], [C.cyan, "native", fmtBytes(facts.nativeSplitBytes)],
      [C.violet, "density", "1,23 MB"], [C.ink3, "lang", "160,6 KB"]] as [string, string, string][]).forEach(([c, l, v], i) => {
      const x = px + 26 + (i % 2) * 260;
      const y = 668 + Math.floor(i / 2) * 26;
      const a = ramp(t, 4.0 + i * 0.08, 4.4 + i * 0.08);
      s.circle(x + 5, y - 5, 4.5, c, { opacity: a });
      s.text(x + 18, y, l, { size: T.small, fill: C.ink2, opacity: a });
      s.text(x + 220, y, v, { size: T.small, family: FONT.mono, fill: C.ink3, anchor: "end", opacity: a });
    });

    // --- ABI gap -----------------------------------------------------------
    const kG = ramp(t, 4.6, 5.6);
    panel(s, { x: px, y: 736, w: 560, h: 168 }, { label: "ABI thực tế trong gói", accent: C.coral, opacity: kG });
    s.text(px + 26, 802, "armeabi-v7a", { size: 34, weight: 800, fill: C.cyan, opacity: kG });
    s.text(px + 300, 800, "32-bit · có", { size: T.small, fill: C.ink2, opacity: kG });
    s.line(px + 26, 826, px + 534, 826, C.line, 1, 0.7 * kG);
    s.text(px + 26, 874, "arm64-v8a", { size: 28, weight: 700, fill: C.ink3, opacity: 0.55 * kG });
    s.text(px + 246, 872, "không có trong gói", { size: T.micro, family: FONT.mono, fill: C.coral, opacity: kG });

    // --- signing line ------------------------------------------------------
    const kS = ramp(t, 5.9, 6.7);
    s.text(px, 950, "APK Signing Block · v2 + v3", { size: T.small, family: FONT.mono, fill: C.ink3, opacity: kS });
    s.text(px, 976, "nguồn gói: APKPure STAMP", { size: T.small, family: FONT.mono, fill: C.ink3, opacity: 0.8 * kS });

    // --- closing observation ----------------------------------------------
    const kF = ramp(t, 7.2, 8.4);
    if (kF > 0.01) {
      s.rect(M, 906, 1140, 2, C.line, { opacity: 0.6 * kF });
      s.text(M, 948, "Một ZIP lồng ZIP lồng APK: ranh giới tin cậy không nằm ở tên tệp.", {
        size: T.body, fill: C.ink2, opacity: kF,
      });
    }

    chrome(s, { chapter: this.chapter, title: this.title, t, dur, index: "02", source: this.source });
    return s.toSvgString();
  },
};
