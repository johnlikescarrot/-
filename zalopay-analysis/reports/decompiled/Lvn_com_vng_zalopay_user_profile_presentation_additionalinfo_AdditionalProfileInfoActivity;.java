package vn.com.vng.zalopay.user_profile.presentation.additionalinfo;
public final class AdditionalProfileInfoActivity extends vn.com.vng.zalopay.core.ui.activity.BaseActivity implements vn.com.vng.zalopay.user_profile.presentation.additionalinfo.a$a {
    public static final synthetic int w;
    public Ux.S k;
    public final O5.b l;
    public final ng.S m;
    public final ng.S n;
    public final ng.S o;
    public final ng.S p;
    public final ng.S q;
    public final ng.S r;
    public final ng.S s;
    public final ng.S t;
    public final ng.S u;
    public final ng.S v;

    public AdditionalProfileInfoActivity()
    {
        this.l = new O5.b(kotlin.jvm.internal.M.a.b(WP.f), new FP.m(this, 0), new BF.a(9, this), new FP.m(this, 1));
        this.m = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131362604);
        this.n = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131362599);
        this.o = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131362606);
        this.p = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131362610);
        this.q = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131362113);
        this.r = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364440);
        this.s = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365132);
        this.t = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365131);
        this.u = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131364477);
        this.v = com.google.android.gms.internal.mlkit_vision_barcode.F0.a(this, 2131365076);
        return;
    }

    public final void O0(YP.c p3)
    {
        android.widget.AutoCompleteTextView v0_0 = p3.b;
        String v3_1 = p3.a;
        if (v0_0 != FP.d.a) {
            if (v0_0 == FP.d.b) {
                ((android.widget.AutoCompleteTextView) this.p.getValue()).setText(v3_1);
            }
            return;
        } else {
            ((android.widget.AutoCompleteTextView) this.o.getValue()).setText(v3_1);
            return;
        }
    }

    public final int e1()
    {
        return 2131559550;
    }

    public final OD.a g1()
    {
        return new OD.a("[Me][Profile]", -1);
    }

    public final WP.f j1()
    {
        return ((WP.f) this.l.getValue());
    }

    public final void onCreate(android.os.Bundle p12)
    {
        CS.v v1_10 = new lS.b(25, com.google.android.gms.internal.mlkit_vision_barcode.b0.a);
        yP.d v8_3 = new yP.d(v1_10, 0);
        int v2_18 = new yP.d(v1_10, 5);
        int v3_1 = new yP.d(v1_10, 3);
        YP.b v4_6 = new yP.d(v1_10, 4);
        yP.d v9_0 = v8_3;
        yP.d v8_1 = new yP.d(v1_10, 2);
        String v5_6 = new xC.b(v1_10, v8_1, 3);
        String v6_5 = new yP.d(v1_10, 6);
        String v0_20 = new DC.Q0(v1_10, v2_18, v3_1, v4_6, v5_6, v6_5, 7);
        int v2_10 = new yP.d(v1_10, 1);
        this.k = ((Ux.S) new DC.Q0(v9_0, v0_20, new yP.c(v1_10, v2_10, 0), new yP.c(v1_10, v2_10, 1), v8_1, v6_5, 2).get());
        super.onCreate(p12);
        android.widget.AutoCompleteTextView v12_23 = this.m;
        ((android.widget.AutoCompleteTextView) v12_23.getValue()).addTextChangedListener(new FP.l(this, 1));
        String v0_26 = this.n;
        ((android.widget.AutoCompleteTextView) v0_26.getValue()).addTextChangedListener(new FP.l(this, 2));
        CS.v v1_15 = this.o;
        int v2_15 = ((android.widget.AutoCompleteTextView) v1_15.getValue());
        v2_15.setFocusableInTouchMode(0);
        v2_15.setOnClickListener(new FP.h(v2_15, this, 0));
        v2_15.addTextChangedListener(new FP.l(this, 3));
        int v2_17 = this.p;
        YP.b v4_13 = ((android.widget.AutoCompleteTextView) v2_17.getValue());
        v4_13.setFocusableInTouchMode(0);
        v4_13.setOnClickListener(new FP.h(v4_13, this, 1));
        v4_13.addTextChangedListener(new FP.l(this, 0));
        ((android.view.View) this.q.getValue()).setOnClickListener(new FP.i(this, 0));
        ((androidx.appcompat.widget.Toolbar) this.u.getValue()).setOnClickListener(new FP.i(this, 1));
        YP.b v4_4 = ((YP.b) this.getIntent().getParcelableExtra("arg_additional_info_data"));
        String v5_4 = this.getString(2131955080);
        if (v4_4 != null) {
            if (!kotlin.jvm.internal.Intrinsics.areEqual(v4_4.a, v5_4)) {
                ((android.widget.AutoCompleteTextView) v12_23.getValue()).setText(v4_4.a);
            }
            if (!kotlin.jvm.internal.Intrinsics.areEqual(v4_4.b, v5_4)) {
                ((android.widget.AutoCompleteTextView) v0_26.getValue()).setText(v4_4.b);
            }
            if (!kotlin.jvm.internal.Intrinsics.areEqual(v4_4.c, v5_4)) {
                ((android.widget.AutoCompleteTextView) v1_15.getValue()).setText(v4_4.c);
            }
            if (!kotlin.jvm.internal.Intrinsics.areEqual(v4_4.d, v5_4)) {
                ((android.widget.AutoCompleteTextView) v2_17.getValue()).setText(v4_4.d);
            }
        }
        android.widget.AutoCompleteTextView v12_17;
        android.widget.AutoCompleteTextView v12_16 = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (v12_16 <= null) {
            v12_17 = 0;
        } else {
            v12_17 = this.getResources().getDimensionPixelSize(v12_16);
        }
        String v0_9 = this.findViewById(2131364265);
        CS.v v1_2 = v0_9.getLayoutParams();
        v1_2.height = v12_17;
        v0_9.setLayoutParams(v1_2);
        com.google.android.gms.internal.mlkit_vision_barcode.W7.b(((android.view.View) this.v.getValue()), 0);
        android.widget.AutoCompleteTextView v12_21 = this.j1();
        v12_21.c.S().f(this, new CS.v(2, new FP.j(this, 0)));
        v12_21.c.r().f(this, new CS.v(2, new FP.j(this, 1)));
        v12_21.p.f(this, new CS.v(2, new FP.j(this, 2)));
        return;
    }
}
