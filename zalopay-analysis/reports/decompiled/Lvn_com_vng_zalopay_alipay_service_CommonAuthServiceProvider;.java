package vn.com.vng.zalopay.alipay.service;
public final class CommonAuthServiceProvider implements com.iap.android.mppclient.basic.service.CommonOAuthService {
    public static final int $stable;
    private final kotlinx.coroutines.CoroutineScope coroutineScope;
    private final Om.a repository;

    static CommonAuthServiceProvider()
    {
        wmoi.igob.jtgo(vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider, 20732, 20732);
        return;
    }

    public CommonAuthServiceProvider(Om.a p2)
    {
        this.repository = p2;
        this.coroutineScope = gi.L.a(kotlin.coroutines.f.a(gi.N.d(), kotlinx.coroutines.Dispatchers.getIO()));
        return;
    }

    public static final synthetic Om.a access$getRepository$p(vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider p0)
    {
        return p0.repository;
    }

    public void getAuthCode(com.iap.android.mppclient.basic.model.CommonOAuthServiceParams p7, com.iap.android.mppclient.basic.callback.Callback p8)
    {
        kotlinx.coroutines.BuildersKt.launch$default(this.coroutineScope, 0, 0, new vn.com.vng.zalopay.alipay.service.CommonAuthServiceProvider$getAuthCode$1(this, p7, p8, 0), 3, 0);
        return;
    }

    public final void sendFailedCallback(com.iap.android.mppclient.basic.callback.Callback p1, String p2, String p3)
    {
        p1.onFailure(p2, p3);
        return;
    }

    public final void sendSuccessCallback(com.iap.android.mppclient.basic.callback.Callback p1, com.iap.android.mppclient.basic.model.CommonOAuthServiceResult p2)
    {
        p1.onSuccess(p2);
        return;
    }
}
