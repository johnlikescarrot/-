package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final class d {
    public final byte[] a;
    public final vn.com.zalopay.ekycpro.presentation.nfc.state.b b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;

    public d(byte[] p1, vn.com.zalopay.ekycpro.presentation.nfc.state.b p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, String p12, String p13, String p14)
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
        this.k = p11;
        this.l = p12;
        this.m = p13;
        this.n = p14;
        return;
    }

    public static vn.com.zalopay.ekycpro.presentation.nfc.state.d a(vn.com.zalopay.ekycpro.presentation.nfc.state.d p16, vn.com.zalopay.ekycpro.presentation.nfc.state.b p17, String p18, String p19, String p20, String p21, int p22)
    {
        String v3_2;
        if ((p22 & 2) == 0) {
            v3_2 = p17;
        } else {
            v3_2 = p16.b;
        }
        String v12_1;
        vn.com.zalopay.ekycpro.presentation.nfc.state.b v2_1 = v3_2;
        String v3_0 = p16.c;
        String v4_0 = p16.d;
        String v5_0 = p16.e;
        String v6_1 = p16.f;
        String v7_1 = p16.g;
        String v8_1 = p16.h;
        String v9_1 = p16.i;
        String v11_0 = p16.a;
        String v10_1 = p16.j;
        if ((p22 & 1024) == 0) {
            v12_1 = p18;
        } else {
            v12_1 = p16.k;
        }
        String v13_1;
        if ((p22 & 2048) == 0) {
            v13_1 = p19;
        } else {
            v13_1 = p16.l;
        }
        String v14_1;
        if ((p22 & 4096) == 0) {
            v14_1 = p20;
        } else {
            v14_1 = p16.m;
        }
        byte[] v1_2;
        if ((p22 & 8192) == 0) {
            v1_2 = p21;
        } else {
            v1_2 = p16.n;
        }
        p16.getClass();
        return new vn.com.zalopay.ekycpro.presentation.nfc.state.d(v11_0, v2_1, v3_0, v4_0, v5_0, v6_1, v7_1, v8_1, v9_1, v10_1, v12_1, v13_1, v14_1, v1_2);
    }

    public final vn.com.zalopay.ekycpro.presentation.nfc.state.b b()
    {
        return this.b;
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
            if (kotlin.jvm.internal.Intrinsics.areEqual(vn.com.zalopay.ekycpro.presentation.nfc.state.d, v1_7)) {
                if (java.util.Arrays.equals(this.a, ((vn.com.zalopay.ekycpro.presentation.nfc.state.d) p5).a)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.zalopay.ekycpro.presentation.nfc.state.d) p5).b)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.zalopay.ekycpro.presentation.nfc.state.d) p5).c)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.l, ((vn.com.zalopay.ekycpro.presentation.nfc.state.d) p5).l)) {
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
        return (this.l.hashCode() + e5.i.c(((this.b.hashCode() + (java.util.Arrays.hashCode(this.a) * 31)) * v1_3), v1_3, this.c));
    }

    public final String toString()
    {
        String v0_4 = java.util.Arrays.toString(this.a);
        StringBuilder v1_1 = new StringBuilder("IdentityVerificationInfo(dg2=");
        v1_1.append(v0_4);
        v1_1.append(", dg13=");
        v1_1.append(this.b);
        v1_1.append(", dsCert=");
        q9.H.p(v1_1, this.c, ", sodEncoded=", this.d, ", comEncoded=");
        q9.H.p(v1_1, this.e, ", dg1Encoded=", this.f, ", dg2Encoded=");
        q9.H.p(v1_1, this.g, ", dg13Encoded=", this.h, ", dg14Encoded=");
        q9.H.p(v1_1, this.i, ", dg15Encoded=", this.j, ", nfcVendor=");
        q9.H.p(v1_1, this.k, ", sig=", this.l, ", qr=");
        return androidx.compose.foundation.layout.G0.n(v1_1, this.m, ", eKycId=", this.n, ")");
    }
}
