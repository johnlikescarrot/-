package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class CvvGuideDialogFragment extends Io.P {
    public Sn.i H;
    public final ng.S I;
    public final a9.f J;
    public final ng.S K;

    public CvvGuideDialogFragment()
    {
        this.I = ng.x.b(new Io.H(this, 0));
        this.J = new a9.f(kotlin.jvm.internal.M.a.b(Io.I), new Io.i(this, 9));
        this.K = ng.x.b(new Io.H(this, 1));
        return;
    }

    public final android.view.View onCreateView(android.view.LayoutInflater p8, android.view.ViewGroup p9, android.os.Bundle p10)
    {
        androidx.constraintlayout.widget.ConstraintLayout v8_1 = p8.inflate(-2079850471, p9, 0);
        int v9_1 = -2079915942;
        androidx.appcompat.widget.AppCompatButton v2_1 = ((androidx.appcompat.widget.AppCompatButton) l5.s.a(-2079915942, v8_1));
        if (v2_1 != null) {
            v9_1 = -2079915884;
            android.view.View v3 = l5.s.a(-2079915884, v8_1);
            if (v3 != null) {
                v9_1 = -2079915793;
                androidx.appcompat.widget.AppCompatTextView v4_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915793, v8_1));
                if (v4_1 != null) {
                    v9_1 = -2079915783;
                    if (((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915783, v8_1)) != null) {
                        v9_1 = -2079915779;
                        vn.com.vng.zalopay.bankbinding.presentation.ui.view.CardCvvExampleView v5_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.CardCvvExampleView) l5.s.a(-2079915779, v8_1));
                        if (v5_1 != null) {
                            this.H = new Sn.i(((androidx.constraintlayout.widget.ConstraintLayout) v8_1), v2_1, v3, v4_1, v5_1, 1);
                            return this.x1().b;
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

    public final void onViewCreated(android.view.View p4, android.os.Bundle p5)
    {
        super.onViewCreated(p4, p5);
        Io.A v5_6 = this.K;
        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(((androidx.appcompat.widget.AppCompatTextView) this.x1().e), ((vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument) v5_6.getValue()).getMessage());
        ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.CardCvvExampleView) this.x1().f).postDelayed(new Io.k(2, this), 200);
        ((vn.com.vng.zalopay.bankbinding.presentation.ui.view.CardCvvExampleView) this.x1().f).setCardStyle(((vn.com.vng.zalopay.bankbinding.presentation.argument.CvvDialogArgument) v5_6.getValue()).getBankStyle());
        ((androidx.appcompat.widget.AppCompatButton) this.x1().c).setOnClickListener(new Io.A(1, this));
        return;
    }

    public final Sn.i x1()
    {
        IllegalStateException v0_0 = this.H;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final Sn.r y1()
    {
        return ((Sn.r) this.I.getValue());
    }
}
