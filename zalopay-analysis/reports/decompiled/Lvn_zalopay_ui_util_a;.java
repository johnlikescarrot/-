package vn.zalopay.ui.util;
public abstract class a {
    public static boolean a;
    public static volatile boolean b;
    public static final java.util.HashMap c;
    public static java.util.Map d;

    static a()
    {
        vn.zalopay.ui.util.a.c = new java.util.HashMap();
        vn.zalopay.ui.util.a.d = new java.util.HashMap();
        return;
    }

    public static String a(String p1, String p2)
    {
        if (!vn.zalopay.ui.util.a.d.containsKey(p1)) {
            if (!vn.zalopay.ui.util.a.d.containsKey(p2)) {
                ck.b.e(wmoi.igob.ggcn(553), new Object[] {p1, Integer.valueOf(vn.zalopay.ui.util.a.d.size())}));
                return "";
            } else {
                return ((MZ.e) vn.zalopay.ui.util.a.d.get(p2)).b;
            }
        } else {
            return ((MZ.e) vn.zalopay.ui.util.a.d.get(p1)).b;
        }
    }

    public static android.graphics.Typeface b()
    {
        Object[] v1_2 = vn.zalopay.ui.util.a.c;
        android.graphics.Typeface v0_3 = ((android.graphics.Typeface) v1_2.get(wmoi.igob.ggcn(554)));
        if (v0_3 == null) {
            ck.b.e(wmoi.igob.ggcn(555), new Object[] {Integer.valueOf(v1_2.size())}));
        }
        return v0_3;
    }

    public static void c(android.content.Context p3)
    {
        try {
            vn.zalopay.ui.util.a.d(p3.getAssets());
            return;
        } catch (Exception v3_2) {
            Object[] v1_1 = new Object[0];
            ck.b.g(v3_2, wmoi.igob.ggcn(556), v1_1);
            return;
        }
    }

    public static void d(android.content.res.AssetManager p4)
    {
        if (!vn.zalopay.ui.util.a.b) {
            vn.zalopay.ui.util.a.c.put(wmoi.igob.ggcn(558), android.graphics.Typeface.createFromAsset(p4, wmoi.igob.ggcn(557)));
            Throwable v0_3 = new vn.zalopay.ui.util.FontLoader$1().getType();
            try {
                Exception v4_1 = p4.open(wmoi.igob.ggcn(559));
                try {
                    byte[] v1_2 = new byte[v4_1.available()];
                    v4_1.read(v1_2);
                    String v2_2 = new String(v1_2);
                    try {
                        v4_1.close();
                    } catch (Exception v4_3) {
                        v4_3.getMessage();
                    }
                    vn.zalopay.ui.util.a.d = ((java.util.Map) nY.h.b(v2_2, v0_3));
                    vn.zalopay.ui.util.a.b = 1;
                    return;
                } catch (Throwable v0_4) {
                    if (v4_1 != null) {
                        try {
                            v4_1.close();
                        } catch (Exception v4_2) {
                            v4_2.getMessage();
                        }
                    }
                    throw v0_4;
                }
            } catch (Throwable v0_4) {
                v4_1 = 0;
            }
        } else {
            return;
        }
    }
}
