package vn.com.vng.zalopay.bankbinding.presentation.ui.view;
public final class CardValidatedDateGuideLineView extends android.widget.RelativeLayout {
    public final Sn.w a;

    public CardValidatedDateGuideLineView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3, 0);
        Sn.w v2_2 = android.view.LayoutInflater.from(p2).inflate(-2079850457, super, 0);
        super.addView(v2_2);
        super.a = Sn.w.a(v2_2);
        return;
    }

    private final Sn.w getBinding()
    {
        IllegalStateException v0_0 = this.a;
        if (v0_0 == null) {
            throw new IllegalStateException("Required value was null.");
        } else {
            return v0_0;
        }
    }

    public final void a()
    {
        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(this.getBinding().k, this.getContext().getString(-2079719325));
        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(this.getBinding().i, this.getContext().getString(-2079719326));
        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(this.getBinding().h, this.getContext().getString(-2079719367));
        com.google.android.gms.internal.mlkit_vision_barcode.V7.c(this.getBinding().g, this.getContext().getString(-2079719369));
        this.getBinding().k.setVisibility(8);
        this.getBinding().i.setVisibility(8);
        return;
    }

    public final void setCardStyle(vn.com.vng.zalopay.data.bank.ui.BankStyle p4)
    {
        this.getBinding().c.setImageName(p4.getIconName().toLowerCase(java.util.Locale.ROOT));
        int v0_2 = this.getBinding().f;
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
