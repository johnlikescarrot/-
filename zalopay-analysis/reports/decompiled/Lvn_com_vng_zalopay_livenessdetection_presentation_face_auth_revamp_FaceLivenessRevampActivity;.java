package vn.com.vng.zalopay.livenessdetection.presentation.face_auth_revamp;
public final class FaceLivenessRevampActivity extends androidx.appcompat.app.AppCompatActivity {
    public static final synthetic int D;
    public final AZ.i A;
    public androidx.camera.view.PreviewView B;
    public final androidx.activity.result.d C;
    public final ng.S i;
    public final ng.S j;
    public final O5.b k;
    public final ng.S l;
    public final ng.S m;
    public float n;
    public boolean o;
    public boolean p;
    public final java.util.concurrent.atomic.AtomicBoolean q;
    public boolean r;
    public Long s;
    public boolean t;
    public BD.c u;
    public final ng.S v;
    public final com.airbnb.lottie.network.g w;
    public BD.a x;
    public BD.e y;
    public androidx.work.impl.constraints.trackers.g z;

    public FaceLivenessRevampActivity()
    {
        this.i = ng.x.b(new zD.e(this, 0));
        this.j = ng.x.b(new vA.b(23));
        this.k = new O5.b(kotlin.jvm.internal.M.a.b(zD.q), new zD.i(this, 0), new zD.e(this, 5), new zD.i(this, 1));
        this.l = ng.x.b(new zD.e(this, 6));
        this.m = ng.x.b(new zD.e(this, 7));
        this.n = -1082130432;
        this.q = new java.util.concurrent.atomic.AtomicBoolean(0);
        this.u = BD.c.a;
        this.v = ng.x.b(new zD.e(this, 8));
        this.w = new com.airbnb.lottie.network.g(2);
        this.A = new AZ.i(new zD.e(this, 9), new zD.e(this, 10));
        this.C = this.registerForActivityResult(new androidx.fragment.app.f0(5), new BV.k1(13, this));
        return;
    }

    public static String l1(java.util.ArrayList p1)
    {
        String v1_6 = ((wD.m) kotlin.collections.CollectionsKt.firstOrNull(p1));
        if (v1_6 == null) {
            v1_6 = wD.m.d;
        }
        switch (v1_6.ordinal()) {
            case 2:
                return "straight";
            case 3:
                return "turn left";
            case 4:
                return "turn right";
            case 5:
                return "smile";
            case 6:
                return "blink 2 eyes";
            case 7:
                return "zoom in";
            case 8:
                return "zoom out";
            default:
                return "straight";
        }
    }

    public final java.util.Map e1()
    {
        java.util.Map v0_1 = com.google.android.gms.internal.mlkit_vision_barcode.L7.c();
        java.util.Map v1_0 = v0_1.c();
        String v2_1 = v0_1.d();
        if (v2_1 == null) {
            v2_1 = vn.com.vng.zalopay.livenessdetection.presentation.face_auth_revamp.FaceLivenessRevampActivity.l1(this.g1().i);
        }
        if (v1_0 == null) {
            v1_0 = v2_1;
        }
        return kotlin.collections.a0.plus(v0_1.b(), kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("action", v1_0)));
    }

    public final wD.c g1()
    {
        return ((wD.c) this.i.getValue());
    }

    public final TC.i h1()
    {
        return ((TC.i) this.m.getValue());
    }

    public final sD.c i1()
    {
        return ((sD.c) this.j.getValue());
    }

    public final zD.q j1()
    {
        return ((zD.q) this.k.getValue());
    }

    public final void k1()
    {
        if (this.p) {
            androidx.camera.view.PreviewView v0_1 = this.B;
            if (v0_1 != null) {
                AZ.i v1 = this.A;
                if (v1.isVisible()) {
                    if (!this.getSupportFragmentManager().P()) {
                        v1.m1();
                    } else {
                        v1.n1();
                    }
                }
                this.o1(v0_1);
                return;
            }
        }
        return;
    }

    public final void m1()
    {
        if (!this.o) {
            int v0_2 = this.getWindow();
            android.view.WindowManager$LayoutParams v1_1 = this.getWindow().getAttributes();
            this.n = v1_1.screenBrightness;
            v1_1.screenBrightness = 1065353216;
            v0_2.setAttributes(v1_1);
            this.o = 1;
            return;
        } else {
            return;
        }
    }

    public final void n1()
    {
        if ((!this.isFinishing()) && (!this.isDestroyed())) {
            j00.e v0_5 = this.A;
            if ((!v0_5.isAdded()) && (!v0_5.isVisible())) {
                if (!this.getSupportFragmentManager().P()) {
                    v0_5.v1(this.getSupportFragmentManager(), "BottomSheetPermissionCamera");
                } else {
                    androidx.fragment.app.n0 v1_4 = androidx.lifecycle.l0.i(this.getLifecycle());
                    kotlinx.coroutines.BuildersKt.launch$default(v1_4, 0, 0, new androidx.lifecycle.z(v1_4, new j00.e(this, 0, 11), 0, 0), 3, 0);
                    return;
                }
            }
        }
        return;
    }

    public final void o1(androidx.camera.view.PreviewView p13)
    {
        if (!this.r) {
            if ((p13.getWidth() > 0) && (p13.getHeight() > 0)) {
                this.r = 1;
                int v1_1 = com.google.android.gms.internal.mlkit_vision_barcode.L7.c();
                int v2_1 = v1_1.d();
                if (v2_1 == 0) {
                    v2_1 = vn.com.vng.zalopay.livenessdetection.presentation.face_auth_revamp.FaceLivenessRevampActivity.l1(this.g1().i);
                }
                JD.c.a("01.1480.000", kotlin.collections.a0.plus(v1_1.b(), kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("action", v2_1))));
                this.q1("01.1496.000");
                int v1_9 = this.getResources().getDisplayMetrics().density;
                int v2_7 = android.os.SystemClock.elapsedRealtime();
                int v4_1 = this.h1();
                int v5_1 = this.x;
                if (v5_1 == 0) {
                    v5_1 = 0;
                }
                v4_1.a(v5_1);
                this.h1().c(fD.a.a);
                int v4_3 = this.z;
                if (v4_3 == 0) {
                    v4_3 = 0;
                }
                if (((BD.c) v4_3.d) == BD.c.a) {
                    v4_3.d = BD.c.b;
                    v4_3.e = Long.valueOf(v2_7);
                }
                int v4_4 = this.z;
                if (v4_4 == 0) {
                    v4_4 = 0;
                }
                this.p1(((BD.c) v4_4.d), v2_7, 1);
                this.h1().i(p13);
                this.getLifecycle().a(this.h1().h());
                this.j1().q0(new CD.b(this.h1().g(), com.facebook.appevents.g.a(((float) p13.getWidth()), ((float) p13.getHeight()), v1_9, 0), com.facebook.appevents.g.a(((float) p13.getWidth()), ((float) p13.getHeight()), v1_9, 1)));
                androidx.lifecycle.l0.i(this.getLifecycle()).a(new vn.com.vng.zalopay.network_monitor.data.monitor.p(this, 0, 3));
                zD.q v8 = this.j1();
                androidx.lifecycle.x v7_0 = this.getLifecycle();
                androidx.lifecycle.A v0_3 = androidx.lifecycle.l0.i(v7_0);
                rx.b v3_1 = new rx.b;
                v3_1(v7_0, v8, 0, this, 16);
                kotlinx.coroutines.BuildersKt.launch$default(v0_3, 0, 0, v3_1, 3, 0);
                return;
            } else {
                if ((!p13.isLaidOut()) || (p13.isLayoutRequested())) {
                    p13.addOnLayoutChangeListener(new jV.u0(this, p13, 2));
                    return;
                } else {
                    this.o1(p13);
                    return;
                }
            }
        } else {
            return;
        }
    }

    public final void onCreate(android.os.Bundle p5)
    {
        super.onCreate(p5);
        wD.l.a = this.g1().n;
        this.m1();
        zD.h v0_17 = this.getApplication();
        if (vD.a.b == null) {
            try {
                vD.a.b = v0_17;
            } catch (zD.h v0_0) {
                throw v0_0;
            }
        }
        zD.h v0_1 = this.getApplication();
        if (aD.b.d == null) {
            try {
                aD.b.d = v0_1;
            } catch (zD.h v0_2) {
                throw v0_2;
            }
        }
        androidx.activity.G v5_9;
        if (((Number) ((BD.b) this.v.getValue()).a.invoke()).longValue() >= 4294967296) {
            v5_9 = BD.a.e;
        } else {
            v5_9 = BD.a.d;
        }
        this.x = v5_9;
        zD.h v0_5 = new BD.e();
        v0_5.b = v5_9;
        v0_5.c = new java.util.ArrayList();
        this.y = v0_5;
        int v1_2 = this.x;
        if (v1_2 == 0) {
            v1_2 = 0;
        }
        this.z = new androidx.work.impl.constraints.trackers.g(v1_2, v0_5);
        this.j1().q0(new CD.d(this.g1().i));
        com.google.android.gms.internal.mlkit_vision_barcode.L7.c().g(vn.com.vng.zalopay.livenessdetection.presentation.face_auth_revamp.FaceLivenessRevampActivity.l1(this.g1().i));
        this.C.a("android.permission.CAMERA", 0);
        k.e.a(this, new y0.b(new qH.c(10, this), 1, 890824214));
        this.getOnBackPressedDispatcher().a(this, new zD.h(this));
        return;
    }

    public final void onPause()
    {
        super.onPause();
        int v0_1 = this.getWindow();
        android.view.WindowManager$LayoutParams v1_1 = this.getWindow().getAttributes();
        v1_1.screenBrightness = this.n;
        v0_1.setAttributes(v1_1);
        this.o = 0;
        return;
    }

    public final void onResume()
    {
        super.onResume();
        this.m1();
        return;
    }

    public final void onStop()
    {
        super.onStop();
        if ((!this.isFinishing()) && (!this.q.get())) {
            this.i1().b(new vn.com.vng.zalopay.livenessdetection.domain.model.exceptions.LivenessCameraXException$ContextSwitchException(0));
            FD.b.a();
            this.finish();
        }
        return;
    }

    public final void p1(BD.c p7, long p8, boolean p10)
    {
        if ((p10 != null) || (this.u != p7)) {
            this.u = p7;
            com.airbnb.lottie.network.g v10_2 = this.w;
            java.util.ArrayList v0_1 = ((java.util.ArrayList) v10_2.b);
            String v1_5 = ((BD.c) v10_2.c);
            if ((v1_5 != null) && (v1_5 != p7)) {
                String v1_1 = androidx.camera.camera2.internal.G.i("transition=", v1_5.name(), "->", p7.name(), ",ts=");
                v1_1.append(p8);
                v0_1.add(v1_1.toString());
            }
            String v1_3 = p7.name();
            String v3_2 = new StringBuilder("phase=");
            v3_2.append(v1_3);
            v3_2.append(",ts=");
            v3_2.append(p8);
            v0_1.add(v3_2.toString());
            v10_2.c = p7;
            this.j1().q0(new CD.e(p7));
            if (p7 == BD.c.c) {
                this.h1().c(fD.a.b);
            }
        }
        return;
    }

    public final void q1(String p5)
    {
        java.util.Map v0_3;
        java.util.Map v0_2 = this.g1().j;
        if (v0_2 == null) {
            v0_3 = 0;
        } else {
            v0_3 = v0_2.a;
        }
        if (v0_3 == wD.h.b) {
            String v1_4;
            java.util.Map v0_1 = kotlin.collections.Z.createMapBuilder();
            v0_1.put("strategy_code", this.g1().l);
            if (!this.g1().k) {
                v1_4 = "v1";
            } else {
                v1_4 = "v2";
            }
            v0_1.put("version", v1_4);
            if (kotlin.jvm.internal.Intrinsics.areEqual(this.g1().l, "S5")) {
                String v1_9 = this.g1().i;
                java.util.ArrayList v2_4 = new java.util.ArrayList(kotlin.collections.F.collectionSizeOrDefault(v1_9, 10));
                String v1_10 = v1_9.iterator();
                while (v1_10.hasNext()) {
                    v2_4.add(Integer.valueOf(((wD.m) v1_10.next()).a));
                }
                v0_1.put("list_step", v2_4);
            }
            JD.c.a(p5, kotlin.collections.Z.build(v0_1));
        }
        return;
    }
}
