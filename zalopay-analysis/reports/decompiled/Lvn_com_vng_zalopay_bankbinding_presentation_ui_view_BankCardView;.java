package vn.com.vng.zalopay.bankbinding.presentation.ui.view;
public final class BankCardView extends androidx.constraintlayout.widget.ConstraintLayout {
    public final Sn.t p;
    public final s5.g q;

    public BankCardView(android.content.Context p9, android.util.AttributeSet p10)
    {
        super(p9, p10, 0);
        super.q = new s5.g();
        String v9_5 = android.view.LayoutInflater.from(p9).inflate(-2079850461, super, 0);
        super.addView(v9_5);
        int v10_1 = -2079915938;
        androidx.appcompat.widget.AppCompatTextView v3_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915938, v9_5));
        if (v3_1 != null) {
            v10_1 = -2079915921;
            if (((android.widget.LinearLayout) l5.s.a(-2079915921, v9_5)) != null) {
                v10_1 = -2079915896;
                com.facebook.drawee.view.SimpleDraweeView v4_1 = ((com.facebook.drawee.view.SimpleDraweeView) l5.s.a(-2079915896, v9_5));
                if (v4_1 != null) {
                    android.widget.RelativeLayout v2_1 = ((android.widget.RelativeLayout) v9_5);
                    v10_1 = -2079915816;
                    androidx.appcompat.widget.AppCompatTextView v6_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915816, v9_5));
                    if (v6_1 != null) {
                        v10_1 = -2079915794;
                        androidx.appcompat.widget.AppCompatTextView v7_1 = ((androidx.appcompat.widget.AppCompatTextView) l5.s.a(-2079915794, v9_5));
                        if (v7_1 != null) {
                            super.p = new Sn.t(v2_1, v3_1, v4_1, v2_1, v6_1, v7_1);
                            return;
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(v9_5.getResources().getResourceName(v10_1)));
    }

    private final Sn.t getBinding()
    {
        IllegalStateException v0_0 = this.p;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final void setCardNumber(String p3)
    {
        this.getBinding().e.setText(this.q.b(p3));
        return;
    }

    public final void setCardStyle(vn.com.vng.zalopay.data.bank.ui.BankStyle p5)
    {
        boolean v1_0;
        int v0_4 = this.getBinding().c;
        boolean v1_2 = p5.getIconUrl();
        android.content.Context v2 = this.getContext();
        android.graphics.drawable.GradientDrawable v3_1 = 0;
        if (!v1_2) {
            v1_0 = 0;
        } else {
            v1_0 = android.net.Uri.parse(v1_2);
        }
        v0_4.e(v1_0, v2);
        int v0_3 = this.getBinding().d.getBackground();
        if ((v0_3 instanceof android.graphics.drawable.GradientDrawable)) {
            v3_1 = ((android.graphics.drawable.GradientDrawable) v0_3);
        }
        int[] v5_3 = new int[] {android.graphics.Color.parseColor(p5.getStartColor()), android.graphics.Color.parseColor(p5.getEndColor())});
        if (v3_1 != null) {
            v3_1.mutate();
        }
        if (v3_1 != null) {
            v3_1.setColors(v5_3);
        }
        return;
    }

    public final void setLabel(String p2)
    {
        this.getBinding().f.setText(p2);
        return;
    }

    public final void setVisibleEditButton(boolean p2)
    {
        int v2_1;
        androidx.appcompat.widget.AppCompatTextView v0_0 = this.getBinding();
        if (p2 == 0) {
            v2_1 = 8;
        } else {
            v2_1 = 0;
        }
        v0_0.b.setVisibility(v2_1);
        return;
    }
}
