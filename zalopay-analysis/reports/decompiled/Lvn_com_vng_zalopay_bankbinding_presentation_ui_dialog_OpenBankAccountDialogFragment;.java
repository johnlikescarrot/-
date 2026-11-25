package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class OpenBankAccountDialogFragment extends Io.d {
    public final a9.f K;
    public final ng.S L;

    public OpenBankAccountDialogFragment()
    {
        this.K = new a9.f(kotlin.jvm.internal.M.a.b(Io.U), new Io.i(this, 12));
        this.L = ng.x.b(new Io.f(6, this));
        return;
    }

    public final M3.a C1(android.view.LayoutInflater p9, android.view.ViewGroup p10)
    {
        String v9_1 = p9.inflate(-2079850493, p10, 0);
        int v10_1 = -2079915945;
        androidx.appcompat.widget.AppCompatButton v3_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915945, v9_1));
        if (v3_1 != null) {
            v10_1 = -2079915943;
            android.widget.ImageView v4_1 = ((android.widget.ImageView) l5.s.a(-2079915943, v9_1));
            if (v4_1 != null) {
                v10_1 = -2079915939;
                androidx.appcompat.widget.AppCompatButton v5_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915939, v9_1));
                if (v5_1 != null) {
                    v10_1 = -2079915888;
                    if (((androidx.appcompat.widget.AppCompatImageView) l5.s.a(-2079915888, v9_1)) != null) {
                        v10_1 = -2079915882;
                        if (((android.widget.LinearLayout) l5.s.a(-2079915882, v9_1)) != null) {
                            v10_1 = -2079915793;
                            android.widget.TextView v6_1 = ((android.widget.TextView) l5.s.a(-2079915793, v9_1));
                            if (v6_1 != null) {
                                v10_1 = -2079915783;
                                android.widget.TextView v7_1 = ((android.widget.TextView) l5.s.a(-2079915783, v9_1));
                                if (v7_1 != null) {
                                    return new Sn.a(((androidx.constraintlayout.widget.ConstraintLayout) v9_1), v3_1, v4_1, v5_1, v6_1, v7_1);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v9_1.getResources().getResourceName(v10_1)));
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.OpenBankAccountDialogArgument E1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.OpenBankAccountDialogArgument) this.L.getValue());
    }

    public final void onViewCreated(android.view.View p3, android.os.Bundle p4)
    {
        super.onViewCreated(p3, p4);
        this.s1(0);
        android.widget.ImageView v3_4 = ((Sn.a) this.y1());
        v3_4.f.setText(this.E1().getTitle());
        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(v3_4.e, this.E1().getMessage());
        v3_4.b.setOnClickListener(new Io.Q(this, 0));
        v3_4.d.setOnClickListener(new Io.Q(this, 1));
        v3_4.c.setOnClickListener(new Io.Q(this, 2));
        return;
    }
}
