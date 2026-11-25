package vn.com.vng.zalopay.tenpay;
public final class TPGPaymentActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int u;
    public kO.b i;
    public Gy.a j;
    public pO.c k;
    public lO.a l;
    public lO.c m;
    public lO.j n;
    public final androidx.activity.result.d o;
    public final androidx.activity.result.d p;
    public androidx.lifecycle.r0 q;
    public KG.a r;
    public qO.a s;
    public final O5.b t;

    public TPGPaymentActivity()
    {
        this.o = this.registerForActivityResult(new androidx.fragment.app.f0(2), new kO.g(this, 0));
        this.p = this.registerForActivityResult(new androidx.fragment.app.f0(6), new kO.g(this, 1));
        this.t = new O5.b(kotlin.jvm.internal.M.a.b(kO.p), new kO.h(this, 0), new kO.d(this, 1), new kO.h(this, 1));
        return;
    }

    public final kO.p e1()
    {
        return ((kO.p) this.t.getValue());
    }

    public final void g1(lO.c p5)
    {
        int v0_8;
        int v0_2 = this.getSystemService("location");
        if (!(v0_2 instanceof android.location.LocationManager)) {
            v0_8 = 0;
        } else {
            v0_8 = ((android.location.LocationManager) v0_2);
        }
        if (v0_8 != 0) {
            int v0_3;
            if (android.os.Build$VERSION.SDK_INT < 28) {
                if ((!v0_8.isProviderEnabled("network")) && (!v0_8.isProviderEnabled("gps"))) {
                    v0_3 = 0;
                } else {
                    v0_3 = 1;
                }
            } else {
                v0_3 = MD.u.r(v0_8);
            }
            if (v0_3 != 0) {
                new com.google.android.gms.location.d(this, com.google.android.gms.location.i.a, com.google.android.gms.common.api.c.a, new E8.a()).c(102, new com.google.android.gms.tasks.g(4)).addOnSuccessListener(new ju.c(7, new jV.k(4, p5))).addOnFailureListener(new ju.c(8, p5));
                return;
            } else {
                p5.b.invoke("Something went wrong");
                return;
            }
        } else {
            return;
        }
    }

    public final void onCreate(android.os.Bundle p14)
    {
        int v1_5;
        super.onCreate(p14);
        YW.h v14_1 = q9.m6.a;
        if (v14_1 != null) {
            int v1_20 = q9.m6.b;
            if (v1_20 == 0) {
                v1_20 = gi.N.d();
                q9.m6.b = v1_20;
            }
            v1_5 = new ub.c(new h3.j(gi.L.a(v1_20), v14_1), new kc.a());
        } else {
            v1_5 = 0;
        }
        if (v1_5 == 0) {
            this.finish();
            return;
        } else {
            this.q = ((androidx.lifecycle.r0) ((dagger.internal.o) v1_5.h).get());
            YW.h v14_16 = ((h3.j) v1_5.b);
            int v1_19 = ((vO.b) v14_16.b).b();
            p9.n7.c(v1_19);
            this.r = v1_19;
            this.s = ((vO.b) v14_16.b).n();
            YW.h v14_21 = this.getIntent().getStringExtra("qrcode_url");
            if (v14_21 != null) {
                int v1_24;
                int v1_23 = this.getIntent().getIntExtra("runtime_environment", 0);
                if (v1_23 == 0) {
                    v1_24 = kO.r.d;
                } else {
                    kO.r.a.getClass();
                    if (v1_23 == 2) {
                        v1_24 = kO.r.c;
                    } else {
                        if (v1_23 == 3) {
                            v1_24 = kO.r.d;
                        } else {
                            if (v1_23 == 5) {
                                v1_24 = kO.r.b;
                            } else {
                                v1_24 = kO.r.d;
                            }
                        }
                    }
                }
                this.j = new Gy.a(9, this);
                String v4_9 = this.getOnBackPressedDispatcher();
                int v5_1 = this.j;
                if (v5_1 == 0) {
                    v5_1 = 0;
                }
                v4_9.a(this, v5_1);
                String v4_11 = new kO.b(this);
                v4_11.setRuntimeEnvironment(v1_24);
                this.k = new pO.c(0, v4_11);
                v4_11.setOnNavigationStateChanged(new kO.e(this, 1));
                int v1_30 = new lO.o(0, 0);
                int v7_15 = new mO.b[1];
                v7_15[0] = new lO.r(new bJ.e(this, v14_21, 20));
                androidx.lifecycle.A v6_13 = v7_15[0];
                int v7_1 = ((mO.a) v6_13.getClass().getAnnotation(mO.a));
                if (v7_1 != 0) {
                    int v7_2 = v7_1.actionNames();
                    if (v7_2.length != 0) {
                        v6_13.onCreate();
                        int v8_2 = new java.util.ArrayList();
                        iC.b v9_0 = v7_2.length;
                        int v10_0 = 0;
                        while (v10_0 < v9_0) {
                            int v11_0 = v7_2[v10_0];
                            if (v11_0.length() > 0) {
                                v8_2.add(v11_0);
                            }
                            v10_0++;
                        }
                        int v7_3 = v8_2.iterator();
                        while (v7_3.hasNext()) {
                            ((java.util.LinkedHashMap) v1_30.b).put(((String) v7_3.next()), v6_13);
                        }
                    }
                }
                int v8_6 = this.k;
                if (v8_6 == 0) {
                    v8_6 = 0;
                }
                v4_11.setJsInterface(kotlin.collections.C.listOf(new kotlin.Pair(new pO.b(v1_30, v8_6), "TPGBridge")));
                int v1_3 = v4_11.getSettings();
                androidx.lifecycle.A v6_2 = android.webkit.WebSettings.getDefaultUserAgent(this);
                int v7_7 = new StringBuilder();
                v7_7.append(v6_2);
                v7_7.append(" MicroMessenger/8.0.43(0x18002B2D) NetType/WIFI Language/zh_CN");
                v1_3.setUserAgentString(v7_7.toString());
                try {
                    int v7_10;
                    v4_11.f.clear();
                    androidx.lifecycle.A v6_6 = v4_11.f;
                    int v7_8 = v4_11.a;
                    iC.b v9_4 = kotlin.collections.C.listOf("open.weixin.qq.com");
                    int v7_9 = v7_8.ordinal();
                } catch (y0.b v0_0) {
                    throw v0_0;
                }
                if (v7_9 == 0) {
                    v7_10 = "https://uat.tenpayglobal.com/sg/inbound-payment/merchant-oauth.shtml";
                } else {
                    if (v7_9 != 1) {
                        if (v7_9 != 2) {
                            throw new kotlin.NoWhenBranchMatchedException();
                        } else {
                        }
                    }
                    v7_10 = "https://www.tenpayglobal.com/sg/inbound-payment/merchant-oauth.shtml";
                }
                v6_6.add(new lO.m("__TPGRuntime.handleNavigation", v7_10, v9_4));
                try {
                    int v1_6 = v4_11.getSettings();
                    v1_6.setJavaScriptEnabled(1);
                    v1_6.setDomStorageEnabled(1);
                    v1_6.setDatabaseEnabled(1);
                    v1_6.setMediaPlaybackRequiresUserGesture(0);
                    android.webkit.CookieManager.getInstance().setAcceptThirdPartyCookies(v4_11, 1);
                    v4_11.setWebViewClient(new com.facebook.internal.b0(4, v4_11));
                } catch (Throwable) {
                }
                int v1_12 = ((Iterable) v4_11.c).iterator();
            } else {
                this.finish();
                return;
            }
        }
    }

    public final void onDestroy()
    {
        Throwable v0_0 = this.i;
        if (v0_0 != null) {
            v0_0.g = 1;
            boolean v2_3 = v0_0.h.iterator();
            while (v2_3.hasNext()) {
                v0_0.removeCallbacks(((Runnable) v2_3.next()));
            }
            int v1_1;
            v0_0.h.clear();
            int v1_0 = v0_0.getParent();
            if (!(v1_0 instanceof android.view.ViewGroup)) {
                v1_1 = 0;
            } else {
                v1_1 = ((android.view.ViewGroup) v1_0);
            }
            if (v1_1 != 0) {
                v1_1.removeView(v0_0);
            }
            v0_0.stopLoading();
            v0_0.clearHistory();
            v0_0.removeAllViews();
            v0_0.destroy();
        }
        super.onDestroy();
        return;
    }

    public final void onPause()
    {
        kO.b v0 = this.i;
        if (v0 != null) {
            v0.onPause();
        }
        super.onPause();
        return;
    }

    public final void onRequestPermissionsResult(int p1, String[] p2, int[] p3)
    {
        super.onRequestPermissionsResult(p1, p2, p3);
        if (p1 == 1001) {
            lO.q v1_2 = this.m;
            if (v1_2 != null) {
                this.m = 0;
                String v2_5 = kotlin.collections.y.firstOrNull(p3);
                if ((v2_5 == null) || (v2_5.intValue() != 0)) {
                    v1_2.b.invoke("Location permission denied");
                    return;
                } else {
                    this.g1(v1_2);
                    return;
                }
            }
        }
        return;
    }

    public final void onResume()
    {
        super.onResume();
        kO.b v0 = this.i;
        if (v0 != null) {
            v0.onResume();
        }
        return;
    }
}
