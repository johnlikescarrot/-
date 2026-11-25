package vn.ai.trueid.sdk.models;
public final class CheckingRecord {
    public final String message;
    public final String name;
    public final boolean status;
    public final vn.ai.trueid.sdk.models.DetectionType type;

    public CheckingRecord(String p1, String p2, vn.ai.trueid.sdk.models.DetectionType p3, boolean p4)
    {
        this.name = p1;
        this.message = p2;
        this.type = p3;
        this.status = p4;
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.CheckingRecord copy$default(vn.ai.trueid.sdk.models.CheckingRecord p0, String p1, String p2, vn.ai.trueid.sdk.models.DetectionType p3, boolean p4, int p5, Object p6)
    {
        if ((p5 & 1) != 0) {
            p1 = p0.name;
        }
        if ((p5 & 2) != 0) {
            p2 = p0.message;
        }
        if ((p5 & 4) != 0) {
            p3 = p0.type;
        }
        if ((p5 & 8) != 0) {
            p4 = p0.status;
        }
        return p0.copy(p1, p2, p3, p4);
    }

    public final String component1()
    {
        return this.name;
    }

    public final String component2()
    {
        return this.message;
    }

    public final vn.ai.trueid.sdk.models.DetectionType component3()
    {
        return this.type;
    }

    public final boolean component4()
    {
        return this.status;
    }

    public final vn.ai.trueid.sdk.models.CheckingRecord copy(String p2, String p3, vn.ai.trueid.sdk.models.DetectionType p4, boolean p5)
    {
        return new vn.ai.trueid.sdk.models.CheckingRecord(p2, p3, p4, p5);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.CheckingRecord)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.name, ((vn.ai.trueid.sdk.models.CheckingRecord) p5).name)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.message, ((vn.ai.trueid.sdk.models.CheckingRecord) p5).message)) {
                        if (this.type == ((vn.ai.trueid.sdk.models.CheckingRecord) p5).type) {
                            if (this.status == ((vn.ai.trueid.sdk.models.CheckingRecord) p5).status) {
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

    public int hashCode()
    {
        int v2_1 = (this.type.hashCode() + e5.i.c((this.name.hashCode() * 31), 31, this.message));
        int v0_1 = this.status;
        if (v0_1 != 0) {
            v0_1 = 1;
        }
        return ((v2_1 * 31) + v0_1);
    }

    public final java.util.Map toMap()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("name", this.name), kotlin.TuplesKt.to("message", this.message), kotlin.TuplesKt.to("type", Integer.valueOf(this.type.ordinal())), kotlin.TuplesKt.to("status", Boolean.valueOf(this.status))}));
    }

    public String toString()
    {
        vn.ai.trueid.sdk.models.DetectionType v2 = this.type;
        boolean v3 = this.status;
        String v0_2 = androidx.camera.camera2.internal.G.i("CheckingRecord(name=", this.name, ", message=", this.message, ", type=");
        v0_2.append(v2);
        v0_2.append(", status=");
        v0_2.append(v3);
        v0_2.append(")");
        return v0_2.toString();
    }
}
