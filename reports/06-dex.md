# 06 — DEX triage (20 dex, androguard lightweight parse)

Totals: classes 63,827, methods 378,040.
Per-dex breakdown in `reports/raw/dex-report.json`.

Top packages: com.google (7,761), org.bouncycastle (6,112), com.alibaba (4,821), com.facebook (3,622), com.iap (1,599), com.visa (1,370), androidx.media3 (1,198), vn.com (1,054), androidx.camera (813), vn.ai (689), androidx.compose (504), com.alipay (493), com.appsflyer (430), com.vnpay (388), com.nimbusds (361), com.xq (299), androidx.appcompat (273), io.reactivex (266), androidx.core (258), org.jmrtd (237).

String signals: zalopay 3,355, signature 1,511, certificate 1,310, protobuf 1,053, firebase 953, appsflyer 733, hmac 527, https:// 406, secret 393, root 335, password 301, adjust 201, http:// 52, pinning 37.
Top URL hosts: simg.zalopay.com.vn×45, zalopay.vn×20, social.zalopay.vn×13, play.google.com×11, appx×10, render.alipay.com×10, appx-ng×9, developer.android.com×8, mnap-director.zalopay.vn×8, www.w3.org×8, www.google.com×8, github.com×7, api.zalopay.vn×7, firebase.google.com×7, googleads.g.doubleclick.net×7.

Pinning strings include "Certificate pinning failure!",
"Cannot find certificatest. Disable ssl pinning.",
`EVENT_SSLPINNING_*`; deeplinks include `zalopay://launch/f/changepin`,
`zalopay://launch/f/resetpin`, mini-app launch URLs.

Symbol families + 200 sampled native-method refs in raw report.
Method: per-DEX `androguard.core.dex.DEX` parse (full AnalyzeAPK OOM-killed,
exit 137 — documented limitation).
