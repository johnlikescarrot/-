package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class BankCardGuideDialogFragment extends DZ.b {
    public Sn.c H;
    public final ng.S I;
    public final a9.f J;
    public final ng.S K;

    public BankCardGuideDialogFragment()
    {
        this.I = ng.x.b(new Io.m(this, 0));
        this.J = new a9.f(kotlin.jvm.internal.M.a.b(Io.n), new Io.i(this, 1));
        this.K = ng.x.b(new Io.m(this, 1));
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p9, android.view.ViewGroup p10, android.os.Bundle p11)
    {
        androidx.constraintlayout.widget.ConstraintLayout v9_1 = p9.inflate(-2079850475, p10, 0);
        int v10_1 = -2079915943;
        androidx.appcompat.widget.AppCompatImageView v2_1 = ((androidx.appcompat.widget.AppCompatImageView) l5.s.a(-2079915943, v9_1));
        if (v2_1 != null) {
            v10_1 = -2079915935;
            androidx.appcompat.widget.AppCompatButton v3_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915935, v9_1));
            if (v3_1 != null) {
                v10_1 = -2079915801;
                androidx.appcompat.widget.AppCompatTextView v4_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915801, v9_1));
                if (v4_1 != null) {
                    v10_1 = -2079915785;
                    androidx.appcompat.widget.AppCompatTextView v5_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915785, v9_1));
                    if (v5_1 != null) {
                        v10_1 = -2079915783;
                        androidx.appcompat.widget.AppCompatTextView v6_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915783, v9_1));
                        if (v6_1 != null) {
                            v10_1 = -2079915780;
                            vn.com.vng.zalopay.bankbinding.presentation.ui.view.CardDetailGuideLineView v7_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.CardDetailGuideLineView) l5.s.a(-2079915780, v9_1));
                            if (v7_1 != null) {
                                this.H = new Sn.c(((androidx.constraintlayout.widget.ConstraintLayout) v9_1), v2_1, v3_1, v4_1, v5_1, v6_1, v7_1);
                                return ((androidx.constraintlayout.widget.ConstraintLayout) this.x1().b);
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

    public final void onViewCreated(android.view.View p3, android.os.Bundle p4)
    {
        super.onViewCreated(p3, p4);
        ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.CardDetailGuideLineView) this.x1().h).postDelayed(new Io.k(0, this), 200);
        ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.CardDetailGuideLineView) this.x1().h).setCardStyle(this.y1().getBankStyle());
        androidx.appcompat.widget.AppCompatButton v3_6 = this.y1().getRetryButtonText();
        if (v3_6 != null) {
            ((androidx.appcompat.widget.AppCompatButton) this.x1().d).setText(v3_6);
        }
        ((androidx.appcompat.widget.AppCompatTextView) this.x1().g).setText(this.y1().getTitle());
        com.google.android.gms.internal.mlkit_vision_barcode.V7.e(((androidx.appcompat.widget.AppCompatTextView) this.x1().f), this.y1().getBoldMessage());
        com.google.android.gms.internal.mlkit_vision_barcode.V7.e(((androidx.appcompat.widget.AppCompatTextView) this.x1().e), this.y1().getMessage());
        if (((androidx.appcompat.widget.AppCompatTextView) this.x1().f).getVisibility() == 8) {
            ((android.view.ViewGroup$MarginLayoutParams) ((androidx.appcompat.widget.AppCompatTextView) this.x1().e).getLayoutParams()).setMargins(0, ((int) eQ.a.b(this.requireContext(), 16)), 0, 0);
        }
        ((androidx.appcompat.widget.AppCompatImageView) this.x1().c).setOnClickListener(new Io.l(this, 0));
        ((androidx.appcompat.widget.AppCompatButton) this.x1().d).setOnClickListener(new Io.l(this, 1));
        return;
    }

    public final Sn.c x1()
    {
        IllegalStateException v0_0 = this.H;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument y1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankCardGuideDialogArgument) this.K.getValue());
    }
}
