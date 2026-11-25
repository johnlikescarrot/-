package vn.ai.trueid.sdk.networks;
public final class c implements vn.ai.trueid.sdk.networks.k {
    public final synthetic vn.ai.trueid.sdk.models.FaceAuthenConfig a;
    public final synthetic io.reactivex.internal.operators.observable.f1 b;

    public c(vn.ai.trueid.sdk.models.FaceAuthenConfig p1, io.reactivex.internal.operators.observable.f1 p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p2)
    {
        this.b.fail(p2);
        return;
    }

    public final void success(org.json.JSONObject p8)
    {
        try {
            io.reactivex.internal.operators.observable.f1 v5 = this.b;
        } catch (Exception) {
            return;
        }
        if (p8.getInt("result") != 1) {
            v5.fail(new vn.ai.trueid.sdk.models.TrueError(p8.getInt("result")));
            return;
        } else {
            String v1_3;
            if (!p8.has("requestId")) {
                v1_3 = p8.getJSONObject("data").getString("requestId");
            } else {
                v1_3 = p8.getString("requestId");
            }
            if (p8.has("data")) {
                fk.a.e(p8.getJSONObject("data"));
                if (p8.getJSONObject("data").has("configSDK")) {
                    android.content.Context v2_5 = p8.optJSONObject("data");
                    if (v2_5 != null) {
                        vn.ai.trueid.sdk.TrueShield.ChallengeManager v0_2 = vn.ai.trueid.sdk.utils.j.h("configSDK", v2_5);
                        if (v0_2 != null) {
                            vn.ai.trueid.sdk.navigation.AuthenticationID.INSTANCE.getSdkConfig$trueid_release().setLivenessType(v0_2.optInt("livenessType", 0));
                        }
                    }
                }
                String v8_2 = p8.optJSONObject("data");
                if (v8_2 != null) {
                    String v8_3 = v8_2.optJSONObject("c");
                    if (v8_3 != null) {
                        String v8_4 = v8_3.optString("d");
                        if (v8_4.length() > 0) {
                            vn.ai.trueid.sdk.TrueShield.ChallengeManager.INSTANCE.solve(vn.ai.trueid.sdk.utils.Helpers.Companion.getContext(), v8_4, v1_3, this.a.getAppId());
                        }
                    }
                }
            }
            v5.success(v1_3);
            return;
        }
    }
}
