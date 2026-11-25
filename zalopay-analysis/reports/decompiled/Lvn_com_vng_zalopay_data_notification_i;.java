package vn.com.vng.zalopay.data.notification;
public final synthetic class i implements java.util.concurrent.Callable {
    public final synthetic vn.com.vng.zalopay.data.notification.j a;
    public final synthetic long b;
    public final synthetic long c;
    public final synthetic long d;

    public synthetic i(vn.com.vng.zalopay.data.notification.j p1, long p2, long p4, long p6)
    {
        this.a = p1;
        this.b = p2;
        this.c = p4;
        this.d = p6;
        return;
    }

    public final Object call()
    {
        this.a.a.B(this.b, this.c, this.d);
        return Boolean.TRUE;
    }
}
