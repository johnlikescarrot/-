package vn.com.vng.zalopay.clearing;
public class ClearingAccountActivity extends vn.com.vng.zalopay.ui.activity.SkinActivity {
    public final OD.a n;

    public ClearingAccountActivity()
    {
        this.n = new OD.a("", -1);
        return;
    }

    public final androidx.fragment.app.Fragment g1()
    {
        android.os.Bundle v0_1 = this.getIntent().getExtras();
        vn.com.vng.zalopay.clearing.ClearingAccountFragment v1_1 = new vn.com.vng.zalopay.clearing.ClearingAccountFragment();
        v1_1.setArguments(v0_1);
        return v1_1;
    }

    public final OD.a i1()
    {
        return this.n;
    }
}
