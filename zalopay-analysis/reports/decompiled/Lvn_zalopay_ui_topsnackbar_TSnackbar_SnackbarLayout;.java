package vn.zalopay.ui.topsnackbar;
public class TSnackbar$SnackbarLayout extends android.widget.LinearLayout {
    public android.widget.TextView a;
    public android.widget.TextView b;
    public final int c;
    public vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout$b d;
    public vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout$a e;

    public TSnackbar$SnackbarLayout(android.content.Context p4, android.util.AttributeSet p5)
    {
        super(p4, p5);
        int v5_2 = p4.obtainStyledAttributes(p5, sZ.h.p);
        super.c = v5_2.getDimensionPixelSize(0, -1);
        if (v5_2.hasValue(6)) {
            androidx.core.view.h0.k(super, ((float) v5_2.getDimensionPixelSize(6, 0)));
        }
        v5_2.recycle();
        super.setClickable(1);
        android.view.LayoutInflater.from(p4).inflate(2131560367, super);
        super.setAccessibilityLiveRegion(1);
        return;
    }

    public android.widget.TextView getActionView()
    {
        return this.b;
    }

    public android.widget.TextView getMessageView()
    {
        return this.a;
    }

    public final void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout$a v0 = this.e;
        if (v0 != null) {
            v0.b();
        }
        return;
    }

    public final void onFinishInflate()
    {
        super.onFinishInflate();
        this.a = ((android.widget.TextView) this.findViewById(2131364233));
        this.b = ((android.widget.TextView) this.findViewById(2131364232));
        return;
    }

    public final void onLayout(boolean p1, int p2, int p3, int p4, int p5)
    {
        if (p1 != 0) {
            vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout$b v2_2 = super.onLayout(p1, p2, p3, p4, p5).d;
            if (v2_2 != null) {
                v2_2.a();
            }
        }
        return;
    }

    public final void onMeasure(int p2, int p3)
    {
        super.onMeasure(p2, p3);
        int v2_1 = this.c;
        if ((v2_1 > 0) && (this.getMeasuredWidth() > v2_1)) {
            super.onMeasure(android.view.View$MeasureSpec.makeMeasureSpec(v2_1, 1073741824), p3);
        }
        return;
    }

    public void setOnAttachStateChangeListener(vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout$a p1)
    {
        this.e = p1;
        return;
    }

    public void setOnLayoutChangeListener(vn.zalopay.ui.topsnackbar.TSnackbar$SnackbarLayout$b p1)
    {
        this.d = p1;
        return;
    }
}
