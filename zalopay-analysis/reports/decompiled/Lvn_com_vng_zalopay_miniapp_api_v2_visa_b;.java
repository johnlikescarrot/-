package vn.com.vng.zalopay.miniapp.api.v2.visa;
public final class b implements vn.com.vng.zalopay.miniapp.api.v2.visa.VisaHostApduService$a {
    public final synthetic vn.com.vng.zalopay.miniapp.api.v2.visa.c a;

    public b(vn.com.vng.zalopay.miniapp.api.v2.visa.c p1)
    {
        this.a = p1;
        return;
    }

    public final void a(int p3)
    {
        try {
            vn.com.vng.zalopay.miniapp.api.v2.visa.c.d(this.a).execute(new IF.g(this, p3));
            Throwable v3_1 = kotlin.Unit.INSTANCE;
        } catch (Throwable v3_3) {
            v3_1 = kotlin.ResultKt.createFailure(v3_3);
        }
        Throwable v3_2 = ng.L.a(v3_1);
        if (v3_2 != null) {
            Object[] v0_2 = new Object[0];
            ck.b.c(v3_2, "Visa Web APDU deactivation dispatch failed", v0_2);
        }
        return;
    }

    public final void b(byte[] p8, android.os.Bundle p9, kotlin.jvm.functions.Function1 p10)
    {
        byte[] v8_6;
        byte[] v4 = java.util.Arrays.copyOf(p8, p8.length);
        if (p9 == null) {
            v8_6 = 0;
        } else {
            v8_6 = new android.os.Bundle(p9);
        }
        try {
            kotlin.jvm.functions.Function1 v3 = p10;
            try {
                vn.com.vng.zalopay.miniapp.api.v2.visa.c.d(this.a).execute(new IF.h(this, p10, v4, v8_6, 0));
                byte[] v8_2 = kotlin.Unit.INSTANCE;
            } catch (Throwable v0_1) {
                v8_2 = kotlin.ResultKt.createFailure(v0_1);
            }
            byte[] v8_4 = ng.L.a(v8_2);
            if (v8_4 != null) {
                Object[] v9_2 = new Object[0];
                ck.b.c(v8_4, "Visa Web APDU dispatch failed", v9_2);
                v3.invoke(vn.com.vng.zalopay.miniapp.api.v2.visa.c.e());
            }
            return;
        } catch (Throwable v0_1) {
            v3 = p10;
        }
    }
}
