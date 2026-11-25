/** Chapter 10 — closing: the one-paragraph result, and how to cite it. */

import { C, FONT, Svg, T, ease, fmtInt, ramp } from "../../theme.ts";
import { chrome, kicker, para } from "../../components.ts";
import { facts } from "../../data.ts";
import type { Scene } from "./index.ts";

const M = 96;

const TAKEAWAYS: [string, string, string][] = [
  ["84,5%", "lưu lượng trỏ về hạ tầng first-party", C.brandHi],
  ["85,2%", "thư viện native hardened đầy đủ", C.lime],
  ["1,6%", "bytecode là mã riêng của ZaloPay", C.cyan],
  ["0", "khoá bí mật lộ trong chuỗi DEX", C.violet],
];

export const close: Omit<Scene, "start"> = {
  id: "10-close",
  chapter: "kết",
  title: "kết luận & trích dẫn",
  source: "sha256 " + facts.containerSha.slice(0, 16) + "…",
  duration: 18,
  render(t, dur) {
    const s = new Svg();
    s.backdrop(t, 1.0);

    // soft closing glow
    const gl = s.rad(960, 420, 900, [
      [0, C.brandHi, 0.10 * ramp(t, 0, 2)],
      [1, C.brandHi, 0],
    ]);
    s.rect(0, 0, 1920, 1080, gl);

    kicker(s, M, 178, "kết", C.cyan, ramp(t, 0, 0.6));
    s.text(M, 268, "Một chiếc ví được đọc như một tài liệu.", {
      size: 56, weight: 800, opacity: ramp(t, 0.1, 0.9), letter: -1,
    });

    TAKEAWAYS.forEach(([v, l, c], i) => {
      const k = ramp(t, 0.5 + i * 0.22, 1.3 + i * 0.22, ease.outExpo);
      const x = M + i * 442;
      s.text(x, 404, v, { size: 72, weight: 800, fill: c, opacity: k, letter: -2 });
      s.text(x, 442, l, { size: T.micro, fill: C.ink3, opacity: k });
      s.rect(x, 466, 380 * k, 2, c, { opacity: 0.55 });
    });

    para(
      s, M, 552,
      "Không có lỗ hổng nào được tuyên bố. Điều tìm thấy là một kiến trúc phòng thủ dày — R8, native hardening, " +
        "một lớp bảo vệ mã, pinning — và ba điểm cần theo dõi: cleartext được bật trong network security config, " +
        "30 thành phần exported, và 6 thư viện bị bóc tên mà không có nguồn gốc công khai.",
      { w: 1560, size: T.lead, fill: C.ink2, opacity: ramp(t, 1.4, 2.4), lineHeight: 46 },
    );

    // --- citation block ----------------------------------------------------
    const kC = ramp(t, 3.0, 4.0);
    if (kC > 0.01) {
      s.rect(M, 800, 1728, 1, C.line, { opacity: 0.7 * kC });
      s.text(M, 846, "TRÍCH DẪN", { size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2.6, opacity: kC });
      para(
        s, M, 884,
        "\"Giải phẫu một ví điện tử: phân tích tĩnh toàn bộ artifact ZaloPay 11.17.0 (XAPK)\" — " +
          "phân tích tĩnh tái lập được, 6 tệp JSON bằng chứng, phát hành kèm mã nguồn.",
        { w: 1180, size: T.small, fill: C.ink2, opacity: kC },
      );
      s.text(1400, 846, "artifact", { size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2, opacity: kC });
      s.text(1400, 880, `pkg ${facts.packageName}`, { size: T.micro, family: FONT.mono, fill: C.ink2, opacity: kC });
      s.text(1400, 906, `v${facts.versionName} (${facts.versionCode})`, { size: T.micro, family: FONT.mono, fill: C.ink2, opacity: kC });
      s.text(1400, 932, `sha256 ${facts.containerSha.slice(0, 24)}…`, { size: T.micro, family: FONT.mono, fill: C.ink3, opacity: kC });
      s.text(1400, 958, `${fmtInt(facts.entriesScanned)} entry · ${facts.libCount} ELF · ${facts.dexFiles} DEX`, {
        size: T.micro, family: FONT.mono, fill: C.ink3, opacity: kC,
      });
    }

    const kF = ramp(t, 5.0, 6.0);
    s.text(M, 1000, "Phân tích tĩnh trên một bản phát hành công khai. Không có dữ liệu người dùng, không có bí mật nào bị công bố.", {
      size: T.micro, family: FONT.mono, fill: C.ink3, opacity: 0.8 * kF,
    });

    chrome(s, { chapter: this.chapter, title: this.title, t, dur, index: "10", source: this.source });
    return s.toSvgString();
  },
};
