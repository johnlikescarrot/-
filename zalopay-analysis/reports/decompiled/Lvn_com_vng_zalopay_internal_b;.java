package vn.com.vng.zalopay.internal;
public final class b implements vn.com.vng.zalopay.internal.d {
    public final synthetic int a;
    public final Pt.n b;
    public final Object c;

    public synthetic b(Pt.n p1, Object p2, int p3)
    {
        this.a = p3;
        this.b = p1;
        this.c = p2;
        return;
    }

    public final int a()
    {
        switch (this.a) {
            case 0:
                return 10000;
            default:
                return 10001;
        }
    }

    public final boolean b()
    {
        switch (this.a) {
            case 0:
                return 0;
            default:
                return 0;
        }
    }

    public final void c(android.app.Application p13)
    {
        ck.a v1_0 = 0;
        String v3_0 = "";
        switch (this.a) {
            case 0:
                String v0_37 = this.b.p();
                rQ.i v4_14 = ((android.content.Context) this.c);
                nQ.b v5_0 = v4_14.getApplicationContext();
                wQ.b v6_2 = new u00.a();
                v6_2.b = v4_14.getApplicationContext();
                v6_2.c = 15000;
                v6_2.e = java.util.UUID.randomUUID().toString();
                v6_2.c = 3000;
                v6_2.d = new DQ.a(v5_0, 1);
                v6_2.a = new pS.e(v4_14);
                v6_2.f = MD.u.n(v4_14);
                nQ.b v5_9 = new u00.a();
                v5_9.b = v6_2.b;
                v5_9.c = v6_2.c;
                v5_9.d = v6_2.d;
                v5_9.a = v6_2.a;
                v5_9.e = v6_2.e;
                v5_9.f = v6_2.f;
                wQ.b v6_7 = new u00.b();
                v6_7.a = new androidx.camera.camera2.internal.compat.workaround.f(1, 12);
                v6_7.b = v5_9;
                v6_7.c = new lS.b(16, new kc.h(7));
                v6_7.d = new c2.b(v4_14);
                rQ.i v4_12 = new u00.b();
                v4_12.a = v6_7.a;
                v4_12.b = v6_7.b;
                tQ.c v7_22 = ((c2.b) v6_7.d);
                uQ.b v8_6 = ((lS.b) v6_7.c);
                int v9_1 = v6_7.b;
                nQ.b v5_17 = new S1.f();
                v9_1.getClass();
                v5_17.g = v9_1;
                v7_22.getClass();
                v5_17.c = v7_22;
                v8_6.getClass();
                v5_17.d = v8_6;
                v5_17.e = java.util.concurrent.Executors.newSingleThreadScheduledExecutor(new F.r("analytics-logs-pusher"));
                tQ.c v7_27 = v9_1.b.getSharedPreferences("zpw_analytics_retry_handler_prefs", 0);
                v5_17.f = v7_27;
                tQ.c v7_28 = v7_27.getBoolean("retry_flag", 0);
                v5_17.h = Boolean.valueOf(v7_28);
                if (v7_28 != null) {
                    v5_17.f();
                }
                String v0_1;
                v4_12.d = v5_17;
                v4_12.c = new S.u(v5_17, v6_7.b);
                u00.b.f = v4_12;
                rQ.i v4 = GO.e.a;
                if (v0_37 == null) {
                    v0_1 = "";
                } else {
                    v0_1 = v0_37.c;
                    if (v0_1 == null) {
                    }
                }
                GO.e.d = v0_1;
                try {
                    String v0_2 = com.google.android.gms.internal.mlkit_vision_barcode.r0.b(p13);
                } catch (String v0_5) {
                    v0_5.toString();
                    if (v1_0 != null) {
                        GO.e.e = v1_0;
                    }
                    GO.e.n();
                    String v0_7 = com.appsflyer.AppsFlyerLib.getInstance().getAppsFlyerUID(p13);
                    if (v0_7 != null) {
                        v3_0 = v0_7;
                    } else {
                    }
                    GO.e.g = v3_0;
                    return;
                } catch (String v0_8) {
                    ck.b.d("Tracker").a(v0_8);
                    return;
                }
                if (v0_2 == null) {
                    v0_2 = com.google.android.gms.internal.mlkit_vision_barcode.r0.d(java.net.Inet4Address);
                    if (v0_2 == null) {
                        v1_0 = com.google.android.gms.internal.mlkit_vision_barcode.r0.d(java.net.Inet6Address);
                    } else {
                    }
                }
                v1_0 = v0_2;
            default:
                String v0_10 = this.b.p();
                xQ.a v10_1 = ((xQ.a) this.c);
                if (v0_10 != null) {
                    v1_0 = v0_10.c;
                }
                if (v1_0 == null) {
                    v1_0 = "";
                }
                String v0_13 = u00.b.f.b.e;
                if (v0_13 != null) {
                    v3_0 = v0_13;
                }
                kQ.f.a("disk", new nQ.b());
                String v0_17 = new oQ.c();
                android.view.Choreographer.getInstance().postFrameCallback(new oQ.b(v0_17));
                kQ.f.a("gpu", v0_17);
                kQ.f.a("network", new rQ.i(p13));
                kQ.f.a("memory", new qQ.b(p13));
                kQ.f.a("battery", new mQ.b(p13));
                kQ.f.a("device", new uQ.b(p13));
                tQ.c v7_5 = new tQ.c(p13, v3_0);
                kQ.f.a("context", v7_5);
                wQ.b v6_4 = new wQ.b();
                v6_4.a = v1_0;
                kQ.f.a("user", v6_4);
                kQ.f.a("install_app", new pQ.c(p13));
                kQ.f.a("permission", new sQ.b(p13));
                kQ.f.d.put("network", new rQ.f(p13, kQ.f.h, new rQ.i(p13), new nQ.b(), v6_4, v7_5, new uQ.b(p13)));
                kQ.f.e = V3.l.e(p13);
                kQ.f.f = v10_1;
                kQ.f.g = new AQ.a(p13);
                kQ.f.b = 1;
                return;
        }
    }
}
