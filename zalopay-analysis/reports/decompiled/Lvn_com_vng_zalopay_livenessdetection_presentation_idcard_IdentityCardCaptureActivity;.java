package vn.com.vng.zalopay.livenessdetection.presentation.idcard;
public final class IdentityCardCaptureActivity extends androidx.activity.ComponentActivity {
    public static final synthetic int j;
    public ED.i i;

    public IdentityCardCaptureActivity()
    {
        return;
    }

    public static final void b1(vn.com.vng.zalopay.livenessdetection.presentation.idcard.IdentityCardCaptureActivity p25, java.util.ArrayList p26)
    {
        int v1_1 = p26.iterator();
        while (v1_1.hasNext()) {
            String v2_10 = ((String) v1_1.next());
            ED.j v5_10 = new kotlin.text.Regex(wmoi.igob.ggcn(11905));
            Object v6_5 = wmoi.igob.ggcn(11906);
            ED.j v5_11 = kotlin.text.Regex.find$default(v5_10, com.google.android.gms.internal.play_billing.c1.l(v6_5, v2_10, ""), 0, 2, 0);
            if (v5_11 == null) {
                System.out.println(wmoi.igob.ggcn(11909));
            } else {
                ED.j v5_16 = ((String) v5_11.c().get(1));
                String v8_9 = p25.i;
                if (v8_9 == null) {
                    v8_9 = 0;
                }
                int v16_1 = androidx.camera.camera2.internal.G.e(wmoi.igob.ggcn(11907), v5_16);
                String v8_10 = v8_9.c;
                do {
                    String v11_7 = v8_10.getValue();
                } while(!v8_10.c(v11_7, ED.j.a(((ED.j) v11_7), 0, 0, 0, v16_1, 0, 0, 55)));
                System.out.println(androidx.camera.camera2.internal.G.e(wmoi.igob.ggcn(11908), v5_16));
            }
            String v2_13 = kotlin.text.Regex.find$default(new kotlin.text.Regex(wmoi.igob.ggcn(11910)), com.google.android.gms.internal.play_billing.c1.l(v6_5, v2_10, ""), 0, 2, 0);
            if ((v2_13 == null) || (v2_13.c().size() <= 4)) {
                String v3_0 = new Object[0];
                ck.b.a(wmoi.igob.ggcn(11919), v3_0);
            } else {
                ED.j v5_2 = ((String) v2_13.c().get(1));
                System.out.println(androidx.camera.camera2.internal.G.e(wmoi.igob.ggcn(11911), v5_2));
                String v8_2 = wmoi.igob.ggcn(11912);
                ED.j v5_5 = ci.t.k(new ci.v(kotlin.text.Regex.findAll$default(new kotlin.text.Regex(v8_2), v5_2, 0, 2, 0), new AA.d(22)));
                StringBuilder v7_7 = v5_5.size();
                String v11_4 = wmoi.igob.ggcn(11913);
                String v12_1 = wmoi.igob.ggcn(11914);
                if (v7_7 >= 2) {
                    StringBuilder v7_8 = p25.i;
                    if (v7_8 == null) {
                        v7_8 = 0;
                    }
                    ED.j v13_0 = v5_5.get(2);
                    int v14_0 = v5_5.get(1);
                    int v15_0 = v5_5.get(0);
                    String v3_2 = new StringBuilder(wmoi.igob.ggcn(11915));
                    v3_2.append(v13_0);
                    v3_2.append(v12_1);
                    v3_2.append(v14_0);
                    v3_2.append(v12_1);
                    String v22 = androidx.activity.result.e.l(v15_0, v11_4, v3_2);
                    String v3_3 = v7_8.c;
                    do {
                        boolean v4_3 = v3_3.getValue();
                    } while(!v3_3.c(v4_3, ED.j.a(((ED.j) v4_3), 0, 0, 0, 0, v22, 0, 47)));
                    String v3_5 = new StringBuilder(wmoi.igob.ggcn(11916));
                    v3_5.append(v5_5);
                    System.out.println(v3_5.toString());
                }
                String v2_7 = ci.t.k(new ci.v(kotlin.text.Regex.findAll$default(new kotlin.text.Regex(v8_2), ((String) v2_13.c().get(4)), 0, 2, 0), new AA.d(22)));
                if (v5_5.size() >= 2) {
                    boolean v4_12;
                    String v3_12 = p25.i;
                    if (v3_12 == null) {
                        v4_12 = 0;
                    } else {
                        v4_12 = v3_12;
                    }
                    String v3_13 = v2_7.get(2);
                    ED.j v5_7 = v2_7.get(1);
                    Object v6_1 = v2_7.get(0);
                    StringBuilder v7_12 = new StringBuilder(wmoi.igob.ggcn(11917));
                    v7_12.append(v3_13);
                    v7_12.append(v12_1);
                    v7_12.append(v5_7);
                    v7_12.append(v12_1);
                    String v19_1 = androidx.activity.result.e.l(v6_1, v11_4, v7_12);
                    String v3_14 = v4_12.c;
                    do {
                        boolean v4_13 = v3_14.getValue();
                    } while(!v3_14.c(v4_13, ED.j.a(((ED.j) v4_13), 0, 0, 0, 0, 0, v19_1, 31)));
                }
                String v3_16 = new StringBuilder(wmoi.igob.ggcn(11918));
                v3_16.append(v2_7);
                System.out.println(v3_16.toString());
            }
        }
        boolean v4_0 = p25.i;
        if (!v4_0) {
            v4_0 = 0;
        }
        v4_0.getClass();
        kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.l(v4_0), 0, 0, new ED.h(v4_0, 1, 0, 1), 3, 0);
        return;
    }

    public final void onCreate(android.os.Bundle p4)
    {
        super.onCreate(p4);
        this.i = new ED.i();
        AA.e v4_7 = wmoi.igob.ggcn(11920);
        if (androidx.core.content.c.a(this, v4_7) != 0) {
            androidx.core.app.d.e(this, new String[] {v4_7}), 101);
        }
        k.e.a(this, new y0.b(new AA.e(6, this), 1, -1243898343));
        return;
    }
}
