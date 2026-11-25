package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final class i {
    public final int a;
    public final String b;
    public final String c;
    public final int d;
    public final String e;

    public i(int p1, int p2, String p3, String p4, String p5)
    {
        this.a = p1;
        this.b = p3;
        this.c = p4;
        this.d = p2;
        this.e = p5;
        return;
    }

    public synthetic i(String p7, int p8, String p9, int p10)
    {
        if ((p10 & 2) != 0) {
            p7 = "H\u00e3y ch\u1ea1m chip CCCD v\u00e0o m\u1eb7t sau, ph\u00eda tr\u00ean xung quanh camera c\u1ee7a \u0111i\u1ec7n tho\u1ea1i";
        }
        if ((p10 & 8) != 0) {
            p8 = 2131234362;
        }
        if ((p10 & 16) != 0) {
            p9 = "Qu\u00e9t l\u1ea1i";
        }
        this(0, p8, p7, "S\u1eb3n s\u00e0ng qu\u00e9t", p9);
        return;
    }

    public final int a()
    {
        return this.a;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.zalopay.ekycpro.presentation.nfc.state.i)) {
                if (this.a == ((vn.com.zalopay.ekycpro.presentation.nfc.state.i) p5).a) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.zalopay.ekycpro.presentation.nfc.state.i) p5).b)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.zalopay.ekycpro.presentation.nfc.state.i) p5).c)) {
                            if (this.d == ((vn.com.zalopay.ekycpro.presentation.nfc.state.i) p5).d) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.zalopay.ekycpro.presentation.nfc.state.i) p5).e)) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        return (this.e.hashCode() + Kb.e.v(this.d, e5.i.c(e5.i.c((Integer.hashCode(this.a) * 31), 31, this.b), 31, this.c), 31));
    }

    public final String toString()
    {
        String v0_2 = com.alibaba.ariver.commonability.device.jsapi.clipboard.a.m(this.a, "NFCScanningPopUpUIState(state=", ", stateDescription=", this.b, ", title=");
        org.bouncycastle.jcajce.provider.asymmetric.a.s(this.d, this.c, ", icon=", ", ctaTextContent=", v0_2);
        return A1.a.l(v0_2, this.e, ")");
    }
}
