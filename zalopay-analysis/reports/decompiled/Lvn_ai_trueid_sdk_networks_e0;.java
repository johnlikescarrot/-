package vn.ai.trueid.sdk.networks;
public final class e0 implements vn.ai.trueid.sdk.networks.W {
    public final synthetic vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 a;
    public final synthetic String b;
    public final synthetic android.content.Context c;
    public final synthetic A0.h d;

    public e0(vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 p1, String p2, android.app.Activity p3, A0.h p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p2)
    {
        this.a.fail(p2);
        return;
    }

    public final void success(String p15)
    {
        String v1_2 = 0;
        try {
            java.util.Map v15_5 = new byte[32];
            new java.security.SecureRandom().nextBytes(v15_5);
            v15_5[0] = ((byte) (v15_5[0] & 248));
            vn.ai.trueid.sdk.networks.d0 v2_5 = ((byte) (v15_5[31] & 127));
            v15_5[31] = v2_5;
            v15_5[31] = ((byte) (v2_5 | 64));
            java.util.Map v0_5 = vn.ai.trueid.sdk.utils.SmartOTPHelper.vaultX25519Pubkey(v15_5);
        } catch (java.util.Map v0_6) {
            v0_6.printStackTrace();
            vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 v3 = this.a;
            if (v1_2 != null) {
                byte[] v4_1 = ((byte[]) v1_2.getFirst());
                byte[] v5_1 = ((byte[]) v1_2.getSecond());
                java.util.Map v15_9 = android.util.Base64.encodeToString(v5_1, 2);
                vn.ai.trueid.sdk.models.sotp.TrueIdSmartOTPConfig v6 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpConfig$trueid_release();
                Mk.f.l(v6.getInitiateEndpoint(), vn.ai.trueid.sdk.networks.h0.d, kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("userId", v6.getUserId()), kotlin.TuplesKt.to("requestId", vn.ai.trueid.sdk.networks.g0.d()), kotlin.TuplesKt.to("personId", this.b), kotlin.TuplesKt.to("deviceId", vn.ai.trueid.sdk.navigation.TrueIDV3.getDeviceId()), kotlin.TuplesKt.to("data", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("apiVersion", "2026-07-15"), kotlin.TuplesKt.to("clientPublicKey", v15_9), kotlin.TuplesKt.to("enrollmentMode", "EXPLICIT"), kotlin.TuplesKt.to("appInstanceId", vn.ai.trueid.sdk.utils.SmartOTPHelper.h(this.c)), kotlin.TuplesKt.to("forceReplaceDevices", Boolean.TRUE)})))})), new vn.ai.trueid.sdk.networks.d0(v3, v4_1, v5_1, v6, this.b, this.c, this.d));
                return;
            } else {
                vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {"OCRA", "\u274c X25519 keypair generation failed"}));
                v3.fail(new vn.ai.trueid.sdk.models.TrueError(340, "Failed to create X25519 keypair"));
                return;
            }
        }
        if (v0_5 != null) {
            v1_2 = new kotlin.Pair(v15_5, v0_5);
        } else {
        }
    }
}
