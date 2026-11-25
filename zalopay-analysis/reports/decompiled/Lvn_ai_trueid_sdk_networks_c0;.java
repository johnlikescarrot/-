package vn.ai.trueid.sdk.networks;
public final class c0 implements vn.ai.trueid.sdk.networks.V {
    public final synthetic android.content.Context a;
    public final synthetic A0.h b;
    public final synthetic byte[] c;
    public final synthetic vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 d;
    public final synthetic String e;

    public c0(android.content.Context p1, A0.h p2, byte[] p3, vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 p4, String p5)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p5;
        return;
    }

    public static final void a(boolean p1, byte[] p2, String p3, boolean p4, android.content.Context p5, int p6, vn.ai.trueid.sdk.navigation.TrueIDV3$registerSeed$2 p7, org.json.JSONObject p8, String p9)
    {
        if (p1 != 0) {
            vn.ai.trueid.sdk.utils.SmartOTPHelper.j(p3, p2);
        }
        if ((p4 != 0) && (p9 != null)) {
            boolean vtmp12 = vn.ai.trueid.sdk.utils.SmartOTPHelper.t(p5, p2, p9, p3);
        }
        int v1_2 = p5.getSharedPreferences("sotp_pin_seed_store", 0).edit();
        StringBuilder v9_2 = new StringBuilder("sotp_type_");
        v9_2.append(p3);
        v1_2.putInt(v9_2.toString(), p6).apply();
        p5.getSharedPreferences("trueid_sotp_instance", 0).edit().putString("credential_instance_id", vn.ai.trueid.sdk.utils.SmartOTPHelper.h(p5)).apply();
        java.util.Arrays.fill(p2, 0);
        vn.ai.trueid.sdk.utils.Helpers.Companion.trueIdLog(new Object[] {androidx.camera.camera2.internal.G.d(p6, "\u2705 SOTP v3.0 registration completed (sotpType=", ")!")}));
        p7.success(p8);
        return;
    }

    public final void fail(vn.ai.trueid.sdk.models.TrueError p3)
    {
        java.util.Arrays.fill(this.c, 0);
        this.d.fail(p3);
        return;
    }

    public final void success(org.json.JSONObject p14)
    {
        int v9_0;
        int v0_5 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpConfig$trueid_release().getSotpType();
        if ((v0_5 != 1) || (vn.ai.trueid.sdk.utils.SmartOTPHelper.l(this.a))) {
            v9_0 = v0_5;
        } else {
            vn.ai.trueid.sdk.utils.Helpers.Companion.trueIdLog(new Object[] {"\u26a0\ufe0f [TP-1319] type 1 thi\u1ebfu biometric STRONG \u2192 suy bi\u1ebfn PIN-only (type 2)"}));
            v9_0 = 2;
        }
        if ((v9_0 != 1) && (v9_0 != 3)) {
            vn.ai.trueid.sdk.networks.b0 v4_0 = 0;
        } else {
            v4_0 = 1;
        }
        if ((v9_0 != 1) && (v9_0 != 2)) {
            int v7_0 = 0;
        } else {
            v7_0 = 1;
        }
        String v6_0 = this.d;
        byte[] v5 = this.c;
        if (v7_0 == 0) {
            vn.ai.trueid.sdk.networks.c0.a(v4_0, v5, this.e, v7_0, this.a, v9_0, v6_0, p14, 0);
            return;
        } else {
            this.b.invoke(new vn.ai.trueid.sdk.networks.b0(v5, v6_0, v4_0, this.e, v7_0, this.a, v9_0, p14));
            return;
        }
    }
}
