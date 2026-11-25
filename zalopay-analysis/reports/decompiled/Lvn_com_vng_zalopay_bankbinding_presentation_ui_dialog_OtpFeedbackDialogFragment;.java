package vn.com.vng.zalopay.bankbinding.presentation.ui.dialog;
public final class OtpFeedbackDialogFragment extends Io.d {
    public final a9.f K;
    public final ng.S L;

    public OtpFeedbackDialogFragment()
    {
        this.K = new a9.f(kotlin.jvm.internal.M.a.b(Io.W), new Io.i(this, 13));
        this.L = ng.x.b(new Io.f(7, this));
        return;
    }

    public final M3.a C1(android.view.LayoutInflater p8, android.view.ViewGroup p9)
    {
        String v8_2 = p8.inflate(-2079850470, p9, 0);
        int v9_1 = -2079915929;
        androidx.appcompat.widget.AppCompatImageView v3_1 = ((androidx.appcompat.widget.AppCompatImageView) l5.s.a(-2079915929, v8_2));
        if (v3_1 != null) {
            v9_1 = -2079915925;
            android.widget.LinearLayout v4_1 = ((android.widget.LinearLayout) l5.s.a(-2079915925, v8_2));
            if (v4_1 != null) {
                androidx.core.widget.NestedScrollView v2_1 = ((androidx.core.widget.NestedScrollView) v8_2);
                v9_1 = -2079915846;
                android.view.View v5 = l5.s.a(-2079915846, v8_2);
                if (v5 != null) {
                    v9_1 = -2079915833;
                    android.widget.TextView v6_1 = ((android.widget.TextView) l5.s.a(-2079915833, v8_2));
                    if (v6_1 != null) {
                        return new Sn.l(v2_1, v3_1, v4_1, v5, v6_1);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v8_2.getResources().getResourceName(v9_1)));
    }

    public final void onViewCreated(android.view.View p5, android.os.Bundle p6)
    {
        super.onViewCreated(p5, p6);
        q9.p0.a(this);
        android.widget.LinearLayout v5_2 = ((Sn.l) this.y1());
        v5_2.b.setOnClickListener(new Io.A(2, this));
        Po.t v0_1 = new Po.t(this.requireContext());
        Po.t.g(v0_1, kotlin.collections.y.toList(this.getResources().getStringArray(-2080243712)));
        Po.t.f(v0_1).b.setLines(3);
        Io.V v6_8 = Po.t.f(v0_1).d;
        String v1_3 = new android.widget.LinearLayout$LayoutParams(-1, -2);
        v1_3.setMargins(0, 8, 0, 0);
        v6_8.setLayoutParams(v1_3);
        v6_8.setText(this.getString(-2079719415));
        v0_1.r = new Io.V(0, this);
        v5_2.c.addView(v0_1);
        return;
    }
}
