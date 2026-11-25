package vn.com.vng.zalopay.skewface.presentation.mlkit;
public class GraphicOverlay extends android.view.View {
    public final Object a;
    public final java.util.ArrayList b;
    public final android.graphics.Matrix c;
    public int d;
    public int e;
    public float f;
    public float g;
    public float h;
    public boolean i;
    public boolean j;

    public GraphicOverlay(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.a = new Object();
        super.b = new java.util.ArrayList();
        super.c = new android.graphics.Matrix();
        super.f = 1065353216;
        super.j = 1;
        super.addOnLayoutChangeListener(new Ek.A(1, super));
        return;
    }

    public final void a()
    {
        Throwable v1_3 = this.b.stream().findFirst();
        this.b.clear();
        java.util.ArrayList v2_1 = this.b;
        java.util.Objects.requireNonNull(v2_1);
        v1_3.ifPresent(new KN.b(0, v2_1));
        this.postInvalidate();
        return;
    }

    public final void b(int p5, int p6)
    {
        String v2_0;
        Object v1 = 0;
        if (p5 <= null) {
            v2_0 = 0;
        } else {
            v2_0 = 1;
        }
        p9.y6.i("image width must be positive", v2_0);
        if (p6 > 0) {
            v1 = 1;
        }
        p9.y6.i("image height must be positive", v1);
        this.d = p5;
        this.e = p6;
        this.i = 1;
        this.j = 1;
        this.postInvalidate();
        return;
    }

    public final void c()
    {
        if ((this.j) && ((this.d > 0) && (this.e > 0))) {
            int v0_1 = (((float) this.getWidth()) / ((float) this.getHeight()));
            float v1_4 = (((float) this.d) / ((float) this.e));
            this.g = 0;
            this.h = 0;
            if (v0_1 <= v1_4) {
                this.f = (((float) this.getHeight()) / ((float) this.e));
                this.g = (((((float) this.getHeight()) * v1_4) - ((float) this.getWidth())) / 1073741824);
            } else {
                this.f = (((float) this.getWidth()) / ((float) this.d));
                this.h = (((((float) this.getWidth()) / v1_4) - ((float) this.getHeight())) / 1073741824);
            }
            int v0_21 = this.c;
            v0_21.reset();
            v0_21.setScale(this.f, this.f);
            v0_21.postTranslate((- this.g), (- this.h));
            if (this.i) {
                v0_21.postScale(-1082130432, 1065353216, (((float) this.getWidth()) / 1073741824), (((float) this.getHeight()) / 1073741824));
            }
            this.j = 0;
        }
        return;
    }

    public int getImageHeight()
    {
        return this.e;
    }

    public int getImageWidth()
    {
        return this.d;
    }

    public float getPostScaleHeightOffset()
    {
        return this.h;
    }

    public float getPostScaleWidthOffset()
    {
        return this.g;
    }

    public float getScaleFactor()
    {
        return this.f;
    }

    public final void onDraw(android.graphics.Canvas p7)
    {
        super.onDraw(p7);
        this.c();
        java.util.Iterator v1_1 = this.b.iterator();
        while (v1_1.hasNext()) {
            android.graphics.Paint v2_1 = ((NN.a) v1_1.next());
            android.graphics.RectF v3 = v2_1.f;
            if (p7 != null) {
                boolean v4_0 = v2_1.d;
                android.graphics.Paint v5 = 0;
                if (!v4_0) {
                    v4_0 = 0;
                }
                p7.drawPaint(v4_0);
                boolean v4_1 = v2_1.e;
                if (!v4_1) {
                    v4_1 = 0;
                }
                android.graphics.Paint v2_2;
                p7.drawOval(v3, v4_1);
                if (!v2_1.g) {
                    v2_2 = v2_1.b;
                    if (v2_2 != null) {
                        v5 = v2_2;
                    }
                } else {
                    v2_2 = v2_1.c;
                    if (v2_2 != null) {
                    } else {
                    }
                }
                p7.drawOval(v3, v5);
            }
        }
        return;
    }
}
