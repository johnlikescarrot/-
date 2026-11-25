package vn.ai.trueid.sdk.navigation;
public final class FaceLiveness$startFaceLiveness$2 implements vn.ai.trueid.sdk.networks.L {
    public final synthetic android.content.Context a;
    public final synthetic vn.ai.trueid.sdk.navigation.FaceLivenessListener b;

    public FaceLiveness$startFaceLiveness$2(android.content.Context p1, vn.ai.trueid.sdk.navigation.FaceLivenessListener p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public void fail(vn.ai.trueid.sdk.models.TrueError p9)
    {
        this.b.onComplete(new vn.ai.trueid.sdk.models.FaceLivenessResult(p9.getCode(), p9.getMessage(), 0, 0, 0, 28, 0));
        return;
    }

    public void success(String p2)
    {
        p2 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE;
        android.content.Context v0 = this.a;
        vn.ai.trueid.sdk.navigation.FaceLiveness.access$convertConfig(p2, v0);
        vn.ai.trueid.sdk.navigation.FaceLiveness.access$startFaceSDK(p2, v0);
        return;
    }
}
