package vn.com.vng.zalopay.miniapp.api.v2.visa;
public abstract class a {

    public static void a(String p2)
    {
        if (kotlin.jvm.internal.Intrinsics.areEqual(vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.f, p2)) {
            vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.e = 0;
            vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.f = 0;
        }
        return;
    }

    public static void b(String p4, vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService$a p5)
    {
        try {
            int v4_2;
            vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.e = p5;
            vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.f = p4;
            int v4_3 = vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService.g;
        } catch (int v4_4) {
            throw v4_4;
        }
        if (v4_3 == 0) {
            v4_2 = 0;
        } else {
            v4_2 = ((vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService) v4_3.get());
        }
        if (v4_2 != 0) {
            android.os.Bundle v0_2 = ((IF.b) v4_2.c.getAndSet(0));
            if (v0_2 != null) {
                p5.b(v0_2.a, v0_2.b, new IF.a(v4_2, 1));
            }
        }
        return;
    }
}
