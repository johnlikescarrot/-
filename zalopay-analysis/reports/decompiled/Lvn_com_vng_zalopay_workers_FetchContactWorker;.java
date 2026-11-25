package vn.com.vng.zalopay.workers;
public final class FetchContactWorker extends androidx.work.RxWorker {
    public Gv.q$b c;

    public FetchContactWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        return;
    }

    public final gf.N b()
    {
        androidx.work.o v0_0 = q9.a5.c();
        if (v0_0 != null) {
            DC.z v4_1 = new DC.z();
            AC.a v5_1 = new AC.a(v0_0, 5);
            tf.b v1_8 = dagger.internal.f.b(new DC.B(v4_1, v5_1, dagger.internal.f.b(new DC.D(v4_1, new AC.a(v0_0, 10))), dagger.internal.f.b(new DC.A(v4_1, new AC.a(v0_0, 6))), dagger.internal.f.b(new DC.C(v4_1, new AC.a(v0_0, 11))), new AP.d(17, new AC.a(v0_0, 7)), new AC.a(v0_0, 9)));
            int v2_12 = dagger.internal.f.b(new DC.E(v4_1, v1_8, v5_1, new AC.a(v0_0, 8)));
            this.c = ((Gv.q$b) v1_8.get());
            v2_12.get();
            p9.n7.b(v0_0.m());
            return new tf.b(2, androidx.work.r.a());
        } else {
            return new tf.b(2, androidx.work.r.a());
        }
    }
}
