package vn.com.vng.zalopay.passport.widget;
abstract class OnboardingView extends android.widget.LinearLayout {
    public final Boolean a;
    public android.view.View$OnClickListener b;
    public android.view.View$OnClickListener c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final Boolean h;
    public final String i;
    android.widget.TextView mDescInputView;
    vn.zalopay.ui.IconFont mIconBtnSecondary;
    android.widget.Button mPrimaryBtnView;
    android.view.View mSecondaryButton;
    android.widget.TextView mSubTitleView;
    android.widget.TextView mTitleView;
    android.widget.TextView mTvSecondaryView;

    public OnboardingView(android.content.Context p4, android.util.AttributeSet p5)
    {
        super(p4, p5, 0);
        int v1_3 = Boolean.FALSE;
        super.a = v1_3;
        super.b = 0;
        super.c = 0;
        super.d = 0;
        super.e = 0;
        super.f = 0;
        super.g = 0;
        super.h = v1_3;
        super.i = 0;
        if (!super.isInEditMode()) {
            android.view.View.inflate(p4, 2131559508, super);
            super.setOrientation(1);
            super.setGravity(1);
            super.setPadding(eQ.a.a(1098907648), eQ.a.a(1098907648), eQ.a.a(1098907648), 0);
            android.view.View v4_1 = p4.obtainStyledAttributes(p5, sm.c.b);
            super.d = v4_1.getString(7);
            super.e = v4_1.getString(6);
            super.f = v4_1.getString(1);
            super.a = Boolean.valueOf(v4_1.getBoolean(4, super.a.booleanValue()));
            super.g = v4_1.getString(0);
            super.h = Boolean.valueOf(v4_1.getBoolean(5, 0));
            super.i = v4_1.getString(3);
            v4_1.recycle();
            super.addView(super.c(), 3);
        }
        return;
    }

    public void a()
    {
        this.mTitleView.setText(this.d);
        this.mSubTitleView.setText(this.e);
        this.mPrimaryBtnView.setText(this.g);
        this.mDescInputView.setText(this.f);
        this.e(this.a.booleanValue());
        this.f(this.h.booleanValue());
        this.mTvSecondaryView.setText(this.i);
        return;
    }

    public void b()
    {
        if (!this.mDescInputView.getText().toString().equals(this.f)) {
            this.mDescInputView.setText(this.f);
            this.mDescInputView.setTextColor(this.getContext().getColor(2131099828));
            return;
        } else {
            return;
        }
    }

    public abstract android.view.View c();

    public void d(String p4)
    {
        if (!android.text.TextUtils.isEmpty(p4)) {
            this.mDescInputView.setTextColor(this.getContext().getColor(2131100648));
            this.setEnablePrimaryButton(0);
        } else {
            this.mDescInputView.setTextColor(this.getContext().getColor(2131099828));
            this.setEnablePrimaryButton(1);
        }
        this.mDescInputView.setText(p4);
        return;
    }

    public abstract void e(boolean p0);

    public abstract void f(boolean p0);

    public void onClick(android.view.View p2)
    {
        android.view.View$OnClickListener v0 = this.b;
        if (v0 != null) {
            v0.onClick(p2);
        }
        return;
    }

    public void onFinishInflate()
    {
        super.onFinishInflate();
        butterknife.ButterKnife.a(this, this);
        this.a();
        return;
    }

    public void onSecondClick(android.view.View p2)
    {
        android.view.View$OnClickListener v0 = this.c;
        if (v0 != null) {
            v0.onClick(p2);
        }
        return;
    }

    public abstract void setEnablePrimaryButton(boolean p0);
}
