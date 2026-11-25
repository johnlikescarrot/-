package vn.ai.trueid.sdk.activity;
public class BaseActivity extends androidx.appcompat.app.AppCompatActivity {

    public BaseActivity()
    {
        return;
    }

    public void attachBaseContext(android.content.Context p4)
    {
        super.attachBaseContext(m9.D2.b(p4));
        android.content.res.Resources v0_1 = new java.util.Locale(android.preference.PreferenceManager.getDefaultSharedPreferences(p4).getString("Locale.Helper.Selected.Language", java.util.Locale.getDefault().getLanguage()));
        vn.ai.trueid.sdk.utils.Helpers$Companion v4_4 = this.getBaseContext().getResources().getConfiguration();
        v4_4.setLocale(v0_1);
        this.getBaseContext().getResources().updateConfiguration(v4_4, this.getBaseContext().getResources().getDisplayMetrics());
        this.getBaseContext().getApplicationContext().getResources().updateConfiguration(v4_4, this.getBaseContext().getResources().getDisplayMetrics());
        vn.ai.trueid.sdk.utils.Helpers.Companion.setResources(this.getBaseContext().getApplicationContext().getResources());
        return;
    }

    public void onCreate(android.os.Bundle p1)
    {
        super.onCreate(p1);
        boolean v1_1 = this.getSupportActionBar();
        if (v1_1) {
            v1_1.j();
        }
        if (!vn.ai.trueid.sdk.utils.Helpers.Companion.verifyInternal()) {
            this.finish();
        }
        return;
    }

    public void onPause()
    {
        int v0_0 = m9.F2.a;
        if (v0_0 != 0) {
            v0_0.dismiss();
        }
        m9.F2.a = 0;
        super.onPause();
        return;
    }

    public final void onWindowFocusChanged(boolean p2)
    {
        super.onWindowFocusChanged(p2);
        if (p2 != null) {
            this.getWindow().getDecorView().setSystemUiVisibility(4098);
        }
        return;
    }
}
