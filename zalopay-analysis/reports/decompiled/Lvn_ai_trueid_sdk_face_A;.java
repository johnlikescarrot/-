package vn.ai.trueid.sdk.face;
public final synthetic class A implements android.view.View$OnClickListener {
    public final synthetic int a;
    public final synthetic vn.ai.trueid.sdk.face.a0 b;

    public synthetic A(vn.ai.trueid.sdk.face.a0 p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final void onClick(android.view.View p2)
    {
        switch (this.a) {
            case 0:
                vn.ai.trueid.sdk.face.a0.a(this.b, p2);
                return;
            case 1:
                vn.ai.trueid.sdk.face.a0.b(this.b, p2);
                return;
            default:
                vn.ai.trueid.sdk.face.a0.c(this.b, p2);
                return;
        }
    }
}
