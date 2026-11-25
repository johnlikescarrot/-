package vn.zalopay.ui.androidsvg;
public final enum class d extends java.lang.Enum {
    public static final enum vn.zalopay.ui.androidsvg.d a;
    public static final enum vn.zalopay.ui.androidsvg.d b;
    public static final synthetic vn.zalopay.ui.androidsvg.d[] c;

    static d()
    {
        vn.zalopay.ui.androidsvg.d[] v0_1 = new vn.zalopay.ui.androidsvg.d("all", 0);
        vn.zalopay.ui.androidsvg.d.a = v0_1;
        new vn.zalopay.ui.androidsvg.d("aural", 1);
        new vn.zalopay.ui.androidsvg.d("braille", 2);
        new vn.zalopay.ui.androidsvg.d("embossed", 3);
        new vn.zalopay.ui.androidsvg.d("handheld", 4);
        new vn.zalopay.ui.androidsvg.d("print", 5);
        new vn.zalopay.ui.androidsvg.d("projection", 6);
        vn.zalopay.ui.androidsvg.d.b = new vn.zalopay.ui.androidsvg.d("screen", 7);
        new vn.zalopay.ui.androidsvg.d("speech", 8);
        new vn.zalopay.ui.androidsvg.d("tty", 9);
        vn.zalopay.ui.androidsvg.d.c = new vn.zalopay.ui.androidsvg.d[] {v0_1, new vn.zalopay.ui.androidsvg.d("tv", 10)});
        return;
    }

    public static vn.zalopay.ui.androidsvg.d valueOf(String p1)
    {
        return ((vn.zalopay.ui.androidsvg.d) Enum.valueOf(vn.zalopay.ui.androidsvg.d, p1));
    }

    public static vn.zalopay.ui.androidsvg.d[] values()
    {
        return ((vn.zalopay.ui.androidsvg.d[]) vn.zalopay.ui.androidsvg.d.c.clone());
    }
}
