package vn.com.vng.zalopay.alipay.service;
final class MpmServiceProvider$userInitiatePay$1$1 implements ji.j {
    final synthetic vn.com.vng.zalopay.alipay.service.MpmServiceProvider this$0;

    public MpmServiceProvider$userInitiatePay$1$1(vn.com.vng.zalopay.alipay.service.MpmServiceProvider p1)
    {
        this.this$0 = p1;
        return;
    }

    public final Object emit(Hm.b p1, kotlin.coroutines.Continuation p2)
    {
        java.util.Objects.toString(p1);
        vn.com.vng.zalopay.alipay.service.MpmServiceProvider.access$handleInitiatePay(this.this$0, p1);
        return kotlin.Unit.INSTANCE;
    }

    public bridge synthetic Object emit(Object p1, kotlin.coroutines.Continuation p2)
    {
        return this.emit(((Hm.b) p1), p2);
    }
}
