package vn.com.vng.zalopay.alipay.service;
public final class DeeplinkServiceProvider implements com.alipay.iap.android.wallet.foundation.deeplink.DeeplinkService {
    public static final int $stable;

    public DeeplinkServiceProvider()
    {
        return;
    }

    public void open(String p1, java.util.Map p2, com.alipay.iap.android.wallet.acl.base.APIContext p3, com.alipay.iap.android.wallet.acl.base.Callback p4)
    {
        com.alipay.iap.android.wallet.acl.base.BaseResult v1_1 = new com.alipay.iap.android.wallet.acl.base.BaseResult();
        v1_1.setExtendedInfo(p2);
        p4.result(v1_1);
        return;
    }

    public boolean open(android.net.Uri p3, com.alipay.iap.android.wallet.acl.base.APIContext p4)
    {
        if (p3) {
            kotlin.coroutines.CoroutineContext v4_0 = Em.c.a();
            if (v4_0 != null) {
                return ((Boolean) kotlinx.coroutines.BuildersKt.runBlocking(androidx.lifecycle.l0.i(v4_0.getLifecycle()).b, new vn.com.vng.zalopay.alipay.service.DeeplinkServiceProvider$open$1(p3, 0))).booleanValue();
            }
        }
        return 0;
    }
}
