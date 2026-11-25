package vn.ai.trueid.sdk.face;
public final class c {
    public static final vn.ai.trueid.sdk.face.c a;
    public static volatile android.app.Application b;

    public static c()
    {
        vn.ai.trueid.sdk.face.c.a = new vn.ai.trueid.sdk.face.c();
        ng.x.b(vn.ai.trueid.sdk.face.b.a);
        return;
    }

    public c()
    {
        return;
    }

    public final void a(android.app.Application p2)
    {
        if (vn.ai.trueid.sdk.face.c.b != null) {
            return;
        } else {
            try {
                vn.ai.trueid.sdk.face.c.b = p2;
                return;
            } catch (Throwable v2_1) {
                throw v2_1;
            }
        }
    }
}
