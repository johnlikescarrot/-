package vn.com.vng.zalopay.webapp;
public final class i implements qm.e {
    public static final java.util.Map b;
    public final XQ.f a;

    static i()
    {
        vn.com.vng.zalopay.webapp.i.b = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("downloadImage", Integer.valueOf(1))}));
        return;
    }

    public i(WQ.e p1)
    {
        this.a = p1;
        return;
    }

    public final void a(String p9, org.json.JSONObject p10, qm.g p11)
    {
        if (!android.text.TextUtils.isEmpty(p9)) {
            nK.F v0_7 = ((Integer) vn.com.vng.zalopay.webapp.i.b.get(p9));
            if (v0_7 != null) {
                if (v0_7.intValue() == 1) {
                    int v4_1;
                    if (p10 == null) {
                        v4_1 = "url";
                    } else {
                        v4_1 = p10.optString("type");
                        if (v4_1 == 0) {
                        }
                    }
                    int v4_2 = v4_1.toLowerCase(java.util.Locale.getDefault());
                    int v5_1 = vn.com.vng.zalopay.webapp.h.a;
                    if ((!kotlin.jvm.internal.Intrinsics.areEqual(v4_2, "url")) && (kotlin.jvm.internal.Intrinsics.areEqual(v4_2, "base64"))) {
                        v5_1 = vn.com.vng.zalopay.webapp.h.b;
                    }
                    int v4_4 = v5_1.ordinal();
                    String v6_2 = 0;
                    if (v4_4 == 0) {
                        if (p10 != null) {
                            v6_2 = p10.optString("url");
                        }
                    } else {
                        if (v4_4 != 1) {
                            throw new kotlin.NoWhenBranchMatchedException();
                        } else {
                            if (p10 != null) {
                                v6_2 = p10.optString("base64");
                            }
                        }
                    }
                    if ((v6_2 != null) && (v6_2.length() != 0)) {
                        this.a.b(v6_2, v5_1, new com.google.android.gms.internal.firebase-perf.z(new nK.F(this, p9, p11), new mS.a(this, p11), 25));
                        return;
                    } else {
                        com.google.android.gms.internal.firebase-perf.z v10_5 = new StringBuilder("Cannot handle function: ");
                        v10_5.append(p9);
                        p11.a(2, v10_5.toString());
                    }
                }
                return;
            } else {
                com.google.android.gms.internal.firebase-perf.z v10_7 = new StringBuilder("Cannot handle function: ");
                v10_7.append(p9);
                p11.a(2, v10_7.toString());
                return;
            }
        } else {
            p11.a(2, "empty function");
            return;
        }
    }

    public final String[] b()
    {
        String[] v1_1 = new String[0];
        return ((String[]) ((java.util.Collection) vn.com.vng.zalopay.webapp.i.b.keySet()).toArray(v1_1));
    }
}
