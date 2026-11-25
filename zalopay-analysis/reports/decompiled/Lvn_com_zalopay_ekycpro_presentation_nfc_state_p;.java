package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final class p {
    public final int a;
    public final float b;
    public final String c;
    public final String d;

    public synthetic p(int p4)
    {
        this(p4, 0, "Th\u1ef1c hi\u1ec7n theo clip h\u01b0\u1edbng d\u1eabn sau", "Tr\u01b0\u1ee3t m\u1eb7t sau CCCD d\u1ecdc theo ph\u00eda sau \u0111i\u1ec7n tho\u1ea1i, khu v\u1ef1c g\u1ea7n camera");
        return;
    }

    public p(int p1, float p2, String p3, String p4)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        this.d = p4;
        return;
    }

    public static vn.com.zalopay.ekycpro.presentation.nfc.state.p a(vn.com.zalopay.ekycpro.presentation.nfc.state.p p3, float p4)
    {
        int v0 = p3.a;
        String v1 = p3.c;
        String v2 = p3.d;
        p3.getClass();
        return new vn.com.zalopay.ekycpro.presentation.nfc.state.p(v0, p4, v1, v2);
    }

    public final boolean equals(Object p3)
    {
        if ((this == p3) || (((p3 instanceof vn.com.zalopay.ekycpro.presentation.nfc.state.p)) && ((this.a == ((vn.com.zalopay.ekycpro.presentation.nfc.state.p) p3).a) && ((Float.compare(this.b, ((vn.com.zalopay.ekycpro.presentation.nfc.state.p) p3).b) == 0) && ((kotlin.jvm.internal.Intrinsics.areEqual(this.c, ((vn.com.zalopay.ekycpro.presentation.nfc.state.p) p3).c)) && (kotlin.jvm.internal.Intrinsics.areEqual(this.d, ((vn.com.zalopay.ekycpro.presentation.nfc.state.p) p3).d))))))) {
            return 1;
        } else {
            return 0;
        }
    }

    public final int hashCode()
    {
        return (this.d.hashCode() + e5.i.c(Kb.e.d(this.b, (Integer.hashCode(this.a) * 31), 31), 31, this.c));
    }

    public final String toString()
    {
        String v0_2 = new StringBuilder("StoryItemUIState(imageAnimResId=");
        v0_2.append(this.a);
        v0_2.append(", progressLoading=");
        v0_2.append(this.b);
        v0_2.append(", title=");
        return androidx.compose.foundation.layout.G0.n(v0_2, this.c, ", description=", this.d, ")");
    }
}
