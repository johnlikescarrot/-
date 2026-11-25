package vn.com.vng.zalopay.authentication;
public final class e extends Gd.a implements vn.com.vng.zalopay.authentication.f$a {
    public final android.content.Context c;
    public vn.com.vng.zalopay.authentication.secret.a d;
    public vn.com.vng.zalopay.authentication.h e;

    public e(android.content.Context p2)
    {
        super(5);
        super.c = p2;
        return;
    }

    public final void P(Throwable p5)
    {
        vn.com.vng.zalopay.authentication.i v0_0 = this.b;
        if (v0_0 != null) {
            if (!(p5 instanceof vn.com.vng.zalopay.exception.KeyInvalidatedException)) {
                if ((p5 instanceof fx.a)) {
                    int v1_0;
                    int v1_6 = ((fx.a) p5).a;
                    if ((v1_6 != 3) && ((v1_6 != 7) && ((v1_6 != 5) && (v1_6 != 1)))) {
                        v1_0 = 0;
                    } else {
                        v1_0 = 1;
                    }
                    ((vn.com.vng.zalopay.authentication.i) v0_0).C0(p5.getMessage(), (v1_0 ^ 1));
                    return;
                }
            } else {
                ((vn.com.vng.zalopay.authentication.i) v0_0).V0();
                return;
            }
        }
        return;
    }

    public final void j(String p2)
    {
        vn.com.vng.zalopay.authentication.i v0_0 = this.b;
        if (v0_0 != null) {
            ((vn.com.vng.zalopay.authentication.i) v0_0).M0();
            ((vn.com.vng.zalopay.authentication.i) this.b).hideLoading();
            ((vn.com.vng.zalopay.authentication.i) this.b).j(p2);
            return;
        } else {
            return;
        }
    }
}
