package vn.ai.trueid.sdk.face;
public final synthetic class r implements java.lang.Runnable {
    public final synthetic int a;
    public final synthetic vn.ai.trueid.sdk.face.a0 b;

    public synthetic r(vn.ai.trueid.sdk.face.a0 p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final void run()
    {
        switch (this.a) {
            case 0:
                vn.ai.trueid.sdk.face.a0.d(this.b);
                return;
            case 1:
                vn.ai.trueid.sdk.face.a0.b(this.b);
                return;
            case 2:
                vn.ai.trueid.sdk.face.a0.a(this.b);
                return;
            case 3:
                vn.ai.trueid.sdk.face.a0.c(this.b);
                return;
            case 4:
                vn.ai.trueid.sdk.face.u.a(this.b);
                return;
            case 5:
                vn.ai.trueid.sdk.face.w.a(this.b);
                return;
            default:
                vn.ai.trueid.sdk.face.x.a(this.b);
                return;
        }
    }
}
