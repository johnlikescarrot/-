package vn.ai.trueid.sdk.networks;
public final class Z implements vn.ai.trueid.sdk.networks.k {
    public final synthetic vn.ai.trueid.sdk.navigation.TrueIDV3$generateOtpOCRA$1 a;
    public final synthetic android.content.Context b;
    public final synthetic String c;

    public Z(vn.ai.trueid.sdk.navigation.TrueIDV3$generateOtpOCRA$1 p1, android.content.Context p2, String p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p2)
    {
        this.a.fail(p2);
        return;
    }

    public final void success(org.json.JSONObject p6)
    {
        vn.ai.trueid.sdk.navigation.TrueIDV3$generateOtpOCRA$1 v0 = this.a;
        String v2_4 = vn.ai.trueid.sdk.utils.j.h("data", p6);
        if (v2_4 != null) {
            String v2_7 = v2_4.optString("challenge", "");
            vn.ai.trueid.sdk.utils.SmartOTPHelper.m(this.b);
            String v2_0 = vn.ai.trueid.sdk.utils.SmartOTPHelper.f(v2_7, this.c);
            if (v2_0 != null) {
                p6.put("responseCode", v2_0);
                v0.success(p6);
                return;
            } else {
                v0.fail(new vn.ai.trueid.sdk.models.TrueError(500, "Failed to generate OTP"));
                return;
            }
        } else {
            v0.fail(vn.ai.trueid.sdk.models.TrueError.Companion.getDATA_ERROR());
            return;
        }
    }
}
