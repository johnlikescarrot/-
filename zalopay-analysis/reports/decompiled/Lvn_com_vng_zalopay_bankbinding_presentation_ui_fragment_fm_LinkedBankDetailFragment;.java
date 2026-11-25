package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.fm;
public final class LinkedBankDetailFragment extends Lo.c {
    public Fo.o s;
    public final a9.f t;
    public final ng.S u;
    public To.j v;
    public final ng.w w;
    public Bo.n x;

    public LinkedBankDetailFragment()
    {
        kotlin.reflect.d v1_0 = kotlin.jvm.internal.M.a;
        this.t = new a9.f(v1_0.b(Mo.h), new Lo.F(this, 26));
        this.u = ng.x.b(new Ho.v0(this, 1));
        ng.w v0_5 = new Ho.v0(this, 3);
        ng.w v2_4 = ng.x.a(ng.y.c, new Io.M(27, new Lo.F(this, 27)));
        this.w = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(So.m), new Lo.Y(v2_4, 24), new Lo.Y(v2_4, 25), v0_5);
        return;
    }

    public final void m1(int p20, q0.s p21)
    {
        fo.d v1_0;
        q0.v v5 = p21.x(-814851686);
        if (!v5.I(this)) {
            v1_0 = 2;
        } else {
            v1_0 = 4;
        }
        fo.d v1_1 = (v1_0 | p20);
        if (((v1_1 & 3) == 2) && (v5.c())) {
            v5.m();
        } else {
            kotlin.jvm.functions.Function0 v3_11 = q0.d.q(this.p1().j, v5, 0);
            int v8_10 = androidx.compose.foundation.layout.c.C(androidx.compose.foundation.layout.c.A(((float) 16), 0, androidx.compose.foundation.layout.P0.e(D0.p.a, 1065353216), 2), 0, 0, 0, n9.T.a(this.requireContext(), v5), 7);
            kotlin.jvm.functions.Function0 v2_7 = (v5.I(this) | v5.p(v3_11));
            int v6_33 = v5.G();
            kotlin.jvm.functions.Function0 v9_10 = q0.r.a;
            if ((v2_7 != null) || (v6_33 == v9_10)) {
                v6_33 = new Ho.t(this, v3_11, 10);
                v5.B(v6_33);
            }
            kotlin.jvm.functions.Function0 v2_9 = v9_10;
            Z.d.a(v8_10, 0, 0, 0, 0, 0, 0, ((kotlin.jvm.functions.Function1) v6_33), v5, 0, 254);
            if (!((wo.e) v3_11.getValue()).c.b) {
                v5.q(-95299352);
            } else {
                v5.q(-89948194);
                int v6_43 = ((wo.e) v3_11.getValue()).c.a;
                kotlin.jvm.functions.Function0 v9_12 = v5.I(this);
                kotlin.jvm.functions.Function0 v10_7 = v5.G();
                if ((v9_12 != null) || (v10_7 == v2_9)) {
                    v10_7 = new Ho.v0(this, 4);
                    v5.B(v10_7);
                }
                Ho.Q.d(v6_43, ((kotlin.jvm.functions.Function0) v10_7), v5, 0);
            }
            v5.W(0);
            if (!((wo.e) v3_11.getValue()).b) {
                v5.q(-95299352);
            } else {
                v5.q(-89737859);
                com.google.android.gms.internal.mlkit_vision_barcode.a0.a(0, v5);
            }
            v5.W(0);
            if (!((wo.e) v3_11.getValue()).d) {
                v5.q(-95299352);
            } else {
                v5.q(-89636024);
                int v6_8 = this.getContext();
                kotlin.jvm.functions.Function0 v9_3 = q9.I4.b(-2079719255, new Object[] {this.o1().getName()}), v5);
                if (!android.text.TextUtils.isEmpty(v9_3)) {
                    com.google.android.gms.internal.mlkit_vision_barcode.h7.a();
                    int v6_9 = android.widget.Toast.makeText(v6_8, v9_3, 0);
                    com.google.android.gms.internal.mlkit_vision_barcode.h7.a = v6_9;
                    v6_9.setGravity(17, 0, 0);
                    com.google.android.gms.internal.mlkit_vision_barcode.h7.a.show();
                }
                int v6_11 = this.r;
                if (v6_11 == 0) {
                    v6_11 = 0;
                }
                if (!v6_11.o()) {
                    int v6_13 = this.getActivity();
                    if (v6_13 != 0) {
                        int v6_14 = v6_13.getOnBackPressedDispatcher();
                        if (v6_14 != 0) {
                            v6_14.d();
                        }
                    }
                }
            }
            v5.W(0);
            if (!((wo.e) v3_11.getValue()).e) {
                v5.q(-95299352);
            } else {
                v5.q(-89184540);
                int v6_19 = v5.I(this);
                int v8_0 = v5.G();
                if ((v6_19 != 0) || (v8_0 == v2_9)) {
                    v8_0 = new Ho.v0(this, 5);
                    v5.B(v8_0);
                }
                Ho.w0.d(this, ((kotlin.jvm.functions.Function0) v8_0), v5, (v1_1 & 14));
            }
            v5.W(0);
            kotlin.jvm.functions.Function0 v3_6 = ((fo.d) ((wo.e) v3_11.getValue()).f.a());
            if (v3_6 != null) {
                v5.q(-88984962);
                int v6_23 = v5.I(this);
                int v8_3 = v5.G();
                if ((v6_23 != 0) || (v8_3 == v2_9)) {
                    v8_3 = new Ho.v0(this, 6);
                    v5.B(v8_3);
                }
                int v8_5 = ((kotlin.jvm.functions.Function0) v8_3);
                int v6_26 = (v5.I(this) | v5.p(v3_6));
                kotlin.jvm.functions.Function0 v9_6 = v5.G();
                if ((v6_26 != 0) || (v9_6 == v2_9)) {
                    v9_6 = new Ho.q(this, v3_6, 10);
                    v5.B(v9_6);
                }
                kotlin.jvm.functions.Function0 v9_8 = ((kotlin.jvm.functions.Function0) v9_6);
                int v6_28 = v5.I(this);
                kotlin.jvm.functions.Function0 v10_2 = v5.G();
                if ((v6_28 != 0) || (v10_2 == v2_9)) {
                    v10_2 = new Ho.v0(this, 7);
                    v5.B(v10_2);
                }
                Ho.w0.a(this, v3_6, v8_5, v9_8, ((kotlin.jvm.functions.Function0) v10_2), v5, (v1_1 & 14));
                v5.W(0);
            } else {
                v5.q(-88984963);
                v5.W(0);
            }
        }
        fo.d v1_5 = v5.Y();
        if (v1_5 != null) {
            v1_5.d = new Ho.u0(this, p20, 2);
        }
        return;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument o1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument) this.u.getValue());
    }

    public final void onAttach(android.content.Context p2)
    {
        IllegalAccessException v2_4;
        super.onAttach(p2);
        IllegalAccessException v2_3 = this.getActivity();
        if (!(v2_3 instanceof Fo.o)) {
            v2_4 = 0;
        } else {
            v2_4 = ((Fo.o) v2_3);
        }
        if (v2_4 == null) {
            throw new IllegalAccessException("Parent activity must implement FundManagementComponentListener");
        } else {
            this.s = v2_4;
            v2_4.get().b(this);
            return;
        }
    }

    public final void onCreate(android.os.Bundle p8)
    {
        super.onCreate(p8);
        So.m v8_1 = this.x;
        if (v8_1 == null) {
            v8_1 = 0;
        }
        U2.a v1_0 = this.o1().getBankDetail().getBankCode();
        int v2_2 = this.o1().getBankDetail().getBankType();
        v8_1.getClass();
        So.m v8_5 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v1_0), kotlin.TuplesKt.to("linkingType", Integer.valueOf(v2_2))}));
        UR.a.e("01.2049.011", v8_5);
        java.util.Objects.toString(v8_5);
        So.m v8_6 = this.p1();
        v8_6.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v8_6), 0, 0, new So.l(v8_6, 0, 0), 3, 0);
        return;
    }

    public final void onResume()
    {
        String v0_0;
        super.onResume();
        if (this.o1().getBankDetail().getBankType() != 1) {
            v0_0 = this.getString(-2079719331);
        } else {
            v0_0 = this.getString(-2079719330);
        }
        qo.k.a.setValue(v0_0);
        return;
    }

    public final So.m p1()
    {
        return ((So.m) this.w.getValue());
    }
}
