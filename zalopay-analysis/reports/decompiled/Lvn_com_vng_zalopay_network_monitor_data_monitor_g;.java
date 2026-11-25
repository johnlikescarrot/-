package vn.com.vng.zalopay.network_monitor.data.monitor;
public final class g implements vn.com.vng.zalopay.network_monitor.data.monitor.i {
    public final int a;

    public g(int p1)
    {
        this.a = p1;
        return;
    }

    public final boolean equals(Object p4)
    {
        if (this != p4) {
            if ((p4 instanceof vn.com.vng.zalopay.network_monitor.data.monitor.g)) {
                if (this.a == ((vn.com.vng.zalopay.network_monitor.data.monitor.g) p4).a) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final int hashCode()
    {
        return Integer.hashCode(this.a);
    }

    public final String toString()
    {
        return androidx.camera.camera2.internal.G.d(this.a, wmoi.igob.ggcn(9524), wmoi.igob.ggcn(9525));
    }
}
