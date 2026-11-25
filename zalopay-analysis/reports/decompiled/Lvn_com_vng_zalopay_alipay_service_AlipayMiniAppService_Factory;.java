package vn.com.vng.zalopay.alipay.service;
public final class AlipayMiniAppService_Factory implements dagger.internal.g {
    private final dagger.internal.o getOAuthCodeMiniAppProgramUseCaseProvider;
    private final dagger.internal.o getPublicProfileMiniProgramUseCaseProvider;
    private final dagger.internal.o iDataPersistentProvider;

    private AlipayMiniAppService_Factory(dagger.internal.o p1, dagger.internal.o p2, dagger.internal.o p3)
    {
        this.iDataPersistentProvider = p1;
        this.getOAuthCodeMiniAppProgramUseCaseProvider = p2;
        this.getPublicProfileMiniProgramUseCaseProvider = p3;
        return;
    }

    public static vn.com.vng.zalopay.alipay.service.AlipayMiniAppService_Factory create(dagger.internal.o p1, dagger.internal.o p2, dagger.internal.o p3)
    {
        return new vn.com.vng.zalopay.alipay.service.AlipayMiniAppService_Factory(p1, p2, p3);
    }

    public static vn.com.vng.zalopay.alipay.service.AlipayMiniAppService newInstance(St.a p1, Pm.b p2, Pm.c p3)
    {
        return new vn.com.vng.zalopay.alipay.service.AlipayMiniAppService(p1, p2, p3);
    }

    public bridge synthetic Object get()
    {
        return this.get();
    }

    public vn.com.vng.zalopay.alipay.service.AlipayMiniAppService get()
    {
        return vn.com.vng.zalopay.alipay.service.AlipayMiniAppService_Factory.newInstance(((St.a) this.iDataPersistentProvider.get()), ((Pm.b) this.getOAuthCodeMiniAppProgramUseCaseProvider.get()), ((Pm.c) this.getPublicProfileMiniProgramUseCaseProvider.get()));
    }
}
