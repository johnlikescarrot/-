package vn.com.vng.zalopay.splashscreen.worker_manager;
public final class UpdateUserInfoOFPWorker extends androidx.work.CoroutineWorker {
    public final android.content.Context d;
    public uc.i e;

    public UpdateUserInfoOFPWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        super.d = p1;
        return;
    }

    public final Object b(wg.c p6)
    {
        String v0_2;
        if (!(p6 instanceof cO.d)) {
            v0_2 = new cO.d(this, p6);
        } else {
            v0_2 = ((cO.d) p6);
            Object v1_2 = ((cO.d) p6).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((cO.d) p6).f = (v1_2 - -2147483648);
            }
        }
        IllegalStateException v6_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        cO.f v2_0 = v0_2.f;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v6_1);
            IllegalStateException v6_2 = kotlinx.coroutines.Dispatchers.getIO();
            cO.f v2_2 = new cO.f(this, 0);
            v0_2.f = 1;
            IllegalStateException v6_3 = kotlinx.coroutines.BuildersKt.withContext(v6_2, v2_2, v0_2);
            if (v6_3 != v1_1) {
                return v6_3;
            } else {
                return v1_1;
            }
        } else {
            if (v2_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(v6_1);
                return v6_1;
            }
        }
    }
}
