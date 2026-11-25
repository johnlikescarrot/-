/**
 * Scene definitions for ZaloPay 11.17.0 reverse-engineering video.
 * Each scene: slide content (title, subtitle, bullets, chart) + narration text.
 * Narration is Vietnamese (primary) with English on-slide keywords for international reach.
 */

export interface ChartBar {
  label: string;
  value: number;
  color?: string;
}

export interface Scene {
  id: string;
  kicker: string; // small top label, e.g. "01 · INTRODUCTION"
  title: string;
  subtitle: string;
  bullets: string[];
  chart?: { title: string; bars: ChartBar[]; unit?: string };
  footer: string;
  narration: string; // Vietnamese narration script (~40-60s per scene)
}

export const VIDEO_META = {
  title: "ZaloPay 11.17.0 — Reverse Engineering a Vietnamese Super-App",
  subtitle: "Comprehensive offline static analysis of 6,471 entries · ZP-RE-2026-11-17.0",
  width: 1280,
  height: 720,
  fps: 30,
};

export const SCENES: Scene[] = [
  {
    id: "01-title",
    kicker: "ZP-RE-2026-11-17.0 · ACADEMIC VIDEO",
    title: "ZaloPay 11.17.0",
    subtitle: "Reverse Engineering a Vietnamese Super-App — 6,471 entries, offline static analysis",
    bullets: [
      "Package vn.com.vng.zalopay · v11.17.0 (1102758) · XAPK / APKPure",
      "ZIP 98.8 MB → XAPK 105.6 MB → 5 APK splits (base + ABI + lang + density)",
      "20 DEX · 27 native libs · 16 on-device ML models · 373 mini-apps",
    ],
    footer: "Static-only · No execution · PII redacted · Reproducible from scripts/",
    narration:
      "Xin chào. Đây là video học thuật trình bày nghiên cứu dịch ngược toàn diện ứng dụng ZaloPay phiên bản mười một chấm mười bảy. Chúng tôi phân tích tĩnh ngoại tuyến gói XAPK gồm sáu nghìn bốn trăm bảy mươi mốt mục, từ ZIP gốc qua XAPK tới năm APK chia tách. Video trình bày kiến trúc, bề mặt API, hệ sinh thái mini-app, học máy trên thiết bị và cơ chế bảo mật. Mọi con số đều có thể tái lập từ bảng kiểm kê và các script trong kho mã.",
  },
  {
    id: "02-rq",
    kicker: "01 · INTRODUCTION & RESEARCH QUESTIONS",
    title: "Why reverse-engineer a super-app?",
    subtitle: "ZaloPay is a wallet + bank + mini-app platform + eKYC in one package",
    bullets: [
      "RQ1 — Packaging & size distribution? (ZIP → XAPK → APK)",
      "RQ2 — Software architecture recoverable from static analysis?",
      "RQ3 — Network / API surface & mini-app ecosystem?",
      "RQ4 — Security, encryption & on-device ML?",
      "RQ5 — Limitations & ethics?",
    ],
    footer: "Scope: static analysis only. No API calls, no exploitation, no secret exfiltration.",
    narration:
      "ZaloPay không chỉ là ví điện tử. Đó là siêu ứng dụng tích hợp thanh toán QR, chuyển tiền, liên kết ngân hàng, định danh điện tử eKYC, NFC và hàng trăm mini-app. Nghiên cứu đặt năm câu hỏi: cấu trúc đóng gói ra sao, kiến trúc khôi phục được ở mức nào, bề mặt API có gì đặc biệt, bảo mật và học máy triển khai thế nào, và giới hạn đạo đức là gì. Phạm vi chỉ là phân tích tĩnh, không thực thi mã.",
  },
  {
    id: "03-method",
    kicker: "02 · METHODOLOGY (REA-INSPIRED)",
    title: "REA-inspired offline pipeline",
    subtitle: "Inspect → Extract → Strings → Config review → Native triage → Evidence",
    bullets: [
      "Inspired by morluto/rea — agent-based offline artifact inspection",
      "Python stdlib only (zipfile, hashlib, regex) — no jadx/apktool in sandbox",
      "UTF-8 + UTF-16LE string extraction from DEX, Manifest, .so, resources.arsc",
      "Evidence: INVENTORY.csv · hashes.json · stats.json · scripts/",
    ],
    footer: "Every claim links to a file name, size, hash, or extracted string.",
    narration:
      "Phương pháp lấy cảm hứng từ khung REA của morluto, chuyên kiểm kê và trích xuất artifact ngoại tuyến bằng tác nhân. Trong môi trường không có jadx hay apktool, chúng tôi dùng Python thuần: liệt kê ZIP, XAPK, APK, băm SHA hai trăm năm mươi sáu, trích chuỗi UTF tám và UTF mười sáu từ DEX, Manifest và thư viện native. Mọi kết luận đều dẫn tới bằng chứng cụ thể.",
  },
  {
    id: "04-packaging",
    kicker: "03 · PACKAGING CHAIN",
    title: "ZIP → XAPK → 5 APKs",
    subtitle: "XAPK v2 · minSdk 26 · targetSdk 36 · 55 permissions",
    bullets: [
      "vn.com.vng.zalopay.apk — 79.36 MB (base, 6,162 entries)",
      "config.armeabi_v7a.apk — 24.83 MB (27 .so, ABI split)",
      "config.en / config.vi — 74 / 86 KB (language splits)",
      "config.xhdpi.apk — 1.23 MB (258 drawables, density split)",
    ],
    chart: {
      title: "Base APK composition (uncompressed, MB)",
      bars: [
        { label: "DEX (20 files)", value: 66.5, color: "#2563eb" },
        { label: "res/ (5,604)", value: 35.9, color: "#0ea5e9" },
        { label: "assets/ (124)", value: 28.6, color: "#10b981" },
      ],
      unit: " MB",
    },
    footer: "Total inventory: 6,471 entries · Base uncompressed ~130 MB",
    narration:
      "Chuỗi đóng gói bắt đầu từ ZIP chín mươi tám phẩy tám megabyte, chứa XAPK một trăm lẻ năm phẩy sáu megabyte. XAPK gồm APK cơ sở bảy mươi chín megabyte với sáu nghìn một trăm sáu mươi hai mục, APK native ARM v bảy hai mươi bốn megabyte, hai APK ngôn ngữ Anh và Việt, và APK mật độ xhdpi. DEX chiếm sáu mươi sáu phẩy năm megabyte, tài nguyên ba mươi lăm phẩy chín, assets hai mươi tám phẩy sáu.",
  },
  {
    id: "05-manifest",
    kicker: "04 · MANIFEST & PERMISSIONS",
    title: "55 permissions, hundreds of components",
    subtitle: "Binary XML (125,916 bytes) · 618 UTF-16 strings · dynamic features",
    bullets: [
      "Network, location, camera, mic, contacts, calendar, storage, biometric",
      "NFC + Host APDU (Visa), billing, ads (AD_ID + AdServices), QUERY_ALL_PACKAGES",
      "Dynamic features: cashier, cashier_v3, bank_binding, liveness, login_v2, withdraw…",
      "Deep links: zalopay://, go.zalopay.vn, onelink.zalopay.vn, kmzalopay.onelink.me",
    ],
    footer: "Wide permission set is typical for a super-app — but raises privacy surface.",
    narration:
      "Manifest nhị phân một trăm hai mươi lăm nghìn byte chứa sáu trăm mười tám chuỗi UTF mười sáu. Ứng dụng xin năm mươi lăm quyền: mạng, định vị, camera, micro, danh bạ, lịch, lưu trữ, sinh trắc, NFC, thanh toán Google Play và quảng cáo. Có hàng trăm Activity cho luồng cashier, liên kết ngân hàng, eKYC, mini-app và Griver. Các dynamic feature cho thấy kiến trúc mô-đun theo tính năng.",
  },
  {
    id: "06-dex",
    kicker: "05 · DEX & ARCHITECTURE",
    title: "Kotlin + Compose + React Native + Griver",
    subtitle: "20 DEX files · Kotlin coroutines 1.8.1 · Compose 1.7.2 · Dagger 2.59.2",
    bullets: [
      "classes2.dex largest (16.5 MB) — multidex, multi-module",
      "AndroidX: CameraX 1.4.0 · Room 2.8.4 · Work 2.7.1 · Biometric 1.2.0",
      "Packages: vn.com.vng.zalopay.* · vn.com.zalopay.auth.* · com.alibaba.griver.*",
      "loadLibrary: fgcpkjvqe, taibmcf, sqlcipher, security_lite, mlkit, barhopper…",
    ],
    footer: "Hybrid architecture: native core + RN bundle + mini-program runtime.",
    narration:
      "Mã DEX gồm hai mươi tệp, tổng sáu mươi sáu phẩy năm megabyte. Tệp lớn nhất là classes hai, mười sáu phẩy năm megabyte. Ngôn ngữ chính là Kotlin với coroutines, giao diện Jetpack Compose, điều hướng Navigation Compose, và dependency injection Dagger. Kiến trúc lai gồm lõi native, bundle React Native và runtime mini-program Griver của Alipay.",
  },
  {
    id: "07-native",
    kicker: "06 · NATIVE LIBRARIES (.SO)",
    title: "27 ARMv7 libraries, obfuscated names",
    subtitle: "ELF 32-bit · BoringSSL + Protobuf · Alipay Obfuscator · ML Kit",
    bullets: [
      "libtaibmcf.so 12.6 MB + libfgcpkjvqe.so 6.5 MB — BoringSSL, JNI_OnLoad only",
      "libsecurity_lite.so — Alipay SecurityGuardLite (encrypt / signRequest)",
      "libsqlcipher.so 3.6 MB — encrypted SQLite (AES)",
      "libmlkit* + libface_detector + libVFaceLib (OpenCV) + libpicodet_ncnn",
    ],
    chart: {
      title: "Largest native libraries (MB)",
      bars: [
        { label: "libtaibmcf", value: 12.0, color: "#dc2626" },
        { label: "libmlkitcommon", value: 6.6, color: "#2563eb" },
        { label: "libmlkit_ocr", value: 6.5, color: "#0ea5e9" },
        { label: "libfgcpkjvqe", value: 6.2, color: "#7c3aed" },
        { label: "libface_detector", value: 5.1, color: "#10b981" },
      ],
      unit: " MB",
    },
    footer: "Obfuscated names (taibmcf, fgcpkjvqe, a97d…) hinder static analysis.",
    narration:
      "Hai mươi bảy thư viện native ARM v bảy, tất cả ELF ba mươi hai bit. Hai thư viện lớn nhất là libtaibmcf mười hai phẩy sáu megabyte và libfgcpkjvqe sáu phẩy năm, đều chứa BoringSSL và Protobuf, chỉ export JNI OnLoad. Thư viện security lite là Alipay SecurityGuardLite với các hàm mã hóa và ký request. SQLCipher mã hóa cơ sở dữ liệu cục bộ.",
  },
  {
    id: "08-network",
    kicker: "07 · NETWORK & API SURFACE",
    title: "142 URLs · 35 api_names · gRPC",
    subtitle: "OkHttp + Retrofit + Protobuf · api.zalopay.vn · openapi · token · qrpay",
    bullets: [
      "Core: api / openapi / token / sapi / qrpay / bnpl-gateway / loyalty (.zalopay.vn)",
      "um/*: createaccesstoken, getuserinfo, updateprofile, verifyotp, listcardinfo…",
      "v001/tpe/*: createorder, submittrans, transhistory, gettransstatus, mapcard…",
      "connector_mode = grpc · api_route = https · 43 banks · Napas 3-D Secure allowlist",
    ],
    footer: "DEX string counts: okhttp 1,403 · protobuf 1,055 · firebase 1,075 · facebook 1,251",
    narration:
      "Bề mặt mạng gồm một trăm bốn mươi hai URL duy nhất. Các máy chủ chính là api, openapi, token, qrpay và bnpl gateway của zalopay chấm vn. Có ba mươi lăm endpoint trong cấu hình, chia thành quản lý người dùng um, giao dịch tpe và CDN. Chế độ kết nối là gRPC qua HTTPS. Danh sách ngân hàng gồm bốn mươi ba ngân hàng, với allowlist 3D Secure của Napas.",
  },
  {
    id: "09-miniapp",
    kicker: "08 · MINI-APP ECOSYSTEM",
    title: "373 mini-apps, one platform",
    subtitle: "miniapp_config.json (883 KB) · zalopay://launch/app/{id} · social.zalopay.vn",
    bullets: [
      "Dịch vụ phổ biến 88 · Giải trí 77 · Tài chính 65 · Ăn uống 32 · Đối tác 18",
      "Each app: app_id, base_url, one_link, deep_link, nav_bar (FULLSCREEN/NORMAL)",
      "Home shortcuts: transfer · linkcard · wallet · promotion (zalopay://launch/f/*)",
      "Griver runtime: GriverBaseActivity, IPC Lite1–3, TinyAppHostApduService (NFC)",
    ],
    chart: {
      title: "Mini-apps by category (top 5)",
      bars: [
        { label: "Popular services", value: 88, color: "#2563eb" },
        { label: "Entertainment", value: 77, color: "#0ea5e9" },
        { label: "Finance", value: 65, color: "#10b981" },
        { label: "Food & drink", value: 32, color: "#f59e0b" },
        { label: "Partners", value: 18, color: "#7c3aed" },
      ],
    },
    footer: "Super-app strategy: native core + web/mini-app for rapid feature rollout.",
    narration:
      "Hệ sinh thái mini-app gồm ba trăm bảy mươi ba ứng dụng được cấu hình sẵn, mỗi ứng dụng có mã định danh, URL cơ sở, liên kết deep link và thanh điều hướng riêng. Các nhóm lớn nhất là dịch vụ phổ biến tám mươi tám, giải trí bảy mươi bảy và tài chính sáu mươi lăm. Runtime Griver của Alipay cung cấp IPC và dịch vụ NFC cho mini-app.",
  },
  {
    id: "10-ml",
    kicker: "09 · ON-DEVICE ML",
    title: "OCR, face, barcode — all on-device",
    subtitle: "16 TFLite models (11.7 MB) · ML Kit OCR (Vietnamese) · TrueID eKYC",
    bullets: [
      "Object: object_labeler 3.8 MB · ODT classifier/localizer · FSSD · BlazeFace",
      "OCR: RPN detector · line recognition (LSTM) · layout · language ID",
      "Barcode: SSD MobileNet · 1D extractor/regressor · Barhopper v3",
      "Face: Haar cascade · VFaceLib (OpenCV) · NCNN PicoDet · liveness",
    ],
    chart: {
      title: "Largest TFLite models (MB)",
      bars: [
        { label: "object_labeler", value: 3.6, color: "#dc2626" },
        { label: "ODT classifier", value: 1.6, color: "#2563eb" },
        { label: "ODT localizer", value: 1.2, color: "#0ea5e9" },
        { label: "contours.tfl", value: 1.1, color: "#10b981" },
        { label: "FSSD medium", value: 0.6, color: "#f59e0b" },
      ],
      unit: " MB",
    },
    footer: "On-device inference: faster, offline-capable, privacy-preserving.",
    narration:
      "Mười sáu mô hình TensorFlow Lite tổng mười một phẩy bảy megabyte phục vụ nhận diện vật thể, OCR tiếng Việt, quét barcode và nhận diện khuôn mặt. Mô hình lớn nhất là object labeler ba phẩy tám megabyte. Pipeline OCR của ML Kit gồm detector, line recognition và layout. Tất cả chạy trên thiết bị, giúp giảm tải máy chủ và bảo vệ dữ liệu sinh trắc.",
  },
  {
    id: "11-security",
    kicker: "10 · SECURITY & JS BRIDGE",
    title: "Encryption, biometrics, WebView bridge",
    subtitle: "SQLCipher · SecurityGuardLite · RSA 2048 · ZaloPayJSBridge",
    bullets: [
      "SQLCipher (AES) for local DB · BoringSSL (AES/RSA) for transport",
      "RSA public key + signature in assets/config · PCI DSS seal",
      "JS Bridge: ZaloPayJSBridge / Internal / Alipay — call · callback · trigger",
      "Root detection (ic_warning_rooted) · screen-capture detection · biometric auth",
    ],
    footer: "Bridge is XSS→native surface — allow_urls + deeplink blacklist are critical.",
    narration:
      "Bảo mật gồm SQLCipher mã hóa cơ sở dữ liệu, BoringSSL cho truyền tải, khóa RSA hai nghìn lẻ bốn mươi tám bit trong cấu hình, và xác thực sinh trắc. Cầu nối JavaScript gồm ba bridge: ZaloPay, Internal và Alipay, với giao thức call, callback và trigger. Đây là bề mặt tấn công quan trọng nếu WebView tải URL không tin cậy, vì vậy danh sách URL cho phép là kiểm soát then chốt.",
  },
  {
    id: "12-rn",
    kicker: "11 · REACT NATIVE BUNDLE",
    title: "android.1.zip — 3.6 MB JS bundle",
    subtitle: "Metro bundler · production (__DEV__=false) · Sentry · CDN",
    bullets: [
      "main.jsbundle 3,613,478 bytes · 326 entries in android.1.zip",
      "React Navigation · Redux · Moment · react-native-* · zalopaycomponents",
      "CDN: cdn / qccdn / qccps (.zalopay.com.vn) · Sentry o529616.ingest.sentry.io",
      "bundle.json: external android.1.zip v1.0.0 · internal ba910kal",
    ],
    footer: "Note: localhost:8081 dev URL remains in production bundle — should be removed.",
    narration:
      "Bundle React Native nằm trong android chấm một zip, gồm ba trăm hai mươi sáu mục, trong đó main jsbundle ba phẩy sáu megabyte ở chế độ production. Bundle dùng Metro, React Navigation, Redux và các thư viện react native. Nội dung tải từ CDN zalopay và báo lỗi qua Sentry. Một URL localhost của môi trường dev còn sót lại trong bản production cần được loại bỏ.",
  },
  {
    id: "13-discussion",
    kicker: "12 · DISCUSSION, LIMITS & ETHICS",
    title: "Super-app trade-offs",
    subtitle: "Static-only · ARMv7 only · No dynamic validation · PII redacted",
    bullets: [
      "Strengths: hybrid speed, on-device ML, layered encryption, mini-app scale",
      "Risks: 105 MB size, obfuscation, wide permissions, PII sample in assets",
      "Limits: no jadx decompile, no runtime trace, no signature verification",
      "Ethics: research / education / interoperability only — no exploitation",
    ],
    footer: "client_tracking.json contains sample trip PII — redacted here, should not ship.",
    narration:
      "Thảo luận cho thấy ZaloPay là siêu ứng dụng điển hình: kiến trúc lai giúp ra tính năng nhanh, học máy trên thiết bị và mã hóa nhiều lớp. Tuy nhiên kích thước lớn, tên thư viện bị obfuscate và tập quyền rộng đặt ra thách thức. Nghiên cứu chỉ phân tích tĩnh, chỉ trên ARM v bảy, chưa decompile hay kiểm tra runtime. Dữ liệu cá nhân mẫu trong assets đã được che giấu.",
  },
  {
    id: "14-conclusion",
    kicker: "13 · CONCLUSION & FUTURE WORK",
    title: "What we proved — and what's next",
    subtitle: "6,471 entries inventoried · architecture reconstructed · evidence published",
    bullets: [
      "Proved: packaging, architecture, API, mini-apps, ML, security (static evidence)",
      "Next: jadx + apktool decompile · isolated dynamic analysis (with consent)",
      "Next: cross-version diff · official pentest with VNG · REA automation",
      "Cite: ZP-RE-2026-11-17.0 · INVENTORY.csv · hashes.json · this video (MP4)",
    ],
    footer: "Repo: github.com/johnlikescarrot/- · Branch arena/93d12279-repo · PR #15+",
    narration:
      "Kết luận: chúng tôi đã kiểm kê sáu nghìn bốn trăm bảy mươi mốt mục, khôi phục kiến trúc và công bố bằng chứng đầy đủ. Hướng tiếp theo là decompile bằng jadx, phân tích động trong môi trường cô lập với ủy quyền hợp pháp, so sánh liên phiên bản và kiểm thử xâm nhập chính thức cùng VNG. Video này được xuất MP4 với âm thanh thuyết minh, sẵn sàng trích dẫn học thuật.",
  },
];
