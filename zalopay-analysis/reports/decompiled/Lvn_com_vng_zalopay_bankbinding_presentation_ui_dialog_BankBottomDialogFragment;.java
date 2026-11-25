package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class BankBottomDialogFragment extends Io.X {
    public Sn.c H;
    public final a9.f I;
    public final ng.S J;

    public BankBottomDialogFragment()
    {
        this.I = new a9.f(kotlin.jvm.internal.M.a.b(Io.j), new Io.i(this, 0));
        this.J = ng.x.b(new Io.f(0, this));
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p9, android.view.ViewGroup p10, android.os.Bundle p11)
    {
        androidx.constraintlayout.widget.ConstraintLayout v9_1 = p9.inflate(-2079850490, p10, 0);
        int v10_1 = -2079915948;
        android.widget.TextView v2_1 = ((android.widget.TextView) l5.s.a(-2079915948, v9_1));
        if (v2_1 != null) {
            v10_1 = -2079915947;
            android.widget.TextView v3_1 = ((android.widget.TextView) l5.s.a(-2079915947, v9_1));
            if (v3_1 != null) {
                v10_1 = -2079915898;
                androidx.appcompat.widget.AppCompatImageView v4_1 = ((androidx.appcompat.widget.AppCompatImageView) l5.s.a(-2079915898, v9_1));
                if (v4_1 != null) {
                    v10_1 = -2079915897;
                    vn.zalopay.ui.svgutil.ZPSVGImageView v5_1 = ((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(-2079915897, v9_1));
                    if (v5_1 != null) {
                        v10_1 = -2079915824;
                        android.widget.TextView v6_1 = ((android.widget.TextView) l5.s.a(-2079915824, v9_1));
                        if (v6_1 != null) {
                            v10_1 = -2079915783;
                            android.widget.TextView v7_1 = ((android.widget.TextView) l5.s.a(-2079915783, v9_1));
                            if (v7_1 != null) {
                                this.H = new Sn.c(((androidx.constraintlayout.widget.ConstraintLayout) v9_1), v2_1, v3_1, v4_1, v5_1, v6_1, v7_1);
                                return ((androidx.constraintlayout.widget.ConstraintLayout) this.y1().b);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v9_1.getResources().getResourceName(v10_1)));
    }

    public final void onDestroyView()
    {
        this.H = 0;
        super.onDestroyView();
        return;
    }

    public final void onViewCreated(android.view.View p2, android.os.Bundle p3)
    {
        super.onViewCreated(p2, p3);
        if (this.x1().getIcon() == 0) {
            ((androidx.appcompat.widget.AppCompatImageView) this.y1().c).setVisibility(8);
        } else {
            com.google.android.gms.internal.mlkit_vision_barcode.W7.d(((androidx.appcompat.widget.AppCompatImageView) this.y1().c), 1);
            ((androidx.appcompat.widget.AppCompatImageView) this.y1().c).setImageResource(this.x1().getIcon());
        }
        com.google.android.gms.internal.mlkit_vision_barcode.V7.e(((android.widget.TextView) this.y1().g), this.x1().getTitle());
        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(this.y1().f, this.x1().getMessage());
        android.widget.TextView v2_15 = this.x1().getConfirmButtonText();
        if (v2_15 != null) {
            this.y1().d.setText(v2_15);
        }
        this.y1().d.setOnClickListener(new Io.h(this, 0));
        ((vn.zalopay.ui.svgutil.ZPSVGImageView) this.y1().h).setOnClickListener(new Io.h(this, 1));
        this.y1().e.setOnClickListener(new Io.h(this, 2));
        return;
    }

    public final android.app.Dialog q1(android.os.Bundle p3)
    {
        android.app.Dialog v3_1 = super.q1(p3);
        v3_1.setOnShowListener(new Io.g(0));
        return v3_1;
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument x1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankBottomDialogArgument) this.J.getValue());
    }

    public final Sn.c y1()
    {
        IllegalStateException v0_0 = this.H;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }
}
