package vn.com.vng.zalopay.app_widget.worker_manager;
public final class AppWidgetFetchLocalPersonQrWorker extends androidx.work.CoroutineWorker {
    public final android.content.Context d;

    public AppWidgetFetchLocalPersonQrWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        super.d = p1;
        return;
    }

    public final Object b(wg.c p6)
    {
        String v0_2;
        if (!(p6 instanceof rn.c)) {
            v0_2 = new rn.c(this, p6);
        } else {
            v0_2 = ((rn.c) p6);
            Object v1_2 = ((rn.c) p6).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((rn.c) p6).f = (v1_2 - -2147483648);
            }
        }
        IllegalStateException v6_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        B2.V v2_0 = v0_2.f;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v6_1);
            IllegalStateException v6_2 = kotlinx.coroutines.Dispatchers.getIO();
            B2.V v2_2 = new B2.V(this, 0);
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
