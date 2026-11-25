package vn.ai.trueid.sdk.networks;
public final class f0 implements vn.ai.trueid.sdk.networks.k {
    public final synthetic android.content.Context a;
    public final synthetic String b;
    public final synthetic kotlin.jvm.internal.Lambda c;

    public f0(android.content.Context p1, String p2, kotlin.jvm.functions.Function1 p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = ((kotlin.jvm.internal.Lambda) p3);
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p3)
    {
        vn.ai.trueid.sdk.utils.SmartOTPHelper.r(this.a, 3600, this.b);
        this.c.invoke(0);
        return;
    }

    public final void success(org.json.JSONObject p7)
    {
        int v0_2 = p7.optInt("result", -1);
        int v7_5 = p7.optJSONObject("data");
        int v1_1 = 0;
        if (v7_5 != 0) {
            v1_1 = v7_5.optInt("cooldownSeconds", 0);
        }
        if (v1_1 <= 0) {
            v1_1 = 3600;
        }
        kotlin.jvm.internal.Lambda v3 = this.c;
        String v4 = this.b;
        android.content.Context v5 = this.a;
        if (v0_2 != 1) {
            vn.ai.trueid.sdk.utils.SmartOTPHelper.r(v5, 3600, v4);
            v3.invoke(0);
            return;
        } else {
            vn.ai.trueid.sdk.utils.SmartOTPHelper.r(v5, v1_1, v4);
            vn.ai.trueid.sdk.utils.Helpers.Companion.printLog(new Object[] {"SmartOtpClient", androidx.camera.camera2.internal.G.d(v1_1, "\u1f512 SOTP service locked (factor=PIN, cooldown=", "s)")}));
            v3.invoke(Integer.valueOf(v1_1));
            return;
        }
    }
}
