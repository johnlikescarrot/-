package vn.com.vng.zalopay.network_monitor.data.monitor;
public final class a {
    public final long a;
    public final double b;
    public final boolean c;
    public final boolean d;

    public a(long p1, double p3, boolean p5, boolean p6)
    {
        this.a = p1;
        this.b = p3;
        this.c = p5;
        this.d = p6;
        return;
    }

    public final boolean equals(Object p8)
    {
        if (this != p8) {
            if ((p8 instanceof vn.com.vng.zalopay.network_monitor.data.monitor.a)) {
                if (this.a == ((vn.com.vng.zalopay.network_monitor.data.monitor.a) p8).a) {
                    if (Double.compare(this.b, ((vn.com.vng.zalopay.network_monitor.data.monitor.a) p8).b) == 0) {
                        if (this.c == ((vn.com.vng.zalopay.network_monitor.data.monitor.a) p8).c) {
                            if (this.d == ((vn.com.vng.zalopay.network_monitor.data.monitor.a) p8).d) {
                                return 1;
                            } else {
                                return 0;
                            }
                        } else {
                            return 0;
                        }
                    } else {
                        return 0;
                    }
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
        int v1_3 = 31;
        return (Boolean.hashCode(this.d) + Kb.e.i(((Double.hashCode(this.b) + (Long.hashCode(this.a) * 31)) * v1_3), v1_3, this.c));
    }

    public final String toString()
    {
        String v0_2 = A1.a.n(this.a, "ApiMetricEntry(timestampMs=", ", latencyMs=");
        v0_2.append(this.b);
        v0_2.append(", isSuccess=");
        v0_2.append(this.c);
        v0_2.append(", isServerError=");
        v0_2.append(this.d);
        v0_2.append(")");
        return v0_2.toString();
    }
}
