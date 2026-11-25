package vn.zalopay.ui.androidsvg;
public abstract class I0 {
    public static final java.util.HashMap a;

    static I0()
    {
        java.util.HashMap v0_1 = new java.util.HashMap(13);
        vn.zalopay.ui.androidsvg.I0.a = v0_1;
        Integer v1_6 = Integer.valueOf(400);
        v0_1.put("normal", v1_6);
        String v2_0 = Integer.valueOf(700);
        v0_1.put("bold", v2_0);
        androidx.compose.foundation.layout.G0.s(1, v0_1, "bolder", -1, "lighter");
        androidx.compose.foundation.layout.G0.s(100, v0_1, "100", 200, "200");
        v0_1.put("300", Integer.valueOf(300));
        v0_1.put("400", v1_6);
        androidx.compose.foundation.layout.G0.s(500, v0_1, "500", 600, "600");
        androidx.compose.foundation.layout.G0.x(v0_1, "700", v2_0, 800, "800");
        v0_1.put("900", Integer.valueOf(900));
        return;
    }
}
