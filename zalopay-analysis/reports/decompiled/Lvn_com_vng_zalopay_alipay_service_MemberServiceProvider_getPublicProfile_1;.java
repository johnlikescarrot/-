package vn.com.vng.zalopay.alipay.service;
final class MemberServiceProvider$getPublicProfile$1 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    final synthetic String $appId;
    final synthetic kotlin.jvm.functions.Function1 $callback;
    final synthetic java.util.List $scopes;
    final synthetic vn.com.vng.zalopay.alipay.service.AlipayMiniAppService $service;
    int label;
    final synthetic vn.com.vng.zalopay.alipay.service.MemberServiceProvider this$0;

    public MemberServiceProvider$getPublicProfile$1(vn.com.vng.zalopay.alipay.service.MemberServiceProvider p1, String p2, java.util.List p3, vn.com.vng.zalopay.alipay.service.AlipayMiniAppService p4, kotlin.jvm.functions.Function1 p5, kotlin.coroutines.Continuation p6)
    {
        this.this$0 = p1;
        this.$appId = p2;
        this.$scopes = p3;
        this.$service = p4;
        this.$callback = p5;
        super(2, p6);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p8, kotlin.coroutines.Continuation p9)
    {
        return new vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfile$1(this.this$0, this.$appId, this.$scopes, this.$service, this.$callback, p9);
    }

    public bridge synthetic Object invoke(Object p1, Object p2)
    {
        return this.invoke(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2));
    }

    public final Object invoke(kotlinx.coroutines.CoroutineScope p1, kotlin.coroutines.Continuation p2)
    {
        return ((vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfile$1) this.create(p1, p2)).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p5)
    {
        kotlin.jvm.functions.Function1 v0_0 = vg.i.getCOROUTINE_SUSPENDED();
        String v1_0 = this.label;
        if (v1_0 == null) {
            kotlin.ResultKt.throwOnFailure(p5);
            kotlin.Unit v5_5 = this.this$0;
            String v1_2 = this.$appId;
            java.util.List v3 = this.$scopes;
            this.label = 1;
            p5 = vn.com.vng.zalopay.alipay.service.MemberServiceProvider.access$getPublicProfileRemote(v5_5, v1_2, v3, this);
            if (p5 == v0_0) {
                return v0_0;
            }
        } else {
            if (v1_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(p5);
            }
        }
        if (((Nm.b) p5) != null) {
            this.$service.setPublicProfileMiniProgram(this.$appId, ((Nm.b) p5));
        }
        this.$callback.invoke(((Nm.b) p5));
        return kotlin.Unit.INSTANCE;
    }
}
