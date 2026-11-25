package vn.ai.trueid.sdk.networks;
public final class z extends kotlin.jvm.internal.Lambda implements kotlin.jvm.functions.Function0 {
    public final synthetic String d;
    public final synthetic java.util.concurrent.atomic.AtomicInteger e;
    public final synthetic int f;
    public final synthetic kotlin.jvm.functions.Function0 g;

    public z(String p1, java.util.concurrent.atomic.AtomicInteger p2, int p3, kotlin.jvm.functions.Function0 p4)
    {
        this.d = p1;
        this.e = p2;
        this.f = p3;
        this.g = p4;
        super(0);
        return;
    }

    public final Object invoke()
    {
        kotlin.Unit v0_0 = this.d;
        kotlin.jvm.functions.Function0 v1_1 = v0_0.hashCode();
        if (v1_1 == -859619302) {
            if (v0_0.equals("imageMRZ")) {
                fk.b.t = 0;
            }
        } else {
            if (v1_1 == -859618736) {
                if (v0_0.equals("imageNFC")) {
                    fk.b.s = 0;
                }
            } else {
                if ((v1_1 == 1911933116) && (v0_0.equals("imageQR"))) {
                    fk.b.u = 0;
                }
            }
        }
        vn.ai.trueid.sdk.networks.B.a(this.e, this.f, this.g);
        return kotlin.Unit.INSTANCE;
    }
}
