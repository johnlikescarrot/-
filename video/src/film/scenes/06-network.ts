/** Chapter 06 — network surface: who the app talks to, and how. */

import { C, FONT, Svg, T, ease, fmtInt, ramp } from "../../theme.ts";
import { chrome, codeBlock, kicker, panel, para, ring } from "../../components.ts";
import { facts } from "../../data.ts";
import { data } from "../../data.ts";
import type { Scene } from "./index.ts";

const M = 96;

export const network: Omit<Scene, "start"> = {
  id: "06-network",
  chapter: "05 · mạng",
  title: "1.370 URL · 84,5% first-party",
  source: "data/04_secrets.json",
  duration: 25,
  render(t, dur) {
    const s = new Svg();
    s.backdrop(t, 0.75);

    kicker(s, M, 148, "bề mặt mạng", C.cyan, ramp(t, 0, 0.6));
    s.text(M, 236, "Chiếc ví nói chuyện với rất ít người lạ.", {
      size: 58, weight: 800, opacity: ramp(t, 0.15, 0.9), letter: -1,
    });
    para(s, M, 290, `${fmtInt(facts.urls.unique)} URL duy nhất trong ${fmtInt(facts.urls.total)} lần xuất hiện. ` +
      "Phần lớn host thuộc chính ZaloPay — phần còn lại là hạ tầng thanh toán và SDK đo lường.", {
      w: 1180, size: T.body, fill: C.ink2, opacity: ramp(t, 0.5, 1.2),
    });

    // --- owner split ring --------------------------------------------------
    const owners = facts.hostOwners.slice(0, 4);
    const totalHits = owners.reduce((a, o) => a + o[1], 0);
    const kR = ramp(t, 0.7, 1.9);
    ring(s, 200, 470, 116, owners[0][1] / totalHits, {
      t: kR, color: C.brandHi, sw: 16,
      value: `${facts.firstPartyShare.toFixed(1).replace(".", ",")}%`, label: "first-party",
    });
    s.text(200, 620, "host hits", { size: T.micro, family: FONT.mono, fill: C.ink3, anchor: "middle", opacity: kR });

    // --- top hosts ---------------------------------------------------------
    const hosts = facts.topHosts.slice(0, 7);
    const maxHits = hosts[0].hits;
    hosts.forEach((h, i) => {
      const k = ramp(t, 1.2 + i * 0.16, 1.9 + i * 0.16, ease.out);
      const y = 400 + i * 46;
      const firstParty = h.owner.includes("ZaloPay");
      s.text(M + 330, y, h.host, {
        size: T.small, family: FONT.mono, fill: firstParty ? C.ink : C.ink2, opacity: k,
      });
      s.rect(M + 800, y - 14, 430, 10, C.line, { r: 5, opacity: 0.35 * k });
      s.rect(M + 800, y - 14, 430 * (h.hits / maxHits) * k, 10, firstParty ? C.brand : C.ink3, { r: 5, opacity: 0.9 });
      s.text(M + 1260, y, fmtInt(h.hits), { size: T.small, family: FONT.mono, fill: C.ink3, opacity: k });
    });

    // --- owner legend ------------------------------------------------------
    owners.forEach(([name, hits], i) => {
      const k = ramp(t, 2.4 + i * 0.1, 3.0 + i * 0.1);
      const y = 730 + i * 30;
      s.circle(M + 340, y - 6, 5, i === 0 ? C.brandHi : i === 1 ? C.cyan : i === 2 ? C.violet : C.ink3, { opacity: k });
      s.text(M + 358, y, name, { size: T.small, fill: C.ink2, opacity: k });
      s.text(M + 700, y, fmtInt(hits), { size: T.small, family: FONT.mono, fill: C.ink3, anchor: "end", opacity: k });
    });
    const otherHosts = data.secrets.hosts.filter((h) => !h.owner.includes("ZaloPay")).slice(0, 5);
    s.text(M + 330, 866, "không phải ZaloPay: " + otherHosts.map((h) => h.host).join(" · ").slice(0, 104), {
      size: T.micro, family: FONT.mono, fill: C.ink3, opacity: ramp(t, 3.2, 3.8),
    });

    // --- the cleartext finding --------------------------------------------
    const px = 1430;
    const kC = ramp(t, 3.0, 4.0);
    panel(s, { x: px, y: 360, w: 394, h: 250 }, { label: "network security config", accent: C.amber, opacity: kC });
    s.text(px + 24, 428, facts.nscCleartext ? "cleartextTraffic" : "—", { size: 26, weight: 700, fill: C.ink, opacity: kC });
    s.text(px + 24, 460, facts.nscCleartext ? "Permitted = true" : "", { size: 26, weight: 700, fill: C.amber, opacity: kC });
    para(s, px + 24, 502, "base-config cho phép HTTP rõ ràng. Không phải lỗ hổng tự thân, nhưng là bề mặt cần chứng minh là không dùng.", {
      w: 346, size: T.micro + 2, fill: C.ink3, opacity: kC,
    });
    s.text(px + 24, 596, `pin sha256 khai báo: ${facts.pins}`, { size: T.micro, family: FONT.mono, fill: C.ink3, opacity: kC });

    // --- scheme mix --------------------------------------------------------
    const kS = ramp(t, 4.2, 5.2);
    panel(s, { x: px, y: 636, w: 394, h: 180 }, { label: "sơ đồ URL", accent: C.brandHi, opacity: kS });
    const schemes = Object.entries(facts.urls.schemes);
    schemes.forEach(([sc, n], i) => {
      const k = ramp(t, 4.4 + i * 0.12, 5.0 + i * 0.12);
      const y = 700 + i * 32;
      s.text(px + 24, y, sc, { size: T.small, family: FONT.mono, fill: sc === "http" ? C.amber : C.ink2, opacity: k });
      s.rect(px + 120, y - 12, 150 * (n / Math.max(...schemes.map((x) => x[1]))) * k, 8, sc === "http" ? C.amber : C.cyan, { r: 4 });
      s.text(px + 370, y, fmtInt(n), { size: T.small, family: FONT.mono, fill: C.ink3, anchor: "end", opacity: k });
    });

    // --- closing line ------------------------------------------------------
    const kL = ramp(t, 5.6, 6.6);
    s.text(M, 906, "Không có API key, không có khoá riêng tư trong chuỗi DEX. Cái tìm thấy là 3 public key PEM — dùng để xác thực, không phải để ký.", {
      size: T.small, fill: C.ink3, opacity: kL,
    });

    chrome(s, { chapter: this.chapter, title: this.title, t, dur, index: "06", source: this.source });
    return s.toSvgString();
  },
};
