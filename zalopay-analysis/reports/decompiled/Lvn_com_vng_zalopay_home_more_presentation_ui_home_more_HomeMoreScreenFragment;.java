package vn.com.vng.zalopay.home_more.presentation.ui.home_more;
public final class HomeMoreScreenFragment extends Sx.a {
    public dagger.e t;
    public Ux.S u;
    public dagger.e v;
    public final ng.w w;
    public final boolean x;
    public final java.util.UUID y;
    public final Ux.G z;

    public HomeMoreScreenFragment()
    {
        Ux.G v0_4 = new TT.a(9, this);
        ng.w v1_0 = ng.x.a(ng.y.c, new Q0.L(8, new Q0.L(7, this)));
        this.w = androidx.fragment.app.FragmentViewModelLazyKt.createViewModelLazy(this, kotlin.jvm.internal.M.a.b(Ux.X), new CS.x(v1_0, 20), new CS.x(v1_0, 21), v0_4);
        this.x = 1;
        this.y = java.util.UUID.randomUUID();
        this.z = new Ux.G();
        return;
    }

    public final boolean m1()
    {
        return this.x;
    }

    public final boolean n1()
    {
        Ux.c v0_0 = this.t;
        Ux.t v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        Ux.c v0_2 = ((Ux.E) ((Sx.f) ((Ux.c) v0_0.get()).h0()).a());
        if ((v0_2 == null) || (v0_2.i != 1)) {
            return 0;
        } else {
            Rx.a.b("01.1020.002");
            Wx.d v2_2 = v0_2.d;
            if (v2_2 != null) {
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_2.c, v2_2)) {
                    Ux.c v0_6 = this.t;
                    if (v0_6 != null) {
                        v1_0 = v0_6;
                    }
                    ((Ux.c) v1_0.get()).k0(new Ux.t(1));
                } else {
                    Ux.c v0_9 = this.t;
                    if (v0_9 != null) {
                        v1_0 = v0_9;
                    }
                    ((Ux.c) v1_0.get()).k0(Ux.l.a);
                    return 1;
                }
            }
            return 1;
        }
    }

    public final void o1(int p5, q0.s p6)
    {
        Ux.d v0_10;
        q0.Y0 v6_1 = p6.x(-800467472);
        if (!v6_1.I(this)) {
            v0_10 = 2;
        } else {
            v0_10 = 4;
        }
        if ((((v0_10 | p5) & 3) == 2) && (v6_1.c())) {
            v6_1.m();
        } else {
            Ux.d v0_3 = this.t;
            if (v0_3 == null) {
                v0_3 = 0;
            }
            Ux.d v0_6 = ((Ux.c) v0_3.get()).e;
            int v2_0 = v6_1.I(this);
            kotlin.jvm.functions.Function2 v3_0 = v6_1.G();
            if ((v2_0 != 0) || (v3_0 == q0.r.a)) {
                v3_0 = new Ux.e(this, 0, 0);
                v6_1.B(v3_0);
            }
            q0.Z.g(v0_6, ((kotlin.jvm.functions.Function2) v3_0), v6_1);
        }
        q0.Y0 v6_2 = v6_1.Y();
        if (v6_2 != null) {
            v6_2.d = new Ux.d(this, p5);
        }
        return;
    }

    public final void onCreate(android.os.Bundle p4)
    {
        try {
            String v0_0 = com.google.android.gms.internal.mlkit_vision_barcode.q6.a;
        } catch (Throwable) {
            String v0_2 = this.getActivity();
            if (v0_2 == null) {
                super.onCreate(p4);
                return;
            } else {
                v0_2.finish();
                super.onCreate(p4);
                return;
            }
        }
        if (v0_0 == null) {
            String v0_3 = com.google.android.gms.internal.mlkit_vision_barcode.q6.b;
            if (v0_3 == null) {
                throw new IllegalStateException("envComponent must be initialized first by calling HomeMorePlugin.install()");
            } else {
                v0_0 = new O5.b(new androidx.webkit.internal.o(19, v0_3));
                com.google.android.gms.internal.mlkit_vision_barcode.q6.a = v0_0;
            }
        } else {
        }
        v0_0.y(this);
        super.onCreate(p4);
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p13, android.view.ViewGroup p14, android.os.Bundle p15)
    {
        android.content.Context v5 = p13.getContext();
        androidx.compose.ui.platform.ComposeView v13_2 = this.t;
        if (v13_2 == null) {
            v13_2 = 0;
        }
        Sx.b v2_1 = ((Sx.b) v13_2.get());
        int v1_0 = this.getLifecycle();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v1_0), 0, 0, new PU.i(v1_0, v2_1, 0, this, v5, 18), 3, 0);
        androidx.compose.ui.platform.ComposeView v13_4 = new androidx.compose.ui.platform.ComposeView(v5, 0, 6);
        v13_4.setContent(new y0.b(new Ux.d(this, 0, 0), 1, 1550284210));
        return v13_4;
    }
}
