package vn.ai.trueid.sdk.facev3.present.presentation.widgets;
public class GraphicOverlay extends android.view.View {
    public final Object a;
    public final java.util.ArrayList b;

    public GraphicOverlay(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.a = new Object();
        super.b = new java.util.ArrayList();
        new android.graphics.Matrix();
        super.addOnLayoutChangeListener(new Dk.c());
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
        ClassCastException v0_5 = this.b.iterator();
        if (v0_5.hasNext()) {
            if (v0_5.next() != null) {
                throw new ClassCastException();
            } else {
                throw 0;
            }
        } else {
            return;
        }
    }
}
