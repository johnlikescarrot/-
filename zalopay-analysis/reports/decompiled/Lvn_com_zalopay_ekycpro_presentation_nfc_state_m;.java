package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final class m {
    public final GU.b a;
    public final java.util.List b;

    public m(GU.b p1, java.util.List p2)
    {
        this.a = p1;
        this.b = p2;
        return;
    }

    public static vn.com.zalopay.ekycpro.presentation.nfc.state.m a(vn.com.zalopay.ekycpro.presentation.nfc.state.m p2)
    {
        GU.b v0 = p2.a;
        java.util.List v1 = p2.b;
        p2.getClass();
        return new vn.com.zalopay.ekycpro.presentation.nfc.state.m(v0, v1);
    }

    public final boolean equals(Object p4)
    {
        if (this != p4) {
            if (((p4 instanceof vn.com.zalopay.ekycpro.presentation.nfc.state.m)) && ((this.a == ((vn.com.zalopay.ekycpro.presentation.nfc.state.m) p4).a) && (kotlin.jvm.internal.Intrinsics.areEqual(this.b, ((vn.com.zalopay.ekycpro.presentation.nfc.state.m) p4).b)))) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 1;
        }
    }

    public final int hashCode()
    {
        return (Boolean.hashCode(0) + Kb.e.h((this.a.hashCode() * 31), 31, this.b));
    }

    public final String toString()
    {
        String v0_2 = new StringBuilder("RetryAuthenOptionsScreenState(selectedOption=");
        v0_2.append(this.a);
        v0_2.append(", options=");
        v0_2.append(this.b);
        v0_2.append(", showGuideDeviceNfc=false)");
        return v0_2.toString();
    }
}
