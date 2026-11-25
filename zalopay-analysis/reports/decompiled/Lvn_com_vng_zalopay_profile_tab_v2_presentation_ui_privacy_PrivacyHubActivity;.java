package vn.com.vng.zalopay.profile_tab_v2.presentation.ui.privacy;
public final class PrivacyHubActivity extends vn.com.vng.zalopay.profile_tab_v2.presentation.base.BaseComposeActivity {
    public static final java.util.Set n;
    public DC.d0 j;
    public KG.a k;
    public java.util.UUID l;
    public final O5.b m;

    static PrivacyHubActivity()
    {
        vn.com.vng.zalopay.profile_tab_v2.presentation.ui.privacy.PrivacyHubActivity.n = kotlin.collections.y.toSet(new String[] {"http", "https"}));
        return;
    }

    public PrivacyHubActivity()
    {
        this.m = new O5.b(kotlin.jvm.internal.M.a.b(bJ.P), new bJ.f(this, 0), new bJ.d(0, this), new bJ.f(this, 1));
        return;
    }

    public final void e1(int p14, q0.s p15)
    {
        int v0_4;
        q0.Y0 v15_1 = p15.x(844328864);
        if (!v15_1.I(this)) {
            v0_4 = 16;
        } else {
            v0_4 = 32;
        }
        if ((((v0_4 | p14) & 19) == 18) && (v15_1.c())) {
            v15_1.m();
        } else {
            int v0_2 = this.m;
            bJ.L v1_7 = q0.d.q(ji.j0.i(((ji.b0) ((bJ.P) v0_2.getValue()).f.getValue())), v15_1, 0);
            Jl.e v3_0 = v15_1.G();
            q0.D0 v4 = q0.r.a;
            if (v3_0 == v4) {
                v3_0 = new Jl.e();
                v15_1.B(v3_0);
            }
            Jl.e v3_2 = ((Jl.e) v3_0);
            androidx.compose.ui.platform.o v5_3 = ji.j0.C(((bJ.P) v0_2.getValue()).g);
            int v6_0 = v15_1.I(this);
            bJ.P v7_0 = v15_1.G();
            if ((v6_0 != 0) || (v7_0 == v4)) {
                v7_0 = new bJ.e(this, v3_2, 0);
                v15_1.B(v7_0);
            }
            m9.j4.a(v5_3, 0, ((kotlin.jvm.functions.Function1) v7_0), v15_1, 0);
            bJ.L v1_9 = ((bJ.L) v1_7.getValue());
            bJ.P v7_4 = ((bJ.P) v0_2.getValue());
            int v0_6 = v15_1.p(v7_4);
            kotlin.jvm.functions.Function1 v2_1 = v15_1.G();
            if ((v0_6 != 0) || (v2_1 == v4)) {
                androidx.compose.ui.platform.o v5_5 = new androidx.compose.ui.platform.o(1, v7_4, bJ.P, "processIntent", "processIntent(Lvn/com/vng/zalopay/profile_tab_v2/presentation/base/UiIntent;)V", 0, 11);
                v15_1.B(v5_5);
                v2_1 = v5_5;
            }
            bJ.H.e(v1_9, ((kotlin.jvm.functions.Function1) ((kotlin.reflect.g) v2_1)), v3_2, v15_1, 3456);
        }
        q0.Y0 v15_2 = v15_1.Y();
        if (v15_2 != null) {
            v15_2.d = new YW.h(this, p14, 6);
        }
        return;
    }

    public final void g1(Jl.e p11, String p12)
    {
        long v0_0 = this.l;
        if (v0_0 != 0) {
            p11.a(v0_0);
        }
        java.util.UUID v2 = java.util.UUID.randomUUID();
        this.l = v2;
        Jl.e.b(p11, v2, p12, Integer.valueOf(2131232918), new K0.y(ql.b.b), 3000, Jl.c.b, 192);
        return;
    }

    public final void onCreate(android.os.Bundle p6)
    {
        if (android.os.Build$VERSION.SDK_INT != 26) {
            this.setRequestedOrientation(1);
        }
        bJ.y v0_3 = com.google.android.gms.internal.mlkit_vision_barcode.y0.a;
        if (v0_3 == null) {
            bJ.y v0_14 = com.google.android.gms.internal.mlkit_vision_barcode.y0.c;
            if (v0_14 == null) {
                throw new IllegalStateException("envComponent must be initialized first by calling ProfileTabV2Plugin.install()");
            } else {
                v0_3 = new JI.a(new AW.a(13, v0_14));
                com.google.android.gms.internal.mlkit_vision_barcode.y0.a = v0_3;
            }
        } else {
        }
        this.j = ((DC.d0) v0_3.x);
        bJ.y v0_8 = ((KI.a) ((AW.a) v0_3.a).b).b();
        p9.n7.c(v0_8);
        this.k = v0_8;
        bJ.y v0_10 = kotlin.Unit.INSTANCE;
        Throwable v1_7 = ng.L.a(v0_10);
        if (v1_7 != null) {
            Object[] v3_1 = new Object[0];
            ck.b.d("PrivacyHubActivity").e(v1_7, "Cannot inject PrivacyHubActivity", v3_1);
        }
        if (!(v0_10 instanceof ng.K)) {
            super.onCreate(p6);
            ((bJ.P) this.m.getValue()).n0(bJ.y.a);
            return;
        } else {
            super.onCreate(p6);
            this.finish();
            return;
        }
    }
}
