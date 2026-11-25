package vn.zalopay.ui.androidsvg;
public final enum class h extends java.lang.Enum {
    public static final enum vn.zalopay.ui.androidsvg.h a;
    public static final enum vn.zalopay.ui.androidsvg.h b;
    public static final enum vn.zalopay.ui.androidsvg.h c;
    public static final enum vn.zalopay.ui.androidsvg.h d;
    public static final java.util.HashMap e;
    public static final synthetic vn.zalopay.ui.androidsvg.h[] f;

    static h()
    {
        vn.zalopay.ui.androidsvg.h[] v1_11 = new vn.zalopay.ui.androidsvg.h("target", 0);
        new vn.zalopay.ui.androidsvg.h("root", 1);
        vn.zalopay.ui.androidsvg.h.a = new vn.zalopay.ui.androidsvg.h("nth_child", 2);
        new vn.zalopay.ui.androidsvg.h("nth_last_child", 3);
        vn.zalopay.ui.androidsvg.h.b = new vn.zalopay.ui.androidsvg.h("nth_of_type", 4);
        vn.zalopay.ui.androidsvg.h.c = new vn.zalopay.ui.androidsvg.h("nth_last_of_type", 5);
        new vn.zalopay.ui.androidsvg.h("first_child", 6);
        new vn.zalopay.ui.androidsvg.h("last_child", 7);
        new vn.zalopay.ui.androidsvg.h("first_of_type", 8);
        new vn.zalopay.ui.androidsvg.h("last_of_type", 9);
        new vn.zalopay.ui.androidsvg.h("only_child", 10);
        new vn.zalopay.ui.androidsvg.h("only_of_type", 11);
        new vn.zalopay.ui.androidsvg.h("empty", 12);
        new vn.zalopay.ui.androidsvg.h("not", 13);
        vn.zalopay.ui.androidsvg.h v17 = v1_11;
        new vn.zalopay.ui.androidsvg.h("lang", 14);
        new vn.zalopay.ui.androidsvg.h("visited", 16);
        new vn.zalopay.ui.androidsvg.h("active", 18);
        new vn.zalopay.ui.androidsvg.h("indeterminate", 23);
        vn.zalopay.ui.androidsvg.h[] v1_5 = new vn.zalopay.ui.androidsvg.h("UNSUPPORTED", 24);
        vn.zalopay.ui.androidsvg.h.d = v1_5;
        vn.zalopay.ui.androidsvg.h v16 = new vn.zalopay.ui.androidsvg.h("link", 15);
        vn.zalopay.ui.androidsvg.h v18 = new vn.zalopay.ui.androidsvg.h("hover", 17);
        vn.zalopay.ui.androidsvg.h v21 = new vn.zalopay.ui.androidsvg.h("enabled", 20);
        vn.zalopay.ui.androidsvg.h v22 = new vn.zalopay.ui.androidsvg.h("disabled", 21);
        int v0_5 = 0;
        vn.zalopay.ui.androidsvg.h v20 = new vn.zalopay.ui.androidsvg.h("focus", 19);
        vn.zalopay.ui.androidsvg.h v23 = new vn.zalopay.ui.androidsvg.h("checked", 22);
        vn.zalopay.ui.androidsvg.h.f = new vn.zalopay.ui.androidsvg.h[] {v17, v1_5});
        vn.zalopay.ui.androidsvg.h.e = new java.util.HashMap();
        vn.zalopay.ui.androidsvg.h[] v1_10 = vn.zalopay.ui.androidsvg.h.values();
        int v2_3 = v1_10.length;
        while (v0_5 < v2_3) {
            vn.zalopay.ui.androidsvg.h v3_1 = v1_10[v0_5];
            if (v3_1 != vn.zalopay.ui.androidsvg.h.d) {
                vn.zalopay.ui.androidsvg.h.e.put(v3_1.name().replace(95, 45), v3_1);
            }
            v0_5++;
        }
        return;
    }

    public static vn.zalopay.ui.androidsvg.h valueOf(String p1)
    {
        return ((vn.zalopay.ui.androidsvg.h) Enum.valueOf(vn.zalopay.ui.androidsvg.h, p1));
    }

    public static vn.zalopay.ui.androidsvg.h[] values()
    {
        return ((vn.zalopay.ui.androidsvg.h[]) vn.zalopay.ui.androidsvg.h.f.clone());
    }
}
