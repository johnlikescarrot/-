package vn.com.vng.zalopay.alipay.service;
public final class AutoDebitServiceProvider implements com.iap.android.mppclient.autodebit.AutoDebitService {
    public static final int $stable;
    public static final vn.com.vng.zalopay.alipay.service.AutoDebitServiceProvider$Companion Companion;
    private static com.iap.android.mppclient.basic.callback.Callback callback;

    static AutoDebitServiceProvider()
    {
        vn.com.vng.zalopay.alipay.service.AutoDebitServiceProvider.Companion = new vn.com.vng.zalopay.alipay.service.AutoDebitServiceProvider$Companion(0);
        return;
    }

    public AutoDebitServiceProvider()
    {
        return;
    }

    public static final synthetic com.iap.android.mppclient.basic.callback.Callback access$getCallback$cp()
    {
        return vn.com.vng.zalopay.alipay.service.AutoDebitServiceProvider.callback;
    }

    public void showAuthPage(com.iap.android.mppclient.autodebit.model.SignContractOAuthServiceParams p1, com.iap.android.mppclient.basic.callback.Callback p2)
    {
        vn.com.vng.zalopay.alipay.service.AutoDebitServiceProvider.callback = p2;
        return;
    }
}
