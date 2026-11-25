package vn.com.vng.zalopay.webapp;
public final class f implements qm.e, XQ.s {
    public static final java.util.HashMap c;
    public final XQ.d a;
    public qm.g b;

    static f()
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        vn.com.vng.zalopay.webapp.f.c = v0_1;
        v0_1.put("scanQRCode", Integer.valueOf(1));
        return;
    }

    public f(androidx.work.impl.utils.e p1)
    {
        this.a = p1;
        return;
    }

    public final void a(String p5, org.json.JSONObject p6, qm.g p7)
    {
        if (!android.text.TextUtils.isEmpty(p5)) {
            int v5_5 = ((Integer) vn.com.vng.zalopay.webapp.f.c.get(p5));
            if (v5_5 != 0) {
                int v0_1 = 1;
                if (v5_5.intValue() == 1) {
                    p6.optString("desc");
                    int v5_3 = p6.optInt("needResult");
                    XQ.d v6_1 = p6.optString("scanType");
                    if ((!v6_1.contains("qrCode")) || (v6_1.contains("barCode"))) {
                        if ((!v6_1.contains("barCode")) || (v6_1.contains("qrCode"))) {
                            v0_1 = 3;
                        } else {
                            v0_1 = 2;
                        }
                    } else {
                        UR.a.c(13222, java.util.Calendar.getInstance().getTime().toString(), 0, 0);
                    }
                    this.a.i(v5_3, v0_1);
                    this.b = p7;
                }
            }
        }
        return;
    }

    public final String[] b()
    {
        String[] v1_1 = new String[0];
        return ((String[]) vn.com.vng.zalopay.webapp.f.c.keySet().toArray(v1_1));
    }
}
