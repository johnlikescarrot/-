package vn.com.vng.zalopay.webapp.activity;
public class WebAppExternalActivity extends vn.com.vng.zalopay.webapp.activity.WebAppBaseToolbarActivity {
    public static final synthetic int z;
    android.view.View backView;
    android.widget.ImageButton btnBack;
    android.widget.ImageButton btnClose;
    android.view.View closeView;
    android.view.View externalButtonView;
    androidx.appcompat.widget.Toolbar toolbar;
    public final OD.a v;
    public java.util.ArrayList w;
    public final android.content.SharedPreferences x;
    public boolean y;

    public WebAppExternalActivity()
    {
        this.v = new OD.a("", -1);
        android.content.SharedPreferences v0_1 = 0;
        this.w = 0;
        CC.f v1_0 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v1_0 == null) {
            v1_0 = 0;
        }
        CC.f v1_1 = v1_0.a;
        if (v1_1 != null) {
            v0_1 = v1_1;
        }
        this.x = v0_1.H();
        return;
    }

    public final androidx.fragment.app.Fragment g1()
    {
        android.os.Bundle v0_1 = this.getIntent().getExtras();
        WQ.t v1_1 = new WQ.t();
        v1_1.setArguments(v0_1);
        return v1_1;
    }

    public final int h1()
    {
        return 2131560389;
    }

    public final OD.a i1()
    {
        return this.v;
    }

    public void onBackWebAppClicked()
    {
        if (!this.y) {
            this.onBackPressed();
            return;
        } else {
            this.finish();
            return;
        }
    }

    public void onCloseWebAppClicked()
    {
        this.finish();
        return;
    }

    public final void onCreate(android.os.Bundle p8)
    {
        super.onCreate(p8);
        if (android.os.Build$VERSION.SDK_INT > 26) {
            this.getWindow().setLayout(-1, -1);
            android.widget.ImageButton v8_4 = this.getIntent().getExtras();
            if (!v8_4.isEmpty()) {
                android.widget.ImageButton v8_5 = v8_4.getBundle("app_web_params");
                if ((v8_5 != null) && (v8_5.containsKey("percentage-screen"))) {
                    android.content.res.ColorStateList v1_4 = Double.parseDouble(v8_5.get("percentage-screen").toString());
                    if (v1_4 < 4636737291354636288) {
                        android.widget.ImageButton v8_12 = ((int) ((((double) this.getResources().getDisplayMetrics().heightPixels) * v1_4) / 4636737291354636288));
                        this.getWindow().setGravity(8388691);
                        this.getWindow().setLayout(-1, v8_12);
                        this.getWindow().addFlags(2);
                        this.getWindow().setDimAmount(1056964608);
                        this.getWindow().setBackgroundDrawableResource(2131100832);
                        this.toolbar.setBackgroundResource(2131231926);
                        this.tvTitle.setTextColor(this.getColor(2131100595));
                        this.btnClose.setImageTintList(android.content.res.ColorStateList.valueOf(this.getColor(2131100595)));
                        this.btnBack.setImageTintList(android.content.res.ColorStateList.valueOf(this.getColor(2131100595)));
                        this.btnBack.setVisibility(8);
                    }
                }
            }
        }
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().s(0);
            this.getSupportActionBar().u();
        }
        this.y = 1;
        android.widget.ImageButton v8_27 = this.x;
        if (v8_27.getLong("pref_webview_background_timestamp", 0) != 0) {
            v8_27.edit().remove("pref_webview_background_timestamp").apply();
        }
        return;
    }

    public final void onPause()
    {
        super.onPause();
        this.x.edit().putLong("pref_webview_background_timestamp", System.currentTimeMillis()).apply();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        long v0_3 = this.x.getLong("pref_webview_background_timestamp", 0);
        if ((v0_3 != 0) && ((System.currentTimeMillis() - v0_3) >= 3600000)) {
            this.finish();
        }
        return;
    }

    public final void p1(String p4)
    {
        if (java.util.regex.Pattern.compile("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})").matcher(p4).matches()) {
            try {
                this.r.c(com.google.android.gms.internal.mlkit_vision_barcode.y0.a(new vn.com.vng.zalopay.webapp.activity.a(this, p4, 1)));
            } catch (vn.zalopay.ui.navigationbar.NavigationBar v0_1) {
                v0_1.getMessage();
            }
            this.navigationBar.setTitleColor(android.graphics.Color.parseColor(p4));
            return;
        } else {
            return;
        }
    }

    public final void q1(java.util.ArrayList p4)
    {
        android.widget.LinearLayout v0_1 = ((android.widget.LinearLayout) this.externalButtonView);
        java.util.Iterator v1 = p4.iterator();
        while (v1.hasNext()) {
            v0_1.addView(((android.widget.ImageButton) v1.next()));
        }
        v0_1.invalidate();
        this.w = p4;
        return;
    }

    public final void r1(boolean p3)
    {
        android.view.View v0 = this.backView;
        if (v0 != null) {
            if (p3 == 0) {
                v0.setVisibility(8);
            } else {
                v0.setVisibility(0);
            }
            this.y = (p3 ^ 1);
            return;
        } else {
            return;
        }
    }

    public final void s1(boolean p3)
    {
        vn.zalopay.ui.navigationbar.NavigationBar v0_0 = this.closeView;
        if (v0_0 != null) {
            if (!p3) {
                v0_0.setVisibility(8);
            } else {
                v0_0.setVisibility(0);
            }
            this.navigationBar.setIsShowCloseButton(p3);
            return;
        } else {
            return;
        }
    }

    public final void t1()
    {
        int v0_0 = this.w;
        if (v0_0 != 0) {
            android.widget.LinearLayout v1_1 = ((android.widget.LinearLayout) this.externalButtonView);
            int v0_2 = v0_0.iterator();
            while (v0_2.hasNext()) {
                v1_1.removeView(((android.widget.ImageButton) v0_2.next()));
            }
            v1_1.invalidate();
            this.w = 0;
            return;
        } else {
            return;
        }
    }
}
