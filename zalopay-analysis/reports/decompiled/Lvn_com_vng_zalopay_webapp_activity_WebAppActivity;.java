package vn.com.vng.zalopay.webapp.activity;
public class WebAppActivity extends vn.com.vng.zalopay.user.UserBaseToolBarActivity {
    vn.zalopay.ui.IconFont btnBack;
    vn.zalopay.ui.IconFont btnClose;
    public final OD.a r;
    android.widget.TextView tvTitle;

    public WebAppActivity()
    {
        this.r = new OD.a("", -1);
        return;
    }

    public bridge synthetic androidx.fragment.app.Fragment g1()
    {
        return this.o1();
    }

    public final int h1()
    {
        return 2131558453;
    }

    public final OD.a i1()
    {
        return this.r;
    }

    public Ts.d o1()
    {
        android.os.Bundle v0_1 = this.getIntent().getExtras();
        WQ.u v1_1 = new WQ.u();
        v1_1.setArguments(v0_1);
        return v1_1;
    }

    public void onBackWebAppClicked()
    {
        UR.a.d("01.4620.001");
        this.onBackPressed();
        return;
    }

    public void onCloseWebAppClicked()
    {
        this.finish();
        return;
    }

    public final void onCreate(android.os.Bundle p6)
    {
        super.onCreate(p6);
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().s(0);
            this.getSupportActionBar().u();
        }
        this.btnBack.setIcon(2131952898);
        this.btnBack.setIconColor(2131101034);
        this.btnBack.setTextSize(1, 1099956224);
        this.btnClose.setIcon(2131955797);
        this.btnClose.setIconColor(2131101034);
        this.btnClose.setTextSize(1, 1099956224);
        this.mNavigationBar.setIsShowCloseButton(0);
        return;
    }

    public final void setTitle(CharSequence p2)
    {
        vn.zalopay.ui.navigationbar.NavigationBar v0_0 = this.tvTitle;
        if (v0_0 != null) {
            v0_0.setText(p2);
            this.mNavigationBar.setTitle(p2.toString());
            return;
        } else {
            return;
        }
    }
}
