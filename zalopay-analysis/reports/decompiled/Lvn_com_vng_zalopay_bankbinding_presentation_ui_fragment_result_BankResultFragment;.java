package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.result;
public final class BankResultFragment extends Lo.c {
    public final boolean s;
    public final ng.S t;
    public ji.u0 u;
    public To.e v;
    public final ng.w w;
    public Eo.a x;
    public final a9.f y;
    public final ng.S z;

    public BankResultFragment()
    {
        this.s = 1;
        this.t = ng.x.b(new No.a(this, 0));
        ng.S v0_1 = new No.a(this, 1);
        int v1_3 = ng.x.a(ng.y.c, new Io.M(28, new Lo.F(this, 29)));
        Lo.F v2_2 = kotlin.jvm.internal.M.a;
        this.w = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v2_2.b(Ro.J), new Lo.Y(v1_3, 26), new Lo.Y(v1_3, 27), v0_1);
        this.y = new a9.f(v2_2.b(No.c), new Lo.F(this, 28));
        this.z = ng.x.b(new No.a(this, 2));
        return;
    }

    public final void m1(int p17, q0.s p18)
    {
        y0.b v0_0;
        q0.v v8 = p18.x(-1015054364);
        if (!v8.I(this)) {
            v0_0 = 2;
        } else {
            v0_0 = 4;
        }
        if ((((v0_0 | p17) & 3) == 2) && (v8.c())) {
            v8.m();
        } else {
            q0.C0 v5 = q0.d.q(((Ro.J) this.w.getValue()).e, v8, 0);
            y0.b v0_15 = androidx.compose.foundation.a.b(androidx.compose.foundation.layout.P0.e(D0.p.a, 1065353216), ql.b.d, K0.X.a);
            v8.q(-1003410150);
            v8.q(212064437);
            v8.W(0);
            int v1_11 = ((A1.c) v8.d(androidx.compose.ui.platform.m0.f));
            int v2_1 = v8.G();
            kotlin.jvm.functions.Function0 v3_3 = q0.r.a;
            if (v2_1 == v3_3) {
                v2_1 = new G1.A(v1_11);
                v8.B(v2_1);
            }
            G1.A v12_1 = ((G1.A) v2_1);
            int v1_12 = v8.G();
            if (v1_12 == v3_3) {
                v1_12 = new G1.k();
                v8.B(v1_12);
            }
            int v2_4 = ((G1.k) v1_12);
            int v1_14 = v8.G();
            if (v1_14 == v3_3) {
                v1_14 = q0.d.G(Boolean.FALSE, q0.D0.e);
                v8.B(v1_14);
            }
            q0.C0 v14_1 = ((q0.C0) v1_14);
            int v1_16 = v8.G();
            if (v1_16 == v3_3) {
                v1_16 = new G1.o(v2_4);
                v8.B(v1_16);
            }
            kotlin.jvm.functions.Function1 v13_4 = ((G1.o) v1_16);
            int v1_19 = v8.G();
            if (v1_19 == v3_3) {
                v1_19 = q0.d.G(kotlin.Unit.INSTANCE, q0.D0.b);
                v8.B(v1_19);
            }
            int v1_21 = ((q0.C0) v1_19);
            int v6_7 = (v8.I(v12_1) | v8.t(257));
            a1.U v10_0 = v8.G();
            if ((v6_7 != 0) || (v10_0 == v3_3)) {
                v10_0 = new Ho.w(v1_21, v12_1, v13_4, v14_1, 11);
                v8.B(v10_0);
            }
            a1.U v10_1 = ((a1.U) v10_0);
            int v6_0 = v8.G();
            if (v6_0 == v3_3) {
                v6_0 = new Ho.x(v14_1, v13_4, 11);
                v8.B(v6_0);
            }
            int v6_2 = ((kotlin.jvm.functions.Function0) v6_0);
            D0.q v11_2 = v8.I(v12_1);
            kotlin.jvm.functions.Function1 v13_0 = v8.G();
            if ((v11_2 != null) || (v13_0 == v3_3)) {
                v13_0 = new Ho.y(v12_1, 11);
                v8.B(v13_0);
            }
            a1.x0.a(i1.n.a(v0_15, ((kotlin.jvm.functions.Function1) v13_0), 0), y0.c.c(1200550679, new Ho.B(v1_21, v2_4, v6_2, this, v5, 2), v8), v10_1, v8, 48);
            v8.W(0);
        }
        y0.b v0_6 = v8.Y();
        if (v0_6 != null) {
            v0_6.d = new Fo.b(this, p17, 10);
        }
        return;
    }

    public final boolean n1()
    {
        return this.s;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument o1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankResultFragmentArgument) this.z.getValue());
    }

    public final void onAttach(android.content.Context p2)
    {
        IllegalAccessException v2_4;
        super.onAttach(p2);
        IllegalAccessException v2_3 = this.getActivity();
        if (!(v2_3 instanceof Eo.a)) {
            v2_4 = 0;
        } else {
            v2_4 = ((Eo.a) v2_3);
        }
        if (v2_4 == null) {
            throw new IllegalAccessException("Parent activity must implement BankComponentListener");
        } else {
            this.x = v2_4;
            v2_4.m().q(this);
            return;
        }
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p4, android.view.ViewGroup p5, android.os.Bundle p6)
    {
        int v0 = this.u;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.getClass();
        v0.l(0, uo.n.a);
        return super.onCreateView(p4, p5, p6);
    }

    public final void onResume()
    {
        super.onResume();
        java.util.Map v0_7 = ((Co.a) this.t.getValue());
        kotlin.Pair v1_0 = this.o1().getDescription();
        kotlin.Pair v2_0 = "";
        if (v1_0 == null) {
            v1_0 = "";
        }
        String v3_1;
        if (v0_7.d != 1) {
            v3_1 = "01.2037.000";
        } else {
            v3_1 = "01.2035.000";
        }
        kotlin.Pair v4_2 = kotlin.TuplesKt.to("bankCode", v0_7.b);
        String v5_1 = v0_7.a;
        if (v5_1 != null) {
            v2_0 = v5_1;
        }
        java.util.Map v0_8 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {v4_2, kotlin.TuplesKt.to("zpTransID", v2_0), kotlin.TuplesKt.to("errorCode", Integer.valueOf(com.google.android.gms.internal.mlkit_vision_barcode.s0.c(v0_7.c))), kotlin.TuplesKt.to("displayMessage", v1_0)}));
        UR.a.e(v3_1, v0_8);
        java.util.Objects.toString(v0_8);
        return;
    }

    public final void onViewCreated(android.view.View p7, android.os.Bundle p8)
    {
        super.onViewCreated(p7, p8);
        Ro.J v7_3 = ((Ro.J) this.w.getValue());
        v7_3.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v7_3), 0, 0, new Lo.x(v7_3, 0, 3), 3, 0);
        return;
    }
}
