package vn.com.vng.zalopay.quickpay_native.presentation.ui;
public final class QuickPayNativeActivity extends androidx.fragment.app.FragmentActivity {
    public static final synthetic int n;
    public TJ.X i;
    public KG.a j;
    public final O5.b k;
    public CJ.e l;
    public com.facebook.applinks.b m;

    public QuickPayNativeActivity()
    {
        this.k = new O5.b(kotlin.jvm.internal.M.a.b(TJ.V), new CJ.f(this, 0), new CJ.d(this, 1), new CJ.f(this, 1));
        return;
    }

    public final void onCreate(android.os.Bundle p4)
    {
        androidx.activity.q.a(this, 0, 3);
        super.onCreate(p4);
        AA.e v4_8 = q9.l6.b;
        if (v4_8 != null) {
            this.i = new TJ.X(new tJ.b(v4_8.b));
            AA.e v4_3 = ((androidx.camera.camera2.internal.S0) v4_8.a.b);
            y0.b v0_5 = ((KG.a) ((dagger.internal.o) v4_3.b).get());
            p9.n7.c(v0_5);
            this.j = v0_5;
            if (v4_3.c()) {
                this.getWindow().setFlags(8192, 8192);
            }
            k.e.a(this, new y0.b(new AA.e(3, this), 1, 1251326335));
            return;
        } else {
            this.finish();
            return;
        }
    }

    public final void onStart()
    {
        super.onStart();
        try {
            if (android.os.Build$VERSION.SDK_INT < 34) {
                java.util.concurrent.Executor v1_0 = this.getContentResolver();
                android.net.Uri v0_2 = new com.facebook.applinks.b(v1_0, new CJ.d(this, 0));
                this.m = v0_2;
                if (((EJ.b) v0_2.d) == null) {
                    EJ.b v3_3 = new EJ.b(v0_2, new android.os.Handler(android.os.Looper.getMainLooper()), 0);
                    v0_2.d = v3_3;
                    v1_0.registerContentObserver(android.provider.MediaStore$Images$Media.EXTERNAL_CONTENT_URI, 1, v3_3);
                }
                return;
            } else {
                android.net.Uri v0_5 = new CJ.e(0, this);
                this.registerScreenCaptureCallback(androidx.core.content.c.c(this), v0_5);
                this.l = v0_5;
                return;
            }
        } catch (SecurityException) {
        }
    }

    public final void onStop()
    {
        if (android.os.Build$VERSION.SDK_INT >= 34) {
            Throwable v0_3 = this.l;
            if (v0_3 != null) {
                this.l = 0;
                try {
                    this.unregisterScreenCaptureCallback(v0_3);
                } catch (Throwable v0_1) {
                    Object vtmp1 = kotlin.ResultKt.createFailure(v0_1);
                }
            }
        }
        Throwable v0_2 = this.m;
        if (v0_2 != null) {
            ng.J v1_2 = ((EJ.b) v0_2.d);
            if (v1_2 != null) {
                ((android.content.ContentResolver) v0_2.b).unregisterContentObserver(v1_2);
            }
            v0_2.d = 0;
        }
        this.m = 0;
        super.onStop();
        return;
    }
}
