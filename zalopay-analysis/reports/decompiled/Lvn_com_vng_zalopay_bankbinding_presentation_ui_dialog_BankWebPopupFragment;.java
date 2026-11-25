package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class BankWebPopupFragment extends Io.d {
    public final a9.f K;
    public final ng.S L;
    public final float M;
    public final boolean N;

    public BankWebPopupFragment()
    {
        this.K = new a9.f(kotlin.jvm.internal.M.a.b(Io.G), new Io.i(this, 8));
        this.L = ng.x.b(new Io.f(5, this));
        this.M = 1060320051;
        this.N = 1;
        return;
    }

    public final boolean A1()
    {
        return this.N;
    }

    public final float B1()
    {
        return this.M;
    }

    public final M3.a C1(android.view.LayoutInflater p8, android.view.ViewGroup p9)
    {
        String v8_2 = p8.inflate(-2079850466, p9, 0);
        int v9_1 = -2079915933;
        android.widget.TextView v3_1 = ((android.widget.TextView) l5.s.a(-2079915933, v8_2));
        if (v3_1 != null) {
            v9_1 = -2079915900;
            vn.zalopay.ui.svgutil.ZPSVGImageView v4_1 = ((vn.zalopay.ui.svgutil.ZPSVGImageView) l5.s.a(-2079915900, v8_2));
            if (v4_1 != null) {
                v9_1 = -2079915813;
                android.widget.TextView v5_1 = ((android.widget.TextView) l5.s.a(-2079915813, v8_2));
                if (v5_1 != null) {
                    v9_1 = -2079915773;
                    vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankMediaWebView v6_1 = ((vn.com.vng.zalopay.bankbinding.presentation.ui.webapp.BankMediaWebView) l5.s.a(-2079915773, v8_2));
                    if (v6_1 != null) {
                        return new Sn.o(((androidx.constraintlayout.widget.ConstraintLayout) v8_2), v3_1, v4_1, v5_1, v6_1);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v8_2.getResources().getResourceName(v9_1)));
    }

    public final vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument E1()
    {
        return ((vn.com.vng.zalopay.bankbinding.presentation.argument.BankWebPopupArgument) this.L.getValue());
    }

    public final void onViewCreated(android.view.View p3, android.os.Bundle p4)
    {
        super.onViewCreated(p3, p4);
        vn.zalopay.ui.svgutil.ZPSVGImageView v3_2 = ((Sn.o) this.y1());
        com.google.android.gms.internal.mlkit_vision_barcode.W7.d(v3_2.b, this.E1().getEnableFaq());
        this.s1(0);
        Io.F v4_2 = v3_2.d;
        int v0_1 = this.E1().getConfirmButton();
        if (v0_1.length() <= 0) {
            v0_1 = 0;
        }
        if (v0_1 == 0) {
            v0_1 = this.getString(-2079719390);
        }
        v4_2.setText(v0_1);
        Io.F v4_4 = new Qo.a(this.E1().getUrl(), v3_2.e);
        vn.zalopay.ui.svgutil.ZPSVGImageView v3_4 = v4_4.b;
        v3_4.setWebViewClient(v4_4);
        v3_4.resumeTimers();
        v3_4.loadUrl(v4_4.a);
        ((Sn.o) this.y1()).d.setOnClickListener(new Io.F(this, 0));
        ((Sn.o) this.y1()).b.setOnClickListener(new Io.F(this, 1));
        ((Sn.o) this.y1()).c.setOnClickListener(new Io.F(this, 2));
        return;
    }
}
