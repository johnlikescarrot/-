package vn.com.vng.zalopay.homev5.presenter.ui.onboarding;
public final class OnboardingActivity extends vn.com.vng.zalopay.homev5.presenter.base.BaseComposeActivity {
    public static final synthetic int o;
    public final JB.p j;
    public final m6.c k;
    public final d6.l l;
    public final O5.b m;
    public final ng.S n;

    public OnboardingActivity()
    {
        this.m = new O5.b(kotlin.jvm.internal.M.a.b(JB.q), new JB.b(this, 0), new JB.a(this, 0), new JB.b(this, 1));
        this.n = ng.x.b(new JB.a(this, 1));
        aA.a v0_4 = com.google.android.gms.internal.mlkit_vision_barcode.i6.b;
        if (v0_4 == null) {
            aA.a v0_5 = com.google.android.gms.internal.mlkit_vision_barcode.i6.a;
            if (v0_5 == null) {
                throw new IllegalStateException("envComponent must be initialized first by calling HomeV5Plugin.install()");
            } else {
                String v1_5 = new XQ.i(9, v0_5);
                aA.a v0_9 = com.google.android.gms.internal.mlkit_vision_barcode.i6.c;
                if (v0_9 == null) {
                    v0_9 = gi.N.d();
                    com.google.android.gms.internal.mlkit_vision_barcode.i6.c = v0_9;
                }
                aA.a v3_3 = new aA.a(v1_5, new androidx.camera.camera2.impl.a(7, gi.L.a(v0_9)), new kX.d());
                com.google.android.gms.internal.mlkit_vision_barcode.i6.b = v3_3;
                v0_4 = v3_3;
            }
        }
        this.j = ((JB.p) v0_4.Q.a);
        aA.a v0_16 = ((aA.c) v0_4.a.b).b();
        p9.n7.c(v0_16);
        this.k = new m6.c(9, v0_16);
        this.l = new d6.l(new D5.f(16));
        return;
    }

    public final void e1(int p18, q0.s p19)
    {
        JB.n v3_0;
        q0.Y0 v2_1 = p19.x(-716950955);
        if (!v2_1.I(this)) {
            v3_0 = 16;
        } else {
            v3_0 = 32;
        }
        if ((((v3_0 | p18) & 17) == 16) && (v2_1.c())) {
            v2_1.m();
        } else {
            JB.n v3_6 = ((android.view.View) v2_1.d(androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.f));
            int v5_1 = (v2_1.I(this) | v2_1.I(v3_6));
            kotlin.jvm.functions.Function1 v6_1 = v2_1.G();
            q0.D0 v8 = q0.r.a;
            if ((v5_1 != 0) || (v6_1 == v8)) {
                v6_1 = new Az.j(this, v3_6, 0, 29);
                v2_1.B(v6_1);
            }
            q0.Z.g(kotlin.Unit.INSTANCE, ((kotlin.jvm.functions.Function2) v6_1), v2_1);
            JB.n v3_8 = this.m;
            int v4_4 = q0.d.q(((JB.q) v3_8.getValue()).k0(), v2_1, 0);
            JB.q v11_1 = ((JB.q) v3_8.getValue());
            JB.n v3_10 = v2_1.I(v11_1);
            kotlin.jvm.functions.Function1 v6_4 = v2_1.G();
            if ((v3_10 != null) || (v6_4 == v8)) {
                AA.z v9_1 = new AA.z(1, v11_1, JB.q, "processIntent", "processIntent(Ljava/lang/Object;)V", 0, 19);
                v2_1.B(v9_1);
                v6_4 = v9_1;
            }
            KB.l.k(((kotlin.jvm.functions.Function1) ((kotlin.reflect.g) v6_4)), ((JB.n) v4_4.getValue()), 0, v2_1, 0);
        }
        q0.Y0 v2_2 = v2_1.Y();
        if (v2_2 != null) {
            v2_2.d = new AA.e(this, p18, 9);
        }
        return;
    }

    public final void onCreate(android.os.Bundle p3)
    {
        androidx.activity.q.a(this, 0, 3);
        super.onCreate(p3);
        n9.s4.a(this.getOnBackPressedDispatcher(), this, new FW.C(12));
        ((JB.q) this.m.getValue()).o0(new JB.l());
        return;
    }
}
