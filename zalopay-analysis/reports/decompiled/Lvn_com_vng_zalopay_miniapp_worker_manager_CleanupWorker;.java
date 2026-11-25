package vn.com.vng.zalopay.miniapp.worker_manager;
public final class CleanupWorker extends androidx.work.CoroutineWorker {
    public uc.i d;
    public AW.a e;

    public CleanupWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        return;
    }

    public final Object b(wg.c p11)
    {
        String v0_2;
        if (!(p11 instanceof mG.a)) {
            v0_2 = new mG.a(this, p11);
        } else {
            v0_2 = ((mG.a) p11);
            Object v1_2 = ((mG.a) p11).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((mG.a) p11).f = (v1_2 - -2147483648);
            }
        }
        androidx.work.o v11_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        long v2_0 = v0_2.f;
        if (v2_0 == 0) {
            long v6_0;
            kotlin.ResultKt.throwOnFailure(v11_1);
            androidx.work.o v11_3 = ((CC.f) q9.a5.a());
            this.d = new uc.i(2, ((zs.c) v11_3.K1.get()));
            this.e = new AW.a(1, ((zs.c) v11_3.K1.get()));
            androidx.work.o v11_9 = this.getInputData().a.get("mini_app_id");
            if (!(v11_9 instanceof Long)) {
                v6_0 = -1;
            } else {
                v6_0 = ((Long) v11_9).longValue();
            }
            androidx.work.o v11_12 = new Long(v6_0);
            if (v11_12.longValue() == -1) {
                v11_12 = 0;
            }
            if (v11_12 == null) {
                return androidx.work.r.a();
            } else {
                long v6_2 = v11_12.longValue();
                androidx.work.o v11_14 = kotlinx.coroutines.Dispatchers.getIO();
                D1.f v4_6 = new D1.f(this, v6_2, 0, 6);
                v0_2.f = 1;
                androidx.work.o v11_15 = kotlinx.coroutines.BuildersKt.withContext(v11_14, v4_6, v0_2);
                if (v11_15 != v1_1) {
                    return v11_15;
                } else {
                    return v1_1;
                }
            }
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(v11_1);
                return v11_1;
            }
        }
    }
}
