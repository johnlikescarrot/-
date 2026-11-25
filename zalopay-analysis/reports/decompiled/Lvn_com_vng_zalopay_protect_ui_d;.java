package vn.com.vng.zalopay.protect.ui;
public final class d extends b4.a {
    public final synthetic int c;
    public final synthetic vn.com.vng.zalopay.protect.ui.ProtectAccountFragment d;

    public synthetic d(vn.com.vng.zalopay.protect.ui.ProtectAccountFragment p1, int p2)
    {
        this.c = p2;
        this.d = p1;
        return;
    }

    public final void a(android.view.View p1)
    {
        switch (this.c) {
            case 0:
                this.d.onClickLoginDevices();
                return;
            case 1:
                vn.com.vng.zalopay.protect.ui.ProtectAccountFragment v1_2 = this.d;
                v1_2.onClickTermConditions();
                v1_2.onClickTermsConditions();
                return;
            case 2:
                this.d.onClickSetupAutoLockingTime();
                return;
            case 3:
                this.d.onClickChangePassword();
                return;
            case 4:
                this.d.onClickLogout();
                return;
            case 5:
                this.d.onClickChangePhone();
                return;
            default:
                this.d.onClickTouchPointResetPassCode();
                return;
        }
    }
}
