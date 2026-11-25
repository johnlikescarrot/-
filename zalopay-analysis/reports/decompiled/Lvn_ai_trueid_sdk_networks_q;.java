package vn.ai.trueid.sdk.networks;
public final class q {

    public static final vn.ai.trueid.sdk.models.ResultValue a(String p3, org.json.JSONObject p4)
    {
        if ((!p4.has(p3)) || (p4.isNull(p3))) {
            return new vn.ai.trueid.sdk.models.ResultValue("", "");
        } else {
            String v3_1 = p4.getJSONObject(p3);
            return new vn.ai.trueid.sdk.models.ResultValue(v3_1.optString("code", ""), v3_1.optString("value", ""));
        }
    }

    public static final String b(String p2, org.json.JSONObject p3)
    {
        if ((!p3.has(p2)) || (p3.isNull(p2))) {
            return "";
        } else {
            return p3.getJSONObject(p2).optString("value", "");
        }
    }

    public static final java.util.Map c(String p6, String p7, java.util.List p8)
    {
        String v0_1 = new byte[4];
        Mg.e.a.getClass();
        Mg.e.b.g().nextBytes(v0_1);
        java.nio.charset.Charset v1_3 = java.nio.ByteBuffer.allocate(8).order(java.nio.ByteOrder.LITTLE_ENDIAN).putLong((System.currentTimeMillis() * 1000000)).array();
        if (kotlin.jvm.internal.Intrinsics.areEqual(p7, "1")) {
            p6 = p6.substring(0, 52);
        }
        java.util.Map v6_3 = android.util.Base64.encodeToString(kotlin.collections.w.plus(kotlin.collections.w.plus(v0_1, v1_3), p6.getBytes(kotlin.text.Charsets.UTF_8)), 2);
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("apiVersion", p7), kotlin.TuplesKt.to("nonce", v6_3), kotlin.TuplesKt.to("signature", Pk.b.b(p7, v6_3, p8))}));
    }

    public static void d(vn.ai.trueid.sdk.networks.t p6)
    {
        if (!vn.ai.trueid.sdk.networks.u.c.containsKey("Authorization")) {
            n8.i v0_4 = new m6.c(17, p6);
            java.util.Map v6_10 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
            String v2_1 = v6_10.getEkycConfig$trueid_release();
            if (v2_1.getConfigEndPoint().getAccesstoken().length() != 0) {
                java.util.HashMap v1_6 = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Content-Type", "application/json"), kotlin.TuplesKt.to("User-Agent", vn.ai.trueid.sdk.utils.Helpers.Companion.provideUserAgent()), kotlin.TuplesKt.to("ClientID", v6_10.getEkycConfig$trueid_release().getAppId())}));
                java.util.Map v6_4 = v6_10.getConfigHeader$trueid_release();
                if (v6_4.length() > 0) {
                    vn.ai.trueid.sdk.networks.n v3_13 = v6_4.keys();
                    while (v3_13.hasNext()) {
                        String v4_9 = ((String) v3_13.next());
                        v1_6.put(v4_9, v6_4.optString(v4_9));
                    }
                }
                vn.ai.trueid.sdk.networks.q.h(vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getEkycConfig$trueid_release().getConfigEndPoint().getAccesstoken(), v1_6, kotlin.collections.a0.plus(kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("client_id", v2_1.getAppId()), kotlin.TuplesKt.to("client_secret", v2_1.getAppSecret()), kotlin.TuplesKt.to("grant_type", "client_credentials")})), vn.ai.trueid.sdk.networks.u.b), new vn.ai.trueid.sdk.networks.n(v0_4));
                return;
            } else {
                v0_4.success("");
                return;
            }
        } else {
            if (kotlin.jvm.internal.Intrinsics.areEqual(vn.ai.trueid.sdk.networks.q.f(), "NO_REQUEST")) {
                n8.i v0_8 = new n8.i(14, p6);
                java.util.Map v6_11 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
                String v2_0 = v6_11.getEkycConfig$trueid_release();
                if (v2_0.getConfigEndPoint().getCreaterequest().length() != 0) {
                    vn.ai.trueid.sdk.networks.q.h(v6_11.getEkycConfig$trueid_release().getConfigEndPoint().getCreaterequest(), vn.ai.trueid.sdk.networks.u.c, new java.util.HashMap(vn.ai.trueid.sdk.networks.u.b), new vn.ai.trueid.sdk.networks.o(v2_0, v0_8));
                    return;
                } else {
                    v0_8.success("");
                    return;
                }
            } else {
                p6.success(vn.ai.trueid.sdk.networks.q.f());
                return;
            }
        }
    }

    public static void e(vn.ai.trueid.sdk.networks.s p5)
    {
        String v0_3 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getEkycConfig$trueid_release().getConfigEndPoint().getOcr();
        java.util.HashMap v1_1 = new java.util.HashMap();
        v1_1.putAll(vn.ai.trueid.sdk.networks.u.b);
        vn.ai.trueid.sdk.networks.q.h(v0_3, vn.ai.trueid.sdk.networks.u.c, v1_1, new io.reactivex.internal.operators.observable.f1(18, p5));
        return;
    }

    public static String f()
    {
        String v0_1 = vn.ai.trueid.sdk.networks.u.b.get("request_id");
        if (v0_1 == null) {
            v0_1 = "NO_REQUEST";
        }
        return ((String) v0_1);
    }

    public static void g()
    {
        kotlin.Pair v7_1;
        String v1_1 = vn.ai.trueid.sdk.utils.Helpers.Companion;
        StringBuilder v3_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        vn.ai.trueid.sdk.networks.u.c = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Content-Type", "application/json"), kotlin.TuplesKt.to("User-Agent", v1_1.provideUserAgent()), kotlin.TuplesKt.to("ClientID", v3_0.getEkycConfig$trueid_release().getAppId())}));
        kotlin.Pair v4_3 = kotlin.TuplesKt.to("platform", "android");
        java.util.Map v0_11 = android.os.Build$VERSION.SDK_INT;
        kotlin.TuplesKt.to("os_version", String.valueOf(v0_11));
        if (v3_0.getDeviceInfo() == null) {
            v7_1 = v1_1.getAdvId();
        } else {
            v7_1 = v3_0.getDeviceInfo().getId();
        }
        kotlin.Pair v9_5;
        kotlin.Pair v16;
        kotlin.TuplesKt.to("device_id", v7_1);
        String v8_2 = kotlin.TuplesKt.to("app_version", v1_1.getAppVersion());
        if (v3_0.getDeviceInfo() == null) {
            v9_5 = android.os.Build.MODEL;
            kotlin.Pair v12_0 = android.os.Build.MANUFACTURER;
            v16 = v1_1;
            if (!kotlin.text.y.startsWith$default(v9_5.toLowerCase(), v12_0.toLowerCase(), 0, 2, 0)) {
                v9_5 = androidx.activity.result.e.o(v12_0, " ", v9_5);
            }
        } else {
            v9_5 = v3_0.getDeviceInfo().getModel();
            v16 = v1_1;
        }
        StringBuilder v3_10;
        kotlin.TuplesKt.to("device_model", v9_5);
        kotlin.Pair v9_7 = v16.getContext();
        if (v3_0.getDeviceInfo() == null) {
            v3_10 = v16.getAdvId();
        } else {
            v3_10 = v3_0.getDeviceInfo().getId();
        }
        kotlin.Pair v12_1 = kotlin.TuplesKt.to("id", v3_10);
        kotlin.Pair v13_2 = new StringBuilder();
        v13_2.append(android.os.Build.MANUFACTURER);
        v13_2.append(" ");
        v13_2.append(android.os.Build.MODEL);
        kotlin.TuplesKt.to("model", v13_2.toString());
        StringBuilder v3_2 = android.os.Build.DEVICE;
        String v10_1 = "";
        if (v3_2 == null) {
            v3_2 = "";
        }
        kotlin.TuplesKt.to("name", v3_2);
        kotlin.TuplesKt.to("type", "MOBILE");
        kotlin.TuplesKt.to("appId", v9_7.getPackageName());
        kotlin.TuplesKt.to("installId", "none");
        kotlin.TuplesKt.to("platform", "Android");
        String v2_0 = android.os.Build.BRAND;
        if (v2_0 != null) {
            v10_1 = v2_0;
        }
        kotlin.TuplesKt.to("brandName", v10_1);
        StringBuilder v3_8 = new StringBuilder();
        v3_8.append(android.os.Build$VERSION.RELEASE);
        v3_8.append(" (SDK ");
        v3_8.append(v0_11);
        v3_8.append(")");
        kotlin.TuplesKt.to("os_version", v3_8.toString());
        kotlin.Pair v7 = v8_2;
        vn.ai.trueid.sdk.networks.u.b = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {v4_3, kotlin.TuplesKt.to("mfa_enroll_device_info", kotlin.collections.a0.mapOf(new kotlin.Pair[] {v12_1, kotlin.TuplesKt.to("sdkVersion", "3.3.18")})))}));
        return;
    }

    public static void h(String p10, java.util.Map p11, java.util.Map p12, vn.ai.trueid.sdk.networks.k p13)
    {
        boolean v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        vn.ai.trueid.sdk.navigation.TrueIDEKYCCaptureListener v2 = v0_0.getEkycCaptureListener$trueid_release();
        if ((!v0_0.isEkycCaptureMode$trueid_release()) || (v2 == null)) {
            vn.ai.trueid.sdk.networks.u.a.d(p10, p11, p12, p13);
            return;
        } else {
            new android.os.Handler(android.os.Looper.getMainLooper()).post(new F.k0(v2, p10, p12, new vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback(new Mk.c(p13, 1), new Mk.c(p13, 2), 0, 4, 0), 22));
            return;
        }
    }
}
