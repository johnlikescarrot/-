package vn.com.vng.zalopay.profile_tab_v2.presentation.ui.account;
public final class AccountHostActivity extends vn.com.vng.zalopay.profile_tab_v2.presentation.base.BaseComposeActivity {
    public static final java.util.Set o;
    public f8.o j;
    public NI.g k;
    public KG.a l;
    public final ng.S m;
    public final O5.b n;

    static AccountHostActivity()
    {
        vn.com.vng.zalopay.profile_tab_v2.presentation.ui.account.AccountHostActivity.o = kotlin.collections.y.toSet(new String[] {"http", "https"}));
        return;
    }

    public AccountHostActivity()
    {
        this.m = ng.x.b(new XI.a(this, 0));
        this.n = new O5.b(kotlin.jvm.internal.M.a.b(XI.G), new XI.c(this, 0), new XI.a(this, 1), new XI.c(this, 1));
        return;
    }

    public final void e1(int p23, q0.s p24)
    {
        XI.G v0_0;
        q0.v v9 = p24.x(-2109095643);
        if (!v9.I(this)) {
            v0_0 = 16;
        } else {
            v0_0 = 32;
        }
        if ((((v0_0 | p23) & 19) == 18) && (v9.c())) {
            v9.m();
            vn.com.vng.zalopay.profile_tab_v2.presentation.ui.account.AccountHostActivity v7_0 = this;
            q0.v v5_0 = v9;
        } else {
            q0.C0 v11 = q0.d.q(ji.j0.i(((ji.b0) this.g1().f.getValue())), v9, 0);
            ji.e v12 = ji.j0.C(this.g1().g);
            XI.G v0_13 = v9.I(this);
            AA.e v1_4 = v9.G();
            q0.D0 v13 = q0.r.a;
            if ((v0_13 == null) && (v1_4 != v13)) {
                v7_0 = this;
            } else {
                XI.G v0_15 = new RS.f(1, this, vn.com.vng.zalopay.profile_tab_v2.presentation.ui.account.AccountHostActivity, "handleEffect", "handleEffect(Lvn/com/vng/zalopay/profile_tab_v2/presentation/ui/account/AccountPageEffect;)V", 0, 21);
                v7_0 = this;
                v9.B(v0_15);
                v1_4 = v0_15;
            }
            m9.j4.a(v12, 0, ((kotlin.jvm.functions.Function1) ((kotlin.reflect.g) v1_4)), v9, 0);
            XI.G v0_17 = v7_0.m;
            AA.e v1_11 = q9.I4.a(((XI.H) v0_17.getValue()).c, v9);
            int v2_2 = ((XI.H) v0_17.getValue()).b;
            XI.C v3_2 = ((XI.C) v11.getValue());
            XI.G v0_22 = v7_0.g1();
            kotlin.jvm.functions.Function1 v4_1 = v9.p(v0_22);
            q0.v v5_2 = v9.G();
            if ((v4_1 != null) || (v5_2 == v13)) {
                RS.f v14_1 = new RS.f(1, v0_22, XI.G, "processIntent", "processIntent(Lvn/com/vng/zalopay/profile_tab_v2/presentation/base/UiIntent;)V", 0, 22);
                v9.B(v14_1);
                v5_2 = v14_1;
            }
            v5_0 = v9;
            XI.x.a(v1_11, v2_2, v3_2, ((kotlin.jvm.functions.Function1) ((kotlin.reflect.g) v5_2)), v5_0, 24576);
        }
        XI.G v0_1 = v5_0.Y();
        if (v0_1 != null) {
            v0_1.d = new AA.e(v7_0, p23, 27);
        }
        return;
    }

    public final XI.G g1()
    {
        return ((XI.G) this.n.getValue());
    }

    public final void onCreate(android.os.Bundle p7)
    {
        androidx.lifecycle.A v0_0 = com.google.android.gms.internal.mlkit_vision_barcode.y0.a;
        if (v0_0 == null) {
            androidx.lifecycle.A v0_4 = com.google.android.gms.internal.mlkit_vision_barcode.y0.c;
            if (v0_4 == null) {
                throw new IllegalStateException("envComponent must be initialized first by calling ProfileTabV2Plugin.install()");
            } else {
                v0_0 = new JI.a(new AW.a(13, v0_4));
                com.google.android.gms.internal.mlkit_vision_barcode.y0.a = v0_0;
            }
        } else {
        }
        v0_0.a(this);
        androidx.lifecycle.A v0_2 = kotlin.Unit.INSTANCE;
        int v1_1 = ng.L.a(v0_2);
        if (v1_1 != 0) {
            RB.a v3_1 = new Object[0];
            ck.b.d("AccountHostActivity").e(v1_1, "Cannot inject AccountHostActivity", v3_1);
        }
        if (!(v0_2 instanceof ng.K)) {
            super.onCreate(p7);
            this.g1().n0(XI.r.a);
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new RB.a(this, 0, 14), 3, 0);
            return;
        } else {
            super.onCreate(p7);
            this.finish();
            return;
        }
    }
}
