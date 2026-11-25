package vn.com.vng.zalopay.network_monitor.data.monitor;
public final class f implements vn.com.vng.zalopay.network_monitor.data.monitor.i {
    public static final vn.com.vng.zalopay.network_monitor.data.monitor.f a;

    static f()
    {
        vn.com.vng.zalopay.network_monitor.data.monitor.f.a = new vn.com.vng.zalopay.network_monitor.data.monitor.f();
        return;
    }

    public final boolean equals(Object p2)
    {
        if (this != p2) {
            if ((p2 instanceof vn.com.vng.zalopay.network_monitor.data.monitor.f)) {
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
        return 2065519413;
    }

    public final String toString()
    {
        return "NetworkFailure";
    }
}
