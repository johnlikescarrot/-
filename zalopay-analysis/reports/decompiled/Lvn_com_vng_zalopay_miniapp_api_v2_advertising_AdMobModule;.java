package vn.com.vng.zalopay.miniapp.api.v2.advertising;
public final class AdMobModule implements LR.a {
    public static final int d = 8;
    private final wm.d a;
    private ref.WeakReference b;
    private int c;

    public AdMobModule(wm.d p1)
    {
        this.a = p1;
        return;
    }

    private final void c()
    {
        int v0_0 = this.b;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        int v0_4 = ((android.app.Activity) v0_0.get());
        if (v0_4 != 0) {
            int v0_5 = v0_4.getWindow();
            if ((v0_5 != 0) && (android.os.Build$VERSION.SDK_INT >= 28)) {
                this.c = com.google.gson.internal.c.b(v0_5.getAttributes());
            }
        }
        return;
    }

    private final void d(org.json.JSONObject p1, MR.b p2)
    {
        this.f(p1, p2);
        return;
    }

    private final void e(org.json.JSONObject p1, MR.b p2)
    {
        this.i(p1, p2);
        return;
    }

    private final void f(org.json.JSONObject p14, MR.b p15)
    {
        zm.a v3;
        java.util.List v0_0 = 0;
        if (p14 == null) {
            v3 = 0;
        } else {
            li.e v14_7 = p14.toString();
            if (v14_7 == null) {
            } else {
                v3 = ((zm.a) nY.h.b(v14_7, new vn.com.vng.zalopay.miniapp.api.v2.advertising.AdMobModule$loadRewardedAd$$inlined$parseJson$1().getType()));
            }
        }
        if (v3 == null) {
            if (p15 != null) {
                p15.h(ZF.m.c);
            }
        } else {
            wm.d v6 = this.a;
            li.e v14_4 = this.b;
            if (v14_4 != null) {
                v0_0 = v14_4;
            }
            android.app.Activity v2_1 = ((android.app.Activity) v0_0.get());
            if (v2_1 != null) {
                lS.b v5_1 = new lS.b(3, p15);
                v6.getClass();
                li.e v14_9 = new Ae.r(22);
                f8.G0 v15_3 = ((f8.G0) v14_9.b);
                java.util.List v0_1 = v3.b();
                if (v0_1 != null) {
                    CS.c v1_0 = ((Iterable) v0_1).iterator();
                    while (v1_0.hasNext()) {
                        v15_3.a.add(((String) v1_0.next()));
                    }
                }
                java.util.List v0_3 = v3.c();
                if (v0_3 != null) {
                    v15_3.h = v0_3;
                }
                kotlinx.coroutines.BuildersKt.launch$default(gi.L.a(v6.a), 0, 0, new CS.c(v2_1, v3, new X7.g(v14_9), v5_1, v6, 0, 21), 3, 0);
                return;
            }
        }
        return;
    }

    private final void i(org.json.JSONObject p19, MR.b p20)
    {
        int v11;
        ZF.m v3 = 0;
        if (p19 == null) {
            v11 = 0;
        } else {
            v11 = p19.optString("adId");
        }
        if ((v11 != 0) && (v11.length() != 0)) {
            wm.d v10 = this.a;
            kotlin.Unit v1_2 = this.b;
            if (v1_2 != null) {
                v3 = v1_2;
            }
            android.app.Activity v6_1 = ((android.app.Activity) v3.get());
            if (v6_1 != null) {
                h3.j v9_1 = new h3.j(this, p20);
                com.google.android.gms.internal.ads.Qd v5_1 = ((com.google.android.gms.internal.ads.Qd) v10.b.get(v11));
                if (v5_1 != null) {
                    kotlin.jvm.internal.L v7_1 = new kotlin.jvm.internal.L();
                    kotlin.jvm.internal.L v8_1 = new kotlin.jvm.internal.L();
                    kotlin.Unit v1_7 = gi.L.a(v10.a);
                    ES.d v15 = new ES.d;
                    v15(v5_1, v6_1, v7_1, v8_1, v9_1, v10, v11, 0, 5);
                    if (kotlinx.coroutines.BuildersKt.launch$default(v1_7, 0, 0, v15, 3, 0) != null) {
                        return;
                    }
                }
                v9_1.k(202001, "The rewarded ad wasn\'t ready yet.");
                return;
            }
        } else {
            if (p20 != null) {
                p20.h(ZF.m.c);
            }
        }
        return;
    }

    public void a(String p2, org.json.JSONObject p3, MR.b p4)
    {
        if (p2) {
            switch (p2.hashCode()) {
                case -1224959092:
                    if (p2.equals("loadRewardedBiddingGAM")) {
                        this.d(p3, p4);
                    }
                    break;
                case -290964112:
                    if (p2.equals("loadRewardedGoogleAdMob")) {
                        this.f(p3, p4);
                        return;
                    }
                case -84608541:
                    if (p2.equals("showRewardedBiddingGAM")) {
                        this.e(p3, p4);
                        return;
                    }
                case 700164601:
                    if (p2.equals("showRewardedGoogleAdMob")) {
                        this.i(p3, p4);
                        return;
                    }
                default:
            }
        }
        return;
    }

    public final void b(ref.WeakReference p1)
    {
        this.b = p1;
        this.c();
        return;
    }

    public void g()
    {
        return;
    }

    public final void h(boolean p4)
    {
        android.view.Window v0_0 = this.b;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        android.view.Window v0_2 = ((android.app.Activity) v0_0.get());
        if (v0_2 != null) {
            android.view.Window v0_3 = v0_2.getWindow();
            if ((v0_3 != null) && (android.os.Build$VERSION.SDK_INT >= 28)) {
                int v4_1;
                android.view.WindowManager$LayoutParams v1_1 = v0_3.getAttributes();
                if (p4 == 0) {
                    v4_1 = 2;
                } else {
                    v4_1 = this.c;
                }
                Ua.d.d(v1_1, v4_1);
                v0_3.setAttributes(v1_1);
            }
        }
        return;
    }

    public void onCreate()
    {
        return;
    }
}
