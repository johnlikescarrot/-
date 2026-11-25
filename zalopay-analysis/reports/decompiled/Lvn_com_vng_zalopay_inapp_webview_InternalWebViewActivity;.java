package vn.com.vng.zalopay.inapp_webview;
public final class InternalWebViewActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int l;
    public KR.c i;
    public String j;
    public final vn.com.vng.zalopay.inapp_webview.InternalWebViewActivity$finishReceiver$1 k;

    public InternalWebViewActivity()
    {
        this.k = new vn.com.vng.zalopay.inapp_webview.InternalWebViewActivity$finishReceiver$1(this);
        return;
    }

    public final void onCreate(android.os.Bundle p7)
    {
        y0.b v7_1;
        super.onCreate(p7);
        y0.b v7_11 = this.getIntent().getExtras();
        if (v7_11 == null) {
            v7_1 = "";
        } else {
            v7_1 = v7_11.getString("url");
            if (v7_1 == null) {
            }
        }
        int v2_0;
        int v1_0 = v7_1;
        y0.b v7_3 = this.getIntent().getExtras();
        c00.b v0_0 = 0;
        if (v7_3 == null) {
            v2_0 = 0;
        } else {
            v2_0 = v7_3.getString("title");
        }
        y0.b v7_7 = this.getIntent().getExtras();
        if (v7_7 != null) {
            v0_0 = v7_7.getString("action_id");
        }
        y0.b v7_10;
        this.j = v0_0;
        y0.b v7_9 = this.getIntent().getExtras();
        if (v7_9 == null) {
            v7_10 = 0;
        } else {
            v7_10 = v7_9.getBoolean("secure", 0);
        }
        if (v7_10 != null) {
            this.getWindow().setFlags(8192, 8192);
            if (android.os.Build$VERSION.SDK_INT >= 33) {
                this.setRecentsScreenshotEnabled(0);
            }
        }
        KR.c v3_4 = new KR.c(this);
        this.i = v3_4;
        this.getOnBackPressedDispatcher().a(this, new jC.b(v3_4, this));
        androidx.core.content.c.d(this, this.k, new android.content.IntentFilter("ACTION_FINISH_INTERNAL_WEBVIEW_ACTIVITY"), 4);
        k.e.a(this, new y0.b(new c00.b(v1_0, v2_0, v3_4, this, 18), 1, 1250165172));
        return;
    }

    public final void onDestroy()
    {
        vn.com.vng.zalopay.inapp_webview.InternalWebViewActivity$finishReceiver$1 v0_0 = this.i;
        if (v0_0 != null) {
            KR.c.i(v0_0);
        }
        super.onDestroy();
        this.unregisterReceiver(this.k);
        return;
    }
}
