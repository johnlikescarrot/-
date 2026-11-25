package vn.com.vng.zalopay.protect.ui;
public final synthetic class e implements DZ.w, mf.g, mf.a {
    public final synthetic int a;
    public final synthetic vn.com.vng.zalopay.protect.ui.g b;

    public synthetic e(vn.com.vng.zalopay.protect.ui.g p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public void accept(Object p3)
    {
        android.content.Context v0_0 = this.b;
        vn.com.vng.zalopay.protect.ui.a v1_0 = v0_0.b;
        if (v1_0 != null) {
            ((vn.com.vng.zalopay.protect.ui.a) v1_0).s(q9.g5.b(((Throwable) p3), v0_0.c));
        }
        return;
    }

    public void c(DZ.x p6)
    {
        switch (this.a) {
            case 0:
                NullPointerException v0_1 = this.b;
                v0_1.getClass();
                String v1_4 = new java.util.HashMap();
                v1_4.put("device_id", v0_1.k.c(v0_1.c));
                UR.a.e("01.1729.001", v1_4);
                p6.dismiss();
                if (v0_1.b != null) {
                    if (v0_1.h.a()) {
                        Throwable v6_6 = v0_1.i.i().d(Ef.g.c);
                        String v1_1 = hf.b.a();
                        qf.g v4_2 = new qf.g(new vn.com.vng.zalopay.protect.ui.e(v0_1, 1), new vn.com.vng.zalopay.protect.ui.e(v0_1, 3));
                        try {
                            v6_6.b(new rf.c(v4_2, v1_1));
                            ((kf.a) v0_1.a).c(v4_2);
                        } catch (Throwable v6_10) {
                            throw v6_10;
                        } catch (Throwable v6_9) {
                            q9.e7.a(v6_9);
                            com.google.android.gms.internal.mlkit_vision_barcode.O4.c(v6_9);
                            NullPointerException v0_3 = new NullPointerException("Actually not, but can\'t pass out an exception otherwise...");
                            v0_3.initCause(v6_9);
                            throw v0_3;
                        }
                    } else {
                        ((vn.com.vng.zalopay.protect.ui.a) v0_1.b).k();
                    }
                }
                return;
            default:
                NullPointerException v0_4 = this.b;
                v0_4.getClass();
                String v1_6 = new java.util.HashMap();
                v1_6.put("device_id", v0_4.k.c(v0_4.c));
                UR.a.e("01.1729.002", v1_6);
                p6.dismiss();
                return;
        }
    }

    public void run()
    {
        Ow.b v0_0 = this.b;
        v0_0.f.f(new eu.e());
        v0_0.j.e(1);
        return;
    }
}
