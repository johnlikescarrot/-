package vn.com.vng.zalopay.nfc.state;
public final class c {
    public final byte[] a;
    public final vn.com.vng.zalopay.nfc.state.a b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;

    public c(byte[] p1, vn.com.vng.zalopay.nfc.state.a p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p5;
        this.f = p6;
        this.g = p7;
        this.h = p8;
        this.i = p9;
        this.j = p10;
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            String v1_7;
            if (!p5) {
                v1_7 = 0;
            } else {
                v1_7 = p5.getClass();
            }
            if (kotlin.jvm.internal.Intrinsics.areEqual(vn.com.vng.zalopay.nfc.state.c, v1_7)) {
                if (java.util.Arrays.equals(this.a, ((vn.com.vng.zalopay.nfc.state.c) p5).a)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.vng.zalopay.nfc.state.c) p5).b)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.nfc.state.c) p5).c)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.j, ((vn.com.vng.zalopay.nfc.state.c) p5).j)) {
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

    public final int hashCode()
    {
        int v1_3 = 31;
        return (this.j.hashCode() + e5.i.c(((this.b.hashCode() + (java.util.Arrays.hashCode(this.a) * 31)) * v1_3), v1_3, this.c));
    }

    public final String toString()
    {
        String v0_4 = java.util.Arrays.toString(this.a);
        StringBuilder v1_1 = new StringBuilder("IdentityVerificationInfo(dg2=");
        v1_1.append(v0_4);
        v1_1.append(", dg13=");
        v1_1.append(this.b);
        v1_1.append(", dsCert=");
        q9.H.p(v1_1, this.c, ", sodEncoded=", this.d, ", comEncoded=, dg1Encoded=");
        q9.H.p(v1_1, this.e, ", dg2Encoded=", this.f, ", dg13Encoded=");
        q9.H.p(v1_1, this.g, ", dg14Encoded=", this.h, ", dg15Encoded=");
        return androidx.compose.foundation.layout.G0.n(v1_1, this.i, ", sig=", this.j, ")");
    }
}
