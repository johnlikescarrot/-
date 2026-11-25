package vn.com.vng.zalopay.internal;
public interface d {

    public abstract int a();

    public abstract boolean b();

    public abstract void c(android.app.Application p0);

    public void d(android.app.Application p3)
    {
        com.google.firebase.perf.metrics.Trace v0_1 = this.getClass().getSimpleName();
        Pb.b.a().getClass();
        com.google.firebase.perf.metrics.Trace v0_2 = Pb.b.b(v0_1);
        v0_2.start();
        try {
            this.c(p3);
            v0_2.stop();
            return;
        } catch (Throwable v3_1) {
            v0_2.stop();
            throw v3_1;
        }
    }
}
