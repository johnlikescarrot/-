package vn.com.vng.zalopay.user;
public abstract class UserBaseActivity extends vn.com.vng.zalopay.ui.activity.SkinActivity {
    public final org.greenrobot.eventbus.e n;
    public final um.d o;
    public boolean p;
    public boolean q;

    public UserBaseActivity()
    {
        this.n = ((CC.f) q9.a5.a()).i();
        ((CC.f) q9.a5.a()).R();
        this.o = ((CC.f) q9.a5.a()).m();
        return;
    }

    public final void j1(androidx.fragment.app.Fragment p2)
    {
        if (this.p) {
            super.j1(p2);
            return;
        } else {
            return;
        }
    }

    public final void l1(CC.b p3)
    {
        int v1 = 1;
        if (this.m1() == null) {
            Object[] v3_8 = p3.D();
            if (v3_8.n()) {
                v3_8.r();
                String v0_1 = vn.com.vng.zalopay.AndroidApplication.g;
                if (v0_1 == null) {
                    v0_1 = 0;
                }
                v0_1.f(v3_8.p());
            }
            if (this.m1() == null) {
                v1 = 0;
            }
            if (v1 == 0) {
                ck.b.a("Create UserComponent is NPE in %s. @Injected dependencies can be null - Application restarted [%s]", new Object[] {this.getClass().getSimpleName(), Boolean.valueOf(this.q)}));
            }
        }
        this.p = v1;
        if (v1 == 0) {
            this.o.b(this, at.W.d);
            this.finish();
            return;
        } else {
            this.n1(this.m1());
            return;
        }
    }

    public CC.k m1()
    {
        CC.k v0_0 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        return v0_0.b;
    }

    public void n1(CC.k p1)
    {
        return;
    }

    public void onCreate(android.os.Bundle p2)
    {
        int v0;
        if (p2 == null) {
            v0 = 0;
        } else {
            v0 = 1;
        }
        this.q = v0;
        super.onCreate(p2);
        return;
    }

    public void onPause()
    {
        super.onPause();
        org.greenrobot.eventbus.e v0 = this.n;
        if (v0.e(this)) {
            v0.p(this);
        }
        return;
    }

    public void onResume()
    {
        super.onResume();
        org.greenrobot.eventbus.e v0 = this.n;
        if (!v0.e(this)) {
            v0.k(this);
        }
        return;
    }

    public void onSignOut(eu.e p3)
    {
        Ow.b v3_5;
        String v0 = p3.a;
        if (p3.b != 1) {
            v3_5 = 0;
        } else {
            v3_5 = 2;
        }
        this.n.l();
        ((CC.f) q9.a5.a()).J().b(v3_5, v0);
        ((CC.f) q9.a5.a()).J().a();
        return;
    }
}
