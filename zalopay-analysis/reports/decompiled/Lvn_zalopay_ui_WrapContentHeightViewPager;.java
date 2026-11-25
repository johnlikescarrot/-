package vn.zalopay.ui;
public class WrapContentHeightViewPager extends androidx.viewpager.widget.ViewPager {
    public final boolean K;

    public WrapContentHeightViewPager(android.content.Context p3, android.util.AttributeSet p4)
    {
        super(p3, p4);
        super.K = 1;
        android.content.res.TypedArray v3_1 = p3.obtainStyledAttributes(p4, sZ.h.r);
        super.K = v3_1.getBoolean(0, 1);
        v3_1.recycle();
        return;
    }

    public boolean onInterceptTouchEvent(android.view.MotionEvent p2)
    {
        if ((!this.K) || (!super.onInterceptTouchEvent(p2))) {
            return 0;
        } else {
            return 1;
        }
    }

    public final void onMeasure(int p5, int p6)
    {
        int v0_0 = android.view.View$MeasureSpec.getMode(p6);
        if ((v0_0 == 0) || (v0_0 == -2147483648)) {
            super.onMeasure(p5, p6);
            int v0_1 = 0;
            int v1_0 = 0;
            while (v0_1 < this.getChildCount()) {
                int v2_1 = this.getChildAt(v0_1);
                v2_1.measure(p5, android.view.View$MeasureSpec.makeMeasureSpec(0, 0));
                int v2_2 = v2_1.getMeasuredHeight();
                if (v2_2 > v1_0) {
                    v1_0 = v2_2;
                }
                v0_1++;
            }
            p6 = android.view.View$MeasureSpec.makeMeasureSpec(v1_0, 1073741824);
        }
        super.onMeasure(p5, p6);
        return;
    }

    public boolean onTouchEvent(android.view.MotionEvent p2)
    {
        if (this.K) {
            super.onTouchEvent(p2);
        }
        return 0;
    }
}
