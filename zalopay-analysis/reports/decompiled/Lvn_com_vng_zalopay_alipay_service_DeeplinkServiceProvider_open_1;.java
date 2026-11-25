package vn.com.vng.zalopay.alipay.service;
final class DeeplinkServiceProvider$open$1 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    final synthetic android.net.Uri $uri;
    int label;

    public DeeplinkServiceProvider$open$1(android.net.Uri p1, kotlin.coroutines.Continuation p2)
    {
        this.$uri = p1;
        super(2, p2);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p2, kotlin.coroutines.Continuation p3)
    {
        return new vn.com.vng.zalopay.alipay.service.DeeplinkServiceProvider$open$1(this.$uri, p3);
    }

    public bridge synthetic Object invoke(Object p1, Object p2)
    {
        return this.invoke(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2));
    }

    public final Object invoke(kotlinx.coroutines.CoroutineScope p1, kotlin.coroutines.Continuation p2)
    {
        return ((vn.com.vng.zalopay.alipay.service.DeeplinkServiceProvider$open$1) this.create(p1, p2)).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p4)
    {
        String v0_0 = vg.i.getCOROUTINE_SUSPENDED();
        android.app.Activity v1_1 = this.label;
        if (v1_1 == null) {
            Object v4_1;
            kotlin.ResultKt.throwOnFailure(p4);
            Object v4_4 = this.$uri;
            this.label = 1;
            android.app.Activity v1_0 = Em.c.b();
            if (v1_0 != null) {
                v4_1 = com.google.android.gms.internal.mlkit_vision_barcode.p5.c(v1_0, v4_4, this);
            } else {
                v4_1 = Boolean.FALSE;
            }
            if (v4_1 != v0_0) {
                return v4_1;
            } else {
                return v0_0;
            }
        } else {
            if (v1_1 != 1) {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            } else {
                kotlin.ResultKt.throwOnFailure(p4);
                return p4;
            }
        }
    }
}
