package vn.com.vng.zalopay.user_profile.presentation.umminiapps;
public final class UMMiniAppActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int o;
    public KG.a i;
    public KG.b j;
    public Pt.n k;
    public final SP.t l;
    public final li.e m;
    public final li.e n;

    public UMMiniAppActivity()
    {
        this.l = new SP.t();
        this.m = gi.L.a(kotlinx.coroutines.Dispatchers.getDefault().plus(gi.N.c()));
        this.n = gi.L.a(kotlin.coroutines.f.a(gi.N.d(), kotlinx.coroutines.Dispatchers.getMain().getImmediate()));
        return;
    }

    public static String e1()
    {
        String v0_3 = new java.text.SimpleDateFormat("yyyyMMdd_HHmmssSSS", java.util.Locale.US).format(new java.util.Date());
        Mg.e.a.getClass();
        return androidx.media3.exoplayer.source.r.d(Mg.e.b.d(1000, 9999), v0_3, "_");
    }

    public final void g1(int p28, SP.a p29)
    {
        vn.com.vng.zalopay.user_profile.presentation.umminiapps.UMMiniAppActivity v2 = this;
        if (p28 == 1) {
            kotlin.Pair v1_76 = this.i;
            if (v1_76 == null) {
                v1_76 = 0;
            }
            v1_76.g(this, android.net.Uri.parse("zalopay://launch/f/face-authentication?source=99"));
        }
        if (p28 == 2) {
            n9.O.c(this, new pS.f(HS.c.d, "from_cashier", 0, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("um_key1", Integer.valueOf(12343455)), kotlin.TuplesKt.to("um_key2", "test")})), 20), new SP.h(this, 1));
        }
        if (p28 == 33) {
            q9.A6.a(this, new kX.a(p29.b, vn.com.vng.zalopay.user_profile.presentation.umminiapps.UMMiniAppActivity.e1(), s00.f.a, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("um_key_1", "um test value 1"), kotlin.TuplesKt.to("um_key_2", "um test value 2"), kotlin.TuplesKt.to("um_key_3", "um test abc"), kotlin.TuplesKt.to("um_key_n", "test value n")}))), new oc.b());
        }
        if (p28 == 35) {
            if (!s00.a.d) {
                SP.m v0_123;
                if (s00.a.d) {
                    v0_123 = 0;
                } else {
                    s00.a.d = 1;
                    v0_123 = 1;
                }
                if (v0_123 != null) {
                    kotlinx.coroutines.BuildersKt.launch$default(s00.a.e, 0, 0, new CS.r(2, 13, 0), 3, 0);
                    SP.m v0_138 = new android.content.Intent(this, vn.zalopay.ui.um_custom_loading.CenterLoadingActivity);
                    v0_138.addFlags(65536);
                    s00.b.a(this, v0_138);
                }
            }
            kotlinx.coroutines.BuildersKt.launch$default(this.n, 0, 0, new CS.r(2, 4, 0), 3, 0);
        }
        if (p28 == 36) {
            if (!s00.c.d) {
                SP.m v0_6;
                if (s00.c.d) {
                    v0_6 = 0;
                } else {
                    s00.c.d = 1;
                    v0_6 = 1;
                }
                if (v0_6 != null) {
                    kotlinx.coroutines.BuildersKt.launch$default(s00.c.e, 0, 0, new CS.r(2, 15, 0), 3, 0);
                    SP.m v0_11 = new android.content.Intent(this, vn.zalopay.ui.um_custom_loading.FullScreenLoadingActivity);
                    v0_11.addFlags(65536);
                    s00.b.a(this, v0_11);
                }
            }
            kotlinx.coroutines.BuildersKt.launch$default(this.n, 0, 0, new CS.r(2, 5, 0), 3, 0);
        }
        if (p28 == 38) {
            kotlinx.coroutines.BuildersKt.launch$default(this.n, 0, 0, new SP.r(this, 0, 0), 3, 0);
        }
        if (p28 == 100) {
            this.startActivity(new android.content.Intent(this, vn.com.vng.zalopay.livenessdetection.presentation.qrcode.QRCodeScannerActivity));
        }
        if (p28 == 99) {
            kotlinx.coroutines.BuildersKt.launch$default(this.m, kotlinx.coroutines.Dispatchers.getIO(), 0, new SP.r(this, 0, 1), 2, 0);
        }
        if (p28 == 34) {
            com.google.android.gms.internal.mlkit_vision_barcode.R7.d(this, new JX.a(p29.b, 0, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("um_key_1", "um test value 1"), kotlin.TuplesKt.to("um_key_2", "um test value 2"), kotlin.TuplesKt.to("um_key_3", "um test abc"), kotlin.TuplesKt.to("um_key_n", "test value n")})), 6));
        }
        if (p28 == 3) {
            kotlin.Pair v4_4 = this.i;
            if (v4_4 == null) {
                v4_4 = 0;
            }
            v4_4.g(this, android.net.Uri.parse("zalopay://launch/f/pin-bio-authentication?source_id=99"));
        }
        if (p28 == 5) {
            kotlin.Pair v1_4;
            if (!p29.a) {
                v1_4 = pS.j.e;
            } else {
                v1_4 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.g, 0, v1_4, 0, 108), new SP.g(this, 2));
        }
        if (p28 == 6) {
            n9.O.a(this, new pS.b(1, pS.d.j, 0, 0, 0, 124), new SP.h(this, 2));
        }
        if (p28 == 4) {
            kotlin.Pair v1_11;
            if (!p29.a) {
                v1_11 = pS.j.e;
            } else {
                v1_11 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.h, 0, v1_11, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("your_custom_key1", "your_custom_value2"), kotlin.TuplesKt.to("your_custom_key2", "your_custom_key2")})), 44), new SP.i(this, 2));
        }
        if (p28 == 7) {
            kotlin.Pair v1_19;
            if (!p29.a) {
                v1_19 = pS.j.e;
            } else {
                v1_19 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.i, 0, v1_19, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("your_custom_key1", "your_custom_value2"), kotlin.TuplesKt.to("your_custom_key2", "your_custom_key2")})), 44), new SP.f(this, 0));
        }
        if (p28 == 26) {
            this.startActivity(new android.content.Intent(this, vn.com.vng.zalopay.livenessdetection.presentation.idcard.IdentityCardCaptureActivity));
        }
        kotlin.Pair v4_8;
        if (p28 != 23) {
            v4_8 = 0;
        } else {
            SP.m v0_44 = com.google.android.gms.internal.mlkit_vision_barcode.b0.a;
            v0_44.getClass();
            n9.P.a = new com.google.firebase.iid.i(new oc.b(), v0_44);
            Integer v3_13 = new SP.g(this, 0);
            kotlin.Pair v1_29 = new com.google.android.gms.internal.ads.Js(22, 0);
            SP.m v0_46 = n9.P.a;
            kotlin.Pair v4_9 = v0_46.e();
            p9.n7.c(v4_9);
            v1_29.c = v4_9;
            v1_29.d = v0_46.h();
            v1_29.e = v0_46.g();
            SP.m v0_49 = ((eS.h) v0_46.f().create(eS.h));
            p9.n7.c(v0_49);
            v1_29.f = new an.b(v0_49);
            v4_8 = 0;
            kotlinx.coroutines.BuildersKt.launch$default(((li.e) v1_29.b), 0, 0, new bc.j(v1_29, this, v3_13, 0, 11), 3, 0);
        }
        if (p28 == 8) {
            kotlin.Pair v1_31;
            if (!p29.a) {
                v1_31 = pS.j.e;
            } else {
                v1_31 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.j, 0, v1_31, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("your_custom_key1", "your_custom_value2"), kotlin.TuplesKt.to("your_custom_key2", "your_custom_key2")})), 44), new SP.h(this, 0));
        }
        if (p28 == 9) {
            kotlin.Pair v1_38;
            if (!p29.a) {
                v1_38 = pS.j.e;
            } else {
                v1_38 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.k, 0, v1_38, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("your_custom_key1", "your_custom_value2"), kotlin.TuplesKt.to("your_custom_key2", "your_custom_key2")})), 44), new SP.i(this, 0));
        }
        if (p28 == 37) {
            kotlin.Pair v1_46;
            if (!p29.a) {
                v1_46 = pS.j.e;
            } else {
                v1_46 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.l, 0, v1_46, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("your_custom_key1", "your_custom_value2"), kotlin.TuplesKt.to("your_custom_key2", "your_custom_key2")})), 44), new SP.j(this));
        }
        if (p28 == 15) {
            kotlin.Pair v1_53;
            if (!p29.a) {
                v1_53 = pS.j.e;
            } else {
                v1_53 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.n, 0, v1_53, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("your_custom_key1", "your_custom_value2"), kotlin.TuplesKt.to("your_custom_key2", "your_custom_key2")})), 44), new SP.k(this));
        }
        if (p28 == 17) {
            kotlin.Pair v1_60;
            if (!p29.a) {
                v1_60 = pS.j.e;
            } else {
                v1_60 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.m, 0, v1_60, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("auth_request_id", "auth_request_id"), kotlin.TuplesKt.to("um_key_1", "um_value_1"), kotlin.TuplesKt.to("test_key_2", "test_value_2")})), 44), new SP.l(this, 0));
        }
        if (p28 == 177) {
            SP.m v0_72 = this.k;
            if (v0_72 == null) {
                v0_72 = v4_8;
            }
            XS.b.f(v0_72.F());
        }
        if (p28 == 31) {
            kotlin.Pair v1_67;
            if (!p29.a) {
                v1_67 = pS.j.e;
            } else {
                v1_67 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.o, 0, v1_67, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("auth_request_id", "auth_request_id"), kotlin.TuplesKt.to("um_key_1", "um_value_1"), kotlin.TuplesKt.to("test_key_2", "test_value_2")})), 44), new SP.m(this, 0));
        }
        if (p28 == 32) {
            SP.m v0_78 = vn.com.vng.zalopay.user_profile.presentation.umminiapps.UMMiniAppActivity.e1();
            int v5_14 = p29.b;
            int v8_8 = kotlin.collections.E.listOf(new rS.a[] {new rS.a(4), new rS.a(12)}));
            pS.j v11_5 = new StringBuilder("auth_request_id_");
            v11_5.append(v0_78);
            n9.O.b(this, new rS.b(v5_14, v8_8, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("auth_request_id", v11_5.toString()), kotlin.TuplesKt.to("um_key_1", "um_value_1"), kotlin.TuplesKt.to("um_value_2", "um_value_2")}))), new SP.f(this, 1));
        }
        if (p28 == 19) {
            if (!s00.a.d) {
                SP.m v0_89;
                if (s00.a.d) {
                    v0_89 = 0;
                } else {
                    s00.a.d = 1;
                    v0_89 = 1;
                }
                if (v0_89 != null) {
                    kotlinx.coroutines.BuildersKt.launch$default(s00.a.e, 0, 0, new CS.r(2, 13, v4_8), 3, 0);
                    SP.m v0_94 = new android.content.Intent(this, vn.zalopay.ui.um_custom_loading.CenterLoadingActivity);
                    v0_94.addFlags(65536);
                    s00.b.a(this, v0_94);
                }
            }
            Integer v3_39;
            if (!p29.a) {
                v3_39 = pS.j.e;
            } else {
                v3_39 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.e, 0, v3_39, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("auth_request_id", "auth_request_id"), kotlin.TuplesKt.to("um_key_1", "um_value_1"), kotlin.TuplesKt.to("test_key_2", "test_value_2")})), 44), new SP.g(v2, 1));
        }
        if (p28 == 20) {
            Integer v3_46;
            if (!p29.a) {
                v3_46 = pS.j.e;
            } else {
                v3_46 = pS.j.d;
            }
            n9.O.a(v2, new pS.b(p29.b, pS.d.f, 0, v3_46, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("auth_request_id", "auth_request_id"), kotlin.TuplesKt.to("um_key_1", "um_value_1"), kotlin.TuplesKt.to("test_key_2", "test_value_2")})), 44), new SP.i(v2, 1));
        }
        if (p28 == 28) {
            n9.O.c(this, new pS.f(HS.c.c, "um_mini_app", 1, 0, 24), new SP.j(this));
        }
        if (p28 == 30) {
            n9.O.c(this, new pS.f(HS.c.b, "um_mini_app", 1, 0, 24), new SP.k(this));
        }
        if (p28 == 29) {
            this.getSharedPreferences("zlp_biometric_prefs", 0).edit().clear().apply();
            com.google.android.gms.internal.mlkit_vision_barcode.h7.c(this, "Delete complete");
        }
        if (p28 == 10) {
            Integer v3_57;
            if (!p29.a) {
                v3_57 = pS.j.e;
            } else {
                v3_57 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.l, 0, v3_57, kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("your_custom_key1", "your_custom_value2"), kotlin.TuplesKt.to("your_custom_key2", "your_custom_key2")})), 44), new SP.l(v2, 1));
        }
        if (p28 == 11) {
            kotlinx.coroutines.BuildersKt.launch$default(this.m, 0, 0, new SP.q(this, v4_8, 0), 3, 0);
            SP.m v0_118 = p29.b;
            Integer v3_65 = p29.a;
            int v5_31 = new StringBuilder("zalopay://launch/f/kyc?source=");
            v5_31.append(v0_118);
            v5_31.append("&result_screen_visibility=");
            v5_31.append(v3_65);
            SP.m v0_122 = q9.y6.h(v5_31.toString());
            if (v0_122 != null) {
                Integer v3_66 = this.i;
                if (v3_66 == null) {
                    v3_66 = v4_8;
                }
                v3_66.o(this, v0_122);
            }
        }
        if (p28 == 12) {
            kotlinx.coroutines.BuildersKt.launch$default(this.m, 0, 0, new SP.q(this, v4_8, 1), 3, 0);
            SP.m v0_124 = p29.b;
            kotlin.Pair v1_77 = p29.a;
            Integer v3_68 = new StringBuilder("zalopay://launch/f/adjust-kyc?source=");
            v3_68.append(v0_124);
            v3_68.append("&result_screen_visibility=");
            v3_68.append(v1_77);
            SP.m v0_127 = q9.y6.h(v3_68.toString());
            if (v0_127 != null) {
                kotlin.Pair v1_78 = this.i;
                if (v1_78 == null) {
                    v1_78 = v4_8;
                }
                v1_78.o(this, v0_127);
            }
        }
        if (p28 == 13) {
            kotlin.Pair v1_80;
            if (!p29.a) {
                v1_80 = pS.j.e;
            } else {
                v1_80 = pS.j.d;
            }
            n9.O.a(this, new pS.b(p29.b, pS.d.j, 0, v1_80, kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("from_zmp", Integer.valueOf(p29.c))), 44), new SP.m(this, 1));
        }
        if (p28 == 14) {
            SP.m v0_134 = q9.y6.h("https://zalo.me/s/3888182390069527250/?");
            if (v0_134 != null) {
                kotlin.Pair v1_83 = this.i;
                if (v1_83 != null) {
                    v4_8 = v1_83;
                }
                v4_8.g(this, v0_134);
            }
        }
        return;
    }

    public final void onCreate(android.os.Bundle p12)
    {
        y0.b v0_0 = com.google.android.gms.internal.mlkit_vision_barcode.b0.a;
        int v1_1 = v0_0.b();
        p9.n7.c(v1_1);
        this.i = v1_1;
        int v1_2 = v0_0.s();
        p9.n7.c(v1_2);
        this.j = v1_2;
        this.k = v0_0.h();
        super.onCreate(p12);
        k.e.a(this, new y0.b(new SP.d(this, 0), 1, 1176547514));
        androidx.lifecycle.x v4 = this.getLifecycle();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v4), 0, 0, new QP.C(v4, this.l, 0, this, 13), 3, 0);
        return;
    }

    public final void onDestroy()
    {
        gi.L.b(this.m, 0);
        rN.b.d("UMMiniAppActivity");
        super.onDestroy();
        return;
    }
}
