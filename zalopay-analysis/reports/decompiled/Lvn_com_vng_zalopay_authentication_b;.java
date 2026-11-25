package vn.com.vng.zalopay.authentication;
public final class b extends b4.a {
    public final synthetic int c;
    public final synthetic vn.com.vng.zalopay.authentication.AuthenticationDialog d;

    public synthetic b(vn.com.vng.zalopay.authentication.AuthenticationDialog p1, int p2)
    {
        this.c = p2;
        this.d = p1;
        return;
    }

    public final void a(android.view.View p1)
    {
        switch (this.c) {
            case 0:
                this.d.onClickCancel();
                return;
            default:
                this.d.onOnClickButtonSecond();
                return;
        }
    }
}
