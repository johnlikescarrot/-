package vn.ai.trueid.sdk.utils;
public final class d extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function0 {
    public static final vn.ai.trueid.sdk.utils.d d;

    static d()
    {
        vn.ai.trueid.sdk.utils.d.d = new vn.ai.trueid.sdk.utils.d(0);
        return;
    }

    public final Object invoke()
    {
        int v1_0;
        if (vn.ai.trueid.sdk.utils.Helpers.Companion.checkSecurityDevice() == 450) {
            v1_0 = 1;
        } else {
            v1_0 = 0;
            int v2_0 = 0;
            while (v2_0 < 5) {
                if (!new java.io.File(new String[] {"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/su", "/data/local/bin/su"})[v2_0]).exists()) {
                    v2_0++;
                }
            }
        }
        return Boolean.valueOf(v1_0);
    }
}
