package vn.ai.trueid.sdk.navigation;
public final class FaceLiveness$startFaceSDK$1$complete$1 implements vn.ai.trueid.sdk.networks.K {
    public final synthetic vn.ai.trueid.sdk.navigation.FaceLiveness$startFaceSDK$1 a;

    public FaceLiveness$startFaceSDK$1$complete$1(vn.ai.trueid.sdk.navigation.FaceLiveness$startFaceSDK$1 p1)
    {
        this.a = p1;
        return;
    }

    public void fail(vn.ai.trueid.sdk.models.TrueError p8)
    {
        Integer v4 = Integer.valueOf(0);
        if ((p8.getCode() < 400) && (p8.getCode() != vn.ai.trueid.sdk.models.TrueError.Companion.getLIVENESS_SESSION_TIMEOUT().getCode())) {
            int v1_2 = wk.a.k;
            if (v1_2 != 0) {
                v1_2.m(p8.getMessage(), 0, v4, v4, 0);
            }
            vn.ai.trueid.sdk.navigation.FaceLiveness v8_1 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE;
            if (v8_1.getCount() > v8_1.getSDKConfig$trueid_release().getMaxRetry()) {
                vn.ai.trueid.sdk.navigation.FaceLiveness.access$closeWithMaxRetry(v8_1);
            }
            return;
        } else {
            vn.ai.trueid.sdk.navigation.FaceLiveness$startFaceSDK$1.access$closeWithByHTTPCode(this.a, p8);
            return;
        }
    }

    public void success(org.json.JSONObject p3)
    {
        vn.ai.trueid.sdk.navigation.FaceLiveness v0 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE;
        v0.setCurResult(p3);
        try {
            if (p3.getInt("result") != 1) {
                if (v0.getCount() > v0.getSDKConfig$trueid_release().getMaxRetry()) {
                    vn.ai.trueid.sdk.navigation.FaceLiveness.access$closeWithMaxRetry(v0);
                }
                kc.d.a(v0.getSdkConfig$trueid_release().getErrorDetails(), v0.getCurResult());
                v0.showErrorMessage();
                return;
            } else {
                vn.ai.trueid.sdk.navigation.FaceLiveness.access$closeSuccess(v0);
                return;
            }
        } catch (Exception) {
            return;
        }
    }
}
