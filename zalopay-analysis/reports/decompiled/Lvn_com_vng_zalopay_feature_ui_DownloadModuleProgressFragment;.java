package vn.com.vng.zalopay.feature.ui;
public final class DownloadModuleProgressFragment extends e3.c {
    public lx.a A;
    public final ng.S B;
    public boolean C;
    public final ng.S D;
    public nY.p w;
    public androidx.lifecycle.r0 x;
    public Jv.b y;
    public final ng.w z;

    public DownloadModuleProgressFragment()
    {
        ng.S v0_4 = new mS.a(3, this);
        int v1_0 = ng.x.a(ng.y.c, new o1.p(5, new o1.p(4, this)));
        this.z = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, kotlin.jvm.internal.M.a.b(aP.a), new androidx.fragment.app.L0(v1_0, 9), new androidx.fragment.app.L0(v1_0, 10), v0_4);
        this.B = ng.x.b(new oM.h(4));
        this.D = ng.x.b(new oM.h(5));
        return;
    }

    public static void r1(vn.com.vng.zalopay.feature.ui.DownloadModuleProgressFragment p1)
    {
        if (!p1.C) {
            p1.C = 1;
            ((com.google.firebase.perf.metrics.Trace) p1.B.getValue()).start();
        }
        int v0_1 = p1.w;
        if (v0_1 == 0) {
            v0_1 = 0;
        }
        if (v0_1.a()) {
            p1.s1(0);
            super.onResume();
            lx.a v1_1 = p1.A;
            if (v1_1 != null) {
                v1_1.S0();
            }
            return;
        } else {
            p1.o1(-6);
            return;
        }
    }

    public final void n1()
    {
        this.t1(2);
        Z2.L v0_1 = this.A;
        if (v0_1 == null) {
            q9.p0.a(this).o();
            return;
        } else {
            v0_1.w0();
            return;
        }
    }

    public final void o1(int p4)
    {
        this.t1(p4);
        int v0_0 = this.y;
        Jv.b v1 = 0;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        ((android.widget.TextView) v0_0.g).setVisibility(8);
        android.widget.Button v4_1 = ((android.util.SparseIntArray) this.D.getValue()).get(p4, 2131952482);
        int v0_3 = this.y;
        if (v0_3 == 0) {
            v0_3 = 0;
        }
        ((android.widget.TextView) v0_3.c).setText(this.getString(v4_1));
        android.widget.Button v4_3 = this.y;
        if (v4_3 == null) {
            v4_3 = 0;
        }
        ((android.widget.TextView) v4_3.c).setVisibility(0);
        android.widget.Button v4_6 = this.y;
        if (v4_6 != null) {
            v1 = v4_6;
        }
        ((android.widget.Button) v1.e).setVisibility(0);
        return;
    }

    public final void onActivityCreated(android.os.Bundle p3)
    {
        super.onActivityCreated(p3);
        ((aP.a) this.z.getValue()).d.f(this.getViewLifecycleOwner(), new GP.c(12, this));
        android.widget.Button v3_2 = this.y;
        if (v3_2 == null) {
            v3_2 = 0;
        }
        ((android.widget.Button) v3_2.e).setOnClickListener(new androidx.media3.ui.h(17, this));
        return;
    }

    public final void onAttach(android.content.Context p4)
    {
        androidx.lifecycle.r0 v4_2;
        super.onAttach(p4);
        if (!(p4 instanceof lx.a)) {
            v4_2 = 0;
        } else {
            v4_2 = ((lx.a) p4);
        }
        this.A = v4_2;
        androidx.lifecycle.r0 v4_6 = q9.a5.a();
        v4_6.getClass();
        dagger.internal.o v0_2 = dagger.internal.f.b(new Yt.w(new CC.j(v4_6, 6), 11));
        WH.e v1_4 = p9.m7.a(1);
        v1_4.put(aP.a, v0_2);
        dagger.internal.o v0_5 = dagger.internal.f.b(new WH.e(1, new dagger.internal.n(v1_4)));
        androidx.lifecycle.r0 v4_3 = ((CC.f) v4_6).l();
        p9.n7.b(v4_3);
        this.w = v4_3;
        this.x = ((androidx.lifecycle.r0) v0_5.get());
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p9, android.view.ViewGroup p10, android.os.Bundle p11)
    {
        String v9_1 = p9.inflate(2131558929, p10, 0);
        int v10_1 = 2131362112;
        android.widget.Button v2_1 = ((android.widget.Button) l5.s.a(2131362112, v9_1));
        if (v2_1 != null) {
            v10_1 = 2131363138;
            if (((android.widget.ImageView) l5.s.a(2131363138, v9_1)) != null) {
                v10_1 = 2131363919;
                android.widget.ProgressBar v3_1 = ((android.widget.ProgressBar) l5.s.a(2131363919, v9_1));
                if (v3_1 != null) {
                    v10_1 = 2131364860;
                    android.widget.TextView v4_1 = ((android.widget.TextView) l5.s.a(2131364860, v9_1));
                    if (v4_1 != null) {
                        v10_1 = 2131364632;
                        android.widget.TextView v5_1 = ((android.widget.TextView) l5.s.a(2131364632, v9_1));
                        if (v5_1 != null) {
                            v10_1 = 2131364741;
                            android.widget.TextView v6_1 = ((android.widget.TextView) l5.s.a(2131364741, v9_1));
                            if (v6_1 != null) {
                                v10_1 = 2131365000;
                                android.widget.TextView v7_1 = ((android.widget.TextView) l5.s.a(2131365000, v9_1));
                                if (v7_1 != null) {
                                    android.widget.FrameLayout v1_1 = ((android.widget.FrameLayout) v9_1);
                                    this.y = new Jv.b(v1_1, v2_1, v3_1, v4_1, v5_1, v6_1, v7_1);
                                    return v1_1;
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException(wmoi.igob.ggcn(7755).concat(v9_1.getResources().getResourceName(v10_1)));
    }

    public final void onResume()
    {
        super.onResume();
        if (!this.C) {
            this.C = 1;
            ((com.google.firebase.perf.metrics.Trace) this.B.getValue()).start();
            return;
        } else {
            return;
        }
    }

    public final void p1()
    {
        this.t1(1);
        lx.a v0_1 = this.A;
        if (v0_1 != null) {
            v0_1.G0();
        }
        return;
    }

    public final void q1(long p2, long p4)
    {
        this.s1(((int) ((((float) p2) * 1120403456) / ((float) p4))));
        int v2_5 = ((Integer) ((aP.a) this.z.getValue()).d.d());
        if ((v2_5 != 0) && (1 != v2_5.intValue())) {
            this.o1(-6);
        }
        return;
    }

    public final void s1(int p4)
    {
        int v0_0 = this.y;
        Jv.b v1 = 0;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        com.google.android.gms.internal.mlkit_vision_barcode.W7.c(((android.widget.TextView) v0_0.c));
        int v0_12 = this.y;
        if (v0_12 == 0) {
            v0_12 = 0;
        }
        com.google.android.gms.internal.mlkit_vision_barcode.W7.c(((android.widget.Button) v0_12.e));
        int v0_3 = this.y;
        if (v0_3 == 0) {
            v0_3 = 0;
        }
        ((android.widget.ProgressBar) v0_3.f).setProgress(p4);
        int v0_6 = this.y;
        if (v0_6 == 0) {
            v0_6 = 0;
        }
        ((android.widget.TextView) v0_6.g).setText(this.getString(2131953894, new Object[] {Integer.valueOf(p4)})));
        android.widget.TextView v4_4 = this.y;
        if (v4_4 != null) {
            v1 = v4_4;
        }
        ((android.widget.TextView) v1.g).setVisibility(0);
        return;
    }

    public final void t1(int p6)
    {
        if (this.C) {
            this.C = 0;
            ng.S v0_2 = this.B;
            ((com.google.firebase.perf.metrics.Trace) v0_2.getValue()).putMetric(wmoi.igob.ggcn(7756), ((long) p6));
            ((com.google.firebase.perf.metrics.Trace) v0_2.getValue()).stop();
            return;
        } else {
            return;
        }
    }
}
