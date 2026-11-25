package vn.com.vng.zalopay.authentication;
public final class d implements vn.com.vng.zalopay.authentication.f$a {
    public Ow.a a;
    public android.content.Context b;
    public boolean c;
    public final m9.o d;
    public final vn.com.vng.zalopay.authentication.secret.a e;
    public final com.android.billingclient.api.c0 f;
    public final ref.WeakReference g;

    public d(android.content.Context p3, String p4, boolean p5, m9.o p6)
    {
        vn.com.vng.zalopay.authentication.c v0_1 = new vn.com.vng.zalopay.authentication.c(this);
        ref.WeakReference v1_1 = new ref.WeakReference(p3);
        this.g = v1_1;
        com.android.billingclient.api.c0 v3_8 = new vn.com.vng.zalopay.authentication.secret.a();
        this.e = v3_8;
        this.d = p6;
        this.c = p5;
        XZ.a v5_1 = this.f;
        if ((v5_1 == null) || (!v5_1.A())) {
            com.android.billingclient.api.c0 v3_3;
            com.android.billingclient.api.c0 v3_1 = v3_8.a;
            if ((v3_1 == null) || (!v3_1.y())) {
                v3_3 = 0;
            } else {
                v3_3 = 1;
            }
            XZ.a v5_4 = new com.android.billingclient.api.c0(((android.app.Activity) v1_1.get()));
            this.f = v5_4;
            XZ.a v5_6 = ((TZ.b) v5_4.b);
            v5_6.a = p4;
            int v6_4 = ((WZ.e) v5_6.f);
            if (v6_4 != 0) {
                v6_4.setTitle(p4);
            }
            Throwable v4_1 = this.c;
            int v6_6 = ((WZ.e) v5_6.f);
            if (v6_6 != 0) {
                v6_6.d(v4_1, v3_3);
            }
            v5_6.c = v3_3;
            v5_6.b = v4_1;
            v5_6.d = v0_1;
            this.f.k();
            com.android.billingclient.api.c0 v3_5 = this.f;
            v3_5.u().runOnUiThread(new XZ.a(v3_5, 1));
            return;
        } else {
            return;
        }
    }

    public final void P(Throwable p5)
    {
        com.google.android.gms.internal.mlkit_vision_barcode.y6.b(p5);
        com.android.billingclient.api.c0 v5_3 = q9.g5.b(p5, this.b);
        try {
            int v0_2 = this.f;
        } catch (com.android.billingclient.api.c0 v5_1) {
            v5_1.getMessage();
            return;
        }
        if (v0_2 == 0) {
            return;
        } else {
            if ((!android.text.TextUtils.isEmpty(v5_3)) && (((TZ.b) v0_2.b) != null)) {
                v0_2.u().runOnUiThread(new Kk.f(v0_2, v5_3, 17));
            }
            this.f.s(0);
            return;
        }
    }

    public final void a()
    {
        com.android.billingclient.api.c0 v0_0 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        com.android.billingclient.api.c0 v0_1 = v0_0.b;
        if (v0_1 == null) {
            com.android.billingclient.api.c0 v0_2 = this.f;
            try {
                if (v0_2 != null) {
                    v0_2.m();
                }
            } catch (Exception) {
            }
            return;
        } else {
            v0_1.Q0(this);
            return;
        }
    }

    public final void j(String p6)
    {
        UR.a.e("01.1030.002", kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("reason", ""), kotlin.TuplesKt.to("message", ""), kotlin.TuplesKt.to("os", "android"), kotlin.TuplesKt.to("timeStamp", String.valueOf(System.currentTimeMillis()))})));
        System.currentTimeMillis();
        if (this.c) {
            m9.o v0_4 = this.e;
            if (v0_4 != null) {
                boolean v1_2 = v0_4.a;
                if ((v1_2) && (v1_2.y())) {
                    v0_4.d(p6);
                }
            }
        }
        m9.o v0_6 = this.g;
        if (v0_6 != null) {
            v0_6.get();
        }
        m9.o v0_8 = this.d;
        if (v0_8 != null) {
            v0_8.c(p6);
        }
        try {
            this.f.m();
            return;
        } catch (Exception v6_2) {
            v6_2.getMessage();
            return;
        }
    }
}
