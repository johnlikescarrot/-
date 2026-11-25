package vn.ai.trueid.sdk.navigation;
public final class AuthenticationID$startFaceSDK$1$complete$1 implements vn.ai.trueid.sdk.networks.e {
    public final synthetic vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceSDK$1 a;

    public AuthenticationID$startFaceSDK$1$complete$1(vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceSDK$1 p1)
    {
        this.a = p1;
        return;
    }

    public void fail(vn.ai.trueid.sdk.models.TrueError p8)
    {
        Integer v4 = Integer.valueOf(0);
        if ((p8.getCode() < 400) && (p8.getCode() != vn.ai.trueid.sdk.models.TrueError.Companion.getLIVENESS_SESSION_TIMEOUT().getCode())) {
            fk.c.c(p8.getCode(), 7, 0);
            int v1_1 = wk.a.k;
            if (v1_1 != 0) {
                v1_1.m(p8.getMessage(), 0, v4, v4, 0);
            }
            vn.ai.trueid.sdk.navigation.AuthenticationID v8_1 = vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE;
            if (v8_1.getCount() > v8_1.getSdkConfig$trueid_release().getMaxRetry()) {
                vn.ai.trueid.sdk.navigation.AuthenticationID.access$closeWithMaxRetry(v8_1);
            }
            return;
        } else {
            vn.ai.trueid.sdk.navigation.AuthenticationID$startFaceSDK$1.access$closeWithByHTTPCode(this.a, p8);
            return;
        }
    }

    public void success(org.json.JSONObject p9)
    {
        vn.ai.trueid.sdk.navigation.AuthenticationID v0 = vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE;
        v0.setCurResult(p9);
        try {
            java.util.List v1_6 = p9.getInt("result");
        } catch (Exception) {
            return;
        }
        if (v1_6 != 1) {
            if (!vn.ai.trueid.sdk.navigation.AuthenticationID.access$isUserBlocked(v0, p9)) {
                fk.c.c(v1_6, 7, 0);
                if (v0.getCount() > v0.getSdkConfig$trueid_release().getMaxRetry()) {
                    vn.ai.trueid.sdk.navigation.AuthenticationID.access$closeWithMaxRetry(v0);
                }
                kc.d.a(v0.getSdkConfig$trueid_release().getErrorDetails(), v0.getCurResult());
                v0.showErrorMessage();
                return;
            } else {
                org.json.JSONObject v9_4 = vn.ai.trueid.sdk.models.TrueError.Companion;
                vn.ai.trueid.sdk.navigation.AuthenticationID.access$closeWithErrorCode(v0, new zk.a(v9_4.getUSER_BLOCKED().getCode(), v9_4.getUSER_BLOCKED().getMessage(), 0, 0, 28));
                return;
            }
        } else {
            vn.ai.trueid.sdk.navigation.AuthenticationID.access$closeSuccess(v0);
            return;
        }
    }
}
