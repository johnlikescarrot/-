package vn.com.vng.zalopay.network_monitor.data.monitor;
public final class p extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    public final synthetic int d;
    public int e;
    public final synthetic Object f;

    public synthetic p(Object p1, kotlin.coroutines.Continuation p2, int p3)
    {
        this.d = p3;
        this.f = p1;
        super(2, p2);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p3, kotlin.coroutines.Continuation p4)
    {
        switch (this.d) {
            case 0:
                return new vn.com.vng.zalopay.network_monitor.data.monitor.p(((vn.com.vng.zalopay.network_monitor.data.monitor.q) this.f), p4, 0);
            case 1:
                return new vn.com.vng.zalopay.network_monitor.data.monitor.p(((vn.com.vng.zalopay.permission.notification.presentation.NotificationPermissionActivity) this.f), p4, 1);
            case 2:
                return new vn.com.vng.zalopay.network_monitor.data.monitor.p(((y7.a) this.f), p4, 2);
            case 3:
                return new vn.com.vng.zalopay.network_monitor.data.monitor.p(((vn.com.vng.zalopay.livenessdetection.presentation.face_auth_revamp.FaceLivenessRevampActivity) this.f), p4, 3);
            default:
                return new vn.com.vng.zalopay.network_monitor.data.monitor.p(((zJ.J) this.f), p4, 4);
        }
    }

    public final Object invoke(Object p2, Object p3)
    {
        switch (this.d) {
            case 0:
                return ((vn.com.vng.zalopay.network_monitor.data.monitor.p) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            case 1:
                return ((vn.com.vng.zalopay.network_monitor.data.monitor.p) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            case 2:
                return ((vn.com.vng.zalopay.network_monitor.data.monitor.p) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            case 3:
                return ((vn.com.vng.zalopay.network_monitor.data.monitor.p) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            default:
                return ((vn.com.vng.zalopay.network_monitor.data.monitor.p) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
        }
    }

    public final Object invokeSuspend(Object p7)
    {
        y7.a v2_1 = this.f;
        switch (this.d) {
            case 0:
                y7.a v2_5 = ((vn.com.vng.zalopay.network_monitor.data.monitor.q) v2_1);
                kotlin.Unit v0_1 = vg.i.getCOROUTINE_SUSPENDED();
                int v4_5 = this.e;
                if (v4_5 == 0) {
                    kotlin.ResultKt.throwOnFailure(p7);
                    this.e = 1;
                    p7 = vn.com.vng.zalopay.network_monitor.data.monitor.q.f(v2_5, ((String) kotlin.collections.CollectionsKt.first(rH.b.a)), kotlin.collections.Q.a, this);
                    if (p7 != v0_1) {
                        if (((Boolean) p7).booleanValue()) {
                            v0_1 = kotlin.Unit.INSTANCE;
                        } else {
                            Object v7_33 = v2_5.z;
                            this.e = 2;
                            if (v7_33.emit(kotlin.Unit.INSTANCE, this) != v0_1) {
                            }
                        }
                    }
                } else {
                    if (v4_5 == 1) {
                        kotlin.ResultKt.throwOnFailure(p7);
                    } else {
                        if (v4_5 != 2) {
                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                        } else {
                            kotlin.ResultKt.throwOnFailure(p7);
                        }
                    }
                }
                return v0_1;
            case 1:
                y7.a v2_3 = ((vn.com.vng.zalopay.permission.notification.presentation.NotificationPermissionActivity) v2_1);
                kotlin.Unit v0_7 = vg.i.getCOROUTINE_SUSPENDED();
                int v4_3 = this.e;
                if (v4_3 == 0) {
                    kotlin.ResultKt.throwOnFailure(p7);
                    Object v7_24 = ji.j0.q(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.H0(v2_3.i.d, 1));
                    kotlin.Unit v1_6 = new androidx.lifecycle.n(22, v2_3);
                    this.e = 1;
                    if (v7_24.e(v1_6, this) != v0_7) {
                        v0_7 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v4_3 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p7);
                    }
                }
                return v0_7;
            case 2:
                kotlin.Unit v0_6 = vg.i.getCOROUTINE_SUSPENDED();
                int v4_2 = this.e;
                if (v4_2 == 0) {
                    kotlin.ResultKt.throwOnFailure(p7);
                    Object v7_18 = ((y7.a) v2_1).H;
                    if (v7_18 == null) {
                        v0_6 = kotlin.Unit.INSTANCE;
                    } else {
                        this.e = 1;
                        if (gi.N.f(v7_18, this) != v0_6) {
                        }
                    }
                } else {
                    if (v4_2 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p7);
                    }
                }
                return v0_6;
            case 3:
                y7.a v2_4 = ((vn.com.vng.zalopay.livenessdetection.presentation.face_auth_revamp.FaceLivenessRevampActivity) v2_1);
                kotlin.Unit v0_5 = vg.i.getCOROUTINE_SUSPENDED();
                int v4_6 = this.e;
                if (v4_6 == 0) {
                    kotlin.ResultKt.throwOnFailure(p7);
                    Object v7_14 = v2_4.h1().g();
                    kotlin.Unit v1_2 = new zD.g(v2_4, 0);
                    this.e = 1;
                    if (v7_14.e(v1_2, this) != v0_5) {
                        v0_5 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v4_6 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p7);
                    }
                }
                return v0_5;
            default:
                y7.a v2_0 = ((zJ.J) v2_1);
                kotlin.Unit v0_2 = vg.i.getCOROUTINE_SUSPENDED();
                int v4_0 = this.e;
                if (v4_0 == 0) {
                    kotlin.ResultKt.throwOnFailure(p7);
                    if (!((Boolean) v2_0.b.getValue()).booleanValue()) {
                        v0_2 = kotlin.Unit.INSTANCE;
                    } else {
                        this.e = 1;
                        if (gi.U.a(500, this) != v0_2) {
                            v2_0.a.setValue(0);
                            v2_0.b.setValue(Boolean.FALSE);
                        }
                    }
                } else {
                    if (v4_0 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p7);
                    }
                }
                return v0_2;
        }
    }
}
