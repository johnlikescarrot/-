/**
 * Timeline: the single source of truth for narration, on-screen content and captions.
 * Every time value is derived from a measured word-rate model, so re-recording the
 * narration with the same script reproduces the same cue sheet.
 */

export type Beat = "assert" | "evidence" | "caveat" | "transition";

export interface SceneCue {
  /** Short label shown in the chapter rail and burned into the caption track. */
  label: string;
  /** 0..1 emphasis: drives accent colour and animation amplitude. */
  emphasis?: number;
}

export interface Scene {
  id: string;
  chapter: string;
  /** Narration text — spoken verbatim; also the source of the caption words. */
  narration: string;
  /** Big title (left column). */
  title: string;
  /** Supporting line under the title. */
  subtitle?: string;
  /** Figure to draw on the right column, if any. */
  figure?: FigureSpec;
  beat: Beat;
  cues: SceneCue[];
}

export type FigureSpec =
  | { kind: "layer-stack" }
  | { kind: "size-budget" }
  | { kind: "bars"; title: string; rows: Array<[string, number]>; unit?: string }
  | { kind: "metric"; name: string; value: string; definition: string; note?: string }
  | { kind: "hosts"; rows: Array<{ host: string; klass: string }>; title: string }
  | { kind: "quote"; lines: string[]; source: string }
  | { kind: "timeline" }
  | { kind: "none" };

export const FPS = 30;
/** Narration pace used for cue planning (words per minute), measured from the rendered audio. */
export const WPM = 150;
export const LEAD_IN = 1.6;
export const SCENE_GAP = 0.55;
export const TAIL = 3.2;

export const SCENES: Scene[] = [
  {
    id: "cold-open",
    chapter: "00 · Mở đầu",
    beat: "transition",
    title: "Một tệp. Bốn runtime.",
    subtitle: "Nghiên cứu tĩnh hoàn chỉnh trên Zalopay 11.17.0",
    figure: { kind: "layer-stack" },
    cues: [{ label: "vn.com.vng.zalopay · 11.17.0 · versionCode 1102758", emphasis: 0.4 }],
    narration:
      "Một tệp cài đặt duy nhất. Bốn runtime khác nhau. Một ứng dụng thanh toán đang vận hành thật. " +
      "Đây là nghiên cứu tĩnh hoàn chỉnh trên Zalopay phiên bản mười một chấm mười bảy chấm không, " +
      "và mục tiêu không phải là tìm lỗ hổng, mà là đo được kiến trúc.",
  },
  {
    id: "question",
    chapter: "01 · Câu hỏi",
    beat: "assert",
    title: "Câu hỏi nghiên cứu",
    subtitle: "Không phải “ứng dụng này có an toàn không?”",
    figure: {
      kind: "quote",
      lines: [
        "Lớp nào trong ứng dụng sở hữu rủi ro?",
        "Và làm sao đo được điều đó",
        "chỉ từ một tệp APK?",
      ],
      source: "RQ — câu hỏi dẫn dắt toàn bộ nghiên cứu",
    },
    cues: [
      { label: "RQ1 · phân tầng", emphasis: 0.6 },
      { label: "RQ2 · chỉ số tái lập được", emphasis: 0.8 },
    ],
    narration:
      "Câu hỏi không phải là ứng dụng này có an toàn hay không. Câu hỏi là: lớp nào trong ứng dụng " +
      "đang sở hữu rủi ro, và liệu có thể đo được điều đó chỉ từ một tệp APK, bằng những chỉ số " +
      "mà bất kỳ ai cũng tính lại được không?",
  },
  {
    id: "artifact",
    chapter: "02 · Đối tượng",
    beat: "evidence",
    title: "Đối tượng nghiên cứu",
    subtitle: "XAPK 105.580.620 byte · 5 APK · băm SHA-256 công bố",
    figure: { kind: "size-budget" },
    cues: [
      { label: "base 79.4 MB · ABI 24.8 MB · locale 86 KB", emphasis: 0.5 },
      { label: "SHA-256 được công bố cho từng APK", emphasis: 0.9 },
    ],
    narration:
      "Đối tượng là một XAPK hơn một trăm lẻ năm triệu byte, gồm năm APK: gói gốc bảy mươi chín " +
      "megabyte chứa mã, gói kiến trúc ARM chứa thư viện native, hai gói ngôn ngữ và một gói màn hình. " +
      "Mọi kết luận trong nghiên cứu này đều gắn với băm SHA-256 của từng gói, nên người khác có thể " +
      "đối chiếu chính xác cùng một tệp.",
  },
  {
    id: "layers",
    chapter: "03 · Phân tầng",
    beat: "evidence",
    title: "Bốn runtime trong một artifact",
    subtitle: "Kotlin · React Native · mini-program · native ELF",
    figure: { kind: "layer-stack" },
    cues: [
      { label: "L0 · 110.677 lớp Kotlin/Java", emphasis: 0.5 },
      { label: "L1 · JS bundle 3.6 MB, 22 native module", emphasis: 0.7 },
      { label: "L2 · Griver 2.70.0", emphasis: 0.8 },
      { label: "L3 · 27 thư viện ELF", emphasis: 0.9 },
    ],
    narration:
      "Lớp thứ nhất là Kotlin và Java: mười chín tệp DEX, một trăm mười nghìn lớp. Lớp thứ hai là " +
      "React Native: một gói JavaScript ba phẩy sáu megabyte, không hề bị biên dịch thành bytecode, " +
      "nên đọc được trực tiếp. Lớp thứ ba là runtime mini-program kiểu Alipay. Lớp thứ tư là hai mươi " +
      "bảy thư viện native.",
  },
  {
    id: "scale",
    chapter: "04 · Quy mô",
    beat: "evidence",
    title: "Quy mô mã nguồn",
    subtitle: "110.677 lớp · 351.248 phương thức · 374.276 chuỗi",
    figure: {
      kind: "bars",
      title: "Lớp theo từng tệp DEX",
      rows: [
        ["classes7.dex", 19810],
        ["classes4.dex", 18426],
        ["classes6.dex", 17892],
        ["classes5.dex", 13631],
        ["classes2.dex", 10797],
        ["classes3.dex", 3744],
        ["classes9.dex", 2012],
        ["classes.dex", 1768],
      ],
    },
    cues: [
      { label: "19 tệp DEX · 15 MB cho classes2.dex", emphasis: 0.6 },
      { label: "phân tích header-level, không dịch ngược", emphasis: 0.35 },
    ],
    narration:
      "Chỉ riêng lớp Kotlin đã có mười chín tệp DEX. Tệp lớn nhất, classes2, nặng mười lăm megabyte. " +
      "Tổng cộng một trăm mười nghìn lớp, ba trăm năm mươi mốt nghìn phương thức. Nhưng điều thú vị " +
      "không nằm ở số lượng, mà ở việc số lượng đó thuộc về ai.",
  },
  {
    id: "substrate",
    chapter: "05 · Chỉ số SR",
    beat: "assert",
    title: "SR — Substrate Ratio",
    subtitle: "Tỉ lệ lớp do bên thứ ba viết",
    figure: {
      kind: "metric",
      name: "SR",
      value: "0,973",
      definition: "lớp bên thứ ba ÷ tổng số lớp",
      note: "2.990 lớp first-party trên 110.677 lớp",
    },
    cues: [
      { label: "Google 11.780 · BouncyCastle 9.177", emphasis: 0.6 },
      { label: "Alipay 7.333 · Visa 1.792", emphasis: 0.75 },
      { label: "first-party ≈ 2,7 %", emphasis: 0.95 },
    ],
    narration:
      "Chỉ số đầu tiên tôi gọi là Substrate Ratio. Chín mươi bảy phẩy ba phần trăm số lớp là của bên " +
      "thứ ba: Google, Bouncy Castle, Alipay, Visa, Facebook. Mã do Zalopay tự viết chỉ khoảng hai phẩy " +
      "bảy phần trăm. Nói cách khác, đây không phải một ứng dụng thanh toán; đây là một lớp vỏ mỏng " +
      "trên một nền rất lớn.",
  },
  {
    id: "bridge",
    chapter: "06 · Chỉ số CBB",
    beat: "evidence",
    title: "CBB — Bridge Breadth",
    subtitle: "22 native module, 8 thuộc first-party",
    figure: {
      kind: "bars",
      title: "Cầu nối React Native → native",
      rows: [
        ["ZaloPayApi", 1],
        ["ZaloPayTransactionLogs", 1],
        ["ZaloPayRedPacketApi", 1],
        ["ZPBankIntegrationApi", 1],
        ["ZaloPayTracker", 1],
        ["RNCWebView / RNCNetInfo", 1],
        ["RNDeviceInfo / RNSentry", 1],
      ],
      unit: " module",
    },
    cues: [
      { label: "ZaloPayApi · ZPBankIntegrationApi", emphasis: 0.85 },
      { label: "bề mặt tin cậy nằm ở ranh giới JS ↔ native", emphasis: 0.7 },
    ],
    narration:
      "Chỉ số thứ hai là Bridge Breadth: đếm số cầu nối giữa JavaScript và native. Có hai mươi hai " +
      "module, tám trong số đó là của Zalopay, và chúng nắm những thứ nhạy cảm nhất: giao dịch, " +
      "ngân hàng, chia hoá đơn, lì xì. Ranh giới tin cậy thật sự của ứng dụng nằm ở đây, chứ không " +
      "nằm trong giao diện.",
  },
  {
    id: "edi",
    chapter: "07 · Chỉ số EDI",
    beat: "assert",
    title: "EDI — Environment Divergence",
    subtitle: "Môi trường thử nghiệm rò rỉ vào bản phát hành",
    figure: {
      kind: "hosts",
      title: "9 host chỉ tồn tại trong JS bundle",
      rows: [
        { host: "staging.zalopay.vn", klass: "js-only" },
        { host: "sandbox.zalopay.vn", klass: "js-only" },
        { host: "sandbox.zalopay.com.vn", klass: "js-only" },
        { host: "socialstg.zalopay.vn", klass: "js-only" },
        { host: "socialdev.zalopay.vn", klass: "js-only" },
        { host: "stgcdn.zalopay.com.vn", klass: "js-only" },
        { host: "stcstg.zalopay.com.vn", klass: "js-only" },
        { host: "stgcps.zalopay.com.vn", klass: "js-only" },
        { host: "devcps.zalopay.com.vn", klass: "js-only" },
      ],
    },
    cues: [
      { label: "EDI = 0,391 — 9 trên 23 host phi sản xuất", emphasis: 0.9 },
      { label: "không thực hiện truy vấn mạng nào", emphasis: 0.5 },
    ],
    narration:
      "Chỉ số thứ ba là phát hiện thực nghiệm rõ nhất. Trong hai trăm host được trích ra, hai mươi ba " +
      "host là môi trường thử nghiệm. Mười bốn host nằm trong mã Kotlin. Nhưng chín host — staging, " +
      "sandbox, socialstg, devcps — chỉ xuất hiện trong gói JavaScript. Lớp Kotlin đã được tách môi " +
      "trường cẩn thận; lớp JavaScript thì chưa. Chỉ số EDI bằng không phẩy ba chín. Tôi không gửi " +
      "một truy vấn nào tới các host này.",
  },
  {
    id: "nts",
    chapter: "08 · Chỉ số NTS",
    beat: "evidence",
    title: "NTS — Native Transparency",
    subtitle: "10/27 thư viện xuất ký hiệu JNI",
    figure: {
      kind: "metric",
      name: "NTS",
      value: "0,370",
      definition: "thư viện có ≥1 ký hiệu JNI ÷ tổng thư viện",
      note: "9 thư viện chỉ nạp bằng dlopen, tên bị làm mờ",
    },
    cues: [
      { label: "25/27 đạt PIE + NX + RELRO + canary", emphasis: 0.8 },
      { label: "chỉ số yếu là minh bạch, không phải gia cố", emphasis: 0.95 },
    ],
    narration:
      "Chỉ số thứ tư đo mức độ minh bạch của lớp native. Hai mươi lăm trên hai mươi bảy thư viện đạt " +
      "đầy đủ PIE, NX, relro và canary — tức là được gia cố tốt. Nhưng chỉ mười thư viện xuất ký hiệu " +
      "JNI. Chín thư viện mang tên làm mờ, không xuất gì cả, chỉ được nạp qua dlopen. Điểm yếu của " +
      "lớp native không phải là thiếu gia cố, mà là thiếu khả năng kiểm toán.",
  },
  {
    id: "integrity",
    chapter: "09 · Chống can thiệp",
    beat: "evidence",
    title: "Chống can thiệp & điểm yếu kỹ thuật",
    subtitle: "Root · emulator · hook · attestation · certificate pinning",
    figure: {
      kind: "quote",
      lines: [
        "/system/bin/su · /magisk · FRIDA",
        "SafetyNet · Play Integrity",
        "TrueIDSSLPinningConfig",
        "…không tìm thấy khoá bí mật nhúng",
      ],
      source: "research/evidence/excerpts.json — trích dẫn có địa chỉ dòng",
    },
    cues: [
      { label: "dò root/emulator/hook ở tầng chuỗi", emphasis: 0.6 },
      { label: "không có khoá bí mật nhúng trong artifact", emphasis: 1 },
    ],
    narration:
      "Ứng dụng có dò root, dò máy ảo, dò hook, có SafetyNet và Play Integrity, có ghim chứng chỉ " +
      "TLS. Nhưng điều đáng chú ý nhất là thứ không tìm thấy: không có khoá bí mật nào được nhúng " +
      "trong artifact. Với một ứng dụng thanh toán, đó là kết quả đúng, và cũng là giới hạn của " +
      "phân tích tĩnh.",
  },
  {
    id: "pii",
    chapter: "10 · Trách nhiệm",
    beat: "caveat",
    title: "Một phát hiện cần xử lý có trách nhiệm",
    subtitle: "Dữ liệu khách hàng thật nằm trong tệp asset",
    figure: {
      kind: "quote",
      lines: [
        "assets/client_tracking.json — 146 KB",
        "bản ghi chuyến đi sản xuất",
        "đã loại khỏi kho nghiên cứu,",
        "không sao chép bất kỳ trường cá nhân",
      ],
      source: "báo cáo cho nhà cung cấp, không công bố dữ liệu",
    },
    cues: [
      { label: "chỉ báo cáo sự tồn tại + schema", emphasis: 0.85 },
      { label: "khuyến nghị: loại payload khỏi bản phát hành", emphasis: 0.7 },
    ],
    narration:
      "Trong quá trình liệt kê asset, tôi tìm thấy một tệp chứa bản ghi chuyến đi có thật: tên, số " +
      "điện thoại, email, mã số thuế của khách hàng. Đây không phải dữ liệu mẫu. Tôi đã loại tệp này " +
      "khỏi kho nghiên cứu và không sao chép bất kỳ trường cá nhân nào. Nó được báo cáo ở mức sự tồn " +
      "tại, kích thước và tên khoá, đủ để nhà cung cấp sửa.",
  },
  {
    id: "prior",
    chapter: "11 · Định vị",
    beat: "assert",
    title: "Định vị trong tài liệu",
    subtitle: "n = 1 nhưng đủ tầng — bổ khuyết cho nghiên cứu quần thể",
    figure: {
      kind: "timeline",
    },
    cues: [
      { label: "Drebin 2014 · MaMaDroid 2017", emphasis: 0.5 },
      { label: "Alrawi 2019 · Reardon 2019", emphasis: 0.7 },
      { label: "khoảng trống: chỉ số liên tầng", emphasis: 0.95 },
    ],
    narration:
      "Vậy nghiên cứu này đứng ở đâu? Drebin và MaMaDroid phân loại hàng triệu ứng dụng. Alrawi và " +
      "Reardon đo hành vi của SDK trên toàn hệ sinh thái. Họ trả lời câu hỏi rủi ro có tồn tại không. " +
      "Nghiên cứu này trả lời câu hỏi rủi ro nằm ở tầng nào. Đó là khoảng trống mà bốn chỉ số ở trên " +
      "cố gắng lấp đầy: rẻ, tái lập được, và kiểm chứng được từ một tệp duy nhất.",
  },
  {
    id: "repro",
    chapter: "12 · Tái lập",
    beat: "evidence",
    title: "Tái lập trong ba lệnh",
    subtitle: "Không cần JADX, không cần Ghidra, không cần thiết bị",
    figure: {
      kind: "quote",
      lines: [
        "python3 scripts/extract.py",
        "python3 scripts/triage.py",
        "python3 scripts/metrics.py",
        "→ research/metrics.json",
      ],
      source: "mỗi chuỗi trích dẫn có địa chỉ research/dex-strings.txt:<dòng>",
    },
    cues: [
      { label: "chỉ dùng thư viện chuẩn của Python", emphasis: 0.7 },
      { label: "cùng tệp → cùng số liệu", emphasis: 0.95 },
    ],
    narration:
      "Mọi con số trong video này tái lập được bằng ba lệnh Python, không cần JADX, không cần Ghidra, " +
      "không cần thiết bị thật. Mỗi chuỗi được trích dẫn đều có địa chỉ dòng trong tệp chuỗi, nên " +
      "người phản biện có thể kiểm tra từng khẳng định. Cùng một tệp, cùng một kết quả.",
  },
  {
    id: "limits",
    chapter: "13 · Giới hạn",
    beat: "caveat",
    title: "Giới hạn — nói rõ trước khi bị hỏi",
    subtitle: "Sáu giới hạn, tất cả đều kiểm chứng được",
    figure: {
      kind: "quote",
      lines: [
        "bản repack APKPure, không có chữ ký JAR",
        "không có trình dịch ngược ⇒ không có mã nguồn",
        "chỉ một ABI: armeabi-v7a",
        "không thực thi động, không truy vấn mạng",
        "một ảnh chụp: versionCode 1102758",
        "chỉ số có thể trôi theo phiên bản",
      ],
      source: "research/03-synthesis.md §3.4",
    },
    cues: [{ label: "threats to validity — công bố công khai", emphasis: 0.8 }],
    narration:
      "Sáu giới hạn, và tôi nói rõ trước khi bị hỏi. Một: đây là bản repack của APKPure, gói gốc không " +
      "có chữ ký JAR. Hai: không có trình dịch ngược, nên không có khẳng định ở mức mã nguồn. Ba: chỉ " +
      "có một kiến trúc ARM ba hai bit. Bốn: không thực thi động. Năm: đây là một ảnh chụp tại một " +
      "phiên bản. Sáu: các chỉ số sẽ trôi theo thời gian, và đó chính là lý do phải công bố cách tính.",
  },
  {
    id: "close",
    chapter: "14 · Kết luận",
    beat: "assert",
    title: "Kết luận",
    subtitle: "Đo lớp sở hữu rủi ro, không chỉ đo rủi ro",
    figure: {
      kind: "metric",
      name: "4 chỉ số",
      value: "SR · CBB · EDI · NTS",
      definition: "tính từ một artifact, kiểm chứng độc lập được",
      note: "kèm bộ dữ liệu, băm, và script tái lập",
    },
    cues: [
      { label: "mã nguồn + dữ liệu công khai trong PR", emphasis: 0.8 },
      { label: "sẵn sàng cho phản biện", emphasis: 1 },
    ],
    narration:
      "Kết luận rất gọn: với ứng dụng thanh toán hiện đại, câu hỏi đúng không phải là ứng dụng có an " +
      "toàn không, mà là lớp nào đang sở hữu rủi ro. Bốn chỉ số SR, CBB, EDI và NTS trả lời câu hỏi " +
      "đó từ một tệp duy nhất. Toàn bộ mã, dữ liệu và băm của nghiên cứu này được công bố công khai " +
      "để bất kỳ ai cũng có thể phản biện, hoặc tái lập.",
  },
];

export interface CueWord { word: string; t: number }

export interface SceneTiming {
  scene: Scene;
  index: number;
  start: number;
  end: number;
  words: CueWord[];
}

const wordCount = (s: string) => s.trim().split(/\s+/).filter(Boolean).length;

export function planTimeline(scenes: Scene[] = SCENES): { scenes: SceneTiming[]; duration: number } {
  let t = LEAD_IN;
  const out: SceneTiming[] = [];
  for (const [index, scene] of scenes.entries()) {
    const words = scene.narration.trim().split(/\s+/).filter(Boolean);
    const dur = Math.max(3.2, (words.length / WPM) * 60);
    const per = dur / Math.max(words.length, 1);
    const cueWords: CueWord[] = words.map((w, i) => ({ word: w, t: t + i * per }));
    out.push({ scene, index, start: t, end: t + dur, words: cueWords });
    t += dur + SCENE_GAP;
  }
  return { scenes: out, duration: t + TAIL };
}
