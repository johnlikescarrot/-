package vn.ai.trueid.sdk.networks;
public abstract class B {

    public static final void a(java.util.concurrent.atomic.AtomicInteger p2, int p3, kotlin.jvm.functions.Function0 p4)
    {
        if (p2.incrementAndGet() == p3) {
            new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new CS.E(8, p4), 100);
        }
        return;
    }

    public static void b(vn.ai.trueid.sdk.networks.D p6)
    {
        if (!vn.ai.trueid.sdk.networks.E.c.containsKey("Authorization")) {
            n8.i v0_3 = new n8.i(15, p6);
            java.util.Map v6_8 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
            String v1_1 = v6_8.getFaceSDKConfig$trueid_release();
            if (v1_1.getAccessTokenEndPoint().length() != 0) {
                java.util.Map v2_5 = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Content-Type", "application/json"), kotlin.TuplesKt.to("User-Agent", vn.ai.trueid.sdk.utils.Helpers.Companion.provideUserAgent()), kotlin.TuplesKt.to("ClientID", v6_8.getFaceSDKConfig$trueid_release().getAppId())}));
                java.util.Map v6_2 = v6_8.getFaceSDKConfig$trueid_release().configHeaderJson();
                if (v6_2.length() > 0) {
                    vn.ai.trueid.sdk.networks.v v3_5 = v6_2.keys();
                    while (v3_5.hasNext()) {
                        String v4_9 = ((String) v3_5.next());
                        v2_5.put(v4_9, v6_2.optString(v4_9));
                    }
                }
                vn.ai.trueid.sdk.networks.B.e(v1_1.getAccessTokenEndPoint(), v2_5, kotlin.collections.a0.plus(kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("client_id", v1_1.getAppId()), kotlin.TuplesKt.to("client_secret", v1_1.getAppSecret()), kotlin.TuplesKt.to("grant_type", "client_credentials")})), vn.ai.trueid.sdk.networks.E.b), new vn.ai.trueid.sdk.networks.v(v0_3));
                return;
            } else {
                v0_3.success("");
                return;
            }
        } else {
            vn.ai.trueid.sdk.networks.B.c(new rv.b(8, p6));
            return;
        }
    }

    public static void c(vn.ai.trueid.sdk.networks.D p5)
    {
        String v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        vn.ai.trueid.sdk.models.FaceEnrollConfig v1 = v0_0.getFaceSDKConfig$trueid_release();
        java.util.HashMap v2_5 = vn.ai.trueid.sdk.networks.E.b.get("requestId");
        if (v2_5 == null) {
            v2_5 = "";
        }
        if ((v1.getCreateRequestEndPoint().length() != 0) && (v2_5.toString().length() <= 0)) {
            vn.ai.trueid.sdk.networks.B.e(v0_0.getFaceSDKConfig$trueid_release().getCreateRequestEndPoint(), vn.ai.trueid.sdk.networks.E.c, new java.util.HashMap(vn.ai.trueid.sdk.networks.E.b), new vn.ai.trueid.sdk.networks.x(v1, p5));
            return;
        } else {
            p5.success("");
            return;
        }
    }

    public static void d()
    {
        kotlin.Pair v8_1;
        String v1_1 = vn.ai.trueid.sdk.utils.Helpers.Companion;
        StringBuilder v3_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        vn.ai.trueid.sdk.networks.E.c = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Content-Type", "application/json"), kotlin.TuplesKt.to("User-Agent", v1_1.provideUserAgent()), kotlin.TuplesKt.to("ClientID", v3_0.getFaceSDKConfig$trueid_release().getAppId())}));
        kotlin.Pair v4_3 = kotlin.TuplesKt.to("client", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("flowType", "ENROLL")));
        kotlin.TuplesKt.to("platform", "android");
        java.util.Map v0_15 = android.os.Build$VERSION.SDK_INT;
        kotlin.TuplesKt.to("os_version", String.valueOf(v0_15));
        if (v3_0.getDeviceInfo() == null) {
            v8_1 = v1_1.getAdvId();
        } else {
            v8_1 = v3_0.getDeviceInfo().getId();
        }
        kotlin.Pair v10_8;
        kotlin.Pair v17;
        kotlin.Pair v16;
        kotlin.TuplesKt.to("device_id", v8_1);
        String v9_2 = kotlin.TuplesKt.to("app_version", v1_1.getAppVersion());
        if (v3_0.getDeviceInfo() == null) {
            v10_8 = android.os.Build.MODEL;
            kotlin.Pair v13_1 = android.os.Build.MANUFACTURER;
            v16 = v1_1;
            v17 = v3_0;
            if (!kotlin.text.y.startsWith$default(v10_8.toLowerCase(), v13_1.toLowerCase(), 0, 2, 0)) {
                v10_8 = androidx.activity.result.e.o(v13_1, " ", v10_8);
            }
        } else {
            v10_8 = v3_0.getDeviceInfo().getModel();
            v16 = v1_1;
            v17 = v3_0;
        }
        kotlin.Pair v10_11;
        kotlin.TuplesKt.to("device_model", v10_8);
        StringBuilder v3_8 = v16.getContext();
        if (v17.getDeviceInfo() == null) {
            v10_11 = v16.getAdvId();
        } else {
            v10_11 = v17.getDeviceInfo().getId();
        }
        kotlin.Pair v13_0 = kotlin.TuplesKt.to("id", v10_11);
        kotlin.Pair v14_1 = new StringBuilder();
        v14_1.append(android.os.Build.MANUFACTURER);
        v14_1.append(" ");
        v14_1.append(android.os.Build.MODEL);
        kotlin.TuplesKt.to("model", v14_1.toString());
        kotlin.Pair v10_2 = android.os.Build.DEVICE;
        String v11_1 = "";
        if (v10_2 == null) {
            v10_2 = "";
        }
        kotlin.TuplesKt.to("name", v10_2);
        kotlin.TuplesKt.to("appId", v3_8.getPackageName());
        kotlin.TuplesKt.to("installId", "none");
        kotlin.TuplesKt.to("platform", "Android");
        String v2_0 = android.os.Build.BRAND;
        if (v2_0 != null) {
            v11_1 = v2_0;
        }
        kotlin.TuplesKt.to("brandName", v11_1);
        StringBuilder v3_5 = new StringBuilder();
        v3_5.append(android.os.Build$VERSION.RELEASE);
        v3_5.append(" (SDK ");
        v3_5.append(v0_15);
        v3_5.append(")");
        kotlin.TuplesKt.to("os_version", v3_5.toString());
        kotlin.Pair v8 = v9_2;
        vn.ai.trueid.sdk.networks.E.b = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {v4_3, kotlin.TuplesKt.to("device", kotlin.collections.a0.mapOf(new kotlin.Pair[] {v13_0, kotlin.TuplesKt.to("sdkVersion", "3.3.18")})))}));
        return;
    }

    public static void e(String p10, java.util.Map p11, java.util.Map p12, vn.ai.trueid.sdk.networks.k p13)
    {
        boolean v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        vn.ai.trueid.sdk.navigation.TrueIDFaceEnrollCaptureListener v2 = v0_0.getEnrollCaptureListener$trueid_release();
        if ((!v0_0.isEnrollCaptureMode$trueid_release()) || (v2 == null)) {
            vn.ai.trueid.sdk.networks.E.a.d(p10, p11, p12, p13);
            return;
        } else {
            new android.os.Handler(android.os.Looper.getMainLooper()).post(new F.k0(v2, p10, p12, new vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback(new Mk.c(p13, 3), new Mk.c(p13, 4), 0, 4, 0), 23));
            return;
        }
    }
}
