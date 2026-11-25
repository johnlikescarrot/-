package vn;
public final class c extends vn.d {
    public final sn.a a;
    public final boolean b;

    public c(sn.a p1, boolean p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.c)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.c) p5).a)) {
                    if (this.b == ((vn.c) p5).b) {
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
        return (Boolean.hashCode(this.b) + (this.a.hashCode() * 31));
    }

    public final String toString()
    {
        String v0_2 = new StringBuilder("Success(versionModel=");
        v0_2.append(this.a);
        v0_2.append(", updateAvailable=");
        v0_2.append(this.b);
        v0_2.append(")");
        return v0_2.toString();
    }
}
