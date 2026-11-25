package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class ToroFeedbackConfirmationFragment extends Io.d {

    public ToroFeedbackConfirmationFragment()
    {
        return;
    }

    public final M3.a C1(android.view.LayoutInflater p8, android.view.ViewGroup p9)
    {
        String v8_2 = p8.inflate(-2079850465, p9, 0);
        int v9_1 = -2079915971;
        androidx.appcompat.widget.AppCompatButton v3_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915971, v8_2));
        if (v3_1 != null) {
            v9_1 = -2079915929;
            androidx.appcompat.widget.AppCompatImageView v4_1 = ((androidx.appcompat.widget.AppCompatImageView) l5.s.a(-2079915929, v8_2));
            if (v4_1 != null) {
                v9_1 = -2079915915;
                android.widget.TextView v5_1 = ((android.widget.TextView) l5.s.a(-2079915915, v8_2));
                if (v5_1 != null) {
                    v9_1 = -2079915833;
                    android.widget.TextView v6_1 = ((android.widget.TextView) l5.s.a(-2079915833, v8_2));
                    if (v6_1 != null) {
                        return new Sn.p(((androidx.constraintlayout.widget.ConstraintLayout) v8_2), v3_1, v4_1, v5_1, v6_1);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v8_2.getResources().getResourceName(v9_1)));
    }

    public final void onViewCreated(android.view.View p3, android.os.Bundle p4)
    {
        super.onViewCreated(p3, p4);
        q9.p0.a(this);
        androidx.appcompat.widget.AppCompatButton v3_3 = ((Sn.p) this.y1());
        v3_3.e.setText(this.getString(-2079719259));
        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(v3_3.d, this.getString(-2079719260));
        v3_3.c.setOnClickListener(new Io.Z(this, 0));
        v3_3.b.setOnClickListener(new Io.Z(this, 1));
        return;
    }
}
