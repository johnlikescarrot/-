package vn.zalopay.ui;
public class ZPSubmitButton extends androidx.appcompat.widget.AppCompatButton {
    public static final synthetic int f;
    public final android.graphics.drawable.Drawable d;
    public final android.graphics.drawable.Drawable e;

    public ZPSubmitButton(android.content.Context p2, android.util.AttributeSet p3)
    {
        android.content.res.TypedArray v2_2;
        super(p2, p3);
        if (p3 != null) {
            v2_2 = super.getContext().obtainStyledAttributes(p3, sZ.h.u);
        } else {
            v2_2 = 0;
        }
        if (v2_2 != null) {
            super.d = v2_2.getDrawable(1);
            super.e = v2_2.getDrawable(0);
            v2_2.recycle();
            return;
        } else {
            return;
        }
    }

    public final boolean onTouchEvent(android.view.MotionEvent p4)
    {
        if ((this.isEnabled()) && ((this.isClickable()) && (this.isFocusable()))) {
            super.performClick();
            if (this.isEnabled()) {
                this.setEnabled(0);
            }
            this.postDelayed(new com.appsflyer.b(26, this), 300);
            return super.onTouchEvent(p4);
        } else {
            return 0;
        }
    }

    public void setEnabled(boolean p2)
    {
        android.graphics.drawable.Drawable v0;
        if (!p2) {
            v0 = this.e;
        } else {
            v0 = this.d;
        }
        if (v0 != null) {
            this.setBackgroundDrawable(v0);
        }
        super.setEnabled(p2);
        return;
    }
}
