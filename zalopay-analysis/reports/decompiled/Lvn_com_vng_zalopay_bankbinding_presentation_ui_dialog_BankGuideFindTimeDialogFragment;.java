package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class BankGuideFindTimeDialogFragment extends DZ.b {
    public Sn.k H;
    public final ng.S I;
    public final a9.f J;
    public final ng.S K;

    public BankGuideFindTimeDialogFragment()
    {
        this.I = ng.x.b(new Io.y(this, 0));
        this.J = new a9.f(kotlin.jvm.internal.M.a.b(Io.z), new Io.i(this, 5));
        this.K = ng.x.b(new Io.y(this, 1));
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p8, android.view.ViewGroup p9, android.os.Bundle p10)
    {
        androidx.constraintlayout.widget.ConstraintLayout v8_1 = p8.inflate(-2079850473, p9, 0);
        int v9_1 = -2079915943;
        androidx.appcompat.widget.AppCompatImageView v2_1 = ((androidx.appcompat.widget.AppCompatImageView) l5.s.a(-2079915943, v8_1));
        if (v2_1 != null) {
            v9_1 = -2079915942;
            androidx.appcompat.widget.AppCompatButton v3_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915942, v8_1));
            if (v3_1 != null) {
                v9_1 = -2079915801;
                androidx.appcompat.widget.AppCompatTextView v4_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915801, v8_1));
                if (v4_1 != null) {
                    v9_1 = -2079915783;
                    androidx.appcompat.widget.AppCompatTextView v5_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915783, v8_1));
                    if (v5_1 != null) {
                        v9_1 = -2079915780;
                        vn.com.vng.zalopay.bankbinding.presentation.ui.view.CardValidatedDateGuideLineView v6_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.CardValidatedDateGuideLineView) l5.s.a(-2079915780, v8_1));
                        if (v6_1 != null) {
                            this.H = new Sn.k(((androidx.constraintlayout.widget.ConstraintLayout) v8_1), v2_1, v3_1, v4_1, v5_1, v6_1);
                            return this.x1().a;
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v8_1.getResources().getResourceName(v9_1)));
    }

    public final void onDestroyView()
    {
        this.H = 0;
        super.onDestroyView();
        return;
    }

    public final void onViewCreated(android.view.View p3, android.os.Bundle p4)
    {
        super.onViewCreated(p3, p4);
        this.x1().f.a();
        this.x1().f.postDelayed(new Io.k(1, this), 200);
        this.x1().f.setCardStyle(this.z1().getBankStyle());
        this.x1().c.setText(this.z1().getRetryButtonText());
        this.x1().e.setText(this.z1().getTitle());
        com.google.android.gms.internal.mlkit_vision_barcode.V7.e(this.x1().d, this.z1().getMessage());
        this.x1().b.setOnClickListener(new Io.x(this, 0));
        this.x1().c.setOnClickListener(new Io.x(this, 1));
        return;
    }

    public final Sn.k x1()
    {
        IllegalStateException v0_0 = this.H;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final Sn.w y1()
    {
        return ((Sn.w) this.I.getValue());
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument z1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankGuideFindTimeDialogArgument) this.K.getValue());
    }
}
