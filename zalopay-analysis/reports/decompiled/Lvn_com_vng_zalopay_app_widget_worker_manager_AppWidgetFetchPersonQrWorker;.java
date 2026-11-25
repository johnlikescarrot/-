package vn.com.vng.zalopay.app_widget.worker_manager;
public final class AppWidgetFetchPersonQrWorker extends androidx.work.CoroutineWorker {
    public final android.content.Context d;
    public gn.a e;

    public AppWidgetFetchPersonQrWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        super.d = p1;
        return;
    }

    public final Object b(wg.c p6)
    {
        String v0_2;
        if (!(p6 instanceof rn.e)) {
            v0_2 = new rn.e(this, p6);
        } else {
            v0_2 = ((rn.e) p6);
            Object v1_2 = ((rn.e) p6).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((rn.e) p6).f = (v1_2 - -2147483648);
            }
        }
        Object v6_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        B4.w v2_0 = v0_2.f;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v6_1);
            v0_2.f = 1;
            v6_1 = q9.w5.d(this.d, v0_2);
            if (v6_1 != v1_1) {
                if (((Boolean) v6_1).booleanValue()) {
                    this.e = ((gn.a) m9.Q4.a().g.get());
                    Object v6_9 = kotlinx.coroutines.Dispatchers.getIO();
                    B4.w v2_2 = new B4.w(this, 0);
                    v0_2.f = 2;
                    Object v6_10 = kotlinx.coroutines.BuildersKt.withContext(v6_9, v2_2, v0_2);
                    if (v6_10 != v1_1) {
                        return v6_10;
                    }
                } else {
                    return androidx.work.r.b();
                }
            }
        } else {
            if (v2_0 == 1) {
                kotlin.ResultKt.throwOnFailure(v6_1);
            } else {
                if (v2_0 != 2) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                } else {
                    kotlin.ResultKt.throwOnFailure(v6_1);
                    return v6_1;
                }
            }
        }
        return v1_1;
    }
}
