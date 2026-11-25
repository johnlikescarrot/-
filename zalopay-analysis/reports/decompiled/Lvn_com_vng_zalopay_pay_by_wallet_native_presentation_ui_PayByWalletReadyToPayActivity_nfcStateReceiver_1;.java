package vn.com.vng.zalopay.pay_by_wallet_native.presentation.ui;
public final class PayByWalletReadyToPayActivity$nfcStateReceiver$1 extends android.content.BroadcastReceiver {
    public final synthetic vn.com.vng.zalopay.pay_by_wallet_native.presentation.ui.PayByWalletReadyToPayActivity a;

    public PayByWalletReadyToPayActivity$nfcStateReceiver$1(vn.com.vng.zalopay.pay_by_wallet_native.presentation.ui.PayByWalletReadyToPayActivity p1)
    {
        this.a = p1;
        return;
    }

    public final void onReceive(android.content.Context p2, android.content.Intent p3)
    {
        if (p3.getIntExtra("android.nfc.extra.ADAPTER_STATE", 1) == 3) {
            vn.com.vng.zalopay.pay_by_wallet_native.presentation.ui.PayByWalletReadyToPayActivity v2_2 = this.a;
            v2_2.e1().g0(new cI.m(v2_2));
            return;
        } else {
            return;
        }
    }
}
