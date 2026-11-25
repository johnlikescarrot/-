import type { SceneTiming } from "./types.js";

export const SCENE_CATALOG: SceneTiming[] = [
  {
    id: "01_title_thesis",
    index: 1,
    title: "Anatomy of a Quad-Hybrid FinTech Super-App",
    subtitle: "Complete Static Reverse Engineering & Empirical Hardening Telemetry — ZaloPay 11.17.0",
    narration:
      "How does a modern mobile financial super-app actually work beneath the surface? Using deterministic static reverse engineering across all 6,824 artifacts of ZaloPay version 11.17.0, we uncover a quad-hybrid architecture spanning 20 Dalvik executables, 27 native binaries, and 373 mini-programs.",
    narrationVi:
      "Một siêu ứng dụng tài chính di động hiện đại vận hành ra sao dưới bề mặt? Qua dịch ngược tĩnh xác định trên toàn bộ 6.824 hiện vật của ZaloPay 11.17.0, chúng tôi hé lộ kiến trúc tứ-lai gồm 20 tệp Dalvik, 27 thư viện native và 373 mini-program.",
    durationSec: 17,
  },
  {
    id: "02_methodology_rea",
    index: 2,
    title: "Reproducible Static Analysis & morluto/rea Pipeline",
    subtitle: "Non-Exploitative Artifact Graphing, Cryptographic Provenance & Deterministic Verification",
    narration:
      "To guarantee full reproducibility without tampering or exploit synthesis, our pipeline invokes morluto's REA artifact-graph engine alongside Androguard and LIEF. Every single ZIP container, split APK, JavaScript bridge, and native shared object is hashed, parsed, and verified with zero integrity contradictions across 6,824 analyzed files.",
    narrationVi:
      "Để đảm bảo khả năng tái lập mà không xâm nhập hay tạo mã khai thác, hệ thống kết hợp công cụ đồ thị hiện vật REA của morluto cùng Androguard và LIEF. Mọi tệp ZIP, APK phân tách, cầu nối JavaScript và thư viện native đều được băm SHA-256 và kiểm chứng với 0 mâu thuẫn toàn vẹn.",
    durationSec: 20,
  },
  {
    id: "03_container_signing",
    index: 3,
    title: "XAPK Modularity, Deduplication & Code-Signing",
    subtitle: "Five Split APKs (186.28 MiB Uncompressed), APK Signature Schemes v2/v3 & 299 Duplicate Groups",
    narration:
      "Decomposing the XAPK bundle reveals five split APKs totaling 186.28 megabytes uncompressed, verified by APK Signature Schemes v2, v3, and Source Stamp v1. Bytecode and native libraries consume two-thirds of total storage, while SHA-256 deduplication exposes 299 identical file groups wasting 937 kilobytes across redundant assets and fonts.",
    narrationVi:
      "Giải phẫu gói XAPK cho thấy 5 APK phân tách tổng dung lượng 186,28 megabyte chưa nén, xác thực bằng chữ ký APK v2, v3 và Source Stamp v1. Mã byte và thư viện native chiếm hai phần ba dung lượng, trong khi băm SHA-256 phát hiện 299 nhóm tệp trùng lặp gây lãng phí 937 kilobyte.",
    durationSec: 22,
  },
  {
    id: "04_manifest_sandbox",
    index: 4,
    title: "Manifest Surface, Permissions & Process Isolation",
    subtitle: "Android 16 (API 36) Target, 267 Components, 49 Permissions & Seven Custom Processes",
    narration:
      "Targeting Android 16 API level 36, the decoded manifest declares 267 components and 49 permissions, including screen-capture detection, biometric authentication, and NFC. Notably, ZaloPay isolates execution across seven custom processes: five sandboxed mini-program workers and two dedicated security isolates, though legacy cleartext compatibility remains enabled in network security configuration.",
    narrationVi:
      "Nhắm mục tiêu Android 16 API 36, tệp Manifest khai báo 267 thành phần và 49 quyền hệ thống bao gồm phát hiện chụp màn hình, sinh trắc học và NFC. ZaloPay cô lập thực thi qua 7 tiến trình riêng biệt, dù cấu hình mạng vẫn duy trì tương thích cleartext kế thừa.",
    durationSec: 23,
  },
  {
    id: "05_dex_ecosystem",
    index: 5,
    title: "Multi-DEX Bytecode & R8 Obfuscation Telemetry",
    subtitle: "20 DEX Files, 63,827 Classes, 378,040 Methods & 69.64% Identifier Minification",
    narration:
      "Inside the Dalvik layer, 20 DEX files pack 63,827 classes and 378,040 method references, including a dynamically loaded advertising DEX hidden inside assets. Three shards hit the 60,000-method splitter ceiling, while 69.6 percent of all classes undergo R8 short-name obfuscation alongside major ecosystems from Google, BouncyCastle, Ant Group, and Jetpack Compose.",
    narrationVi:
      "Ở tầng Dalvik, 20 tệp DEX chứa 63.827 lớp và 378.040 phương thức, gồm cả một tệp DEX quảng cáo nạp động trong assets. Ba phân mảnh chạm trần 60.000 phương thức, và 69,6% lớp được làm rối tên ngắn R8 song hành cùng các hệ sinh thái Google, BouncyCastle, Ant Group và Jetpack Compose.",
    durationSec: 24,
  },
  {
    id: "06_native_hardening",
    index: 6,
    title: "Native ELF Hardening & 6-Generation Clang Archeology",
    subtitle: "27 ARMv7 Shared Libraries (54.79 MiB), Exploit Mitigations & Compiler Fragmentation",
    narration:
      "Auditing all 27 ARMv7 native libraries exposes six distinct Android NDK Clang generations co-existing simultaneously, from Clang 7.0.2 up to Clang 21.0.0. While PIE and NX reach 100 percent coverage, Full RELRO drops to 85.2 percent due to four unhardened Crashlytics binaries, and FORTIFY_SOURCE covers only 55.6 percent of shared objects.",
    narrationVi:
      "Kiểm toán toàn bộ 27 thư viện ARMv7 phát lộ 6 thế hệ trình biên dịch NDK Clang cùng tồn tại, từ Clang 7.0.2 đến Clang 21.0.0. PIE và NX đạt phủ sóng 100%, nhưng Full RELRO giảm xuống 85,2% do bốn tệp Crashlytics và FORTIFY_SOURCE chỉ đạt 55,6%.",
    durationSec: 23,
  },
  {
    id: "07_hybrid_griver",
    index: 7,
    title: "Quad-Hybrid React Native & Ant Group Griver Runtimes",
    subtitle: "2,346 Metro JS Modules & 373 Sandboxed Mini-Programs via appx-ng Worker Bridges",
    narration:
      "Beyond native Android, ZaloPay embeds dual JavaScript runtimes. A 3.6-megabyte React Native Metro bundle ships 2,346 modules for transaction history and banking workflows, while Ant Group's Griver engine orchestrates 373 mini-programs through pre-bundled appx-ng archives and multi-worker JavaScript bridges verified by REA static AST analysis.",
    narrationVi:
      "Ngoài Android native, ZaloPay nhúng song song hai môi trường JavaScript: gói React Native Metro 3,6 MB với 2.346 mô-đun cho lịch sử giao dịch và ngân hàng, cùng nhân Griver của Ant Group điều phối 373 mini-program qua appx-ng và cầu nối worker được REA kiểm chứng AST.",
    durationSec: 22,
  },
  {
    id: "08_edge_ai_ekyc",
    index: 8,
    title: "On-Device Edge AI & Vietnamese eKYC Vision Stack",
    subtitle: "51 ML Model Artifacts (18.83 MiB) + 26.28 MiB Computer Vision Runtimes",
    narration:
      "Equally striking is the 45-megabyte on-device artificial intelligence footprint. Fifty-one model artifacts—spanning Google ML Kit OCR, Tencent NCNN, OpenCV facial landmarks, and custom TensorFlow Lite detectors—execute barcode scanning, national ID optical recognition, and blink-based liveness verification completely offline without cloud round-trips.",
    narrationVi:
      "Đáng chú ý không kém là hệ thống AI trên thiết bị nặng tới 45 megabyte: 51 tệp mô hình từ Google ML Kit OCR, Tencent NCNN, OpenCV đến TensorFlow Lite thực hiện quét mã QR, đọc OCR căn cước công dân và kiểm tra thực thể sống hoàn toàn ngoại tuyến.",
    durationSec: 22,
  },
  {
    id: "09_conclusions_citation",
    index: 9,
    title: "Architectural Tradeoffs & Reproducible Citation Benchmark",
    subtitle: "Open Deterministic Evidence Graphs, Dataset Schema & Google Scholar Citation Package",
    narration:
      "These empirical measurements demonstrate that modern FinTech super-apps balance feature velocity against security through layered isolation—yet third-party native dependencies remain the weakest link in binary hardening. By releasing our complete deterministic dataset, TypeScript visualization engine, and REA provenance graphs, this work establishes a reproducible benchmark for mobile FinTech architecture research.",
    narrationVi:
      "Các phép đo thực nghiệm cho thấy siêu ứng dụng FinTech cân bằng tốc độ phát triển và bảo mật qua cô lập đa tầng, nhưng thư viện bên thứ ba vẫn là mắt xích yếu nhất. Bộ dữ liệu xác định, công cụ dựng hình TypeScript và đồ thị REA mở đường cho các nghiên cứu trích dẫn tiếp theo.",
    durationSec: 23,
  },
];
