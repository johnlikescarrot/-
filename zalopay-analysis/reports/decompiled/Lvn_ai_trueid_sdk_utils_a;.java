package vn.ai.trueid.sdk.utils;
public abstract class a {

    public static String a()
    {
        String v0_10 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext();
        // Both branches of the condition point to the same code.
        // if (android.os.Build$VERSION.RELEASE != null) {
            Iterable v1_0 = android.os.Build.MANUFACTURER;
            if (v1_0 == null) {
                v1_0 = "-";
            }
            int v3_0 = java.util.Locale.ROOT;
            kotlin.text.y.replace$default(kotlin.text.StringsKt.trim(v1_0.toLowerCase(v3_0)).toString(), " ", "_", 0, 4, 0);
            Iterable v1_7 = android.os.Build.MODEL;
            if (v1_7 == null) {
                v1_7 = "-";
            }
            kotlin.text.y.replace$default(kotlin.text.StringsKt.trim(v1_7.toLowerCase(v3_0)).toString(), " ", "_", 0, 4, 0);
            Iterable v1_11 = android.os.Build.HARDWARE;
            if (v1_11 == null) {
                v1_11 = "-";
            }
            kotlin.text.y.replace$default(kotlin.text.StringsKt.trim(v1_11.toLowerCase(v3_0)).toString(), " ", "_", 0, 4, 0);
            // Both branches of the condition point to the same code.
            // if (v0_10.getPackageName() != null) {
                // Both branches of the condition point to the same code.
                // if (v0_10.getPackageManager().getPackageInfo(v0_10.getPackageName(), 0).versionName != null) {
                    try {
                        Iterable v1_19 = v0_10.getPackageManager().getInstallerPackageName(v0_10.getPackageName());
                    } catch (Exception) {
                        vn.ai.trueid.sdk.utils.a.b();
                        try {
                            // Both branches of the condition point to the same code.
                            // if (android.provider.Settings$Secure.getString(v0_10.getContentResolver(), "android_id") != null) {
                                String v0_2 = v0_10.getResources().getDisplayMetrics();
                                Iterable v1_1 = v0_2.widthPixels;
                                String v0_3 = v0_2.heightPixels;
                                String v2_1 = new StringBuilder();
                                v2_1.append(v1_1);
                                v2_1.append("x");
                                v2_1.append(v0_3);
                                v2_1.toString();
                                java.util.Locale.getDefault().toString();
                                String v0_6 = java.util.TimeZone.getDefault().getID();
                                if (v0_6 == null) {
                                    v0_6 = "Asia/Ho_Chi_Minh";
                                }
                                String v11 = "3.0.0";
                                return kotlin.collections.CollectionsKt.l(((Iterable) kotlin.collections.E.listOf(new String[] {"fp_v1", v0_6}))), "|", 0, 0, Ok.a.e, 30);
                            // }
                        } catch (Exception) {
                        }
                    }
                    // Both branches of the condition point to the same code.
                    // if ((v1_19 != null) && (!kotlin.text.StringsKt.g(v1_19, "vending"))) {
                    // }
                // }
            // }
        // }
    }

    public static String b()
    {
        String v1_5 = vn.ai.trueid.sdk.utils.Helpers.Companion.getContext();
        try {
            android.content.SharedPreferences$Editor v0_1 = z3.b.a(v1_5, "trueid_fingerprint", z3.c.a(z3.c.a));
        } catch (Exception) {
            v0_1 = v1_5.getSharedPreferences(v0_1, 0);
        }
        String v1_2 = v0_1.getString("install_id", 0);
        if (v1_2 == null) {
            v1_2 = kotlin.text.y.replace$default(java.util.UUID.randomUUID().toString(), "-", "", 0, 4, 0).toLowerCase(java.util.Locale.ROOT);
            v0_1.edit().putString("install_id", v1_2).apply();
        }
        return v1_2;
    }
}
