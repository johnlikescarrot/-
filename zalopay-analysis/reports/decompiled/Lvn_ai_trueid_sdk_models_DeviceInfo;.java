package vn.ai.trueid.sdk.models;
public final class DeviceInfo {
    private String id;
    private String model;

    public DeviceInfo()
    {
        this(0, 0, 3, 0);
        return;
    }

    public DeviceInfo(String p1, String p2)
    {
        this.id = p1;
        this.model = p2;
        return;
    }

    public synthetic DeviceInfo(String p2, String p3, int p4, kotlin.jvm.internal.DefaultConstructorMarker p5)
    {
        if ((p4 & 1) != 0) {
            p2 = "";
        }
        if ((p4 & 2) != 0) {
            p3 = "";
        }
        this(p2, p3);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.DeviceInfo copy$default(vn.ai.trueid.sdk.models.DeviceInfo p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.id;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.model;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.id;
    }

    public final String component2()
    {
        return this.model;
    }

    public final vn.ai.trueid.sdk.models.DeviceInfo copy(String p2, String p3)
    {
        return new vn.ai.trueid.sdk.models.DeviceInfo(p2, p3);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.DeviceInfo)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.id, ((vn.ai.trueid.sdk.models.DeviceInfo) p5).id)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.model, ((vn.ai.trueid.sdk.models.DeviceInfo) p5).model)) {
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
            return 1;
        }
    }

    public final String getId()
    {
        return this.id;
    }

    public final String getModel()
    {
        return this.model;
    }

    public int hashCode()
    {
        return (this.model.hashCode() + (this.id.hashCode() * 31));
    }

    public final void setId(String p1)
    {
        this.id = p1;
        return;
    }

    public final void setModel(String p1)
    {
        this.model = p1;
        return;
    }

    public final java.util.Map toMap()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("device_model", this.model), kotlin.TuplesKt.to("id", this.id)}));
    }

    public String toString()
    {
        return androidx.camera.camera2.internal.G.f("DeviceInfo(id=", this.id, ", model=", this.model, ")");
    }
}
