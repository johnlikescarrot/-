package vn.com.vng.zalopay.miniapp.ui;
public final class MiniAppActivity extends androidx.appcompat.app.AppCompatActivity {
    public ns.c$a i;
    public Jv.a j;
    public android.os.Bundle k;
    public org.greenrobot.eventbus.e l;
    public kG.b m;
    public TE.b n;
    public final O5.b o;

    public MiniAppActivity()
    {
        this.o = new O5.b(kotlin.jvm.internal.M.a.b(kG.a), new bG.b(this, 0), new TT.a(29, this), new bG.b(this, 1));
        return;
    }

    public final void e1()
    {
        String v1 = 0;
        ((kG.a) this.o.getValue()).c = 0;
        androidx.fragment.app.a v0_5 = this.k;
        ns.c$a v2_2 = new bG.o();
        v2_2.setArguments(v0_5);
        androidx.fragment.app.a v0_2 = androidx.activity.result.e.d(this.getSupportFragmentManager(), this.getSupportFragmentManager());
        int v3_0 = this.j;
        if (v3_0 == 0) {
            v3_0 = 0;
        }
        String v4_1;
        int v3_3 = ((android.widget.FrameLayout) v3_0.c).getId();
        String v4_0 = this.i;
        if (v4_0 == null) {
            v4_1 = 0;
        } else {
            v4_1 = v4_0.getAppId();
        }
        v0_2.i(v3_3, v2_2, androidx.camera.camera2.internal.G.e(wmoi.igob.ggcn(12959), v4_1), 1);
        ns.c$a v2_0 = this.i;
        if (v2_0 != null) {
            v1 = v2_0.getAppId();
        }
        v0_2.c(v1);
        v0_2.f();
        return;
    }

    public final void g1(android.content.Intent p11)
    {
        ns.c$a v11_5;
        if (p11 == null) {
            v11_5 = 0;
        } else {
            v11_5 = p11.getExtras();
        }
        ns.c$a v11_9;
        this.k = v11_5;
        androidx.lifecycle.x v2_5 = 0;
        if (v11_5 == null) {
            v11_9 = 0;
        } else {
            try {
                ns.c$a v11_3 = ((android.os.Parcelable) p9.M.a(v11_5, wmoi.igob.ggcn(12960), ns.c$a));
            } catch (int v0_4) {
                v11_3 = kotlin.ResultKt.createFailure(v0_4);
            }
            int v0_5 = ng.L.a(v11_3);
            if (v0_5 != 0) {
                int v4_1 = new Object[0];
                ck.b.d(wmoi.igob.ggcn(12961)).e(v0_5, wmoi.igob.ggcn(12962), v4_1);
                v11_3 = 0;
            }
            v11_9 = ((ns.c$a) ((android.os.Parcelable) v11_3));
        }
        int v0_7;
        this.i = v11_9;
        ns.c$a v11_10 = this.getSupportFragmentManager();
        int v0_6 = this.i;
        if (v0_6 == 0) {
            v0_7 = 0;
        } else {
            v0_7 = v0_6.getAppId();
        }
        androidx.lifecycle.A v3_4 = new StringBuilder(wmoi.igob.ggcn(12963));
        v3_4.append(v0_7);
        ns.c$a v11_11 = v11_10.E(v3_4.toString());
        if (v11_11 == null) {
            ns.c$a v11_16 = ((Iterable) this.getSupportFragmentManager().c.f()).iterator();
            while (v11_16.hasNext()) {
                int v0_11 = ((androidx.fragment.app.Fragment) v11_16.next());
                androidx.lifecycle.x v2_6 = this.getSupportFragmentManager();
                v2_6.getClass();
                androidx.lifecycle.A v3_6 = new androidx.fragment.app.a(v2_6);
                v3_6.k(v0_11);
                v3_6.f();
            }
            this.e1();
        } else {
            int v0_12 = this.i;
            if (v0_12 != 0) {
                v2_5 = kotlin.jvm.internal.Intrinsics.areEqual(v0_12.u(), Boolean.FALSE);
            }
            androidx.lifecycle.A v3_8;
            int v0_14 = this.getSupportFragmentManager();
            androidx.lifecycle.A v3_7 = this.i;
            if (v3_7 == null) {
                v3_8 = 0;
            } else {
                v3_8 = v3_7.getAppId();
            }
            v0_14.W(-1, v2_5, v3_8);
            if (v2_5 == null) {
                ((kG.a) this.o.getValue()).c = this.k;
                int v0_0 = this.getSupportFragmentManager();
                v0_0.getClass();
                androidx.lifecycle.x v2_2 = new androidx.fragment.app.a(v0_0);
                v2_2.q(v11_11);
                v2_2.f();
            } else {
                this.e1();
            }
        }
        ns.c$a v11_1 = this.i;
        if (v11_1 != null) {
            int v0_3;
            int v0_2 = this.k;
            if (v0_2 == 0) {
                v0_3 = 0;
            } else {
                v0_3 = v0_2.getBundle(wmoi.igob.ggcn(12964));
            }
            kotlinx.coroutines.BuildersKt.launch$default(androidx.lifecycle.l0.i(this.getLifecycle()), 0, 0, new bG.a(this, v11_1, v0_3, 0), 3, 0);
        }
        return;
    }

    public final void onCreate(android.os.Bundle p4)
    {
        q9.a5.b().N1(this);
        androidx.activity.q.a(this, 0, 3);
        super.onCreate(p4);
        if (p4 != null) {
            NullPointerException v4_2 = ((CharSequence) GO.e.c.get());
            if ((v4_2 == null) || (kotlin.text.StringsKt.isBlank(v4_2))) {
                UR.a.e(String.format(java.util.Locale.getDefault(), wmoi.igob.ggcn(12967), java.util.Arrays.copyOf(new Object[] {Integer.valueOf(0), Integer.valueOf(10)}), 2)), kotlin.collections.Z.mapOf(kotlin.TuplesKt.to(wmoi.igob.ggcn(12965), wmoi.igob.ggcn(12966))));
                m9.p.j(this, at.W.d);
                return;
            }
        }
        NullPointerException v4_5 = this.getLayoutInflater().inflate(2131558467, 0, 0);
        if (v4_5 == null) {
            throw new NullPointerException(wmoi.igob.ggcn(12968));
        } else {
            NullPointerException v4_8 = ((android.widget.FrameLayout) v4_5);
            this.j = new Jv.a(1, v4_8, v4_8);
            this.setContentView(v4_8);
            this.g1(this.getIntent());
            return;
        }
    }

    public final void onNewIntent(android.content.Intent p1)
    {
        super.onNewIntent(p1);
        this.g1(p1);
        return;
    }

    public final void onSignOut(eu.e p4)
    {
        Ow.b v4_5;
        String v0 = p4.a;
        if (p4.b != 1) {
            v4_5 = 0;
        } else {
            v4_5 = 2;
        }
        Ow.b v1_4 = this.l;
        CC.f v2 = 0;
        if (v1_4 == null) {
            v1_4 = 0;
        }
        v1_4.l();
        Ow.b v1_0 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v1_0 == null) {
            v1_0 = 0;
        }
        Ow.b v1_1 = v1_0.a;
        if (v1_1 == null) {
            v1_1 = 0;
        }
        v1_1.J().b(v4_5, v0);
        Ow.b v4_2 = vn.com.vng.zalopay.AndroidApplication.g;
        if (v4_2 == null) {
            v4_2 = 0;
        }
        Ow.b v4_3 = v4_2.a;
        if (v4_3 != null) {
            v2 = v4_3;
        }
        v2.J().a();
        return;
    }

    public final void onStart()
    {
        super.onStart();
        int v0 = this.l;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.k(this);
        return;
    }

    public final void onStop()
    {
        int v0 = this.l;
        if (v0 == 0) {
            v0 = 0;
        }
        v0.p(this);
        super.onStop();
        return;
    }
}
