package vn.com.vng.zalopay.alipay.service;
public final class CommonAuthServiceProvider_Factory implements dagger.internal.g {
    private final dagger.internal.o repositoryProvider;

    private CommonAuthServiceProvider_Factory(dagger.internal.o p1)
    {
        this.repositoryProvider = p1;
        return;
    }

    public static vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider_Factory create(dagger.internal.o p1)
    {
        return new vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider_Factory(p1);
    }

    public static vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider newInstance(Om.a p1)
    {
        return new vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider(p1);
    }

    public bridge synthetic Object get()
    {
        return this.get();
    }

    public vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider get()
    {
        return vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider_Factory.newInstance(((Om.a) this.repositoryProvider.get()));
    }
}
