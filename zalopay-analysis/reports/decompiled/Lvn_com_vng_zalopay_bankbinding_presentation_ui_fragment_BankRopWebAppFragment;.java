package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankRopWebAppFragment extends Ts.c implements qm.d {
    public final androidx.lifecycle.T A;
    public final androidx.lifecycle.T B;
    public Qo.c C;
    public Sn.b s;
    public final a9.f t;
    public final ng.S u;
    public Eo.a v;
    public Z2.L w;
    public ji.u0 x;
    public Bo.p y;
    public final ng.w z;

    public BankRopWebAppFragment()
    {
        kotlin.reflect.d v1_0 = kotlin.jvm.internal.M.a;
        this.t = new a9.f(v1_0.b(Lo.b0), new Lo.F(this, 10));
        this.u = ng.x.b(new Lo.a0(this, 0));
        androidx.lifecycle.T v0_5 = new Lo.a0(this, 1);
        ng.w v2_4 = ng.x.a(ng.y.c, new Io.M(16, new Lo.F(this, 11)));
        this.z = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.M), new Lo.Y(v2_4, 2), new Lo.Y(v2_4, 3), v0_5);
        androidx.lifecycle.T v0_8 = new androidx.lifecycle.T();
        this.A = v0_8;
        this.B = v0_8;
        return;
    }

    public final void N(int p11, String p12, String p13)
    {
        if ((p12 == null) || (p12.length() == 0)) {
            p12 = this.getString(-2079719381);
        }
        java.io.Serializable v0_0 = new vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument(0, 0, p12, -2079915956, "", 0, 0, 99, 0);
        UnsupportedOperationException v11_0 = this.w;
        if (v11_0 == null) {
            v11_0 = 0;
        }
        v11_0.getClass();
        String v13_1 = new android.os.Bundle();
        if (!android.os.Parcelable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument)) {
            if (!java.io.Serializable.isAssignableFrom(vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument)) {
                throw new UnsupportedOperationException(vn.com.vng.zalopay.bankbinding.presentation.argument.BankMiddleDialogArgument.getName().concat(" must implement Parcelable or Serializable or must be an Enum."));
            } else {
                v13_1.putSerializable("bank_middle_dialog_args", ((java.io.Serializable) v0_0));
            }
        } else {
            v13_1.putParcelable("bank_middle_dialog_args", v0_0);
        }
        v11_0.k(-2079915995, v13_1, 0);
        return;
    }

    public final void l0()
    {
        androidx.fragment.app.FragmentActivity v0 = this.getActivity();
        if (v0 != null) {
            v0.finish();
        }
        return;
    }

    public final int n1()
    {
        return -2079850480;
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
            this.v = v2_4;
            v2_4.m().j(this);
            return;
        }
    }

    public final void onDestroyView()
    {
        IllegalStateException v0_0 = this.s;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            IllegalStateException v0_4 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebApp) v0_0.d);
            v0_4.removeAllViews();
            v0_4.clearHistory();
            v0_4.clearCache(1);
            v0_4.destroy();
            super.onDestroyView();
            return;
        }
    }

    public final void onViewCreated(android.view.View p4, android.os.Bundle p5)
    {
        super.onViewCreated(p4, p5);
        qm.h v5_1 = this.x;
        Qo.c v0_0 = 0;
        if (v5_1 == null) {
            v5_1 = 0;
        }
        v5_1.getClass();
        v5_1.l(0, uo.e.a);
        vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebApp v2_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebApp) l5.s.a(-2079915775, p4));
        if (v2_1 == null) {
            throw new NullPointerException("Missing required view with ID: ".concat(p4.getResources().getResourceName(-2079915775)));
        } else {
            this.s = new Sn.b(((android.widget.FrameLayout) p4), ((android.widget.FrameLayout) p4), v2_1, 1);
            this.w = q9.p0.a(this);
            qm.h v5_4 = this.s;
            if (v5_4 == null) {
                throw new IllegalStateException("Required value was null.");
            } else {
                qm.h v5_7 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankWebApp) v5_4.d);
                String v4_10 = new Qo.c(v5_7, this);
                v5_7.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 5.1.1; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19 ZaloPayClient/11.17.0");
                this.C = v4_10;
                this.B.f(this.getViewLifecycleOwner(), new cV.y(2, new Fo.c(6, this)));
                String v4_14 = new Qo.b(this.A);
                qm.h v5_13 = this.C;
                if (v5_13 == null) {
                    v5_13 = 0;
                }
                v5_13.e.a(v4_14);
                String v4_15 = this.C;
                if (v4_15 != null) {
                    v0_0 = v4_15;
                }
                v0_0.c(((vn.com.vng.zalopay.bankbinding.presentation.argument.BankRopWebArgument) this.u.getValue()).getUrl());
                return;
            }
        }
    }
}
