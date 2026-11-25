package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class C0 extends wg.c {
    public synthetic Object d;
    public int e;
    public final synthetic bW.d f;

    public C0(bW.d p1, kotlin.coroutines.Continuation p2)
    {
        this.f = p1;
        super(p2);
        return;
    }

    public final Object invokeSuspend(Object p2)
    {
        this.d = p2;
        this.e = (this.e | -2147483648);
        return this.f.emit(0, this);
    }
}
