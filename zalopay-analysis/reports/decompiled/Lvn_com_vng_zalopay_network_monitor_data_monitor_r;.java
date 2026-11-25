package vn.com.vng.zalopay.network_monitor.data.monitor;
public final class r {
    public final android.net.ConnectivityManager a;
    public final java.util.LinkedHashMap b;
    public final ji.u0 c;
    public final ji.d0 d;
    public final ji.u0 e;
    public final ji.d0 f;
    public final ji.u0 g;
    public final ji.d0 h;
    public final Vu.h i;

    public r(android.content.Context p3)
    {
        Vu.h v3_2;
        Vu.h v3_11 = ((android.net.ConnectivityManager) p3.getSystemService(android.net.ConnectivityManager));
        this.a = v3_11;
        this.b = new java.util.LinkedHashMap();
        int v0_1 = v3_11.getActiveNetwork();
        if (v0_1 != 0) {
            v3_2 = vn.com.vng.zalopay.network_monitor.data.monitor.r.e(v3_11.getNetworkCapabilities(v0_1));
        } else {
            v3_2 = vn.com.vng.zalopay.network_monitor.data.monitor.s.a;
        }
        Vu.h v3_6;
        Vu.h v3_3 = ji.j0.c(v3_2);
        this.c = v3_3;
        this.d = ji.j0.i(v3_3);
        if (((vn.com.vng.zalopay.network_monitor.data.monitor.s) v3_3.getValue()) == vn.com.vng.zalopay.network_monitor.data.monitor.s.a) {
            v3_6 = 0;
        } else {
            v3_6 = 1;
        }
        Vu.h v3_9 = ji.j0.c(Boolean.valueOf(v3_6));
        this.e = v3_9;
        this.f = ji.j0.i(v3_9);
        Vu.h v3_13 = ji.j0.c(Integer.valueOf(0));
        this.g = v3_13;
        this.h = ji.j0.i(v3_13);
        this.i = new Vu.h(6, this);
        return;
    }

    public static final void a(vn.com.vng.zalopay.network_monitor.data.monitor.r p2, android.net.Network p3, android.net.NetworkCapabilities p4)
    {
        java.util.LinkedHashMap v4_1 = vn.com.vng.zalopay.network_monitor.data.monitor.r.e(p4);
        java.util.LinkedHashMap v1_2;
        if (v4_1 == vn.com.vng.zalopay.network_monitor.data.monitor.s.a) {
            v1_2 = 0;
        } else {
            v1_2 = 1;
        }
        if (v1_2 == null) {
            p2.b.remove(p3);
        } else {
            p2.b.put(p3, v4_1);
        }
        p2.c(p2.b());
        return;
    }

    public static vn.com.vng.zalopay.network_monitor.data.monitor.s e(android.net.NetworkCapabilities p1)
    {
        if ((p1 != null) && (p1.hasCapability(12))) {
            if (!p1.hasCapability(16)) {
                return vn.com.vng.zalopay.network_monitor.data.monitor.s.b;
            } else {
                return vn.com.vng.zalopay.network_monitor.data.monitor.s.c;
            }
        } else {
            return vn.com.vng.zalopay.network_monitor.data.monitor.s.a;
        }
    }

    public final vn.com.vng.zalopay.network_monitor.data.monitor.s b()
    {
        vn.com.vng.zalopay.network_monitor.data.monitor.s v0_0 = this.b;
        vn.com.vng.zalopay.network_monitor.data.monitor.s v1_5 = ((Iterable) v0_0.values());
        if ((!(v1_5 instanceof java.util.Collection)) || (!((java.util.Collection) v1_5).isEmpty())) {
            vn.com.vng.zalopay.network_monitor.data.monitor.s v1_0 = v1_5.iterator();
            while (v1_0.hasNext()) {
                vn.com.vng.zalopay.network_monitor.data.monitor.s v3 = vn.com.vng.zalopay.network_monitor.data.monitor.s.c;
                if (((vn.com.vng.zalopay.network_monitor.data.monitor.s) v1_0.next()) == v3) {
                    return v3;
                }
            }
        }
        vn.com.vng.zalopay.network_monitor.data.monitor.s v0_2 = ((Iterable) v0_0.values());
        if ((!(v0_2 instanceof java.util.Collection)) || (!((java.util.Collection) v0_2).isEmpty())) {
            vn.com.vng.zalopay.network_monitor.data.monitor.s v0_3 = v0_2.iterator();
            while (v0_3.hasNext()) {
                vn.com.vng.zalopay.network_monitor.data.monitor.s v2_4 = vn.com.vng.zalopay.network_monitor.data.monitor.s.b;
                if (((vn.com.vng.zalopay.network_monitor.data.monitor.s) v0_3.next()) == v2_4) {
                    return v2_4;
                }
            }
        }
        return vn.com.vng.zalopay.network_monitor.data.monitor.s.a;
    }

    public final void c(vn.com.vng.zalopay.network_monitor.data.monitor.s p5)
    {
        do {
            Object v0_0 = this.c;
        } while(!v0_0.c(v0_0.getValue(), p5));
    }

    public final void d()
    {
        android.net.ConnectivityManager v1_2;
        android.net.NetworkRequest v0_1 = this.a.getActiveNetwork();
        if (v0_1 == null) {
            v1_2 = 0;
        } else {
            v1_2 = this.a.getNetworkCapabilities(v0_1);
        }
        android.net.ConnectivityManager v1_4 = vn.com.vng.zalopay.network_monitor.data.monitor.r.e(v1_2);
        this.b.clear();
        if (v0_1 != null) {
            java.util.LinkedHashMap v3_2;
            if (v1_4 == vn.com.vng.zalopay.network_monitor.data.monitor.s.a) {
                v3_2 = 0;
            } else {
                v3_2 = 1;
            }
            if (v3_2 != null) {
                this.b.put(v0_1, v1_4);
            }
        }
        this.c(v1_4);
        this.a.registerNetworkCallback(new android.net.NetworkRequest$Builder().addCapability(12).build(), this.i);
        return;
    }
}
