package vn.com.vng.zalopay.ui.activity;
public class SplashScreenActivity extends androidx.appcompat.app.AppCompatActivity implements ZO.i {
    public static final synthetic int q;
    public boolean i;
    public final kf.a j;
    public final com.google.firebase.perf.metrics.Trace k;
    public dagger.e l;
    public dagger.e m;
    public dagger.e n;
    public dagger.e o;
    public dagger.e p;

    public SplashScreenActivity()
    {
        this.i = 0;
        this.j = new kf.a(0);
        Pb.b.a().getClass();
        this.k = Pb.b.b("SplashScreen");
        return;
    }

    public static void e1(vn.com.vng.zalopay.ui.activity.SplashScreenActivity p2, android.os.Bundle p3)
    {
        MO.k v0_13;
        p2.k.start();
        if (android.os.Build$VERSION.SDK_INT < 31) {
            v0_13 = new XQ.i(23, p2);
        } else {
            v0_13 = new h2.b(p2);
        }
        v0_13.D();
        MO.k v0_2 = ((CC.f) q9.a5.a());
        p2.l = dagger.internal.f.a(v0_2.a1);
        p2.m = dagger.internal.f.a(v0_2.e0);
        p2.n = dagger.internal.f.a(v0_2.j0);
        p2.o = dagger.internal.f.a(v0_2.f);
        p2.p = dagger.internal.f.a(v0_2.d1);
        super.onCreate(p3);
        ((Ps.d) p2.o.get()).execute(new Ek.b(14));
        Ps.d v3_6 = ((WO.n) p2.l.get());
        v3_6.b = p2;
        MO.k v0_8 = v3_6.g;
        if (!v0_8.e(v3_6)) {
            v0_8.k(v3_6);
        }
        androidx.core.view.h0.l(p2.getWindow().getDecorView(), new Tb.c(16, p2));
        if (p2.isTaskRoot()) {
            ((Ps.d) p2.o.get()).execute(new MO.k(p2, 1));
            ((WO.n) p2.l.get()).E0();
            ((Ps.d) p2.o.get()).execute(new Ek.b(13));
            if (p2.getPackageName().equals("vn.com.vng.zalopay")) {
                ((Ps.d) p2.o.get()).execute(new MO.k(p2, 0));
            }
            return;
        } else {
            p2.finish();
            return;
        }
    }

    public final void L0()
    {
        if (android.os.Build$VERSION.SDK_INT < 31) {
            this.g1();
            return;
        } else {
            new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new MO.l(this, 0), 1000);
            return;
        }
    }

    public final void Q0()
    {
        if (android.os.Build$VERSION.SDK_INT < 31) {
            ((um.d) this.n.get()).b(this, at.W.d);
            this.finish();
            this.k.stop();
            return;
        } else {
            new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new MO.l(this, 1), 1000);
            return;
        }
    }

    public final void g1()
    {
        this.i = 1;
        UR.a.c(11201, ((WO.n) this.l.get()).d.p().m, 0, 0);
        ((vn.com.vng.zalopay.navigation.b) this.m.get()).c(this, 0, 1);
        this.finish();
        this.k.stop();
        return;
    }

    public final android.content.Context getContext()
    {
        return this;
    }

    public final void n0()
    {
        if (!this.isFinishing()) {
            ((vn.com.vng.zalopay.navigation.b) this.m.get()).getClass();
            this.startActivityForResult(new android.content.Intent(this, vn.com.vng.zalopay.passport.ForceUpdatePhoneActivity), 151);
            return;
        } else {
            return;
        }
    }

    public final void onActivityResult(int p3, int p4, android.content.Intent p5)
    {
        super.onActivityResult(p3, p4, p5);
        if (p3 != 151) {
            if (p4 == -1) {
                ND.e v3_8 = vn.com.vng.zalopay.AndroidApplication.g;
                if (v3_8 == null) {
                    v3_8 = 0;
                }
                v3_8.a.j().a = 3;
                this.L0();
            }
            return;
        } else {
            int v4_6 = new kf.b[1];
            v4_6[0] = gf.A.timer(500, java.util.concurrent.TimeUnit.MILLISECONDS).observeOn(hf.b.a()).subscribe(new MO.j(0, this), new Ua.d(12));
            this.j.d(v4_6);
            return;
        }
    }

    public final void onBackPressed()
    {
        return;
    }

    public final void onCreate(android.os.Bundle p3)
    {
        this.getWindow().getDecorView().setSystemUiVisibility(5894);
        this.setContentView(2131558482);
        Pb.b.a().getClass();
        com.google.firebase.perf.metrics.Trace v0_2 = Pb.b.b("SplashOnCreate");
        try {
            v0_2.start();
            vn.com.vng.zalopay.ui.activity.SplashScreenActivity.e1(this, p3);
            v0_2.stop();
            return;
        } catch (Throwable v3_1) {
            v0_2.stop();
            throw v3_1;
        }
    }

    public final void onDestroy()
    {
        super.onDestroy();
        this.j.e();
        ((WO.n) this.l.get()).e0();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if (this.i) {
            this.L0();
        }
        return;
    }

    public final void x()
    {
        ((vn.com.vng.zalopay.navigation.b) this.m.get()).d(this);
        return;
    }
}
