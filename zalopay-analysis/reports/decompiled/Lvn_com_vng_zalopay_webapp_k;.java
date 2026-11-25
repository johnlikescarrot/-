package vn.com.vng.zalopay.webapp;
public final class k implements qm.e {
    public static final java.util.HashMap e;
    public final long a;
    public final Pt.c b;
    public final Pt.n c;
    public final XQ.g d;

    static k()
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        vn.com.vng.zalopay.webapp.k.e = v0_1;
        androidx.compose.foundation.layout.G0.s(1, v0_1, "showLoading", 2, "hideLoading");
        androidx.compose.foundation.layout.G0.s(3, v0_1, "showDialog", 8, "showToast");
        androidx.compose.foundation.layout.G0.s(4, v0_1, "setProperty", 5, "setToolbarActions");
        androidx.compose.foundation.layout.G0.s(6, v0_1, "pushView", 7, "closeWindow");
        androidx.compose.foundation.layout.G0.s(9, v0_1, "showTooltip", 11, "register");
        androidx.compose.foundation.layout.G0.s(13, v0_1, "displayCloseButton", 14, "putExtraIntent");
        return;
    }

    public k(long p1, Pt.c p3, Pt.n p4, XQ.k p5)
    {
        this.a = p1;
        this.b = p3;
        this.c = p4;
        this.d = p5;
        return;
    }

    public final void a(String p10, org.json.JSONObject p11, qm.g p12)
    {
        if (!android.text.TextUtils.isEmpty(p10)) {
            int v0_13 = ((Integer) vn.com.vng.zalopay.webapp.k.e.get(p10));
            if (v0_13 != 0) {
                int v0_1 = 0;
                XQ.g v4 = this.d;
                switch (v0_13.intValue()) {
                    case 1:
                        v4.showLoading();
                        p12.f(0);
                        return;
                    case 2:
                        v4.hideLoading();
                        p12.f(0);
                        return;
                    case 3:
                        v4.v(p11.optString("message"), p11.optString("button").split(","), p12);
                        return;
                    case 4:
                        try {
                            boolean v5_1 = p11.getJSONObject("navigation");
                            v4.w(v5_1.optString("backgroundColor"), v5_1.optBoolean("pullToRefresh"), v5_1.optString("titleColor"), v5_1.optBoolean("hidden"));
                        } catch (String v10_8) {
                            v10_8.getMessage();
                            p12.f(0);
                        } catch (String v10_9) {
                            p12.f(0);
                            throw v10_9;
                        }
                        if (!android.text.TextUtils.isEmpty(p11.optString("statusBar"))) {
                            String v10_6 = p11.getJSONObject("statusBar");
                            boolean v11_6 = v10_6.optString("backgroundColor");
                            String v10_7 = v10_6.optBoolean("hidden", 0);
                            if (!android.text.TextUtils.isEmpty(v11_6)) {
                                v4.a(v11_6, v10_7);
                            } else {
                            }
                        } else {
                        }
                        break;
                    case 5:
                        String v10_4 = new java.util.ArrayList();
                        boolean v11_1 = p11.get("data");
                        if (((v11_1 instanceof org.json.JSONArray)) || ((v11_1 instanceof String))) {
                            boolean v11_3;
                            if (!(v11_1 instanceof org.json.JSONArray)) {
                                v11_3 = new org.json.JSONArray(((String) v11_1));
                            } else {
                                v11_3 = ((org.json.JSONArray) v11_1);
                            }
                            while (v0_1 < v11_3.length()) {
                                v10_4.add(((vn.com.vng.zalopay.domain.webapp.model.WebInternalActionModel) nY.h.b(v11_3.getJSONObject(v0_1).toString(), new vn.com.vng.zalopay.webapp.WebAppUINativeModule$1().getType())));
                                v0_1++;
                            }
                            if (!com.google.android.gms.internal.mlkit_vision_barcode.p0.b(v10_4)) {
                                v4.b(v10_4, p12);
                            } else {
                                p12.f(0);
                            }
                        } else {
                        }
                        break;
                    case 6:
                        v4.p(p11.optString("url"));
                        p12.f(0);
                        return;
                    case 7:
                        v4.closeWindow();
                        p12.f(0);
                        return;
                    case 8:
                        v4.t(p11.optString("message"));
                        p12.f(0);
                        return;
                    case 9:
                        p11.optInt("position");
                        p11.optString("message");
                        p11.optString("iconLink");
                        p11.optString("iconName");
                        boolean v11_13 = this.c;
                        vn.com.vng.zalopay.domain.webapp.model.WebInternalActionModel v1_13 = this.a;
                        String v6_1 = this.b;
                        String v10_23 = v6_1.a(String.format(java.util.Locale.getDefault(), "%s_%d_ToolTip", new Object[] {v11_13.getUserId(), Long.valueOf(v1_13)})), "");
                        if ((!android.text.TextUtils.isEmpty(v10_23)) && (v1_13 == Long.parseLong(v10_23))) {
                            p12.f(0);
                            return;
                        } else {
                            v4.u();
                            if (v1_13 >= 0) {
                                v6_1.put(String.format(java.util.Locale.getDefault(), "%s_%d_ToolTip", new Object[] {v11_13.getUserId(), Long.valueOf(v1_13)})), String.valueOf(v1_13));
                            }
                            p12.f(0);
                            return;
                        }
                    case 10:
                    case 12:
                    default:
                        break;
                    case 11:
                        v4.r(p11.optString("event"));
                        return;
                    case 13:
                        v4.s(p11.optBoolean("isShown"), p12);
                        return;
                    case 14:
                        String v10_10 = p11.optJSONObject("data");
                        int vtmp25 = p11.optInt("code");
                        if (v10_10 != null) {
                            v4.q(v10_10, Integer.valueOf(vtmp25));
                        }
                        p12.f(0);
                        return;
                }
                return;
            } else {
                boolean v11_10 = new StringBuilder("Cannot handle function: ");
                v11_10.append(p10);
                p12.a(2, v11_10.toString());
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
        return ((String[]) vn.com.vng.zalopay.webapp.k.e.keySet().toArray(v1_1));
    }
}
