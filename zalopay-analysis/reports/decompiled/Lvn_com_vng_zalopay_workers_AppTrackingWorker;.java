package vn.com.vng.zalopay.workers;
public final class AppTrackingWorker extends androidx.work.CoroutineWorker {
    public com.google.common.collect.G0 d;

    public AppTrackingWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        return;
    }

    public final Object b(wg.c p7)
    {
        String v0_2;
        if (!(p7 instanceof HR.a)) {
            v0_2 = new HR.a(this, p7);
        } else {
            v0_2 = ((HR.a) p7);
            Object v1_2 = ((HR.a) p7).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((HR.a) p7).f = (v1_2 - -2147483648);
            }
        }
        Object v7_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        B2.X v2_0 = v0_2.f;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v7_1);
            Object v7_2 = q9.a5.c();
            if (v7_2 != null) {
                v7_2.o1(this);
                Object v7_3 = kotlinx.coroutines.Dispatchers.getIO();
                B2.X v2_2 = new B2.X(this, 0, 3);
                v0_2.f = 1;
                Object v7_4 = kotlinx.coroutines.BuildersKt.withContext(v7_3, v2_2, v0_2);
                if (v7_4 != v1_1) {
                    return v7_4;
                } else {
                    return v1_1;
                }
            } else {
                return androidx.work.r.a();
            }
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(v7_1);
                return v7_1;
            }
        }
    }
}
