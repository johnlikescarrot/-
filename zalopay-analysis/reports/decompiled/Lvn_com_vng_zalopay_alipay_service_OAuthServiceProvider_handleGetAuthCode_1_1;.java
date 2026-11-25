package vn.com.vng.zalopay.alipay.service;
final class OAuthServiceProvider$handleGetAuthCode$1$1 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    final synthetic com.alipay.iap.android.wallet.acl.base.Callback $callback;
    final synthetic com.alipay.iap.android.wallet.acl.oauth.OAuthResult $oAuthResult;
    synthetic Object L$0;
    int label;

    public OAuthServiceProvider$handleGetAuthCode$1$1(com.alipay.iap.android.wallet.acl.oauth.OAuthResult p1, com.alipay.iap.android.wallet.acl.base.Callback p2, kotlin.coroutines.Continuation p3)
    {
        this.$oAuthResult = p1;
        this.$callback = p2;
        super(2, p3);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p4, kotlin.coroutines.Continuation p5)
    {
        vn.com.vng.zalopay.alipay.service.OAuthServiceProvider$handleGetAuthCode$1$1 v0_1 = new vn.com.vng.zalopay.alipay.service.OAuthServiceProvider$handleGetAuthCode$1$1(this.$oAuthResult, this.$callback, p5);
        v0_1.L$0 = p4;
        return v0_1;
    }

    public final Object invoke(Nm.a p1, kotlin.coroutines.Continuation p2)
    {
        return ((vn.com.vng.zalopay.alipay.service.OAuthServiceProvider$handleGetAuthCode$1$1) this.create(p1, p2)).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public bridge synthetic Object invoke(Object p1, Object p2)
    {
        return this.invoke(((Nm.a) p1), ((kotlin.coroutines.Continuation) p2));
    }

    public final Object invokeSuspend(Object p4)
    {
        com.alipay.iap.android.wallet.acl.oauth.OAuthResult v0_1 = ((Nm.a) this.L$0);
        vg.i.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
        } else {
            kotlin.ResultKt.throwOnFailure(p4);
            if (v0_1 != null) {
                this.$oAuthResult.setAuthCode(v0_1.a);
                this.$oAuthResult.setAuthErrorScopes(new java.util.HashMap());
                String[] v1_4 = new String[0];
                this.$oAuthResult.setAuthSuccessScopes(((String[]) ((java.util.Collection) v0_1.b).toArray(v1_4)));
                kotlin.Unit v4_4 = this.$callback;
                if (v4_4 != null) {
                    v4_4.result(this.$oAuthResult);
                }
                return kotlin.Unit.INSTANCE;
            } else {
                this.$oAuthResult.setResultError(new com.alipay.iap.android.wallet.acl.base.ResultError(1005, "Failed to retrieve the OAuth code from the service."));
                kotlin.Unit v4_7 = this.$callback;
                if (v4_7 != null) {
                    v4_7.result(this.$oAuthResult);
                }
                return kotlin.Unit.INSTANCE;
            }
        }
    }
}
