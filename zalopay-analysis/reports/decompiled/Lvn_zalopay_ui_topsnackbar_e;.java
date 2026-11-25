package vn.zalopay.ui.topsnackbar;
public final class e {
    public static vn.zalopay.ui.topsnackbar.e e;
    public final Object a;
    public final android.os.Handler b;
    public vn.zalopay.ui.topsnackbar.d c;
    public vn.zalopay.ui.topsnackbar.d d;

    public e()
    {
        this.a = new Object();
        this.b = new android.os.Handler(android.os.Looper.getMainLooper(), new vn.zalopay.ui.topsnackbar.b(this));
        return;
    }

    public static boolean a(vn.zalopay.ui.topsnackbar.d p0, int p1)
    {
        int v0_3 = ((vn.zalopay.ui.topsnackbar.c) p0.a.get());
        if (v0_3 == 0) {
            return 0;
        } else {
            v0_3.a(p1);
            return 1;
        }
    }

    public static vn.zalopay.ui.topsnackbar.e c()
    {
        if (vn.zalopay.ui.topsnackbar.e.e == null) {
            vn.zalopay.ui.topsnackbar.e.e = new vn.zalopay.ui.topsnackbar.e();
        }
        return vn.zalopay.ui.topsnackbar.e.e;
    }

    public final void b(vn.zalopay.ui.topsnackbar.j p3)
    {
        if (this.d(p3)) {
            this.b.removeCallbacksAndMessages(this.c);
        }
        return;
    }

    public final boolean d(vn.zalopay.ui.topsnackbar.c p2)
    {
        Object v0_0 = this.c;
        if ((v0_0 == null) || ((p2 == 0) || (v0_0.a.get() != p2))) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void e(vn.zalopay.ui.topsnackbar.d p5)
    {
        int v0 = p5.b;
        if (v0 != -2) {
            if (v0 <= 0) {
                if (v0 != -1) {
                    v0 = 10000;
                } else {
                    v0 = 1500;
                }
            }
            android.os.Handler v1_2 = this.b;
            v1_2.removeCallbacksAndMessages(p5);
            v1_2.sendMessageDelayed(android.os.Message.obtain(v1_2, 0, p5), ((long) v0));
            return;
        } else {
            return;
        }
    }

    public final void f()
    {
        vn.zalopay.ui.topsnackbar.c v0_0 = this.d;
        if (v0_0 != null) {
            this.c = v0_0;
            this.d = 0;
            vn.zalopay.ui.topsnackbar.c v0_3 = ((vn.zalopay.ui.topsnackbar.c) v0_0.a.get());
            if (v0_3 == null) {
                this.c = 0;
            } else {
                v0_3.show();
                return;
            }
        }
        return;
    }
}
