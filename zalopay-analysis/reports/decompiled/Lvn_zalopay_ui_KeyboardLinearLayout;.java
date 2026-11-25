package vn.zalopay.ui;
public class KeyboardLinearLayout extends android.widget.LinearLayout {
    public final Ai.D a;

    public KeyboardLinearLayout(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.a = new Ai.D(super);
        return;
    }

    public final void onLayout(boolean p1, int p2, int p3, int p4, int p5)
    {
        android.view.ViewGroup v2_1 = super.onLayout(p1, p2, p3, p4, p5).a;
        android.graphics.Rect v3_2 = ((android.graphics.Rect) v2_1.d);
        if (v3_2.height() == 0) {
            ((android.view.ViewGroup) v2_1.f).getWindowVisibleDisplayFrame(v3_2);
        }
        return;
    }

    public void setOnKeyboardStateListener(OZ.e p2)
    {
        this.a.e = p2;
        return;
    }
}
