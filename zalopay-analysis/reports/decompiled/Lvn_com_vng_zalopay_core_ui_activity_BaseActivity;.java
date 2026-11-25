package vn.com.vng.zalopay.core.ui.activity;
public abstract class BaseActivity extends androidx.appcompat.app.AppCompatActivity {
    public final OD.b i;
    public com.google.firebase.perf.metrics.Trace j;

    public BaseActivity()
    {
        this.i = new OD.b();
        return;
    }

    public abstract int e1();

    public abstract OD.a g1();

    public void h1()
    {
        return;
    }

    public boolean i1()
    {
        return (this instanceof vn.com.vng.zalopay.camera.CustomDefaultCamera);
    }

    public void onBackPressed()
    {
        OD.b v0_1 = this.g1().c;
        if (v0_1 > null) {
            UR.a.c(v0_1, 0, 0, 0);
        }
        this.i.getClass();
        super.onBackPressed();
        return;
    }

    public void onCreate(android.os.Bundle p3)
    {
        int v0_0 = Pb.b.a();
        String v1_1 = this.getClass().getSimpleName();
        v0_0.getClass();
        int v0_3 = Pb.b.b(v1_1);
        this.j = v0_3;
        v0_3.start();
        super.onCreate(p3);
        if (!this.i1()) {
            this.setContentView(this.e1());
        } else {
            this.h1();
        }
        this.g1().getClass();
        OD.b v3_5 = this.g1().b;
        if (v3_5.length() != 0) {
            UR.a.i(v3_5);
        }
        this.getWindow().setStatusBarColor(0);
        this.i.getClass();
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

    public void onResume()
    {
        int v0 = this.j;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.stop();
        super.onResume();
        return;
    }
}
