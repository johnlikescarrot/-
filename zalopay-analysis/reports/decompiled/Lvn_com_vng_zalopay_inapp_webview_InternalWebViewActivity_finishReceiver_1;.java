package vn.com.vng.zalopay.inapp_webview;
public final class InternalWebViewActivity$finishReceiver$1 extends android.content.BroadcastReceiver {
    public final synthetic vn.com.vng.zalopay.inapp_webview.InternalWebViewActivity a;

    public InternalWebViewActivity$finishReceiver$1(vn.com.vng.zalopay.inapp_webview.InternalWebViewActivity p1)
    {
        this.a = p1;
        return;
    }

    public final void onReceive(android.content.Context p2, android.content.Intent p3)
    {
        boolean v2_0;
        if (p3 == null) {
            v2_0 = 0;
        } else {
            v2_0 = p3.getAction();
        }
        if (kotlin.jvm.internal.Intrinsics.areEqual(v2_0, "ACTION_FINISH_INTERNAL_WEBVIEW_ACTIVITY")) {
            boolean v2_4 = p3.getExtras();
            if (v2_4) {
                boolean v2_1 = v2_4.getString("action_id");
                if (v2_1) {
                    vn.com.vng.zalopay.inapp_webview.InternalWebViewActivity v3_2 = this.a;
                    if (kotlin.jvm.internal.Intrinsics.areEqual(v2_1, v3_2.j)) {
                        v3_2.finish();
                    }
                }
            }
        }
        return;
    }
}
