package vn.com.vng.zalopay.authentication;
public final class j extends Bw.b {
    public final synthetic vn.com.vng.zalopay.authentication.k b;

    public j(vn.com.vng.zalopay.authentication.k p1)
    {
        this.b = p1;
        return;
    }

    public final void onError(Throwable p2)
    {
        vn.com.vng.zalopay.authentication.f$a v0_1 = this.b.c;
        if (v0_1 != null) {
            v0_1.P(p2);
        }
        return;
    }

    public final void onNext(Object p2)
    {
        vn.com.vng.zalopay.authentication.f$a v0_1 = this.b.c;
        if (v0_1 != null) {
            v0_1.j(((String) p2));
        }
        return;
    }
}
