package vn.com.vng.zalopay.data.notification;
public final synthetic class g implements java.util.concurrent.Callable {
    public final synthetic int a;
    public final synthetic vn.com.vng.zalopay.data.notification.k$a b;

    public synthetic g(vn.com.vng.zalopay.data.notification.k$a p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final Object call()
    {
        switch (this.a) {
            case 0:
                return Integer.valueOf(this.b.c());
            case 1:
                return Integer.valueOf(this.b.j());
            default:
                return this.b.e();
        }
    }
}
