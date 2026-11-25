package vn.ai.trueid.sdk.models.sotp;
public final class DisplayField implements java.io.Serializable {
    private final String label;
    private final vn.ai.trueid.sdk.models.sotp.FieldStyle style;
    private final String value;

    public DisplayField(String p1, String p2, vn.ai.trueid.sdk.models.sotp.FieldStyle p3)
    {
        this.label = p1;
        this.value = p2;
        this.style = p3;
        return;
    }

    public synthetic DisplayField(String p1, String p2, vn.ai.trueid.sdk.models.sotp.FieldStyle p3, int p4, kotlin.jvm.internal.DefaultConstructorMarker p5)
    {
        if ((p4 & 4) != 0) {
            p3 = vn.ai.trueid.sdk.models.sotp.FieldStyle.NORMAL;
        }
        this(p1, p2, p3);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.sotp.DisplayField copy$default(vn.ai.trueid.sdk.models.sotp.DisplayField p0, String p1, String p2, vn.ai.trueid.sdk.models.sotp.FieldStyle p3, int p4, Object p5)
    {
        if ((p4 & 1) != 0) {
            p1 = p0.label;
        }
        if ((p4 & 2) != 0) {
            p2 = p0.value;
        }
        if ((p4 & 4) != 0) {
            p3 = p0.style;
        }
        return p0.copy(p1, p2, p3);
    }

    public final String component1()
    {
        return this.label;
    }

    public final String component2()
    {
        return this.value;
    }

    public final vn.ai.trueid.sdk.models.sotp.FieldStyle component3()
    {
        return this.style;
    }

    public final vn.ai.trueid.sdk.models.sotp.DisplayField copy(String p2, String p3, vn.ai.trueid.sdk.models.sotp.FieldStyle p4)
    {
        return new vn.ai.trueid.sdk.models.sotp.DisplayField(p2, p3, p4);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.sotp.DisplayField)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.label, ((vn.ai.trueid.sdk.models.sotp.DisplayField) p5).label)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.value, ((vn.ai.trueid.sdk.models.sotp.DisplayField) p5).value)) {
                        if (this.style == ((vn.ai.trueid.sdk.models.sotp.DisplayField) p5).style) {
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

    public final String getLabel()
    {
        return this.label;
    }

    public final vn.ai.trueid.sdk.models.sotp.FieldStyle getStyle()
    {
        return this.style;
    }

    public final String getValue()
    {
        return this.value;
    }

    public int hashCode()
    {
        return (this.style.hashCode() + e5.i.c((this.label.hashCode() * 31), 31, this.value));
    }

    public String toString()
    {
        vn.ai.trueid.sdk.models.sotp.FieldStyle v2 = this.style;
        String v0_2 = androidx.camera.camera2.internal.G.i("DisplayField(label=", this.label, ", value=", this.value, ", style=");
        v0_2.append(v2);
        v0_2.append(")");
        return v0_2.toString();
    }
}
