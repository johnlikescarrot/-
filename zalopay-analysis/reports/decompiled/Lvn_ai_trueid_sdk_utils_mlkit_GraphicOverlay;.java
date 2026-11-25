package vn.ai.trueid.sdk.utils.mlkit;
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
        super.addOnLayoutChangeListener(new Ek.A(3, super));
        return;
    }

    public final void a()
    {
        this.b.clear();
        this.postInvalidate();
        return;
    }

    public final void b(int p2, int p3, boolean p4)
    {
        this.d = p2;
        this.e = p3;
        this.i = p4;
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

    public final void onDraw(android.graphics.Canvas p4)
    {
        super.onDraw(p4);
        this.c();
        java.util.Iterator v1_1 = this.b.iterator();
        while (v1_1.hasNext()) {
            ((Rk.a) v1_1.next()).a(p4);
        }
        return;
    }
}
