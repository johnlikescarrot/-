package vn.com.vng.zalopay.nfc.state;
public final class b {
    public final vn.com.vng.zalopay.nfc.state.h a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;

    public b()
    {
        this.a = vn.com.vng.zalopay.nfc.state.h.b;
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        return;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.nfc.state.b)) {
                if (this.a == ((vn.com.vng.zalopay.nfc.state.b) p5).a) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.vng.zalopay.nfc.state.b) p5).b)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.nfc.state.b) p5).c)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.nfc.state.b) p5).d)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.nfc.state.b) p5).e)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(0, 0)) {
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
        return e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.a.hashCode() * 31), 31, this.b), 31, this.c), 31, this.d), 31, this.e);
    }

    public final String toString()
    {
        String v0_2 = new StringBuilder(wmoi.igob.ggcn(3808));
        v0_2.append(this.a);
        v0_2.append(wmoi.igob.ggcn(3809));
        v0_2.append(this.b);
        v0_2.append(wmoi.igob.ggcn(3810));
        q9.H.p(v0_2, this.c, wmoi.igob.ggcn(3811), this.d, wmoi.igob.ggcn(3812));
        return A1.a.l(v0_2, this.e, wmoi.igob.ggcn(3813));
    }
}
