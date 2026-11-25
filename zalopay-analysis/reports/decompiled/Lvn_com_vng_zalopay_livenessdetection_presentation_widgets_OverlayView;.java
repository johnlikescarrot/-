package vn.com.vng.zalopay.livenessdetection.presentation.widgets;
public final class OverlayView extends android.view.View implements kotlinx.coroutines.CoroutineScope {
    public static final synthetic int o;
    public android.graphics.RectF a;
    public android.graphics.Paint b;
    public android.graphics.Paint c;
    public android.graphics.Paint d;
    public android.graphics.Paint e;
    public com.airbnb.lottie.LottieAnimationView f;
    public final ng.S g;
    public boolean h;
    public float i;
    public final float j;
    public final ng.S k;
    public final gi.x0 l;
    public ID.a m;
    public final android.graphics.Paint n;

    public OverlayView(android.content.Context p5, android.util.AttributeSet p6)
    {
        com.airbnb.lottie.LottieAnimationView v5_1;
        super(p5, p6);
        super.g = ng.x.b(new ID.b(super, 0));
        android.view.ViewGroup v6_14 = new android.graphics.Paint();
        v6_14.setColor(-1);
        v6_14.setTextSize(1128792064);
        v6_14.setTextAlign(android.graphics.Paint$Align.CENTER);
        v6_14.setAntiAlias(1);
        android.view.ViewGroup v6_3 = new android.graphics.Paint();
        super.j = ((super.i * 1135869952) / 1120403456);
        super.k = ng.x.b(new ID.b(super, 1));
        super.l = gi.N.c();
        v6_3.setAntiAlias(1);
        android.view.ViewGroup v6_5 = 0;
        super.setLayerType(1, 0);
        com.airbnb.lottie.LottieAnimationView v2_7 = new com.airbnb.lottie.LottieAnimationView(p5);
        v2_7.setRepeatCount(-1);
        v2_7.setScaleType(android.widget.ImageView$ScaleType.CENTER_INSIDE);
        super.f = v2_7;
        if (!(p5 instanceof android.app.Activity)) {
            v5_1 = 0;
        } else {
            v5_1 = ((android.app.Activity) p5);
        }
        if (v5_1 != null) {
            com.airbnb.lottie.LottieAnimationView v5_2 = v5_1.findViewById(16908290);
            if ((v5_2 instanceof android.view.ViewGroup)) {
                v6_5 = ((android.view.ViewGroup) v5_2);
            }
            if (v6_5 != null) {
                v6_5.addView(super.f);
            }
        }
        com.airbnb.lottie.LottieAnimationView v5_5 = new android.graphics.Paint();
        v5_5.setAntiAlias(1);
        v5_5.setXfermode(new android.graphics.PorterDuffXfermode(android.graphics.PorterDuff$Mode.CLEAR));
        v5_5.setStyle(android.graphics.Paint$Style.STROKE);
        v5_5.setStrokeWidth(super.getResources().getDimension(2131165818));
        super.n = v5_5;
        return;
    }

    private final int getAlphaValue()
    {
        return ((Number) this.g.getValue()).intValue();
    }

    private final android.graphics.RectF getOvalRectF()
    {
        android.graphics.RectF v0 = this.a;
        if (v0 == null) {
            v0 = this.a();
        }
        return v0;
    }

    private final float getSweepAngleEraser()
    {
        return ((Number) this.k.getValue()).floatValue();
    }

    public final android.graphics.RectF a()
    {
        float v0_3 = ((float) this.getMeasuredWidth());
        float v2_2 = (1062333317 * v0_3);
        float v0_2 = ((v0_3 - v2_2) / 1073741824);
        float v1_0 = (((float) this.getMeasuredHeight()) * 1046562734);
        return new android.graphics.RectF(v0_2, v1_0, (v2_2 + v0_2), ((1067125067 * v2_2) + v1_0));
    }

    public kotlin.coroutines.CoroutineContext getCoroutineContext()
    {
        return kotlinx.coroutines.Dispatchers.getMain().plus(this.l);
    }

    public final boolean getCountdownActive()
    {
        return this.h;
    }

    public final android.graphics.RectF getOvalRect()
    {
        return this.getOvalRectF();
    }

    public final float getSweepAngle()
    {
        return this.j;
    }

    public final void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        int v0_0 = this.f;
        if (v0_0 != 0) {
            v0_0.cancelAnimation();
        }
        this.f = 0;
        return;
    }

    public final void onDraw(android.graphics.Canvas p15)
    {
        super.onDraw(p15);
        float v0_3 = new android.graphics.Rect(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
        android.graphics.Paint v1_1 = this.b;
        if (v1_1 != null) {
            p15.drawRect(v0_3, v1_1);
        }
        android.graphics.Canvas v8;
        android.graphics.Paint v7 = this.d;
        if (v7 == null) {
            v8 = p15;
        } else {
            p15.drawArc(this.getOvalRectF(), 0, this.getSweepAngleEraser(), 1, v7);
            v8 = p15;
        }
        v8.drawArc(this.getOvalRectF(), 0, this.getSweepAngleEraser(), 1, this.n);
        android.graphics.Paint v13_1 = this.e;
        if (v13_1 != null) {
            v8.drawArc(this.getOvalRectF(), -1028390912, ((this.i * 1135869952) / 1120403456), 0, v13_1);
        }
        android.graphics.Paint v13_2 = this.c;
        if (v13_2 != null) {
            v8.drawArc(this.getOvalRectF(), 0, this.getSweepAngleEraser(), 1, v13_2);
        }
        return;
    }

    public final void onSizeChanged(int p1, int p2, int p3, int p4)
    {
        super.onSizeChanged(p1, p2, p3, p4);
        this.a = this.a();
        return;
    }

    public final void setAnimationFromUrl(String p2)
    {
        com.airbnb.lottie.LottieAnimationView v0 = this.f;
        if (v0 != null) {
            v0.cancelAnimation();
            v0.setAnimationFromUrl(p2);
            v0.playAnimation();
        }
        return;
    }

    public final void setAnimationResource(int p4)
    {
        com.airbnb.lottie.LottieAnimationView v4_1 = new java.io.BufferedReader(new java.io.InputStreamReader(this.getContext().getResources().openRawResource(p4), kotlin.text.Charsets.UTF_8), 8192);
        try {
            Throwable v0_2 = com.google.android.gms.internal.mlkit_vision_barcode.Q4.c(v4_1);
            kotlin.io.CloseableKt.closeFinally(v4_1, 0);
            com.airbnb.lottie.LottieAnimationView v4_2 = this.f;
        } catch (Throwable v0_3) {
            try {
                throw v0_3;
            } catch (Throwable v1_1) {
                kotlin.io.CloseableKt.closeFinally(v4_2, v0_3);
                throw v1_1;
            }
        }
        if (v4_2 != null) {
            v4_2.cancelAnimation();
            Throwable v1_3 = new StringBuilder();
            v1_3.append(v0_2);
            v1_3.append("_animation_name");
            v4_2.setAnimationFromJson(v0_2, v1_3.toString());
            v4_2.playAnimation();
        }
        return;
    }

    public final void setCountdownActive(boolean p1)
    {
        this.h = p1;
        return;
    }

    public final void setCountdownListener(ID.a p1)
    {
        this.m = p1;
        return;
    }

    public final void setOvalColor(int p3)
    {
        android.graphics.Paint v0_1 = new android.graphics.Paint();
        v0_1.setColor(p3);
        v0_1.setStrokeWidth(this.getResources().getDimension(2131165818));
        v0_1.setStyle(android.graphics.Paint$Style.STROKE);
        this.d = v0_1;
        this.invalidate();
        return;
    }

    public final void setProgressSmoothly(int p7)
    {
        kotlinx.coroutines.BuildersKt.launch$default(this, 0, 0, new AA.g(this, p7, 0), 3, 0);
        return;
    }
}
