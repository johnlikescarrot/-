package vn.com.vng.zalopay.navigation;
public final class b {
    public final kc.f a;
    public final Pt.n b;

    public b(Pt.n p2)
    {
        this.a = new kc.f();
        this.b = p2;
        return;
    }

    public static void h(android.content.Context p2, android.os.Bundle p3)
    {
        android.content.Intent v0_1 = new android.content.Intent(p2, vn.com.vng.zalopay.scanqr.presentation.v2.ScanQRActivityV2);
        if (p3 != null) {
            v0_1.putExtras(p3);
        }
        p2.startActivity(v0_1);
        return;
    }

    public final void a(android.content.Context p3, String p4, String p5)
    {
        try {
            String v0_2 = new android.content.Intent("android.intent.action.VIEW", android.net.Uri.parse(p4));
            v0_2.addFlags(268435456);
            p3.startActivity(v0_2);
            return;
        } catch (boolean v4_6) {
            ck.b.a("No Activity found to handle Intent [%s]", new Object[] {v4_6.toString()}));
            if (!android.text.TextUtils.isEmpty(p5)) {
                com.google.android.gms.internal.mlkit_vision_barcode.x0.d(p3, p5);
            }
            return;
        }
    }

    public final void b(android.content.Context p4)
    {
        this.a.getClass();
        try {
            android.content.Intent v1_1 = new android.content.Intent("android.intent.action.DIAL");
            StringBuilder v2_2 = new StringBuilder("tel:");
            v2_2.append(p4.getString(2131954029));
            v1_1.setData(android.net.Uri.parse(v2_2.toString()));
            v1_1.setFlags(268435456);
            p4.startActivity(v1_1);
        } catch (Exception) {
        }
        return;
    }

    public final void c(android.content.Context p3, String p4, boolean p5)
    {
        if (!Um.a.c(vn.com.vng.zalopay.homev5.presenter.ui.home.HomeActivity)) {
            if (!Um.a.c(vn.com.vng.zalopay.ui.activity.HomeActivity)) {
                android.content.Intent v4_1 = new android.content.Intent(p3, vn.com.vng.zalopay.homev5.presenter.ui.routing.HomeRoutingActivity).putExtra("tab", p4);
                if (p5 != 0) {
                    v4_1.setFlags(67108864);
                }
                p3.startActivity(v4_1);
                return;
            } else {
                this.f(p3, p4, p5);
                return;
            }
        } else {
            this.e(p3, p4, p5);
            return;
        }
    }

    public final void d(android.app.Activity p3)
    {
        if (this.b.i()) {
            android.content.Intent v0_3 = new android.content.Intent(p3, vn.com.vng.zalopay.lockscreen.LockScreenActivity);
            v0_3.addFlags(131072);
            p3.startActivityForResult(v0_3, 142);
            return;
        } else {
            return;
        }
    }

    public final void e(android.content.Context p3, String p4, boolean p5)
    {
        android.content.Intent v0_2 = new android.content.Intent(p3, vn.com.vng.zalopay.homev5.presenter.ui.home.HomeActivity).putExtra("tab", p4);
        if (p5 != null) {
            v0_2.addFlags(335544320);
        }
        p3.startActivity(v0_2);
        if ((p4) && (p4.equals("userprofile"))) {
            this.g(p3);
        }
        return;
    }

    public final void f(android.content.Context p3, String p4, boolean p5)
    {
        android.content.Intent v0_2 = new android.content.Intent(p3, vn.com.vng.zalopay.ui.activity.HomeActivity).putExtra("tab", p4);
        if (p5 != null) {
            v0_2.addFlags(335544320);
        }
        p3.startActivity(v0_2);
        if ((p4) && (p4.equals("userprofile"))) {
            this.g(p3);
        }
        return;
    }

    public final void g(android.content.Context p4)
    {
        if (com.google.android.gms.internal.mlkit_vision_barcode.b0.a == null) {
            com.google.android.gms.internal.mlkit_vision_barcode.b0.a = new com.google.android.gms.internal.gtm.I(new d6.u(), q9.a5.b());
        }
        android.content.Intent v0_1 = new android.content.Intent(p4, vn.com.vng.zalopay.user_profile.presentation.profiledetails.ProfileDetailsActivity);
        if (this.b.i()) {
            p4.startActivity(v0_1);
        }
        return;
    }

    public final void i(androidx.fragment.app.FragmentActivity p19, String p20, android.os.Bundle p21)
    {
        android.content.Intent v3_1 = 0;
        Long v5 = Long.valueOf(0);
        if (p19 != null) {
            int v1_7;
            String v8 = 0;
            if (com.google.android.gms.internal.mlkit_vision_barcode.G0.c().booleanValue()) {
                if (com.google.android.gms.internal.mlkit_vision_barcode.m0.b(hs.d.b(((long) 2)))) {
                    java.util.Locale.getDefault();
                    int v9_14 = n9.e0.c(((long) 2));
                    String v10_1 = new StringBuilder();
                    v10_1.append(v9_14);
                    v10_1.append("/config/signature.txt");
                    if ((com.google.android.gms.internal.mlkit_vision_barcode.m0.b(v10_1.toString())) && (hs.d.c() == null)) {
                        kotlinx.coroutines.BuildersKt.runBlocking$default(0, new CQ.a(2, 29, 0), 1, 0);
                    }
                }
                Em.b v6_20 = hs.d.c();
                if (v6_20 != null) {
                    Em.b v6_21 = v6_20.a();
                    if (v6_21 != null) {
                        Em.b v6_22 = v6_21.a();
                        if (v6_22 != null) {
                            Em.b v6_23 = m9.i4.a(v6_22);
                            String v10_4 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v6_23, 10));
                            Em.b v6_24 = v6_23.iterator();
                            while (v6_24.hasNext()) {
                                v10_4.add(Long.valueOf(((UF.a) v6_24.next()).a));
                            }
                            android.content.Intent v3_3;
                            if (!v10_4.contains(v5)) {
                                v3_3 = new android.content.Intent(p19, vn.com.vng.zalopay.inapp_webview.InternalWebViewActivity);
                                v3_3.putExtra("url", p20);
                            } else {
                                int v1_9;
                                if (p20 == null) {
                                    v1_9 = 0;
                                } else {
                                    v1_9 = q9.y6.h(p20);
                                }
                                int v1_2;
                                if (v1_9 != 0) {
                                    Em.b v6_28 = new android.os.Bundle();
                                    String v11_13 = new java.util.HashMap();
                                    boolean v13_6 = ((Iterable) v1_9.getQueryParameterNames()).iterator();
                                    while (v13_6.hasNext()) {
                                        android.content.Intent v3_0;
                                        String v14_2 = ((String) v13_6.next());
                                        String v15 = v1_9.getQueryParameter(v14_2);
                                        android.content.Intent v16_1 = v3_1;
                                        if (v15 != null) {
                                            v3_0 = v15;
                                        } else {
                                            v3_0 = "";
                                        }
                                        v11_13.put(v14_2, v3_0);
                                        v6_28.putString(v14_2, v15);
                                        v3_1 = v16_1;
                                    }
                                    v1_2 = v6_28;
                                } else {
                                    v1_2 = new android.os.Bundle();
                                }
                                android.content.Intent v16_0 = v3_1;
                                v1_2.putAll(p21);
                                v3_3 = new android.content.Intent(p19, vn.com.vng.zalopay.miniapp.ui.MiniAppActivity);
                                ss.h v4_1 = hs.d.c();
                                if (v4_1 != null) {
                                    ss.h v4_2 = v4_1.a();
                                    if (v4_2 != null) {
                                        ss.h v4_3 = v4_2.a();
                                        if (v4_3 != null) {
                                            ss.h v4_5 = m9.i4.a(v4_3).iterator();
                                            while (v4_5.hasNext()) {
                                                Em.b v6_2 = v4_5.next();
                                                if (((UF.a) v6_2).a == v16_0) {
                                                }
                                                ss.h v4_8;
                                                Em.b v6_4 = ((UF.a) v6_2);
                                                if (v6_4 == null) {
                                                    v4_8 = "";
                                                } else {
                                                    int v9_3 = v6_4.b;
                                                    Em.b v6_5 = v6_4.c;
                                                    String v11_1 = new StringBuilder("https://social.zalopay.vn/spa/v2");
                                                    v11_1.append(v9_3);
                                                    v11_1.append("?fsm=");
                                                    v11_1.append(v6_5);
                                                    ss.h v4_7 = new StringBuilder(v11_1.toString());
                                                    Em.b v6_9 = ((Iterable) v1_2.keySet()).iterator();
                                                    while (v6_9.hasNext()) {
                                                        int v9_13 = ((String) v6_9.next());
                                                        String v11_4 = "?";
                                                        if (kotlin.text.StringsKt.g(v4_7, "?")) {
                                                            v11_4 = "&";
                                                        }
                                                        v4_7.append(v11_4);
                                                        v4_7.append(v9_13);
                                                        v4_7.append("=");
                                                        v4_7.append(v1_2.getString(v9_13));
                                                    }
                                                    v4_8 = v4_7.toString();
                                                    if (v4_8 == null) {
                                                    }
                                                }
                                                v3_3.putExtra("app_web_url", v4_8);
                                                ss.h v4_9 = hs.d.c();
                                                if (v4_9 != null) {
                                                    ss.h v4_10 = v4_9.a();
                                                    if (v4_10 != null) {
                                                        ss.h v4_11 = v4_10.a();
                                                        if (v4_11 != null) {
                                                            ss.h v4_16 = ((ss.h) v4_11.stream().filter(new Em.b(5, v5)).findFirst().orElse(0));
                                                            v3_3.putExtra("arg_app_reload_content", v4_16.f());
                                                            v3_3.putExtra("arg_app_mini_app_control_mode", v4_16.a());
                                                            v3_3.putExtra("arg_app_mini_app_control_close", v4_16.d().a());
                                                            v3_3.putExtra("arg_app_mini_app_control_more", v4_16.d().c());
                                                            v3_3.putExtra("arg_app_mini_app_control_favorite", v4_16.d().b());
                                                            if (!kotlin.text.y.equals(v1_2.getString("is_new_layer", "false"), "true", 1)) {
                                                                v3_3.addFlags(872415232);
                                                                v1_7 = v3_3;
                                                                v1_7.putExtra("app_name", "");
                                                                v1_7.putExtra("app_id", v5);
                                                                v1_7.putExtra("app_web_params", p21);
                                                                if (p21 != null) {
                                                                    v8 = p21.getString("forwardResult", "false");
                                                                }
                                                                if (Boolean.parseBoolean(v8)) {
                                                                    if (!(p19 instanceof vn.com.vng.zalopay.splashscreen.presentation.SplashScreenActivityV2)) {
                                                                        p19.startActivityForResult(v1_7, 131);
                                                                        return;
                                                                    } else {
                                                                        v1_7.setFlags(33554432);
                                                                    }
                                                                }
                                                                p19.startActivity(v1_7);
                                                                return;
                                                            } else {
                                                                v3_3.addFlags(268435456);
                                                            }
                                                        } else {
                                                            while(true) {
                                                                v4_16 = 0;
                                                            }
                                                        }
                                                    } else {
                                                    }
                                                }
                                            }
                                            v6_2 = 0;
                                        }
                                    }
                                }
                                throw new Exception("Data is null");
                            }
                        }
                    }
                }
                throw new Exception("Data is null");
            } else {
                v1_7 = new android.content.Intent(p19, vn.com.vng.zalopay.miniapp.error.NoWebViewSdkActivity);
            }
        }
        return;
    }

    public final void j(android.content.Context p3, int p4, String p5)
    {
        if (!android.text.TextUtils.isEmpty(p5)) {
            android.os.Bundle v0_2 = new android.os.Bundle();
            v0_2.putString("url", p5);
            vn.com.vng.zalopay.navigation.c.a(p3, v0_2, p4);
            return;
        } else {
            return;
        }
    }
}
