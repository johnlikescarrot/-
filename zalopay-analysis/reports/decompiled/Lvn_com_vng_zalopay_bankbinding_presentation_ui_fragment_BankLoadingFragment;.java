package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankLoadingFragment extends Ts.c {
    public Eo.a s;
    public final ng.S t;
    public Z2.L u;
    public To.a v;
    public final ng.w w;
    public ji.u0 x;

    public BankLoadingFragment()
    {
        this.t = ng.x.b(new Lo.A(this, 0));
        this.w = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, kotlin.jvm.internal.M.a.b(Ro.y), new Io.i(this, 26), new Io.i(this, 27), new Lo.A(this, 1));
        return;
    }

    public final int n1()
    {
        return -2079850485;
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
            this.s = v2_4;
            v2_4.m().b(this);
            return;
        }
    }

    public final void onViewCreated(android.view.View p13, android.os.Bundle p14)
    {
        super.onViewCreated(p13, p14);
        androidx.lifecycle.T v13_1 = this.x;
        if (v13_1 == null) {
            v13_1 = 0;
        }
        v13_1.getClass();
        v13_1.l(0, uo.i.a);
        this.u = q9.p0.a(this);
        cV.y v14_6 = this.w;
        if (!kotlin.jvm.internal.Intrinsics.areEqual(this.q1().getService(), "SUBMIT_FORM")) {
            Ro.y.g0(((Ro.y) v14_6.getValue()), this.q1().getBankCode(), this.q1().getClientBankCode(), this.q1().getSuggestionType(), this.q1().getInitBankNumber(), 48);
        } else {
            int v1_11 = ((Ro.y) v14_6.getValue());
            String v4_1 = this.q1().getBankCode();
            int v2_3 = this.q1().getInitBankNumber();
            int v5_1 = this.q1().getSuggestionType();
            Integer v3_1 = this.requireContext();
            v1_11.getClass();
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v1_11), 0, 0, new Ro.u(v1_11, v2_3, v3_1, v4_1, v5_1, 0), 3, 0);
        }
        androidx.lifecycle.T v13_17 = ((Ro.y) v14_6.getValue());
        v13_17.g.f(this.getViewLifecycleOwner(), new cV.y(2, new Lo.B(this, 0)));
        v13_17.p.f(this.getViewLifecycleOwner(), new cV.y(2, new Cn.b(this)));
        v13_17.i.f(this.getViewLifecycleOwner(), new cV.y(2, new Lo.B(this, 1)));
        v13_17.k.f(this.getViewLifecycleOwner(), new cV.y(2, new Lo.B(this, 2)));
        v13_17.m.f(this.getViewLifecycleOwner(), new cV.y(2, new Lo.B(this, 3)));
        v13_17.o.f(this.getViewLifecycleOwner(), new cV.y(2, new Lo.B(this, 4)));
        return;
    }

    public final vn.com.vng.zalopay.feature.argument.BankBindingArgs q1()
    {
        return ((vn.com.vng.zalopay.feature.argument.BankBindingArgs) this.t.getValue());
    }
}
