package vn.com.zalopay.ekycpro.presentation.guardian_consent;
public final class GuardianInfoActivity extends androidx.activity.ComponentActivity {
    public static final synthetic int q;
    public kV.a i;
    public final O5.b j;
    public HD.i k;
    public final O5.b l;
    public final AW.a m;
    public String n;
    public KG.a o;
    public aV.d p;

    public GuardianInfoActivity()
    {
        kotlin.reflect.d v2_1 = kotlin.jvm.internal.M.a;
        this.j = new O5.b(v2_1.b(kV.k), new kV.f(this, 0), new kV.d(this, 0), new kV.f(this, 1));
        this.l = new O5.b(v2_1.b(kV.m), new kV.f(this, 2), new kV.d(this, 1), new kV.f(this, 3));
        this.m = new AW.a(10);
        this.n = java.util.UUID.randomUUID().toString();
        return;
    }

    public final void b1(D0.q p17, q0.s p18, int p19)
    {
        boolean v6_1;
        int v4_8 = Integer.valueOf(0);
        int v5_2 = p18.x(-222744908);
        if (!v5_2.I(this)) {
            v6_1 = 16;
        } else {
            v6_1 = 32;
        }
        if ((((v6_1 | p19) & 19) == 18) && (v5_2.c())) {
            v5_2.m();
        } else {
            boolean v6_10 = v5_2.G();
            int v7_2 = q0.r.a;
            if (v6_10 == v7_2) {
                v6_10 = q0.d.G(new J0.f(0), q0.D0.e);
                v5_2.B(v6_10);
            }
            boolean v6_13 = ((q0.C0) v6_10);
            Integer v8_4 = v5_2.I(this);
            boolean v9_1 = v5_2.G();
            if ((v8_4 != null) || (v9_1 == v7_2)) {
                v9_1 = new jV.k(6, this);
                v5_2.B(v9_1);
            }
            Integer v8_7 = U0.c.d(v4_8, ((kotlin.jvm.functions.Function1) v9_1), v5_2, 2);
            int v4_11 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(Float.valueOf(0), v4_8), kotlin.TuplesKt.to(Float.valueOf((- J0.f.b(((J0.f) v6_13.getValue()).a))), Integer.valueOf(1))}));
            boolean v9_13 = v5_2.G();
            if (v9_13 == v7_2) {
                v9_13 = new OV.F(v6_13, 26);
                v5_2.B(v9_13);
            }
            boolean v6_15 = D0.p.a;
            boolean v9_16 = androidx.compose.ui.layout.a.f(v6_15, ((kotlin.jvm.functions.Function1) v9_13));
            c1.j v11_3 = v5_2.G();
            if (v11_3 == v7_2) {
                v11_3 = new fI.b(21);
                v5_2.B(v11_3);
            }
            int v4_12 = U0.c.e(v9_16, v8_7, v4_11, X.e0.a, ((kotlin.jvm.functions.Function2) v11_3));
            boolean v9_17 = D0.b.a;
            c1.j v11_6 = androidx.compose.foundation.layout.w.e(v9_17, 0);
            int v12_2 = v5_2.P;
            c1.j v13_1 = v5_2.S();
            int v4_0 = D0.s.c(v4_12, v5_2);
            c1.m.d2.getClass();
            c1.k v14_1 = c1.l.b;
            v5_2.l();
            if (!v5_2.O) {
                v5_2.h();
            } else {
                v5_2.K(v14_1);
            }
            c1.j v15_1 = c1.l.g;
            q0.d.N(v11_6, v15_1, v5_2);
            c1.j v11_0 = c1.l.f;
            q0.d.N(v13_1, v11_0, v5_2);
            c1.j v13_0 = c1.l.i;
            if ((v5_2.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v5_2.G(), Integer.valueOf(v12_2)))) {
                Kb.e.t(v12_2, v5_2, v12_2, v13_0);
            }
            int v3_2 = c1.l.d;
            q0.d.N(v4_0, v3_2, v5_2);
            int v4_1 = v5_2.p(v8_7);
            kotlin.jvm.functions.Function1 v10_2 = v5_2.G();
            if ((v4_1 != 0) || (v10_2 == v7_2)) {
                v10_2 = new OP.c(v8_7, 5);
                v5_2.B(v10_2);
            }
            int v4_3 = androidx.compose.foundation.layout.c.u(v6_15, ((kotlin.jvm.functions.Function1) v10_2));
            boolean v6_3 = androidx.compose.foundation.layout.w.e(v9_17, 0);
            int v7_0 = v5_2.P;
            Integer v8_0 = v5_2.S();
            int v4_4 = D0.s.c(v4_3, v5_2);
            v5_2.l();
            if (!v5_2.O) {
                v5_2.h();
            } else {
                v5_2.K(v14_1);
            }
            q0.d.N(v6_3, v15_1, v5_2);
            q0.d.N(v8_0, v11_0, v5_2);
            if ((v5_2.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v5_2.G(), Integer.valueOf(v7_0)))) {
                Kb.e.t(v7_0, v5_2, v7_0, v13_0);
            }
            q0.d.N(v4_4, v3_2, v5_2);
            Hl.g.a(p17, this.m, v5_2, 6);
            v5_2.W(1);
            v5_2.W(1);
        }
        int v3_5 = v5_2.Y();
        if (v3_5 != 0) {
            v3_5.d = new kV.e(this, p17, p19, 0);
        }
        return;
    }

    public final void c1(sN.s p15, long p16, q0.s p18, int p19, int p20)
    {
        java.io.Serializable v5_0;
        long v3_0;
        IllegalArgumentException v0_4 = p18.x(-1626230592);
        String v1_9 = (p20 & 2);
        if (v1_9 == null) {
            if ((p19 & 48) != 0) {
                v3_0 = p16;
                v5_0 = p19;
            } else {
                java.io.Serializable v5_9;
                v3_0 = p16;
                if (!v0_4.u(p16)) {
                    v5_9 = 16;
                } else {
                    v5_9 = 32;
                }
                v5_0 = (p19 | v5_9);
            }
        } else {
            v5_0 = (p19 | 48);
            v3_0 = p16;
        }
        String v6_4;
        if (!v0_4.I(this)) {
            v6_4 = 128;
        } else {
            v6_4 = 256;
        }
        java.io.Serializable v5_21 = (v5_0 | v6_4);
        if (((v5_21 & 147) == 146) && (v0_4.c())) {
            v0_4.m();
        } else {
            if (v1_9 != null) {
                v3_0 = 2500;
            }
            String v1_8 = ((v5_21 & 112) | 6);
            java.io.Serializable v5_22 = v0_4.G();
            String v6_7 = q0.r.a;
            if (v5_22 == v6_7) {
                v5_22 = q0.d.G(Long.valueOf(v3_0), q0.D0.e);
                v0_4.B(v5_22);
            }
            String v1_11;
            java.io.Serializable v5_24 = ((q0.C0) v5_22);
            sN.s v7_3 = Long.valueOf(v3_0);
            int v10_1 = Long.valueOf(1000);
            int v11_1 = v0_4.p(v5_24);
            if (((((v1_8 & 112) ^ 48) <= 32) || (!v0_4.u(v3_0))) && ((v1_8 & 48) != 32)) {
                v1_11 = 0;
            } else {
                v1_11 = 1;
            }
            String v1_13 = ((v1_11 | v11_1) | v0_4.u(1000));
            String v2_5 = v0_4.G();
            if ((v1_13 != null) || (v2_5 == v6_7)) {
                v2_5 = new TC.e(v3_0, 0, v5_24);
                v0_4.B(v2_5);
            }
            q0.Z.f(v7_3, v10_1, ((kotlin.jvm.functions.Function2) v2_5), v0_4);
            if (((int) ((((((Number) v5_24.getValue()).longValue() % 86400000) % 3600000) % 60000) / 1000)) == 0) {
                rN.c.a(new sN.t(p15));
                String v1_1 = kotlin.collections.a0.emptyMap();
                String v2_1 = new android.content.Intent();
                v2_1.putExtra("arg_result_state", 1);
                String v1_3 = v1_1.entrySet().iterator();
                while (v1_3.hasNext()) {
                    java.io.Serializable v5_7 = ((java.util.Map$Entry) v1_3.next());
                    String v6_2 = ((String) v5_7.getKey());
                    java.io.Serializable v5_8 = v5_7.getValue();
                    if (!(v5_8 instanceof String)) {
                        if (!(v5_8 instanceof Integer)) {
                            if (!(v5_8 instanceof Boolean)) {
                                if (!(v5_8 instanceof Float)) {
                                    if (!(v5_8 instanceof Double)) {
                                        if (!(v5_8 instanceof Long)) {
                                            if (!(v5_8 instanceof android.os.Parcelable)) {
                                                if (!(v5_8 instanceof java.io.Serializable)) {
                                                    throw new IllegalArgumentException(androidx.compose.foundation.layout.G0.k(v5_8.getClass(), "Unsupported data type: "));
                                                } else {
                                                    v2_1.putExtra(v6_2, ((java.io.Serializable) v5_8));
                                                }
                                            } else {
                                                v2_1.putExtra(v6_2, ((android.os.Parcelable) v5_8));
                                            }
                                        } else {
                                            v2_1.putExtra(v6_2, ((Number) v5_8).longValue());
                                        }
                                    } else {
                                        v2_1.putExtra(v6_2, ((Number) v5_8).doubleValue());
                                    }
                                } else {
                                    v2_1.putExtra(v6_2, ((Number) v5_8).floatValue());
                                }
                            } else {
                                v2_1.putExtra(v6_2, ((Boolean) v5_8).booleanValue());
                            }
                        } else {
                            v2_1.putExtra(v6_2, ((Number) v5_8).intValue());
                        }
                    } else {
                        v2_1.putExtra(v6_2, ((String) v5_8));
                    }
                }
                this.setResult(-1, v2_1);
                this.finish();
            }
        }
        boolean v8_0 = v3_0;
        IllegalArgumentException v0_1 = v0_4.Y();
        if (v0_1 != null) {
            v0_1.d = new cG.j(this, p15, v8_0, p19, p20, 1);
        }
        return;
    }

    public final kV.k e1()
    {
        return ((kV.k) this.j.getValue());
    }

    public final void onCreate(android.os.Bundle p23)
    {
        super.onCreate(p23);
        this.getWindow().addFlags(128);
        androidx.activity.G v1_12 = n9.W2.a;
        v1_12.getClass();
        Gy.a v2_0 = new AW.a(3, n9.W2.a);
        int v3_4 = new s5.g();
        int v4_5 = new s5.f();
        int v5_6 = dagger.internal.f.b(new AB.b(16, v3_4));
        int v6_6 = dagger.internal.f.b(new AB.b(15, v3_4));
        cV.Y v7_6 = dagger.internal.f.b(new AB.b(14, v3_4));
        int v8_4 = dagger.internal.f.b(new AU.e(v3_4, 5));
        int v9_4 = dagger.internal.f.b(new AU.e(v3_4, 0));
        dagger.internal.o v10_3 = dagger.internal.f.b(new AU.e(v3_4, 3));
        dagger.internal.o v11_3 = dagger.internal.f.b(new AU.e(v3_4, 2));
        int v3_7 = dagger.internal.f.b(new AU.e(v3_4, 7));
        Gy.a v2_12 = dagger.internal.f.b(new AA.C(v2_0, dagger.internal.f.b(new AP.d(2, v2_0)), 19));
        dagger.internal.o v12_8 = dagger.internal.f.b(new AB.b(12, v4_5));
        int v4_8 = dagger.internal.f.b(new AB.b(11, v4_5));
        this.i = new kV.a(((NU.m) v5_6.get()), ((NU.f) v6_6.get()), ((NU.d) v7_6.get()), ((NU.j) v8_4.get()), ((NU.c) v9_4.get()), ((NU.h) v10_3.get()), ((NU.g) v11_3.get()), ((NU.l) v3_7.get()));
        this.k = new HD.i(((KU.j) v2_12.get()), v1_12.h(), ((aV.d) v12_8.get()), 4);
        androidx.activity.G v1_0 = v1_12.b();
        p9.n7.b(v1_0);
        this.o = v1_0;
        this.p = ((aV.d) v4_8.get());
        this.e1().g0(new kV.C(this.getIntent().getIntExtra("source", 1)));
        androidx.activity.G v1_7 = ((kV.m) this.l.getValue());
        v1_7.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v1_7), 0, 0, new cV.Y(v1_7, 0, 9), 3, 0);
        k.e.a(this, new y0.b(new kV.c(this, 0), 1, 1415858022));
        this.getOnBackPressedDispatcher().a(this, new Gy.a(this, 0, 10));
        return;
    }

    public final void onDestroy()
    {
        rN.c.a(new sN.t(((kV.F) this.e1().l.b.getValue()).l));
        super.onDestroy();
        return;
    }
}
