package vn.ai.trueid.sdk.networks;
public final class o implements vn.ai.trueid.sdk.networks.k {
    public final synthetic vn.ai.trueid.sdk.models.EkycConfig a;
    public final synthetic n8.i b;

    public o(vn.ai.trueid.sdk.models.EkycConfig p1, n8.i p2)
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

    public final void success(org.json.JSONObject p10)
    {
        vn.ai.trueid.sdk.TrueShield.ChallengeManager v0_2 = wmoi.igob.ggcn(6561);
        String v1_3 = wmoi.igob.ggcn(6562);
        android.content.Context v2_6 = wmoi.igob.ggcn(6563);
        try {
            n8.i v5 = this.b;
        } catch (Exception) {
            return;
        }
        if (p10.getInt(v1_3) != 1) {
            v5.fail(new vn.ai.trueid.sdk.models.TrueError(p10.getInt(v1_3)));
            return;
        } else {
            String v1_2;
            String v1_0 = p10.has(v2_6);
            String v3_1 = 0;
            String v4_1 = wmoi.igob.ggcn(6564);
            if (v1_0 == null) {
                String v1_1 = p10.optJSONObject(v4_1);
                if (v1_1 == null) {
                    v1_2 = 0;
                } else {
                    v1_2 = v1_1.optString(v2_6);
                }
                if (v1_2 == null) {
                    v1_2 = "";
                }
            } else {
                v1_2 = p10.getString(v2_6);
            }
            fk.a.e(p10);
            android.content.Context v2_1 = p10.optJSONObject(wmoi.igob.ggcn(6565));
            if (v2_1 != null) {
                android.content.Context v2_2 = v2_1.optInt(wmoi.igob.ggcn(6566), 0);
                if (v2_2 > null) {
                    vn.ai.trueid.sdk.models.EkycConfig v6_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getEkycConfig$trueid_release();
                    if (v6_2 != null) {
                        v6_2.setLivenessType(v2_2);
                    } else {
                    }
                }
            }
            android.content.Context v2_3 = p10.optJSONObject(v0_2);
            if (v2_3 != null) {
                v3_1 = v2_3;
            } else {
                String v10_2 = p10.optJSONObject(v4_1);
                if (v10_2 != null) {
                    v3_1 = v10_2.optJSONObject(v0_2);
                }
            }
            if (v3_1 != null) {
                String v10_4 = v3_1.optString(wmoi.igob.ggcn(6567));
                if (v10_4.length() > 0) {
                    vn.ai.trueid.sdk.TrueShield.ChallengeManager.INSTANCE.solve(vn.ai.trueid.sdk.utils.Helpers.Companion.getContext(), v10_4, v1_2, this.a.getAppId());
                }
            }
            v5.success(v1_2);
            return;
        }
    }
}
