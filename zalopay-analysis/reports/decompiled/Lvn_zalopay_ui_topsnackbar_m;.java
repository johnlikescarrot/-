package vn.zalopay.ui.topsnackbar;
public final class m extends com.google.android.material.behavior.SwipeDismissBehavior {
    public final synthetic vn.zalopay.ui.topsnackbar.p h;

    public m(vn.zalopay.ui.topsnackbar.p p1)
    {
        this.h = p1;
        return;
    }

    public final boolean g(androidx.coordinatorlayout.widget.CoordinatorLayout p4, android.view.View p5, android.view.MotionEvent p6)
    {
        vn.zalopay.ui.topsnackbar.d v0_0 = this.h;
        if (p4.p(((vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout) p5), ((int) p6.getX()), ((int) p6.getY()))) {
            vn.zalopay.ui.topsnackbar.e v1_0 = p6.getActionMasked();
            if (v1_0 == null) {
                vn.zalopay.ui.topsnackbar.e.c().b(v0_0.c);
            } else {
                if ((v1_0 == 1) || (v1_0 == 3)) {
                    vn.zalopay.ui.topsnackbar.e v1_2 = vn.zalopay.ui.topsnackbar.e.c();
                    if (v1_2.d(v0_0.c)) {
                        v1_2.e(v1_2.c);
                    }
                }
            }
        }
        return super.g(p4, ((vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout) p5), p6);
    }

    public final boolean s(android.view.View p1)
    {
        return (p1 instanceof vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout);
    }
}
