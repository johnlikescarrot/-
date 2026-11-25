package vn.ai.trueid.sdk.liveness.networks;
public final class h implements vn.ai.trueid.sdk.liveness.networks.j {
    public final synthetic java.util.Map a;
    public final synthetic java.util.List b;
    public final synthetic java.util.List c;
    public final synthetic d6.l d;

    public h(java.util.Map p1, java.util.List p2, java.util.List p3, d6.l p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public final void m(vn.ai.trueid.sdk.liveness.networks.l p2)
    {
        this.d.e(p2);
        return;
    }

    public final void success(String p13)
    {
        String v13_1 = Gk.c.a();
        java.util.Iterator v0_4 = v13_1.v;
        Object[] v1_6 = this.a;
        java.util.Map v2_1 = kotlin.collections.a0.toMutableMap(v1_6);
        String v4_13 = new StringBuilder();
        v4_13.append(vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.f());
        v4_13.append("|in:");
        v4_13.append(kotlin.collections.CollectionsKt.l(((Iterable) this.b), "-", 0, 0, 0, 62));
        v4_13.append("|out:");
        v4_13.append(kotlin.collections.CollectionsKt.l(((Iterable) this.c), "-", 0, 0, 0, 62));
        vn.ai.trueid.sdk.liveness.networks.k.c.put("User-Agent", v4_13.toString());
        lO.o v3_2 = v13_1.w;
        if (v3_2.length() > 0) {
            String v4_8;
            if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_4, "2")) {
                String v4_7 = ((String) vn.ai.trueid.sdk.liveness.networks.k.c.get("Authorization"));
                if (v4_7 == null) {
                    v4_7 = "";
                }
                v4_8 = kotlin.text.y.replace$default(v4_7, "Bearer ", "", 0, 4, 0);
            } else {
                String v4_10 = vn.ai.trueid.sdk.liveness.networks.k.b.get("requestId");
                if (v4_10 == null) {
                    v4_10 = "NO_REQUEST";
                }
                v4_8 = ((String) v4_10);
            }
            Object[] v1_9 = ((java.util.List) kotlin.collections.a0.toMutableMap(v1_6).get("faceImages"));
            vn.ai.trueid.sdk.face.navigation.FaceSDK v5_13 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
            String v4_11 = v5_13.getNonce(v4_8, v0_4);
            v2_1.putAll(kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("apiVersion", v0_4), kotlin.TuplesKt.to("nonce", v4_11), kotlin.TuplesKt.to("signature", v5_13.makeSignature(v3_2, v0_4, v4_11, v1_9))})));
            if (vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.c().getBoolean(vn.ai.trueid.sdk.face.R$bool.sdk_debug)) {
                java.util.Iterator v0_1 = v2_1.entrySet().iterator();
                while (v0_1.hasNext()) {
                    v0_1.next();
                    Object[] v1 = vn.ai.trueid.sdk.liveness.networks.g.a;
                    vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.a(new Object[] {"$it"}));
                }
            }
        }
        java.util.Iterator v0_3 = new java.util.HashMap();
        v0_3.putAll(v2_1);
        v0_3.putAll(vn.ai.trueid.sdk.liveness.networks.k.b);
        vn.ai.trueid.sdk.liveness.networks.k.a.a(v13_1.b, vn.ai.trueid.sdk.liveness.networks.k.c, v0_3, new lO.o(15, this.d));
        return;
    }
}
