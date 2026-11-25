package vn.com.vng.zalopay.livenessdetection.presentation;
public final class FaceLivenessCameraXActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int n;
    public final ng.S i;
    public final ng.S j;
    public Jv.a k;
    public float l;
    public boolean m;

    public FaceLivenessCameraXActivity()
    {
        this.i = ng.x.b(new mS.a(6, this));
        this.j = ng.x.b(new oM.h(24));
        this.l = -1082130432;
        return;
    }

    public final void onCreate(android.os.Bundle p6)
    {
        super.onCreate(p6);
        wD.l.a = ((wD.c) this.i.getValue()).n;
        AH.d v1_1 = 0;
        androidx.activity.G v6_4 = this.getLayoutInflater().inflate(2131558900, 0, 0);
        Throwable v0_10 = 2131362802;
        if (((androidx.fragment.app.FragmentContainerView) l5.s.a(2131362802, v6_4)) != null) {
            Throwable v0_12 = ((androidx.constraintlayout.widget.ConstraintLayout) v6_4);
            String v4_0 = l5.s.a(2131364265, v6_4);
            if (v4_0 == null) {
                v0_10 = 2131364265;
            } else {
                this.k = new Jv.a(4, v4_0, v0_12);
                this.setContentView(v0_12);
                androidx.activity.G v6_12 = q9.u6.a(this);
                if (v6_12 != null) {
                    androidx.activity.G v6_13 = v6_12.getWindow();
                    if (v6_13 != null) {
                        if (android.os.Build$VERSION.SDK_INT < 30) {
                            androidx.activity.G v6_15 = v6_13.getDecorView();
                            v6_15.setSystemUiVisibility((v6_15.getSystemUiVisibility() | 8192));
                        } else {
                            androidx.activity.G v6_16 = v6_13.getInsetsController();
                            if (v6_16 != null) {
                                v6_16.setSystemBarsAppearance(8, 8);
                            }
                        }
                    }
                }
                androidx.activity.G v6_20;
                androidx.activity.G v6_19 = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (v6_19 <= null) {
                    v6_20 = 0;
                } else {
                    v6_20 = this.getResources().getDimensionPixelSize(v6_19);
                }
                Throwable v0_21 = this.k;
                if (v0_21 == null) {
                    v0_21 = 0;
                }
                Throwable v0_23 = v0_21.c.getLayoutParams();
                v0_23.height = v6_20;
                androidx.activity.G v6_21 = this.k;
                if (v6_21 == null) {
                    v6_21 = 0;
                }
                v6_21.c.setLayoutParams(v0_23);
                androidx.activity.G v6_23 = this.k;
                if (v6_23 != null) {
                    v1_1 = v6_23;
                }
                com.google.android.gms.internal.mlkit_vision_barcode.W7.b(((androidx.constraintlayout.widget.ConstraintLayout) v1_1.b), 0);
                Throwable v0_24 = this.getApplication();
                if (vD.a.b == null) {
                    try {
                        vD.a.b = v0_24;
                    } catch (Throwable v0_1) {
                        throw v0_1;
                    }
                }
                Throwable v0_2 = this.getApplication();
                if (aD.b.d == null) {
                    try {
                        aD.b.d = v0_2;
                    } catch (Throwable v0_3) {
                        throw v0_3;
                    }
                }
                this.getLifecycle().a(new AH.d(this.getLifecycle(), new Cc.f(kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("method", "S5"))), 1));
                this.getOnBackPressedDispatcher().a(this, new tD.a(this));
                return;
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v6_4.getResources().getResourceName(v0_10)));
    }

    public final void onPause()
    {
        super.onPause();
        int v0_1 = this.getWindow();
        android.view.WindowManager$LayoutParams v1_1 = this.getWindow().getAttributes();
        v1_1.screenBrightness = this.l;
        v0_1.setAttributes(v1_1);
        this.m = 0;
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if (!this.m) {
            int v0_2 = this.getWindow();
            android.view.WindowManager$LayoutParams v1_1 = this.getWindow().getAttributes();
            this.l = v1_1.screenBrightness;
            v1_1.screenBrightness = 1065353216;
            v0_2.setAttributes(v1_1);
            this.m = 1;
            return;
        } else {
            return;
        }
    }

    public final void onStop()
    {
        super.onStop();
        JD.g v0 = com.google.android.gms.internal.mlkit_vision_barcode.L7.c();
        java.util.Map v1 = kotlin.collections.a0.emptyMap();
        java.util.concurrent.ConcurrentHashMap v2 = v0.a;
        java.util.Iterator v3_3 = new java.util.LinkedHashMap();
        java.util.Map v4_1 = v2.entrySet().iterator();
        while (v4_1.hasNext()) {
            String v5_5 = ((java.util.Map$Entry) v4_1.next());
            if (((JD.b) v5_5.getValue()).d == null) {
                v3_3.put(v5_5.getKey(), v5_5.getValue());
            }
        }
        java.util.Iterator v3_1 = v3_3.entrySet().iterator();
        while (v3_1.hasNext()) {
            java.util.Map v4_4 = ((java.util.Map$Entry) v3_1.next());
            String v5_2 = ((String) v4_4.getKey());
            java.util.Map v4_7 = ((JD.b) v4_4.getValue()).b(v1);
            v2.put(v5_2, JD.b.a(v4_7, 0, kotlin.collections.a0.plus(kotlin.collections.a0.plus(v4_7.e, v0.b()), kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("status", "not complete"))), 15));
            java.util.Objects.toString(v2.get(v5_2));
            java.util.Map v4_11 = ((JD.b) v2.get(v5_2));
            if (v4_11 != null) {
                JD.c.a(v4_11.a, v4_11.e);
            }
        }
        return;
    }
}
