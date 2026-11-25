/**
 * Timeline & script for Zalopay 11.17.0 research video.
 * Each slide: title, subtitle, bullets, narration (Vietnamese, for TTS).
 * Designed for 1920x1080, ~20-25s per slide.
 */
export interface Slide {
  id: string;
  title: string;
  subtitle: string;
  bullets: string[];
  narration: string;
  durationSec: number;
}

export const VIDEO_TITLE = "Zalopay 11.17.0 — Phân tích tĩnh chuyên sâu";
export const VIDEO_SUBTITLE = "Reverse Engineering học thuật • XAPK • Android • TypeScript Video";

export const SLIDES: Slide[] = [
  {
    id: "01-title",
    title: "Zalopay 11.17.0",
    subtitle: "Phân tích tĩnh chuyên sâu — XAPK • 105 MB • 5 split APK",
    bullets: [
      "Package: vn.com.vng.zalopay",
      "Version: 11.17.0 (1102758) • minSdk 26 • targetSdk 36",
      "Phương pháp: Static analysis • Manifest • DEX • SDK fingerprinting",
    ],
    narration:
      "Xin chào. Đây là nghiên cứu phân tích tĩnh chuyên sâu ứng dụng Zalopay phiên bản mười một chấm mười bảy. Chúng tôi giải nén XAPK một trăm lẻ năm megabyte, gồm năm split APK, và phân tích manifest, mã DEX, thư viện native và kiến trúc, theo quy trình chuẩn của công cụ rea và OWASP MASVS. Toàn bộ nghiên cứu chỉ ở mức cấu trúc phục vụ học thuật và phòng thủ.",
    durationSec: 24,
  },
  {
    id: "02-xapk",
    title: "Cấu trúc XAPK",
    subtitle: "Base 79,3 MB • Native 24,8 MB • Ngôn ngữ • DPI",
    bullets: [
      "Base APK: 6.162 entries • 79.359.087 bytes",
      "config.armeabi_v7a: 28 thư viện .so • 24,8 MB",
      "config.en / vi: tài nguyên ngôn ngữ • config.xhdpi: 258 drawable",
      "Chữ ký APK v2/v3 • Phân phối qua App Bundle (bundletool)",
    ],
    narration:
      "XAPK chứa năm split. Base APK bảy mươi chín megabyte với sáu nghìn một trăm sáu mươi hai entries. Split armeabi v bảy a chứa hai mươi tám thư viện native. Các split ngôn ngữ và mật độ hiển thị cho thấy phân phối qua Android App Bundle. Chữ ký dùng scheme v hai v ba hiện đại.",
    durationSec: 22,
  },
  {
    id: "03-dex",
    title: "Kiến trúc DEX",
    subtitle: "19 tệp DEX • Multidex • Obfuscation R8",
    bullets: [
      "19 DEX: classes.dex + classes2–19 + audience_network.dex",
      "Lớn nhất: classes2.dex 16,5 MB • classes7.dex 10,8 MB",
      "Cụm package: Alibaba Griver • Visa SDK • IAP • Compose • CameraX",
      "Package obfuscated: com/xq/cxzmoiwuegvp — dấu hiệu R8 mạnh",
    ],
    narration:
      "Base APK chứa mười chín tệp DEX, tổng khoảng bảy mươi chín megabyte. Tệp lớn nhất là classes hai, mười sáu phẩy năm megabyte. Phân tích tiền tố package cho thấy cụm Alibaba Griver, Visa SDK, In-App Purchase, Jetpack Compose và CameraX. Sự xuất hiện của package ngẫu nhiên cho thấy obfuscation R8 mạnh.",
    durationSec: 22,
  },
  {
    id: "04-components",
    title: "Thành phần Android",
    subtitle: "212 Activities • 37 Services • 19 Receivers • 39 Permissions",
    bullets: [
      "Activities: Home, Cashier, ScanCode, Withdraw, eKYC, OTP, NFC, MiniApp",
      "Services: FCM, WorkManager, Griver IPC, VisaHostApduService",
      "Permissions nhạy cảm: Camera, Mic, Vị trí, Biometric, NFC, QUERY_ALL_PACKAGES",
      "14 Intent actions: MAIN, VIEW, SEND, MY_PACKAGE_REPLACED…",
    ],
    narration:
      "Manifest ghi nhận hai trăm mười hai activities, ba mươi bảy services và mười chín receivers. Các luồng chính gồm trang chủ, thu ngân, quét mã, rút tiền, eKYC, OTP, NFC và mini-app. Ba mươi chín permissions bao gồm camera, micro, vị trí chính xác, sinh trắc học và NFC, phù hợp ví điện tử đa năng nhưng cần minh bạch.",
    durationSec: 24,
  },
  {
    id: "05-stack",
    title: "Stack công nghệ",
    subtitle: "Kotlin • Compose • Dagger • Room • Firebase • ML Kit • Griver",
    bullets: [
      "Kotlin + Compose + Material + CameraX + Glance + Lottie + Rive",
      "Dagger • Room • OkHttp • Retrofit • RxJava • Protobuf • Nimbus JOSE",
      "Firebase • Play Services • ML Kit • Billing • TagManager",
      "Visa SDK • TrueID eKYC • FPT eKYC • VNPay • AppsFlyer • Facebook",
    ],
    narration:
      "Fingerprint qua META-INF cho thấy stack Kotlin, Jetpack Compose, Dagger, Room, OkHttp, Retrofit và Firebase. Tích hợp sâu ML Kit, Alibaba Griver mini-app, Visa SDK, TrueID eKYC và VNPay. Đây là kiến trúc super-app điển hình với nhiều SDK chuyên biệt.",
    durationSec: 22,
  },
  {
    id: "06-native",
    title: "Thư viện native",
    subtitle: "28 .so • SQLCipher • ML Vision • Obfuscation native",
    bullets: [
      "ML/Vision: face_detector, MLKit OCR, picodet_ncnn, barhopper_v3",
      "Bảo mật: libsqlcipher.so 3,5 MB — mã hóa DB • libsecurity_lite.so",
      "Obfuscated: libtaibmcf.so 12,6 MB • libfgcpkjvqe.so 6,5 MB",
      "Face/eKYC: libVFaceLib.so • libtrueidlib.so • librive-android.so",
    ],
    narration:
      "Split armeabi chứa hai mươi tám thư viện native. Nhóm ML gồm face detector, OCR và NCNN. Thư viện SQLCipher cho thấy cơ sở dữ liệu được mã hóa. Các thư viện tên ngẫu nhiên như libtaibmcf mười hai phẩy sáu megabyte cho thấy obfuscation native mạnh, tăng chi phí phân tích tĩnh.",
    durationSec: 22,
  },
  {
    id: "07-security",
    title: "Đánh giá bảo mật",
    subtitle: "Điểm mạnh phòng thủ • Điểm cần giám sát",
    bullets: [
      "Mạnh: SQLCipher • Biometric • NFC • Screen-capture detection • Target SDK 36",
      "Mạnh: eKYC liveness • Obfuscation 2 lớp (DEX + native)",
      "Lưu ý: Quyền rộng • WebView/JS bridge • Config chứa khóa • SDK tracking",
      "Khuyến nghị: Tối thiểu hóa quyền • Allowlist WebView • Cập nhật thư viện",
    ],
    narration:
      "Về bảo mật, điểm mạnh gồm mã hóa cơ sở dữ liệu, sinh trắc học, NFC, phát hiện chụp màn hình và obfuscation hai lớp. Điểm cần giám sát gồm bề mặt quyền rộng, WebView và JS bridge của mini-app, config chứa khóa công khai, và các SDK tracking. Khuyến nghị là tối thiểu hóa quyền và cập nhật thư viện thường xuyên.",
    durationSec: 24,
  },
  {
    id: "08-conclusion",
    title: "Kết luận",
    subtitle: "Super-app • Kiến trúc hiện đại • Tư duy phòng thủ tốt",
    bullets: [
      "6.162 entries • 212 activities • 19 DEX • 28 native libs",
      "Kotlin + Compose + Dagger + Room + SQLCipher — hiệu năng & bảo mật",
      "Nghiên cứu học thuật, phòng thủ — không trích xuất khóa hay logic thanh toán",
      "Tham khảo: rea • OWASP MASVS • Android Security • Griver Docs",
    ],
    narration:
      "Kết luận, Zalopay mười một chấm mười bảy là super-app với sáu nghìn entries, hai trăm mười hai activities, mười chín DEX và hai mươi tám thư viện native. Kiến trúc Kotlin Compose hiện đại, obfuscation mạnh và cơ chế bảo mật tốt. Nghiên cứu này phục vụ học thuật và phòng thủ, không trích xuất khóa hay logic thanh toán. Cảm ơn bạn đã theo dõi.",
    durationSec: 24,
  },
];

export const TOTAL_DURATION = SLIDES.reduce((s, x) => s + x.durationSec, 0);
