package vn.com.vng.zalopay.network_monitor.data.monitor;
public final enum class d extends java.lang.Enum {
    public static final enum vn.com.vng.zalopay.network_monitor.data.monitor.d a;
    public static final enum vn.com.vng.zalopay.network_monitor.data.monitor.d b;
    public static final synthetic vn.com.vng.zalopay.network_monitor.data.monitor.d[] c;

    static d()
    {
        xg.b v0_2 = new vn.com.vng.zalopay.network_monitor.data.monitor.d("Slow", 0);
        vn.com.vng.zalopay.network_monitor.data.monitor.d.a = v0_2;
        vn.com.vng.zalopay.network_monitor.data.monitor.d v1_2 = new vn.com.vng.zalopay.network_monitor.data.monitor.d("Failure", 1);
        vn.com.vng.zalopay.network_monitor.data.monitor.d.b = v1_2;
        vn.com.vng.zalopay.network_monitor.data.monitor.d.c = new vn.com.vng.zalopay.network_monitor.data.monitor.d[] {v0_2, v1_2});
        return;
    }

    public static vn.com.vng.zalopay.network_monitor.data.monitor.d valueOf(String p1)
    {
        return ((vn.com.vng.zalopay.network_monitor.data.monitor.d) Enum.valueOf(vn.com.vng.zalopay.network_monitor.data.monitor.d, p1));
    }

    public static vn.com.vng.zalopay.network_monitor.data.monitor.d[] values()
    {
        return ((vn.com.vng.zalopay.network_monitor.data.monitor.d[]) vn.com.vng.zalopay.network_monitor.data.monitor.d.c.clone());
    }
}
