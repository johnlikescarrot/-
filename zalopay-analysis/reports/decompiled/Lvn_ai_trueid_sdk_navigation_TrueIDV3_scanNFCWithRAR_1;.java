package vn.ai.trueid.sdk.navigation;
public final class TrueIDV3$scanNFCWithRAR$1 implements Nk.d {
    public final synthetic Ek.n a;
    public final synthetic vn.ai.trueid.sdk.navigation.NFCListener b;
    public final synthetic android.app.Activity c;

    public TrueIDV3$scanNFCWithRAR$1(Ek.n p1, vn.ai.trueid.sdk.navigation.NFCListener p2, android.app.Activity p3)
    {
        this.a = p1;
        this.b = p2;
        this.c = p3;
        return;
    }

    public void complete(int p18, String p19, Nk.b p20, int p21)
    {
        vn.ai.trueid.sdk.navigation.NFCListener v5 = this.b;
        Ek.n v6 = this.a;
        if (p18 != 300) {
            android.app.Activity v8_0 = this.c;
            if ((p18 == 209) || (p18 == 301)) {
                android.os.Handler v1_4 = vn.ai.trueid.sdk.utils.j.e(v8_0, "trueidsdk_nfc_request", vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getLanguageJSON());
                v6.getClass();
                v8_0.runOnUiThread(new Ek.m(v6, v1_4, 0));
                return;
            } else {
                if ((p18 == 204) || ((p18 == 205) || (p18 == 207))) {
                    android.os.Handler v1_8 = vn.ai.trueid.sdk.utils.j.e(v8_0, "trueidsdk_nfc_request", vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.getLanguageJSON()).toString();
                    v6.getClass();
                    v8_0.runOnUiThread(new Ek.m(v6, v1_8, 2));
                    new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new Kk.i(v6, v5, 1), 600);
                    return;
                } else {
                    if ((1 <= p21) && (p21 < 11)) {
                        Bk.v v3_4 = (p21 * 10);
                        v6.getClass();
                        v8_0.runOnUiThread(new Be.a(v6, v3_4, 1));
                    }
                    if ((p18 == 1) && (p20 != null)) {
                        v6.getClass();
                        v8_0.runOnUiThread(new Be.a(v6, 100, 1));
                        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new Kk.f(v6, v8_0, 1), 600);
                        new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new Bk.v(p20, v6, v5, 8), 1500);
                    }
                    return;
                }
            }
        } else {
            android.os.Handler v1_17 = vn.ai.trueid.sdk.models.TrueError.Companion;
            fk.c.a(v1_17.getUSER_CANCELLED().getCode(), 0, 0, 0);
            v6.o1(0, 0);
            v5.onComplete(new vn.ai.trueid.sdk.models.NfcResult(v1_17.getUSER_CANCELLED().getCode(), v1_17.getUSER_CANCELLED().getMessage(), 0, 0, 0, 0, 0, 124, 0));
            return;
        }
    }
}
