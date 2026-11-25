package vn.ai.trueid.sdk.networks;
public abstract class d {

    public static void a(vn.ai.trueid.sdk.networks.f p6)
    {
        if (!vn.ai.trueid.sdk.networks.g.c.containsKey("Authorization")) {
            j9.q v0_3 = new j9.q(18, p6);
            java.util.Map v6_11 = vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE;
            String v2_0 = v6_11.getSDKConfig$trueid_release();
            if (v2_0.getAccessTokenEndPoint().length() != 0) {
                java.util.Map v1_4 = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Content-Type", "application/json"), kotlin.TuplesKt.to("User-Agent", vn.ai.trueid.sdk.utils.Helpers.Companion.provideUserAgent()), kotlin.TuplesKt.to("ClientID", v6_11.getSDKConfig$trueid_release().getAppId())}));
                java.util.Map v6_4 = v6_11.getSDKConfig$trueid_release().configHeaderJson();
                if (v6_4.length() > 0) {
                    vn.ai.trueid.sdk.networks.m v3_8 = v6_4.keys();
                    while (v3_8.hasNext()) {
                        String v4_12 = ((String) v3_8.next());
                        v1_4.put(v4_12, v6_4.optString(v4_12));
                    }
                }
                vn.ai.trueid.sdk.networks.g.a.d(v2_0.getAccessTokenEndPoint(), v1_4, kotlin.collections.a0.plus(kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("client_id", v2_0.getAppId()), kotlin.TuplesKt.to("client_secret", v2_0.getAppSecret()), kotlin.TuplesKt.to("grant_type", "client_credentials")})), vn.ai.trueid.sdk.networks.g.b), new vn.ai.trueid.sdk.networks.a(v0_3));
                return;
            } else {
                v0_3.success("");
                return;
            }
        } else {
            j9.q v0_5 = new io.reactivex.internal.operators.observable.f1(17, p6);
            java.util.Map v6_10 = vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE;
            String v2_4 = v6_10.getSDKConfig$trueid_release();
            if (v2_4.getCreateRequestEndPoint().length() != 0) {
                vn.ai.trueid.sdk.networks.g.a.d(v6_10.getSDKConfig$trueid_release().getCreateRequestEndPoint(), vn.ai.trueid.sdk.networks.g.c, new java.util.HashMap(vn.ai.trueid.sdk.networks.g.b), new vn.ai.trueid.sdk.networks.c(v2_4, v0_5));
                return;
            } else {
                v0_5.success("");
                return;
            }
        }
    }
}
