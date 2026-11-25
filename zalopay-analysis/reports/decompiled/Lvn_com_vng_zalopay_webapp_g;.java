package vn.com.vng.zalopay.webapp;
public final class g implements qm.e {
    public static final java.util.HashMap b;
    public final XQ.e a;

    static g()
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        vn.com.vng.zalopay.webapp.g.b = v0_1;
        androidx.compose.foundation.layout.G0.s(1, v0_1, "share", 2, "shareViaFacebook");
        return;
    }

    public g(XQ.j p1)
    {
        this.a = p1;
        return;
    }

    public final void a(String p7, org.json.JSONObject p8, qm.g p9)
    {
        if (!android.text.TextUtils.isEmpty(p7)) {
            XQ.e v0_5 = ((Integer) vn.com.vng.zalopay.webapp.g.b.get(p7));
            if (v0_5 != null) {
                String v7_1 = v0_5.intValue();
                XQ.e v0_1 = this.a;
                if (v7_1 == 1) {
                    String v7_3 = p8.optString("caption");
                    String v1_1 = p8.optString("content");
                    String v3_1 = p8.optString("link");
                    int v4_1 = p8.optInt("type");
                    if (p8.optBoolean("showShareButton", 1)) {
                        v0_1.o(v4_1, v1_1, v7_3, v3_1);
                        p9.f(0);
                        return;
                    } else {
                        p9.f(0);
                        v0_1.w(v4_1, v1_1, v7_3, v3_1);
                        return;
                    }
                } else {
                    if (v7_1 == 2) {
                        v0_1.c(p8.optString("url"), p8.optString("hashtag"), p8.optString("quote"), p9);
                        return;
                    } else {
                        return;
                    }
                }
            } else {
                String v8_4 = new StringBuilder("Cannot handle function: ");
                v8_4.append(p7);
                p9.a(2, v8_4.toString());
                return;
            }
        } else {
            p9.a(2, "empty function");
            return;
        }
    }

    public final String[] b()
    {
        String[] v1_1 = new String[0];
        return ((String[]) vn.com.vng.zalopay.webapp.g.b.keySet().toArray(v1_1));
    }
}
