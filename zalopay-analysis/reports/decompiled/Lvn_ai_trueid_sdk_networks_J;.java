package vn.ai.trueid.sdk.networks;
public abstract class J {

    public static void a(org.json.JSONObject p8)
    {
        String v2_0 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE;
        vn.ai.trueid.sdk.models.FaceLivenessConfig v3 = v2_0.getSDKConfig$trueid_release();
        if ((v3.isConfigHeaderJson()) && (v3.configHeaderJson().has("Authorization"))) {
            vn.ai.trueid.sdk.networks.M.c.put("Authorization", v3.configHeaderJson().getString("Authorization"));
        }
        try {
            String v4_4;
            if (!p8.has("requestId")) {
                String v4_3 = p8.optJSONObject("data");
                if (v4_3 == null) {
                    v4_4 = 0;
                } else {
                    v4_4 = v4_3.optString("requestId");
                }
                if (v4_4 != null) {
                    vn.ai.trueid.sdk.networks.M.b.put("requestId", v4_4);
                    android.content.Context v1_1 = p8.optJSONObject("data");
                    if (v1_1 != null) {
                        fk.a.e(v1_1);
                        org.json.JSONObject v5_3 = vn.ai.trueid.sdk.utils.j.h("configSDK", v1_1);
                        if (v5_3 != null) {
                            v2_0.getSdkConfig$trueid_release().setLivenessType(v5_3.optInt("livenessType", v2_0.getSdkConfig$trueid_release().getLivenessType()));
                        }
                        String v8_1 = p8.optJSONObject("c");
                        if (v8_1 == null) {
                            v8_1 = v1_1.optJSONObject("c");
                        }
                        if (v8_1 != null) {
                            String v8_2 = v8_1.optString("d");
                            if (v8_2.length() > 0) {
                                vn.ai.trueid.sdk.TrueShield.ChallengeManager.INSTANCE.solve(vn.ai.trueid.sdk.utils.Helpers.Companion.getContext(), v8_2, v4_4, v3.getAppId());
                            }
                        }
                    }
                } else {
                    v4_4 = "";
                }
            } else {
                v4_4 = p8.getString("requestId");
            }
        } catch (Exception) {
        }
        return;
    }

    public static void b(vn.ai.trueid.sdk.networks.L p6)
    {
        if (!vn.ai.trueid.sdk.networks.M.c.containsKey("Authorization")) {
            io.reactivex.internal.operators.observable.f1 v0_3 = new io.reactivex.internal.operators.observable.f1(19, p6);
            java.util.Map v6_8 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE;
            String v1_1 = v6_8.getSDKConfig$trueid_release();
            if ((v1_1.getAccessTokenEndPoint() != null) && (v1_1.getAccessTokenEndPoint().length() != 0)) {
                java.util.Map v2_6 = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Content-Type", "application/json"), kotlin.TuplesKt.to("User-Agent", vn.ai.trueid.sdk.utils.Helpers.Companion.provideUserAgent()), kotlin.TuplesKt.to("ClientID", v6_8.getSDKConfig$trueid_release().getAppId())}));
                java.util.Map v6_2 = v6_8.getSDKConfig$trueid_release().configHeaderJson();
                if (v6_2.length() > 0) {
                    vn.ai.trueid.sdk.networks.m v3_5 = v6_2.keys();
                    while (v3_5.hasNext()) {
                        String v4_11 = ((String) v3_5.next());
                        v2_6.put(v4_11, v6_2.optString(v4_11));
                    }
                }
                vn.ai.trueid.sdk.networks.M.a.d(v1_1.getAccessTokenEndPoint(), v2_6, kotlin.collections.a0.plus(kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("client_id", v1_1.getAppId()), kotlin.TuplesKt.to("client_secret", v1_1.getAppSecret()), kotlin.TuplesKt.to("grant_type", "client_credentials")})), vn.ai.trueid.sdk.networks.M.b), new vn.ai.trueid.sdk.networks.G(v0_3));
                return;
            } else {
                v0_3.success("");
                return;
            }
        } else {
            vn.ai.trueid.sdk.networks.J.c(new lO.o(17, p6));
            return;
        }
    }

    public static void c(vn.ai.trueid.sdk.networks.L p6)
    {
        String v0_0 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE;
        vn.ai.trueid.sdk.models.FaceLivenessConfig v1 = v0_0.getSDKConfig$trueid_release();
        if ((v1.getCreateRequestEndPoint() != null) && (v1.getCreateRequestEndPoint().length() != 0)) {
            java.util.HashMap v2_1 = new java.util.HashMap(vn.ai.trueid.sdk.networks.M.b);
            String v0_2 = v0_0.getSDKConfig$trueid_release().getCreateRequestEndPoint();
            if (v0_2 != null) {
                vn.ai.trueid.sdk.networks.M.a.d(v0_2, vn.ai.trueid.sdk.networks.M.c, v2_1, new vn.ai.trueid.sdk.networks.I(v1, p6));
            }
            return;
        } else {
            p6.success("");
            return;
        }
    }

    public static Object d()
    {
        String v0_1 = vn.ai.trueid.sdk.networks.M.b.get("requestId");
        if (v0_1 == null) {
            v0_1 = "NO_REQUEST";
        }
        return v0_1;
    }

    public static void e()
    {
        String v1_0 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE;
        String v2_4 = v1_0.getSDKConfig$trueid_release().getDomain();
        if (kotlin.text.StringsKt.isBlank(v2_4)) {
            v2_4 = "https://localhost";
        }
        String v8_1;
        vn.ai.trueid.sdk.networks.M.a = new vn.ai.trueid.sdk.networks.m(v2_4);
        String v2_2 = vn.ai.trueid.sdk.utils.Helpers.Companion;
        vn.ai.trueid.sdk.networks.M.c = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Content-Type", "application/json"), kotlin.TuplesKt.to("User-Agent", v2_2.provideUserAgent()), kotlin.TuplesKt.to("ClientID", v1_0.getSDKConfig$trueid_release().getAppId())}));
        kotlin.Pair v3_9 = kotlin.TuplesKt.to("client", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("flowType", "LIVENESS")));
        kotlin.TuplesKt.to("platform", "android");
        java.util.Map v0_18 = android.os.Build$VERSION.SDK_INT;
        kotlin.TuplesKt.to("os_version", String.valueOf(v0_18));
        kotlin.Pair v7_7 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        if (v7_7.getDeviceInfo() == null) {
            v8_1 = v2_2.getAdvId();
        } else {
            v8_1 = v7_7.getDeviceInfo().getId();
        }
        kotlin.Pair v17;
        String v10_7;
        vn.ai.trueid.sdk.utils.Helpers$Companion v16;
        kotlin.TuplesKt.to("device_id", v8_1);
        kotlin.Pair v9_3 = kotlin.TuplesKt.to("app_version", v2_2.getAppVersion());
        if (v7_7.getDeviceInfo() == null) {
            v10_7 = android.os.Build.MODEL;
            String v13 = android.os.Build.MANUFACTURER;
            v16 = v2_2;
            v17 = v3_9;
            if (!kotlin.text.y.startsWith$default(v10_7.toLowerCase(), v13.toLowerCase(), 0, 2, 0)) {
                v10_7 = androidx.activity.result.e.o(v13, " ", v10_7);
            }
        } else {
            v10_7 = v7_7.getDeviceInfo().getModel();
            v16 = v2_2;
            v17 = v3_9;
        }
        kotlin.Pair v7_0;
        kotlin.TuplesKt.to("device_model", v10_7);
        kotlin.Pair v3_0 = v16.getContext();
        if (v7_7.getDeviceInfo() == null) {
            v7_0 = v16.getAdvId();
        } else {
            v7_0 = v7_7.getDeviceInfo().getId();
        }
        kotlin.Pair v18 = kotlin.TuplesKt.to("id", v7_0);
        StringBuilder v12_1 = new StringBuilder();
        v12_1.append(android.os.Build.MANUFACTURER);
        v12_1.append(" ");
        v12_1.append(android.os.Build.MODEL);
        kotlin.TuplesKt.to("model", v12_1.toString());
        kotlin.Pair v7_4 = android.os.Build.DEVICE;
        String v10_4 = "";
        if (v7_4 == null) {
            v7_4 = "";
        }
        kotlin.TuplesKt.to("name", v7_4);
        kotlin.TuplesKt.to("appId", v3_0.getPackageName());
        kotlin.TuplesKt.to("installId", "none");
        kotlin.TuplesKt.to("platform", "Android");
        String v1_1 = android.os.Build.BRAND;
        if (v1_1 != null) {
            v10_4 = v1_1;
        }
        kotlin.TuplesKt.to("brandName", v10_4);
        kotlin.Pair v3_5 = new StringBuilder();
        v3_5.append(android.os.Build$VERSION.RELEASE);
        v3_5.append(" (SDK ");
        v3_5.append(v0_18);
        v3_5.append(")");
        kotlin.TuplesKt.to("os_version", v3_5.toString());
        kotlin.Pair v7 = v9_3;
        vn.ai.trueid.sdk.networks.M.b = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {v17, kotlin.TuplesKt.to("device", kotlin.collections.a0.mapOf(new kotlin.Pair[] {v18, kotlin.TuplesKt.to("sdkVersion", "3.3.18")})))}));
        return;
    }
}
