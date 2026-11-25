package vn.zalopay.ui.topsnackbar;
public final class p {
    public static final android.os.Handler e;
    public final android.view.ViewGroup a;
    public final vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout b;
    public final vn.zalopay.ui.topsnackbar.j c;
    public int d;

    static p()
    {
        vn.zalopay.ui.topsnackbar.p.e = new android.os.Handler(android.os.Looper.getMainLooper(), new vn.zalopay.ui.topsnackbar.g());
        return;
    }

    public p(android.view.ViewGroup p4)
    {
        this.c = new vn.zalopay.ui.topsnackbar.j(this);
        this.a = p4;
        this.b = ((vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout) android.view.LayoutInflater.from(((android.content.Context) new ref.WeakReference(p4.getContext()).get())).inflate(2131560366, p4, 0));
        return;
    }

    public final void a()
    {
        androidx.core.view.I0 v0_0 = this.b;
        v0_0.setTranslationY(((float) (- v0_0.getHeight())));
        androidx.core.view.I0 v0_1 = androidx.core.view.x0.a(v0_0);
        v0_1.f(0);
        vn.zalopay.ui.topsnackbar.l v1_1 = new P2.a(1);
        android.view.ViewPropertyAnimator v2_3 = ((android.view.View) v0_1.a.get());
        if (v2_3 != null) {
            v2_3.animate().setInterpolator(v1_1);
        }
        v0_1.c(250);
        v0_1.d(new vn.zalopay.ui.topsnackbar.l(this));
        v0_1.e();
        return;
    }

    public final void b(int p5)
    {
        vn.zalopay.ui.topsnackbar.d v0_0 = vn.zalopay.ui.topsnackbar.e.c();
        int v1_1 = this.c;
        if (!v0_0.d(v1_1)) {
            int v1_0;
            Object v3_2 = v0_0.d;
            if ((v3_2 == null) || (v3_2.a.get() != v1_1)) {
                v1_0 = 0;
            } else {
                v1_0 = 1;
            }
            if (v1_0 != 0) {
                vn.zalopay.ui.topsnackbar.e.a(v0_0.d, p5);
            }
        } else {
            vn.zalopay.ui.topsnackbar.e.a(v0_0.c, p5);
        }
        return;
    }

    public final void c()
    {
        android.view.ViewGroup v0_0 = vn.zalopay.ui.topsnackbar.e.c();
        if (v0_0.d(this.c)) {
            v0_0.c = 0;
            if (v0_0.d != null) {
                v0_0.f();
            }
        }
        android.view.ViewGroup v0_3 = this.b.getParent();
        if ((v0_3 instanceof android.view.ViewGroup)) {
            ((android.view.ViewGroup) v0_3).removeView(this.b);
        }
        return;
    }
}
