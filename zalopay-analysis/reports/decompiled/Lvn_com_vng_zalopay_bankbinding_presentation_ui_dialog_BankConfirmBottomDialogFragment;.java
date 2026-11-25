package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class BankConfirmBottomDialogFragment extends Io.d {
    public final a9.f K;
    public final ng.S L;

    public BankConfirmBottomDialogFragment()
    {
        this.K = new a9.f(kotlin.jvm.internal.M.a.b(Io.r), new Io.i(this, 3));
        this.L = ng.x.b(new Io.f(2, this));
        return;
    }

    public final M3.a C1(android.view.LayoutInflater p9, android.view.ViewGroup p10)
    {
        String v9_1 = p9.inflate(-2079850488, p10, 0);
        int v10_1 = -2079915948;
        android.widget.TextView v3_1 = ((android.widget.TextView) l5.s.a(-2079915948, v9_1));
        if (v3_1 != null) {
            v10_1 = -2079915890;
            androidx.appcompat.widget.AppCompatImageView v4_1 = ((androidx.appcompat.widget.AppCompatImageView) l5.s.a(-2079915890, v9_1));
            if (v4_1 != null) {
                v10_1 = -2079915840;
                vn.zalopay.ui.svgutil.ZPSVGImageView v5_1 = ((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(-2079915840, v9_1));
                if (v5_1 != null) {
                    v10_1 = -2079915824;
                    android.widget.TextView v6_1 = ((android.widget.TextView) l5.s.a(-2079915824, v9_1));
                    if (v6_1 != null) {
                        v10_1 = -2079915783;
                        android.widget.TextView v7_1 = ((android.widget.TextView) l5.s.a(-2079915783, v9_1));
                        if (v7_1 != null) {
                            return new Sn.e(((androidx.constraintlayout.widget.ConstraintLayout) v9_1), v3_1, v4_1, v5_1, v6_1, v7_1);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v9_1.getResources().getResourceName(v10_1)));
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument E1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankConfirmBottomDialogArgument) this.L.getValue());
    }

    public final void onViewCreated(android.view.View p2, android.os.Bundle p3)
    {
        super.onViewCreated(p2, p3);
        if (this.E1().getIcon() == 0) {
            ((Sn.e) this.y1()).c.setVisibility(8);
        } else {
            com.google.android.gms.internal.mlkit_vision_barcode.W7.d(((Sn.e) this.y1()).c, 1);
            ((Sn.e) this.y1()).c.setImageResource(this.E1().getIcon());
        }
        com.google.android.gms.internal.mlkit_vision_barcode.V7.e(((Sn.e) this.y1()).f, this.E1().getTitle());
        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(((Sn.e) this.y1()).e, this.E1().getMessage());
        ((Sn.e) this.y1()).b.setText(this.E1().getConfirmButtonText());
        ((Sn.e) this.y1()).b.setOnClickListener(new Io.q(this, 0));
        ((Sn.e) this.y1()).d.setOnClickListener(new Io.q(this, 1));
        return;
    }

    public final boolean z1()
    {
        return 0;
    }
}
