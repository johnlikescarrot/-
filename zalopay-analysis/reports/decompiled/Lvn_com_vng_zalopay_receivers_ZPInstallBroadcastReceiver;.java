package vn.com.vng.zalopay.receivers;
public final class ZPInstallBroadcastReceiver extends android.content.BroadcastReceiver {

    public ZPInstallBroadcastReceiver()
    {
        return;
    }

    public final void onReceive(android.content.Context p2, android.content.Intent p3)
    {
        new com.appsflyer.SingleInstallBroadcastReceiver().onReceive(p2, p3);
        return;
    }
}
