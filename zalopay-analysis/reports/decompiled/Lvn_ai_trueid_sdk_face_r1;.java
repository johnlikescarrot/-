package vn.ai.trueid.sdk.face;
public final class r1 extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function0 {
    public static final vn.ai.trueid.sdk.face.r1 a;

    public static r1()
    {
        vn.ai.trueid.sdk.face.r1.a = new vn.ai.trueid.sdk.face.r1();
        return;
    }

    public r1()
    {
        super(0);
        return;
    }

    public final Object invoke()
    {
        vn.ai.trueid.sdk.face.p0 v0_1 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getLivenessConfig$face_release();
        if (v0_1 == null) {
            v0_1 = new vn.ai.trueid.sdk.face.p0();
        }
        return v0_1;
    }
}
