package vn.com.vng.zalopay.webapp.activity;
public abstract class WebAppBaseToolbarActivity extends vn.com.vng.zalopay.user.UserBaseToolBarActivity implements XQ.t {
    public static final synthetic int u;
    protected android.widget.LinearLayout llActionViews;
    protected vn.zalopay.ui.navigationbar.NavigationBar navigationBar;
    public final kf.a r;
    public kF.b s;
    public Ew.y t;
    protected android.widget.TextView tvTitle;
    protected android.view.View viewShare;

    public WebAppBaseToolbarActivity()
    {
        this.r = new kf.a(0);
        this.s = 0;
        return;
    }

    public final void i(Ew.y p3)
    {
        this.r.c(com.google.android.gms.internal.mlkit_vision_barcode.y0.a(new vn.com.vng.zalopay.webapp.activity.a(this, p3, 0)));
        return;
    }

    public final void o1(Jw.c p7)
    {
        int v0_0 = p7.a;
        kf.a v2 = this.r;
        if (java.util.regex.Pattern.compile(wmoi.igob.ggcn(12871)).matcher(v0_0).matches()) {
            vn.zalopay.ui.navigationbar.NavigationBar v1_1 = this.getSupportActionBar();
            if (v1_1 != null) {
                try {
                    v2.c(com.google.android.gms.internal.mlkit_vision_barcode.y0.a(new Kk.f(v1_1, v0_0, 10)));
                } catch (vn.zalopay.ui.navigationbar.NavigationBar v1_3) {
                    v1_3.getMessage();
                }
                this.navigationBar.setBackgroundColor(android.graphics.Color.parseColor(v0_0));
            }
        }
        int v0_2 = p7.d;
        int v3_2 = 0;
        v2.c(com.google.android.gms.internal.mlkit_vision_barcode.y0.a(new UQ.b(this, v0_2, 0)));
        if (v0_2 == 0) {
            v3_2 = 8;
        }
        this.navigationBar.setVisibility(v3_2);
        this.p1(p7.b);
        return;
    }

    public final void onDestroy()
    {
        super.onDestroy();
        this.s = 0;
        this.r.e();
        return;
    }

    public void onShareClicked()
    {
        Ew.y v0 = this.t;
        if (v0 != null) {
            kF.b v1 = this.s;
            if (v1 != null) {
                v1.c(v0);
            }
        }
        return;
    }

    public abstract void p1(String p0);

    public final void setTitle(CharSequence p2)
    {
        vn.zalopay.ui.navigationbar.NavigationBar v0_0 = this.tvTitle;
        if (v0_0 != null) {
            v0_0.setText(p2);
            this.navigationBar.setTitle(p2.toString());
            return;
        } else {
            return;
        }
    }
}
