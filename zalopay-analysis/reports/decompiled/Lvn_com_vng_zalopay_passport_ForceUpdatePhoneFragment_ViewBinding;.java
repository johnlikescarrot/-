package vn.com.vng.zalopay.passport;
public class ForceUpdatePhoneFragment_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.passport.ForceUpdatePhoneFragment b;
    public android.view.View c;

    public ForceUpdatePhoneFragment_ViewBinding(vn.com.vng.zalopay.passport.ForceUpdatePhoneFragment p6, android.view.View p7)
    {
        this.b = p6;
        p6.tvWarning1 = ((android.widget.TextView) b4.e.c(p7, 2131364877, "field \'tvWarning1\'", android.widget.TextView));
        p6.tvWarning2 = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364878, "field \'tvWarning2\'"), 2131364878, "field \'tvWarning2\'", android.widget.TextView));
        p6.tvWarning3 = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364879, "field \'tvWarning3\'"), 2131364879, "field \'tvWarning3\'", android.widget.TextView));
        p6.layoutForceUpdatePhone = ((android.widget.LinearLayout) b4.e.a(b4.e.b(p7, 2131363422, "field \'layoutForceUpdatePhone\'"), 2131363422, "field \'layoutForceUpdatePhone\'", android.widget.LinearLayout));
        p6.btnUpdatePhone = ((android.widget.Button) b4.e.a(b4.e.b(p7, 2131362148, "field \'btnUpdatePhone\'"), 2131362148, "field \'btnUpdatePhone\'", android.widget.Button));
        p6.imgForceUpdatePhone = ((android.widget.ImageView) b4.e.a(b4.e.b(p7, 2131363140, "field \'imgForceUpdatePhone\'"), 2131363140, "field \'imgForceUpdatePhone\'", android.widget.ImageView));
        p6.tvTermCondition = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364876, "field \'tvTermCondition\'"), 2131364876, "field \'tvTermCondition\'", android.widget.TextView));
        android.widget.CompoundButton v7_1 = b4.e.b(p7, 2131362263, "method \'onCheckChange\'");
        this.c = v7_1;
        ((android.widget.CompoundButton) v7_1).setOnCheckedChangeListener(new OH.d(0, p6));
        return;
    }

    public final void a()
    {
        IllegalStateException v0_0 = this.b;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.b = 0;
            v0_0.tvWarning1 = 0;
            v0_0.tvWarning2 = 0;
            v0_0.tvWarning3 = 0;
            v0_0.layoutForceUpdatePhone = 0;
            v0_0.btnUpdatePhone = 0;
            v0_0.imgForceUpdatePhone = 0;
            v0_0.tvTermCondition = 0;
            ((android.widget.CompoundButton) this.c).setOnCheckedChangeListener(0);
            this.c = 0;
            return;
        }
    }
}
