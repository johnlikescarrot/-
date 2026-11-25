package vn.com.vng.zalopay.network_monitor.data.monitor;
public final class e implements vn.com.vng.zalopay.network_monitor.data.monitor.i {
    public static final vn.com.vng.zalopay.network_monitor.data.monitor.e a;

    static e()
    {
        vn.com.vng.zalopay.network_monitor.data.monitor.e.a = new vn.com.vng.zalopay.network_monitor.data.monitor.e();
        return;
    }

    public final boolean equals(Object p2)
    {
        if (this != p2) {
            if ((p2 instanceof vn.com.vng.zalopay.network_monitor.data.monitor.e)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final int hashCode()
    {
        return 1097991172;
    }

    public final String toString()
    {
        return "Healthy";
    }
}
