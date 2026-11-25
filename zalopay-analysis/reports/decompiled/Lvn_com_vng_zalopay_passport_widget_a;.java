package vn.com.vng.zalopay.passport.widget;
public final class a implements android.text.TextWatcher {
    public final synthetic vn.com.vng.zalopay.passport.widget.OnboardingEditTextView a;

    public a(vn.com.vng.zalopay.passport.widget.OnboardingEditTextView p1)
    {
        this.a = p1;
        return;
    }

    public final void afterTextChanged(android.text.Editable p1)
    {
        return;
    }

    public final void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
    {
        return;
    }

    public final void onTextChanged(CharSequence p2, int p3, int p4, int p5)
    {
        int v5_1;
        p3 = this.a;
        if (p2.length() < p3.k) {
            v5_1 = 0;
        } else {
            v5_1 = 1;
        }
        p3.mPrimaryBtnView.setEnabled(v5_1);
        if (p2.length() < p3.k) {
            p3.b();
        }
        return;
    }
}
