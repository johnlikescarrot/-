package vn.com.zalopay.ekycpro.presentation.nfc.state;
public abstract class j {

    public static final String a(int p4)
    {
        String v0_4 = m9.Q2.a(kotlin.jvm.internal.M.a.b(vn.com.zalopay.ekycpro.presentation.nfc.state.NFCState));
        if (v0_4 != null) {
            String v0_1 = m9.Q2.b(v0_4).iterator();
            while (v0_1.hasNext()) {
                kotlin.reflect.p v1_2 = v0_1.next();
                Integer v3_1 = new Object[0];
                if (kotlin.jvm.internal.Intrinsics.areEqual(((kotlin.reflect.p) v1_2).e().call(v3_1), Integer.valueOf(p4))) {
                }
                kotlin.reflect.p v1_3 = ((kotlin.reflect.p) v1_2);
                if (v1_3 != null) {
                    String v0_3 = v1_3.getName();
                    if (v0_3 != null) {
                        return v0_3;
                    }
                }
            }
            v1_2 = 0;
        }
        return String.valueOf(p4);
    }
}
