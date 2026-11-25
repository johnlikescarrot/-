package vn.zalopay.ui.edittext;
public final class CustomNumberTextField extends android.widget.LinearLayout {
    public final CZ.a a;

    public CustomNumberTextField(android.content.Context p10, android.util.AttributeSet p11)
    {
        super(p10, p11, 0);
        int v11_5 = android.view.LayoutInflater.from(p10).inflate(2131560378, super, 0);
        super.addView(v11_5);
        int v0_1 = 2131362605;
        vn.zalopay.ui.edittext.NumberEditText v5_1 = ((vn.zalopay.ui.edittext.NumberEditText) l5.s.a(2131362605, v11_5));
        if (v5_1 != null) {
            v0_1 = 2131363096;
            android.widget.ImageView v6_1 = ((android.widget.ImageView) l5.s.a(2131363096, v11_5));
            if (v6_1 != null) {
                v0_1 = 2131364645;
                android.widget.TextView v7_1 = ((android.widget.TextView) l5.s.a(2131364645, v11_5));
                if (v7_1 != null) {
                    androidx.constraintlayout.widget.ConstraintLayout v4_1 = ((androidx.constraintlayout.widget.ConstraintLayout) v11_5);
                    super.a = new CZ.a(v4_1, v5_1, v6_1, v7_1, v4_1);
                    android.view.View.inflate(p10, 2131560378, super);
                    v5_1.addTextChangedListener(new GZ.h(0, super));
                    com.google.android.gms.internal.mlkit_vision_barcode.U7.c(v5_1, v6_1);
                    return;
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v11_5.getResources().getResourceName(v0_1)));
    }

    public final android.widget.TextView getFloatingView()
    {
        return ((android.widget.TextView) this.a.c);
    }

    public final android.view.View getInputTextContainer()
    {
        return ((androidx.constraintlayout.widget.ConstraintLayout) this.a.e);
    }

    public final vn.zalopay.ui.edittext.NumberEditText getInputView()
    {
        return ((vn.zalopay.ui.edittext.NumberEditText) this.a.d);
    }
}
