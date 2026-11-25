package vn.com.vng.webapp.framework;
public class ZPWebViewApp extends android.webkit.WebView implements qm.c {

    public ZPWebViewApp(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.c();
        return;
    }

    public ZPWebViewApp(android.content.Context p1, android.util.AttributeSet p2, int p3)
    {
        super(p1, p2, 0);
        super.c();
        return;
    }

    public final void a()
    {
        if (!android.text.TextUtils.isEmpty(wmoi.igob.ggcn(9718))) {
            qm.k v0_6 = android.os.Looper.myLooper();
            android.os.Handler v1_2 = android.os.Looper.getMainLooper();
            String v2_1 = wmoi.igob.ggcn(9719);
            String v3_1 = wmoi.igob.ggcn(9720);
            if (v0_6 != v1_2) {
                qm.b.a.post(new qm.k(this, v2_1, v3_1, 1));
                return;
            } else {
                this.evaluateJavascript(v2_1, 0);
                this.evaluateJavascript(v3_1, 0);
                return;
            }
        } else {
            return;
        }
    }

    public final void b(qm.i p10)
    {
        String v0_0 = wmoi.igob.ggcn(9721);
        qm.k v1_0 = wmoi.igob.ggcn(9722);
        int v2_1 = wmoi.igob.ggcn(9723);
        String v3 = wmoi.igob.ggcn(9724);
        StringBuilder v4_0 = wmoi.igob.ggcn(9725);
        try {
            if (p10 != null) {
                org.json.JSONObject v5_1 = new org.json.JSONObject();
                v5_1.put(wmoi.igob.ggcn(9726), p10.a);
                v5_1.put(wmoi.igob.ggcn(9727), p10.b);
                v5_1.put(wmoi.igob.ggcn(9728), p10.c);
                v5_1.put(wmoi.igob.ggcn(9729), p10.d);
                v5_1.put(wmoi.igob.ggcn(9730), p10.e);
                android.os.Handler v10_3 = v5_1.toString().replaceAll(v1_0, v0_0);
                StringBuilder v6_6 = new StringBuilder(v1_0);
                v6_6.append(v10_3);
                v6_6.append(v1_0);
                android.os.Handler v10_4 = v6_6.toString();
                StringBuilder v6_8 = new StringBuilder(v4_0);
                v6_8.append(v10_4);
                v6_8.append(v2_1);
                android.os.Handler v10_5 = v6_8.toString();
                String v0_1 = v5_1.toString().replaceAll(v1_0, v0_0);
                StringBuilder v4_3 = new StringBuilder(v1_0);
                v4_3.append(v0_1);
                v4_3.append(v1_0);
                String v0_2 = v4_3.toString();
                qm.k v1_2 = new StringBuilder(v3);
                v1_2.append(v0_2);
                v1_2.append(v2_1);
                String v0_3 = v1_2.toString();
                if (android.os.Looper.myLooper() != android.os.Looper.getMainLooper()) {
                    qm.b.a.post(new qm.k(this, v10_5, v0_3, 0));
                } else {
                    this.evaluateJavascript(v10_5, 0);
                    this.evaluateJavascript(v0_3, 0);
                    return;
                }
            }
        } catch (org.json.JSONException) {
        }
        return;
    }

    public final void c()
    {
        try {
            qm.j v0_0 = this.getSettings();
            v0_0.setJavaScriptEnabled(1);
            v0_0.setDomStorageEnabled(1);
            v0_0.setJavaScriptCanOpenWindowsAutomatically(1);
            v0_0.setBlockNetworkImage(0);
            v0_0.setLoadWithOverviewMode(1);
            v0_0.setLoadsImagesAutomatically(1);
            v0_0.setSavePassword(0);
            v0_0.setSaveFormData(0);
            v0_0.setTextZoom(100);
            this.setWebChromeClient(new qm.j(0));
        } catch (Throwable) {
        }
        return;
    }
}
