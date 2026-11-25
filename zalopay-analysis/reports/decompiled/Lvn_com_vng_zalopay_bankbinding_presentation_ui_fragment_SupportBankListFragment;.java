package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class SupportBankListFragment extends TO.a {
    public To.a A;
    public final ng.w B;
    public Bo.n C;
    public final ng.w D;
    public vn.com.vng.zalopay.navigation.b E;
    public vn.com.vng.zalopay.feature.argument.BankBindingArgs F;
    public Xt.b G;
    public Yv.i H;
    public ji.u0 I;
    public Yv.i J;
    public Go.e K;
    public Go.c L;
    public final ji.i0 M;
    public final ji.c0 N;
    public final ji.i0 O;
    public final ji.c0 P;
    public String Q;
    public final boolean R;
    public Sn.i s;
    public final ng.S t;
    public final ng.S u;
    public Z2.L v;
    public Eo.a w;
    public ji.u0 x;
    public To.k y;
    public final ng.w z;

    public SupportBankListFragment()
    {
        this.t = ng.x.b(new Lo.o0(this, 0));
        this.u = ng.x.b(new Lo.o0(this, 1));
        int v0_4 = new Lo.o0(this, 2);
        int v2_1 = ng.y.c;
        int v1_4 = ng.x.a(v2_1, new Io.M(23, new Lo.F(this, 22)));
        ji.c0 v3_2 = kotlin.jvm.internal.M.a;
        this.z = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v3_2.b(Ro.o0), new Lo.Y(v1_4, 16), new Lo.Y(v1_4, 17), v0_4);
        int v0_8 = new Lo.o0(this, 3);
        int v1_8 = ng.x.a(v2_1, new Io.M(24, new Lo.F(this, 23)));
        this.B = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v3_2.b(Ro.y), new Lo.Y(v1_8, 18), new Lo.Y(v1_8, 19), v0_8);
        int v0_12 = new Lo.o0(this, 4);
        int v1_12 = ng.x.a(v2_1, new Io.M(22, new Lo.F(this, 21)));
        this.D = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, v3_2.b(Ro.t), new Lo.Y(v1_12, 14), new Lo.Y(v1_12, 15), v0_12);
        ji.c0 v3_5 = ji.j0.b(0, 7, 0);
        this.M = v3_5;
        this.N = ji.j0.h(v3_5);
        int v0_17 = ji.j0.b(0, 7, 0);
        this.O = v0_17;
        this.P = ji.j0.h(v0_17);
        this.Q = "";
        this.R = 1;
        return;
    }

    public final boolean m1()
    {
        return this.R;
    }

    public final int n1()
    {
        return -2079850478;
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
            v2_4.m().i(this);
            return;
        }
    }

    public final void onCreate(android.os.Bundle p1)
    {
        super.onCreate(p1);
        this.K = new Go.e();
        this.L = new Go.c();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        this.t1(uo.P0.a);
        Ro.o0 v0_1 = this.r1();
        v0_1.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v0_1), 0, 0, new Ro.f0(v0_1, 0, 3), 3, 0);
        return;
    }

    public final void onViewCreated(android.view.View p18, android.os.Bundle p19)
    {
        super.onViewCreated(p18, p19);
        Ro.o0 v2_34 = -2079915908;
        androidx.lifecycle.F v3_39 = l5.s.a(-2079915908, p18);
        if (v3_39 != null) {
            int v5_0 = -2079915793;
            int v6_1 = ((android.widget.TextView) l5.s.a(-2079915793, v3_39));
            if (v6_1 == 0) {
                throw new NullPointerException("Missing required view with ID: ".concat(v3_39.getResources().getResourceName(-2079915793)));
            } else {
                int v9_1 = new Sn.y(((android.widget.LinearLayout) v3_39), ((android.widget.LinearLayout) v3_39), v6_1);
                v2_34 = -2079915860;
                int v10_4 = ((androidx.recyclerview.widget.RecyclerView) l5.s.a(-2079915860, p18));
                if (v10_4 != 0) {
                    v2_34 = -2079915847;
                    U2.a v11_4 = ((androidx.appcompat.widget.SearchView) l5.s.a(-2079915847, p18));
                    if (v11_4 != null) {
                        v2_34 = -2079915777;
                        androidx.lifecycle.F v3_27 = l5.s.a(-2079915777, p18);
                        if (v3_27 != null) {
                            Ro.o0 v2_73 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915935, v3_27));
                            if (v2_73 == null) {
                                v5_0 = -2079915935;
                            } else {
                                int v6_22 = ((android.widget.TextView) l5.s.a(-2079915793, v3_27));
                                if (v6_22 != 0) {
                                    this.s = new Sn.i(((androidx.constraintlayout.widget.ConstraintLayout) p18), v9_1, v10_4, v11_4, new Sn.q(((android.widget.LinearLayout) v3_27), v2_73, ((android.widget.LinearLayout) v3_27), v6_22), 0);
                                    this.v = q9.p0.a(vn.com.vng.zalopay.bankbinding.presentation.ui.fragment.SupportBankListFragment v0);
                                    IllegalArgumentException v1_48 = this.r1();
                                    v1_48.getClass();
                                    kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v1_48), 0, 0, new Ro.X(v1_48, 0, 2), 3, 0);
                                    IllegalArgumentException v1_49 = this.r1();
                                    Ro.o0 v2_79 = v1_49.Q;
                                    if (v2_79 != null) {
                                        v2_79.cancel(0);
                                    }
                                    v1_49.Q = kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v1_49), 0, 0, new Ro.i0(this.N, v1_49, 0, 0), 3, 0);
                                    IllegalArgumentException v1_1 = this.r1();
                                    Ro.o0 v2_2 = v1_1.S;
                                    if (v2_2 != null) {
                                        v2_2.cancel(0);
                                    }
                                    v1_1.S = kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v1_1), v1_1.d.c, 0, new Ro.i0(this.P, v1_1, 0, 1), 2, 0);
                                    IllegalArgumentException v1_2 = this.F;
                                    if (v1_2 == null) {
                                        v1_2 = 0;
                                    }
                                    if (v1_2.getShouldDisplayEKycSuggestion()) {
                                        this.t1(uo.R0.a);
                                        IllegalArgumentException v1_6 = new android.text.SpannableStringBuilder(this.getString(-2079719262));
                                        v1_6.setSpan(new android.text.style.ForegroundColorSpan(this.requireContext().getColor(2131099754)), 0, v1_6.length(), 34);
                                        Ro.o0 v2_11 = new StringBuilder();
                                        v2_11.append(this.getString(-2079719350));
                                        v2_11.append(" ");
                                        int v5_7 = this.u;
                                        ((Sn.y) v5_7.getValue()).c.setText(v2_11);
                                        ((Sn.y) v5_7.getValue()).c.append(v1_6);
                                        ((Sn.y) v5_7.getValue()).b.setOnClickListener(new Lo.p0(this, 1));
                                        com.google.android.gms.internal.mlkit_vision_barcode.W7.d(((Sn.y) v5_7.getValue()).b, 1);
                                    }
                                    IllegalArgumentException v1_15 = this.x;
                                    if (v1_15 == null) {
                                        v1_15 = 0;
                                    }
                                    v1_15.getClass();
                                    v1_15.l(0, uo.f.a);
                                    this.Q = "";
                                    ((androidx.recyclerview.widget.RecyclerView) this.q1().d).setHasFixedSize(1);
                                    Ro.o0 v2_24 = ((androidx.recyclerview.widget.RecyclerView) this.q1().d);
                                    androidx.core.view.h0.l(v2_24, new hJ.g(v2_24));
                                    if (!v2_24.isAttachedToWindow()) {
                                        v2_24.addOnAttachStateChangeListener(new nY.e());
                                    } else {
                                        v2_24.requestApplyInsets();
                                    }
                                    this.s1();
                                    Ro.o0 v2_25 = this.K;
                                    if (v2_25 == null) {
                                        v2_25 = 0;
                                    }
                                    v2_25.g.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 9)));
                                    Ro.o0 v2_28 = this.K;
                                    if (v2_28 == null) {
                                        v2_28 = 0;
                                    }
                                    v2_28.i.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 10)));
                                    Ro.o0 v2_30 = this.L;
                                    if (v2_30 == null) {
                                        v2_30 = 0;
                                    }
                                    v2_30.g.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 11)));
                                    Ro.o0 v2_32 = this.K;
                                    if (v2_32 == null) {
                                        v2_32 = 0;
                                    }
                                    v2_32.k.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 12)));
                                    ((Sn.q) this.t.getValue()).b.setOnClickListener(new Lo.p0(this, 0));
                                    Ro.o0 v2_41 = ((androidx.appcompat.widget.SearchView) this.q1().e);
                                    IllegalArgumentException v1_18 = ji.j0.c("");
                                    v2_41.setOnQueryTextListener(new Ko.h(v2_41, v1_18));
                                    IllegalArgumentException v1_19 = ji.j0.h(v1_18);
                                    Ro.o0 v2_42 = this.r1();
                                    int v5_25 = v2_42.R;
                                    if (v5_25 != 0) {
                                        v5_25.cancel(0);
                                    }
                                    v2_42.R = kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v2_42), 0, 0, new Ro.X(v1_19, v2_42, 0, 4), 3, 0);
                                    int v10_5 = androidx.lifecycle.l0.i(this.getLifecycle());
                                    kotlinx.coroutines.BuildersKt.launch$default(v10_5, 0, 0, new androidx.lifecycle.z(v10_5, new Fo.f(v1_19, this, 0, 16), 0, 0), 3, 0);
                                    IllegalArgumentException v1_20 = this.r1();
                                    v1_20.o.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 13)));
                                    v1_20.q.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 18)));
                                    v1_20.u.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 19)));
                                    v1_20.s.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 20)));
                                    v1_20.w.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 2)));
                                    v1_20.A.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 3)));
                                    v1_20.C.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 4)));
                                    v1_20.F.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 5)));
                                    v1_20.y.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 6)));
                                    v1_20.H.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 7)));
                                    v1_20.J.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 14)));
                                    v1_20.L.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 15)));
                                    v1_20.N.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 16)));
                                    v1_20.P.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 17)));
                                    ((Ro.y) this.B.getValue()).g.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 8)));
                                    ((Ro.t) this.D.getValue()).f.f(this.getViewLifecycleOwner(), new cV.y(2, new Ko.j(this, 1)));
                                    if (p19 == null) {
                                        IllegalArgumentException v1_31 = this.F;
                                        if (v1_31 == null) {
                                            v1_31 = 0;
                                        }
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(v1_31.getService(), "LINK_STATUS")) {
                                            IllegalArgumentException v1_35 = this.F;
                                            if (v1_35 == null) {
                                                v1_35 = 0;
                                            }
                                            IllegalArgumentException v1_37;
                                            IllegalArgumentException v1_36 = v1_35.getExtraArgument();
                                            if (!(v1_36 instanceof vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs)) {
                                                v1_37 = 0;
                                            } else {
                                                v1_37 = ((vn.com.vng.zalopay.feature.argument.BankLinkStatusArgs) v1_36);
                                            }
                                            if (v1_37 != null) {
                                                Ro.o0 v2_68 = this.r1();
                                                v2_68.getClass();
                                                kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v2_68), 0, 0, new Ro.X(v2_68, v1_37, 0, 3), 3, 0);
                                            } else {
                                                Ao.c.b("Invalid argument for link-status deeplink", new IllegalArgumentException("BankLinkStatusArgs is null"));
                                                return;
                                            }
                                        }
                                    }
                                    return;
                                }
                            }
                            throw new NullPointerException("Missing required view with ID: ".concat(v3_27.getResources().getResourceName(v5_0)));
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(p18.getResources().getResourceName(v2_34)));
    }

    public final boolean p1()
    {
        this.t1(uo.D0.a);
        return 0;
    }

    public final Sn.i q1()
    {
        IllegalStateException v0_0 = this.s;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final Ro.o0 r1()
    {
        return ((Ro.o0) this.z.getValue());
    }

    public final void s1()
    {
        if (!kotlin.jvm.internal.Intrinsics.areEqual(this.Q, "default_view")) {
            this.Q = "default_view";
            androidx.recyclerview.widget.RecyclerView v0_7 = ((androidx.recyclerview.widget.RecyclerView) this.q1().d);
            this.getContext();
            int v1_2 = new androidx.recyclerview.widget.GridLayoutManager(3);
            v1_2.K = new Lo.q0(this);
            v0_7.setLayoutManager(v1_2);
            androidx.recyclerview.widget.RecyclerView v0_1 = this.q1();
            int v1_3 = this.K;
            if (v1_3 == 0) {
                v1_3 = 0;
            }
            ((androidx.recyclerview.widget.RecyclerView) v0_1.d).setAdapter(v1_3);
            return;
        } else {
            return;
        }
    }

    public final void t1(uo.V0 p8)
    {
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), kotlinx.coroutines.Dispatchers.getDefault(), 0, new Fo.f(this, p8, 0, 14), 2, 0);
        return;
    }

    public final void u1(uo.C0 p11)
    {
        String v1 = 0;
        if (!(p11 instanceof uo.y0)) {
            if (!(p11 instanceof uo.z0)) {
                return;
            } else {
                kotlin.Unit v11_2 = ((uo.z0) p11).a;
                IO.a v2_0 = v11_2.a;
                if (v2_0 != null) {
                    v1 = v2_0.a;
                }
                if (v1 == null) {
                    v1 = "";
                }
                this.t1(new uo.E0(new IO.a(v11_2.g, v11_2.f, v11_2.e, v11_2.j, v1, v11_2.h, v11_2.i)));
                return;
            }
        } else {
            IO.a v2_5 = ((uo.y0) p11).a;
            this.t1(new uo.L0(v2_5.a, v2_5.e));
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), kotlinx.coroutines.Dispatchers.getDefault(), 0, new Fo.f(this, p11, 0, 15), 2, 0);
            return;
        }
    }
}
