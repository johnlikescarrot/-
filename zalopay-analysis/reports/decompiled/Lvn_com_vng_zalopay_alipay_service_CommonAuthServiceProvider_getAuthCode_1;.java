package vn.com.vng.zalopay.alipay.service;
final class CommonAuthServiceProvider$getAuthCode$1 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    final synthetic com.iap.android.mppclient.basic.callback.Callback $callback;
    final synthetic com.iap.android.mppclient.basic.model.CommonOAuthServiceParams $params;
    int label;
    final synthetic vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider this$0;

    public CommonAuthServiceProvider$getAuthCode$1(vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider p1, com.iap.android.mppclient.basic.model.CommonOAuthServiceParams p2, com.iap.android.mppclient.basic.callback.Callback p3, kotlin.coroutines.Continuation p4)
    {
        this.this$0 = p1;
        this.$params = p2;
        this.$callback = p3;
        super(2, p4);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p4, kotlin.coroutines.Continuation p5)
    {
        return new vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider$getAuthCode$1(this.this$0, this.$params, this.$callback, p5);
    }

    public bridge synthetic Object invoke(Object p1, Object p2)
    {
        return this.invoke(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2));
    }

    public final Object invoke(kotlinx.coroutines.CoroutineScope p1, kotlin.coroutines.Continuation p2)
    {
        return ((vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider$getAuthCode$1) this.create(p1, p2)).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p5)
    {
        vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider v0_0 = vg.i.getCOROUTINE_SUSPENDED();
        com.iap.android.mppclient.basic.callback.Callback v1_1 = this.label;
        try {
            if (v1_1 == null) {
                kotlin.ResultKt.throwOnFailure(p5);
                String v5_12 = vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider.access$getRepository$p(this.this$0);
                com.iap.android.mppclient.basic.callback.Callback v1_7 = this.$params;
                String v3 = v1_7.authClientId;
                com.iap.android.mppclient.basic.callback.Callback v1_0 = v1_7.scopes;
                this.label = 1;
                p5 = v5_12.a(v3, v1_0, this);
                if (p5 != v0_0) {
                    vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider v0_2 = ((Hm.d) p5).a();
                    if (v0_2 == null) {
                        vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider v0_3 = this.this$0;
                        com.iap.android.mppclient.basic.callback.Callback v1_2 = this.$callback;
                        String v2_1 = ((Hm.d) p5).b();
                        if (v2_1 == null) {
                            v2_1 = "1003";
                        }
                        String v5_4 = ((Hm.d) p5).c();
                        if (v5_4 == null) {
                            v5_4 = "SYSTEM_ERROR";
                        }
                        v0_3.sendFailedCallback(v1_2, v2_1, v5_4);
                    } else {
                        String v5_6 = new com.iap.android.mppclient.basic.model.CommonOAuthServiceResult();
                        v5_6.authCode = v0_2.a();
                        v5_6.authSuccessScopes = v0_2.c();
                        v5_6.authErrorScopes = v0_2.b();
                        this.this$0.sendSuccessCallback(this.$callback, v5_6);
                        return kotlin.Unit.INSTANCE;
                    }
                } else {
                    return v0_0;
                }
            } else {
                if (v1_1 != 1) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                } else {
                    kotlin.ResultKt.throwOnFailure(p5);
                }
            }
        } catch (String v5_11) {
            throw v5_11;
        } catch (java.util.concurrent.CancellationException) {
            this.this$0.sendFailedCallback(this.$callback, "1002", "INVALID_NETWORK");
        }
        return kotlin.Unit.INSTANCE;
    }
}
