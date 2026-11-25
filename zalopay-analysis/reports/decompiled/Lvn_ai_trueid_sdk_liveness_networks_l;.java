package vn.ai.trueid.sdk.liveness.networks;
public final class l {
    public static final vn.ai.trueid.sdk.liveness.networks.l c;
    public final int a;
    public String b;

    static l()
    {
        vn.ai.trueid.sdk.liveness.networks.l.c = new vn.ai.trueid.sdk.liveness.networks.l(-1, vn.ai.trueid.sdk.liveness.networks.Helpers$Companion.c().getString(2131955418));
        return;
    }

    public l(int p1, String p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.liveness.networks.l)) {
                if (this.a == ((vn.ai.trueid.sdk.liveness.networks.l) p5).a) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.ai.trueid.sdk.liveness.networks.l) p5).b)) {
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

    public final int hashCode()
    {
        return (this.b.hashCode() + (Integer.hashCode(this.a) * 31));
    }

    public final String toString()
    {
        return new org.json.JSONObject(kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("code", Integer.valueOf(this.a)), kotlin.TuplesKt.to("message", this.b)}))).toString();
    }
}
