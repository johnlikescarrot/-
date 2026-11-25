package vn.com.vng.zalopay.internal;
public final synthetic class g implements kotlin.jvm.functions.Function1 {
    public final synthetic int a;
    public final synthetic android.app.Application b;

    public synthetic g(android.app.Application p1, int p2)
    {
        this.a = p2;
        this.b = p1;
        return;
    }

    public final Object invoke(Object p5)
    {
        switch (this.a) {
            case 0:
                try {
                    hs.d.h(com.google.android.gms.internal.mlkit_vision_barcode.m0.c(this.b.getAssets(), "config/zalopay_config.json"));
                } catch (Exception) {
                }
                return kotlin.Unit.INSTANCE;
            case 1:
                byte[] v0_17;
                if (((Boolean) p5).booleanValue()) {
                    String v5_10 = this.b.getAssets();
                    nY.n v2_1 = hs.d.b(((long) 2));
                    java.util.Locale.getDefault();
                    byte[] v0_11 = androidx.activity.result.e.n(n9.e0.c(((long) 2)), "/config/signature.txt");
                    byte[] v1_3 = com.google.android.gms.internal.mlkit_vision_barcode.m0.d(v2_1);
                    byte[] v0_12 = com.google.android.gms.internal.mlkit_vision_barcode.m0.d(v0_11);
                    boolean v3 = 0;
                    try {
                        if ((!android.text.TextUtils.isEmpty(v1_3)) && (!android.text.TextUtils.isEmpty(v0_12))) {
                            String v5_2 = com.google.android.gms.internal.mlkit_vision_barcode.m0.c(v5_10, "config/public_key.txt");
                            if (!android.text.TextUtils.isEmpty(v5_2)) {
                                byte[] v1_7 = com.google.android.gms.internal.mlkit_vision_barcode.z0.b(nY.y.e("|", new String[] {v1_3}))).getBytes();
                                byte[] v0_15 = android.util.Base64.decode(v0_12.getBytes(), 0);
                                if (nY.n.c == null) {
                                    nY.n.c = new nY.n();
                                }
                                nY.n.c.getClass();
                                v3 = nY.n.f(v5_2, v1_7, v0_15);
                            } else {
                            }
                        }
                    } catch (Exception) {
                    }
                    v0_17 = new kotlin.Pair(Boolean.TRUE, Boolean.valueOf(v3));
                } else {
                    v0_17 = new kotlin.Pair(((Boolean) p5), Boolean.FALSE);
                }
                return v0_17;
            default:
                if (!((Boolean) ((kotlin.Pair) p5).getFirst()).booleanValue()) {
                    hs.d.h(com.google.android.gms.internal.mlkit_vision_barcode.m0.c(this.b.getAssets(), "config/zalopay_config.json"));
                    if (((Boolean) ((kotlin.Pair) p5).getFirst()).booleanValue()) {
                        UR.a.c(22000, "verify_fail", 0, 0);
                        byte[] v0_5 = new Object[0];
                        ck.b.e("[Application] Verify signature resource file fail", v0_5);
                    }
                    return ((kotlin.Pair) p5);
                } else {
                    if (!((Boolean) ((kotlin.Pair) p5).getSecond()).booleanValue()) {
                    } else {
                        hs.d.h(com.google.android.gms.internal.mlkit_vision_barcode.m0.d(hs.d.b(((long) 2))));
                        return ((kotlin.Pair) p5);
                    }
                }
        }
    }
}
