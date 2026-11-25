package vn.com.vng.zalopay.reformkyc.presentation.ui;
public final class ReformKycActivity extends vn.com.vng.zalopay.core.ui.activity.BaseActivity {

    public ReformKycActivity()
    {
        return;
    }

    public static final void j1(vn.com.vng.zalopay.reformkyc.presentation.ui.ReformKycActivity p2)
    {
        androidx.fragment.app.n0 v2_1 = q9.u6.a(p2);
        if (v2_1 != null) {
            androidx.fragment.app.n0 v2_2 = v2_1.getSupportFragmentManager();
            if (v2_2 != null) {
                IK.b.J.getClass();
                new IK.b().v1(v2_2, "javaClass");
            }
        }
        return;
    }

    public final int e1()
    {
        return 2131558452;
    }

    public final OD.a g1()
    {
        return new OD.a("", -1);
    }

    public final void k1(int p2)
    {
        vn.zalopay.ui.navigationbar.NavigationBar v0_2 = ((vn.zalopay.ui.navigationbar.NavigationBar) this.findViewById(2131363687));
        if (v0_2 != null) {
            v0_2.setTitle(this.getString(p2));
        }
        return;
    }

    public final void onActivityResult(int p1, int p2, android.content.Intent p3)
    {
        super.onActivityResult(p1, p2, p3);
        if ((p1 == 1018) && (p2 == -1)) {
            this.finish();
        }
        return;
    }

    public final void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        androidx.fragment.app.a v0_8 = ((vn.zalopay.ui.navigationbar.NavigationBar) this.findViewById(2131363687));
        if (v0_8 != null) {
            v0_8.setTitle(this.getString(2131953883));
            v0_8.setListener(new IK.n(this, 0));
        }
        if (p5 == null) {
            androidx.fragment.app.a v0_2 = this.getIntent().getExtras();
            IK.m.J.getClass();
            kotlin.Unit v5_3 = new IK.m();
            v5_3.setArguments(v0_2);
            if (this.getSupportFragmentManager().E(v5_3.getTag()) == null) {
                androidx.fragment.app.a v0_7 = androidx.activity.result.e.d(this.getSupportFragmentManager(), this.getSupportFragmentManager());
                v0_7.i(2131362806, v5_3, IK.m.getSimpleName(), 1);
                v0_7.f();
            }
        }
        return;
    }
}
