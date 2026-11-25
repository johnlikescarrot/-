/**
 * Single source of truth for TIME and NARRATION.
 *
 * Scenes declare an id, an act, a *designed* duration and a draw function.
 * Narration lines are keyed by scene id. The audio stage synthesises one voice
 * track per act, measures it, and writes `film/data/audio_timing.json`; the
 * renderer then derives each scene's real duration from the words it carries,
 * so picture and voice cannot drift.
 */
import type { Ctx } from "./draw.js";
import type { Evidence } from "../research/evidence.js";

export interface RenderCfg {
  width: number;
  height: number;
  fps: number;
  dpi: number;
  narration: boolean;
  timing: Timing | null;
}

export interface Timing {
  /** Per act: measured voice duration in seconds. */
  acts: Record<string, number>;
  /** Per scene id: measured voice duration in seconds (proportional split). */
  scenes: Record<string, number>;
}

export type ActId = "overture" | "artifact" | "identity" | "code" | "native" | "trust" | "verdict" | "coda";

export interface SceneMeta {
  id: string;
  act: ActId;
  title: string;
  /** Designed duration in seconds (used when a scene carries no narration). */
  dur: number;
  /** Evidence ids cited by this scene (HUD provenance). */
  evidence: string[];
  draw: (ctx: Ctx, t: number, ev: Evidence, cfg: RenderCfg) => void;
  post?: (ctx: Ctx, t: number) => void;
}

export const ACT_ORDER: ActId[] = ["overture", "artifact", "identity", "code", "native", "trust", "verdict", "coda"];

export const ACTS: Record<ActId, { label: string; roman: string; color: string; title: string }> = {
  overture: { label: "OVERTURE", roman: "I", color: "#4dd0ff", title: "The archive" },
  artifact: { label: "THE ARTIFACT", roman: "II", color: "#00e5a0", title: "Container anatomy" },
  identity: { label: "IDENTITY & SURFACE", roman: "III", color: "#ffb545", title: "What Android is told" },
  code: { label: "THE CODE MASS", roman: "IV", color: "#a78bfa", title: "60,671 classes" },
  native: { label: "NATIVE HARDENING", roman: "V", color: "#ff7ab6", title: "The stripped frontier" },
  trust: { label: "TRUST BOUNDARIES", roman: "VI", color: "#ff5f6d", title: "TLS, pinning, storage" },
  verdict: { label: "THE VERDICT", roman: "VII", color: "#00e5a0", title: "Trojan or product" },
  coda: { label: "CODA", roman: "VIII", color: "#98a6b8", title: "What we learned" },
};

export interface NarrLine {
  /** Scene id this line belongs to. */
  scene: string;
  /** Exact words handed to the speech synthesiser (English). */
  vo: string;
  /** Burned-in English subtitle. */
  en: string;
  /** Burned-in Vietnamese subtitle. */
  vi: string;
}

/**
 * The voice of the film. One line per scene, written to be spoken at a
 * measured, documentary pace (~150 wpm). Punctuation is deliberate: commas and
 * full stops are the only timing control a synthesiser respects.
 */
export const NARRATION: NarrLine[] = [
  // ── ACT I ────────────────────────────────────────────────────────────────
  {
    scene: "title",
    vo: "Eleven seventeen point zero. One hundred and five megabytes. One wallet, and the entire machinery of a super-app, compressed into a single archive.",
    en: "Zalopay 11.17.0 — 105 MB of wallet, super-app and infrastructure.",
    vi: "Zalopay 11.17.0 — 105 MB ví điện tử, siêu ứng dụng và hạ tầng.",
  },
  {
    scene: "layers",
    vo: "This film is an autopsy, performed without touching a single live account. Six layers: container, manifest, bytecode, native code, trust boundaries, and behaviour.",
    en: "Six layers: container, manifest, bytecode, native, trust, behaviour.",
    vi: "Sáu lớp: container, manifest, bytecode, native, tin cậy, hành vi.",
  },
  {
    scene: "question",
    vo: "The question is simple, and almost never answered with evidence. What is actually inside a national payment app?",
    en: "What is actually inside a national payment app?",
    vi: "Bên trong ứng dụng thanh toán quốc gia thực sự có gì?",
  },
  // ── ACT II ───────────────────────────────────────────────────────────────
  {
    scene: "unpack",
    vo: "The downloaded file is a zip containing a single X A P K. That archive holds five A P Ks: one base, one for the A R M library, one high density artwork set, and two languages.",
    en: "ZIP → XAPK → 5 APKs: base, armeabi-v7a, xhdpi, English, Vietnamese.",
    vi: "ZIP → XAPK → 5 APK: base, armeabi-v7a, xhdpi, tiếng Anh, tiếng Việt.",
  },
  {
    scene: "mass",
    vo: "Ninety eight point eight megabytes compressed. Fifty seven megabytes of native code. Thirty megabytes of assets. And one point eight megabytes of compiled string resources.",
    en: "98.8 MB archive · 57.5 MB native · 30 MB assets · 1.8 MB resources.arsc",
    vi: "98.8 MB nén · 57.5 MB native · 30 MB assets · 1.8 MB resources.arsc",
  },
  {
    scene: "files",
    vo: "Six thousand one hundred and ninety two files in the base A P K alone. This stopped being a mobile app some releases ago. It is closer to an operating system with a payments licence.",
    en: "6,192 files in the base APK alone — an operating system, not an app.",
    vi: "Chỉ APK gốc đã có 6.192 tệp — gần như một hệ điều hành.",
  },
  // ── ACT III ──────────────────────────────────────────────────────────────
  {
    scene: "components",
    vo: "The manifest reads like a city plan. Two hundred activities. Thirty four services. Twenty two broadcast receivers. Nine content providers. Forty nine permissions.",
    en: "200 activities · 34 services · 22 receivers · 9 providers · 49 permissions",
    vi: "200 activity · 34 service · 22 receiver · 9 provider · 49 quyền",
  },
  {
    scene: "signing",
    vo: "It targets S D K thirty six. It is not debuggable. Backup is switched off. And it is signed with one self-signed R S A key, issued in twenty sixteen, valid until twenty forty one.",
    en: "targetSdk 36 · not debuggable · backup off · v2+v3, RSA-2048",
    vi: "targetSdk 36 · không debug · tắt backup · ký v2+v3, RSA-2048",
  },
  {
    scene: "surface",
    vo: "Twenty two components are exported to the whole device without a permission guard. Fourteen activities answer intents. That is the surface every other app on the phone can see.",
    en: "22 exported components answer without a permission guard.",
    vi: "22 thành phần exported không có quyền bảo vệ.",
  },
  // ── ACT IV ───────────────────────────────────────────────────────────────
  {
    scene: "dexmass",
    vo: "Nineteen D E X files. Sixty thousand six hundred and seventy one classes. Two hundred and sixty four thousand methods. Three hundred and seventy four thousand string literals.",
    en: "19 DEX · 60,671 classes · 264,525 methods · 374,276 strings",
    vi: "19 DEX · 60.671 lớp · 264.525 phương thức · 374.276 chuỗi",
  },
  {
    scene: "obfuscation",
    vo: "Seventy two and a half per cent of those classes have one or two letter names. That is legacy ProGuard collapsing, not a proprietary virtual machine, which is exactly why the readable remainder is so valuable.",
    en: "72.5% of classes are 1–2 letters: ProGuard collapsing, not a custom VM.",
    vi: "72,5% lớp chỉ 1–2 ký tự: ProGuard, không phải VM riêng.",
  },
  {
    scene: "firstparty",
    vo: "Because underneath, the architecture is loud. Miniapps, e K Y C, cards, cash in, bank binding, pin lifecycle, fraud scoring. One thousand one hundred and thirty eight first party classes, named in plain English.",
    en: "1,138 first-party classes, named in plain English: the real architecture.",
    vi: "1.138 lớp nội bộ, tên rõ ràng: kiến trúc thật.",
  },
  {
    scene: "host",
    vo: "And the largest single subsystem is not written by Zalopay at all. Four thousand seven hundred and seventy seven classes belong to Alipay's miniapp runtime. The wallet is a host platform.",
    en: "4,777 classes are Alipay's ARiver/GRiver runtime — the wallet is a host.",
    vi: "4.777 lớp là runtime miniapp của Alipay — ví chỉ là máy chủ.",
  },
  // ── ACT V ────────────────────────────────────────────────────────────────
  {
    scene: "elfs",
    vo: "Now the frontier. Twenty seven native libraries, fifty seven megabytes, and every single one of them stripped. The obvious symbols have simply been removed.",
    en: "27 native libraries · 57 MB · all stripped of a symbol table.",
    vi: "27 thư viện native · 57 MB · đều bị lược bỏ bảng ký hiệu.",
  },
  {
    scene: "obfuscated",
    vo: "Then the names stop being names. Twelve and a half megabytes of tai b m c f. Six and a half of f g c p k j v q e. Read-only data entropy above seven point two: packed, encrypted, or both.",
    en: "12.6 MB libtaibmcf · 6.5 MB libfgcpkjvqe · .rodata entropy 7.28",
    vi: "12,6 MB libtaibmcf · 6,5 MB libfgcpkjvqe · entropy .rodata 7,28",
  },
  {
    scene: "crypto",
    vo: "And beneath them, the parts that quietly protect the money. S Q L Cipher for the database. SecurityGuardLite for request signing. Hardware biometrics, anchored in the Android keystore.",
    en: "SQLCipher · SecurityGuardLite signing · hardware-backed biometrics",
    vi: "SQLCipher · ký bằng SecurityGuardLite · sinh trắc học phần cứng",
  },
  // ── ACT VI ───────────────────────────────────────────────────────────────
  {
    scene: "nsc",
    vo: "Here is the finding that matters most. The application-wide network security configuration permits cleartext traffic, and its debug override trusts certificate authorities installed by the user.",
    en: "The app-wide network security config permits cleartext traffic.",
    vi: "Cấu hình bảo mật mạng toàn ứng dụng cho phép lưu lượng không mã hóa.",
  },
  {
    scene: "pinning",
    vo: "No pin set is declared in that file. So every pin, where one exists, lives in code and in native libraries. Twenty five classes touch certificate pinning, and native interceptors sit in front of the transport.",
    en: "No <pin-set>: pinning lives in code and native, not in configuration.",
    vi: "Không có <pin-set>: pinning nằm trong mã và native, không ở cấu hình.",
  },
  {
    scene: "capabilities",
    vo: "And yet, no trojan primitives. No accessibility service. No device administrator receiver. No notification listener. And no S M S send path anywhere in sixty thousand classes.",
    en: "No AccessibilityService · no device-admin · no NotificationListener · no SMS",
    vi: "Không AccessibilityService · không device-admin · không đọc thông báo · không gửi SMS",
  },
  // ── ACT VII ──────────────────────────────────────────────────────────────
  {
    scene: "verdict",
    vo: "So the verdict is not that this application is safe forever. It is that this particular artifact is exactly what it claims to be, down to the byte.",
    en: "The artifact is what it claims to be — verifiably, down to the byte.",
    vi: "Gói cài đặt đúng là thứ nó tuyên bố — kiểm chứng được đến từng byte.",
  },
  {
    scene: "risks",
    vo: "The real risk is structural. Exported components without permission guards. A network configuration that trusts the user store. Those are the levers, not a hidden payload.",
    en: "Real risk is structural: unguarded exports and a permissive config.",
    vi: "Rủi ro thật mang tính cấu trúc: export thiếu bảo vệ và cấu hình dễ dãi.",
  },
  {
    scene: "method",
    vo: "Which is why every number in this film is machine-derived, and shipped with the film. Verify the signature. Read the configuration. Count the exports. That is the whole method.",
    en: "Verify the signature. Read the config. Count the exports.",
    vi: "Kiểm chữ ký. Đọc cấu hình. Đếm thành phần export.",
  },
  // ── ACT VIII ─────────────────────────────────────────────────────────────
  {
    scene: "coda",
    vo: "Six layers. Fifty seven megabytes of native code. Sixty thousand classes. One conclusion.",
    en: "Six layers. One conclusion.",
    vi: "Sáu lớp. Một kết luận.",
  },
  {
    scene: "end",
    vo: "This is what a super-app wallet looks like from the inside. And now, the instruments are in your hands.",
    en: "This is a super-app wallet from the inside. Now the instruments are yours.",
    vi: "Đây là ví siêu ứng dụng từ bên trong. Giờ công cụ nằm trong tay bạn.",
  },
];

export const ACT_OF_SCENE = (id: string): string | null => {
  const l = NARRATION.find((n) => n.scene === id);
  return l ? l.scene : null;
};

export function linesFor(sceneId: string): NarrLine[] {
  return NARRATION.filter((l) => l.scene === sceneId);
}

export function narrationTextForAct(act: ActId, scenes: SceneMeta[]): { scene: string; vo: string }[] {
  const ids = scenes.filter((s) => s.act === act).map((s) => s.id);
  return NARRATION.filter((n) => ids.includes(n.scene)).map((n) => ({ scene: n.scene, vo: n.vo }));
}

/** Deterministic char-weighted split used by the audio stage and the renderer. */
export function splitByChars(lines: { vo: string }[], total: number): number[] {
  const sum = lines.reduce((a, l) => a + Math.max(1, l.vo.length), 0);
  return lines.map((l) => (Math.max(1, l.vo.length) / sum) * total);
}
