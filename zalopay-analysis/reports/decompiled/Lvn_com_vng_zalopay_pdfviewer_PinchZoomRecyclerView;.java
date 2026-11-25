package vn.com.vng.zalopay.pdfviewer;
public final class PinchZoomRecyclerView extends androidx.recyclerview.widget.RecyclerView {
    public int a;
    public final android.view.ScaleGestureDetector b;
    public float c;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;

    public PinchZoomRecyclerView(android.content.Context p2, android.util.AttributeSet p3)
    {
        super(p2, p3);
        super.a = -1;
        super.c = 1065353216;
        if (!super.isInEditMode()) {
            super.b = new android.view.ScaleGestureDetector(super.getContext(), new pI.h(super));
        }
        return;
    }

    public final void dispatchDraw(android.graphics.Canvas p3)
    {
        p3.save();
        if (this.c == 1065353216) {
            this.h = 0;
            this.i = 0;
        }
        p3.translate(this.h, this.i);
        p3.scale(this.c, this.c);
        super.dispatchDraw(p3);
        p3.restore();
        this.invalidate();
        return;
    }

    public final void onDraw(android.graphics.Canvas p3)
    {
        super.onDraw(p3);
        p3.save();
        p3.translate(this.h, this.i);
        p3.scale(this.c, this.c);
        p3.restore();
        return;
    }

    public final boolean onInterceptTouchEvent(android.view.MotionEvent p1)
    {
        try {
            return super.onInterceptTouchEvent(p1);
        } catch (int v1_2) {
            v1_2.printStackTrace();
            return 0;
        }
    }

    public final void onMeasure(int p2, int p3)
    {
        this.j = ((float) android.view.View$MeasureSpec.getSize(p2));
        this.k = ((float) android.view.View$MeasureSpec.getSize(p3));
        super.onMeasure(p2, p3);
        return;
    }

    public final boolean onTouchEvent(android.view.MotionEvent p8)
    {
        super.onTouchEvent(p8);
        float v0_1 = p8.getAction();
        this.b.onTouchEvent(p8);
        int v1_3 = (v0_1 & 255);
        int v2_4 = 0;
        if (v1_3 == 0) {
            float v0_0 = p8.getX();
            int v1_0 = p8.getY();
            this.f = v0_0;
            this.g = v1_0;
            this.a = p8.getPointerId(0);
        } else {
            if (v1_3 == 1) {
                this.a = -1;
            } else {
                if (v1_3 == 2) {
                    float v0_3 = ((v0_1 & 65280) >> 8);
                    int v1_1 = p8.getX(v0_3);
                    int v8_2 = p8.getY(v0_3);
                    int v2_1 = (v8_2 - this.g);
                    float v4_2 = (this.h + (v1_1 - this.f));
                    this.h = v4_2;
                    float v0_7 = (this.i + v2_1);
                    this.i = v0_7;
                    if (v4_2 <= 0) {
                        float v5_2 = this.d;
                        if (v4_2 < v5_2) {
                            this.h = v5_2;
                        }
                    } else {
                        this.h = 0;
                    }
                    if (v0_7 <= 0) {
                        int v2_3 = this.e;
                        if (v0_7 < v2_3) {
                            this.i = v2_3;
                        }
                    } else {
                        this.i = 0;
                    }
                    this.f = v1_1;
                    this.g = v8_2;
                    this.invalidate();
                } else {
                    if (v1_3 == 3) {
                        this.a = -1;
                    } else {
                        if (v1_3 == 6) {
                            float v0_10 = ((v0_1 & 65280) >> 8);
                            if (p8.getPointerId(v0_10) == this.a) {
                                if (v0_10 == 0) {
                                    v2_4 = 1;
                                }
                                this.f = p8.getX(v2_4);
                                this.g = p8.getY(v2_4);
                                this.a = p8.getPointerId(v2_4);
                            }
                        }
                    }
                }
            }
        }
        return 1;
    }
}
