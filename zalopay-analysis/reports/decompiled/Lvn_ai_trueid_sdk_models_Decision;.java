package vn.ai.trueid.sdk.models;
public final class Decision {
    private int code;
    private String decision;

    public Decision(int p1, String p2)
    {
        this.code = p1;
        this.decision = p2;
        return;
    }

    public synthetic Decision(int p1, String p2, int p3, kotlin.jvm.internal.DefaultConstructorMarker p4)
    {
        if ((p3 & 2) != 0) {
            p2 = "";
        }
        this(p1, p2);
        return;
    }

    public static synthetic vn.ai.trueid.sdk.models.Decision copy$default(vn.ai.trueid.sdk.models.Decision p0, int p1, String p2, int p3, Object p4)
    {
        if ((p3 & 1) != 0) {
            p1 = p0.code;
        }
        if ((p3 & 2) != 0) {
            p2 = p0.decision;
        }
        return p0.copy(p1, p2);
    }

    public final int component1()
    {
        return this.code;
    }

    public final String component2()
    {
        return this.decision;
    }

    public final vn.ai.trueid.sdk.models.Decision copy(int p2, String p3)
    {
        return new vn.ai.trueid.sdk.models.Decision(p2, p3);
    }

    public boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.models.Decision)) {
                if (this.code == ((vn.ai.trueid.sdk.models.Decision) p5).code) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.decision, ((vn.ai.trueid.sdk.models.Decision) p5).decision)) {
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

    public final int getCode()
    {
        return this.code;
    }

    public final String getDecision()
    {
        return this.decision;
    }

    public int hashCode()
    {
        return (this.decision.hashCode() + (Integer.hashCode(this.code) * 31));
    }

    public final void setCode(int p1)
    {
        this.code = p1;
        return;
    }

    public final void setDecision(String p1)
    {
        this.decision = p1;
        return;
    }

    public final java.util.Map toMap()
    {
        return kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("code", Integer.valueOf(this.code)), kotlin.TuplesKt.to("decision", this.decision)}));
    }

    public String toString()
    {
        return com.alibaba.ariver.commonability.device.jsapi.clipboard.a.g(this.code, "Decision(code=", ", decision=", this.decision, ")");
    }
}
