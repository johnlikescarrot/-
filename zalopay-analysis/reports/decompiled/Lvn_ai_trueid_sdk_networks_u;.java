package vn.ai.trueid.sdk.networks;
public abstract class u {
    public static final vn.ai.trueid.sdk.networks.m a;
    public static java.util.Map b;
    public static java.util.Map c;

    static u()
    {
        String v1_2;
        String v1_1 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getEkycConfig$trueid_release();
        if (v1_1 == null) {
            v1_2 = "";
        } else {
            v1_2 = v1_1.getDomain();
            if (v1_2 == null) {
            }
        }
        vn.ai.trueid.sdk.networks.u.a = new vn.ai.trueid.sdk.networks.m(v1_2);
        vn.ai.trueid.sdk.networks.u.b = new java.util.LinkedHashMap();
        vn.ai.trueid.sdk.networks.u.c = new java.util.LinkedHashMap();
        return;
    }
}
