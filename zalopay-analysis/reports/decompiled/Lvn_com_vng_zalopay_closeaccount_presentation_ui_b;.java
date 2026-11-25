package vn.com.vng.zalopay.closeaccount.presentation.ui;
public final class b extends androidx.fragment.app.v {
    public static final lc.a T;
    public static final synthetic kotlin.reflect.n[] U;
    public vn.com.vng.zalopay.closeaccount.presentation.ui.a H;
    public final vn.com.vng.zalopay.authentication.secret.a I;
    public String J;
    public boolean K;
    public final Ai.t L;
    public final Ai.t M;
    public final Ai.t N;
    public final Ai.t O;
    public final Ai.t P;
    public final Ai.t Q;
    public final Ai.t R;
    public final Ai.t S;

    static b()
    {
        kotlin.reflect.p v1_2 = kotlin.jvm.internal.M.a;
        lc.a v0_11 = v1_2.g(new kotlin.jvm.internal.D("txtMgs", 0, "getTxtMgs()Landroid/widget/TextView;", vn.com.vng.zalopay.closeaccount.presentation.ui.b));
        kotlin.reflect.p v3_0 = Kb.e.r(vn.com.vng.zalopay.closeaccount.presentation.ui.b, "txtTitle", "getTxtTitle()Landroid/widget/TextView;", 0, v1_2);
        kotlin.reflect.p v5_2 = Kb.e.r(vn.com.vng.zalopay.closeaccount.presentation.ui.b, "viewInputPin", "getViewInputPin()Lvn/zalopay/ui/password/view/GrayPassCodeRoundView;", 0, v1_2);
        kotlin.reflect.p v6_2 = Kb.e.r(vn.com.vng.zalopay.closeaccount.presentation.ui.b, "viewKeyBoard", "getViewKeyBoard()Lvn/com/vng/zalopay/passport/widget/CustomKeyboardView;", 0, v1_2);
        kotlin.reflect.p v7_2 = Kb.e.r(vn.com.vng.zalopay.closeaccount.presentation.ui.b, "bioActiveStateView", "getBioActiveStateView()Landroid/widget/CheckBox;", 0, v1_2);
        kotlin.reflect.p v8_2 = Kb.e.r(vn.com.vng.zalopay.closeaccount.presentation.ui.b, "txtForgetPin", "getTxtForgetPin()Landroid/widget/TextView;", 0, v1_2);
        kotlin.reflect.p v9_2 = Kb.e.r(vn.com.vng.zalopay.closeaccount.presentation.ui.b, "txtPinError", "getTxtPinError()Landroid/widget/TextView;", 0, v1_2);
        kotlin.reflect.p v1_1 = Kb.e.r(vn.com.vng.zalopay.closeaccount.presentation.ui.b, "btnCancel", "getBtnCancel()Landroid/widget/ImageView;", 0, v1_2);
        kotlin.reflect.n[] v4_1 = new kotlin.reflect.n[8];
        v4_1[0] = v0_11;
        v4_1[1] = v3_0;
        v4_1[2] = v5_2;
        v4_1[3] = v6_2;
        v4_1[4] = v7_2;
        v4_1[5] = v8_2;
        v4_1[6] = v9_2;
        v4_1[7] = v1_1;
        vn.com.vng.zalopay.closeaccount.presentation.ui.b.U = v4_1;
        vn.com.vng.zalopay.closeaccount.presentation.ui.b.T = new lc.a();
        return;
    }

    public b()
    {
        this.I = new vn.com.vng.zalopay.authentication.secret.a();
        this.J = "";
        this.L = new Ai.t(2131364686, 22);
        this.M = new Ai.t(2131364787, 22);
        this.N = new Ai.t(2131365118, 22);
        this.O = new Ai.t(2131365099, 22);
        this.P = new Ai.t(2131362753, 22);
        this.Q = new Ai.t(2131364647, 22);
        this.R = new Ai.t(2131364735, 22);
        this.S = new Ai.t(2131363207, 22);
        return;
    }

    public final void onCancel(android.content.DialogInterface p1)
    {
        p1 = this.H;
        if (p1 != null) {
            p1.a();
        }
        return;
    }

    public final void onCreate(android.os.Bundle p2)
    {
        super.onCreate(p2);
        this.t1(1, 2132017637);
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p2, android.view.ViewGroup p3, android.os.Bundle p4)
    {
        return p2.inflate(2131559509, p3, 0);
    }

    public final void onDestroy()
    {
        if (this.H != null) {
            this.H = 0;
        }
        super.onDestroy();
        return;
    }

    public final void onStart()
    {
        super.onStart();
        android.view.View v0_0 = this.C;
        if (v0_0 != null) {
            android.view.View v0_3 = v0_0.getWindow();
            if (v0_3 != null) {
                v0_3.setGravity(81);
                v0_3.setLayout(-1, -2);
                p9.U.a(v0_3, 0);
                androidx.core.view.h0.l(v0_3.getDecorView().findViewById(16908290), new com.google.gson.internal.c(13));
            }
        }
        return;
    }

    public final void onViewCreated(android.view.View p3, android.os.Bundle p4)
    {
        super.onViewCreated(p3, p4);
        es.i v4_9 = this.L;
        kotlin.reflect.n[] v1 = vn.com.vng.zalopay.closeaccount.presentation.ui.b.U;
        if (!kotlin.text.StringsKt.isBlank(this.J)) {
            ((android.widget.TextView) v4_9.j(this, v1[0])).setText(this.J);
        } else {
            ((android.widget.TextView) v4_9.j(this, v1[0])).setText(this.getString(2131955002));
        }
        ((android.widget.TextView) this.M.j(this, v1[1])).setText(this.getString(2131955000));
        this.x1().setPinLength(6);
        ((vn.com.vng.zalopay.passport.widget.CustomKeyboardView) this.O.j(this, v1[3])).setEditable(this.x1());
        this.w1().setChecked(this.K);
        if (!this.K) {
            this.w1().setVisibility(8);
        } else {
            this.w1().setVisibility(0);
        }
        ((android.widget.TextView) this.Q.j(this, v1[5])).setOnClickListener(new es.i(this, 0));
        this.x1().setOnPinChangeListener(new SM.l0(28, this));
        this.w1().setOnCheckedChangeListener(new CS.p(2, this));
        ((android.widget.ImageView) this.S.j(this, v1[7])).setOnClickListener(new es.i(this, 1));
        return;
    }

    public final android.app.Dialog q1(android.os.Bundle p3)
    {
        return new vn.com.vng.zalopay.closeaccount.presentation.ui.c(this, this.requireActivity(), this.w);
    }

    public final android.widget.CheckBox w1()
    {
        return ((android.widget.CheckBox) this.P.j(this, vn.com.vng.zalopay.closeaccount.presentation.ui.b.U[4]));
    }

    public final vn.zalopay.ui.password.view.GrayPassCodeRoundView x1()
    {
        return ((vn.zalopay.ui.password.view.GrayPassCodeRoundView) this.N.j(this, vn.com.vng.zalopay.closeaccount.presentation.ui.b.U[2]));
    }
}
