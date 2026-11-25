package vn.com.vng.zalopay.homev5.presenter.ui.home_page;
public final class B0 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    public final synthetic int d;
    public int e;
    public final synthetic ji.c0 f;
    public final synthetic q0.C0 g;
    public final synthetic vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 h;

    public synthetic B0(ji.c0 p1, q0.C0 p2, vn.com.vng.zalopay.homev5.presenter.ui.home_page.g1 p3, kotlin.coroutines.Continuation p4, int p5)
    {
        this.d = p5;
        this.f = p1;
        this.g = p2;
        this.h = p3;
        super(2, p4);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p8, kotlin.coroutines.Continuation p9)
    {
        switch (this.d) {
            case 0:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.B0(this.f, this.g, this.h, p9, 0);
            default:
                return new vn.com.vng.zalopay.homev5.presenter.ui.home_page.B0(this.f, this.g, this.h, p9, 1);
        }
    }

    public final Object invoke(Object p2, Object p3)
    {
        switch (this.d) {
            case 0:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.B0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
            default:
                return ((vn.com.vng.zalopay.homev5.presenter.ui.home_page.B0) this.create(((kotlinx.coroutines.CoroutineScope) p2), ((kotlin.coroutines.Continuation) p3))).invokeSuspend(kotlin.Unit.INSTANCE);
        }
    }

    public final Object invokeSuspend(Object p6)
    {
        switch (this.d) {
            case 0:
                String v0_2 = vg.i.getCOROUTINE_SUSPENDED();
                ji.f0 v1_4 = this.e;
                if (v1_4 == null) {
                    kotlin.ResultKt.throwOnFailure(p6);
                    kotlin.KotlinNothingValueException v6_1 = new bW.d(this.g, this.h, 26);
                    this.e = 1;
                    if (this.f.b.e(v6_1, this) == v0_2) {
                        return v0_2;
                    }
                } else {
                    if (v1_4 == 1) {
                        kotlin.ResultKt.throwOnFailure(p6);
                    } else {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                throw new kotlin.KotlinNothingValueException();
            default:
                String v0_3 = vg.i.getCOROUTINE_SUSPENDED();
                ji.f0 v1_2 = this.e;
                if (v1_2 == null) {
                    kotlin.ResultKt.throwOnFailure(p6);
                    kotlin.KotlinNothingValueException v6_9 = ji.j0.q(new WJ.J(new CA.X(this.f, this.g, 18), 5));
                    ji.f0 v1_8 = new androidx.lifecycle.n(21, this.h);
                    this.e = 1;
                    if (v6_9.e(v1_8, this) != v0_3) {
                        v0_3 = kotlin.Unit.INSTANCE;
                    }
                } else {
                    if (v1_2 != 1) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p6);
                    }
                }
                return v0_3;
        }
    }
}
