package vn.com.vng.zalopay.alipay.service;
final class MemberServiceProvider$getPublicProfileRemote$2$1$1 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    final synthetic kotlin.coroutines.Continuation $continuation;
    final synthetic ji.i $flow;
    int label;

    public MemberServiceProvider$getPublicProfileRemote$2$1$1(ji.i p1, kotlin.coroutines.Continuation p2, kotlin.coroutines.Continuation p3)
    {
        this.$flow = p1;
        this.$continuation = p2;
        super(2, p3);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p3, kotlin.coroutines.Continuation p4)
    {
        return new vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2$1$1(this.$flow, this.$continuation, p4);
    }

    public bridge synthetic Object invoke(Object p1, Object p2)
    {
        return this.invoke(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2));
    }

    public final Object invoke(kotlinx.coroutines.CoroutineScope p1, kotlin.coroutines.Continuation p2)
    {
        return ((vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2$1$1) this.create(p1, p2)).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p6)
    {
        String v0_0 = vg.i.getCOROUTINE_SUSPENDED();
        vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2$1$1$1 v1_1 = this.label;
        if (v1_1 == null) {
            kotlin.ResultKt.throwOnFailure(p6);
            kotlin.Unit v6_5 = this.$flow;
            vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2$1$1$1 v1_0 = new vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2$1$1$1(this.$continuation, 0);
            this.label = 1;
            if (ji.j0.m(v6_5, v1_0, this) == v0_0) {
                return v0_0;
            }
        } else {
            if (v1_1 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(p6);
            }
        }
        return kotlin.Unit.INSTANCE;
    }
}
