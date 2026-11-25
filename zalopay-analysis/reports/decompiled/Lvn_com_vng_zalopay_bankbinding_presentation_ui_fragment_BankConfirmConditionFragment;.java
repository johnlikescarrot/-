package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankConfirmConditionFragment extends Lo.j0 {
    public Xt.b A;
    public Yv.i B;
    public final a9.f C;
    public final ng.S D;
    public final ng.S E;
    public final ng.S F;
    public To.a u;
    public final ng.w v;
    public ji.u0 w;
    public ji.u0 x;
    public Bo.b y;
    public vn.com.vng.zalopay.feature.argument.BankBindingArgs z;

    public BankConfirmConditionFragment()
    {
        ng.S v0_3 = new Lo.k(this, 0);
        int v1_0 = ng.x.a(ng.y.c, new Io.M(3, new Io.i(this, 20)));
        Io.i v2_0 = kotlin.jvm.internal.M.a;
        this.v = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v2_0.b(Ro.g), new Io.N(v1_0, 6), new Io.N(v1_0, 7), v0_3);
        this.C = new a9.f(v2_0.b(Lo.l), new Io.i(this, 19));
        this.D = ng.x.b(new Lo.k(this, 1));
        this.E = ng.x.b(new Lo.k(this, 2));
        this.F = ng.x.b(new Lo.k(this, 3));
        return;
    }

    public final void m1(int p26, q0.s p27)
    {
        Fo.b v2_1;
        vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankConfirmConditionFragment v0_0 = this;
        q0.v v10_2 = p27.x(-396134227);
        if (!v10_2.I(this)) {
            v2_1 = 2;
        } else {
            v2_1 = 4;
        }
        if ((((p26 | v2_1) & 3) == 2) && (v10_2.c())) {
            v10_2.m();
        } else {
            Fo.b v2_20 = q0.d.q(((ji.s0) this.F.getValue()), v10_2, 0);
            D0.q v3_6 = D0.p.a;
            int v5_4 = androidx.compose.foundation.a.b(androidx.compose.foundation.layout.P0.g(v3_6, 1065353216), ql.b.d, K0.X.a);
            String v6_4 = androidx.compose.foundation.layout.r.c;
            int v7_3 = D0.b.m;
            int v8_4 = androidx.compose.foundation.layout.D.a(v6_4, v7_3, v10_2, 0);
            long v9_4 = v10_2.P;
            kotlin.jvm.functions.Function0 v11_4 = v10_2.S();
            int v5_5 = D0.s.c(v5_4, v10_2);
            c1.m.d2.getClass();
            q0.v v12_3 = c1.l.b;
            v10_2.l();
            if (!v10_2.O) {
                v10_2.h();
            } else {
                v10_2.K(v12_3);
            }
            int v13_2 = c1.l.g;
            q0.d.N(v8_4, v13_2, v10_2);
            int v8_5 = c1.l.f;
            q0.d.N(v11_4, v8_5, v10_2);
            kotlin.jvm.functions.Function0 v11_5 = c1.l.i;
            if ((v10_2.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v10_2.G(), Integer.valueOf(v9_4)))) {
                v10_2.B(Integer.valueOf(v9_4));
                v10_2.e(Integer.valueOf(v9_4), v11_5);
            }
            int v14_7 = c1.l.d;
            q0.d.N(v5_5, v14_7, v10_2);
            int v5_6 = v3_6;
            D0.q v3_7 = androidx.compose.foundation.layout.F.a(v5_6, 1065353216);
            long v9_7 = (v10_2.p(v2_20) | v10_2.I(this));
            androidx.compose.foundation.layout.i v15_4 = v10_2.G();
            int v4_11 = q0.r.a;
            if ((v9_7 != 0) || (v15_4 == v4_11)) {
                v15_4 = new Ho.t(v2_20, this, 3);
                v10_2.B(v15_4);
            }
            c1.j v22 = v8_5;
            Fo.b v16 = v2_20;
            q0.D0 v24 = v4_11;
            vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankConfirmConditionFragment v0_1 = v5_6;
            Fo.b v2_2 = v7_3;
            int v1_0 = v12_3;
            c1.j v18_0 = v11_5;
            c1.j v19_0 = v13_2;
            kotlin.jvm.functions.Function0 v11_0 = v10_2;
            c1.j v17_0 = v14_7;
            androidx.compose.foundation.layout.i v15_0 = v6_4;
            Z.d.a(v3_7, 0, 0, 0, 0, 0, 0, ((kotlin.jvm.functions.Function1) v15_4), v11_0, 0, 254);
            q0.v v10_1 = v11_0;
            D0.q v3_0 = androidx.compose.foundation.layout.P0.g(v0_1, 1065353216);
            Fo.b v2_5 = androidx.compose.foundation.layout.D.a(v15_0, v2_2, v10_1, 0);
            int v4_1 = v10_1.P;
            int v5_0 = v10_1.S();
            D0.q v3_1 = D0.s.c(v3_0, v10_1);
            v10_1.l();
            if (!v10_1.O) {
                v10_1.h();
            } else {
                v10_1.K(v1_0);
            }
            q0.d.N(v2_5, v19_0, v10_1);
            q0.d.N(v5_0, v22, v10_1);
            if ((v10_1.O) || (!kotlin.jvm.internal.Intrinsics.areEqual(v10_1.G(), Integer.valueOf(v4_1)))) {
                v10_1.B(Integer.valueOf(v4_1));
                v10_1.e(Integer.valueOf(v4_1), v18_0);
            }
            q0.d.N(v3_1, v17_0, v10_1);
            androidx.compose.foundation.layout.c.d(androidx.compose.foundation.layout.P0.i(v0_1, ((float) 16)), v10_1);
            Ho.f.c(((xo.a) v16.getValue()).a.getCtaDescription(), androidx.compose.foundation.layout.c.A(((float) 16), 0, v0_1, 2), 2132017478, 0, 1096810496, 0, new BZ.n(2), v10_1, 2122160, 40);
            D0.q v3_3 = androidx.compose.foundation.layout.c.y(androidx.compose.foundation.layout.P0.g(v0_1, 1065353216), ((float) 16));
            String v6_2 = ((xo.a) v16.getValue()).a.getCtaButtonText();
            v0_0 = this;
            Fo.b v2_16 = v16;
            int v1_12 = (v10_1.I(this) | v10_1.p(v2_16));
            int v4_5 = v10_1.G();
            if ((v1_12 != 0) || (v4_5 == v24)) {
                v4_5 = new Ho.q(this, v2_16, 6);
                v10_1.B(v4_5);
            }
            int v4_8;
            q0.v v12_1 = v10_1;
            q9.E7.b(v3_3, 0, 1, v6_2, 0, 0, 0, ((kotlin.jvm.functions.Function0) v4_5), v12_1, 390, 114);
            v10_2 = v12_1;
            v10_2.W(1);
            v10_2.W(1);
            if (!((xo.a) v2_16.getValue()).b) {
                v4_8 = 0;
                v10_2.q(1329079605);
            } else {
                v10_2.q(1337868074);
                v4_8 = 0;
                com.google.android.gms.internal.mlkit_vision_barcode.a0.a(0, v10_2);
            }
            v10_2.W(v4_8);
        }
        int v1_21 = v10_2.Y();
        if (v1_21 != 0) {
            v1_21.d = new Fo.b(v0_0, p26, 4);
        }
        return;
    }

    public final boolean n1()
    {
        int v0_0 = this.y;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        v0_0.getClass();
        int v0_3 = kotlin.collections.a0.emptyMap();
        UR.a.e("01.2044.003", v0_3);
        java.util.Objects.toString(v0_3);
        int v0_1 = this.getActivity();
        if (v0_1 != 0) {
            v0_1.finish();
        }
        return 1;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument o1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.ConfirmConditionArgument) this.D.getValue());
    }

    public final void onAttach(android.content.Context p1)
    {
        super.onAttach(p1);
        ro.a v1_1 = this.t;
        if (v1_1 == null) {
            v1_1 = 0;
        }
        v1_1.m().n(this);
        return;
    }

    public final void onResume()
    {
        super.onResume();
        Lo.k v0_0 = this.s;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        v0_0.getClass();
        v0_0.l(0, uo.g.a);
        Lo.k v0_1 = this.y;
        if (v0_1 == null) {
            v0_1 = 0;
        }
        v0_1.g(this.o1().getSuggestBank().getBankCode(), this.o1().getEntryPoint());
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new Ko.a(new Lo.k(this, 4), 0, 1), 3, 0);
        return;
    }

    public final void onViewCreated(android.view.View p9, android.os.Bundle p10)
    {
        super.onViewCreated(p9, p10);
        androidx.lifecycle.T v9_1 = this.s;
        if (v9_1 == null) {
            v9_1 = 0;
        }
        v9_1.getClass();
        v9_1.l(0, uo.g.a);
        androidx.lifecycle.T v9_3 = ((Ro.g) this.v.getValue());
        v9_3.g.f(this.getViewLifecycleOwner(), new cV.y(2, new Ho.c(this, 1)));
        v9_3.i.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankConfirmConditionFragment, "handleSubmitOpenBankAccountState", "handleSubmitOpenBankAccountState(Lvn/com/vng/zalopay/bankbinding/presentation/model/UiStateModel;)V", 0, 3)));
        return;
    }

    public final ji.b0 p1()
    {
        return ((ji.b0) this.E.getValue());
    }

    public final void q1(pS.d p9)
    {
        android.content.Context v0 = this.getContext();
        if (v0 != null) {
            n9.O.a(v0, new pS.b(6, p9, 0, 0, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("result_screen_visibility", Integer.valueOf(1))), 60), new Bo.b(this));
            return;
        } else {
            return;
        }
    }

    public final void r1(int p3, String p4)
    {
        try {
            Throwable v3_2 = android.net.Uri.parse(q9.Y4.a(p3, p4));
            int v0_0 = this.B;
        } catch (Throwable v3_3) {
            ck.b.c(v3_3, "[Bank] fail to parse uri with deeplink [%s]", new Object[] {p4}));
            Ao.c.b("Fail to start eKyc via deeplink", v3_3);
            return;
        }
        if (v0_0 == 0) {
            v0_0 = 0;
        } else {
        }
        q9.X4.b(v0_0, this.getActivity(), v3_2);
        return;
    }
}
