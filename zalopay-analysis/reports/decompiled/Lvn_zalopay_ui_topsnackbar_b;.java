package vn.zalopay.ui.topsnackbar;
public final synthetic class b implements android.os.Handler$Callback {
    public final synthetic vn.zalopay.ui.topsnackbar.e a;

    public synthetic b(vn.zalopay.ui.topsnackbar.e p1)
    {
        this.a = p1;
        return;
    }

    public final boolean handleMessage(android.os.Message p4)
    {
        int v0_0 = this.a;
        if (p4.what != 0) {
            return 0;
        } else {
            int v4_5 = ((vn.zalopay.ui.topsnackbar.d) p4.obj);
            if (v0_0.c == v4_5) {
                vn.zalopay.ui.topsnackbar.e.a(v4_5, 2);
            } else {
                if (v0_0.d == v4_5) {
                }
            }
            return 1;
        }
    }
}
