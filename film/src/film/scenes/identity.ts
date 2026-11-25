/** ACT III — Identity & surface: manifest census, signing, attack surface. */
import type { SceneMeta } from "../timeline.js";
import { card, circle, glow, line, measure, para, rect, ring, strokeRect } from "../draw.js";
import { text } from "../draw.js";
import { C, F, G, H, W } from "../theme.js";
import { clamp, ease, hash, inv, rgba } from "../util.js";
import { bar, chip, counter, heading, kvRow, panelCard } from "../components.js";

/* ------------------------------------------------------- component census */

export const s_components: SceneMeta = {
  id: "components",
  act: "identity",
  title: "Component census",
  dur: 20,
  evidence: ["02"],
  draw(ctx, t, ev) {
    heading(ctx, "Layer 02 — Manifest", "A city plan, in binary AXML", inv(t, 0.2, 1.4), C.amber, { size: 48 });
    const counts = [
      { label: "activities", value: ev.manifest.counts.activities, color: C.amber },
      { label: "services", value: ev.manifest.counts.services, color: C.cyan },
      { label: "receivers", value: ev.manifest.counts.receivers, color: C.violet },
      { label: "providers", value: ev.manifest.counts.providers, color: C.green },
      { label: "permissions", value: ev.manifest.counts.permissions, color: C.red },
    ];
    const max = Math.max(...counts.map((c) => c.value));
    counts.forEach((c, i) => {
      const p = inv(t, 0.8 + i * 0.22, 2.2 + i * 0.22);
      if (p <= 0) return;
      const y = 360 + i * 70;
      const e = ease.outQuint(p);
      counter(ctx, c.value * e, G.margin, y, 1, { size: 62, color: C.white, font: F.displayBold });
      text(ctx, c.label, G.margin + 176, y - 16, { font: F.monoMed, size: 14, color: c.color, letter: 2, alpha: clamp(p) });
      // bar scaled to the largest series, not to itself
      rect(ctx, G.margin + 176, y - 2, 560, 10, C.line, 0.6, 3);
      rect(ctx, G.margin + 176, y - 2, 560 * (c.value / max) * e, 10, c.color, 0.9, 3);
      text(ctx, `${c.value} declared`, G.margin + 176 + 560, y - 16, {
        font: F.mono, size: 12, color: C.dim2, align: "right", alpha: clamp(p),
      });
    });

    // right: activity density ring
    const rp = inv(t, 2.4, 4.4);
    const cx = 1330;
    const cy = 492;
    if (rp > 0) {
      const rings = [
        { label: "activities", v: ev.manifest.counts.activities, c: C.amber, r: 168 },
        { label: "services", v: ev.manifest.counts.services, c: C.cyan, r: 128 },
        { label: "receivers", v: ev.manifest.counts.receivers, c: C.violet, r: 88 },
        { label: "providers", v: ev.manifest.counts.providers, c: C.green, r: 48 },
      ];
      glow(ctx, cx, cy, 300, C.amber, 0.05);
      rings.forEach((r, i) => {
        const ip = inv(rp, i * 0.12, 0.55 + i * 0.12);
        ring(ctx, cx, cy, r.r, C.line, 1, 0.5);
        ring(ctx, cx, cy, r.r, r.c, 2.5, clamp(ip));
        // outward radial ticks: one per declared component, capped
        const ticks = Math.min(120, r.v);
        for (let d = 0; d < ticks * ease.outQuint(ip); d++) {
          const a = (d / ticks) * Math.PI * 2 - Math.PI / 2;
          const r0 = r.r + 5;
          const r1 = r.r + 5 + 7 * clamp(ip);
          line(ctx, cx + Math.cos(a) * r0, cy + Math.sin(a) * r0, cx + Math.cos(a) * r1, cy + Math.sin(a) * r1, r.c, 1.6, 0.85);
        }
      });
      // legend
      rings.forEach((r, i) => {
        const lp = inv(rp, 0.4 + i * 0.1, 1.0 + i * 0.1);
        const ly = 384 + i * 32;
        ctx.save();
        ctx.globalAlpha = clamp(lp);
        rect(ctx, cx + 236, ly - 11, 9, 9, r.c, 1, 2);
        text(ctx, r.label, cx + 258, ly, { font: F.mono, size: 13, color: C.dim });
        text(ctx, String(r.v), cx + 460, ly, { font: F.monoMed, size: 13, color: C.white, align: "right" });
        ctx.restore();
      });
      text(ctx, String(ev.manifest.counts.activities), cx, cy + 16, { font: F.displayBold, size: 54, color: C.white, align: "center", alpha: clamp(rp) });
      text(ctx, "ACTIVITIES", cx, cy + 44, { font: F.mono, size: 10, color: C.dim2, letter: 2, align: "center", alpha: clamp(rp) });
    }

    // main activity / application identity
    const mp = inv(t, 5.0, 6.4);
    if (mp > 0) {
      const bx = G.margin;
      card(ctx, bx, 700, 1680, 96, { accent: C.amber, alpha: clamp(mp) });
      text(ctx, "LAUNCHER ACTIVITY", bx + 24, 728, { font: F.mono, size: 10, color: C.amber, letter: 2, alpha: clamp(mp) });
      text(ctx, ev.manifest.main_activity, bx + 24, 756, { font: F.monoMed, size: 15, color: C.white, alpha: clamp(mp) });
      text(ctx, "APPLICATION", bx + 24, 780, { font: F.mono, size: 10, color: C.dim2, letter: 2, alpha: clamp(mp) });
      text(ctx, ev.manifest.xml_attributes.name ?? "—", bx + 132, 780, { font: F.mono, size: 11, color: C.dim, alpha: clamp(mp) });
      text(ctx, "MIN SDK", bx + 1500, 728, { font: F.mono, size: 10, color: C.dim2, letter: 2, align: "right", alpha: clamp(mp) });
      text(ctx, ev.manifest.min_sdk, bx + 1656, 766, { font: F.displayBold, size: 30, color: C.white, align: "right", alpha: clamp(mp) });
    }
  },
};

/* --------------------------------------------------------------- signing */

export const s_signing: SceneMeta = {
  id: "signing",
  act: "identity",
  title: "Signing identity",
  dur: 20,
  evidence: ["02"],
  draw(ctx, t, ev) {
    heading(ctx, "Layer 02 — Signature", "One key, valid until 2041", inv(t, 0.2, 1.4), C.amber, { size: 48 });
    const c = ev.manifest.certificate;
    const p = inv(t, 0.8, 2.4);

    // certificate card
    const x = G.margin;
    const y = 360;
    const w = 900;
    ctx.save();
    ctx.globalAlpha = clamp(p);
    rect(ctx, x, y, w, 420, C.panel, 0.85, 10);
    strokeRect(ctx, x, y, w, 420, C.amber, 1, 0.4, 10);
    rect(ctx, x, y, w, 44, C.amber, 0.14, 10);
    text(ctx, "X.509 CERTIFICATE · APK SIGNING BLOCK", x + 24, y + 29, { font: F.monoBold, size: 12, color: C.amber, letter: 2 });
    text(ctx, "v2 + v3  ·  SHA256withRSA  ·  2048-bit", x + w - 24, y + 29, { font: F.mono, size: 12, color: C.dim, align: "right" });
    const rows: [string, string][] = [
      ["subject", c.subject],
      ["issuer", c.issuer],
      ["serial", `0x${c.serial}`],
      ["valid from", c.not_before.slice(0, 10)],
      ["valid to", c.not_after.slice(0, 10)],
      ["key", `${c.pubkey_type} · ${c.pubkey_bits} bit`],
      ["der length", `${c.der_len} bytes`],
    ];
    rows.forEach(([k, v], i) => {
      const rp = inv(t, 1.2 + i * 0.12, 2.0 + i * 0.12);
      kvRow(ctx, x + 24, y + 90 + i * 44, w - 48, k, v, rp, { size: 14 });
    });
    ctx.restore();

    // fingerprint
    const fp = c.sha256;
    const half = Math.ceil(fp.length / 2);
    const fpp = inv(t, 2.6, 4.2);
    if (fpp > 0) {
      const fy = 812;
      text(ctx, "SHA-256 CERTIFICATE FINGERPRINT", x, fy, { font: F.mono, size: 11, color: C.dim2, letter: 2, alpha: clamp(fpp) });
      text(ctx, fp.slice(0, half), x, fy + 32, { font: F.monoMed, size: 17, color: C.white, alpha: clamp(fpp) });
      text(ctx, fp.slice(half), x, fy + 58, { font: F.monoMed, size: 17, color: C.white, alpha: clamp(fpp) });
      text(ctx, `SHA-1 ${c.sha1}`, x, fy + 90, { font: F.mono, size: 12, color: C.dim2, alpha: clamp(fpp) * 0.9 });
    }

    // flags column
    const flags = [
      { k: "android:debuggable", v: "absent", ok: true },
      { k: "android:allowBackup", v: ev.manifest.xml_attributes.allowBackup ?? "?", ok: ev.manifest.xml_attributes.allowBackup === "false" },
      { k: "android:fullBackupContent", v: ev.manifest.xml_attributes.fullBackupContent ?? "?", ok: true },
      { k: "android:dataExtractionRules", v: "declared", ok: true },
      { k: "android:extractNativeLibs", v: ev.manifest.xml_attributes.extractNativeLibs ?? "?", ok: true },
      { k: "android:requestLegacyExternalStorage", v: ev.manifest.xml_attributes.requestLegacyExternalStorage ?? "?", ok: false },
      { k: "android:hardwareAccelerated", v: ev.manifest.xml_attributes.hardwareAccelerated ?? "?", ok: true },
    ];
    const rx = 1140;
    text(ctx, "HARDENING FLAGS", rx, 372, { font: F.mono, size: 11, color: C.dim2, letter: 2, alpha: clamp(inv(t, 3.4, 4.2)) });
    flags.forEach((f, i) => {
      const fp2 = inv(t, 3.6 + i * 0.2, 4.6 + i * 0.2);
      if (fp2 <= 0) return;
      const yy = 412 + i * 52;
      ctx.save();
      ctx.globalAlpha = clamp(fp2);
      circle(ctx, rx + 6, yy - 5, 4, f.ok ? C.green : C.amber, 0.95);
      text(ctx, f.k, rx + 24, yy, { font: F.mono, size: 13, color: C.dim });
      text(ctx, f.v, rx + 640, yy, { font: F.monoMed, size: 13, color: f.ok ? C.white : C.amber, align: "right" });
      line(ctx, rx, yy + 14, rx + 640, yy + 14, C.line, 1, 0.5);
      ctx.restore();
    });

    // target sdk
    const gp = inv(t, 6.0, 7.4);
    if (gp > 0) {
      card(ctx, rx, 790, 640, 210, { accent: C.amber, alpha: clamp(gp) });
      text(ctx, "TARGET SDK", rx + 28, 828, { font: F.mono, size: 11, color: C.dim2, letter: 2, alpha: clamp(gp) });
      text(ctx, ev.manifest.target_sdk, rx + 28, 918, { font: F.displayBold, size: 78, color: C.amber, alpha: clamp(gp) });
      text(ctx, `min SDK ${ev.manifest.min_sdk}`, rx + 158, 918, { font: F.mono, size: 16, color: C.dim, alpha: clamp(gp) });
      text(ctx, "Android 16 target — newest platform behaviour enforced", rx + 28, 962, {
        font: F.mono, size: 12, color: C.dim2, alpha: clamp(gp),
      });
    }
  },
};

/* -------------------------------------------------------- attack surface */

export const s_surface: SceneMeta = {
  id: "surface",
  act: "identity",
  title: "Attack surface",
  dur: 22,
  evidence: ["10"],
  draw(ctx, t, ev) {
    heading(ctx, "Layer 02 — Surface", "22 doors, no guard", inv(t, 0.2, 1.4), C.red, { size: 48 });
    text(
      ctx,
      `${ev.exported.count} exported components · ${ev.exported.unprotected.length} without an android:permission guard`,
      G.margin, 300, { font: F.mono, size: 15, color: C.dim, alpha: clamp(inv(t, 0.8, 2.0)) },
    );

    const comps = ev.exported.components.filter((c) => !c.permission);
    const cols = 3;
    const cw = 520;
    const ch = 116;
    comps.slice(0, 15).forEach((c, i) => {
      const p = inv(t, 1.2 + i * 0.1, 1.9 + i * 0.1);
      if (p <= 0) return;
      const col = i % cols;
      const row = Math.floor(i / cols);
      const x = G.margin + col * (cw + 30);
      const y = 344 + row * (ch + 16);
      const e = ease.outQuint(p);
      ctx.save();
      ctx.globalAlpha = clamp(p);
      card(ctx, x, y, cw, ch, { accent: C.red, alpha: clamp(p) });
      const short = c.name.split(".").slice(-1)[0] ?? c.name;
      text(ctx, short.slice(0, 34), x + 18, y + 34, { font: F.bodySemi, size: 16, color: C.white, maxWidth: cw - 36 });
      text(ctx, c.type.toUpperCase(), x + 18, y + 58, { font: F.mono, size: 10, color: C.red, letter: 1.6 });
      const acts = c.actions.filter((a) => a.startsWith("android.intent.action")).length;
      text(ctx, c.actions.length ? `${c.actions.length} filter${c.actions.length > 1 ? "s" : ""} · ${acts} system` : "no intent filter", x + 18, y + 82, {
        font: F.mono, size: 11, color: C.dim2,
      });
      text(ctx, "permission: none", x + 18, y + 104, { font: F.mono, size: 11, color: C.amber, alpha: 0.9 });
      ctx.restore();
    });

    // side statistics
    const rx = 1700;
    const by = 380;
    const stats: [string, number, string][] = [
      ["activity", ev.exported.by_type.activity ?? 0, C.red],
      ["activity-alias", ev.exported.by_type["activity-alias"] ?? 0, C.amber],
      ["service", ev.exported.by_type.service ?? 0, C.cyan],
      ["receiver", ev.exported.by_type.receiver ?? 0, C.violet],
    ];
    stats.forEach(([k, v, col], i) => {
      const p = inv(t, 5.0 + i * 0.2, 6.0 + i * 0.2);
      text(ctx, String(v), rx + 200, by + i * 56, { font: F.displayBold, size: 32, color: col, align: "right", alpha: clamp(p) });
      text(ctx, k, rx + 200, by + i * 56 + 18, { font: F.mono, size: 10, color: C.dim2, align: "right", alpha: clamp(p) });
    });

    chip(ctx, W - G.margin - 250, 268, "DECLARED, NOT HIDDEN", C.amber, inv(t, 9.4, 10.4), { filled: true });
  },
};

export const ACT3_SCENES = [s_components, s_signing, s_surface];
