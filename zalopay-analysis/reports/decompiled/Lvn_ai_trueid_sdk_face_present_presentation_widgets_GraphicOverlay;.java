package vn.ai.trueid.sdk.face.present.presentation.widgets;
public class GraphicOverlay extends android.view.View {
    public final Object a;
    public final java.util.ArrayList b;

    public GraphicOverlay(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.a = new Object();
        super.b = new java.util.ArrayList();
        new android.graphics.Matrix();
        super.addOnLayoutChangeListener(new Ek.A(9, super));
        return;
    }

    private synthetic void a(android.view.View p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9)
    {
        return;
    }

    public static synthetic void a(vn.ai.trueid.sdk.face.present.presentation.widgets.GraphicOverlay p0, android.view.View p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, int p9)
    {
        p0.a(p1, p2, p3, p4, p5, p6, p7, p8, p9);
        return;
    }

    public int getImageHeight()
    {
        return 0;
    }

    public int getImageWidth()
    {
        return 0;
    }

    public final void onDraw(android.graphics.Canvas p3)
    {
        super.onDraw(p3);
        int v0_3 = this.b.iterator();
        if (v0_3.hasNext()) {
            vn.ai.trueid.sdk.face.d0.a(v0_3.next());
            throw 0;
        } else {
            return;
        }
    }
}
