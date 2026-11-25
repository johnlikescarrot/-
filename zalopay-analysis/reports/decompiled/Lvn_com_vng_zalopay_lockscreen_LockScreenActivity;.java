package vn.com.vng.zalopay.lockscreen;
public final class LockScreenActivity extends androidx.appcompat.app.AppCompatActivity implements ND.a {
    public static final synthetic int q;
    public ND.c i;
    public final ng.S j;
    public final ng.S k;
    public final ng.S l;
    public final ng.S m;
    public final ng.S n;
    public boolean o;
    public final android.os.Handler p;

    public LockScreenActivity()
    {
        this.j = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364648);
        this.k = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131363287);
        this.l = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131363800);
        this.m = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364774);
        this.n = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131363166);
        this.p = new android.os.Handler();
        return;
    }

    public final void J0()
    {
        n9.O.c(this, new pS.f(HS.c.c, "lock_screen", 1, 0, 24), new Tb.c(17, this));
        return;
    }

    public final void N0(int p2)
    {
        this.g1().a(p2);
        return;
    }

    public final void b(String p3)
    {
        int v0_0 = this.m;
        ((android.widget.TextView) v0_0.getValue()).setText(p3);
        ((android.widget.TextView) v0_0.getValue()).setVisibility(0);
        return;
    }

    public final int b0()
    {
        return this.getResources().getInteger(2131427390);
    }

    public final vn.zalopay.ui.password.view.KeyboardView e1()
    {
        return ((vn.zalopay.ui.password.view.KeyboardView) this.k.getValue());
    }

    public final void finish()
    {
        int v0_0 = vn.com.vng.zalopay.AndroidApplication.g;
        int v1_0 = 0;
        if (v0_0 == 0) {
            v0_0 = 0;
        }
        int v0_2 = v0_0.a;
        if (v0_2 != 0) {
            v1_0 = v0_2;
        }
        v1_0.j().a = 3;
        this.setResult(-1);
        super.finish();
        return;
    }

    public final vn.zalopay.ui.password.view.PassCodeRoundView g1()
    {
        return ((vn.zalopay.ui.password.view.PassCodeRoundView) this.l.getValue());
    }

    public final android.app.Activity getActivity()
    {
        return this;
    }

    public final void hideLoading()
    {
        ((android.view.View) this.n.getValue()).setVisibility(4);
        return;
    }

    public final void onBackPressed()
    {
        if (this.o) {
            this.moveTaskToBack(1);
        }
        this.o = 1;
        this.p.postDelayed(new Ae.s(20, this), 2000);
        return;
    }

    public final void onCreate(android.os.Bundle p4)
    {
        CC.k v0_0 = q9.a5.c();
        if (v0_0 == null) {
            CC.k v0_2 = new Object[0];
            ck.b.e("[LockScreen] User not logged in, maybe session expired", v0_2);
        } else {
            v0_0.Z(this);
        }
        n9.O.a = new Iz.b(new jQ.B(7), q9.a5.b());
        super.onCreate(p4);
        int v4_1 = this.i;
        if (v4_1 == 0) {
            v4_1 = 0;
        }
        v4_1.b = this;
        this.setContentView(2131558943);
        return;
    }

    public final void onDestroy()
    {
        ND.c v1 = 0;
        this.p.removeCallbacksAndMessages(0);
        ND.c v0_1 = this.i;
        if (v0_1 != null) {
            v1 = v0_1;
        }
        v1.e0();
        super.onDestroy();
        return;
    }

    public final void onResume()
    {
        super.onResume();
        ((android.widget.TextView) this.j.getValue()).setOnClickListener(new Ae.p(20, this));
        this.e1().a(1);
        this.e1().setTextNumberColor(this.getColor(2131101034));
        vn.zalopay.ui.password.view.KeyboardView v0_3 = this.e1();
        ND.c v2_3 = v0_3.c.iterator();
        while (v2_3.hasNext()) {
            ((vn.zalopay.ui.password.view.KeyboardButtonView) v2_3.next()).setBackgroundResource(2131235081);
        }
        ((vn.zalopay.ui.password.view.KeyboardButtonView) v0_3.c.get(10)).setBackgroundResource(2131234553);
        ((vn.zalopay.ui.password.view.KeyboardButtonView) v0_3.c.get(11)).setBackgroundResource(2131234553);
        this.e1().setImageDrawableButtonClear(this.getDrawable(2131233545));
        vn.zalopay.ui.password.view.KeyboardView v0_10 = this.i;
        ND.c v2_10 = 0;
        if (v0_10 == null) {
            v0_10 = 0;
        }
        if (HS.d.a.a(this, v0_10.c.getUserId())) {
            this.e1().setEnableButtonFingerPrint(1);
            this.e1().setImageDrawableButtonFingerPrint(this.getDrawable(2131234256));
            this.J0();
        }
        this.g1().setVisibility(0);
        this.g1().setPinLength(this.b0());
        this.g1().setFullDotDrawable(2131234713);
        this.g1().setEmptyDotDrawable(2131234710);
        this.g1().a(0);
        vn.zalopay.ui.password.view.KeyboardView v0_21 = this.i;
        if (v0_21 != null) {
            v2_10 = v0_21;
        }
        vn.zalopay.ui.password.view.KeyboardView v0_22 = this.e1();
        v2_10.getClass();
        v0_22.setKeyboardButtonClickedListener(v2_10);
        return;
    }

    public final void showLoading()
    {
        this.g1().setVisibility(0);
        ((android.widget.TextView) this.m.getValue()).setVisibility(4);
        ((android.view.View) this.n.getValue()).setVisibility(0);
        return;
    }
}
