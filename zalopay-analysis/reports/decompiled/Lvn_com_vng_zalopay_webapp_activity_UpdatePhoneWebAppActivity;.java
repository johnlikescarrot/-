package vn.com.vng.zalopay.webapp.activity;
public final class UpdatePhoneWebAppActivity extends vn.com.vng.zalopay.webapp.activity.WebAppActivity {

    public UpdatePhoneWebAppActivity()
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
        WQ.p v1_1 = new WQ.p();
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
