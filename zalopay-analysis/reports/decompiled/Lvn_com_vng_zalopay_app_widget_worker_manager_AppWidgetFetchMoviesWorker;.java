package vn.com.vng.zalopay.app_widget.worker_manager;
public final class AppWidgetFetchMoviesWorker extends androidx.work.CoroutineWorker {
    public gn.a d;

    public AppWidgetFetchMoviesWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        return;
    }

    public final Object b(wg.c p6)
    {
        String v0_2;
        if (!(p6 instanceof rn.d)) {
            v0_2 = new rn.d(this, p6);
        } else {
            v0_2 = ((rn.d) p6);
            Object v1_2 = ((rn.d) p6).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((rn.d) p6).f = (v1_2 - -2147483648);
            }
        }
        IllegalStateException v6_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        ki.k v2_0 = v0_2.f;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v6_1);
            this.d = ((gn.a) m9.Q4.a().g.get());
            IllegalStateException v6_6 = kotlinx.coroutines.Dispatchers.getIO();
            ki.k v2_2 = new ki.k(this, 0);
            v0_2.f = 1;
            IllegalStateException v6_7 = kotlinx.coroutines.BuildersKt.withContext(v6_6, v2_2, v0_2);
            if (v6_7 != v1_1) {
                return v6_7;
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
