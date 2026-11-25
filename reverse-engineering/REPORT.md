# Bao cao Dich ngược (Reverse Engineering) — ZaloPay 11.17.0 (base.apk)

Tep goc: `base.apk`  |  Kich thuoc: 79,359,087 byte  |  SHA-256: `e289d547b45f6224aed8ce7bf72d75ea5545cd50c0e768d1a74a3e8165fe6a03`
Tong so tep trong APK: 6,162

## 1. Thong tin chung (AndroidManifest.xml)
- Ten goi (package): **vn.com.vng.zalopay**
- Phien ban: 11.17.0 (code 1102758)
- minSdk: 26  |  targetSdk: 36  |  maxSdk: None
- So quyen khai bao: 49
- Hoat dong (Activity): 200  |  Dich vu (Service): 34
- Trinh nhan (Receiver): 22  |  Cung cap (Provider): 9

Quyen bat buoc / quan tam:
  - `android.permission.ACCESS_COARSE_LOCATION`
  - `android.permission.ACCESS_FINE_LOCATION`
  - `android.permission.ACCESS_NETWORK_STATE`
  - `android.permission.ACCESS_WIFI_STATE`
  - `android.permission.CAMERA`
  - `android.permission.CHANGE_WIFI_STATE`
  - `android.permission.INTERNET`
  - `android.permission.QUERY_ALL_PACKAGES`
  - `android.permission.READ_BASIC_PHONE_STATE`
  - `android.permission.READ_CONTACTS`
  - `android.permission.READ_EXTERNAL_STORAGE`
  - `android.permission.READ_MEDIA_AUDIO`
  - `android.permission.READ_PHONE_STATE`
  - `android.permission.RECEIVE_BOOT_COMPLETED`
  - `android.permission.RECORD_AUDIO`
  - `android.permission.USE_BIOMETRIC`
  - `android.permission.USE_FINGERPRINT`
  - `android.permission.WAKE_LOCK`
  - `android.permission.WRITE_CONTACTS`
  - `android.permission.WRITE_EXTERNAL_STORAGE`
  - `android.settings.action.MANAGE_OVERLAY_PERMISSION`

## 2. Cac tep DEX (multi-dex) — so lop & phuong thuc (parser thu Python)
Tong so tep .dex: 20
Tong class_defs: 63,827  |  method_ids: 378,040
string_ids: 404,914  |  type_ids: 119,197
field_ids: 317,841  |  proto_ids: 105,000

| File DEX | Kich thuoc | class_defs | method_ids | string_ids | type_ids |
|---|---|---|---|---|---|
| `assets/audience_network.dex` | 4,864,036 | 3,156 | 26,792 | 30,638 | 4,504 |
| `classes.dex` | 8,831,004 | 10,703 | 59,948 | 61,218 | 17,196 |
| `classes10.dex` | 78,204 | 92 | 578 | 920 | 461 |
| `classes11.dex` | 163,420 | 177 | 1,171 | 1,584 | 740 |
| `classes12.dex` | 398,276 | 425 | 2,604 | 3,207 | 1,191 |
| `classes13.dex` | 145,824 | 139 | 997 | 1,381 | 590 |
| `classes14.dex` | 81,748 | 90 | 575 | 961 | 404 |
| `classes15.dex` | 82,152 | 113 | 658 | 1,005 | 436 |
| `classes16.dex` | 15,916 | 20 | 101 | 261 | 116 |
| `classes17.dex` | 208,476 | 243 | 1,311 | 1,847 | 848 |
| `classes18.dex` | 95,920 | 115 | 668 | 1,094 | 553 |
| `classes19.dex` | 719,548 | 777 | 3,781 | 4,159 | 1,782 |
| `classes2.dex` | 16,516,960 | 7,421 | 45,527 | 56,948 | 11,243 |
| `classes3.dex` | 850,600 | 1,081 | 6,354 | 8,010 | 3,827 |
| `classes4.dex` | 9,418,124 | 11,441 | 59,997 | 52,812 | 19,105 |
| `classes5.dex` | 7,357,484 | 7,983 | 49,055 | 56,424 | 14,079 |
| `classes6.dex` | 7,612,432 | 5,475 | 49,201 | 55,274 | 18,399 |
| `classes7.dex` | 10,803,680 | 12,953 | 60,000 | 59,110 | 20,633 |
| `classes8.dex` | 239,036 | 295 | 1,552 | 1,906 | 915 |
| `classes9.dex` | 1,218,908 | 1,128 | 7,170 | 6,155 | 2,175 |

## 3. Thu vien & khoi luong con (trich tu META-INF va assets)
- Thu vien nhan biet tu META-INF (version files): browser, camera, compose, dagger, datastore, google, kotlin, lifecycle, navigation, paging, play, privacysandbox, room, security, window, work
- Khong co thu vien native (.so) trong base.apk.
- Assets quan tam: assets/FastestDet.ncnn.bin, assets/FastestDet.ncnn.param, assets/audience_network.dex, assets/custom_models/object_labeler.tflite, assets/mlkit-google-ocr-models/aksara/aksara_page_layout_analysis_rpn_gcn.binarypb, assets/mlkit-google-ocr-models/aksara/aksara_page_layout_analysis_ti_rpn_gcn.binarypb, assets/mlkit-google-ocr-models/gocr/gocr_models/line_recognition_legacy_mobile/Latn_ctc/optical/assets.extra/LabelMap.pb, assets/mlkit-google-ocr-models/gocr/gocr_models/line_recognition_legacy_mobile/Latn_ctc/optical/conv_model.fb, assets/mlkit-google-ocr-models/gocr/gocr_models/line_recognition_legacy_mobile/Latn_ctc/optical/lstm_model.fb, assets/mlkit-google-ocr-models/gocr/gocr_models/line_recognition_legacy_mobile/Latn_ctc_cpu.binarypb, assets/mlkit-google-ocr-models/gocr/gocr_models/line_recognition_legacy_mobile/tflite_langid.tflite, assets/mlkit-google-ocr-models/gocr/layout/line_clustering_custom_ops/model.tflite, assets/mlkit-google-ocr-models/gocr/layout/line_splitting_custom_ops/model.tflite, assets/mlkit-google-ocr-models/taser/detector/region_proposal_text_detector_tflite_vertical_mbv2_v1.bincfg, assets/mlkit-google-ocr-models/taser/detector/rpn_text_detector_mobile_space_to_depth_quantized_mbv2_v1.tflite, assets/mlkit-google-ocr-models/taser/rpn_text_detection_tflite_mobile_mbv2.binarypb, assets/mlkit-google-ocr-models/taser/segmenter/tflite_script_detector_0.3.bincfg, assets/mlkit-google-ocr-models/taser/segmenter/tflite_script_detector_0.3.conv_model, assets/mlkit-google-ocr-models/taser/segmenter/tflite_script_detector_0.3.lstm_model, assets/mlkit-google-ocr-models/taser/taser_script_identification_tflite_mobile.binarypb, assets/mlkit-google-ocr-models/taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile_engine.binarypb, assets/mlkit-google-ocr-models/taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile_engine_ti.binarypb, assets/mlkit-google-ocr-models/taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile_recognizer.binarypb, assets/mlkit-google-ocr-models/taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile_runner.binarypb, assets/mlkit-google-ocr-models/taser_tflite_gocrlatin_mbv2_scriptid_aksara_layout_gcn_mobile_runner_ti.binarypb
- res/raw quan tam: res/raw/anim_done_unlink_bank.json, res/raw/anim_loading_resume.json, res/raw/anim_loading_unlink_bank.json, res/raw/anim_location_profile_tab.json, res/raw/anim_otp_popup_verifying.json, res/raw/animation_s5_turn_left.json, res/raw/animation_s5_turn_right.json, res/raw/googlemaps.json, res/raw/haarcascade_frontalface_default.xml, res/raw/ic_anim_zlp_loading_auth_state.json, res/raw/ic_anim_zlp_loading_common_state.json, res/raw/ic_anim_zlp_loading_state.json, res/raw/ic_cashier_loading.json, res/raw/ic_cashier_pay_loading.json, res/raw/ic_cashier_pay_loading_v3.json

Co so du lieu / AI nhan dang mat (KYC): tflite (ML Kit), ncnn (FastestDet), trueface/trueid, haarcascade, lbfmodel.
Mang quang cao: audience_network.dex (Facebook Audience Network).
Mang: Retrofit + OkHttp (REST), Dagger (DI tu chieu), Kotlin + Jetpack Compose (giao dien).

## 4. Dien dan & xau ket noi duoc tim thay
Tong so dien dan (http/https): 1380
  - `http://...)`
  - `http://127.0.0.1:`
  - `http://alipay-rmsdeploy-image.cn-hangzhou.alipay.aliyun-inc.com/nebulamng/tiny/wws7wcwjj.jpg`
  - `http://bit.ly/ZaloPayFeedback`
  - `http://g.co/dev/packagevisibility.`
  - `http://goo.gl/8Rd3yj`
  - `http://goo.gl/naFqQk`
  - `http://hostname/?`
  - `http://html.zapps.vn/pay/FAQCenter/suport_bidv.html`
  - `http://iopengw-280.sggz00b.dev.alipay.net/`
  - `http://ns.adobe.com/xap/1.0/`
  - `http://onelink.zalopay.vn/3939374179343473`
  - `http://openauth.alipay.com/`
  - `http://schemas.android.com/aapt`
  - `http://schemas.android.com/apk/res-auto`
  - `http://schemas.android.com/apk/res/android`
  - `http://tfsimg.alipay.com/images/partner/T1IVXXXb0bXXXXXXXX`
  - `http://trueid.net`
  - `http://vnpay.vn`
  - `http://www.android.com/`
  - `http://www.apache.org/licenses/`
  - `http://www.apache.org/licenses/LICENSE-2.0`
  - `http://www.example.com`
  - `http://www.facebook.com/dialog/oauth`
  - `http://www.google-analytics.com`
  - `http://www.google.com`
  - `http://www.jacoco.org/jacoco`
  - `http://www.w3.org/1999/xlink`
  - `http://www.w3.org/2000/svg`
  - `http://www.w3.org/TR/SVG11/feature#`
  - `http://www.w3.org/ns/ttml#parameter`
  - `http://xml.org/sax/features/external-general-entities`
  - `http://xml.org/sax/features/external-parameter-entities`
  - `http://xml.org/sax/properties/lexical-handler`
  - `http://xmlpull.org/v1/doc/features.html#process-docdecl`
  - `http://xmlpull.org/v1/doc/features.html#process-namespaces`
  - `https://%sadrevenue.%s/api/v2/generic/v6.17.4/android?app_id=`
  - `https://%sapp.%s`
  - `https://%sars.%s/api/v2/android/validate_subscription?app_id=`
  - `https://%sars.%s/api/v2/android/validate_subscription_v2?app_id=`
  ... va 1340 dien dan khac (xem analysis.json)

## 5. Lich su phien ban (META-INF/CHANGES)
```
jsoup changelog

*** Release 1.15.1 [PENDING]
  * Change: removed previously deprecated methods and classes (including org.jsoup.safety.Whitelist; use
    org.jsoup.safety.Safelist instead).

  * Improvement: when converting jsoup Documents to W3C Documents in W3CDom, preserve HTML valid attribute names if the
    input document is using the HTML syntax. (Previously, would always coerce using the more restrictive XML syntax.)
    <https://github.com/jhy/jsoup/pull/1648>

  * Improvement: added the :containsWholeText(text) selector, to match against non-normalized Element text. That can be
    useful when elements can only be distinguished by e.g. specific case, or leading whitespace, etc.
    <https://github.com/jhy/jsoup/issues/1636>

  * Improvement: added Element#wholeOwnText() to retrieve the original (non-normalized) ownText of an Element. Also
    added the :containsWholeOwnText(text) selector, to match against that. BR elements are now treated as newlines
    in the wholeText methods.
    <https://github.com/jhy/jsoup/issues/1636>

  * Improvement: added the :matchesWholeText(regex) and :matchesWholeOwnText(regex) selectors, to match against whole
    (non-normalized, case sensitive> element text and own text, respectively.
    <https://github.com/jhy/jsoup/issues/1636>

  * Improvement: when evaluating an XPath query against a context element, the complete document is now visible to the
    query, vs only the context element's sub-tree. This enables support for querie
```

## 6. Ket luan & khuyen bao mat
- Ung dung su dung multi-dex lon (20 tap, >63,827 lop), target Android 14 (SDK 36).
- Nhieu quyen nhay cam (camera, microphone, contacts, SMS, storage, overlay) can kiem tra liet ke ke toan.
- Co inte grasi nhan dang mat/KYC (TrueFace/TrueID, ML Kit, OpenCV) va quang cao Facebook.
- Khong co thu vien native (.so) trong base APK; ma duoc thuc thi chinh thu Java/Kotlin.

---
Duoc tao bo tu phan tich tu dong (khong can Java/apktool). Xem them: analysis.json, apk_inventory.json.