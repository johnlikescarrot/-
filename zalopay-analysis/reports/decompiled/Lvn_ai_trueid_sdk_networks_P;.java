package vn.ai.trueid.sdk.networks;
public final class P extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    public int d;
    public synthetic Object e;
    public final synthetic c1.P f;
    public final synthetic org.json.JSONObject g;

    public P(c1.P p1, org.json.JSONObject p2, kotlin.coroutines.Continuation p3)
    {
        this.f = p1;
        this.g = p2;
        super(2, p3);
        return;
    }

    public static final Object a(Object p12, kotlin.jvm.internal.J p13, java.util.Map p14, c1.P p15, String p16, String p17, wg.c p18)
    {
        java.util.Map v2_1;
        Throwable v0_0 = p17;
        if (!(p18 instanceof vn.ai.trueid.sdk.networks.O)) {
            v2_1 = new vn.ai.trueid.sdk.networks.O(p18);
        } else {
            v2_1 = ((vn.ai.trueid.sdk.networks.O) p18);
            kotlin.jvm.internal.J v3_2 = ((vn.ai.trueid.sdk.networks.O) p18).l;
            if ((v3_2 & -2147483648) == 0) {
            } else {
                ((vn.ai.trueid.sdk.networks.O) p18).l = (v3_2 - -2147483648);
            }
        }
        java.util.Map v14_1;
        Object v11;
        String v9;
        String v13_2;
        java.io.File v10;
        java.io.File v15_1;
        vn.ai.trueid.sdk.networks.m v1_1 = v2_1.k;
        kotlin.jvm.internal.J v3_1 = vg.i.getCOROUTINE_SUSPENDED();
        String v4_0 = v2_1.l;
        if (v4_0 == null) {
            kotlin.ResultKt.throwOnFailure(v1_1);
            vn.ai.trueid.sdk.networks.m v1_4 = vn.ai.trueid.sdk.utils.Helpers.Companion.initFileImage(kotlin.text.StringsKt.G(p17, 47));
            v2_1.d = p12;
            v2_1.e = p13;
            v2_1.f = ((java.util.Map) p14);
            v2_1.g = p15;
            v2_1.h = p16;
            v2_1.i = p17;
            v2_1.j = v1_4;
            v2_1.l = 1;
            if (gi.U.a(150, v2_1) != v3_1) {
                v9 = p13;
                v10 = p14;
                v11 = p15;
                v14_1 = v1_4;
                v13_2 = p16;
                v15_1 = p12;
            } else {
                return v3_1;
            }
        } else {
            if (v4_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.Unit v12_5 = v2_1.j;
                String v13_4 = v2_1.i;
                java.util.Map v14_2 = v2_1.h;
                java.io.File v15_2 = v2_1.g;
                Throwable v0_3 = ((java.util.Map) v2_1.f);
                kotlin.jvm.internal.J v3_3 = v2_1.e;
                java.util.Map v2_5 = v2_1.d;
                kotlin.ResultKt.throwOnFailure(v1_1);
                v11 = v15_2;
                v10 = v0_3;
                v15_1 = v2_5;
                v9 = v3_3;
                v0_0 = v13_4;
                v13_2 = v14_2;
                v14_1 = v12_5;
            }
        }
        int v16_1 = v9;
        E1.g v17_1 = v10;
        Object v18_1 = v11;
        try {
            v9 = v16_1;
            v10 = v17_1;
            v11 = v18_1;
        } catch (Exception) {
            v9 = v16_1;
            v10 = v17_1;
            v11 = v18_1;
            kotlin.Unit v12_10 = (v9.a + 1);
            v9.a = v12_10;
            if (v12_10 != v10.size()) {
                return kotlin.Unit.INSTANCE;
            } else {
                v11.invoke();
                return kotlin.Unit.INSTANCE;
            }
        }
        vn.ai.trueid.sdk.networks.U.a.b(v0_0, vn.ai.trueid.sdk.networks.U.c, v14_1, new A0.b(v13_2, v14_1, v15_1, v16_1, v17_1, v18_1), new E1.g(v13_2, v15_1, v9, v10, v11));
        return kotlin.Unit.INSTANCE;
    }

    public final kotlin.coroutines.Continuation create(Object p4, kotlin.coroutines.Continuation p5)
    {
        vn.ai.trueid.sdk.networks.P v0_1 = new vn.ai.trueid.sdk.networks.P(this.f, this.g, p5);
        v0_1.e = p4;
        return v0_1;
    }

    public final Object invoke(Object p1, Object p2)
    {
        return ((vn.ai.trueid.sdk.networks.P) this.create(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2))).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p20)
    {
        kotlin.Unit v1_3 = vg.i.getCOROUTINE_SUSPENDED();
        Object v2_7 = this.d;
        if (v2_7 == null) {
            kotlin.ResultKt.throwOnFailure(p20);
            kotlinx.coroutines.CoroutineScope v4_1 = ((kotlinx.coroutines.CoroutineScope) this.e);
            c1.P v5_2 = new java.util.ArrayList();
            Object v2_5 = ((Iterable) kotlin.collections.E.listOf(new String[] {"imageNFC", "imageMRZ", "imageQR"}))).iterator();
            while (v2_5.hasNext()) {
                int v6_10 = ((String) v2_5.next());
                B2.I v7_2 = this.g;
                int v9_1 = 0;
                if (v7_2.has(v6_10)) {
                    B2.I v7_4 = v7_2.getJSONObject(v6_10).getString("data");
                    if ((v7_4.length() > 0) && (android.webkit.URLUtil.isValidUrl(v7_4))) {
                        v9_1 = kotlin.TuplesKt.to(v6_10, v7_4);
                    }
                }
                if (v9_1 != 0) {
                    v5_2.add(v9_1);
                }
            }
            java.util.Map v15 = kotlin.collections.a0.toMap(v5_2);
            c1.P v5_3 = this.f;
            if (!v15.isEmpty()) {
                kotlin.jvm.internal.J v14_1 = new kotlin.jvm.internal.J();
                Object v13_1 = new Object();
                Object v2_9 = new java.util.ArrayList(v15.size());
                java.util.Iterator v18 = v15.entrySet().iterator();
                while (v18.hasNext()) {
                    int v6_6 = ((java.util.Map$Entry) v18.next());
                    B2.I v7_1 = new B2.I;
                    c1.P v16 = v5_3;
                    v7_1(((String) v6_6.getKey()), ((String) v6_6.getValue()), v13_1, v14_1, v15, v16, 0);
                    v2_9.add(kotlinx.coroutines.BuildersKt.launch$default(v4_1, 0, 0, v7_1, 3, 0));
                    v5_3 = v16;
                }
                this.d = 1;
                if (gi.N.o(v2_9, this) == v1_3) {
                    return v1_3;
                }
            } else {
                v5_3.invoke();
                return kotlin.Unit.INSTANCE;
            }
        } else {
            if (v2_7 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(p20);
            }
        }
        return kotlin.Unit.INSTANCE;
    }
}
