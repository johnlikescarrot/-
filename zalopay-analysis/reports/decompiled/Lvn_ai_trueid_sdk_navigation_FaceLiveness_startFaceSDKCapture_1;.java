package vn.ai.trueid.sdk.navigation;
public final class FaceLiveness$startFaceSDKCapture$1 implements wk.b {

    public void complete(zk.a p30)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness v1 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE;
        v1.setCount((v1.getCount() + 1));
        java.util.List v2_2 = p30.c;
        int v4 = p30.a;
        if (!((java.util.Collection) v2_2).isEmpty()) {
            String v5_3 = ((String) kotlin.collections.CollectionsKt.last(v2_2));
            String v7_1 = p30.e;
            if (v7_1.length() == 0) {
                String v7_2 = wk.a.c;
                if (v7_2 == null) {
                    String v9_1 = wmoi.igob.ggcn(15304);
                    long v10_2 = wmoi.igob.ggcn(15305);
                    int v13_2 = wmoi.igob.ggcn(15306);
                    String v14_1 = wmoi.igob.ggcn(15307);
                    String v15_1 = wmoi.igob.ggcn(15308);
                    v7_2 = new yk.b(v9_1, v10_2, v9_1, 0, v13_2, v14_1, v15_1, v15_1, wmoi.igob.ggcn(15309), 0, 1, 1, "", 0, 0, 0, 0, 0, 0, 8126472);
                }
                if ((!v7_2.s) || ((v2_2.size() <= 1) || (kotlin.text.y.startsWith$default(v5_3, wmoi.igob.ggcn(15310), 0, 2, 0)))) {
                    v5_3 = "";
                }
                v7_1 = v5_3;
            }
            v1.setCurVideoBase64$trueid_release(v7_1);
            if (v1.getCurVideoBase64$trueid_release().length() > 0) {
                v2_2 = kotlin.collections.CollectionsKt.dropLast(v2_2, 1);
            }
            v1.setCurImages(v2_2);
        }
        if ((v4 != 1) || (v1.getCurImages() == null)) {
            vn.ai.trueid.sdk.navigation.FaceLiveness.access$captureCloseError(v1, v4, p30.b);
            return;
        } else {
            if (!v1.getCaptureNeedsNewRequest$trueid_release()) {
                vn.ai.trueid.sdk.navigation.FaceLiveness.access$sendCaptureBody(v1);
                return;
            } else {
                v1.setCaptureNeedsNewRequest$trueid_release(0);
                String v7_0 = new vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureStepCallback(Kk.c.g, Kk.c.h, 0, 4, 0);
                vn.ai.trueid.sdk.navigation.TrueIDLivenessCaptureListener v0_1 = v1.getCaptureListener$trueid_release();
                if (v0_1 != null) {
                    v0_1.onRequestCreate(v7_0);
                }
                return;
            }
        }
    }
}
