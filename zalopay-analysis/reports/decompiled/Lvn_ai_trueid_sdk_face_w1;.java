package vn.ai.trueid.sdk.face;
public final class w1 extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function1 {
    public final synthetic vn.ai.trueid.sdk.face.a2 a;

    public w1(vn.ai.trueid.sdk.face.a2 p1)
    {
        this.a = p1;
        super(1);
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a2 p4)
    {
        boolean v0_0 = p4.c();
        int v1_1 = v0_0.b;
        if (v1_1 != 0) {
            int v1_2 = v1_1.i;
            if (v1_2 != 0) {
                v0_0.a();
                boolean v0_8 = v1_2.c;
                if (v0_8) {
                    v0_8.setColor(android.graphics.Color.parseColor(v1_2.n));
                }
                v1_2.invalidate();
            }
        }
        p4.c.d.setVisibility(0);
        boolean v0_4 = p4.c.i;
        int v2_1 = v0_4.c;
        if (v2_1 != 0) {
            v2_1.setColor(android.graphics.Color.parseColor(v0_4.o));
        }
        v0_4.invalidate();
        if (p4.c.c.isEnabled()) {
            p4.c.c.setVisibility(0);
        }
        return;
    }

    public final void a(String p5)
    {
        android.util.Log.e(this.a.b, "call FaceSDK.showError");
        this.a.c().b();
        if (p5 == null) {
            p5 = "";
        }
        this.a.a(p5);
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new vn.ai.trueid.sdk.face.D(this.a, 7), 500);
        return;
    }

    public final bridge synthetic Object invoke(Object p1)
    {
        this.a(((String) p1));
        return kotlin.Unit.INSTANCE;
    }
}
