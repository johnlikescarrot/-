package vn.ai.trueid.sdk.face;
public final class u extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function0 {
    public final synthetic vn.ai.trueid.sdk.face.a0 a;

    public u(vn.ai.trueid.sdk.face.a0 p1)
    {
        this.a = p1;
        super(0);
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a0 p0)
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
            android.os.Handler v0_7 = this.a;
            android.util.Log.e(v0_7.b, "show LayoutSuccessScan");
            v0_7.c.l.setVisibility(8);
            v0_7.c.c.setVisibility(8);
            v0_7.c.b.setVisibility(8);
            v0_7.c.d.setVisibility(8);
            v0_7.c.n.setVisibility(8);
            v0_7.c.f.setVisibility(8);
            v0_7.c.i.setVisibility(8);
            v0_7.c.h.setVisibility(0);
        }
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new vn.ai.trueid.sdk.face.r(this.a, 4), 100);
        return;
    }

    public final bridge synthetic Object invoke()
    {
        this.a();
        return kotlin.Unit.INSTANCE;
    }
}
