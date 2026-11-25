package vn.ai.trueid.sdk.utils;
public final class Helpers {
    public static String APP_APPLICATION_ID;
    public static String AppVersion;
    public static final vn.ai.trueid.sdk.utils.Helpers$Companion Companion;
    private static final String TAG;
    private static String advId;
    public static android.content.Context context;
    private static boolean isDebug;
    public static String packageName;
    public static android.content.SharedPreferences prefs;
    public static android.content.res.Resources resources;

    static Helpers()
    {
        wmoi.igob.jtgo(vn.ai.trueid.sdk.utils.Helpers, 22967, 22967);
        vn.ai.trueid.sdk.utils.Helpers.Companion = new vn.ai.trueid.sdk.utils.Helpers$Companion(0);
        vn.ai.trueid.sdk.utils.Helpers.advId = java.util.UUID.randomUUID().toString();
        return;
    }

    public Helpers()
    {
        return;
    }

    public static final synthetic String access$getAdvId$cp()
    {
        return vn.ai.trueid.sdk.utils.Helpers.advId;
    }

    public static final synthetic boolean access$isDebug$cp()
    {
        return vn.ai.trueid.sdk.utils.Helpers.isDebug;
    }

    public static final synthetic void access$setAdvId$cp(String p0)
    {
        vn.ai.trueid.sdk.utils.Helpers.advId = p0;
        return;
    }

    public static final synthetic void access$setDebug$cp(boolean p0)
    {
        vn.ai.trueid.sdk.utils.Helpers.isDebug = p0;
        return;
    }

    public static final int checkSecurityDevice()
    {
        return vn.ai.trueid.sdk.utils.Helpers.Companion.checkSecurityDevice();
    }

    public static final boolean verifyInternal()
    {
        return vn.ai.trueid.sdk.utils.Helpers.Companion.verifyInternal();
    }
}
