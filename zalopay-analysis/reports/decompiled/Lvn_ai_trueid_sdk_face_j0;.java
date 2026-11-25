package vn.ai.trueid.sdk.face;
public final class j0 {
    public final android.app.Application a;
    public final vn.ai.trueid.sdk.face.navigation.FaceSDK b;

    public j0(android.app.Application p1)
    {
        this.a = p1;
        this.b = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
        return;
    }

    public static vn.ai.trueid.sdk.face.c0 a(vn.ai.trueid.sdk.face.j0 p1)
    {
        return new vn.ai.trueid.sdk.face.c0(p1.b());
    }

    public static vn.ai.trueid.sdk.face.q0 a()
    {
        return new vn.ai.trueid.sdk.face.q0();
    }

    public final vn.ai.trueid.sdk.face.c1 b()
    {
        return new vn.ai.trueid.sdk.face.c1(vn.ai.trueid.sdk.face.present.presentation.utils.LocaleHelper.INSTANCE.onAttach(this.a.getApplicationContext()));
    }
}
