package vn.com.vng.zalopay.alipay.service;
public final class CodeServiceProvider implements com.alipay.iap.android.wallet.foundation.code.CodeService {
    public static final int $stable;

    public CodeServiceProvider()
    {
        return;
    }

    public static synthetic kotlin.Unit a(com.alipay.iap.android.wallet.acl.base.Callback p0, String p1)
    {
        return vn.com.vng.zalopay.alipay.service.CodeServiceProvider.scan$lambda$0(p0, p1);
    }

    private static final kotlin.Unit scan$lambda$0(com.alipay.iap.android.wallet.acl.base.Callback p1, String p2)
    {
        if (p1 != null) {
            p1.result(new com.alipay.iap.android.wallet.foundation.code.ScannerResult(p2));
        }
        return kotlin.Unit.INSTANCE;
    }

    public android.graphics.Bitmap generateCodeImage(com.alipay.iap.android.wallet.foundation.code.CodeImageOption p2, com.alipay.iap.android.wallet.acl.base.APIContext p3)
    {
        return android.graphics.Bitmap.createBitmap(200, 100, android.graphics.Bitmap$Config.ARGB_8888);
    }

    public void scan(com.alipay.iap.android.wallet.foundation.code.ScannerOption p1, com.alipay.iap.android.wallet.acl.base.APIContext p2, com.alipay.iap.android.wallet.acl.base.Callback p3)
    {
        android.content.Intent v1_3 = new vn.com.vng.zalopay.alipay.service.a(p3, 0);
        String v2_1 = Em.c.b();
        if (v2_1 == null) {
            v1_3.invoke("");
            return;
        } else {
            com.google.android.gms.internal.mlkit_vision_barcode.p5.b = v1_3;
            android.content.Intent v1_2 = new android.content.Intent(v2_1, vn.com.vng.zalopay.alipay.presentation.AlipayEmptyScanQRActivity);
            v1_2.addFlags(65536);
            v2_1.startActivity(v1_2);
            return;
        }
    }
}
