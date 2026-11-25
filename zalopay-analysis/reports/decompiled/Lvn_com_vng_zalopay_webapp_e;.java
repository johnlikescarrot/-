package vn.com.vng.zalopay.webapp;
public final class e implements qm.e {
    public static final java.util.HashMap b;
    public final XQ.c a;

    static e()
    {
        java.util.HashMap v0_1 = new java.util.HashMap();
        vn.com.vng.zalopay.webapp.e.b = v0_1;
        androidx.compose.foundation.layout.G0.s(1, v0_1, "payOrder", 2, "transferMoney");
        androidx.compose.foundation.layout.G0.s(3, v0_1, "confirmBinding", 4, "payOrderV2");
        androidx.compose.foundation.layout.G0.s(5, v0_1, "updateResultStatus", 6, "renderResultCustomArea");
        androidx.compose.foundation.layout.G0.s(7, v0_1, "renderResultExtraButton", 13, "scanqr");
        androidx.compose.foundation.layout.G0.s(12, v0_1, "setupQuickPay", 8, "quickpayBioPopup");
        androidx.compose.foundation.layout.G0.s(9, v0_1, "quickPayKycPopup", 10, "executeResultAction");
        androidx.compose.foundation.layout.G0.s(11, v0_1, "topup", 14, "executeCashierAction");
        androidx.compose.foundation.layout.G0.s(15, v0_1, "startCashier", 16, "closeVietQRScreen");
        androidx.compose.foundation.layout.G0.s(17, v0_1, "customBlockResize", 18, "receiveInstallmentData");
        return;
    }

    public e(XQ.i p1)
    {
        this.a = p1;
        return;
    }

    public final void a(String p3, org.json.JSONObject p4, qm.g p5)
    {
        if (!android.text.TextUtils.isEmpty(p3)) {
            int v0_4 = ((Integer) vn.com.vng.zalopay.webapp.e.b.get(p3));
            if (v0_4 != 0) {
                XQ.c v1_0 = this.a;
                switch (v0_4.intValue()) {
                    case 1:
                        v1_0.h(p4, p5);
                        return;
                    case 2:
                        v1_0.l(p4, new lO.o(19, p5));
                        return;
                    case 3:
                        v1_0.p(p4, p5);
                        return;
                    case 4:
                        v1_0.j(p4, p5);
                        return;
                    case 5:
                        v1_0.w(p4.optInt("status"), p4.optString("message"));
                        return;
                    case 6:
                        v1_0.d(p4.optString("url"), Double.valueOf(p4.optDouble("ratio")));
                        return;
                    case 7:
                        v1_0.t(p4.optString("title"), p4.optString("deeplink"));
                        return;
                    case 8:
                        v1_0.s(p4, p5);
                        return;
                    case 9:
                        v1_0.v(p4, p5);
                        return;
                    case 10:
                        v1_0.x(p4.optString("action"), p4, p5);
                        return;
                    case 11:
                        v1_0.c(p4, p5);
                        return;
                    case 12:
                        v1_0.z(p4, p5);
                        return;
                    case 13:
                        v1_0.n(p4, p5);
                        return;
                    case 14:
                        v1_0.y(p4.optString("action"), p4, p5);
                        return;
                    case 15:
                        v1_0.g(p4, p5);
                        return;
                    case 16:
                        v1_0.e();
                        return;
                    case 17:
                        v1_0.i();
                        return;
                    case 18:
                        v1_0.k(p4, p5);
                        return;
                    default:
                        return;
                }
            } else {
                String v4_6 = new StringBuilder("Cannot handle function: ");
                v4_6.append(p3);
                p5.a(2, v4_6.toString());
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
        return ((String[]) vn.com.vng.zalopay.webapp.e.b.keySet().toArray(v1_1));
    }
}
