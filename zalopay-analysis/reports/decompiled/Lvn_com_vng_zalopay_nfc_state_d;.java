package vn.com.vng.zalopay.nfc.state;
public final class d {
    public final boolean a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final vn.com.vng.zalopay.nfc.state.g i;

    public d(boolean p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, vn.com.vng.zalopay.nfc.state.g p9)
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
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.nfc.state.d)) {
                if (this.a == ((vn.com.vng.zalopay.nfc.state.d) p5).a) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.vng.zalopay.nfc.state.d) p5).b)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.nfc.state.d) p5).c)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.nfc.state.d) p5).d)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.nfc.state.d) p5).e)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.nfc.state.d) p5).f)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.vng.zalopay.nfc.state.d) p5).g)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.h, ((vn.com.vng.zalopay.nfc.state.d) p5).h)) {
                                                if (this.i == ((vn.com.vng.zalopay.nfc.state.d) p5).i) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        return (this.i.hashCode() + e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((Boolean.hashCode(this.a) * 31), 31, this.b), 31, this.c), 31, this.d), 31, this.e), 31, this.f), 31, this.g), 31, this.h));
    }

    public final String toString()
    {
        String v0_1 = com.google.android.gms.internal.play_billing.c1.n(wmoi.igob.ggcn(3798), wmoi.igob.ggcn(3796), this.b, wmoi.igob.ggcn(3797), this.a);
        q9.H.p(v0_1, this.c, wmoi.igob.ggcn(3799), this.d, wmoi.igob.ggcn(3800));
        q9.H.p(v0_1, this.e, wmoi.igob.ggcn(3801), this.f, wmoi.igob.ggcn(3802));
        q9.H.p(v0_1, this.g, wmoi.igob.ggcn(3803), this.h, wmoi.igob.ggcn(3804));
        v0_1.append(this.i);
        v0_1.append(wmoi.igob.ggcn(3805));
        return v0_1.toString();
    }
}
