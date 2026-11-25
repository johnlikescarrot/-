package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankIdentityEntryPointFragment extends KZ.b {
    public final a9.f r;
    public final ng.S s;
    public Z2.L t;
    public final android.os.Handler u;
    public Xt.b v;
    public vn.com.vng.zalopay.navigation.b w;
    public Yv.i x;
    public Eo.a y;

    public BankIdentityEntryPointFragment()
    {
        this.r = new a9.f(kotlin.jvm.internal.M.a.b(Lo.z), new Io.i(this, 25));
        this.s = ng.x.b(new Lo.u(this, 0));
        this.u = new android.os.Handler(android.os.Looper.getMainLooper());
        return;
    }

    public static final Object p1(vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankIdentityEntryPointFragment p5, wg.c p6)
    {
        android.content.Intent v0_2;
        if (!(p6 instanceof Lo.y)) {
            v0_2 = new Lo.y(p5, p6);
        } else {
            v0_2 = ((Lo.y) p6);
            android.os.Bundle v1_2 = ((Lo.y) p6).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((Lo.y) p6).f = (v1_2 - -2147483648);
            }
        }
        android.net.Uri v6_1 = v0_2.d;
        android.os.Bundle v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        String v2_0 = v0_2.f;
        try {
            if (v2_0 == null) {
                kotlin.ResultKt.throwOnFailure(v6_1);
                android.net.Uri v6_2 = p5.v;
                String v2_1 = 0;
                if (v6_2 == null) {
                    v6_2 = 0;
                }
                android.net.Uri v6_6;
                android.net.Uri v6_5 = ((Xt.a) v6_2.get()).c;
                if (v6_5 == null) {
                    v6_6 = 0;
                } else {
                    v6_6 = ((String) v6_5.get("kyc_deeplink"));
                }
                if ((v6_6 != null) && ((v6_6.length() != 0) && (com.google.android.gms.internal.mlkit_vision_barcode.l0.c(v6_6)))) {
                    android.net.Uri v6_9 = android.net.Uri.parse(q9.Y4.a(p5.q1().getEKycSource(), v6_6));
                    Yv.i v4_5 = p5.x;
                    if (v4_5 != null) {
                        v2_1 = v4_5;
                    }
                    Object v5_1 = p5.getActivity();
                    v0_2.f = 1;
                    if (v2_1.a(v5_1, v6_9, 1, v0_2) == v1_1) {
                        return v1_1;
                    }
                } else {
                    android.net.Uri v6_10 = p5.w;
                    if (v6_10 != null) {
                        v2_1 = v6_10;
                    }
                    android.net.Uri v6_11 = p5.getActivity();
                    Object v5_4 = p5.q1().getEKycSource();
                    v2_1.getClass();
                    android.content.Intent v0_4 = q9.B5.a(hx.f);
                    if ((v0_4 != null) && (v6_11 != null)) {
                        android.content.Intent v0_5 = v0_4.a(v6_11);
                        android.os.Bundle v1_4 = new android.os.Bundle();
                        v1_4.putInt("source", v5_4);
                        v0_5.putExtras(v1_4);
                        v6_11.startActivity(v0_5, v1_4);
                    }
                }
            } else {
                if (v2_0 != 1) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                } else {
                    kotlin.ResultKt.throwOnFailure(v6_1);
                }
            }
        } catch (Object v5_7) {
            android.net.Uri v6_14 = new Object[0];
            ck.b.c(v5_7, "[Bank] Error when start e-kyc from deeplink", v6_14);
        }
        return kotlin.Unit.INSTANCE;
    }

    public final void m1(int p13, q0.s p14)
    {
        q0.Y0 v14_10;
        q0.v v9 = p14.x(671498923);
        if (!v9.I(this)) {
            v14_10 = 2;
        } else {
            v14_10 = 4;
        }
        if ((((v14_10 | p13) & 3) == 2) && (v9.c())) {
            v9.m();
        } else {
            int v1_0 = androidx.compose.foundation.layout.c.y(androidx.compose.foundation.a.b(D0.p.a, ql.b.d, K0.X.a), WT.c.g);
            q0.Y0 v14_5 = v9.I(this);
            Fo.b v0_3 = v9.G();
            if ((v14_5 != null) || (v0_3 == q0.r.a)) {
                v0_3 = new Lo.v(this, 0);
                v9.B(v0_3);
            }
            Z.d.a(v1_0, 0, 0, 0, 0, 0, 0, ((kotlin.jvm.functions.Function1) v0_3), v9, 0, 254);
        }
        q0.Y0 v14_9 = v9.Y();
        if (v14_9 != null) {
            v14_9.d = new Fo.b(this, p13, 6);
        }
        return;
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
            this.y = v2_4;
            v2_4.m().o(this);
            return;
        }
    }

    public final void onDetach()
    {
        this.u.removeCallbacksAndMessages(0);
        super.onDetach();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        java.util.Map v0_3 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", this.q1().getBankCode()), kotlin.TuplesKt.to("appVer", "11.17.0"), kotlin.TuplesKt.to("mode", this.q1().getMode())}));
        UR.a.e("01.2028.002", v0_3);
        java.util.Objects.toString(v0_3);
        return;
    }

    public final void onViewCreated(android.view.View p1, android.os.Bundle p2)
    {
        super.onViewCreated(p1, p2);
        this.t = q9.p0.a(this);
        return;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument q1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankIdentityEntryPointArgument) this.s.getValue());
    }
}
