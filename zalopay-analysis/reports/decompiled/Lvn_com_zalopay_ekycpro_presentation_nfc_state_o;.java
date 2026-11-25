package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final enum class o extends java.lang.Enum {
    public static final enum vn.com.zalopay.ekycpro.presentation.nfc.state.o b;
    public static final synthetic vn.com.zalopay.ekycpro.presentation.nfc.state.o[] c;
    public final int a;

    static o()
    {
        int v2 = 0;
        vn.com.zalopay.ekycpro.presentation.nfc.state.o[] v0_3 = new vn.com.zalopay.ekycpro.presentation.nfc.state.o("COMPLETED_NFC", 0, 1);
        vn.com.zalopay.ekycpro.presentation.nfc.state.o.b = v0_3;
        vn.com.zalopay.ekycpro.presentation.nfc.state.o.c = new vn.com.zalopay.ekycpro.presentation.nfc.state.o[] {v0_3, new vn.com.zalopay.ekycpro.presentation.nfc.state.o("NEED_TO_SCAN_NFC", 1, 2), new vn.com.zalopay.ekycpro.presentation.nfc.state.o("TICKET_PROCESSING", 2, 3)});
        vn.com.zalopay.ekycpro.presentation.nfc.state.o[] v0_2 = vn.com.zalopay.ekycpro.presentation.nfc.state.o.values();
        java.util.LinkedHashMap v3_4 = new java.util.LinkedHashMap(Og.x.coerceAtLeast(kotlin.collections.Z.mapCapacity(v0_2.length), 16));
        int v1_4 = v0_2.length;
        while (v2 < v1_4) {
            vn.com.zalopay.ekycpro.presentation.nfc.state.o v4_1 = v0_2[v2];
            v3_4.put(Integer.valueOf(v4_1.a), v4_1);
            v2++;
        }
        return;
    }

    public o(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.a = p3;
        return;
    }

    public static vn.com.zalopay.ekycpro.presentation.nfc.state.o valueOf(String p1)
    {
        return ((vn.com.zalopay.ekycpro.presentation.nfc.state.o) Enum.valueOf(vn.com.zalopay.ekycpro.presentation.nfc.state.o, p1));
    }

    public static vn.com.zalopay.ekycpro.presentation.nfc.state.o[] values()
    {
        return ((vn.com.zalopay.ekycpro.presentation.nfc.state.o[]) vn.com.zalopay.ekycpro.presentation.nfc.state.o.c.clone());
    }
}
