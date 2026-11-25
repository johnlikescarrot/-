package vn.com.vng.zalopay.pay_by_wallet_native.presentation.ui;
public final class PayByWalletReadyToPayActivity extends androidx.fragment.app.FragmentActivity {
    public static final synthetic int n;
    public dagger.e i;
    public final O5.b j;
    public boolean k;
    public boolean l;
    public final vn.com.vng.zalopay.pay_by_wallet_native.presentation.ui.PayByWalletReadyToPayActivity$nfcStateReceiver$1 m;

    public PayByWalletReadyToPayActivity()
    {
        this.j = new O5.b(kotlin.jvm.internal.M.a.b(jI.w), new dI.g(this, 0), new dI.d(this, 18), new dI.g(this, 1));
        this.m = new vn.com.vng.zalopay.pay_by_wallet_native.presentation.ui.PayByWalletReadyToPayActivity$nfcStateReceiver$1(this);
        return;
    }

    public static final void c1(vn.com.vng.zalopay.pay_by_wallet_native.presentation.ui.PayByWalletReadyToPayActivity p6)
    {
        String v0_4 = android.media.MediaPlayer.create(p6, 2131886150);
        if (v0_4 == null) {
            throw new IllegalStateException("Unable to create tap success media player");
        } else {
            v0_4.setOnCompletionListener(new dI.e());
            v0_4.setOnErrorListener(new dI.f());
            v0_4.start();
            String v0_1 = kotlin.Unit.INSTANCE;
            String v0_3 = ng.L.a(v0_1);
            if (v0_3 != null) {
                android.util.Log.w("PayByWalletReadyToPay", "playTapSuccessFeedback audio failed", v0_3);
            }
            android.os.Vibrator v3_0 = 0;
            if (android.os.Build$VERSION.SDK_INT < 31) {
                kotlin.Unit v6_1 = p6.getSystemService("vibrator");
                if ((v6_1 instanceof android.os.Vibrator)) {
                    v3_0 = ((android.os.Vibrator) v6_1);
                }
            } else {
                kotlin.Unit v6_3;
                kotlin.Unit v6_2 = p6.getSystemService("vibrator_manager");
                if (!(v6_2 instanceof android.os.VibratorManager)) {
                    v6_3 = 0;
                } else {
                    v6_3 = ((android.os.VibratorManager) v6_2);
                }
                if (v6_3 != null) {
                    v3_0 = v6_3.getDefaultVibrator();
                }
            }
            if (v3_0 != null) {
                v3_0.vibrate(android.os.VibrationEffect.createOneShot(120, -1));
            } else {
            }
            kotlin.Unit v6_7 = kotlin.Unit.INSTANCE;
            kotlin.Unit v6_8 = ng.L.a(v6_7);
            if (v6_8 != null) {
                android.util.Log.w("PayByWalletReadyToPay", "playTapSuccessFeedback vibration failed", v6_8);
            }
            return;
        }
    }

    public final jI.w e1()
    {
        return ((jI.w) this.j.getValue());
    }

    public final void onCreate(android.os.Bundle p4)
    {
        androidx.activity.q.a(this, 0, 3);
        super.onCreate(p4);
        int v4_6 = QH.b.c;
        if (v4_6 != 0) {
            androidx.core.view.m1 v0_6;
            this.i = dagger.internal.f.a(v4_6.C);
            QH.b.d = new ref.WeakReference(this);
            int v4_3 = this.getWindow();
            v4_3.setStatusBarColor(-1);
            v4_3.setNavigationBarColor(-1);
            v4_3.getDecorView().setBackgroundColor(-1);
            int v4_5 = this.getWindow();
            y0.b v1_2 = new androidx.core.view.e(2, this.getWindow().getDecorView());
            androidx.core.view.m1 v0_4 = android.os.Build$VERSION.SDK_INT;
            if (v0_4 < 35) {
                if (v0_4 < 30) {
                    v0_6 = new androidx.core.view.m1(v4_5, v1_2);
                } else {
                    v0_6 = new androidx.core.view.n1(v4_5, v1_2);
                }
            } else {
                v0_6 = new androidx.core.view.o1(v4_5, v1_2);
            }
            v0_6.e(1);
            v0_6.d(1);
            k.e.a(this, new y0.b(new YW.h(9, this), 1, -380019420));
            return;
        } else {
            this.finish();
            return;
        }
    }

    public final void onDestroy()
    {
        li.e v0_8;
        this.isFinishing();
        li.e v0_0 = QH.b.d;
        if (v0_0 == null) {
            v0_8 = 0;
        } else {
            v0_8 = ((vn.com.vng.zalopay.pay_by_wallet_native.presentation.ui.PayByWalletReadyToPayActivity) v0_0.get());
        }
        if (v0_8 == this) {
            QH.b.d = 0;
            QH.b.e = 0;
        }
        if (this.isFinishing()) {
            if (this.i != null) {
                this.e1().g0(cI.x.a);
            }
            li.e v0_4 = QH.b.c;
            if (v0_4 != null) {
                li.e v0_5 = v0_4.a();
                java.util.concurrent.ScheduledExecutorService v3_1 = v0_5.a;
                if (v3_1.B.compareAndSet(0, 1)) {
                    v3_1.c();
                    v3_1.k.shutdownNow();
                }
                gi.L.b(v0_5.c.d, 0);
            }
            QH.b.c = 0;
            QH.b.b = 0;
            QH.b.d = 0;
            QH.b.e = 0;
        }
        super.onDestroy();
        return;
    }

    public final void onPause()
    {
        if (this.l) {
            this.l = 0;
            this.unregisterReceiver(this.m);
        }
        if (this.i != null) {
            this.e1().g0(cI.f.a);
        }
        super.onPause();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        if (this.i != null) {
            if (!this.l) {
                androidx.core.content.c.d(this, this.m, new android.content.IntentFilter("android.nfc.action.ADAPTER_STATE_CHANGED"), 2);
                this.l = 1;
            }
            if (this.k) {
                this.e1().g0(new cI.g(this));
                return;
            } else {
                this.k = 1;
                return;
            }
        } else {
            return;
        }
    }

    public final void onStart()
    {
        super.onStart();
        QH.b.e = 1;
        return;
    }

    public final void onStop()
    {
        QH.b.e = 0;
        if (this.i != null) {
            this.e1().g0(cI.y.a);
        }
        super.onStop();
        return;
    }
}
