package vn.ai.trueid.sdk.navigation;
public final class FaceLiveness$startFaceSDK$1 implements wk.b {

    public static final void access$closeWithByHTTPCode(vn.ai.trueid.sdk.navigation.FaceLiveness$startFaceSDK$1 p3, vn.ai.trueid.sdk.models.TrueError p4)
    {
        p3.getClass();
        vn.ai.trueid.sdk.navigation.FaceLiveness.access$removeTimeOut(vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE);
        android.os.Handler v3_2 = wk.a.j;
        if (v3_2 != null) {
            v3_2.invoke();
        }
        vn.ai.trueid.sdk.navigation.FaceLiveness.access$getHandler$p().postDelayed(new Kk.b(p4, 1), 650);
        return;
    }

    public void complete(zk.a p5)
    {
        java.util.List v0_0 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE;
        v0_0.setCount((v0_0.getCount() + 1));
        if (!((java.util.Collection) p5.c).isEmpty()) {
            v0_0.setCurImages(p5.c);
        }
        if ((p5.a != 1) || (v0_0.getCurImages() == null)) {
            vn.ai.trueid.sdk.navigation.FaceLiveness.access$closeWithErrorCode(v0_0, p5);
            return;
        } else {
            java.util.List v5_5;
            if (!v0_0.getSDKConfig$trueid_release().isRecordFace()) {
                v5_5 = v0_0.getCurImages();
            } else {
                java.util.List v5_4;
                java.util.List v5_3 = v0_0.getCurImages();
                if (v5_3 == null) {
                    v5_4 = 0;
                } else {
                    v5_4 = v5_3.size();
                }
                if (v5_4 <= 1) {
                } else {
                    v5_5 = kotlin.collections.CollectionsKt.dropLast(v0_0.getCurImages(), 1);
                }
            }
            vn.ai.trueid.sdk.networks.J.b(new vn.ai.trueid.sdk.networks.H(kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("faceImages", v5_5)), v0_0.getLsDurationIn(), v0_0.getLsDurationOut(), new vn.ai.trueid.sdk.navigation.FaceLiveness$startFaceSDK$1$complete$1(this)));
            return;
        }
    }
}
