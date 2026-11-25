package vn.ai.trueid.sdk.face.present.presentation.utils;
public final class LocaleHelper {
    public static final vn.ai.trueid.sdk.face.present.presentation.utils.LocaleHelper INSTANCE = None;
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    public static LocaleHelper()
    {
        vn.ai.trueid.sdk.face.present.presentation.utils.LocaleHelper.INSTANCE = new vn.ai.trueid.sdk.face.present.presentation.utils.LocaleHelper();
        return;
    }

    private LocaleHelper()
    {
        return;
    }

    private final String getPersistedData(android.content.Context p2, String p3)
    {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(p2).getString("Locale.Helper.Selected.Language", p3);
    }

    private final void persist(android.content.Context p2, String p3)
    {
        android.content.SharedPreferences$Editor v2_2 = android.preference.PreferenceManager.getDefaultSharedPreferences(p2).edit();
        if (p3 == null) {
            p3 = "en";
        }
        v2_2.putString("Locale.Helper.Selected.Language", p3);
        v2_2.apply();
        v2_2.commit();
        return;
    }

    public final String getLanguage(android.content.Context p2)
    {
        return this.getPersistedData(p2, java.util.Locale.getDefault().getLanguage());
    }

    public final android.content.res.Resources getResources(android.content.Context p1)
    {
        return this.onAttach(p1).getResources();
    }

    public final android.content.Context onAttach(android.content.Context p2)
    {
        return this.setLocale(p2, this.getPersistedData(p2, java.util.Locale.getDefault().getLanguage()));
    }

    public final android.content.Context onAttach(android.content.Context p1, String p2)
    {
        return this.setLocale(p1, this.getPersistedData(p1, p2));
    }

    public final android.content.Context setLocale(android.content.Context p10, String p11)
    {
        this.persist(p10, p11);
        android.content.res.Resources v0_2 = new java.util.Locale(p11);
        java.util.Locale.setDefault(v0_2);
        android.util.DisplayMetrics v1_2 = new android.content.res.Configuration(p10.getResources().getConfiguration());
        v1_2.setLocale(v0_2);
        if ((p11 != null) && ((kotlin.text.StringsKt.g(p11, "-r")) || (kotlin.text.StringsKt.g(p11, "-")))) {
            android.content.res.Resources v0_4;
            android.util.Log.e("FaceSDK", "language ".concat(p11));
            if (!kotlin.text.StringsKt.g(p11, "-r")) {
                v0_4 = kotlin.text.StringsKt.y(p11, new String[] {"-"}), 0, 6);
            } else {
                v0_4 = kotlin.text.StringsKt.y(p11, new String[] {"-r"}), 0, 6);
            }
            if (v0_4.size() == 2) {
                java.util.Locale v11_2 = java.util.Locale.forLanguageTag(kotlin.text.y.replace$default(p11, "-r", "-", 0, 4, 0));
                java.util.Locale.setDefault(v11_2);
                android.content.res.Resources v0_7 = p10.getResources();
                android.util.DisplayMetrics v1_1 = v0_7.getDisplayMetrics();
                android.content.res.Configuration v2_7 = v0_7.getConfiguration();
                v2_7.locale = v11_2;
                v0_7.updateConfiguration(v2_7, v1_1);
                return p10;
            }
        }
        return p10.createConfigurationContext(v1_2);
    }
}
