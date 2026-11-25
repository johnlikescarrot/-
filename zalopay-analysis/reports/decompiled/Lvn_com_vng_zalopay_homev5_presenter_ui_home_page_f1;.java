package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class f1 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    public final synthetic int d;
    public int e;
    public final synthetic vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 f;
    public final synthetic boolean g;

    public synthetic f1(vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 p1, boolean p2, kotlin.coroutines.Continuation p3, int p4)
    {
        this.d = p4;
        this.f = p1;
        this.g = p2;
        super(2, p3);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p4, kotlin.coroutines.Continuation p5)
    {
        switch (this.d) {
            case 0:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.f1(this.f, this.g, p5, 0);
            default:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.f1(this.f, this.g, p5, 1);
        }
    }

    public final Object invoke(Object p2, Object p3)
    {
        switch (this.d) {
            case 0:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.f1) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            default:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.f1) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
        }
    }

    public final Object invokeSuspend(Object p21)
    {
        switch (this.d) {
            case 0:
                AA.f v4_1;
                Object v1_1 = this.f;
                ji.i v2_0 = v1_1.h;
                kotlin.Unit v3_0 = vg.i.getCOROUTINE_SUSPENDED();
                AA.f v4_7 = this.e;
                if (v4_7 == null) {
                    kotlin.ResultKt.throwOnFailure(p21);
                    AA.f v4_0 = v1_1.j;
                    this.e = 1;
                    v4_1 = v4_0.a(this);
                    if (v4_1 != v3_0) {
                        AA.f v4_2 = ((ji.i) v4_1);
                        if (this.g) {
                            kotlinx.coroutines.BuildersKt.launch$default(v1_1.i0(), v2_0.c, 0, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(v1_1, 0, 3), 2, 0);
                            kotlinx.coroutines.BuildersKt.launch$default(v1_1.i0(), v2_0.c, 0, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(v1_1, 0, 2), 2, 0);
                            kotlinx.coroutines.BuildersKt.launch$default(v1_1.i0(), v2_0.c, 0, new vn.com.vng.zalopay.homev5.presenter.ui.home_page.y0(v1_1, 0, 1), 2, 0);
                        }
                        ji.i v2_3 = ji.j0.q(new vn.com.vng.zalopay.homev5.presenter.ui.home_page.c1(ji.j0.q(v4_2), v1_1, 1));
                        AA.f v4_6 = new AA.f(9, v1_1);
                        this.e = 2;
                        if (v2_3.e(v4_6, this) != v3_0) {
                            v3_0 = kotlin.Unit.INSTANCE;
                        }
                    }
                } else {
                    if (v4_7 == 1) {
                        kotlin.ResultKt.throwOnFailure(p21);
                        v4_1 = p21;
                    } else {
                        if (v4_7 != 2) {
                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                        } else {
                            kotlin.ResultKt.throwOnFailure(p21);
                        }
                    }
                }
                return v3_0;
            default:
                Object v1_5 = vg.i.getCOROUTINE_SUSPENDED();
                ji.i v2_5 = this.e;
                if (v2_5 == null) {
                    kotlin.ResultKt.throwOnFailure(p21);
                    ji.i v2_7 = this.f.q;
                    this.e = 1;
                    if (v2_7.a.e(this.g) != v1_5) {
                        v1_5 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v2_5 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p21);
                    }
                }
                return v1_5;
        }
    }
}
