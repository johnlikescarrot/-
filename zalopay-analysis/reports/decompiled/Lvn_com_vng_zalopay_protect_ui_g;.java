package vn.com.vng.zalopay.protect.ui;
public final class g extends Gd.a {
    public final android.content.Context c;
    public final vn.com.vng.zalopay.navigation.b d;
    public final Pt.n e;
    public final org.greenrobot.eventbus.e f;
    public final uu.b g;
    public final nY.p h;
    public final hh.a i;
    public final Ow.b j;
    public final Ns.g k;

    public g(org.greenrobot.eventbus.e p4, Pt.n p5, vn.com.vng.zalopay.navigation.b p6, android.content.Context p7, uu.b p8, nY.p p9, hh.a p10, Ow.b p11, Ns.g p12)
    {
        super(5);
        new vn.com.vng.zalopay.authentication.secret.a();
        int v0_2 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v0_2 == 0) {
            v0_2 = 0;
        }
        new N4.b(v0_2, 6);
        super.f = p4;
        super.e = p5;
        super.d = p6;
        super.c = p7;
        super.g = p8;
        super.h = p9;
        super.i = p10;
        super.j = p11;
        super.k = p12;
        return;
    }

    public final android.app.Activity E0()
    {
        android.app.Activity v0_0 = this.b;
        if (v0_0 != null) {
            return ((vn.com.vng.zalopay.protect.ui.a) v0_0).getActivity();
        } else {
            return 0;
        }
    }
}
