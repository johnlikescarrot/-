package vn.ai.trueid.sdk.views;
public final class CardMRZMaskView extends Sk.a {
    public float s;
    public final int t;

    public CardMRZMaskView(android.content.Context p10, android.util.AttributeSet p11)
    {
        super(p10, p11);
        super.setWillNotDraw(0);
        super.setLayerType(2, 0);
        if (p11 != 0) {
            super.setMetrics(super.getResources().getDisplayMetrics());
            float v11_1 = p10.obtainStyledAttributes(p11, dk.a.a, 0, 0);
            super.setMaskColor(v11_1.getInt(6, 2131100450));
            super.setDashColor(v11_1.getInt(3, 2131099828));
            super.s = (v11_1.getDimension(7, 0) * super.getMetrics().density);
            super.setCenterX(v11_1.getDimension(0, (((float) super.getMetrics().widthPixels) / 1073741824)));
            super.t = super.getMetrics().widthPixels;
            vn.ai.trueid.sdk.utils.Helpers$Companion v5_18 = v11_1.getDimension(1, (((float) super.getMetrics().heightPixels) / 1120403456));
            float v10_28 = p10.getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
            if (v10_28 == 0) {
                v10_28 = "TRUEID";
            }
            float v10_30;
            if (!kotlin.jvm.internal.Intrinsics.areEqual(v10_28, "hlb")) {
                v10_30 = 40;
            } else {
                v10_30 = 50;
            }
            float v2_5;
            super.setCenterY((v5_18 * ((float) v10_30)));
            super.getMetrics();
            super.getTransparentPaint().setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff$Mode.CLEAR));
            super.getDashPaint().setStyle(android.graphics.Paint$Style.STROKE);
            super.getDashPaint().setColor(v11_1.getInt(3, 2131099828));
            super.getDashPaint().setStrokeWidth(v11_1.getDimension(9, (((float) 5) * super.getMetrics().density)));
            float v10_1 = super.getDashPaint();
            vn.ai.trueid.sdk.utils.Helpers$Companion v5_0 = new float[2];
            v5_0 = {0, 0};
            v10_1.setPathEffect(new android.graphics.DashPathEffect(v5_0, 0));
            float v10_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
            if (!v10_2.getConfigUI$trueid_release().has("defaultSpacing")) {
                v2_5 = 18;
            } else {
                v2_5 = v10_2.getConfigUI$trueid_release().getInt("defaultSpacing");
            }
            float v10_7;
            float v2_7 = vn.ai.trueid.sdk.utils.Helpers.Companion.convertDpToPixel(v2_5);
            if (!v10_2.isTablet$trueid_release()) {
                v10_7 = (((float) super.getMetrics().widthPixels) * 1065353216);
            } else {
                v10_7 = (((float) (super.getMetrics().widthPixels / 3)) * 1073741824);
            }
            float v10_12 = (v10_7 - ((float) (v2_7 * 2)));
            float v2_13 = ((v10_12 / ((float) 1200)) * ((float) 800));
            super.setFrameWidth(v11_1.getDimension(5, v10_12));
            super.setFrameHeight((v11_1.getDimension(4, v2_13) / ((float) 2)));
            super.setTransOff(v11_1.getDimension(9, (super.getMetrics().density * 0)));
            super.setCornerColor(v11_1.getColor(2, -1));
            float v11_4 = new android.graphics.PorterDuffColorFilter(super.getCornerColor(), android.graphics.PorterDuff$Mode.DST_IN);
            if (super.getCornerColor() != -1) {
                v11_4 = new android.graphics.PorterDuffColorFilter(super.getCornerColor(), android.graphics.PorterDuff$Mode.SRC_IN);
            }
            super.getCornerPaint().setColorFilter(v11_4);
            super.a();
            super.setConnerSize((((float) 14) * super.getMetrics().density));
        }
        return;
    }

    public final void a()
    {
        this.setFrame(new android.graphics.RectF((this.getCenterX() - (this.getFrameWidth() / ((float) 2))), (this.getCenterY() - (this.getFrameHeight() / ((float) 2))), ((this.getFrameWidth() / ((float) 2)) + this.getCenterX()), ((this.getFrameHeight() / ((float) 2)) + this.getCenterY())));
        this.setFrameDetect(new android.graphics.RectF(0, (this.getFrame().top - this.getFrame().left), ((float) this.t), (this.getFrame().bottom + this.getFrame().left)));
        return;
    }

    public final void draw(android.graphics.Canvas p4)
    {
        super.draw(p4);
        p4.drawColor(this.getMaskColor());
        p4.drawRoundRect(this.getFrame(), this.s, this.s, this.getDashPaint());
        p4.drawRoundRect(this.getFrame(), this.s, this.s, this.getTransparentPaint());
        android.graphics.Paint v0_4 = this.getContext().getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
        if (v0_4 == null) {
            v0_4 = "TRUEID";
        }
        if (kotlin.jvm.internal.Intrinsics.areEqual(v0_4, "hlb")) {
            android.graphics.Paint v0_7 = new android.graphics.Paint();
            v0_7.setStyle(android.graphics.Paint$Style.STROKE);
            v0_7.setColor(Sk.c.k());
            v0_7.setStrokeWidth(1086324736);
            v0_7.setAntiAlias(1);
            p4.drawRoundRect(this.getFrame(), this.s, this.s, v0_7);
        }
        return;
    }

    public final float getRoundRadius$trueid_release()
    {
        return this.s;
    }

    public final void setRoundRadius$trueid_release(float p1)
    {
        this.s = p1;
        return;
    }
}
