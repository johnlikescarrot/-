package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankWaitingApprovalKycFragment extends Ts.c {
    public Sn.j s;
    public final ng.S t;
    public final a9.f u;
    public final ng.S v;
    public Eo.a w;
    public Yv.i x;
    public ji.u0 y;

    public BankWaitingApprovalKycFragment()
    {
        this.t = ng.x.b(new Lo.e0(this, 0));
        this.u = new a9.f(kotlin.jvm.internal.M.a.b(Lo.f0), new Lo.F(this, 14));
        this.v = ng.x.b(new Lo.e0(this, 1));
        return;
    }

    public final int n1()
    {
        return -2079850477;
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
            this.w = v2_4;
            v2_4.m().m(this);
            return;
        }
    }

    public final void onResume()
    {
        super.onResume();
        java.util.Map v0_3 = this.q1();
        java.util.Map v0_4 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v0_3.getBankCode()), kotlin.TuplesKt.to("appVer", "11.17.0"), kotlin.TuplesKt.to("mode", v0_3.getMode())}));
        UR.a.e("01.2028.006", v0_4);
        java.util.Objects.toString(v0_4);
        return;
    }

    public final void onViewCreated(android.view.View p4, android.os.Bundle p5)
    {
        super.onViewCreated(p4, p5);
        int v5_1 = this.y;
        if (v5_1 == 0) {
            v5_1 = 0;
        }
        String v1_0 = new uo.j(this.q1().getBankName());
        v5_1.getClass();
        v5_1.l(0, v1_0);
        int v5_2 = -2079915940;
        androidx.appcompat.widget.AppCompatButton v0_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915940, p4));
        if (v0_1 != null) {
            v5_2 = -2079915888;
            if (((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(-2079915888, p4)) != null) {
                v5_2 = -2079915793;
                String v1_4 = ((android.widget.TextView) l5.s.a(-2079915793, p4));
                if (v1_4 != null) {
                    v5_2 = -2079915783;
                    android.widget.TextView v2_1 = ((android.widget.TextView) l5.s.a(-2079915783, p4));
                    if (v2_1 != null) {
                        this.s = new Sn.j(((androidx.constraintlayout.widget.ConstraintLayout) p4), v0_1, v1_4, v2_1);
                        Io.A v4_7 = ((Sn.j) this.t.getValue());
                        androidx.appcompat.widget.AppCompatButton v0_4 = v4_7.b;
                        v4_7.d.setText(this.q1().getTitle());
                        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(v4_7.c, this.q1().getMessage());
                        v0_4.setText(this.q1().getCtaLabel());
                        v0_4.setOnClickListener(new Io.A(8, this));
                        return;
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(p4.getResources().getResourceName(v5_2)));
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument q1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.oao.WaitingApprovalEKYCArgument) this.v.getValue());
    }
}
