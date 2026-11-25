package vn.com.vng.zalopay.webapp;
public final class b implements qm.e {
    public static final java.util.HashMap b;
    public final XQ.b a;

    static b()
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        vn.com.vng.zalopay.webapp.b.b = v0_1;
        androidx.compose.foundation.layout.G0.s(1, v0_1, "promotionEvent", 2, "launchExternalApp");
        androidx.compose.foundation.layout.G0.s(3, v0_1, "launchDeeplink", 4, "launchPromotionWebApp");
        return;
    }

    public b(Tb.c p1)
    {
        this.a = p1;
        return;
    }

    public final void a(String p9, org.json.JSONObject p10, qm.g p11)
    {
        if (!android.text.TextUtils.isEmpty(p9)) {
            String v0_8 = ((Integer) vn.com.vng.zalopay.webapp.b.b.get(p9));
            if (v0_8 != null) {
                android.os.Bundle v9_1 = v0_8.intValue();
                boolean v2 = 1;
                XQ.b v4 = this.a;
                if (v9_1 == 1) {
                    android.os.Bundle v9_4 = p10.optString("packageId");
                    String v0_3 = p10.optString("alternateUrl");
                    String v10_2 = p10.optInt("internalApp");
                    if (android.text.TextUtils.isEmpty(v9_4)) {
                        if (v10_2 <= null) {
                            if (!android.text.TextUtils.isEmpty(v0_3)) {
                                v4.d(v0_3);
                            }
                        } else {
                            v4.e(((long) v10_2));
                        }
                    } else {
                        v4.f(v9_4);
                    }
                    p11.f(0);
                    return;
                } else {
                    if (v9_1 == 2) {
                        android.os.Bundle v9_7 = p10.optString("url");
                        String v10_3 = p10.optString("packageId");
                        if (!android.text.TextUtils.isEmpty(v9_7)) {
                            v4.a(v9_7, v10_3);
                            p11.f(0);
                            return;
                        } else {
                            p11.a(2, "Invalid parameter");
                            return;
                        }
                    } else {
                        if (v9_1 == 3) {
                            android.os.Bundle v9_9 = p10.optString("url");
                            if (!android.text.TextUtils.isEmpty(v9_9)) {
                                if (!com.google.android.gms.internal.mlkit_vision_barcode.l0.c(v9_9)) {
                                    v4.a(v9_9, "");
                                    p11.f(0);
                                    return;
                                } else {
                                    v4.h(v9_9);
                                    p11.f(0);
                                    return;
                                }
                            } else {
                                p11.a(2, "Url is empty");
                                return;
                            }
                        } else {
                            if (v9_1 == 4) {
                                String v6_1 = java.net.URLDecoder.decode(p10.optString("url"), "UTF-8");
                                android.os.Bundle v9_12 = p10.optJSONObject("options");
                                if (!android.text.TextUtils.isEmpty(v6_1)) {
                                    if (v9_12 != null) {
                                        v2 = v9_12.optBoolean("showShareButton", 1);
                                    }
                                    android.os.Bundle v9_14 = new android.os.Bundle();
                                    v9_14.putString("url", v6_1);
                                    v9_14.putBoolean("show_share_button", v2);
                                    v4.c(v9_14);
                                    p11.f(0);
                                } else {
                                    p11.a(2, "Url is empty");
                                }
                            }
                            return;
                        }
                    }
                }
            } else {
                String v10_1 = new StringBuilder("Cannot handle function: ");
                v10_1.append(p9);
                p11.a(2, v10_1.toString());
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
        return ((String[]) vn.com.vng.zalopay.webapp.b.b.keySet().toArray(v1_1));
    }
}
