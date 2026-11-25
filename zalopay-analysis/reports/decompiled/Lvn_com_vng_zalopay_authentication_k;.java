package vn.com.vng.zalopay.authentication;
public final class k implements vn.com.vng.zalopay.authentication.f {
    public final kf.a a;
    public final Ow.a b;
    public final vn.com.vng.zalopay.authentication.f$a c;

    public k(Ow.a p3, vn.com.vng.zalopay.authentication.f$a p4)
    {
        this.a = new kf.a(0);
        this.b = p3;
        this.c = p4;
        return;
    }

    public final void a(String p3)
    {
        this.a.c(((kf.b) q9.H.f(this.b.f(p3).map(new nY.s(17))).subscribeWith(new vn.com.vng.zalopay.authentication.j(this))));
        return;
    }
}
