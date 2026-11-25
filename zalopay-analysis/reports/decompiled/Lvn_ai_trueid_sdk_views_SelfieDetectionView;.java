package vn.ai.trueid.sdk.views;
public final class SelfieDetectionView extends Sk.a {
    public float A;
    public int s;
    public int t;
    public final android.graphics.Paint u;
    public final android.graphics.Bitmap v;
    public int w;
    public int x;
    public final boolean y;
    public Sk.b z;

    public SelfieDetectionView(android.content.Context p9, android.util.AttributeSet p10)
    {
        super(p9, p10);
        super.z = Sk.b.c;
        super.setWillNotDraw(0);
        long v2_10 = 0;
        super.setLayerType(2, 0);
        int v9_1 = p9.obtainStyledAttributes(p10, dk.a.b, 0, 0);
        super.setMetrics(super.getResources().getDisplayMetrics());
        super.setMaskColor(v9_1.getInt(8, 2131100450));
        super.setTransparentPaint(new android.graphics.Paint());
        super.getTransparentPaint().setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff$Mode.CLEAR));
        float v3_9 = android.graphics.Paint$Style.STROKE;
        super.getDashPaint().setStyle(v3_9);
        super.getDashPaint().setColor(v9_1.getInt(4, 2131099802));
        super.getDashPaint().setStrokeWidth(v9_1.getDimension(9, (((float) 1) * super.getMetrics().density)));
        float v10_23 = super.getDashPaint();
        android.graphics.PorterDuff$Mode v6_3 = new float[2];
        v6_3 = {1101004800, 1101004800};
        v10_23.setPathEffect(new android.graphics.DashPathEffect(v6_3, 0));
        float v10_25 = new android.graphics.Paint();
        super.u = v10_25;
        v10_25.setStyle(v3_9);
        float v10_26 = super.u;
        if (v10_26 == 0) {
            v10_26 = 0;
        }
        v10_26.setColor(v9_1.getInt(5, -16711936));
        float v10_27 = super.u;
        if (v10_27 != 0) {
            v2_10 = v10_27;
        }
        v2_10.setStrokeWidth((super.getMetrics().density * ((float) 2)));
        super.setCornerColor(v9_1.getColor(3, -1));
        long v2_14 = new android.graphics.PorterDuffColorFilter(super.getCornerColor(), android.graphics.PorterDuff$Mode.DST_IN);
        if (super.getCornerColor() != -1) {
            v2_14 = new android.graphics.PorterDuffColorFilter(super.getCornerColor(), android.graphics.PorterDuff$Mode.SRC_IN);
        }
        super.getCornerPaint().setColorFilter(v2_14);
        float v1_0 = v9_1.getBoolean(2, 0);
        super.y = v1_0;
        if (v1_0 != 0) {
            super.v = android.graphics.BitmapFactory.decodeResource(super.getResources(), 2131235063);
        }
        super.setFrameWidth(((float) (((double) super.getMetrics().widthPixels) * 4604480259023595110)));
        super.setFrameHeight(((float) (((double) super.getFrameWidth()) * 4608983858650965606)));
        super.setCenterX(v9_1.getDimension(0, ((float) (((double) super.getMetrics().widthPixels) * 4602678819172646912))));
        super.setCenterY(v9_1.getDimension(1, ((float) (((double) super.getMetrics().heightPixels) * 4602678819172646912))));
        if (((((float) super.getMetrics().widthPixels) - super.getCenterX()) < (super.getFrameWidth() / ((float) 2))) || ((super.getCenterX() - (super.getFrameWidth() / ((float) 2))) < 0)) {
            super.setCenterX(((float) (((double) super.getMetrics().widthPixels) * 4602678819172646912)));
        }
        if (((((float) super.getMetrics().heightPixels) - super.getCenterY()) < (super.getFrameHeight() / ((float) 2))) || ((super.getCenterY() - (super.getFrameHeight() / ((float) 2))) < 0)) {
            super.setCenterY(((float) (((double) super.getMetrics().heightPixels) * 4602678819172646912)));
        }
        super.setFrame(new android.graphics.RectF((super.getCenterX() - (super.getFrameWidth() / ((float) 2))), (super.getCenterY() - (super.getFrameHeight() / ((float) 2))), ((super.getFrameWidth() / ((float) 2)) + super.getCenterX()), ((super.getFrameHeight() / ((float) 2)) + super.getCenterY())));
        super.setTransOff(v9_1.getDimension(10, (super.getMetrics().density * 1084227584)));
        super.setTransOff((((float) 6) * super.getMetrics().density));
        super.setTransparentFrame(new android.graphics.RectF((super.getTransOff() + super.getFrame().left), (super.getTransOff() + super.getFrame().top), (super.getFrame().right - super.getTransOff()), (super.getFrame().bottom - super.getTransOff())));
        super.b(((int) ((((double) super.getFrame().width()) * 4605831338911806259) + ((double) super.getFrame().left))));
        return;
    }

    public final void a()
    {
        if (((((float) this.getMetrics().widthPixels) - this.getCenterX()) < (this.getFrameWidth() / ((float) 2))) || ((this.getCenterX() - (this.getFrameWidth() / ((float) 2))) < 0)) {
            this.setCenterX(((float) (((double) this.getMetrics().widthPixels) * 4602678819172646912)));
        }
        if (((((float) this.getMetrics().heightPixels) - this.getCenterY()) < (this.getFrameHeight() / ((float) 2))) || ((this.getCenterY() - (this.getFrameHeight() / ((float) 2))) < 0)) {
            this.setCenterY(((float) (((double) this.getMetrics().heightPixels) * 4602678819172646912)));
        }
        this.setFrame(new android.graphics.RectF((this.getCenterX() - (this.getFrameWidth() / ((float) 2))), (this.getCenterY() - (this.getFrameHeight() / ((float) 2))), ((this.getFrameWidth() / ((float) 2)) + this.getCenterX()), ((this.getFrameHeight() / ((float) 2)) + this.getCenterY())));
        this.setTransparentFrame(new android.graphics.RectF((this.getTransOff() + this.getFrame().left), (this.getTransOff() + this.getFrame().top), (this.getFrame().right - this.getTransOff()), (this.getFrame().bottom - this.getTransOff())));
        this.b(((int) ((((double) this.getFrame().width()) * 4605831338911806259) + ((double) this.getFrame().left))));
        return;
    }

    public final void b(int p19)
    {
        double v2_2 = ((double) this.getFrame().height());
        double v4_1 = ((double) this.getFrame().width());
        double v6_2 = ((double) this.getFrame().centerX());
        double v8_2 = ((double) this.getFrame().centerY());
        this.w = p19;
        if ((((((float) this.getMetrics().widthPixels) - this.getFrame().width()) / ((float) 2)) > ((float) p19)) || ((this.getFrame().width() + ((((float) this.getMetrics().widthPixels) - this.getFrame().width()) / ((float) 2))) < ((float) p19))) {
            this.w = ((int) ((((double) this.getFrame().width()) * 4606281698874543309) + ((double) this.getFrame().left)));
        }
        int v1_9 = this.w;
        double v2_3 = (v2_2 / ((double) 2));
        this.x = ((int) ((- Math.sqrt((((((double) 1) - (((((double) v1_9) - v6_2) * (((double) v1_9) - v6_2)) / ((v4_1 / ((double) 2)) * (v4_1 / ((double) 2))))) * v2_3) * v2_3))) + v8_2));
        return;
    }

    public final android.graphics.RectF getDetectFrame()
    {
        return this.getFrame();
    }

    public final float getProcess()
    {
        return this.A;
    }

    public final int getScreenHeight()
    {
        return this.t;
    }

    public final int getScreenWidth()
    {
        return this.s;
    }

    public final Sk.b getStatus()
    {
        return this.z;
    }

    public final void onDraw(android.graphics.Canvas p20)
    {
        android.graphics.Paint v6_8;
        super.onDraw(p20);
        float v4_29 = (this.A * ((float) 360));
        p20.drawColor(this.getMaskColor());
        p20.drawOval(this.getTransparentFrame(), this.getTransparentPaint());
        p20.drawOval(this.getFrame(), this.getDashPaint());
        android.graphics.Bitmap v2_20 = this.getFrame();
        float v7_0 = 0;
        android.graphics.Paint v3_19 = this.u;
        if (v3_19 != null) {
            v6_8 = v3_19;
        } else {
            v6_8 = 0;
        }
        p20.drawArc(v2_20, -1028390912, v4_29, 0, v6_8);
        android.graphics.Bitmap v2_23 = this.z;
        float v5_12 = (((double) this.getMetrics().density) * ((double) 32));
        android.graphics.Paint v3_25 = (((double) 32) * ((double) this.getMetrics().density));
        android.graphics.Bitmap$Config v8_4 = new android.graphics.Paint();
        v8_4.setStyle(android.graphics.Paint$Style.STROKE);
        v8_4.setStrokeWidth((((float) 2) * this.getMetrics().density));
        if (v2_23 != Sk.b.a) {
            if (v2_23 == Sk.b.b) {
                v8_4.setColor(-65536);
                p20.drawOval(this.getFrame(), v8_4);
                android.graphics.Bitmap v2_29 = android.graphics.Bitmap.createScaledBitmap(android.graphics.BitmapFactory.decodeResource(this.getResources(), 2131235064).copy(android.graphics.Bitmap$Config.ARGB_8888, 1), ((int) v5_12), ((int) v3_25), 1);
                p20.drawBitmap(v2_29, ((float) (this.w - (v2_29.getWidth() / 2))), ((float) (this.x - (v2_29.getHeight() / 2))), 0);
            }
        } else {
            v8_4.setColor(-16711936);
            p20.drawOval(this.getFrame(), v8_4);
            android.graphics.Bitmap v2_1 = android.graphics.Bitmap.createScaledBitmap(android.graphics.BitmapFactory.decodeResource(this.getResources(), 2131235065).copy(android.graphics.Bitmap$Config.ARGB_8888, 1), ((int) v5_12), ((int) v3_25), 1);
            p20.drawBitmap(v2_1, ((float) (this.w - (v2_1.getWidth() / 2))), ((float) (this.x - (v2_1.getHeight() / 2))), 0);
        }
        if (this.y) {
            android.graphics.Paint v3_6 = (this.getMetrics().density * ((float) 14));
            float v4_5 = this.getMetrics();
            float v4_7 = this.v;
            if (v4_7 != 0) {
                v7_0 = v4_7;
            }
            android.graphics.Bitmap v12 = android.graphics.Bitmap.createScaledBitmap(v7_0, ((int) v3_6), ((int) (((float) 14) * v4_5.density)), 1);
            int v15 = v12.getWidth();
            int v16 = v12.getHeight();
            android.graphics.Paint v3_10 = ((float) (v15 / 2));
            float v5_4 = ((float) (v16 / 2));
            p20.drawBitmap(v12, (this.getFrame().left - v3_10), (this.getFrame().top - v5_4), this.getCornerPaint());
            android.graphics.Bitmap v2_12 = new android.graphics.Matrix();
            v2_12.setRotate(1119092736);
            p20.drawBitmap(android.graphics.Bitmap.createBitmap(v12, 0, 0, v15, v16, v2_12, 1), (this.getFrame().width() + (this.getFrame().left - v3_10)), (this.getFrame().top - v5_4), this.getCornerPaint());
            android.graphics.Bitmap v2_16 = new android.graphics.Matrix();
            v2_16.setRotate(-1028390912);
            p20.drawBitmap(android.graphics.Bitmap.createBitmap(v12, 0, 0, v15, v16, v2_16, 1), (this.getFrame().left - v3_10), (this.getFrame().height() + (this.getFrame().top - v5_4)), this.getCornerPaint());
            android.graphics.Bitmap v2_19 = new android.graphics.Matrix();
            v2_19.setRotate(1127481344);
            p20.drawBitmap(android.graphics.Bitmap.createBitmap(v12, 0, 0, v15, v16, v2_19, 1), (this.getFrame().width() + ((float) ((int) (this.getFrame().left - v3_10)))), (this.getFrame().height() + ((float) ((int) (this.getFrame().top - v5_4)))), this.getCornerPaint());
        }
        return;
    }

    public final void setProcess(float p4)
    {
        float v0_0 = 0;
        if (p4 >= 0) {
            if (p4 <= 1065353216) {
                v0_0 = p4;
            } else {
                v0_0 = 1065353216;
            }
        }
        this.A = v0_0;
        if (p4 < 1065353216) {
            this.setStatus(Sk.b.c);
            this.invalidate();
            return;
        } else {
            this.setStatus(Sk.b.a);
            return;
        }
    }

    public final void setScreenHeight(int p1)
    {
        this.t = p1;
        return;
    }

    public final void setScreenWidth(int p1)
    {
        this.s = p1;
        return;
    }

    public final void setStatus(Sk.b p1)
    {
        this.z = p1;
        this.invalidate();
        return;
    }
}
