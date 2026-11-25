package vn.com.vng.zalopay.clearing;
public class ClearingAccountFragment_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.clearing.ClearingAccountFragment b;
    public android.view.View c;

    public ClearingAccountFragment_ViewBinding(vn.com.vng.zalopay.clearing.ClearingAccountFragment p6, android.view.View p7)
    {
        this.b = p6;
        p6.mKeyboardView = ((vn.com.vng.zalopay.passport.widget.CustomKeyboardView) b4.e.c(p7, 2131363285, "field \'mKeyboardView\'", vn.com.vng.zalopay.passport.widget.CustomKeyboardView));
        p6.mFlipperView = ((android.widget.ViewFlipper) b4.e.a(b4.e.b(p7, 2131362779, "field \'mFlipperView\'"), 2131362779, "field \'mFlipperView\'", android.widget.ViewFlipper));
        p6.mPhoneView = ((vn.com.vng.zalopay.passport.widget.OnboardingEditTextView) b4.e.a(b4.e.b(p7, 2131362780, "field \'mPhoneView\'"), 2131362780, "field \'mPhoneView\'", vn.com.vng.zalopay.passport.widget.OnboardingEditTextView));
        p6.mInputPinView = ((vn.com.vng.zalopay.passport.widget.OnboardingPasswordRoundView) b4.e.a(b4.e.b(p7, 2131362781, "field \'mInputPinView\'"), 2131362781, "field \'mInputPinView\'", vn.com.vng.zalopay.passport.widget.OnboardingPasswordRoundView));
        p6.mInputOTPView = ((vn.com.vng.zalopay.passport.widget.OnboardingEditTextView) b4.e.a(b4.e.b(p7, 2131362782, "field \'mInputOTPView\'"), 2131362782, "field \'mInputOTPView\'", vn.com.vng.zalopay.passport.widget.OnboardingEditTextView));
        p6.mScrollView = ((android.widget.ScrollView) b4.e.a(b4.e.b(p7, 2131364160, "field \'mScrollView\'"), 2131364160, "field \'mScrollView\'", android.widget.ScrollView));
        p6.mMgsCustomerServicePhone = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364849, "field \'mMgsCustomerServicePhone\'"), 2131364849, "field \'mMgsCustomerServicePhone\'", android.widget.TextView));
        p6.mMgsCustomerService = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364848, "field \'mMgsCustomerService\'"), 2131364848, "field \'mMgsCustomerService\'", android.widget.TextView));
        p6.mMgsError = ((android.widget.TextView) b4.e.a(b4.e.b(p7, 2131364913, "field \'mMgsError\'"), 2131364913, "field \'mMgsError\'", android.widget.TextView));
        android.view.View v7_1 = b4.e.b(p7, 2131362138, "method \'closeApp\'");
        this.c = v7_1;
        v7_1.setOnClickListener(new UQ.d(1, p6));
        return;
    }

    public final void a()
    {
        IllegalStateException v0_0 = this.b;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.b = 0;
            v0_0.mKeyboardView = 0;
            v0_0.mFlipperView = 0;
            v0_0.mPhoneView = 0;
            v0_0.mInputPinView = 0;
            v0_0.mInputOTPView = 0;
            v0_0.mScrollView = 0;
            v0_0.mMgsCustomerServicePhone = 0;
            v0_0.mMgsCustomerService = 0;
            v0_0.mMgsError = 0;
            this.c.setOnClickListener(0);
            this.c = 0;
            return;
        }
    }
}
