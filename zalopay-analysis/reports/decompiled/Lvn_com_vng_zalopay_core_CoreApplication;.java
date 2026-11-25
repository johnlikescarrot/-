package vn.com.vng.zalopay.core;
public abstract class CoreApplication extends com.google.android.play.core.splitcompat.SplitCompatApplication {

    public CoreApplication()
    {
        return;
    }

    public abstract void b();

    public abstract void c();

    public abstract void d();

    public abstract void e();

    public void onCreate()
    {
        super.onCreate();
        this.b();
        this.c();
        this.d();
        this.e();
        return;
    }

    public final void onLowMemory()
    {
        super.onLowMemory();
        d6.q v0_0 = e6.p.p;
        z5.i.d(v0_0, wmoi.igob.ggcn(2782));
        d6.q v0_2 = v0_0.e();
        com.google.gson.internal.c v1_0 = new com.google.gson.internal.c(8);
        v0_2.f.f(v1_0);
        v0_2.g.f(v1_0);
        return;
    }
}
