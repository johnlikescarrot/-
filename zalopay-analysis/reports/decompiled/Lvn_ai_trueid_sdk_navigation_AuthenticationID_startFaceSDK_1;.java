package vn.ai.trueid.sdk.navigation;
public final class AuthenticationID$startFaceSDK$1 implements wk.b {
    public final synthetic vn.ai.trueid.sdk.models.FaceAuthenInput a;

    public AuthenticationID$startFaceSDK$1(vn.ai.trueid.sdk.models.FaceAuthenInput p1)
    {
        this.a = p1;
        return;
    }

    public static final void access$closeWithByHTTPCode(vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceSDK$1 p3, vn.ai.trueid.sdk.models.TrueError p4)
    {
        p3.getClass();
        vn.ai.trueid.sdk.navigation.AuthenticationID.access$removeTimeOut(vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE);
        fk.c.c(p4.getCode(), 7, 0);
        android.os.Handler v3_4 = wk.a.j;
        if (v3_4 != null) {
            v3_4.invoke();
        }
        vn.ai.trueid.sdk.navigation.AuthenticationID.access$getHandler$p().postDelayed(new Kk.b(p4, 0), 650);
        return;
    }

    public void complete(zk.a p30)
    {
        java.util.List v2_1 = vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE;
        v2_1.setCount((v2_1.getCount() + 1));
        vn.ai.trueid.sdk.networks.b v5_13 = p30.c;
        String v6_0 = p30.d;
        int v7 = 0;
        if ((p30.a == 1) && (v6_0.size() >= 2)) {
            String v9_1 = new StringBuilder("validConfig : faceSDKResult - ");
            v9_1.append(v6_0);
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {v9_1.toString()}));
            v2_1.getLsDurationIn().add(v6_0.get(0));
            v2_1.getLsDurationOut().add(v6_0.get(1));
        }
        if (!((java.util.Collection) v5_13).isEmpty()) {
            v2_1.setCurImages(v5_13);
        }
        if ((v2_1.getCurImages() != null) && (v2_1.getCurImages().size() >= 3)) {
            java.util.List v3_15 = vn.ai.trueid.sdk.utils.Helpers.Companion;
            v3_15.errorLog(new Object[] {androidx.activity.result.e.k(v2_1.getCurImages().get(1), "Selfie 2 Base64: ")}));
            v3_15.errorLog(new Object[] {androidx.activity.result.e.k(v2_1.getCurImages().get(2), "Selfie 3 Base64: ")}));
        }
        if ((p30.a != 1) || (v2_1.getCurImages() == null)) {
            vn.ai.trueid.sdk.navigation.AuthenticationID.access$closeWithErrorCode(v2_1, p30);
            return;
        } else {
            java.util.List v1_1 = wk.a.c;
            if (v1_1 == null) {
                v1_1 = new yk.b("#26B840", "#42526E", "#26B840", 0, "#FF6234", "#000000", "#253858", "#253858", "#FFFFFF", 0, 1, 1, "", 0, 0, 0, 0, 0, 0, 8126472);
            }
            java.util.List v1_4;
            if (!v1_1.s) {
                v1_4 = v2_1.getCurImages();
            } else {
                java.util.List v1_3 = v2_1.getCurImages();
                if (v1_3 != null) {
                    v7 = v1_3.size();
                }
                if (v7 <= 1) {
                } else {
                    v1_4 = kotlin.collections.CollectionsKt.dropLast(v2_1.getCurImages(), 1);
                }
            }
            vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceSDK$1$complete$1 v4_0 = this.a;
            vn.ai.trueid.sdk.networks.d.a(new vn.ai.trueid.sdk.networks.b(kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("faceImages", v1_4), kotlin.TuplesKt.to("transId", v4_0.getTransID()), kotlin.TuplesKt.to("personId", v4_0.getPersonID())})), v2_1.getLsDurationIn(), v2_1.getLsDurationOut(), new vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceSDK$1$complete$1(this)));
            return;
        }
    }
}
