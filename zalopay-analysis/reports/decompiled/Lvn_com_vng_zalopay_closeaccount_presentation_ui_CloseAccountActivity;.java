package vn.com.vng.zalopay.closeaccount.presentation.ui;
public final class CloseAccountActivity extends vn.com.vng.zalopay.core.ui.activity.BaseActivity implements es.f {
    public static final synthetic int p;
    public androidx.lifecycle.r0 k;
    public final O5.b l;
    public vn.com.vng.zalopay.closeaccount.presentation.ui.b m;
    public es.g n;
    public final ng.S o;

    public CloseAccountActivity()
    {
        this.l = new O5.b(kotlin.jvm.internal.M.a.b(fs.b), new es.d(this, 0), new bJ.d(8, this), new es.d(this, 1));
        this.o = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364476);
        return;
    }

    public final int e1()
    {
        return 2131558450;
    }

    public final OD.a g1()
    {
        return new OD.a("[CloseAccount]", -1);
    }

    public final androidx.appcompat.widget.Toolbar j1()
    {
        return ((androidx.appcompat.widget.Toolbar) this.o.getValue());
    }

    public final void k1(boolean p3)
    {
        if (this.m == null) {
            vn.com.vng.zalopay.closeaccount.presentation.ui.b.T.getClass();
            vn.com.vng.zalopay.closeaccount.presentation.ui.b v0_3 = new vn.com.vng.zalopay.closeaccount.presentation.ui.b();
            v0_3.J = "";
            v0_3.K = p3;
            this.m = v0_3;
            v0_3.H = new vn.com.vng.zalopay.closeaccount.presentation.ui.a(this);
            if ((!this.isFinishing()) && (!v0_3.isVisible())) {
                v0_3.v1(this.getSupportFragmentManager(), vn.com.vng.zalopay.closeaccount.presentation.ui.b.getSimpleName());
            }
        }
        return;
    }

    public final void onBackPressed()
    {
        this.finish();
        super.onBackPressed();
        return;
    }

    public final void onCreate(android.os.Bundle p4)
    {
        super.onCreate(p4);
        int v4_4 = q9.a5.b();
        int v4_5 = dagger.internal.f.b(new AC.c(new LQ.a(v4_4, 17), dagger.internal.f.b(new Yt.w(dagger.internal.f.b(new WH.e(10, new LQ.a(v4_4, 18))), 7)), 29));
        android.view.ViewGroup$LayoutParams v1_4 = p9.m7.a(1);
        v1_4.put(fs.b, v4_5);
        this.k = ((androidx.lifecycle.r0) dagger.internal.f.b(new WH.e(1, new dagger.internal.n(v1_4))).get());
        this.setSupportActionBar(this.j1());
        int v4_12 = this.getSupportActionBar();
        if (v4_12 != 0) {
            v4_12.s(1);
        }
        int v4_13 = this.getSupportActionBar();
        if (v4_13 != 0) {
            v4_13.u();
        }
        int v4_26;
        com.google.android.gms.internal.mlkit_vision_barcode.W7.c(this.j1());
        es.g.L.getClass();
        int v4_17 = new es.g(this);
        this.n = v4_17;
        v4_17.v1(this.getSupportFragmentManager(), "CloseAccountDialog");
        com.google.android.gms.internal.mlkit_vision_barcode.W7.b(this.findViewById(2131364102), 1);
        int v4_21 = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (v4_21 <= 0) {
            int v4_23 = this.getResources().getDisplayMetrics();
            if (v4_23 != 0) {
                v4_26 = ((int) (v4_23.density * 1103101952));
            } else {
                v4_26 = 50;
            }
        } else {
            v4_26 = this.getResources().getDimensionPixelSize(v4_21);
        }
        int v0_7 = this.findViewById(2131364265);
        android.view.ViewGroup$LayoutParams v1_12 = v0_7.getLayoutParams();
        v1_12.height = v4_26;
        v0_7.setLayoutParams(v1_12);
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        this.m = 0;
        return;
    }
}
