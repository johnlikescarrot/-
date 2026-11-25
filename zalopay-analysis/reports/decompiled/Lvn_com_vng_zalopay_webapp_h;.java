package vn.com.vng.zalopay.webapp;
public final enum class h extends java.lang.Enum {
    public static final enum vn.com.vng.zalopay.webapp.h a;
    public static final enum vn.com.vng.zalopay.webapp.h b;
    public static final synthetic vn.com.vng.zalopay.webapp.h[] c;

    static h()
    {
        xg.b v0_2 = new vn.com.vng.zalopay.webapp.h("Url", 0);
        vn.com.vng.zalopay.webapp.h.a = v0_2;
        vn.com.vng.zalopay.webapp.h v1_2 = new vn.com.vng.zalopay.webapp.h("Base64", 1);
        vn.com.vng.zalopay.webapp.h.b = v1_2;
        vn.com.vng.zalopay.webapp.h.c = new vn.com.vng.zalopay.webapp.h[] {v0_2, v1_2});
        return;
    }

    public static vn.com.vng.zalopay.webapp.h valueOf(String p1)
    {
        return ((vn.com.vng.zalopay.webapp.h) Enum.valueOf(vn.com.vng.zalopay.webapp.h, p1));
    }

    public static vn.com.vng.zalopay.webapp.h[] values()
    {
        return ((vn.com.vng.zalopay.webapp.h[]) vn.com.vng.zalopay.webapp.h.c.clone());
    }
}
