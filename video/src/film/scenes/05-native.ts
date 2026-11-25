/** Chapter 05 — the native layer: hardening posture and where the code hides. */

import { C, FONT, Svg, T, ease, fmtBytes, ramp } from "../../theme.ts";
import { chrome, codeBlock, kicker, panel, para } from "../../components.ts";
import { facts } from "../../data.ts";
import type { Scene } from "./index.ts";

const M = 96;

export const native: Omit<Scene, "start"> = {
  id: "05-native",
  chapter: "04 · native",
  title: "27 thư viện ELF",
  source: "data/03_native.json",
  duration: 27,
  render(t, dur) {
    const s = new Svg();
    s.backdrop(t, 0.75);

    kicker(s, M, 148, "lib/armeabi-v7a · 27 tệp · 57,5 MB", C.violet, ramp(t, 0, 0.6));
    s.text(M, 236, "Nơi logic trốn khỏi Java.", {
      size: 60, weight: 800, opacity: ramp(t, 0.15, 0.9), letter: -1,
    });
    para(s, M, 288, "85,2% thư viện được hardened đầy đủ. Sáu tệp còn lại bị bóc tên — và chúng không phải SDK bên thứ ba.", {
      w: 1340, size: T.body, fill: C.ink2, opacity: ramp(t, 0.5, 1.2),
    });

    // --- horizontal size bars (top 12) ------------------------------------
    s.text(M, 372, "MƯỜI HAI THƯ VIỆN LỚN NHẤT", {
      size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2.2, opacity: ramp(t, 0.8, 1.4),
    });
    const libs = facts.libsBySize.slice(0, 12);
    const maxSize = libs[0].size;
    libs.forEach((l, i) => {
      const k = ramp(t, 1.0 + i * 0.06, 1.7 + i * 0.06, ease.out);
      const y = 404 + i * 26;
      const isProt = l.family.startsWith("Code-protection");
      const isMl = /ML|Face|PicoDet|Barcode/.test(l.family);
      const color = isProt ? C.coral : isMl ? C.violet : C.brand;
      s.text(M, y + 13, l.short.replace(/^lib|\.so$/g, "").slice(0, 18), {
        size: 13, family: FONT.mono, fill: isProt ? C.coral : C.ink2, opacity: k,
      });
      s.rect(300, y + 2, 470, 10, C.line, { r: 5, opacity: 0.4 * k });
      s.rect(300, y + 2, 470 * (l.size / maxSize) * k, 10, color, { r: 5, opacity: 0.9 });
      s.text(860, y + 12, fmtBytes(l.size), {
        size: 13, family: FONT.mono, fill: C.ink3, anchor: "end", opacity: k,
      });
    });

    // --- hardening posture: five checks -----------------------------------
    const kH = ramp(t, 3.2, 4.2);
    s.text(M, 760, "TƯ THẾ PHÒNG THỦ", { size: T.micro, family: FONT.mono, fill: C.ink3, letter: 2.2, opacity: kH });
    const checks: [string, number, number, string][] = [
      ["PIE (vị trí độc lập)", facts.hard.pie, facts.hard.total, C.cyan],
      ["NX (stack không thực thi)", facts.hard.nx, facts.hard.total, C.cyan],
      ["RELRO đầy đủ + BIND_NOW", facts.hard.relro, facts.hard.total, C.lime],
      ["Stack canary", facts.hard.canary, facts.hard.total, C.lime],
      ["Ký hiệu bị bóc (stripped)", facts.hard.stripped, facts.hard.total, C.ink3],
    ];
    checks.forEach(([label, pass, total, color], i) => {
      const k = ramp(t, 3.4 + i * 0.18, 4.1 + i * 0.18, ease.out);
      const y = 800 + i * 36;
      s.text(M, y, label, { size: T.small, fill: C.ink2, opacity: k });
      s.rect(430, y - 12, 300, 8, C.line, { r: 4, opacity: 0.4 * k });
      s.rect(430, y - 12, 300 * (pass / total) * k, 8, color, { r: 4, opacity: 0.95 });
      s.text(760, y, `${pass}/${total}`, { size: T.small, family: FONT.mono, fill: color, opacity: k });
    });

    // --- name-stripped family ---------------------------------------------
    const px = 1000;
    const kC = ramp(t, 2.2, 3.2);
    panel(s, { x: px, y: 372, w: 824, h: 300 }, { label: "họ thư viện bị bóc tên", accent: C.coral, opacity: kC });
    s.text(px + 26, 452, "6 tệp", { size: 42, weight: 800, fill: C.ink, opacity: kC });
    s.text(px + 152, 450, fmtBytes(facts.protectorBytes), { size: T.body, family: FONT.mono, fill: C.coral, opacity: kC });
    s.text(px + 350, 450, "tên 8 ký tự, ký hiệu rỗng", { size: T.micro, family: FONT.mono, fill: C.ink3, opacity: kC });
    facts.protectorLibs.forEach((l, i) => {
      const k = ramp(t, 2.6 + i * 0.1, 3.2 + i * 0.1);
      const x = px + 26 + (i % 2) * 300;
      const y = 506 + Math.floor(i / 2) * 34;
      s.circle(x + 6, y - 6, 4, C.coral, { opacity: k });
      s.text(x + 20, y, l, { size: T.small, family: FONT.mono, fill: C.ink2, opacity: k });
    });
    para(s, px + 26, 630, "Payload của chúng chứa đầy descriptor JNI và tên lớp Java — dấu vết của một lớp bảo vệ mã, không phải một SDK.", {
      w: 770, size: T.small, fill: C.ink3, opacity: ramp(t, 3.4, 4.2),
    });

    // --- JNI surface -------------------------------------------------------
    const k4 = ramp(t, 4.4, 5.4);
    if (k4 > 0.01) {
      codeBlock(s, { x: px, y: 700, w: 824, h: 236 }, {
        title: "JNI exports · 284 ký hiệu", t: t - 4.4, perLine: 0.05, hi: [0, 1],
        lines: [
          "Java_com_alipay_plus_security_lite_SecurityGuardLiteNative_encrypt",
          "Java_vn_ai_trueid_sdk_utils_SmartOTPHelper_vaultAuthCeremony",
          "Java_com_example_picodet_PicoDetJNI_detect",
          "Java_vn_ai_faceauth_sdk_opencv_VFaceLib_00024Companion_faceAlignment",
          "Java_com_google_android_libraries_barhopper_BarhopperV3_createNative",
          "Java_app_rive_runtime_kotlin_core_Artboard_cppAdvance",
        ],
      });
    }

    // --- closing observation ----------------------------------------------
    const kL = ramp(t, 6.4, 7.4);
    s.text(px, 980, "Bốn ngoại lệ RELRO đều là Crashlytics — SDK của Google, không phải mã ZaloPay.", {
      size: T.micro, family: FONT.mono, fill: C.ink3, opacity: kL,
    });

    chrome(s, { chapter: this.chapter, title: this.title, t, dur, index: "05", source: this.source });
    return s.toSvgString();
  },
};
