package vn.ai.faceauth.sdk;
public final class g implements java.util.concurrent.Executor {
    public final java.util.concurrent.Executor a;
    public final java.util.concurrent.atomic.AtomicBoolean b;

    public static synthetic void $r8$lambda$kaGeA11EhKV80NjARvHBBifc8Eo(vn.ai.faceauth.sdk.g p0, Runnable p1)
    {
        p0.a(p1);
        return;
    }

    static g()
    {
        xq.vinxp.ffcdedcddfafbebfdacaaaab(22);
        return;
    }

    public g(java.util.concurrent.Executor p2)
    {
        this.b = new java.util.concurrent.atomic.AtomicBoolean();
        this.a = p2;
        return;
    }

    private synthetic void a(Runnable p2)
    {
        if (!this.b.get()) {
            p2.run();
            return;
        } else {
            return;
        }
    }

    public final native void execute(Runnable p0);
}
