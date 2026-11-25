package vn.com.zalopay.ekycpro.presentation.nfc.state;
public final enum class g extends java.lang.Enum {
    public static final enum vn.com.zalopay.ekycpro.presentation.nfc.state.g a;
    public static final enum vn.com.zalopay.ekycpro.presentation.nfc.state.g b;
    public static final synthetic vn.com.zalopay.ekycpro.presentation.nfc.state.g[] c;

    static g()
    {
        xg.b v0_2 = new vn.com.zalopay.ekycpro.presentation.nfc.state.g("SCAN_NFC", 0);
        vn.com.zalopay.ekycpro.presentation.nfc.state.g v1_2 = new vn.com.zalopay.ekycpro.presentation.nfc.state.g("VN_EID", 1);
        vn.com.zalopay.ekycpro.presentation.nfc.state.g.a = v1_2;
        vn.com.zalopay.ekycpro.presentation.nfc.state.g v2_0 = new vn.com.zalopay.ekycpro.presentation.nfc.state.g("BANK_COUNTER", 2);
        vn.com.zalopay.ekycpro.presentation.nfc.state.g.b = v2_0;
        vn.com.zalopay.ekycpro.presentation.nfc.state.g.c = new vn.com.zalopay.ekycpro.presentation.nfc.state.g[] {v0_2, v1_2, v2_0});
        return;
    }

    public static vn.com.zalopay.ekycpro.presentation.nfc.state.g valueOf(String p1)
    {
        return ((vn.com.zalopay.ekycpro.presentation.nfc.state.g) Enum.valueOf(vn.com.zalopay.ekycpro.presentation.nfc.state.g, p1));
    }

    public static vn.com.zalopay.ekycpro.presentation.nfc.state.g[] values()
    {
        return ((vn.com.zalopay.ekycpro.presentation.nfc.state.g[]) vn.com.zalopay.ekycpro.presentation.nfc.state.g.c.clone());
    }
}
