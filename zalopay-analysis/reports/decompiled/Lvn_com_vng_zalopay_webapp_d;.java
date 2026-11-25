package vn.com.vng.zalopay.webapp;
public final class d implements qm.e, kotlinx.coroutines.CoroutineScope {
    public static final java.util.HashMap a;

    static d()
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        vn.com.vng.zalopay.webapp.d.a = v0_1;
        v0_1.put("writeLog", Integer.valueOf(1));
        return;
    }

    public final void a(String p5, org.json.JSONObject p6, qm.g p7)
    {
        if (!android.text.TextUtils.isEmpty(p5)) {
            long v0_4 = ((Integer) vn.com.vng.zalopay.webapp.d.a.get(p5));
            if (v0_4 != 0) {
                if (v0_4.intValue() == 1) {
                    ck.a v5_3 = p6.optString("type");
                    long v0_1 = p6.optLong("time");
                    Object[] v6_1 = p6.optString("data");
                    if (v5_3 != null) {
                        Long v7_3 = v5_3.hashCode();
                        if (v7_3 == 3237038) {
                            v5_3.equals("info");
                        } else {
                            if (v7_3 == 3641990) {
                                if (v5_3.equals("warn")) {
                                    ck.b.e("time: %s, data: %s", new Object[] {Long.valueOf(v0_1), v6_1}));
                                    return;
                                }
                            } else {
                                if ((v7_3 == 96784904) && (v5_3.equals("error"))) {
                                    ck.b.d("WEB_APP_LOG_NATIVE_MODULE_TAG").c("time: %s, data: %s", new Object[] {Long.valueOf(v0_1), v6_1}));
                                    return;
                                }
                            }
                        }
                    }
                }
                return;
            } else {
                p7.a(2, String.format("Cannot handle function: %s", java.util.Arrays.copyOf(new Object[] {p5}), 1)));
                return;
            }
        } else {
            p7.a(2, "empty function");
            return;
        }
    }

    public final String[] b()
    {
        String[] v1_1 = new String[0];
        return ((String[]) ((java.util.Collection) vn.com.vng.zalopay.webapp.d.a.keySet()).toArray(v1_1));
    }

    public final kotlin.coroutines.CoroutineContext getCoroutineContext()
    {
        return kotlinx.coroutines.Dispatchers.getIO();
    }
}
