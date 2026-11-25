package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final class e {
    public final int a;
    public final String b;
    public final String c;
    public final vn.com.zalopay.ekycpro.presentation.nfc.state.g d;

    public e(int p1, String p2, String p3, vn.com.zalopay.ekycpro.presentation.nfc.state.g p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public final boolean equals(Object p3)
    {
        if ((this == p3) || (((p3 instanceof vn.com.zalopay.ekycpro.presentation.nfc.state.e)) && ((this.a == ((vn.com.zalopay.ekycpro.presentation.nfc.state.e) p3).a) && ((kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.zalopay.ekycpro.presentation.nfc.state.e) p3).b)) && ((kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.zalopay.ekycpro.presentation.nfc.state.e) p3).c)) && (this.d == ((vn.com.zalopay.ekycpro.presentation.nfc.state.e) p3).d)))))) {
            return 1;
        } else {
            return 0;
        }
    }

    public final int hashCode()
    {
        return (this.d.hashCode() + e5.i.c(e5.i.c((Integer.hashCode(this.a) * 31), 31, this.b), 31, this.c));
    }

    public final String toString()
    {
        String v0_2 = com.alibaba.ariver.commonability.device.jsapi.clipboard.a.m(this.a, "IdentityVerificationOption(icon=", ", title=", this.b, ", desc=");
        v0_2.append(this.c);
        v0_2.append(", type=");
        v0_2.append(this.d);
        v0_2.append(")");
        return v0_2.toString();
    }
}
