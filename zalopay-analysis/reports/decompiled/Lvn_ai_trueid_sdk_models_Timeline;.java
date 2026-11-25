package vn.ai.trueid.sdk.models;
public final class Timeline {
    private final int code;
    private final boolean status;
    private final long timestamp;

    public Timeline(long p1, boolean p3, int p4)
    {
        this.timestamp = p1;
        this.status = p3;
        this.code = p4;
        return;
    }

    public synthetic Timeline(long p1, boolean p3, int p4, int p5, kotlin.jvm.internal.DefaultConstructorMarker p6)
    {
        if ((p5 & 2) != 0) {
            p3 = 0;
        }
        this(p1, p3, p4);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.Timeline copy$default(vn.ai.trueid.sdk.models.Timeline p0, long p1, boolean p3, int p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.timestamp;
        }
        if ((p5 & 2) != 0) {
            p3 = p0.status;
        }
        if ((p5 & 4) != 0) {
            p4 = p0.code;
        }
        return p0.copy(p1, p3, p4);
    }

    public final long component1()
    {
        return this.timestamp;
    }

    public final boolean component2()
    {
        return this.status;
    }

    public final int component3()
    {
        return this.code;
    }

    public final vn.ai.trueid.sdk.models.Timeline copy(long p2, boolean p4, int p5)
    {
        return new vn.ai.trueid.sdk.models.Timeline(p2, p4, p5);
    }

    public boolean equals(Object p8)
    {
        if (this != p8) {
            if ((p8 instanceof vn.ai.trueid.sdk.models.Timeline)) {
                if (this.timestamp == ((vn.ai.trueid.sdk.models.Timeline) p8).timestamp) {
                    if (this.status == ((vn.ai.trueid.sdk.models.Timeline) p8).status) {
                        if (this.code == ((vn.ai.trueid.sdk.models.Timeline) p8).code) {
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
            return 1;
        }
    }

    public final int getCode()
    {
        return this.code;
    }

    public final boolean getStatus()
    {
        return this.status;
    }

    public final long getTimestamp()
    {
        return this.timestamp;
    }

    public int hashCode()
    {
        int vtmp1 = Long.hashCode(this.timestamp);
        int v1_2 = this.status;
        if (v1_2 != 0) {
            v1_2 = 1;
        }
        return (Integer.hashCode(this.code) + (((vtmp1 * 31) + v1_2) * 31));
    }

    public final java.util.Map toMap()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("timestamp", Long.valueOf(this.timestamp)), kotlin.TuplesKt.to("status", Boolean.valueOf(this.status)), kotlin.TuplesKt.to("code", Integer.valueOf(this.code))}));
    }

    public String toString()
    {
        String v0_0 = this.timestamp;
        boolean v2 = this.status;
        int v3 = this.code;
        StringBuilder v4_1 = new StringBuilder("Timeline(timestamp=");
        v4_1.append(v0_0);
        v4_1.append(", status=");
        v4_1.append(v2);
        v4_1.append(", code=");
        v4_1.append(v3);
        v4_1.append(")");
        return v4_1.toString();
    }
}
