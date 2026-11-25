package vn.ai.trueid.sdk.networks;
public final class a0 implements vn.ai.trueid.sdk.networks.k {
    public final synthetic vn.ai.trueid.sdk.navigation.TrueIDV3$handleSmartOtpPush$1 a;
    public final synthetic vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig b;
    public final synthetic String c;
    public final synthetic String d;
    public final synthetic String e;
    public final synthetic String f;
    public final synthetic String g;

    public a0(vn.ai.trueid.sdk.navigation.TrueIDV3$handleSmartOtpPush$1 p1, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig p2, String p3, String p4, String p5, String p6, String p7)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p5;
        this.f = p6;
        this.g = p7;
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p2)
    {
        this.a.fail(p2);
        return;
    }

    public final void success(org.json.JSONObject p21)
    {
        org.json.JSONObject v0_10 = this.b;
        vn.ai.trueid.sdk.navigation.TrueIDV3$handleSmartOtpPush$1 v4 = this.a;
        byte[] v8_0 = vn.ai.trueid.sdk.utils.j.h("data", p21);
        if (v8_0 != null) {
            String v9_1 = v8_0.optString("serverNonce", "");
            Mk.e v10_3 = v8_0.optString("txDigest", "");
            org.json.JSONObject v3_4 = v8_0.optString("credentialId", "");
            if (v9_1.length() != 0) {
                if (v10_3.length() != 0) {
                    if (v3_4.length() != 0) {
                        if (!kotlin.text.StringsKt.g(v0_10.getHpkeAuthInfo(), "mock")) {
                            int v6_2 = vn.ai.trueid.sdk.utils.SmartOTPHelper.vaultVerifyTxDigest(this.c, v10_3);
                            if (v6_2 == 0) {
                                vn.ai.trueid.sdk.utils.Helpers.Companion.trueIdLog(new Object[] {"[OCRA] \u2705 WYSIWYS verification passed"}));
                            } else {
                                org.json.JSONObject v3_7 = new StringBuilder("\u274c WYSIWYS verification failed: ");
                                v3_7.append(v6_2);
                                vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"OCRA", v3_7.toString()}));
                                v4.fail(new vn.ai.trueid.sdk.models.TrueError(341, "WYSIWYS verification failed: transaction data tampered!"));
                                return;
                            }
                        } else {
                            byte[] v8_6 = v0_10.getHpkeAuthInfo();
                            byte[] v11_4 = new StringBuilder("[OCRA] \u26a0\ufe0f Mock auth mode (hpkeAuthInfo=\'");
                            v11_4.append(v8_6);
                            v11_4.append("\'): skipping WYSIWYS check \u2014 server txDigest is hardcoded test vector");
                            vn.ai.trueid.sdk.utils.Helpers.Companion.trueIdLog(new Object[] {v11_4.toString()}));
                        }
                        String v5_7 = vn.ai.trueid.sdk.utils.SmartOTPHelper.vaultComputeQAuth(v9_1, v10_3, v3_4);
                        if (v5_7 != null) {
                            String v5_8 = vn.ai.trueid.sdk.utils.Helpers.Companion.hexStringToByteArray(v5_7);
                            int v6_11 = "OCRA-1:HOTP-SHA256-8:QH64".getBytes(kotlin.text.Charsets.UTF_8);
                            byte[] v8_12 = new byte[((v6_11.length + 1) + v5_8.length)];
                            System.arraycopy(v6_11, 0, v8_12, 0, v6_11.length);
                            v8_12[v6_11.length] = 0;
                            System.arraycopy(v5_8, 0, v8_12, (v6_11.length + 1), v5_8.length);
                            vn.ai.trueid.sdk.networks.h0.a = new Mk.e(v8_12, this.d, this.e, this.f, this.g, v0_10.getVerifyEndpoint(), v3_4, v9_1, v10_3);
                            v4.success(new org.json.JSONObject().put("result", 1));
                            return;
                        } else {
                            v4.fail(new vn.ai.trueid.sdk.models.TrueError(500, "Failed to compute native Q_auth"));
                            return;
                        }
                    }
                } else {
                }
            } else {
            }
            v4.fail(vn.ai.trueid.sdk.models.TrueError.Companion.getDATA_ERROR());
            return;
        } else {
            v4.fail(vn.ai.trueid.sdk.models.TrueError.Companion.getDATA_ERROR());
            return;
        }
    }
}
