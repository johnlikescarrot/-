package vn.com.vng.zalopay.logindevice;
public final class DeviceVerificationActivity extends vn.com.vng.zalopay.core.ui.activity.BaseToolBarActivity {

    public DeviceVerificationActivity()
    {
        return;
    }

    public final androidx.fragment.app.Fragment g1()
    {
        int v1_1;
        int v1_0 = this.getIntent();
        if (v1_0 == 0) {
            v1_1 = 0;
        } else {
            v1_1 = v1_0.getExtras();
        }
        TD.a.I.getClass();
        TD.a v0_2 = new TD.a();
        v0_2.setArguments(v1_1);
        return v0_2;
    }

    public final OD.a i1()
    {
        return new OD.a("[Login][OTP]", -1);
    }
}
