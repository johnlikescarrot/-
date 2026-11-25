package vn.com.vng.zalopay.permission.location.presentation;
public final class LocationPermissionActivity extends androidx.activity.ComponentActivity {
    public static final String[] o;
    public final q0.N0 i;
    public boolean j;
    public boolean k;
    public boolean l;
    public final ng.S m;
    public final androidx.activity.result.d n;

    static LocationPermissionActivity()
    {
        vn.com.vng.zalopay.permission.location.presentation.LocationPermissionActivity.o = new String[] {wmoi.igob.ggcn(8051), wmoi.igob.ggcn(8052)});
        return;
    }

    public LocationPermissionActivity()
    {
        this.i = q0.d.G(Boolean.FALSE, q0.D0.e);
        this.m = ng.x.b(new oM.h(20));
        this.n = this.registerForActivityResult(new androidx.fragment.app.f0(4), new BV.k1(10, this));
        return;
    }

    public static void b1(vn.com.vng.zalopay.permission.location.presentation.LocationPermissionActivity p1, String p2)
    {
        p1.l = 1;
        qI.c.a(p1, p2, 0);
        p1.finish();
        return;
    }

    public final boolean c1()
    {
        int v0_3 = ((String[]) this.m.getValue());
        int v3 = 0;
        while (v3 < v0_3.length) {
            if (!androidx.core.app.d.f(this, v0_3[v3])) {
                v3++;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public final void onCreate(android.os.Bundle p4)
    {
        super.onCreate(p4);
        java.util.Map v0_0 = this.i;
        if (p4 == null) {
            String v4_4 = 0;
            while(true) {
                int v1_2 = vn.com.vng.zalopay.permission.location.presentation.LocationPermissionActivity.o;
                if (v4_4 >= 2) {
                    break;
                }
                if (androidx.core.content.c.a(this, v1_2[v4_4]) != 0) {
                    v4_4++;
                } else {
                    vn.com.vng.zalopay.permission.location.presentation.LocationPermissionActivity.b1(this, wmoi.igob.ggcn(8056));
                    return;
                }
            }
            String v4_12 = this.m;
            if ((!q9.x6.b(this, ((String[]) v4_12.getValue()))) && (!this.c1())) {
                v0_0.setValue(Boolean.TRUE);
                uI.a.a(wmoi.igob.ggcn(8057), kotlin.collections.a0.emptyMap());
            } else {
                this.k = this.c1();
                this.n.a(((String[]) v4_12.getValue()), 0);
                q9.x6.a(this, v1_2);
            }
        } else {
            v0_0.setValue(Boolean.valueOf(p4.getBoolean(wmoi.igob.ggcn(8053))));
            this.j = p4.getBoolean(wmoi.igob.ggcn(8054));
            this.k = p4.getBoolean(wmoi.igob.ggcn(8055));
        }
        k.e.a(this, new y0.b(new qH.c(3, this), 1, 195673756));
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        if ((this.isFinishing()) && (!this.l)) {
            qI.c.a(this, "", 0);
        }
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if (this.j) {
            int v0_1 = 0;
            this.j = 0;
            while (v0_1 < 2) {
                if (androidx.core.content.c.a(this, vn.com.vng.zalopay.permission.location.presentation.LocationPermissionActivity.o[v0_1]) != 0) {
                    v0_1++;
                } else {
                    this.l = 1;
                    qI.c.a(this, wmoi.igob.ggcn(8058), 1);
                    this.finish();
                    return;
                }
            }
            vn.com.vng.zalopay.permission.location.presentation.LocationPermissionActivity.b1(this, wmoi.igob.ggcn(8059));
        }
        return;
    }

    public final void onSaveInstanceState(android.os.Bundle p3)
    {
        super.onSaveInstanceState(p3);
        p3.putBoolean(wmoi.igob.ggcn(8060), ((Boolean) this.i.getValue()).booleanValue());
        p3.putBoolean(wmoi.igob.ggcn(8061), this.j);
        p3.putBoolean(wmoi.igob.ggcn(8062), this.k);
        return;
    }
}
