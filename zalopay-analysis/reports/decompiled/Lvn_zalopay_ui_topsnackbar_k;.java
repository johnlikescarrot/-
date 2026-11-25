package vn.zalopay.ui.topsnackbar;
public final class k implements com.google.android.material.behavior.SwipeDismissBehavior$a {
    public final synthetic vn.zalopay.ui.topsnackbar.p a;

    public k(vn.zalopay.ui.topsnackbar.p p1)
    {
        this.a = p1;
        return;
    }

    public final void a(android.view.View p2)
    {
        this.a.b(0);
        return;
    }

    public final void b(int p3)
    {
        vn.zalopay.ui.topsnackbar.d v0_0 = this.a;
        if (p3 == null) {
            Throwable v3_3 = vn.zalopay.ui.topsnackbar.e.c();
            if (v3_3.d(v0_0.c)) {
                v3_3.e(v3_3.c);
            }
            return;
        } else {
            if ((p3 == 1) || (p3 == 2)) {
                vn.zalopay.ui.topsnackbar.e.c().b(v0_0.c);
                return;
            } else {
                return;
            }
        }
    }
}
