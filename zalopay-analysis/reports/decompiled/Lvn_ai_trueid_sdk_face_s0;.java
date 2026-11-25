package vn.ai.trueid.sdk.face;
public final class s0 implements androidx.lifecycle.r0 {
    public final vn.ai.trueid.sdk.face.c1 a;
    public final vn.ai.trueid.sdk.face.q0 b;
    public final vn.ai.trueid.sdk.face.c0 c;

    public synthetic s0()
    {
        vn.ai.trueid.sdk.face.c1 v0_1 = vn.ai.trueid.sdk.face.g0.a().b();
        vn.ai.trueid.sdk.face.g0.a().getClass();
        this(v0_1, vn.ai.trueid.sdk.face.j0.a(), vn.ai.trueid.sdk.face.j0.a(vn.ai.trueid.sdk.face.g0.a()));
        return;
    }

    public s0(vn.ai.trueid.sdk.face.c1 p1, vn.ai.trueid.sdk.face.q0 p2, vn.ai.trueid.sdk.face.c0 p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        return;
    }

    public final androidx.lifecycle.ViewModel create(Class p4)
    {
        if (!p4.isAssignableFrom(vn.ai.trueid.sdk.face.r0)) {
            throw new IllegalArgumentException(wmoi.igob.ggcn(13540));
        } else {
            return new vn.ai.trueid.sdk.face.r0(this.a, this.b, this.c);
        }
    }

    public androidx.lifecycle.ViewModel create(Class p1, S2.c p2)
    {
        return this.create(p1);
    }

    public bridge synthetic androidx.lifecycle.ViewModel create(kotlin.reflect.d p1, S2.c p2)
    {
        return super.create(p1, p2);
    }
}
