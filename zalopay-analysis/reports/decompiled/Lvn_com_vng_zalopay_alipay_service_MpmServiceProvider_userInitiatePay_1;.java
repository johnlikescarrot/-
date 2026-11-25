package vn.com.vng.zalopay.alipay.service;
final class MpmServiceProvider$userInitiatePay$1 extends kotlin.coroutines.jvm.internal.SuspendLambda implements kotlin.jvm.functions.Function2 {
    final synthetic String $codeValue;
    int label;
    final synthetic vn.com.vng.zalopay.alipay.service.MpmServiceProvider this$0;

    public MpmServiceProvider$userInitiatePay$1(vn.com.vng.zalopay.alipay.service.MpmServiceProvider p1, String p2, kotlin.coroutines.Continuation p3)
    {
        this.this$0 = p1;
        this.$codeValue = p2;
        super(2, p3);
        return;
    }

    public final kotlin.coroutines.Continuation create(Object p3, kotlin.coroutines.Continuation p4)
    {
        return new vn.com.vng.zalopay.alipay.service.MpmServiceProvider$userInitiatePay$1(this.this$0, this.$codeValue, p4);
    }

    public bridge synthetic Object invoke(Object p1, Object p2)
    {
        return this.invoke(((kotlinx.coroutines.CoroutineScope) p1), ((kotlin.coroutines.Continuation) p2));
    }

    public final Object invoke(kotlinx.coroutines.CoroutineScope p1, kotlin.coroutines.Continuation p2)
    {
        return ((vn.com.vng.zalopay.alipay.service.MpmServiceProvider$userInitiatePay$1) this.create(p1, p2)).invokeSuspend(kotlin.Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object p7)
    {
        vn.com.vng.zalopay.alipay.service.MpmServiceProvider v0_0 = vg.i.getCOROUTINE_SUSPENDED();
        String v1_1 = this.label;
        try {
            if (v1_1 == null) {
                kotlin.ResultKt.throwOnFailure(p7);
                kotlin.Unit v7_1 = vn.com.vng.zalopay.alipay.service.MpmServiceProvider.access$getDecodeAlipayMppUseCase$p(this.this$0);
                String v1_0 = this.$codeValue;
                if (v1_0 == null) {
                    v1_0 = "";
                }
                this.label = 1;
                p7 = new ji.e0(new AA.j(v7_1.a, v1_0, 0, 18));
                if (p7 != v0_0) {
                    String v1_3 = new vn.com.vng.zalopay.alipay.service.MpmServiceProvider$userInitiatePay$1$1(this.this$0);
                    this.label = 2;
                    if (((ji.i) p7).e(v1_3, this) == v0_0) {
                        return v0_0;
                    }
                } else {
                    return v0_0;
                }
            } else {
                if (v1_1 == 1) {
                    kotlin.ResultKt.throwOnFailure(p7);
                } else {
                    if (v1_1 != 2) {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    } else {
                        kotlin.ResultKt.throwOnFailure(p7);
                    }
                }
            }
        } catch (kotlin.Unit v7_9) {
            vn.com.vng.zalopay.alipay.service.MpmServiceProvider.access$showErrorDialog(this.this$0, v7_9);
        } catch (fu.j) {
            vn.com.vng.zalopay.alipay.service.MpmServiceProvider.access$showErrorDialog(this.this$0, 0);
        }
        return kotlin.Unit.INSTANCE;
    }
}
