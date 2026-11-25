package vn.com.zalopay.inappupdate.workmanager;
public final class InAppUpdateGetVersionWorker extends androidx.work.CoroutineWorker {
    public final android.content.Context d;
    public St.a e;

    public InAppUpdateGetVersionWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        super.d = p1;
        return;
    }

    public final Object b(wg.c p7)
    {
        String v0_2;
        if (!(p7 instanceof UW.a)) {
            v0_2 = new UW.a(this, p7);
        } else {
            v0_2 = ((UW.a) p7);
            Object v1_2 = ((UW.a) p7).f;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((UW.a) p7).f = (v1_2 - -2147483648);
            }
        }
        IllegalStateException v7_1 = v0_2.d;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        B2.X v2_0 = v0_2.f;
        if (v2_0 == null) {
            kotlin.ResultKt.throwOnFailure(v7_1);
            IllegalStateException v7_2 = m9.P2.a;
            B2.X v2_3 = ((CC.b) v7_2.b).a();
            p9.n7.b(v2_3);
            this.e = v2_3;
            IllegalStateException v7_5 = ((CC.b) v7_2.b).Q();
            p9.n7.b(v7_5);
            IllegalStateException v7_6 = v7_5.c;
            B2.X v2_5 = new B2.X(this, 0, 8);
            v0_2.f = 1;
            IllegalStateException v7_7 = kotlinx.coroutines.BuildersKt.withContext(v7_6, v2_5, v0_2);
            if (v7_7 != v1_1) {
                return v7_7;
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
