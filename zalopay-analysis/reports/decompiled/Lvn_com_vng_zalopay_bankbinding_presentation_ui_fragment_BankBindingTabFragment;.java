package vn.com.vng.zalopay.bankbinding.presentation.ui.fragment;
public final class BankBindingTabFragment extends Ts.c {
    public final a9.f s;
    public final ng.S t;
    public Sn.b u;
    public Eo.a v;
    public Go.a w;
    public final ng.S x;
    public final Bo.b y;

    public BankBindingTabFragment()
    {
        this.s = new a9.f(kotlin.jvm.internal.M.a.b(Lo.h), new Io.i(this, 18));
        this.t = ng.x.b(new Lo.f(this, 0));
        this.x = ng.x.b(new Lo.f(this, 1));
        this.y = new Bo.b(this);
        return;
    }

    public final int n1()
    {
        return -2079850491;
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
            v2_4.m().getClass();
            return;
        }
    }

    public final void onViewCreated(android.view.View p6, android.os.Bundle p7)
    {
        super.onViewCreated(p6, p7);
        int v7_1 = -2079915836;
        Go.a v0_3 = ((com.google.android.material.tabs.TabLayout) l5.s.a(-2079915836, p6));
        if (v0_3 != null) {
            v7_1 = -2079915776;
            uo.r v1_10 = ((androidx.viewpager2.widget.ViewPager2) l5.s.a(-2079915776, p6));
            if (v1_10 != null) {
                this.u = new Sn.b(((android.widget.LinearLayout) p6), v0_3, v1_10, 0);
                this.w = new Go.a(this);
                androidx.viewpager2.widget.ViewPager2 v6_14 = this.q1();
                int v7_10 = this.w;
                Go.a v0_2 = 0;
                if (v7_10 == 0) {
                    v7_10 = 0;
                }
                ((androidx.viewpager2.widget.ViewPager2) v6_14.d).setAdapter(v7_10);
                int v7_13 = ((com.google.android.material.tabs.TabLayout) this.q1().c);
                uo.r v1_5 = ((androidx.viewpager2.widget.ViewPager2) this.q1().d);
                androidx.viewpager2.widget.ViewPager2 v6_18 = new l00.d(v7_13, v1_5, this.y);
                if (v6_18.e) {
                    throw new IllegalStateException("ZPTabLayoutMediator is already attached");
                } else {
                    int v2_4 = v1_5.getAdapter();
                    if (v2_4 == 0) {
                        throw new IllegalStateException("ZPTabLayoutMediator attached before ViewPager2 has an adapter");
                    } else {
                        v6_18.d = v2_4;
                        v6_18.e = 1;
                        v1_5.b(new l00.b(v7_13));
                        v7_13.a(new l00.c(v1_5));
                        java.util.LinkedHashMap v3_6 = new P3.e(1, v6_18);
                        androidx.recyclerview.widget.W v4_1 = v6_18.d;
                        if (v4_1 != null) {
                            v4_1.registerAdapterDataObserver(v3_6);
                        }
                        v6_18.a();
                        v7_13.l(v1_5.getCurrentItem(), 0, 1, 1);
                        int v7_16 = this.t;
                        ((androidx.viewpager2.widget.ViewPager2) this.q1().d).setOffscreenPageLimit(((vn.com.vng.zalopay.bankbinding.presentation.argument.BankTabsArgument) v7_16.getValue()).getModels().size());
                        androidx.viewpager2.widget.ViewPager2 v6_27 = this.w;
                        if (v6_27 != null) {
                            v0_2 = v6_27;
                        }
                        androidx.viewpager2.widget.ViewPager2 v6_2 = ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankTabsArgument) v7_16.getValue()).getModels();
                        v0_2.getClass();
                        androidx.viewpager2.widget.ViewPager2 v6_4 = ((Iterable) v6_2).iterator();
                        int v7_2 = 0;
                        while (v6_4.hasNext()) {
                            uo.r v1_1 = v6_4.next();
                            int v2_0 = (v7_2 + 1);
                            if (v7_2 < 0) {
                                kotlin.collections.E.throwIndexOverflow();
                            }
                            v0_2.n.put(Integer.valueOf(v7_2), ((uo.r) v1_1));
                            v7_2 = v2_0;
                        }
                        v0_2.notifyDataSetChanged();
                        ((androidx.viewpager2.widget.ViewPager2) this.q1().d).b(new Lo.g(this));
                        return;
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(p6.getResources().getResourceName(v7_1)));
    }

    public final Sn.b q1()
    {
        IllegalStateException v0_0 = this.u;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }
}
