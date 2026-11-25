package vn.com.vng.zalopay.data.notification;
public final synthetic class f implements java.util.concurrent.Callable {
    public final synthetic int a;
    public final synthetic vn.com.vng.zalopay.data.notification.j b;
    public final synthetic hH.a c;

    public synthetic f(vn.com.vng.zalopay.data.notification.j p1, hH.a p2, int p3)
    {
        this.a = p3;
        this.b = p1;
        this.c = p2;
        return;
    }

    public final Object call()
    {
        switch (this.a) {
            case 0:
                this.b.a.h(this.c);
                return Boolean.TRUE;
            default:
                Long v0_6 = this.b.a;
                vn.com.vng.zalopay.data.notification.k$a v1_0 = this.c;
                boolean v2_1 = v0_6.F(v1_0.b, v1_0.c);
                Long v0_1 = v0_6.p(v1_0);
                if (v2_1) {
                    v0_1 = -1;
                }
                return Long.valueOf(v0_1);
        }
    }
}
