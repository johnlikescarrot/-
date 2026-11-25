package vn.com.vng.zalopay.my_qr;
public final class MyQRActivity extends androidx.appcompat.app.AppCompatActivity implements gY.a {
    public static final synthetic int o;
    public KG.a i;
    public dagger.e j;
    public final O5.b k;
    public final q0.N0 l;
    public final q0.N0 m;
    public final gY.b n;

    public MyQRActivity()
    {
        this.k = new O5.b(kotlin.jvm.internal.M.a.b(GG.m), new oG.g(this, 0), new mS.a(1, this), new oG.g(this, 1));
        gY.b v0_1 = q0.D0.e;
        this.l = q0.d.G(0, v0_1);
        this.m = q0.d.G(kotlin.collections.a0.emptyMap(), v0_1);
        this.n = new gY.b(this, this);
        return;
    }

    public final void e1(android.content.Intent p5)
    {
        java.util.Map v5_1;
        if (p5 == null) {
            v5_1 = kotlin.collections.a0.emptyMap();
        } else {
            java.util.Map v5_2 = p5.getExtras();
            if (v5_2 != null) {
                q0.N0 v0_3 = kotlin.collections.Z.createMapBuilder();
                String v1_2 = v5_2.keySet().iterator();
                while (v1_2.hasNext()) {
                    String v2_2 = ((String) v1_2.next());
                    String v3 = v5_2.getString(v2_2);
                    if (v3 != null) {
                        v0_3.put(v2_2, v3);
                    }
                }
                v5_1 = kotlin.collections.Z.build(v0_3);
            }
        }
        this.m.setValue(v5_1);
        java.util.Map v5_4 = ((String) v5_1.get("tab"));
        FG.a.b.getClass();
        this.l.setValue(d6.l.h(v5_4));
        return;
    }

    public final GG.m g1()
    {
        return ((GG.m) this.k.getValue());
    }

    public final void onCreate(android.os.Bundle p19)
    {
        super.onCreate(p19);
        androidx.activity.q.a(this, 0, 3);
        BG.a.g(this.getWindow());
        this.e1(vn.com.vng.zalopay.my_qr.MyQRActivity v0.getIntent());
        y0.b v2_0 = com.google.android.gms.internal.mlkit_vision_barcode.i5.a;
        YW.h v1_9 = new n9.i3(v2_0, com.google.android.gms.internal.mlkit_vision_barcode.i5.b);
        int v5_6 = new vG.c();
        int v3_6 = new vG.a(v1_9, 6);
        wL.m v7_3 = new vG.a(v1_9, 0);
        vG.a v14_1 = new vG.a(v1_9, 8);
        int v4_13 = new vG.a(v1_9, 2);
        vG.a v12_1 = new vG.a(v1_9, 11);
        int v13_1 = new vG.a(v1_9, 7);
        vG.a v15_1 = new Yt.w(new vG.a(v1_9, 12), 16);
        wL.m v6_8 = new vG.a(v1_9, 3);
        wL.m v8_6 = new vG.a(v1_9, 1);
        int v9_3 = new vG.a(v1_9, 4);
        vG.a v15_2 = v9_3;
        YW.h v1_0 = dagger.internal.f.b(new nA.w(dagger.internal.f.b(new nA.w(v5_6, dagger.internal.f.b(new vG.e(v5_6, v3_6, v7_3, v14_1, dagger.internal.f.b(new CL.d(v5_6, v4_13, v12_1, v13_1, v15_1, v6_8, new AU.d(v7_3, v8_6, v9_3, new vG.a(v1_9, 5), 19), new vG.a(v1_9, 13), 12)), dagger.internal.f.b(new vG.d(v5_6, new vG.a(v1_9, 10), new vG.a(v1_9, 9))))), 28)), 15));
        int v3_2 = dagger.internal.f.b(new ou.e(2, new pG.b(v15_2)));
        YW.h v1_5 = dagger.internal.f.b(new AU.d(new Xv.c(v1_0, v3_2, 28), new wL.m(v3_2, 6), new wL.m(v3_2, 7), new wL.m(v3_2, 8), 4));
        int v4_5 = p9.m7.a(1);
        v4_5.put(GG.m, v1_5);
        YW.h v1_8 = dagger.internal.f.b(new WH.e(1, new dagger.internal.n(v4_5)));
        y0.b v2_3 = ((KG.a) v2_0.c.get());
        p9.n7.c(v2_3);
        this.i = v2_3;
        this.j = dagger.internal.f.a(v1_8);
        k.e.a(this, new y0.b(new YW.h(27, this), 1, -1466147488));
        return;
    }

    public final void onNewIntent(android.content.Intent p1)
    {
        super.onNewIntent(p1);
        this.setIntent(p1);
        this.e1(p1);
        return;
    }

    public final void onPause()
    {
        this.n.b();
        super.onPause();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        this.n.a();
        return;
    }

    public final void onScreenCaptured()
    {
        this.g1().g0(CG.g.a);
        return;
    }

    public final void onWindowFocusChanged(boolean p1)
    {
        super.onWindowFocusChanged(p1);
        if (p1 != null) {
            BG.a.g(this.getWindow());
        }
        return;
    }
}
