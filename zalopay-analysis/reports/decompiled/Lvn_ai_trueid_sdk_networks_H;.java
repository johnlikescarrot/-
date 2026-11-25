package vn.ai.trueid.sdk.networks;
public final class H implements vn.ai.trueid.sdk.networks.L {
    public final synthetic java.util.Map a;
    public final synthetic java.util.List b;
    public final synthetic java.util.List c;
    public final synthetic vn.ai.trueid.sdk.navigation.FaceLiveness$startFaceSDK$1$complete$1 d;

    public H(java.util.Map p1, java.util.List p2, java.util.List p3, vn.ai.trueid.sdk.navigation.FaceLiveness$startFaceSDK$1$complete$1 p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p2)
    {
        this.d.fail(p2);
        return;
    }

    public final void success(String p14)
    {
        j9.q v3_2;
        String v14_2 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE.getSDKConfig$trueid_release();
        java.util.HashMap v0_5 = this.a;
        vn.ai.trueid.sdk.networks.m v1_7 = kotlin.collections.a0.toMutableMap(v0_5);
        j9.q v3_16 = v0_5.get("faceImages");
        if (!(v3_16 instanceof java.util.List)) {
            v3_2 = 0;
        } else {
            v3_2 = ((java.util.List) v3_16);
        }
        if (v3_2 == null) {
            v3_2 = kotlin.collections.E.emptyList();
        }
        j9.q v3_14;
        Object v4_8 = vn.ai.trueid.sdk.networks.J.d().toString();
        v14_2.getAppId();
        v1_7.put("faceImages", vn.ai.trueid.sdk.utils.j.j(v4_8, v3_2));
        vn.ai.trueid.sdk.networks.M.c.put("User-Agent", androidx.compose.foundation.layout.G0.m(vn.ai.trueid.sdk.utils.Helpers.Companion.provideUserAgent(), "|in:", kotlin.collections.CollectionsKt.l(((Iterable) this.b), "-", 0, 0, 0, 62), "|out:", kotlin.collections.CollectionsKt.l(((Iterable) this.c), "-", 0, 0, 0, 62)));
        if (!kotlin.jvm.internal.Intrinsics.areEqual(v14_2.getSignVersion(), "2")) {
            j9.q v3_13 = ((String) vn.ai.trueid.sdk.networks.M.c.get("Authorization"));
            if (v3_13 == null) {
                v3_13 = "";
            }
            v3_14 = kotlin.text.y.replace$default(v3_13, "Bearer ", "", 0, 4, 0);
        } else {
            v3_14 = vn.ai.trueid.sdk.networks.J.d().toString();
        }
        j9.q v3_1;
        Object v4_15 = v14_2.getSignVersion();
        Object v5_10 = ((java.util.List) v1_7.get("faceImages"));
        j9.q v3_17 = wk.a.c(v3_14, v4_15);
        v1_7.putAll(kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("apiVersion", v4_15), kotlin.TuplesKt.to("nonce", v3_17), kotlin.TuplesKt.to("signature", Pk.b.b(v4_15, v3_17, v5_10))})));
        if (v14_2.getLivenessType() != 3) {
            v3_1 = 0;
        } else {
            v3_1 = 1;
        }
        java.util.HashMap v0_1;
        if (v3_1 == null) {
            v0_1 = new java.util.HashMap();
            v0_1.putAll(v1_7);
            v0_1.putAll(vn.ai.trueid.sdk.networks.M.b);
        } else {
            v0_1 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("requestId", java.util.UUID.randomUUID().toString()), kotlin.TuplesKt.to("faceImages", v0_5.get("faceImages"))}));
        }
        String v14_1;
        if (v3_1 == null) {
            v14_1 = v14_2.getLivenessEndPoint();
        } else {
            v14_1 = "https://https://pad.trueid.ai/face/v1.3/sdk-verify";
        }
        vn.ai.trueid.sdk.networks.m v1_5 = new java.util.LinkedHashMap();
        j9.q v3_4 = v0_1.entrySet().iterator();
        while (v3_4.hasNext()) {
            Object v4_5 = ((java.util.Map$Entry) v3_4.next());
            Object v5_2 = ((String) v4_5.getKey());
            if ((!kotlin.jvm.internal.Intrinsics.areEqual(v5_2, "faceImages")) && (!kotlin.jvm.internal.Intrinsics.areEqual(v5_2, "imageFace"))) {
                v1_5.put(v4_5.getKey(), v4_5.getValue());
            }
        }
        vn.ai.trueid.sdk.networks.M.a.d(v14_1, vn.ai.trueid.sdk.networks.M.c, v0_1, new j9.q(20, this.d));
        return;
    }
}
