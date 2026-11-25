package vn.com.vng.zalopay.splashscreen.worker_manager;
public final class FirstTrackingVitalsWorker extends androidx.work.CoroutineWorker {
    public final android.content.Context d;

    public FirstTrackingVitalsWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        super.d = p1;
        return;
    }

    public final Object b(wg.c p7)
    {
        String v0_2;
        if (!(p7 instanceof cO.a)) {
            v0_2 = new cO.a(this, p7);
        } else {
            v0_2 = ((cO.a) p7);
            Object v1_2 = ((cO.a) p7).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((cO.a) p7).f = (v1_2 - -2147483648);
            }
        }
        IllegalStateException v7_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        b0.x v2_0 = v0_2.f;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v7_1);
            IllegalStateException v7_2 = kotlinx.coroutines.Dispatchers.getIO();
            b0.x v2_2 = new b0.x(this, 0, 2);
            v0_2.f = 1;
            IllegalStateException v7_3 = kotlinx.coroutines.BuildersKt.withContext(v7_2, v2_2, v0_2);
            if (v7_3 != v1_1) {
                return v7_3;
            } else {
                return v1_1;
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
