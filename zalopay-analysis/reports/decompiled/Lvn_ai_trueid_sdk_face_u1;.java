package vn.ai.trueid.sdk.face;
public final class u1 extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function0 {
    public final synthetic vn.ai.trueid.sdk.face.a2 a;

    public u1(vn.ai.trueid.sdk.face.a2 p1)
    {
        this.a = p1;
        super(0);
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a2 p0)
    {
        androidx.fragment.app.FragmentActivity v0_1 = p0.getActivity();
        if (v0_1 != null) {
            v0_1.finish();
        }
        return;
    }

    public final void a()
    {
        if (vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE.getConfig$face_release().isShowLivenessSuccess()) {
            this.a.g();
        }
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new vn.ai.trueid.sdk.face.D(this.a, 6), 100);
        return;
    }

    public final bridge synthetic Object invoke()
    {
        this.a();
        return kotlin.Unit.INSTANCE;
    }
}
