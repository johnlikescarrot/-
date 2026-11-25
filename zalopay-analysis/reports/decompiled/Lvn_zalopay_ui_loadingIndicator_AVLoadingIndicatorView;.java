package vn.zalopay.ui.loadingIndicator;
public class AVLoadingIndicatorView extends android.view.View {
    public static int f = 25;
    public static int g = 20;
    public int a;
    public int b;
    public android.graphics.Paint c;
    public a9.f d;
    public boolean e;

    public AVLoadingIndicatorView(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.a(p2);
        return;
    }

    public final void a(android.util.AttributeSet p4)
    {
        a9.f v4_9 = this.getContext().obtainStyledAttributes(p4, sZ.h.a);
        this.a = v4_9.getInt(0, 0);
        this.b = v4_9.getColor(1, -1);
        vn.zalopay.ui.loadingIndicator.AVLoadingIndicatorView.g = this.getResources().getDimensionPixelSize(2131167095);
        v4_9.recycle();
        a9.f v4_2 = new android.graphics.Paint();
        this.c = v4_2;
        v4_2.setColor(this.b);
        this.c.setStyle(android.graphics.Paint$Style.FILL);
        this.c.setAntiAlias(1);
        if (this.a == 0) {
            this.d = new a9.f(9);
        }
        a9.f v4_8 = this.d;
        v4_8.getClass();
        v4_8.b = new ref.WeakReference(this);
        return;
    }

    public final void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (this.e) {
            this.d.H(1);
        }
        return;
    }

    public final void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        this.d.H(3);
        return;
    }

    public final void onDraw(android.graphics.Canvas p11)
    {
        float v2_0;
        super.onDraw(p11);
        a9.f v0 = this.d;
        android.graphics.Paint v1 = this.c;
        int v3 = 0;
        if (v0.y() == null) {
            v2_0 = 0;
        } else {
            v2_0 = v0.y().getWidth();
        }
        float v4_1;
        if (v0.y() == null) {
            v4_1 = 0;
        } else {
            v4_1 = v0.y().getHeight();
        }
        float v4_6;
        float v2_4 = ((((float) Math.min(v2_0, v4_1)) - 1101004800) / 1086324736);
        if (v0.y() == null) {
            v4_6 = 0;
        } else {
            v4_6 = v0.y().getWidth();
        }
        float v7_2;
        float v5_1 = (1073741824 * v2_4);
        float v4_10 = (((float) (v4_6 / 2)) - (v5_1 + 1092616192));
        if (v0.y() == null) {
            v7_2 = 0;
        } else {
            v7_2 = v0.y().getHeight();
        }
        float v7_5 = ((float) (v7_2 / 2));
        while (v3 < 3) {
            p11.save();
            int v8_1 = ((float) v3);
            p11.translate(((v8_1 * 1092616192) + ((v5_1 * v8_1) + v4_10)), v7_5);
            p11.scale(((float[]) v0.d)[v3], ((float[]) v0.d)[v3]);
            p11.drawCircle(0, 0, v2_4, v1);
            p11.restore();
            v3++;
        }
        return;
    }

    public final void onLayout(boolean p4, int p5, int p6, int p7, int p8)
    {
        void v4_1 = super.onLayout(p4, p5, p6, p7, p8);
        if (!v4_1.e) {
            v4_1.e = 1;
            a9.f v5_3 = v4_1.d;
            v5_3.getClass();
            java.util.ArrayList v6_2 = new java.util.ArrayList();
            int v8_2 = 0;
            while (v8_2 < 3) {
                android.animation.ValueAnimator v0_2 = new float[3];
                v0_2 = {1065353216, 1050253722, 1065353216};
                android.animation.ValueAnimator v0_3 = android.animation.ValueAnimator.ofFloat(v0_2);
                v0_3.setDuration(750);
                v0_3.setRepeatCount(-1);
                v0_3.setStartDelay(((long) new int[] {120, 240, 360})[v8_2]));
                v0_3.addUpdateListener(new PZ.a(v5_3, v8_2));
                v0_3.start();
                v6_2.add(v0_3);
                v8_2++;
            }
            v5_3.c = v6_2;
        }
        return;
    }

    public final void onMeasure(int p5, int p6)
    {
        int v0_8 = (android.view.View$MeasureSpec.getSize(p5) / (vn.zalopay.ui.loadingIndicator.AVLoadingIndicatorView.g / 2));
        vn.zalopay.ui.loadingIndicator.AVLoadingIndicatorView.f = v0_8;
        int v1_3 = (((int) this.getContext().getResources().getDisplayMetrics().density) * v0_8);
        int v0_1 = android.view.View$MeasureSpec.getMode(p5);
        int v5_1 = android.view.View$MeasureSpec.getSize(p5);
        if (v0_1 != 1073741824) {
            if (v0_1 == -2147483648) {
                v1_3 = Math.min(v1_3, v5_1);
            }
        } else {
            v1_3 = v5_1;
        }
        int v0_7 = (((int) this.getContext().getResources().getDisplayMetrics().density) * vn.zalopay.ui.loadingIndicator.AVLoadingIndicatorView.f);
        int v5_3 = android.view.View$MeasureSpec.getMode(p6);
        int v6_1 = android.view.View$MeasureSpec.getSize(p6);
        if (v5_3 != 1073741824) {
            if (v5_3 == -2147483648) {
                v0_7 = Math.min(v0_7, v6_1);
            }
        } else {
            v0_7 = v6_1;
        }
        this.setMeasuredDimension(v1_3, v0_7);
        return;
    }

    public void setVisibility(int p2)
    {
        if (this.getVisibility() != p2) {
            super.setVisibility(p2);
            if ((p2 != 8) && (p2 != 4)) {
                this.d.H(1);
                return;
            } else {
                this.d.H(2);
            }
        }
        return;
    }
}
