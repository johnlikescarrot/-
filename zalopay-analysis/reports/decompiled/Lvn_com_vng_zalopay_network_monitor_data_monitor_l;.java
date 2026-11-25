package vn.com.vng.zalopay.network_monitor.data.monitor;
public final class l implements kotlin.jvm.functions.Function1 {
    public final synthetic int a;
    public final synthetic okhttp3.Call b;

    public synthetic l(okhttp3.Call p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final Object invoke(Object p2)
    {
        switch (this.a) {
            case 0:
                this.b.cancel();
                return kotlin.Unit.INSTANCE;
            default:
                this.b.cancel();
                return kotlin.Unit.INSTANCE;
        }
    }
}
