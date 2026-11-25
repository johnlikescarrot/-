package vn.com.vng.zalopay.alipay.service;
final class MemberServiceProvider$getPublicProfileRemote$2 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    final synthetic String $appId;
    final synthetic java.util.List $scopes;
    private synthetic Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    public MemberServiceProvider$getPublicProfileRemote$2(String p1, java.util.List p2, kotlin.coroutines.Continuation p3)
    {
        this.$appId = p1;
        this.$scopes = p2;
        super(2, p3);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p4, kotlin.coroutines.Continuation p5)
    {
        vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2 v0_1 = new vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2(this.$appId, this.$scopes, p5);
        v0_1.L$0 = p4;
        return v0_1;
    }

    public bridge synthetic Object invoke(Object p1, Object p2)
    {
        return this.invoke(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2));
    }

    public final Object invoke(kotlinx.coroutines.CoroutineScope p1, kotlin.coroutines.Continuation p2)
    {
        return ((vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2) this.create(p1, p2)).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p12)
    {
        kotlinx.coroutines.CoroutineScope v1_1 = ((kotlinx.coroutines.CoroutineScope) this.L$0);
        String v0_1 = vg.i.getCOROUTINE_SUSPENDED();
        int v2_2 = this.label;
        if (v2_2 == 0) {
            kotlin.ResultKt.throwOnFailure(p12);
            Object v12_6 = com.google.android.gms.internal.mlkit_vision_barcode.n5.a;
            if (v12_6 != null) {
                int v6_0 = this.$appId;
                java.util.List v7 = this.$scopes;
                this.L$0 = v1_1;
                this.L$1 = v12_6;
                this.L$2 = v6_0;
                this.L$3 = v7;
                this.label = 1;
                ug.e v10_1 = new ug.e(vg.h.intercepted(this));
                int v5_0 = v12_6.getGetPublicProfileMiniProgramUseCase();
                v5_0.getClass();
                kotlinx.coroutines.BuildersKt.launch$default(v1_1, 0, 0, new vn.com.vng.zalopay.alipay.service.MemberServiceProvider$getPublicProfileRemote$2$1$1(new ji.e0(new PU.i(v5_0, v6_0, v7, 0, 2)), v10_1, 0), 3, 0);
                Object v12_3 = v10_1.a();
                vg.i.getCOROUTINE_SUSPENDED();
                if (v12_3 != v0_1) {
                    return v12_3;
                } else {
                    return v0_1;
                }
            } else {
                return 0;
            }
        } else {
            if (v2_2 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                String v0 = ((String) this.L$2);
                kotlin.ResultKt.throwOnFailure(p12);
                return p12;
            }
        }
    }
}
