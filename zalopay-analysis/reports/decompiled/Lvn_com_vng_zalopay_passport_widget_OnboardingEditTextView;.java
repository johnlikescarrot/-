package vn.com.vng.zalopay.passport.widget;
public class OnboardingEditTextView extends vn.com.vng.zalopay.passport.widget.OnboardingView {
    public vn.com.vng.zalopay.passport.widget.CustomTextView j;
    public int k;

    public OnboardingEditTextView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.k = -1;
        return;
    }

    public final void a()
    {
        super.a();
        this.j.addTextChangedListener(new vn.com.vng.zalopay.passport.widget.a(this));
        return;
    }

    public final android.view.View c()
    {
        vn.com.vng.zalopay.passport.widget.CustomTextView v0_2 = ((vn.com.vng.zalopay.passport.widget.CustomTextView) android.view.View.inflate(this.getContext(), 2131559506, 0));
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
        int v0_2;
        super.b();
        if (!this.a.booleanValue()) {
            v0_2 = 8;
        } else {
            v0_2 = 0;
        }
        this.mPrimaryBtnView.setVisibility(v0_2);
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

    public final void h()
    {
        this.mPrimaryBtnView.setBackground(0);
        android.widget.Button v0_6 = this.mPrimaryBtnView.getLayoutParams();
        v0_6.width = -2;
        this.mPrimaryBtnView.setLayoutParams(v0_6);
        this.mPrimaryBtnView.setPadding(eQ.a.a(1098907648), 0, eQ.a.a(1098907648), 0);
        this.mPrimaryBtnView.setCompoundDrawablesWithIntrinsicBounds(0, 0, this.getContext().getDrawable(2131233330), 0);
        this.mPrimaryBtnView.setTextColor(androidx.core.content.c.b(this.getContext(), 2131100851));
        return;
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

    public void setInputLength(int p4)
    {
        vn.com.vng.zalopay.passport.widget.CustomTextView v0 = this.j;
        android.text.InputFilter[] v4_2 = new android.text.InputFilter[1];
        v4_2[0] = new android.text.InputFilter$LengthFilter(p4);
        v0.setFilters(v4_2);
        return;
    }

    public void setInputText(String p2)
    {
        this.j.setInputText(p2);
        return;
    }

    public void setInputTextEnabled(boolean p2)
    {
        this.j.setEnabled(p2);
        return;
    }

    public void setLengthToActiveButton(int p3)
    {
        int v0_2;
        this.k = p3;
        if (this.getInputText().length() < this.k) {
            v0_2 = 0;
        } else {
            v0_2 = 1;
        }
        this.mPrimaryBtnView.setEnabled(v0_2);
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
