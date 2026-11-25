package vn.ai.trueid.sdk.networks;
public abstract class h0 {
    public static Mk.e a;
    public static vn.ai.trueid.sdk.networks.m b;
    public static java.util.Map c;
    public static java.util.Map d;

    static h0()
    {
        try {
            java.util.LinkedHashMap v0_5 = new vn.ai.trueid.sdk.networks.m(vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getSmartOtpConfig$trueid_release().getDomain());
        } catch (Throwable) {
            v0_5 = new vn.ai.trueid.sdk.networks.m("");
        }
        vn.ai.trueid.sdk.networks.h0.b = v0_5;
        vn.ai.trueid.sdk.networks.h0.c = new java.util.LinkedHashMap();
        vn.ai.trueid.sdk.networks.h0.d = new java.util.LinkedHashMap();
        return;
    }
}
