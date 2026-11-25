package vn.ai.trueid.sdk.navigation;
public final class TrueIDV3$handleSmartOtpPush$1 implements vn.ai.trueid.sdk.networks.V {
    public final synthetic android.app.Activity a;
    public final synthetic String b;
    public final synthetic String c;
    public final synthetic String d;

    public TrueIDV3$handleSmartOtpPush$1(android.app.Activity p1, String p2, String p3, String p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public void fail(vn.ai.trueid.sdk.models.TrueError p7)
    {
        int v0_2 = new vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult(0, 0, 0, 7, 0);
        v0_2.setCode(p7.getCode());
        v0_2.setErrorMessage(p7.getMessage());
        vn.ai.trueid.sdk.navigation.TrueIDV3 v7_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener v1_0 = v7_2.getActivePushListener$trueid_release();
        if (v1_0 != null) {
            v1_0.onComplete(v0_2);
        }
        v7_2.setActivePushListener$trueid_release(0);
        return;
    }

    public void success(org.json.JSONObject p8)
    {
        android.app.Activity v1 = this.a;
        v1.runOnUiThread(new B6.a(v1, this.b, this.c, this.d, vn.ai.trueid.sdk.networks.h0.a, 3));
        return;
    }
}
