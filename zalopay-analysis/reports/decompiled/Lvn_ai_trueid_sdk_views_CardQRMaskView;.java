package vn.ai.trueid.sdk.views;
public final class CardQRMaskView extends Sk.a {
    public float s;

    public CardQRMaskView(android.content.Context p18, android.util.AttributeSet p19)
    {
        super(p18, p19);
        int v3 = 0;
        super.setWillNotDraw(0);
        float v5_0 = 0;
        super.setLayerType(2, 0);
        if (p19 != null) {
            int v6_3;
            super.setMetrics(super.getResources().getDisplayMetrics());
            float v2_4 = p18.obtainStyledAttributes(p19, dk.a.a, 0, 0);
            super.setMaskColor(v2_4.getInt(6, 2131100450));
            super.setDashColor(v2_4.getInt(3, 2131099828));
            super.s = (v2_4.getDimension(7, 0) * super.getMetrics().density);
            super.setCenterX(v2_4.getDimension(0, (((float) super.getMetrics().widthPixels) / 1073741824)));
            super.getMetrics();
            super.setCenterY(v2_4.getDimension(1, (((float) super.getMetrics().heightPixels) / 1073741824)));
            super.getMetrics();
            super.getTransparentPaint().setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff$Mode.CLEAR));
            super.getDashPaint().setStyle(android.graphics.Paint$Style.STROKE);
            super.getDashPaint().setColor(v2_4.getInt(3, 2131099828));
            super.getDashPaint().setStrokeWidth(v2_4.getDimension(9, (super.getMetrics().density * ((float) 5))));
            int v7_17 = super.getDashPaint();
            int v14_2 = new float[2];
            v14_2 = {0, 0};
            v7_17.setPathEffect(new android.graphics.DashPathEffect(v14_2, 0));
            Sk.c.d();
            if (!vn.ai.trueid.sdk.navigation.TrueIDV3.INSTANCE.isTablet$trueid_release()) {
                v6_3 = (((float) super.getMetrics().widthPixels) * 1065353216);
            } else {
                v6_3 = (((float) (super.getMetrics().widthPixels / 3)) * 1073741824);
            }
            int v6_7 = ((v6_3 / ((float) 9)) * ((float) 5));
            super.setFrameWidth(v2_4.getDimension(5, v6_7));
            super.setFrameHeight(v2_4.getDimension(4, v6_7));
            int v6_11 = super.getContext().getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
            if (v6_11 == 0) {
                v6_11 = "TRUEID";
            }
            if (kotlin.jvm.internal.Intrinsics.areEqual(v6_11, "moonbase")) {
                super.setFrameWidth((((float) super.getMetrics().widthPixels) * 1060655596));
                super.setFrameHeight(super.getFrameWidth());
            }
            super.setTransOff(v2_4.getDimension(9, (super.getMetrics().density * 0)));
            super.setCornerColor(v2_4.getColor(2, -1));
            float v2_2 = new android.graphics.PorterDuffColorFilter(super.getCornerColor(), android.graphics.PorterDuff$Mode.DST_IN);
            if (super.getCornerColor() != -1) {
                v2_2 = new android.graphics.PorterDuffColorFilter(super.getCornerColor(), android.graphics.PorterDuff$Mode.SRC_IN);
            }
            super.getCornerPaint().setColorFilter(v2_2);
            super.a();
            android.graphics.Bitmap v1_1 = p18.getDrawable(2131235063);
            float v2_7 = android.graphics.Bitmap.createBitmap(v1_1.getIntrinsicWidth(), v1_1.getIntrinsicHeight(), android.graphics.Bitmap$Config.ARGB_8888);
            int v4_6 = new android.graphics.Canvas(v2_7);
            v1_1.setBounds(0, 0, v4_6.getWidth(), v4_6.getHeight());
            v1_1.draw(v4_6);
            super.setConner(v2_7);
            android.graphics.Bitmap v8_0 = super.getConner();
            android.graphics.Bitmap v1_3 = android.graphics.Color.parseColor("#FFAF01");
            float v2_8 = Sk.c.f();
            if (v8_0 != null) {
                int v11_0 = v8_0.getWidth();
                int v15_0 = v8_0.getHeight();
                int v4_7 = (v11_0 * v15_0);
                android.graphics.Bitmap v9_0 = new int[v4_7];
                v8_0.getPixels(v9_0, 0, v11_0, 0, 0, v11_0, v15_0);
                while (v3 < v4_7) {
                    float v5_1 = v9_0[v3];
                    if (v5_1 == v1_3) {
                        v5_1 = v2_8;
                    }
                    v9_0[v3] = v5_1;
                    v3++;
                }
                int v12_1 = v11_0;
                android.graphics.Bitmap v9_1 = android.graphics.Bitmap.createBitmap(v11_0, v15_0, v8_0.getConfig());
                v9_1.setPixels(v9_0, 0, v12_1, 0, 0, v12_1, v15_0);
                v5_0 = v9_1;
            }
            super.setConner(v5_0);
            super.setConnerSize((((float) 60) * super.getMetrics().density));
        }
        return;
    }

    public final void a()
    {
        this.setFrame(new android.graphics.RectF((this.getCenterX() - (this.getFrameWidth() / ((float) 2))), (this.getCenterY() - (this.getFrameHeight() / ((float) 2))), ((this.getFrameWidth() / ((float) 2)) + this.getCenterX()), ((this.getFrameHeight() / ((float) 2)) + this.getCenterY())));
        this.setTransparentFrame(new android.graphics.RectF((this.getTransOff() + this.getFrame().left), (this.getTransOff() + this.getFrame().top), (this.getFrame().right - this.getTransOff()), (this.getFrame().bottom - this.getTransOff())));
        return;
    }

    public final void draw(android.graphics.Canvas p15)
    {
        super.draw(p15);
        p15.drawColor(this.getMaskColor());
        android.graphics.Bitmap v0_2 = this.getContext().getSharedPreferences("vendor_prefs", 0).getString("vendor_id", 0);
        if (v0_2 == null) {
            v0_2 = "TRUEID";
        }
        if (!kotlin.jvm.internal.Intrinsics.areEqual(v0_2, "moonbase")) {
            p15.drawRoundRect(this.getTransparentFrame(), this.s, this.s, this.getTransparentPaint());
            p15.drawRoundRect(this.getFrame(), this.s, this.s, this.getDashPaint());
            float v6_31 = this.getConner();
            if (v6_31 != 0) {
                p15.drawBitmap(v6_31, 0, new android.graphics.RectF((this.getFrame().left - (this.getConnerSize() / ((float) 10))), (this.getFrame().top - (this.getConnerSize() / ((float) 10))), (this.getConnerSize() + this.getFrame().left), (this.getConnerSize() + this.getFrame().top)), this.getCornerPaint());
                android.graphics.Matrix v11_8 = new android.graphics.Matrix();
                v11_8.setRotate(1119092736);
                p15.drawBitmap(android.graphics.Bitmap.createBitmap(v6_31, 0, 0, v6_31.getWidth(), v6_31.getHeight(), v11_8, 1), 0, new android.graphics.RectF((this.getFrame().right - this.getConnerSize()), (this.getFrame().top - (this.getConnerSize() / ((float) 10))), ((this.getConnerSize() / ((float) 10)) + this.getFrame().right), (this.getConnerSize() + this.getFrame().top)), this.getCornerPaint());
                android.graphics.Matrix v11_1 = new android.graphics.Matrix();
                v11_1.setRotate(-1028390912);
                p15.drawBitmap(android.graphics.Bitmap.createBitmap(v6_31, 0, 0, v6_31.getWidth(), v6_31.getHeight(), v11_1, 1), 0, new android.graphics.RectF((this.getFrame().left - (this.getConnerSize() / ((float) 10))), (this.getFrame().bottom - this.getConnerSize()), (this.getConnerSize() + this.getFrame().left), ((this.getConnerSize() / ((float) 10)) + this.getFrame().bottom)), this.getCornerPaint());
                android.graphics.Matrix v11_3 = new android.graphics.Matrix();
                v11_3.setRotate(1127481344);
                p15.drawBitmap(android.graphics.Bitmap.createBitmap(v6_31, 0, 0, v6_31.getWidth(), v6_31.getHeight(), v11_3, 1), 0, new android.graphics.RectF((this.getFrame().right - this.getConnerSize()), (this.getFrame().bottom - this.getConnerSize()), ((this.getConnerSize() / ((float) 10)) + this.getFrame().right), ((this.getConnerSize() / ((float) 10)) + this.getFrame().bottom)), this.getCornerPaint());
            }
            return;
        } else {
            android.graphics.Bitmap v0_8 = this.getResources().getDisplayMetrics().density;
            float v2_2 = (1105199104 * v0_8);
            p15.drawRoundRect(this.getTransparentFrame(), v2_2, v2_2, this.getTransparentPaint());
            float v4_11 = new android.graphics.Paint(1);
            float v6_3 = android.graphics.Paint$Style.STROKE;
            v4_11.setStyle(v6_3);
            v4_11.setColor(-1);
            v4_11.setStrokeWidth((1069547520 * v0_8));
            p15.drawRoundRect(this.getFrame(), v2_2, v2_2, v4_11);
            float v4_12 = this.getFrame();
            int v8_11 = (1104465101 * v0_8);
            int v9_8 = new android.graphics.Paint(1);
            v9_8.setStyle(v6_3);
            v9_8.setColor(-1);
            v9_8.setStrokeWidth((v0_8 * 1078774989));
            v9_8.setStrokeCap(android.graphics.Paint$Cap.ROUND);
            v9_8.setStrokeJoin(android.graphics.Paint$Join.ROUND);
            android.graphics.Bitmap v0_14 = new android.graphics.Path();
            float v5_15 = (((float) 2) * v2_2);
            float v7_13 = v4_12.left;
            int v10_2 = v4_12.top;
            v0_14.addArc(new android.graphics.RectF(v7_13, v10_2, (v7_13 + v5_15), (v10_2 + v5_15)), 1127481344, 1119092736);
            v0_14.moveTo((v4_12.left + v2_2), v4_12.top);
            v0_14.lineTo(((v4_12.left + v2_2) + v8_11), v4_12.top);
            v0_14.moveTo(v4_12.left, (v4_12.top + v2_2));
            v0_14.lineTo(v4_12.left, ((v4_12.top + v2_2) + v8_11));
            float v6_13 = v4_12.right;
            int v10_3 = v4_12.top;
            v0_14.addArc(new android.graphics.RectF((v6_13 - v5_15), v10_3, v6_13, (v10_3 + v5_15)), 1132920832, 1119092736);
            v0_14.moveTo((v4_12.right - v2_2), v4_12.top);
            v0_14.lineTo(((v4_12.right - v2_2) - v8_11), v4_12.top);
            v0_14.moveTo(v4_12.right, (v4_12.top + v2_2));
            v0_14.lineTo(v4_12.right, ((v4_12.top + v2_2) + v8_11));
            float v6_22 = v4_12.left;
            float v7_15 = v4_12.bottom;
            v0_14.addArc(new android.graphics.RectF(v6_22, (v7_15 - v5_15), (v6_22 + v5_15), v7_15), 1119092736, 1119092736);
            v0_14.moveTo((v4_12.left + v2_2), v4_12.bottom);
            v0_14.lineTo(((v4_12.left + v2_2) + v8_11), v4_12.bottom);
            v0_14.moveTo(v4_12.left, (v4_12.bottom - v2_2));
            v0_14.lineTo(v4_12.left, ((v4_12.bottom - v2_2) - v8_11));
            float v6_30 = v4_12.right;
            int v10_5 = v4_12.bottom;
            v0_14.addArc(new android.graphics.RectF((v6_30 - v5_15), (v10_5 - v5_15), v6_30, v10_5), 0, 1119092736);
            v0_14.moveTo((v4_12.right - v2_2), v4_12.bottom);
            v0_14.lineTo(((v4_12.right - v2_2) - v8_11), v4_12.bottom);
            v0_14.moveTo(v4_12.right, (v4_12.bottom - v2_2));
            v0_14.lineTo(v4_12.right, ((v4_12.bottom - v2_2) - v8_11));
            p15.drawPath(v0_14, v9_8);
            return;
        }
    }

    public final android.graphics.RectF getFrameView()
    {
        return this.getTransparentFrame();
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
