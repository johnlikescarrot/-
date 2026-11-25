package vn.ai.trueid.sdk.face;
public final class t0 {
    public final String a;
    public final boolean b;
    public final vn.ai.trueid.sdk.face.h1 c;

    public synthetic t0()
    {
        this("", 0, vn.ai.trueid.sdk.face.h1.STEP_FIRST_CHECK);
        return;
    }

    public t0(String p1, boolean p2, vn.ai.trueid.sdk.face.h1 p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.ai.trueid.sdk.face.t0)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.ai.trueid.sdk.face.t0) p5).a)) {
                    if (this.b == ((vn.ai.trueid.sdk.face.t0) p5).b) {
                        if (this.c == ((vn.ai.trueid.sdk.face.t0) p5).c) {
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

    public final int hashCode()
    {
        int vtmp1 = this.a.hashCode();
        int v1_1 = this.b;
        if (v1_1 != 0) {
            v1_1 = 1;
        }
        int v1_0;
        int v0_5 = (((vtmp1 * 31) + v1_1) * 31);
        int v1_2 = this.c;
        if (v1_2 != 0) {
            v1_0 = v1_2.hashCode();
        } else {
            v1_0 = 0;
        }
        return (v0_5 + v1_0);
    }

    public final String toString()
    {
        String v0_2 = new StringBuilder("LivenessViewState(messageLiveness=");
        v0_2.append(this.a);
        v0_2.append(", isCompleted=");
        v0_2.append(this.b);
        v0_2.append(", stepLiveness=");
        v0_2.append(this.c);
        v0_2.append(41);
        return v0_2.toString();
    }
}
