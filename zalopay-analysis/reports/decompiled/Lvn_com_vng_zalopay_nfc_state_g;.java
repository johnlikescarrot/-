package vn.com.vng.zalopay.nfc.state;
public final enum class g extends java.lang.Enum {
    public static final enum vn.com.vng.zalopay.nfc.state.g a;
    public static final synthetic vn.com.vng.zalopay.nfc.state.g[] b;

    static g()
    {
        xg.b v0_2 = new vn.com.vng.zalopay.nfc.state.g("REQUEST_ERROR", 0);
        vn.com.vng.zalopay.nfc.state.g.a = v0_2;
        vn.com.vng.zalopay.nfc.state.g.b = new vn.com.vng.zalopay.nfc.state.g[] {v0_2, new vn.com.vng.zalopay.nfc.state.g("SUBMIT_ERROR", 1)});
        return;
    }

    public static vn.com.vng.zalopay.nfc.state.g valueOf(String p1)
    {
        return ((vn.com.vng.zalopay.nfc.state.g) Enum.valueOf(vn.com.vng.zalopay.nfc.state.g, p1));
    }

    public static vn.com.vng.zalopay.nfc.state.g[] values()
    {
        return ((vn.com.vng.zalopay.nfc.state.g[]) vn.com.vng.zalopay.nfc.state.g.b.clone());
    }
}
