package vn.zalopay.ui.topsnackbar;
public final synthetic class h implements vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout$a, vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout$b {
    public final synthetic vn.zalopay.ui.topsnackbar.p a;

    public synthetic h(vn.zalopay.ui.topsnackbar.p p1)
    {
        this.a = p1;
        return;
    }

    public void a()
    {
        vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout v0_0 = this.a;
        v0_0.a();
        v0_0.b.setOnLayoutChangeListener(0);
        return;
    }

    public void b()
    {
        Throwable v0_0 = this.a;
        int v1_3 = vn.zalopay.ui.topsnackbar.e.c();
        vn.zalopay.ui.topsnackbar.i v2_2 = v0_0.c;
        int v5 = 1;
        if (!v1_3.d(v2_2)) {
            int v1_2;
            int v1_5 = v1_3.d;
            if ((v1_5 == 0) || (v1_5.a.get() != v2_2)) {
                v1_2 = 0;
            } else {
                v1_2 = 1;
            }
            if (v1_2 == 0) {
                v5 = 0;
            } else {
            }
        }
        if (v5 != 0) {
            vn.zalopay.ui.topsnackbar.p.e.post(new vn.zalopay.ui.topsnackbar.i(v0_0));
        }
        return;
    }
}
