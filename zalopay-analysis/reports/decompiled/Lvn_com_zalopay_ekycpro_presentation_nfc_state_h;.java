package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final class h implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator CREATOR;
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;

    static h()
    {
        vn.com.zalopay.ekycpro.presentation.nfc.state.h.CREATOR = new m7.e(26);
        return;
    }

    public synthetic h(String p3, String p4, String p5, String p6, int p7, String p8, String p9, String p10, String p11)
    {
        if ((p7 & 8) != 0) {
            p6 = "";
        }
        if ((p7 & 128) != 0) {
            p11 = "";
        }
        this(p3, p4, p5, p6, p8, p9, p10, p11);
        return;
    }

    public h(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        this.e = p5;
        this.f = p6;
        this.g = p7;
        this.h = p8;
        return;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final boolean equals(Object p5)
    {
        if (this != p5) {
            if ((p5 instanceof vn.com.zalopay.ekycpro.presentation.nfc.state.h)) {
                if (kotlin.jvm.internal.Intrinsics.areEqual(this.a, ((vn.com.zalopay.ekycpro.presentation.nfc.state.h) p5).a)) {
                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.zalopay.ekycpro.presentation.nfc.state.h) p5).b)) {
                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.zalopay.ekycpro.presentation.nfc.state.h) p5).c)) {
                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.zalopay.ekycpro.presentation.nfc.state.h) p5).d)) {
                                if (kotlin.jvm.internal.Intrinsics.areEqual(this.e, ((vn.com.zalopay.ekycpro.presentation.nfc.state.h) p5).e)) {
                                    if (kotlin.jvm.internal.Intrinsics.areEqual(this.f, ((vn.com.zalopay.ekycpro.presentation.nfc.state.h) p5).f)) {
                                        if (kotlin.jvm.internal.Intrinsics.areEqual(this.g, ((vn.com.zalopay.ekycpro.presentation.nfc.state.h) p5).g)) {
                                            if (kotlin.jvm.internal.Intrinsics.areEqual(this.h, ((vn.com.zalopay.ekycpro.presentation.nfc.state.h) p5).h)) {
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
            return 1;
        }
    }

    public final int hashCode()
    {
        return (this.h.hashCode() + e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c(e5.i.c((this.a.hashCode() * 31), 31, this.b), 31, this.c), 31, this.d), 31, this.e), 31, this.f), 31, this.g));
    }

    public final String toString()
    {
        String v0_2 = androidx.camera.camera2.internal.G.i("MrzInfo(idNumber=", this.a, ", dob=", this.b, ", dueDate=");
        q9.H.p(v0_2, this.c, ", issueDate=", this.d, ", fullIdNumber=");
        q9.H.p(v0_2, this.e, ", fullDobString=", this.f, ", fullDueDateString=");
        return androidx.compose.foundation.layout.G0.n(v0_2, this.g, ", bcaOption=", this.h, ")");
    }

    public final void writeToParcel(android.os.Parcel p1, int p2)
    {
        p1.writeString(this.a);
        p1.writeString(this.b);
        p1.writeString(this.c);
        p1.writeString(this.d);
        p1.writeString(this.e);
        p1.writeString(this.f);
        p1.writeString(this.g);
        p1.writeString(this.h);
        return;
    }
}
