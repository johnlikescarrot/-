package vn.com.vng.zalopay.nfc.state;
public final enum class h extends java.lang.Enum {
    public static final enum vn.com.vng.zalopay.nfc.state.h b;
    public static final synthetic vn.com.vng.zalopay.nfc.state.h[] c;
    public final int a;

    static h()
    {
        int v2 = 0;
        vn.com.vng.zalopay.nfc.state.h[] v0_3 = new vn.com.vng.zalopay.nfc.state.h("COMPLETED_NFC", 0, 1);
        vn.com.vng.zalopay.nfc.state.h.b = v0_3;
        vn.com.vng.zalopay.nfc.state.h.c = new vn.com.vng.zalopay.nfc.state.h[] {v0_3, new vn.com.vng.zalopay.nfc.state.h("NEED_TO_SCAN_NFC", 1, 2), new vn.com.vng.zalopay.nfc.state.h("TICKET_PROCESSING", 2, 3)});
        vn.com.vng.zalopay.nfc.state.h[] v0_2 = vn.com.vng.zalopay.nfc.state.h.values();
        java.util.LinkedHashMap v3_4 = new java.util.LinkedHashMap(Og.x.coerceAtLeast(kotlin.collections.Z.mapCapacity(v0_2.length), 16));
        int v1_4 = v0_2.length;
        while (v2 < v1_4) {
            vn.com.vng.zalopay.nfc.state.h v4_1 = v0_2[v2];
            v3_4.put(Integer.valueOf(v4_1.a), v4_1);
            v2++;
        }
        return;
    }

    public h(String p1, int p2, int p3)
    {
        super(p1, p2);
        super.a = p3;
        return;
    }

    public static vn.com.vng.zalopay.nfc.state.h valueOf(String p1)
    {
        return ((vn.com.vng.zalopay.nfc.state.h) Enum.valueOf(vn.com.vng.zalopay.nfc.state.h, p1));
    }

    public static vn.com.vng.zalopay.nfc.state.h[] values()
    {
        return ((vn.com.vng.zalopay.nfc.state.h[]) vn.com.vng.zalopay.nfc.state.h.c.clone());
    }
}
