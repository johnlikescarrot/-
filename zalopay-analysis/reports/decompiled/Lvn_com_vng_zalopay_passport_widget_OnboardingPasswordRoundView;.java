package vn.com.vng.zalopay.passport.widget;
public class OnboardingPasswordRoundView extends vn.com.vng.zalopay.passport.widget.OnboardingView {
    public vn.com.vng.zalopay.passport.widget.CustomPassCodeRoundView j;

    public OnboardingPasswordRoundView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        return;
    }

    public final android.view.View c()
    {
        vn.com.vng.zalopay.passport.widget.CustomPassCodeRoundView v0_2 = ((vn.com.vng.zalopay.passport.widget.CustomPassCodeRoundView) android.view.View.inflate(this.getContext(), 2131559507, 0));
        this.j = v0_2;
        return v0_2;
    }

    public final void e(boolean p2)
    {
        int v2_1;
        if (p2 == 0) {
            v2_1 = 8;
        } else {
            v2_1 = 0;
        }
        this.mPrimaryBtnView.setVisibility(v2_1);
        return;
    }

    public final void f(boolean p2)
    {
        int v2_1;
        if (p2 == 0) {
            v2_1 = 8;
        } else {
            v2_1 = 0;
        }
        this.mSecondaryButton.setVisibility(v2_1);
        return;
    }

    public final void g()
    {
        super.b();
        this.e(this.a.booleanValue());
        this.setInputText("");
        return;
    }

    public String getInputText()
    {
        return this.j.getInputText();
    }

    public GZ.l getNumberEditable()
    {
        return this.j;
    }

    public bridge synthetic void onClick(android.view.View p1)
    {
        super.onClick(p1);
        return;
    }

    public bridge synthetic void onSecondClick(android.view.View p1)
    {
        super.onSecondClick(p1);
        return;
    }

    public void setEnablePrimaryButton(boolean p2)
    {
        this.mPrimaryBtnView.setEnabled(p2);
        return;
    }

    public void setEnableSecondaryButton(Boolean p3)
    {
        this.mSecondaryButton.setEnabled(p3.booleanValue());
        this.mTvSecondaryView.setEnabled(p3.booleanValue());
        return;
    }

    public void setEnteredListener(PH.a p2)
    {
        this.j.setPinEnteredListener(p2);
        return;
    }

    public void setError(String p1)
    {
        this.d(p1);
        return;
    }

    public void setIconSecondaryButton(String p3)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            this.mIconBtnSecondary.setVisibility(0);
            this.mIconBtnSecondary.setIcon(p3);
            return;
        } else {
            this.mIconBtnSecondary.setVisibility(8);
            return;
        }
    }

    public void setInputText(String p2)
    {
        this.j.setInputText(p2);
        return;
    }

    public void setOnClick(android.view.View$OnClickListener p1)
    {
        this.b = p1;
        return;
    }

    public void setOnSecondClick(android.view.View$OnClickListener p1)
    {
        this.c = p1;
        return;
    }

    public void setSubTitle(String p2)
    {
        this.mSubTitleView.setText(p2);
        return;
    }

    public void setTextSecondaryButton(String p2)
    {
        this.mTvSecondaryView.setText(p2);
        return;
    }
}
