package vn.com.vng.zalopay;
public abstract class BaseApplication extends vn.com.vng.zalopay.core.CoreApplication implements cw.b {
    public CC.f a;
    public CC.k b;
    public kotlinx.coroutines.Job c;

    public BaseApplication()
    {
        return;
    }

    public final Lm.a a()
    {
        int v0_0 = this.b;
        if (v0_0 == 0) {
            return 0;
        } else {
            CC.g v1_1 = v0_0.x1();
            return new CC.i(v1_1.a, v1_1.b, new Mm.a(), new androidx.work.impl.utils.e(15, new IH.a(new D5.f(10), v0_0)));
        }
    }

    public final void b()
    {
        kotlinx.coroutines.Job v0_0 = this.c;
        if ((v0_0 != null) && ((v0_0.isActive() ^ 1) != 1)) {
            return;
        } else {
            this.c = kotlinx.coroutines.BuildersKt.launch$default(gi.L.a(kotlinx.coroutines.Dispatchers.getIO()), 0, 0, new j00.e(this, 0, 6), 3, 0);
            return;
        }
    }

    public final void c()
    {
        if (!vn.zalopay.ui.util.a.a) {
            vn.zalopay.ui.util.a.a = 1;
            new Thread(new tZ.a(4, this)).start();
            return;
        } else {
            return;
        }
    }

    public final void d()
    {
        return;
    }

    public final void e()
    {
        try {
            java.io.IOException v0_1 = new StringBuilder();
            v0_1.append(this.getFilesDir().getCanonicalPath());
            String v1_3 = java.io.File.separator;
            v0_1.append(v1_3);
            v0_1.append(this.getPackageName());
            v0_1.append(v1_3);
            v0_1.append(wmoi.igob.ggcn(7912));
            n9.e0.a = v0_1.toString();
            return;
        } catch (java.io.IOException v0_3) {
            ck.b.f(v0_3);
            return;
        }
    }

    public final void onTrimMemory(int p2)
    {
        super.onTrimMemory(p2);
        if ((p2 == 15) || (p2 == 80)) {
            if ((lG.a.b == null) && (lG.a.b == null)) {
                lG.a.b = new lG.a();
            }
            kotlinx.coroutines.Job v0_4 = lG.a.b.a;
            if (v0_4 != null) {
                v0_4.clear();
            }
            LF.c.b = 0;
            LF.g.a.clear();
            LF.e.a.evictAll();
            LF.e.b.evictAll();
            kotlinx.coroutines.Job v0_10 = this.c;
            if (v0_10 != null) {
                v0_10.cancel(0);
            }
        }
        return;
    }
}
