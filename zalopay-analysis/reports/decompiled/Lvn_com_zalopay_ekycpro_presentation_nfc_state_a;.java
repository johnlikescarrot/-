package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final class a {
    public final vn.com.zalopay.ekycpro.presentation.nfc.state.h a;
    public final vn.com.zalopay.ekycpro.presentation.nfc.state.h b;

    public a(vn.com.zalopay.ekycpro.presentation.nfc.state.h p1, vn.com.zalopay.ekycpro.presentation.nfc.state.h p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.zalopay.ekycpro.presentation.nfc.state.a)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.zalopay.ekycpro.presentation.nfc.state.a) p5).a)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.zalopay.ekycpro.presentation.nfc.state.a) p5).b)) {
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
        int v1_2;
        int v0 = 0;
        int v1_0 = this.a;
        if (v1_0 != 0) {
            v1_2 = v1_0.hashCode();
        } else {
            v1_2 = 0;
        }
        int v1_3 = (v1_2 * 31);
        vn.com.zalopay.ekycpro.presentation.nfc.state.h v2 = this.b;
        if (v2 != null) {
            v0 = v2.hashCode();
        }
        return (v1_3 + v0);
    }

    public final String toString()
    {
        String v0_2 = new StringBuilder("CombinedMrzResult(mrzFromImage=");
        v0_2.append(this.a);
        v0_2.append(", mrzFromOcr=");
        v0_2.append(this.b);
        v0_2.append(")");
        return v0_2.toString();
    }
}
