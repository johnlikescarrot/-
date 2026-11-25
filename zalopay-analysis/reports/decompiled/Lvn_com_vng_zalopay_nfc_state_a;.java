package vn.com.vng.zalopay.nfc.state;
public final class a {
    public final String a;
    public final String b;
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

    public a(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10, String p11, String p12, String p13, String p14)
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

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.vng.zalopay.nfc.state.a)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.vng.zalopay.nfc.state.a) p5).a)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.vng.zalopay.nfc.state.a) p5).b)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.vng.zalopay.nfc.state.a) p5).c)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.vng.zalopay.nfc.state.a) p5).d)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.vng.zalopay.nfc.state.a) p5).e)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.vng.zalopay.nfc.state.a) p5).f)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.vng.zalopay.nfc.state.a) p5).g)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.h, ((vn.com.vng.zalopay.nfc.state.a) p5).h)) {
                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.i, ((vn.com.vng.zalopay.nfc.state.a) p5).i)) {
                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.j, ((vn.com.vng.zalopay.nfc.state.a) p5).j)) {
                                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.k, ((vn.com.vng.zalopay.nfc.state.a) p5).k)) {
                                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.l, ((vn.com.vng.zalopay.nfc.state.a) p5).l)) {
                                                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.m, ((vn.com.vng.zalopay.nfc.state.a) p5).m)) {
                                                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.n, ((vn.com.vng.zalopay.nfc.state.a) p5).n)) {
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
        return (this.n.hashCode() + e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.a.hashCode() * 31), 31, this.b), 31, this.c), 31, this.d), 31, this.e), 31, this.f), 31, this.g), 31, this.h), 31, this.i), 31, this.j), 31, this.k), 31, this.l), 31, this.m));
    }

    public final String toString()
    {
        String v0_2 = androidx.camera.camera2.internal.G.i("DG13Info(eIdNumber=", this.a, ", name=", this.b, ", dob=");
        q9.H.p(v0_2, this.c, ", nationality=", this.d, ", ethnicity=");
        q9.H.p(v0_2, this.e, ", religion=", this.f, ", placeOrigin=");
        q9.H.p(v0_2, this.g, ", placeResidence=", this.h, ", issueDate=");
        q9.H.p(v0_2, this.i, ", personalIdentify=", this.j, ", fatherName=");
        q9.H.p(v0_2, this.k, ", motherName=", this.l, ", spouseName=");
        return androidx.compose.foundation.layout.G0.n(v0_2, this.m, ", cmnd=", this.n, ")");
    }
}
