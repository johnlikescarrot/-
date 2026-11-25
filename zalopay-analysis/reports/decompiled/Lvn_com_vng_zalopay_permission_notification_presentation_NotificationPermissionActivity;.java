package vn.com.vng.zalopay.permission.notification.presentation;
public final class NotificationPermissionActivity extends androidx.activity.ComponentActivity {
    public static final synthetic int k;
    public final wI.i i;
    public final androidx.activity.result.d j;

    public NotificationPermissionActivity()
    {
        this.i = new wI.i();
        this.j = this.registerForActivityResult(new androidx.fragment.app.f0(5), new BV.k1(12, this));
        return;
    }

    public final void b1()
    {
        if (((xI.f) this.i.d.b.getValue()).a != xI.g.a) {
            zI.a.a(wmoi.igob.ggcn(12080));
        } else {
            zI.a.a(wmoi.igob.ggcn(12079));
        }
        this.finish();
        return;
    }

    public final void onCreate(android.os.Bundle p6)
    {
        boolean v1_0;
        super.onCreate(p6);
        xI.g v0_6 = wmoi.igob.ggcn(12081);
        if (androidx.core.content.c.a(this, v0_6) != 0) {
            v1_0 = 0;
        } else {
            v1_0 = 1;
        }
        wI.i v3 = this.i;
        if (v1_0) {
            v3.g0(xI.b.a);
        }
        xI.g v0_2;
        if (!v1_0) {
            if (android.os.Build$VERSION.SDK_INT >= 33) {
                boolean v1_3 = q9.x6.b(this, new String[] {v0_6}));
                xI.g v0_1 = androidx.core.app.d.f(this, v0_6);
                if ((v1_3) || (v0_1 != null)) {
                    v0_2 = xI.g.b;
                    v3.g0(new xI.e(v0_2));
                    n9.s4.a(this.getOnBackPressedDispatcher(), this, new wI.a(0, this));
                    k.e.a(this, new y0.b(new qH.c(8, this), 1, -1762310682));
                    return;
                }
            }
            v0_2 = xI.g.c;
        } else {
            v0_2 = xI.g.a;
        }
        v3.g0(new xI.e(v0_2));
        n9.s4.a(this.getOnBackPressedDispatcher(), this, new wI.a(0, this));
        k.e.a(this, new y0.b(new qH.c(8, this), 1, -1762310682));
        return;
    }

    public final void onResume()
    {
        super.onResume();
        wI.i v0 = this.i;
        if (((xI.f) v0.d.b.getValue()).c) {
            xI.e v1_2;
            if (androidx.core.content.c.a(this, wmoi.igob.ggcn(12082)) != 0) {
                v1_2 = 0;
            } else {
                v1_2 = 1;
            }
            xI.g v2_0 = xI.b.a;
            if (v1_2 != null) {
                v0.g0(v2_0);
            }
            if (v1_2 == null) {
                v0.g0(new xI.e(xI.g.c));
            } else {
                v0.g0(v2_0);
                v0.g0(new xI.e(xI.g.a));
                return;
            }
        }
        return;
    }
}
