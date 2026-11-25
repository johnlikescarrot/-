package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final class c {
    public final vn.com.zalopay.ekycpro.presentation.nfc.state.o a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    public final String e;
    public final String f;
    public final String g;
    public final boolean h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;

    public synthetic c(String p15, String p16, String p17, boolean p18, String p19, String p20, String p21, int p22)
    {
        String v5;
        if ((p22 & 16) == 0) {
            v5 = p15;
        } else {
            v5 = "";
        }
        String v6;
        if ((p22 & 32) == 0) {
            v6 = p16;
        } else {
            v6 = "";
        }
        String v7;
        if ((p22 & 64) == 0) {
            v7 = p17;
        } else {
            v7 = "";
        }
        boolean v8;
        if ((p22 & 128) == 0) {
            v8 = p18;
        } else {
            v8 = 0;
        }
        String v11;
        if ((p22 & 2048) == 0) {
            v11 = p19;
        } else {
            v11 = "";
        }
        String v12;
        if ((p22 & 4096) == 0) {
            v12 = p20;
        } else {
            v12 = "";
        }
        String v13;
        if ((p22 & 8192) == 0) {
            v13 = p21;
        } else {
            v13 = "";
        }
        this(vn.com.zalopay.ekycpro.presentation.nfc.state.o.b, 0, 0, 0, v5, v6, v7, v8, "", "", v11, v12, v13);
        return;
    }

    public c(vn.com.zalopay.ekycpro.presentation.nfc.state.o p1, boolean p2, boolean p3, boolean p4, String p5, String p6, String p7, boolean p8, String p9, String p10, String p11, String p12, String p13)
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
        return;
    }

    public static vn.com.zalopay.ekycpro.presentation.nfc.state.c a(vn.com.zalopay.ekycpro.presentation.nfc.state.c p14, boolean p15, boolean p16, boolean p17, String p18, String p19, String p20, String p21, String p22, String p23, String p24, int p25)
    {
        boolean v2_1;
        vn.com.zalopay.ekycpro.presentation.nfc.state.o v1 = p14.a;
        if ((p25 & 2) == 0) {
            v2_1 = p15;
        } else {
            v2_1 = p14.b;
        }
        boolean v3_1;
        if ((p25 & 4) == 0) {
            v3_1 = p16;
        } else {
            v3_1 = p14.c;
        }
        boolean v4_1;
        if ((p25 & 8) == 0) {
            v4_1 = p17;
        } else {
            v4_1 = p14.d;
        }
        String v5_1;
        if ((p25 & 16) == 0) {
            v5_1 = p18;
        } else {
            v5_1 = p14.e;
        }
        String v6_1;
        if ((p25 & 32) == 0) {
            v6_1 = p19;
        } else {
            v6_1 = p14.f;
        }
        String v7_1;
        if ((p25 & 64) == 0) {
            v7_1 = p20;
        } else {
            v7_1 = p14.g;
        }
        String v10_1;
        boolean v8 = p14.h;
        String v9 = p14.i;
        if ((p25 & 512) == 0) {
            v10_1 = p21;
        } else {
            v10_1 = p14.j;
        }
        String v11_1;
        p14.getClass();
        if ((p25 & 2048) == 0) {
            v11_1 = p22;
        } else {
            v11_1 = p14.k;
        }
        String v12_1;
        if ((p25 & 4096) == 0) {
            v12_1 = p23;
        } else {
            v12_1 = p14.l;
        }
        String v13;
        if ((p25 & 8192) == 0) {
            v13 = p24;
        } else {
            v13 = p14.m;
        }
        p14.getClass();
        return new vn.com.zalopay.ekycpro.presentation.nfc.state.c(v1, v2_1, v3_1, v4_1, v5_1, v6_1, v7_1, v8, v9, v10_1, v11_1, v12_1, v13);
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.zalopay.ekycpro.presentation.nfc.state.c)) {
                if (this.a == ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).a) {
                    if (this.b == ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).b) {
                        if (this.c == ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).c) {
                            if (this.d == ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).d) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).e)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).f)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).g)) {
                                            if (this.h == ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).h) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.i, ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).i)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.j, ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).j)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(0, 0)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.k, ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).k)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.l, ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).l)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.m, ((vn.com.zalopay.ekycpro.presentation.nfc.state.c) p5).m)) {
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
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final int hashCode()
    {
        return (this.m.hashCode() + e5.i.c(e5.i.c(e5.i.c(e5.i.c(Kb.e.i(e5.i.c(e5.i.c(e5.i.c(Kb.e.i(Kb.e.i(Kb.e.i((this.a.hashCode() * 31), 31, this.b), 31, this.c), 31, this.d), 31, this.e), 31, this.f), 31, this.g), 31, this.h), 31, this.i), 961, this.j), int v1_0, this.k), 31, this.l));
    }

    public final String toString()
    {
        String v0_1 = new StringBuilder("IdentityVerificationConfig(status=");
        v0_1.append(this.a);
        v0_1.append(", showBottomNFC=");
        v0_1.append(this.b);
        v0_1.append(", showBottomNFCTrueId=");
        q9.H.q(v0_1, this.c, ", startNFC=", this.d, ", idNumber=");
        q9.H.p(v0_1, this.e, ", dob=", this.f, ", dueDate=");
        com.alibaba.ariver.commonability.device.jsapi.clipboard.a.z(v0_1, this.g, ", isPACE=", this.h, ", sig=");
        q9.H.p(v0_1, this.i, ", eKycId=", this.j, ", successInfo=null, fullIdNumber=");
        q9.H.p(v0_1, this.k, ", fullDob=", this.l, ", fullDueDate=");
        return A1.a.l(v0_1, this.m, ")");
    }
}
