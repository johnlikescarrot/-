package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final class n {
    public final String a;
    public final String b;
    public final String c;
    public final b00.P d;
    public final boolean e;

    public n(String p1, String p2, String p3, b00.P p4, boolean p5)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p5;
        return;
    }

    public static vn.com.zalopay.ekycpro.presentation.nfc.state.n a(vn.com.zalopay.ekycpro.presentation.nfc.state.n p6, String p7, String p8, String p9, b00.P p10, boolean p11, int p12)
    {
        if ((p12 & 1) != 0) {
            p7 = p6.a;
        }
        if ((p12 & 2) != 0) {
            p8 = p6.b;
        }
        if ((p12 & 4) != 0) {
            p9 = p6.c;
        }
        if ((p12 & 8) != 0) {
            p10 = p6.d;
        }
        if ((p12 & 16) != 0) {
            p11 = p6.e;
        }
        p6.getClass();
        return new vn.com.zalopay.ekycpro.presentation.nfc.state.n(p7, p8, p9, p10, p11);
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.zalopay.ekycpro.presentation.nfc.state.n)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.zalopay.ekycpro.presentation.nfc.state.n) p5).a)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.zalopay.ekycpro.presentation.nfc.state.n) p5).b)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.zalopay.ekycpro.presentation.nfc.state.n) p5).c)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.zalopay.ekycpro.presentation.nfc.state.n) p5).d)) {
                                if (this.e == ((vn.com.zalopay.ekycpro.presentation.nfc.state.n) p5).e) {
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
        return (Boolean.hashCode(this.e) + ((this.d.hashCode() + e5.i.c(e5.i.c((this.a.hashCode() * 31), 31, this.b), 31, this.c)) * 31));
    }

    public final String toString()
    {
        String v0_2 = androidx.camera.camera2.internal.G.i("ReviewMrzState(idNumber=", this.a, ", dob=", this.b, ", expiredDate=");
        v0_2.append(this.c);
        v0_2.append(", issuedDateInputField=");
        v0_2.append(this.d);
        v0_2.append(", showIssueDateInput=");
        return androidx.activity.result.e.q(")", v0_2, this.e);
    }
}
