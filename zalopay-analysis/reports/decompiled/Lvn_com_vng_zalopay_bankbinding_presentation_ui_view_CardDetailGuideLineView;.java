package vn.com.vng.zalopay.bankbinding.presentation.ui.view;
public final class CardDetailGuideLineView extends androidx.constraintlayout.widget.ConstraintLayout {
    public final Sn.s p;

    public CardDetailGuideLineView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        Sn.s v2_2 = android.view.LayoutInflater.from(p2).inflate(-2079850462, super, 0);
        super.addView(v2_2);
        super.p = Sn.s.a(v2_2);
        return;
    }

    private final Sn.s getBinding()
    {
        IllegalStateException v0_0 = this.p;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final void setCardStyle(vn.com.vng.zalopay.data.bank.ui.BankStyle p4)
    {
        this.getBinding().c.setImageName(p4.getIconName().toLowerCase(java.util.Locale.ROOT));
        int v0_2 = this.getBinding().e;
        int v1_0 = p4.getName();
        if (v1_0 == 0) {
            v1_0 = "";
        }
        int v0_7;
        v0_2.setText(v1_0);
        int v0_6 = this.getBinding().b.getBackground();
        if (!(v0_6 instanceof android.graphics.drawable.GradientDrawable)) {
            v0_7 = 0;
        } else {
            v0_7 = ((android.graphics.drawable.GradientDrawable) v0_6);
        }
        if (v0_7 != 0) {
            v0_7.mutate();
        }
        int v1_3 = android.graphics.Color.parseColor(p4.getStartColor());
        int[] v4_2 = android.graphics.Color.parseColor(p4.getEndColor());
        if (v0_7 != 0) {
            v0_7.setColors(new int[] {v1_3, v4_2}));
        }
        return;
    }
}
