package vn.com.vng.zalopay.webapp.activity;
public final class ManualResetPinWebAppActivity extends vn.com.vng.zalopay.webapp.activity.WebAppActivity {

    public ManualResetPinWebAppActivity()
    {
        return;
    }

    public final bridge synthetic androidx.fragment.app.Fragment g1()
    {
        return this.o1();
    }

    public final Ts.d o1()
    {
        android.os.Bundle v0_1 = this.getIntent().getExtras();
        WQ.n v1_1 = new WQ.n();
        v1_1.setArguments(v0_1);
        return v1_1;
    }

    public final void onActivityResult(int p2, int p3, android.content.Intent p4)
    {
        super.onActivityResult(p2, p3, p4);
        this.o1().onActivityResult(p2, p3, p4);
        return;
    }
}
