package vn.com.vng.zalopay.passport;
public class ForceUpdatePhoneFragment extends Ts.d implements vm.a {
    android.widget.Button btnUpdatePhone;
    android.widget.ImageView imgForceUpdatePhone;
    android.widget.LinearLayout layoutForceUpdatePhone;
    android.widget.TextView tvTermCondition;
    android.widget.TextView tvWarning1;
    android.widget.TextView tvWarning2;
    android.widget.TextView tvWarning3;
    public OY.e v;
    public vn.com.vng.zalopay.navigation.b w;

    public ForceUpdatePhoneFragment()
    {
        return;
    }

    public final int m1()
    {
        return 2131558933;
    }

    public final void n1()
    {
        CC.k v0 = q9.a5.c();
        if (v0 != null) {
            v0.s0(this);
            return;
        } else {
            return;
        }
    }

    public void onCheckChange()
    {
        android.widget.Button v0 = this.btnUpdatePhone;
        v0.setEnabled((v0.isEnabled() ^ 1));
        return;
    }

    public final void onViewCreated(android.view.View p4, android.os.Bundle p5)
    {
        super.onViewCreated(p4, p5);
        this.v.c(this.getContext(), this.imgForceUpdatePhone);
        eQ.a.h(this.tvTermCondition, 2131953462, 2131954955, 2131099828, new OH.c(this, 0));
        return;
    }
}
