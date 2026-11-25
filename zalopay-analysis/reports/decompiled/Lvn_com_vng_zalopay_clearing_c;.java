package vn.com.vng.zalopay.clearing;
public final class c extends android.os.CountDownTimer {
    public final synthetic vn.com.vng.zalopay.clearing.ClearingAccountFragment a;

    public c(vn.com.vng.zalopay.clearing.ClearingAccountFragment p3, long p4)
    {
        this.a = p3;
        super(p4, 1000);
        return;
    }

    public final void onFinish()
    {
        String v0_0 = this.a;
        v0_0.mInputOTPView.setTextSecondaryButton(v0_0.getString(2131954323));
        v0_0.mInputOTPView.setEnableSecondaryButton(Boolean.TRUE);
        v0_0.mInputOTPView.setIconSecondaryButton(v0_0.getString(2131953921));
        return;
    }

    public final void onTick(long p4)
    {
        vn.com.vng.zalopay.clearing.ClearingAccountFragment v1 = this.a;
        v1.mInputOTPView.setTextSecondaryButton(String.format(v1.getString(2131954325), new Object[] {m9.D.b(p4)})));
        v1.mInputOTPView.setEnableSecondaryButton(Boolean.FALSE);
        v1.mInputOTPView.setIconSecondaryButton("");
        return;
    }
}
