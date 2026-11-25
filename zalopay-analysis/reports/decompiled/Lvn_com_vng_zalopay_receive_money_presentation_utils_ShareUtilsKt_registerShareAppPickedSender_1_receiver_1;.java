package vn.com.vng.zalopay.receive_money.presentation.utils;
public final class ShareUtilsKt$registerShareAppPickedSender$1$receiver$1 extends android.content.BroadcastReceiver {
    public final synthetic kotlin.jvm.functions.Function1 a;
    public final synthetic android.content.Context b;

    public ShareUtilsKt$registerShareAppPickedSender$1$receiver$1(android.content.Context p1, kotlin.jvm.functions.Function1 p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final void onReceive(android.content.Context p2, android.content.Intent p3)
    {
        Throwable v2_0 = xK.i.a(p3);
        try {
            this.b.unregisterReceiver(this);
        } catch (Throwable v2_1) {
            throw v2_1;
        }
        if (xK.i.b == this) {
            xK.i.b = 0;
        }
        if (v2_0 != null) {
            this.a.invoke(v2_0);
        }
        return;
    }
}
