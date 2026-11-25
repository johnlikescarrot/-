package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class w0 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    public final synthetic int d;
    public int e;
    public final synthetic int f;
    public final synthetic Object g;
    public final synthetic int h;

    public w0(Z.H p2, int p3, int p4, kotlin.coroutines.Continuation p5)
    {
        this.d = 1;
        this.f = p3;
        this.g = p2;
        this.h = p4;
        super(2, p5);
        return;
    }

    public synthetic w0(Object p1, int p2, int p3, kotlin.coroutines.Continuation p4, int p5)
    {
        this.d = p5;
        this.g = p1;
        this.f = p2;
        this.h = p3;
        super(2, p4);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p8, kotlin.coroutines.Continuation p9)
    {
        switch (this.d) {
            case 0:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.w0(((Z.H) this.g), this.f, this.h, p9, 0);
            case 1:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.w0(((Z.H) this.g), this.f, this.h, p9);
            default:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.w0(((ES.i) this.g), this.f, this.h, p9, 2);
        }
    }

    public final Object invoke(Object p2, Object p3)
    {
        switch (this.d) {
            case 0:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.w0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            case 1:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.w0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            default:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.w0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
        }
    }

    public final Object invokeSuspend(Object p11)
    {
        switch (this.d) {
            case 0:
                String v0_5 = vg.i.getCOROUTINE_SUSPENDED();
                kotlin.Unit v1_2 = this.e;
                if (v1_2 == null) {
                    kotlin.ResultKt.throwOnFailure(p11);
                    int v11_19 = ((Z.H) this.g);
                    this.e = 1;
                    int v11_20 = gi.L.c(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.w0(v11_19, this.h, this.f, 0), this);
                    if (v11_20 != vg.i.getCOROUTINE_SUSPENDED()) {
                        v11_20 = kotlin.Unit.INSTANCE;
                    }
                    if (v11_20 != v0_5) {
                        v0_5 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v1_2 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p11);
                    }
                }
                return v0_5;
            case 1:
                String v0_3 = ((Z.H) this.g);
                kotlin.Unit v1_1 = vg.i.getCOROUTINE_SUSPENDED();
                int v2_6 = this.e;
                if (v2_6 == 0) {
                    kotlin.ResultKt.throwOnFailure(p11);
                    int v2_0 = 0;
                    int v11_8 = Og.x.coerceAtLeast(this.f, 0);
                    int v4_4 = (v0_3.j().i() - v0_3.j().g());
                    if ((v4_4 > 0) && ((1 <= v11_8) && (v11_8 < v4_4))) {
                        v2_0 = (Og.x.coerceAtLeast((v4_4 - v11_8), 0) / 2);
                    }
                    if (v2_0 > 0) {
                        v2_0 -= v0_3.j().d();
                    }
                    int v11_14 = (- v2_0);
                    this.e = 1;
                    if (com.google.android.gms.internal.mlkit_vision_barcode.u6.c(v0_3, this.h, v11_14, this) != v1_1) {
                        v1_1 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v2_6 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p11);
                    }
                }
                return v1_1;
            default:
                String v0_8 = ((ES.i) this.g);
                kotlin.Unit v1_0 = vg.i.getCOROUTINE_SUSPENDED();
                int v2_8 = this.e;
                if (v2_8 == 0) {
                    kotlin.ResultKt.throwOnFailure(p11);
                    int v11_23 = v0_8.c;
                    int v2_10 = new mS.h(0);
                    this.e = 1;
                    v11_23.getClass();
                    v11_23.l(0, v2_10);
                    if (kotlin.Unit.INSTANCE != v1_0) {
                        int v4_1 = ((com.google.firebase.messaging.p) v0_8.g);
                        String v5_0 = String.valueOf(this.f);
                        this.e = 2;
                        v4_1.getClass();
                        p11 = new ji.e0(new B2.V(v4_1, v5_0, this.h, 0, 6));
                        if (p11 != v1_0) {
                            int v11_6 = ji.j0.x(((ji.i) p11), kotlinx.coroutines.Dispatchers.getIO());
                            int v2_3 = new ES.h(v0_8, 0);
                            this.e = 3;
                            if (v11_6.e(v2_3, this) != v1_0) {
                                v1_0 = kotlin.Unit.INSTANCE;
                            }
                        }
                    }
                } else {
                    if (v2_8 == 1) {
                        kotlin.ResultKt.throwOnFailure(p11);
                    } else {
                        if (v2_8 == 2) {
                            kotlin.ResultKt.throwOnFailure(p11);
                        } else {
                            if (v2_8 != 3) {
                                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                            } else {
                                kotlin.ResultKt.throwOnFailure(p11);
                            }
                        }
                    }
                }
                return v1_0;
        }
    }
}
