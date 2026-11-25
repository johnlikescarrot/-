package vn.com.vng.zalopay.data.notification;
public final class e implements vn.com.vng.zalopay.data.notification.d$a {
    public final io.reactivex.subjects.f a;

    public e()
    {
        this.a = new io.reactivex.subjects.d().f();
        return;
    }

    public final gf.A a()
    {
        return this.a.ofType(hH.a);
    }

    public final void b(hH.a p2)
    {
        this.a.onNext(p2);
        return;
    }

    public final void finalize()
    {
        this.a.onComplete();
        return;
    }
}
