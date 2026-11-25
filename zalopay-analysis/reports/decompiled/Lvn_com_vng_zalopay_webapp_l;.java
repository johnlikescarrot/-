package vn.com.vng.zalopay.webapp;
public final class l implements qm.e {
    public static final java.util.HashMap b;
    public final XQ.h a;

    static l()
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        vn.com.vng.zalopay.webapp.l.b = v0_1;
        androidx.compose.foundation.layout.G0.s(1, v0_1, "getUserInfo", 3, "getAccessToken");
        return;
    }

    public l(XQ.l p1)
    {
        this.a = p1;
        return;
    }

    public final void a(String p3, org.json.JSONObject p4, qm.g p5)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            int v0_5 = ((Integer) vn.com.vng.zalopay.webapp.l.b.get(p3));
            if (v0_5 != 0) {
                long v3_1 = v0_5.intValue();
                XQ.h v1_0 = this.a;
                if (v3_1 == 1) {
                    try {
                        long v3_3 = p4.optLong("appid");
                    } catch (long v3_4) {
                        v3_4.getMessage();
                        v3_3 = 0;
                    }
                    v1_0.d(v3_3, p5);
                    return;
                } else {
                    if (v3_1 == 3) {
                        v1_0.g(p5);
                        return;
                    } else {
                        return;
                    }
                }
            } else {
                int v4_3 = new StringBuilder("Cannot handle function: ");
                v4_3.append(p3);
                p5.a(2, v4_3.toString());
                return;
            }
        } else {
            p5.a(2, "empty function");
            return;
        }
    }

    public final String[] b()
    {
        String[] v1_1 = new String[0];
        return ((String[]) vn.com.vng.zalopay.webapp.l.b.keySet().toArray(v1_1));
    }
}
