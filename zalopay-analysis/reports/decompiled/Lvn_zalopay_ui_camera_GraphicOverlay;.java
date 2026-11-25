package vn.zalopay.ui.camera;
public class GraphicOverlay extends android.view.View {
    public final Object a;
    public final java.util.HashSet b;

    public GraphicOverlay(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.a = new Object();
        super.b = new java.util.HashSet();
        return;
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
