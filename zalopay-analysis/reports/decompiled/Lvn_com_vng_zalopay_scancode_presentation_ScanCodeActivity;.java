package vn.com.vng.zalopay.scancode.presentation;
public final class ScanCodeActivity extends androidx.activity.ComponentActivity {
    public static final synthetic int l;
    public final VL.s i;
    public final androidx.activity.result.d j;
    public java.util.ArrayList k;

    public ScanCodeActivity()
    {
        this.i = new VL.s();
        this.j = this.registerForActivityResult(new androidx.fragment.app.f0(6), new BV.k1(4, this));
        return;
    }

    public final void onCreate(android.os.Bundle p7)
    {
        int v7_2;
        super.onCreate(p7);
        int v7_10 = this.getIntent().getExtras();
        if (v7_10 == 0) {
            v7_2 = new java.util.ArrayList();
        } else {
            v7_2 = v7_10.getStringArrayList("types");
            if (v7_2 == 0) {
            }
        }
        this.k = v7_2;
        int v1_1 = new java.util.ArrayList();
        int v7_3 = v7_2.iterator();
        while (v7_3.hasNext()) {
            int v2_0;
            int v2_19 = ((String) v7_3.next()).toLowerCase(java.util.Locale.ROOT);
            switch (v2_19.hashCode()) {
                case -1355092717:
                    if (v2_19.equals("code39")) {
                        v2_0 = Integer.valueOf(2);
                    } else {
                        v2_0 = 0;
                    }
                    break;
                case -993060056:
                    if (v2_19.equals("pdf417")) {
                        v2_0 = Integer.valueOf(2048);
                    }
                    break;
                case 3617:
                    if (v2_19.equals("qr")) {
                        v2_0 = Integer.valueOf(256);
                    }
                    break;
                case 3105574:
                    if (v2_19.equals("ean8")) {
                        v2_0 = Integer.valueOf(64);
                    }
                    break;
                case 3596345:
                    if (v2_19.equals("upca")) {
                        v2_0 = Integer.valueOf(512);
                    }
                    break;
                case 96272628:
                    if (v2_19.equals("ean13")) {
                        v2_0 = Integer.valueOf(32);
                    }
                    break;
                case 100525054:
                    if (v2_19.equals("itf14")) {
                        v2_0 = Integer.valueOf(128);
                    }
                    break;
                case 941726090:
                    if (v2_19.equals("codabar")) {
                        v2_0 = Integer.valueOf(8);
                    }
                    break;
                case 941796650:
                    if (v2_19.equals("code128")) {
                        v2_0 = Integer.valueOf(1);
                    }
                    break;
                case 2003869675:
                    if (v2_19.equals("datamatrix")) {
                        v2_0 = Integer.valueOf(16);
                    }
                    break;
                default:
            }
            if (v2_0 != 0) {
                v1_1.add(v2_0);
            }
        }
        int v2_14 = 0;
        if (v1_1.isEmpty()) {
            v1_1 = kotlin.collections.C.listOf(Integer.valueOf(0));
        }
        int v1_2 = ((java.util.List) v1_1);
        int v7_9 = ((Number) kotlin.collections.CollectionsKt.first(v1_2)).intValue();
        int[] v4_4 = kotlin.collections.CollectionsKt.toIntArray(((java.util.Collection) kotlin.collections.CollectionsKt.drop(((Iterable) v1_2), 1)));
        int[] v4_5 = java.util.Arrays.copyOf(v4_4, v4_4.length);
        if (v4_5 != null) {
            while (v2_14 < v4_5.length) {
                v7_9 |= v4_5[v2_14];
                v2_14++;
            }
        }
        com.google.android.gms.internal.mlkit_vision_barcode.c6.c(new pc.b(v7_9));
        this.i.g0(new XL.l(v1_2));
        k.e.a(this, new y0.b(new VL.a(this, 0), 1, -700275786));
        return;
    }

    public final void onResume()
    {
        super.onResume();
        this.i.g0(XL.j.a);
        return;
    }
}
