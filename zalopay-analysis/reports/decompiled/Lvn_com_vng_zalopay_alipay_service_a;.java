package vn.com.vng.zalopay.alipay.service;
public final synthetic class a implements kotlin.jvm.functions.Function1 {
    public final synthetic int a;
    public final synthetic com.alipay.iap.android.wallet.acl.base.Callback b;

    public synthetic a(com.alipay.iap.android.wallet.acl.base.Callback p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final Object invoke(Object p2)
    {
        switch (this.a) {
            case 0:
                return vn.com.vng.zalopay.alipay.service.CodeServiceProvider.a(this.b, ((String) p2));
            default:
                return vn.com.vng.zalopay.alipay.service.OAuthServiceProvider.a(this.b, ((Boolean) p2).booleanValue());
        }
    }
}
