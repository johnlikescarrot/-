package vn.zalopay.ui.edittext;
public final class CustomTextInputEditText extends androidx.appcompat.widget.AppCompatEditText {
    public GZ.p g;
    public android.graphics.Rect h;

    public CustomTextInputEditText(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        super.h = new android.graphics.Rect();
        return;
    }

    public final boolean onTouchEvent(android.view.MotionEvent p5)
    {
        if ((p5 == 0) || (p5.getAction() != 0)) {
            return super.onTouchEvent(p5);
        } else {
            int v1_1 = ((int) p5.getY());
            int v2 = (v1_1 - 25);
            int v3_1 = (this.getWidth() - (((int) p5.getX()) + 40));
            if (v3_1 <= 0) {
                v3_1 += 40;
            }
            if (v2 > 0) {
                v1_1 = v2;
            }
            if (this.h.contains(v3_1, v1_1)) {
                int v0_4 = this.g;
                if (v0_4 != 0) {
                    if (v0_4 != 0) {
                        v0_4.a();
                    }
                    p5.setAction(3);
                    return 0;
                }
            }
            return super.onTouchEvent(p5);
        }
    }

    public final void setCallBack(GZ.p p1)
    {
        this.g = p1;
        return;
    }

    public final void setCompoundDrawables(android.graphics.drawable.Drawable p2, android.graphics.drawable.Drawable p3, android.graphics.drawable.Drawable p4, android.graphics.drawable.Drawable p5)
    {
        if (p4 != null) {
            this.h = p4.getBounds();
        }
        super.setCompoundDrawables(p2, p3, p4, p5);
        return;
    }
}
