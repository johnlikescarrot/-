package vn.com.vng.zalopay.authentication;
public final class h implements vn.com.vng.zalopay.authentication.f {
    public final N4.b a;
    public final vn.com.vng.zalopay.authentication.secret.a b;
    public final android.content.Context c;
    public androidx.appcompat.widget.v1 d;
    public boolean e;
    public final vn.com.vng.zalopay.authentication.f$a f;
    public n9.i3 g;

    public h(android.content.Context p1, vn.com.vng.zalopay.authentication.secret.a p2, vn.com.vng.zalopay.authentication.f$a p3)
    {
        this.c = p1;
        this.f = p3;
        this.b = p2;
        this.a = new N4.b(p1, 6);
        return;
    }

    public final void a(hh.a p5)
    {
        String v0_0 = this.a;
        if (v0_0.g()) {
            androidx.appcompat.widget.v1 v1_2 = new androidx.appcompat.widget.v1();
            this.d = v1_2;
            this.e = 0;
            if (this.g == null) {
                n9.i3 v2_1 = new n9.i3();
                v2_1.a = new ref.WeakReference(this);
                v2_1.b = this.c;
                this.g = v2_1;
            }
            yn.b v3_3 = yn.a.a;
            if (v3_3 == null) {
                throw new IllegalStateException("FingerprintManagerCompat not initialized");
            } else {
                v3_3.f(v0_0.a, p5, v1_2, this.g);
                return;
            }
        } else {
            return;
        }
    }
}
