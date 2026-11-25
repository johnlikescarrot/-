package vn.com.vng.zalopay.logindevice;
public class LoginDevicesActivity extends vn.com.vng.zalopay.user.UserBaseToolBarActivity {
    public final OD.a r;

    public LoginDevicesActivity()
    {
        this.r = new OD.a("", -1);
        return;
    }

    public final androidx.fragment.app.Fragment g1()
    {
        android.os.Bundle v0_1 = new android.os.Bundle();
        vn.com.vng.zalopay.logindevice.LoginDevicesFragment v1_1 = new vn.com.vng.zalopay.logindevice.LoginDevicesFragment();
        v1_1.setArguments(v0_1);
        return v1_1;
    }

    public final OD.a i1()
    {
        return this.r;
    }
}
