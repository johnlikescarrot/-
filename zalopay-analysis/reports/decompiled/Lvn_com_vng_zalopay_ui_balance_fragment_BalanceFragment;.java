package vn.com.vng.zalopay.ui.balance.fragment;
public final class BalanceFragment extends Ts.c {
    public RO.a s;
    public final ng.w t;
    public Jv.b u;
    public final ng.S v;
    public boolean w;
    public s5.f x;
    public Ds.a y;
    public final boolean z;

    public BalanceFragment()
    {
        int v0_4 = new OO.b(this, 0);
        int v1_0 = ng.x.a(ng.y.c, new A0.c(28, new A0.c(27, this)));
        this.t = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, kotlin.jvm.internal.M.a.b(QO.i), new CS.x(v1_0, 18), new CS.x(v1_0, 19), v0_4);
        this.v = ng.x.b(new OO.b(this, 1));
        this.w = 1;
        this.z = 1;
        return;
    }

    public final boolean m1()
    {
        return this.z;
    }

    public final int n1()
    {
        return 2131558914;
    }

    public final void onAttach(android.content.Context p1)
    {
        super.onAttach(p1);
        q9.a5.b().h0(this);
        return;
    }

    public final void onResume()
    {
        super.onResume();
        QO.i v0_0 = this.x;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        v0_0.getClass();
        QO.i v0_4 = kotlin.collections.a0.emptyMap();
        UR.a.e("01.2099.000", v0_4);
        java.util.Objects.toString(v0_4);
        QO.i v0_3 = ((QO.i) this.t.getValue());
        v0_3.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v0_3), 0, 0, new QO.h(v0_3, 0), 3, 0);
        return;
    }

    public final void onViewCreated(android.view.View p21, android.os.Bundle p22)
    {
        super.onViewCreated(p21, p22);
        androidx.lifecycle.F v2_2 = 2131362456;
        if (((android.widget.LinearLayout) l5.s.a(2131362456, p21)) != null) {
            v2_2 = 2131364305;
            if (((android.widget.ImageView) l5.s.a(2131364305, p21)) != null) {
                v2_2 = 2131364307;
                vn.zalopay.ui.svgutil.ZPSVGImageView v7_1 = ((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(2131364307, p21));
                if (v7_1 != null) {
                    v2_2 = 2131364830;
                    android.widget.TextView v8_1 = ((android.widget.TextView) l5.s.a(2131364830, p21));
                    if (v8_1 != null) {
                        v2_2 = 2131364831;
                        android.widget.TextView v9_1 = ((android.widget.TextView) l5.s.a(2131364831, p21));
                        if (v9_1 != null) {
                            v2_2 = 2131364932;
                            if (((androidx.appcompat.widget.AppCompatTextView) l5.s.a(2131364932, p21)) != null) {
                                v2_2 = 2131365148;
                                CS.v v3_23 = l5.s.a(2131365148, p21);
                                if (v3_23 != null) {
                                    Jv.c v10 = Jv.c.a(v3_23);
                                    v2_2 = 2131365155;
                                    CS.v v3_24 = l5.s.a(2131365155, p21);
                                    if (v3_24 != null) {
                                        androidx.lifecycle.F v2_3 = 2131363561;
                                        if (((android.widget.LinearLayout) l5.s.a(2131363561, v3_24)) != null) {
                                            Jv.d v12_2 = ((android.widget.RelativeLayout) v3_24);
                                            if (((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(2131364304, v3_24)) == null) {
                                                v2_3 = 2131364304;
                                            } else {
                                                if (((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(2131364306, v3_24)) == null) {
                                                    v2_3 = 2131364306;
                                                } else {
                                                    android.view.View v13_2 = 2131364989;
                                                    Jv.d v14_3 = ((android.widget.TextView) l5.s.a(2131364989, v3_24));
                                                    if (v14_3 != null) {
                                                        v13_2 = 2131364990;
                                                        android.widget.RelativeLayout v15_3 = ((android.widget.TextView) l5.s.a(2131364990, v3_24));
                                                        if (v15_3 != null) {
                                                            Jv.d v11_4 = new Jv.d(v12_2, v12_2, v14_3, v15_3, 0);
                                                            android.view.View v13_4 = l5.s.a(2131365163, p21);
                                                            if (v13_4 == null) {
                                                                v2_2 = 2131365163;
                                                                throw new NullPointerException("Missing required view with ID: ".concat(p21.getResources().getResourceName(v2_2)));
                                                            } else {
                                                                if (((android.widget.LinearLayout) l5.s.a(2131363561, v13_4)) != null) {
                                                                    android.widget.RelativeLayout v15_1 = ((android.widget.RelativeLayout) v13_4);
                                                                    if (((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(2131364304, v13_4)) == null) {
                                                                        v2_3 = 2131364304;
                                                                    } else {
                                                                        if (((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(2131364306, v13_4)) == null) {
                                                                            v2_3 = 2131364306;
                                                                        } else {
                                                                            v2_3 = 2131365001;
                                                                            android.widget.TextView v17_1 = ((android.widget.TextView) l5.s.a(2131365001, v13_4));
                                                                            if (v17_1 != null) {
                                                                                v2_3 = 2131365002;
                                                                                android.widget.TextView v18_1 = ((android.widget.TextView) l5.s.a(2131365002, v13_4));
                                                                                if (v18_1 != null) {
                                                                                    this.u = new Jv.b(((androidx.constraintlayout.widget.ConstraintLayout) p21), v7_1, v8_1, v9_1, v10, v11_4, new Jv.d(v15_1, v15_1, v17_1, v18_1, 1));
                                                                                    ((vn.zalopay.ui.svgutil.ZPSVGImageView) this.q1().e).setOnClickListener(new OO.a(this, 0));
                                                                                    ((Jv.d) this.q1().g).c.setOnClickListener(new OO.a(this, 1));
                                                                                    ((Jv.d) this.q1().h).c.setOnClickListener(new OO.a(this, 2));
                                                                                    ((Jv.c) this.q1().f).b.setOnClickListener(new OO.a(this, 3));
                                                                                    androidx.lifecycle.T v1_32 = ((QO.i) this.t.getValue());
                                                                                    v1_32.i.f(this.getViewLifecycleOwner(), new CS.v(7, new OO.c(this, 0)));
                                                                                    v1_32.k.f(this.getViewLifecycleOwner(), new CS.v(7, new OO.c(this, 1)));
                                                                                    v1_32.m.f(this.getViewLifecycleOwner(), new CS.v(7, new OO.c(this, 2)));
                                                                                    v1_32.o.f(this.getViewLifecycleOwner(), new CS.v(7, new OO.c(this, 3)));
                                                                                    return;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                throw new NullPointerException("Missing required view with ID: ".concat(v13_4.getResources().getResourceName(v2_3)));
                                                            }
                                                        }
                                                    }
                                                    v2_3 = v13_2;
                                                }
                                            }
                                        }
                                        throw new NullPointerException("Missing required view with ID: ".concat(v3_24.getResources().getResourceName(v2_3)));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public final boolean p1()
    {
        int v0_0 = this.x;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        v0_0.getClass();
        int v0_1 = kotlin.collections.a0.emptyMap();
        UR.a.e("01.2099.004", v0_1);
        java.util.Objects.toString(v0_1);
        return 0;
    }

    public final Jv.b q1()
    {
        IllegalArgumentException v0_0 = this.u;
        if (v0_0 == null) {
            throw new IllegalArgumentException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final void r1(boolean p2)
    {
        int v2_1;
        this.w = p2;
        if (p2 == 0) {
            ((android.widget.TextView) this.q1().c).setText(((CharSequence) ((QO.i) this.t.getValue()).i.d()));
            v2_1 = 2131233578;
        } else {
            ((android.widget.TextView) this.q1().c).setText(this.getString(2131952510));
            v2_1 = 2131233575;
        }
        ((vn.zalopay.ui.svgutil.ZPSVGImageView) this.q1().e).setImageResourceInternal(v2_1);
        return;
    }
}
