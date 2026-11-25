package vn.com.vng.zalopay.vitals.collector.network;
public final class SendNetworkLogWorker extends androidx.work.CoroutineWorker {

    public SendNetworkLogWorker(android.content.Context p1, androidx.work.WorkerParameters p2)
    {
        super(p1, p2);
        return;
    }

    public final Object b(wg.c p11)
    {
        String v0_2;
        if (!(p11 instanceof rQ.l)) {
            v0_2 = new rQ.l(this, p11);
        } else {
            v0_2 = ((rQ.l) p11);
            Object v1_2 = ((rQ.l) p11).h;
            if ((v1_2 & -2147483648) == 0) {
            } else {
                ((rQ.l) p11).h = (v1_2 - -2147483648);
            }
        }
        kotlin.Unit v11_21;
        Object v2_4;
        java.util.List v6_2;
        java.util.List v6_1;
        Object v2_6;
        kotlin.Unit v11_6 = v0_2.f;
        Object v1_1 = vg.i.getCOROUTINE_SUSPENDED();
        Object v2_0 = v0_2.h;
        try {
            if (v2_0 == null) {
                kotlin.ResultKt.throwOnFailure(v11_6);
                p11 = BQ.c.a;
                kotlin.Unit v11_9 = ((BQ.a) kQ.f.d.get("network"));
                if ((v11_9 instanceof rQ.f)) {
                    v6_1 = v11_9;
                    kotlin.Unit v11_16;
                    kotlin.Unit v11_15 = ((rQ.f) v6_1).g;
                    if (v11_15 == null) {
                        v11_16 = kotlin.collections.E.emptyList();
                    } else {
                        v11_16 = v11_15.b();
                        if (v11_16 == null) {
                        }
                    }
                    if (!v11_16.isEmpty()) {
                        v2_4 = kotlin.collections.CollectionsKt.take(((Iterable) v11_16), 30);
                        kotlin.Unit v11_19 = new rQ.g(v2_4);
                        v11_19.toString();
                        xQ.a v7 = kQ.f.f;
                        if (v7 == null) {
                            v6_2 = v2_4;
                            v2_6 = v6_1;
                            v11_21 = 0;
                            java.util.Objects.toString(v11_21);
                            if ((v11_21 == null) || (v11_21.a() != null)) {
                                return new androidx.work.p();
                            } else {
                                kotlin.Unit v11_1;
                                kotlin.Unit v11_26 = ((rQ.f) v2_6);
                                v0_2.d = v2_6;
                                v0_2.e = 0;
                                v0_2.h = 2;
                                kotlin.Unit v11_27 = v11_26.g;
                                if (v11_27 == null) {
                                    v11_1 = kotlin.Unit.INSTANCE;
                                } else {
                                    v11_1 = v11_27.c(v6_2, v0_2);
                                    if (v11_1 != vg.i.getCOROUTINE_SUSPENDED()) {
                                        v11_1 = kotlin.Unit.INSTANCE;
                                    } else {
                                    }
                                }
                                if (v11_1 == v1_1) {
                                }
                            }
                        } else {
                            v0_2.d = v6_1;
                            v0_2.e = ((java.util.List) v2_4);
                            v0_2.h = 1;
                            v11_6 = v7.a(v11_19, v0_2);
                            if (v11_6 != v1_1) {
                                v11_21 = ((yQ.f) v11_6);
                                v6_2 = v2_4;
                                v2_6 = v6_1;
                            } else {
                            }
                        }
                    } else {
                        return androidx.work.r.b();
                    }
                } else {
                    return androidx.work.r.a();
                }
            } else {
                if (v2_0 == 1) {
                    v2_4 = ((java.util.List) v0_2.e);
                    v6_1 = v0_2.d;
                    kotlin.ResultKt.throwOnFailure(v11_6);
                } else {
                    if (v2_0 != 2) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        v2_6 = v0_2.d;
                        kotlin.ResultKt.throwOnFailure(v11_6);
                        v6_1 = v2_6;
                    }
                }
            }
        } catch (kotlin.Unit v11_2) {
            v11_2.toString();
            return new androidx.work.p();
        }
        return v1_1;
    }
}
