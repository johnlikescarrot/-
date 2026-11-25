package vn.ai.trueid.sdk.face;
public final class w extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function1 {
    public final synthetic vn.ai.trueid.sdk.face.a0 a;

    public w(vn.ai.trueid.sdk.face.a0 p1)
    {
        this.a = p1;
        super(1);
        return;
    }

    public static final void a(vn.ai.trueid.sdk.face.a0 p4)
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

    public final void a(String p7)
    {
        android.util.Log.e(this.a.b, wmoi.igob.ggcn(14950));
        this.a.c().b();
        vn.ai.trueid.sdk.face.a0 v0_2 = this.a;
        if (p7 == null) {
            p7 = "";
        }
        v0_2.c.k.setVisibility(0);
        vn.ai.trueid.sdk.face.r v1_2 = v0_2.c;
        vn.ai.trueid.sdk.face.navigation.FaceSDK v3 = vn.ai.trueid.sdk.face.navigation.FaceSDK.INSTANCE;
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.bindHtmlToString(v1_2.k, vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(v1_2.l.getContext(), wmoi.igob.ggcn(14951), v3.getLanguageJSON()));
        vn.ai.trueid.sdk.face.r v1_8 = v0_2.c.l;
        if (p7.length() <= 0) {
            p7 = vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.getTextByKey(v0_2.c.l.getContext(), wmoi.igob.ggcn(14952), v3.getLanguageJSON());
        }
        vn.ai.trueid.sdk.face.present.presentation.utils.UtilsKt.bindHtmlToString(v1_8, p7);
        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new vn.ai.trueid.sdk.face.r(this.a, 5), 500);
        return;
    }

    public final bridge synthetic Object invoke(Object p1)
    {
        this.a(((String) p1));
        return kotlin.Unit.INSTANCE;
    }
}
