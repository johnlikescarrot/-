/** Chapter 08 — content beats extension: what the magic bytes actually say. */

import { C, FONT, Svg, T, ease, fmtInt, ramp } from "../../theme.ts";
import { chrome, codeBlock, kicker, panel, para } from "../../components.ts";
import { facts, data } from "../../data.ts";
import type { Scene } from "./index.ts";

const M = 96;

const KIND: Record<string, { vn: string; hex: string }> = {
  "ELF executable": { vn: "ELF · thư viện native", hex: "7F 45 4C 46" },
  "Dalvik DEX": { vn: "DEX · bytecode Dalvik", hex: "64 65 78 0A" },
  "ZIP archive": { vn: "ZIP · gói lồng", hex: "50 4B 03 04" },
  gzip: { vn: "gzip", hex: "1F 8B" },
  "PEM key/cert": { vn: "PEM · khoá công khai", hex: "2D 2D 2D 2D 2D" },
  opaque: { vn: "không có magic đã biết", hex: "—" },
};

export const payloads: Omit<Scene, "start"> = {
  id: "08-payloads",
  chapter: "07 · nội dung thật",
  title: "magic byte · 6.464 entry",
  source: "data/06_payloads.json",
  duration: 24,
  render(t, dur) {
    const s = new Svg();
    s.backdrop(t, 0.75);

    kicker(s, M, 148, "content-magic scan", C.amber, ramp(t, 0, 0.6));
    s.text(M, 236, "Tên tệp là lời khai. Byte đầu tiên là sự thật.", {
      size: 58, weight: 800, opacity: ramp(t, 0.15, 0.9), letter: -1,
    });
    para(s, M, 288, `Cả ${fmtInt(facts.entriesScanned)} entry được phân loại lại bằng 16 byte đầu — không tin phần mở rộng.`, {
      w: 1300, size: T.body, fill: C.ink2, opacity: ramp(t, 0.5, 1.2),
    });

    // --- magic table (meaningful kinds first) -----------------------------
    const counts = new Map(facts.contentTypes);
    const order = ["ELF executable", "Dalvik DEX", "ZIP archive", "gzip", "PEM key/cert", "opaque"];
    order.forEach((kind, i) => {
      const k = ramp(t, 0.8 + i * 0.14, 1.6 + i * 0.14, ease.out);
      const y = 372 + i * 46;
      const meta = KIND[kind] ?? { vn: kind, hex: "—" };
      const n = counts.get(kind) ?? 0;
      s.text(M, y, meta.vn, { size: T.small, fill: kind === "opaque" ? C.ink3 : C.ink2, opacity: k });
      s.text(M + 330, y, meta.hex, { size: T.micro, family: FONT.mono, fill: C.cyan, opacity: k });
      s.text(M + 700, y, `${fmtInt(n)} entry`, { size: T.micro, family: FONT.mono, fill: C.ink3, anchor: "end", opacity: 0.9 * k });
      s.rect(M, y + 14, 700 * k, 1, C.line, { opacity: 0.4 });
    });

    // --- the masquerade ---------------------------------------------------
    const px = 1000;
    const kM = ramp(t, 2.0, 3.0);
    panel(s, { x: px, y: 356, w: 824, h: 214 }, { label: "một tệp .so không phải ELF", accent: C.coral, opacity: kM });
    s.text(px + 26, 422, "assets/trueidlib.so", { size: 30, weight: 700, family: FONT.mono, fill: C.ink, opacity: kM });
    s.text(px + 26, 456, "392 byte · entropy 7,50 bit/byte", { size: T.micro, family: FONT.mono, fill: C.coral, opacity: kM });
    if (facts.hiddenExec[0]) {
      s.text(px + 26, 496, "Tên .so, nội dung là khối nhiễu mật độ cao.", { size: T.small, fill: C.ink2, opacity: kM });
      s.text(px + 26, 526, "Không phải ELF: dlopen / System.load sẽ từ chối.", {
        size: T.micro, family: FONT.mono, fill: C.ink3, opacity: kM,
      });
    }

    // --- notable assets ---------------------------------------------------
    const kA = ramp(t, 3.4, 4.4);
    panel(s, { x: px, y: 596, w: 824, h: 196 }, { label: "asset đáng chú ý", accent: C.violet, opacity: kA });
    const assets: [string, string][] = [
      ["assets/audience_network.dex", "4,6 MB · DEX phụ"],
      ["assets/android.1.zip", "3,7 MB · ZIP lồng"],
      ["assets/custom_models/object_labeler.tflite", "3,6 MB · mô hình"],
      ["assets/FastestDet.ncnn.bin", "2,3 MB · mạng ncnn"],
    ];
    assets.forEach(([p, n], i) => {
      const k = ramp(t, 3.7 + i * 0.12, 4.3 + i * 0.12);
      const y = 660 + i * 32;
      s.text(px + 26, y, p, { size: 14, family: FONT.mono, fill: C.ink2, opacity: k });
      s.text(px + 796, y, n, { size: 13, family: FONT.mono, fill: C.ink3, anchor: "end", opacity: 0.9 * k });
    });

    // --- JS bridge --------------------------------------------------------
    const kP = ramp(t, 4.6, 5.4);
    if (kP > 0.01) {
      codeBlock(s, { x: M, y: 700, w: 700, h: 220 }, {
        title: "cầu JavaScript · GriverBridge", t: t - 4.6, perLine: 0.08,
        lines: [
          "h5_bridge.js      4 446 B   postMessage ×3",
          "workerjs.js      14 360 B   postMessage ×2",
          "jsbridge.js       3 634 B",
          "res/raw/h5_bridge.js   dự phòng",
          "cover_component.js 2 888 B",
        ],
      });
    }

    // --- closing ----------------------------------------------------------
    const kL = ramp(t, 6.4, 7.4);
    s.rect(px, 830, 824, 2, C.amber, { opacity: 0.6 * kL });
    para(s, px, 872, "Ba public key PEM nằm rời trong assets — dùng để xác thực chữ ký, không phải khoá bí mật.", {
      w: 800, size: T.small, fill: C.ink3, opacity: kL,
    });

    chrome(s, { chapter: this.chapter, title: this.title, t, dur, index: "08", source: this.source });
    return s.toSvgString();
  },
};
