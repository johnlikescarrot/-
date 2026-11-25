package vn.com.vng.zalopay.internal;
public final class a implements vn.com.vng.zalopay.internal.d {
    public final synthetic int a;

    public synthetic a(int p1)
    {
        this.a = p1;
        return;
    }

    private final void e(android.app.Application p2)
    {
        Throwable v2_6 = new ck.a(1);
        if (v2_6 == ck.b.c) {
            throw new IllegalArgumentException("Cannot plant Timber into itself.");
        } else {
            String v0_3 = ck.b.a;
            try {
                v0_3.add(v2_6);
                Throwable v2_2 = new ck.a[v0_3.size()];
                ck.b.b = ((ck.a[]) v0_3.toArray(v2_2));
                return;
            } catch (Throwable v2_5) {
                throw v2_5;
            }
        }
    }

    public final int a()
    {
        switch (this.a) {
            case 0:
                return 99;
            case 1:
                return 80;
            case 2:
                return 10;
            case 3:
                return 60;
            case 4:
                return 3;
            case 5:
                return 50;
            case 6:
                return 2;
            default:
                return 30;
        }
    }

    public final boolean b()
    {
        switch (this.a) {
            case 0:
                return 1;
            case 1:
                return 1;
            case 2:
                return 1;
            case 3:
                return 1;
            case 4:
                return 1;
            case 5:
                return 1;
            case 6:
                return 0;
            default:
                return 1;
        }
    }

    public final void c(android.app.Application p10)
    {
        a9.f v1_0 = 0;
        String v2_8 = 0;
        switch (this.a) {
            case 0:
                try {
                    kotlinx.coroutines.BuildersKt.launch$default(gi.L.a(kotlinx.coroutines.Dispatchers.getIO()), 0, 0, new Tv.e(p10, 0, 4), 3, 0);
                } catch (Exception) {
                    Throwable v0_14 = new Object[0];
                    ck.b.a("Can not initialize Admob SDK", v0_14);
                }
                return;
            case 1:
                try {
                    if ((kb.a.a == null) && (kb.a.a == null)) {
                        Throwable v0_7 = eb.g.c();
                        v0_7.a();
                        kb.a.a = com.google.firebase.analytics.FirebaseAnalytics.getInstance(v0_7.a);
                    }
                } catch (Throwable v0_6) {
                    throw v0_6;
                }
                PD.c.a.add(new PD.d(kb.a.a));
                return;
            case 2:
                Throwable v0_17 = B5.a.a;
                v0_17.a = 6;
                String v3_5 = new e6.j(p10);
                v3_5.b = e6.f.a;
                v3_5.c = Integer.valueOf(1);
                e6.k v5_3 = new e6.k(v3_5);
                p6.c.b();
                if (!M5.a.b) {
                    M5.a.b = 1;
                } else {
                    if (v0_17.a(5)) {
                        B5.b.b(5, M5.a.getSimpleName(), "Fresco has already been initialized! `Fresco.initialize(...)` should only be called 1 single time to avoid memory leaks!");
                    }
                }
                q9.N.a = 0;
                if (G6.a.a != null) {
                    v1_0 = 1;
                }
                if (v1_0 == null) {
                    p6.c.b();
                    try {
                        Class.forName("com.facebook.imagepipeline.nativecode.NativeCodeInitializer").getMethod("init", new Class[] {android.content.Context})).invoke(0, new Object[] {p10}));
                    } catch (ClassNotFoundException) {
                        G6.a.b(new m6.t(7));
                    } catch (Throwable v0_1) {
                        Throwable v10_1 = v0_1;
                        p6.c.b();
                        throw v10_1;
                    }
                    p6.c.b();
                }
                Throwable v10_2 = p10.getApplicationContext();
                if ((e6.p.p != null) && (B5.a.a.a(5))) {
                    B5.b.b(5, e6.p.getSimpleName(), "ImagePipelineFactory has already been initialized! `ImagePipelineFactory.initialize(...)` should only be called once to avoid unexpected behavior.");
                }
                e6.p.p = new e6.p(v5_3);
                p6.c.b();
                a9.f v1_7 = new a9.f(v10_2, 7);
                M5.a.a = v1_7;
                com.facebook.drawee.view.SimpleDraweeView.i = v1_7;
                p6.c.b();
                p6.c.b();
                return;
            case 3:
                Throwable v10_9 = vn.com.vng.zalopay.AndroidApplication.g;
                if (v10_9 == null) {
                    v10_9 = 0;
                }
                Throwable v10_10 = v10_9.a;
                if (v10_10 != null) {
                    v2_8 = v10_10;
                }
                Throwable v10_11 = v2_8.S();
                Throwable v0_16 = v10_11.c;
                if ((v0_16 != null) && (!v0_16.e(v10_11))) {
                    v0_16.k(v10_11);
                }
                return;
            case 4:
                Throwable v0_3 = j00.d.c;
                if (xn.c.b == null) {
                    xn.c.b = new xn.c();
                }
                a9.f v1_9 = new n8.i(17, p10);
                v0_3.a = xn.c.b;
                v0_3.b = v1_9;
                return;
            case 5:
                p10.registerReceiver(new vn.com.vng.zalopay.receivers.NetworkReceiver(), new android.content.IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                return;
            case 6:
                this.e(p10);
                return;
            default:
                com.zing.zalo.zalosdk.oauth.ZaloSDKApplication.a(p10);
                return;
        }
    }
}
