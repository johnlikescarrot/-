package vn.ai.trueid.sdk.networks;
public final class x implements vn.ai.trueid.sdk.networks.k {
    public final synthetic vn.ai.trueid.sdk.models.FaceEnrollConfig a;
    public final synthetic vn.ai.trueid.sdk.networks.D b;

    public x(vn.ai.trueid.sdk.models.FaceEnrollConfig p1, vn.ai.trueid.sdk.networks.D p2)
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

    public final void success(org.json.JSONObject p9)
    {
        try {
            vn.ai.trueid.sdk.networks.D v6 = this.b;
        } catch (Exception) {
            return;
        }
        if (p9.getInt("result") != 1) {
            v6.fail(new vn.ai.trueid.sdk.models.TrueError(p9.getInt("result")));
            return;
        } else {
            String v2_3;
            if (!p9.has("requestId")) {
                v2_3 = p9.getJSONObject("data").getString("requestId");
            } else {
                v2_3 = p9.getString("requestId");
            }
            fk.a.e(p9.getJSONObject("data"));
            if (p9.getJSONObject("data").has("configSDK")) {
                String v3_4 = p9.optJSONObject("data");
                if (v3_4 != null) {
                    android.content.Context v1_1 = vn.ai.trueid.sdk.utils.j.h("configSDK", v3_4);
                    if (v1_1 != null) {
                        String v3_6 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getFaceEnrollConfig$trueid_release();
                        if (v3_6 != null) {
                            v3_6.setLivenessType(v1_1.optInt("livenessType", 0));
                        } else {
                        }
                    }
                }
            }
            android.content.Context v1_3 = p9.optJSONObject("c");
            if (v1_3 == null) {
                String v9_2 = p9.optJSONObject("data");
                if (v9_2 == null) {
                    v1_3 = 0;
                } else {
                    v1_3 = v9_2.optJSONObject("c");
                }
            }
            if (v1_3 != null) {
                String v9_4 = v1_3.optString("d");
                if (v9_4.length() > 0) {
                    vn.ai.trueid.sdk.TrueShield.ChallengeManager.INSTANCE.solve(vn.ai.trueid.sdk.utils.Helpers.Companion.getContext(), v9_4, v2_3, this.a.getAppId());
                }
            }
            v6.success(v2_3);
            return;
        }
    }
}
