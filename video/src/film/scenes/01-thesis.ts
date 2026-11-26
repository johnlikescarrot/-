/** Thesis: what this film is, how it was made, and what it refuses to claim. */

import { C, FONT, GRID, H, Svg, T, W, ease, ramp } from "../../theme.ts";
import { chrome, kicker, panel, para, wrap } from "../../components.ts";
import type { Scene } from "./index.ts";

const Q = "Nếu bạn chỉ được cầm một tệp .zip và một niềm tin rằng bên trong là một chiếc ví điện tử, " +
  "bạn có thể biết chính xác điều gì — và điều gì bạn buộc phải thừa nhận là không biết?";

export const thesis: Omit<Scene, "start"> = {
  id: "thesis",
  chapter: "luận đề",
  title: "câu hỏi & phương pháp",
  source: "phương pháp: phân tích tĩnh, bằng chứng khép kín",
  duration: 14,
  render(t, dur) {
    const s = new Svg();
    s.backdrop(t, 0.85);

    const k0 = ramp(t, 0.1, 0.8);
    kicker(s, GRID.margin, 170, "luận đề", C.amber, k0);

    const lines = wrap(Q, 1420, 52);
    lines.forEach((ln, i) => {
      const a = ramp(t, 0.35 + i * 0.22, 1.05 + i * 0.22, ease.out);
      s.text(GRID.margin, 250 + i * 70, ln, { size: 52, weight: 600, fill: C.ink, opacity: a });
    });

    const cards: { label: string; head: string; body: string; color: string }[] = [
      {
        label: "01 · vật liệu",
        head: "Byte, không phải lời kể",
        body: "Container, manifest, DEX, ELF, asset — mọi khẳng định truy về một trường JSON sinh từ byte gốc.",
        color: C.brandHi,
      },
      {
        label: "02 · công cụ",
        head: "Harness tự viết, tái lập được",
        body: "Parser DEX/ELF thuần struct; androguard chỉ dùng để giải mã XML nhị phân. Không decompiler ảo.",
        color: C.cyan,
      },
      {
        label: "03 · giới hạn",
        head: "Nói rõ cái không biết",
        body: "Không chạy app, không có thiết bị, không đọc được vùng mã hoá. Suy luận hành vi bị loại bỏ.",
        color: C.amber,
      },
    ];

    cards.forEach((c, i) => {
      const k = ramp(t, 2.1 + i * 0.55, 3.0 + i * 0.55, ease.out);
      const x = GRID.margin + i * ((W - GRID.margin * 2 + 24) / 3);
      const w = (W - GRID.margin * 2 + 24) / 3 - 24;
      const y = 520;
      panel(s, { x, y, w, h: 300 }, { accent: c.color, label: c.label, opacity: k });
      s.text(x + 26, y + 96, c.head, { size: 32, weight: 700, fill: C.ink, opacity: k });
      para(s, x + 26, y + 148, c.body, { w: w - 52, size: T.body, fill: C.ink2, opacity: k, lineHeight: 38 });
      s.rect(x + 26, y + 268, 46 * k, 2, c.color, { opacity: 0.8 });
    });

    const k5 = ramp(t, 4.4, 5.4);
    s.text(GRID.margin, 900, "Câu trả lời không phải một bản án. Đó là một bản đồ có toạ độ.", {
      size: T.lead, weight: 600, fill: C.ink2, opacity: k5,
    });

    chrome(s, { chapter: "luận đề", title: "câu hỏi & phương pháp", t, dur, index: "01", source: this.source });
    return s.toSvgString();
  },
};
