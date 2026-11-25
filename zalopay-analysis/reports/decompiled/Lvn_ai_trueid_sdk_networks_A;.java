package vn.ai.trueid.sdk.networks;
public final class A extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    public java.util.concurrent.atomic.AtomicInteger d;
    public kotlin.jvm.functions.Function0 e;
    public java.util.Iterator f;
    public String g;
    public String h;
    public java.io.File i;
    public int j;
    public int k;
    public final synthetic java.util.Map l;
    public final synthetic java.util.concurrent.atomic.AtomicInteger m;
    public final synthetic int n;
    public final synthetic kotlin.jvm.functions.Function0 o;

    public A(java.util.Map p1, java.util.concurrent.atomic.AtomicInteger p2, int p3, kotlin.jvm.functions.Function0 p4, kotlin.coroutines.Continuation p5)
    {
        this.l = p1;
        this.m = p2;
        this.n = p3;
        this.o = p4;
        super(2, p5);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p7, kotlin.coroutines.Continuation p8)
    {
        return new vn.ai.trueid.sdk.networks.A(this.l, this.m, this.n, this.o, p8);
    }

    public final Object invoke(Object p1, Object p2)
    {
        return ((vn.ai.trueid.sdk.networks.A) this.create(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2))).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p18)
    {
        kotlin.jvm.functions.Function0 v8_0;
        java.util.concurrent.atomic.AtomicInteger v9_0;
        boolean v4_6;
        int v5_3;
        String v7_0;
        String v6_1;
        String v2_1;
        kotlin.Unit v1_0 = vg.i.getCOROUTINE_SUSPENDED();
        String v2_3 = this.k;
        try {
            if (v2_3 == null) {
                kotlin.ResultKt.throwOnFailure(p18);
                v7_0 = this.l.entrySet().iterator();
                v9_0 = this.m;
                v2_1 = this.n;
                v8_0 = this.o;
            } else {
                if (v2_3 != 1) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                } else {
                    v2_1 = this.j;
                    v4_6 = this.i;
                    v5_3 = this.h;
                    v6_1 = this.g;
                    v7_0 = this.f;
                    v8_0 = this.e;
                    v9_0 = this.d;
                    kotlin.ResultKt.throwOnFailure(p18);
                    String v15 = v2_1;
                    String v12 = v6_1;
                    v2_1 = v7_0;
                    kotlin.jvm.functions.Function0 v16 = v8_0;
                    java.util.concurrent.atomic.AtomicInteger v14 = v9_0;
                    try {
                        int v13 = v4_6;
                        kotlin.jvm.functions.Function0 v10_0 = v16;
                    } catch (Exception) {
                        v10_0 = v16;
                        v7_0 = v2_1;
                        v8_0 = v10_0;
                        v6_1 = v12;
                        v9_0 = v14;
                        v2_1 = v15;
                        boolean v4_14 = v6_1.hashCode();
                        if (v4_14 == -859619302) {
                            if (v6_1.equals("imageMRZ")) {
                                fk.b.t = 0;
                            }
                        } else {
                            if (v4_14 == -859618736) {
                                if (v6_1.equals("imageNFC")) {
                                    fk.b.s = 0;
                                }
                            } else {
                                if (v4_14 == 1911933116) {
                                    if (v6_1.equals("imageQR")) {
                                        fk.b.u = 0;
                                    }
                                }
                            }
                        }
                        vn.ai.trueid.sdk.networks.B.a(v9_0, v2_1, v8_0);
                    } catch (Exception) {
                    }
                    vn.ai.trueid.sdk.networks.E.a.b(v5_3, vn.ai.trueid.sdk.networks.E.c, v13, new vn.ai.trueid.sdk.networks.y(v12, v13, v14, v15, v16), new vn.ai.trueid.sdk.networks.z(v12, v14, v15, v10_0));
                    v7_0 = v2_1;
                    v8_0 = v10_0;
                    v9_0 = v14;
                    v2_1 = v15;
                }
            }
        } catch (Exception) {
        }
        if (!v7_0.hasNext()) {
            return kotlin.Unit.INSTANCE;
        } else {
            boolean v4_10 = ((java.util.Map$Entry) v7_0.next());
            v6_1 = ((String) v4_10.getKey());
            v5_3 = ((String) v4_10.getValue());
            v4_6 = vn.ai.trueid.sdk.utils.Helpers.Companion.initFileImage(kotlin.text.StringsKt.G(v5_3, 47));
            this.d = v9_0;
            this.e = v8_0;
            this.f = v7_0;
            this.g = v6_1;
            this.h = v5_3;
            this.i = v4_6;
            this.j = v2_1;
            this.k = 1;
            if (gi.U.a(150, this) != v1_0) {
            } else {
                return v1_0;
            }
        }
    }
}
