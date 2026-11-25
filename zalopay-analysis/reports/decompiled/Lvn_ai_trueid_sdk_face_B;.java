package vn.ai.trueid.sdk.face;
public final synthetic class B implements android.view.View$OnClickListener {
    public final synthetic int a;
    public final synthetic vn.ai.trueid.sdk.face.a1 b;

    public synthetic B(vn.ai.trueid.sdk.face.a1 p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final void onClick(android.view.View p2)
    {
        switch (this.a) {
            case 0:
                vn.ai.trueid.sdk.face.a1.a(this.b, p2);
                return;
            default:
                vn.ai.trueid.sdk.face.a1.b(this.b, p2);
                return;
        }
    }
}
