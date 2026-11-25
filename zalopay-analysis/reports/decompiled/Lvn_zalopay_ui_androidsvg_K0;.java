package vn.zalopay.ui.androidsvg;
public final enum class K0 extends java.lang.Enum {
    public static final enum vn.zalopay.ui.androidsvg.K0 a;
    public static final enum vn.zalopay.ui.androidsvg.K0 b;
    public static final enum vn.zalopay.ui.androidsvg.K0 c;
    public static final enum vn.zalopay.ui.androidsvg.K0 d;
    public static final java.util.HashMap e;
    public static final synthetic vn.zalopay.ui.androidsvg.K0[] f;

    static K0()
    {
        vn.zalopay.ui.androidsvg.K0[] v1_20 = new vn.zalopay.ui.androidsvg.K0("svg", 0);
        new vn.zalopay.ui.androidsvg.K0("a", 1);
        new vn.zalopay.ui.androidsvg.K0("circle", 2);
        new vn.zalopay.ui.androidsvg.K0("clipPath", 3);
        new vn.zalopay.ui.androidsvg.K0("defs", 4);
        vn.zalopay.ui.androidsvg.K0.a = new vn.zalopay.ui.androidsvg.K0("desc", 5);
        new vn.zalopay.ui.androidsvg.K0("ellipse", 6);
        new vn.zalopay.ui.androidsvg.K0("g", 7);
        new vn.zalopay.ui.androidsvg.K0("image", 8);
        new vn.zalopay.ui.androidsvg.K0("line", 9);
        new vn.zalopay.ui.androidsvg.K0("linearGradient", 10);
        new vn.zalopay.ui.androidsvg.K0("marker", 11);
        new vn.zalopay.ui.androidsvg.K0("mask", 12);
        new vn.zalopay.ui.androidsvg.K0("path", 13);
        vn.zalopay.ui.androidsvg.K0 v17 = v1_20;
        new vn.zalopay.ui.androidsvg.K0("pattern", 14);
        new vn.zalopay.ui.androidsvg.K0("radialGradient", 17);
        new vn.zalopay.ui.androidsvg.K0("stop", 20);
        vn.zalopay.ui.androidsvg.K0[] v1_1 = new vn.zalopay.ui.androidsvg.K0("SWITCH", 22);
        vn.zalopay.ui.androidsvg.K0.b = v1_1;
        vn.zalopay.ui.androidsvg.K0 v26 = v1_1;
        new vn.zalopay.ui.androidsvg.K0("symbol", 23);
        vn.zalopay.ui.androidsvg.K0.c = new vn.zalopay.ui.androidsvg.K0("title", 26);
        new vn.zalopay.ui.androidsvg.K0("use", 29);
        int v0_15 = new vn.zalopay.ui.androidsvg.K0("UNSUPPORTED", 31);
        vn.zalopay.ui.androidsvg.K0.d = v0_15;
        vn.zalopay.ui.androidsvg.K0[] v1_15 = v17;
        vn.zalopay.ui.androidsvg.K0 v16 = new vn.zalopay.ui.androidsvg.K0("polygon", 15);
        v17 = new vn.zalopay.ui.androidsvg.K0("polyline", 16);
        vn.zalopay.ui.androidsvg.K0 v19 = new vn.zalopay.ui.androidsvg.K0("rect", 18);
        vn.zalopay.ui.androidsvg.K0 v20 = new vn.zalopay.ui.androidsvg.K0("solidColor", 19);
        vn.zalopay.ui.androidsvg.K0 v22 = new vn.zalopay.ui.androidsvg.K0("style", 21);
        vn.zalopay.ui.androidsvg.K0 v23 = v26;
        vn.zalopay.ui.androidsvg.K0 v25 = new vn.zalopay.ui.androidsvg.K0("text", 24);
        v26 = new vn.zalopay.ui.androidsvg.K0("textPath", 25);
        vn.zalopay.ui.androidsvg.K0 v28 = new vn.zalopay.ui.androidsvg.K0("tref", 27);
        vn.zalopay.ui.androidsvg.K0 v29 = new vn.zalopay.ui.androidsvg.K0("tspan", 28);
        vn.zalopay.ui.androidsvg.K0 v31 = new vn.zalopay.ui.androidsvg.K0("view", 30);
        int v0_16 = 0;
        vn.zalopay.ui.androidsvg.K0.f = new vn.zalopay.ui.androidsvg.K0[] {v1_15, v0_15});
        vn.zalopay.ui.androidsvg.K0.e = new java.util.HashMap();
        vn.zalopay.ui.androidsvg.K0[] v1_19 = vn.zalopay.ui.androidsvg.K0.values();
        int v2_9 = v1_19.length;
        while (v0_16 < v2_9) {
            vn.zalopay.ui.androidsvg.K0 v3_0 = v1_19[v0_16];
            if (v3_0 != vn.zalopay.ui.androidsvg.K0.b) {
                if (v3_0 != vn.zalopay.ui.androidsvg.K0.d) {
                    vn.zalopay.ui.androidsvg.K0.e.put(v3_0.name(), v3_0);
                }
            } else {
                vn.zalopay.ui.androidsvg.K0.e.put("switch", v3_0);
            }
            v0_16++;
        }
        return;
    }

    public static vn.zalopay.ui.androidsvg.K0 valueOf(String p1)
    {
        return ((vn.zalopay.ui.androidsvg.K0) Enum.valueOf(vn.zalopay.ui.androidsvg.K0, p1));
    }

    public static vn.zalopay.ui.androidsvg.K0[] values()
    {
        return ((vn.zalopay.ui.androidsvg.K0[]) vn.zalopay.ui.androidsvg.K0.f.clone());
    }
}
