package vn.com.vng.zalopay.protect.ui;
public class ProtectAccountFragment_ViewBinding implements butterknife.Unbinder {
    public vn.com.vng.zalopay.protect.ui.ProtectAccountFragment b;
    public android.view.View c;
    public android.view.View d;
    public android.view.View e;
    public android.view.View f;
    public android.view.View g;
    public android.view.View h;
    public android.view.View i;
    public android.view.View j;

    public ProtectAccountFragment_ViewBinding(vn.com.vng.zalopay.protect.ui.ProtectAccountFragment p5, android.view.View p6)
    {
        this.b = p5;
        vn.com.vng.zalopay.protect.ui.d v0_26 = b4.e.b(p6, 2131364312, "field \'mSwcTouchIdView\' and method \'onTouchTouchId\'");
        p5.mSwcTouchIdView = ((androidx.appcompat.widget.SwitchCompat) b4.e.a(v0_26, 2131364312, "field \'mSwcTouchIdView\'", androidx.appcompat.widget.SwitchCompat));
        this.c = v0_26;
        v0_26.setOnTouchListener(new com.vnpay.vntaxi.ui.fragments.viewCar.i(3, p5));
        p5.mSwcProtectAccountView = ((androidx.appcompat.widget.SwitchCompat) b4.e.a(b4.e.b(p6, 2131364311, "field \'mSwcProtectAccountView\'"), 2131364311, "field \'mSwcProtectAccountView\'", androidx.appcompat.widget.SwitchCompat));
        p5.mSwcRequireConfirmPassword = ((androidx.appcompat.widget.SwitchCompat) b4.e.a(b4.e.b(p6, 2131364314, "field \'mSwcRequireConfirmPassword\'"), 2131364314, "field \'mSwcRequireConfirmPassword\'", androidx.appcompat.widget.SwitchCompat));
        p5.mVgTouchIdView = b4.e.b(p6, 2131365061, "field \'mVgTouchIdView\'");
        p5.mTvTouchIdView = b4.e.b(p6, 2131363458, "field \'mTvTouchIdView\'");
        vn.com.vng.zalopay.protect.ui.d v0_25 = b4.e.b(p6, 2131365062, "field \'mVgManageDeviceLogin\' and method \'onClickLoginDevices\'");
        p5.mVgManageDeviceLogin = v0_25;
        this.d = v0_25;
        v0_25.setOnClickListener(new vn.com.vng.zalopay.protect.ui.d(p5, 0));
        vn.com.vng.zalopay.protect.ui.d v0_28 = b4.e.b(p6, 2131364379, "field \'viewTermConditions\', method \'onClickTermConditions\', and method \'onClickTermsConditions\'");
        p5.viewTermConditions = v0_28;
        this.e = v0_28;
        v0_28.setOnClickListener(new vn.com.vng.zalopay.protect.ui.d(p5, 1));
        p5.mTvManageDeviceLogin = b4.e.b(p6, 2131364910, "field \'mTvManageDeviceLogin\'");
        vn.com.vng.zalopay.protect.ui.d v0_32 = b4.e.b(p6, 2131365065, "field \'mVgSetupAutoLockingTime\' and method \'onClickSetupAutoLockingTime\'");
        p5.mVgSetupAutoLockingTime = v0_32;
        this.f = v0_32;
        v0_32.setOnClickListener(new vn.com.vng.zalopay.protect.ui.d(p5, 2));
        p5.mTvLockTime = ((android.widget.TextView) b4.e.a(b4.e.b(p6, 2131364904, "field \'mTvLockTime\'"), 2131364904, "field \'mTvLockTime\'", android.widget.TextView));
        p5.mTvLockTimeDescription = ((android.widget.TextView) b4.e.a(b4.e.b(p6, 2131364824, "field \'mTvLockTimeDescription\'"), 2131364824, "field \'mTvLockTimeDescription\'", android.widget.TextView));
        p5.mProtectDescView = ((android.widget.TextView) b4.e.a(b4.e.b(p6, 2131364743, "field \'mProtectDescView\'"), 2131364743, "field \'mProtectDescView\'", android.widget.TextView));
        p5.mProtectAccountView = b4.e.b(p6, 2131365060, "field \'mProtectAccountView\'");
        vn.com.vng.zalopay.protect.ui.d v0_5 = b4.e.b(p6, 2131365059, "method \'onClickChangePassword\'");
        this.g = v0_5;
        v0_5.setOnClickListener(new vn.com.vng.zalopay.protect.ui.d(p5, 3));
        vn.com.vng.zalopay.protect.ui.d v0_7 = b4.e.b(p6, 2131363955, "method \'onClickLogout\'");
        this.h = v0_7;
        v0_7.setOnClickListener(new vn.com.vng.zalopay.protect.ui.d(p5, 4));
        vn.com.vng.zalopay.protect.ui.d v0_9 = b4.e.b(p6, 2131362276, "method \'onClickChangePhone\'");
        this.i = v0_9;
        v0_9.setOnClickListener(new vn.com.vng.zalopay.protect.ui.d(p5, 5));
        android.view.View v6_1 = b4.e.b(p6, 2131364043, "method \'onClickTouchPointResetPassCode\'");
        this.j = v6_1;
        v6_1.setOnClickListener(new vn.com.vng.zalopay.protect.ui.d(p5, 6));
        return;
    }

    public final void a()
    {
        IllegalStateException v0_0 = this.b;
        if (v0_0 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        } else {
            this.b = 0;
            v0_0.mSwcTouchIdView = 0;
            v0_0.mSwcProtectAccountView = 0;
            v0_0.mSwcRequireConfirmPassword = 0;
            v0_0.mVgTouchIdView = 0;
            v0_0.mTvTouchIdView = 0;
            v0_0.mVgManageDeviceLogin = 0;
            v0_0.viewTermConditions = 0;
            v0_0.mTvManageDeviceLogin = 0;
            v0_0.mVgSetupAutoLockingTime = 0;
            v0_0.mTvLockTime = 0;
            v0_0.mTvLockTimeDescription = 0;
            v0_0.mProtectDescView = 0;
            v0_0.mProtectAccountView = 0;
            this.c.setOnTouchListener(0);
            this.c = 0;
            this.d.setOnClickListener(0);
            this.d = 0;
            this.e.setOnClickListener(0);
            this.e = 0;
            this.f.setOnClickListener(0);
            this.f = 0;
            this.g.setOnClickListener(0);
            this.g = 0;
            this.h.setOnClickListener(0);
            this.h = 0;
            this.i.setOnClickListener(0);
            this.i = 0;
            this.j.setOnClickListener(0);
            this.j = 0;
            return;
        }
    }
}
