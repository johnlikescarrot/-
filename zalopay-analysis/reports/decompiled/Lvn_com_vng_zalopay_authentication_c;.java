package vn.com.vng.zalopay.authentication;
public final class c implements WZ.c {
    public final synthetic vn.com.vng.zalopay.authentication.d a;

    public c(vn.com.vng.zalopay.authentication.d p1)
    {
        this.a = p1;
        return;
    }

    public final void a(String p4, boolean p5)
    {
        vn.com.vng.zalopay.authentication.d v0 = this.a;
        new vn.com.vng.zalopay.authentication.k(v0.a, v0).a(p4);
        try {
            com.android.billingclient.api.c0 v4_3 = v0.f;
        } catch (com.android.billingclient.api.c0 v4_1) {
            v4_1.getMessage();
            return;
        }
        if (v4_3 == null) {
            return;
        } else {
            if (((TZ.b) v4_3.b) != null) {
                v4_3.u().runOnUiThread(new XZ.a(v4_3, 2));
            } else {
            }
            v0.f.s(1);
            return;
        }
    }

    public final void h()
    {
        Exception v0_0 = this.a;
        v0_0.d.e();
        androidx.fragment.app.FragmentActivity v1_2 = v0_0.g;
        if ((v1_2.get() instanceof androidx.fragment.app.FragmentActivity)) {
            androidx.fragment.app.FragmentActivity v1_1 = ((androidx.fragment.app.FragmentActivity) v1_2.get());
            if (!v1_1.isFinishing()) {
                m9.p.f(v1_1);
                try {
                    v0_0.f.m();
                    return;
                } catch (Exception v0_2) {
                    v0_2.getMessage();
                    return;
                }
            }
        }
        return;
    }

    public final void j(boolean p2)
    {
        UR.a.d("01.1030.009");
        Pt.n v0_1 = this.a;
        v0_1.c = p2;
        Pt.n v0_3 = v0_1.e.a;
        if (v0_3 != null) {
            v0_3.m(p2);
            return;
        } else {
            return;
        }
    }

    public final void m()
    {
        m9.o v0_1 = this.a.d;
        if (v0_1 != null) {
            v0_1.d();
        }
        return;
    }
}
