package vn.com.vng.zalopay.ui.activity;
public class ExternalCallSplashScreenActivity extends vn.com.vng.zalopay.ui.activity.BaseDeeplinkActivity implements ZO.c, DZ.g$a {
    public boolean o;
    public String p;
    public final OD.a q;

    public ExternalCallSplashScreenActivity()
    {
        this.q = new OD.a("", -1);
        return;
    }

    public final void a(int p2)
    {
        if (p2 == 1500) {
            new vn.com.vng.zalopay.authentication.secret.a().c();
        }
        return;
    }

    public final void c(int p5)
    {
        if (p5 == 1500) {
            String v0_2 = this.getString(2131953384);
            int v1_1 = this.n;
            v1_1.getClass();
            new vn.com.vng.zalopay.authentication.d(this, v0_2, 0, new MO.d(1, v1_1)).a();
        }
        return;
    }

    public final bridge synthetic androidx.fragment.app.Fragment g1()
    {
        return 0;
    }

    public final OD.a i1()
    {
        return this.q;
    }

    public final void l1(CC.b p1)
    {
        p1.F(this);
        return;
    }

    public final void o()
    {
        com.google.android.gms.internal.mlkit_vision_barcode.T7.d(this, new DZ.e(2131234257, this.getString(2131952849), this.getString(2131952848), this.getString(2131952058), this.getString(2131955646), 1, 17), "SplashScreenConfirmDialog");
        return;
    }

    public final void onActivityResult(int p1, int p2, android.content.Intent p3)
    {
        super.onActivityResult(p1, p2, p3);
        this.o = 0;
        return;
    }

    public final void onCreate(android.os.Bundle p1)
    {
        String v1_1;
        super.onCreate(p1);
        if (p1 == null) {
            v1_1 = 0;
        } else {
            v1_1 = 1;
        }
        this.o = v1_1;
        this.p = this.getCallingPackage();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if ((this.o) && (!android.text.TextUtils.isEmpty(this.p))) {
            this.finish();
        }
        return;
    }

    public final void s(String p2)
    {
        com.google.android.gms.internal.mlkit_vision_barcode.T7.e(this, p2, this.getString(2131952456));
        return;
    }

    public final void u(android.content.Intent p1, Integer p2)
    {
        this.setResult(p2.intValue(), p1);
        this.finish();
        return;
    }
}
