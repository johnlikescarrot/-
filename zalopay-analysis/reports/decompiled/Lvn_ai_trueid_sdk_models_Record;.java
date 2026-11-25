package vn.ai.trueid.sdk.models;
public final class Record {
    private int count;
    private java.util.List histories;
    private boolean isClose;
    private boolean status;
    private String type;

    public Record()
    {
        this(0, 0, 0, 0, 0, 31, 0);
        return;
    }

    public Record(String p1, int p2, boolean p3, boolean p4, java.util.List p5)
    {
        this.type = p1;
        this.count = p2;
        this.status = p3;
        this.isClose = p4;
        this.histories = p5;
        return;
    }

    public synthetic Record(String p2, int p3, boolean p4, boolean p5, java.util.List p6, int p7, kotlin.jvm.internal.DefaultConstructorMarker p8)
    {
        if ((p7 & 1) != 0) {
            p2 = "";
        }
        if ((p7 & 2) != 0) {
            p3 = 0;
        }
        if ((p7 & 4) != 0) {
            p4 = 0;
        }
        if ((p7 & 8) != 0) {
            p5 = 0;
        }
        if ((p7 & 16) != 0) {
            p6 = new java.util.ArrayList();
        }
        this(p2, p3, p4, p5, p6);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.Record copy$default(vn.ai.trueid.sdk.models.Record p0, String p1, int p2, boolean p3, boolean p4, java.util.List p5, int p6, Object p7)
    {
        if ((p6 & 1) != 0) {
            p1 = p0.type;
        }
        if ((p6 & 2) != 0) {
            p2 = p0.count;
        }
        if ((p6 & 4) != 0) {
            p3 = p0.status;
        }
        if ((p6 & 8) != 0) {
            p4 = p0.isClose;
        }
        if ((p6 & 16) != 0) {
            p5 = p0.histories;
        }
        return p0.copy(p1, p2, p3, p4, p5);
    }

    public final String component1()
    {
        return this.type;
    }

    public final int component2()
    {
        return this.count;
    }

    public final boolean component3()
    {
        return this.status;
    }

    public final boolean component4()
    {
        return this.isClose;
    }

    public final java.util.List component5()
    {
        return this.histories;
    }

    public final vn.ai.trueid.sdk.models.Record copy(String p7, int p8, boolean p9, boolean p10, java.util.List p11)
    {
        return new vn.ai.trueid.sdk.models.Record(p7, p8, p9, p10, p11);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.Record)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.type, ((vn.ai.trueid.sdk.models.Record) p5).type)) {
                    if (this.count == ((vn.ai.trueid.sdk.models.Record) p5).count) {
                        if (this.status == ((vn.ai.trueid.sdk.models.Record) p5).status) {
                            if (this.isClose == ((vn.ai.trueid.sdk.models.Record) p5).isClose) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.histories, ((vn.ai.trueid.sdk.models.Record) p5).histories)) {
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
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final int getCount()
    {
        return this.count;
    }

    public final java.util.List getHistories()
    {
        return this.histories;
    }

    public final boolean getStatus()
    {
        return this.status;
    }

    public final String getType()
    {
        return this.type;
    }

    public int hashCode()
    {
        int vtmp2 = Kb.e.v(this.count, (this.type.hashCode() * 31), 31);
        boolean v2_0 = this.status;
        boolean v3 = 1;
        if (v2_0) {
            v2_0 = 1;
        }
        boolean v2_1 = this.isClose;
        if (!v2_1) {
            v3 = v2_1;
        }
        return (this.histories.hashCode() + ((((vtmp2 + v2_0) * 31) + v3) * 31));
    }

    public final boolean isClose()
    {
        return this.isClose;
    }

    public final void setClose(boolean p1)
    {
        this.isClose = p1;
        return;
    }

    public final void setCount(int p1)
    {
        this.count = p1;
        return;
    }

    public final void setHistories(java.util.List p1)
    {
        this.histories = p1;
        return;
    }

    public final void setStatus(boolean p1)
    {
        this.status = p1;
        return;
    }

    public final void setType(String p1)
    {
        this.type = p1;
        return;
    }

    public final java.util.Map toMap()
    {
        java.util.Map v0_1 = kotlin.TuplesKt.to("type", this.type);
        kotlin.Pair v1_3 = kotlin.TuplesKt.to("status", Boolean.valueOf(this.status));
        kotlin.Pair v2_2 = kotlin.TuplesKt.to("isClose", Boolean.valueOf(this.isClose));
        kotlin.Pair v3_3 = kotlin.TuplesKt.to("count", Integer.valueOf(this.count));
        kotlin.Pair v4_2 = ((Iterable) this.histories);
        java.util.ArrayList v5_1 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v4_2, 10));
        kotlin.Pair v4_3 = v4_2.iterator();
        while (v4_3.hasNext()) {
            v5_1.add(((vn.ai.trueid.sdk.models.Timeline) v4_3.next()).toMap());
        }
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {v0_1, v1_3, v2_2, v3_3, kotlin.TuplesKt.to("histories", v5_1)}));
    }

    public String toString()
    {
        boolean v2 = this.status;
        boolean v3 = this.isClose;
        java.util.List v4 = this.histories;
        String v0_2 = androidx.compose.foundation.layout.G0.p(this.count, "Record(type=", this.type, ", count=", ", status=");
        q9.H.q(v0_2, v2, ", isClose=", v3, ", histories=");
        return androidx.camera.camera2.internal.G.h(v0_2, v4, ")");
    }
}
