package vn.ai.trueid.sdk.views;
public final class CardMaskView extends Sk.a {
    public boolean s;
    public boolean t;
    public float u;
    public final int v;
    public final ng.S w;

    public CardMaskView(android.content.Context p11, android.util.AttributeSet p12)
    {
        super(p11, p12);
        super.w = ng.x.b(new Q0.L(3, super));
        super.setWillNotDraw(0);
        super.setLayerType(2, 0);
        if (p12 != 0) {
            float v7_15;
            super.setMetrics(super.getResources().getDisplayMetrics());
            float v12_7 = p11.obtainStyledAttributes(p12, dk.a.a, 0, 0);
            super.setMaskColor(v12_7.getInt(6, 2131100450));
            super.setDashColor(v12_7.getInt(3, 2131099828));
            super.u = (v12_7.getDimension(7, 0) * super.getMetrics().density);
            super.setCenterX(v12_7.getDimension(0, (((float) super.getMetrics().widthPixels) / 1073741824)));
            super.v = super.getMetrics().widthPixels;
            android.graphics.PorterDuff$Mode v4_17 = v12_7.getDimension(1, (((float) super.getMetrics().heightPixels) / 1120403456));
            if (!kotlin.jvm.internal.Intrinsics.areEqual(super.getVendorId(), "hlb")) {
                v7_15 = 40;
            } else {
                v7_15 = 50;
            }
            float v7_5;
            super.setCenterY((v4_17 * ((float) v7_15)));
            super.getMetrics();
            super.getTransparentPaint().setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff$Mode.CLEAR));
            super.getDashPaint().setStyle(android.graphics.Paint$Style.STROKE);
            super.getDashPaint().setColor(v12_7.getInt(3, 2131099828));
            super.getDashPaint().setStrokeWidth(v12_7.getDimension(9, (((float) 5) * super.getMetrics().density)));
            int v3_1 = super.getDashPaint();
            vn.ai.trueid.sdk.utils.Helpers$Companion v9_0 = new float[2];
            v9_0 = {0, 0};
            v3_1.setPathEffect(new android.graphics.DashPathEffect(v9_0, 0));
            int v3_2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
            if (!v3_2.getConfigUI$trueid_release().has("defaultSpacing")) {
                v7_5 = 18;
            } else {
                v7_5 = v3_2.getConfigUI$trueid_release().getInt("defaultSpacing");
            }
            android.graphics.Canvas v1_3;
            float v7_7 = vn.ai.trueid.sdk.utils.Helpers.Companion.convertDpToPixel(v7_5);
            if (!v3_2.isTablet$trueid_release()) {
                v1_3 = (((float) super.getMetrics().widthPixels) * 1065353216);
            } else {
                v1_3 = (((float) (super.getMetrics().widthPixels / 3)) * 1073741824);
            }
            float v6_3;
            float v7_9;
            android.graphics.Canvas v1_5 = (v1_3 - ((float) (v7_7 * 2)));
            int v3_11 = kotlin.jvm.internal.Intrinsics.areEqual(super.getVendorId(), "bvb");
            if (v3_11 == 0) {
                v6_3 = (v1_5 / ((float) 1586));
                v7_9 = 1000;
            } else {
                v6_3 = (v1_5 / ((float) 384));
                v7_9 = 249;
            }
            float v6_6 = (v6_3 * ((float) v7_9));
            super.setFrameWidth(v12_7.getDimension(5, v1_5));
            super.setFrameHeight(v12_7.getDimension(4, v6_6));
            super.setTransOff(v12_7.getDimension(9, (super.getMetrics().density * 0)));
            super.setCornerColor(v12_7.getColor(2, -1));
            float v12_3 = new android.graphics.PorterDuffColorFilter(super.getCornerColor(), android.graphics.PorterDuff$Mode.DST_IN);
            if (super.getCornerColor() != -1) {
                v12_3 = new android.graphics.PorterDuffColorFilter(super.getCornerColor(), android.graphics.PorterDuff$Mode.SRC_IN);
            }
            super.getCornerPaint().setColorFilter(v12_3);
            super.a();
            if (v3_11 == 0) {
                float v11_1 = p11.getDrawable(2131232436);
                float v12_8 = android.graphics.Bitmap.createBitmap(v11_1.getIntrinsicWidth(), v11_1.getIntrinsicHeight(), android.graphics.Bitmap$Config.ARGB_8888);
                android.graphics.Canvas v1_19 = new android.graphics.Canvas(v12_8);
                v11_1.setBounds(0, 0, v1_19.getWidth(), v1_19.getHeight());
                v11_1.draw(v1_19);
                super.setConner(v12_8);
                super.setConnerSize((((float) 14) * super.getMetrics().density));
            }
        }
        return;
    }

    private final float getDefaultRound()
    {
        int v0_1;
        int v1_1 = 16;
        if (!kotlin.jvm.internal.Intrinsics.areEqual(this.getVendorId(), "hlb")) {
            vn.ai.trueid.sdk.navigation.TrueIDV3 v2 = vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE;
            if (v2.getConfigUI$trueid_release().has("borderRadius")) {
                v1_1 = v2.getConfigUI$trueid_release().getInt("borderRadius");
            }
            v0_1 = vn.ai.trueid.sdk.utils.Helpers.Companion.convertDpToPixel(v1_1);
        } else {
            v0_1 = vn.ai.trueid.sdk.utils.Helpers.Companion.convertDpToPixel(16);
        }
        return ((float) v0_1);
    }

    private final String getVendorId()
    {
        return ((String) this.w.getValue());
    }

    public final void a()
    {
        this.setFrame(new android.graphics.RectF((this.getCenterX() - (this.getFrameWidth() / ((float) 2))), (this.getCenterY() - (this.getFrameHeight() / ((float) 2))), ((this.getFrameWidth() / ((float) 2)) + this.getCenterX()), ((this.getFrameHeight() / ((float) 2)) + this.getCenterY())));
        this.setTransparentFrame(new android.graphics.RectF((this.getTransOff() + this.getFrame().left), (this.getTransOff() + this.getFrame().top), (this.getFrame().right - this.getTransOff()), (this.getFrame().bottom - this.getTransOff())));
        this.setFrameDetect(new android.graphics.RectF(0, (this.getFrame().top - this.getFrame().left), ((float) this.v), (this.getFrame().bottom + this.getFrame().left)));
        return;
    }

    public final android.graphics.Bitmap b(android.graphics.Bitmap p8)
    {
        int v0_3 = (((float) p8.getWidth()) / ((float) this.getWidth()));
        int v1_0 = (((float) p8.getHeight()) / ((float) this.getHeight()));
        int v2_5 = ((float) Math.sin(Math.toRadians(4618441417868443648)));
        int v3_4 = (((this.getTransparentFrame().height() * v1_0) * v2_5) / ((float) 2));
        float v5_4 = (((this.getTransparentFrame().width() * v0_3) * v2_5) / ((float) 2));
        int v2_11 = Og.x.coerceAtLeast(((int) ((this.getTransparentFrame().left * v0_3) - v3_4)), 0);
        long v4_3 = Og.x.coerceAtLeast(((int) ((this.getTransparentFrame().top * v1_0) - v5_4)), 0);
        int v0_4 = Og.x.coerceAtMost(((int) ((this.getTransparentFrame().right * v0_3) + v3_4)), p8.getWidth());
        int v1_4 = Og.x.coerceAtMost(((int) ((this.getTransparentFrame().bottom * v1_0) + v5_4)), p8.getHeight());
        if ((v0_4 > v2_11) && (v1_4 > v4_3)) {
            android.graphics.Bitmap v8_1 = android.graphics.Bitmap.createBitmap(p8, v2_11, v4_3, (v0_4 - v2_11), (v1_4 - v4_3));
            if (v8_1.getWidth() > 1036) {
                p8 = android.graphics.Bitmap.createScaledBitmap(v8_1, 1036, Og.x.coerceAtLeast(((int) ((((long) v8_1.getHeight()) * ((long) 1036)) / ((long) v8_1.getWidth()))), 1), 1);
            } else {
                return v8_1;
            }
        }
        return p8;
    }

    public final void draw(android.graphics.Canvas p10)
    {
        super.draw(p10);
        p10.drawColor(this.getMaskColor());
        if (this.s) {
            android.graphics.Paint v0_19 = new android.graphics.Paint();
            v0_19.setColor(Sk.c.f());
            p10.drawRoundRect(new android.graphics.RectF((this.getFrame().left - ((float) 10)), (this.getFrame().top - ((float) 10)), (this.getFrame().right + ((float) 10)), (this.getFrame().bottom + ((float) 10))), this.getDefaultRound(), this.getDefaultRound(), v0_19);
        }
        p10.drawRoundRect(this.getTransparentFrame(), this.getDefaultRound(), this.getDefaultRound(), this.getTransparentPaint());
        if (!kotlin.jvm.internal.Intrinsics.areEqual(this.getVendorId(), "bvb")) {
            p10.drawRoundRect(this.getFrame(), this.getDefaultRound(), this.getDefaultRound(), this.getDashPaint());
        }
        if (this.t) {
            android.graphics.Paint v0_18 = new android.graphics.Paint();
            v0_18.setColor(-1);
            v0_18.setAlpha(128);
            p10.drawRoundRect(this.getTransparentFrame(), this.getDefaultRound(), this.getDefaultRound(), v0_18);
        }
        if (this.getConner() != null) {
            android.graphics.Paint v0_22 = (this.getFrameWidth() / ((float) 4));
            float v5_5 = vn.ai.trueid.sdk.utils.Helpers.Companion;
            float v3_0 = new android.graphics.RectF((this.getFrame().left + ((float) v5_5.convertDpToPixel(20))), ((this.getFrame().bottom - ((float) v5_5.convertDpToPixel(20))) - ((((float) 4) * v0_22) / ((float) 3))), ((this.getFrame().left + ((float) v5_5.convertDpToPixel(20))) + v0_22), (this.getFrame().bottom - ((float) v5_5.convertDpToPixel(20))));
            android.graphics.Paint v0_4 = new android.graphics.Paint();
            v0_4.setStyle(android.graphics.Paint$Style.STROKE);
            v0_4.setColor(-1);
            v0_4.setStrokeWidth(this.getDashPaint().getStrokeWidth());
            float v2_7 = new float[2];
            v2_7 = {1101004800, 1101004800};
            v0_4.setPathEffect(new android.graphics.DashPathEffect(v2_7, 0));
            p10.drawRoundRect(v3_0, this.getDefaultRound(), this.getDefaultRound(), v0_4);
        }
        if (kotlin.jvm.internal.Intrinsics.areEqual(this.getVendorId(), "hlb")) {
            android.graphics.Paint v0_8 = new android.graphics.Paint();
            v0_8.setStyle(android.graphics.Paint$Style.STROKE);
            v0_8.setColor(Sk.c.k());
            v0_8.setStrokeWidth(1086324736);
            v0_8.setAntiAlias(1);
            p10.drawRoundRect(this.getTransparentFrame(), this.getDefaultRound(), this.getDefaultRound(), v0_8);
        }
        return;
    }

    public final android.graphics.RectF getFrameView()
    {
        return this.getTransparentFrame();
    }

    public final float getRoundRadius$trueid_release()
    {
        return this.u;
    }

    public final boolean getState()
    {
        return this.s;
    }

    public final boolean getUploading()
    {
        return this.t;
    }

    public final void setRoundRadius$trueid_release(float p1)
    {
        this.u = p1;
        return;
    }

    public final void setState(boolean p1)
    {
        this.s = p1;
        this.invalidate();
        return;
    }

    public final void setUploading(boolean p1)
    {
        this.t = p1;
        this.invalidate();
        return;
    }
}
