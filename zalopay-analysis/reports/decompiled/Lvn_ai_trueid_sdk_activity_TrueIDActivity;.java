package vn.ai.trueid.sdk.activity;
public final class TrueIDActivity extends vn.ai.trueid.sdk.activity.BaseActivity {
    public static final synthetic int n;
    public int i;
    public final ng.S j;
    public fk.d k;
    public Ek.d l;
    public XQ.i m;

    public TrueIDActivity()
    {
        this.i = androidx.appcompat.app.w.b;
        this.j = ng.x.b(ek.j.i);
        return;
    }

    public final void attachBaseContext(android.content.Context p4)
    {
        super.attachBaseContext(p4);
        m9.D2.b(p4);
        int v0_0 = new java.util.Locale(android.preference.PreferenceManager.getDefaultSharedPreferences(p4).getString("Locale.Helper.Selected.Language", java.util.Locale.getDefault().getLanguage()));
        android.os.LocaleList v4_4 = this.getBaseContext().getResources().getConfiguration();
        v4_4.setLocale(v0_0);
        this.getBaseContext().getResources().updateConfiguration(v4_4, this.getBaseContext().getResources().getDisplayMetrics());
        this.getBaseContext().getApplicationContext().getResources().updateConfiguration(v4_4, this.getBaseContext().getResources().getDisplayMetrics());
        vn.ai.trueid.sdk.utils.Helpers.Companion.setResources(this.getBaseContext().getApplicationContext().getResources());
        this.getBaseContext().getResources().getConfiguration().getLocales().get(0);
        return;
    }

    public final void onActivityResult(int p2, int p3, android.content.Intent p4)
    {
        super.onActivityResult(p2, p3, p4);
        Ek.d v4_1 = this.l;
        if (v4_1 != null) {
            v4_1.onActivityResult(p2, p3, this.getIntent());
        }
        return;
    }

    public final void onBackPressed()
    {
        if (android.os.Build$VERSION.SDK_INT == 29) {
            this.finishAfterTransition();
        }
        return;
    }

    public final void onCreate(android.os.Bundle p26)
    {
        this.i = androidx.appcompat.app.w.b;
        androidx.appcompat.app.w.l(1);
        super.onCreate(p26);
        int v2_22 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        v2_22.setSessionActive$trueid_release(1);
        this.setContentView(2131560349);
        String v3_3 = this.getViewModelStore();
        androidx.lifecycle.ViewModel v4_0 = this.getDefaultViewModelProviderFactory();
        DD.c v5_0 = this.getDefaultViewModelCreationExtras();
        StringBuilder v6_7 = kotlin.jvm.internal.M.a.b(fk.d);
        String v7_2 = v6_7.h();
        if (v7_2 == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        } else {
            String v7_3 = "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(v7_2);
            androidx.lifecycle.ViewModel v8_4 = v3_3.b(v7_3);
            if (!v6_7.r(v8_4)) {
                androidx.lifecycle.ViewModel v8_6 = new S2.e(v5_0);
                v8_6.b(U2.d.a, v7_3);
                androidx.lifecycle.ViewModel v4_1 = v4_0.create(v6_7, v8_6);
            } else {
                if ((v4_0 instanceof androidx.lifecycle.t0)) {
                    ((androidx.lifecycle.t0) v4_0).a(v8_4);
                }
            }
            this.k = ((fk.d) v8_4);
            m9.E2.a(this);
            DD.c v5_1 = 0;
            if (!v2_22.isRunOnlyNFC$trueid_release()) {
                if (!v2_22.isRunEnroll$trueid_release()) {
                    vn.ai.trueid.sdk.networks.q.g();
                    vn.ai.trueid.sdk.networks.q.d(new an.b(this));
                } else {
                    vn.ai.trueid.sdk.networks.B.d();
                    vn.ai.trueid.sdk.networks.B.b(new androidx.media3.extractor.mp4.g(12, this));
                }
            } else {
                kotlin.Pair v11_1;
                StringBuilder v6_15 = vn.ai.trueid.sdk.utils.Helpers.Companion;
                vn.ai.trueid.sdk.networks.U.c = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("Content-Type", "application/json"), kotlin.TuplesKt.to("User-Agent", v6_15.provideUserAgent()), kotlin.TuplesKt.to("ClientID", v2_22.getNFCConfig$trueid_release().getAppId())}));
                androidx.lifecycle.ViewModel v8_2 = kotlin.TuplesKt.to("platform", "android");
                String v3_4 = android.os.Build$VERSION.SDK_INT;
                kotlin.TuplesKt.to("os_version", String.valueOf(v3_4));
                if (v2_22.getDeviceInfo() == null) {
                    v11_1 = v6_15.getAdvId();
                } else {
                    v11_1 = v2_22.getDeviceInfo().getId();
                }
                int v26_1;
                kotlin.Pair v13_2;
                kotlin.Pair v16_0;
                kotlin.TuplesKt.to("device_id", v11_1);
                Nk.c v12_2 = kotlin.TuplesKt.to("app_version", v6_15.getAppVersion());
                if (v2_22.getDeviceInfo() == null) {
                    v13_2 = android.os.Build.MODEL;
                    androidx.core.app.e v1_1 = android.os.Build.MANUFACTURER;
                    v26_1 = v2_22;
                    v16_0 = v6_15;
                    if (!kotlin.text.y.startsWith$default(v13_2.toLowerCase(), v1_1.toLowerCase(), 0, 2, 0)) {
                        v13_2 = androidx.activity.result.e.o(v1_1, " ", v13_2);
                    }
                } else {
                    v13_2 = v2_22.getDeviceInfo().getModel();
                    v26_1 = v2_22;
                    v16_0 = v6_15;
                }
                StringBuilder v6_3;
                kotlin.TuplesKt.to("device_model", v13_2);
                int v2_2 = v16_0.getContext();
                if (v26_1.getDeviceInfo() == null) {
                    v6_3 = v16_0.getAdvId();
                } else {
                    v6_3 = v26_1.getDeviceInfo().getId();
                }
                kotlin.Pair v16_1 = kotlin.TuplesKt.to("id", v6_3);
                StringBuilder v15_2 = new StringBuilder();
                v15_2.append(android.os.Build.MANUFACTURER);
                v15_2.append(" ");
                v15_2.append(android.os.Build.MODEL);
                kotlin.TuplesKt.to("model", v15_2.toString());
                StringBuilder v6_8 = android.os.Build.DEVICE;
                kotlin.Pair v13_7 = "";
                if (v6_8 == null) {
                    v6_8 = "";
                }
                kotlin.TuplesKt.to("name", v6_8);
                kotlin.TuplesKt.to("appId", v2_2.getPackageName());
                kotlin.TuplesKt.to("installId", "none");
                kotlin.TuplesKt.to("platform", "Android");
                int v2_6 = android.os.Build.BRAND;
                if (v2_6 != 0) {
                    v13_7 = v2_6;
                }
                kotlin.TuplesKt.to("brandName", v13_7);
                StringBuilder v6_12 = new StringBuilder();
                v6_12.append(android.os.Build$VERSION.RELEASE);
                v6_12.append(" (SDK ");
                v6_12.append(v3_4);
                v6_12.append(")");
                kotlin.TuplesKt.to("os_version", v6_12.toString());
                kotlin.Pair v11 = v12_2;
                vn.ai.trueid.sdk.networks.U.b = kotlin.collections.a0.mutableMapOf(new kotlin.Pair[] {v8_2, kotlin.TuplesKt.to("device", kotlin.collections.a0.mapOf(new kotlin.Pair[] {v16_1, kotlin.TuplesKt.to("sdkVersion", "3.3.18")})))}));
                vn.ai.trueid.sdk.networks.Q.a(new androidx.core.app.e(13, this));
            }
            androidx.core.app.e v1_11 = this.k;
            if (v1_11 == null) {
                v1_11 = 0;
            }
            v1_11.c.f(this, new DD.c(1, this));
            androidx.core.app.e v1_13 = this.k;
            if (v1_13 != null) {
                v5_1 = v1_13;
            }
            v5_1.d.f(this, new DD.c(2, new W0.F(22, this)));
            this.m = new XQ.i(22, this);
            ((Nk.c) vn.ai.trueid.sdk.activity.TrueIDActivity v0.j.getValue()).a(this, 3, this.m);
            return;
        }
    }

    public final void onDestroy()
    {
        System.gc();
        int v0_0 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
        v0_0.releaseSession$trueid_release();
        v0_0.clearListener();
        v0_0.clearConfig();
        fk.a.a();
        super.onDestroy();
        androidx.appcompat.app.w.l(this.i);
        return;
    }

    public final void onNewIntent(android.content.Intent p3)
    {
        super.onNewIntent(p3);
        Ek.d v0 = this.l;
        if ((v0 != null) && ((v0 instanceof Ek.k))) {
            v0.z1(p3);
        }
        return;
    }

    public final void onPause()
    {
        String v0_0 = android.nfc.NfcAdapter.getDefaultAdapter(this);
    }

    public final void onResume()
    {
        int v2_0;
        super.onResume();
        XQ.i v0_0 = android.nfc.NfcAdapter.getDefaultAdapter(this);
        Nk.c v1_5 = new android.content.Intent(this, vn.ai.trueid.sdk.activity.TrueIDActivity);
        v1_5.addFlags(536870912);
        int v2_4 = android.os.Build$VERSION.SDK_INT;
        if (v2_4 < 34) {
            v2_0 = 0;
        } else {
            int v3_0 = android.app.ActivityOptions.makeBasic();
            if (v2_4 < 35) {
                v3_0.setPendingIntentBackgroundActivityStartMode(1);
            } else {
                v3_0.setPendingIntentCreatorBackgroundActivityStartMode(1);
            }
            v2_0 = v3_0.toBundle();
        }
        android.app.PendingIntent vtmp6 = android.app.PendingIntent.getActivity(this, 0, v1_5, 33554432, v2_0);
        if (v0_0 != null) {
            v0_0.enableForegroundDispatch(this, vtmp6, 0, 0);
        }
        XQ.i v0_1 = this.m;
        if (v0_1 != null) {
            ((Nk.c) this.j.getValue()).a(this, 3, v0_1);
        }
        return;
    }

    public final void onResumeFragments()
    {
        super.onResumeFragments();
        vn.ai.trueid.sdk.utils.Helpers.Companion.setResources(this.getResources());
        return;
    }
}
