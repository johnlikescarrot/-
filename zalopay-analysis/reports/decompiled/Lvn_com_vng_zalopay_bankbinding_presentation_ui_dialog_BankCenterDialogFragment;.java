package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class BankCenterDialogFragment extends Io.e {
    public final a9.f I;
    public final ng.S J;

    public BankCenterDialogFragment()
    {
        this.I = new a9.f(kotlin.jvm.internal.M.a.b(Io.p), new Io.i(this, 2));
        this.J = ng.x.b(new Io.f(1, this));
        return;
    }

    public final M3.a x1(android.view.LayoutInflater p10, android.view.ViewGroup p11)
    {
        String v10_1 = p10.inflate(-2079850489, p11, 0);
        int v11_1 = -2079915944;
        androidx.appcompat.widget.AppCompatButton v3_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915944, v10_1));
        if (v3_1 != null) {
            v11_1 = -2079915942;
            androidx.appcompat.widget.AppCompatButton v4_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915942, v10_1));
            if (v4_1 != null) {
                v11_1 = -2079915895;
                android.widget.ImageView v5_1 = ((android.widget.ImageView) l5.s.a(-2079915895, v10_1));
                if (v5_1 != null) {
                    v11_1 = -2079915893;
                    android.widget.ImageView v6_1 = ((android.widget.ImageView) l5.s.a(-2079915893, v10_1));
                    if (v6_1 != null) {
                        v11_1 = -2079915877;
                        if (((android.widget.LinearLayout) l5.s.a(-2079915877, v10_1)) != null) {
                            v11_1 = -2079915793;
                            android.widget.TextView v7_1 = ((android.widget.TextView) l5.s.a(-2079915793, v10_1));
                            if (v7_1 != null) {
                                v11_1 = -2079915783;
                                android.widget.TextView v8_1 = ((android.widget.TextView) l5.s.a(-2079915783, v10_1));
                                if (v8_1 != null) {
                                    return new Sn.d(((androidx.constraintlayout.widget.ConstraintLayout) v10_1), v3_1, v4_1, v5_1, v6_1, v7_1, v8_1);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v10_1.getResources().getResourceName(v11_1)));
    }

    public final void y1()
    {
        androidx.appcompat.widget.AppCompatButton v0_7 = ((Sn.d) this.w1()).e;
        Io.o v1_14 = this.z1().getDrawableIcon();
        int v2_3 = this.requireContext();
        if (v1_14 != null) {
            try {
                v0_7.setImageDrawable(v2_3.getDrawable(v1_14));
            } catch (Throwable) {
            }
            v0_7.setVisibility(0);
        } else {
            v0_7.setVisibility(8);
        }
        com.google.android.gms.internal.mlkit_vision_barcode.V7.e(((Sn.d) this.w1()).g, this.z1().getTitle());
        com.google.android.gms.internal.mlkit_vision_barcode.V7.e(((Sn.d) this.w1()).b, this.z1().getCancelButton());
        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(((Sn.d) this.w1()).f, this.z1().getMessage());
        ((Sn.d) this.w1()).c.setText(this.z1().getConfirmButton());
        ((Sn.d) this.w1()).d.setOnClickListener(new Io.o(this, 0));
        ((Sn.d) this.w1()).c.setOnClickListener(new Io.o(this, 1));
        ((Sn.d) this.w1()).b.setOnClickListener(new Io.o(this, 2));
        return;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument z1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCenterDialogArgument) this.J.getValue());
    }
}
