package vn.com.vng.zalopay.network_monitor.data.monitor;
public final enum class c extends java.lang.Enum {
    public static final enum vn.com.vng.zalopay.network_monitor.data.monitor.c a;
    public static final enum vn.com.vng.zalopay.network_monitor.data.monitor.c b;
    public static final synthetic vn.com.vng.zalopay.network_monitor.data.monitor.c[] c;

    static c()
    {
        xg.b v0_2 = new vn.com.vng.zalopay.network_monitor.data.monitor.c("ScheduledSlow", 0);
        vn.com.vng.zalopay.network_monitor.data.monitor.c.a = v0_2;
        vn.com.vng.zalopay.network_monitor.data.monitor.c v1_2 = new vn.com.vng.zalopay.network_monitor.data.monitor.c("Running", 1);
        vn.com.vng.zalopay.network_monitor.data.monitor.c.b = v1_2;
        vn.com.vng.zalopay.network_monitor.data.monitor.c.c = new vn.com.vng.zalopay.network_monitor.data.monitor.c[] {v0_2, v1_2});
        return;
    }

    public static vn.com.vng.zalopay.network_monitor.data.monitor.c valueOf(String p1)
    {
        return ((vn.com.vng.zalopay.network_monitor.data.monitor.c) Enum.valueOf(vn.com.vng.zalopay.network_monitor.data.monitor.c, p1));
    }

    public static vn.com.vng.zalopay.network_monitor.data.monitor.c[] values()
    {
        return ((vn.com.vng.zalopay.network_monitor.data.monitor.c[]) vn.com.vng.zalopay.network_monitor.data.monitor.c.c.clone());
    }
}
