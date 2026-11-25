package vn.com.vng.zalopay.webapp;
public final class j implements qm.e {
    public static final java.util.HashMap a;

    static j()
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        vn.com.vng.zalopay.webapp.j.a = v0_1;
        androidx.compose.foundation.layout.G0.s(1, v0_1, "submitAnalyticsEvent", 2, "trackScreen");
        v0_1.put("submitEventTracking", Integer.valueOf(3));
        return;
    }

    public final void a(String p10, org.json.JSONObject p11, qm.g p12)
    {
        if (!android.text.TextUtils.isEmpty(p10)) {
            Class v0_9 = ((Integer) vn.com.vng.zalopay.webapp.j.a.get(p10));
            if (v0_9 != null) {
                boolean v10_1 = v0_9.intValue();
                try {
                    if (v10_1 == 1) {
                        int v4 = p11.optInt("eventid");
                        String v3 = p11.optString("category");
                        String v6 = p11.optString("action");
                        String v7 = p11.optString("label");
                        String v8 = p11.optString("stringvalue");
                        int v5 = p11.optInt("numbervalue");
                        if ((v4 >= 1) && ((!android.text.TextUtils.isEmpty(v3)) && (!android.text.TextUtils.isEmpty(v6)))) {
                            UR.a.f(v3, v4, v5, v6, v7, v8);
                            p12.f(0);
                        }
                    } else {
                        if (v10_1 == 2) {
                            boolean v10_11 = p11.optString("screenName");
                            if (!android.text.TextUtils.isEmpty(v10_11)) {
                                UR.a.i(v10_11);
                                p12.f(0);
                                return;
                            }
                        } else {
                            if (v10_1 == 3) {
                                boolean v10_13 = p11.optString("eventId");
                                java.util.HashMap v11_2 = p11.optString("metaData");
                                if (!android.text.TextUtils.isEmpty(v10_13)) {
                                    if (!android.text.TextUtils.isEmpty(v11_2)) {
                                        UR.a.e(v10_13, ((java.util.HashMap) nY.h.b(v11_2, java.util.HashMap)));
                                    } else {
                                        UR.a.d(v10_13);
                                    }
                                    p12.f(0);
                                    return;
                                }
                            }
                        }
                    }
                } catch (Exception) {
                }
                return;
            } else {
                java.util.HashMap v11_6 = new StringBuilder("Cannot handle function: ");
                v11_6.append(p10);
                p12.a(2, v11_6.toString());
                return;
            }
        } else {
            p12.a(2, "empty function");
            return;
        }
    }

    public final String[] b()
    {
        String[] v1_1 = new String[0];
        return ((String[]) vn.com.vng.zalopay.webapp.j.a.keySet().toArray(v1_1));
    }
}
