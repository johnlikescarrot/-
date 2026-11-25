package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class BankDatePickerDialogFragment extends DZ.b {
    public static final String[] M;
    public Sn.f H;
    public final a9.f I;
    public final ng.S J;
    public final ng.S K;
    public final ng.S L;

    static BankDatePickerDialogFragment()
    {
        String v2 = "02";
        String v4 = "04";
        String v6 = "06";
        String v8 = "08";
        String v10 = "10";
        vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.BankDatePickerDialogFragment.M = new String[] {"Th\u00e1ng", "12"});
        return;
    }

    public BankDatePickerDialogFragment()
    {
        this.I = new a9.f(kotlin.jvm.internal.M.a.b(Io.w), new Io.i(this, 4));
        this.J = ng.x.b(new Io.v(this, 0));
        this.K = ng.x.b(new Io.v(this, 1));
        this.L = ng.x.b(new Io.v(this, 2));
        return;
    }

    public final String[] A1()
    {
        return ((String[]) this.K.getValue());
    }

    public final void B1(String p4)
    {
        android.widget.NumberPicker v0_2 = ((android.widget.NumberPicker) this.y1().g);
        String[] v1 = vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.BankDatePickerDialogFragment.M;
        v0_2.setDisplayedValues(v1);
        v0_2.setWrapSelectorWheel(1);
        v0_2.setMinValue(0);
        v0_2.setMaxValue(12);
        if (kotlin.collections.y.contains(v1, p4)) {
            v0_2.setValue(kotlin.collections.y.indexOf(v1, p4));
        }
        return;
    }

    public final void C1(String p4)
    {
        android.widget.NumberPicker v0_2 = ((android.widget.NumberPicker) this.y1().j);
        v0_2.setDisplayedValues(this.A1());
        v0_2.setWrapSelectorWheel(1);
        v0_2.setMinValue(0);
        v0_2.setMaxValue((this.A1().length - 1));
        if (kotlin.collections.y.contains(this.A1(), p4)) {
            v0_2.setValue(kotlin.collections.y.indexOf(this.A1(), p4));
        }
        return;
    }

    public final void D1(String p4, String p5, String p6)
    {
        int v0_0 = this.y1();
        ((androidx.appcompat.widget.AppCompatTextView) v0_0.f).setVisibility(4);
        int v0_5 = ((androidx.appcompat.widget.AppCompatTextView) v0_0.e);
        v0_5.setText(p4);
        v0_5.setVisibility(0);
        this.y1().b.setEnabled(0);
        this.z1().d(String.format("%s/%s", java.util.Arrays.copyOf(new Object[] {p5, p6}), 2)), p4);
        return;
    }

    public final void onCancel(android.content.DialogInterface p2)
    {
        java.util.Map v2_3 = kotlin.collections.Z.mapOf(kotlin.TuplesKt.to("bankCode", this.z1().a));
        UR.a.e("01.2027.007", v2_3);
        java.util.Objects.toString(v2_3);
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p11, android.view.ViewGroup p12, android.os.Bundle p13)
    {
        androidx.constraintlayout.widget.ConstraintLayout v11_1 = p11.inflate(-2079850474, p12, 0);
        int v12_1 = -2079915943;
        vn.zalopay.ui.svgutil.ZPSVGImageView v2_1 = ((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(-2079915943, v11_1));
        if (v2_1 != null) {
            v12_1 = -2079915942;
            androidx.appcompat.widget.AppCompatButton v3_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915942, v11_1));
            if (v3_1 != null) {
                v12_1 = -2079915907;
                androidx.appcompat.widget.AppCompatTextView v4_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915907, v11_1));
                if (v4_1 != null) {
                    v12_1 = -2079915868;
                    androidx.appcompat.widget.AppCompatTextView v5_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915868, v11_1));
                    if (v5_1 != null) {
                        v12_1 = -2079915867;
                        android.widget.NumberPicker v6_1 = ((android.widget.NumberPicker) l5.s.a(-2079915867, v11_1));
                        if (v6_1 != null) {
                            v12_1 = -2079915861;
                            if (((android.widget.LinearLayout) l5.s.a(-2079915861, v11_1)) != null) {
                                androidx.constraintlayout.widget.ConstraintLayout v1_1 = ((androidx.constraintlayout.widget.ConstraintLayout) v11_1);
                                v12_1 = -2079915835;
                                androidx.appcompat.widget.AppCompatTextView v8_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915835, v11_1));
                                if (v8_1 != null) {
                                    v12_1 = -2079915772;
                                    android.widget.NumberPicker v9_1 = ((android.widget.NumberPicker) l5.s.a(-2079915772, v11_1));
                                    if (v9_1 != null) {
                                        this.H = new Sn.f(v1_1, v2_1, v3_1, v4_1, v5_1, v6_1, v1_1, v8_1, v9_1);
                                        return ((androidx.constraintlayout.widget.ConstraintLayout) this.y1().h);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v11_1.getResources().getResourceName(v12_1)));
    }

    public final void onDestroyView()
    {
        this.H = 0;
        super.onDestroyView();
        return;
    }

    public final void onViewCreated(android.view.View p3, android.os.Bundle p4)
    {
        Io.t v4_1;
        super.onViewCreated(p3, p4);
        android.widget.NumberPicker v3_6 = this.z1();
        Io.t v4_14 = this.x1().isIssueDate();
        v3_6.getClass();
        if (v4_14 == null) {
            v4_1 = "Expiry date";
        } else {
            v4_1 = "Issue date";
        }
        android.widget.NumberPicker v3_7 = kotlin.collections.a0.mapOf(new kotlin.Pair[] {kotlin.TuplesKt.to("bankCode", v3_6.a), kotlin.TuplesKt.to("type", v4_1)}));
        UR.a.e("01.2027.003", v3_7);
        java.util.Objects.toString(v3_7);
        android.widget.NumberPicker v3_8 = this.y1();
        ((androidx.appcompat.widget.AppCompatTextView) v3_8.i).setText(this.x1().getTitle());
        ((androidx.appcompat.widget.AppCompatTextView) v3_8.f).setText(this.x1().getMessage());
        ((vn.zalopay.ui.svgutil.ZPSVGImageView) v3_8.d).setOnClickListener(new Io.s(this, 0));
        if (this.x1().getPreviousValue().length() == 4) {
            android.widget.NumberPicker v3_16 = this.x1().getPreviousValue().substring(2, 4);
            this.B1(this.x1().getPreviousValue().substring(0, 2));
            this.C1(v3_16);
        } else {
            this.B1(((String) kotlin.collections.y.first(vn.com.vng.zalopay.bankbinding.presentation.ui.dialog.BankDatePickerDialogFragment.M)));
            this.C1(String.valueOf(this.x1().getDefaultYear()));
        }
        this.y1().b.setOnClickListener(new Io.s(this, 1));
        ((android.widget.NumberPicker) this.y1().g).setOnValueChangedListener(new Io.t(this, 0));
        ((android.widget.NumberPicker) this.y1().j).setOnValueChangedListener(new Io.t(this, 1));
        return;
    }

    public final android.app.Dialog q1(android.os.Bundle p3)
    {
        android.app.Dialog v3_1 = super.q1(p3);
        v3_1.setOnShowListener(new Io.u(((V9.g) v3_1), this));
        return v3_1;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument x1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankDatePickerDialogArgument) this.J.getValue());
    }

    public final Sn.f y1()
    {
        IllegalStateException v0_0 = this.H;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final Bo.d z1()
    {
        return ((Bo.d) this.L.getValue());
    }
}
