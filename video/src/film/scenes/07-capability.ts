/** Chapter 07 — capability census: what the bytecode can reach. */

import { C, FONT, Svg, T, ease, fmtInt, ramp } from "../../theme.ts";
import { chrome, kicker, para } from "../../components.ts";
import { facts } from "../../data.ts";
import type { Scene } from "./index.ts";

const M = 96;

const LABELS: Record<string, string> = {
  obfuscation: "R8 obfuscation (class 1–2 ký tự)",
  dynamic_code: "Nạp mã động / mini-app runtime",
  payments_and_emv: "Thanh toán · EMV · OTP · 3DS",
  ekyc_face: "eKYC · liveness · giấy tờ VN",
  analytics_and_ads: "Đo lường & quảng cáo",
  symmetric_crypto: "Mã hoá đối xứng",
  device_attestation: "Chứng thực thiết bị",
  webview_bridge: "Cầu JavaScript ↔ native",
  biometric_and_keystore: "Sinh trắc & keystore",
  digest_and_kdf: "Băm & dẫn xuất khoá",
  tls_pinning: "TLS pinning",
  asymmetric_crypto: "Mã hoá bất đối xứng",
  hook_detection: "Dò hook / debugger",
  root_emulator_detection: "Dò root / emulator",
};

export const capability: Omit<Scene, "start"> = {
  id: "07-capability",
  chapter: "06 · năng lực",
  title: "14 chủ đề · census chuỗi",
  source: "data/05_security.json",
  duration: 25,
  render(t, dur) {
    const s = new Svg();
    s.backdrop(t, 0.75);

    kicker(s, M, 148, "census năng lực từ 243.786 chuỗi", C.lime, ramp(t, 0, 0.6));
    s.text(M, 236, "Không phải \"app làm gì\". Là \"app có thể chạm tới đâu\".", {
      size: 54, weight: 800, opacity: ramp(t, 0.15, 0.9), letter: -1,
    });
    para(s, M, 288, "Mỗi thanh là số chuỗi duy nhất khớp một chủ đề. Hiện diện chuỗi chứng minh đường mã có thể tới — không chứng minh nó đang chạy.", {
      w: 1500, size: T.body, fill: C.ink2, opacity: ramp(t, 0.5, 1.2),
    });

    const themes = facts.themes;
    const maxCount = themes[0][1].count;
    themes.forEach(([key, v], i) => {
      const k = ramp(t, 1.0 + i * 0.09, 1.8 + i * 0.09, ease.out);
      const y = 392 + i * 37;
      const isRisk = ["hook_detection", "root_emulator_detection", "tls_pinning", "device_attestation"].includes(key);
      const color = isRisk ? C.amber : key === "ekyc_face" || key === "payments_and_emv" ? C.cyan : C.brand;
      s.text(M, y, LABELS[key] ?? key, { size: T.small, fill: C.ink2, opacity: k });
      s.rect(M + 470, y - 14, 620, 10, C.line, { r: 5, opacity: 0.32 * k });
      s.rect(M + 470, y - 14, 620 * (v.count / maxCount) * k, 10, color, { r: 5, opacity: 0.9 });
      s.text(M + 1110, y, fmtInt(v.count), { size: T.small, family: FONT.mono, fill: C.ink, opacity: k });
    });

    // --- SDK census column -------------------------------------------------
    const px = 1290;
    const kS = ramp(t, 2.6, 3.6);
    s.text(px, 358, "SDK THEO DẤU VẾT", { size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2.2, opacity: kS });
    facts.sdkCensus.slice(0, 6).forEach((r, i) => {
      const k = ramp(t, 2.8 + i * 0.12, 3.4 + i * 0.12);
      const y = 404 + i * 74;
      s.text(px, y, r.sdk.length > 34 ? r.sdk.slice(0, 34) + "…" : r.sdk, { size: T.micro, fill: C.ink2, opacity: k });
      s.text(px + 470, y + 20, fmtInt(r.string_hits) + " hits", { size: T.micro, family: FONT.mono, fill: C.ink3, opacity: 0.9 * k, anchor: "end" });
      s.rect(px, y + 28, 530 * k, 1.5, C.lineHi, { opacity: 0.7 });
    });

    // --- limits callout ----------------------------------------------------
    const kL = ramp(t, 5.4, 6.4);
    s.rect(px, 872, 530, 2, C.amber, { opacity: 0.7 * kL });
    para(s, px, 906, "Giới hạn: R8 đổi tên lớp nhưng không đổi chuỗi. Vắng chuỗi không có nghĩa là vắng khả năng.", {
      w: 520, size: T.micro + 1, fill: C.ink3, opacity: kL,
    });

    chrome(s, { chapter: this.chapter, title: this.title, t, dur, index: "07", source: this.source });
    return s.toSvgString();
  },
};
