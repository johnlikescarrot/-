package vn.com.vng.zalopay.network_monitor.data.monitor;
public final enum class s extends java.lang.Enum {
    public static final enum vn.com.vng.zalopay.network_monitor.data.monitor.s a;
    public static final enum vn.com.vng.zalopay.network_monitor.data.monitor.s b;
    public static final enum vn.com.vng.zalopay.network_monitor.data.monitor.s c;
    public static final synthetic vn.com.vng.zalopay.network_monitor.data.monitor.s[] d;

    static s()
    {
        xg.b v0_2 = new vn.com.vng.zalopay.network_monitor.data.monitor.s("Unavailable", 0);
        vn.com.vng.zalopay.network_monitor.data.monitor.s.a = v0_2;
        vn.com.vng.zalopay.network_monitor.data.monitor.s v1_2 = new vn.com.vng.zalopay.network_monitor.data.monitor.s("Unvalidated", 1);
        vn.com.vng.zalopay.network_monitor.data.monitor.s.b = v1_2;
        vn.com.vng.zalopay.network_monitor.data.monitor.s v2_1 = new vn.com.vng.zalopay.network_monitor.data.monitor.s("Validated", 2);
        vn.com.vng.zalopay.network_monitor.data.monitor.s.c = v2_1;
        vn.com.vng.zalopay.network_monitor.data.monitor.s.d = new vn.com.vng.zalopay.network_monitor.data.monitor.s[] {v0_2, v1_2, v2_1});
        return;
    }

    public static vn.com.vng.zalopay.network_monitor.data.monitor.s valueOf(String p1)
    {
        return ((vn.com.vng.zalopay.network_monitor.data.monitor.s) Enum.valueOf(vn.com.vng.zalopay.network_monitor.data.monitor.s, p1));
    }

    public static vn.com.vng.zalopay.network_monitor.data.monitor.s[] values()
    {
        return ((vn.com.vng.zalopay.network_monitor.data.monitor.s[]) vn.com.vng.zalopay.network_monitor.data.monitor.s.d.clone());
    }
}
