package vn.ai.trueid.sdk.networks;
public abstract class M {
    public static vn.ai.trueid.sdk.networks.m a;
    public static java.util.Map b;
    public static java.util.Map c;

    static M()
    {
        String v1_2 = vn.ai.trueid.sdk.navigation.FaceLiveness.INSTANCE.getSDKConfig$trueid_release().getDomain();
        if (kotlin.text.StringsKt.isBlank(v1_2)) {
            v1_2 = "https://localhost";
        }
        vn.ai.trueid.sdk.networks.M.a = new vn.ai.trueid.sdk.networks.m(v1_2);
        vn.ai.trueid.sdk.networks.M.b = new java.util.LinkedHashMap();
        vn.ai.trueid.sdk.networks.M.c = new java.util.LinkedHashMap();
        return;
    }
}
