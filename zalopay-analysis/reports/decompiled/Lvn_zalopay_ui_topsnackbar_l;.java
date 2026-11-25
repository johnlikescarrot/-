package vn.zalopay.ui.topsnackbar;
public final class l extends p9.T {
    public final synthetic int a;
    public final synthetic vn.zalopay.ui.topsnackbar.p b;

    public l(vn.zalopay.ui.topsnackbar.p p2)
    {
        this.a = 0;
        this.b = p2;
        return;
    }

    public l(vn.zalopay.ui.topsnackbar.p p1, int p2)
    {
        this.a = 1;
        this.b = p1;
        return;
    }

    public final void onAnimationEnd()
    {
        switch (this.a) {
            case 0:
                Throwable v0_3 = vn.zalopay.ui.topsnackbar.e.c();
                if (v0_3.d(this.b.c)) {
                    v0_3.e(v0_3.c);
                }
                return;
            default:
                this.b.c();
                return;
        }
    }

    public final void onAnimationStart()
    {
        android.widget.TextView v4_4 = this.b;
        switch (this.a) {
            case 0:
                androidx.core.view.I0 v0_6 = v4_4.b;
                if (v0_6.a.getVisibility() == 0) {
                    v0_6.a.setAlpha(0);
                    androidx.core.view.I0 v0_2 = androidx.core.view.x0.a(v0_6.a);
                    v0_2.a(1065353216);
                    v0_2.c(((long) 180));
                    android.view.ViewPropertyAnimator v3_2 = ((android.view.View) v0_2.a.get());
                    if (v3_2 != null) {
                        v3_2.animate().setStartDelay(((long) 70));
                    }
                    v0_2.e();
                }
                return;
            default:
                androidx.core.view.I0 v0_3 = v4_4.b;
                if (v0_3.a.getVisibility() == 0) {
                    v0_3.a.setAlpha(1065353216);
                    androidx.core.view.I0 v0_5 = androidx.core.view.x0.a(v0_3.a);
                    v0_5.a(0);
                    v0_5.c(((long) 180));
                    android.view.ViewPropertyAnimator v3_7 = ((android.view.View) v0_5.a.get());
                    if (v3_7 != null) {
                        v3_7.animate().setStartDelay(((long) 0));
                    }
                    v0_5.e();
                }
                return;
        }
    }
}
