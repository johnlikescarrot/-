package vn.com.vng.zalopay.data.notification;
public final synthetic class h implements java.util.concurrent.Callable {
    public final synthetic int a;
    public final synthetic Object b;
    public final synthetic long c;

    public synthetic h(Object p1, long p2, int p4)
    {
        this.a = p4;
        this.b = p1;
        this.c = p2;
        return;
    }

    public final Object call()
    {
        switch (this.a) {
            case 0:
                return Boolean.valueOf(((vn.com.vng.zalopay.data.notification.j) this.b).a.m(this.c));
            case 1:
                return ((vn.com.vng.zalopay.data.notification.j) this.b).a.k(this.c);
            default:
                return ((xu.h) this.b).a.G(this.c);
        }
    }
}
