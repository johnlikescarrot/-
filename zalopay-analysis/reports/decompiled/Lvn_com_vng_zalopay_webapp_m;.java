package vn.com.vng.zalopay.webapp;
public final class m implements qm.e, kotlinx.coroutines.CoroutineScope {
    public static final java.util.HashMap a;

    static m()
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        vn.com.vng.zalopay.webapp.m.a = v0_1;
        v0_1.put("postData", Integer.valueOf(1));
        return;
    }

    public final void a(String p7, org.json.JSONObject p8, qm.g p9)
    {
        li.e v0_3 = ((Integer) vn.com.vng.zalopay.webapp.m.a.get(p7));
        if (v0_3 != null) {
            if (v0_3.intValue() == 1) {
                kotlinx.coroutines.BuildersKt.launch$default(gi.L.a(kotlinx.coroutines.Dispatchers.getIO()), 0, 0, new pI.c(p8.optString("rating"), p8.optString("rate_number"), 0, 20), 3, 0);
            }
            return;
        } else {
            p9.a(2, String.format("Cannot handle function: %s", java.util.Arrays.copyOf(new Object[] {p7}), 1)));
            return;
        }
    }

    public final String[] b()
    {
        String[] v1_1 = new String[0];
        return ((String[]) ((java.util.Collection) vn.com.vng.zalopay.webapp.m.a.keySet()).toArray(v1_1));
    }

    public final kotlin.coroutines.CoroutineContext getCoroutineContext()
    {
        return kotlinx.coroutines.Dispatchers.getIO();
    }
}
