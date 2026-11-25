package vn.com.vng.zalopay.authentication.secret;
public final class a {
    public static final vn.com.vng.zalopay.authentication.secret.c b;
    public final Pt.n a;

    static a()
    {
        vn.com.vng.zalopay.authentication.secret.a.b = new vn.com.vng.zalopay.authentication.secret.c();
        return;
    }

    public a()
    {
        Pt.n v0_0 = vn.com.vng.zalopay.AndroidApplication.g;
        CC.f v1 = 0;
        if (v0_0 == null) {
            v0_0 = 0;
        }
        Pt.n v0_1 = v0_0.a;
        if (v0_1 != null) {
            v1 = v0_1;
        }
        this.a = v1.D();
        return;
    }

    public static String a(javax.crypto.Cipher p2)
    {
        String v0_0 = vn.com.vng.zalopay.authentication.secret.a.b;
        if (v0_0 != null) {
            try {
                if (p2 != 0) {
                    return new String(p2.doFinal(android.util.Base64.decode(v0_0.b.f(), 0)), java.nio.charset.Charset.defaultCharset());
                } else {
                    return 0;
                }
            } catch (Exception) {
                return 0;
            }
        } else {
            return "";
        }
    }

    public static javax.crypto.Cipher b(int p1, String p2)
    {
        vn.com.vng.zalopay.authentication.secret.c v0 = vn.com.vng.zalopay.authentication.secret.a.b;
        if (v0 != null) {
            return v0.b(p1, p2);
        } else {
            return 0;
        }
    }

    public final void c()
    {
        Pt.n v0 = this.a;
        if (v0 != null) {
            v0.C();
            return;
        } else {
            return;
        }
    }

    public final void d(String p5)
    {
        Bw.b v0_2 = this.a.f();
        if ((!android.text.TextUtils.isEmpty(p5)) && (!p5.equals(v0_2))) {
            Bw.b v0_9 = vn.com.vng.zalopay.authentication.secret.a.b;
            if (v0_9 != null) {
                Bw.b v0_1 = v0_9.a;
                v0_1.load(0);
            } else {
                return;
            }
        }
        return;
    }
}
