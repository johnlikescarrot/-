package vn.ai.trueid.sdk.models;
public final class ResultValue {
    private final String code;
    private final String value;

    public ResultValue()
    {
        this(0, 0, 3, 0);
        return;
    }

    public ResultValue(String p1, String p2)
    {
        this.code = p1;
        this.value = p2;
        return;
    }

    public synthetic ResultValue(String p2, String p3, int p4, kotlin.jvm.internal.DefaultConstructorMarker p5)
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

    public static synthetic vn.ai.trueid.sdk.models.ResultValue copy$default(vn.ai.trueid.sdk.models.ResultValue p0, String p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.code;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.value;
        }
        return p0.copy(p1, p2);
    }

    public final String component1()
    {
        return this.code;
    }

    public final String component2()
    {
        return this.value;
    }

    public final vn.ai.trueid.sdk.models.ResultValue copy(String p2, String p3)
    {
        return new vn.ai.trueid.sdk.models.ResultValue(p2, p3);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.ResultValue)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.code, ((vn.ai.trueid.sdk.models.ResultValue) p5).code)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.value, ((vn.ai.trueid.sdk.models.ResultValue) p5).value)) {
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

    public final String getCode()
    {
        return this.code;
    }

    public final String getValue()
    {
        return this.value;
    }

    public int hashCode()
    {
        return (this.value.hashCode() + (this.code.hashCode() * 31));
    }

    public final java.util.Map toMap()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("code", this.code), kotlin.TuplesKt.to("value", this.value)}));
    }

    public String toString()
    {
        return androidx.camera.camera2.internal.G.f("ResultValue(code=", this.code, ", value=", this.value, ")");
    }
}
