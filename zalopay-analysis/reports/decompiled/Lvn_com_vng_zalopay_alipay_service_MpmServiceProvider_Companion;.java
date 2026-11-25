package vn.com.vng.zalopay.alipay.service;
public final class MpmServiceProvider$Companion {

    private MpmServiceProvider$Companion()
    {
        return;
    }

    public synthetic MpmServiceProvider$Companion(kotlin.jvm.internal.DefaultConstructorMarker p1)
    {
        return;
    }

    public final void sendFailedCallback(String p2, String p3)
    {
        com.iap.android.mppclient.basic.callback.Callback v0 = vn.com.vng.zalopay.alipay.service.MpmServiceProvider.access$getCallback$cp();
        if (v0 != null) {
            v0.onFailure(p2, p3);
        }
        return;
    }

    public final void sendSuccessCallback(com.iap.android.mppclient.basic.model.DecodeServiceResult p2)
    {
        com.iap.android.mppclient.basic.callback.Callback v0 = vn.com.vng.zalopay.alipay.service.MpmServiceProvider.access$getCallback$cp();
        if (v0 != null) {
            v0.onSuccess(p2);
        }
        return;
    }
}
