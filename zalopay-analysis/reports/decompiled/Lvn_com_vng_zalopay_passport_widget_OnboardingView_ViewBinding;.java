package vn.com.vng.zalopay.passport.widget;
public class OnboardingView_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.passport.widget.OnboardingView b;
    public android.view.View c;
    public android.view.View d;

    public OnboardingView_ViewBinding(vn.com.vng.zalopay.passport.widget.OnboardingView p6, android.view.View p7)
    {
        this.b = p6;
        p6.mTitleView = ((android.widget.TextView) b4.e.c(p7, 2131364787, wmoi.igob.ggcn(1920), android.widget.TextView));
        String v1_1 = wmoi.igob.ggcn(1921);
        p6.mSubTitleView = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364774, v1_1), 2131364774, v1_1, android.widget.TextView));
        String v1_3 = wmoi.igob.ggcn(1922);
        p6.mDescInputView = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364613, v1_3), 2131364613, v1_3, android.widget.TextView));
        int v0_9 = b4.e.b(p7, 2131362108, wmoi.igob.ggcn(1923));
        p6.mPrimaryBtnView = ((android.widget.Button) b4.e.a(v0_9, 2131362108, wmoi.igob.ggcn(1924), android.widget.Button));
        this.c = v0_9;
        v0_9.setOnClickListener(new vn.com.vng.zalopay.passport.widget.b(p6, 0));
        int v0_11 = b4.e.b(p7, 2131362114, wmoi.igob.ggcn(1925));
        p6.mSecondaryButton = v0_11;
        this.d = v0_11;
        v0_11.setOnClickListener(new vn.com.vng.zalopay.passport.widget.b(p6, 1));
        String v3_7 = wmoi.igob.ggcn(1926);
        p6.mIconBtnSecondary = ((vn.zalopay.ui.IconFont) b4.e.a(b4.e.b(p7, 2131362984, v3_7), 2131362984, v3_7, vn.zalopay.ui.IconFont));
        String v1_17 = wmoi.igob.ggcn(1927);
        p6.mTvSecondaryView = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364594, v1_17), 2131364594, v1_17, android.widget.TextView));
        return;
    }

    public final void a()
    {
        IllegalStateException v0_0 = this.b;
        if (v0_0 == null) {
            throw new IllegalStateException(wmoi.igob.ggcn(1928));
        } else {
            this.b = 0;
            v0_0.mTitleView = 0;
            v0_0.mSubTitleView = 0;
            v0_0.mDescInputView = 0;
            v0_0.mPrimaryBtnView = 0;
            v0_0.mSecondaryButton = 0;
            v0_0.mIconBtnSecondary = 0;
            v0_0.mTvSecondaryView = 0;
            this.c.setOnClickListener(0);
            this.c = 0;
            this.d.setOnClickListener(0);
            this.d = 0;
            return;
        }
    }
}
