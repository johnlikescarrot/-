package vn.com.vng.zalopay.splashscreen.worker_manager;
public final class PushDFPWorker extends androidx.work.CoroutineWorker {
    public android.content.SharedPreferences d;
    public rv.b e;
    public com.google.android.gms.internal.gtm.I f;

    public PushDFPWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        return;
    }

    public final Object b(wg.c p10)
    {
        ck.a v0_2;
        if (!(p10 instanceof cO.b)) {
            v0_2 = new cO.b(this, p10);
        } else {
            v0_2 = ((cO.b) p10);
            Object v1_2 = ((cO.b) p10).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((cO.b) p10).f = (v1_2 - -2147483648);
            }
        }
        Object v10_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        String v2_0 = v0_2.f;
        try {
            if (v2_0 == null) {
                kotlin.ResultKt.throwOnFailure(v10_1);
                Object v10_3 = ((CC.f) q9.a5.a());
                this.d = ((android.content.SharedPreferences) v10_3.o.get());
                this.e = new rv.b(0, ((bu.a) v10_3.J1.get()));
                this.f = v10_3.h();
                Object v10_5 = this.d;
                String v2_6 = 0;
                if (v10_5 == null) {
                    v10_5 = 0;
                } else {
                }
                if (v10_5.getLong(wmoi.igob.ggcn(12610), 0) < System.currentTimeMillis()) {
                    Object v10_7 = Um.a.b();
                    com.google.android.gms.internal.gtm.I v4_5 = this.f;
                    if (v4_5 != null) {
                        v2_6 = v4_5;
                    }
                    Object v10_9 = Yt.h.a(v2_6.t(v10_7));
                    String v2_8 = Yt.h.b();
                    v0_2.f = 1;
                    if (this.g(v2_8, v10_9, v0_2) == v1_1) {
                        return v1_1;
                    }
                }
            } else {
                if (v2_0 != 1) {
                    throw new IllegalStateException(wmoi.igob.ggcn(12609));
                } else {
                    kotlin.ResultKt.throwOnFailure(v10_1);
                }
            }
        } catch (Object v10_13) {
            ck.b.d(wmoi.igob.ggcn(12611)).d(v10_13);
            return androidx.work.r.a();
        }
        return androidx.work.r.b();
    }

    public final Object g(String p6, String p7, wg.c p8)
    {
        long v0_2;
        if (!(p8 instanceof cO.c)) {
            v0_2 = new cO.c(this, p8);
        } else {
            v0_2 = ((cO.c) p8);
            Object v1_2 = ((cO.c) p8).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((cO.c) p8).f = (v1_2 - -2147483648);
            }
        }
        android.content.SharedPreferences$Editor v8_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        au.a v2_0 = v0_2.f;
        android.content.SharedPreferences v4 = 0;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v8_1);
            android.content.SharedPreferences$Editor v8_2 = this.e;
            if (v8_2 == null) {
                v8_2 = 0;
            }
            v8_2.getClass();
            String v6_3 = ((bu.a) v8_2.b).a(new au.a(p6, p7));
            v0_2.f = 1;
            v8_1 = com.google.android.gms.internal.mlkit_vision_barcode.p5.b(v6_3, oi.a.b, v0_2);
            if (v8_1 == v1_1) {
                return v1_1;
            }
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException(wmoi.igob.ggcn(12612));
            } else {
                kotlin.ResultKt.throwOnFailure(v8_1);
            }
        }
        android.content.SharedPreferences$Editor v8_3 = ((Zt.b) v8_1);
        if (v8_3.a() == 1) {
            String v6_8 = v8_3.b().a();
            android.content.SharedPreferences$Editor v8_4 = this.d;
            if (v8_4 != null) {
                v4 = v8_4;
            }
            android.content.SharedPreferences$Editor v8_5 = v4.edit();
            v8_5.putLong(wmoi.igob.ggcn(12613), (System.currentTimeMillis() + v6_8));
            v8_5.apply();
        }
        return kotlin.Unit.INSTANCE;
    }
}
