package vn.com.vng.zalopay.alipay.service;
final class OAuthServiceProvider$handleGetAuthCode$1 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    final synthetic String $appId;
    final synthetic com.alipay.iap.android.wallet.acl.base.Callback $callback;
    final synthetic com.alipay.iap.android.wallet.acl.oauth.OAuthResult $oAuthResult;
    final synthetic java.util.List $scopes;
    Object L$0;
    int label;

    public OAuthServiceProvider$handleGetAuthCode$1(String p1, java.util.List p2, com.alipay.iap.android.wallet.acl.oauth.OAuthResult p3, com.alipay.iap.android.wallet.acl.base.Callback p4, kotlin.coroutines.Continuation p5)
    {
        this.$appId = p1;
        this.$scopes = p2;
        this.$oAuthResult = p3;
        this.$callback = p4;
        super(2, p5);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p7, kotlin.coroutines.Continuation p8)
    {
        return new vn.com.vng.zalopay.alipay.service.OAuthServiceProvider$handleGetAuthCode$1(this.$appId, this.$scopes, this.$oAuthResult, this.$callback, p8);
    }

    public bridge synthetic Object invoke(Object p1, Object p2)
    {
        return this.invoke(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2));
    }

    public final Object invoke(kotlinx.coroutines.CoroutineScope p1, kotlin.coroutines.Continuation p2)
    {
        return ((vn.com.vng.zalopay.alipay.service.OAuthServiceProvider$handleGetAuthCode$1) this.create(p1, p2)).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p10)
    {
        com.alipay.iap.android.wallet.acl.oauth.OAuthResult v0_0 = vg.i.getCOROUTINE_SUSPENDED();
        vn.com.vng.zalopay.alipay.service.OAuthServiceProvider$handleGetAuthCode$1$1 v1_0 = this.label;
        if (v1_0 == null) {
            kotlin.Unit v10_1;
            kotlin.ResultKt.throwOnFailure(p10);
            kotlin.Unit v10_10 = com.google.android.gms.internal.mlkit_vision_barcode.n5.a;
            if (v10_10 == null) {
                v10_1 = 0;
            } else {
                com.alipay.iap.android.wallet.acl.base.Callback v4_0 = v10_10.getGetOAuthCodeMiniAppProgramUseCase();
                if (v4_0 == null) {
                } else {
                    v10_1 = new ji.e0(new PU.i(v4_0, this.$appId, this.$scopes, 0, 1));
                }
            }
            if (v10_1 != null) {
                vn.com.vng.zalopay.alipay.service.OAuthServiceProvider$handleGetAuthCode$1$1 v1_2 = new vn.com.vng.zalopay.alipay.service.OAuthServiceProvider$handleGetAuthCode$1$1(this.$oAuthResult, this.$callback, 0);
                this.L$0 = 0;
                this.label = 1;
                if (ji.j0.m(v10_1, v1_2, this) == v0_0) {
                    return v0_0;
                }
            } else {
                this.$oAuthResult.setResultError(new com.alipay.iap.android.wallet.acl.base.ResultError(1005, ""));
                kotlin.Unit v10_5 = this.$callback;
                if (v10_5 != null) {
                    v10_5.result(this.$oAuthResult);
                }
                return kotlin.Unit.INSTANCE;
            }
        } else {
            if (v1_0 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(p10);
            }
        }
        return kotlin.Unit.INSTANCE;
    }
}
