package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final class k {
    public final boolean a;
    public final boolean b;
    public final hU.b c;
    public final boolean d;
    public final hU.c e;
    public final iV.x f;

    public k(boolean p1, boolean p2, hU.b p3, boolean p4, hU.c p5, iV.x p6)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p5;
        this.f = p6;
        return;
    }

    public static vn.com.zalopay.ekycpro.presentation.nfc.state.k a(vn.com.zalopay.ekycpro.presentation.nfc.state.k p7, boolean p8, boolean p9, hU.b p10, boolean p11, hU.c p12, iV.x p13, int p14)
    {
        if ((p14 & 1) != 0) {
            p8 = p7.a;
        }
        if ((p14 & 2) != 0) {
            p9 = p7.b;
        }
        if ((p14 & 4) != 0) {
            p10 = p7.c;
        }
        if ((p14 & 8) != 0) {
            p11 = p7.d;
        }
        if ((p14 & 16) != 0) {
            p12 = p7.e;
        }
        if ((p14 & 32) != 0) {
            p13 = p7.f;
        }
        p7.getClass();
        return new vn.com.zalopay.ekycpro.presentation.nfc.state.k(p8, p9, p10, p11, p12, p13);
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.zalopay.ekycpro.presentation.nfc.state.k)) {
                if (this.a == ((vn.com.zalopay.ekycpro.presentation.nfc.state.k) p5).a) {
                    if (this.b == ((vn.com.zalopay.ekycpro.presentation.nfc.state.k) p5).b) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.zalopay.ekycpro.presentation.nfc.state.k) p5).c)) {
                            if (this.d == ((vn.com.zalopay.ekycpro.presentation.nfc.state.k) p5).d) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.zalopay.ekycpro.presentation.nfc.state.k) p5).e)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.zalopay.ekycpro.presentation.nfc.state.k) p5).f)) {
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
        int v3_0;
        int v0_9 = Kb.e.i((Boolean.hashCode(this.a) * 31), 31, this.b);
        int v2_0 = 0;
        int v3_4 = this.c;
        if (v3_4 != 0) {
            v3_0 = v3_4.hashCode();
        } else {
            v3_0 = 0;
        }
        int v3_3;
        int v0_3 = Kb.e.i(((v0_9 + v3_0) * 31), 31, this.d);
        int v3_2 = this.e;
        if (v3_2 != 0) {
            v3_3 = v3_2.hashCode();
        } else {
            v3_3 = 0;
        }
        int v0_6 = ((v0_3 + v3_3) * 31);
        iV.x v1_0 = this.f;
        if (v1_0 != null) {
            v2_0 = v1_0.hashCode();
        }
        return (v0_6 + v2_0);
    }

    public final String toString()
    {
        String v0_2 = androidx.media3.exoplayer.source.r.f("NfcBehalfBState(isLoading=", ", isSubmitting=", ", response=", this.a, this.b);
        v0_2.append(this.c);
        v0_2.append(", resultSuccess=");
        v0_2.append(this.d);
        v0_2.append(", resultMetadata=");
        v0_2.append(this.e);
        v0_2.append(", errorPopup=");
        v0_2.append(this.f);
        v0_2.append(")");
        return v0_2.toString();
    }
}
