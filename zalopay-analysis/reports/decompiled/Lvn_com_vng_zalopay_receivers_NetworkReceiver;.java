package vn.com.vng.zalopay.receivers;
public final class NetworkReceiver extends android.content.BroadcastReceiver {

    public NetworkReceiver()
    {
        return;
    }

    public final void onReceive(android.content.Context p2, android.content.Intent p3)
    {
        int v2_4;
        int v2_3 = p2.getSystemService("connectivity");
        YG.a v0_0 = 0;
        if (!(v2_3 instanceof android.net.ConnectivityManager)) {
            v2_4 = 0;
        } else {
            v2_4 = ((android.net.ConnectivityManager) v2_3);
        }
        int v2_2;
        if (v2_4 != 0) {
            int v2_1 = v2_4.getActiveNetworkInfo();
            if (v2_1 == 0) {
                v2_2 = 0;
            } else {
                v2_2 = v2_1.isConnected();
            }
        } else {
            v2_2 = 1;
        }
        org.greenrobot.eventbus.e v3_1 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v3_1 == null) {
            v3_1 = 0;
        }
        org.greenrobot.eventbus.e v3_2 = v3_1.a;
        if (v3_2 != null) {
            v0_0 = v3_2;
        }
        v0_0.i().f(new YG.a(v2_2));
        return;
    }
}
