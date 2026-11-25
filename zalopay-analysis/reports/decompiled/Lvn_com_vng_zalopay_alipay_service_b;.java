package vn.com.vng.zalopay.alipay.service;
public final synthetic class b implements kotlin.jvm.functions.Function0 {
    public final synthetic int a;
    public final synthetic fu.j b;

    public synthetic b(fu.j p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final Object invoke()
    {
        switch (this.a) {
            case 0:
                return vn.com.vng.zalopay.alipay.service.MpmServiceProvider.a(this.b);
            default:
                return vn.com.vng.zalopay.alipay.service.MpmServiceProvider.c(this.b);
        }
    }
}
