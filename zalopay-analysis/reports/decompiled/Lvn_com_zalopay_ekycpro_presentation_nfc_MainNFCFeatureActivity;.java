package vn.com.zalopay.ekycpro.presentation.nfc;
public final class MainNFCFeatureActivity extends vn.com.vng.zalopay.core.ui.activity.BaseFeatureActivity {
    public static ref.WeakReference L;
    public static final iV.x M;
    public pV.w A;
    public final O5.b B;
    public Cx.h C;
    public final O5.b D;
    public Ae.r E;
    public final O5.b F;
    public kotlinx.coroutines.Job G;
    public CV.a H;
    public final D7.o I;
    public final q0.N0 J;
    public final androidx.activity.result.d K;
    public android.nfc.NfcAdapter j;
    public Cc.f k;
    public KG.a l;
    public BV.Y0 m;
    public final O5.b n;
    public com.airbnb.lottie.network.g o;
    public final O5.b p;
    public k0.r q;
    public final O5.b r;
    public com.google.firebase.iid.i s;
    public final O5.b t;
    public BV.q u;
    public BV.b1 v;
    public com.android.billingclient.api.c0 w;
    public com.airbnb.lottie.network.g x;
    public pV.w y;
    public final O5.b z;

    static MainNFCFeatureActivity()
    {
        vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity.M = new iV.x(1, "S\u1eafp xong r\u1ed3i!", "Ti\u1ebfp t\u1ee5c th\u1ef1c hi\u1ec7n \u0111\u1ec3 kh\u00f4ng gi\u00e1n \u0111o\u1ea1n giao d\u1ecbch, b\u1ea3o v\u1ec7 t\u00e0i kho\u1ea3n v\u00e0 tu\u00e2n th\u1ee7 quy \u0111\u1ecbnh Ng\u00e2n h\u00e0ng Nh\u00e0 n\u01b0\u1edbc!", "https://simg.zalopay.com.vn/zst/zpi-spa/um/images/confirm_icon.png", "Ti\u1ebfp t\u1ee5c", 0, 0, 930);
        return;
    }

    public MainNFCFeatureActivity()
    {
        kotlin.reflect.d v3_1 = kotlin.jvm.internal.M.a;
        this.n = new O5.b(v3_1.b(BV.X0), new BV.s1(this, 10), new BV.e1(this, 12), new BV.s1(this, 11));
        this.p = new O5.b(v3_1.b(KV.j), new BV.s1(this, 12), new BV.e1(this, 14), new BV.s1(this, 13));
        this.r = new O5.b(v3_1.b(KV.i), new BV.s1(this, 14), new BV.e1(this, 15), new BV.s1(this, 15));
        this.t = new O5.b(v3_1.b(KV.d), new BV.s1(this, 0), new BV.e1(this, 21), new BV.s1(this, 1));
        this.z = new O5.b(v3_1.b(tV.t), new BV.s1(this, 2), new BV.e1(this, 28), new BV.s1(this, 3));
        this.B = new O5.b(v3_1.b(pV.v), new BV.s1(this, 4), new BV.i1(this, 6), new BV.s1(this, 5));
        this.D = new O5.b(v3_1.b(hW.m), new BV.s1(this, 6), new BV.i1(this, 15), new BV.s1(this, 7));
        this.F = new O5.b(v3_1.b(KV.l), new BV.s1(this, 8), new BV.i1(this, 25), new BV.s1(this, 9));
        this.I = new D7.o(6);
        this.J = q0.d.G(Boolean.FALSE, q0.D0.e);
        this.K = this.registerForActivityResult(new androidx.fragment.app.f0(6), new BV.k1(0, this));
        return;
    }

    public final void h1(BV.R0 p22, q0.s p23, int p24)
    {
        int v3_0;
        q0.v v10_1 = p23.x(-1356266507);
        if (!v10_1.I(p22)) {
            v3_0 = 2;
        } else {
            v3_0 = 4;
        }
        D0.q v4_4;
        if (!v10_1.I(this)) {
            v4_4 = 16;
        } else {
            v4_4 = 32;
        }
        if (((((v3_0 | p24) | v4_4) & 19) == 18) && (v10_1.c())) {
            v10_1.m();
        } else {
            int v3_15 = v10_1.I(this);
            D0.q v4_8 = v10_1.G();
            xV.h v6_4 = q0.r.a;
            if ((v3_15 != 0) || (v4_8 == v6_4)) {
                v4_8 = new BV.d1(this, 3);
                v10_1.B(v4_8);
            }
            boolean v7_6 = ((kotlin.jvm.functions.Function1) v4_8);
            int v3_18 = v10_1.I(this);
            D0.q v4_11 = v10_1.G();
            if ((v3_18 != 0) || (v4_11 == v6_4)) {
                v4_11 = new BV.e1(this, 10);
                v10_1.B(v4_11);
            }
            kotlin.jvm.functions.Function0 v15_1 = ((kotlin.jvm.functions.Function0) v4_11);
            int v3_20 = this.H;
            D0.q v4_13 = 0;
            if (v3_20 == 0) {
                v3_20 = 0;
            }
            int v3_21 = v3_20.j;
            if (!kotlin.jvm.internal.Intrinsics.areEqual(v3_21, "none")) {
                if (!kotlin.jvm.internal.Intrinsics.areEqual(v3_21, "old_bottomsheet")) {
                    v10_1.q(-1776573579);
                    int v3_24 = this.l;
                    if (v3_24 == 0) {
                        v3_24 = 0;
                    }
                    String v5_6 = this.H;
                    if (v5_6 != null) {
                        v4_13 = v5_6;
                    }
                    D0.q v4_14 = v4_13.j;
                    if (kotlin.text.StringsKt.isBlank(v4_14)) {
                        v4_14 = "survey_nfc";
                    }
                    String v5_8 = v4_14;
                    int v8_8 = p22.D.f.contains(GU.b.f);
                    int v9_6 = p22.C.h();
                    hW.m v11_1 = this.l1();
                    kotlin.jvm.functions.Function0 v14_3 = v10_1.I(this);
                    kotlin.jvm.functions.Function2 v12_0 = v10_1.G();
                    if ((v14_3 != null) || (v12_0 == v6_4)) {
                        v12_0 = new BV.e1(this, 11);
                        v10_1.B(v12_0);
                    }
                    kotlin.jvm.functions.Function2 v12_1 = ((kotlin.jvm.functions.Function0) v12_0);
                    kotlin.jvm.functions.Function0 v14_0 = v10_1.p(v7_6);
                    kotlin.jvm.functions.Function0 v13_0 = v10_1.G();
                    if ((v14_0 != null) || (v13_0 == v6_4)) {
                        v13_0 = new AA.u(17, v7_6);
                        v10_1.B(v13_0);
                    }
                    q0.v v16 = v10_1;
                    hW.w.a(v3_24, v5_8, xV.h.c, v8_8, 0, 0, v9_6, v11_1, v7_6, v12_1, ((kotlin.jvm.functions.Function0) v13_0), v15_1, v16, 384, 0, 48);
                    v10_1 = v16;
                    v10_1.W(0);
                } else {
                    v10_1.q(-1996984010);
                    D0.q v4_2 = androidx.compose.foundation.layout.P0.g(D0.p.a, 1065353216);
                    int v3_6 = p22.C.h();
                    int v8_1 = v10_1.p(v7_6);
                    int v9_1 = v10_1.G();
                    if ((v8_1 != 0) || (v9_1 == v6_4)) {
                        v9_1 = new AA.u(16, v7_6);
                        v10_1.B(v9_1);
                    }
                    iV.p.a(v4_2, vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity.M, v3_6, v7_6, v15_1, ((kotlin.jvm.functions.Function0) v9_1), v10_1, 54);
                    v10_1.W(0);
                }
            } else {
                v10_1.q(-1996996286);
                int v3_10 = Boolean.valueOf(p22.C.h());
                String v5_3 = (v10_1.I(p22) | v10_1.p(v15_1));
                boolean v7_2 = v10_1.G();
                if ((v5_3 != null) || (v7_2 == v6_4)) {
                    v7_2 = new AA.k(p22, v15_1, 0, 5);
                    v10_1.B(v7_2);
                }
                q0.Z.g(v3_10, ((kotlin.jvm.functions.Function2) v7_2), v10_1);
                v10_1.W(0);
            }
        }
        int v3_11 = v10_1.Y();
        if (v3_11 != 0) {
            v3_11.d = new AZ.h(this, p22, p24, 2);
        }
        return;
    }

    public final void i1(sN.i p4, boolean p5)
    {
        android.content.Intent v0_1 = new android.content.Intent();
        android.os.Bundle v1_1 = new android.os.Bundle();
        v1_1.putParcelable("nfc_result", p4);
        if (p5 != 0) {
            v1_1.putBoolean("nfc_force_fail", 1);
        }
        v0_1.putExtras(v1_1);
        this.setResult(-1, v0_1);
        this.finish();
        this.overridePendingTransition(0, 0);
        return;
    }

    public final KV.d j1()
    {
        return ((KV.d) this.t.getValue());
    }

    public final KV.i k1()
    {
        return ((KV.i) this.r.getValue());
    }

    public final hW.m l1()
    {
        return ((hW.m) this.D.getValue());
    }

    public final void m1()
    {
        android.app.PendingIntent v0_3;
        this.j = android.nfc.NfcAdapter.getDefaultAdapter(this);
        if (android.os.Build$VERSION.SDK_INT < 31) {
            v0_3 = android.app.PendingIntent.getActivity(this, 0, new android.content.Intent(this, vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity).addFlags(536870912), 134217728);
        } else {
            v0_3 = android.app.PendingIntent.getActivity(this, 0, new android.content.Intent(this, vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity).addFlags(536870912), 33554432);
        }
        android.content.IntentFilter[] v1_4 = new android.content.IntentFilter[] {new android.content.IntentFilter("android.nfc.action.NDEF_DISCOVERED"), new android.content.IntentFilter("android.nfc.action.TAG_DISCOVERED"), new android.content.IntentFilter("android.nfc.action.TECH_DISCOVERED")});
        android.nfc.NfcAdapter v2_3 = this.j;
        if (v2_3 == null) {
            android.nfc.NfcAdapter v2_4 = android.nfc.NfcAdapter.getDefaultAdapter(this);
            if (v2_4 != null) {
                v2_4.enableForegroundDispatch(this, v0_3, v1_4, 0);
            }
            return;
        } else {
            v2_3.enableForegroundDispatch(this, v0_3, v1_4, 0);
            return;
        }
    }

    public final boolean n1()
    {
        int v0_0 = this.j;
        if ((v0_0 == 0) || (v0_0.isEnabled())) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void o1()
    {
        android.content.Intent v0_0 = android.nfc.NfcAdapter.getDefaultAdapter(this);
        this.j = v0_0;
        if (v0_0 != null) {
            if (this.n1()) {
                com.google.android.gms.internal.mlkit_vision_barcode.h7.c(this, "C\u1ea7n k\u00edch ho\u1ea1t NFC \u0111\u1ec3 s\u1eed d\u1ee5ng t\u00ednh n\u0103ng");
                this.startActivity(new android.content.Intent("android.settings.WIRELESS_SETTINGS"));
            }
            return;
        } else {
            com.google.android.gms.internal.mlkit_vision_barcode.h7.c(this, "Thi\u1ebft b\u1ecb kh\u00f4ng h\u1ed7 tr\u1ee3 t\u00ednh n\u0103ng NFC.");
            return;
        }
    }

    public final void onCreate(android.os.Bundle p39)
    {
        GV.m v1_0;
        void v38_1 = super.onCreate(p39);
        this.getWindow().addFlags(128);
        vn.com.vng.zalopay.core.ui.activity.BaseFeatureActivity.e1(this);
        java.util.Objects.toString(p39);
        if (p39 != null) {
            v1_0 = p39;
        } else {
            v1_0 = this.getIntent().getExtras();
        }
        GV.m v1_4;
        if (v1_0 == null) {
            v1_4 = 0;
        } else {
            v1_4 = ((CV.a) v1_0.getParcelable("main_nfc_activity_args"));
        }
        String v33 = this.getIntent().getStringExtra("nfc_behalf_b_request_id");
        if (v1_4 == null) {
            if ((v33 != null) && (!kotlin.text.StringsKt.isBlank(v33))) {
                this.H = new CV.a("", "", "", 0, new OU.c(0, 0, 0, 0, 0, 0, 0, 0, 0, 2047), kotlin.collections.E.emptyList(), xV.B.d, kotlin.collections.E.emptyList(), "", "", 0, 0, 0, 0, 0, 0, 1, "", "manual", kotlin.collections.E.emptyList(), 1, 0, 0, 0, 0, 0, v33, 0, 0, 0, 998244352);
            } else {
                this.g1();
                this.finish();
                return;
            }
        } else {
            this.H = v1_4;
        }
        GV.m v1_43 = this.I;
        v1_43.getClass();
        vn.ai.trueid.sdk.navigation.TrueIDV3.setLanguage(this, "{\n  \"trueidsdk_nfc_title\": \"Qu\u00e9t ch\u00edp th\u1ebb c\u0103n c\u01b0\u1edbc\",\n  \"trueidsdk_nfc_message\": \"Vui l\u00f2ng \u0111\u1ec3 m\u00e1y g\u1ea7n chip NFC tr\u00ean m\u1eb7t sau th\u1ebb v\u00e0 Qu\u00e9t\",\n  \"trueidsdk_cancel\": \"Hu\u1ef7\",\n  \"trueidsdk_nfc_request\": \"\u0110\u01b0a iPhone c\u1ee7a b\u1ea1n g\u1ea7n v\u1edbi CCCD h\u1ed7 tr\u1ee3 NFC.\",\n  \"trueidsdk_nfc_authenticating\": \"\u0110ang x\u00e1c th\u1ef1c CCCD\",\n  \"trueidsdk_nfc_reading\": \"\u0110ang \u0111\u1ecdc\",\n  \"trueidsdk_nfc_success\": \"\u0110\u1ecdc th\u1ebb CCCD th\u00e0nh c\u00f4ng\",\n  \"trueidsdk_nfc_invalid\": \"Xin l\u1ed7i, \u0111\u00e3 x\u1ea3y ra s\u1ef1 c\u1ed1 khi \u0111\u1ecdc CCCD. Vui l\u00f2ng th\u1eed l\u1ea1i.\"\n}");
        vn.ai.trueid.sdk.navigation.TrueIDV3.setConfigNFC(this, new vn.ai.trueid.sdk.models.NFCConfig("https://api.trueid.vn", "", "", "", "appId", "appSecret", new org.json.JSONObject()), "{\"stepVerification\":[\"SINGLENFC\"]}");
        v1_43.b = 1;
        GV.m v1_1 = n9.W2.a;
        if (v1_1 == null) {
            throw new IllegalStateException("EKycProPlugin is not installed. Call install() first.");
        } else {
            vn.com.zalopay.ekycpro.presentation.nfc.state.l v5_0 = this.H;
            if (v5_0 == null) {
                v5_0 = 0;
            }
            int v6_1 = new com.airbnb.lottie.network.g(v1_1, v5_0, 4);
            AA.g v11_1 = new com.airbnb.lottie.network.f(1, v1_1);
            int v12_1 = new s5.f();
            int v13_0 = dagger.internal.f.b(new CU.a(v6_1, 0));
            int v7_3 = dagger.internal.f.b(new CU.a(v6_1, 3));
            int v8_1 = dagger.internal.f.b(new CU.a(v6_1, 4));
            int v14_0 = dagger.internal.f.b(new CU.b(v6_1, v7_3, v8_1, 0));
            vn.com.zalopay.ekycpro.presentation.nfc.state.l v5_14 = dagger.internal.f.b(new AU.d(v6_1, v7_3, v8_1, dagger.internal.f.b(new CU.a(v6_1, 1)), 2));
            long v9_5 = dagger.internal.f.b(new AB.b(10, v12_1));
            dagger.internal.o v10_4 = dagger.internal.f.b(new CU.a(v6_1, 2));
            int v8_2 = dagger.internal.f.b(new AA.C(v6_1, v8_1, 26));
            int v6_2 = dagger.internal.f.b(new AA.C(v6_1, v7_3, 25));
            int v7_7 = dagger.internal.f.b(new AA.C(v11_1, dagger.internal.f.b(new AP.d(1, v11_1)), 5));
            this.k = new Cc.f(3);
            AA.g v11_4 = v1_1.b();
            p9.n7.b(v11_4);
            this.l = v11_4;
            v13_0.get();
            this.m = new BV.Y0(((KU.c) v14_0.get()), ((KU.b) v5_14.get()), ((aV.d) v9_5.get()), ((CV.a) v10_4.get()), 0);
            int v12_12 = ((CV.a) v10_4.get());
            Ns.d v15_6 = ((Ns.d) v13_0.get());
            vn.com.zalopay.ekycpro.presentation.nfc.state.l v3_0 = v1_1.q();
            p9.n7.b(v3_0);
            this.o = new com.airbnb.lottie.network.g(v12_12, v15_6, v3_0);
            this.q = new k0.r(((CV.a) v10_4.get()), ((KU.e) v8_2.get()), 16);
            this.s = new com.google.firebase.iid.i(((CV.a) v10_4.get()), ((KU.d) v6_2.get()), 12);
            int v6_6 = new BV.X0(((KU.c) v14_0.get()), ((KU.b) v5_14.get()), ((aV.d) v9_5.get()), ((CV.a) v10_4.get()));
            long v9_9 = new la.f();
            AA.g v11_18 = new NU.c();
            int v12_17 = new lc.a();
            v13_0.get();
            int v8 = new BV.b1(v9_9, v11_18, v12_17);
            int v8_10 = v1_1.q();
            p9.n7.b(v8_10);
            v13_0.get();
            this.u = new BV.q(v6_6, v8_10, ((CV.a) v10_4.get()));
            int v6_8 = new la.f();
            int v8_12 = new NU.c();
            long v9_14 = new lc.a();
            v13_0.get();
            this.v = new BV.b1(v6_8, v8_12, v9_14);
            int v6_9 = v1_1.h();
            int v8_13 = v1_1.b();
            p9.n7.b(v8_13);
            this.w = new com.android.billingclient.api.c0(v6_9, v8_13);
            this.x = new com.airbnb.lottie.network.g(v1_1.i(), v1_1.j(), 20);
            this.y = new pV.w(((KU.b) v5_14.get()), v1_1.h(), ((Ns.d) v13_0.get()), new XQ.i(new D5.f(26), new N4.b(v1_1.getAppContext(), 0)), 1);
            this.A = new pV.w(((KU.b) v5_14.get()), v1_1.h(), ((Ns.d) v13_0.get()), new XQ.i(new D5.f(26), new N4.b(v1_1.getAppContext(), 0)), 0);
            this.C = new Cx.h(13, ((KU.h) v7_7.get()));
            this.E = new Ae.r(((CV.a) v10_4.get()), ((Ns.d) v13_0.get()));
            if (com.google.android.gms.internal.mlkit_vision_barcode.Y.a == null) {
                vn.com.zalopay.ekycpro.presentation.nfc.state.l v5_21 = new Object[0];
                com.google.android.gms.internal.mlkit_vision_barcode.Y.a = SP.n.e("[TrackerManager] getTracker() called before init(). Auto-initializing.", v5_21);
            }
            GV.m v1_16 = com.google.android.gms.internal.mlkit_vision_barcode.Y.a;
            vn.com.zalopay.ekycpro.presentation.nfc.state.l v5_22 = this.H;
            if (v5_22 == null) {
                int v6_17 = 0;
            } else {
                v6_17 = v5_22;
            }
            int v6_18 = v6_17.H;
            if (v5_22 == null) {
                v5_22 = 0;
            }
            vn.com.zalopay.ekycpro.presentation.nfc.state.l v5_25 = ((xV.i) kotlin.collections.CollectionsKt.firstOrNull(v5_22.t));
            if (v5_25 == null) {
                vn.com.zalopay.ekycpro.presentation.nfc.state.l v5_27 = this.H;
                if (v5_27 == null) {
                    v5_27 = 0;
                }
                vn.com.zalopay.ekycpro.presentation.nfc.state.l v5_26 = v5_27.r;
            } else {
                v5_26 = v5_25.b();
                if (v5_26 == null) {
                }
            }
            v1_16.d = v6_18;
            v1_16.e = v5_26;
            GV.m v1_18 = this.u;
            if (v1_18 == null) {
                v1_18 = 0;
            }
            int v6_19 = this.H;
            if (v6_19 == 0) {
                v6_19 = 0;
            }
            int v6_22 = String.valueOf(v6_19.e.a);
            int v7_9 = this.H;
            if (v7_9 == 0) {
                int v8_20 = 0;
            } else {
                v8_20 = v7_9;
            }
            if (v7_9 == 0) {
                long v9_18 = 0;
            } else {
                v9_18 = v7_9;
            }
            if (v7_9 == 0) {
                v7_9 = 0;
            }
            v1_18.v0(new BV.F(v6_22, v8_20.e.d, v9_18.a, v7_9.d));
            GV.m v1_19 = this.v;
            if (v1_19 == null) {
                v1_19 = 0;
            }
            int v6_23 = this.H;
            if (v6_23 == 0) {
                v6_23 = 0;
            }
            int v6_26 = String.valueOf(v6_23.e.a);
            int v7_12 = this.H;
            if (v7_12 == 0) {
                int v8_23 = 0;
            } else {
                v8_23 = v7_12;
            }
            if (v7_12 == 0) {
                long v9_20 = 0;
            } else {
                v9_20 = v7_12;
            }
            if (v7_12 == 0) {
                v7_12 = 0;
            }
            v1_19.t0(new b00.w(v6_26, v8_23.e.d, v9_20.d, v7_12.a));
            GV.m v1_20 = this.H;
            if (v1_20 == null) {
                v1_20 = 0;
            }
            if (!v1_20.E) {
                GV.m v1_22 = this.u;
                if (v1_22 == null) {
                    v1_22 = 0;
                }
                v1_22.v0(BV.A.a);
                if (p39 != null) {
                    if (p39.getBoolean("nfc_on_behalf_on_qr", 0)) {
                        GV.m v1_26 = p39.getString("nfc_on_behalf_deeplink");
                        vn.com.zalopay.ekycpro.presentation.nfc.state.l v3_27 = "";
                        if (v1_26 != null) {
                            int v8_26 = v1_26;
                        } else {
                            v8_26 = "";
                        }
                        if (!kotlin.text.StringsKt.isBlank(v8_26)) {
                            GV.m v1_28 = this.k1();
                            long v9_22 = p39.getLong("nfc_on_behalf_expire_at", 0);
                            vn.com.zalopay.ekycpro.presentation.nfc.state.l v5_35 = p39.getString("nfc_on_behalf_expired_display");
                            if (v5_35 != null) {
                                AA.g v11_22 = v5_35;
                            } else {
                                v11_22 = "";
                            }
                            int v0_3 = p39.getString("nfc_on_behalf_phone_b");
                            if (v0_3 != 0) {
                                v3_27 = v0_3;
                            }
                            v1_28.getClass();
                            if (!kotlin.text.StringsKt.isBlank(v8_26)) {
                                v1_28.i = v3_27;
                                int v0_5 = v1_28.e;
                            }
                            int v0_6 = this.k;
                            if (v0_6 == 0) {
                                v0_6 = 0;
                            }
                            v0_6.d(GV.m.b);
                        }
                    }
                }
            } else {
                int v0_7 = vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity.L;
                if (v0_7 != 0) {
                    int v0_9 = ((vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity) v0_7.get());
                    if (v0_9 != 0) {
                        if (v0_9 == this) {
                            v0_9 = 0;
                        } else {
                            if (v0_9.isFinishing()) {
                            }
                        }
                        if (v0_9 != 0) {
                            v0_9.finish();
                        }
                    }
                }
                vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity.L = new ref.WeakReference(this);
                int v0_12 = android.nfc.NfcAdapter.getDefaultAdapter(this);
                this.j = v0_12;
                if (v0_12 != 0) {
                    int v0_13 = this.k;
                    if (v0_13 == 0) {
                        v0_13 = 0;
                    }
                    v0_13.d(GV.f.b);
                } else {
                    int v0_14 = this.k;
                    if (v0_14 == 0) {
                        v0_14 = 0;
                    }
                    v0_14.d(GV.e.b);
                }
            }
            k.e.a(this, new y0.b(new BV.c1(this, 0), 1, 566305031));
            int v0_17 = this.v;
            if (v0_17 == 0) {
                BV.d1 v2_3 = 0;
            } else {
                v2_3 = v0_17;
            }
            GV.m v1_38 = this.getLifecycle();
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v1_38), 0, 0, new BV.o1(v1_38, v2_3, 0, this, 1), 3, 0);
            vn.com.zalopay.ekycpro.presentation.nfc.state.l v3_32 = this.u;
            if (v3_32 == null) {
                BV.d1 v2_4 = 0;
            } else {
                v2_4 = v3_32;
            }
            GV.m v1_39 = this.getLifecycle();
            int v4_2 = v38_1;
            int v12_21 = v4_2;
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v1_39), 0, 0, new BV.o1(v1_39, v2_4, 0, v4_2, 0), 3, 0);
            ((KV.j) v12_21.p.getValue()).g.f(v12_21, new cV.y(2, new BV.d1(v12_21, 5)));
            v12_21.k1().h.f(v12_21, new cV.y(2, new BV.d1(v12_21, 7)));
            ((KV.l) v12_21.F.getValue()).g.f(v12_21, new cV.y(2, new BV.d1(v12_21, 6)));
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(v12_21.getLifecycle()), 0, 0, new AA.g(v12_21, 0, 8), 3, 0);
            vn.com.zalopay.ekycpro.presentation.nfc.state.l v3_42 = v12_21.H;
            if (v3_42 == null) {
                v3_42 = 0;
            }
            if (v3_42.E) {
                v12_21.j1().h.f(v12_21, new cV.y(2, new BV.d1(v12_21, 4)));
            }
            return;
        }
    }

    public final void onDestroy()
    {
        int v0_0;
        super.onDestroy();
        rN.b.d("ADJUST_KYC_AUTH_CHALLENGE_MORE");
        int v0_2 = vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity.L;
        if (v0_2 == 0) {
            v0_0 = 0;
        } else {
            v0_0 = ((vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity) v0_2.get());
        }
        if (v0_0 == this) {
            vn.com.zalopay.ekycpro.presentation.nfc.MainNFCFeatureActivity.L = 0;
        }
        return;
    }

    public final void onNewIntent(android.content.Intent p5)
    {
        super.onNewIntent(p5);
        if ((kotlin.jvm.internal.Intrinsics.areEqual("android.nfc.action.TAG_DISCOVERED", p5.getAction())) || (kotlin.jvm.internal.Intrinsics.areEqual("android.nfc.action.TECH_DISCOVERED", p5.getAction()))) {
            org.jmrtd.lds.icao.MRZInfo v0_2 = this.u;
            uc.i v1_1 = 0;
            if (v0_2 == null) {
                v0_2 = 0;
            }
            if (kotlin.jvm.internal.Intrinsics.areEqual(((BV.R0) v0_2.h0()).a.b(), "jth")) {
                org.jmrtd.lds.icao.MRZInfo v0_8 = this.u;
                if (v0_8 == null) {
                    v0_8 = 0;
                }
                if (((BV.R0) v0_8.h0()).b.d) {
                    kotlinx.coroutines.Job v5_3;
                    kotlinx.coroutines.Job v5_2 = p5.getExtras();
                    if (v5_2 == null) {
                        v5_3 = 0;
                    } else {
                        v5_3 = ((android.nfc.Tag) p9.M.a(v5_2, "android.nfc.extra.TAG", android.nfc.Tag));
                    }
                    org.jmrtd.lds.icao.MRZInfo v0_14 = this.u;
                    if (v0_14 == null) {
                        v0_14 = 0;
                    }
                    nY.h.d(((BV.R0) v0_14.h0()).b);
                    try {
                        org.jmrtd.lds.icao.MRZInfo v0_19 = this.u;
                    } catch (Throwable) {
                        if (com.google.android.gms.internal.mlkit_vision_barcode.Y.a == null) {
                            kotlinx.coroutines.Job v5_7 = new Object[0];
                            com.google.android.gms.internal.mlkit_vision_barcode.Y.a = SP.n.e("[TrackerManager] getTracker() called before init(). Auto-initializing.", v5_7);
                        }
                        com.google.android.gms.internal.mlkit_vision_barcode.Y.a.p(-4);
                        kotlinx.coroutines.Job v5_10 = this.u;
                        if (v5_10 != null) {
                            v1_1 = v5_10;
                        }
                        v1_1.v0(new BV.d0(-4));
                    }
                    if (v0_19 == null) {
                        v0_19 = 0;
                    } else {
                    }
                    int v2_2 = this.u;
                    if (v2_2 == 0) {
                        v2_2 = 0;
                    } else {
                    }
                    String v3_0 = this.u;
                    if (v3_0 == null) {
                        v3_0 = 0;
                    } else {
                    }
                    this.G = p9.w5.b(this, v5_3, p9.w5.a(((BV.R0) v0_19.h0()).b.e, ((BV.R0) v2_2.h0()).b.f, ((BV.R0) v3_0.h0()).b.g), new uc.i(4, this));
                    return;
                }
            }
        }
        return;
    }

    public final void onPause()
    {
        try {
            kotlinx.coroutines.Job v0_0 = android.nfc.NfcAdapter.getDefaultAdapter(this);
        } catch (Exception) {
            kotlinx.coroutines.Job v0_1 = this.G;
            if (v0_1 != null) {
                v0_1.cancel(0);
            }
            super.onPause();
            return;
        }
        if (v0_0 == null) {
        } else {
            v0_0.disableForegroundDispatch(this);
        }
    }

    public final void onResume()
    {
        super.onResume();
        com.android.billingclient.api.c0 v0_0 = this.H;
        if ((v0_0 == null) || (!v0_0.E)) {
            com.android.billingclient.api.c0 v0_2 = this.w;
            if (v0_2 != null) {
                v0_2.B(this, new BV.e1(this, 6), new AB.a(4), new BV.c1(this, 1));
            }
            this.m1();
            return;
        } else {
            this.m1();
            return;
        }
    }

    public final void onSaveInstanceState(android.os.Bundle p5)
    {
        super.onSaveInstanceState(p5);
        String v0_0 = this.H;
        if (v0_0 != null) {
            p5.putParcelable("main_nfc_activity_args", v0_0);
        }
        String v0_17 = this.H;
        if ((v0_17 != null) && (!v0_17.E)) {
            String v0_1 = this.k;
            if (v0_1 == null) {
                v0_1 = 0;
            }
            if (kotlin.jvm.internal.Intrinsics.areEqual(((GV.r) ((ji.u0) v0_1.c).getValue()).e(), "nfc_on_behalf_qr_screen")) {
                String v0_12 = ((vn.com.zalopay.ekycpro.presentation.nfc.state.l) this.k1().f.b.getValue());
                if (!kotlin.text.StringsKt.isBlank(v0_12.c)) {
                    p5.putString("nfc_on_behalf_deeplink", v0_12.c);
                    p5.putLong("nfc_on_behalf_expire_at", v0_12.d);
                    p5.putString("nfc_on_behalf_expired_display", v0_12.e);
                    p5.putString("nfc_on_behalf_phone_b", this.k1().i);
                    p5.putBoolean("nfc_on_behalf_on_qr", 1);
                }
            }
        }
        return;
    }

    public final void onStart()
    {
        super.onStart();
        kotlin.jvm.internal.N v0_0 = this.x;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        v0_0.D(this);
        kotlin.jvm.internal.N v0_1 = kotlin.jvm.internal.M.a;
        ((java.util.concurrent.ConcurrentHashMap) rN.b.d.getValue()).remove(v0_1.b(sN.b));
        rN.b.c("ADJUST_KYC_AUTH_CHALLENGE_MORE", v0_1.b(sN.b), androidx.lifecycle.l0.i(this.getLifecycle()), kotlinx.coroutines.Dispatchers.getMain(), 1, new AA.k(this, 0, 6));
        return;
    }

    public final void onStop()
    {
        int v0 = this.x;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.G(this);
        super.onStop();
        return;
    }

    public final void onUserSessionExpiredEvent(eu.f p2)
    {
        String vtmp1 = q9.B4.a(p2.a, this);
        int v0 = this.x;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.A(this, vtmp1);
        return;
    }

    public final void p1(wW.a p4)
    {
        this.J.setValue(Boolean.TRUE);
        android.content.Intent v0_2 = new android.content.Intent(this, vn.com.zalopay.identity_verification.presentation.VerificationByVNeIDActivity);
        v0_2.putExtra("VNEID_ACTIVITY_ARGS", p4);
        this.K.a(v0_2, androidx.core.app.e.r(this, 0, 0));
        this.overridePendingTransition(0, 0);
        return;
    }

    public final void q1(vn.com.zalopay.ekycpro.presentation.nfc.state.d p11)
    {
        String v0_0 = this.H;
        BV.X0 v1_0 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        if (!v0_0.E) {
            gU.c v11_1 = ((BV.X0) this.n.getValue());
            String v0_1 = this.u;
            if (v0_1 == null) {
                v0_1 = 0;
            }
            Az.j v7_0 = ((BV.R0) v0_1.h0()).b.j;
            String v0_5 = this.u;
            if (v0_5 == null) {
                v0_5 = 0;
            }
            int v5_0 = ((BV.R0) v0_5.h0()).b.i;
            String v0_9 = this.u;
            if (v0_9 == null) {
                v0_9 = 0;
            }
            String v0_13 = ((BV.R0) v0_9.h0()).a.b();
            if (v0_13 == null) {
                String v0_14 = this.u;
                if (v0_14 == null) {
                    v0_14 = 0;
                }
                v0_13 = ((BV.R0) v0_14.h0()).D.r;
            }
            String v0_19 = vn.com.zalopay.ekycpro.presentation.nfc.state.d.a(p11, 0, v0_13, v5_0, 0, v7_0, 5119);
            int v2_0 = v11_1.g;
            v2_0.getClass();
            v2_0.l(0, aV.g.a);
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v11_1), 0, 0, new Az.j(v11_1, v0_19, 0, 5), 3, 0);
            return;
        } else {
            String v0_20 = this.u;
            if (v0_20 == null) {
                v0_20 = 0;
            }
            v0_20.v0(new BV.d0(5));
            String v0_21 = this.u;
            if (v0_21 == null) {
                v0_21 = 0;
            }
            String v0_25 = ((BV.R0) v0_21.h0()).a.b();
            if (v0_25 == null) {
                String v0_26 = this.H;
                if (v0_26 != null) {
                    v1_0 = v0_26;
                }
                v0_25 = v1_0.r;
            }
            this.j1().m0(m9.w.b(vn.com.zalopay.ekycpro.presentation.nfc.state.d.a(p11, 0, v0_25, 0, 0, 0, 15359)));
            return;
        }
    }
}
