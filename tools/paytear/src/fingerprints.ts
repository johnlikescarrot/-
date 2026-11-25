/**
 * Supply-chain fingerprinting: maps DEX package prefixes, native libraries,
 * assets and manifest facts onto known SDKs / vendors, with evidence labels.
 */

export type Evidence = "dex-package" | "native-library" | "asset" | "manifest" | "resource";

export interface SdkFingerprint {
  key: string;
  name: string;
  vendor: string;
  category: "payments" | "analytics" | "ads" | "security" | "ml-vision" | "ui" | "platform" | "crypto" | "networking" | "storage" | "mini-program";
  prefixes?: string[];
  nativeLibs?: string[];
  assets?: string[];
}

export const SDK_CATALOG: SdkFingerprint[] = [
  { key: "mpaas-ariver", name: "mPaaS / Ariver mini-program runtime", vendor: "Alibaba Cloud", category: "mini-program", prefixes: ["com/alibaba/ariver/", "com/alibaba/griver/", "com/alipay/plus/"], nativeLibs: [], assets: ["assets/Griver/"] },
  { key: "iap-connect", name: "IAP Connect (cross-border acquiring)", vendor: "Alipay / Ant Group", category: "payments", prefixes: ["com/iap/ac/", "com/iap/android/"], assets: ["assets/iapconnect_config_full"] },
  { key: "visa-cbp", name: "Visa Cardholder Benefits Platform / Visa Payment SDK", vendor: "Visa", category: "payments", prefixes: ["com/visa/cbp/", "com/visa/visapaysdk/"], assets: ["assets/com/visa/cbp/"] },
  { key: "facebook-audience-network", name: "Meta Audience Network (ads)", vendor: "Meta", category: "ads", prefixes: ["com/facebook/ads/"], assets: ["assets/audience_network.dex"] },
  { key: "appsflyer", name: "AppsFlyer attribution", vendor: "AppsFlyer", category: "analytics", prefixes: ["com/appsflyer/"], assets: ["assets/com/appsflyer/internal/"] },
  { key: "firebase", name: "Firebase (Messaging, Crashlytics, Remote Config)", vendor: "Google", category: "platform", prefixes: ["com/google/firebase/"], nativeLibs: ["libcrashlytics.so", "libcrashlytics-common.so"] },
  { key: "google-ads", name: "Google Mobile Ads", vendor: "Google", category: "ads", prefixes: ["com/google/android/gms/ads/"], assets: ["assets/containers/GTM-PC9W5BW.json"] },
  { key: "mlkit-ocr", name: "ML Kit Text Recognition / Barcode", vendor: "Google", category: "ml-vision", prefixes: ["com/google/mlkit/"], nativeLibs: ["libbarhopper_v3.so", "libmlkit_google_ocr_pipeline.so", "libmlkitcommonpipeline.so"], assets: ["assets/mlkit-google-ocr-models/"] },
  { key: "mediapipe-face", name: "MediaPipe face detector", vendor: "Google", category: "ml-vision", prefixes: [], nativeLibs: ["libface_detector_v2_jni.so", "libimage_processing_util_jni.so"] },
  { key: "picodet-ncnn", name: "PicoDet + NCNN on-device detection", vendor: "PaddlePaddle / Tencent NCNN", category: "ml-vision", nativeLibs: ["libpicodet_ncnn.so"], assets: ["assets/FastestDet.ncnn.bin", "assets/FastestDet.ncnn.param"] },
  { key: "sqlcipher", name: "SQLCipher encrypted database", vendor: "Zetetic", category: "storage", prefixes: ["net/sqlcipher/"], nativeLibs: ["libsqlcipher.so"] },
  { key: "bouncycastle", name: "Bouncy Castle JCE provider", vendor: "Legion of the Bouncy Castle", category: "crypto", prefixes: ["org/bouncycastle/"] },
  { key: "nimbus-jose", name: "Nimbus JOSE+JWT", vendor: "Federated Identity", category: "crypto", prefixes: ["com/nimbusds/jose/"] },
  { key: "tink", name: "Google Tink", vendor: "Google", category: "crypto", prefixes: ["com/google/crypto/tink/"] },
  { key: "okhttp", name: "OkHttp", vendor: "Square", category: "networking", prefixes: ["okhttp3/", "okio/"] },
  { key: "compose", name: "Jetpack Compose", vendor: "Google", category: "ui", prefixes: ["androidx/compose/"] },
  { key: "camerax", name: "CameraX", vendor: "Google", category: "ui", prefixes: ["androidx/camera/"] },
  { key: "lottie", name: "Lottie animations", vendor: "Airbnb", category: "ui", prefixes: ["com/airbnb/lottie/"] },
  { key: "rive", name: "Rive runtime", vendor: "Rive", category: "ui", nativeLibs: ["librive-android.so"], assets: ["assets/riv/"] },
  { key: "glide", name: "Glide image loader", vendor: "Bumptech", category: "ui", prefixes: ["com/bumptech/glide/"], nativeLibs: ["libimagepipeline.so", "libnative-imagetranscoder.so", "libnative-filters.so"] },
  { key: "media3", name: "AndroidX Media3 / ExoPlayer", vendor: "Google", category: "ui", prefixes: ["androidx/media3/"] },
  { key: "glance", name: "Glance app widgets", vendor: "Google", category: "ui", prefixes: ["androidx/glance/"] },
  { key: "react-native", name: "React Native (legacy JS bundle)", vendor: "Meta", category: "platform", prefixes: ["com/facebook/react/"], assets: ["assets/android.1.zip", "assets/main.jsbundle"] },
  { key: "trueid", name: "TrueID eKYC / liveness", vendor: "VNG", category: "security", prefixes: ["vn/ai/trueid/"], nativeLibs: ["libtrueidlib.so", "libVFaceLib.so"] },
  { key: "fpt-ekyc", name: "FPT.AI eKYC", vendor: "FPT", category: "security", prefixes: ["com/fpt/fci/"] },
  { key: "alipay-securityguard", name: "Alipay SecurityGuard Lite", vendor: "Ant Group", category: "security", prefixes: ["com/alipay/plus/security/"], nativeLibs: ["libsecurity_lite.so"] },
  { key: "vng-bshield", name: "VNG BShield (RASP / tamper defence)", vendor: "VNG", category: "security", prefixes: ["vn/com/vng/bcell/"], nativeLibs: ["libohxbee.so", "libtaibmcf.so", "libfgcpkjvqe.so", "libljsfrcwmw.so", "liba97d.so"], assets: ["assets/bshield.dat", "assets/taibmcf.dat", "assets/fgcpkjvqe.dat"] },
  { key: "protobuf", name: "Protocol Buffers", vendor: "Google", category: "platform", prefixes: ["com/google/protobuf/"] },
  { key: "androidx-test", name: "AndroidX Test (shipped in release build)", vendor: "Google", category: "platform", prefixes: ["androidx/test/"] },
  { key: "exoplayer-ads", name: "IMA / media ads", vendor: "Google", category: "ads", prefixes: ["com/google/ads/interactivemedia/"] },
];

export interface SdkMatch {
  key: string;
  name: string;
  vendor: string;
  category: SdkFingerprint["category"];
  evidence: Evidence;
  value: string;
  classes?: number;
  bytes?: number;
}

export function matchSdks(input: {
  classNames: Iterable<string>;
  nativeLibs: Iterable<string>;
  assets: Iterable<string>;
}): SdkMatch[] {
  const classes = [...input.classNames];
  const libs = [...input.nativeLibs];
  const assets = [...input.assets];
  const out: SdkMatch[] = [];
  for (const sdk of SDK_CATALOG) {
    for (const prefix of sdk.prefixes ?? []) {
      const prefixPath = prefix.startsWith("L") ? prefix : prefix;
      let count = 0;
      for (const c of classes) {
        const p = c.startsWith("L") ? c.slice(1) : c;
        if (p.startsWith(prefixPath)) count++;
      }
      if (count > 0) {
        out.push({ key: sdk.key, name: sdk.name, vendor: sdk.vendor, category: sdk.category, evidence: "dex-package", value: prefix, classes: count });
      }
    }
    for (const lib of sdk.nativeLibs ?? []) {
      if (libs.some((l) => l.endsWith(lib))) {
        out.push({ key: sdk.key, name: sdk.name, vendor: sdk.vendor, category: sdk.category, evidence: "native-library", value: lib });
      }
    }
    for (const a of sdk.assets ?? []) {
      if (assets.some((x) => x.startsWith(a) || x.includes(a))) {
        out.push({ key: sdk.key, name: sdk.name, vendor: sdk.vendor, category: sdk.category, evidence: "asset", value: a });
      }
    }
  }
  return out;
}

export const DANGEROUS_PERMISSIONS: Record<string, { group: string; note: string; risk: "high" | "medium" | "low" }> = {
  "android.permission.READ_CONTACTS": { group: "contacts", note: "read contact list", risk: "high" },
  "android.permission.WRITE_CONTACTS": { group: "contacts", note: "modify contact list", risk: "high" },
  "android.permission.READ_CALENDAR": { group: "calendar", note: "read calendar events", risk: "medium" },
  "android.permission.WRITE_CALENDAR": { group: "calendar", note: "write calendar events", risk: "medium" },
  "android.permission.CAMERA": { group: "camera", note: "camera frames (KYC / QR / card scan)", risk: "high" },
  "android.permission.RECORD_AUDIO": { group: "microphone", note: "microphone capture", risk: "high" },
  "android.permission.ACCESS_FINE_LOCATION": { group: "location", note: "precise location", risk: "high" },
  "android.permission.ACCESS_COARSE_LOCATION": { group: "location", note: "approximate location", risk: "high" },
  "android.permission.READ_PHONE_STATE": { group: "phone", note: "telephony state / identifiers", risk: "high" },
  "android.permission.READ_BASIC_PHONE_STATE": { group: "phone", note: "basic telephony state", risk: "medium" },
  "android.permission.READ_EXTERNAL_STORAGE": { group: "storage", note: "read shared storage", risk: "medium" },
  "android.permission.WRITE_EXTERNAL_STORAGE": { group: "storage", note: "write shared storage", risk: "medium" },
  "android.permission.READ_MEDIA_AUDIO": { group: "storage", note: "read audio media", risk: "low" },
  "android.permission.QUERY_ALL_PACKAGES": { group: "package-visibility", note: "enumerate installed apps", risk: "high" },
  "android.permission.SYSTEM_ALERT_WINDOW": { group: "overlay", note: "draw over other apps", risk: "high" },
  "android.settings.action.MANAGE_OVERLAY_PERMISSION": { group: "overlay", note: "request overlay capability", risk: "high" },
  "android.permission.NFC": { group: "nfc", note: "NFC / card emulation", risk: "medium" },
  "android.permission.POST_NOTIFICATIONS": { group: "notifications", note: "post notifications", risk: "low" },
  "android.permission.RECEIVE_BOOT_COMPLETED": { group: "boot", note: "auto-start after boot", risk: "medium" },
  "com.google.android.gms.permission.AD_ID": { group: "advertising", note: "advertising identifier", risk: "medium" },
  "android.permission.DETECT_SCREEN_CAPTURE": { group: "privacy", note: "detect screen recording", risk: "low" },
  "android.permission.USE_BIOMETRIC": { group: "biometric", note: "biometric authentication", risk: "low" },
  "android.permission.USE_FINGERPRINT": { group: "biometric", note: "legacy fingerprint API", risk: "low" },
  "android.permission.FOREGROUND_SERVICE": { group: "background", note: "long-running foreground work", risk: "medium" },
  "com.zing.zalo.permission.ACCESS_THIRD_PARTY_APP_AUTHORIZATION": { group: "sso", note: "Zalo SSO authorisation", risk: "medium" },
  "com.samsung.android.providers.context.permission.WRITE_USE_APP_FEATURE_SURVEY": { group: "oem-analytics", note: "Samsung usage survey", risk: "low" },
  "com.samsung.android.mapsagent.permission.READ_APP_INFO": { group: "oem-analytics", note: "Samsung app info", risk: "low" },
  "com.huawei.appmarket.service.commondata.permission.GET_COMMON_DATA": { group: "oem-analytics", note: "Huawei AppGallery data", risk: "low" },
};
