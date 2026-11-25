package vn.ai.trueid.sdk.networks;
public abstract class Q {

    public static void a(vn.ai.trueid.sdk.networks.T p6)
    {
        if (!vn.ai.trueid.sdk.networks.U.c.containsKey(wmoi.igob.ggcn(6409))) {
            pO.c v0_1 = new pO.c(13, p6);
            java.util.Map v6_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
            String v2_6 = v6_2.getNFCConfig$trueid_release();
            if (v2_6.getAccesstokenEndPoint().length() != 0) {
                java.util.Map v1_8 = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(wmoi.igob.ggcn(6410), wmoi.igob.ggcn(6411)), kotlin.TuplesKt.to(wmoi.igob.ggcn(6412), vn.ai.trueid.sdk.utils.Helpers.Companion.provideUserAgent()), kotlin.TuplesKt.to(wmoi.igob.ggcn(6413), v6_2.getNFCConfig$trueid_release().getAppId())}));
                java.util.Map v6_6 = v6_2.getNFCConfig$trueid_release().getConfigHeader();
                if (v6_6.length() > 0) {
                    vn.ai.trueid.sdk.networks.m v3_9 = v6_6.keys();
                    while (v3_9.hasNext()) {
                        String v4_15 = ((String) v3_9.next());
                        v1_8.put(v4_15, v6_6.optString(v4_15));
                    }
                }
                vn.ai.trueid.sdk.networks.U.a.d(v2_6.getAccesstokenEndPoint(), v1_8, kotlin.collections.a0.plus(kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(wmoi.igob.ggcn(6414), v2_6.getAppId()), kotlin.TuplesKt.to(wmoi.igob.ggcn(6415), v2_6.getAppSecret()), kotlin.TuplesKt.to(wmoi.igob.ggcn(6416), wmoi.igob.ggcn(6417))})), vn.ai.trueid.sdk.networks.U.b), new vn.ai.trueid.sdk.networks.N(v0_1));
                return;
            } else {
                v0_1.success("");
                return;
            }
        } else {
            pO.c v0_3 = new rv.b(9, p6);
            java.util.Map v6_1 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
            if (v6_1.getNFCConfig$trueid_release().getCreateRequestEndPoint().length() != 0) {
                vn.ai.trueid.sdk.networks.U.a.d(v6_1.getNFCConfig$trueid_release().getCreateRequestEndPoint(), vn.ai.trueid.sdk.networks.U.c, new java.util.HashMap(vn.ai.trueid.sdk.networks.U.b), new m6.c(19, v0_3));
                return;
            } else {
                v0_3.success("");
                return;
            }
        }
    }
}
