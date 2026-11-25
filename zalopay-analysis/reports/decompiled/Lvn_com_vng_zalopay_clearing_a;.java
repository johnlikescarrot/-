package vn.com.vng.zalopay.clearing;
public final synthetic class a implements android.view.View$OnClickListener {
    public final synthetic int a;
    public final synthetic vn.com.vng.zalopay.clearing.ClearingAccountFragment b;

    public synthetic a(vn.com.vng.zalopay.clearing.ClearingAccountFragment p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final void onClick(android.view.View p4)
    {
        switch (this.a) {
            case 0:
                kf.b v4_4 = this.b;
                kf.a v0_4 = v4_4.v;
                ((kf.a) v0_4.a).c(((kf.b) q9.H.f(v0_4.c.a(v4_4.mPhoneView.getInputText(), v4_4.mInputOTPView.getInputText())).subscribeWith(new WO.f(v0_4, 2))));
                return;
            default:
                kf.b v4_6 = this.b;
                v4_6.mInputOTPView.g();
                v4_6.mInputOTPView.setInputText("");
                v4_6.q1(1);
                return;
        }
    }
}
