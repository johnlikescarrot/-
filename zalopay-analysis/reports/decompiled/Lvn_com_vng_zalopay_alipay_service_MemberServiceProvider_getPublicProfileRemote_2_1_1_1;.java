package vn.com.vng.zalopay.alipay.service;
final class MemberServiceProvider$getPublicProfileRemote$2$1$1$1 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    final synthetic kotlin.coroutines.Continuation $continuation;
    synthetic Object L$0;
    int label;

    public MemberServiceProvider$getPublicProfileRemote$2$1$1$1(kotlin.coroutines.Continuation p1, kotlin.coroutines.Continuation p2)
    {
        this.$continuation = p1;
        super(2, p2);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p3, kotlin.coroutines.Continuation p4)
    {
        vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2$1$1$1 v0_1 = new vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2$1$1$1(this.$continuation, p4);
        v0_1.L$0 = p3;
        return v0_1;
    }

    public final Object invoke(Nm.b p1, kotlin.coroutines.Continuation p2)
    {
        return ((vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2$1$1$1) this.create(p1, p2)).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public bridge synthetic Object invoke(Object p1, Object p2)
    {
        return this.invoke(((Nm.b) p1), ((kotlin.coroutines.Continuation) p2));
    }

    public final Object invokeSuspend(Object p3)
    {
        String v0_1 = ((Nm.b) this.L$0);
        vg.i.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
        } else {
            kotlin.ResultKt.throwOnFailure(p3);
            this.$continuation.resumeWith(v0_1);
            return kotlin.Unit.INSTANCE;
        }
    }
}
