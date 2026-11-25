package vn.ai.trueid.sdk.navigation;
public final class TrueIDV3$generateOtpOCRA$1 implements vn.ai.trueid.sdk.networks.V {
    public final synthetic vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult a;
    public final synthetic vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener b;

    public TrueIDV3$generateOtpOCRA$1(vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult p1, vn.ai.trueid.sdk.navigation.TrueIdSmartOTPListener p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public void fail(vn.ai.trueid.sdk.models.TrueError p3)
    {
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult v1 = this.a;
        v1.setCode(p3.getCode());
        v1.setErrorMessage(p3.getMessage());
        this.b.onComplete(v1);
        return;
    }

    public void success(org.json.JSONObject p2)
    {
        vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPResult v0 = this.a;
        v0.setResult(p2.toString());
        this.b.onComplete(v0);
        return;
    }
}
