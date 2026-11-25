package vn.ai.trueid.sdk.face;
public final class g extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function1 {
    public static final vn.ai.trueid.sdk.face.g a;

    public static g()
    {
        vn.ai.trueid.sdk.face.g.a = new vn.ai.trueid.sdk.face.g();
        return;
    }

    public g()
    {
        super(1);
        return;
    }

    public final Object invoke(Object p8)
    {
        if (((String) p8).length() > 4096) {
            int v0_2 = (((String) p8).length() / 4096);
            int v2_0 = Math.min(1, v0_2);
            StringBuilder v3_1 = new StringBuilder();
            int v4 = 0;
            while (v4 < 4096) {
                String v5_0 = (v4 * v0_2);
                v3_1.append(((String) p8).substring(v5_0, (v5_0 + v2_0)));
                v4++;
            }
            return v3_1.toString();
        } else {
            return ((String) p8);
        }
    }
}
