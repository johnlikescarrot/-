package vn.com.vng.zalopay.workers;
public final class SyncContactWorker extends androidx.work.RxWorker {
    public final l9.w c;
    public Gv.q$b d;
    public SR.c e;
    public Ew.D f;
    public CW.a g;

    public SyncContactWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        super.c = new l9.w();
        return;
    }

    public final gf.N b()
    {
        tf.c v2_31 = new String[0];
        if (com.google.android.gms.internal.mlkit_vision_barcode.u0.e(com.google.android.gms.internal.mlkit_vision_barcode.u0.c(this.getApplicationContext(), ((String[]) ((java.util.Collection) kotlin.collections.C.listOf("android.permission.READ_CONTACTS")).toArray(v2_31))))) {
            AK.a v0_13 = q9.a5.c();
            if (v0_13 != null) {
                int v4_4 = new DC.z();
                AC.a v5_1 = new AC.a(v0_13, 5);
                tf.c v1_25 = dagger.internal.f.b(new DC.B(v4_4, v5_1, dagger.internal.f.b(new DC.D(v4_4, new AC.a(v0_13, 10))), dagger.internal.f.b(new DC.A(v4_4, new AC.a(v0_13, 6))), dagger.internal.f.b(new DC.C(v4_4, new AC.a(v0_13, 11))), new AP.d(17, new AC.a(v0_13, 7)), new AC.a(v0_13, 9)));
                tf.c v2_30 = dagger.internal.f.b(new DC.E(v4_4, v1_25, v5_1, new AC.a(v0_13, 8)));
                this.d = ((Gv.q$b) v1_25.get());
                this.e = ((SR.c) v2_30.get());
                tf.c v1_30 = v0_13.r();
                p9.n7.b(v1_30);
                this.f = v1_30;
                AK.a v0_14 = v0_13.getContext();
                p9.n7.b(v0_14);
                this.g = new CW.a(v0_14);
                AK.a v0_15 = this.f;
                tf.c v1_33 = 0;
                if (v0_15 == null) {
                    v0_15 = 0;
                }
                AK.a v0_17 = is.T.b(String.valueOf(v0_15.d));
                tf.c v2_33 = this.d;
                if (v2_33 != null) {
                    v1_33 = v2_33;
                }
                AK.a v0_7 = gf.A.zip(v1_33.b().map(new Gv.l(3, new HR.c(v0_17, 0))), com.google.android.gms.internal.mlkit_vision_barcode.t0.d(new Gv.n(2, this)).map(new Gv.l(4, new AZ.f(this, v0_17, 14))), new AK.a(23)).flatMap(new Gv.l(5, new Am.b(9, this))).lastOrError();
                tf.c v2_10 = new AK.a(21, new FW.C(8));
                v0_7.getClass();
                return new tf.c(new tf.c(v0_7, v2_10, 0), new AK.a(22), 1);
            } else {
                return new tf.b(2, androidx.work.r.a());
            }
        } else {
            return new tf.b(2, androidx.work.r.a());
        }
    }
}
