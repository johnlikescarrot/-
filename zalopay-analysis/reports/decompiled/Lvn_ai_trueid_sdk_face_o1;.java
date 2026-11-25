package vn.ai.trueid.sdk.face;
public final class o1 extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function1 {
    public final synthetic vn.ai.trueid.sdk.face.a2 a;
    public final synthetic android.graphics.Bitmap b;
    public final synthetic androidx.camera.core.d c;

    public o1(vn.ai.trueid.sdk.face.a2 p1, android.graphics.Bitmap p2, androidx.camera.core.d p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        super(1);
        return;
    }

    public final Object invoke(Object p4)
    {
        vn.ai.trueid.sdk.face.a2.a(this.a, ((java.util.List) p4), this.b, this.c);
        return kotlin.Unit.INSTANCE;
    }
}
