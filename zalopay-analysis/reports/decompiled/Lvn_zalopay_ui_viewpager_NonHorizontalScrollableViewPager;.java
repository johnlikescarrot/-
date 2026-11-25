package vn.zalopay.ui.viewpager;
public class NonHorizontalScrollableViewPager extends vn.zalopay.ui.WrapContentHeightViewPager {

    public NonHorizontalScrollableViewPager(android.content.Context p1, android.util.AttributeSet p2)
    {
        super(p1, p2);
        return;
    }

    public final boolean onInterceptTouchEvent(android.view.MotionEvent p1)
    {
        return 0;
    }

    public final boolean onTouchEvent(android.view.MotionEvent p1)
    {
        return 0;
    }
}
