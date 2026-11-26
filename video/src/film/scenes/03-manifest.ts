/** Chapter 03 — the manifest as a permission and surface budget. */

import { C, FONT, Svg, T, ease, ramp } from "../../theme.ts";
import { chrome, kicker, panel, para, ring, wrap } from "../../components.ts";
import { facts } from "../../data.ts";
import { data } from "../../data.ts";
import type { Scene } from "./index.ts";

const M = 96;

const SENSITIVE: { key: string; label: string }[] = [
  { key: "android.permission.READ_CONTACTS", label: "đọc danh bạ" },
  { key: "android.permission.WRITE_CONTACTS", label: "ghi danh bạ" },
  { key: "android.permission.READ_PHONE_STATE", label: "trạng thái máy" },
  { key: "android.permission.ACCESS_FINE_LOCATION", label: "vị trí chính xác" },
  { key: "android.permission.CAMERA", label: "camera" },
  { key: "android.permission.RECORD_AUDIO", label: "micro" },
  { key: "android.permission.READ_CALENDAR", label: "lịch" },
  { key: "android.permission.QUERY_ALL_PACKAGES", label: "app đã cài" },
  { key: "android.permission.NFC", label: "NFC / EMV" },
  { key: "android.permission.DETECT_SCREEN_CAPTURE", label: "dò chụp màn hình" },
];

export const manifest: Omit<Scene, "start"> = {
  id: "03-manifest",
  chapter: "02 · bề mặt",
  title: "manifest & quyền",
  source: "data/01_provenance.json · manifest",
  duration: 25,
  render(t, dur) {
    const s = new Svg();
    s.backdrop(t, 0.75);

    kicker(s, M, 148, "AndroidManifest.xml · 125.916 byte", C.amber, ramp(t, 0, 0.6));
    s.text(M, 240, "Bề mặt tấn công được khai báo công khai.", {
      size: 60, weight: 800, opacity: ramp(t, 0.15, 0.9), letter: -1,
    });

    // --- three rings -------------------------------------------------------
    const rings = [
      { v: facts.activities, label: "activity", color: C.brandHi },
      { v: facts.exportedCount, label: "exported", color: C.coral },
      { v: Object.keys(facts.components).reduce((a, k) => a + (facts.components[k]?.length ?? 0), 0), label: "component", color: C.cyan },
    ];
    rings.forEach((r, i) => {
      const cx = M + 110 + i * 300;
      const k = ramp(t, 0.6 + i * 0.25, 1.6 + i * 0.25);
      ring(s, cx, 420, 84, Math.min(1, r.v / 210), { t: k, color: r.color, sw: 12, value: String(r.v), label: r.label });
    });

    // --- permission text ---------------------------------------------------
    const kP = ramp(t, 1.6, 2.6);
    para(
      s, M + 950, 350,
      `${facts.permissions} quyền được khai báo — trong đó ${facts.riskyPermissions} quyền thuộc nhóm nhạy cảm về danh tính,\n` +
        "vị trí, sinh trắc và danh mục ứng dụng.",
      { w: 760, size: T.lead, fill: C.ink2, opacity: kP },
    );

    // --- sensitive permission grid ----------------------------------------
    s.text(M, 596, "QUYỀN NHẠY CẢM ĐANG KHAI BÁO", {
      size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2.6, opacity: ramp(t, 2.2, 2.8),
    });
    SENSITIVE.forEach((p, i) => {
      const k = ramp(t, 2.4 + i * 0.13, 3.0 + i * 0.13, ease.out);
      const cx = M + (i % 2) * 420;
      const cy = 640 + Math.floor(i / 2) * 62;
      const present = facts.components && data.provenance.manifest.permissions.includes(p.key);
      s.circle(cx + 7, cy - 7, 5, present ? C.coral : C.ink3, { opacity: k });
      s.text(cx + 24, cy, p.label, { size: T.small, fill: C.ink2, opacity: k });
      s.text(cx + 380, cy, present ? "KHAI BÁO" : "—", {
        size: T.micro, family: FONT.mono, fill: present ? C.coral : C.ink3, anchor: "end", opacity: k,
      });
    });

    // --- exported components panel ----------------------------------------
    const px = 1080;
    const kE = ramp(t, 3.4, 4.4);
    panel(s, { x: px, y: 620, w: 744, h: 236 }, { label: `exported = true · ${facts.exportedCount}`, accent: C.coral, opacity: kE });
    const names = Object.values(facts.components).flat().filter((c) => c.exported === true).slice(0, 6);
    names.forEach((c, i) => {
      const k = ramp(t, 3.7 + i * 0.1, 4.2 + i * 0.1);
      const y = 690 + i * 28;
      const short = c.name.length > 56 ? c.name.slice(0, 54) + "…" : c.name;
      s.text(px + 26, y, short, { size: T.micro, family: FONT.mono, fill: C.ink2, opacity: k });
    });

    // --- risk statement ----------------------------------------------------
    const kR = ramp(t, 5.2, 6.2);
    s.rect(M, 900, 840, 2, C.line, { opacity: 0.6 * kR });
    const lines = wrap(
      "30 thành phần mở ra cho ứng dụng khác gọi vào. Con số này không phải lỗ hổng — " +
        "nó là hợp đồng: mỗi thành phần exported là một API công khai phải tự phòng thủ.",
      840, T.small,
    );
    lines.forEach((ln, i) => s.text(M, 936 + i * 26, ln, { size: T.small, fill: C.ink3, opacity: kR }));

    chrome(s, { chapter: this.chapter, title: this.title, t, dur, index: "03", source: this.source });
    return s.toSvgString();
  },
};
