package vn.com.vng.zalopay.bankbinding.presentation.ui.view;
public final class CardCvvExampleView extends androidx.constraintlayout.widget.ConstraintLayout {
    public final Sn.r p;

    public CardCvvExampleView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        Sn.r v2_2 = android.view.LayoutInflater.from(p2).inflate(-2079850463, super, 0);
        super.addView(v2_2);
        super.p = Sn.r.a(v2_2);
        return;
    }

    private final Sn.r getBinding()
    {
        IllegalStateException v0_0 = this.p;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final void setCardStyle(vn.com.vng.zalopay.data.bank.ui.BankStyle p3)
    {
        int v0_3;
        int v0_2 = this.getBinding().b.getBackground();
        if (!(v0_2 instanceof android.graphics.drawable.GradientDrawable)) {
            v0_3 = 0;
        } else {
            v0_3 = ((android.graphics.drawable.GradientDrawable) v0_2);
        }
        if (v0_3 != 0) {
            v0_3.mutate();
        }
        int v1_1 = android.graphics.Color.parseColor(p3.getStartColor());
        int[] v3_2 = android.graphics.Color.parseColor(p3.getEndColor());
        if (v0_3 != 0) {
            v0_3.setColors(new int[] {v1_1, v3_2}));
        }
        return;
    }
}
