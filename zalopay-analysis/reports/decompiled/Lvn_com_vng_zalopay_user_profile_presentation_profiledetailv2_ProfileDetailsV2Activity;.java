package vn.com.vng.zalopay.user_profile.presentation.profiledetailv2;
public final class ProfileDetailsV2Activity extends androidx.activity.ComponentActivity {
    public static final synthetic int k;
    public final AW.a i;
    public final String j;

    public ProfileDetailsV2Activity()
    {
        this.i = new AW.a(10);
        this.j = java.util.UUID.randomUUID().toString();
        return;
    }

    public final void b1(D0.q p17, q0.s p18, int p19)
    {
        boolean v6_1;
        int v4_8 = Integer.valueOf(0);
        int v5_2 = p18.x(-2103699161);
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
                v9_1 = new Am.b(20, this);
                v5_2.B(v9_1);
            }
            Integer v8_7 = U0.c.d(v4_8, ((kotlin.jvm.functions.Function1) v9_1), v5_2, 2);
            int v4_11 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to(Float.valueOf(0), v4_8), kotlin.TuplesKt.to(Float.valueOf((- J0.f.b(((J0.f) v6_13.getValue()).a))), Integer.valueOf(1))}));
            boolean v9_13 = v5_2.G();
            if (v9_13 == v7_2) {
                v9_13 = new AD.d(v6_13, 26);
                v5_2.B(v9_13);
            }
            boolean v6_15 = D0.p.a;
            boolean v9_16 = androidx.compose.ui.layout.a.f(v6_15, ((kotlin.jvm.functions.Function1) v9_13));
            c1.j v11_3 = v5_2.G();
            if (v11_3 == v7_2) {
                v11_3 = new MJ.a(10);
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
                v10_2 = new OP.c(v8_7, 0);
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
            Hl.g.a(p17, this.i, v5_2, 6);
            v5_2.W(1);
            v5_2.W(1);
        }
        int v3_5 = v5_2.Y();
        if (v3_5 != 0) {
            v3_5.d = new AZ.h(this, p17, p19, 22);
        }
        return;
    }

    public final void onCreate(android.os.Bundle p4)
    {
        super.onCreate(p4);
        k.e.a(this, new y0.b(new OP.b(this, 0), 1, 825462233));
        return;
    }
}
