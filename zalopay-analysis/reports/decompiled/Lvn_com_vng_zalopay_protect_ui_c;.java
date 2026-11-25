package vn.com.vng.zalopay.protect.ui;
public final synthetic class c implements java.lang.Runnable {
    public final synthetic vn.com.vng.zalopay.protect.ui.ProtectAccountFragment a;
    public final synthetic boolean b;

    public synthetic c(vn.com.vng.zalopay.protect.ui.ProtectAccountFragment p1, boolean p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final void run()
    {
        this.a.mSwcTouchIdView.setChecked(this.b);
        return;
    }
}
