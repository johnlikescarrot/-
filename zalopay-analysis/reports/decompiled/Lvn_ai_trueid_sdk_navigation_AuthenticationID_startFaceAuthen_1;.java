package vn.ai.trueid.sdk.navigation;
public final class AuthenticationID$startFaceAuthen$1 implements vn.ai.trueid.sdk.networks.f {
    public final synthetic android.content.Context a;
    public final synthetic vn.ai.trueid.sdk.models.FaceAuthenInput b;
    public final synthetic vn.ai.trueid.sdk.navigation.AuthenListener c;

    public AuthenticationID$startFaceAuthen$1(android.content.Context p1, vn.ai.trueid.sdk.models.FaceAuthenInput p2, vn.ai.trueid.sdk.navigation.AuthenListener p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        return;
    }

    public void fail(vn.ai.trueid.sdk.models.TrueError p9)
    {
        this.c.onComplete(new vn.ai.trueid.sdk.models.FaceAuthenResult(p9.getCode(), p9.getMessage(), 0, 0, fk.b.p, 12, 0));
        return;
    }

    public void success(String p3)
    {
        p3 = vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE;
        android.content.Context v0 = this.a;
        vn.ai.trueid.sdk.navigation.AuthenticationID.access$convertConfig(p3, v0);
        vn.ai.trueid.sdk.navigation.AuthenticationID.access$startFaceSDK(p3, v0, this.b);
        return;
    }
}
