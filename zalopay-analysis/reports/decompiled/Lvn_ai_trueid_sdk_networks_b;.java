package vn.ai.trueid.sdk.networks;
public final class b implements vn.ai.trueid.sdk.networks.f {
    public final synthetic java.util.Map a;
    public final synthetic java.util.List b;
    public final synthetic java.util.List c;
    public final synthetic vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceSDK$1$complete$1 d;

    public b(java.util.Map p1, java.util.List p2, java.util.List p3, vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceSDK$1$complete$1 p4)
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

    public final void success(String p15)
    {
        java.util.HashMap v0_6;
        String v15_2 = vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE.getSDKConfig$trueid_release();
        java.util.HashMap v0_9 = this.a;
        vn.ai.trueid.sdk.networks.m v1_4 = kotlin.collections.a0.toMutableMap(v0_9);
        java.util.HashMap v0_17 = v0_9.get("faceImages");
        if (!(v0_17 instanceof java.util.List)) {
            v0_6 = 0;
        } else {
            v0_6 = ((java.util.List) v0_17);
        }
        if (v0_6 == null) {
            v0_6 = kotlin.collections.E.emptyList();
        }
        rv.b v3_5 = vn.ai.trueid.sdk.networks.g.b.get("requestId");
        Object v5_5 = "NO_REQUEST";
        if (v3_5 == null) {
            v3_5 = "NO_REQUEST";
        }
        java.util.HashMap v0_15;
        rv.b v3_6 = v3_5.toString();
        v15_2.getAppId();
        v1_4.put("faceImages", vn.ai.trueid.sdk.utils.j.j(v3_6, v0_6));
        vn.ai.trueid.sdk.networks.g.c.put("User-Agent", androidx.compose.foundation.layout.G0.m(vn.ai.trueid.sdk.utils.Helpers.Companion.provideUserAgent(), "|in:", kotlin.collections.CollectionsKt.l(((Iterable) this.b), "-", 0, 0, 0, 62), "|out:", kotlin.collections.CollectionsKt.l(((Iterable) this.c), "-", 0, 0, 0, 62)));
        if (!kotlin.jvm.internal.Intrinsics.areEqual(v15_2.getSignVersion(), "2")) {
            java.util.HashMap v0_14 = ((String) vn.ai.trueid.sdk.networks.g.c.get("Authorization"));
            if (v0_14 == null) {
                v0_14 = "";
            }
            v0_15 = kotlin.text.y.replace$default(v0_14, "Bearer ", "", 0, 4, 0);
        } else {
            java.util.HashMap v0_18 = vn.ai.trueid.sdk.networks.g.b.get("requestId");
            if (v0_18 != null) {
                v5_5 = v0_18;
            }
            v0_15 = v5_5.toString();
        }
        rv.b v3_13 = v15_2.getSignVersion();
        Object v4_8 = ((java.util.List) v1_4.get("faceImages"));
        java.util.HashMap v0_19 = wk.a.c(v0_15, v3_13);
        v1_4.putAll(kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("apiVersion", v3_13), kotlin.TuplesKt.to("nonce", v0_19), kotlin.TuplesKt.to("signature", Pk.b.b(v3_13, v0_19, v4_8))})));
        java.util.HashMap v0_5 = new java.util.HashMap();
        v0_5.putAll(v1_4);
        v0_5.putAll(vn.ai.trueid.sdk.networks.g.b);
        vn.ai.trueid.sdk.networks.m v1_2 = new java.util.LinkedHashMap();
        rv.b v3_1 = v0_5.entrySet().iterator();
        while (v3_1.hasNext()) {
            Object v4_3 = ((java.util.Map$Entry) v3_1.next());
            Object v5_2 = ((String) v4_3.getKey());
            if ((!kotlin.jvm.internal.Intrinsics.areEqual(v5_2, "faceImages")) && (!kotlin.jvm.internal.Intrinsics.areEqual(v5_2, "imageFace"))) {
                v1_2.put(v4_3.getKey(), v4_3.getValue());
            }
        }
        vn.ai.trueid.sdk.networks.g.a.d(v15_2.getAuthenEndPoint(), vn.ai.trueid.sdk.networks.g.c, v0_5, new rv.b(7, this.d));
        return;
    }
}
