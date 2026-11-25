package vn.zalopay.ui.androidsvg;
public final class i implements vn.zalopay.ui.androidsvg.e {
    public final java.util.List a;

    public i(java.util.List p1)
    {
        this.a = p1;
        return;
    }

    public final boolean a(vn.zalopay.ui.androidsvg.a0 p3)
    {
        java.util.Iterator v0_1 = this.a.iterator();
        while (v0_1.hasNext()) {
            if (vn.zalopay.ui.androidsvg.p.g(((vn.zalopay.ui.androidsvg.n) v0_1.next()), p3)) {
                return 0;
            }
        }
        return 1;
    }

    public final String toString()
    {
        return androidx.camera.camera2.internal.G.h(new StringBuilder("not("), this.a, ")");
    }
}
