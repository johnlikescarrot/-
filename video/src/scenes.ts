/**
 * Scene definitions for the Zalopay 11.17.0 reverse-engineering video.
 * All numbers come from docs/analysis.json (tools/analyze_apk.py).
 * Narration is Vietnamese; each scene maps to assets/audio/scene-XX.mp3
 * (generated with TTS) and to an SRT subtitle block.
 */

export interface BarDatum {
  label: string;
  value: number;
  display: string;
}

export interface Scene {
  id: string;
  kicker: string;
  title: string;
  subtitle: string;
  bullets: string[];
  chartTitle?: string;
  chart?: BarDatum[];
  bigNumber?: string;
  bigLabel?: string;
  narration: string;
}

export const VIDEO_TITLE = "Dịch ngược tĩnh toàn diện Zalopay 11.17.0 (XAPK)";
export const VIDEO_SUBTITLE =
  "Nghiên cứu tái tạo được · TypeScript → MP4 · 1920×1080 · H.264/AAC";

export const SCENES: Scene[] = [
  {
    id: "01-title",
    kicker: "NGHIÊN CỨU TÁI TẠO ĐƯỢC · 2026-11-25",
    title: "Dịch ngược tĩnh toàn diện\nZalopay 11.17.0 (XAPK)",
    subtitle:
      "Từ gói XAPK 105 MB đến bản đồ kiến trúc hoàn chỉnh — phương pháp, bằng chứng và video học thuật.",
    bullets: [
      "Package vn.com.vng.zalopay · 11.17.0 (1102758) · minSdk 26 · targetSdk 36",
      "Pipeline Python stdlib-only → docs/analysis.json (máy đọc được)",
      "Video TypeScript: SVG → PNG → MP4 (H.264 + AAC), phụ đề SRT",
    ],
    bigNumber: "105.6 MB",
    bigLabel: "XAPK · 5 split APK",
    narration:
      "Xin chào. Đây là nghiên cứu dịch ngược tĩnh toàn diện đối với gói Zalopay phiên bản mười một chấm mười bảy, định dạng X A P K, dung lượng khoảng một trăm lẻ năm megabyte. Chúng tôi sẽ đi từ kiểm kê gói, qua mã D E X, manifest, thư viện native, cấu hình runtime, hệ sinh thái mini-app, cho đến trí tuệ nhân tạo trên thiết bị và bảo mật. Mọi con số trong video đều tái tạo được bằng pipeline Python đi kèm.",
  },
  {
    id: "02-method",
    kicker: "PHƯƠNG PHÁP LUẬN",
    title: "Kiểm kê → Trích xuất → Phân tích\n→ Bằng chứng kiểm chứng",
    subtitle:
      "Lấy cảm hứng từ morluto/rea (reverse engineer anything with agents).",
    bullets: [
      "ZIP → XAPK → 5 split APK (base + armeabi-v7a + en + vi + xhdpi)",
      "Giải mã binary XML strings, DEX strings, JNI symbols — không cần Java",
      "Tham chiếu tương đương: jadx (DEX→Java), apktool/rea (resources), aapt2",
      "Đạo đức: chỉ phân tích tĩnh phòng thủ, không vượt qua kiểm soát thanh toán",
    ],
    bigNumber: "4 bước",
    bigLabel: "inventory · extract · analyze · evidence",
    narration:
      "Phương pháp của chúng tôi gồm bốn bước: kiểm kê, trích xuất, phân tích và bằng chứng kiểm chứng, theo triết lý của khung rea. Thay vì phụ thuộc vào Java và jadx, pipeline Python chỉ dùng thư viện chuẩn để giải mã chuỗi nhị phân trong manifest, chuỗi trong D E X và ký hiệu J N I. Cách này nhẹ, minh bạch và tái tạo được ở mọi môi trường.",
  },
  {
    id: "03-xapk",
    kicker: "KIỂM KÊ GÓI",
    title: "XAPK v2: 1 base + 4 splits",
    subtitle: "Phân phối kiểu App Bundle — code chung tách khỏi ABI, locale và density.",
    bullets: [
      "Base vn.com.vng.zalopay.apk ~79.4 MB · 6.162 entries · ~136 MB uncompressed",
      "config.armeabi-v7a.apk ~24.8 MB · 27 thư viện .so (chỉ 32-bit trong gói này)",
      "config.vi / config.en: resources.arsc tiếng Việt / tiếng Anh",
      "config.xhdpi.apk ~1.2 MB: drawables mật độ xhdpi",
    ],
    bigNumber: "6.162",
    bigLabel: "entries trong base APK",
    narration:
      "Gói X A P K gồm một base và bốn split. Base chứa mã và tài nguyên chung, khoảng bảy mươi chín megabyte với hơn sáu nghìn mục. Split armeabi v bảy a chứa hai mươi bảy thư viện native. Hai split ngôn ngữ chứa tài nguyên tiếng Việt và tiếng Anh, còn split x h d p i chứa hình ảnh theo mật độ điểm ảnh.",
  },
  {
    id: "04-dex",
    kicker: "MÃ DALVIK / KOTLIN",
    title: "20 tệp DEX · ~69.7 MB",
    subtitle: "Kotlin + Compose, OkHttp, gRPC/Protobuf, Room, WorkManager, Dagger.",
    bullets: [
      "Lớn nhất: classes2.dex ~16.5 MB · classes7.dex ~10.8 MB · classes4.dex ~9.4 MB",
      "~793.000 chuỗi ASCII: SDK nổi bật — Griver, Facebook, ML Kit, Visa, Firebase",
      "~233 chuỗi URL: api.zalopay.vn, sapi, openapi, social, graph.zaloapp.com",
      "Crypto: AES/GCM, AES/CBC, AndroidKeyStore, BiometricPrompt, Tink",
    ],
    chartTitle: "Kích thước DEX lớn nhất (MB)",
    chart: [
      { label: "classes2", value: 16.5, display: "16.5" },
      { label: "classes7", value: 10.8, display: "10.8" },
      { label: "classes4", value: 9.4, display: "9.4" },
      { label: "classes.dex", value: 8.8, display: "8.8" },
      { label: "classes6", value: 7.6, display: "7.6" },
      { label: "classes5", value: 7.4, display: "7.4" },
    ],
    narration:
      "Base chứa hai mươi tệp D E X, tổng khoảng sáu mươi chín phẩy bảy megabyte. Các module lớn nhất là classes hai, classes bảy và classes bốn, tương ứng các cụm thanh toán, Visa, V N Pay và M L Kit. Khai thác chuỗi cho thấy khoảng bảy trăm chín mươi ba nghìn chuỗi, với các dấu hiệu SDK như Griver, Facebook, M L Kit và Firebase, cùng các domain chính thức của Zalopay và Zalo.",
  },
  {
    id: "05-manifest",
    kicker: "ANDROID MANIFEST",
    title: "~212 Activity · 50+ quyền",
    subtitle: "Từ home, eKYC, cashier đến mini-app và app widgets.",
    bullets: [
      "Home/onboarding: HomeActivity (homev5), Splash, Onboarding",
      "Auth/eKYC: Login, OTP, MainEKyc, Liveness, IdentityCard, VNeID, SmartOTP",
      "Cashier: CashierV3, ResultPageV3, PayByWallet, QuickPay, ScanQR, NFC",
      "Quyền nhạy cảm: Camera, Mic, Vị trí, Danh bạ, Sinh trắc học, NFC, QUERY_ALL_PACKAGES",
    ],
    bigNumber: "~212",
    bigLabel: "Activity trong binary XML",
    narration:
      "Manifest nhị phân chứa khoảng hai trăm mười hai activity, bao phủ home, xác thực và e K Y C, thu ngân, liên kết ngân hàng, quét Q R, N F C và mini-app. Hơn năm mươi quyền được khai báo, trong đó có camera, micro, vị trí, danh bạ, sinh trắc học và N F C, phản ánh nhu cầu xác minh danh tính và thanh toán không chạm.",
  },
  {
    id: "06-native",
    kicker: "THƯ VIỆN NATIVE (ARMv7)",
    title: "27 thư viện .so",
    subtitle: "ML Kit, OpenCV, TrueID, NCNN, Rive, SQLCipher + lớp bảo vệ obfuscated.",
    bullets: [
      "libtaibmcf.so 12.6 MB · libfgcpkjvqe.so 6.5 MB — tên ngẫu nhiên, ít JNI (anti-tamper)",
      "OCR: libmlkit_google_ocr_pipeline · Face: libface_detector_v2 · QR: libbarhopper_v3",
      "eKYC: libVFaceLib (OpenCV) · libtrueidlib (SmartOTP/X25519) · libohxbee (liveness)",
      "Khác: libpicodet_ncnn (PicoDet) · librive-android · libsqlcipher · libsecurity_lite",
    ],
    chartTitle: "Native libs lớn nhất (MB)",
    chart: [
      { label: "libtaibmcf", value: 12.6, display: "12.6" },
      { label: "libmlkitcommon", value: 6.9, display: "6.9" },
      { label: "libmlkit_ocr", value: 6.8, display: "6.8" },
      { label: "libfgcpkjvqe", value: 6.5, display: "6.5" },
      { label: "libface_det", value: 5.4, display: "5.4" },
      { label: "libsqlcipher", value: 3.6, display: "3.6" },
    ],
    narration:
      "Split armeabi v bảy a chứa hai mươi bảy thư viện native. Hai thư viện lớn nhất có tên ngẫu nhiên và rất ít ký hiệu J N I rõ ràng, cho thấy lớp bảo vệ thương mại chống can thiệp. Phần còn lại là M L Kit O C R và nhận diện khuôn mặt, True I D, liveness, N C N N PicoDet, Rive, S Q L Cipher và Security Guard Lite của Alipay.",
  },
  {
    id: "07-config",
    kicker: "CẤU HÌNH RUNTIME",
    title: "gRPC · 43 ngân hàng · 57 keys",
    subtitle: "zalopay_config.json điều khiển API, KYC, deep-link và WebView allow-list.",
    bullets: [
      "api_route=https · connector_mode=grpc · ping 30s · timeout 60s",
      "API: um/* (token/profile/OTP) · v001/tpe/* (order/trans/card) · cpscdn (bank list)",
      "43 ngân hàng (VTB, VCB, BIDV…) · OTP 6 chữ số/60s · giới hạn KYC theo cấp độ",
      "WebView chỉ cho phép *.zalopay.vn · deep-link zalopay://launch/f/ · RSA-2048 ký cấu hình",
    ],
    bigNumber: "43",
    bigLabel: "ngân hàng trong banks_information",
    narration:
      "Tệp cấu hình runtime có năm mươi bảy khóa chính. Kênh chính là g R P C trên H T T P S, với các nhóm A P I quản lý người dùng và thanh toán bên thứ ba. Danh sách ngân hàng có bốn mươi ba ngân hàng, giới hạn giao dịch theo cấp độ K Y C, mã O T P sáu chữ số, và danh sách trắng nghiêm ngặt cho WebView chỉ cho phép các domain Zalopay.",
  },
  {
    id: "08-miniapp",
    kicker: "HỆ SINH THÁI MINI-APP",
    title: "373 mini-apps + React Native",
    subtitle: "Super-app lai: native shell + RN + Alipay Griver + web mini-apps.",
    bullets: [
      "Danh mục: Dịch vụ phổ biến 88 · Giải trí 77 · Tài chính 65 · Ăn uống 32",
      "316/373 dùng social.zalopay.vn · deep-link zalopay://launch/app/{id}",
      "React Native: main.jsbundle ~3.6 MB (production, không source-map) · Sentry",
      "Bridges: ZaloPayJSBridge (call/callback/subscribe) · AlipayJSBridge qua MessagePort",
    ],
    chartTitle: "Mini-apps theo danh mục (top 6)",
    chart: [
      { label: "Dịch vụ phổ biến", value: 88, display: "88" },
      { label: "Giải trí - Cộng đồng", value: 77, display: "77" },
      { label: "Tài chính", value: 65, display: "65" },
      { label: "Ăn uống", value: 32, display: "32" },
      { label: "Dịch vụ đối tác", value: 18, display: "18" },
      { label: "Mua sắm", value: 15, display: "15" },
    ],
    narration:
      "Hệ sinh thái có ba trăm bảy mươi ba mini-app, tập trung ở dịch vụ phổ biến, giải trí và tài chính. Hầu hết chạy trên social chấm zalopay chấm vn và mở bằng deep-link nội bộ. Bên trong còn có bundle React Native ba phẩy sáu megabyte và hai cầu JavaScript: ZaloPay J S Bridge cho WebView và Alipay J S Bridge cho mini-program Griver.",
  },
  {
    id: "09-ml-security",
    kicker: "ML / eKYC & BẢO MẬT",
    title: "AI trên thiết bị + defense-in-depth",
    subtitle: "OCR, face/liveness, NFC chip, VNeID — kèm mã hoá và ký số.",
    bullets: [
      "OCR: ML Kit + TFLite (14 models) · NCNN FastestDet/PicoDet · object_labeler.tflite",
      "Face/liveness: Face Detector v2, VFaceLib, TrueID, liveness extras",
      "Bảo mật: HTTPS+gRPC, SQLCipher, Keystore+Biometric, SmartOTP X25519, obfuscation",
      "Rủi ro cần kiểm chứng động: bridge rộng, SDK tracking, quyền rộng, fixture client_tracking",
    ],
    bigNumber: "14",
    bigLabel: "mô hình TFLite trên thiết bị",
    narration:
      "Trí tuệ nhân tạo trên thiết bị bao phủ toàn bộ vòng e K Y C: O C R giấy tờ, nhận diện khuôn mặt và liveness, chip C C C D qua N F C và xác minh V N e I D. Về bảo mật, bằng chứng tĩnh cho thấy H T T P S, g R P C, S Q L Cipher, Keystore, sinh trắc học và ký cấu hình R S A. Các rủi ro còn lại như cầu JavaScript rộng và SDK theo dõi cần kiểm chứng động.",
  },
  {
    id: "10-conclusion",
    kicker: "KẾT LUẬN & TÁI TẠO",
    title: "Super-app lai, bằng chứng mở",
    subtitle: "Mọi kết quả đều tái tạo được — hãy trích dẫn và kiểm chứng.",
    bullets: [
      "Tái tạo: python3 tools/analyze_apk.py → docs/analysis.json",
      "Video: cd video && npm install && npm run build → out/*.mp4",
      "Trích dẫn: docs/paper.md · docs/references.bib · CITATION.cff",
      "Lưu ý: trích dẫn phụ thuộc bình duyệt & lập chỉ mục — không có video nào đảm bảo số 1",
    ],
    bigNumber: "MP4",
    bigLabel: "1920×1080 · H.264 + AAC · SRT",
    narration:
      "Tóm lại, Zalopay là một super-app lai với vỏ native, React Native, mini-program Griver và ba trăm bảy mươi ba mini-app web, được bảo vệ bởi nhiều lớp mã hoá và xác thực. Toàn bộ pipeline, dữ liệu và video đều mở để tái tạo và trích dẫn. Cảm ơn bạn đã theo dõi nghiên cứu này.",
  },
];
