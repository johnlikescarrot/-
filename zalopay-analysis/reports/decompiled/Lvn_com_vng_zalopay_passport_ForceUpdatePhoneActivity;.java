package vn.com.vng.zalopay.passport;
public class ForceUpdatePhoneActivity extends vn.com.vng.zalopay.user.UserBaseToolBarActivity {

    public ForceUpdatePhoneActivity()
    {
        return;
    }

    public final androidx.fragment.app.Fragment g1()
    {
        android.os.Bundle v0_1 = new android.os.Bundle();
        vn.com.vng.zalopay.passport.ForceUpdatePhoneFragment v1_1 = new vn.com.vng.zalopay.passport.ForceUpdatePhoneFragment();
        v1_1.setArguments(v0_1);
        return v1_1;
    }

    public final OD.a i1()
    {
        return new OD.a("", -1);
    }

    public final boolean onCreateOptionsMenu(android.view.Menu p4)
    {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().s(0);
        }
        androidx.appcompat.app.f v0_1 = ((android.widget.TextView) this.findViewById(2131364787));
        if (v0_1 == null) {
            this.setTitle(2131954082);
        } else {
            v0_1.setVisibility(0);
            v0_1.setText(2131954082);
            this.getSupportActionBar().u();
        }
        return super.onCreateOptionsMenu(p4);
    }
}
