/** Chapter 09 — method: the pipeline, and what it cannot see. */

import { C, FONT, Svg, T, ease, fmtInt, ramp } from "../../theme.ts";
import { chrome, kicker, panel, para, wrap } from "../../components.ts";
import { facts } from "../../data.ts";
import type { Scene } from "./index.ts";

const M = 96;

const STAGES: [string, string, string][] = [
  ["01", "provenance", "container · hash · manifest · chữ ký"],
  ["02", "dex census", "19 tệp · class · chuỗi · native"],
  ["03", "native ELF", "27 .so · hardening · JNI"],
  ["04", "secrets", "3.114 URL · host · PEM"],
  ["05", "security", "14 chủ đề · SDK"],
  ["06", "payloads", "6.464 entry · magic byte"],
];

const LIMITS = [
  "Không thực thi ứng dụng: mọi kết luận là tĩnh, không phải quan sát runtime.",
  "Không có thiết bị ARM thật: hành vi native chỉ suy ra từ bảng ký hiệu.",
  "Vùng mã hoá / VMP không được giải: chỉ mô tả hình dạng, không đọc nội dung.",
  "Không suy đoán ý định: \"có khả năng\" không đồng nghĩa với \"đang làm\".",
];

export const method: Omit<Scene, "start"> = {
  id: "09-method",
  chapter: "08 · phương pháp",
  title: "pipeline · limits",
  source: "6 script Python · 6 tệp JSON · 1 bộ phim TS",
  duration: 22,
  render(t, dur) {
    const s = new Svg();
    s.backdrop(t, 0.75);

    kicker(s, M, 148, "reproducible by construction", C.cyan, ramp(t, 0, 0.6));
    s.text(M, 236, "Không có bước nào là hộp đen.", {
      size: 58, weight: 800, opacity: ramp(t, 0.15, 0.9), letter: -1,
    });
    para(s, M, 288, "Sáu script Python → sáu tệp JSON → một bộ phim TypeScript. Chạy lại từ đầu, ra đúng những con số này.", {
      w: 1380, size: T.body, fill: C.ink2, opacity: ramp(t, 0.5, 1.2),
    });

    // --- pipeline ----------------------------------------------------------
    STAGES.forEach(([n, name, note], i) => {
      const k = ramp(t, 0.9 + i * 0.16, 1.6 + i * 0.16, ease.out);
      const x = M + (i % 3) * 500;
      const y = 372 + Math.floor(i / 3) * 150;
      s.rect(x, y, 466, 118, C.panel, { r: 12, stroke: C.line, opacity: 0.9 * k });
      s.rect(x + 1, y + 14, 3, 90, C.brandHi, { opacity: 0.9 * k });
      s.text(x + 22, y + 40, n, { size: T.micro, family: FONT.mono, fill: C.brandHi, letter: 2, opacity: k });
      s.text(x + 22, y + 78, name, { size: 30, weight: 700, fill: C.ink, opacity: k });
      s.text(x + 22, y + 102, note, { size: T.micro, family: FONT.mono, fill: C.ink3, opacity: k });
      if (i % 3 < 2) {
        const ax = x + 472;
        const ak = ramp(t, 1.3 + i * 0.16, 1.8 + i * 0.16);
        s.line(ax, y + 59, ax + 22, y + 59, C.lineHi, 2, ak);
      }
    });

    // --- limits ------------------------------------------------------------
    const kL = ramp(t, 3.2, 4.2);
    s.text(M, 690, "GIỚI HẠN — ĐƯỢC NÊU TRƯỚC, KHÔNG PHẢI SAU", {
      size: T.micro, family: FONT.mono, fill: C.amber, letter: 2.4, opacity: kL,
    });
    LIMITS.forEach((l, i) => {
      const k = ramp(t, 3.4 + i * 0.2, 4.0 + i * 0.2);
      const y = 736 + i * 54;
      s.circle(M + 6, y - 6, 4, C.amber, { opacity: k });
      const lines = wrap(l, 1180, T.small);
      lines.slice(0, 1).forEach((ln) => s.text(M + 24, y, ln, { size: T.small, fill: C.ink2, opacity: k }));
    });

    // --- right panel: tooling ---------------------------------------------
    const px = 1600;
    const kT = ramp(t, 2.4, 3.4);
    panel(s, { x: px, y: 372, w: 224, h: 330 }, { label: "công cụ", accent: C.violet, opacity: kT });
    const tools: [string, string][] = [
      ["androguard", "chỉ để giải mã XML nhị phân"],
      ["dexlib (tự viết)", "parser DEX thuần struct"],
      ["elflib (tự viết)", "parser ELF32 + bảng động"],
      ["resvg", "rasterize SVG → PNG"],
      ["ffmpeg / libx264", "mã hoá H.264 + AAC"],
      ["TypeScript", "scene là hàm thuần của t"],
    ];
    tools.forEach(([a], i) => {
      const k = ramp(t, 2.7 + i * 0.1, 3.3 + i * 0.1);
      const y = 436 + i * 42;
      s.text(px + 20, y, a, { size: T.micro + 1, family: FONT.mono, fill: C.ink, opacity: k });
    });

    // --- reproducibility callout ------------------------------------------
    const kR = ramp(t, 5.0, 6.0);
    para(s, px, 760, `Tổng ${fmtInt(facts.entriesScanned)} entry, 0 lần chạy app, 6 tệp JSON làm bằng chứng.`, {
      w: 220, size: T.small, fill: C.ink3, opacity: kR,
    });

    chrome(s, { chapter: this.chapter, title: this.title, t, dur, index: "09", source: this.source });
    return s.toSvgString();
  },
};
