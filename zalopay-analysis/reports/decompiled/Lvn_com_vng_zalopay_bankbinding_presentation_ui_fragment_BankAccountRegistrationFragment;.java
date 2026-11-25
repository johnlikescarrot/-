package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankAccountRegistrationFragment extends TO.a {
    public Bo.b A;
    public Eo.a B;
    public Z2.L C;
    public final a9.f s;
    public final ng.S t;
    public final ng.S u;
    public To.a v;
    public final ng.w w;
    public ji.u0 x;
    public ji.u0 y;
    public vn.com.vng.zalopay.feature.argument.BankBindingArgs z;

    public BankAccountRegistrationFragment()
    {
        kotlin.reflect.d v1_0 = kotlin.jvm.internal.M.a;
        this.s = new a9.f(v1_0.b(Lo.e), new Io.i(this, 16));
        this.t = ng.x.b(new Lo.d(this, 0));
        this.u = ng.x.b(new Lo.d(this, 1));
        ng.w v0_8 = new Lo.d(this, 2);
        ng.w v2_6 = ng.x.a(ng.y.c, new Io.M(2, new Io.i(this, 17)));
        this.w = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v1_0.b(Ro.g), new Io.N(v2_6, 4), new Io.N(v2_6, 5), v0_8);
        return;
    }

    public final int n1()
    {
        return -2079850492;
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
            this.B = v2_4;
            v2_4.m().g(this);
            return;
        }
    }

    public final void onViewCreated(android.view.View p14, android.os.Bundle p15)
    {
        this = super.onViewCreated(p14, p15);
        int v0_0 = this.y;
        Boolean v8 = 0;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        v0_0.getClass();
        v0_0.l(0, uo.k.a);
        this.C = q9.p0.a(this);
        ng.w v9 = this.w;
        androidx.lifecycle.T v10_1 = ((Ro.g) v9.getValue());
        v10_1.g.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankAccountRegistrationFragment, "handleCheckOpenBankAccountState", "handleCheckOpenBankAccountState(Lvn/com/vng/zalopay/bankbinding/presentation/model/UiStateModel;)V", 0, 1)));
        v10_1.i.f(this.getViewLifecycleOwner(), new cV.y(2, new Io.l0(1, this, vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.BankAccountRegistrationFragment, "handleSubmitOpenBankAccountState", "handleSubmitOpenBankAccountState(Lvn/com/vng/zalopay/bankbinding/presentation/model/UiStateModel;)V", 0, 2)));
        int v0_8 = this.z;
        if (v0_8 == 0) {
            v0_8 = 0;
        }
        int v0_11 = ((String) v0_8.getExtraInfo().get("skip-tc"));
        if (v0_11 != 0) {
            v8 = Boolean.valueOf(Boolean.parseBoolean(v0_11));
        }
        ((Ro.g) v9.getValue()).g0(((String) this.t.getValue()), (q9.t6.b(v8) ^ 1));
        return;
    }
}
