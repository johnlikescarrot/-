package vn.ai.trueid.sdk.networks;
public final class w implements vn.ai.trueid.sdk.networks.D {
    public final synthetic java.util.Map a;
    public final synthetic java.util.List b;
    public final synthetic java.util.List c;
    public final synthetic Ek.a0 d;

    public w(java.util.Map p1, java.util.List p2, java.util.List p3, Ek.a0 p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p2)
    {
        this.d.a(p2);
        return;
    }

    public final void success(String p15)
    {
        java.util.HashMap v0_6;
        String v15_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getFaceSDKConfig$trueid_release();
        java.util.HashMap v0_9 = this.a;
        java.util.Map v1_2 = kotlin.collections.a0.toMutableMap(v0_9);
        java.util.HashMap v0_17 = v0_9.get("faceImages");
        if (!(v0_17 instanceof java.util.List)) {
            v0_6 = 0;
        } else {
            v0_6 = ((java.util.List) v0_17);
        }
        if (v0_6 == null) {
            v0_6 = kotlin.collections.E.emptyList();
        }
        Ek.a0 v3_2 = vn.ai.trueid.sdk.networks.E.b.get("requestId");
        kotlin.Pair v5_0 = "NO_REQUEST";
        if (v3_2 == null) {
            v3_2 = "NO_REQUEST";
        }
        java.util.HashMap v0_15;
        Ek.a0 v3_3 = v3_2.toString();
        v15_2.getAppId();
        v1_2.put("faceImages", vn.ai.trueid.sdk.utils.j.j(v3_3, v0_6));
        vn.ai.trueid.sdk.networks.E.c.put("User-Agent", androidx.compose.foundation.layout.G0.m(vn.ai.trueid.sdk.utils.Helpers.Companion.provideUserAgent(), "|in:", kotlin.collections.CollectionsKt.l(((Iterable) this.b), "-", 0, 0, 0, 62), "|out:", kotlin.collections.CollectionsKt.l(((Iterable) this.c), "-", 0, 0, 0, 62)));
        if (!kotlin.jvm.internal.Intrinsics.areEqual(v15_2.getSignVersion(), "2")) {
            java.util.HashMap v0_14 = ((String) vn.ai.trueid.sdk.networks.E.c.get("Authorization"));
            if (v0_14 == null) {
                v0_14 = "";
            }
            v0_15 = kotlin.text.y.replace$default(v0_14, "Bearer ", "", 0, 4, 0);
        } else {
            java.util.HashMap v0_18 = vn.ai.trueid.sdk.networks.E.b.get("requestId");
            if (v0_18 != null) {
                v5_0 = v0_18;
            }
            v0_15 = v5_0.toString();
        }
        Ek.a0 v3_10 = v15_2.getSignVersion();
        m6.c v2_4 = ((java.util.List) v1_2.get("faceImages"));
        java.util.HashMap v0_19 = wk.a.c(v0_15, v3_10);
        v1_2.putAll(kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("apiVersion", v3_10), kotlin.TuplesKt.to("nonce", v0_19), kotlin.TuplesKt.to("signature", Pk.b.b(v3_10, v0_19, v2_4))})));
        java.util.HashMap v0_5 = new java.util.HashMap();
        v0_5.putAll(v1_2);
        v0_5.putAll(vn.ai.trueid.sdk.networks.E.b);
        vn.ai.trueid.sdk.networks.B.e(v15_2.getEnrollEndPoint(), vn.ai.trueid.sdk.networks.E.c, v0_5, new m6.c(18, this.d));
        return;
    }
}
