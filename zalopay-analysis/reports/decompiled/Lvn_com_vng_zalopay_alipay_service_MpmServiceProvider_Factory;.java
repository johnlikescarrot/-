package vn.com.vng.zalopay.alipay.service;
public final class MpmServiceProvider_Factory implements dagger.internal.g {
    private final dagger.internal.o contextProvider;
    private final dagger.internal.o decodeAlipayMppUseCaseProvider;

    private MpmServiceProvider_Factory(dagger.internal.o p1, dagger.internal.o p2)
    {
        this.contextProvider = p1;
        this.decodeAlipayMppUseCaseProvider = p2;
        return;
    }

    public static vn.com.vng.zalopay.alipay.service.MpmServiceProvider_Factory create(dagger.internal.o p1, dagger.internal.o p2)
    {
        return new vn.com.vng.zalopay.alipay.service.MpmServiceProvider_Factory(p1, p2);
    }

    public static vn.com.vng.zalopay.alipay.service.MpmServiceProvider newInstance(android.content.Context p1, Pm.a p2)
    {
        return new vn.com.vng.zalopay.alipay.service.MpmServiceProvider(p1, p2);
    }

    public bridge synthetic Object get()
    {
        return this.get();
    }

    public vn.com.vng.zalopay.alipay.service.MpmServiceProvider get()
    {
        return vn.com.vng.zalopay.alipay.service.MpmServiceProvider_Factory.newInstance(((android.content.Context) this.contextProvider.get()), ((Pm.a) this.decodeAlipayMppUseCaseProvider.get()));
    }
}
