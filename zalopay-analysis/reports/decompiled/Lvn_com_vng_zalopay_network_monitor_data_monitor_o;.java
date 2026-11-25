package vn.com.vng.zalopay.network_monitor.data.monitor;
public final class o extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    public int d;
    public synthetic Object e;
    public final synthetic vn.com.vng.zalopay.network_monitor.data.monitor.d f;
    public final synthetic vn.com.vng.zalopay.network_monitor.data.monitor.q g;

    public o(vn.com.vng.zalopay.network_monitor.data.monitor.d p1, vn.com.vng.zalopay.network_monitor.data.monitor.q p2, kotlin.coroutines.Continuation p3)
    {
        this.f = p1;
        this.g = p2;
        super(2, p3);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p4, kotlin.coroutines.Continuation p5)
    {
        vn.com.vng.zalopay.network_monitor.data.monitor.o v0_1 = new vn.com.vng.zalopay.network_monitor.data.monitor.o(this.f, this.g, p5);
        v0_1.e = p4;
        return v0_1;
    }

    public final Object invoke(Object p1, Object p2)
    {
        return ((vn.com.vng.zalopay.network_monitor.data.monitor.o) this.create(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2))).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p10)
    {
        vn.com.vng.zalopay.network_monitor.data.monitor.q v0_1 = ((kotlinx.coroutines.CoroutineScope) this.e);
        vn.com.vng.zalopay.network_monitor.data.monitor.q v1_0 = vg.i.getCOROUTINE_SUSPENDED();
        Object v2_3 = this.d;
        boolean v4 = 1;
        boolean v6 = 0;
        try {
            if (v2_3 == null) {
                kotlin.ResultKt.throwOnFailure(p10);
                if (this.f != vn.com.vng.zalopay.network_monitor.data.monitor.d.a) {
                    Throwable v10_14;
                    Object v2_2 = vn.com.vng.zalopay.network_monitor.data.monitor.d.a;
                    if (this.f != v2_2) {
                        v10_14 = kotlin.collections.Q.a;
                    } else {
                        v10_14 = vn.com.vng.zalopay.network_monitor.data.monitor.q.d(this.g);
                    }
                    if ((this.f != v2_2) || (!v10_14.isEmpty())) {
                        Object v2_5 = this.g;
                        if (this.f != vn.com.vng.zalopay.network_monitor.data.monitor.d.b) {
                            v4 = 0;
                        } else {
                        }
                        this.e = v0_1;
                        this.d = 2;
                        if (vn.com.vng.zalopay.network_monitor.data.monitor.q.g(v2_5, v10_14, v4, this) == v1_0) {
                            return v1_0;
                        }
                    } else {
                        vn.com.vng.zalopay.network_monitor.data.monitor.q v1_2 = this.g;
                        try {
                            if (v1_2.k == ((kotlinx.coroutines.Job) v0_1.getCoroutineContext().get(kotlinx.coroutines.Job.Key))) {
                                v1_2.k = 0;
                                v1_2.l = 0;
                                v1_2.m = 0;
                                v6 = vn.com.vng.zalopay.network_monitor.data.monitor.q.h(v1_2);
                            } else {
                            }
                        } catch (Throwable v10_18) {
                            throw v10_18;
                        }
                        if (v6) {
                            this.g.t();
                        }
                        return kotlin.Unit.INSTANCE;
                    }
                } else {
                    vn.com.vng.zalopay.network_monitor.data.monitor.d v7_1 = q9.K6.g(1500, kotlin.time.f.c);
                    this.e = v0_1;
                    this.d = 1;
                    if (gi.U.b(v7_1, this) != v1_0) {
                        this.g.l = vn.com.vng.zalopay.network_monitor.data.monitor.c.b;
                    } else {
                        return v1_0;
                    }
                }
            } else {
                if (v2_3 == 1) {
                    kotlin.ResultKt.throwOnFailure(p10);
                } else {
                    if (v2_3 != 2) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p10);
                    }
                }
            }
        } catch (Throwable v10_21) {
            vn.com.vng.zalopay.network_monitor.data.monitor.q v1_4 = this.g;
            try {
                if (v1_4.k == ((kotlinx.coroutines.Job) v0_1.getCoroutineContext().get(kotlinx.coroutines.Job.Key))) {
                    v1_4.k = 0;
                    v1_4.l = 0;
                    v1_4.m = 0;
                    v6 = vn.com.vng.zalopay.network_monitor.data.monitor.q.h(v1_4);
                } else {
                }
            } catch (Throwable v10_1) {
                throw v10_1;
            }
            if (v6) {
                this.g.t();
            }
            throw v10_21;
        }
        vn.com.vng.zalopay.network_monitor.data.monitor.q v0_4 = this.g;
        try {
            if (v0_4.k == ((kotlinx.coroutines.Job) v0_1.getCoroutineContext().get(kotlinx.coroutines.Job.Key))) {
                v0_4.k = 0;
                v0_4.l = 0;
                v0_4.m = 0;
                v6 = vn.com.vng.zalopay.network_monitor.data.monitor.q.h(v0_4);
            } else {
            }
        } catch (Throwable v10_6) {
            throw v10_6;
        }
        if (v6) {
            this.g.t();
        }
        return kotlin.Unit.INSTANCE;
    }
}
