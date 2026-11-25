# 02 — Manifest & attack surface

Application class `jwbmqnsll.xvndbxlk`, allowBackup=false, largeHeap=true,
requestLegacyExternalStorage=true, resizeableActivity=false,
networkSecurityConfig set, dataExtractionRules set.

Exported: 30 components —
activities 14, aliases 2,
services 6, receivers 8,
providers 0.

Notable exported activities include SplashScreenActivityV2, QRReceiverActivity,
VNeID verification, eKYC (FPT), TrueID liveness, Compose PreviewActivity and
instrumentation bootstrap activities (test scaffolding shipped in release).

Deep links (11 filters): schemes zalopay, zalopay-1, zalopay.api.v2,
zalopay-vnpay, fbconnect, tagmanager.c…, https hosts go.zalopay.vn,
onelink.zalopay.vn, km.zalopay.vn, zalopay.vn.

Raw evidence: `reports/raw/manifest-findings.json`.
