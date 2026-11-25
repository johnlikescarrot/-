package vn.ai.trueid.sdk.utils;
public final class c extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function0 {
    public static final vn.ai.trueid.sdk.utils.c d;

    static c()
    {
        vn.ai.trueid.sdk.utils.c.d = new vn.ai.trueid.sdk.utils.c(0);
        return;
    }

    public final Object invoke()
    {
        return String.valueOf(vn.ai.trueid.sdk.utils.Helpers.Companion.isEmulator());
    }
}
