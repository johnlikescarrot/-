package vn.com.vng.zalopay.network_monitor.data.monitor;
public final class b {
    public final int a;
    public final double b;
    public final int c;
    public final int d;

    public b(int p1, double p2, int p4, int p5)
    {
        this.a = p1;
        this.b = p2;
        this.c = p4;
        this.d = p5;
        return;
    }

    public final boolean equals(Object p8)
    {
        if (this != p8) {
            if ((p8 instanceof vn.com.vng.zalopay.network_monitor.data.monitor.b)) {
                if (this.a == ((vn.com.vng.zalopay.network_monitor.data.monitor.b) p8).a) {
                    if (Double.compare(this.b, ((vn.com.vng.zalopay.network_monitor.data.monitor.b) p8).b) == 0) {
                        if (this.c == ((vn.com.vng.zalopay.network_monitor.data.monitor.b) p8).c) {
                            if (this.d == ((vn.com.vng.zalopay.network_monitor.data.monitor.b) p8).d) {
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
        return (Integer.hashCode(this.d) + Kb.e.v(this.c, ((Double.hashCode(this.b) + (Integer.hashCode(this.a) * 31)) * v1_3), v1_3));
    }

    public final String toString()
    {
        String v0_1 = new StringBuilder("ApiWindowMetrics(failureCount=");
        v0_1.append(this.a);
        v0_1.append(", averageLatencyMs=");
        v0_1.append(this.b);
        v0_1.append(", serverErrorCount=");
        v0_1.append(this.c);
        v0_1.append(", recentSampleCount=");
        v0_1.append(this.d);
        v0_1.append(")");
        return v0_1.toString();
    }
}
