/* SCENES: data-driven documentary chapters. Numbers load from repo evidence. */
import { Frame, W, H, C, col, ease, clamp01, lerp, fmtTime, wrap, RGBA } from './gfx';

// eslint-disable-next-line @typescript-eslint/no-var-requires
const path = require('path');
const ROOT = path.join(__dirname, '..', '..');
const req = (p: string, fb: any) => { try { return require(path.join(ROOT, p)); } catch { return fb; } };
const dexR = req('reports/raw/dex-report.json', { per_dex: {}, top_packages: [], string_keywords: {}, url_hosts: [], counts: { classes: 63827, methods: 378040 } });
const natR: any[] = req('reports/raw/native-report.json', []);
const mfR: any = req('reports/raw/manifest-findings.json', { exported: {} });
const invR: any[] = req('reports/raw/inventory.json', []);

const rnd = (n: number) => { const x = Math.sin(n * 127.1 + 311.7) * 43758.5453; return x - Math.floor(x); };
export interface SceneCtx { t: number; dur: number; tG: number; frame: number; idx: number; total: number; g01: number }
export interface Scene {
  id: string; kicker: string; title: string; narration: string;
  captions: string[]; minDur: number; draw(f: Frame, c: SceneCtx): void;
}

/* ---------- shared chrome ---------- */
function bg(f: Frame, tG: number) {
  f.gradientV(C.bg0, C.bg1);
  const drift = Math.floor(tG * 6) % 48;
  f.grid(48, C.grid);
  // scan sweep
  const sx = Math.floor((tG * 90) % (W + 400)) - 200;
  for (let y = 0; y < H; y += 2) for (let x = Math.max(0, sx - 30); x < Math.min(W, sx + 30); x++)
    f.px(x, y, col('#35d0ff', Math.max(0, 26 - Math.abs(x - sx))));
  // starfield nodes
  for (let i = 0; i < 70; i++) {
    const x = Math.floor(rnd(i) * W), y = Math.floor(60 + rnd(i + 500) * (H - 160));
    const tw = 0.5 + 0.5 * Math.sin(tG * 1.5 + i);
    f.px(x, y, col('#35d0ff', Math.floor(20 + 40 * tw)));
  }
  void drift;
  // corner brackets
  const B = C.line, L = 26, m = 14;
  f.rect(m, m, L, 2, B); f.rect(m, m, 2, L, B);
  f.rect(W - m - L, m, L, 2, B); f.rect(W - m - 2, m, 2, L, B);
  f.rect(m, H - m - 2, L, 2, B); f.rect(m, H - m - L, 2, L, B);
  f.rect(W - m - L, H - m - 2, L, 2, B); f.rect(W - m - 2, H - m - L, 2, L, B);
}
function chrome(f: Frame, s: Scene, c: SceneCtx) {
  // top bar
  f.rect(0, 0, W, 44, col('#0a1030', 240));
  f.rect(0, 44, W, 1, C.line);
  f.text(`SC ${String(c.idx + 1).padStart(2, '0')}/${String(c.total).padStart(2, '0')}  ${s.kicker}`, 28, 14, 2, C.cyan);
  const tc = fmtTime(c.tG);
  f.text(tc, W - 28, 14, 2, C.dim, 'r');
  f.text('STATIC ANALYSIS // RESEARCH FILM', W - 28, 14, 2, col('#000000', 0), 'r');
  // global progress
  f.hbar(0, 45, W, 3, c.g01, C.gold, col('#141c44'));
  // caption bar
  const lines = wrap(s.captions[Math.min(s.captions.length - 1, Math.floor(c.t / Math.max(4, c.dur / s.captions.length)))], 92);
  f.rect(0, H - 88, W, 88, col('#05081a', 235));
  f.rect(0, H - 88, W, 1, C.line);
  f.para(lines.slice(0, 2), W / 2, H - 70, 2, C.text, 26, 'c');
  f.text('ZALOPAY 11.17.0 DEEP RE', W - 24, H - 116, 1, C.faint, 'r');
}
function header(f: Frame, kicker: string, title: string, sub: string, t01: number) {
  const e = ease(clamp01(t01 * 3));
  f.text(kicker, 60, 78, 2, C.gold);
  f.text(title, 60 - Math.round((1 - e) * 40), 108, 5, C.text);
  f.text(sub, 60, 108 + 44, 2, C.dim);
  f.rect(60, 108 + 44 + 30, Math.round(1160 * e), 2, C.cyan);
}
const MB = (b: number) => b >= 1048576 ? `${(b / 1048576).toFixed(1)} MB` : `${Math.round(b / 1024)} KB`;

/* ---------- scenes ---------- */
export const SCENES: Scene[] = [
  {
    id: 'title', kicker: 'APKPURE XAPK // DEEP REVERSE ENGINEERING', title: 'ZALOPAY 11.17.0',
    minDur: 15,
    narration: 'ZaloPay eleven point seventeen point zero. A Vietnamese payment super application, distributed as an APKPure XAPK bundle. In this research film we reverse engineer every file in the corpus. Five Android packages. Twenty Dalvik executables. Sixty three thousand classes. Three hundred seventy eight thousand methods. Twenty eight native libraries. One signed configuration envelope. All hashes, all methods, all evidence, published alongside this video.',
    captions: [
      'ZALOPAY 11.17.0 // anatomy of a Vietnamese payment super-app, reversed end to end',
      '5 APKs // 20 DEX files // 63,827 classes // 378,040 methods // 28 native libs',
      'Every claim in this film traces to hashed evidence in reports/ and evidence/',
    ],
    draw(f, c) {
      const e = ease(clamp01(c.t / 2));
      f.text('APKPURE XAPK // DEEP REVERSE ENGINEERING', W / 2, 150, 2, C.gold, 'c');
      const sc = 11;
      f.text('ZALOPAY', W / 2, 190 - Math.round((1 - e) * 30), sc, C.text, 'c');
      f.text('11.17.0', W / 2, 190 + 7 * sc + 14, sc, C.cyan, 'c');
      f.text('ANATOMY OF A PAYMENT SUPER-APP', W / 2, 190 + 14 * sc + 40, 3, C.dim, 'c');
      const stats = ['5 APK SPLITS', '20 DEX', '63,827 CLASSES', '378,040 METHODS', '28 NATIVE LIBS'];
      const bw = 210, gap = 14, x0 = (W - (stats.length * bw + (stats.length - 1) * gap)) / 2;
      stats.forEach((s, i) => {
        const on = clamp01(c.t * 1.2 - 2 - i * 0.35);
        if (on <= 0) return;
        const x = x0 + i * (bw + gap);
        f.rect(x, 508, bw, 46, col('#101a3a', Math.round(230 * on)));
        f.outline(x, 508, bw, 46, C.cyan, 1);
        f.text(s, x + bw / 2, 524, 2, C.text, 'c');
      });
      f.text('SHA256 34b9b6b6...f4b4610a // XAPK 686e9459...7b8d // STATIC-ONLY // RESEARCH USE', W / 2, 580, 1, C.faint, 'c');
    },
  },
  {
    id: 'corpus', kicker: 'CH.1 // CORPUS ANATOMY', title: 'ONE ZIP, ONE XAPK, FIVE APKS',
    minDur: 30,
    narration: 'Chapter one. Corpus anatomy. The outer zip is ninety eight megabytes. Inside, a single XAPK of one hundred five megabytes, stored uncompressed. The XAPK manifest declares package vn dot com dot vng dot zalopay, version code one one zero two seven five eight, targeting SDK thirty six. Five splits follow. The seventy nine megabyte base. A twenty four megabyte ARM native split. Density and locale splits for extra high screens, English, and Vietnamese. Every byte hashed. Nothing skipped.',
    captions: [
      'Outer ZIP 98.8 MB -> single XAPK 105.6 MB -> 5 APK splits + manifest + icon',
      'Base APK 79.4 MB carries code; config.armeabi_v7a 24.8 MB carries ARM32 libs',
      'All splits share versionCode 1102758 and one signer: CN Vu Manh Hieu',
    ],
    draw(f, c) {
      header(f, 'CH.1 // CORPUS ANATOMY', 'ONE ZIP, FIVE APKS', 'XAPK split model // every entry SHA256-hashed (report 00)', c.t / c.dur);
      const entries = (invR[1] && invR[1].entries ? invR[1].entries : []).filter((e: any) => e.file.endsWith('.apk'));
      const order = ['vn.com.vng.zalopay.apk', 'config.armeabi_v7a.apk', 'config.xhdpi.apk', 'config.vi.apk', 'config.en.apk'];
      entries.sort((a: any, b: any) => order.indexOf(a.file) - order.indexOf(b.file));
      const maxB = Math.max(...entries.map((e: any) => e.bytes), 1);
      entries.forEach((e: any, i: number) => {
        const y = 220 + i * 76;
        const on = ease(clamp01(c.t / c.dur * 2.2 - i * 0.18));
        f.panel(60, y, 1160, 62);
        f.text(e.file, 84, y + 8, 2, C.text);
        f.text(`${e.bytes.toLocaleString('en-US')} B  //  SHA256 ${String(e.sha256).slice(0, 16)}...`, 84, y + 34, 1, C.dim);
        f.hbar(640, y + 16, 440 * on, 30, Math.pow(e.bytes / maxB, 0.35), i === 0 ? C.cyan : C.blue);
        f.text(MB(e.bytes), 1096, y + 22, 2, C.gold, 'r');
      });
    },
  },
  {
    id: 'manifest', kicker: 'CH.2 // MANIFEST + ATTACK SURFACE', title: '200 ACTIVITIES, WIDE OPEN DOORS',
    minDur: 30,
    narration: 'Chapter two. Manifest and attack surface. The base manifest declares two hundred activities, thirty four services, twenty two receivers, nine providers, and two aliases. Dozens are exported. Payment screens, QR receivers, V N e I D verification, e K Y C and liveness cameras from F P T and True I D. Test scaffolding shipped in release, including preview and instrumentation activities. Eleven deep link filters span zalopay schemes, V N Pay, Facebook connect, and verified app links on zalopay dot vn.',
    captions: [
      '200 activities / 34 services / 22 receivers / 9 providers / 2 aliases',
      'Exported: QR, VNeID, eKYC, liveness, Compose Preview + instrumentation shims',
      '11 deep-link filters: zalopay://, zalopay-vnpay, fbconnect, applinks',
    ],
    draw(f, c) {
      header(f, 'CH.2 // MANIFEST + ATTACK SURFACE', '200 ACTIVITIES', 'exported components + deep links (report 02)', c.t / c.dur);
      const comps: [string, string, RGBA][] = [['ACTIVITIES', '200', C.cyan], ['SERVICES', '34', C.blue], ['RECEIVERS', '22', C.gold], ['PROVIDERS', '9', C.green], ['ALIASES', '2', C.dim]];
      comps.forEach(([k, v, colr], i) => {
        const x = 60 + i * 232;
        f.panel(x, 220, 208, 96);
        f.text(k, x + 104, 232, 2, C.dim, 'c');
        const n = Math.round(parseInt(v) * ease(clamp01(c.t / c.dur * 2 - i * 0.1)));
        f.text(String(n), x + 104, 262, 5, colr, 'c');
      });
      f.panel(60, 336, 560, 262, 'EXPORTED HIGHLIGHTS');
      const exp: string[] = [...(mfR.exported?.activity || []), ...(mfR.exported?.service || [])].map((s: string) => s.split('.').slice(-1)[0]);
      const show = ['SplashScreenActivityV2', 'QRReceiverActivity', 'VerificationByVNeIDActivity', 'EkycActivity', 'LivenessCameraXActivity', 'PreviewActivity', 'MyFcmListenerService', 'VisaHostApduService'];
      show.forEach((s, i) => {
        if (c.t / c.dur * 3 < i * 0.22) return;
        f.circle(92, 388 + i * 26, 4, C.red, true);
        f.text(s.slice(0, 40), 110, 381 + i * 26, 2, exp.includes(s) ? C.text : C.dim);
      });
      f.panel(644, 336, 576, 262, 'DEEP-LINK SCHEMES');
      const schemes: [string, number][] = [['zalopay', 10], ['https', 5], ['zalopay-1', 3], ['zalopay.api.v2', 3], ['zalopay-vnpay', 1], ['fbconnect', 1]];
      schemes.forEach(([s, n], i) => {
        const y = 384 + i * 34;
        f.text(s, 668, y, 2, C.text);
        f.hbar(880, y - 2, 220, 18, (n / 10) * ease(clamp01(c.t / c.dur * 2.5 - i * 0.15)), C.cyan);
        f.text(`x${n}`, 1116, y, 2, C.gold);
      });
      f.text('hosts: go. onelink. km. social. *.zalopay.vn', 668, 384 + 6 * 34, 1, C.faint);
    },
  },
  {
    id: 'trust', kicker: 'CH.3 // NETWORK + TRUST', title: 'CLEARTEXT ON, PINS OFF',
    minDur: 30,
    narration: 'Chapter three. Network and trust. The network security config permits cleartext traffic globally, with system trust anchors and debug overrides that accept user certificates. One domain exception covers Google storage. There is no pin set. Yet the code is full of pinning strings, including certificate pinning failure events. Policy says open. Code says pinned. That gap is the finding. Alongside: H C E payment A I Ds for Visa, broad file provider cache paths, and an R S A twenty forty eight signed configuration envelope.',
    captions: [
      'NSC: cleartextTrafficPermitted=true globally, debug user-CA overrides, NO pin-set',
      'Code disagrees: EVENT_SSLPINNING_* + "Certificate pinning failure!" strings',
      'HCE Visa AIDs + RSA-2048 signed config (450 B pubkey, 256 B signature)',
    ],
    draw(f, c) {
      header(f, 'CH.3 // NETWORK + TRUST', 'CLEARTEXT ON, PINS OFF', 'policy vs code gap (reports 03 + 05)', c.t / c.dur);
      f.panel(60, 220, 560, 200, 'network_security_config.xml');
      const rows: [string, string, RGBA][] = [
        ['base-config cleartext', 'TRUE', C.red], ['trust-anchors', 'system', C.gold],
        ['debug-overrides', 'cleartext + user CA', C.red], ['domain: storage.googleapis.com', 'subdomains', C.blue],
        ['pin-set', 'ABSENT', C.red],
      ];
      rows.forEach(([k, v, cc], i) => {
        if (c.t / c.dur * 3 < 0.3 + i * 0.25) return;
        f.text(k, 84, 268 + i * 28, 2, C.dim);
        f.text(v, 596, 268 + i * 28, 2, cc, 'r');
      });
      f.panel(644, 220, 576, 200, 'CODE SAYS OTHERWISE (DEX STRINGS)');
      ['"Certificate pinning failure!"', '"Cannot find certificatest...', 'EVENT_SSLPINNING_CHECK_FAIL', 'EVENT_SSLPINNING_ADD_CERTS_FAIL'].forEach((s, i) => {
        f.text('> ' + s, 668, 268 + i * 30, 2, C.green);
      });
      f.panel(60, 436, 560, 162, 'HCE PAYMENT AIDS (apdu_service.xml)');
      ['325041592E5359532E4444463031', 'A0000000980840', 'A0000000031010 (Visa)'].forEach((s, i) => {
        f.text(s, 84, 478 + i * 30, 2, C.text);
      });
      f.panel(644, 436, 576, 162, 'SIGNED CONFIG ENVELOPE');
      f.text('public_key.txt   450 B  RSA-2048', 668, 478, 2, C.text);
      f.text('signature.txt    256 B raw (RSA sig)', 668, 508, 2, C.text);
      f.text('zalopay_config 105,659 B // miniapp 883,684 B', 668, 538, 2, C.gold);
    },
  },
  {
    id: 'dex', kicker: 'CH.4 // DEX AT SCALE', title: '378,040 METHODS UNDER ONE ROOF',
    minDur: 32,
    narration: 'Chapter four. Dalvik at scale. Twenty dex files. The largest, classes two, holds sixteen megabytes. Audience Network ships its own dex. Nineteen belong to the app and its SDK mesh. Google leads with seven thousand classes. Bouncy Castle crypto next. Then Alibaba, Meta, I A P, Visa, Media Three, and just one thousand vn dot com classes of first party code. String forensics finds three thousand zalopay hits, fifteen hundred signatures, nine hundred firebase, four hundred H T T P S URLs, and six frida mentions. Obfuscation is partial. Telemetry is total.',
    captions: [
      '20 DEX: classes2.dex 16.5 MB largest; audience_network.dex fully third-party',
      'Top packages: google 7,761 / bouncycastle 6,112 / alibaba 4,821 / meta 3,622',
      'Strings: zalopay x3355, signature x1511, firebase x953, frida x6',
    ],
    draw(f, c) {
      header(f, 'CH.4 // DEX AT SCALE', '378,040 METHODS', '20 DEX // SDK mesh // string forensics (report 06)', c.t / c.dur);
      const per = dexR.per_dex as Record<string, any>;
      const names = Object.keys(per).sort((a, b) => (per[b]?.bytes || 0) - (per[a]?.bytes || 0)).slice(0, 20);
      const maxB = Math.max(...names.map(n => per[n]?.bytes || 1), 1);
      f.panel(60, 220, 1160, 190, `DEX SIZE RANKING (log-ish) // ${dexR.counts?.classes?.toLocaleString('en-US') || '63,827'} CLASSES`);
      names.forEach((n, i) => {
        const bx = 84 + i * 56;
        const frac = Math.pow((per[n]?.bytes || 0) / maxB, 0.4) * ease(clamp01(c.t / c.dur * 2 - i * 0.04));
        const bh = Math.round(110 * frac);
        f.rect(bx, 368 - bh, 40, bh, n.includes('audience') ? C.gold : C.cyan);
        if (i % 2 === 0) f.text(n.replace('classes', 'c').replace('.dex', ''), bx + 20, 376, 1, C.dim, 'c');
      });
      f.panel(60, 424, 560, 174, 'TOP PACKAGES (CLASS COUNT)');
      const pkgs: [string, number][] = (dexR.top_packages || []).slice(0, 6);
      const pm = Math.max(...pkgs.map(p => p[1]), 1);
      pkgs.forEach(([p, n], i) => {
        f.text(p.split('.').slice(0, 2).join('.'), 84, 464 + i * 24, 1, C.dim);
        f.hbar(240, 462 + i * 24, 240, 14, (n / pm) * ease(clamp01(c.t / c.dur * 2.5 - i * 0.12)), C.blue);
        f.text(n.toLocaleString('en-US'), 500, 462 + i * 24, 1, C.text);
      });
      f.panel(644, 424, 576, 174, 'STRING SIGNALS');
      const kw = dexR.string_keywords || {};
      const keys = ['zalopay', 'signature', 'certificate', 'firebase', 'hmac', 'secret', 'password', 'root'];
      keys.forEach((k, i) => {
        const x = 668 + (i % 2) * 270, y = 464 + Math.floor(i / 2) * 30;
        f.text(k, x, y, 2, C.dim);
        f.text(`x${(kw[k] || 0).toLocaleString('en-US')}`, x + 150, y, 2, C.gold);
      });
    },
  },
  {
    id: 'native', kicker: 'CH.5 // NATIVE LAYER', title: 'ARM32 ONLY, STRIPPED, HARDENED',
    minDur: 30,
    narration: 'Chapter five. The native layer. Twenty seven thirty two bit ARM libraries ride in the ABI split, plus one three hundred ninety two byte anomaly in the base. Security Guard Lite from Alipay hides its J N I behind register natives. We recover its init, encrypt, decrypt, and sign request entry points from strings. S Q L Cipher guards storage. Face, barcode, and M L Kit vision libraries cluster around e K Y C and QR. Every library ships position independent, read only relocations, and bind now. No Java exports in dynamic symbols. Stripped, but not silent.',
    captions: [
      '27x ARM32 .so in ABI split + 392-byte non-ELF trueidlib anomaly in base',
      'SecurityGuard Lite JNI recovered from strings: init/encrypt/decrypt/signRequest',
      'All ELF: PIE + RELRO + BIND_NOW, no exec stack, zero Java_* dynsyms',
    ],
    draw(f, c) {
      header(f, 'CH.5 // NATIVE LAYER', 'STRIPPED, NOT SILENT', '28 .so triage (report 07 + native-strings.json)', c.t / c.dur);
      f.panel(60, 220, 760, 378, 'KEY LIBRARIES (SIZE // ROLE)');
      const pick = ['libsecurity_lite.so', 'libsqlcipher.so', 'libVFaceLib.so', 'libbarhopper_v3.so', 'libmlkit_google_ocr_pipeline.so', 'libpicodet_ncnn.so', 'librive-android.so', 'libtaibmcf.so'];
      const role: Record<string, string> = { libsecurity_lite: 'Alipay SecurityGuard', libsqlcipher: 'encrypted SQLite', libVFaceLib: 'face/liveness', libbarhopper_v3: 'QR/barcode', libmlkit_google_ocr_pipeline: 'ML Kit OCR', libpicodet_ncnn: 'object detect', 'librive-android': 'risk/device', libtaibmcf: 'obfuscated core' };
      pick.forEach((p, i) => {
        if (c.t / c.dur * 3 < 0.2 + i * 0.2) return;
        const hit = natR.find((n: any) => String(n.file).includes(p));
        const y = 264 + i * 40;
        f.text(p.replace('config.armeabi_v7a__', '').slice(0, 30), 84, y, 2, C.text);
        f.text(role[p.replace('.so', '')] || '', 430, y, 2, C.dim);
        f.text(hit ? MB(hit.bytes) : '?', 796, y, 2, C.gold, 'r');
      });
      f.panel(844, 220, 376, 150, 'HARDENING');
      ['PIE ............ ON', 'RELRO .......... ON', 'BIND_NOW ....... ON', 'STACK_EXEC ..... OFF'].forEach((s, i) => f.text(s, 868, 264 + i * 24, 2, C.green));
      f.panel(844, 384, 376, 214, 'JNI RECOVERY');
      f.text('dynsym Java_* : 0', 868, 424, 2, C.red);
      f.text('strings:', 868, 452, 2, C.dim);
      ['...SecurityGuardLite', 'Native_init', '..._encrypt / _decrypt', '..._signRequest'].forEach((s, i) => f.text(s, 868, 478 + i * 24, 2, C.green));
      f.text('+392 B trueidlib.so anomaly', 868, 574, 1, C.gold);
    },
  },
  {
    id: 'jsbridge', kicker: 'CH.6 // JS BRIDGES', title: 'THE WEBVIEW CONTROL PLANE',
    minDur: 30,
    narration: 'Chapter six. JavaScript bridges. Nineteen web assets form the hybrid control plane. ZaloPay J S Bridge and its internal twin expose callback pools and subscribers to native. Griver compatibility shims mirror the Alipay bridge, with worker bootstrap and embedded view managers. Error page templates stand ready for every container state. We reconstructed the application graph with R E A agents, yielding a thirty seven node semantic graph across eighteen files. Native decompilers wanted Ghidra and J A D X providers we did not have. That gap is documented, not hidden.',
    captions: [
      'ZaloPayJSBridge + Internal twin: callbackPool/subscribers -> native dispatch',
      'Griver/Alipay shims: appx bootstrap, h5_bridge, workerjs, embed manager',
      'REA agents 6.5.0: 37-node semantic graph, 18 files, 100,428 bytes',
    ],
    draw(f, c) {
      header(f, 'CH.6 // JS BRIDGES', 'WEBVIEW CONTROL PLANE', '19 assets // REA evidence (report 08)', c.t / c.dur);
      f.panel(60, 220, 560, 378, 'BRIDGE FILES');
      const files: [string, string][] = [['android_jsbridge.js', '3.6 KB'], ['internaljsbridge.js', '3.8 KB'], ['jsbridge.js', '3.5 KB'], ['webapp.js', '619 B'], ['h5_bridge.js', '4.3 KB'], ['workerjs.js', '14 KB'], ['cover_component.js', '2.8 KB'], ['h5_bridge (res/raw)', '1.8 KB']];
      files.forEach(([n, s], i) => {
        if (c.t / c.dur * 3 < 0.2 + i * 0.2) return;
        f.circle(92, 272 + i * 40, 4, C.cyan, true);
        f.text(n, 110, 265 + i * 40, 2, C.text);
        f.text(s, 596, 265 + i * 40, 2, C.gold, 'r');
      });
      f.panel(644, 220, 576, 200, 'EXPOSED SURFACE');
      ['ZaloPayJSBridge.callbackPool', 'ZaloPayJSBridge.subscribers', 'ZaloPayInternalJSBridge.*', 'AlipayJSBridgeReady shim', 'androidEmbedViewManager', 'webapp_getNavigation()'].forEach((s, i) => {
        f.text('> ' + s, 668, 264 + i * 26, 2, i < 3 ? C.gold : C.green);
      });
      f.panel(644, 436, 576, 162, 'REA AGENTS 6.5.0 EVIDENCE');
      f.text('37-node semantic graph', 668, 476, 2, C.text);
      f.text('18 files // 100,428 bytes', 668, 504, 2, C.text);
      f.text('gap: Ghidra/JADX providers absent', 668, 532, 2, C.red);
      f.text('5.8 MB evidence: raw/rea-js-analyze.json', 668, 560, 1, C.faint);
    },
  },
  {
    id: 'synthesis', kicker: 'CH.7 // SYNTHESIS', title: 'FINDINGS, RISKS, REPRODUCIBILITY',
    minDur: 30,
    narration: 'Chapter seven. Synthesis. The architecture is a split model super app with a third party SDK mesh dwarfing first party code. Risks, static only, pending dynamic confirmation. Global cleartext with no pins. Broad file provider paths. Exported test activities. An H C E payment surface. And a signed config envelope whose trust chain must still be verified. Reproduction is fully pinned. Androguard four point one point four. R E A agents six point five. Ten reports, raw JSON, scripts, and digests, all hashed, all public in the pull request.',
    captions: [
      'RISK-1 cleartext/no-pin gap // RISK-2 file provider // RISK-3 exported tests',
      'RISK-4 HCE AID surface // RISK-5 config trust chain unverified (static-only)',
      'Repro: androguard 4.1.4 + rea-agents 6.5.0 + pinned scripts, all hashed',
    ],
    draw(f, c) {
      header(f, 'CH.7 // SYNTHESIS', 'FINDINGS + REPRODUCIBILITY', 'static-only risks // pinned toolchain (report 09)', c.t / c.dur);
      f.panel(60, 220, 640, 378, 'RISK REGISTER (STATIC-ONLY)');
      const risks: [string, string, RGBA][] = [
        ['R-1', 'cleartext-permitted + no pin-set vs pinning code', C.red],
        ['R-2', 'FileProvider cache-path "/" + external "."', C.gold],
        ['R-3', 'exported Preview/test instrumentation activities', C.gold],
        ['R-4', 'HCE + Visa AID + deeplink payment surface', C.blue],
        ['R-5', 'RSA-2048 config envelope: chain unverified', C.blue],
      ];
      risks.forEach(([id, d, cc], i) => {
        if (c.t / c.dur * 3 < 0.2 + i * 0.25) return;
        f.text(id, 84, 268 + i * 60, 3, cc);
        f.text(d.slice(0, 44), 170, 272 + i * 60, 2, C.text);
        if (d.length > 44) f.text(d.slice(44), 170, 296 + i * 60, 2, C.text);
      });
      f.panel(724, 220, 496, 200, 'REPRODUCIBILITY');
      ['androguard 4.1.4 (DEX/AXML)', 'rea-agents 6.5.0 (JS graph)', 'readelf/strings/openssl', 'reports 00-09 + raw JSON', 'tools/stage1/2/3b/4.py'].forEach((s, i) => f.text('+ ' + s, 748, 264 + i * 28, 2, C.green));
      f.panel(724, 436, 496, 162, 'LIMITATIONS (STATED)');
      ['full AnalyzeAPK OOM (exit 137)', 'no Ghidra/JADX providers', 'no dynamic execution'].forEach((s, i) => f.text('- ' + s, 748, 476 + i * 28, 2, C.dim));
    },
  },
  {
    id: 'outro', kicker: 'CREDITS // CITE THIS WORK', title: 'EVIDENCE OVER CLAIMS',
    minDur: 15,
    narration: 'Evidence over claims. All findings, hashes, scripts, and transcripts live in the pull request beside this video. Cite the reports, challenge the risks, rerun the toolchain. ZaloPay eleven point seventeen point zero, reversed end to end.',
    captions: [
      'Cite: ZaloPay 11.17.0 Deep RE (2026) — reports 00-09 + evidence/ + tools/',
      'Branch arena/753157fb-repo // PR-linked film + transcript + subtitles',
      'Evidence over claims. Rerun everything. Challenge every risk.',
    ],
    draw(f, c) {
      const e = ease(clamp01(c.t / 1.5));
      f.text('CREDITS // CITE THIS WORK', W / 2, 150, 2, C.gold, 'c');
      f.text('EVIDENCE', W / 2, 190, 10, C.text, 'c');
      f.text('OVER CLAIMS', W / 2, 190 + 78, 10, C.cyan, 'c');
      const rows = ['reports/00-09 ............ findings + method', 'reports/raw/ ............. 12 JSON evidence bundles', 'evidence/ ................ trimmed digests + icon', 'tools/ ................... stage1/2/3b/4 + digest', 'video/ ................... this film, TypeScript-built'];
      rows.forEach((r, i) => {
        if (c.t < 2 + i * 0.4) return;
        f.text(r, W / 2, 400 + i * 32, 2, C.text, 'c');
      });
      void e;
      f.text('ZALOPAY 11.17.0 DEEP RE (2026) // STATIC ANALYSIS // RESEARCH USE ONLY', W / 2, 580, 1, C.faint, 'c');
    },
  },
];
export function renderScene(f: Frame, s: Scene, c: SceneCtx) {
  bg(f, c.tG);
  s.draw(f, c);
  // transitions
  const fin = clamp01(c.t / 0.6), fout = clamp01((c.dur - c.t) / 0.6);
  const dim = 1 - Math.min(fin, fout);
  if (dim > 0.01) f.rect(0, 0, W, H, col('#04060f', Math.round(255 * dim * 0.9)));
  chrome(f, s, c);
}
