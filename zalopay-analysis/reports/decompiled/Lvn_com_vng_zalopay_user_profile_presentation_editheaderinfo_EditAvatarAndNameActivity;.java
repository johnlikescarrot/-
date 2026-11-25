package vn.com.vng.zalopay.user_profile.presentation.editheaderinfo;
public final class EditAvatarAndNameActivity extends vn.com.vng.zalopay.core.ui.activity.BaseActivity implements vn.com.vng.zalopay.user_profile.presentation.editheaderinfo.b$a {
    public static final synthetic int E;
    public final ng.S A;
    public final ng.S B;
    public final ng.S C;
    public final ng.S D;
    public String k;
    public Ux.S l;
    public final O5.b m;
    public String n;
    public String o;
    public android.net.Uri p;
    public ZP.a q;
    public java.io.File r;
    public final ng.S s;
    public final ng.S t;
    public final ng.S u;
    public final ng.S v;
    public final ng.S w;
    public final ng.S x;
    public final ng.S y;
    public final ng.S z;

    public EditAvatarAndNameActivity()
    {
        this.m = new O5.b(kotlin.jvm.internal.M.a.b(WP.f), new KP.h(this, 0), new BF.a(12, this), new KP.h(this, 1));
        this.n = "";
        this.o = "";
        this.q = new ZP.a("", "");
        this.s = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131362607);
        this.t = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365080);
        this.u = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131362988);
        this.v = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364439);
        this.w = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365092);
        this.x = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131362120);
        this.y = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364708);
        this.z = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365131);
        this.A = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365132);
        this.B = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364571);
        this.C = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364569);
        this.D = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365088);
        return;
    }

    public final void B0(android.net.Uri p1, String p2)
    {
        this.p = p1;
        byte[] v1_5 = new java.io.File(p2);
        if (v1_5.isFile()) {
            WP.f v2_4 = new com.google.android.gms.cloudmessaging.m(this);
            v2_4.e = v1_5;
            v2_4.b = 100;
            this.l1().i0(Cg.l.readBytes(v2_4.b()));
            return;
        } else {
            com.google.android.gms.internal.mlkit_vision_barcode.h7.e(this, this.getString(2131955142));
            return;
        }
    }

    public final void H0(android.net.Uri p8)
    {
        if (!kotlin.jvm.internal.Intrinsics.areEqual(p8, android.net.Uri.EMPTY)) {
            this.p = p8;
            java.io.File v2 = 0;
            try {
                Throwable v4_7 = java.util.Locale.US;
                new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", v4_7).format(Long.valueOf(System.currentTimeMillis()));
                int v1_1 = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", v4_7).format(Long.valueOf(System.currentTimeMillis()));
                Throwable v3_6 = this.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES);
                Throwable v4_4 = new StringBuilder("JPEG_");
                v4_4.append(v1_1);
                v4_4.append("_");
                WP.f v0_5 = java.io.File.createTempFile(v4_4.toString(), ".jpg", v3_6);
            } catch (WP.f v0_6) {
                com.google.android.gms.internal.mlkit_vision_barcode.h7.e(this, v0_6.getMessage());
                v0_5 = 0;
            }
            if (v0_5 != null) {
                int v1_3 = this.k;
                if (v1_3 == 0) {
                    v1_3 = 0;
                }
                androidx.core.content.FileProvider.getUriForFile(this, v1_3, v0_5);
            }
            if (v0_5 != null) {
                Throwable v3_7 = v0_5.getAbsolutePath();
                if (v3_7 != null) {
                    Throwable v4_0 = android.graphics.BitmapFactory.decodeStream(this.getContentResolver().openInputStream(p8));
                    if (v4_0 != null) {
                        int v8_11;
                        int v8_10 = this.getContentResolver().openFileDescriptor(p8, "r");
                        if (v8_10 == 0) {
                            v8_11 = 0;
                        } else {
                            v8_11 = v8_10.getFileDescriptor();
                        }
                        int v5_4;
                        if (v8_11 == 0) {
                            v5_4 = 0;
                        } else {
                            v5_4 = new android.media.ExifInterface(v8_11);
                        }
                        int v8_12;
                        if (v5_4 == 0) {
                            v8_12 = 0;
                        } else {
                            v8_12 = Integer.valueOf(v5_4.getAttributeInt("Orientation", 0));
                        }
                        if (v8_12 != 0) {
                            if (v8_12.intValue() != 6) {
                                if (v8_12 != 0) {
                                    if (v8_12.intValue() == 3) {
                                        v4_0 = com.google.android.gms.internal.mlkit_vision_barcode.P7.b(v4_0, 1127481344);
                                        try {
                                            int v8_3 = new java.io.FileOutputStream(v3_7);
                                            try {
                                                v4_0.compress(android.graphics.Bitmap$CompressFormat.JPEG, 100, v8_3);
                                            } catch (Throwable v3_2) {
                                                try {
                                                    throw v3_2;
                                                } catch (Throwable v4_1) {
                                                    kotlin.io.CloseableKt.closeFinally(v8_3, v3_2);
                                                    throw v4_1;
                                                }
                                            }
                                            kotlin.io.CloseableKt.closeFinally(v8_3, 0);
                                            if (v0_5 != null) {
                                                this.r = v0_5;
                                                int v8_7 = new com.google.android.gms.cloudmessaging.m(this);
                                                WP.f v0_1 = this.r;
                                                if (v0_1 != null) {
                                                    v2 = v0_1;
                                                }
                                                v8_7.e = v2;
                                                v8_7.b = 100;
                                                this.l1().i0(Cg.l.readBytes(v8_7.b()));
                                                return;
                                            }
                                            return;
                                        } catch (int v8_4) {
                                            ck.b.b(v8_4);
                                        }
                                    }
                                } else {
                                }
                                if (v8_12 != 0) {
                                    if (v8_12.intValue() == 8) {
                                        v4_0 = com.google.android.gms.internal.mlkit_vision_barcode.P7.b(v4_0, 1132920832);
                                    }
                                } else {
                                }
                                if (v8_12 != 0) {
                                    v8_12.intValue();
                                    int v5 = 1;
                                } else {
                                }
                            } else {
                                v4_0 = com.google.android.gms.internal.mlkit_vision_barcode.P7.b(v4_0, 1119092736);
                            }
                        } else {
                        }
                    }
                }
            }
        }
        return;
    }

    public final int e1()
    {
        return 2131559459;
    }

    public final OD.a g1()
    {
        return new OD.a(this.getLocalClassName(), -1);
    }

    public final android.widget.AutoCompleteTextView j1()
    {
        return ((android.widget.AutoCompleteTextView) this.s.getValue());
    }

    public final com.google.android.material.textfield.TextInputLayout k1()
    {
        return ((com.google.android.material.textfield.TextInputLayout) this.v.getValue());
    }

    public final WP.f l1()
    {
        return ((WP.f) this.m.getValue());
    }

    public final void m1(String p4)
    {
        com.google.android.gms.internal.mlkit_vision_barcode.W7.c(((android.view.View) this.A.getValue()));
        ((android.view.View) this.z.getValue()).setVisibility(0);
        ((android.widget.TextView) this.B.getValue()).setText(p4);
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new KP.d(this, 0), 2500);
        return;
    }

    public final void onCreate(android.os.Bundle p19)
    {
        com.google.android.gms.internal.gtm.I v9 = com.google.android.gms.internal.mlkit_vision_barcode.b0.a;
        String v2_17 = new lS.b(25, v9);
        yP.d v11_1 = new yP.d(v2_17, 0);
        ZP.a v3_0 = new yP.d(v2_17, 5);
        String v4_5 = new yP.d(v2_17, 3);
        String v5_2 = new yP.d(v2_17, 4);
        yP.d v15_1 = new yP.d(v2_17, 2);
        xC.b v6_1 = new xC.b(v2_17, v15_1, 3);
        yP.d v7_1 = new yP.d(v2_17, 6);
        WP.f v1_31 = new DC.Q0(v2_17, v3_0, v4_5, v5_2, v6_1, v7_1, 7);
        yP.d v16 = v7_1;
        ZP.a v3_30 = new yP.d(v2_17, 1);
        DC.Q0 v10_1 = new DC.Q0(v11_1, v1_31, new yP.c(v2_17, v3_30, 0), new yP.c(v2_17, v3_30, 1), v15_1, v16, 2);
        WP.f v1_33 = v9.r();
        p9.n7.c(v1_33);
        this.k = v1_33;
        this.l = ((Ux.S) v10_1.get());
        super.onCreate(p19);
        WP.f v1_37 = this.getIntent().getStringExtra("key_name_edit_display_name");
        if (v1_37 != null) {
            this.j1().setText(v1_37);
            String v2_20 = this.q;
            ZP.a v3_31 = v2_20.a;
            v2_20.getClass();
            this.q = new ZP.a(v3_31, v1_37);
        }
        WP.f v1_40 = this.getIntent().getStringExtra("key_name_edit_avatar");
        String v2_24 = this.t;
        if (v1_40 != null) {
            ZP.a v3_32 = this.q;
            String v4_12 = v3_32.b;
            v3_32.getClass();
            this.q = new ZP.a(v1_40, v4_12);
            this.n = v1_40;
            this.o = v1_40;
            ((com.facebook.drawee.view.SimpleDraweeView) v2_24.getValue()).setImageURI(this.n);
        }
        WP.f v1_0;
        WP.f v1_45 = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (v1_45 <= null) {
            v1_0 = 0;
        } else {
            v1_0 = this.getResources().getDimensionPixelSize(v1_45);
        }
        String v4_1 = this.findViewById(2131364265);
        String v5_0 = v4_1.getLayoutParams();
        v5_0.height = v1_0;
        v4_1.setLayoutParams(v5_0);
        com.google.android.gms.internal.mlkit_vision_barcode.W7.b(((android.view.View) this.D.getValue()), 0);
        WP.f v1_4 = this.j1();
        com.google.android.gms.internal.mlkit_vision_barcode.U7.c(v1_4, ((android.widget.ImageView) this.u.getValue()));
        com.google.android.gms.internal.mlkit_vision_barcode.V7.d(v1_4, 50);
        v1_4.setOnFocusChangeListener(new GZ.c(this, 2));
        v1_4.addTextChangedListener(new KP.g(this, 1));
        v1_4.addTextChangedListener(new KP.g(this, 0));
        ((androidx.appcompat.widget.Toolbar) this.w.getValue()).setOnClickListener(new KP.e(this, 0));
        ((com.facebook.drawee.view.SimpleDraweeView) v2_24.getValue()).setOnClickListener(new KP.e(this, 1));
        ((android.view.View) this.x.getValue()).setOnClickListener(new KP.e(this, 2));
        this.l1().d.X().f(this, new CS.v(5, new KP.f(this, 0)));
        this.l1().d.Y().f(this, new CS.v(5, new KP.f(this, 1)));
        this.l1().r.f(this, new CS.v(5, new KP.f(this, 2)));
        this.l1().t.f(this, new CS.v(5, new KP.f(this, 3)));
        this.l1().V(this.q, new ZP.a(this.o, this.j1().getText().toString()));
        this.l1().I(this.j1().getText().toString());
        return;
    }
}
