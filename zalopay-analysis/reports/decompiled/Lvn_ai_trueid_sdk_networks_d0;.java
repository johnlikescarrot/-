package vn.ai.trueid.sdk.networks;
public final class d0 implements vn.ai.trueid.sdk.networks.k {
    public static final String flfsytc = "";
    public static final String foptj = "credentialId";
    public static final String jetyao = "";
    public static final String kmuui = "Ceremony error";
    public static final String netkk = "\u274c Ceremony error: ";
    public static final String pshpe = "OCRA";
    public static final String rslszz = "data";
    public static final String swnzmgo = "\u274c Native Register Ceremony failed: ";
    public static final String wbfjzsr = "challengeId";
    public static final String wtmsow = "wrappedSecret";
    public final synthetic vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 a;
    public final synthetic byte[] b;
    public final synthetic byte[] c;
    public final synthetic vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig d;
    public final synthetic String e;
    public final synthetic android.content.Context f;
    public final synthetic A0.h g;

    static d0()
    {
        wmoi.igob.jtgo(vn.ai.trueid.sdk.networks.d0, 18141, 18142);
        return;
    }

    public d0(vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 p1, byte[] p2, byte[] p3, vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig p4, String p5, android.content.Context p6, A0.h p7)
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

    public final void fail(vn.ai.trueid.sdk.models.TrueError p3)
    {
        java.util.Arrays.fill(this.b, 0);
        java.util.Arrays.fill(this.c, 0);
        this.a.fail(p3);
        return;
    }

    public final void success(org.json.JSONObject p22)
    {
        vn.ai.trueid.sdk.models.TrueError v2_3 = vn.ai.trueid.sdk.networks.d0.pshpe;
        int v3_2 = this.c;
        String v4_2 = this.b;
        vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 v10 = this.a;
        byte[] v9_0 = vn.ai.trueid.sdk.utils.Helpers.Companion;
        String v11_1 = new StringBuilder("\u1f50e [V3 initiate] raw response = ");
        v11_1.append(p22);
        v9_0.trueIdLog(new Object[] {v11_1.toString()}));
        vn.ai.trueid.sdk.models.TrueError v0_11 = vn.ai.trueid.sdk.utils.j.h(vn.ai.trueid.sdk.networks.d0.rslszz, p22);
        if (v0_11 != null) {
            A0.h v8_4 = new StringBuilder("\u1f50e [V3 initiate] data node = ");
            v8_4.append(v0_11);
            v9_0.trueIdLog(new Object[] {v8_4.toString()}));
            android.content.Context v7_3 = v0_11.optString(vn.ai.trueid.sdk.networks.d0.wtmsow, "");
            String v17 = v0_11.optString(vn.ai.trueid.sdk.networks.d0.flfsytc, "");
            String v16 = v0_11.optString(vn.ai.trueid.sdk.networks.d0.foptj, "");
            vn.ai.trueid.sdk.models.TrueError v0_12 = v0_11.optString(vn.ai.trueid.sdk.networks.d0.wbfjzsr, "");
            if (v7_3.length() != 0) {
                if (v17.length() != 0) {
                    if (v16.length() != 0) {
                        if (v0_12.length() != 0) {
                            String v5_7 = new byte[9];
                            A0.h v8_9 = new byte[32];
                            byte[] v19 = v5_7;
                            byte[] v20_0 = v8_9;
                            String v5_8 = vn.ai.trueid.sdk.utils.SmartOTPHelper.vaultRegisterCeremony(android.util.Base64.decode(v7_3, 0), this.b, this.c, v16, v17, this.d.getHpkeRegistrationInfo(), v19, v20_0);
                            android.content.Context v7_5 = v19;
                            A0.h v8_10 = v20_0;
                            java.util.Arrays.fill(v4_2, 0);
                            java.util.Arrays.fill(v3_2, 0);
                            if (v5_8 == null) {
                                vn.ai.trueid.sdk.networks.g0.c(this.e, v0_12, kotlin.text.StringsKt.trim(new String(v7_5, 0, 8, kotlin.text.Charsets.UTF_8)).toString(), new vn.ai.trueid.sdk.networks.c0(this.f, this.g, v8_10, v10, v16));
                                return;
                            } else {
                                java.util.Arrays.fill(v8_10, 0);
                                vn.ai.trueid.sdk.models.TrueError v0_14 = new StringBuilder(vn.ai.trueid.sdk.networks.d0.swnzmgo);
                                v0_14.append(v5_8);
                                v9_0.errorLog(new Object[] {v2_3, v0_14.toString()}));
                                v10.fail(new vn.ai.trueid.sdk.models.TrueError(v5_8, vn.ai.trueid.sdk.networks.d0.jetyao));
                                return;
                            }
                        }
                    } else {
                    }
                } else {
                }
            } else {
            }
            java.util.Arrays.fill(v4_2, 0);
            java.util.Arrays.fill(v3_2, 0);
            v10.fail(vn.ai.trueid.sdk.models.TrueError.Companion.getDATA_ERROR());
            return;
        } else {
            v10.fail(vn.ai.trueid.sdk.models.TrueError.Companion.getDATA_ERROR());
            return;
        }
    }
}
