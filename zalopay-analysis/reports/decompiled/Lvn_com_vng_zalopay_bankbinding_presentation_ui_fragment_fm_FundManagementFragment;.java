package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.fm;
public final class FundManagementFragment extends Lo.c {
    public Fo.o s;
    public final ng.S t;
    public To.i u;
    public final ng.w v;
    public Bo.n w;
    public com.airbnb.lottie.network.g x;
    public final ng.w y;
    public final ji.u0 z;

    public FundManagementFragment()
    {
        this.t = ng.x.b(new Ho.e0(this, 1));
        ji.u0 v0_10 = new Ho.e0(this, 4);
        kotlin.reflect.d v2_1 = ng.y.c;
        ng.w v1_2 = ng.x.a(v2_1, new Io.M(25, new Lo.F(this, 24)));
        Lo.Y v3_2 = kotlin.jvm.internal.M.a;
        this.v = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v3_2.b(So.i), new Lo.Y(v1_2, 20), new Lo.Y(v1_2, 21), v0_10);
        ji.u0 v0_4 = new Ho.e0(this, 7);
        ng.w v1_7 = ng.x.a(v2_1, new Io.M(26, new Lo.F(this, 25)));
        this.y = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v3_2.b(FT.b), new Lo.Y(v1_7, 22), new Lo.Y(v1_7, 23), v0_4);
        this.z = ji.j0.c(DT.c.a);
        return;
    }

    public final void m1(int p13, q0.s p14)
    {
        int v14_4;
        q0.v v7 = p14.x(-1547016102);
        if (!v7.I(this)) {
            v14_4 = 2;
        } else {
            v14_4 = 4;
        }
        int v14_5 = (v14_4 | p13);
        if (((v14_5 & 3) == 2) && (v7.c())) {
            v7.m();
        } else {
            int v0_12 = q0.d.q(this.p1().p, v7, 0);
            int v1_6 = q0.d.q(((FT.b) this.y.getValue()).f, v7, 0);
            q0.C0 v11 = q0.d.q(this.z, v7, 0);
            String v2_5 = Al.o.c(((wo.b) v0_12.getValue()).a, v7);
            String v3_1 = v7.I(this);
            int v4_2 = v7.G();
            int v9_1 = q0.r.a;
            if ((v3_1 != null) || (v4_2 == v9_1)) {
                v4_2 = new Ho.e0(this, 8);
                v7.B(v4_2);
            }
            int v0_17;
            Al.o.a(v2_5, ((kotlin.jvm.functions.Function0) v4_2), androidx.compose.foundation.layout.P0.e(D0.p.a, 1065353216), 0, 0, y0.c.c(-986631131, new Ho.d(this, v1_6, v0_12, 10), v7), v7, 196992);
            int v0_16 = ((wo.b) v0_12.getValue()).h;
            if (v0_16 == 0) {
                v0_17 = 0;
            } else {
                v0_17 = ((uo.g1) v0_16.a());
            }
            if (!(v0_17 instanceof uo.e1)) {
                if (!(v0_17 instanceof uo.d1)) {
                    if (!(v0_17 instanceof uo.f1)) {
                        v7.q(-623607746);
                        v7.W(0);
                    } else {
                        v7.q(2142925397);
                        com.google.android.gms.internal.mlkit_vision_barcode.a0.a(0, v7);
                        v7.W(0);
                    }
                } else {
                    v7.q(2142803226);
                    this.o1(((wo.i) ((uo.d1) v0_17).a), v7, ((v14_5 << 3) & 112));
                    v7.W(0);
                }
            } else {
                v7.q(2142416997);
                int v1_1 = q9.I4.a(-2079719313, v7);
                String v2_0 = q9.I4.a(-2079719397, v7);
                String v3_0 = q9.I4.a(-2079719411, v7);
                int v0_3 = v7.G();
                if (v0_3 == v9_1) {
                    v0_3 = new Ho.P(9);
                    v7.B(v0_3);
                }
                Ho.s0.j(v1_1, v2_0, v3_0, 0, ((kotlin.jvm.functions.Function0) v0_3), 0, v7, 24576, 40);
                v7.W(0);
            }
            Ho.s0.g(this, ((DT.d) v11.getValue()), v7, (v14_5 & 14));
        }
        int v14_2 = v7.Y();
        if (v14_2 != 0) {
            v14_2.d = new Ho.X(this, p13);
        }
        return;
    }

    public final void o1(wo.i p34, q0.s p35, int p36)
    {
        Ho.P v3_9;
        q0.v v10 = p35.x(-863422332);
        if ((p36 & 6) != 0) {
            v3_9 = p36;
        } else {
            Ho.P v3_6;
            if (!v10.p(p34)) {
                v3_6 = 2;
            } else {
                v3_6 = 4;
            }
            v3_9 = (v3_6 | p36);
        }
        if ((p36 & 48) == 0) {
            String v4_4;
            if (!v10.I(this)) {
                v4_4 = 16;
            } else {
                v4_4 = 32;
            }
            v3_9 |= v4_4;
        }
        if (((v3_9 & 19) == 18) && (v10.c())) {
            v10.m();
        } else {
            if (!(p34 instanceof wo.g)) {
                if (!(p34 instanceof wo.h)) {
                    if (!(p34 instanceof wo.f)) {
                        v10.q(-820168772);
                        v10.W(0);
                        throw new kotlin.NoWhenBranchMatchedException();
                    } else {
                        v10.q(344861758);
                        String v4_7 = ((wo.f) p34).a;
                        String v5_1 = ((wo.f) p34).b;
                        String v6 = q9.I4.a(-2079719288, v10);
                        String v7 = q9.I4.a(-2079719337, v10);
                        Ho.P v3_21 = v10.G();
                        if (v3_21 == q0.r.a) {
                            v3_21 = new Ho.P(8);
                            v10.B(v3_21);
                        }
                        Ho.s0.j(v4_7, v5_1, v6, v7, ((kotlin.jvm.functions.Function0) v3_21), 0, v10, 24576, 32);
                        v10.W(0);
                    }
                } else {
                    v10.q(344733883);
                    v10.W(0);
                    int v11_4 = new vn.com.vng.zalopay.feature.argument.BankBindingArgs(0, 0, 0, "WEB_THREE_DS", "FundManagement", 0, ((wo.h) p34).a, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 524199, 0);
                    Ho.P v3_0 = this.getContext();
                    if (v3_0 != null) {
                        m9.p.c(v3_0, v11_4);
                    }
                }
            } else {
                v10.q(344618594);
                v10.W(0);
                int v11_1 = new vn.com.vng.zalopay.feature.argument.BankBindingArgs(0, 0, 0, "OTP", "FundManagement", 0, ((wo.g) p34).a, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 524199, 0);
                Ho.P v3_7 = this.getContext();
                if (v3_7 != null) {
                    m9.p.c(v3_7, v11_1);
                }
            }
        }
        Ho.P v3_8 = v10.Y();
        if (v3_8 != null) {
            v3_8.d = new Ho.u(this, p34, p36, 3);
        }
        return;
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
            v2_4.get().a(this);
            return;
        }
    }

    public final void onCreate(android.os.Bundle p14)
    {
        super.onCreate(p14);
        int v14_1 = this.p1();
        v14_1.g0();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v14_1), 0, 0, new So.b(v14_1, 0, 3), 3, 0);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v14_1), 0, 0, new So.b(v14_1, 0, 1), 3, 0);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v14_1), 0, 0, new So.g(v14_1, 0), 3, 0);
        FT.b.g0(((FT.b) this.y.getValue()), 0, 0, 9, 0, 103);
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new Mo.e(this, 0, 3), 3, 0);
        return;
    }

    public final void onResume()
    {
        super.onResume();
        So.i v0_0 = this.w;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        v0_0.getClass();
        So.i v0_7 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("appVer", "11.17.0"), kotlin.TuplesKt.to("kycLevel", Integer.valueOf(((Number) ((ng.S) v0_0.b).getValue()).intValue())), kotlin.TuplesKt.to("eKYC", ((String) ((ng.S) v0_0.c).getValue()))}));
        UR.a.e("01.2049.000", v0_7);
        java.util.Objects.toString(v0_7);
        So.i v0_8 = qo.k.a;
        v0_8.getClass();
        v0_8.l(0, "");
        So.i v0_9 = this.p1();
        v0_9.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v0_9), 0, 0, new So.h(v0_9, 0), 3, 0);
        this.p1().h0();
        return;
    }

    public final void onViewCreated(android.view.View p11, android.os.Bundle p12)
    {
        super.onViewCreated(p11, p12);
        U2.a v11_1 = this.p1();
        v11_1.r.f(this.getViewLifecycleOwner(), new cV.y(2, new Mo.d(this, 2)));
        v11_1.t.f(this.getViewLifecycleOwner(), new cV.y(2, new Mo.d(this, 3)));
        v11_1.v.f(this.getViewLifecycleOwner(), new cV.y(2, new Mo.d(this, 4)));
        v11_1.x.f(this.getViewLifecycleOwner(), new cV.y(2, new Mo.d(this, 5)));
        if (p12 == null) {
            vn.com.vng.zalopay.feature.argument.BankUnLinkStatusArgs v2_9 = ((vn.com.vng.zalopay.feature.argument.FundingManagerArgs) this.t.getValue()).getUnLinkStatusArgs();
            if (v2_9 != null) {
                So.i v1_8 = this.p1();
                android.content.Context v3_4 = this.requireContext();
                v1_8.getClass();
                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v1_8), 0, 0, new Io.g0(v1_8, v2_9, v3_4, 0, 10), 3, 0);
            }
        }
        return;
    }

    public final So.i p1()
    {
        return ((So.i) this.v.getValue());
    }

    public final void q1(Yn.e p12)
    {
        androidx.lifecycle.T v0_0 = this.w;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        oo.a v2_0;
        Xs.b v1_6 = p12.a;
        oo.a v2_2 = p12.f;
        if (v2_2 == null) {
            v2_0 = 0;
        } else {
            v2_0 = v2_2.a;
        }
        Io.g0 v3_0 = "";
        if (v2_0 == null) {
            v2_0 = "";
        }
        v0_0.getClass();
        androidx.lifecycle.T v0_3 = kotlin.TuplesKt.to("sof", v1_6);
        Xs.b v1_0 = q9.y6.i(v2_0);
        if (v1_0 != null) {
            v3_0 = v1_0;
        }
        androidx.lifecycle.T v0_5 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {v0_3, kotlin.TuplesKt.to("status", v3_0)}));
        UR.a.e("01.2049.008", v0_5);
        java.util.Objects.toString(v0_5);
        vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument v4_0 = this.getContext();
        if (v4_0 != null) {
            oo.a v2_1 = this.p1();
            androidx.lifecycle.T v0_6 = v2_1.q;
            Xs.b v1_4 = p12.g;
            if (v1_4 != null) {
                Io.g0 v3_1 = v1_4.c;
                Xs.b v1_5 = v1_4.a;
                String v6_0 = p12.i;
                if (v6_0 == 1) {
                    if (kotlin.text.StringsKt.isBlank(v1_5)) {
                        if (v3_1 != null) {
                            androidx.lifecycle.T v0_7 = v2_1.s;
                            vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData.Companion.getClass();
                            v0_7.l(new Xs.b(new vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailArgument(new vn.com.vng.zalopay.bankbinding.presentation.argument.fm.LinkedBankDetailData(v3_1.getBankCode(), v3_1.getBankConnectorCode(), v3_1.getBankType(), v3_1.getFirstNum(), v3_1.getLastNum()), p12.a, p12.d, p12.c, p12.b)));
                        } else {
                            Ao.c.b("[FM] Not receive binding bank detail from BE", new IllegalArgumentException());
                        }
                    } else {
                        v0_6.l(new Xs.b(v1_5));
                        return;
                    }
                } else {
                    if (v6_0 == 2) {
                        if (v3_1 != null) {
                            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v2_1), 0, 0, new Io.g0(v2_1, v3_1, v4_0, 0, 11), 3, 0);
                            return;
                        }
                    } else {
                        v0_6.l(new Xs.b(v1_5));
                        return;
                    }
                }
            } else {
                Ao.c.b("[FM] Not exist CTA entity from BE", new IllegalArgumentException());
                return;
            }
        }
        return;
    }
}
