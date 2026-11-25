package vn.ai.trueid.sdk.networks;
public final class g0 {

    public static void a(vn.ai.trueid.sdk.networks.W p6)
    {
        String v0_1 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpConfig$trueid_release();
        if (v0_1.getAccessTokenEndPoint().length() != 0) {
            vn.ai.trueid.sdk.networks.h0.b.d(v0_1.getAccessTokenEndPoint(), kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Content-Type", "application/json"), kotlin.TuplesKt.to("User-Agent", vn.ai.trueid.sdk.utils.Helpers.Companion.provideUserAgent()), kotlin.TuplesKt.to("ClientID", v0_1.getAppId())})), kotlin.collections.a0.plus(kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("client_id", v0_1.getAppId()), kotlin.TuplesKt.to("client_secret", v0_1.getAppSecret()), kotlin.TuplesKt.to("grant_type", "client_credentials")})), vn.ai.trueid.sdk.networks.h0.c), new vn.ai.trueid.sdk.networks.X(p6));
            return;
        } else {
            p6.success("");
            return;
        }
    }

    public static void b(vn.ai.trueid.sdk.networks.W p4)
    {
        if (!vn.ai.trueid.sdk.networks.h0.d.containsKey("Authorization")) {
            vn.ai.trueid.sdk.networks.g0.a(new lS.b(20, p4));
            return;
        } else {
            if (kotlin.jvm.internal.Intrinsics.areEqual(vn.ai.trueid.sdk.networks.g0.d(), "NO_REQUEST")) {
                lO.o v0_4 = new lO.o(18, p4);
                String v4_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpConfig$trueid_release();
                if (v4_2.getCreateRequestEndPoint().length() != 0) {
                    Mk.f.l(v4_2.getCreateRequestEndPoint(), vn.ai.trueid.sdk.networks.h0.d, new java.util.HashMap(vn.ai.trueid.sdk.networks.h0.c), new vn.ai.trueid.sdk.networks.Y(v0_4));
                    return;
                } else {
                    v0_4.success("");
                    return;
                }
            } else {
                p4.success(vn.ai.trueid.sdk.networks.g0.d());
                return;
            }
        }
    }

    public static void c(String p6, String p7, String p8, vn.ai.trueid.sdk.networks.c0 p9)
    {
        j9.q v0_1 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpConfig$trueid_release();
        Mk.f.l(v0_1.getCompleteEndpoint(), vn.ai.trueid.sdk.networks.h0.d, kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("userId", v0_1.getUserId()), kotlin.TuplesKt.to("requestId", vn.ai.trueid.sdk.networks.g0.d()), kotlin.TuplesKt.to("personId", p6), kotlin.TuplesKt.to("deviceId", vn.ai.trueid.sdk.navigation.TrueIDV3.getDeviceId()), kotlin.TuplesKt.to("data", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("apiVersion", "2026-07-15"), kotlin.TuplesKt.to("challengeId", p7), kotlin.TuplesKt.to("responseCode", p8)})))})), new j9.q(21, p9));
        return;
    }

    public static String d()
    {
        String v0_1 = vn.ai.trueid.sdk.networks.h0.c.get("requestId");
        if (v0_1 == null) {
            v0_1 = "NO_REQUEST";
        }
        return ((String) v0_1);
    }

    public static String e(Integer p4)
    {
        if ((p4 == null) || (p4.intValue() <= 0)) {
            return "\u0110\u00e3 kho\u00e1 d\u1ecbch v\u1ee5 do nh\u1eadp sai qu\u00e1 s\u1ed1 l\u1ea7n. Vui l\u00f2ng th\u1eed l\u1ea1i sau ho\u1eb7c li\u00ean h\u1ec7 qu\u1ea3n tr\u1ecb vi\u00ean.";
        } else {
            return androidx.camera.camera2.internal.G.d(((int) Math.ceil((((double) p4.intValue()) / 4633641066610819072))), "\u0110\u00e3 kho\u00e1 d\u1ecbch v\u1ee5 do nh\u1eadp sai qu\u00e1 s\u1ed1 l\u1ea7n. Vui l\u00f2ng th\u1eed l\u1ea1i sau ", " ph\u00fat ho\u1eb7c li\u00ean h\u1ec7 qu\u1ea3n tr\u1ecb vi\u00ean.");
        }
    }

    public static void f()
    {
        try {
            vn.ai.trueid.sdk.networks.h0.b = new vn.ai.trueid.sdk.networks.m(vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpConfig$trueid_release().getDomain());
        } catch (Object[] v0_17) {
            vn.ai.trueid.sdk.utils.Helpers.Companion.errorLog(new Object[] {androidx.camera.camera2.internal.G.e("SmartOtpClient: failed to re-initialize Client: ", v0_17.getMessage())}));
        }
        vn.ai.trueid.sdk.utils.Helpers$Companion v1_2 = vn.ai.trueid.sdk.utils.Helpers.Companion;
        Object[] v0_7 = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Content-Type", "application/json"), kotlin.TuplesKt.to("User-Agent", v1_2.provideUserAgent()), kotlin.TuplesKt.to("ClientID", vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpConfig$trueid_release().getAppId())}));
        vn.ai.trueid.sdk.networks.h0.d = v0_7;
        v1_2.printLog(new Object[] {"buildHeaders:", v0_7}));
        Object[] v0_15 = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("client", kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("flowType", "SMARTOTP"))), kotlin.TuplesKt.to("platform", "android"), kotlin.TuplesKt.to("os_version", String.valueOf(android.os.Build$VERSION.SDK_INT))}));
        vn.ai.trueid.sdk.networks.h0.c = v0_15;
        v1_2.printLog(new Object[] {"systemParams:", v0_15}));
        return;
    }

    public static String g(byte[] p6)
    {
        java.util.Locale v0_6 = (kotlin.collections.y.last(p6) & 15);
        return String.format(java.util.Locale.US, androidx.camera.camera2.internal.G.d(8, "%0", "d"), java.util.Arrays.copyOf(new Object[] {Integer.valueOf((((p6[(v0_6 + 3)] & 255) | ((((p6[v0_6] & 127) << 24) | ((p6[(v0_6 + 1)] & 255) << 16)) | ((p6[(v0_6 + 2)] & 255) << 8))) % ((int) Math.pow(4621819117588971520, ((double) 8)))))}), 1));
    }
}
