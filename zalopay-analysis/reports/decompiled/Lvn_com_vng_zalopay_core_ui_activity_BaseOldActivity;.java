package vn.com.vng.zalopay.core.ui.activity;
public abstract class BaseOldActivity extends androidx.appcompat.app.AppCompatActivity {
    public final int i;
    public final OD.b j;
    public butterknife.Unbinder k;
    public boolean l;
    public com.google.firebase.perf.metrics.Trace m;

    public BaseOldActivity()
    {
        this.i = 2131558451;
        this.j = new OD.b();
        return;
    }

    public androidx.fragment.app.Fragment e1()
    {
        return this.getSupportFragmentManager().D(2131362806);
    }

    public abstract androidx.fragment.app.Fragment g1();

    public int h1()
    {
        return this.i;
    }

    public abstract OD.a i1();

    public void j1(androidx.fragment.app.Fragment p4)
    {
        if (this.getSupportFragmentManager().E(p4.getTag()) == null) {
            androidx.fragment.app.a v0_1 = androidx.activity.result.e.d(this.getSupportFragmentManager(), this.getSupportFragmentManager());
            v0_1.m(2131362806, p4, p4.getClass().getSimpleName());
            v0_1.f();
            return;
        } else {
            return;
        }
    }

    public final void k()
    {
        m9.B.d(this);
        return;
    }

    public final void onBackPressed()
    {
        OD.b v0_0 = this.e1();
        if ((this.l) && ((!(v0_0 instanceof Ts.d)) || (!((Ts.d) v0_0).onBackPressed()))) {
            OD.b v0_2 = this.i1().c;
            int v1_0 = Integer.valueOf(v0_2);
            if (v0_2 <= null) {
                v1_0 = 0;
            }
            if (v1_0 != 0) {
                UR.a.c(v1_0.intValue(), 0, 0, 0);
            }
            this.j.getClass();
            super.onBackPressed();
        }
        return;
    }

    public void onCreate(android.os.Bundle p3)
    {
        int v0_0 = Pb.b.a();
        String v1_1 = this.getClass().getSimpleName();
        v0_0.getClass();
        int v0_5 = Pb.b.b(v1_1);
        this.m = v0_5;
        v0_5.start();
        super.onCreate(p3);
        this.setContentView(this.h1());
        int v0_2 = this.g1();
        if (v0_2 != 0) {
            if (p3 != null) {
                v0_2 = 0;
            }
            if (v0_2 != 0) {
                this.j1(v0_2);
            }
        }
        this.i1().getClass();
        OD.b v3_3 = this.i1().b;
        if (!android.text.TextUtils.isEmpty(v3_3)) {
            UR.a.i(v3_3);
        }
        this.getWindow().setStatusBarColor(0);
        this.j.getClass();
        return;
    }

    public void onDestroy()
    {
        super.onDestroy();
        int v0 = this.k;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.a();
        return;
    }

    public void onNewIntent(android.content.Intent p1)
    {
        super.onNewIntent(p1);
        this.e1();
        return;
    }

    public final boolean onOptionsItemSelected(android.view.MenuItem p2)
    {
        if (p2.getItemId() != 16908332) {
            return 0;
        } else {
            this.onBackPressed();
            return 1;
        }
    }

    public void onPause()
    {
        super.onPause();
        this.l = 0;
        return;
    }

    public void onResume()
    {
        int v0_0 = this.m;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        v0_0.stop();
        super.onResume();
        this.l = 1;
        return;
    }

    public final void setContentView(int p1)
    {
        super.setContentView(p1);
        this.k = butterknife.ButterKnife.a(this.getWindow().getDecorView(), this);
        return;
    }
}
